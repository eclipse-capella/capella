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

import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EStructuralFeature;

/**
 * TODO Please document this interface..
 */
public interface ISimpleEditableSemanticFieldController extends ISimpleSemanticFieldController {

  /**
   * @param semanticElement
   * @param semanticFeature
   * @param defaultName
   * @return
   */
  public EObject editValue(EObject semanticElement, EStructuralFeature semanticFeature, String defaultName);
}
