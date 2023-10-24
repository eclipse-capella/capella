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
import java.util.HashSet;
import java.util.Optional;

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

  public final String PAB_DIAGRAM = "_yYiSoHjqEea__MYrXGSERA";//$NON-NLS-1$
  public final String FC1 = "3ba5658d-1416-4928-bb63-bf26a021cc34";//$NON-NLS-1$
  public final String FE1 = "fa61847f-3abe-4ab2-a857-72bd5e6b4d01";//$NON-NLS-1$
  public final String FCAT1 = "931993e9-058d-42c9-90f7-ece80b7fff43";//$NON-NLS-1$
  public final String P1 = "68d57242-548d-41c4-879d-3a68de004e23";//$NON-NLS-1$
  public final String P2 = "29281d72-e1d4-4f26-b78d-1c657a36fab8";//$NON-NLS-1$
  public final String P3 = "fc9728ae-ab7b-4ff9-949d-63b082c60c25";//$NON-NLS-1$
  public final String P4 = "a1277e72-90ac-45ef-82c6-84dd5009b55c";//$NON-NLS-1$

  public final String PPath1 = "727d2255-e536-40e5-97d8-43628029b2b3";//$NON-NLS-1$
  public final String PL1 = "b897b8a1-0131-4e85-8ee3-fd7d981dc583";//$NON-NLS-1$
  public final String PCAT1 = "e72a1517-a8ef-4ecc-9c62-cb929938b1a4";//$NON-NLS-1$
  public final String PP1 = "62d9e0f0-2b41-422a-aa33-02dd03925ed0";//$NON-NLS-1$
  public final String PP2 = "87083a2b-9b2a-4246-b5ba-e2d46771628e";//$NON-NLS-1$
  public final String PP3 = "5aa6b85b-52c3-4070-9f7e-d68ffa00c50d";//$NON-NLS-1$
  public final String PP4 = "23f6362b-85a2-4823-8ef9-131c26f8567f";//$NON-NLS-1$

  @Override
  protected String getRequiredTestModel() {
    return "In-Flight Entertainment System"; //$NON-NLS-1$
  }

  @Override
  public void test() throws Exception {

    Session s = getSession(getRequiredTestModel());
    SessionContext sc = new SessionContext(s);

    testSelectRelatedFCElements(sc, PAB_DIAGRAM, FC1, FE1, FCAT1, P1, P2, P3, P4);
    testSelectRelatedPPElements(sc, PAB_DIAGRAM, PPath1, PL1, PCAT1, PP1, PP2, PP3, PP4);

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
    Optional<DRepresentationDescriptor> repDescriptor = DialectManager.INSTANCE.getAllRepresentationDescriptors(s).stream()
        .filter(d -> d.getUid().equals(repId)).findFirst();
    if (repDescriptor.isEmpty())
      fail("Could not find descriptor with id " + repId);
    DDiagram diagram = (DDiagram) repDescriptor.get().getRepresentation();
    return diagram;
  }
}
