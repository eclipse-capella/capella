/*******************************************************************************
 * Copyright (c) 2013, 2024 Thales LAS France SA.
 * Copyright (c) 2024, 2024 THALES GLOBAL SERVICES.
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
import org.polarsys.capella.core.data.fa.FunctionOutputPort;
import org.polarsys.capella.core.data.fa.FunctionalExchange;
import org.polarsys.capella.core.validation.rule.AbstractValidationRule;

/**
 * Outgoing FunctionalExchanges with same name must use same port.
 */
public class FunctionalExchangesOutSameName extends AbstractValidationRule {

  @Override
  public IStatus validate(final IValidationContext ctx) {
    final EObject eObj = ctx.getTarget();

    if (eObj instanceof AbstractFunction) {
      final AbstractFunction fct = (AbstractFunction) eObj;

      final Map<String, FunctionOutputPort> otherFEs = new HashMap<String, FunctionOutputPort>();

      for (final ActivityEdge out : fct.getOutgoing()) {
        if (out instanceof FunctionalExchange) {
          final FunctionalExchange fe = (FunctionalExchange) out;

          if (otherFEs.containsKey(fe.getName())
              && !otherFEs.get(fe.getName()).equals(fe.getSourceFunctionOutputPort())) {
            return ctx.createFailureStatus(new Object[] { fe.getName() });
          }
          otherFEs.put(fe.getName(), fe.getSourceFunctionOutputPort());
        }
      }
    }

    return ctx.createSuccessStatus();
  }
}
