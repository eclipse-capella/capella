/*******************************************************************************
 * Copyright (c) 2006, 2015 THALES GLOBAL SERVICES.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *  
 * Contributors:
 *    Thales - initial API and implementation
 *******************************************************************************/
package org.polarsys.capella.test.diagram.tools.ju.idb;

import org.eclipse.sirius.business.api.session.Session;
import org.polarsys.capella.core.commands.preferences.service.AbstractPreferencesInitializer;
import org.polarsys.capella.core.model.preferences.IInheritancePreferences;
import org.polarsys.capella.core.sirius.analysis.IDiagramNameConstants;
import org.polarsys.capella.core.sirius.analysis.constants.IToolNameConstants;
import org.polarsys.capella.test.diagram.common.ju.context.DiagramContext;
import org.polarsys.capella.test.diagram.common.ju.context.SessionContext;
import org.polarsys.capella.test.diagram.common.ju.step.crud.CreateDiagramStep;
import org.polarsys.capella.test.diagram.common.ju.step.crud.OpenDiagramStep;
import org.polarsys.capella.test.diagram.common.ju.step.tools.CreateContainerTool;
import org.polarsys.capella.test.diagram.common.ju.step.tools.CreateDEdgeTool;
import org.polarsys.capella.test.diagram.tools.ju.model.EmptyProject;
import org.polarsys.capella.test.diagram.tools.ju.model.GenericModel;
import org.polarsys.capella.test.diagram.tools.ju.step.idb.InsertRemoveComponentTool;
import org.polarsys.capella.test.diagram.tools.ju.step.idb.InsertRemoveRelationshipsTool;

public class InsertRelationship extends EmptyProject {

  @Override
  public void test() throws Exception {
    Session session = getSession(getRequiredTestModel());
    SessionContext context = new SessionContext(session);

    DiagramContext idb1 = new CreateDiagramStep(context, LA__LOGICAL_SYSTEM,
        IDiagramNameConstants.INTERFACES_BLANK_DIAGRAM_NAME).run();

    new OpenDiagramStep(idb1).run();

    createComponents(idb1);
    createInterfacesLinks(idb1);
    createCommunicationLinks(idb1);
    createGeneralizationLinks(idb1);

    testInsertInterfacesLinksOnDiagram(context);
    testInsertInterfacesLinksOnLC2(context);

    testInsertCommunicationLinksOnDiagram(context);
    testInsertCommunicationLinksOnLC2(context);

    testInsertGeneralizationLinks(context);
  }

  protected void createComponents(DiagramContext diagramContext) {
    //Create LC1 LC2 and two sub components of LC2, LC3 and LC4
    new CreateContainerTool(diagramContext, IToolNameConstants.TOOL_IDB_CREATE_COMPONENT, GenericModel.LC_1,
        diagramContext.getDiagramId()).run();

    new CreateContainerTool(diagramContext, IToolNameConstants.TOOL_IDB_CREATE_COMPONENT, GenericModel.LC_2,
        diagramContext.getDiagramId()).run();

    new CreateContainerTool(diagramContext, IToolNameConstants.TOOL_IDB_CREATE_COMPONENT, GenericModel.LC_3,
        GenericModel.LC_2).run();

    new CreateContainerTool(diagramContext, IToolNameConstants.TOOL_IDB_CREATE_COMPONENT, GenericModel.LC_4,
        GenericModel.LC_2).run();
  }

  protected void createInterfacesLinks(DiagramContext diagramContext) {

    new CreateContainerTool(diagramContext, IToolNameConstants.TOOL_IDB_CREATE_INTERFACE, GenericModel.INTERFACE_1,
        GenericModel.LC_2).run();

    new CreateContainerTool(diagramContext, IToolNameConstants.TOOL_IDB_CREATE_INTERFACE, GenericModel.INTERFACE_2,
        GenericModel.LC_2).run();

    new CreateDEdgeTool(diagramContext, IToolNameConstants.TOOL_IDB_CREATE_USES, GenericModel.OBJECT_1,
        GenericModel.LC_1, GenericModel.INTERFACE_1).run();

    new CreateDEdgeTool(diagramContext, IToolNameConstants.TOOL_IDB_CREATE_IMPLEMENTS, GenericModel.OBJECT_2,
        GenericModel.LC_1, GenericModel.INTERFACE_2).run();
  }

