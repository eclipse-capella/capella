/*******************************************************************************
 * Copyright (c) 2006, 2016 THALES GLOBAL SERVICES.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *  
 * Contributors:
 *    Thales - initial API and implementation
 *******************************************************************************/
package org.polarsys.capella.core.data.ctx.validation.actor;

import java.util.Iterator;

import org.eclipse.core.runtime.IStatus;
import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.validation.EMFEventType;
import org.eclipse.emf.validation.IValidationContext;

import org.polarsys.capella.core.data.ctx.Actor;
import org.polarsys.capella.core.data.ctx.OperationalActorRealization;
import org.polarsys.capella.core.data.ctx.OperationalEntityRealization;
import org.polarsys.capella.core.validation.rule.AbstractValidationRule;
import org.polarsys.capella.common.data.modellingcore.AbstractTrace;

/**
 * Ensures that an Actor realize at least one Entity
 */
public class SystemActorEntityRealization extends AbstractValidationRule {
  /**
   * @see org.eclipse.emf.validation.AbstractModelConstraint#validate(org.eclipse.emf.validation.IValidationContext)
   */
  @Override
  public IStatus validate(IValidationContext ctx) {
    EObject eObj = ctx.getTarget();
    EMFEventType eType = ctx.getEventType();

    if (eType == EMFEventType.NULL) {
      if (eObj instanceof Actor) {
        Actor actor = (Actor) eObj;
        EList<AbstractTrace> traces = actor.getOutgoingTraces();
        if (traces.size() < 1) {
          return ctx.createFailureStatus(actor.getName() + " (" + actor.eClass().getName() + ") does not realize any Entity."); //$NON-NLS-1$ //$NON-NLS-2$
        }
        Iterator<AbstractTrace> iterator = traces.iterator();
        while (iterator.hasNext()) {
          AbstractTrace next = iterator.next();
          if ((next instanceof OperationalActorRealization) || (next instanceof OperationalEntityRealization)) {
            return ctx.createSuccessStatus();
          }
        }
        // actor does not realize any entity
        return ctx.createFailureStatus(actor.getName() + " (" + actor.eClass().getName() + ") does not realize any Entity."); //$NON-NLS-1$ //$NON-NLS-2$
      }
    }

    return ctx.createSuccessStatus();
  }
}
