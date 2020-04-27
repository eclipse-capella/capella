/*******************************************************************************
 * Copyright (c) 2006, 2019 THALES GLOBAL SERVICES.
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
   * Private constructor.
   */
  private InformationNamingHelper() {
  }

  /**
   * @param element element whose value is requested
   */
  public static String getValue(Property element) {
    if (element != null) {
      String propertyName = element.getName();
      if (propertyName != null) {
        EObject container = element.eContainer();
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
   * @param element element whose value is requested
   */
  public static String getValue(Operation element) {
    if (element != null) {
      String value = element.getName();
      EObject container = element.eContainer();
      if (container instanceof AbstractNamedElement) {
        value = ((AbstractNamedElement) container).getName() + "::" + value; //$NON-NLS-1$
      }
      return value;
    }
    return Messages.getString("UndefinedValue"); //$NON-NLS-1$
  }
}
