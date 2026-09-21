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
package org.polarsys.capella.core.data.fa.validation.ce;

import org.eclipse.core.runtime.IStatus;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.validation.IValidationContext;
import org.polarsys.capella.core.data.fa.AbstractFunction;
import org.polarsys.capella.core.data.fa.FunctionInputPort;
import org.polarsys.capella.core.data.fa.FunctionOutputPort;
import org.polarsys.capella.core.data.fa.FunctionalExchange;
import org.polarsys.capella.core.validation.rule.AbstractValidationRule;

/**
 * This class implements the rule that functional exchanges must have one or more exchange items, except when connected
 * to an actor. I.e. FunctionalExchange not connected to an actor should have one or more Exchange Items.
 */
public class FunctionalExchangeOneOrMoreExchangedItems extends AbstractValidationRule {

  @Override
  public IStatus validate(IValidationContext ctx) {
    EObject eObj = ctx.getTarget();

    if (eObj instanceof FunctionalExchange) {
      FunctionalExchange fe = (FunctionalExchange) eObj;
      // if it has not one or more exchange items
      if (fe.getExchangedItems().size() == 0) {
        if (fe.getSource() instanceof FunctionOutputPort && fe.getTarget() instanceof FunctionInputPort) {
          final FunctionOutputPort outPort = (FunctionOutputPort) fe.getSource();
          final FunctionInputPort inPort = (FunctionInputPort) fe.getTarget();

          if (inPort.eContainer() instanceof AbstractFunction && outPort.eContainer() instanceof AbstractFunction) {

            if (fe.getAllocatingComponentExchanges().size() > 0) {
              return ctx.createFailureStatus(new Object[] { fe.getName() });
            }
          }
        }
      }
    }

    return ctx.createSuccessStatus();
  }

}
