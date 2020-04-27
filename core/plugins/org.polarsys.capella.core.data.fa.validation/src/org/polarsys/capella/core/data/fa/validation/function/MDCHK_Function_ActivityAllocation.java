/*******************************************************************************
 * Copyright (c) 2006, 2016 THALES GLOBAL SERVICES.
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
import org.polarsys.capella.core.data.capellacore.NamedElement;
import org.polarsys.capella.core.data.oa.ActivityAllocation;
import org.polarsys.capella.core.model.helpers.AbstractFunctionExt;
import org.polarsys.capella.core.validation.rule.AbstractValidationRule;
import org.polarsys.capella.common.data.modellingcore.AbstractTrace;

/**
 * Checks activity allocations consistency.
 */
public class MDCHK_Function_ActivityAllocation extends AbstractValidationRule {
  /**
   * @see org.eclipse.emf.validation.AbstractModelConstraint#validate(org.eclipse.emf.validation.IValidationContext)
   */
  @Override
  public IStatus validate(IValidationContext ctx) {
    EObject eObj = ctx.getTarget();
    EMFEventType eType = ctx.getEventType();
    if (eType == EMFEventType.NULL) {
      if (eObj instanceof AbstractFunction) {
        AbstractFunction function = (AbstractFunction) eObj;
        for (AbstractTrace trace : function.getIncomingTraces()) {
          if (trace instanceof ActivityAllocation && !(AbstractFunctionExt.isLeaf(function))) {
            return createFailureStatus(ctx, new Object[] { function.getName(), ((NamedElement) trace.getSourceElement()).getName() });
          }
        }
      }
    }
    return ctx.createSuccessStatus();
  }
}
