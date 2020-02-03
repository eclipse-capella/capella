/*******************************************************************************
 * Copyright (c) 2020 THALES GLOBAL SERVICES.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *  
 * Contributors:
 *    Thales - initial API and implementation
 *******************************************************************************/
package org.polarsys.capella.core.ui.search;

import java.util.Collection;
import java.util.HashSet;
import java.util.Stack;

import org.eclipse.core.resources.IProject;
import org.eclipse.search.ui.text.Match;

/**
 * Entity responsible of model result hierarchical organization.
 */
public abstract class AbstractCapellaSearchEntry extends Match {
  /** precedent AbstractCapellaSearchResultEntry in the hierarchical search query result thread */
  protected AbstractCapellaSearchEntry parent;

  /** true if a real match (eg. not an intermediary structural object for ordering purpose only), false otherwise */
  protected boolean matchedOnce;

  /**
   * all AbstractCapellaSearchResultEntry children, potentially matches themselves or intermediary structural object(s)
   * for ordering purposes only)
   */
  private Collection<Object> children;
  
  protected String text;
  
  private IProject project;

  public AbstractCapellaSearchEntry(AbstractCapellaSearchEntry _parent, Object _source, boolean _matched, IProject _project) {
    super(_source, -1, -1);
    parent = _parent;
    children = new HashSet<Object>();
    project = _project;
    setMatchedOnce(_matched);
  }

  public AbstractCapellaSearchEntry(AbstractCapellaSearchEntry _parent, Object _source,
      String _valuation, boolean _matched, IProject _project) {
    this(_parent, _source, _matched, _project);
    text = _valuation;
  }
  
  public String getText() {
    return text;
  }
  
  public Collection<Object> getChildren() {
    return children;
  }

  public IProject getProject() {
    return project;
  }
  
  /**
   * Adds AbstractCapellaSearchResultEntry child, potentially match itself or intermediary structural object(s) for
   * ordering purposes only.
   */
  public void addChildren(AbstractCapellaSearchEntry e) {
    children.add(e);
  }

  /**
   * Remove AbstractCapellaSearchResultEntry child, potentially match itself or intermediary structural object(s) for
   * ordering purposes only.
   */
  public void removeChildren(AbstractCapellaSearchEntry e) {
    children.remove(e);
  }

  /////////////////////////
  // Getters / Setters
  /////////////////////////

  /**
   * @return AbstractCapellaSearchResultEntry parent, potentially match itself or intermediary structural object(s) for
   *         ordering purposes only.
   */
  public AbstractCapellaSearchEntry getParent() {
    return parent;
  }

  /**
   * Sets AbstractCapellaSearchResultEntry parent, potentially match itself or intermediary structural object(s) for
   * ordering purposes only.
   */
  public void setParent(AbstractCapellaSearchEntry p) {
    parent = p;
  }

  /**
   * @return true if a real match (eg. not an intermediary structural object for ordering purpose only), false otherwise
   */
  public boolean wasMatchedAtleastOnce() {
    return matchedOnce;
  }

  /**
   * Set to true if a real match (eg. not an intermediary structural object for ordering purpose only), false otherwise
   */
  public void setMatchedOnce(boolean matchedOnce) {
    this.matchedOnce = matchedOnce;
  }

  /**
   * @return AbstractCapellaSearchResultEntry hierarchy in a root to leaf order (eg. tree like), void Stack otherwise.
   */
  public Stack<AbstractCapellaSearchEntry> getHierarchyFromRootToLeaf() {
    Stack<AbstractCapellaSearchEntry> hierarchy = new Stack<AbstractCapellaSearchEntry>();
    getHierarchyFromRootToLeaf(this, hierarchy);
    return hierarchy;
  }

  /**
   * @return AbstractCapellaSearchResultEntry hierarchy in a root to leaf order (eg. tree like), void Stack otherwise.
   */
  private void getHierarchyFromRootToLeaf(AbstractCapellaSearchEntry e,
      Stack<AbstractCapellaSearchEntry> hierarchy) {
    if (e instanceof AbstractCapellaSearchEntry) {
      hierarchy.push(e);
      getHierarchyFromRootToLeaf(e.getParent(), hierarchy);
    }
  }

  @Override
  public int hashCode() {
    final int prime = 31;
    int result = 1;
    result = prime * result + ((parent == null) ? 0 : parent.hashCode());
    result = prime * result + ((getElement() == null) ? 0 : getElement().hashCode());
    return result;
  }

  /**
   * Overriding the equals method allows to apply a 1-1 comparison to all elements of a result hierarchy in a reverse
   * order.
   * 
   * This is needed for result hierarchy sub tree insertion algorithms.
   * 
   * A simple example:
   * 
   * LEFT | RIGHT ========|======== A1 | A1 |-B1 | |-B1 |-C1 | |-C1 |-C2 | |-C3
   * 
   * Trying to insert A1->(B1->(C3)) into existing A1->(B1->(C1, C2)) hierarchy implies being able to evaluate until
   * which point two elements of the same level are still equals or not.
   * 
   * Here, each comparison A1 <> A1, B1 <> B1, C1 <> C3 implies their ancestor to be compared back on a 1-1 basis.
   * 
   * This means that comparing left side A1.B1.C1 & right side A1.B1.C1 is something like: C1==C1 && B1==B1 && A1==A1
   * (eg: a back comparison to the root).
   * 
   */
  @Override
  public boolean equals(Object obj) {
    if (this == obj)
      return true;
    if (obj == null)
      return false;
    if (getClass() != obj.getClass())
      return false;
    final AbstractCapellaSearchEntry other = (AbstractCapellaSearchEntry) obj;
    if (getElement() == null) {
      if (other.getElement() != null)
        return false;
    } else if (!getElement().equals(other.getElement()))
      return false;
    if (parent == null) {
      if (other.parent != null)
        return false;
    } else if (!parent.equals(other.parent))
      return false;
    return true;
  }
}
