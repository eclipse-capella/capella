/*******************************************************************************
 * Copyright (c) 2006, 2017 THALES GLOBAL SERVICES.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *  
 * Contributors:
 *    Thales - initial API and implementation
 *******************************************************************************/
package org.polarsys.capella.core.data.information.validation.collection;

import org.eclipse.core.runtime.IStatus;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.validation.IValidationContext;

import org.polarsys.capella.core.data.information.Collection;
import org.polarsys.capella.core.data.capellacore.Type;
import org.polarsys.capella.core.validation.rule.AbstractValidationRule;

/**
 * This query validates if Collection is typed
 */
public class CollectionType extends AbstractValidationRule {

  /**
   * @see org.eclipse.emf.validation.AbstractModelConstraint#validate(org.eclipse.emf.validation.IValidationContext)
   */
  @Override
  public IStatus validate(IValidationContext ctx) {
    EObject eObj = ctx.getTarget();
    if (eObj instanceof Collection) {
      Type type = ((Collection) eObj).getType();
      if (type == null) {
        return ctx.createFailureStatus(new Object[] {((Collection) eObj).getName()});
      }
    }

    return ctx.createSuccessStatus();
  }
}
