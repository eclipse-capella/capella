/*******************************************************************************
 * Copyright (c) 2006, 2017 THALES GLOBAL SERVICES.
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
package org.polarsys.capella.core.data.information.validation.exchangeitem;

import java.util.ArrayList;

import org.eclipse.core.runtime.IStatus;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.validation.EMFEventType;
import org.eclipse.emf.validation.IValidationContext;
import org.eclipse.emf.validation.model.ConstraintStatus;

import org.polarsys.capella.core.data.information.ExchangeItem;
import org.polarsys.capella.core.data.information.ExchangeItemElement;
import org.polarsys.capella.core.data.information.ExchangeMechanism;
import org.polarsys.capella.core.data.information.ParameterDirection;
import org.polarsys.capella.core.validation.rule.AbstractValidationRule;

/**
 * this rule check that for an Exchange Item with none Operation Exchange Mechanism, the inner Exchange Item Element shall NOT have parameter direction set and
 * in case of an Exchange Item Operation Exchange Mechanism, all inner Exchanges Items Elements shall have parameter a direction set
 */
public class CheckExchangeItemElementDirection extends AbstractValidationRule {

  private ArrayList<IStatus> statuses;

  @Override
  public IStatus validate(IValidationContext ctx) {
    EObject eObj = ctx.getTarget();
    EMFEventType eType = ctx.getEventType();
    if (eType == EMFEventType.NULL) {
      // filter ExchangeItem
      if (eObj instanceof ExchangeItemElement) {
        // collection of status message
        statuses = new ArrayList<IStatus>();
        ExchangeItemElement exchangeItemElement = (ExchangeItemElement) eObj;
        ExchangeItem exchangeItemParent = (ExchangeItem) exchangeItemElement.eContainer();
        ExchangeMechanism exchangeMechanism = exchangeItemParent.getExchangeMechanism();
        if ((null != exchangeMechanism) && (exchangeMechanism != ExchangeMechanism.OPERATION)) {
          // other exchange mechanism value check
          validateOtherExchangeMechanism(ctx, exchangeItemParent, exchangeItemElement, exchangeMechanism);
        } else {
          validateOperationExchangeMechanism(ctx, exchangeItemParent, exchangeItemElement, exchangeMechanism);
        }
        if (statuses.size() > 0) {
          return ConstraintStatus.createMultiStatus(ctx, statuses);
        }
      }
    }

    // No conflict found
    return ctx.createSuccessStatus();
  }

  /**
   * this operation check that for an Exchange Item with NOT Operation kind, the inner Exchange Item Element shall NOT have parameter direction
   * @param ctx
   * @param exchangeItemParent
   * @param exchangeItem
   * @param exchangeMechanism
   */
  private void validateOtherExchangeMechanism(IValidationContext ctx, ExchangeItem exchangeItemParent, ExchangeItemElement exchangeItemElement,
      ExchangeMechanism exchangeMechanism) {
    ParameterDirection direction = exchangeItemElement.getDirection();
    if (!direction.equals(ParameterDirection.UNSET)) {
      String EIE_PARAMETER = "\"" + exchangeItemElement.getName() + "\" ( " + exchangeItemElement.eClass().getName() + " ) ";
      String EI_PARAMETER = "\"" + exchangeItemParent.getName() + "\" ( " + exchangeItemParent.eClass().getName() + " ) ";
      IStatus status = ctx.createFailureStatus(new Object[] { EIE_PARAMETER, EI_PARAMETER, exchangeMechanism.getName(), " shall not " });
      statuses.add(status);

    }
  }

  /**
   * this operation check that for Exchange Item with Operation kind, all inner Exchange Item Elements shall have parameter a direction set
   * @param ctx
   * @param exchangeItemParent
   * @param exchangeItem
   * @param exchangeMechanism
   */
  private void validateOperationExchangeMechanism(IValidationContext ctx, ExchangeItem exchangeItemParent, ExchangeItemElement exchangeItemElement,
      ExchangeMechanism exchangeMechanism) {
    ParameterDirection direction = exchangeItemElement.getDirection();
    if ((direction == null) || direction.equals(ParameterDirection.UNSET)) {
      String EIE_PARAMETER = "\"" + exchangeItemElement.getName() + "\" ( " + exchangeItemElement.eClass().getName() + " ) ";
      String EI_PARAMETER = "\"" + exchangeItemParent.getName() + "\" ( " + exchangeItemParent.eClass().getName() + " ) ";
      IStatus status = ctx.createFailureStatus(new Object[] { EIE_PARAMETER, EI_PARAMETER, exchangeMechanism.getName(), " shall " });
      statuses.add(status);
    }

  }

}
