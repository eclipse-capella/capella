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
   * @param object_p
   * @param feature_p the feature.
   * @param value_p the current text value.
   * @return <code>true</code> means a notification has to be sent.
   */
  static public boolean isNotificationRequired(EObject object_p, EStructuralFeature feature_p, String value_p) {
    boolean isNotificationRequired = false;
    // Get the feature value.
    Object featureValue = object_p.eGet(feature_p);
    // If the feature value is unset, check the new value is not empty.
    if ((null == featureValue) && !EMPTY_STRING.equals(value_p)) {
      isNotificationRequired = true;
    }
    // The feature is set, compare the text value with the current feature value.
    // A notification is required only if values are different ones.
    if (null != featureValue) {
      isNotificationRequired = !featureValue.equals(value_p);
    }
    return isNotificationRequired;
  }

  /**
   * Is a notification required (something has changed)?
   * @param object_p
   * @param feature_p the feature.
   * @param value_p the current value.
   * @return <code>true</code> means a notification has to be sent.
   */
  static public boolean isNotificationRequired(EObject object_p, EStructuralFeature feature_p, Object value_p) {
    if (value_p instanceof String) {
      return isNotificationRequired(object_p, feature_p, (String) value_p);
    }
    boolean isNotificationRequired = false;
    // Get the feature value.
    Object featureValue = object_p.eGet(feature_p);
    // If the feature value is unset, check the new value is not null.
    if ((null == featureValue) && (null != value_p)) {
      isNotificationRequired = true;
    }
    // The feature is set, compare the current value with the current feature value.
    // A notification is required only if values are different ones.
    if (null != featureValue) {
      isNotificationRequired = !featureValue.equals(value_p);
    }
    return isNotificationRequired;
  }

}
