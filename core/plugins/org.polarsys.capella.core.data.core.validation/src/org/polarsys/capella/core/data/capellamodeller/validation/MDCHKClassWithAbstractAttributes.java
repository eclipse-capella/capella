/*******************************************************************************
 * Copyright (c) 2006, 2018 THALES GLOBAL SERVICES.
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
package org.polarsys.capella.core.data.capellamodeller.validation;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;

import org.eclipse.core.runtime.IStatus;
import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.validation.EMFEventType;
import org.eclipse.emf.validation.IValidationContext;
import org.eclipse.emf.validation.model.ConstraintStatus;

import org.polarsys.capella.core.data.information.Class;
import org.polarsys.capella.core.data.information.Property;
import org.polarsys.capella.core.data.capellacore.GeneralClass;
import org.polarsys.capella.core.data.capellacore.GeneralizableElement;
import org.polarsys.capella.core.validation.rule.AbstractValidationRule;

/**
 * check If a class has an abstract attribute or a abstract role of an association on the navigable side, the class shall be abstract
 */
public class MDCHKClassWithAbstractAttributes extends AbstractValidationRule {

  /**
   * @see org.eclipse.emf.validation.AbstractModelConstraint#validate(org.eclipse.emf.validation.IValidationContext)
   */
  @Override
  public IStatus validate(IValidationContext ctx) {
    EObject eObj = ctx.getTarget();
    EMFEventType eType = ctx.getEventType();
    if (eType == EMFEventType.NULL && eObj instanceof Class) {
      Class classToValidate = (Class) eObj;
      boolean isNotAbstractClass = !classToValidate.isAbstract();
      if (isNotAbstractClass) {
        // This collection will store the validation errors
        Collection<IStatus> statuses = new ArrayList<>();
        validateAbstractClass(ctx, classToValidate, statuses);
        boolean isValidatedSuperClasses = validateAbstractSuperClass(ctx, classToValidate, classToValidate, statuses);
        if (isValidatedSuperClasses) {
          IStatus failureStatus = ctx.createFailureStatus("\"" + classToValidate.getName() + "\" (Class)", //$NON-NLS-1$ //$NON-NLS-2$
              " inherited attribute", ""); //$NON-NLS-1$ //$NON-NLS-2$
          statuses.add(failureStatus);
        }
        if (!statuses.isEmpty()) {
          // There are conflicts returns them as a multi-statuses status
          return ConstraintStatus.createMultiStatus(ctx, statuses);
        }
      }
    }
    return ctx.createSuccessStatus();
  }
	  
  /**
   * @param generalClass
   * @param statuses
   * @param elt
   * @param ctx
   */
  private void validateAbstractClass(IValidationContext ctx, GeneralClass generalClass, Collection<IStatus> statuses) {
    List<Property> properties = generalClass.getContainedProperties();
    for (Property property : properties) {
      // it is an property
      if (property.getAssociation() == null) {
        boolean isAbstractAttr = property.isIsAbstract();
        if (isAbstractAttr) {
          IStatus failureStatus =
              ctx.createFailureStatus("\"" + generalClass.getName() + "\" (Class)", " attribute ", "\"" + property.getName() + "\" (Property)"); //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$ //$NON-NLS-4$ //$NON-NLS-5$
          statuses.add(failureStatus);
        }
      } else {
        List<Property> navigableMembers = property.getAssociation().getNavigableMembers();
        if (null != navigableMembers) {
          for (Property object : navigableMembers) {
            if (object.isIsAbstract()) {
              IStatus failureStatus =
                  ctx.createFailureStatus("\"" + generalClass.getName() + "\" (Class)", " association role ", "\"" + property.getAssociation().getName()   //$NON-NLS-1$//$NON-NLS-2$//$NON-NLS-3$ //$NON-NLS-4$
                                                                                                                + "\" (Association)"); //$NON-NLS-1$
              statuses.add(failureStatus);
            }

          }
        }

      }

    }

  }

  /**
   * @param ctx
   * @param generalClass
   * @param statuses
   */
  private boolean validateAbstractSuperClass(IValidationContext ctx, GeneralClass classToValidate, GeneralClass generalClass, Collection<IStatus> statuses) {
    List<GeneralizableElement> superGeneralizableElements = generalClass.getSuper();
    if (superGeneralizableElements != null && !superGeneralizableElements.isEmpty()) {
      for (Iterator<GeneralizableElement> iterator = superGeneralizableElements.iterator(); iterator.hasNext();) {
        Class cla = (Class) iterator.next();
        boolean hasAbstractAttribute = hasAbstractAttributeOrAssociationRole(classToValidate, cla, generalClass) || validateAbstractSuperClass(ctx, classToValidate, cla, statuses);
        if (hasAbstractAttribute) {
          return true;
        }
      }
    }
    return false;
  }


  /**
   * @param generalClass
   * @param subClass
   * @return
   */
  private boolean hasAbstractAttributeOrAssociationRole(GeneralClass classToValidate, Class generalClass, GeneralClass subClass) {
    List<Property> properties = generalClass.getContainedProperties();
    for (Property property : properties) {
      // it is an property
      if (property.getAssociation() == null) {
        boolean isAbstractAttr = property.isIsAbstract();
        if (isAbstractAttr) {
          return !isOverridedProperty(classToValidate, subClass, property);
        }
      } else {
        EList<Property> navigableMembers = property.getAssociation().getNavigableMembers();
        if (null != navigableMembers) {
          for (Property object : navigableMembers) {
            if (object.isIsAbstract()) {
              return true;
            }
          }
        }
      }

    }
    return false;
  }
	
  /**
   * @param subClass
   * @param propertyToSearch
   * @return
   */
  private boolean isOverridedProperty(GeneralClass classToValidate, GeneralClass subClass, Property propertyToSearch) {
    List<Property> properties = subClass.getContainedProperties();
    for (Property property : properties) {
      if (property.getName().equals(propertyToSearch.getName())) {
        return true;
      }
    }
    // if the property is not overridden in the super class it must be overridden in a source class
    return isOverridedPropertyInSourceClass(classToValidate, propertyToSearch);
  }

  /**
   * @param classToValidate
   * @param propertyToSearch
   * @return
   */
  private boolean isOverridedPropertyInSourceClass(GeneralClass classToValidate, Property propertyToSearch) {
    List<Property> properties = classToValidate.getContainedProperties();
    for (Property property : properties) {
      if (property.getName().equals(propertyToSearch.getName())) {
        return true;
      }
    }
    return false;
  }
}
