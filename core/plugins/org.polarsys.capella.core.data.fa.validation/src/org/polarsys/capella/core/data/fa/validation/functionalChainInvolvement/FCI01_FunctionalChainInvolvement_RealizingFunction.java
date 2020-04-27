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
package org.polarsys.capella.core.data.fa.validation.functionalChainInvolvement;

import org.eclipse.core.runtime.IStatus;
import org.eclipse.emf.validation.EMFEventType;
import org.eclipse.emf.validation.IValidationContext;
import org.polarsys.capella.common.data.modellingcore.AbstractTrace;
import org.polarsys.capella.common.helpers.EcoreUtil2;
import org.polarsys.capella.core.data.fa.AbstractFunction;
import org.polarsys.capella.core.data.fa.FunctionalChainInvolvement;
import org.polarsys.capella.core.model.helpers.CapellaElementExt;
import org.polarsys.capella.core.validation.rule.AbstractValidationRule;

/**
 * Checks realization consistency between abstract functions. A transitioned involvement is valid if the transitioned
 * involved function is valid.
 */
public class FCI01_FunctionalChainInvolvement_RealizingFunction extends AbstractValidationRule {
  /**
   * @see org.eclipse.emf.validation.AbstractModelConstraint#validate(org.eclipse.emf.validation.IValidationContext)
   */
  @Override
  public IStatus validate(IValidationContext ctx) {

    if (ctx.getEventType() == EMFEventType.NULL && ctx.getTarget() instanceof FunctionalChainInvolvement) {
      FunctionalChainInvolvement involvement = (FunctionalChainInvolvement) ctx.getTarget();
      if (involvement.getInvolved() instanceof AbstractFunction) {
        AbstractFunction initialLevelFunction = (AbstractFunction) involvement.getInvolved();

        for (AbstractTrace outgoingTrace : involvement.getOutgoingTraces()) {
          if (outgoingTrace.getTargetElement() instanceof FunctionalChainInvolvement) {
            FunctionalChainInvolvement outgoingInvolvement = (FunctionalChainInvolvement) outgoingTrace
                .getTargetElement();
            if (outgoingInvolvement.getInvolved() instanceof AbstractFunction) {
              AbstractFunction outgoingFunction = (AbstractFunction) outgoingInvolvement.getInvolved();

              for (AbstractTrace incomingTrace : outgoingFunction.getIncomingTraces()) {
                if (incomingTrace.getSourceElement() instanceof AbstractFunction) {
                  AbstractFunction secondLevelFunction = (AbstractFunction) incomingTrace.getSourceElement();
                  if (EcoreUtil2.isOrIsContainedBy(initialLevelFunction, secondLevelFunction)) {
                    return ctx.createSuccessStatus();
                  }
                }
              }
            }
          }
        }

        if (!involvement.getOutgoingTraces().isEmpty()) {
          return ctx.createFailureStatus(CapellaElementExt.getName(involvement));
        }
      }
    }

    return ctx.createSuccessStatus();
  }
}
