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

import org.polarsys.capella.core.data.cs.Part;
import org.polarsys.capella.core.data.cs.PhysicalLink;
import org.polarsys.capella.core.data.cs.PhysicalPath;
import org.polarsys.capella.core.data.cs.PhysicalPathInvolvement;
import org.polarsys.capella.core.data.cs.PhysicalPathReference;
import org.polarsys.capella.core.data.capellacore.InvolvedElement;
import org.polarsys.capella.core.validation.rule.AbstractValidationRule;

public class PhysicalPathInvolvement_Involved extends AbstractValidationRule {
  /**
   * @see org.eclipse.emf.validation.AbstractModelConstraint#validate(org.eclipse.emf.validation.IValidationContext)
   */
  @Override
  public IStatus validate(IValidationContext ctx) {
    if (EMFEventType.NULL.equals(ctx.getEventType())) {
      EObject eObj = ctx.getTarget();
      if (eObj instanceof PhysicalPathInvolvement) {
        InvolvedElement involved = ((PhysicalPathInvolvement) eObj).getInvolved();

        if (eObj instanceof PhysicalPathReference) {
          if (!(involved instanceof PhysicalPath)) {
            return ctx.createFailureStatus(new Object[] { Messages.MDCHK_PhysicalPathInvolvement_PhysicalPathReference,
                                                          Messages.MDCHK_PhysicalPathInvolvement_aPhysicalPath });
          }
        } else {
          if (!(involved instanceof Part) && !(involved instanceof PhysicalLink)) {
            return ctx.createFailureStatus(new Object[] { Messages.MDCHK_PhysicalPathInvolvement_PhysicalPathInvolvement,
                                                          Messages.MDCHK_PhysicalPathInvolvement_aPartOrPhysicalLink });
          }

        }
      }
    }
    return ctx.createSuccessStatus();
  }
}
