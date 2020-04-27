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

import java.util.ArrayList;
import java.util.Collection;

import org.eclipse.emf.ecore.EObject;

/**
 */
public class CollectionExt {

  /**
   * @param c1 a collection of eobjects
   * @param c2 a collection of eobjects
   * @return a collection containing the union of c1 and c2 elements (no duplicates)
   */
  public static Collection<EObject> mergeCollections(Collection<? extends EObject> c1, Collection<? extends EObject> c2) {
    Collection<EObject> result = new ArrayList<EObject>(c1);
    for (EObject obj : c2) {
      if (!result.contains(obj)) {
        result.add(obj);
      }
    }
    return result;
  }

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
  public static String[] getArray(Collection<String> list) {
    String[] result = new String[list.size()];
    int i = 0;
    for (String str : list) {
      result[i++] = str;
    }
    return result;
  }
}
