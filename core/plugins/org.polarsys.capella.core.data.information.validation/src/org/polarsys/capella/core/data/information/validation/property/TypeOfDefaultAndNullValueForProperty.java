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

import java.util.List;

import org.eclipse.core.runtime.IStatus;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.validation.IValidationContext;
import org.polarsys.capella.core.data.capellacore.GeneralizableElement;
import org.polarsys.capella.core.data.capellacore.Type;
import org.polarsys.capella.core.data.helpers.capellacore.services.GeneralizableElementExt;
import org.polarsys.capella.core.data.information.Class;
import org.polarsys.capella.core.data.information.Property;
import org.polarsys.capella.core.data.information.datavalue.DataValue;
import org.polarsys.capella.core.validation.rule.AbstractValidationRule;

/**
 * Check Property untyped
 */
public class TypeOfDefaultAndNullValueForProperty extends AbstractValidationRule {
  /**
   * @see org.eclipse.emf.validation.AbstractModelConstraint#validate(org.eclipse.emf.validation.IValidationContext)
   */
  @Override
  public IStatus validate(IValidationContext ctx) {
    EObject eObj = ctx.getTarget();

    // if eObj is a Property in a named container
    if ((eObj instanceof Property) && (eObj.eContainer() instanceof Class)) {
      Class container = (Class) eObj.eContainer();
      Property property = (Property) eObj;

      DataValue defaultValue = property.getOwnedDefaultValue();
      DataValue nullValue = property.getOwnedNullValue();

      // if it has a default value, this value must be valid
      if (!this.hasValidValue(defaultValue, property)) {
        return ctx.createFailureStatus("default", property.getName(), container.getName());
        // if it has a null value, this value must be valid
      } else if (!this.hasValidValue(nullValue, property)) {
        return ctx.createFailureStatus("null", property.getName(), container.getName());
      }
    }

    return ctx.createSuccessStatus();
  }

  private boolean hasValidValue(DataValue value, Property property) {

    // the value is valid if undefined
    if (value == null) {
      return true;
    }
    
    Type valueType = value.getType();
    Type propertyType = property.getType();

    // the value is valid if its type is the same of its property's datatype
    if (valueType == propertyType) {
      return true;
    }

    // or a super property's datatype
    if (propertyType instanceof GeneralizableElement) {
      GeneralizableElement genPropertyType = (GeneralizableElement) propertyType;
      List<GeneralizableElement> superDataTypes = GeneralizableElementExt.getAllSuperGeneralizableElements(genPropertyType);
      return superDataTypes.contains(valueType);
    }

    return false;
  }
}
