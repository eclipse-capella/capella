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
package org.polarsys.capella.core.data.pa.validation.physicalComponent;

import org.eclipse.core.runtime.IStatus;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.validation.EMFEventType;
import org.eclipse.emf.validation.IValidationContext;
import org.polarsys.capella.core.data.pa.PhysicalComponent;
import org.polarsys.capella.core.model.helpers.ComponentExt;
import org.polarsys.capella.core.validation.rule.AbstractValidationRule;

/**
 * Ensures that the Component Realization targeting a Physical Component always realizes an Logical Component.
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
      if (eObj instanceof PhysicalComponent && ComponentExt.isActor(eObj)) {
        PhysicalComponent actor = (PhysicalComponent) eObj;
        if (!actor.getRealizedLogicalComponents().isEmpty()) {
          return ctx.createSuccessStatus();
        }
        return ctx.createFailureStatus(
            actor.getName() + " (" + actor.eClass().getName() + ") does not realize any Logical Actor."); //$NON-NLS-1$ //$NON-NLS-2$
      }
    }
    return ctx.createSuccessStatus();
  }

}
