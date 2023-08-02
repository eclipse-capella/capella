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
package org.polarsys.capella.test.diagram.tool.ju.library;

import java.util.ArrayList;
import java.util.List;

import org.eclipse.emf.ecore.EObject;
import org.eclipse.sirius.business.api.session.Session;
import org.eclipse.sirius.viewpoint.DSemanticDecorator;
import org.polarsys.capella.core.sirius.analysis.constants.IDNDToolNameConstants;
import org.polarsys.capella.test.diagram.common.ju.api.AbstractDiagramTestCase;
import org.polarsys.capella.test.diagram.common.ju.context.DiagramContext;
import org.polarsys.capella.test.diagram.common.ju.step.crud.OpenDiagramStep;
import org.polarsys.capella.test.diagram.common.ju.step.tools.DragAndDropFromProjectExplorerTool;
import org.polarsys.capella.test.framework.context.SessionContext;

public class LibraryDragAndDropTest extends AbstractDiagramTestCase {

  // DndTestModel diagrams
  private String OAIB_DIAGRAM = "[OAIB] OperationalActivity Model";
  private String OAB_DIAGRAM = "[OAB] Operational Entities";
  private String ORB_DIAGRAM = "[ORB] Operational Entities";
  private String COC_DIAGRAM = "[COC] OperationalCapability Model";
  private String OCB_DIAGRAM = "[OCB] Operational Capabilities";
  private String EAB_DIAGRAM = "[EAB] Structure";
  private String PDFB_DIAGRAM = "[PDFB] Root Physical Function";
  private String PAB_DIAGRAM = "[PAB] Structure";
  private String MS_DIAGRAM = "[M&S] Default Region";
  private String MSM_DIAGRAM = "[MSM] Default Region";
  private String CDB_DIAGRAM = "[CDB] Data";
  private String SDFB_DIAGRAM = "[SDFB] SystemFunction Model";
  private String SAB_DIAGRAM = "[SAB] Structure";
  private String MB_DIAGRAM = "[MB] Missions";
  private String CM_DIAGRAM = "[CM] Mission Model";
  private String CC_DIAGRAM = "[CC] Capability Model";
  private String MCB_DIAGRAM = "[MCB] Capabilities";
  private String LDFB_DIAGRAM = "[LDFB] Root Logical Function";
  private String LAB_DIAGRAM = "[LAB] Structure";
  private String CRB_DIAGRAM = "[CRB] Capabilities";
  
  // Library1 elements
  private String P_FUNCTION = "bac9b02d-04de-4c22-afdb-b1c92d094dc2";
  private String P_COMPONENT = "1911635d-088e-4b0f-a551-d157bfde80f0";
  private String O_ACTIVITY = "5ba1255d-4a7d-4d73-be60-fb66840bcc28";
  private String O_ENTITY = "f6cc83c7-743b-46c4-ad36-51f2b8055175";
  private String O_ROLE = "2cc2f57a-92ba-4fc7-9c22-a47b6c8df662";
  private String O_CAPA = "0dbfb439-115f-4643-a6e0-654953e4c0f5";
  private String L_FUNCTION = "071d968d-0daa-4274-97ed-f170bcda024f";
  private String L_COMPONENT = "bc57803e-3f4b-4759-8567-1483e414b4be";
  private String E_CONFITEM = "1e89bae4-a486-4d55-aea9-f641f5e18a9b";
  private String MISSION = "24295d77-2a39-4fd9-b6c6-ad6f3a82ab44";
  private String S_CAPA = "791649e5-3154-4ce9-8204-22f3d0df2aa0";
  private String S_ACTOR = "a7712f42-9669-428d-bca6-4791ce9b6346";
  private String S_FUNCTION = "11e9b88d-535e-4c7c-a6d5-64ac6a4c9bd8";
  private String MODE = "bd1d1bb3-f9eb-4a0d-b0b8-ca0242897ecc";
  private String EXCHANGE_ITEM = "b52cc388-b9bd-4a73-887b-288733bc65c7";
  private String LITERAL_NUMERIC_VALUE = "9342d26d-66a5-4bbc-ae09-edfc6a5befcf";
  private String DATA_PKG = "11b13c2f-74a7-4b1c-a233-bf10aacf22c8";
  private String CLASS = "874186a4-d227-4e76-aea3-4a501f8a8a2a";
  private String INTERFACE = "7d840bbc-519a-445b-986d-23f4d1d1168d";
  private String COLLECTION = "5d7406ff-02f4-4c42-beb2-b24d49788a23";
  private String DATATYPE = "54110cfa-348e-43fe-9fa4-ec02a442a39c";
  private String ENUM = "4662bcde-491f-48b3-8e80-8b700a03a4fa";
  private String INTERFACE_PKG = "dfa63618-66e2-4ed0-9ea2-684364f6089c";
  private String UNIT = "d4a25f54-b45b-4ec1-92fb-7bcf7f2c5ad0";
  private String L_CAPABILITY = "3604b6ab-d92e-4dc5-a4c9-249a305bcba9";
  
