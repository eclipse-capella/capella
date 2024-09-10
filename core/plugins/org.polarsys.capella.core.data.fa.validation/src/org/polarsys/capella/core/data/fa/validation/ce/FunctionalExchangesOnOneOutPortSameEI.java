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
package org.polarsys.capella.core.data.fa.validation.ce;

import java.util.ArrayList;
import java.util.List;

import org.eclipse.core.runtime.IStatus;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.validation.IValidationContext;
import org.polarsys.capella.core.data.fa.FunctionOutputPort;
import org.polarsys.capella.core.data.fa.FunctionalExchange;
import org.polarsys.capella.core.data.information.ExchangeItem;
import org.polarsys.capella.core.validation.rule.AbstractValidationRule;

/**
 * Functional exchanges on the same output port must have the same EI. On the checked FE, signals the EI on other FE
 * that are on the same port.
 */
public class FunctionalExchangesOnOneOutPortSameEI extends AbstractValidationRule {

  @Override
  public IStatus validate(IValidationContext ctx) {
    EObject eObj = ctx.getTarget();

    if (eObj instanceof FunctionalExchange) {
      FunctionalExchange fe = (FunctionalExchange) eObj;

      if (fe.getSource() instanceof FunctionOutputPort) {
        final FunctionOutputPort inPort = (FunctionOutputPort) fe.getSource();

        for (FunctionalExchange otherFe : inPort.getOutgoingFunctionalExchanges()) {
          final List<ExchangeItem> checkedFeEi = new ArrayList<ExchangeItem>(fe.getExchangedItems());
          final List<ExchangeItem> otherFeEi = new ArrayList<ExchangeItem>(otherFe.getExchangedItems());
          final List<ExchangeItem> otherFeEiNotInCheckedEiList = FunctionalExchangesOnOneOutPortSameEI
              .removeEiRightListInLeftList(otherFeEi, checkedFeEi);

          if (otherFeEiNotInCheckedEiList.size() > 0) {
            return ctx.createFailureStatus(
                new Object[] { fe.getName(), otherFe.getName(), otherFeEiNotInCheckedEiList.get(0) });
          }
        }
      }
    }

    return ctx.createSuccessStatus();
  }

  private static List<ExchangeItem> removeEiRightListInLeftList(final List<ExchangeItem> feEiLeft,
      final List<ExchangeItem> feEiRight) {
    for (ExchangeItem ei : feEiRight) {
      if (feEiLeft.contains(ei)) {
        feEiLeft.remove(ei);
      }
    }

    return feEiLeft;
  }
}
