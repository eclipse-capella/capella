/*******************************************************************************
 * Copyright (c) 2006, 2018 THALES GLOBAL SERVICES.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *  
 * Contributors:
 *    Thales - initial API and implementation
 *******************************************************************************/
package org.polarsys.capella.core.data.fa.validation.functionalChain;

import java.util.ArrayList;
import java.util.List;

import org.eclipse.core.runtime.IStatus;
import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.validation.EMFEventType;
import org.eclipse.emf.validation.IValidationContext;

import org.polarsys.capella.core.data.fa.AbstractFunction;
import org.polarsys.capella.core.data.fa.FunctionalChain;
import org.polarsys.capella.core.data.capellacommon.State;
import org.polarsys.capella.core.validation.rule.AbstractValidationRule;

public class FunctionalChainFunctionActiveInState extends AbstractValidationRule {
  /**
   * @see org.eclipse.emf.validation.AbstractModelConstraint#validate(org.eclipse.emf.validation.IValidationContext)
   */
  @Override
  public IStatus validate(IValidationContext ctx) {
    EObject eObj = ctx.getTarget();
    EMFEventType eType = ctx.getEventType();

    if ((eType == EMFEventType.NULL) && (eObj instanceof FunctionalChain)) {
      // functiaonlChain
      FunctionalChain chain = (FunctionalChain) eObj;
      String chainType = chain.eClass().getName();

      // available states
      List<State> availableInStates = new ArrayList<State>(chain.getAvailableInStates());

      // involved functions
      EList<AbstractFunction> involvedFunctions = chain.getInvolvedFunctions();

      // reunion of available states in the functions involved in this chain
      List<State> availableStatesInFunctions = new ArrayList<State>();
      for (AbstractFunction function : involvedFunctions) {
        availableStatesInFunctions.addAll(function.getAvailableInStates());
      }

      availableInStates.removeAll(availableStatesInFunctions);
      if (!availableInStates.isEmpty()) {
        return ctx.createFailureStatus(new Object[] { chain.getName(), chainType });
      }
    }
    return ctx.createSuccessStatus();
  }
}
