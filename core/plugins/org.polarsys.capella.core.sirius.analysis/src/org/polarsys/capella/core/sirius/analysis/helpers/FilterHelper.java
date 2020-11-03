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
package org.polarsys.capella.core.sirius.analysis.helpers;

import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

import org.eclipse.sirius.diagram.DDiagram;
import org.eclipse.sirius.viewpoint.DRepresentationDescriptor;
import org.eclipse.sirius.viewpoint.description.DAnnotation;
import org.eclipse.sirius.viewpoint.description.IdentifiedElement;
import org.polarsys.capella.core.diagram.helpers.DAnnotationHelper;
import org.polarsys.capella.core.diagram.helpers.IRepresentationAnnotationConstants;

/**
 * On some diagram, it is useful to know if a filter has been desactivated at least one time.
 *
 * Edges related to these filters are not displayed/present on diagram as long as user hasn't unchecked the filter. (see
 * related preconditionExpressions)
 */
public class FilterHelper {

  /**
   * Returns whether the given filter has been desactivated once by the user. This method works only for filters that
   * were monitored by the monitorDesactivation method.
   */
  public static boolean isDesactivatedOnce(String filterId, DRepresentationDescriptor descriptor) {
    if (descriptor != null) {
      DAnnotation annotation = DAnnotationHelper.getAnnotation(IRepresentationAnnotationConstants.DesactivatedFilters,
          descriptor, false);
      if (annotation != null) {
        return annotation.getDetails().containsKey(filterId);
      }
    }
    return false;
  }

  /**
   * This method will monitor the desactivation of the given filters. When a filter is desactivated by the user, we will
   * keep an annotation on the descriptor
   */
  public static void monitorDesactivation(List<String> filters, DRepresentationDescriptor descriptor) {
    if (descriptor != null) {
      DDiagram diagram = ((DDiagram) descriptor.getRepresentation());
      if (diagram != null) {
        Collection<String> definedFilters = diagram.getDescription().getFilters().stream()
            .map(IdentifiedElement::getName).collect(Collectors.toSet());
        Collection<String> activatedFilters = diagram.getActivatedFilters().stream().map(IdentifiedElement::getName)
            .collect(Collectors.toSet());
        Collection<String> monitoredDesactivatedFilters = filters.stream()
            .filter(f -> definedFilters.contains(f) && !activatedFilters.contains(f)).collect(Collectors.toList());

        if (!monitoredDesactivatedFilters.isEmpty()) {
          DAnnotation annotation = DAnnotationHelper
              .getAnnotation(IRepresentationAnnotationConstants.DesactivatedFilters, descriptor, true);
          for (String filterId : monitoredDesactivatedFilters) {
            if (!annotation.getDetails().containsKey(filterId)) {
              annotation.getDetails().put(filterId, Boolean.TRUE.toString());
            }
          }
        }
      }
    }
  }

}
