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

package org.polarsys.capella.core.refinement.framework.ui.model;

import java.util.ArrayList;
import java.util.List;

/**
 */
public abstract class SelectionItemNode {
  private boolean _isChecked = false;
  private SelectionItemNode _parent = null;
  private List<SelectionItemNode> _children = null;

  /**
   * Constructor
   */
  public SelectionItemNode() {
    _children = new ArrayList<SelectionItemNode>();
  }

  /**
   * @return Returns the name.
   */
  public abstract String getName();

  /**
   * @return Returns the name.
   */
  public abstract String getFullName();

  /**
   * @return Returns the data attached to this item.
   */
  public abstract Object getData();

  /**
   * @return Returns the data attached to this item.
   */
  public abstract Object getSrc();

  /**
   * @return Returns the children.
   */
  public List<SelectionItemNode> getChildren() {
    return _children;
  }

  /**
   * @return Returns the parent.
   */
  public SelectionItemNode getParent() {
    return _parent;
  }

  /**
   * @param parent The parent to set.
   */
  public void setParent(SelectionItemNode parent) {
    _parent = parent;
  }

  /**
   * @param checked_p
   */
  public void setIsChecked(boolean checked_p) {
    _isChecked = checked_p;
  }

  /**
   * Checks if the current item has some children checked.
   * @return <code>True</code> if some children are checked else <code>false</code>.
   */
  public boolean hasChildrenChecked() {
    boolean result = false;

    for (SelectionItemNode node : _children) {
      result |= node._isChecked;
    }

    return result;
  }

  /**
   * Checks if the current item has all children checked.
   * @return <code>True</code> if all children are checked else <code>false</code>.
   */
  public boolean hasAllChildrenChecked() {
    boolean result = true;

    for (SelectionItemNode node : _children) {
      result &= node._isChecked;
    }

    return result;
  }

  /**
   * @param node
   */
  public void addChild(SelectionItemNode node) {
    if (_children == null)
      _children = new ArrayList<SelectionItemNode>();
    _children.add(node);
    node.setParent(this);
  }

  /**
   * 
   */
  @Override
  public String toString() {
    return "Node" + "[" + getName() + "," + getChildren() + "]"; //$NON-NLS-1$//$NON-NLS-2$ //$NON-NLS-3$ //$NON-NLS-4$
  }
}
