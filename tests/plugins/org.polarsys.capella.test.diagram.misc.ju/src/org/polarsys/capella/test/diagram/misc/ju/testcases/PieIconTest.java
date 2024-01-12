/*******************************************************************************
 * Copyright (c) 2024 THALES GLOBAL SERVICES.
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
package org.polarsys.capella.test.diagram.misc.ju.testcases;

import java.util.Arrays;
import java.util.List;

import org.eclipse.emf.ecore.EObject;
import org.eclipse.gef.EditPart;
import org.eclipse.gmf.runtime.diagram.ui.editparts.IGraphicalEditPart;
import org.eclipse.sirius.business.api.session.Session;
import org.eclipse.sirius.diagram.DDiagram;
import org.eclipse.sirius.diagram.DDiagramElement;
import org.eclipse.sirius.diagram.DEdge;
import org.eclipse.sirius.diagram.ui.internal.edit.parts.DEdgeBeginNameEditPart;
import org.eclipse.sirius.diagram.ui.internal.edit.parts.DEdgeEndNameEditPart;
import org.eclipse.sirius.diagram.ui.tools.api.graphical.edit.styles.StyleConfiguration;
import org.eclipse.sirius.diagram.ui.tools.internal.graphical.edit.styles.StyleConfigurationRegistry;
import org.polarsys.capella.core.data.cs.PhysicalLink;
import org.polarsys.capella.core.data.fa.FunctionalExchange;
import org.polarsys.capella.core.sirius.analysis.CapellaServices;
import org.polarsys.capella.core.sirius.analysis.DiagramServices;
import org.polarsys.capella.core.sirius.analysis.cache.DEdgeIconCache;
import org.polarsys.capella.test.diagram.common.ju.wrapper.utils.DiagramHelper;
import org.polarsys.capella.test.framework.api.BasicTestCase;
import org.polarsys.capella.test.framework.context.SessionContext;

/**
 * This tests ensures that the pie icons (displayed when FC or PP overlap) are displayed on diagram opening .
 *
 */
public class PieIconTest extends BasicTestCase {

  private static final String PROJECT_NAME = "testPie"; //$NON-NLS-1$

  protected Session session;
  protected SessionContext context;

  public static String OAIB = "_uMIigLCvEe6Fv9U2y24pjw";
  public static String OAB = "_97h-ILCwEe6Fv9U2y24pjw";
  public static String SAB = "_CZ_7QLC2Ee6Fv9U2y24pjw";
  public static String SDFB = "_MWE0wLC0Ee6Fv9U2y24pjw";
  public static String LAB = "_1jTKwK-mEe6L-OKa_6GDIw";
  public static String LDFB = "_c61CELEYEe6Fv9U2y24pjw";
  public static String PAB = "_S542sLEaEe6Fv9U2y24pjw";
  public static String PDFB = "_0DlQ4LEZEe6Fv9U2y24pjw";

  @Override
  public List<String> getRequiredTestModels() {
    return Arrays.asList(PROJECT_NAME);
  }

  @Override
  protected void setUp() throws Exception {
    super.setUp();
    this.session = getSession(PROJECT_NAME);
    this.context = new SessionContext(session);

  }

  @Override
  public void test() throws Exception {
    testOA();
    testSA();
    testLA();
    testPA();
  }

  public void testOA() {
    testPieIconsOnFunctionalExchanges(session, OAB, false);
    testPieIconsOnFunctionalExchanges(session, OAIB, false);
  }
  
  public void testSA() {
    testPieIconsOnFunctionalExchanges(session, SAB, true);
    testPieIconsOnFunctionalExchanges(session, SDFB, false);
   }
  
  public void testLA() {
    testPieIconsOnFunctionalExchanges(session, LAB, true);
    testPieIconsOnFunctionalExchanges(session, LDFB, false);
   }
  
  public void testPA() {
    testPieIconsOnFunctionalExchanges(session, PAB, true);
    testPieIconsOnFunctionalExchanges(session, PDFB, false);
   }
  
