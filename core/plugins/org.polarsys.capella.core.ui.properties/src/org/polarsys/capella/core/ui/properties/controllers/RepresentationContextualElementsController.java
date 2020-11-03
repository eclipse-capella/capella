/*******************************************************************************
 * Copyright (c) 2006, 2020 THALES GLOBAL SERVICES.
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
package org.polarsys.capella.core.ui.properties.controllers;

import java.util.ArrayList;
import java.util.List;

import org.eclipse.emf.ecore.EObject;
import org.eclipse.sirius.viewpoint.DRepresentationDescriptor;
import org.polarsys.capella.core.diagram.helpers.ContextualDiagramHelper;

/**
 */
public class RepresentationContextualElementsController {

  /**
   * {@inheritDoc}
   */
  public List<EObject> loadValues(EObject semanticElement) {
    return ContextualDiagramHelper.getService().getContextualElements((DRepresentationDescriptor) semanticElement);
  }

  /**
   * {@inheritDoc}
   */
  public List<EObject> readOpenValues(EObject semanticElement, boolean available) {
    if (available) {
      ArrayList<EObject> values = new ArrayList<EObject>();
      values.addAll(ContextualDiagramHelper.getService().getAvailableContextualElements((DRepresentationDescriptor) semanticElement));
      return values;
    }
    return ContextualDiagramHelper.getService().getContextualElements((DRepresentationDescriptor) semanticElement);
  }

  /**
   * {@inheritDoc}
   */
  public List<EObject> writeOpenValues(EObject semanticElement, List<EObject> values) {
    ContextualDiagramHelper.getService().setContextualElements((DRepresentationDescriptor) semanticElement, values);
    return values;
  }
}
