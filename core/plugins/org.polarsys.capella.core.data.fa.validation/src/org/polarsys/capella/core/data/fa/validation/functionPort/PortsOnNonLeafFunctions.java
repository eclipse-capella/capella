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
package org.polarsys.capella.core.data.fa.validation.functionPort;

import org.eclipse.core.runtime.IStatus;
import org.eclipse.emf.validation.IValidationContext;

import org.polarsys.capella.core.data.fa.AbstractFunction;
import org.polarsys.capella.core.data.fa.FunctionPort;
import org.polarsys.capella.core.validation.rule.AbstractValidationRule;

/**
 * Ports are not allowed on non leaf functions
 * (meaning that a functional exchange has not been allocated down to a leaf function)
 */
public class PortsOnNonLeafFunctions extends AbstractValidationRule {

  @Override
  public IStatus validate(IValidationContext ctx) {
    if (ctx.getTarget() instanceof FunctionPort) {
      FunctionPort port = (FunctionPort) ctx.getTarget();

      if (port.eContainer() instanceof AbstractFunction) {
        AbstractFunction containingFunction = (AbstractFunction) port.eContainer();

        if (containingFunction.getOwnedFunctions().size() > 0)
          return ctx.createFailureStatus(new Object[] { port.getName() });
      }
    }

    return ctx.createSuccessStatus();
  }
}
