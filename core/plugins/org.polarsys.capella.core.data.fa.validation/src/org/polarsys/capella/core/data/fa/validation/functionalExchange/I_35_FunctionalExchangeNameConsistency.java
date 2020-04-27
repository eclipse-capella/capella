/*******************************************************************************
 * Copyright (c) 2017 THALES GLOBAL SERVICES.
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

package org.polarsys.capella.core.data.fa.validation.functionalExchange;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Deque;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.eclipse.core.runtime.IStatus;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.validation.IValidationContext;
import org.polarsys.capella.common.helpers.EObjectLabelProviderHelper;
import org.polarsys.capella.core.data.fa.FunctionalExchange;
import org.polarsys.capella.core.validation.rule.AbstractValidationRule;

/**
 * This rule checks that all functional exchanges connected to the same source and/or target port
 * have identical names.
 */
public class I_35_FunctionalExchangeNameConsistency extends AbstractValidationRule {
  /**
   * @see org.eclipse.emf.validation.AbstractModelConstraint#validate(org.eclipse.emf.validation.IValidationContext)
   */
  @Override
  public IStatus validate(IValidationContext ctx) {
    EObject eObj = ctx.getTarget();

    Deque<FunctionalExchange> toVisit = new ArrayDeque<FunctionalExchange>();
    Set<FunctionalExchange> seen = new HashSet<FunctionalExchange>();
    toVisit.add((FunctionalExchange) eObj);

    while (!toVisit.isEmpty()){
      FunctionalExchange e = toVisit.pop();
      seen.add(e);
      if (e.getSourceFunctionOutputPort() != null){
        for (FunctionalExchange sameOut : e.getSourceFunctionOutputPort().getOutgoingFunctionalExchanges()){
          if (seen.add(sameOut)){
            toVisit.add(sameOut);
          }
        }
      }

      if (e.getTargetFunctionInputPort() != null){
        for (FunctionalExchange sameIn : e.getTargetFunctionInputPort().getIncomingFunctionalExchanges()){
          if (seen.add(sameIn)){
            toVisit.add(sameIn);
          }
        }
      }
    }

    ctx.skipCurrentConstraintForAll(seen);

    Set<String> nameSet = new HashSet<String>();
    List<String> nameList = new ArrayList<String>();
    for (FunctionalExchange fe : seen){
      nameSet.add(fe.getName());
      nameList.add("\"" + EObjectLabelProviderHelper.getText(fe) + "\"");  //$NON-NLS-1$//$NON-NLS-2$
    }

    if (nameSet.size() == 1){
      return ctx.createSuccessStatus();
    }
    ctx.addResults(seen);
    return ctx.createFailureStatus(nameList);
  }

}
