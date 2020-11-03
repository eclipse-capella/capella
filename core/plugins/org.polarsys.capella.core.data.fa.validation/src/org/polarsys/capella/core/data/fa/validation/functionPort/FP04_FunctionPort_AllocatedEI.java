/*******************************************************************************
 * Copyright (c) 2006, 2020 THALES GLOBAL SERVICES.
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
package org.polarsys.capella.core.data.fa.validation.functionPort;

import org.eclipse.core.runtime.IStatus;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.validation.EMFEventType;
import org.eclipse.emf.validation.IValidationContext;

import org.polarsys.capella.core.data.fa.FunctionInputPort;
import org.polarsys.capella.core.data.fa.FunctionOutputPort;
import org.polarsys.capella.core.data.helpers.fa.services.FunctionPortExt;
import org.polarsys.capella.core.data.information.ExchangeItem;
import org.polarsys.capella.core.data.information.ExchangeItemRealization;
import org.polarsys.capella.core.data.information.InformationRealization;
import org.polarsys.capella.core.data.information.Port;
import org.polarsys.capella.core.validation.rule.AbstractValidationRule;
import org.polarsys.capella.common.data.modellingcore.AbstractTrace;

/**
 */
public class FP04_FunctionPort_AllocatedEI extends AbstractValidationRule {
  /**
   * @see org.eclipse.emf.validation.AbstractModelConstraint#validate(org.eclipse.emf.validation.IValidationContext)
   */
  @Override
  public IStatus validate(IValidationContext ctx) {
    EObject eObj = ctx.getTarget();
    EMFEventType eType = ctx.getEventType();
    if (eType == EMFEventType.NULL) {
      if (eObj instanceof FunctionInputPort) {
        for (Port fip : FunctionPortExt.getRealizingPorts((FunctionInputPort) eObj)) {
          if (fip instanceof FunctionInputPort) {
            if (!haveCommonEI((FunctionInputPort) eObj, (FunctionInputPort) fip)) {
              return ctx.createFailureStatus(new Object[] { ((FunctionInputPort) eObj).getName() });
            }
          }
        }
      } else if (eObj instanceof FunctionOutputPort) {
        for (Port fop : FunctionPortExt.getRealizingPorts((FunctionOutputPort) eObj)) {
          if (fop instanceof FunctionOutputPort) {
            if (!haveCommonEI((FunctionOutputPort) eObj, (FunctionOutputPort) fop)) {
              return ctx.createFailureStatus(new Object[] { ((FunctionOutputPort) eObj).getName() });
            }
          }
        }
      }
    }
    return ctx.createSuccessStatus();
  }

  protected boolean haveCommonEI(FunctionInputPort fip1, FunctionInputPort fip2) {
    for (ExchangeItem item : fip1.getIncomingExchangeItems()) {
      if (!fip2.getIncomingExchangeItems().contains(item)) {
        boolean found = false;
        for (ExchangeItem ei : fip2.getIncomingExchangeItems()) {
          for (AbstractTrace trace : ei.getOutgoingTraces()) {
            if (((trace instanceof ExchangeItemRealization) && item.equals(((ExchangeItemRealization) trace).getRealizedItem()))
             || ((trace instanceof InformationRealization) && item.equals(((InformationRealization) trace).getTargetElement())))
            {
              found = true;
            }
          }
        }
        if (!found) {
          return false;
        }
      }
    }
    return true;
  }

  protected boolean haveCommonEI(FunctionOutputPort fop1, FunctionOutputPort fop2) {
    for (ExchangeItem item : fop1.getOutgoingExchangeItems()) {
      if (!fop2.getOutgoingExchangeItems().contains(item)) {
        boolean found = false;
        for (ExchangeItem ei : fop2.getOutgoingExchangeItems()) {
          for (AbstractTrace trace : ei.getOutgoingTraces()) {
            if (((trace instanceof ExchangeItemRealization) && item.equals(((ExchangeItemRealization) trace).getRealizedItem()))
             || ((trace instanceof InformationRealization) && item.equals(((InformationRealization) trace).getTargetElement())))
            {
              found = true;
            }
          }
        }
        if (!found) {
          return false;
        }
      }
    }
    return true;
  }
}
