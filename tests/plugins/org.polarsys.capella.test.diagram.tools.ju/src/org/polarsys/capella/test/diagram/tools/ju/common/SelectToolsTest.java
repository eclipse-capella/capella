/*******************************************************************************
 * Copyright (c) 2018, 2023 THALES GLOBAL SERVICES.
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
package org.polarsys.capella.test.diagram.tools.ju.common;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.eclipse.emf.ecore.EObject;
import org.eclipse.sirius.business.api.dialect.DialectManager;
import org.eclipse.sirius.business.api.session.Session;
import org.eclipse.sirius.diagram.AbstractDNode;
import org.eclipse.sirius.diagram.DDiagram;
import org.eclipse.sirius.diagram.DDiagramElement;
import org.eclipse.sirius.diagram.DEdge;
import org.eclipse.sirius.diagram.DNodeContainer;
import org.eclipse.sirius.diagram.description.DiagramDescription;
import org.eclipse.sirius.diagram.description.DiagramElementMapping;
import org.eclipse.sirius.viewpoint.DRepresentation;
import org.eclipse.sirius.viewpoint.DRepresentationDescriptor;
import org.eclipse.sirius.viewpoint.DRepresentationElement;
import org.eclipse.sirius.viewpoint.DSemanticDecorator;
import org.polarsys.capella.test.diagram.common.ju.api.AbstractDiagramTestCase;
import org.polarsys.capella.test.diagram.common.ju.context.CommonDiagram;
import org.polarsys.capella.test.diagram.common.ju.context.DiagramContext;
import org.polarsys.capella.test.diagram.common.ju.wrapper.utils.DiagramHelper;
import org.polarsys.capella.test.framework.context.SessionContext;
import org.polarsys.capella.test.framework.helpers.GuiActions;

/**
 * Test whether the selection tools are properly selecting expected elements
 */
public class SelectToolsTest extends AbstractDiagramTestCase {

  public final String REC_DIAGRAM = "_yYiSnnjqEea__MYrXGSERA";//$NON-NLS-1$
  public final String REC_PHYSICAL_COMPONENT = "1c483402-df88-446f-a39b-1e0e9720faa9";//$NON-NLS-1$
  public final String REC_PHYSICAL_FUNCTION_1 = "e24662c4-47b2-445f-9d18-7214fca7013e";//$NON-NLS-1$
  public final String REC_FIP1_PF1 = "f0c1cbdc-5fe6-44e9-a691-1d5b540a8e51";//$NON-NLS-1$
  public final String REC_PHYSICAL_FUNCTION_2 = "ccfb7dc0-c861-44db-95ea-0a216b9ec643";//$NON-NLS-1$
  public final String REC_FIP1_PF2 = "0ced1a83-7e58-4db2-87b9-223756fcfed6";//$NON-NLS-1$

  public final String RPL_DIAGRAM = "_yYiSn3jqEea__MYrXGSERA";//$NON-NLS-1$
  public final String RPL_PHYSICAL_COMPONENT = "c9e311a0-b855-48f6-8ee6-5830f2873e0b";//$NON-NLS-1$
  public final String RPL_PHYSICAL_FUNCTION_1 = "c5503a00-fae8-439d-90a7-6b8644d2f609";//$NON-NLS-1$
  public final String RPL_FIP1_PF1 = "cf123dcb-5d86-47fa-9dbb-e6670495bfdb";//$NON-NLS-1$
  public final String RPL_PHYSICAL_FUNCTION_2 = "238797f1-c012-446a-9b79-e94cf88bd180";//$NON-NLS-1$
  public final String RPL_FIP1_PF2 = "78736f13-8b41-4119-b0b4-48e733598c85";//$NON-NLS-1$

