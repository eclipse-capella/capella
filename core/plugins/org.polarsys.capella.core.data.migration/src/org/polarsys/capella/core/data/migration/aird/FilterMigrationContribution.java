/*******************************************************************************
 * Copyright (c) 2019 THALES GLOBAL SERVICES.
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
package org.polarsys.capella.core.data.migration.aird;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.InternalEObject;
import org.eclipse.sirius.diagram.AppliedCompositeFilters;
import org.eclipse.sirius.diagram.DSemanticDiagram;
import org.eclipse.sirius.diagram.description.DiagramDescription;
import org.eclipse.sirius.diagram.description.filter.FilterDescription;
import org.eclipse.sirius.viewpoint.DRepresentationDescriptor;
import org.eclipse.sirius.viewpoint.description.DAnnotation;
import org.eclipse.sirius.viewpoint.description.RepresentationDescription;
import org.polarsys.capella.core.data.migration.context.MigrationContext;
import org.polarsys.capella.core.data.migration.contribution.AbstractMigrationContribution;
import org.polarsys.capella.core.diagram.helpers.DAnnotationHelper;
import org.polarsys.capella.core.diagram.helpers.IRepresentationAnnotationConstants;

public class FilterMigrationContribution extends AbstractMigrationContribution {

  private static final String MIGRATED_FITLER_EXT = ".filter";
  private static final String FRAGMENT_SEPARATOR = "\\@";
  private static final String FILTER_SEPARATOR = "\\'";
  private static final String FRAGMENT_FILTER_KEY = "filters";

  private static final String PLUGIN_TYPE = "plugin";
  private static final String VALID_PLUGIN = "org.polarsys.capella.core.sirius.analysis";
  private static final String DESCRIPTION_TYPE = "description";

  private static Map<String, String> filterNameExceptions;
  
  private Map<DiagramDescription, Set<String>> validFilterNames;

  static {
    filterNameExceptions = new HashMap<>();
    filterNameExceptions.put("ShowEIExchangeContext", "show.ei.exchange.context.filter");
    filterNameExceptions.put("CEParam", "show.ce.param.filter");
    filterNameExceptions.put("CEEIParam", "show.ce.ei.param.filter");
    filterNameExceptions.put("ShowFEExchangeContex", "show.fe.exchange.context.filter");
    filterNameExceptions.put("ShowCEExchangeContext", "show.ce.exchange.context.filter");
  }
  
  public FilterMigrationContribution() {
    validFilterNames = new HashMap<>();
  }

  @Override
  public void unaryMigrationExecute(EObject currentElement, MigrationContext context) {

    if (currentElement instanceof DSemanticDiagram) {

      DSemanticDiagram diagram = (DSemanticDiagram) currentElement;
      DiagramDescription diagramDescription = diagram.getDescription();
      List<FilterDescription> filterDescriptions = new ArrayList<>();
      filterDescriptions.addAll(diagram.getActivatedFilters());
      diagram.getOwnedDiagramElements().stream()
          .forEach(e -> e.getGraphicalFilters().stream().filter(AppliedCompositeFilters.class::isInstance)
              .map(AppliedCompositeFilters.class::cast)
              .forEach(f -> filterDescriptions.addAll(f.getCompositeFilterDescriptions())));
      for (FilterDescription filterDescription : filterDescriptions) {
        if (isInvalidFilter(filterDescription)) {
          InternalEObject internalObjectFilter = (InternalEObject) filterDescription;
          URI filterURI = internalObjectFilter.eProxyURI();

          if (shouldMigrateFilter(filterURI)) {
            String invalidFilterName = extractFilterName(filterURI);
            String validFilterNameCandidate = getValidFilterNameCandidate(invalidFilterName);

            if (isValidFilterName(diagramDescription, validFilterNameCandidate)) {
              URI validFilterURI = getValidFilterURI(filterURI, invalidFilterName, validFilterNameCandidate);
              internalObjectFilter.eSetProxyURI(validFilterURI);
            }

            else {
              System.err.println("[Migration] Invalid filter name: " + validFilterNameCandidate + " for "
                  + diagramDescription.getName() + " " + diagram.getUid() + "and " + invalidFilterName);
            }
          }
        }
      }

    } 
    else if (currentElement instanceof DRepresentationDescriptor) {
      DRepresentationDescriptor descriptor = (DRepresentationDescriptor) currentElement;
      DAnnotation annotation = DAnnotationHelper.getAnnotation(IRepresentationAnnotationConstants.DesactivatedFilters,
          descriptor, false);
      if (annotation != null) {
        Map<String, String> oldFilter2NewFilter = new HashMap<>();
        annotation.getDetails().stream().forEach(a -> {
          String oldFilterName = a.getKey();
          String validFilterNameCandidate = getValidFilterNameCandidate(oldFilterName);
          RepresentationDescription description = descriptor.getDescription();
          if (description instanceof DiagramDescription
              && !isValidFilterName((DiagramDescription) description, oldFilterName)
              && isValidFilterName((DiagramDescription) description, validFilterNameCandidate)) {
            oldFilter2NewFilter.put(oldFilterName, validFilterNameCandidate);
          }
        });
        oldFilter2NewFilter.keySet().stream().forEach(k -> {
          String value = annotation.getDetails().get(k); 
          annotation.getDetails().removeKey(k);
          annotation.getDetails().put(oldFilter2NewFilter.get(k), value);
        });
      }
    }

  }

  protected boolean isInvalidFilter(FilterDescription filterDescription) {
    return filterDescription.eIsProxy() && filterDescription.getName().isEmpty();
  }

  /**
   * Returns true if the filterName is declared by the current diagram description.
   */
  private boolean isValidFilterName(DiagramDescription diagramDescription, String filterName) {

    Set<String> validDiagramFilterNames = validFilterNames.computeIfAbsent(diagramDescription,
        desc -> desc.getFilters().stream().map(this::getExistingFilterName).collect(Collectors.toSet()));

    return validDiagramFilterNames.contains(filterName);
  }

  /**
   * Returns the filter name from the filter description. If the filter description is a proxy, then the filter name is
   * extracted from the proxy URI.
   */
  private String getExistingFilterName(FilterDescription filter) {
    if (filter instanceof InternalEObject && filter.eIsProxy()) {
      InternalEObject internalObjectFilter = (InternalEObject) filter;

      URI invalidFilterURI = internalObjectFilter.eProxyURI();
      return extractFilterName(invalidFilterURI);
    }

    return filter.getName();
  }

  /**
   * Extracts a filter name from a proxy URI.
   * 
   */
  private String extractFilterName(URI filterURI) {
    String fragment = filterURI.fragment();

    String[] tokens = fragment.split(FRAGMENT_SEPARATOR);

    if (tokens.length > 0) {
      String filterFragment = tokens[tokens.length - 1];

      if (filterFragment.startsWith(FRAGMENT_FILTER_KEY)) {
        tokens = filterFragment.split(FILTER_SEPARATOR);

        if (tokens.length == 3) {
          return tokens[1].replaceAll("%20", " ");
        }
      }
    }
    return null;
  }

  /**
   * Transforms a invalid filter URI into a valid one.
   * 
   */
  private URI getValidFilterURI(URI invalidFilterURI, String invalidFilterName, String validFilterName) {

    String uriValue = invalidFilterURI.toPlatformString(true);
    String fragment = invalidFilterURI.fragment();
    String invalidEcondedFilterName = invalidFilterName.replaceAll(" ", "%20");

    fragment = fragment.replace(invalidEcondedFilterName, validFilterName);

    return URI.createPlatformPluginURI(uriValue, true).appendFragment(fragment);
  }

  private boolean shouldMigrateFilter(URI filterURI) {
    String[] filterSegments = filterURI.segments();

    return filterSegments.length == 4 && PLUGIN_TYPE.equals(filterSegments[0]) && VALID_PLUGIN.equals(filterSegments[1])
        && DESCRIPTION_TYPE.equals(filterSegments[2]);
  }

  /*
   * Do not judge me :)
   */
  public static String getValidFilterNameCandidate(String invalidFilterName) {

    String filterNameException = filterNameExceptions.get(invalidFilterName);

    if (filterNameException != null) {
      return filterNameException;
    }

    String validName = invalidFilterName.replaceAll("/", " ");
    validName = validName.replaceAll("\\(", " ");
    validName = validName.replaceAll("\\)", " ");
    validName = validName.replaceAll("\\[", " ");
    validName = validName.replaceAll("\\]", " ");
    validName = validName.replaceAll("&", " and ");
    validName = validName.replaceAll("PCs", "pcs");

    char[] validNameCharArray = validName.toCharArray();

    StringBuilder builder = new StringBuilder();

    for (int i = 0; i < validNameCharArray.length - 1; i++) {
      char currentLetter = validNameCharArray[i];
      char nextLetter = validNameCharArray[i + 1];

      if (Character.isUpperCase(currentLetter) && Character.isLowerCase(nextLetter)) {
        builder.append(" ");
      }
      builder.append(currentLetter);
    }

    builder.append(validNameCharArray[validNameCharArray.length - 1]);

    String[] tokens = builder.toString().toLowerCase().split(" ");

    builder = new StringBuilder();

    for (String token : tokens) {
      if (token != null && !token.isEmpty() && !token.equals(" ")) {
        if (builder.length() != 0) {
          builder.append(".");
        }
        builder.append(token);
      }
    }

    return builder.toString() + MIGRATED_FITLER_EXT;
  }

}
