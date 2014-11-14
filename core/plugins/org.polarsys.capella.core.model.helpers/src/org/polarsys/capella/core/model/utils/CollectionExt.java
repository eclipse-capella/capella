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

import java.util.ArrayList;
import java.util.Collection;

/**
 */
public class CollectionExt {

  public static <T> Collection<T> filter(Collection<?> collection, Class<T> type) {
    ArrayList<T> result = new ArrayList<T>(collection.size());

    for (Object o : collection) {
      if (type.isInstance(o)) {
        result.add(type.cast(o));
      }
    }
    result.trimToSize();

    return result;
  }

  /**
   *
   */
  public static String[] getArray(Collection<String> list_p) {
    String[] result = new String[list_p.size()];
    int i = 0;
    for (String str : list_p) {
      result[i++] = str;
    }
    return result;
  }
}
