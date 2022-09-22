/*******************************************************************************
 * Copyright (c) 2019, 2020 THALES GLOBAL SERVICES.
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

public class CreateComponentExchangeGroup extends XABDiagramsProject {

  @Override
  public void test() throws Exception {
    Session session = getSession(getRequiredTestModel());
    SessionContext context = new SessionContext(session);

    testCreateComponentExchange(context);
    testCreateComponentExchangeDelegation(context);
    testCreateComponentExchangeWithPorts(context);
    testCreateComponentExchangeWithoutPorts(context);
    testCreateComponentExchangeBetweenTypes(context);
  }

  public void testCreateComponentExchange(SessionContext context) {
    testOnXAB_CreateComponentExchange(context, OA__OAB_DIAGRAM,
        BlockArchitectureExt.Type.OA, OA__OAB_OA1, OA__OAB_OA2, null, null);
    
    testOnXAB_CreateComponentExchange(context, OA__OAB_DIAGRAM,
            BlockArchitectureExt.Type.OA, OA__OAB_OA1, OA__OAB_OA1, null, null);

    testOnXAB_CreateComponentExchange(context, SA__SAB_DIAGRAM,
        BlockArchitectureExt.Type.SA, SA__SAB_A1, SA__SAB_A2,
        SA__SAB_COMPONENT_PORT_A1_CP1, SA__SAB_COMPONENT_PORT_A2_CP1);
    
    testOnXAB_CreateComponentExchange(context, SA__SAB_DIAGRAM,
            BlockArchitectureExt.Type.SA, SA__SAB_A1, SA__SAB_A1,
            null, null);

    testOnXAB_CreateComponentExchange(context, LA__LAB_DIAGRAM,
        BlockArchitectureExt.Type.LA, LA__LAB_A1, LA__LAB_A2,
        LA__LAB_COMPONENT_PORT_A1_CP1, LA__LAB_COMPONENT_PORT_A2_CP1);
    
    testOnXAB_CreateComponentExchange(context, LA__LAB_DIAGRAM,
            BlockArchitectureExt.Type.LA, LA__LAB_A1, LA__LAB_A1,
            null, null);

    testOnXAB_CreateComponentExchange(context, PA__PAB_DIAGRAM,
        BlockArchitectureExt.Type.PA, PA__PAB_COMPONENT_PC3,
        PA__PAB_COMPONENT_PC4, PA__PAB_COMPONENT_PORT_PC3_CP1,
        PA__PAB_COMPONENT_PORT_PC4_CP1);
    
    testOnXAB_CreateComponentExchange(context, PA__PAB_DIAGRAM,
            BlockArchitectureExt.Type.PA, PA__PAB_COMPONENT_PC3,
            PA__PAB_COMPONENT_PC3, null,
            null);
  }

  public void testCreateComponentExchangeDelegation(SessionContext context) {
    testOnXAB_CreateComponentExchangeDelegation(context, LA__LAB_DIAGRAM,
        BlockArchitectureExt.Type.LA, LA__LAB_LOGICAL_SYSTEM_PART, LA__LAB_LC1_PART,
        LA__LAB_COMPONENT_PORT_LOGICAL_SYSTEM_CP1,
        LA__LAB_COMPONENT_PORT_LC1_CP2);
    testOnXAB_CreateComponentExchangeDelegation(context, PA__PAB_DIAGRAM,
        BlockArchitectureExt.Type.PA, PA__PAB_COMPONENT_PC3,
        PA__PAB_COMPONENT_PC3_1, PA__PAB_COMPONENT_PORT_PC3_CP1,
        PA__PAB_COMPONENT_PORT_PC3_1_CP1);
  }

  public void testCreateComponentExchangeWithPorts(SessionContext context) {
    testOnXAB_CreateComponentExchangeWithPorts(context, SA__SAB_DIAGRAM,
        BlockArchitectureExt.Type.SA, SA__SAB_A1, SA__SAB_A2);

    testOnXAB_CreateComponentExchangeWithPorts(context, LA__LAB_DIAGRAM,
        BlockArchitectureExt.Type.LA, LA__LAB_A1, LA__LAB_A2);

    testOnXAB_CreateComponentExchangeWithPorts(context, PA__PAB_DIAGRAM,
        BlockArchitectureExt.Type.PA, PA__PAB_COMPONENT_PC3,
        PA__PAB_COMPONENT_PC4);
  }
  
  public void testCreateComponentExchangeWithoutPorts(SessionContext context) {
    testOnXAB_CreateComponentExchangeWithoutPorts(context, SA__SAB_DIAGRAM,
        BlockArchitectureExt.Type.SA, SA__SAB_A1, SA__SAB_A2);

    testOnXAB_CreateComponentExchangeWithoutPorts(context, LA__LAB_DIAGRAM,
        BlockArchitectureExt.Type.LA, LA__LAB_A1, LA__LAB_A2);

    testOnXAB_CreateComponentExchangeWithoutPorts(context, PA__PAB_DIAGRAM,
        BlockArchitectureExt.Type.PA, PA__PAB_COMPONENT_PC3,
        PA__PAB_COMPONENT_PC4);
  }

  public void testCreateComponentExchangeBetweenTypes(SessionContext context) {
    testOnXAB_CreateComponentExchangeBetweenTypes(context, SA__SAB_DIAGRAM,
        BlockArchitectureExt.Type.SA, SA__SAB_A1, SA__SAB_A2);

    testOnXAB_CreateComponentExchangeBetweenTypes(context, LA__LAB_DIAGRAM,
        BlockArchitectureExt.Type.LA, LA__LAB_A1, LA__LAB_A2);

    testOnXAB_CreateComponentExchangeBetweenTypes(context, PA__PAB_DIAGRAM,
        BlockArchitectureExt.Type.PA, PA__PAB_COMPONENT_PC3,
        PA__PAB_COMPONENT_PC4);
  }
  
  public void testOnXAB_CreateComponentExchange(SessionContext context, String diagramName,
      BlockArchitectureExt.Type type, String compSourceId, String compTargetId, String portSourceId,
      String portTargetId) {
    XABDiagram diagram = XABDiagram.openDiagram(context, diagramName, type);

    // Create a delegation between 2 components
    diagram.createComponentExchange(compSourceId, compTargetId, GenericModel.COMPONENT_EXCHANGE_1);

    if (type != BlockArchitectureExt.Type.OA && (portTargetId != null || portSourceId != null)) {
      // Create a delegation between a component and a component port
      diagram.createComponentExchange(compSourceId, portTargetId, GenericModel.COMPONENT_EXCHANGE_2);

      // Create a delegation between a component port and a component
      diagram.createComponentExchange(portSourceId, compTargetId, GenericModel.COMPONENT_EXCHANGE_3);

      // Create a delegation between a component port and a component port
      diagram.createComponentExchange(portSourceId, portTargetId, GenericModel.COMPONENT_EXCHANGE_4);
    }
  }

  public void testOnXAB_CreateComponentExchangeDelegation(SessionContext context, String diagramName,
      BlockArchitectureExt.Type type, String compSourceId, String compTargetId, String portSourceId,
      String portTargetId) {
    XABDiagram diagram = XABDiagram.openDiagram(context, diagramName, type);

    // Create a delegation between 2 components
    diagram.createComponentExchangeDelegation(compSourceId, compTargetId, GenericModel.COMPONENT_EXCHANGE_1);
    diagram.createComponentExchangeWithDelegation(compSourceId, compTargetId, GenericModel.COMPONENT_EXCHANGE_2);

    // Create a delegation between a component and a component port
    diagram.createComponentExchangeDelegation(compSourceId, portTargetId, GenericModel.COMPONENT_EXCHANGE_4);
    diagram.createComponentExchangeWithDelegation(compSourceId, portTargetId, GenericModel.COMPONENT_EXCHANGE_5);

    // Create a delegation between a component port and a component
    diagram.createComponentExchangeDelegation(portSourceId, compTargetId, GenericModel.COMPONENT_EXCHANGE_6);
    if (type == BlockArchitectureExt.Type.PA)
      diagram.createComponentExchangeWithDelegation(portSourceId, compTargetId, GenericModel.COMPONENT_EXCHANGE_7);

    // Create a delegation between a component port and a component port
    diagram.createComponentExchangeDelegation(portSourceId, portTargetId, GenericModel.COMPONENT_EXCHANGE_8);
    if (type == BlockArchitectureExt.Type.PA)
      diagram.createComponentExchangeWithDelegation(portSourceId, portTargetId, GenericModel.COMPONENT_EXCHANGE_9);
  }

  public void testOnXAB_CreateComponentExchangeWithPorts(SessionContext context, String diagramName,
      BlockArchitectureExt.Type type, String compSourceId, String compTargetId) {
    XABDiagram diagram = XABDiagram.openDiagram(context, diagramName, type);

    diagram.createComponentExchangeWithPorts(compSourceId, compTargetId, GenericModel.COMPONENT_EXCHANGE_1);
  }

  public void testOnXAB_CreateComponentExchangeWithoutPorts(SessionContext context, String diagramName,
      BlockArchitectureExt.Type type, String compSourceId, String compTargetId) {
    XABDiagram diagram = XABDiagram.openDiagram(context, diagramName, type);

    diagram.createComponentExchangeWithoutPorts(compSourceId, compTargetId, GenericModel.COMPONENT_EXCHANGE_1);
  }

  public void testOnXAB_CreateComponentExchangeBetweenTypes(SessionContext context, String diagramName,
      BlockArchitectureExt.Type type, String compSourceId, String compTargetId) {
    XABDiagram diagram = XABDiagram.openDiagram(context, diagramName, type);

    diagram.createComponentExchangeBetweenTypes(compSourceId, compTargetId, GenericModel.COMPONENT_EXCHANGE_1);
  }

  
}
