/*******************************************************************************
 * Copyright (c) 2006, 2020 THALES GLOBAL SERVICES.
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

import org.eclipse.core.runtime.IStatus;
import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.validation.EMFEventType;
import org.eclipse.emf.validation.IValidationContext;

import org.polarsys.capella.core.data.interaction.AbstractCapability;
import org.polarsys.capella.core.validation.rule.AbstractValidationRule;

/**
 * Ensures that a capability doesn't extend itself.
 */
public class MDCHK_Capability_Extension_1 extends AbstractValidationRule {
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

        EList<AbstractCapability> extendedCapabilities = capability.getExtendedAbstractCapabilities();
        if (extendedCapabilities.contains(capability)) {
          // ERROR
          return createFailureStatus(ctx, new Object[] { capability.getName() });

        }
        return ctx.createSuccessStatus();
      }
    }
    return ctx.createSuccessStatus();
  }
}
