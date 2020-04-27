/*******************************************************************************
 * Copyright (c) 2006, 2016 THALES GLOBAL SERVICES.
 * 
 * This program and the accompanying materials are made available under the
 * terms of the Eclipse Public License 2.0 which is available at
 * http://www.eclipse.org/legal/epl-2.0
 * 
 * SPDX-License-Identifier: EPL-2.0
 * 
 * Contributors:
 *    Thales - initial API and implementation
 *******************************************************************************/
package org.polarsys.capella.core.data.common.validation.statetransition;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import org.eclipse.core.runtime.IStatus;
import org.eclipse.emf.validation.AbstractModelConstraint;
import org.eclipse.emf.validation.IValidationContext;
import org.eclipse.emf.validation.model.ConstraintStatus;
import org.polarsys.capella.common.data.behavior.AbstractEvent;
import org.polarsys.capella.core.data.capellacommon.State;
import org.polarsys.capella.core.data.fa.AbstractFunction;

public class MDCHK_StateMachine_AvailableFunctions extends AbstractModelConstraint {

  private static final String DO_ACTIVITY = "doActivity"; //$NON-NLS-1$
  private static final String ENTRY = "entry"; //$NON-NLS-1$
  private static final String EXIT = "exit"; //$NON-NLS-1$

  /**
   * {@inheritDoc}
   */
  @Override
  public IStatus validate(IValidationContext ctx) {
    State state = (State) ctx.getTarget();
    List<IStatus> result  = new ArrayList<IStatus>();
    Collection<AbstractFunction> availableFunctions = state.getAvailableAbstractFunctions();

    for (AbstractEvent activity : state.getDoActivity()) {
      if (!(activity instanceof AbstractFunction) || !availableFunctions.contains(activity)) {
        result.add(ctx.createFailureStatus(new Object[] { DO_ACTIVITY, activity.getName(), state.getName() }));
      }
    }
    for (AbstractEvent entry : state.getEntry()) {
      if (!(entry instanceof AbstractFunction) || !availableFunctions.contains(entry)) {
        result.add(ctx.createFailureStatus(new Object[] { ENTRY, entry.getName(), state.getName() }));
      }
    }
    for (AbstractEvent exit : state.getExit()) {
      if (!(exit instanceof AbstractFunction) || !availableFunctions.contains(exit)) {
        result.add(ctx.createFailureStatus(new Object[] { EXIT, exit.getName(), state.getName() }));
      }
    }
    if (!result.isEmpty()) {
      return ConstraintStatus.createMultiStatus(ctx, result);
    }
    return ctx.createSuccessStatus();
  }
}
