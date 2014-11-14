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

import java.util.List;

import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EStructuralFeature;

import org.polarsys.capella.core.data.capellacore.CapellaElement;

/**
 * TODO Please document this interface..
 */
public interface ISimpleSemanticFieldController {

  /**
   * @param semanticElement_p
   * @param semanticFeature_p
   * @return
   */
  public EObject loadValue(CapellaElement semanticElement_p, EStructuralFeature semanticFeature_p);

  /**
   * @param semanticElement_p
   * @param semanticFeature_p
   * @return
   */
  public List<EObject> readOpenValues(CapellaElement semanticElement_p, EStructuralFeature semanticFeature_p);

  /**
   * @param semanticElement_p
   * @param semanticFeature_p
   * @param defaultName_p
   * @param value_p
   * @return
   */
  public EObject writeOpenValue(CapellaElement semanticElement_p, EStructuralFeature semanticFeature_p, String defaultName_p, EObject value_p);
}
