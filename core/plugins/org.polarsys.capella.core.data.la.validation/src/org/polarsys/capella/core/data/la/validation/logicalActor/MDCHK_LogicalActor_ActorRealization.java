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
package org.polarsys.capella.core.data.la.validation.logicalActor;

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
import org.polarsys.capella.core.data.la.LogicalActor;
import org.polarsys.capella.core.data.la.SystemActorRealization;
import org.polarsys.capella.core.validation.rule.AbstractValidationRule;
import org.polarsys.capella.common.data.modellingcore.AbstractTrace;
import org.polarsys.capella.common.data.modellingcore.TraceableElement;

/**
 * This rule ensures that LogicalActor's System Actor Realization has consistent source and target (that is source as LogicalActor and target as System Actor)
 */
public class MDCHK_LogicalActor_ActorRealization extends AbstractValidationRule {
  /**
   * @see org.eclipse.emf.validation.AbstractModelConstraint#validate(org.eclipse.emf.validation.IValidationContext)
   */
  @Override
  public IStatus validate(IValidationContext ctx_p) {
    EObject eObj = ctx_p.getTarget();
    EMFEventType eType = ctx_p.getEventType();

    if (eType == EMFEventType.NULL) {
      if (eObj instanceof LogicalActor) {
        List<IStatus> statuses = new ArrayList<IStatus>();
        LogicalActor actor = (LogicalActor) eObj;
        EList<AbstractTrace> traces = actor.getOutgoingTraces();
        // if no realization found, no consistency check needed
        if (traces.size() < 1) {
          return ctx_p.createSuccessStatus();
        }

        Iterator<AbstractTrace> iterator = traces.iterator();
        while (iterator.hasNext()) {
          AbstractTrace next = iterator.next();
          if (next instanceof SystemActorRealization) {
            TraceableElement targetElement = next.getTargetElement();
            // if target is not actor create failure status message
            if ((null == targetElement) || !(targetElement instanceof Actor)) {
              statuses.add(ctx_p.createFailureStatus(actor.getName() + " (" + actor.eClass().getName() //$NON-NLS-1$
                                                     + ") contain realization with inconsistent Target (it should be System Actor).")); //$NON-NLS-1$
            }
            TraceableElement sourceElement = next.getSourceElement();
            // if target is not actor create failure status message
            if ((null == sourceElement) || !(sourceElement instanceof LogicalActor)) {
              statuses.add(ctx_p.createFailureStatus(actor.getName() + " (" + actor.eClass().getName() //$NON-NLS-1$
                                                     + ") contain realization with inconsistent Source (it should be Logical Actor).")); //$NON-NLS-1$
            }
          }
        }
        // return list of failure status message if any
        if (statuses.size() > 0) {
          return ConstraintStatus.createMultiStatus(ctx_p, statuses);
        }
      }
    }
    return ctx_p.createSuccessStatus();

  }
}