  public final String OAB_DIAGRAM = "_x0W387OIEemYUPV0pY7feg";//$NON-NLS-1$
  public final String PAB_DIAGRAM = "_yYiSoHjqEea__MYrXGSERA";//$NON-NLS-1$
  public final String SAB_DIAGRAM = "_yYhranjqEea__MYrXGSERA";//$NON-NLS-1$
  public final String LAB_DIAGRAM = "_yYhrgHjqEea__MYrXGSERA";//$NON-NLS-1$
  public final String SDFB_DIAGRAM = "_yYhrZXjqEea__MYrXGSERA";//$NON-NLS-1$
  public final String LDFB_DIAGRAM = "_yYhrfXjqEea__MYrXGSERA";//$NON-NLS-1$
  public final String PDFB_DIAGRAM = "_yYiSqHjqEea__MYrXGSERA";//$NON-NLS-1$

  public final String FCAT_SAB = "01ad36d4-7802-4317-ae4c-787823a980f3"; //$NON-NLS-1$
  public final String FCAT_FE_SAB = "3a5b3611-9455-470f-a88f-e27e34700f7f"; //$NON-NLS-1$
  public final String FCAT_PORT_SAB = "_p12kMHy5Ee6GwptREVxSoQ"; //$NON-NLS-1$
  public final String CCAT_SAB = "4fd16531-1c47-49ca-ba93-5fe70530f2d5"; //$NON-NLS-1$
  public final String CCAT_PORT_SAB = "_4vwjQnyvEe6GwptREVxSoQ"; //$NON-NLS-1$
  public final String CCAT_CE_SAB = "a3b8046f-cb0e-4104-814e-dd592b8d1be6"; //$NON-NLS-1$
  public final String PCAT_SAB = "44acd9e6-b8cc-4f6f-a983-99bdb464d4e4"; //$NON-NLS-1$
  public final String PCAT_PORT_SAB = "_mo0PMHyzEe6GwptREVxSoQ"; //$NON-NLS-1$
  public final String PCAT_PL_SAB = "9d603b5f-f114-4556-8d84-5a93d6d98aa7"; //$NON-NLS-1$

  // NO CE / PL on xDFB
  public final String FCAT_SDFB = "c3a5698a-98e8-4318-b9cb-dc0a05ea9f61"; //$NON-NLS-1$
  public final String FCAT_FE_SDFB = "bcc99ef8-ea8a-4926-9d6f-4d6ca6c12fc1"; //$NON-NLS-1$
  public final String FCAT_PORT_SDFB = "_ynIbUHyzEe6GwptREVxSoQ"; //$NON-NLS-1$

  public final String FCAT_LDFB = "9badd06b-4bfa-46f9-995c-7816fd93a261"; //$NON-NLS-1$
  public final String FCAT_FE_LDFB = "b073eb42-1859-4a4d-9dde-06903707b4a9"; //$NON-NLS-1$
  public final String FCAT_PORT_LDFB = "_TyaSMH1pEe6c-68cChSq1Q"; //$NON-NLS-1$

  public final String FCAT_PDFB = "bf08add7-f0bb-42b7-9973-5c8659723977"; //$NON-NLS-1$
  public final String FCAT_FE_PDFB = "85240d00-9922-4206-aea9-3f330fdac4d5"; //$NON-NLS-1$
  public final String FCAT_PORT_PDFB = "_CwoOc3y0Ee6GwptREVxSoQ"; //$NON-NLS-1$

  public final String FCAT_LAB = "664aa8e3-8e72-45b5-ae3a-a7d55746bd41"; //$NON-NLS-1$
  public final String FCAT_PORT_LAB = "_cBUs0H1HEe6c-68cChSq1Q"; //$NON-NLS-1$
  public final String FCAT_FE_LAB = "3e233685-06b6-42a9-925e-f9dd0c43b753"; //$NON-NLS-1$
  public final String CCAT_LAB = "a1b10c3e-bd46-4433-9257-48170f5ef09e"; //$NON-NLS-1$
  public final String CCAT_PORT_LAB = "_sUe45nywEe6GwptREVxSoQ"; //$NON-NLS-1$
  public final String CCAT_CE_LAB = "eb07cb8d-8ee6-4c6f-ab31-bcb2977ffaa9"; //$NON-NLS-1$
  public final String PCAT_LAB = "9396522f-e187-4437-acfa-efc1a25b5b3f"; //$NON-NLS-1$
  public final String PCAT_PORT_LAB = "_G1UTQnyxEe6GwptREVxSoQ"; //$NON-NLS-1$
  public final String PCAT_PL_LAB = "bac94c68-e1b4-494c-a2ea-84ce2d43f3ae"; //$NON-NLS-1$

