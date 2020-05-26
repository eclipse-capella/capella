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
package org.polarsys.capella.test.odesign.helper;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.eclipse.sirius.business.api.componentization.ViewpointRegistry;
import org.eclipse.sirius.diagram.business.api.componentization.DiagramComponentizationManager;
import org.eclipse.sirius.diagram.business.internal.metamodel.helper.ContentHelper;
import org.eclipse.sirius.diagram.description.ContainerMapping;
import org.eclipse.sirius.diagram.description.DiagramDescription;
import org.eclipse.sirius.diagram.description.DiagramElementMapping;
import org.eclipse.sirius.diagram.description.EdgeMapping;
import org.eclipse.sirius.diagram.description.filter.CompositeFilterDescription;
import org.eclipse.sirius.diagram.description.filter.FilterDescription;
import org.eclipse.sirius.diagram.description.filter.MappingFilter;
import org.eclipse.sirius.viewpoint.description.Viewpoint;
import org.eclipse.sirius.viewpoint.description.tool.AbstractToolDescription;

public class OdesignTestHelper {
  public static Viewpoint getViewpointByName(String vpName) {
    Optional<Viewpoint> vpOpt = ViewpointRegistry.getInstance().getViewpoints().stream()
        .filter(vp -> vp.getName().equals(vpName)).findFirst();
    if (vpOpt.isPresent()) {
      return vpOpt.get();
    }
    return null;
  }
  
  public static DiagramDescription getDiagramDescriptionByName(Viewpoint vp, String desName) {
    List<DiagramDescription> allDiagramDescriptions = vp.getOwnedRepresentations().stream()
        .filter(DiagramDescription.class::isInstance).map(DiagramDescription.class::cast).collect(Collectors.toList());
    Optional<DiagramDescription> diagDesOpt = allDiagramDescriptions.stream()
        .filter(des -> des.getName().equals(desName)).findFirst();
    if (diagDesOpt.isPresent()) {
      return diagDesOpt.get();
    }
    return null;
  }
  
  public static FilterDescription getFilterDescriptionByName(DiagramDescription diagDes, String filterName) {
    Optional<FilterDescription> filterOpt = diagDes.getFilters().stream().filter(f -> f.getName().equals(filterName))
        .findFirst();
    if (filterOpt.isPresent()) {
      return filterOpt.get();
    }
    return null;
  }
  
  public static boolean hasMapping(DiagramDescription diaDes, DiagramElementMapping mapping) {
    if (mapping instanceof ContainerMapping) {
      return ContentHelper.getAllContainerMappings(diaDes, false).contains(mapping);
    } else if (mapping instanceof EdgeMapping) {
      return ContentHelper.getAllEdgeMappings(diaDes, false).contains(mapping);
    }
    return false;
  }
  
  
  /**
   * Check whether a diagram description has a filter on a given mapping
   * 
   * @param diaDes
   * @param filterName
   * @param mapping
   * @return
   */
  public static boolean hasFilterOnMapping(DiagramDescription diagDes, String filterName,
      DiagramElementMapping mapping) {
    FilterDescription filter = getFilterDescriptionByName(diagDes, filterName);
    if (filter instanceof CompositeFilterDescription) {
      return ((CompositeFilterDescription) filter).getFilters().stream().filter(MappingFilter.class::isInstance)
          .map(MappingFilter.class::cast).anyMatch(f -> f.getMappings().contains(mapping));
    }
    return false;
  }
  
  public static boolean hasTool(DiagramDescription diaDes, String toolName) {
    final List<AbstractToolDescription> tools = new DiagramComponentizationManager()
        .getAllTools(ViewpointRegistry.getInstance().getViewpoints(), diaDes);
    return tools.stream().anyMatch(t -> t.getName() != null && t.getName().equals(toolName));
  }
}
