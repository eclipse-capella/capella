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
package org.polarsys.capella.core.validation.rule;

import org.eclipse.core.runtime.IStatus;
import org.eclipse.emf.validation.AbstractModelConstraint;
import org.eclipse.emf.validation.IValidationContext;

/**
 * This class delegates constraint evaluation.
 * 
 */
public abstract class AbstractDelegatedModelConstraint extends AbstractModelConstraint {
  
  @Override
  public IStatus validate(IValidationContext ctx_p) {
    return getDelegateFor(ctx_p).validate(ctx_p);
  }
  
  /**
   * Finds an AbstractModelConstraint delegate for the given context. 
   * @param ctx_p
   * @return An AbstractModelConstraint delegate. May not be null.
   */
  protected abstract AbstractModelConstraint getDelegateFor(IValidationContext ctx_p);
  
}
