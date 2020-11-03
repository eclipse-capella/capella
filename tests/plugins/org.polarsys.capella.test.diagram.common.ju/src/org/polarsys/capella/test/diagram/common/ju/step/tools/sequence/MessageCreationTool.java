/*******************************************************************************
 * Copyright (c) 2016, 2020 THALES GLOBAL SERVICES.
 * 
 * This program and the accompanying materials are made available under the
 * terms of the Eclipse Public License 2.0 which is available at
 * http://www.eclipse.org/legal/epl-2.0
 * 
 * SPDX-License-Identifier: EPL-2.0
 * 
 * Contributors:
 *    Thales - initial API and implementation
 *******************************************************************************/
package org.polarsys.capella.test.diagram.common.ju.step.tools.sequence;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;

import java.util.Map;

import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.osgi.util.NLS;
import org.eclipse.sirius.diagram.DDiagram;
import org.eclipse.sirius.diagram.DDiagramElement;
import org.polarsys.capella.common.helpers.EObjectExt;
import org.polarsys.capella.common.helpers.EObjectLabelProviderHelper;
import org.polarsys.capella.core.data.cs.ExchangeItemAllocation;
import org.polarsys.capella.core.data.fa.ComponentExchange;
import org.polarsys.capella.core.data.fa.FunctionalExchange;
import org.polarsys.capella.core.data.interaction.MessageKind;
import org.polarsys.capella.core.data.interaction.SequenceMessage;
import org.polarsys.capella.core.model.handler.helpers.RepresentationHelper;
import org.polarsys.capella.test.diagram.common.ju.context.DiagramContext;
import org.polarsys.capella.test.diagram.common.ju.headless.HeadlessResultOpProvider;
import org.polarsys.capella.test.diagram.common.ju.headless.IHeadlessResult;
import org.polarsys.capella.test.diagram.common.ju.step.tools.CreateDEdgeTool;
import org.polarsys.capella.test.diagram.common.ju.wrapper.utils.DiagramHelper;
import org.polarsys.capella.test.framework.api.CommonTestMessages;

public class MessageCreationTool extends CreateDEdgeTool {
  private MessageKind messageKind = null;
  private boolean hasReturnBranch = false;
  private int numCountChecks;

  public MessageCreationTool(DiagramContext context, String toolName, String sourceId, String targetId) {
    super(context, toolName, sourceId, targetId);
    this.numCountChecks = 1;
  }

  public MessageCreationTool(DiagramContext context, String toolName, String id, String sourceId, String targetId) {
    super(context, toolName, sourceId, targetId, id);
    this.numCountChecks = 2;
  }

  public MessageCreationTool(DiagramContext context, String toolName, String id, String sourceId, String targetId,
      boolean returnBranch) {
    super(context, toolName, sourceId, targetId, id);
    this.hasReturnBranch = returnBranch;
    this.numCountChecks = 2;
  }

  ///////////////// MSG_KIND ///////////
  public MessageCreationTool(DiagramContext context, String toolName, String sourceId, String targetId,
      MessageKind messageKind) {
    super(context, toolName, sourceId, targetId);
    this.messageKind = messageKind;
    this.numCountChecks = 2;
  }

  public MessageCreationTool(DiagramContext context, String toolName, String id, String sourceId, String targetId,
      MessageKind messageKind) {
    super(context, toolName, sourceId, targetId, id);
    this.messageKind = messageKind;
    this.numCountChecks = 3;
  }

  public MessageCreationTool(DiagramContext context, String toolName, String id, String sourceId, String targetId,
      MessageKind messageKind, boolean returnBranch) {
    this(context, toolName, sourceId, targetId, id);
    this.hasReturnBranch = returnBranch;
  }

  /**
   * @see org.polarsys.capella.test.diagram.common.ju.steps.AbstractExecuteToolCmdStep.tool.AbstractExecuteToolCmdTest#preTestRun()
   */
  @Override
  protected void preRunTest() {
    HeadlessResultOpProvider.INSTANCE.setCurrentOp(createOperation());
    super.preRunTest();
  }

  /**
   * @return
   */
  protected IHeadlessResult createOperation() {
    return new IHeadlessResult() {

      @Override
      public Object getResult(java.util.Collection<? extends EObject> selections, Map<String, Object> parameters) {
        return getExecutionContext().getSemanticElement(_newIdentifier);
      }
    };
  }

  /**
   * @see org.polarsys.capella.test.common.AbstractExtendedTest#postTestRun()
   */
  @SuppressWarnings("synthetic-access")
  @Override
  protected void postRunTest() {
    super.postRunTest();

    DDiagram diagram = getDiagramContext().getDiagram();
    DiagramHelper.refreshDiagram(diagram);

    EObject exchangeToAdd = null;
    String failMessage = "";
    if (_newIdentifier != null) {
      exchangeToAdd = getExecutionContext().getSemanticElement(_newIdentifier);
      NLS.bind(CommonTestMessages.objectRepresentationNotAvailableOnDiagram,
          EObjectLabelProviderHelper.getText(exchangeToAdd), EObjectExt.getText(diagram));
    } else {
      failMessage = "Message Creation postconditions failed";
    }

    // one edge should be created or two edges should be created if it has return branch
    if (_newEdgesElements.size() == getNoEdgesCreated()) {
      DDiagramElement view = _newEdgesElements.iterator().next();
      EList<EObject> semanticElements = view.getSemanticElements();
      int countChecks = 0;
      for (EObject semanticElement : semanticElements) {
        if (exchangeToAdd != null && (semanticElement instanceof FunctionalExchange
            || semanticElement instanceof ComponentExchange || semanticElement instanceof ExchangeItemAllocation)) {
          // and one of its semantic elements must be the added functional Exchange
          assertEquals(failMessage, exchangeToAdd, semanticElement);
          countChecks++;
        }
        if (semanticElement instanceof SequenceMessage) {
          countChecks++;
        }
        if (messageKind != null && semanticElement instanceof SequenceMessage) {
          assertEquals("Message Kind unexpected", messageKind, ((SequenceMessage) semanticElement).getKind());
          countChecks++;
        }
        if (countChecks == numCountChecks) {
          return;
        }
      }
    }

    fail(failMessage);
  }

  private int getNoEdgesCreated() {
    if (hasReturnBranch)
      return 2;
    return 1;
  }
}