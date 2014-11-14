/*******************************************************************************
 * Copyright (c) 2006, 2014 THALES GLOBAL SERVICES.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
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

import com.google.common.base.Predicate;
import com.google.common.collect.Collections2;

public class MDCHK_State_Mode_Hierarchy extends AbstractModelConstraint {
  public MDCHK_State_Mode_Hierarchy() {
  }

  @Override
  public IStatus validate(IValidationContext ctx_p) {
    State state = (State) ctx_p.getTarget();
    if ((state instanceof Pseudostate) || (state instanceof FinalState)) {
      return ctx_p.createSuccessStatus();
    }

    // check if state/mode has no mode/state as "brother"
    EList<Region> regions = state.getInvolverRegions();
    for (Region region : regions) {
      EList<AbstractState> ownedStates = region.getOwnedStates();
      Collection<AbstractState> states = Collections2.filter(ownedStates, Utils.getPredicate(CapellacommonPackage.eINSTANCE.getState()));
      Collection<AbstractState> modes = Collections2.filter(ownedStates, Utils.getPredicate(CapellacommonPackage.eINSTANCE.getMode()));
      if ((modes.size() != 0) && (states.size() != 0)) {
        return createFailureStatus(ctx_p, state);
      }
    }

    return checkStateHierarchy(ctx_p, state);
  }

  /**
   * @param ctx_p
   * @param state_p
   */
  private IStatus createFailureStatus(IValidationContext ctx_p, State state_p) {

    if (state_p instanceof Mode) {
      return ctx_p.createFailureStatus(new Object[] { "Mode", state_p.getName(), "State" }); //$NON-NLS-1$ //$NON-NLS-2$
    }
    return ctx_p.createFailureStatus(new Object[] { "State", state_p.getName(), "Mode" }); //$NON-NLS-1$//$NON-NLS-2$
  }

  /**
   * @param ctx_p
   * @param state_p
   * @param alreadyChecked
   * @return
   */
  private IStatus checkStateHierarchy(IValidationContext ctx_p, State state_p) {
    for (Region region : state_p.getOwnedRegions()) {
      for (AbstractState subState : region.getOwnedStates()) {
        if ((subState instanceof Mode) && !(state_p instanceof Mode)) {
          return createFailureStatus(ctx_p, state_p);
        }
        if ((subState.eClass() == CapellacommonPackage.eINSTANCE.getState()) && (state_p instanceof Mode)) {
          return createFailureStatus(ctx_p, state_p);
        }
      }
    }
    return ctx_p.createSuccessStatus();

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
