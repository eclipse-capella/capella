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
package org.polarsys.capella.core.data.fa.validation.componentPort;

import org.eclipse.core.runtime.IStatus;
import org.eclipse.emf.validation.IValidationContext;
import org.polarsys.capella.core.data.fa.ComponentPort;
import org.polarsys.capella.core.validation.rule.AbstractValidationRule;

/**
 * A component port that have a CE should also have interface(s).
 */
public class ComponentPortHasCEAndInterface extends AbstractValidationRule {

  @Override
  public IStatus validate(IValidationContext ctx) {
    if (ctx.getTarget() instanceof ComponentPort) {
      ComponentPort cp = (ComponentPort) ctx.getTarget();

      if (cp.getComponentExchanges().size() > 0 && cp.getProvidedInterfaces().size() == 0
          && cp.getRequiredInterfaces().size() == 0) {
        return ctx.createFailureStatus(new Object[] { cp.getName() });
      }
    }

    return ctx.createSuccessStatus();
  }

}
