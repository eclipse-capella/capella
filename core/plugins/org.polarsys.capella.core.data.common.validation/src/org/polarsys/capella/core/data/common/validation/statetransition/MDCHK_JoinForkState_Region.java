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

import java.util.Collection;

import org.eclipse.core.runtime.IStatus;
import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.validation.AbstractModelConstraint;
import org.eclipse.emf.validation.IValidationContext;
import org.polarsys.capella.common.helpers.EcoreUtil2;
import org.polarsys.capella.core.data.capellacommon.AbstractState;
import org.polarsys.capella.core.data.capellacommon.ForkPseudoState;
import org.polarsys.capella.core.data.capellacommon.JoinPseudoState;
import org.polarsys.capella.core.data.capellacommon.Pseudostate;
import org.polarsys.capella.core.data.capellacommon.State;
import org.polarsys.capella.core.data.capellacommon.StateTransition;

import com.google.common.base.Function;
import com.google.common.collect.Collections2;

public class MDCHK_JoinForkState_Region extends AbstractModelConstraint {

  public MDCHK_JoinForkState_Region() {
    // TODO Auto-generated constructor stub
  }

  private boolean isJoinState;

  @Override
  public IStatus validate(IValidationContext ctx) {
    Pseudostate state = (Pseudostate) ctx.getTarget();

    if (state instanceof JoinPseudoState) {
      isJoinState = true;
    } else if (state instanceof ForkPseudoState) {
      isJoinState = false;
    } else {
      return ctx.createSuccessStatus();
    }

    EList<StateTransition> transitions;
    transitions = isJoinState ? state.getIncoming() : state.getOutgoing();

    if ((transitions == null) || (transitions.size() < 2)) {
      // error but not treated in this rule
      return ctx.createSuccessStatus();
    }

    Collection<AbstractState> statesAffected = Collections2.transform(transitions, new Function<StateTransition, AbstractState>() {
      @SuppressWarnings("synthetic-access")
      @Override
      public AbstractState apply(StateTransition arg0) {
        return isJoinState ? arg0.getSource() : arg0.getTarget();
      }
    });

    EObject eObject = EcoreUtil2.getCommonAncestor(statesAffected);
    if (!statesAffected.contains(eObject) && (eObject instanceof State)) {
      return ctx.createSuccessStatus();
    }
    return ctx.createFailureStatus(new Object[] { state.eClass().getName(), state.getName(), isJoinState ? "incoming" : "outgoing" }); //$NON-NLS-1$//$NON-NLS-2$
  }
}
