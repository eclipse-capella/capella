/*******************************************************************************
 * Copyright (c) 2023 THALES GLOBAL SERVICES.
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
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.validation.EMFEventType;
import org.eclipse.emf.validation.IValidationContext;
import org.polarsys.capella.common.data.modellingcore.AbstractTrace;
import org.polarsys.capella.core.data.fa.ComponentExchange;
import org.polarsys.capella.core.data.fa.ComponentExchangeAllocation;
import org.polarsys.capella.core.validation.rule.AbstractValidationRule;

/**
 * Check the unicity of the component exchange allocation. A component exchange must be allocated to only one
 * physical link
 *
 */
public class MDCHK_ComponentExchange_PhysicalLinkAllocationUnicity extends AbstractValidationRule {


  @Override
  public IStatus validate(IValidationContext ctx) {
    EObject eObj = ctx.getTarget();
    EMFEventType eType = ctx.getEventType();

    if (eType == EMFEventType.NULL) {
      if (eObj instanceof ComponentExchange) {
        ComponentExchange ce = (ComponentExchange) eObj;
        int nbIncomingAllocations = 0;
        for (AbstractTrace trace : ce.getIncomingTraces()) {
          if (trace instanceof ComponentExchangeAllocation) {
            nbIncomingAllocations ++;
          }
        }
        if (nbIncomingAllocations > 1) 
          return createFailureStatus(ctx, new Object[] { ce.getName() });
      }
    }
    return ctx.createSuccessStatus();
  }

}
