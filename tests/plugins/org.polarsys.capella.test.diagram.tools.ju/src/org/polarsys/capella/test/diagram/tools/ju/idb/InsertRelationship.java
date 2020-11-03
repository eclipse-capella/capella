/*******************************************************************************
 * Copyright (c) 2006, 2020 THALES GLOBAL SERVICES.
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
import org.polarsys.capella.core.model.preferences.IInheritancePreferences;
import org.polarsys.capella.test.diagram.common.ju.context.IDBDiagram;
import org.polarsys.capella.test.diagram.tools.ju.model.EmptyProject;
import org.polarsys.capella.test.framework.context.SessionContext;
import org.polarsys.capella.test.framework.model.GenericModel;

public class InsertRelationship extends EmptyProject {
  String interface1;
  String interface2;

  @Override
  public void test() throws Exception {
    Session session = getSession(getRequiredTestModel());
    SessionContext context = new SessionContext(session);

    IDBDiagram idb = IDBDiagram.createDiagram(context, LA__LOGICAL_SYSTEM);

    createComponents(idb);
    createCommunicationLinks(idb);
    createInterfacesLinks(idb);
    createGeneralizationLinks(idb);

    testInsertInterfacesLinksOnDiagram(context);
    testInsertInterfacesLinksOnLC2(context);

    testInsertCommunicationLinksOnDiagram(context);
    testInsertCommunicationLinksOnLC2(context);

    testInsertGeneralizationLinks(context);
  }

  protected void createComponents(IDBDiagram idb) {
    // Create LC1 LC2 and two sub components of LC2, LC3 and LC4
    idb.createComponent(GenericModel.LC_1);
    idb.createComponent(GenericModel.LC_2);
    idb.createComponent(GenericModel.LC_2, GenericModel.LC_3);
    idb.createComponent(GenericModel.LC_2, GenericModel.LC_4);
  }

  protected void createInterfacesLinks(IDBDiagram idb) {
    interface1 = idb.createInterfaceInContainer(GenericModel.LC_2);
    interface2 = idb.createInterfaceInContainer(GenericModel.LC_2);

    idb.createUses(GenericModel.LC_1, interface1, GenericModel.OBJECT_1);
    idb.createImplements(GenericModel.LC_1, interface2, GenericModel.OBJECT_2);
  }

  protected void testInsertInterfacesLinksOnLC2(SessionContext context) {
    // If LC2 is displayed, we need to insert elements inside it
    IDBDiagram idb = IDBDiagram.createDiagram(context, LA__LOGICAL_SYSTEM);
    idb.open();

    idb.insertComponents(GenericModel.LC_1, GenericModel.LC_2);

    idb.insertRelationship(GenericModel.LC_1, GenericModel.OBJECT_1);
    idb.hasView(GenericModel.OBJECT_1);
    idb.hasView(interface1);

    idb.insertRelationship(GenericModel.LC_1, GenericModel.OBJECT_2);
    idb.hasView(GenericModel.OBJECT_2);
    idb.hasView(interface2);

    idb.mustGraphicalOwnedBy(interface1, GenericModel.LC_2);
    idb.mustGraphicalOwnedBy(interface2, GenericModel.LC_2);

  }

  protected void testInsertInterfacesLinksOnDiagram(SessionContext context) {
    // If LC2 is not displayed, we need to insert elements inside diagram
    IDBDiagram idb = IDBDiagram.createDiagram(context, LA__LOGICAL_SYSTEM);
    idb.open();

    idb.insertComponents(GenericModel.LC_1);

    idb.insertRelationship(GenericModel.LC_1, GenericModel.OBJECT_1);
    idb.hasView(GenericModel.OBJECT_1);
    idb.hasView(interface1);

    idb.insertRelationship(GenericModel.LC_1, GenericModel.OBJECT_2);
    idb.hasView(GenericModel.OBJECT_2);
    idb.hasView(interface2);

    idb.mustGraphicalOwnedBy(interface1, idb.getDiagramId());
    idb.mustGraphicalOwnedBy(interface2, idb.getDiagramId());

  }

  private void createCommunicationLinks(IDBDiagram diagramContext) {

    diagramContext.createEvent(GenericModel.LC_2, GenericModel.EXCHANGE_ITEM_1);
    diagramContext.createEvent(GenericModel.LC_2, GenericModel.EXCHANGE_ITEM_2);

    diagramContext.createCommunicationLinkAcquire(GenericModel.LC_1, GenericModel.EXCHANGE_ITEM_1, GenericModel.CL_1);
    diagramContext.createCommunicationLinkAcquire(GenericModel.LC_1, GenericModel.EXCHANGE_ITEM_2, GenericModel.CL_2);

  }

  private void createGeneralizationLinks(IDBDiagram diagramContext) {

    SessionContext sessionContext = diagramContext.getSessionContext();

    sessionContext.setPreference(IInheritancePreferences.PREFS_ALLOW_COMPONENT_NON_ACTOR_INHERITANCE, true);
    sessionContext.setPreference(IInheritancePreferences.PREFS_ALLOW_MULTIPLE_INHERITANCE, true);

    diagramContext.createGeneralization(GenericModel.LC_1, GenericModel.LC_2, GenericModel.GENERALIZATION_2);
    diagramContext.createGeneralization(GenericModel.LC_1, GenericModel.LC_3, GenericModel.GENERALIZATION_3);
    diagramContext.createGeneralization(GenericModel.LC_1, GenericModel.LC_4, GenericModel.GENERALIZATION_4);

  }

  private void testInsertCommunicationLinksOnDiagram(SessionContext context) {
    // If LC2 is displayed, we need to insert elements inside it
    IDBDiagram idb = IDBDiagram.createDiagram(context, LA__LOGICAL_SYSTEM);
    idb.open();

    idb.insertComponents(GenericModel.LC_1);

    idb.insertRelationship(GenericModel.LC_1, GenericModel.CL_1);
    idb.hasView(GenericModel.CL_1);
    idb.hasView(GenericModel.EXCHANGE_ITEM_1);

    idb.insertRelationship(GenericModel.LC_1, GenericModel.CL_2);
    idb.hasView(GenericModel.CL_2);
    idb.hasView(GenericModel.EXCHANGE_ITEM_2);

    idb.mustGraphicalOwnedBy(GenericModel.EXCHANGE_ITEM_1, idb.getDiagramId());
    idb.mustGraphicalOwnedBy(GenericModel.EXCHANGE_ITEM_2, idb.getDiagramId());

  }

  private void testInsertCommunicationLinksOnLC2(SessionContext context) {
    // If LC2 is displayed, we need to insert elements inside it
    IDBDiagram idb = IDBDiagram.createDiagram(context, LA__LOGICAL_SYSTEM);
    idb.open();

    idb.insertComponents(GenericModel.LC_1, GenericModel.LC_2);

    idb.insertRelationship(GenericModel.LC_1, GenericModel.CL_1);
    idb.hasView(GenericModel.CL_1);
    idb.hasView(GenericModel.EXCHANGE_ITEM_1);

    idb.insertRelationship(GenericModel.LC_1, GenericModel.CL_2);
    idb.hasView(GenericModel.CL_2);
    idb.hasView(GenericModel.EXCHANGE_ITEM_2);

    idb.mustGraphicalOwnedBy(GenericModel.EXCHANGE_ITEM_1, GenericModel.LC_2);
    idb.mustGraphicalOwnedBy(GenericModel.EXCHANGE_ITEM_2, GenericModel.LC_2);

  }

  private void testInsertGeneralizationLinks(SessionContext context) {
    IDBDiagram idb = IDBDiagram.createDiagram(context, LA__LOGICAL_SYSTEM);
    idb.open();

    idb.insertComponents(GenericModel.LC_1);

    idb.insertRelationship(GenericModel.LC_1, GenericModel.GENERALIZATION_3);
    idb.hasView(GenericModel.GENERALIZATION_3);
    idb.hasView(GenericModel.LC_3);
    idb.mustGraphicalOwnedBy(GenericModel.LC_3, idb.getDiagramId());

    idb.insertRelationship(GenericModel.LC_1, GenericModel.GENERALIZATION_2);
    idb.hasView(GenericModel.GENERALIZATION_2);
    idb.hasView(GenericModel.LC_2);
    idb.mustGraphicalOwnedBy(GenericModel.LC_2, idb.getDiagramId());

    idb.insertRelationship(GenericModel.LC_1, GenericModel.GENERALIZATION_4);
    idb.hasView(GenericModel.GENERALIZATION_4);
    idb.hasView(GenericModel.LC_4);
    idb.mustGraphicalOwnedBy(GenericModel.LC_4, GenericModel.LC_2);
  }
}
