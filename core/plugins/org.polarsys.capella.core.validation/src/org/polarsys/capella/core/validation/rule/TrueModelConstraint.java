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
package org.polarsys.capella.core.validation.rule;

import org.eclipse.core.runtime.IStatus;
import org.eclipse.emf.validation.AbstractModelConstraint;
import org.eclipse.emf.validation.IValidationContext;

/**
 * A model constraint that's always satisfied.
 * 
 */
public class TrueModelConstraint extends AbstractModelConstraint {

  /**
   * A sharable instance of this class.
   */
  public static final TrueModelConstraint INSTANCE = new TrueModelConstraint();
  
  /**
   * {@inheritDoc}
   */
  @Override
  public IStatus validate(IValidationContext ctx_p) {
    return ctx_p.createSuccessStatus();
  }

}
