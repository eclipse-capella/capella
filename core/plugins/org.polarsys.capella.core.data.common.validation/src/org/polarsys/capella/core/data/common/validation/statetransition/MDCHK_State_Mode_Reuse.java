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

import org.eclipse.core.runtime.IStatus;
import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.validation.AbstractModelConstraint;
import org.eclipse.emf.validation.IValidationContext;
import org.polarsys.capella.common.data.modellingcore.IState;
import org.polarsys.capella.core.data.capellacommon.FinalState;
import org.polarsys.capella.core.data.capellacommon.Mode;
import org.polarsys.capella.core.data.capellacommon.Pseudostate;
import org.polarsys.capella.core.data.capellacommon.State;
import org.polarsys.capella.core.model.preferences.helpers.PreferencesHelper;
import org.polarsys.capella.core.sirius.analysis.StateMachineServices;

public class MDCHK_State_Mode_Reuse extends AbstractModelConstraint {

  public MDCHK_State_Mode_Reuse() {
    // TODO Auto-generated constructor stub
  }

  public boolean isMixedHierarchyAllowed() {
    return PreferencesHelper.isMixedModeStateAllowed();
  }

  @Override
  public IStatus validate(IValidationContext ctx) {
    if (isMixedHierarchyAllowed())
      return ctx.createSuccessStatus();

    State state = (State) ctx.getTarget();
    if ((state instanceof Pseudostate) || (state instanceof FinalState)) {
      return ctx.createSuccessStatus();
    }

    EList<IState> referencedStates = state.getReferencedStates();
    for (IState s : referencedStates) {
      if (StateMachineServices.isReferencedState(s, state) && s.eClass() != state.eClass())
        return createFailureStatus(ctx, state);
    }

    return ctx.createSuccessStatus();
  }

  private IStatus createFailureStatus(IValidationContext ctx, State state) {

    if (state instanceof Mode) {
      return ctx.createFailureStatus(new Object[] { "Mode", state.getName(), "State" }); //$NON-NLS-1$ //$NON-NLS-2$
    }
    return ctx.createFailureStatus(new Object[] { "State", state.getName(), "Mode" }); //$NON-NLS-1$//$NON-NLS-2$
  }
}
