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
package org.polarsys.capella.test.diagram.tools.ju.idb;

import org.eclipse.sirius.business.api.session.Session;
import org.polarsys.capella.core.data.cs.Component;
import org.polarsys.capella.core.data.cs.Interface;
import org.polarsys.capella.core.data.information.ExchangeMechanism;
import org.polarsys.capella.core.sirius.analysis.IDiagramNameConstants;
import org.polarsys.capella.test.diagram.common.ju.context.IDBDiagram;
import org.polarsys.capella.test.diagram.tools.ju.model.EmptyProject;
import org.polarsys.capella.test.framework.context.SessionContext;
import org.polarsys.capella.test.framework.model.GenericModel;

public class DragAndDropTest extends EmptyProject {
  IDBDiagram idbSetup;
  String actor1;
  String component1;
  String component2;
  String component2_1;
  Component objComp2;
  Component objComp2_1;
  String interface1;
  String interface2;
  String interface3;
  Interface objIntf1;
  Interface objIntf2;
  Interface objIntf3;
  String ei1;
  String ei2;
  String constraint;
  String port;

  @Override
  public void test() throws Exception {
    Session session = getSession(getRequiredTestModel());
    SessionContext context = new SessionContext(session);

    testOnDiagramIDB(context, IDiagramNameConstants.INTERFACES_BLANK_DIAGRAM_NAME);
    testOnDiagramCII(context, IDiagramNameConstants.CONTEXTUAL_COMPONENT_INTERNAL_INTERFACES_DIAGRAM_NAME);
    testOnDiagramCDI(context, IDiagramNameConstants.CONTEXTUAL_COMPONENT_DETAILED_INTERFACES_DIAGRAM_NAME);
    testOnDiagramCEI(context, IDiagramNameConstants.CONTEXTUAL_COMPONENT_EXTERNAL_INTERFACES_DIAGRAM_NAME);
  }

  private void testOnDiagramIDB(SessionContext context, String diagramKind) {
    init(context);

    IDBDiagram idb = IDBDiagram.createDiagram(context, diagramKind, LA__LOGICAL_SYSTEM);
    String diagramId = idb.getDiagramId();

    // drag and drop component from explorer
    idb.dragAndDropComponentFromExplorer(actor1, diagramId);
    idb.dragAndDropComponentFromExplorer(component1, diagramId);
    idb.dragAndDropComponentFromExplorer(component2_1, diagramId);
    idb.dragAndDropComponentFromExplorer(component2, diagramId);
    assertTrue(objComp2_1.eContainer().equals(objComp2));

    // drag and drop interface from explorer
    idb.dragAndDropInterfaceFromExplorer(interface1, diagramId);
    idb.dragAndDropInterfaceFromExplorer(interface2, component2);
    idb.dragAndDropInterfaceFromExplorer(interface3, component2_1);
    assertTrue(objIntf2.eContainer().eContainer().equals(objComp2));
    assertTrue(objIntf3.eContainer().eContainer().equals(objComp2_1));

    // drag and drop interface from diagram
    idb.dragAndDropInterfaceFromDiagram(interface2, component2_1);
    idb.dragAndDropInterfaceFromDiagram(interface1, component1);
    idb.dragAndDropInterfaceFromDiagram(interface1, diagramId);
    idb.dragAndDropInterfaceFromDiagram(interface2, component2);

    // drag and drop component from diagram
    String component3 = idb.createComponent();
    String component4 = idb.createComponent();
    idb.dragAndDropComponentFromDiagram(component3, component4);

    // drag and drop exchange item allocation from explorer
    idb.dragAndDropExchangeItemAllocationFromExplorer(ei1, interface1);

    String ei = idb.createExchangeItem(ExchangeMechanism.EVENT);
    String interf = idb.createInterface();
    idb.dragAndDropExchangeItemAllocationFromDiagram(ei, interf);

    String actor2 = idb.createActor();
    String port2 = idb.createStandardPort(actor1, GenericModel.COMPONENT_PORT_2);
    idb.dragAndDropComponentPortFromDiagram(port2, actor2);

    idb.dragAndDropConstraintFromExplorer(constraint, diagramId);
  }

