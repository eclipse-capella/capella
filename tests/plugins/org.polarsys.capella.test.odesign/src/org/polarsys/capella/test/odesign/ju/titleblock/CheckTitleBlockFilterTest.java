/*******************************************************************************
 * Copyright (c) 2020 THALES GLOBAL SERVICES.
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
import org.eclipse.sirius.viewpoint.description.Viewpoint;
import org.polarsys.capella.core.sirius.analysis.DiagramServices;
import org.polarsys.capella.core.sirius.analysis.IDiagramNameConstants;
import org.polarsys.capella.core.sirius.analysis.IMappingNameConstants;
import org.polarsys.capella.core.sirius.analysis.IViewpointNameConstants;
import org.polarsys.capella.core.sirius.analysis.activator.SiriusViewActivator;
import org.polarsys.capella.core.sirius.analysis.constants.IFilterNameConstants;
import org.polarsys.capella.test.odesign.helper.OdesignTestHelper;

public class CheckTitleBlockFilterTest extends CheckTitleBlockTest {
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
    
    assertAllDiagramContainFilterOnMapping(IFilterNameConstants.FILTER_COMMON_HIDE_DIAGRAM_TITLE_BLOCKS, titleBlockContainerMapping);
    assertAllDiagramContainFilterOnMapping(IFilterNameConstants.FILTER_COMMON_HIDE_ELEMENT_TITLE_BLOCKS, titleBlockContainerMapping);
  }
  
  /**
   * Assert that all diagram descriptions must contain a filter for mapping
   */
  private void assertAllDiagramContainFilterOnMapping(String filterName, DiagramElementMapping mapping) {
    List<DiagramDescription> diagramDescriptionsWithoutFilterOnMapping = getDiagramDescriptionsWithoutFilterOnMapping(
        filterName, mapping);
    assertTrue("The following diagrams do not have Title block " + filterName + " filter: "
        + diagramDescriptionsWithoutFilterOnMapping.stream().map(m -> m.getName()).collect(Collectors.joining(", ")),
        diagramDescriptionsWithoutFilterOnMapping.isEmpty());
  }

  private List<DiagramDescription> getDiagramDescriptionsWithoutFilterOnMapping(String filterName,
      DiagramElementMapping mapping) {
    return ViewpointRegistry.getInstance().getViewpoints().stream()
        .filter(vp -> EcoreUtil.getURI(vp).segment(1) == SiriusViewActivator.ID)
        .flatMap(vp -> vp.getOwnedRepresentations().stream())
        .filter(DiagramDescription.class::isInstance).map(DiagramDescription.class::cast)
        .filter(des -> !isDiagramDescriptionIgnored(des))
        .filter(des -> !OdesignTestHelper.hasFilterOnMapping(des, filterName, mapping)).collect(Collectors.toList());
  }
}
