/*******************************************************************************
 * Copyright (c) 2019, 2020 THALES GLOBAL SERVICES.
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
import org.polarsys.capella.core.data.information.datatype.Enumeration;
import org.polarsys.capella.core.data.information.datavalue.EnumerationLiteral;
import org.polarsys.capella.core.data.information.datavalue.LiteralBooleanValue;
import org.polarsys.capella.core.validation.rule.AbstractValidationRule;

public class DataValueContainingFeatureCheck extends AbstractValidationRule {

  @Override
  public IStatus validate(IValidationContext ctx) {
    EObject target = ctx.getTarget();
    if (target instanceof EnumerationLiteral && target.eContainer() instanceof Enumeration) {
      EnumerationLiteral enumLiteral = (EnumerationLiteral) target;
      Enumeration enumeration = (Enumeration) target.eContainer();
      if (enumeration.getOwnedDataValues().contains(enumLiteral)) {
        return ctx.createFailureStatus(enumLiteral.getName(), Messages.DataValueContainingFeatureCheck_EnumLiteral_Type, enumeration.getName(),
        		Messages.DataValueContainingFeatureCheck_Enumeration_Type);
      }
    } else if (target instanceof LiteralBooleanValue && target.eContainer() instanceof BooleanType) {
      LiteralBooleanValue literalBooleanValue = (LiteralBooleanValue) target;
      BooleanType booleanType = (BooleanType) target.eContainer();
      if (booleanType.getOwnedDataValues().contains(literalBooleanValue)) {
        return ctx.createFailureStatus(literalBooleanValue.getName(), Messages.DataValueContainingFeatureCheck_BooleanLiteral_Type, booleanType.getName(),
        		Messages.DataValueContainingFeatureCheck_Boolean_Type);
      }
    }
    // Return success
    return ctx.createSuccessStatus();
  }
}
