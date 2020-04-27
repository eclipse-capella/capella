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
package org.polarsys.capella.core.data.fa.validation.functionalExchange;

import java.util.List;

import org.eclipse.core.runtime.IStatus;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.validation.EMFEventType;
import org.eclipse.emf.validation.IValidationContext;
import org.polarsys.capella.common.data.activity.ActivityNode;
import org.polarsys.capella.common.data.modellingcore.AbstractExchangeItem;
import org.polarsys.capella.common.helpers.EObjectLabelProviderHelper;
import org.polarsys.capella.common.mdsofa.common.constant.ICommonConstants;
import org.polarsys.capella.core.data.fa.FunctionInputPort;
import org.polarsys.capella.core.data.fa.FunctionOutputPort;
import org.polarsys.capella.core.data.fa.FunctionalExchange;
import org.polarsys.capella.core.data.information.ExchangeItem;
import org.polarsys.capella.core.validation.rule.AbstractValidationRule;

/**
 * This rule ensures that the target flow port is used by a functional exchange
 */
public class FunctionalExchange_exchangeItemsOnFunctionPorts extends AbstractValidationRule {

  /**
   * @see org.eclipse.emf.validation.AbstractModelConstraint#validate(org.eclipse.emf.validation.IValidationContext)
   */
  @Override
  public IStatus validate(IValidationContext ctx) {
    // Raise a warning if one of exchange item convoyed by related functional exchanges are not convoyed by the port
    EObject eObj = ctx.getTarget();
    EMFEventType eType = ctx.getEventType();

    if (eType == EMFEventType.NULL && eObj instanceof FunctionalExchange) {
      String resultSource = ICommonConstants.EMPTY_STRING;
      String resultTarget = ICommonConstants.EMPTY_STRING;
      List<ExchangeItem> eisSource = null;
      List<ExchangeItem> eisTarget = null;
      FunctionalExchange fe = (FunctionalExchange) eObj;
      ActivityNode source = fe.getSource();
      ActivityNode target = fe.getTarget();

      
      if (target instanceof FunctionInputPort) {
        eisTarget = ((FunctionInputPort) target).getIncomingExchangeItems();
      }

      if (source instanceof FunctionOutputPort) {
        eisSource = ((FunctionOutputPort) source).getOutgoingExchangeItems();
      }
      
      if (null != eisTarget && null != eisSource) {
        for (AbstractExchangeItem item : fe.getExchangedItems()) {
          if (!eisSource.contains(item)) {
            resultSource =
                (resultSource.length() > 0 ? ICommonConstants.EMPTY_STRING + ICommonConstants.COMMA_CHARACTER + ICommonConstants.WHITE_SPACE_CHARACTER
                                          : ICommonConstants.EMPTY_STRING) + item.getName();
          }
          if (!eisTarget.contains(item)) {
            resultTarget =
                (resultTarget.length() > 0 ? ICommonConstants.EMPTY_STRING + ICommonConstants.COMMA_CHARACTER + ICommonConstants.WHITE_SPACE_CHARACTER
                                          : ICommonConstants.EMPTY_STRING) + item.getName();
          }
        }

        if (resultSource.length() > 0 || resultTarget.length() > 0) {
          if (resultSource.length() == 0) {
            resultSource = "all are allocated"; //$NON-NLS-1$
          }
          if (resultTarget.length() == 0) {
            resultTarget = Messages.exchangeItemsOnFunctionPorts_allocated;
          }

          return ctx.createFailureStatus(EObjectLabelProviderHelper.getText(fe), resultSource, resultTarget);
        }
      }
    }
    return ctx.createSuccessStatus();
  }
}
