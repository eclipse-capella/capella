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
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.validation.AbstractModelConstraint;
import org.eclipse.emf.validation.IValidationContext;
import org.eclipse.emf.validation.model.ConstraintStatus;
import org.polarsys.capella.common.data.behavior.AbstractEvent;
import org.polarsys.capella.core.data.capellacommon.State;
import org.polarsys.capella.core.data.cs.Component;
import org.polarsys.capella.core.data.fa.AbstractFunction;
import org.polarsys.capella.core.data.information.Class;
import org.polarsys.capella.core.model.helpers.ComponentExt;

public class MDCHK_StateMachine_Function extends AbstractModelConstraint {

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

    EObject eContainer = state.eContainer();
    while ((eContainer != null) && !(eContainer instanceof Component) && !(eContainer instanceof Class)) {
      eContainer = eContainer.eContainer();
    }
    if (eContainer instanceof Component) {
      Collection<Component> subComponents = ComponentExt.getAllSubUsedAndDeployedComponents((Component) eContainer);
      subComponents.add((Component) eContainer);

      for (AbstractEvent activity : state.getDoActivity()) {
        boolean ok = false;
        if (activity instanceof AbstractFunction) {
          for (Component component : subComponents) {
            ok |= component.getAllocatedFunctions().contains(activity);
          }
        }
        if (!ok) {
          result.add(ctx.createFailureStatus(new Object[] { DO_ACTIVITY, activity.getName(), state.getName(), eContainer }));
        }
      }
      for (AbstractEvent entry : state.getEntry()) {
        boolean ok = false;
        if (entry instanceof AbstractFunction) {
          for (Component component : subComponents) {
            ok |= component.getAllocatedFunctions().contains(entry);
          }
        }
        if (!ok) {
          result.add(ctx.createFailureStatus(new Object[] { ENTRY, entry.getName(), state.getName(), eContainer }));
        }
      }
      for (AbstractEvent exit : state.getExit()) {
        boolean ok = false;
        if (exit instanceof AbstractFunction) {
          for (Component component : subComponents) {
            ok |= component.getAllocatedFunctions().contains(exit);
          }
        }
        if (!ok) {
          result.add(ctx.createFailureStatus(new Object[] { EXIT, exit.getName(), state.getName(), eContainer }));
        }
      }
      if (!result.isEmpty()) {
        return ConstraintStatus.createMultiStatus(ctx, result);
      }
    }
    return ctx.createSuccessStatus();
  }
}
