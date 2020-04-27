/*******************************************************************************
 * Copyright (c) 2006, 2019 THALES GLOBAL SERVICES.
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
package org.polarsys.capella.core.semantic.queries.basic.queries.utils;

import java.util.ArrayList;
import java.util.List;

import org.eclipse.emf.ecore.EObject;
import org.polarsys.capella.core.model.helpers.ComponentExt;

/**
 * This class provides some utility methods to filter queries results
 */
public class QueriesFilters {
  /**
   * Filters the given list to remove instances of <code>AbstractActor</code> and <code>OperationalActor</code> in it.
   * @param objects the list
   * @return the filtered list
   */
  public static List<Object> filterListToRemoveActors(List<Object> objects) {
    List<Object> returnValue = new ArrayList<Object>();
    for (Object obj : objects) {
      if (obj instanceof EObject && !ComponentExt.isActor((EObject)obj)) {
        returnValue.add(obj);
      }
    }
    return returnValue;
  }
  
  /**
   * Filters the given list to remove instances of <code>AbstractActor</code> and <code>OperationalActor</code> in it.
   * @param objects the list
   * @return the filtered list
   */
  public static List<Object> filterListToGetOnlyActors(List<Object> objects) {
    List<Object> returnValue = new ArrayList<Object>();
    for (Object obj : objects) {
      if (obj instanceof EObject && ComponentExt.isActor((EObject)obj)) {
        returnValue.add(obj);
      }
    }
    return returnValue;
  }
}
