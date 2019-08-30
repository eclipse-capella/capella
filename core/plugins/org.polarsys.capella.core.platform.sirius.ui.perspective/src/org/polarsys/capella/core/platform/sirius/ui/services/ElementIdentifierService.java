/*******************************************************************************
 * Copyright (c) 2019 THALES GLOBAL SERVICES.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *  
 * Contributors:
 *    Thales - initial API and implementation
 *******************************************************************************/
package org.polarsys.capella.core.platform.sirius.ui.services;

import java.util.HashMap;
import java.util.Map;

import org.eclipse.emf.ecore.EObject;
import org.eclipse.sirius.diagram.description.DiagramDescription;
import org.eclipse.sirius.viewpoint.description.IdentifiedElement;
import org.eclipse.sirius.viewpoint.description.Viewpoint;

public class ElementIdentifierService implements IElementIdentifierService {

  private static final String SEPARATOR = "/";

  private Map<String, String> viewpointShortNames;

  public ElementIdentifierService() {
    viewpointShortNames = new HashMap<>();

    viewpointShortNames.put("Common", "co");
    viewpointShortNames.put("Operational Analysis", "oa");
    viewpointShortNames.put("Physical Architecture", "pa");
    viewpointShortNames.put("EPBS architecture", "epbs");
    viewpointShortNames.put("Logical Architecture", "la");
    viewpointShortNames.put("System Analysis", "sa");
  }

  @Override
  public String getIdentifier(DiagramDescription diagram, IdentifiedElement element) {
    EObject diagramContainer = diagram.eContainer();

    if (diagramContainer instanceof Viewpoint) {
      Viewpoint viewpoint = (Viewpoint) diagramContainer;
      return getViewpointShortName(viewpoint) + SEPARATOR + getDiagramShortName(diagram) + SEPARATOR
          + element.getName();
    }

    return null;
  }

  private String getViewpointShortName(Viewpoint viewpoint) {
    return viewpointShortNames.get(viewpoint.getName());
  }

  private String getDiagramShortName(DiagramDescription diagram) {
    String titleExpression = diagram.getTitleExpression();
    String[] tokens = titleExpression.split("(\\[)|(\\])");

    if (tokens.length == 3 && !tokens[1].contains("self")) {
      return tokens[1].toLowerCase();
    }

    tokens = diagram.getName().split(" ");
    StringBuilder result = new StringBuilder();

    for (String token : tokens) {
      char letter = Character.toLowerCase(token.charAt(0));
      result.append(letter);
    }

    return result.toString();
  }

}
