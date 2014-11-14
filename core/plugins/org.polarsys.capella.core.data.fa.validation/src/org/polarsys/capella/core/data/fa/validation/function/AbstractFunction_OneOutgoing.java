/*******************************************************************************
 * Copyright (c) 2006, 2014 THALES GLOBAL SERVICES.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
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
public class AbstractFunction_OneOutgoing extends AbstractValidationRule {
  /**
   * @see org.eclipse.emf.validation.AbstractModelConstraint#validate(org.eclipse.emf.validation.IValidationContext)
   */
  @Override
  public IStatus validate(IValidationContext ctx_p) {
    EObject eObj = ctx_p.getTarget();
    EMFEventType eType = ctx_p.getEventType();

    if (eType == EMFEventType.NULL) {
      if (eObj instanceof AbstractFunction) {
        AbstractFunction function = (AbstractFunction) eObj;
        if (FunctionExt.isControlNodeOneOutput(function)) {
          if (FunctionExt.getOutGoingExchange(function).size() > 1) {
            return createFailureStatus(ctx_p, new Object[] { CapellaElementExt.getName(eObj), function.getKind().getName(), Messages.AbstractFunction_Exchange });
          }
          if (function.getOutputs().size() > 1) {
            return createFailureStatus(ctx_p, new Object[] { CapellaElementExt.getName(eObj), function.getKind().getName(), Messages.AbstractFunction_Port });
          }
        }
      }
    }
    return ctx_p.createSuccessStatus();
  }
}