  protected void testInsertInterfacesLinksOnLC2(SessionContext context) {
    //If LC2 is displayed, we need to insert elements inside it
    DiagramContext idb = new CreateDiagramStep(context, LA__LOGICAL_SYSTEM,
        IDiagramNameConstants.INTERFACES_BLANK_DIAGRAM_NAME).run();

    new OpenDiagramStep(idb).run();
    new InsertRemoveComponentTool(idb).insert(GenericModel.LC_1, GenericModel.LC_2);

    new InsertRemoveRelationshipsTool(idb, GenericModel.LC_1).insert(GenericModel.OBJECT_1);
    idb.hasView(GenericModel.OBJECT_1);
    idb.hasView(GenericModel.INTERFACE_1);

    new InsertRemoveRelationshipsTool(idb, GenericModel.LC_1).insert(GenericModel.OBJECT_2);
    idb.hasView(GenericModel.OBJECT_2);
    idb.hasView(GenericModel.INTERFACE_2);

    mustGraphicalOwnedBy(idb, GenericModel.INTERFACE_1, GenericModel.LC_2);
    mustGraphicalOwnedBy(idb, GenericModel.INTERFACE_2, GenericModel.LC_2);

  }

  protected void testInsertInterfacesLinksOnDiagram(SessionContext context) {
    //If LC2 is not displayed, we need to insert elements inside diagram
    DiagramContext idb = new CreateDiagramStep(context, LA__LOGICAL_SYSTEM,
        IDiagramNameConstants.INTERFACES_BLANK_DIAGRAM_NAME).run();

    new OpenDiagramStep(idb).run();
    new InsertRemoveComponentTool(idb).insert(GenericModel.LC_1);

    new InsertRemoveRelationshipsTool(idb, GenericModel.LC_1).insert(GenericModel.OBJECT_1);
    idb.hasView(GenericModel.OBJECT_1);
    idb.hasView(GenericModel.INTERFACE_1);

    new InsertRemoveRelationshipsTool(idb, GenericModel.LC_1).insert(GenericModel.OBJECT_2);
    idb.hasView(GenericModel.OBJECT_2);
    idb.hasView(GenericModel.INTERFACE_2);

    mustGraphicalOwnedBy(idb, GenericModel.INTERFACE_1, idb.getDiagramId());
    mustGraphicalOwnedBy(idb, GenericModel.INTERFACE_2, idb.getDiagramId());

  }

  private void mustGraphicalOwnedBy(DiagramContext diagramContext, String s1, String s2) {
    assertTrue(diagramContext.getView(s1).eContainer().equals(diagramContext.getView(s2)));
  }

  private void createCommunicationLinks(DiagramContext diagramContext) {

    new CreateContainerTool(diagramContext, IToolNameConstants.TOOL_IDB_CREATE_EVENT, GenericModel.EXCHANGE_ITEM_1,
        GenericModel.LC_2).run();

    new CreateContainerTool(diagramContext, IToolNameConstants.TOOL_IDB_CREATE_EVENT, GenericModel.EXCHANGE_ITEM_2,
        GenericModel.LC_2).run();

    new CreateDEdgeTool(diagramContext, IToolNameConstants.TOOL_IDB_CREATE_COMMUNICATIONLINK_ACQUIRE,
        GenericModel.CL_1, GenericModel.LC_1, GenericModel.EXCHANGE_ITEM_1).run();

    new CreateDEdgeTool(diagramContext, IToolNameConstants.TOOL_IDB_CREATE_COMMUNICATIONLINK_ACQUIRE,
        GenericModel.CL_2, GenericModel.LC_1, GenericModel.EXCHANGE_ITEM_2).run();

  }

  private void createGeneralizationLinks(DiagramContext diagramContext) {

    AbstractPreferencesInitializer.preferencesManager.setValue(
        IInheritancePreferences.PREFS_ALLOW_COMPONENT_INHERITANCE, true);

    AbstractPreferencesInitializer.preferencesManager.setValue(
        IInheritancePreferences.PREFS_ALLOW_MULTIPLE_INHERITANCE, true);

    new CreateDEdgeTool(diagramContext, IToolNameConstants.TOOL_IDB_CREATE_GENERALIZATION,
        GenericModel.GENERALIZATION_2, GenericModel.LC_1, GenericModel.LC_2).run();

    new CreateDEdgeTool(diagramContext, IToolNameConstants.TOOL_IDB_CREATE_GENERALIZATION,
        GenericModel.GENERALIZATION_3, GenericModel.LC_1, GenericModel.LC_3).run();

    new CreateDEdgeTool(diagramContext, IToolNameConstants.TOOL_IDB_CREATE_GENERALIZATION,
        GenericModel.GENERALIZATION_4, GenericModel.LC_1, GenericModel.LC_4).run();
  }

