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
import org.polarsys.capella.core.data.capellacommon.ForkPseudoState;
import org.polarsys.capella.core.data.capellacommon.Region;
import org.polarsys.capella.core.data.capellacommon.StateTransition;

public class MDCHK_Fork_TargetState_Region_Fork extends AbstractModelConstraint {
  /*
   * (non-Javadoc)
   * @see org.eclipse.emf.validation.AbstractModelConstraint#validate(org.eclipse.emf.validation.IValidationContext)
   */
  @Override
  public IStatus validate(IValidationContext ctx) {
    if (ctx.getTarget() instanceof ForkPseudoState) {
      ForkPseudoState fork = (ForkPseudoState) ctx.getTarget();
      Region forkRegion = StateMachineUtils.getRegion(fork);
      if (forkRegion != null) {
        for (StateTransition transition : fork.getOutgoing()) {
          Region stateRegion = StateMachineUtils.getRegion(transition.getTarget());
          if (stateRegion != null && stateRegion.equals(forkRegion)) {
            return ctx.createFailureStatus(fork.getName());
          }
        }
      }
      return ctx.createSuccessStatus();
    }
    return null;
  }
}
