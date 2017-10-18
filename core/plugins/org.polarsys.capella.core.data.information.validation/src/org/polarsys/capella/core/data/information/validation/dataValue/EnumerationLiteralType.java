/*******************************************************************************
 * Copyright (c) 2006, 2017 THALES GLOBAL SERVICES.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *  
 * Contributors:
 *    Thales - initial API and implementation
 *******************************************************************************/
package org.polarsys.capella.core.data.information.validation.dataValue;

import org.eclipse.core.runtime.IStatus;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.validation.IValidationContext;

import org.polarsys.capella.core.data.information.datatype.Enumeration;
import org.polarsys.capella.core.data.information.datavalue.EnumerationLiteral;
import org.polarsys.capella.core.validation.rule.AbstractValidationRule;
import org.polarsys.capella.common.data.modellingcore.AbstractType;

/**
 * This query validates if EnumerationLiteral is typed by its parent
 */
public class EnumerationLiteralType extends AbstractValidationRule {

  /**
   * @see org.eclipse.emf.validation.AbstractModelConstraint#validate(org.eclipse.emf.validation.IValidationContext)
   */
  @Override
  public IStatus validate(IValidationContext ctx) {
    EObject eObj = ctx.getTarget();
    if (eObj instanceof EnumerationLiteral) {
      EnumerationLiteral enumLiteral = (EnumerationLiteral) eObj;
      EObject container = enumLiteral.eContainer();
      if (container != null && container instanceof Enumeration) {
        Enumeration eNum = (Enumeration) container;
        AbstractType abstractType = enumLiteral.getAbstractType();
        if (abstractType == null || !eNum.equals(abstractType)) {
          return ctx.createFailureStatus(new Object[] {enumLiteral.getName(),eNum.getName()});
        }
      }
    }

    return ctx.createSuccessStatus();
  }

}
