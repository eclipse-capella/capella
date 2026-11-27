/*******************************************************************************
 * Copyright (c) 2013, 2024 Thales LAS France SAS.
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

import java.util.HashMap;
import java.util.Map;

import org.eclipse.core.runtime.IStatus;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.validation.IValidationContext;
import org.polarsys.capella.common.data.activity.ActivityEdge;
import org.polarsys.capella.core.data.fa.AbstractFunction;
import org.polarsys.capella.core.data.fa.FunctionInputPort;
import org.polarsys.capella.core.data.fa.FunctionalExchange;
import org.polarsys.capella.core.validation.rule.AbstractValidationRule;

/**
 * Incoming FunctionalExchanges with same name must use same port.
 */
public class FunctionalExchangesInSameName extends AbstractValidationRule {

  @Override
  public IStatus validate(IValidationContext ctx) {
    EObject eObj = ctx.getTarget();

    if (eObj instanceof AbstractFunction) {
      AbstractFunction fct = (AbstractFunction) eObj;

      final Map<String, FunctionInputPort> otherFEs = new HashMap<String, FunctionInputPort>();

      for (ActivityEdge in : fct.getIncoming()) {
        if (in instanceof FunctionalExchange) {
          FunctionalExchange fe = (FunctionalExchange) in;

          if (otherFEs.containsKey(fe.getName())
              && !otherFEs.get(fe.getName()).equals(fe.getTargetFunctionInputPort())) {
            return ctx.createFailureStatus(new Object[] { fe.getName() });
          }
          otherFEs.put(fe.getName(), fe.getTargetFunctionInputPort());
        }
      }
    }

    return ctx.createSuccessStatus();
  }

}
