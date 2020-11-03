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
import org.polarsys.capella.core.data.fa.FunctionRealization;
import org.polarsys.capella.core.validation.rule.AbstractValidationRule;
import org.polarsys.capella.common.data.modellingcore.AbstractTrace;

/**
 * Checks functions allocation consistency.
 */
public class AbstractFunction_TransitionFunctionKind extends AbstractValidationRule {
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
        for (AbstractTrace trace : function.getOutgoingTraces()) {
          if (trace instanceof FunctionRealization) {
            AbstractFunction source = ((FunctionRealization) trace).getAllocatingFunction();
            AbstractFunction target = ((FunctionRealization) trace).getAllocatedFunction();
            if (source != null && target != null && !source.getKind().equals(target.getKind())) {
              return createFailureStatus(ctx, new Object[] { source.getName(), source.getKind().getName(), target.getName(), target.getKind().getName() });
            }
          }
        }
      }
    }
    return ctx.createSuccessStatus();
  }
}