  // DndTestModel elements
  private String MODEL_P_COMPONENT = "77493fc9-0c6a-45bf-9dd0-e77896eaf924";
  private String MODEL_L_COMPONENT = "1fc85f11-c350-4a5f-8043-3cd4f0fd7ec9";
  private String MODEL_S_COMPONENT = "c5e23053-7c66-4413-9ee8-d82de1b145bb";
  
  private String LIB2_SYSTEMFUNCTION_1 = "4f840640-726f-4079-b03d-5dfba733ffb3"; //$NON-NLS-1$
  
  @Override
  public List<String> getRequiredTestModels() {
    List<String> requiredTestModels = new ArrayList<String>();
    requiredTestModels.add(getRequiredTestModel());
    requiredTestModels.add(getRequiredTestLibrary());
    requiredTestModels.add(getRequiredTestLibrary2());

    return requiredTestModels;
  }

  @Override
  protected String getRequiredTestModel() {
	return "DndTestModel";
  }

  protected String getRequiredTestLibrary() {
    return "Library1";
  }

  protected String getRequiredTestLibrary2() {
    return "Library2";
  }

  @Override
  public void test() throws Exception {

    Session session = getSession(getRequiredTestModel());

    SessionContext context = new SessionContext(session);
    
    //OAIB
    DiagramContext diagramContext = new OpenDiagramStep(context, OAIB_DIAGRAM).run();
    dragAndDropShouldFail(diagramContext, O_ACTIVITY,diagramContext.getDiagramId(),
        IDNDToolNameConstants.TOOL_OAIB_DND_OPERATIONALACTIVITY_FROM_EXPLORER);
    diagramContext.close();
    
    //OAB
    diagramContext = new OpenDiagramStep(context, OAB_DIAGRAM).run();
    dragAndDropShouldFail(diagramContext, O_ACTIVITY, diagramContext.getDiagramId(),
        IDNDToolNameConstants.TOOL_OAB_DND_OPERATIONALACTIVITIES_FROM_EXPLORER);
    dragAndDropShouldFail(diagramContext, O_ACTIVITY, diagramContext.getDiagramId(),
        IDNDToolNameConstants.TOOL_OAB_DND_OPERATIONALACTIVITIES_FROM_EXPLORER_TO_ROLE);

    dragAndDropShouldFail(diagramContext, O_ENTITY, diagramContext.getDiagramId(),
        IDNDToolNameConstants.TOOL_OAB_DND_ENTITIES_FROM_EXPLORER);
    dragAndDropShouldFail(diagramContext, O_ROLE, diagramContext.getDiagramId(),
        IDNDToolNameConstants.TOOL_OAB_DND_ROLES_FROM_EXPLORER);
    diagramContext.close();
    
    //ORB
    diagramContext = new OpenDiagramStep(context, ORB_DIAGRAM).run();
    dragAndDropShouldFail(diagramContext, O_ROLE, diagramContext.getDiagramId(),
        IDNDToolNameConstants.TOOL_ORB_DND_ROLE_FOM_EXPLORER);
    dragAndDropShouldFail(diagramContext, O_ACTIVITY, diagramContext.getDiagramId(),
        IDNDToolNameConstants.TOOL_ORB_DND_OPERATIONALACTIVITIES_FROM_EXPLORER);
    diagramContext.close();
    
    //OCB
    diagramContext = new OpenDiagramStep(context, OCB_DIAGRAM).run();
    dragAndDropShouldFail(diagramContext, O_ENTITY, diagramContext.getDiagramId(),
        IDNDToolNameConstants.TOOL_OCB_DND_ENTITIES_FROM_EXPLORER);
    dragAndDropShouldFail(diagramContext, O_CAPA, diagramContext.getDiagramId(),
        IDNDToolNameConstants.TOOL_OCB_DND_OPERATIONAL_CAPABILITIES_FROM_EXPLORER);
    diagramContext.close();
    
    //COC
    diagramContext = new OpenDiagramStep(context, COC_DIAGRAM).run();
    dragAndDropShouldFail(diagramContext, O_ENTITY, diagramContext.getDiagramId(),
        IDNDToolNameConstants.TOOL_OCB_DND_ENTITIES_FROM_EXPLORER);
    dragAndDropShouldFail(diagramContext, O_CAPA, diagramContext.getDiagramId(),
        IDNDToolNameConstants.TOOL_OCB_DND_OPERATIONAL_CAPABILITIES_FROM_EXPLORER);
    diagramContext.close();

    // SDFB
    diagramContext = new OpenDiagramStep(context, SDFB_DIAGRAM).run();
    dragAndDropShouldFail(diagramContext, S_FUNCTION, diagramContext.getDiagramId(),
        IDNDToolNameConstants.TOOL_SDFB_DND_FUNCTION_FROM_EXPLORER);
    diagramContext.close();

    // SAB
    diagramContext = new OpenDiagramStep(context, SAB_DIAGRAM).run();
    dragAndDropShouldFail(diagramContext, S_ACTOR, diagramContext.getDiagramId(),
        IDNDToolNameConstants.TOOL_SAB_DND_ACTORS_FROM_EXPLORER);
     

    EObject droppedElementSemantic = context.getSemanticElement(MODEL_S_COMPONENT);
    DSemanticDecorator decorator = diagramContext.getView(droppedElementSemantic);

    dragAndDropShouldFail(diagramContext, S_FUNCTION, decorator.getUid(),
        IDNDToolNameConstants.TOOL_SAB_DND_FUNCTION_ALLOCATION_FROM_EXPLORER);
    dragAndDropShouldFail(diagramContext, LIB2_SYSTEMFUNCTION_1, decorator.getUid(),
	  IDNDToolNameConstants.TOOL_SAB_DND_FUNCTION_ALLOCATION_FROM_EXPLORER);
    
    diagramContext.close();

    // MCB
    diagramContext = new OpenDiagramStep(context, MCB_DIAGRAM).run();
    dragAndDropShouldFail(diagramContext, MISSION, diagramContext.getDiagramId(),
        IDNDToolNameConstants.TOOL_MCB_DND_MISSIONS_FROM_EXPLORER);
    dragAndDropShouldFail(diagramContext, S_CAPA, diagramContext.getDiagramId(),
        IDNDToolNameConstants.TOOL_MCB_DND_CAPABILITIES_FROM_EXPLORER);
    dragAndDropShouldFail(diagramContext, S_ACTOR, diagramContext.getDiagramId(),
        IDNDToolNameConstants.TOOL_MCB_DND_ACTORS_FROM_EXPLORER);

    // CC
    diagramContext = new OpenDiagramStep(context, CC_DIAGRAM).run();
    dragAndDropShouldFail(diagramContext, MISSION, diagramContext.getDiagramId(),
        IDNDToolNameConstants.TOOL_CC_DND_MISSIONS_FROM_EXPLORER);
    dragAndDropShouldFail(diagramContext, S_CAPA, diagramContext.getDiagramId(),
        IDNDToolNameConstants.TOOL_CC_DND_CAPABILITIES_FROM_EXPLORER);
    dragAndDropShouldFail(diagramContext, S_ACTOR, diagramContext.getDiagramId(),
        IDNDToolNameConstants.TOOL_CC_DND_ACTORS_FROM_EXPLORER);
    
    // MB
    diagramContext = new OpenDiagramStep(context, MB_DIAGRAM).run();
    dragAndDropShouldFail(diagramContext, MISSION, diagramContext.getDiagramId(),
        IDNDToolNameConstants.TOOL_MB_DND_MISSIONS_FROM_EXPLORER);
    dragAndDropShouldFail(diagramContext, S_CAPA, diagramContext.getDiagramId(),
        IDNDToolNameConstants.TOOL_MB_DND_CAPABILITIES_FROM_EXPLORER);
    dragAndDropShouldFail(diagramContext, S_ACTOR, diagramContext.getDiagramId(),
        IDNDToolNameConstants.TOOL_MB_DND_ACTORS_FROM_EXPLORER);
    
    // CM
    diagramContext = new OpenDiagramStep(context, CM_DIAGRAM).run();
    dragAndDropShouldFail(diagramContext, MISSION, diagramContext.getDiagramId(),
        IDNDToolNameConstants.TOOL_CM_DND_MISSIONS_FROM_EXPLORER);
    dragAndDropShouldFail(diagramContext, S_CAPA, diagramContext.getDiagramId(),
        IDNDToolNameConstants.TOOL_CM_DND_CAPABILITIES_FROM_EXPLORER);
    dragAndDropShouldFail(diagramContext, S_ACTOR, diagramContext.getDiagramId(),
        IDNDToolNameConstants.TOOL_CM_DND_ACTORS_FROM_EXPLORER);
    
    // LDFB
    diagramContext = new OpenDiagramStep(context, LDFB_DIAGRAM).run();
    dragAndDropShouldFail(diagramContext, L_FUNCTION, diagramContext.getDiagramId(),
        IDNDToolNameConstants.TOOL_LDFB_DND_FUNCTION_FROM_EXPLORER);
    
    // LAB
    diagramContext = new OpenDiagramStep(context, LAB_DIAGRAM).run();
    dragAndDropShouldFail(diagramContext, L_COMPONENT, diagramContext.getDiagramId(),
        IDNDToolNameConstants.TOOL_LAB_DND_COMPONENTS_FROM_EXPLORER);
    droppedElementSemantic = context.getSemanticElement(MODEL_L_COMPONENT);
    decorator = diagramContext.getView(droppedElementSemantic);
    dragAndDropShouldFail(diagramContext, L_FUNCTION, decorator.getUid(),
        IDNDToolNameConstants.TOOL_LAB_DND_FUNCTION_ALLOCATION_FROM_EXPLORER);
    
    // CRB
    diagramContext = new OpenDiagramStep(context, CRB_DIAGRAM).run();
    dragAndDropShouldFail(diagramContext, L_COMPONENT, diagramContext.getDiagramId(),
        IDNDToolNameConstants.TOOL_CRB_DND_COMPONENT_FROM_EXPLORER);
    dragAndDropShouldFail(diagramContext, L_CAPABILITY, diagramContext.getDiagramId(),
		        IDNDToolNameConstants.TOOL_CRB_DND_CAPABILITIES_FROM_EXPLORER);
    
    // PDFB
    diagramContext = new OpenDiagramStep(context, PDFB_DIAGRAM).run();
    dragAndDropShouldFail(diagramContext, P_FUNCTION, diagramContext.getDiagramId(),
        IDNDToolNameConstants.TOOL_PDFB_DND_FUNCTION_FROM_EXPLORER);
    
    // PAB
    diagramContext = new OpenDiagramStep(context, PAB_DIAGRAM).run();
    dragAndDropShouldFail(diagramContext, P_COMPONENT, diagramContext.getDiagramId(),
        IDNDToolNameConstants.TOOL_PAB_DND_COMPONENTS_FROM_EXPLORER);

    droppedElementSemantic = context.getSemanticElement(MODEL_P_COMPONENT);
    decorator = diagramContext.getView(droppedElementSemantic);
    dragAndDropShouldFail(diagramContext, P_FUNCTION, decorator.getUid(),
        IDNDToolNameConstants.TOOL_PAB_DND_ABSTRACTFUNCTION_ALLOCATION_FROM_EXPLORER);
    
    //EAB
    diagramContext = new OpenDiagramStep(context, EAB_DIAGRAM).run();
    dragAndDropShouldFail(diagramContext, E_CONFITEM, diagramContext.getDiagramId(),
        IDNDToolNameConstants.TOOL_EAB_DND_CONFIGURATIONITEM_FROM_EXPLORER);
    
    //M&S
    diagramContext = new OpenDiagramStep(context, MS_DIAGRAM).run();
    dragAndDropShouldFail(diagramContext, MODE, diagramContext.getDiagramId(),
        IDNDToolNameConstants.TOOL_MS_DND_MODESTATE_FROM_EXPLORER);
    
    //MSM
    diagramContext = new OpenDiagramStep(context, MSM_DIAGRAM).run();
    dragAndDropShouldFail(diagramContext, MODE, diagramContext.getDiagramId(),
        IDNDToolNameConstants.TOOL_MS_DND_MODESTATE_FROM_EXPLORER);
    

    
    //CDB
    diagramContext = new OpenDiagramStep(context, CDB_DIAGRAM).run();
    dragAndDropShouldWork(diagramContext, EXCHANGE_ITEM, diagramContext.getDiagramId(),
        IDNDToolNameConstants.TOOL_CDB_DND_EXCHANGEITEM_FROM_EXPLORER);
    dragAndDropShouldWork(diagramContext, LITERAL_NUMERIC_VALUE, diagramContext.getDiagramId(),
        IDNDToolNameConstants.TOOL_CDB_DND_DATAVALUE_FROM_EXPLORER);
    dragAndDropShouldWork(diagramContext, DATA_PKG, diagramContext.getDiagramId(),
        IDNDToolNameConstants.TOOL_CDB_DND_DATAPKG_FROM_EXPLORER);
    dragAndDropShouldWork(diagramContext, CLASS, diagramContext.getDiagramId(),
        IDNDToolNameConstants.TOOL_CDB_DND_CLASS_FROM_EXPLORER);
    dragAndDropShouldWork(diagramContext, INTERFACE, diagramContext.getDiagramId(),
        IDNDToolNameConstants.TOOL_CDB_DND_INTERFACE_FROM_EXPLORER);
    dragAndDropShouldWork(diagramContext, COLLECTION, diagramContext.getDiagramId(),
        IDNDToolNameConstants.TOOL_CDB_DND_COLLECTION_FROM_EXPLORER);
    dragAndDropShouldWork(diagramContext, UNIT, diagramContext.getDiagramId(),
        IDNDToolNameConstants.TOOL_CDB_DND_UNIT_FROM_EXPLORER);
    dragAndDropShouldWork(diagramContext, DATATYPE, diagramContext.getDiagramId(),
        IDNDToolNameConstants.TOOL_CDB_DND_DATATYPE_FROM_EXPLORER);
    dragAndDropShouldWork(diagramContext, INTERFACE_PKG, diagramContext.getDiagramId(),
        IDNDToolNameConstants.TOOL_CDB_DND_INTERFACEPKG_FROM_EXPLORER);
    dragAndDropShouldWork(diagramContext, ENUM, diagramContext.getDiagramId(),
        IDNDToolNameConstants.TOOL_CDB_DND_ENUMERATION_FROM_EXPLORER);

  }
  
  private void dragAndDropShouldFail(DiagramContext diagramContext, String droppedElementId,
      String targetId, String tool) {
    EObject droppedElementSemantic = diagramContext.getSessionContext().getSemanticElement(droppedElementId);
    
    new DragAndDropFromProjectExplorerTool(diagramContext,
        tool, droppedElementSemantic, targetId).cannotRun();
  }

  private void dragAndDropShouldWork(DiagramContext diagramContext, String droppedElementId,
      String targetId, String tool) {
    EObject droppedElementSemantic = diagramContext.getSessionContext().getSemanticElement(droppedElementId);

    new DragAndDropFromProjectExplorerTool(diagramContext, tool, droppedElementSemantic, targetId).contextOk();
    ;
  }
}
