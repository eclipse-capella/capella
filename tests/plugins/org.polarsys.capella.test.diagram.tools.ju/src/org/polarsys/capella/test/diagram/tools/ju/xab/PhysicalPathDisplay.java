/*******************************************************************************
 * Copyright (c) 2021 THALES GLOBAL SERVICES.
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

import java.util.Collection;
import java.util.Optional;

import org.eclipse.sirius.business.api.session.Session;
import org.eclipse.sirius.diagram.DDiagramElement;
import org.eclipse.sirius.diagram.DEdge;
import org.eclipse.sirius.diagram.DNode;
import org.eclipse.sirius.diagram.Square;
import org.eclipse.sirius.diagram.description.DiagramElementMapping;
import org.polarsys.capella.core.data.capellacore.Involvement;
import org.polarsys.capella.core.data.cs.PhysicalLink;
import org.polarsys.capella.core.data.cs.PhysicalPath;
import org.polarsys.capella.core.data.pa.PhysicalComponent;
import org.polarsys.capella.core.model.helpers.BlockArchitectureExt;
import org.polarsys.capella.core.sirius.analysis.DDiagramContents;
import org.polarsys.capella.core.sirius.analysis.DiagramServices;
import org.polarsys.capella.core.sirius.analysis.constants.MappingConstantsHelper;
import org.polarsys.capella.test.diagram.common.ju.api.AbstractDiagramTestCase;
import org.polarsys.capella.test.diagram.common.ju.context.XABDiagram;
import org.polarsys.capella.test.framework.context.SessionContext;

public class PhysicalPathDisplay extends AbstractDiagramTestCase {
  protected Session session;
  protected SessionContext context;
  public static final String PA__PAB_DIAGRAM = "[PAB] Physical System";
  public static final String PHYSICAL_SYSTEM = "b501d39f-3d93-4b07-9c6e-12e4c9c425c4"; //$NON-NLS-1$
  public static final String PA__PAB_NOODLE_BAG_DIAGRAM = "[PAB] Noodle Bag";
  public static final String NOODLE_BAG_PHYSICAL_PATH = "9851607d-ac87-4c9c-8a0c-b20f5b34dac2"; //$NON-NLS-1$
  
  protected void initTest() {
    session = getSession(getRequiredTestModel());
    context = new SessionContext(session);
  }

  @Override
  public void test() throws Exception {
    initTest();
    testCompositePhysicalPaths();
    testNoodleBagPhysicalPath();
  }

  /**
   * This test is based on pre-created Compound Physical Paths representing different composition configurations in the
   * PhysicalPathDisplay model. Each time a path is displayed, we test that the physical link (connecting 2 composed
   * paths) is colored and internal links are created.
   */
  public void testCompositePhysicalPaths() {
    XABDiagram diagram = XABDiagram.openDiagram(context, PA__PAB_DIAGRAM, BlockArchitectureExt.Type.PA);
    PhysicalComponent physicalSystem = (PhysicalComponent) context.getSemanticElement(PHYSICAL_SYSTEM);
    for (PhysicalPath path : physicalSystem.getOwnedPhysicalPath()) {
      if (path.getName().startsWith("Compound")) {
        diagram.insertPhysicalPath(path.getId());
        diagram.refreshDiagram();

        Optional<Involvement> plInvOpt = path.getInvolvedInvolvements().stream()
            .filter(i -> i.getInvolved() instanceof PhysicalLink).findFirst();
        assertTrue(plInvOpt.isPresent());
        PhysicalLink pl = (PhysicalLink) plInvOpt.get().getInvolved();
        DDiagramContents ctx = new DDiagramContents(diagram.getDiagram());
        DiagramElementMapping physicalLinkEdgeMapping = ctx
            .getMapping(MappingConstantsHelper.getMappingABPhysicalLink(ctx.getDDiagram()));
        DiagramElementMapping internalPhysicalLinkEdgeMapping = ctx
            .getMapping(MappingConstantsHelper.getMappingPhysicalPathInternLink(ctx.getDDiagram()));
        DiagramElementMapping pathMapping = ctx
            .getMapping(MappingConstantsHelper.getMappingPhysicalPath(ctx.getDDiagram()));
        Collection<DDiagramElement> plEdges = DiagramServices.getDiagramServices().getDiagramElements(diagram.getDiagram(),
            physicalLinkEdgeMapping, pl);
        Collection<DDiagramElement> internalPLEdges = DiagramServices.getDiagramServices().getDiagramElements(diagram.getDiagram(),
            internalPhysicalLinkEdgeMapping, path);
        DNode pathView = (DNode) DiagramServices.getDiagramServices().getDiagramElement(diagram.getDiagram(),
            pathMapping, path);

        for (DDiagramElement plEdge : plEdges) {
          assertTrue(plEdge instanceof DEdge);
          assertTrue(
              "The Physical Link " + pl.getName() + " is not colored when the Physical Path " + path.getName()
                  + " is displayed.",
              ((DEdge) plEdge).getOwnedStyle().getStrokeColor().equals(((Square) pathView.getOwnedStyle()).getColor()));
        }
        assertTrue("There should be 2 interal links for " + path.getName() + ".",
            internalPLEdges.stream().filter(e -> e.getTarget().equals(path)).count() == 2);

        diagram.removePhysicalPath(path.getId());
        diagram.refreshDiagram();
      }
    }
  }
  
  /**
   * Test that internal links between every physical ports belonging to a displayed physical path are created. This kind
   * of network of internal links looks like a "noodle bag".
   */
  public void testNoodleBagPhysicalPath() {
    XABDiagram diagram = XABDiagram.openDiagram(context, PA__PAB_NOODLE_BAG_DIAGRAM, BlockArchitectureExt.Type.PA);
    PhysicalPath physicalPath = (PhysicalPath) context.getSemanticElement(NOODLE_BAG_PHYSICAL_PATH);
    diagram.insertPhysicalPath(physicalPath.getId());
    diagram.refreshDiagram();

    DDiagramContents ctx = new DDiagramContents(diagram.getDiagram());
    DiagramElementMapping internalPhysicalLinkEdgeMapping = ctx
        .getMapping(MappingConstantsHelper.getMappingPhysicalPathInternLink(ctx.getDDiagram()));
    Collection<DDiagramElement> internalPLEdges = DiagramServices.getDiagramServices()
        .getDiagramElements(diagram.getDiagram(), internalPhysicalLinkEdgeMapping, physicalPath);
    assertTrue("There should be 6 interal links for " + physicalPath.getName() + ".",
        internalPLEdges.stream().filter(e -> e.getTarget().equals(physicalPath)).count() == 6);
  }

  @Override
  public String getRequiredTestModel() {
    return PhysicalPathDisplay.class.getSimpleName();
  }
}
