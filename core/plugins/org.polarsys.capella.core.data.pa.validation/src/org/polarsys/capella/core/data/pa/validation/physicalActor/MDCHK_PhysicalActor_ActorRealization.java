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
package org.polarsys.capella.core.data.pa.validation.physicalActor;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.eclipse.core.runtime.IStatus;
import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.validation.EMFEventType;
import org.eclipse.emf.validation.IValidationContext;
import org.eclipse.emf.validation.model.ConstraintStatus;

import org.polarsys.capella.core.data.cs.Component;
import org.polarsys.capella.core.data.la.LogicalActor;
import org.polarsys.capella.core.data.pa.LogicalActorRealization;
import org.polarsys.capella.core.data.pa.PhysicalActor;
import org.polarsys.capella.core.validation.rule.AbstractValidationRule;

/**
 * This rule ensures that a Physical Actor always realizes Logical Actor
 */
public class MDCHK_PhysicalActor_ActorRealization extends AbstractValidationRule {
  /**
   * @see org.eclipse.emf.validation.AbstractModelConstraint#validate(org.eclipse.emf.validation.IValidationContext)
   */
  @Override
  public IStatus validate(IValidationContext ctx_p) {
    EObject eObj = ctx_p.getTarget();
    EMFEventType eType = ctx_p.getEventType();

    if (eType == EMFEventType.NULL) {
      if (eObj instanceof PhysicalActor) {
        List<IStatus> statuses = new ArrayList<IStatus>();
        PhysicalActor actor = (PhysicalActor) eObj;
        EList<LogicalActorRealization> logicalActorRealizations = actor.getLogicalActorRealizations();

        // if no realization found, no consistency check needed
        if (logicalActorRealizations.size() < 1) {
          return ctx_p.createSuccessStatus();
        }

        Iterator<LogicalActorRealization> iterator = logicalActorRealizations.iterator();
        while (iterator.hasNext()) {
          LogicalActorRealization next = iterator.next();
          Component allocatedComponent = next.getAllocatedComponent();
          if ((null == allocatedComponent) || !(allocatedComponent instanceof LogicalActor)) {
            statuses.add(ctx_p.createFailureStatus(actor.getName() + " (" + actor.eClass().getName() //$NON-NLS-1$
                                                   + ") contain realization with inconsistent Target (it should be Logical Actor)")); //$NON-NLS-1$
          }
          Component allocatingComponent = next.getAllocatingComponent();
          if ((null == allocatingComponent) || !(allocatingComponent instanceof PhysicalActor)) {
            statuses.add(ctx_p.createFailureStatus(actor.getName() + " (" + actor.eClass().getName() //$NON-NLS-1$
                                                   + ") contain realization with inconsistent target (it should be Physical Actor)")); //$NON-NLS-1$

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
