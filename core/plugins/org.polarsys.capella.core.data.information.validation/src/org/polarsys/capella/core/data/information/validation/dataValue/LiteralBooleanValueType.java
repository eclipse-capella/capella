/*******************************************************************************
 * Copyright (c) 2006, 2017 THALES GLOBAL SERVICES.
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
package org.polarsys.capella.core.data.information.validation.dataValue;

import org.eclipse.core.runtime.IStatus;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.validation.IValidationContext;

import org.polarsys.capella.core.data.information.datatype.BooleanType;
import org.polarsys.capella.core.data.information.datavalue.LiteralBooleanValue;
import org.polarsys.capella.core.validation.rule.AbstractValidationRule;
import org.polarsys.capella.common.data.modellingcore.AbstractType;

/**
 * This query validates if LiteralBooleanValue is typed by its parent
 */
public class LiteralBooleanValueType extends AbstractValidationRule {

  /**
   * @see org.eclipse.emf.validation.AbstractModelConstraint#validate(org.eclipse.emf.validation.IValidationContext)
   */
  @Override
  public IStatus validate(IValidationContext ctx) {
    EObject eObj = ctx.getTarget();
    if (eObj instanceof LiteralBooleanValue) {
      LiteralBooleanValue booleanLiteral = (LiteralBooleanValue) eObj;
      EObject container = booleanLiteral.eContainer();
      if (container != null && container instanceof BooleanType) {
        BooleanType booleanType = (BooleanType) container;
        AbstractType abstractType = booleanLiteral.getAbstractType();
        if (abstractType == null || !booleanType.equals(abstractType)) {
          return ctx.createFailureStatus(new Object[] {booleanLiteral.getName(),booleanType.getName()});
        }
      }
    }

    return ctx.createSuccessStatus();
  }

}
