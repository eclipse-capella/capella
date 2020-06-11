/*******************************************************************************
 * Copyright (c) 2006, 2019, 2020 THALES GLOBAL SERVICES.
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
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.validation.EMFEventType;
import org.eclipse.emf.validation.IValidationContext;
import org.polarsys.capella.core.data.pa.PhysicalComponent;
import org.polarsys.capella.core.model.helpers.CapellaElementExt;
import org.polarsys.capella.core.model.helpers.ComponentExt;
import org.polarsys.capella.core.validation.rule.AbstractValidationRule;

/**
 * TC_DC_09 - Ensures that the Component Realization targeting a Physical Component always realizes a Logical Component.
 */
public class PhysicalComponent_RealizedLogicalComponents extends AbstractValidationRule {
  /**
   * @see org.eclipse.emf.validation.AbstractModelConstraint#validate(org.eclipse.emf.validation.IValidationContext)
   */
  @Override
  public IStatus validate(IValidationContext ctx) {
    EObject eObj = ctx.getTarget();
    EMFEventType eType = ctx.getEventType();

    if (eType == EMFEventType.NULL) {
      if (eObj instanceof PhysicalComponent && !ComponentExt.isActor(eObj)) {
        PhysicalComponent component = (PhysicalComponent) eObj;
        if (component.eContainer() instanceof PhysicalComponent) {
          if (!component.getRealizedLogicalComponents().isEmpty()) {
            return ctx.createSuccessStatus();
          }
          return ctx.createFailureStatus(
              CapellaElementExt.getValidationRuleMessagePrefix(component) + " does not realize any Logical Component.");
        }
      }
    }
    return ctx.createSuccessStatus();
  }
}
