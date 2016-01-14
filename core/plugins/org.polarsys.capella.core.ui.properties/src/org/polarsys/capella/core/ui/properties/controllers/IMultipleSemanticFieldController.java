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

import java.util.List;

import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EStructuralFeature;

import org.polarsys.capella.core.data.capellacore.CapellaElement;

/**
 * TODO Please document this interface.
 */
public interface IMultipleSemanticFieldController {

  /**
   * @param semanticElement
   * @param semanticFeature
   * @return
   */
  public List<EObject> loadValues(CapellaElement semanticElement, EStructuralFeature semanticFeature);

  /**
   * @param semanticElement
   * @param semanticFeature
   * @param available
   * @return
   */
  public List<EObject> readOpenValues(CapellaElement semanticElement, EStructuralFeature semanticFeature, boolean available);

  /**
   * @param semanticElement
   * @param semanticFeature
   * @param values
   * @return
   */
  public List<EObject> writeOpenValues(CapellaElement semanticElement, EStructuralFeature semanticFeature, List<EObject> values);
}
