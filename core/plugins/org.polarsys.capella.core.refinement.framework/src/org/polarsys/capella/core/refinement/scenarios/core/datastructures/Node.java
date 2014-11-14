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
package org.polarsys.capella.core.refinement.scenarios.core.datastructures;

import java.util.ArrayList;
import java.util.List;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EObject;

/**
 * Represents a node of the Tree<T> class. The Node<T> is also a container,
 * and can be thought of as instrumentation to determine the location of the
 * type T in the Tree<T>.
 * 
 */
public class Node<T> {

  /**
   * the related node is the corresponding node in the refined tree
   */
  public Node<T> relatedNode = null;

  /**
   * the opposite node is the corresponding REPLY (/CALL) node related to the current CALL (/REPLY) node
   */
  public Node<T> oppositeNode = null;

  protected Node<T> _parent = null;
  protected Node<T> _previous = null;
  protected Node<T> _next = null;
  protected Node<T> _head = null;

  private T _data = null;
  private int _level = 0;

  /**
   * Default constructor.
   */
  public Node(Node<T> parent) {
    super();
    setParent(parent);
    if (_parent != null) _level = _parent._level + 1;
  }

  /**
   * Convenience constructor to create a Node<T> with an instance of T.
   * 
   * @param data
   *            an instance of T.
   */
  public Node(Node<T> parent, T data) {
    this(parent);
    setData(data);
  }

  /**
   * Return the children of Node<T>.
   * 
   * @return the children of Node<T>
   */
  public List<Node<T>> getChildren() {
    List<Node<T>> children = new ArrayList<Node<T>>();
    for (Node<T> childNode=_head; childNode!=null; childNode=childNode._next) {
      children.add(childNode);
    }
    return children;
  }

  /**
   * Returns the number of immediate children of this Node<T>.
   * 
   * @return the number of immediate children.
   */
  public int getNumberOfChildren() {
    int size = 0;
    for (Node<T> childNode=_head; childNode!=null; childNode=childNode._next) {
      size++;
    }
    return size;
  }

  /**
   * Adds a child at the end of the list of children for this Node<T>.
   * 
   * @param child
   *            a Node<T> object to set.
   */
  public void addLastChild(Node<T> child) {
    Node<T> lastChild = getLastChildNode();

    if (lastChild!=null) lastChild._next = child;
    else this._head = child;

    child._previous = lastChild;
    child._next = null;
    child._parent = this;
    child._level = _level+1;
  }

  /**
   * Adds a child at the beginning of the list of children for this Node<T>.
   * 
   * @param child
   */
  public void addFirstChild(Node<T> child) {
    Node<T> firstChild = getFirstChildNode();

    if (firstChild != null) firstChild._previous = child;

    child._previous = null;
    child._next = firstChild;
    child._parent = this;
    child._level = _level+1;

    this._head = child;
  }

  /**
   * Inserts the current node 'Message' as child node of the parent node 'parentNode'
   * and as successor of 'previousNode' (chaining management HEAD / NEXT / PREVIOUS). 
   * Note: In the case where no predecessor is passed as parameter (previousNode == NULL), 
   *       the current node is inserted as head node with respect to his father. 
   * WARNING: probably 'blind' the insertion in the case where the previous node
   *          does not have the same father that one passed as parameter. In this case, it's better 
   *          to clear the 'Next' field of the previous node, otherwise, we get 'transverse' 
   *          relationships successor/predecessor  parents ....VERY DANGEROUS. BRIEF, see if this 
   *          can potentially occur, especially according to the order of traversal of the source tree.
   * @param previousNode
   * @param nextNode
   */
  public void addChildAfter(Node<T> child, Node<T> previousNode) {
    if (previousNode != null) {
      for (Node<T> childNode=_head; childNode!=null; childNode=childNode._next) {
        if (childNode == previousNode) {
          Node<T> nextNode = previousNode._next;

          child._previous = previousNode;
          child._next = nextNode;
          child._parent = this;
          child._level = _level+1;
          previousNode._next = child;
          if (nextNode != null) nextNode._previous = child;
        }
      }
    }
    else {
      addFirstChild(child);
    }
  }

  /**
   * @return the parent of the current node
   */
  public Node<T> getParent() {
    return _parent;
  }

  /**
   * 
   */
  public void setParent(Node<T> parent) {
    this._parent = parent;
  }

  /**
   * @return the previous sibling of the current node
   */
  public Node<T> getPreviousNode() {
    return _previous;
  }

