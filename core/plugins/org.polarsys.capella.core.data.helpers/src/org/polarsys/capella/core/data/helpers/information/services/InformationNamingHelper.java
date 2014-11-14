/*******************************************************************************
 * Copyright (c) 2006, 2014 THALES GLOBAL SERVICES.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *  
 * Contributors:
 *    Thales - initial API and implementation
 *******************************************************************************/
package org.polarsys.capella.core.data.helpers.information.services;

import org.eclipse.emf.ecore.EObject;

import org.polarsys.capella.core.data.information.Operation;
import org.polarsys.capella.core.data.information.Property;
import org.polarsys.capella.core.data.capellacore.Classifier;
import org.polarsys.capella.common.data.modellingcore.AbstractNamedElement;

/**
 */
public class InformationNamingHelper {
  /**
   * @param element_p element whose value is requested
   */
  public static String getValue(Property element_p) {
    if (element_p != null) {
      String propertyName = element_p.getName();
      if (propertyName != null) {
        EObject container = element_p.eContainer();
        if (container instanceof Classifier) {
          Classifier propertyContainer = (Classifier) container;
          if (propertyContainer.getName() != null) {
            return propertyContainer.getName() + "::" + propertyName; //$NON-NLS-1$
          }
        } else {
          return propertyName;
        }
      }
    }
    return Messages.getString("UndefinedValue"); //$NON-NLS-1$
  }

  /**
   * @param element_p element whose value is requested
   */
  public static String getValue(Operation element_p) {
    if (element_p != null) {
      String value = element_p.getName();
      EObject container = element_p.eContainer();
      if (container instanceof AbstractNamedElement) {
        value = ((AbstractNamedElement) container).getName() + "::" + value; //$NON-NLS-1$
      }
      return value;
    }
    return Messages.getString("UndefinedValue"); //$NON-NLS-1$
  }
}
