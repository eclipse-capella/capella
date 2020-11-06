/*******************************************************************************
 * Copyright (c) 2006, 2020 THALES GLOBAL SERVICES.
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

import org.eclipse.emf.ecore.EObject;
import org.polarsys.capella.core.ui.metric.dialog.MetricLabelProvider;

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
  
  public void sortChildren() {
    MetricLabelProvider labelProvider = new MetricLabelProvider();
    children.sort((obj1, obj2) -> {
      String text1 = labelProvider.getText(obj1);
      String text2 = labelProvider.getText(obj2);
      if (text1.equals(text2)) {
        // In case of meta-classes with the same name, use the fully qualified name to have the exact order
        return ((EObject) obj1.getElement()).eClass().getInstanceTypeName()
            .compareTo(((EObject) obj2.getElement()).eClass().getInstanceTypeName());
      }
      return text1.compareTo(text2);
    });
  }

}
