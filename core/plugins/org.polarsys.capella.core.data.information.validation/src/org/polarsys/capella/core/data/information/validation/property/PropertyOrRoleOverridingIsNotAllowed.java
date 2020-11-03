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

import org.eclipse.core.runtime.IStatus;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.validation.IValidationContext;
import org.polarsys.capella.core.data.capellacore.GeneralizableElement;
import org.polarsys.capella.core.data.helpers.capellacore.services.GeneralizableElementExt;
import org.polarsys.capella.core.data.information.Class;
import org.polarsys.capella.core.data.information.Property;
import org.polarsys.capella.core.validation.rule.AbstractValidationRule;

/**
 * Check Property untyped
 */
public class PropertyOrRoleOverridingIsNotAllowed extends AbstractValidationRule {
  /**
   * @see org.eclipse.emf.validation.AbstractModelConstraint#validate(org.eclipse.emf.validation.IValidationContext)
   */
  @Override
  public IStatus validate(IValidationContext ctx) {
    EObject eObj = ctx.getTarget();

    // if eObj is a Property in a class
    if ((eObj instanceof Property) && (eObj.eContainer() instanceof Class)) {
      Class ownerClass = (Class) eObj.eContainer();
      Property property = (Property) eObj;

      // search if it overrides one of its parent classes properties
      Class parentClass = this.propertyOverriding(property, ownerClass);

      // in this case, return a failure
      if (parentClass != null) {
        return ctx.createFailureStatus(ownerClass.getName(), property.getName(), parentClass.getName());
      }
    }

    return ctx.createSuccessStatus();
  }

  private Class propertyOverriding(Property property, Class ownerClass) {
    // for all parent classes
    for (GeneralizableElement parent : GeneralizableElementExt.getAllSuperGeneralizableElements(ownerClass)) {
      if (parent instanceof Class) {
        Class parentClass = (Class) parent;

        // if one of their property is equal to this property
        for (Property parentPoperty : parentClass.getContainedProperties()) {
          if (parentPoperty.getName().equals(property.getName())) {

            // return the class of this overridden property
            return parentClass;
          }
        }
      }
    }
    return null;
  }
}
