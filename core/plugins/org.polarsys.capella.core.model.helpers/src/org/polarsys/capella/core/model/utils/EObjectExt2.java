/*******************************************************************************
 * Copyright (c) 2006, 2016 THALES GLOBAL SERVICES.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *  
 * Contributors:
 *    Thales - initial API and implementation
 *******************************************************************************/

package org.polarsys.capella.core.model.utils;

import java.util.Collection;

import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EStructuralFeature;

import org.polarsys.capella.common.helpers.EObjectExt;
import org.polarsys.capella.common.mdsofa.common.constant.ICommonConstants;

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
    String value = ICommonConstants.EMPTY_STRING;

    if (values.isEmpty()) {
      value = defaultText;
    } else {
      int index = 0;
      for (Object obj : values) {
        if (obj instanceof EObject) {
          value += NamingHelper.getValue((EObject) obj, feature);
        } else {
          value += defaultText;
        }
        if (++index < values.size()) {
          value += ", "; //$NON-NLS-1$
        }
      }
    }
    return value;
  }
}
