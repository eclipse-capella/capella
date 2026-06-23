/*******************************************************************************
 * Copyright (c) 2014, 2024 Thales LAS France SAS.
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
package org.polarsys.capella.core.data.fa.validation.ce;

import org.eclipse.core.runtime.IStatus;
import org.eclipse.emf.validation.IValidationContext;
import org.polarsys.capella.core.data.fa.ComponentExchange;
import org.polarsys.capella.core.validation.rule.AbstractValidationRule;

/**
 * A CE should have at least 1 allocated FE.
 */
public class ComponentExchangeHasAllocatedFunctionalExchange extends AbstractValidationRule {

  @Override
  public IStatus validate(IValidationContext ctx) {
    if (ctx.getTarget() instanceof ComponentExchange) {
      ComponentExchange ce = (ComponentExchange) ctx.getTarget();
      if (ce.getAllocatedFunctionalExchanges().size() == 0) {
        return ctx.createFailureStatus(new Object[] { ce.getName() });
      }
    }

    return ctx.createSuccessStatus();
  }

}
