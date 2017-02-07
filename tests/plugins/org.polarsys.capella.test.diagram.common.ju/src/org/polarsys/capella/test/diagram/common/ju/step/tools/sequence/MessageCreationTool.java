/*******************************************************************************
 * Copyright (c) 2016, 2017 THALES GLOBAL SERVICES.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
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
import org.polarsys.capella.common.helpers.EObjectLabelProviderHelper;
import org.polarsys.capella.core.data.fa.FunctionalExchange;
import org.polarsys.capella.test.diagram.common.ju.context.DiagramContext;
import org.polarsys.capella.test.diagram.common.ju.headless.HeadlessResultOpProvider;
import org.polarsys.capella.test.diagram.common.ju.headless.IHeadlessResult;
import org.polarsys.capella.test.diagram.common.ju.step.tools.CreateDEdgeTool;
import org.polarsys.capella.test.diagram.common.ju.wrapper.utils.DiagramHelper;
import org.polarsys.capella.test.framework.api.CommonTestMessages;

public class MessageCreationTool extends CreateDEdgeTool {

  public MessageCreationTool(DiagramContext context, String toolName, String id, String sourceId, String targetId) {
    super(context, toolName, sourceId, targetId, id);
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
    
    DDiagram diagram = getExecutionContext().getDiagram();
    DiagramHelper.refreshDiagram(diagram);

    EObject feToAdd = getExecutionContext().getSemanticElement(_newIdentifier);
    
    String failMessage = NLS.bind(CommonTestMessages.objectRepresentationNotAvailableOnDiagram,
        EObjectLabelProviderHelper.getText(feToAdd), diagram.getName());
    
    // Only one edge should be created
    if (_newEdgesElements.size() == 1) {
      DDiagramElement view = _newEdgesElements.iterator().next();
      EList<EObject> semanticElements = view.getSemanticElements();
      for (EObject semanticElement : semanticElements) {
        if (semanticElement instanceof FunctionalExchange) {
          // and one of its semantic elements must be the added functional Exchange
          FunctionalExchange feAdded = (FunctionalExchange) semanticElement;
          assertEquals(failMessage, feToAdd, feAdded);
          return;
        }
      }
    }

    
    fail(failMessage);
  }
}
