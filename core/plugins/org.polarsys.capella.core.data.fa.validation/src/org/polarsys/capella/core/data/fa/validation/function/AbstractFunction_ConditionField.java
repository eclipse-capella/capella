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

import org.polarsys.capella.common.mdsofa.common.constant.ICommonConstants;
import org.polarsys.capella.core.data.fa.AbstractFunction;
import org.polarsys.capella.core.data.helpers.fa.services.FunctionExt;
import org.polarsys.capella.core.validation.rule.AbstractValidationRule;

/**
 * Function of kind Route and Select can only have condition
 */
public class AbstractFunction_ConditionField extends AbstractValidationRule {
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
        String condition = function.getCondition();
        if ((null != condition) && !condition.equalsIgnoreCase(ICommonConstants.EMPTY_STRING)) {
          if (!FunctionExt.isRouteFunction(function) && !FunctionExt.isSelectFunction(function)) {
            return ctx.createFailureStatus(function.getName() + " (" + function.eClass().getName() + ") of kind " + function.getKind()
                                             + " should not have Condition set");
          }
        }
      }
    }
    return ctx.createSuccessStatus();
  }
}
