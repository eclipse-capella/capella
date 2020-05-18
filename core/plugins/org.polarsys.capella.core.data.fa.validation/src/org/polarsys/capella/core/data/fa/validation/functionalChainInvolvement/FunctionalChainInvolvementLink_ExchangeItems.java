/*******************************************************************************
 * Copyright (c) 2006, 2019 THALES GLOBAL SERVICES.
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
package org.polarsys.capella.core.data.fa.validation.functionalChainInvolvement;

import java.util.Collection;
import java.util.stream.Collectors;

import org.eclipse.core.runtime.IStatus;
import org.eclipse.emf.validation.EMFEventType;
import org.eclipse.emf.validation.IValidationContext;
import org.polarsys.capella.common.data.modellingcore.AbstractExchangeItem;
import org.polarsys.capella.common.helpers.EObjectLabelProviderHelper;
import org.polarsys.capella.common.mdsofa.common.constant.ICommonConstants;
import org.polarsys.capella.core.data.fa.FunctionalChainInvolvementLink;
import org.polarsys.capella.core.model.helpers.FunctionalChainExt;
import org.polarsys.capella.core.validation.rule.AbstractValidationRule;

public class FunctionalChainInvolvementLink_ExchangeItems extends AbstractValidationRule {

  /**
   * Text separator for invalid exchange items.
   */
  private static final String EXCHANGE_ITEMS_NAMES_LIST_SEPARATOR = new String(
      new char[] { ICommonConstants.COMMA_CHARACTER + ICommonConstants.WHITE_SPACE_CHARACTER });

  /**
   * @see org.eclipse.emf.validation.AbstractModelConstraint#validate(org.eclipse.emf.validation.IValidationContext)
   */
  @Override
  public IStatus validate(IValidationContext ctx) {

    if (ctx.getEventType() == EMFEventType.NULL && ctx.getTarget() instanceof FunctionalChainInvolvementLink) {
      FunctionalChainInvolvementLink link = (FunctionalChainInvolvementLink) ctx.getTarget();
      Collection<AbstractExchangeItem> invalidExchangeItems = FunctionalChainExt.getInvalidExchangeItems(link);
      int invalidItemsSize = invalidExchangeItems.size();

      if (invalidItemsSize > 0) {
        String invalidExchageItemsLabel = invalidExchangeItems.stream().map(AbstractExchangeItem::getName)
            .collect(Collectors.joining(EXCHANGE_ITEMS_NAMES_LIST_SEPARATOR));
        String suffix = invalidItemsSize > 1 ? "are" : "is";

        return ctx.createFailureStatus(EObjectLabelProviderHelper.getText(link), invalidExchageItemsLabel, suffix);
      }
    }

    return ctx.createSuccessStatus();
  }
}
