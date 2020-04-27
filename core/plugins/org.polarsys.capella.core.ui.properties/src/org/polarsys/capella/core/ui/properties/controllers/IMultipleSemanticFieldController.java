/*******************************************************************************
 * Copyright (c) 2006, 2016 THALES GLOBAL SERVICES.
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

import java.util.List;

import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EStructuralFeature;

/**
 * TODO Please document this interface.
 */
public interface IMultipleSemanticFieldController {

  /**
   * @param semanticElement
   * @param semanticFeature
   * @return
   */
  public List<EObject> loadValues(EObject semanticElement, EStructuralFeature semanticFeature);

  /**
   * @param semanticElement
   * @param semanticFeature
   * @param available
   * @return
   */
  public List<EObject> readOpenValues(EObject semanticElement, EStructuralFeature semanticFeature, boolean available);

  /**
   * @param semanticElement
   * @param semanticFeature
   * @param values
   * @return
   */
  public List<EObject> writeOpenValues(EObject semanticElement, EStructuralFeature semanticFeature, List<EObject> values);

}
