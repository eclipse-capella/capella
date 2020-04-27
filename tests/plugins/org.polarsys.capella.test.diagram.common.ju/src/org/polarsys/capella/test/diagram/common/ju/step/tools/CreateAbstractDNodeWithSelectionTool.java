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
package org.polarsys.capella.test.diagram.common.ju.step.tools;

import java.util.Map;

import org.eclipse.emf.ecore.EObject;
import org.eclipse.sirius.diagram.AbstractDNode;
import org.polarsys.capella.core.data.capellacore.CapellaElement;
import org.polarsys.capella.test.diagram.common.ju.context.DiagramContext;
import org.polarsys.capella.test.diagram.common.ju.headless.HeadlessResultOpProvider;
import org.polarsys.capella.test.diagram.common.ju.headless.IHeadlessResult;

/**
 * Test step to call an AbstractDNode (DDiagramElementContainer, DNode...) creation tool.
 */
public class CreateAbstractDNodeWithSelectionTool<T extends AbstractDNode> extends CreateAbstractDNodeTool<T> {
  protected String selectedId;

  public CreateAbstractDNodeWithSelectionTool(DiagramContext context, String toolName, String targetContainerId,
      String containerId, String selectedId, Class<T> expectedNodeType, Class<? extends CapellaElement> targetType) {

    super(context, toolName, targetContainerId, containerId, expectedNodeType, targetType);
    this.selectedId = selectedId;
  }

  @Override
  protected void preRunTest() {
    HeadlessResultOpProvider.INSTANCE.setCurrentOp(new IHeadlessResult() {
      @Override
      public Object getResult(java.util.Collection<? extends EObject> selections, Map<String, Object> parameters) {
        return getExecutionContext().getSemanticElements(selectedId);
      }
    });
    super.preRunTest();
  }
}
