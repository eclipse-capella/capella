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
import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.validation.AbstractModelConstraint;
import org.eclipse.emf.validation.IValidationContext;
import org.polarsys.capella.core.data.capellacommon.State;
import org.polarsys.capella.core.data.capellacommon.StateMachine;
import org.polarsys.capella.core.data.cs.SystemComponent;
import org.polarsys.capella.core.data.fa.AbstractFunction;
import org.polarsys.capella.core.data.fa.FunctionalChain;

public class MDCHK_FunctionalChain_AllocatedFunction extends AbstractModelConstraint {
  /*
   * (non-Javadoc)
   * @see org.eclipse.emf.validation.AbstractModelConstraint#validate(org.eclipse.emf.validation.IValidationContext)
   */
  @Override
  public IStatus validate(IValidationContext ctx) {
    if (ctx.getTarget() instanceof FunctionalChain) {
      FunctionalChain functionalChain = (FunctionalChain) ctx.getTarget();
      if (!functionalChain.getAvailableInStates().isEmpty() && !functionalChain.getInvolvedFunctions().isEmpty()) {

        EList<State> availableInStates = functionalChain.getAvailableInStates();

        EList<AbstractFunction> involvedFunctions = functionalChain.getInvolvedFunctions();

        EList<StateMachine> stateMachines = StateMachineUtils.getAllStateMachines(availableInStates);

        EList<SystemComponent> systemComponents = StateMachineUtils.getSystemComponents(stateMachines);

        for (SystemComponent systemComponent : systemComponents) {
          for (AbstractFunction involvedFunction : involvedFunctions) {
            if (!systemComponent.getAllocatedFunctions().contains(involvedFunction)) {
              return ctx.createFailureStatus(functionalChain.getName(), systemComponent.getName());
            }
          }
        }
      }
      return ctx.createSuccessStatus();
    }
    return null;
  }
}
