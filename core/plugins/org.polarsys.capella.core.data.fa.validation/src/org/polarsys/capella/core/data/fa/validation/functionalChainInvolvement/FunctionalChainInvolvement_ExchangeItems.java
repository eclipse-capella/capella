/*******************************************************************************
 * Copyright (c) 2006, 2017 THALES GLOBAL SERVICES.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *  
 * Contributors:
 *    Thales - initial API and implementation
 *******************************************************************************/
package org.polarsys.capella.core.data.fa.validation.functionalChainInvolvement;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import org.apache.commons.lang.StringUtils;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.validation.EMFEventType;
import org.eclipse.emf.validation.IValidationContext;
import org.polarsys.capella.common.data.modellingcore.AbstractExchangeItem;
import org.polarsys.capella.common.helpers.EObjectLabelProviderHelper;
import org.polarsys.capella.common.mdsofa.common.constant.ICommonConstants;
import org.polarsys.capella.core.data.fa.FunctionalChainInvolvement;
import org.polarsys.capella.core.data.fa.FunctionalExchange;
import org.polarsys.capella.core.model.helpers.FunctionalChainExt;
import org.polarsys.capella.core.validation.rule.AbstractValidationRule;

public class FunctionalChainInvolvement_ExchangeItems extends AbstractValidationRule {

  /**
   * Text separator for invalid exchange items.
   */
  private static final String EXCHANGE_ITEMS_NAMES_LIST_SEPARATOR = new String(new char[] { ICommonConstants.COMMA_CHARACTER
                                                                                            + ICommonConstants.WHITE_SPACE_CHARACTER });

  /**
   * @see org.eclipse.emf.validation.AbstractModelConstraint#validate(org.eclipse.emf.validation.IValidationContext)
   */
  @Override
  public IStatus validate(IValidationContext ctx) {
    //
    // Preconditions.
    //
    EMFEventType eType = ctx.getEventType();
    if (EMFEventType.NULL != eType) {
      return ctx.createSuccessStatus();
    }
    EObject eObj = ctx.getTarget();
    if (!(eObj instanceof FunctionalChainInvolvement)) {
      return ctx.createSuccessStatus();
    }
    FunctionalChainInvolvement element = (FunctionalChainInvolvement) eObj;

    if (!(element.getInvolved() instanceof FunctionalExchange)) {
      return ctx.createSuccessStatus();
    }

    // Collect invalid ExchangeItems (ExchangeItems referenced by the SequenceMessage but not associated with the invoked operation).
    Collection<AbstractExchangeItem> invalidExchangeItems = FunctionalChainExt.getInvalidExchangeItems(element);

    // Some exchange items are invalid, raise a failure status.
    if (!invalidExchangeItems.isEmpty()) {
      List<String> invalidExchangeItemsNames = new ArrayList<String>(invalidExchangeItems.size());
      for (AbstractExchangeItem abstractExchangeItem : invalidExchangeItems) {
        invalidExchangeItemsNames.add(abstractExchangeItem.getName());
      }
      return ctx.createFailureStatus(EObjectLabelProviderHelper.getText(element),
          StringUtils.join(invalidExchangeItemsNames, EXCHANGE_ITEMS_NAMES_LIST_SEPARATOR), invalidExchangeItemsNames.size() > 1 ? "are" : "is"); //$NON-NLS-1$//$NON-NLS-2$
    }

    return ctx.createSuccessStatus();
  }
}
