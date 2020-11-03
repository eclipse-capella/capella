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
import org.polarsys.capella.core.data.fa.FunctionalChainInvolvementLink;
import org.polarsys.capella.core.validation.rule.AbstractValidationRule;

public class MDCHK_FunctionalChainInvolvement_NextAndPrevious_1 extends AbstractValidationRule {
  /**
   * @see org.eclipse.emf.validation.AbstractModelConstraint#validate(org.eclipse.emf.validation.IValidationContext)
   */
  @Override
  public IStatus validate(IValidationContext ctx) {
    if (EMFEventType.NULL == ctx.getEventType() && ctx.getTarget() instanceof FunctionalChainInvolvementLink) {
      FunctionalChainInvolvementLink link = (FunctionalChainInvolvementLink) ctx.getTarget();

      if (link.getPreviousFunctionalChainInvolvements().isEmpty()
          || link.getNextFunctionalChainInvolvements().isEmpty()) {
        return ctx.createFailureStatus(link);
      }
    }

    return ctx.createSuccessStatus();
  }
}
