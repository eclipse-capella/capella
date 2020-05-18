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

import org.eclipse.core.runtime.IStatus;
import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.validation.EMFEventType;
import org.eclipse.emf.validation.IValidationContext;
import org.polarsys.capella.common.data.modellingcore.AbstractTrace;
import org.polarsys.capella.common.helpers.EObjectLabelProviderHelper;
import org.polarsys.capella.core.data.fa.ComponentExchangeFunctionalExchangeAllocation;
import org.polarsys.capella.core.data.fa.FunctionalExchange;
import org.polarsys.capella.core.validation.rule.AbstractValidationRule;

public class DWF_DC_27_FunctionalExchangeComponentExchangeAllocationUnicity extends AbstractValidationRule {

  @Override
  public IStatus validate(IValidationContext ctx) {
    EObject eObj = ctx.getTarget();
    EMFEventType eType = ctx.getEventType();

    if (eType == EMFEventType.NULL && eObj instanceof FunctionalExchange) {
      FunctionalExchange fe = (FunctionalExchange) eObj;
      int nbIncomingAllocations = 0;
      EList<AbstractTrace> incomingTraces = fe.getIncomingTraces();
      for (AbstractTrace trace : incomingTraces) {
        if (trace instanceof ComponentExchangeFunctionalExchangeAllocation) {
          nbIncomingAllocations++;
        }
      }
      if (nbIncomingAllocations > 1) {
        return ctx.createFailureStatus(EObjectLabelProviderHelper.getText(fe));
      }
    }
    return ctx.createSuccessStatus();
  }

}
