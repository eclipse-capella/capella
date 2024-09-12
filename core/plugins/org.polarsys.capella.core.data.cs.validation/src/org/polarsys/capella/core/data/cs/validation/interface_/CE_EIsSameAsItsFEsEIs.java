/*******************************************************************************
 * Copyright (c) 2016, 2024 Thales LAS France SA.
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
package org.polarsys.capella.core.data.cs.validation.interface_;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashSet;
import java.util.Set;

import org.eclipse.core.runtime.IStatus;
import org.eclipse.emf.validation.IValidationContext;
import org.eclipse.emf.validation.model.ConstraintStatus;
import org.polarsys.capella.common.data.modellingcore.AbstractExchangeItem;
import org.polarsys.capella.core.data.fa.ComponentExchange;
import org.polarsys.capella.core.data.fa.FunctionalExchange;
import org.polarsys.capella.core.data.information.ExchangeItem;
import org.polarsys.capella.core.validation.rule.AbstractValidationRuleOnSystemAnalysis;

/**
 * The CE EI shall be the union of the EI of the FE allocated to the CE
 */
public class CE_EIsSameAsItsFEsEIs extends AbstractValidationRuleOnSystemAnalysis {

  @Override
  public IStatus validateOnSA(final IValidationContext ctx) {
    IStatus resultStatus = null;

    if (ctx.getTarget() instanceof ComponentExchange) {
      final ComponentExchange targetCE = (ComponentExchange) ctx.getTarget();

      final Set<ExchangeItem> collOfExchangeItemsFromCE = new HashSet<ExchangeItem>(
          getExchangeItemsFromComponentExchange(targetCE));
      final Set<ExchangeItem> collOfExchangeItemsViaFE = getExchangeItemsFromCE_FE(targetCE);

      if (collOfExchangeItemsFromCE.equals(collOfExchangeItemsViaFE)) {
        resultStatus = ctx.createSuccessStatus();
      } else {
        final Collection<IStatus> statuses = new ArrayList<IStatus>();

        // report differences : EIs present in CE but not in FEs
        {
          final Set<ExchangeItem> setOfExchangeItemsInCEOnly = new HashSet<ExchangeItem>(collOfExchangeItemsFromCE);
          setOfExchangeItemsInCEOnly.removeAll(collOfExchangeItemsViaFE);
          for (final ExchangeItem currExchangeItem : setOfExchangeItemsInCEOnly) {
            statuses.add(ctx.createFailureStatus(
                new Object[] { targetCE.getName(), currExchangeItem.getName(), "ComponentExchange" }));
          }
        }

        // report differences : EIs present in FEs but not in CE
        {
          final Set<ExchangeItem> setOfExchangeItemsInFEOnly = new HashSet<ExchangeItem>(collOfExchangeItemsViaFE);
          setOfExchangeItemsInFEOnly.removeAll(collOfExchangeItemsFromCE);
          for (final ExchangeItem currExchangeItem : setOfExchangeItemsInFEOnly) {
            statuses.add(ctx.createFailureStatus(
                new Object[] { targetCE.getName(), currExchangeItem.getName(), "FunctionalExchange" }));
          }
        }

        resultStatus = ConstraintStatus.createMultiStatus(ctx, statuses);
      }

    } else {
      resultStatus = ctx.createSuccessStatus();
    }

    return resultStatus;
  }

  protected static Collection<ExchangeItem> getExchangeItemsFromComponentExchange(final ComponentExchange currentCE) {
    final Collection<AbstractExchangeItem> currentCEConvoyedAbstractExchangeItems = currentCE.getConvoyedInformations();
    final Collection<ExchangeItem> resultCollOfExchangeItems = new ArrayList<ExchangeItem>(
        currentCEConvoyedAbstractExchangeItems.size());

    for (final AbstractExchangeItem currentAbstractExchangeItem : currentCEConvoyedAbstractExchangeItems) {
      if (currentAbstractExchangeItem instanceof ExchangeItem) {
        resultCollOfExchangeItems.add((ExchangeItem) currentAbstractExchangeItem);
      }
    }

    return resultCollOfExchangeItems;
  }

  private static Set<ExchangeItem> getExchangeItemsFromCE_FE(final ComponentExchange inputCE) {
    final Set<ExchangeItem> resultSetOfExchangeItems = new HashSet<ExchangeItem>();

    for (final FunctionalExchange currentFE : inputCE.getAllocatedFunctionalExchanges()) {
      resultSetOfExchangeItems.addAll(currentFE.getExchangedItems());
    }

    return resultSetOfExchangeItems;
  }

}
