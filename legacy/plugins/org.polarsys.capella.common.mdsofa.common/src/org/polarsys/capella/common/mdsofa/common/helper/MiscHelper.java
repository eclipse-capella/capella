/*******************************************************************************
 * Copyright (c) 2006, 2014 THALES GLOBAL SERVICES.
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
package org.polarsys.capella.common.mdsofa.common.helper;

import java.util.ArrayList;
import java.util.List;

/**
 * Miscellaneous helper.
 */
public class MiscHelper {
  /**
   * Returns a list backed by the specified array.
   * @param elements_p the array by which the list will be backed.
   * @return a not null list.
   */
  public static <T> List<T> asList(T[] elements_p) {
    ArrayList<T> result = new ArrayList<T>(elements_p.length);
    for (T element : elements_p) {
      result.add(element);
    }
    return result;
  }
}
