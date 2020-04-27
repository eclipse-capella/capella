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

import static org.polarsys.capella.common.helpers.EObjectLabelProviderHelper.getMetaclassLabel;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;

import org.eclipse.core.runtime.IStatus;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.validation.IValidationContext;
import org.eclipse.emf.validation.model.ConstraintStatus;
import org.polarsys.capella.core.data.fa.FunctionInputPort;
import org.polarsys.capella.core.data.fa.FunctionOutputPort;
import org.polarsys.capella.core.data.fa.FunctionPort;
import org.polarsys.capella.core.data.fa.FunctionalExchange;
import org.polarsys.capella.core.data.information.ExchangeItem;
import org.polarsys.capella.core.validation.rule.AbstractValidationRule;

/**
 * This rule ensures that all exchange items on a function port are used by at least
 * one functional exchange connected to the port.
 */
public class DCOM_21_UnusedExchangeItems extends AbstractValidationRule {
  /**
   * @see org.eclipse.emf.validation.AbstractModelConstraint#validate(org.eclipse.emf.validation.IValidationContext)
   */
  @Override
  public IStatus validate(IValidationContext ctx) {
    EObject eObj = ctx.getTarget();
    Collection<IStatus> problems = new ArrayList<IStatus>();
    for (ExchangeItem unused : getUnusedExchangeItems((FunctionPort) ctx.getTarget())){
      problems.add(ctx.createFailureStatus(eObj, getMetaclassLabel(eObj, false), unused, getMetaclassLabel(unused, false)));
    }
    IStatus result;
    if (problems.size() > 0){
      result = ConstraintStatus.createMultiStatus(ctx, problems);
    } else {
      result = ctx.createSuccessStatus();
    }
    return result;
  }


  /**
   * Find all exchange items on a port which are not used by at least one
   * of the ports connected functional exchanges.
   * 
   * @param port the port whose exchange items should be tested for usage
   * @return all unused exchange items
   */
  public static Collection<ExchangeItem> getUnusedExchangeItems(FunctionPort port){
    Collection<ExchangeItem> toTest = new ArrayList<ExchangeItem>();
    Collection<FunctionalExchange> fes = Collections.emptyList();
    if (port instanceof FunctionInputPort) {
      toTest.addAll(((FunctionInputPort) port).getIncomingExchangeItems());
      fes = ((FunctionInputPort) port).getIncomingFunctionalExchanges();
    } else if (port instanceof FunctionOutputPort) {
      toTest.addAll(((FunctionOutputPort) port).getOutgoingExchangeItems());
      fes = ((FunctionOutputPort) port).getOutgoingFunctionalExchanges();
    }

    for (FunctionalExchange fe : fes) {
      toTest.removeAll(fe.getExchangedItems());
    }

    return toTest;
  }

}
