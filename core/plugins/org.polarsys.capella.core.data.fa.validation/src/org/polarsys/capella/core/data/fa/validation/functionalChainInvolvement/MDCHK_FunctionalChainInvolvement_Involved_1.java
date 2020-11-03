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
package org.polarsys.capella.core.data.fa.validation.functionalChainInvolvement;

import org.eclipse.core.runtime.IStatus;
import org.eclipse.emf.validation.EMFEventType;
import org.eclipse.emf.validation.IValidationContext;
import org.polarsys.capella.core.data.capellacore.InvolvedElement;
import org.polarsys.capella.core.data.fa.AbstractFunction;
import org.polarsys.capella.core.data.fa.FunctionalChain;
import org.polarsys.capella.core.data.fa.FunctionalChainInvolvement;
import org.polarsys.capella.core.data.fa.FunctionalChainInvolvementFunction;
import org.polarsys.capella.core.data.fa.FunctionalChainInvolvementLink;
import org.polarsys.capella.core.data.fa.FunctionalChainReference;
import org.polarsys.capella.core.data.fa.FunctionalExchange;
import org.polarsys.capella.core.validation.rule.AbstractValidationRule;

public class MDCHK_FunctionalChainInvolvement_Involved_1 extends AbstractValidationRule {
  /**
   * @see org.eclipse.emf.validation.AbstractModelConstraint#validate(org.eclipse.emf.validation.IValidationContext)
   */
  @Override
  public IStatus validate(IValidationContext ctx) {
    if (ctx.getEventType() == EMFEventType.NULL && ctx.getTarget() instanceof FunctionalChainInvolvement) {

      FunctionalChainInvolvement involvement = (FunctionalChainInvolvement) ctx.getTarget();
      InvolvedElement involved = involvement.getInvolved();

      if (involvement instanceof FunctionalChainReference) {
        if (!(involved instanceof FunctionalChain)) {
          return ctx.createFailureStatus(Messages.MDCHK_FunctionalChainInvolvement_FunctionalChainReference,
              Messages.MDCHK_FunctionalChainInvolvement_aFunctionalChain);
        }
      } else if (involvement instanceof FunctionalChainInvolvementLink) {
        if (!(involved instanceof FunctionalExchange || involved instanceof AbstractFunction)) {
          return ctx.createFailureStatus(Messages.MDCHK_FunctionalChainInvolvement_FunctionalChainInvolvementLink,
              Messages.MDCHK_FunctionalChainInvolvement_aFunctionOrFunctionalExchange);
        }
      } else if (involvement instanceof FunctionalChainInvolvementFunction && !(involved instanceof AbstractFunction)) {
        return ctx.createFailureStatus(Messages.MDCHK_FunctionalChainInvolvement_FunctionalChainInvolvementFunction,
            Messages.MDCHK_FunctionalChainInvolvement_aFunction);
      }
    }
    return ctx.createSuccessStatus();
  }
}
