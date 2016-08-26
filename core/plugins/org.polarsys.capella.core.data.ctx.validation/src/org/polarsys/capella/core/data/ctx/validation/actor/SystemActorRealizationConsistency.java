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

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.eclipse.core.runtime.IStatus;
import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.validation.EMFEventType;
import org.eclipse.emf.validation.IValidationContext;
import org.eclipse.emf.validation.model.ConstraintStatus;

import org.polarsys.capella.core.data.ctx.Actor;
import org.polarsys.capella.core.data.ctx.OperationalActorRealization;
import org.polarsys.capella.core.data.ctx.OperationalEntityRealization;
import org.polarsys.capella.core.data.oa.Entity;
import org.polarsys.capella.core.validation.rule.AbstractValidationRule;
import org.polarsys.capella.common.data.modellingcore.AbstractTrace;
import org.polarsys.capella.common.data.modellingcore.TraceableElement;

/**
 * This rule ensures that LogicalActor's Entity/OperatinoalActor Realization has consistent source and target (that is source as Actor and target as Entity)
 */
public class SystemActorRealizationConsistency extends AbstractValidationRule {
  /**
   * @see org.eclipse.emf.validation.AbstractModelConstraint#validate(org.eclipse.emf.validation.IValidationContext)
   */
  @Override
  public IStatus validate(IValidationContext ctx) {
    EObject eObj = ctx.getTarget();
    EMFEventType eType = ctx.getEventType();

    if (eType == EMFEventType.NULL) {
      if (eObj instanceof Actor) {
        List<IStatus> statuses = new ArrayList<IStatus>();
        Actor actor = (Actor) eObj;
        EList<AbstractTrace> traces = actor.getOutgoingTraces();
        // if no realization found, no consistency check needed
        if (traces.size() < 1) {
          return ctx.createSuccessStatus();
        }

        Iterator<AbstractTrace> iterator = traces.iterator();
        while (iterator.hasNext()) {
          AbstractTrace next = iterator.next();
          if ((next instanceof OperationalActorRealization) || (next instanceof OperationalEntityRealization)) {
            TraceableElement targetElement = next.getTargetElement();
            // if target is not actor create failure status message
            if ((null == targetElement) || !(targetElement instanceof Entity)) {
              statuses.add(ctx.createFailureStatus(actor.getName() + " (" + actor.eClass().getName() //$NON-NLS-1$
                                                     + ") contain realization with inconsistent Target (it should be an Entity or Operational Actor)")); //$NON-NLS-1$
            }
            TraceableElement sourceElement = next.getSourceElement();
            // if target is not actor create failure status message
            if ((null == sourceElement) || !(sourceElement instanceof Actor)) {
              statuses.add(ctx.createFailureStatus(actor.getName() + " (" + actor.eClass().getName() //$NON-NLS-1$
                                                     + ") contain realization with inconsistent Source (it should be System Actor)")); //$NON-NLS-1$
            }
          }
        }
        // return list of failure status message if any
        if (statuses.size() > 0) {
          return ConstraintStatus.createMultiStatus(ctx, statuses);
        }
      }
    }
    return ctx.createSuccessStatus();

  }
}
