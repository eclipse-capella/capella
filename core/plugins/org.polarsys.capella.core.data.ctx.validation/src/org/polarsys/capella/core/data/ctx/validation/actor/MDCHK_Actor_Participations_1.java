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
package org.polarsys.capella.core.data.ctx.validation.actor;

import org.eclipse.core.runtime.IStatus;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.validation.EMFEventType;
import org.eclipse.emf.validation.IValidationContext;

import org.polarsys.capella.core.data.ctx.Actor;
import org.polarsys.capella.core.data.helpers.ctx.services.ActorExt;
import org.polarsys.capella.core.validation.rule.AbstractValidationRule;

/**
 * Minimum number of participation from an Actor towards a Capability should be one
 */
public class MDCHK_Actor_Participations_1 extends AbstractValidationRule {
  /**
   * @see org.eclipse.emf.validation.AbstractModelConstraint#validate(org.eclipse.emf.validation.IValidationContext)
   */
  @Override
  public IStatus validate(IValidationContext ctx) {
    EObject eObj = ctx.getTarget();
    EMFEventType eType = ctx.getEventType();

    // In the case of batch mode.
    if (eType == EMFEventType.NULL) {
      if (eObj instanceof Actor) {
        Actor actor_p = (Actor) eObj;

        if (ActorExt.getInvolvedCapabilities(actor_p).size() == 0) {
          return createFailureStatus(ctx, new Object[] { actor_p.getName() });
        }
      }
    }
    return ctx.createSuccessStatus();
  }
}
