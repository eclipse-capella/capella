/*******************************************************************************
 * Copyright (c) 2019 THALES GLOBAL SERVICES.
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

import static org.junit.Assert.assertTrue;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Map;

import org.eclipse.emf.ecore.EObject;
import org.eclipse.sirius.diagram.sequence.SequenceDDiagram;
import org.eclipse.sirius.viewpoint.DSemanticDecorator;
import org.polarsys.capella.core.data.interaction.Scenario;
import org.polarsys.capella.core.data.interaction.SequenceMessage;
import org.polarsys.capella.test.diagram.common.ju.context.DiagramContext;
import org.polarsys.capella.test.diagram.common.ju.headless.HeadlessResultOpProvider;
import org.polarsys.capella.test.diagram.common.ju.headless.IHeadlessResult;
import org.polarsys.capella.test.diagram.common.ju.step.tools.AbstractToolStep;
import org.polarsys.capella.test.diagram.common.ju.wrapper.utils.ArgumentType;

public class LostFoundMessageCreationTool extends AbstractToolStep {
  
  protected String containerId;
  protected String edgeId;
  protected Collection<SequenceMessage> messages;
  protected String sourceId;
  protected String targetId;

  public LostFoundMessageCreationTool(DiagramContext context, String toolName, String containerId, String edgeId, String sourceId, String targetId) {
    super(context, toolName);
    this.edgeId = edgeId;
    this.containerId = containerId;
    this.sourceId = sourceId;
    this.targetId = targetId;
  }

  /**
   * @see org.polarsys.capella.test.diagram.common.ju.steps.AbstractExecuteToolCmdStep.tool.AbstractExecuteToolCmdTest#preTestRun()
   */
  @Override
  protected void preRunTest() {
    HeadlessResultOpProvider.INSTANCE.setCurrentOp(new IHeadlessResult() {

      @Override
      public Object getResult(java.util.Collection<? extends EObject> selections, Map<String, Object> parameters) {
        return getExecutionContext().getSemanticElement(edgeId);
      }
    });
    super.preRunTest();
    messages = getSequenceMessages();
  }
  
  @Override
  protected void postRunTest() {
    Collection<SequenceMessage> addedMessages = getSequenceMessages();
    addedMessages.removeAll(messages);
    EObject source = (sourceId == null)? null : getExecutionContext().getSemanticElement(sourceId);
    EObject target = (targetId == null)? null: getExecutionContext().getSemanticElement(targetId);
    
    for(SequenceMessage message : addedMessages) {
      System.out.println(message);
      if(source != null) {
        assertTrue("SequenceMessage does not have sending part " + source.getClass(), message.getSendingPart().getRepresentingInstanceRoles().contains(source));
      }
      if(target != null) {
        assertTrue("SequenceMessage does not have receiving part " + target.getClass(), message.getReceivingPart().getRepresentingInstanceRoles().contains(target));
      }
    }
  }
  
  private Collection<SequenceMessage> getSequenceMessages() {
    Collection<SequenceMessage> messages = new ArrayList<SequenceMessage> ();
    if(getDiagramContext().getDiagram() instanceof SequenceDDiagram) {
      if(((SequenceDDiagram)getDiagramContext().getDiagram()).getTarget() instanceof Scenario) {
        Scenario scen = (Scenario) ((SequenceDDiagram)getDiagramContext().getDiagram()).getTarget();
        messages.addAll(scen.getOwnedMessages());
      }
    }
    return messages;
  }
  
  @Override
  protected void initToolArguments() {
    DSemanticDecorator containerView = getDiagramContext().getView(containerId);
    _toolWrapper.setArgumentValue(ArgumentType.CONTAINER, containerView.getTarget());
    _toolWrapper.setArgumentValue(ArgumentType.CONTAINER_VIEW, containerView);
  }

  @Override
  public Object getResult() {
    return null;
  }
}