/*******************************************************************************
 * Copyright (c) 2019, 2020 THALES GLOBAL SERVICES.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
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
import org.polarsys.capella.core.model.helpers.BlockArchitectureExt;
import org.polarsys.capella.core.model.helpers.CapellaElementExt;
import org.polarsys.capella.core.model.helpers.ComponentExt;
import org.polarsys.capella.core.validation.rule.AbstractValidationRule;

/**
 * TC_DC_13 - This rule ensures that Root Physical Component always realizes a Root Logical Component.
 */
public class PhysicalSystem_RealizedLogicalSystem extends AbstractValidationRule {
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
        if (component.equals(BlockArchitectureExt.getRootBlockArchitecture(component).getSystem())) {
          if (component.getRealizedLogicalComponents().isEmpty()) {
            return ctx.createFailureStatus(
                CapellaElementExt.getValidationRuleMessagePrefix(component) + " does not realize the Logical Component");
          }
        }
      }
    }
    return ctx.createSuccessStatus();
  }
}
