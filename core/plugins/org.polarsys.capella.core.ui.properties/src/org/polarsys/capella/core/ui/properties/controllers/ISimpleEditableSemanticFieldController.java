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

import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EStructuralFeature;

import org.polarsys.capella.core.data.capellacore.CapellaElement;

/**
 * TODO Please document this interface..
 */
public interface ISimpleEditableSemanticFieldController extends ISimpleSemanticFieldController {

  /**
   * @param semanticElement_p
   * @param semanticFeature_p
   * @param defaultName_p
   * @return
   */
  public EObject editValue(CapellaElement semanticElement_p, EStructuralFeature semanticFeature_p, String defaultName_p);
}
