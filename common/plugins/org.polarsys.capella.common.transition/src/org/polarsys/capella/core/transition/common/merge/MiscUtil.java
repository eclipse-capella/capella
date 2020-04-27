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

package org.polarsys.capella.core.transition.common.merge;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.util.EcoreUtil;

/**
 * Utility class providing simple reusable services related to models
 */
public final class MiscUtil {

  private MiscUtil() {
    // Forbids instantiation
  }

  /**
   * From a set of elements, filter out those which are transitively contained in others
   */
  public static <T extends EObject> List<T> getRoots(Collection<? extends T> elements1) {
    List<EObject> result = new ArrayList<EObject>(elements1);
    Set<EObject> elements = new HashSet<EObject>(elements1);

    for (EObject element : elements) {
      if ((element.eContainer() != null) && elements.contains(element.eContainer())) {
        result.remove(element);
      }
    }
    return (List) result;
  }

  /**
   * 
   * Return whether the given element is not transitively contained by any of the given elements, unless it is one of the given elements.
   */
  private static boolean isRootAmong(EObject element, Collection<? extends EObject> elements) {
    Collection<EObject> filtered = new ArrayList<EObject>(elements);
    filtered.remove(element);
    return !EcoreUtil.isAncestor(filtered, element);
  }

}
