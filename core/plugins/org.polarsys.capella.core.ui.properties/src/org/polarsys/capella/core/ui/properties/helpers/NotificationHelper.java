/*******************************************************************************
 * Copyright (c) 2006, 2015 THALES GLOBAL SERVICES.
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
package org.polarsys.capella.core.ui.properties.helpers;

import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EStructuralFeature;

import org.polarsys.capella.common.mdsofa.common.constant.ICommonConstants;

public class NotificationHelper {
  /**
   * Empty string constant.
   */
  public static final String EMPTY_STRING = ICommonConstants.EMPTY_STRING;

  /**
   * Is a notification required (something has changed)?
   * @param object
   * @param feature the feature.
   * @param value the current text value.
   * @return <code>true</code> means a notification has to be sent.
   */
  static public boolean isNotificationRequired(EObject object, EStructuralFeature feature, String value) {
    boolean isNotificationRequired = false;
    // Get the feature value.
    Object featureValue = object.eGet(feature);
    // If the feature value is unset, check the new value is not empty.
    if ((null == featureValue) && !EMPTY_STRING.equals(value)) {
      isNotificationRequired = true;
    }
    // The feature is set, compare the text value with the current feature value.
    // A notification is required only if values are different ones.
    if (null != featureValue) {
      isNotificationRequired = !featureValue.equals(value);
    }
    return isNotificationRequired;
  }

  /**
   * Is a notification required (something has changed)?
   * @param object
   * @param feature the feature.
   * @param value the current value.
   * @return <code>true</code> means a notification has to be sent.
   */
  static public boolean isNotificationRequired(EObject object, EStructuralFeature feature, Object value) {
    if (value instanceof String) {
      return isNotificationRequired(object, feature, (String) value);
    }
    boolean isNotificationRequired = false;
    // Get the feature value.
    Object featureValue = object.eGet(feature);
    // If the feature value is unset, check the new value is not null.
    if ((null == featureValue) && (null != value)) {
      isNotificationRequired = true;
    }
    // The feature is set, compare the current value with the current feature value.
    // A notification is required only if values are different ones.
    if (null != featureValue) {
      isNotificationRequired = !featureValue.equals(value);
    }
    return isNotificationRequired;
  }

}
