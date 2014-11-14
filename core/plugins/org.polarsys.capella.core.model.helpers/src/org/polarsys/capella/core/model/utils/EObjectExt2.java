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
   * @param values_p
   * @param feature_p
   * @return
   */
  public static String formatValues(Collection<?> values_p, EStructuralFeature feature_p) {
    return formatValues(values_p, feature_p, Messages.getString("UndefinedValue")); //$NON-NLS-1$
  }

  /**
   * Format values.
   * @param values_p
   * @param feature_p
   * @param defaultText_p
   * @return
   */
  public static String formatValues(Collection<?> values_p, EStructuralFeature feature_p, String defaultText_p) {
    String value = ICommonConstants.EMPTY_STRING;

    if (values_p.isEmpty()) {
      value = defaultText_p;
    } else {
      int index = 0;
      for (Object obj : values_p) {
        if (obj instanceof EObject) {
          value += NamingHelper.getValue((EObject) obj, feature_p);
        } else {
          value += defaultText_p;
        }
        if (++index < values_p.size()) {
          value += ", "; //$NON-NLS-1$
        }
      }
    }
    return value;
  }
}
