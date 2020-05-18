/*******************************************************************************
 * Copyright (c) 2006, 2014 THALES GLOBAL SERVICES.
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
package org.polarsys.capella.core.data.core.validation.capellaelement.nameconflict.signatures;

import org.eclipse.emf.ecore.EObject;

import org.polarsys.capella.core.model.utils.CapellaSwitch;

/**
 * Computes slots for model objects.
 * Slots partition the direct contents of a model object into distinct sets.
 * 
 */
public class CapellaSlots extends CapellaSwitch<Object> {
  
  @Override
  /**
   * The default slot for an EObject is its EClass
   * {@inheritDoc}
   */
  public Object defaultCase(EObject e) {
    return e.eClass();
  }
  
}
