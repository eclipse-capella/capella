/*******************************************************************************
 * Copyright (c) 2018 THALES GLOBAL SERVICES.
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

package org.polarsys.capella.core.data.fa.validation.function;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.eclipse.core.runtime.IStatus;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.validation.EMFEventType;
import org.eclipse.emf.validation.IValidationContext;
import org.polarsys.capella.core.data.capellacommon.State;
import org.polarsys.capella.core.data.fa.AbstractFunction;
import org.polarsys.capella.core.data.fa.FunctionalChain;
import org.polarsys.capella.core.validation.rule.AbstractValidationRule;

public class MDCHK_Function_StateAllocation extends AbstractValidationRule {

  @Override
  public IStatus validate(IValidationContext ctx) {
    EObject eObj = ctx.getTarget();
    EMFEventType eType = ctx.getEventType();

    if ((eType == EMFEventType.NULL) && (eObj instanceof AbstractFunction)) {
      AbstractFunction abstractFunction = (AbstractFunction) eObj;
      List<State> functionStates = abstractFunction.getAvailableInStates();
      Set<State> allChainStates = new HashSet<State>();
      String chainName = "FunctionalChain";

      for (FunctionalChain chain : abstractFunction.getInvolvingFunctionalChains()) {
        allChainStates.addAll(chain.getAvailableInStates());
        chainName = chain.eClass().getName();
      }

      boolean ok = allChainStates.isEmpty();
      if (!ok) {
        for (State state : functionStates) {
          if (allChainStates.contains(state)) {
            ok = true;
            break;
          }
        }
      }

      if (!ok) {
        return ctx.createFailureStatus( abstractFunction.getName(), chainName );
      }
    }

    return ctx.createSuccessStatus();
  }

}
