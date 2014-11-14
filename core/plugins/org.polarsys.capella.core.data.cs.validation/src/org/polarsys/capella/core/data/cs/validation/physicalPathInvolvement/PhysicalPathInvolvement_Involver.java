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
package org.polarsys.capella.core.data.cs.validation.physicalPathInvolvement;

import org.eclipse.core.runtime.IStatus;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.validation.EMFEventType;
import org.eclipse.emf.validation.IValidationContext;

import org.polarsys.capella.core.data.cs.PhysicalPath;
import org.polarsys.capella.core.data.cs.PhysicalPathInvolvement;
import org.polarsys.capella.core.data.capellacore.InvolverElement;
import org.polarsys.capella.core.validation.rule.AbstractValidationRule;

public class PhysicalPathInvolvement_Involver extends AbstractValidationRule {
  /**
   * @see org.eclipse.emf.validation.AbstractModelConstraint#validate(org.eclipse.emf.validation.IValidationContext)
   */
  @Override
  public IStatus validate(IValidationContext ctx) {
    if (EMFEventType.NULL.equals(ctx.getEventType())) {
      EObject eObj = ctx.getTarget();
      if (eObj instanceof PhysicalPathInvolvement) {
        EObject owner = eObj.eContainer();
        InvolverElement involver = ((PhysicalPathInvolvement) eObj).getInvolver();

        if (!(involver instanceof PhysicalPath) || !involver.equals(owner)) {
          return ctx.createFailureStatus(new Object[] { eObj });
        }
      }
    }
    return ctx.createSuccessStatus();
  }
}
