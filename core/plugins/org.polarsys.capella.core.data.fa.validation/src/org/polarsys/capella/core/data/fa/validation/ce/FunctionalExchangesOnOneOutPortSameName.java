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

import java.util.ArrayList;
import java.util.List;

import org.eclipse.core.runtime.IStatus;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.validation.IValidationContext;
import org.polarsys.capella.core.data.fa.FunctionOutputPort;
import org.polarsys.capella.core.data.fa.FunctionalExchange;
import org.polarsys.capella.core.validation.rule.AbstractValidationRule;

/**
 * Functional exchanges on the same output port must have the same name.
 */
public class FunctionalExchangesOnOneOutPortSameName extends AbstractValidationRule {

  @Override
  public IStatus validate(IValidationContext ctx) {
    EObject eObj = ctx.getTarget();

    if (eObj instanceof FunctionalExchange) {
      FunctionalExchange fe = (FunctionalExchange) eObj;

      if (fe.getSource() instanceof FunctionOutputPort) {
        final FunctionOutputPort outPort = (FunctionOutputPort) fe.getSource();

        final List<String> otherFENamesDifferent = new ArrayList<String>();

        for (FunctionalExchange otherFE : outPort.getOutgoingFunctionalExchanges()) {
          if (!fe.getName().equals(otherFE.getName())) {
            otherFENamesDifferent.add(otherFE.getName());
          }
        }

        if (otherFENamesDifferent.size() > 0) {
          return ctx.createFailureStatus(new Object[] { fe.getName(), otherFENamesDifferent.get(0) });
        }
      }
    }

    return ctx.createSuccessStatus();
  }
}
