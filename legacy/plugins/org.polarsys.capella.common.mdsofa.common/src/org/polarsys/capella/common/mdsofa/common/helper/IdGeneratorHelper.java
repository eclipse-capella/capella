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
package org.polarsys.capella.common.mdsofa.common.helper;

import org.polarsys.capella.common.mdsofa.common.constant.ICommonConstants;

/**
 * Helper to generate object unique id.
 */
public class IdGeneratorHelper {
  /**
   * Id suffix.
   */
  private volatile static int __idSuffix = 0;

  /**
   * Generate unique id for given object.
   * @param object_p
   * @return the not null generated unique id.
   */
  public static String generateUniqueId(Object object_p) {
    String prefix = null;
    // If given object is not null, use it to create a prefix.
    if (null != object_p) {
      // Create prefix.
      prefix = object_p.getClass().getSimpleName();
    }
    return generateUniqueIdUsingPrefix(prefix);
  }

  /**
   * Generate unique id using given prefix.
   * @param prefix_p
   * @return the not null generated unique id.
   */
  public static String generateUniqueIdUsingPrefix(String prefix_p) {
    String prefix = prefix_p;
    if (null == prefix) {
      prefix = ICommonConstants.EMPTY_STRING;
    }
    StringBuilder result = new StringBuilder(StringHelper.forceSimpleSetOfCharacters(prefix));
    result.append(ICommonConstants.POINT_CHARACTER).append(System.currentTimeMillis()).append(ICommonConstants.POINT_CHARACTER).append(__idSuffix++);
    return result.toString();
  }
}
