/*******************************************************************************
 * Copyright (c) 2006, 2014 THALES GLOBAL SERVICES.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
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
  public IStatus validate(IValidationContext ctx_p) {
    EObject eObj = ctx_p.getTarget();
    EMFEventType eType = ctx_p.getEventType();
    if (eType == EMFEventType.NULL) {
      if (eObj instanceof FunctionInputPort) {
        for (Port fip : FunctionPortExt.getRealizingPorts((FunctionInputPort) eObj)) {
          if (fip instanceof FunctionInputPort) {
            if (!haveCommonEI((FunctionInputPort) eObj, (FunctionInputPort) fip)) {
              return ctx_p.createFailureStatus(new Object[] { ((FunctionInputPort) eObj).getName() });
            }
          }
        }
      } else if (eObj instanceof FunctionOutputPort) {
        for (Port fop : FunctionPortExt.getRealizingPorts((FunctionOutputPort) eObj)) {
          if (fop instanceof FunctionOutputPort) {
            if (!haveCommonEI((FunctionOutputPort) eObj, (FunctionOutputPort) fop)) {
              return ctx_p.createFailureStatus(new Object[] { ((FunctionOutputPort) eObj).getName() });
            }
          }
        }
      }
    }
    return ctx_p.createSuccessStatus();
  }

  protected boolean haveCommonEI(FunctionInputPort fip1_p, FunctionInputPort fip2_p) {
    for (ExchangeItem item : fip1_p.getIncomingExchangeItems()) {
      if (!fip2_p.getIncomingExchangeItems().contains(item)) {
        boolean found = false;
        for (ExchangeItem ei : fip2_p.getIncomingExchangeItems()) {
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

  protected boolean haveCommonEI(FunctionOutputPort fop1_p, FunctionOutputPort fop2_p) {
    for (ExchangeItem item : fop1_p.getOutgoingExchangeItems()) {
      if (!fop2_p.getOutgoingExchangeItems().contains(item)) {
        boolean found = false;
        for (ExchangeItem ei : fop2_p.getOutgoingExchangeItems()) {
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
