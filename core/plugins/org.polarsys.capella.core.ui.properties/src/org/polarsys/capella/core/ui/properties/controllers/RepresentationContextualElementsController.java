/*******************************************************************************
 * Copyright (c) 2006, 2014 THALES GLOBAL SERVICES.
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
  public List<EObject> loadValues(EObject semanticElement_p) {
    return ContextualDiagramHelper.getService().getContextualElements((DRepresentation) semanticElement_p);
  }

  /**
   * {@inheritDoc}
   */
  public List<EObject> readOpenValues(EObject semanticElement_p, boolean available_p) {
    if (available_p) {
      ArrayList<EObject> values = new ArrayList<EObject>();
      values.addAll(ContextualDiagramHelper.getService().getAvailableContextualElements((DRepresentation) semanticElement_p));
      return values;
    }
    return ContextualDiagramHelper.getService().getContextualElements((DRepresentation) semanticElement_p);
  }

  /**
   * {@inheritDoc}
   */
  public List<EObject> writeOpenValues(EObject semanticElement_p, List<EObject> values_p) {
    ContextualDiagramHelper.getService().setContextualElements((DRepresentation) semanticElement_p, values_p);
    return values_p;
  }
}