  private void testOnDiagramCII(SessionContext context, String diagramKind) {
    init(context);

    IDBDiagram idb = IDBDiagram.createDiagram(context, diagramKind, LA__LOGICAL_SYSTEM);
    String diagramId = idb.getDiagramId();
    String containerId = LA__LOGICAL_SYSTEM;
    String component1 = idb.createComponent(containerId, GenericModel.COMPONENT_1);
    String component2 = idb.createComponent(containerId, GenericModel.COMPONENT_2);
    String component2_1 = idb.createComponent(containerId, GenericModel.COMPONENT_2_1);

    // drag and drop interface from explorer
    idb.dragAndDropInterfaceFromExplorer(interface1, diagramId);
    idb.dragAndDropInterfaceFromExplorer(interface2, diagramId);
    idb.dragAndDropInterfaceFromExplorer(interface3, diagramId);

    // drag and drop interface from diagram
    idb.dragAndDropInterfaceFromDiagram(interface2, component2_1);
    idb.dragAndDropInterfaceFromDiagram(interface1, component1);
    idb.dragAndDropInterfaceFromDiagram(interface1, diagramId);
    idb.dragAndDropInterfaceFromDiagram(interface2, component2);

    // drag and drop component from diagram
    idb.dragAndDropComponentFromDiagram(component2_1, component1);
    idb.dragAndDropComponentFromDiagram(component2_1, component2);

    // drag and drop exchange item allocation from explorer
    idb.dragAndDropExchangeItemAllocationFromExplorer(ei1, diagramId);

    String ei = idb.createExchangeItemNode(ExchangeMechanism.EVENT);
    idb.dragAndDropExchangeItemAllocationFromDiagram(ei, containerId);

    String port2 = idb.createStandardPort(component1, GenericModel.COMPONENT_PORT_2);
    idb.dragAndDropComponentPortFromDiagram(port2, component2);

    idb.dragAndDropConstraintFromExplorer(constraint, diagramId);
  }

  private void testOnDiagramCDI(SessionContext context, String diagramKind) {
    init(context);

    IDBDiagram idb = IDBDiagram.createDiagram(context, diagramKind, LA__LOGICAL_SYSTEM);
    String diagramId = idb.getDiagramId();

    // drag and drop interface from explorer
    idb.dragAndDropInterfaceFromExplorer(interface1, diagramId);
    idb.dragAndDropInterfaceFromExplorer(interface2, diagramId);
    idb.dragAndDropInterfaceFromExplorer(interface3, diagramId);

    // drag and drop exchange item allocation from explorer
    idb.dragAndDropExchangeItemAllocationFromExplorer(ei1, diagramId);

    // drag and drop exchange item allocation from diagram
    String ei = idb.createExchangeItem(ExchangeMechanism.EVENT);
    String interf = idb.createInterface();
    idb.dragAndDropExchangeItemAllocationFromDiagram(ei, interf);

    idb.dragAndDropConstraintFromExplorer(constraint, diagramId);
  }

  private void testOnDiagramCEI(SessionContext context, String diagramKind) {
    init(context);

    IDBDiagram idb = IDBDiagram.createDiagram(context, diagramKind, LA__LOGICAL_SYSTEM);
    String diagramId = idb.getDiagramId();

    // drag and drop component from explorer
    idb.dragAndDropComponentFromExplorer(component1, diagramId);

    // drag and drop interface from explorer
    idb.dragAndDropInterfaceFromExplorer(interface1, diagramId);
    idb.dragAndDropInterfaceFromExplorer(interface2, diagramId);
    idb.dragAndDropInterfaceFromExplorer(interface3, diagramId);

    // drag and drop exchange item allocation from explorer
    idb.dragAndDropExchangeItemAllocationFromExplorer(ei1, diagramId);

    idb.dragAndDropConstraintFromExplorer(constraint, diagramId);
  }

  private void init(SessionContext context) {
    idbSetup = IDBDiagram.createDiagram(context, LA__LOGICAL_SYSTEM);

    // setup components
    actor1 = idbSetup.createActor();
    component1 = idbSetup.createComponent();
    component2 = idbSetup.createComponent();
    component2_1 = idbSetup.createSubComponent(component2);
    objComp2 = idbSetup.getSessionContext().getSemanticElement(component2);
    objComp2_1 = idbSetup.getSessionContext().getSemanticElement(component2_1);

    // setup interface
    interface1 = idbSetup.createInterface();
    interface2 = idbSetup.createInterfaceInContainer(component2);
    interface3 = idbSetup.createInterfaceInContainer(component2_1);
    objIntf1 = idbSetup.getSessionContext().getSemanticElement(interface1);
    objIntf2 = idbSetup.getSessionContext().getSemanticElement(interface2);
    objIntf3 = idbSetup.getSessionContext().getSemanticElement(interface3);

    // setup exchange item allocation
    ei1 = idbSetup.createExchangeItem(ExchangeMechanism.EVENT);
    ei2 = idbSetup.createExchangeItem(ExchangeMechanism.FLOW);

    port = idbSetup.createStandardPort(actor1, GenericModel.COMPONENT_PORT_1);

    constraint = idbSetup.createConstraint(GenericModel.CONSTRAINT_1);
  }
}
