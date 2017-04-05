/*******************************************************************************
 * Copyright (c) 2017 THALES GLOBAL SERVICES.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *   
 * Contributors:
 *    Thales - initial API and implementation
 *******************************************************************************/
package org.polarsys.capella.core.data.common.statemachine.validation;

import org.eclipse.core.runtime.IStatus;
import org.eclipse.emf.validation.AbstractModelConstraint;
import org.eclipse.emf.validation.IValidationContext;
import org.polarsys.capella.core.data.capellacommon.Pseudostate;
import org.polarsys.capella.core.data.capellacommon.StateTransition;

public class MDCHK_PseudoState_OutTransitionTrigger extends AbstractModelConstraint {
  /*
   * (non-Javadoc)
   * @see org.eclipse.emf.validation.AbstractModelConstraint#validate(org.eclipse.emf.validation.IValidationContext)
   */
  @Override
  public IStatus validate(IValidationContext ctx) {
    if (ctx.getTarget() instanceof Pseudostate) {
      Pseudostate pseudoState = (Pseudostate) ctx.getTarget();
      for (StateTransition transition : pseudoState.getOutgoing()) {
        if (transition.getTriggers().size() > 0) {
          return ctx.createFailureStatus(pseudoState.getName());
        }
      }
      return ctx.createSuccessStatus();
    }
    return null;
  }
}
