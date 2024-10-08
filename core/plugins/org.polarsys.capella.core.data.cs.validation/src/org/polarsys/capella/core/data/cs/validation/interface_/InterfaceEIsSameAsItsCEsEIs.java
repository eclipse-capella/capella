/*******************************************************************************
 * Copyright (c) 2016, 2024 Thales LAS France SAS.
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
import org.polarsys.capella.core.data.cs.Interface;
import org.polarsys.capella.core.data.fa.ComponentExchange;
import org.polarsys.capella.core.data.fa.ComponentPort;
import org.polarsys.capella.core.data.information.ExchangeItem;
import org.polarsys.capella.core.validation.rule.AbstractValidationRuleOnSystemAnalysis;

/**
 * The interface EI shall be identical at SA to the CE EI allocated.
 */
public class InterfaceEIsSameAsItsCEsEIs extends AbstractValidationRuleOnSystemAnalysis {

  @Override
  public IStatus validateOnSA(final IValidationContext ctx) {
    IStatus resultStatus = null;

    if (ctx.getTarget() instanceof Interface) {
      final Interface ctxInterface = (Interface) ctx.getTarget();

      final Set<ExchangeItem> collOfExchangeItemsFromI = new HashSet<ExchangeItem>(ctxInterface.getExchangeItems());
      final Set<ExchangeItem> collOfExchangeItemsViaCE = getExchangeItemsFromInterface_ComponentPorts_ComponentExchanges(
          ctxInterface);

      if (collOfExchangeItemsFromI.equals(collOfExchangeItemsViaCE)) {
        resultStatus = ctx.createSuccessStatus();
      } else {
        final Collection<IStatus> statuses = new ArrayList<IStatus>();

        // report differences : EIs present in Interface but not in CEs
        {
          final Set<ExchangeItem> setOfExchangeItemInInterfaceOnly = new HashSet<ExchangeItem>(
              collOfExchangeItemsFromI);
          setOfExchangeItemInInterfaceOnly.removeAll(collOfExchangeItemsViaCE);
          for (final ExchangeItem currExchangeItem : setOfExchangeItemInInterfaceOnly) {
            statuses.add(ctx
                .createFailureStatus(new Object[] { ctxInterface.getName(), currExchangeItem.getName(), "Interface" }));
          }
        }

        // report differences : EIs present in CEs but not in Interface
        {
          final Set<ExchangeItem> setOfExchangeItemInComponentExchangeOnly = new HashSet<ExchangeItem>(
              collOfExchangeItemsViaCE);
          setOfExchangeItemInComponentExchangeOnly.removeAll(collOfExchangeItemsFromI);
          for (final ExchangeItem currExchangeItem : setOfExchangeItemInComponentExchangeOnly) {
            statuses.add(ctx.createFailureStatus(
                new Object[] { ctxInterface.getName(), currExchangeItem.getName(), "ComponentExchange" }));
          }
        }

        resultStatus = ConstraintStatus.createMultiStatus(ctx, statuses);
      }

    } else {
      resultStatus = ctx.createSuccessStatus();
    }

    return resultStatus;
  }

  private static Set<ExchangeItem> getExchangeItemsFromInterface_ComponentPorts_ComponentExchanges(
      final Interface inputInterface) {
    final Set<ExchangeItem> resultSetOfExchangeItems = new HashSet<ExchangeItem>();

    for (final ComponentPort currentComponentPort : inputInterface.getProvidingComponentPorts()) {
      for (final ComponentExchange currentComponentExchange : currentComponentPort.getComponentExchanges()) {
        resultSetOfExchangeItems
            .addAll(CE_EIsSameAsItsFEsEIs.getExchangeItemsFromComponentExchange(currentComponentExchange));
      }
    }

    return resultSetOfExchangeItems;
  }

}
