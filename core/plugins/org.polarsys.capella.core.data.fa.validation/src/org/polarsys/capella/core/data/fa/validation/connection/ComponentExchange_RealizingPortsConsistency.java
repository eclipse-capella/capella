/*******************************************************************************
 * Copyright (c) 2006, 2014 THALES GLOBAL SERVICES.
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
package org.polarsys.capella.core.data.fa.validation.connection;

import java.util.List;

import org.eclipse.core.runtime.IStatus;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.validation.EMFEventType;
import org.eclipse.emf.validation.IValidationContext;

import org.polarsys.capella.core.data.fa.ComponentExchange;
import org.polarsys.capella.core.data.fa.FaPackage;
import org.polarsys.capella.core.data.information.Port;
import org.polarsys.capella.core.data.capellacore.CapellaElement;
import org.polarsys.capella.core.model.helpers.ComponentExchangeExt;
import org.polarsys.capella.core.model.helpers.RefinementLinkExt;
import org.polarsys.capella.core.validation.rule.AbstractValidationRule;
import org.polarsys.capella.common.data.modellingcore.TraceableElement;

/**
 * Checks realization consistency between ports of functional exchanges.
 */
public class ComponentExchange_RealizingPortsConsistency extends AbstractValidationRule {
  /**
   * @see org.eclipse.emf.validation.AbstractModelConstraint#validate(org.eclipse.emf.validation.IValidationContext)
   */
  @Override
  public IStatus validate(IValidationContext ctx) {
    EObject eObj = ctx.getTarget();
    EMFEventType eType = ctx.getEventType();
    if (eType == EMFEventType.NULL) {
      if (eObj instanceof ComponentExchange) {
        ComponentExchange currentExchange = (ComponentExchange) eObj;

        EObject sourceCurrent = ComponentExchangeExt.getSourcePort(currentExchange);
        EObject targetCurrent = ComponentExchangeExt.getTargetPort(currentExchange);

        if (sourceCurrent != null && sourceCurrent instanceof Port && targetCurrent != null && targetCurrent instanceof Port) {

          List<CapellaElement> previousPhaseElements = RefinementLinkExt.getRelatedTargetElements((CapellaElement) eObj, FaPackage.Literals.COMPONENT_EXCHANGE);

          for (CapellaElement element : previousPhaseElements) {
            ComponentExchange exc = (ComponentExchange) element;

            EObject sourcePrevious = ComponentExchangeExt.getSourcePort(exc);
            EObject targetPrevious = ComponentExchangeExt.getTargetPort(exc);

            boolean sourceValid = true;
            boolean targetValid = true;
            if (sourcePrevious != null && sourcePrevious instanceof Port && targetPrevious != null && targetPrevious instanceof Port) {
              sourceValid = RefinementLinkExt.isLinkedTo((TraceableElement) sourceCurrent, (TraceableElement) sourcePrevious);
              targetValid = RefinementLinkExt.isLinkedTo((TraceableElement) targetCurrent, (TraceableElement) targetPrevious);
            }

            if (sourceValid && targetValid)
              return ctx.createSuccessStatus();
          }

          if (previousPhaseElements.size() != 0) {
            return createFailureStatus(ctx, new Object[] { currentExchange.getName() });
          }
        }
      }
    }
    return ctx.createSuccessStatus();

  }
}
