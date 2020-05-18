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
package org.polarsys.capella.core.data.la.validation.capabilityRealization;

import org.eclipse.core.runtime.IStatus;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.validation.EMFEventType;
import org.eclipse.emf.validation.IValidationContext;

import org.polarsys.capella.common.helpers.EcoreUtil2;
import org.polarsys.capella.core.data.epbs.EpbsPackage;
import org.polarsys.capella.core.data.la.CapabilityRealization;
import org.polarsys.capella.core.data.la.LaPackage;
import org.polarsys.capella.core.data.pa.PaPackage;
import org.polarsys.capella.core.validation.rule.AbstractValidationRule;

/**
 * This check insures that an AspectPkg doesn't contain more than one FunctionalAspect package.
 */
public class MDCHK_CapabilityRealization_Location_1 extends AbstractValidationRule {
  /**
   * @see org.eclipse.emf.validation.AbstractModelConstraint#validate(org.eclipse.emf.validation.IValidationContext)
   */
  @Override
  public IStatus validate(IValidationContext ctx) {
    EObject eObj = ctx.getTarget();
    EMFEventType eType = ctx.getEventType();

    if (eType == EMFEventType.NULL) {
      if (eObj instanceof CapabilityRealization) {
        CapabilityRealization capability = (CapabilityRealization) eObj;

        if (!EcoreUtil2.isContainedBy(capability, LaPackage.Literals.LOGICAL_ARCHITECTURE)
            && !EcoreUtil2.isContainedBy(capability, PaPackage.Literals.PHYSICAL_ARCHITECTURE)
            && !EcoreUtil2.isContainedBy(capability, EpbsPackage.Literals.EPBS_ARCHITECTURE)) {

          return createFailureStatus(ctx, new Object[] { capability.getName() });
        }
      }
    }
    return ctx.createSuccessStatus();
  }
}
