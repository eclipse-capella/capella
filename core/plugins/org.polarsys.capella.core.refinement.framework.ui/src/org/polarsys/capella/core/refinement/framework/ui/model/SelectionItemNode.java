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

package org.polarsys.capella.core.refinement.framework.ui.model;

import java.util.ArrayList;
import java.util.List;

/**
 */
public abstract class SelectionItemNode {
  private boolean isChecked = false;
  private SelectionItemNode parent = null;
  private List<SelectionItemNode> children = null;

  /**
   * Constructor
   */
  public SelectionItemNode() {
    children = new ArrayList<SelectionItemNode>();
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
    return children;
  }

  /**
   * @return Returns the parent.
   */
  public SelectionItemNode getParent() {
    return parent;
  }

  /**
   * @param parent The parent to set.
   */
  public void setParent(SelectionItemNode parent) {
    this.parent = parent;
  }

  /**
   * @param checked
   */
  public void setIsChecked(boolean checked) {
    isChecked = checked;
  }

  /**
   * Checks if the current item has some children checked.
   * @return <code>True</code> if some children are checked else <code>false</code>.
   */
  public boolean hasChildrenChecked() {
    boolean result = false;

    for (SelectionItemNode node : children) {
      result |= node.isChecked;
    }

    return result;
  }

  /**
   * Checks if the current item has all children checked.
   * @return <code>True</code> if all children are checked else <code>false</code>.
   */
  public boolean hasAllChildrenChecked() {
    boolean result = true;

    for (SelectionItemNode node : children) {
      result &= node.isChecked;
    }

    return result;
  }

  /**
   * @param node
   */
  public void addChild(SelectionItemNode node) {
    if (children == null)
      children = new ArrayList<SelectionItemNode>();
    children.add(node);
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
