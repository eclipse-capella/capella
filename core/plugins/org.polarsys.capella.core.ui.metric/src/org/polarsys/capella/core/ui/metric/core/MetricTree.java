/*******************************************************************************
 * Copyright (c) 2006, 2017 THALES GLOBAL SERVICES.
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

  private MetricTree<T> parent = null;
  private List<MetricTree<T>> children = null;

  private T nodeId;
  private Integer data;
  
  public T getId() { return nodeId;}
  public Integer getData() { return data;}
  public void setData(Integer value_p) { data = value_p; return;}
  public boolean hasData() { return null != data;}
  
  public MetricTree<T> getParent() {return parent;}
  public boolean isRoot() {return null == parent; }

  public List<MetricTree<T>> getChildren() { return children;}
  public boolean hasChildren() { return null != children && !children.isEmpty();}
  
  public void addChild(MetricTree<T> child_p) {
    addChildren(Collections.singletonList(child_p));
    return;
  }

  public void addChildren(List<MetricTree<T>> children_p) {
    if (!hasChildren()) {
      children = new ArrayList<MetricTree<T>>();
    }
    children.addAll(children_p);
    return;
  }

  public MetricTree(T id, Integer data, MetricTree<T> parent) {
    this.parent = parent;
    this.nodeId = id;
    this.data = data;
  }
  
  public void clear() {
    this.parent = null;
    this.nodeId = null;
    this.data = null;
    if (null != getChildren()) {
      for (MetricTree<T> current: getChildren()) {
        current.clear();
      }
      this.children.clear();
    }
    
    return;
  }
  
}