  public final String FCAT_PAB_EDGE = "_vO7sgXc5Ee6beLviQov1LQ"; //$NON-NLS-1$
  public final String FCAT_PAB = "931993e9-058d-42c9-90f7-ece80b7fff43"; //$NON-NLS-1$
  public final String FCAT_PORT_PAB = "_A5uv83J8Ee6MRMSmk5q7sQ"; //$NON-NLS-1$
  public final String FCAT_FE_PAB = "2198eb3c-c766-4abb-b2bb-5e5e57db86aa"; //$NON-NLS-1$
  public final String FCAT_FE2_PAB = "fabe2bde-f0f1-4275-a0b0-82bf22e55819"; //$NON-NLS-1$
  public final String CCAT_PAB = "99bbe3c5-3423-4779-8f9d-fc3264759ba2"; //$NON-NLS-1$
  public final String CCAT_PORT_PAB = "_lYG1BH1SEe6c-68cChSq1Q"; //$NON-NLS-1$
  public final String CCAT_CE_PAB = "18f32d5e-221a-44ab-b7dd-3c99f65a7dce"; //$NON-NLS-1$
  public final String PCAT_PAB = "e72a1517-a8ef-4ecc-9c62-cb929938b1a4"; //$NON-NLS-1$
  public final String PCAT_PORT_PAB = "_rqm90HfAEe6OhIHMRH8U5Q"; //$NON-NLS-1$
  public final String PCAT_PL_PAB = "c4de8d15-7a19-4de0-aad6-0ec1b74be12d"; //$NON-NLS-1$

  public final String PPath1 = "727d2255-e536-40e5-97d8-43628029b2b3";//$NON-NLS-1$
  public final String PPath1_Node_Decorator = "_YjiFEHJ3Ee6MRMSmk5q7sQ";//$NON-NLS-1$
  public final String PHYSICAL_LINK_1_EDGE = "_nLm-oooPEeaQmcRqIfTB6w";//$NON-NLS-1$
  public final String PHYSICAL_LINK_1_PORT1 = "_nJb0Z4oPEeaQmcRqIfTB6w";//$NON-NLS-1$
  public final String PHYSICAL_LINK_1_PORT2 = "_nKF7s4oPEeaQmcRqIfTB6w";//$NON-NLS-1$
  public final String PHYSICAL_LINK_CATEGORY_1_EDGE = "_rqqoMHfAEe6OhIHMRH8U5Q";//$NON-NLS-1$
  public final String PHYSICAL_LINK_CATEGORY_1_PORT1 = "_rqm90HfAEe6OhIHMRH8U5Q";//$NON-NLS-1$
  public final String PHYSICAL_LINK_CATEGORY_1_PORT2 = "_rqpaEXfAEe6OhIHMRH8U5Q";//$NON-NLS-1$

  public final String OP1 = "c27f6524-d09e-4962-8f71-c347796bfe6f";//$NON-NLS-1$
  public final String OP1_EDGE1 = "_6VUMebOIEemYUPV0pY7feg";//$NON-NLS-1$
  public final String OP1_EDGE2 = "_6Vd9b7OIEemYUPV0pY7feg";//$NON-NLS-1$
  public final String OP1_NODE1 = "_6UbbobOIEemYUPV0pY7feg";//$NON-NLS-1$
  public final String OP1_DECORATOR = "_r8DgcLOLEemYUPV0pY7feg";//$NON-NLS-1$
  public final String OP1_NODE2 = "_6UuWg7OIEemYUPV0pY7feg";//$NON-NLS-1$
  public final String OP1_NODE3 = "_6URqkLOIEemYUPV0pY7feg";//$NON-NLS-1$


