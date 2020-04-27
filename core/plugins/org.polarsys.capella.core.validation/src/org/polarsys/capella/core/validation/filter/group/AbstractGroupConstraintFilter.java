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
package org.polarsys.capella.core.validation.filter.group;

import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.validation.service.IConstraintDescriptor;
import org.eclipse.emf.validation.service.IConstraintFilter;

/**
 * Filter in order to group model constraint
 */
public abstract class AbstractGroupConstraintFilter implements IConstraintFilter {

  /**
   * 
   * @see org.eclipse.emf.validation.service.IConstraintFilter#accept(org.eclipse.emf.validation.service.IConstraintDescriptor, org.eclipse.emf.ecore.EObject)
   */
  public boolean accept(IConstraintDescriptor constraint_p, EObject target_p) {
    return getGroup().isConstraintContainedInto(constraint_p);
  }
  
  abstract public ConstraintGroupEnum getGroup();
  
}
