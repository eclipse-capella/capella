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
package org.polarsys.capella.core.data.fa.validation.functionalChainInvolvement;

import org.eclipse.core.runtime.IStatus;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.validation.EMFEventType;
import org.eclipse.emf.validation.IValidationContext;

import org.polarsys.capella.core.data.fa.FunctionalChainInvolvement;
import org.polarsys.capella.core.data.fa.FunctionalExchange;
import org.polarsys.capella.core.data.capellacore.InvolvedElement;
import org.polarsys.capella.core.validation.rule.AbstractValidationRule;

public class MDCHK_FunctionalChainInvolvement_NextAndPrevious_1 extends AbstractValidationRule {
  /**
   * @see org.eclipse.emf.validation.AbstractModelConstraint#validate(org.eclipse.emf.validation.IValidationContext)
   */
  @Override
  public IStatus validate(IValidationContext ctx) {
    if (EMFEventType.NULL.equals(ctx.getEventType())) {
      EObject eObj = ctx.getTarget();
      if (eObj instanceof FunctionalChainInvolvement) {
        InvolvedElement involved = ((FunctionalChainInvolvement) eObj).getInvolved();

        if (involved instanceof FunctionalExchange) {
          if (((FunctionalChainInvolvement) eObj).getPreviousFunctionalChainInvolvements().isEmpty()) {
            return ctx.createFailureStatus(new Object[] { eObj });
          }
          if (((FunctionalChainInvolvement) eObj).getNextFunctionalChainInvolvements().isEmpty()) {
            return ctx.createFailureStatus(new Object[] { eObj });
          }
        }
      }
    }
    return ctx.createSuccessStatus();
  }
}
