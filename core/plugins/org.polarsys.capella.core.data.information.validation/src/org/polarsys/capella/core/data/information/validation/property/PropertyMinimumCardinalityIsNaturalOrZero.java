/*******************************************************************************
 * Copyright (c) 2006, 2015 THALES GLOBAL SERVICES.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *  
 * Contributors:
 *    Thales - initial API and implementation
 *******************************************************************************/
package org.polarsys.capella.core.data.information.validation.property;

import org.eclipse.core.runtime.IStatus;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.validation.IValidationContext;
import org.polarsys.capella.core.data.capellacore.NamedElement;
import org.polarsys.capella.core.data.information.Class;
import org.polarsys.capella.core.data.information.ExchangeItem;
import org.polarsys.capella.core.data.information.ExchangeItemElement;
import org.polarsys.capella.core.data.information.Property;
import org.polarsys.capella.core.data.information.datatype.NumericType;
import org.polarsys.capella.core.data.information.datatype.NumericTypeKind;
import org.polarsys.capella.core.data.information.datavalue.NumericValue;
import org.polarsys.capella.core.data.oa.Role;
import org.polarsys.capella.core.model.helpers.DataValueExt;
import org.polarsys.capella.core.validation.rule.AbstractValidationRule;

/**
 * Check Property untyped
 */
public class PropertyMinimumCardinalityIsNaturalOrZero extends AbstractValidationRule {
  /**
   * @see org.eclipse.emf.validation.AbstractModelConstraint#validate(org.eclipse.emf.validation.IValidationContext)
   */
  @Override
  public IStatus validate(IValidationContext ctx) {
    EObject eObj = ctx.getTarget();

    // if eObj is a Property in a named container
    if (isValidType(eObj) && (eObj.eContainer() instanceof NamedElement)) {
      NamedElement container = (NamedElement) eObj.eContainer();
      Property property = (Property) eObj;

      // if it is not abstract its min card must be a natural or zero
      if (!property.isIsAbstract() && !this.isNaturalOrZero(property.getOwnedMinCard())) {
        return ctx.createFailureStatus(container.getName(), property.getName());
      }
    }
    // or an exchange item element
    else if ((eObj instanceof ExchangeItemElement) && (eObj.eContainer() instanceof NamedElement)) {
      NamedElement container = (NamedElement) eObj.eContainer();
      ExchangeItemElement exchangeItemElement = (ExchangeItemElement) eObj;

      // its min card must be a natural or zero
      if (!this.isNaturalOrZero(exchangeItemElement.getOwnedMinCard())) {
        return ctx.createFailureStatus(container.getName(), exchangeItemElement.getName());
      }
    }

    return ctx.createSuccessStatus();
  }

  private boolean isValidType(EObject eObj) {
    return eObj instanceof Property && eObj.eContainer() instanceof Class || eObj instanceof Role
        || eObj instanceof ExchangeItem;
  }

  private boolean isNaturalOrZero(NumericValue value) {
    if (value != null) {
      NumericType type = value.getNumericType();

      // the type of min card must be undefined or Integer
      if (type == null || (type.getKind() == NumericTypeKind.INTEGER)) {
        try {
          // and its value must be parse to an integer
          int integer = Integer.parseInt(DataValueExt.getCardValue(value));

          // and positive or zero
          return integer >= 0;
        } catch (Exception e) {
          return false;
        }
      }
    }
    return false;
  }
}
