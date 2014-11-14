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
package org.polarsys.capella.core.data.oa.validation.operationalActivity;

import java.util.Iterator;

import org.eclipse.core.runtime.IStatus;
import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.validation.EMFEventType;
import org.eclipse.emf.validation.IValidationContext;

import org.polarsys.capella.core.data.ctx.OperationalActorRealization;
import org.polarsys.capella.core.data.ctx.OperationalEntityRealization;
import org.polarsys.capella.core.data.oa.Entity;
import org.polarsys.capella.core.validation.rule.AbstractValidationRule;
import org.polarsys.capella.common.data.modellingcore.AbstractTrace;

/**
 * This rule ensures that an Entity is realized by at least one system Actor
 */
public class EntityRealizedByActor extends AbstractValidationRule {
  /**
   * @see org.eclipse.emf.validation.AbstractModelConstraint#validate(org.eclipse.emf.validation.IValidationContext)
   */
  @Override
  public IStatus validate(IValidationContext ctx_p) {
    EObject eObj = ctx_p.getTarget();
    EMFEventType eType = ctx_p.getEventType();

    if (eType == EMFEventType.NULL) {
      if (eObj instanceof Entity) {
        Entity actor = (Entity) eObj;
        EList<AbstractTrace> traces = actor.getIncomingTraces();
        if (traces.size() < 1) {
          return ctx_p.createFailureStatus(actor.getName() + " (" + actor.eClass().getName() + ") is not realized by any System Actor."); //$NON-NLS-1$ //$NON-NLS-2$
        }
        Iterator<AbstractTrace> iterator = traces.iterator();
        while (iterator.hasNext()) {
          AbstractTrace next = iterator.next();
          if ((next instanceof OperationalActorRealization) || (next instanceof OperationalEntityRealization)) {
            return ctx_p.createSuccessStatus();
          }
        }
        // entity/operationalActor is not realized by any System Actor
        return ctx_p.createFailureStatus(actor.getName() + " (" + actor.eClass().getName() + ") is not realized by any System Actor."); //$NON-NLS-1$ //$NON-NLS-2$
      }
    }

    return ctx_p.createSuccessStatus();
  }
}
