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
import org.polarsys.capella.common.data.modellingcore.ModellingcorePackage;
import org.polarsys.capella.core.business.queries.IBusinessQuery;
import org.polarsys.capella.core.business.queries.capellacore.BusinessQueriesProvider;
import org.polarsys.capella.core.data.capellacore.CapellaElement;
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
      if (!this.hasValidValue(defaultValue)) {
        return ctx.createFailureStatus("default", property.getName(), container.getName());
        // if it has a null value, this value must be valid
      } else if (!this.hasValidValue(nullValue)) {
        return ctx.createFailureStatus("null", property.getName(), container.getName());
      }
    }

    return ctx.createSuccessStatus();
  }

  private boolean hasValidValue(DataValue value) {

    if (value == null) {
      return true;
    }
    
    IBusinessQuery query = BusinessQueriesProvider.getInstance().getContribution(value.eClass(),
        ModellingcorePackage.Literals.ABSTRACT_TYPED_ELEMENT__ABSTRACT_TYPE);

    if (query != null) {
      List<CapellaElement> availableElements = query.getAvailableElements(value);
      return availableElements.contains(value.getType());
    }

    return false;
  }
}
