/*******************************************************************************
 * Copyright (c) 2006, 2019 THALES GLOBAL SERVICES.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *  
 * Contributors:
 *    Thales - initial API and implementation
 *******************************************************************************/
package org.polarsys.capella.core.ui.metric.core;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

import org.eclipse.emf.ecore.EObject;

/**
 * Tool in order to perform metric
 */
public class Metric<T> {

  Set<IMetricFilter> filters;
  Map<T, Integer> result;

  public Metric() {
    result = new HashMap<>();
    filters = new HashSet<>();
  }
  
  public Metric(Set<T> targets) {
    this();
    
    if (null != targets) {
      for (T current : targets) {
        result.put(current, Integer.valueOf(0));
      }
    }
  }
  
  public Map<T, Integer> getResult() {
    return result;
  }

  /**
   * Add new filter
   * 
   * @param filter
   * @see IMetricFilter
   */
  public void addFilter(IMetricFilter filter) {
    filters.add(filter);
  }

  /**
   * Check the current object
   * 
   * @param eObject
   * @return
   */
  public boolean accept(EObject eObject) {
    for (IMetricFilter filter : filters) {
      if (!filter.accept(eObject)) {
        return false;
      }
    }
    return true;
  }

  /**
   * Update data
   * 
   * @param key
   */
  public void update(T key) {

    int i = 1;

    if (result.containsKey(key)) {
      i += result.get(key).intValue();
    }

    result.put(key, Integer.valueOf(i));

    return;
  }

}
