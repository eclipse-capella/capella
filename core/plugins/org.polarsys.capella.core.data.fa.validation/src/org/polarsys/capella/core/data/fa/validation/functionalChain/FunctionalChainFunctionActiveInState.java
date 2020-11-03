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
package org.polarsys.capella.core.data.fa.validation.functionalChain;

import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;

import org.eclipse.core.runtime.IStatus;
import org.eclipse.emf.validation.EMFEventType;
import org.eclipse.emf.validation.IValidationContext;
import org.polarsys.capella.core.data.capellacommon.State;
import org.polarsys.capella.core.data.fa.FunctionalChain;
import org.polarsys.capella.core.validation.rule.AbstractValidationRule;

public class FunctionalChainFunctionActiveInState extends AbstractValidationRule {
  /**
   * @see org.eclipse.emf.validation.AbstractModelConstraint#validate(org.eclipse.emf.validation.IValidationContext)
   */
  @Override
  public IStatus validate(IValidationContext ctx) {

    if ((ctx.getEventType() == EMFEventType.NULL) && (ctx.getTarget() instanceof FunctionalChain)) {
      FunctionalChain chain = (FunctionalChain) ctx.getTarget();

      Set<State> chainStates = new HashSet<>(chain.getAvailableInStates());

      // reunion of available states in the functions involved in this chain
      Set<State> chainFunctionsStates = chain.getInvolvedFunctions().stream()
          .flatMap(function -> function.getAvailableInStates().stream()).collect(Collectors.toSet());

      chainStates.removeAll(chainFunctionsStates);

      if (!chainStates.isEmpty()) {
        String chainType = chain.eClass().getName();
        return ctx.createFailureStatus(chain.getName(), chainType);
      }
    }
    return ctx.createSuccessStatus();
  }
}
