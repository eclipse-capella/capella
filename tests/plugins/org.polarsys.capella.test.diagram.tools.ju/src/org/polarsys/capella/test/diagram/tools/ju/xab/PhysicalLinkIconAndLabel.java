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

import java.util.Arrays;

import org.eclipse.gef.EditPart;
import org.eclipse.sirius.business.api.session.Session;
import org.eclipse.sirius.diagram.DDiagramElement;
import org.eclipse.sirius.diagram.DEdge;
import org.eclipse.sirius.diagram.ui.internal.edit.parts.DEdgeBeginNameEditPart;
import org.eclipse.sirius.diagram.ui.internal.edit.parts.DEdgeEndNameEditPart;
import org.eclipse.sirius.viewpoint.RGBValues;
import org.eclipse.swt.graphics.Image;
import org.eclipse.swt.graphics.ImageData;
import org.polarsys.capella.core.data.cs.PhysicalPath;
import org.polarsys.capella.core.sirius.analysis.DiagramServices;
import org.polarsys.capella.core.sirius.analysis.cache.PieIconCache;
import org.polarsys.capella.test.diagram.common.ju.context.PABDiagram;
import org.polarsys.capella.test.diagram.common.ju.context.XABDiagram;
import org.polarsys.capella.test.framework.context.SessionContext;

@SuppressWarnings("restriction")
public class PhysicalLinkIconAndLabel extends XABDiagramsProject {
  @Override
  public void test() throws Exception {
    Session session = getSession(getRequiredTestModel());
    SessionContext context = new SessionContext(session);

    PABDiagram pab = PABDiagram.openDiagram(context, PA__PAB_DIAGRAM);
    pab.clearDiagram();
    
    pab.createNodeComponent("Node 1", pab.getDiagramId());
    pab.createNodeComponent("Node 2", pab.getDiagramId());
    pab.createPhysicalLink("Node 1", "Node 2", "Physical Link 1");
    PhysicalPath path1 = pab.createPhysicalPath("Path 1", "Physical Link 1");
    PhysicalPath path2 = pab.createPhysicalPath("Path 2", "Physical Link 1");
    DDiagramElement plView = (DDiagramElement) pab.getView("Physical Link 1");
    assertTrue(plView instanceof DEdge);
    
    checkIcons(pab, plView);
    
    assertTrue("Wrong label for overlapped Physical Link", ((DEdge) plView).getBeginLabel().equals(
        DiagramServices.getDiagramServices().getOverlappedLabels(Arrays.asList(path1.getName(), path2.getName())))
        && ((DEdge) plView).getEndLabel().equals(
            DiagramServices.getDiagramServices().getOverlappedLabels(Arrays.asList(path1.getName(), path2.getName()))));
  }

  @SuppressWarnings({ "unchecked" })
  private void checkIcons(XABDiagram diagram, DDiagramElement view) {
    Image expectedImage = PieIconCache.getInstance().getIcon(Arrays.asList(RGBValues.create(165,42,42), RGBValues.create(24,114,248)));
    assertTrue("Problem while loading reference image", expectedImage != null);
    EditPart edgeEditPart = DiagramServices.getDiagramServices().getEditPart(view);
    assertTrue(edgeEditPart != null);
    DEdgeBeginNameEditPart beginNameEditPart = (DEdgeBeginNameEditPart) edgeEditPart.getChildren().stream()
        .filter(child -> child instanceof DEdgeBeginNameEditPart).findFirst().get();
    Image beginLabelIcon = beginNameEditPart.getLabelIcon();
    assertTrue(hasSameContent(expectedImage, beginLabelIcon));
    DEdgeEndNameEditPart endNameEditPart = (DEdgeEndNameEditPart) edgeEditPart.getChildren().stream()
        .filter(child -> child instanceof DEdgeEndNameEditPart).findFirst().get();
    Image endLabelIcon = endNameEditPart.getLabelIcon();
    assertTrue(hasSameContent(expectedImage, endLabelIcon));
  }
  
  private boolean hasSameContent(Image image1, Image image2) {
    ImageData imageData1 = image1.getImageData();
    ImageData imageData2 = image2.getImageData();
    for (int i = 0; i < (16 * 16); i++) {
      if (imageData1.data[i] != imageData2.data[i]) {
        return false;
      }
    }
    return true;
  }
}