  public final String FC1 = "3ba5658d-1416-4928-bb63-bf26a021cc34";//$NON-NLS-1$
  public final String FC1_Node_Decorator = "_8czq8HJ2Ee6MRMSmk5q7sQ";//$NON-NLS-1$
  public final String FE1 = "_nNBT4IoPEeaQmcRqIfTB6w";//$NON-NLS-1$
  public final String FE1_PORT1 = "_nKgycIoPEeaQmcRqIfTB6w";//$NON-NLS-1$
  public final String FE1_PORT2 = "_nLGBQ4oPEeaQmcRqIfTB6w";//$NON-NLS-1$

  public final String FCAT1_PORT1 = "_A5uI4HJ8Ee6MRMSmk5q7sQ";//$NON-NLS-1$
  public final String FCAT1_PORT2 = "_A5uv83J8Ee6MRMSmk5q7sQ";//$NON-NLS-1$
  public final String FCAT1_EDGE = "_A5vXA3J8Ee6MRMSmk5q7sQ";//$NON-NLS-1$

  @Override
  protected String getRequiredTestModel() {
    return "In-Flight Entertainment System"; //$NON-NLS-1$
  }

  @Override
  public void test() throws Exception {

    Session s = getSession(getRequiredTestModel());
    SessionContext sc = new SessionContext(s);

    testCategoriesSemanticElements(sc);

    testSelectRelatedPPElements(sc, PAB_DIAGRAM, PPath1, PPath1_Node_Decorator, PHYSICAL_LINK_CATEGORY_1_EDGE,
        PHYSICAL_LINK_CATEGORY_1_PORT1, PHYSICAL_LINK_CATEGORY_1_PORT2, PHYSICAL_LINK_1_EDGE, PHYSICAL_LINK_1_PORT1,
        PHYSICAL_LINK_1_PORT2);
    testSelectRelatedFCElements(sc, PAB_DIAGRAM, FC1, FC1_Node_Decorator, FCAT1_EDGE, FCAT1_PORT1, FCAT1_PORT2, FE1,
        FE1_PORT1, FE1_PORT2);
    testSelectRelatedFCElements(sc, OAB_DIAGRAM, OP1, OP1_NODE1, OP1_EDGE1, OP1_NODE2, OP1_EDGE2, OP1_NODE3,
        OP1_DECORATOR);


    testSelectRelatedRecs(sc, REC_DIAGRAM, REC_PHYSICAL_COMPONENT, REC_FIP1_PF2, REC_PHYSICAL_COMPONENT,
        REC_PHYSICAL_FUNCTION_1, REC_PHYSICAL_FUNCTION_2, REC_FIP1_PF1);

    testSelectRelatedRpls(sc, RPL_DIAGRAM, RPL_PHYSICAL_COMPONENT, RPL_PHYSICAL_FUNCTION_2, RPL_PHYSICAL_FUNCTION_1,
        RPL_FIP1_PF2, RPL_PHYSICAL_COMPONENT, RPL_FIP1_PF1);

    // For each kind of diagrams from sample project, check if select tools are working.
    HashSet<DiagramDescription> descriptions = new HashSet<DiagramDescription>();
    for (DRepresentation rep : DialectManager.INSTANCE.getAllRepresentations(s)) {
      if (rep instanceof DDiagram) {
        DDiagram diagram = (DDiagram) rep;
        if (!descriptions.contains(diagram.getDescription())) {
          System.err.println(diagram.getDescription().getName());
          descriptions.add(diagram.getDescription());
          testCommonTools(sc, diagram);
        }
      }
    }
  }

