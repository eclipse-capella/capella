/*******************************************************************************
 * Copyright (c) 2006, 2015 THALES GLOBAL SERVICES.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *  
 * Contributors:
 *    Thales - initial API and implementation
 *******************************************************************************/
package org.polarsys.capella.core.ui.properties.controllers;

import java.util.ArrayList;
import java.util.List;

import org.eclipse.emf.ecore.EObject;
import org.eclipse.sirius.viewpoint.DRepresentation;

import org.polarsys.capella.core.diagram.helpers.ContextualDiagramHelper;

/**
 */
public class RepresentationContextualElementsController {

  /**
   * {@inheritDoc}
   */
  public List<EObject> loadValues(EObject semanticElement) {
    return ContextualDiagramHelper.getService().getContextualElements((DRepresentation) semanticElement);
  }

  /**
   * {@inheritDoc}
   */
  public List<EObject> readOpenValues(EObject semanticElement, boolean available) {
    if (available) {
      ArrayList<EObject> values = new ArrayList<EObject>();
      values.addAll(ContextualDiagramHelper.getService().getAvailableContextualElements((DRepresentation) semanticElement));
      return values;
    }
    return ContextualDiagramHelper.getService().getContextualElements((DRepresentation) semanticElement);
  }

  /**
   * {@inheritDoc}
   */
  public List<EObject> writeOpenValues(EObject semanticElement, List<EObject> values) {
    ContextualDiagramHelper.getService().setContextualElements((DRepresentation) semanticElement, values);
    return values;
  }
}
