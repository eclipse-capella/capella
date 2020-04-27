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
package org.polarsys.capella.core.data.interaction.validation.sequenceMessage;

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
import org.polarsys.capella.core.data.interaction.MessageKind;
import org.polarsys.capella.core.data.interaction.SequenceMessage;
import org.polarsys.capella.core.model.helpers.SequenceMessageExt;
import org.polarsys.capella.core.validation.rule.AbstractValidationRule;

/**
 * Check if ExchangeItems referenced by a SequenceMessage are amongst ExchangeItems of its invoked operation.
 */
public class SequenceMessageExchangeItems extends AbstractValidationRule {

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
    if (!(eObj instanceof SequenceMessage)) {
      return ctx_p.createSuccessStatus();
    }
    SequenceMessage element = (SequenceMessage) eObj;
    // Ignore REPLY SequenceMessages.
    if (element.getKind() == MessageKind.REPLY) {
      return ctx_p.createSuccessStatus();
    }
    if (null == element.getInvokedOperation()) {
      // null value for invokedOperation is tested in another validation rule.
      return ctx_p.createSuccessStatus();
    }

    // Collect invalid ExchangeItems (ExchangeItems referenced by the SequenceMessage but not associated with the invoked operation).
    Collection<AbstractExchangeItem> invalidExchangeItems = SequenceMessageExt.getInvalidExchangeItems(element);

    // Some exchange items are invalid, raise a failure status.
    if (!invalidExchangeItems.isEmpty()) {
      List<String> invalidExchangeItemsNames = new ArrayList<String>(invalidExchangeItems.size());
      for (AbstractExchangeItem abstractExchangeItem : invalidExchangeItems) {
        invalidExchangeItemsNames.add(abstractExchangeItem.getName());
      }
      return ctx_p.createFailureStatus(element.getName(), StringUtils.join(invalidExchangeItemsNames, EXCHANGE_ITEMS_NAMES_LIST_SEPARATOR),
          invalidExchangeItemsNames.size() > 1 ? "are" : "is"); //$NON-NLS-1$//$NON-NLS-2$
    }

    return ctx_p.createSuccessStatus();
  }
}
