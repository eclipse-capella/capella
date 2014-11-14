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
package org.polarsys.capella.core.ui.metric.core;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

import org.eclipse.emf.ecore.EObject;

/**
 * Tool in order to perform metric
 *
 */
public class Metric<T> {

  /** Filters to apply */
  Set<IMetricFilter> _filters;
  
  /** result */
  Map<T, Integer> _result;
    
  /** accessor on result */
  public Map<T, Integer> getResult() {return _result;}
  
  /**
   * Constructor
   */
  public Metric() {
    _result = new HashMap<T, Integer>();
    _filters = new HashSet<IMetricFilter>();
  }
  
  /**
   * Constructor
   * @param targets_p
   */
  public Metric(Set<T> targets_p) {
    this();
    
    if (null != targets_p) {
      for (T current: targets_p) {
        _result.put(current, Integer.valueOf(0));
      }
    }
    
  }
  
  /**
   * Add new filter
   * @param filter_p
   * @see IMetricFilter
   */
  public void addFilter(IMetricFilter filter_p){
   _filters.add(filter_p); 
  }
  
  /**
   * Check the current object
   * @param eobject_p
   * @return
   */
  public boolean accept(EObject eobject_p) {
    
    boolean result = true;
    
    for (IMetricFilter filter: _filters) {
      if (!filter.accept(eobject_p)) { 
        result = false;
        break;
      }
    }
    
    return result;
  }
  
  /**
   * Update data
   * @param key
   */
  public void update(T key) {
    
    int i = 1;
    
    if (_result.containsKey(key)) {
      i += _result.get(key).intValue();
    }
    
    _result.put(key, Integer.valueOf(i));
    
    return;
  }
  
}
