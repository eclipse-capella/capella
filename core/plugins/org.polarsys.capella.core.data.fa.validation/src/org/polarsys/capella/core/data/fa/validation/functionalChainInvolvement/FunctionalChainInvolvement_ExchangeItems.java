/**
 * Copyright (c) THALES, 2011. All rights reserved.
 */
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
import org.polarsys.capella.common.mdsofa.common.constant.ICommonConstants;
import org.polarsys.capella.common.ui.services.helper.EObjectLabelProviderHelper;
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
  public IStatus validate(IValidationContext ctx_p) {
    //
    // Preconditions.
    //
    EMFEventType eType = ctx_p.getEventType();
    if (EMFEventType.NULL != eType) {
      return ctx_p.createSuccessStatus();
    }
    EObject eObj = ctx_p.getTarget();
    if (!(eObj instanceof FunctionalChainInvolvement)) {
      return ctx_p.createSuccessStatus();
    }
    FunctionalChainInvolvement element = (FunctionalChainInvolvement) eObj;

    if (!(element.getInvolved() instanceof FunctionalExchange)) {
      return ctx_p.createSuccessStatus();
    }

    // Collect invalid ExchangeItems (ExchangeItems referenced by the SequenceMessage but not associated with the invoked operation).
    Collection<AbstractExchangeItem> invalidExchangeItems = FunctionalChainExt.getInvalidExchangeItems(element);

    // Some exchange items are invalid, raise a failure status.
    if (!invalidExchangeItems.isEmpty()) {
      List<String> invalidExchangeItemsNames = new ArrayList<String>(invalidExchangeItems.size());
      for (AbstractExchangeItem abstractExchangeItem : invalidExchangeItems) {
        invalidExchangeItemsNames.add(abstractExchangeItem.getName());
      }
      return ctx_p.createFailureStatus(EObjectLabelProviderHelper.getText(element),
          StringUtils.join(invalidExchangeItemsNames, EXCHANGE_ITEMS_NAMES_LIST_SEPARATOR), invalidExchangeItemsNames.size() > 1 ? "are" : "is"); //$NON-NLS-1$//$NON-NLS-2$
    }

    return ctx_p.createSuccessStatus();
  }
}
