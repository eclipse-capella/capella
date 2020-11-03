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
package org.polarsys.capella.core.data.information.validation.property;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import org.eclipse.core.runtime.IStatus;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.validation.IValidationContext;
import org.eclipse.emf.validation.model.ConstraintStatus;
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
    Collection<IStatus> statuses = new ArrayList<IStatus>();

    // if eObj is a Property in a named container
    if ((eObj instanceof Property) && (eObj.eContainer() instanceof Class)) {
      Class container = (Class) eObj.eContainer();
      Property property = (Property) eObj;

      // if it has a default value, this value must be valid
      DataValue defaultValue = property.getOwnedDefaultValue();
      if (!this.hasValidValue(defaultValue, property)) {
        IStatus status = ctx.createFailureStatus("default", property.getName(), container.getName());
        statuses.add(status);
      }

      // if it has a null value, this value must be valid
      DataValue nullValue = property.getOwnedNullValue();
      if (!this.hasValidValue(nullValue, property)) {
        IStatus status = ctx.createFailureStatus("null", property.getName(), container.getName());
        statuses.add(status);
      }
    }

    if (statuses.isEmpty()) {
      return ctx.createSuccessStatus();
    }
    return ConstraintStatus.createMultiStatus(ctx, statuses);
  }

  private boolean hasValidValue(DataValue value, Property property) {

    // the value is valid if undefined
    if (value == null) {
      return true;
    }

    Type valueType = value.getType();
    Type propertyType = property.getType();


    // the value is valid if its type is undefined (implicit typing)
    if (valueType == null) {
      return true;
    }
    
    // the value is valid if its type is the same of its property's datatype
    if (valueType == propertyType) {
      return true;
    }

    // or a super property's datatype
    if (propertyType instanceof GeneralizableElement) {
      GeneralizableElement genPropertyType = (GeneralizableElement) propertyType;
      List<GeneralizableElement> superDataTypes = GeneralizableElementExt
          .getAllSuperGeneralizableElements(genPropertyType);
      return superDataTypes.contains(valueType);
    }

    return false;
  }
}
