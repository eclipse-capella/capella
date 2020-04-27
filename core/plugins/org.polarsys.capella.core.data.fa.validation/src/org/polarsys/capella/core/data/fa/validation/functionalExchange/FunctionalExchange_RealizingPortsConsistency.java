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
import org.polarsys.capella.common.data.modellingcore.TraceableElement;
import org.polarsys.capella.common.helpers.EObjectLabelProviderHelper;
import org.polarsys.capella.core.data.capellacore.CapellaElement;
import org.polarsys.capella.core.data.fa.FaPackage;
import org.polarsys.capella.core.data.fa.FunctionalExchange;
import org.polarsys.capella.core.data.information.Port;
import org.polarsys.capella.core.model.helpers.RefinementLinkExt;
import org.polarsys.capella.core.validation.rule.AbstractValidationRule;

/**
 * Checks realization consistency between ports of functional exchanges.
 */
public class FunctionalExchange_RealizingPortsConsistency extends AbstractValidationRule {
  /**
   * @see org.eclipse.emf.validation.AbstractModelConstraint#validate(org.eclipse.emf.validation.IValidationContext)
   */
  @Override
  public IStatus validate(IValidationContext ctx) {
    EObject eObj = ctx.getTarget();
    EMFEventType eType = ctx.getEventType();
    if (eType == EMFEventType.NULL && eObj instanceof FunctionalExchange) {
        FunctionalExchange currentExchange = (FunctionalExchange) eObj;

        ActivityNode sourceCurrent = currentExchange.getSource();
        ActivityNode targetCurrent = currentExchange.getTarget();

        if (sourceCurrent instanceof Port && targetCurrent instanceof Port) {

          List<CapellaElement> previousPhaseElements = RefinementLinkExt.getRelatedTargetElements(currentExchange, FaPackage.Literals.FUNCTIONAL_EXCHANGE);

          for (CapellaElement element : previousPhaseElements) {
            FunctionalExchange exc = (FunctionalExchange) element;

            ActivityNode sourcePrevious = exc.getSource();
            ActivityNode targetPrevious = exc.getTarget();

            boolean sourceValid = true;
            boolean targetValid = true;
            if (sourcePrevious instanceof Port && targetPrevious instanceof Port) {
              sourceValid = RefinementLinkExt.isLinkedTo((TraceableElement) sourceCurrent, (TraceableElement) sourcePrevious);
              targetValid = RefinementLinkExt.isLinkedTo((TraceableElement) targetCurrent, (TraceableElement) targetPrevious);
            }

            if (sourceValid && targetValid) {
              return ctx.createSuccessStatus();
            }
          }

          if (previousPhaseElements.size() != 0) {
            return ctx.createFailureStatus(EObjectLabelProviderHelper.getText(currentExchange));
          }
        }
    }
    return ctx.createSuccessStatus();

  }
}
