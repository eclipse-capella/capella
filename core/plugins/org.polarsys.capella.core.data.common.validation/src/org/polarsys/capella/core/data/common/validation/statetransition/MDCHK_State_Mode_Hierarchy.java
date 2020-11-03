/*******************************************************************************
 * Copyright (c) 2006, 2020 THALES GLOBAL SERVICES.
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

import java.util.Collection;

import org.eclipse.core.runtime.IStatus;
import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.validation.AbstractModelConstraint;
import org.eclipse.emf.validation.IValidationContext;
import org.polarsys.capella.core.data.capellacommon.AbstractState;
import org.polarsys.capella.core.data.capellacommon.CapellacommonPackage;
import org.polarsys.capella.core.data.capellacommon.FinalState;
import org.polarsys.capella.core.data.capellacommon.Mode;
import org.polarsys.capella.core.data.capellacommon.Pseudostate;
import org.polarsys.capella.core.data.capellacommon.Region;
import org.polarsys.capella.core.data.capellacommon.State;
import org.polarsys.capella.core.model.preferences.CapellaModelPreferencesPlugin;

import com.google.common.base.Predicate;
import com.google.common.collect.Collections2;

public class MDCHK_State_Mode_Hierarchy extends AbstractModelConstraint {
  public MDCHK_State_Mode_Hierarchy() {
  }

  public boolean isMixedHierarchyAllowed() {
    return CapellaModelPreferencesPlugin.getDefault().isMixedModeStateAllowed();
  }

  @Override
  public IStatus validate(IValidationContext ctx) {
    if (isMixedHierarchyAllowed())
      return ctx.createSuccessStatus();

    State state = (State) ctx.getTarget();
    if ((state instanceof Pseudostate) || (state instanceof FinalState)) {
      return ctx.createSuccessStatus();
    }

    // check if state/mode has no mode/state as "brother"
    EList<Region> regions = state.getInvolverRegions();
    for (Region region : regions) {
      EList<AbstractState> ownedStates = region.getOwnedStates();
      Collection<AbstractState> states = Collections2.filter(ownedStates,
          Utils.getPredicate(CapellacommonPackage.eINSTANCE.getState()));
      Collection<AbstractState> modes = Collections2.filter(ownedStates,
          Utils.getPredicate(CapellacommonPackage.eINSTANCE.getMode()));
      if ((modes.size() != 0) && (states.size() != 0)) {
        return createFailureStatus(ctx, state);
      }
    }

    return checkStateHierarchy(ctx, state);
  }

  /**
   * @param ctx
   * @param state
   */
  private IStatus createFailureStatus(IValidationContext ctx, State state) {

    if (state instanceof Mode) {
      return ctx.createFailureStatus(new Object[] { "Mode", state.getName(), "State" }); //$NON-NLS-1$ //$NON-NLS-2$
    }
    return ctx.createFailureStatus(new Object[] { "State", state.getName(), "Mode" }); //$NON-NLS-1$//$NON-NLS-2$
  }

  /**
   * @param ctx
   * @param state
   * @param alreadyChecked
   * @return
   */
  private IStatus checkStateHierarchy(IValidationContext ctx, State state) {
    for (Region region : state.getOwnedRegions()) {
      for (AbstractState subState : region.getOwnedStates()) {
        if ((subState instanceof Mode) && !(state instanceof Mode)) {
          return createFailureStatus(ctx, state);
        }
        if ((subState.eClass() == CapellacommonPackage.eINSTANCE.getState()) && (state instanceof Mode)) {
          return createFailureStatus(ctx, state);
        }
      }
    }
    return ctx.createSuccessStatus();

  }
}

class Utils {
  static <T extends EObject> Predicate<T> getPredicate(final EClass eClass) {
    return new Predicate<T>() {
      @Override
      public boolean apply(T arg0) {
        return eClass == arg0.eClass();
      }
    };
  }
}
