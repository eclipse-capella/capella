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
import org.polarsys.capella.core.data.capellacommon.JoinPseudoState;
import org.polarsys.capella.core.data.capellacommon.Region;
import org.polarsys.capella.core.data.capellacommon.StateTransition;

public class MDCHK_Join_TargetState_Region_Join extends AbstractModelConstraint {
  /*
   * (non-Javadoc)
   * @see org.eclipse.emf.validation.AbstractModelConstraint#validate(org.eclipse.emf.validation.IValidationContext)
   */
  @Override
  public IStatus validate(IValidationContext ctx) {
    if (ctx.getTarget() instanceof JoinPseudoState) {
      JoinPseudoState join = (JoinPseudoState) ctx.getTarget();
      Region joinRegion = StateMachineUtils.getRegion(join);
      if (joinRegion != null) {
        for (StateTransition transition : join.getIncoming()) {
          Region stateRegion = StateMachineUtils.getRegion(transition.getSource());
          if (stateRegion != null && stateRegion.equals(joinRegion)) {
            return ctx.createFailureStatus(join.getName());
          }
        }
      }
      return ctx.createSuccessStatus();
    }
    return null;
  }
}
