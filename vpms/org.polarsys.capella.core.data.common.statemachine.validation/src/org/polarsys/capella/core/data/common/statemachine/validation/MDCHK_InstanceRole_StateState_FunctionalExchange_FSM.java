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

import java.util.List;

import org.eclipse.core.runtime.IStatus;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.validation.AbstractModelConstraint;
import org.eclipse.emf.validation.IValidationContext;
import org.polarsys.capella.common.data.behavior.AbstractEvent;
import org.polarsys.capella.core.data.capellacommon.AbstractState;
import org.polarsys.capella.core.data.capellacommon.StateTransition;
import org.polarsys.capella.core.data.fa.FunctionalExchange;
import org.polarsys.capella.core.data.interaction.InstanceRole;

public class MDCHK_InstanceRole_StateState_FunctionalExchange_FSM extends AbstractModelConstraint {
  /*
   * (non-Javadoc)
   * @see org.eclipse.emf.validation.AbstractModelConstraint#validate(org.eclipse .emf.validation.IValidationContext)
   */
  @Override
  public IStatus validate(IValidationContext ctx) {
    if (ctx.getTarget() instanceof InstanceRole) {
      InstanceRole instanceRole = (InstanceRole) ctx.getTarget();
      List<EObject> orderedElementsOfInstanceRole = InteractionUtils.getOrderedElementsOfInstanceRole(instanceRole);
      List<EObject> elementsInExecution = InteractionUtils.getAllElementsInExecution(orderedElementsOfInstanceRole);
      for (int i = 0; i < elementsInExecution.size() - 1; i++) {
        if (elementsInExecution.get(i) instanceof AbstractState && elementsInExecution.get(i + 1) instanceof AbstractState) {
          AbstractState beginningState = (AbstractState) elementsInExecution.get(i);
          AbstractState endingState = (AbstractState) elementsInExecution.get(i + 1);
          // This code can be used to find all transitions in all paths from beginningState to endingState
          // StateMachineUtils stateMachineUtils = new StateMachineUtils();
          // Paths paths = stateMachineUtils.new Paths();
          // Set<StateTransition> stateTransitionsInPath = paths.getStateTransitionsInPaths(beginningState, endingState);
          for (StateTransition stateTransition : beginningState.getOutgoing()) {
            if (stateTransition.getTarget().equals(endingState)) {
              stateTransition.getTriggers();
              for (AbstractEvent abstractEvent : stateTransition.getTriggers()) {
                if (abstractEvent instanceof FunctionalExchange) {
                  return ctx.createFailureStatus(beginningState.getName(), endingState.getName(), ((FunctionalExchange) abstractEvent).getName());
                }
              }
            }
          }
        }
      }
      return ctx.createSuccessStatus();
    }
    return null;
  }
}
