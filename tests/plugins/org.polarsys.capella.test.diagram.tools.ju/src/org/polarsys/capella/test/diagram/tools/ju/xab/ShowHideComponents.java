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
package org.polarsys.capella.test.diagram.tools.ju.xab;

import org.eclipse.sirius.business.api.session.Session;
import org.polarsys.capella.core.model.helpers.BlockArchitectureExt;

import org.polarsys.capella.test.diagram.common.ju.context.XABDiagram;
import org.polarsys.capella.test.framework.context.SessionContext;
import org.polarsys.capella.test.framework.model.GenericModel;

public class ShowHideComponents extends XABDiagramsProject {

  @Override
  public void test() throws Exception {
    Session session = getSession(getRequiredTestModel());
    SessionContext context = new SessionContext(session);

    testOnXAB(context, OA__OAB_DIAGRAM, BlockArchitectureExt.Type.OA,
        OA__OAB_ENTITY1, OA__OAB_DIAGRAM);
    testOnXAB(context, LA__LAB_DIAGRAM, BlockArchitectureExt.Type.LA,
        LA__LAB_LC1_PART, LA__LAB_LOGICAL_SYSTEM_PART);
  }

  public void testOnXAB(SessionContext context, String diagramName, BlockArchitectureExt.Type type,
      String compId, String containerId) {
    XABDiagram diagram = XABDiagram.openDiagram(context, diagramName, type);
    diagram.removeComponent(compId, containerId);
    diagram.insertComponent(compId, containerId);

    // check that child components are not displayed on the diagram after removing the parent component
    diagram.createComponent(GenericModel.COMPONENT_1_1, compId);
    diagram.createComponent(GenericModel.COMPONENT_1_1_1, GenericModel.COMPONENT_1_1);
    diagram.createComponent(GenericModel.COMPONENT_1_1_2, GenericModel.COMPONENT_1_1);
    diagram.removeComponent(GenericModel.COMPONENT_1_1, compId);

    diagram.hasntViews(GenericModel.COMPONENT_1_1_1, GenericModel.COMPONENT_1_1_2);
    diagram.insertComponent(GenericModel.COMPONENT_1_1, compId);
    diagram.insertComponent(GenericModel.COMPONENT_1_1_1, GenericModel.COMPONENT_1_1);
    diagram.insertComponent(GenericModel.COMPONENT_1_1_2, GenericModel.COMPONENT_1_1);
    
    // check that the elements belonging to a removed component are also removed (ports, exchanges)
    if (type != BlockArchitectureExt.Type.OA) {
      diagram.createComponentPort(GenericModel.COMPONENT_PORT_1, BlockArchitectureExt.ComponentPortType.OUT_FLOW_PORT,
          GenericModel.COMPONENT_1_1_1);
      diagram.createComponentPort(GenericModel.COMPONENT_PORT_2, BlockArchitectureExt.ComponentPortType.IN_FLOW_PORT,
          GenericModel.COMPONENT_1_1_2);
      diagram.createComponentExchange(GenericModel.COMPONENT_PORT_1, GenericModel.COMPONENT_PORT_2, GenericModel.COMPONENT_EXCHANGE_1);
      diagram.removeComponent(GenericModel.COMPONENT_1_1_1, GenericModel.COMPONENT_1_1);
      diagram.hasntViews(GenericModel.COMPONENT_PORT_1, GenericModel.COMPONENT_EXCHANGE_1);
      diagram.hasView(GenericModel.COMPONENT_PORT_2);
    }
  }

  
}
