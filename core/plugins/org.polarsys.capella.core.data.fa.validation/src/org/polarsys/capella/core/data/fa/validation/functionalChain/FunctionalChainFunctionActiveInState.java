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
package org.polarsys.capella.core.data.fa.validation.functionalChain;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.eclipse.core.runtime.IStatus;
import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.validation.EMFEventType;
import org.eclipse.emf.validation.IValidationContext;

import org.polarsys.capella.common.mdsofa.common.constant.ICommonConstants;
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

    if (eType == EMFEventType.NULL) {
      if (eObj instanceof FunctionalChain) {
        // functiaonlChain
        FunctionalChain chain = (FunctionalChain) eObj;
        String chainType = chain.eClass().getName();
        // involved functions
        EList<AbstractFunction> involvedFunctions = chain.getInvolvedFunctions();
        EList<State> availableInStates = chain.getAvailableInStates();
        // collect list of functions which are not active in all the modes and states of functional chain
        List<AbstractFunction> inActiveFunctions = new ArrayList<AbstractFunction>(1);
        for (AbstractFunction function : involvedFunctions) {
          if(!function.getAvailableInStates().containsAll(availableInStates)){
            inActiveFunctions.add(function);
          }
        }
        
        // if any inActiveFunction, create a failure message
        if (!inActiveFunctions.isEmpty()) {
          String currentNext = ICommonConstants.EMPTY_STRING;
          Iterator<AbstractFunction> iterator = inActiveFunctions.iterator();
          while (iterator.hasNext()) {
            AbstractFunction function = iterator.next();
            currentNext = currentNext + function.getName();
            if (iterator.hasNext()) {
              currentNext = currentNext + ", ";  //$NON-NLS-1$
            }
          }
          boolean singleFunction = inActiveFunctions.size() == 1;
          
          return ctx.createFailureStatus(new Object[] { chain.getName(), currentNext, singleFunction ? "is" : "are", singleFunction ? ICommonConstants.EMPTY_STRING : "s", chainType}); //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$
        }
      }
    }
    return ctx.createSuccessStatus();
  }
}
