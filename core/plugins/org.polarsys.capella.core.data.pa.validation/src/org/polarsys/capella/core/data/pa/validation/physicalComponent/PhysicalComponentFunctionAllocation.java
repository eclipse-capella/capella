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
package org.polarsys.capella.core.data.pa.validation.physicalComponent;

import org.eclipse.core.runtime.IStatus;
import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.validation.EMFEventType;
import org.eclipse.emf.validation.IValidationContext;

import org.polarsys.capella.core.data.fa.AbstractFunction;
import org.polarsys.capella.core.data.pa.PhysicalComponent;
import org.polarsys.capella.core.data.pa.PhysicalComponentNature;
import org.polarsys.capella.core.model.helpers.ComponentExt;
import org.polarsys.capella.core.validation.rule.AbstractValidationRule;

/**
 */
public class PhysicalComponentFunctionAllocation extends AbstractValidationRule {
  /**
   * @see org.eclipse.emf.validation.AbstractModelConstraint#validate(org.eclipse.emf.validation.IValidationContext)
   */
  @Override
  public IStatus validate(IValidationContext ctx) {
    EObject eObj = ctx.getTarget();
    EMFEventType eType = ctx.getEventType();

    if (eType == EMFEventType.NULL && eObj instanceof PhysicalComponent) {
      PhysicalComponent physicalComponent = (PhysicalComponent) eObj;
      if (!ComponentExt.isActor(physicalComponent) && physicalComponent.getNature() == PhysicalComponentNature.NODE) {
        EList<AbstractFunction> allocatedFunctions = physicalComponent.getAllocatedFunctions();
        if (!allocatedFunctions.isEmpty()) {
          return ctx.createFailureStatus(physicalComponent.getName() + " (PhysicalComponent) of Nature NODE should not allocate any Function"); //$NON-NLS-1$
        }
      }
    }
    return ctx.createSuccessStatus();
  }

}