  public void testPieIconsOnFunctionalExchanges(Session session, String diagramUID, boolean testPL) {
    DEdgeIconCache.getInstance().reset();

    DiagramHelper.setPrefereneRefreshOnOpening(false);
    DiagramHelper.setPreferenceAutoRefresh(false);

    DDiagram diagram = (DDiagram) DiagramHelper.getDRepresentationByUID(session, diagramUID);
    DiagramHelper.opendiagramEditor(session, diagram);
    assertFalse(hasPieIconOnFunctionalExchange(diagram));
    if (testPL)
      assertFalse(hasPieIconOnPhysicalLink(diagram));
    // Icon shall be null as semanticElements are still FunctionalExchange, and not FunctionalChain
    DiagramHelper.refreshDiagram(diagram);

    // Icon shall not be null after refresh
    assertTrue(hasPieIconOnFunctionalExchange(diagram));
    if (testPL)
      assertTrue(hasPieIconOnPhysicalLink(diagram));
    DiagramHelper.closeEditor(session, diagram);

    DiagramHelper.setPrefereneRefreshOnOpening(true);
    DiagramHelper.setPreferenceAutoRefresh(true);

    // Icon shall not be true on opening with refresh
    diagram = (DDiagram) DiagramHelper.getDRepresentationByUID(session, diagramUID);
    DiagramHelper.opendiagramEditor(session, diagram);
    assertTrue(hasPieIconOnFunctionalExchange(diagram));
    if (testPL)
      assertTrue(hasPieIconOnPhysicalLink(diagram));
    DiagramHelper.closeEditor(session, diagram);
  }
  
  public void testPieIconsOnPhysicalLinks(Session session, String diagramUID){
    DEdgeIconCache.getInstance().reset();

    DiagramHelper.setPrefereneRefreshOnOpening(false);
    DiagramHelper.setPreferenceAutoRefresh(false);

    DDiagram diagram = (DDiagram) DiagramHelper.getDRepresentationByUID(session, diagramUID);
    DiagramHelper.opendiagramEditor(session, diagram);
    assertFalse(hasPieIconOnPhysicalLink(diagram));
    // Icon shall be null as semanticElements are still FunctionalExchange, and not FunctionalChain
    DiagramHelper.refreshDiagram(diagram);

    // Icon shall not be null after refresh
    assertTrue(hasPieIconOnPhysicalLink(diagram));
    DiagramHelper.closeEditor(session, diagram);

    DiagramHelper.setPrefereneRefreshOnOpening(true);
    DiagramHelper.setPreferenceAutoRefresh(true);

    // Icon shall not be true on opening with refresh
    diagram = (DDiagram) DiagramHelper.getDRepresentationByUID(session, diagramUID);
    DiagramHelper.opendiagramEditor(session, diagram);
    assertTrue(hasPieIconOnPhysicalLink(diagram));
    DiagramHelper.closeEditor(session, diagram);
  }

  private boolean hasPieIconOnFunctionalExchange(DDiagram diagram) {
    for (DDiagramElement element : diagram.getDiagramElements()) {
        DEdge edge = (DEdge) element;
        EObject target = edge.getTarget();
        if (target instanceof FunctionalExchange) {
          FunctionalExchange fe = (FunctionalExchange) target;
          EditPart edgeEditPart = DiagramServices.getDiagramServices().getEditPart(edge);
          if (CapellaServices.getService().getDisplayedInvolvingFunctionalChains(edge).size() > 1) {
            if (edgeEditPart != null) {
              EditPart nameEditPart = edgeEditPart.getChildren().stream()
                  .filter(child -> child instanceof DEdgeBeginNameEditPart || child instanceof DEdgeEndNameEditPart)
                  .findAny().get();
              if (nameEditPart != null) {
                StyleConfiguration c = StyleConfigurationRegistry.getInstance()
                    .getStyleConfiguration(edge.getDiagramElementMapping(), edge.getStyle());

                return c.getLabelIcon(element, (IGraphicalEditPart) nameEditPart) != null;
              }

              return nameEditPart != null;
            }
        }
      }
    }
    return false;
  }
  
  private boolean hasPieIconOnPhysicalLink(DDiagram diagram) {
    for (DDiagramElement element : diagram.getDiagramElements()) {
        DEdge edge = (DEdge) element;
        EObject target = edge.getTarget();
        if (target instanceof PhysicalLink) {
          PhysicalLink fe = (PhysicalLink) target;
          EditPart edgeEditPart = DiagramServices.getDiagramServices().getEditPart(edge);
          if (CapellaServices.getService().getDisplayedInvolvingPhysicalPaths(edge).size() > 1) {
            if (edgeEditPart != null) {
              EditPart nameEditPart = edgeEditPart.getChildren().stream()
                  .filter(child -> child instanceof DEdgeBeginNameEditPart || child instanceof DEdgeEndNameEditPart)
                  .findAny().get();
              if (nameEditPart != null) {
                StyleConfiguration c = StyleConfigurationRegistry.getInstance()
                    .getStyleConfiguration(edge.getDiagramElementMapping(), edge.getStyle());

                return c.getLabelIcon(element, (IGraphicalEditPart) nameEditPart) != null;
              }

              return nameEditPart != null;
            }
        }
      }
    }
    return false;
  }
}