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
import org.polarsys.capella.core.data.capellacommon.AbstractState;
import org.polarsys.capella.core.data.interaction.InstanceRole;

public class MDCHK_InstanceRole_StateState_FSM extends AbstractModelConstraint {
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
          AbstractState state1 = (AbstractState) elementsInExecution.get(i);
          AbstractState state2 = (AbstractState) elementsInExecution.get(i + 1);
          if (!StateMachineUtils.isPathFromState1ToState2(state1, state2)) {
            return ctx.createFailureStatus(state1, state2, instanceRole.getName());
          }
        }
      }
      return ctx.createSuccessStatus();
    }
    return null;
  }
}
