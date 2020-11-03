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
package org.polarsys.capella.core.data.fa.validation.function;

import org.eclipse.core.runtime.IStatus;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.validation.EMFEventType;
import org.eclipse.emf.validation.IValidationContext;

import org.polarsys.capella.core.data.fa.AbstractFunction;
import org.polarsys.capella.core.data.fa.ComponentFunctionalAllocation;
import org.polarsys.capella.core.validation.rule.AbstractValidationRule;
import org.polarsys.capella.common.data.modellingcore.AbstractTrace;

/**
 * Check the unicity of the function allocation. A function must be allocated to only one
 * component, including actor 
 *
 */
public class MDCHK_Function_ComponentAllocationUnicity extends AbstractValidationRule {


  @Override
  public IStatus validate(IValidationContext ctx) {
    EObject eObj = ctx.getTarget();
    EMFEventType eType = ctx.getEventType();

    if (eType == EMFEventType.NULL) {
      if (eObj instanceof AbstractFunction) {
        AbstractFunction function = (AbstractFunction) eObj;
        int nbIncomingAllocations = 0;
        for (AbstractTrace trace : function.getIncomingTraces()) {
          if (trace instanceof ComponentFunctionalAllocation) {
            nbIncomingAllocations ++;
          }
        }
        if (nbIncomingAllocations > 1) 
          return createFailureStatus(ctx, new Object[] { function.getName() });
      }
    }
    return ctx.createSuccessStatus();
  }

}
