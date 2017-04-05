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
import org.eclipse.emf.common.util.BasicEList;
import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.validation.AbstractModelConstraint;
import org.eclipse.emf.validation.IValidationContext;
import org.polarsys.capella.common.helpers.EcoreUtil2;
import org.polarsys.capella.core.data.capellacommon.AbstractState;
import org.polarsys.capella.core.data.capellacommon.ForkPseudoState;
import org.polarsys.capella.core.data.capellacommon.State;
import org.polarsys.capella.core.data.capellacommon.StateTransition;

public class MDCHK_Fork_TargetState_Region extends AbstractModelConstraint {
  /*
   * (non-Javadoc)
   * @see org.eclipse.emf.validation.AbstractModelConstraint#validate(org.eclipse.emf.validation.IValidationContext)
   */
  @Override
  public IStatus validate(IValidationContext ctx) {
    EList<AbstractState> targetAbstractState = new BasicEList<AbstractState>();
    if (ctx.getTarget() instanceof ForkPseudoState) {
      ForkPseudoState fork = (ForkPseudoState) ctx.getTarget();
      for (StateTransition transition : fork.getOutgoing()) {
        targetAbstractState.add(transition.getTarget());
      }
      EObject eObject = EcoreUtil2.getCommonAncestor(targetAbstractState);
      if (!targetAbstractState.contains(eObject) && (eObject instanceof State)) {
        return ctx.createSuccessStatus();
      }
      return ctx.createFailureStatus(fork.getName());
    }
    return null;
  }
}
