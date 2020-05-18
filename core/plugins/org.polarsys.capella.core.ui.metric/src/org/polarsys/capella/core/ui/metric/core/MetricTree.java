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
package org.polarsys.capella.core.ui.metric.core;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

public class MetricTree<T> {

  private MetricTree<T> parent = null;
  private List<MetricTree<T>> children = new ArrayList<>();
  private T element;
  private int count = 0;

  public MetricTree(T element, MetricTree<T> parent) {
    this.element = element;
    this.parent = parent;
    if (parent != null) {
      parent.addChild(this);
    }
  }

  public T getElement() {
    return element;
  }

  public int getCount() {
    return count;
  }

  public void increaseCount(int count) {
    this.count += count;
  }

  public MetricTree<T> getParent() {
    return parent;
  }

  public boolean isRoot() {
    return null == parent;
  }
  
  public boolean isLeaf() {
    return children.isEmpty();
  }

  public List<MetricTree<T>> getChildren() {
    return children;
  }

  public boolean hasChildren() {
    return !children.isEmpty();
  }

  public void addChild(MetricTree<T> child) {
    children.add(child);
  }

  public void clear() {
    this.parent = null;
    this.element = null;
    if (null != getChildren()) {
      for (MetricTree<T> current : getChildren()) {
        current.clear();
      }
      this.children.clear();
    }
  }

  public List<MetricTree<T>> getLeafs() {
    List<MetricTree<T>> leafs = new ArrayList<>();
    Stack<MetricTree<T>> stack = new Stack<>();
    stack.push(this);

    while (!stack.isEmpty()) {
      MetricTree<T> tree = stack.pop();

      if (tree.getChildren().isEmpty()) {
        leafs.add(tree);
      } else {
        stack.addAll(tree.getChildren());
      }

    }

    return leafs;
  }

}
