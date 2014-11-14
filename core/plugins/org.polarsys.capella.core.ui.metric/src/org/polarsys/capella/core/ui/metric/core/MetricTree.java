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

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 *
 */
public class MetricTree<T> {

  private MetricTree<T> _parent = null;
  private List<MetricTree<T>> _children = null;

  private T _nodeId;
  private Integer _data;
  
  public T getId() { return _nodeId;}
  public Integer getData() { return _data;}
  public void setData(Integer value_p) { _data = value_p; return;}
  public boolean hasData() { return null != _data;}
  
  public MetricTree<T> getParent() {return _parent;}
  public boolean isRoot() {return null == _parent; }

  public List<MetricTree<T>> getChildren() { return _children;}
  public boolean hasChildren() { return null != _children && !_children.isEmpty();}
  
  public void addChild(MetricTree<T> child_p) {
    addChildren(Collections.singletonList(child_p));
    return;
  }

  public void addChildren(List<MetricTree<T>> children_p) {
    if (!hasChildren()) {
      _children = new ArrayList<MetricTree<T>>();
    }
    _children.addAll(children_p);
    return;
  }

  public MetricTree(T id, Integer data, MetricTree<T> parent) {
    _parent = parent;

    _nodeId = id;
    _data = data;
  }
  
  public void clear() {
    _parent = null;
    _nodeId = null;
    _data = null;
    if (null != getChildren()) {
      for (MetricTree<T> current: getChildren()) {
        current.clear();
      }
      _children.clear();
    }
    
    return;
  }
  
}
