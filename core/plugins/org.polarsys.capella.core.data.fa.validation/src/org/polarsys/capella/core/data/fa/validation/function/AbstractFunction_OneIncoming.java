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
import org.polarsys.capella.core.data.helpers.fa.services.FunctionExt;
import org.polarsys.capella.core.model.helpers.CapellaElementExt;
import org.polarsys.capella.core.validation.rule.AbstractValidationRule;

/**
 * Checks allocation consistency between functions and components.
 */
public class AbstractFunction_OneIncoming extends AbstractValidationRule {
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
        if (FunctionExt.isControlNodeOneInput(function)) {
          if (FunctionExt.getIncomingExchange(function).size() > 1) {
            return createFailureStatus(ctx, new Object[] { CapellaElementExt.getName(eObj), function.getKind().getName(), Messages.AbstractFunction_Exchange });
          }
          if (function.getInputs().size() > 1) {
            return createFailureStatus(ctx, new Object[] { CapellaElementExt.getName(eObj), function.getKind().getName(), Messages.AbstractFunction_Port });
          }
        }
      }
    }
    return ctx.createSuccessStatus();
  }
}
