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

import org.eclipse.core.runtime.IStatus;
import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.validation.EMFEventType;
import org.eclipse.emf.validation.IValidationContext;

import org.polarsys.capella.core.data.la.LogicalActor;
import org.polarsys.capella.core.data.la.SystemActorRealization;
import org.polarsys.capella.core.validation.rule.AbstractValidationRule;

/**
 * Rule ensures that logical actor realizes at least one system actor
 */
public class LogicalActor_PhysicalActor_ActorRealization extends AbstractValidationRule {
  /**
   * @see org.eclipse.emf.validation.AbstractModelConstraint#validate(org.eclipse.emf.validation.IValidationContext)
   */
  @Override
  public IStatus validate(IValidationContext ctx_p) {

    EObject eObj = ctx_p.getTarget();
    EMFEventType eType = ctx_p.getEventType();

    if (eType == EMFEventType.NULL) {
      if (eObj instanceof LogicalActor) {
        LogicalActor logicalActor = (LogicalActor) eObj;
        // get system realization
        EList<SystemActorRealization> actorRealisations = logicalActor.getSystemActorRealizations();
        if (actorRealisations.size() < 1) {
          return ctx_p.createFailureStatus(logicalActor.getName() + " (" + logicalActor.eClass().getName() + ") does not realize any System Actor."); //$NON-NLS-1$//$NON-NLS-2$
        }
      }
    }
    return ctx_p.createSuccessStatus();
  }

}
