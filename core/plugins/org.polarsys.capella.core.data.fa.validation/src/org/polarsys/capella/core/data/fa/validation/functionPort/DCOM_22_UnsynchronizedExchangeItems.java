/*******************************************************************************
 * Copyright (c) 2017 THALES GLOBAL SERVICES.
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

import java.util.Collection;
import java.util.Collections;
import java.util.HashSet;

import org.eclipse.core.runtime.IStatus;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.validation.IValidationContext;
import org.polarsys.capella.common.helpers.EObjectLabelProviderHelper;
import org.polarsys.capella.core.data.fa.FunctionInputPort;
import org.polarsys.capella.core.data.fa.FunctionOutputPort;
import org.polarsys.capella.core.data.fa.FunctionalExchange;
import org.polarsys.capella.core.data.information.ExchangeItem;
import org.polarsys.capella.core.validation.rule.AbstractValidationRule;

/**
 * This rule ensures that a function port and all its connected functional
 * exchanges have exactly the same exchange items.
 */
public class DCOM_22_UnsynchronizedExchangeItems extends AbstractValidationRule {
  /**
   * @see org.eclipse.emf.validation.AbstractModelConstraint#validate(org.eclipse.emf.validation.IValidationContext)
   */
  @Override
  public IStatus validate(IValidationContext ctx) {
    EObject eObj = ctx.getTarget();

    Collection<ExchangeItem> portEIs = new HashSet<ExchangeItem>();
    Collection<FunctionalExchange> allFEs = Collections.emptyList();
    
    if (eObj instanceof FunctionInputPort) {
      portEIs.addAll(((FunctionInputPort) eObj).getIncomingExchangeItems());
      allFEs = ((FunctionInputPort) eObj).getIncomingFunctionalExchanges();
    } else if (eObj instanceof FunctionOutputPort) {
      portEIs.addAll(((FunctionOutputPort) eObj).getOutgoingExchangeItems());
      allFEs = ((FunctionOutputPort) eObj).getOutgoingFunctionalExchanges();
    }

    for (FunctionalExchange fe : allFEs) {
        if (!portEIs.containsAll(fe.getExchangedItems())){
          return ctx.createFailureStatus(eObj, EObjectLabelProviderHelper.getMetaclassLabel(eObj, false));
        }
        if (!fe.getExchangedItems().containsAll(portEIs)){
          return ctx.createFailureStatus(eObj, EObjectLabelProviderHelper.getMetaclassLabel(eObj, false));
        }
    }

    return ctx.createSuccessStatus();

  }
}
