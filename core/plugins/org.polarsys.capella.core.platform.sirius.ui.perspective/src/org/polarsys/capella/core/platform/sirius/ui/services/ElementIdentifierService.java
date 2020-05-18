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
package org.polarsys.capella.core.platform.sirius.ui.services;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

import org.eclipse.emf.ecore.EObject;
import org.eclipse.sirius.diagram.description.DiagramDescription;
import org.eclipse.sirius.viewpoint.description.IdentifiedElement;
import org.eclipse.sirius.viewpoint.description.RepresentationDescription;
import org.eclipse.sirius.viewpoint.description.Viewpoint;

public class ElementIdentifierService implements IElementIdentifierService {

  private static final String SEPARATOR = ".";

  private Map<String, String> viewpointShortNames;

  public ElementIdentifierService() {
    viewpointShortNames = new HashMap<>();

    viewpointShortNames.put("Common", "common");
    viewpointShortNames.put("Operational Analysis", "oa");
    viewpointShortNames.put("System Analysis", "sa");
    viewpointShortNames.put("Logical Architecture", "la");
    viewpointShortNames.put("Physical Architecture", "pa");
    viewpointShortNames.put("EPBS architecture", "epbs");
  }

  @Override
  public String getIdentifier(DiagramDescription diagram, IdentifiedElement element) {
    Objects.requireNonNull(diagram);
    Objects.requireNonNull(element);

    EObject diagramContainer = diagram.eContainer();

    if (diagramContainer instanceof Viewpoint) {
      Viewpoint viewpoint = (Viewpoint) diagramContainer;
      String id = getViewpointShortName(viewpoint);
      id += SEPARATOR + getDiagramShortName(diagram);
      id += SEPARATOR + element.getName();
      return id;
    }

    return null;
  }

  private String getViewpointShortName(Viewpoint viewpoint) {
    return viewpointShortNames.getOrDefault(viewpoint.getName(), viewpoint.getName());
  }

  private String getDiagramShortName(RepresentationDescription representationDescription) {
    String titleExpression = representationDescription.getTitleExpression();
    if (titleExpression != null) {
      titleExpression = titleExpression.replace("&", "n");
      String[] tokens = titleExpression.split("(\\[)|(\\])");
      if (tokens.length == 3 && !tokens[1].contains("self")) {
        return tokens[1].toLowerCase();
      }
    }

    String[] tokens = representationDescription.getName().split(" ");
    StringBuilder result = new StringBuilder();
    for (String token : tokens) {
      char letter = Character.toLowerCase(token.charAt(0));
      result.append(letter);
    }
    return result.toString();
  }

  @Override
  public String getIdentifier(RepresentationDescription representationDescription) {
    if (representationDescription.eContainer() instanceof Viewpoint) {
      String id = getViewpointShortName((Viewpoint) representationDescription.eContainer());
      if (representationDescription != null) {
        id += SEPARATOR + getDiagramShortName(representationDescription);
      }
      return id;
    }
    return null;
  }

  @Override
  public String getIdentifier(Viewpoint viewpoint) {
    return getViewpointShortName(viewpoint);
  }

}
