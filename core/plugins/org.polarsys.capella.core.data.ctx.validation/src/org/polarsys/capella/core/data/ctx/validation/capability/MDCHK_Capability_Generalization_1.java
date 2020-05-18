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
package org.polarsys.capella.core.data.ctx.validation.capability;

import java.util.Iterator;

import org.eclipse.core.runtime.IStatus;
import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.validation.EMFEventType;
import org.eclipse.emf.validation.IValidationContext;

import org.polarsys.capella.core.data.interaction.AbstractCapability;
import org.polarsys.capella.core.data.interaction.AbstractCapabilityGeneralization;
import org.polarsys.capella.core.validation.rule.AbstractValidationRule;

/**
 * Ensures that a capability doesn't generalize itself.
 */
public class MDCHK_Capability_Generalization_1 extends AbstractValidationRule {
  /**
   * @see org.eclipse.emf.validation.AbstractModelConstraint#validate(org.eclipse.emf.validation.IValidationContext)
   */
  @Override
  public IStatus validate(IValidationContext ctx) {
    EObject eObj = ctx.getTarget();
    EMFEventType eType = ctx.getEventType();
    if (eType == EMFEventType.NULL) {
      if (eObj instanceof AbstractCapability) {

        AbstractCapability capability = (AbstractCapability) eObj;

        EList<AbstractCapabilityGeneralization> generalizedCapabilities = capability.getSubGeneralizations();
        Iterator<AbstractCapabilityGeneralization> iterator = generalizedCapabilities.iterator();
        while (iterator.hasNext()) {
          AbstractCapabilityGeneralization capaGeneralization = iterator.next();
          AbstractCapability superCapa = capaGeneralization.getSuper();
          AbstractCapability subCapa = capaGeneralization.getSub();
          if (superCapa == subCapa) {
            return createFailureStatus(ctx, new Object[] { capability.getName() });
          }
        }
      }
    }
    return ctx.createSuccessStatus();
  }
}
