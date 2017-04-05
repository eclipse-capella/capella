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
import org.polarsys.capella.core.data.capellacommon.StateMachine;
import org.polarsys.capella.core.data.la.LogicalComponent;
import org.polarsys.capella.core.data.la.LogicalFunction;
import org.polarsys.capella.core.data.pa.PhysicalComponent;
import org.polarsys.capella.core.data.pa.PhysicalFunction;

public class MDCHK_Function_AvailableIn extends AbstractModelConstraint {
  /*
   * (non-Javadoc)
   * @see org.eclipse.emf.validation.AbstractModelConstraint#validate(org.eclipse.emf.validation.IValidationContext)
   */
  @Override
  public IStatus validate(IValidationContext ctx) {
    if (ctx.getTarget() instanceof LogicalFunction) {
      LogicalFunction logicalFunction = (LogicalFunction) ctx.getTarget();
      if (!logicalFunction.getAllocatorLogicalComponents().isEmpty()) {
        LogicalComponent logicalComponentRoot = logicalFunction.getAllocatorLogicalComponents().get(0);
        EList<StateMachine> allStateMachines = StateMachineUtils.getAllStateMachines(logicalComponentRoot);
        for (StateMachine stateMachine : allStateMachines) {
          if (StateMachineUtils.isAvailableIn(logicalFunction, stateMachine)) {
            return ctx.createSuccessStatus();
          }
        }
        return ctx.createFailureStatus(logicalFunction.getName(), logicalComponentRoot.getName());
      }
      return ctx.createSuccessStatus();
    }
    if (ctx.getTarget() instanceof PhysicalFunction) {
      PhysicalFunction physicalFunction = (PhysicalFunction) ctx.getTarget();
      if (!physicalFunction.getAllocatorPhysicalComponents().isEmpty()) {
        PhysicalComponent physicalComponentRoot = physicalFunction.getAllocatorPhysicalComponents().get(0);
        EList<StateMachine> allStateMachines = StateMachineUtils.getAllStateMachines(physicalComponentRoot);
        for (StateMachine stateMachine : allStateMachines) {
          if (StateMachineUtils.isAvailableIn(physicalFunction, stateMachine)) {
            return ctx.createSuccessStatus();
          }
        }
        return ctx.createFailureStatus(physicalFunction.getName(), physicalComponentRoot.getName());
      }
      return ctx.createSuccessStatus();
    }
    return null;
  }
}
