/*******************************************************************************
 * Copyright (c) 2006, 2016 THALES GLOBAL SERVICES.
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

package org.polarsys.capella.core.model.utils;

import java.util.Collection;

import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EStructuralFeature;
import org.polarsys.capella.common.helpers.EObjectExt;

/**
 */
public class EObjectExt2 extends EObjectExt {

  /**
   * Format values.
   * @param values
   * @param feature
   * @return
   */
  public static String formatValues(Collection<?> values, EStructuralFeature feature) {
    return formatValues(values, feature, Messages.getString("UndefinedValue")); //$NON-NLS-1$
  }

  /**
   * Format values.
   * @param values
   * @param feature
   * @param defaultText
   * @return
   */
  public static String formatValues(Collection<?> values, EStructuralFeature feature, String defaultText) {
    if (values.isEmpty()) {
      return defaultText;
    } 
    StringBuilder value = new StringBuilder();
    int index = 0;
    for (Object obj : values) {
      if (obj instanceof EObject) {
        value.append(NamingHelper.getValue((EObject) obj, feature));
      } else {
        value.append(defaultText);
      }
      if (++index < values.size()) {
        value.append(", "); //$NON-NLS-1$
      }
    }
    return value.toString();
  }
}
