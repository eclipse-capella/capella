/*******************************************************************************
 * Copyright (c) 2019, THALES GLOBAL SERVICES.
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
package org.polarsys.capella.test.diagram.tools.ju.xab.showhide.functions;

import org.eclipse.sirius.business.api.session.Session;
import org.polarsys.capella.test.diagram.common.ju.context.PABDiagram;
import org.polarsys.capella.test.diagram.common.ju.context.XABDiagram;
import org.polarsys.capella.test.diagram.tools.ju.model.EmptyProject;
import org.polarsys.capella.test.framework.context.SessionContext;
import org.polarsys.capella.test.framework.model.GenericModel;

/**
 * This class tests the same scenarios as {@link AbstractShowHideFunctions} but for <strong>deployed behavior</strong>
 * components.
 * 
 */
public final class ShowHideFunctionsPAOnDeployedComponents extends AbstractShowHideFunctions {

  @Override
  public void test() throws Exception {
    Session session = getSession(getRequiredTestModel());
    SessionContext context = new SessionContext(session);

    String rootSF = EmptyProject.PA__ROOT_PF;
    String rootCPS = EmptyProject.PA__PHYSICAL_SYSTEM;

    initialize(context, rootSF, rootCPS);
    testCommonScenarios(context, rootSF, rootCPS);
  }

  @Override
  protected void createComponent(XABDiagram xab, String id, String containerId) {
    ((PABDiagram) xab).createDeployedBehaviorComponent(id, containerId);
  }

  @Override
  protected void insertComponent(XABDiagram xab, String id, String containerId) {
    ((PABDiagram) xab).insertDeployedBehaviorComponent(id, containerId);
  }

  @Override
  protected void removeComponent(XABDiagram xab, String id, String containerId) {
    ((PABDiagram) xab).removeDeployedBehaviorComponent(id, containerId);
  }

  @Override
  protected PABDiagram initializeXAB(SessionContext context, String rootSF, String rootCPS) {
    PABDiagram pab = (PABDiagram) XABDiagram.createDiagram(context, rootCPS);
    pab.clearDiagram();

    // first component must not be deployed
    pab.createBehaviorComponent(GenericModel.COMPONENT_1, pab.getDiagramId());

    createComponent(pab, GenericModel.COMPONENT_1_1, GenericModel.COMPONENT_1);
    createComponent(pab, GenericModel.COMPONENT_1_1_1, GenericModel.COMPONENT_1_1);
    createComponent(pab, GenericModel.COMPONENT_1_2, GenericModel.COMPONENT_1);

    pab.manageAllocatedFunction(GenericModel.FUNCTION_1_1_1, GenericModel.COMPONENT_1_1_1);
    pab.manageAllocatedFunction(GenericModel.FUNCTION_1_1_2, GenericModel.COMPONENT_1_1_1);
    pab.manageAllocatedFunction(GenericModel.FUNCTION_1_2, GenericModel.COMPONENT_1_2);

    return pab;
  }

  protected PABDiagram setUpDiagram(SessionContext context, String rootSF, String rootCPS) {

    PABDiagram pab = (PABDiagram) XABDiagram.createDiagram(context, rootCPS);
    pab.clearDiagram();

    // first insert must not be for a deployed component
    pab.insertBehaviorComponent(GenericModel.COMPONENT_1, pab.getDiagramId());

    insertComponent(pab, GenericModel.COMPONENT_1_1, GenericModel.COMPONENT_1);
    insertComponent(pab, GenericModel.COMPONENT_1_1_1, GenericModel.COMPONENT_1_1);
    insertComponent(pab, GenericModel.COMPONENT_1_2, GenericModel.COMPONENT_1);

    return pab;
  }

}