  protected void testCategoriesSemanticElements(SessionContext sc) {
    // No categories nor category ports for OAB

    // SAB
    testSemanticElements(sc, SAB_DIAGRAM, FCAT_SAB, FCAT_SAB, FCAT_FE_SAB);
    testSemanticElements(sc, SAB_DIAGRAM, FCAT_PORT_SAB, FCAT_SAB, FCAT_FE_SAB);
    testSemanticElements(sc, SAB_DIAGRAM, CCAT_SAB, CCAT_SAB, CCAT_CE_SAB);
    testSemanticElements(sc, SAB_DIAGRAM, CCAT_PORT_SAB, CCAT_SAB, CCAT_CE_SAB);
    testSemanticElements(sc, SAB_DIAGRAM, PCAT_SAB, PCAT_SAB, PCAT_PL_SAB);
    testSemanticElements(sc, SAB_DIAGRAM, PCAT_PORT_SAB, PCAT_SAB, PCAT_PL_SAB);

    // SDFB
    testSemanticElements(sc, SDFB_DIAGRAM, FCAT_SDFB, FCAT_SDFB, FCAT_FE_SDFB);
    testSemanticElements(sc, SDFB_DIAGRAM, FCAT_PORT_SDFB, FCAT_SDFB, FCAT_FE_SDFB);

    // LAB
    testSemanticElements(sc, LAB_DIAGRAM, FCAT_LAB, FCAT_LAB, FCAT_FE_LAB);
    testSemanticElements(sc, LAB_DIAGRAM, FCAT_PORT_LAB, FCAT_LAB, FCAT_FE_LAB);
    testSemanticElements(sc, LAB_DIAGRAM, CCAT_LAB, CCAT_LAB, CCAT_CE_LAB);
    testSemanticElements(sc, LAB_DIAGRAM, CCAT_PORT_LAB, CCAT_LAB, CCAT_CE_LAB);
    testSemanticElements(sc, LAB_DIAGRAM, PCAT_LAB, PCAT_LAB, PCAT_PL_LAB);
    testSemanticElements(sc, LAB_DIAGRAM, PCAT_PORT_LAB, PCAT_LAB, PCAT_PL_LAB);

    // LDFB
    testSemanticElements(sc, LDFB_DIAGRAM, FCAT_LDFB, FCAT_LDFB, FCAT_FE_LDFB);
    testSemanticElements(sc, LDFB_DIAGRAM, FCAT_PORT_LDFB, FCAT_LDFB, FCAT_FE_LDFB);

    // PAB
    testSemanticElements(sc, PAB_DIAGRAM, FCAT_PAB_EDGE, FCAT_PAB, FCAT_FE_PAB);
    testSemanticElements(sc, PAB_DIAGRAM, FCAT_PORT_PAB, FCAT_PAB, FCAT_FE_PAB, FCAT_FE2_PAB);
    testSemanticElements(sc, PAB_DIAGRAM, CCAT_PAB, CCAT_PAB, CCAT_CE_PAB);
    testSemanticElements(sc, PAB_DIAGRAM, CCAT_PORT_PAB, CCAT_PAB, CCAT_CE_PAB);
    testSemanticElements(sc, PAB_DIAGRAM, PCAT_PAB, PCAT_PAB, PCAT_PL_PAB);
    testSemanticElements(sc, PAB_DIAGRAM, PCAT_PORT_PAB, PCAT_PAB, PCAT_PL_PAB);

    // PDFB
    testSemanticElements(sc, PDFB_DIAGRAM, FCAT_PDFB, FCAT_PDFB, FCAT_FE_PDFB);
    testSemanticElements(sc, PDFB_DIAGRAM, FCAT_PORT_PDFB, FCAT_PDFB, FCAT_FE_PDFB);

  }

  protected void testSemanticElements(SessionContext sc, String diagramId, String sourceElementId,
      String... expectedSelectedElements) {
    DDiagram diagram = getDiagram(sc.getSession(), diagramId);
    CommonDiagram cd = new CommonDiagram(sc, diagram);
    cd.open();
    cd.refreshDiagram();

    List<EObject> semanticElements = null;

    DSemanticDecorator decorator = cd.getView(sourceElementId);
    if (decorator == null)
      decorator = org.polarsys.capella.test.diagram.common.ju.wrapper.utils.DiagramHelper
          .getOnDiagramByUID(cd.getDiagram(), sourceElementId);

    if (decorator instanceof DRepresentationElement) {
      semanticElements = ((DRepresentationElement) decorator).getSemanticElements();
    } else {
      fail("Decorator is not RepresentationElement");
    }

    List<EObject> expectedElements = Arrays.stream(expectedSelectedElements).map(sel -> sc.getSemanticElement(sel))
        .map(EObject.class::cast).collect(Collectors.toList());

    assertTrue(semanticElements.size() == expectedElements.size());
    assertTrue(semanticElements.containsAll(expectedElements));
  }