  private void testInsertCommunicationLinksOnDiagram(SessionContext context) {
    //If LC2 is displayed, we need to insert elements inside it
    DiagramContext idb = new CreateDiagramStep(context, LA__LOGICAL_SYSTEM,
        IDiagramNameConstants.INTERFACES_BLANK_DIAGRAM_NAME).run();

    new OpenDiagramStep(idb).run();
    new InsertRemoveComponentTool(idb).insert(GenericModel.LC_1);

    new InsertRemoveRelationshipsTool(idb, GenericModel.LC_1).insert(GenericModel.CL_1);
    idb.hasView(GenericModel.CL_1);
    idb.hasView(GenericModel.EXCHANGE_ITEM_1);

    new InsertRemoveRelationshipsTool(idb, GenericModel.LC_1).insert(GenericModel.CL_2);
    idb.hasView(GenericModel.CL_2);
    idb.hasView(GenericModel.EXCHANGE_ITEM_2);

    mustGraphicalOwnedBy(idb, GenericModel.EXCHANGE_ITEM_1, idb.getDiagramId());
    mustGraphicalOwnedBy(idb, GenericModel.EXCHANGE_ITEM_2, idb.getDiagramId());

  }

  private void testInsertCommunicationLinksOnLC2(SessionContext context) {
    //If LC2 is displayed, we need to insert elements inside it
    DiagramContext idb = new CreateDiagramStep(context, LA__LOGICAL_SYSTEM,
        IDiagramNameConstants.INTERFACES_BLANK_DIAGRAM_NAME).run();

    new OpenDiagramStep(idb).run();
    new InsertRemoveComponentTool(idb).insert(GenericModel.LC_1, GenericModel.LC_2);

    new InsertRemoveRelationshipsTool(idb, GenericModel.LC_1).insert(GenericModel.CL_1);
    idb.hasView(GenericModel.CL_1);
    idb.hasView(GenericModel.EXCHANGE_ITEM_1);

    new InsertRemoveRelationshipsTool(idb, GenericModel.LC_1).insert(GenericModel.CL_2);
    idb.hasView(GenericModel.CL_2);
    idb.hasView(GenericModel.EXCHANGE_ITEM_2);

    mustGraphicalOwnedBy(idb, GenericModel.EXCHANGE_ITEM_1, GenericModel.LC_2);
    mustGraphicalOwnedBy(idb, GenericModel.EXCHANGE_ITEM_2, GenericModel.LC_2);

  }

  private void testInsertGeneralizationLinks(SessionContext context) {
    DiagramContext idb = new CreateDiagramStep(context, LA__LOGICAL_SYSTEM,
        IDiagramNameConstants.INTERFACES_BLANK_DIAGRAM_NAME).run();

    new OpenDiagramStep(idb).run();
    new InsertRemoveComponentTool(idb).insert(GenericModel.LC_1);

    new InsertRemoveRelationshipsTool(idb, GenericModel.LC_1).insert(GenericModel.GENERALIZATION_3);
    idb.hasView(GenericModel.GENERALIZATION_3);
    idb.hasView(GenericModel.LC_3);
    mustGraphicalOwnedBy(idb, GenericModel.LC_3, idb.getDiagramId());

    new InsertRemoveRelationshipsTool(idb, GenericModel.LC_1).insert(GenericModel.GENERALIZATION_2);
    idb.hasView(GenericModel.GENERALIZATION_2);
    idb.hasView(GenericModel.LC_2);
    mustGraphicalOwnedBy(idb, GenericModel.LC_2, idb.getDiagramId());

    new InsertRemoveRelationshipsTool(idb, GenericModel.LC_1).insert(GenericModel.GENERALIZATION_4);
    idb.hasView(GenericModel.GENERALIZATION_4);
    idb.hasView(GenericModel.LC_4);
    mustGraphicalOwnedBy(idb, GenericModel.LC_4, GenericModel.LC_2);
  }
}
