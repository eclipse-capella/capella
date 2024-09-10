/*******************************************************************************
 * Copyright (c) 2014, 2024 Thales LAS France SA.
 * Copyright (c) 2024, 2024 THALES GLOBAL SERVICES.
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
package org.polarsys.capella.core.data.fa.validation.functionalChain;

import org.eclipse.core.runtime.IStatus;
import org.eclipse.emf.validation.IValidationContext;

import org.polarsys.capella.core.data.fa.FunctionalChain;
import org.polarsys.capella.core.validation.rule.AbstractValidationRule;

/**
 * FunctionalChains should have a description.
 */
public class FunctionalChainsDescription extends AbstractValidationRule {

  @Override
  public IStatus validate(IValidationContext ctx) {
    if (ctx.getTarget() instanceof FunctionalChain) {
      FunctionalChain fc = (FunctionalChain) ctx.getTarget();
      String toBeChecked;

      toBeChecked = fc.getDescription();

      if (toBeChecked == null || toBeChecked.trim().isEmpty()) {
        return ctx.createFailureStatus(new Object[] { fc.getName() });
      }
    }

    return ctx.createSuccessStatus();
  }
}
