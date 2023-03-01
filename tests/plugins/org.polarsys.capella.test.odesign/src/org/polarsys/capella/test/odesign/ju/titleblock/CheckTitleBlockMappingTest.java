/*******************************************************************************
 * Copyright (c) 2020, 2021 THALES GLOBAL SERVICES.
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
package org.polarsys.capella.test.odesign.ju.titleblock;

import java.util.List;
import java.util.stream.Collectors;

import org.eclipse.emf.ecore.util.EcoreUtil;
import org.eclipse.sirius.business.api.componentization.ViewpointRegistry;
import org.eclipse.sirius.diagram.description.AbstractNodeMapping;
import org.eclipse.sirius.diagram.description.DiagramDescription;
import org.eclipse.sirius.diagram.description.DiagramElementMapping;
import org.eclipse.sirius.diagram.description.EdgeMapping;
import org.eclipse.sirius.viewpoint.description.Viewpoint;
import org.polarsys.capella.core.sirius.analysis.DiagramServices;
import org.polarsys.capella.core.sirius.analysis.IDiagramNameConstants;
import org.polarsys.capella.core.sirius.analysis.IMappingNameConstants;
import org.polarsys.capella.core.sirius.analysis.IViewpointNameConstants;
import org.polarsys.capella.core.sirius.analysis.activator.SiriusViewActivator;
import org.polarsys.capella.test.odesign.helper.OdesignTestHelper;

public class CheckTitleBlockMappingTest extends CheckTitleBlockTest {
  @Override
  public void test() throws Exception {
    Viewpoint commonVP = OdesignTestHelper.getViewpointByName(IViewpointNameConstants.COMMON_VIEWPOINT_NAME);
    if (commonVP == null) {
      fail("Common viewpoint not found");
    }

    DiagramDescription classDiagramDescription = OdesignTestHelper.getDiagramDescriptionByName(commonVP,
        IDiagramNameConstants.CLASS_BLANK_DIAGRAM_NAME);
    if (classDiagramDescription == null) {
      fail("CBD diagram description not found");
    }

    AbstractNodeMapping titleBlockContainerMapping = DiagramServices.getDiagramServices()
        .getAbstractNodeMapping(classDiagramDescription, IMappingNameConstants.DT_TITLE_BLOCK_CONTAINER);
    if (titleBlockContainerMapping == null) {
      fail("Title block container mapping not found");
    }

    assertAllDiagramContainMapping(titleBlockContainerMapping);
    
    EdgeMapping titleBlockEdgeMapping = DiagramServices.getDiagramServices().getEdgeMapping(classDiagramDescription,
        IMappingNameConstants.DT_TITLE_BLOCK_EDGE);
    if (titleBlockEdgeMapping == null) {
      fail("Title block edge mapping not found");
    }

    assertAllDiagramContainMapping(titleBlockEdgeMapping);
  }
  
  /**
   * Assert that all diagram descriptions must contain mapping
   * @param mapping
   */
  private void assertAllDiagramContainMapping(DiagramElementMapping mapping) {
    List<DiagramDescription> diagramDescriptionsWithoutMapping = getDiagramDescriptionsWithoutMapping(
        mapping);
    assertTrue("The following diagrams do not have Title block " +  mapping.getName() + " mapping: "
        + diagramDescriptionsWithoutMapping.stream().map(m -> m.getName())
            .collect(Collectors.joining(", ")),
            diagramDescriptionsWithoutMapping.isEmpty());
  }

  private List<DiagramDescription> getDiagramDescriptionsWithoutMapping(DiagramElementMapping mapping) {
    return ViewpointRegistry.getInstance().getViewpoints().stream()
        .filter(vp -> EcoreUtil.getURI(vp).segment(1) == SiriusViewActivator.ID)
        .flatMap(vp -> vp.getOwnedRepresentations().stream())
        .filter(DiagramDescription.class::isInstance).map(DiagramDescription.class::cast)
        .filter(des -> !isDiagramDescriptionIgnored(des))
        .filter(des -> !OdesignTestHelper.hasMapping(des, mapping)).collect(Collectors.toList());
  }
}
