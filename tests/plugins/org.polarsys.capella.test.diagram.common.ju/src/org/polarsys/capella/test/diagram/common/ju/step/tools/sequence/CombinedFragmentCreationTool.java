/*******************************************************************************
 * Copyright (c) 2016, 2019 THALES GLOBAL SERVICES.
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
import static org.junit.Assert.fail;

import java.util.ArrayList;
import java.util.Map;

import org.eclipse.emf.ecore.EObject;
import org.eclipse.sirius.diagram.DDiagramElement;
import org.eclipse.sirius.diagram.DNodeContainer;
import org.polarsys.capella.core.data.interaction.CombinedFragment;
import org.polarsys.capella.core.data.interaction.InteractionOperand;
import org.polarsys.capella.core.data.interaction.InteractionOperatorKind;
import org.polarsys.capella.test.diagram.common.ju.context.DiagramContext;
import org.polarsys.capella.test.diagram.common.ju.headless.HeadlessResultOpProvider;
import org.polarsys.capella.test.diagram.common.ju.headless.IHeadlessResult;
import org.polarsys.capella.test.diagram.common.ju.step.tools.CreateAbstractDNodeTool;
import org.polarsys.capella.test.diagram.common.ju.wrapper.utils.DiagramHelper;

public class CombinedFragmentCreationTool extends CreateAbstractDNodeTool<DNodeContainer> {
  private InteractionOperatorKind operatorKind;
  private boolean selectOperand;
  
  public CombinedFragmentCreationTool(DiagramContext context, String toolName, String targetContainerView, String containerView, InteractionOperatorKind operatorKind, boolean selectOperand) {
    super(context, toolName, targetContainerView, containerView);
    this.operatorKind = operatorKind;
    this.selectOperand = selectOperand;
  }

  @Override
  protected void preRunTest() {
    super.preRunTest();
    if(selectOperand) {
      HeadlessResultOpProvider.INSTANCE.setCurrentOp(new IHeadlessResult() {
        @Override
        public Object getResult(java.util.Collection<? extends EObject> selections, Map<String, Object> parameters) {
          return operatorKind;
        }
      });
    }
    elements = DiagramHelper.getDiagramElements(getDiagramContext().getDiagram());
  }
  
  @Override
  protected int expectedNewElements() {
    return 2;
  }
  
  @Override
  protected void postRunTest() {
    newElements = new ArrayList<DDiagramElement>();
    newElements.addAll(DiagramHelper.getDiagramElements(getDiagramContext().getDiagram()));
    newElements.removeAll(elements);
    
    if (newElements.size() != expectedNewElements()) {
      fail();
    }
    
    boolean createdCombinedFragment = false;
    boolean createdOperand = false;
    for(DDiagramElement dElement : newElements) {
      if(dElement.getTarget() instanceof CombinedFragment) {
        createdCombinedFragment = true;
        InteractionOperatorKind kind = ((CombinedFragment)dElement.getTarget()).getOperator();
        assertTrue("Operator should be of kind " + operatorKind + " but is of kind " + kind, kind == operatorKind);
      }
      if(dElement.getTarget() instanceof InteractionOperand) {
        createdOperand = true;
      }
    }
    
    assertTrue("CombinedFragment is not created", createdCombinedFragment);
    assertTrue("Operand is not created", createdOperand);
  }
}