  /**
   * @return the next sibling of the current node
   */
  public Node<T> getNextNode() {
    return _next;
  }

  /**
   * @return the first node of the current node's chained list
   */
  public Node<T> getFirstNode() {
    return (_previous==null)?this:_previous.getFirstNode();
  }

  /**
   * @return the last node of the current node's chained list
   */
  public Node<T> getLastNode() {
    return (_next==null)?this:_next.getLastNode();
  }

  /**
   * @return the first node of the children node's chained list
   */
  public Node<T> getFirstChildNode() {
    return _head;
  }

  /**
   * @return the last node of the children node's chained list
   */
  public Node<T> getLastChildNode() {
    return (_head==null)?null:_head.getLastNode();
  }

  /**
   * @param node
   * @return 'true' if the 'node' parameter is a child of the current node, and 'false' otherwise
   */
  public boolean isParentOf(Node<T> node) {
    for (Node<T> childNode=_head; childNode!=null; childNode=childNode._next) {
      if (childNode==node) return true;
    }
    return false;
  }

  /**
   * 
   */
  public T getData() {
    return _data;
  }

  /**
   * 
   */
  public void setData(T data) {
    _data = data;
  }

  /**
   * 
   */
  public int getLevel() {
    return _level;
  }

  /**
   * 
   */
  public void setLevel(int level) {
    _level = level;
  }

  /**
   * @return the first linked owner node
   */
  public Node<T> getFirstLinkedParent() {
    Node<T> returnedNode = null;
    Node<T> parentNode = getParent();

    if (parentNode != null) {
      if (parentNode.relatedNode != null) {
        returnedNode = parentNode;
      }
      else returnedNode = parentNode.getFirstLinkedParent();
    }

    return returnedNode;
  }

  /**
   * @return the first linked owner node
   */
  public Node<T> getFirstParentInstanceOf(EClass eclass_p) {
    Node<T> returnedNode = null;

    if (((EObject) _data).eClass().isSuperTypeOf(eclass_p)) {
      returnedNode = this;
    } else {
      Node<T> parentNode = getParent();
      if (parentNode != null) {
        returnedNode = parentNode.getFirstParentInstanceOf(eclass_p);
      }
    }

    return returnedNode;
  }

  /**
   * Calculates the SCHEDULING level by returning the first preceding linked node. 
   * -> Traversal by predecessor nodes relationship of the current source tree until one of 
   * them is linked to a node of the target tree.
   * @return
   */
  public Node<T> getFirstLinkedPredecessor() {
    Node<T> parentNode   = getParent();
    Node<T> previousNode = getPreviousNode();
    Node<T> returnedNode = null;
  
    if (previousNode != null) {
      returnedNode = previousNode.findPreviousLinkedNode();
      if (returnedNode == null) {
        if (previousNode.relatedNode != null) {
          returnedNode = previousNode;
        }
        else {
          returnedNode = previousNode.getFirstLinkedPredecessor();
        }
      }
    }
    else if (parentNode != null) {
      returnedNode = parentNode.getFirstLinkedPredecessor();
    }
  
    return returnedNode;
  }

  /**
   * This method looks for the first predecessor linked to a node of the target tree, parsing the current tree.
   * The parsing goes up recursively on owner nodes, inspecting in priority the children nodes.
   * (go in the opposite direction leaves of predecessors nodes)
   * 
   * @return
   */
  public Node<T> findPreviousLinkedNode() {
    Node<T> lastChild = null;
    Node<T> returnedNode = null;
  
    for (lastChild=getLastChildNode(); (lastChild!=null) && (returnedNode==null); lastChild=lastChild.getPreviousNode()) {
      returnedNode = lastChild.findPreviousLinkedNode();
    }
  
    if ((returnedNode==null) && (relatedNode!=null)) {
      returnedNode = this;
    }
  
    return returnedNode;
  }

  /**
   * 
   */
  @Override
  public String toString() {
    String data = ""; //$NON-NLS-1$
    StringBuilder sb = new StringBuilder();
    if (getData() != null) data = getData().toString();
    sb.append("{").append(data).append(",[");  //$NON-NLS-1$//$NON-NLS-2$
    int i = 0;
    for (Node<T> childNode=_head; childNode!=null; childNode=childNode._next) {
      if (i > 0) sb.append(","); //$NON-NLS-1$
      sb.append(childNode.getData().toString());
      i++;
    }
    sb.append("]").append("}"); //$NON-NLS-1$ //$NON-NLS-2$
    return sb.toString();
  }
}
