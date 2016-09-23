/*******************************************************************************
 * Copyright (c) 2006, 2016 THALES GLOBAL SERVICES.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *  
 * Contributors:
 *    Thales - initial API and implementation
 *******************************************************************************/
package org.polarsys.capella.core.refinement.scenarios.core.datastructures;

import java.util.ArrayList;
import java.util.List;

/**
 * Represents a Tree of Objects of generic type T. The Tree is represented as a
 * single rootElement which points to a List<Node<T>> of children. There is no
 * restriction on the number of children that a particular node may have. This
 * Tree provides a method to serialize the Tree into a List by doing a pre-order
 * traversal. It has several methods to allow easy updation of Nodes in the
 * Tree.
 */
public class Tree<T> {

  private Node<T> rootElement = null;

  /**
   * Default constructor.
   */
  public Tree() {
    super();
  }

  /**
   * Return the root Node of the tree.
   * 
   * @return the root element.
   */
  public Node<T> getRootElement() {
    return rootElement;
  }

  /**
   * Set the root Element for the tree.
   * 
   * @param rootElement
   *            the root element to set.
   */
  public void setRootElement(Node<T> rootElement) {
    this.rootElement = rootElement;
  }

  /**
   * Returns a String representation of the Tree. The elements are generated
   * from a pre-order traversal of the Tree.
   * 
   * @return the String representation of the Tree.
   */
  @Override
  public String toString() {
    return walk(rootElement).toString();
  }

  /**
   * Returns the Tree<T> as a List of Node<T> objects. The elements of the
   * List are generated from a pre-order traversal of the tree.
   * Walks the Tree in pre-order style. This is a recursive method, and is
   * called from the toList() method with the root element as the first
   * argument. It appends to the second argument, which is pacapellad by reference *
   * as it recurses down the tree.
   * 
   * @param element
   *            the starting element.
   * @param list
   *            the output of the walk.
   */
  public List<Node<T>> walk(Node<T> element) {
    List<Node<T>> list = new ArrayList<Node<T>>();

    list.add(element);
    for (Node<T> data : element.getChildren()) {
      list.addAll(walk(data));
    }

    return list;
  }

  /**
   * Remove the current 'Message' node of the existing chaining (HEAD / NEXT / PREVIOUS)
   * without removing the node itself.
   */
  public void unChainCurrentNodeMessage(Node<T> currentNode) {
    for (Node<T> node : walk(rootElement)) {
      if (node == currentNode) {
        Node<T> nextNode = node.next;
        Node<T> parentNode = node.parent;
        Node<T> previousNode = node.previous;

        if (previousNode != null) previousNode.next = nextNode;
        if (nextNode != null) nextNode.previous = previousNode;

        if ((parentNode!=null) && (parentNode.head==node)) {
          if (previousNode != null) parentNode.head = previousNode;
          else parentNode.head = nextNode;
        }
        node.next = null;
        node.previous = null;
        node.parent = null;

        return;
      }
    }
  }
}