  protected void testCommonTools(SessionContext sc, DDiagram rep) {
    CommonDiagram cd = new CommonDiagram(sc, rep);
    DiagramContext dc = cd.open();

    // For each kind of elements displayed in the diagram, check if select tools are working.
    HashSet<DiagramElementMapping> mapping = new HashSet<DiagramElementMapping>();

    for (DDiagramElement de : new ArrayList<DDiagramElement>(dc.getDiagram().getOwnedDiagramElements())) {
      if (DiagramHelper.isDiagramElementSelectable(de)) {
        if (!mapping.contains(de.getDiagramElementMapping())) {
          mapping.add(de.getDiagramElementMapping());
          if (de instanceof DNodeContainer) {
            cd.selectOwnedElements(de.getUid());
          }
          if (de instanceof AbstractDNode) {
            cd.selectRelatedEdges(de.getUid());
          }
          if (de instanceof AbstractDNode) {
            cd.selectOwnedPorts(de.getUid());
            cd.selectRelatedRecs(de.getUid());
            cd.selectRelatedRpls(de.getUid());
          }
          if (de instanceof DEdge) {
            cd.selectRelatedRecs(de.getUid());
            cd.selectRelatedRpls(de.getUid());
          }
          cd.selectSameType(de.getUid());
          cd.selectSameMapping(de.getUid());
        }
      }
    }

    dc.close();
    sc.getSession().getTransactionalEditingDomain().getCommandStack().flush();
    GuiActions.flushASyncGuiJobs();
  }

  protected void testSelectRelatedRecs(SessionContext sc, String diagramId, String sourceElementId,
      String... expectedSelectedElements) {
    // Get diagram and ensure it is opened
    DDiagram diagram = getDiagram(sc.getSession(), diagramId);
    CommonDiagram cd = new CommonDiagram(sc, diagram);
    cd.open();
    cd.selectRelatedRecs(sourceElementId, expectedSelectedElements);
  }

  protected void testSelectRelatedFCElements(SessionContext sc, String diagramId, String sourceElementId,
      String... expectedSelectedElements) {
    // Get diagram and ensure it is opened
    DDiagram diagram = getDiagram(sc.getSession(), diagramId);
    CommonDiagram cd = new CommonDiagram(sc, diagram);
    cd.open();
    cd.selectRelatedFCElements(sourceElementId, expectedSelectedElements);
  }

  protected void testSelectRelatedPPElements(SessionContext sc, String diagramId, String sourceElementId,
      String... expectedSelectedElements) {
    // Get diagram and ensure it is opened
    DDiagram diagram = getDiagram(sc.getSession(), diagramId);
    CommonDiagram cd = new CommonDiagram(sc, diagram);
    cd.open();
    cd.selectRelatedPPElements(sourceElementId, expectedSelectedElements);
  }

  protected void testSelectRelatedRpls(SessionContext sc, String diagramId, String sourceElementId,
      String... expectedSelectedElements) {
    // Get diagram and ensure it is opened
    DDiagram diagram = getDiagram(sc.getSession(), diagramId);
    CommonDiagram cd = new CommonDiagram(sc, diagram);
    cd.open();
    cd.selectRelatedRpls(sourceElementId, expectedSelectedElements);
  }

  protected DDiagram getDiagram(Session s, String repId) {
    Optional<DRepresentationDescriptor> repDescriptor = DialectManager.INSTANCE.getAllRepresentationDescriptors(s)
        .stream().filter(d -> d.getUid().equals(repId)).findFirst();
    if (repDescriptor.isEmpty())
      fail("Could not find descriptor with id " + repId);
    DDiagram diagram = (DDiagram) repDescriptor.get().getRepresentation();
    return diagram;
  }
}
