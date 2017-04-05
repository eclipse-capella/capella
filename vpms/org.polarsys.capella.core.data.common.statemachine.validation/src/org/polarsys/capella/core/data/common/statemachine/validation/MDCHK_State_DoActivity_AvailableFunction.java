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
import org.polarsys.capella.common.data.behavior.AbstractEvent;
import org.polarsys.capella.core.data.capellacommon.State;

public class MDCHK_State_DoActivity_AvailableFunction extends AbstractModelConstraint {
  /*
   * (non-Javadoc)
   * @see org.eclipse.emf.validation.AbstractModelConstraint#validate(org.eclipse .emf.validation.IValidationContext)
   */
  @Override
  public IStatus validate(IValidationContext ctx) {
    if (ctx.getTarget() instanceof State) {
      State state = (State) ctx.getTarget();
      AbstractEvent activity = null; // state.getDoActivity();
      if (activity != null) {
        // if (state.getAvailableAbstractFunctions().contains(activity)) {
        return ctx.createSuccessStatus();
        // }
        // EList<State> higherStates = StateMachineUtils.getHigherStates(state);
        // for (State higherState : higherStates) {
        // if (higherState.getAvailableAbstractFunctions().contains(activity)) {
        // return ctx.createSuccessStatus();
        // }
        // }
        // return ctx.createFailureStatus(state.getName(), activity.getName());
      }
      return ctx.createSuccessStatus();
    }
    return null;
  }
}
