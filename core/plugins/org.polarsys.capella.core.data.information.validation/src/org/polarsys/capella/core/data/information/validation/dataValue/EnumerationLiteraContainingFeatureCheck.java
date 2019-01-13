/*******************************************************************************
 * Copyright (c) 2019 THALES GLOBAL SERVICES.
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

public class EnumerationLiteraContainingFeatureCheck extends AbstractValidationRule {

  @Override
  public IStatus validate(IValidationContext ctx) {
    EObject target = ctx.getTarget();
    if (target instanceof EnumerationLiteral && target.eContainer() instanceof Enumeration) {
      EnumerationLiteral enumLiteral = (EnumerationLiteral)target;
      Enumeration enumeration = (Enumeration)target.eContainer();
      if(enumeration.getOwnedDataValues().contains(enumLiteral)) {
        return ctx.createFailureStatus(enumLiteral.getName(), enumeration.getName());
      }
    }
    // Return success
    return ctx.createSuccessStatus();
  }
}
