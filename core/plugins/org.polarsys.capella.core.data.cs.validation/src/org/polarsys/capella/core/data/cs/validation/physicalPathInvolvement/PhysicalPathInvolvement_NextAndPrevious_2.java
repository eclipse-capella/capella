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
package org.polarsys.capella.core.data.cs.validation.physicalPathInvolvement;

import org.eclipse.core.runtime.IStatus;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.validation.EMFEventType;
import org.eclipse.emf.validation.IValidationContext;

import org.polarsys.capella.core.data.cs.PhysicalPathInvolvement;
import org.polarsys.capella.core.data.cs.PhysicalPathReference;
import org.polarsys.capella.core.validation.rule.AbstractValidationRule;

public class PhysicalPathInvolvement_NextAndPrevious_2 extends AbstractValidationRule {
  /**
   * @see org.eclipse.emf.validation.AbstractModelConstraint#validate(org.eclipse.emf.validation.IValidationContext)
   */
  @Override
  public IStatus validate(IValidationContext ctx) {
    if (EMFEventType.NULL.equals(ctx.getEventType())) {
      EObject eObj = ctx.getTarget();
      if (eObj instanceof PhysicalPathInvolvement && !(eObj instanceof PhysicalPathReference)) {
        EObject objOwner = eObj.eContainer();
        for (PhysicalPathInvolvement inv : ((PhysicalPathInvolvement) eObj).getPreviousInvolvements()) {
          EObject invOwner = inv.eContainer();
          if (!objOwner.equals(invOwner)) {
            return ctx.createFailureStatus(new Object[] { eObj });
          }
        }
        for (PhysicalPathInvolvement inv : ((PhysicalPathInvolvement) eObj).getNextInvolvements()) {
          EObject invOwner = inv.eContainer();
          if (!objOwner.equals(invOwner)) {
            return ctx.createFailureStatus(new Object[] { eObj });
          }
        }
      }
    }
    return ctx.createSuccessStatus();
  }
}
