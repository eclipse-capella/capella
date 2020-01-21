/*******************************************************************************
 * Copyright (c) 2007, 2008 Anyware Technologies and others. All rights reserved. This program
 * and the accompanying materials are made available under the terms of the
 * Eclipse Public License v1.0 which accompanies this distribution, and is
 * available at http://www.eclipse.org/legal/epl-v10.html
 * 
 * AbstractModelSearchResultEntry.java
 * 
 * Contributors: 
 * 		Lucas Bigeardel (Anyware Technologies) - initial API and implementation
 * 		Lucas Bigeardel - javadoc
 ******************************************************************************/

package org.polarsys.capella.core.ui.search;

/*-
 * #%L
 * com.thalesgroup.mde.capella.search.parent
 * %%
 * Copyright (C) 2018 - 2019 Thales DMS France SAS
 * %%
 * All rights reserved. This program and the accompanying materials
 *  are made available under the terms of the Eclipse Public License v1.0
 *  which accompanies this distribution, and is available at
 *  http://www.eclipse.org/legal/epl-v10.html
 *    
 *  Contributors:
 *    Thales DMS France SAS - initial API and implementation
 * #L%
 */

import java.util.Collection;
import java.util.HashSet;
import java.util.Stack;

import org.eclipse.search.ui.text.Match;

/**
 * Entity responsible of model result hierarchical organization.
 */
public abstract class AbstractCapellaSearchResultEntry extends Match {
  /** EMF Resource Target of a search query result */
  protected Object target;

  /** EObject source for current query search match occurence (eg. the match itself). */
  protected Object source;

  /** precedent AbstractCapellaSearchResultEntry in the hierarchical search query result thread */
  protected AbstractCapellaSearchResultEntry parent;

  /** true if a real match (eg. not an intermediary structural object for ordering purpose only), false otherwise */
  protected boolean matchedOnce;

  /**
   * all AbstractCapellaSearchResultEntry children, potentially matches themselves or intermediary structural object(s)
   * for ordering purposes only)
   */
  private Collection<Object> children;

  public AbstractCapellaSearchResultEntry(AbstractCapellaSearchResultEntry _parent, Object _target, Object _source,
      boolean _matched) {
    super(_parent, -1, -1);
    target = _target;
    source = _source;
    parent = _parent;

    children = new HashSet<Object>();
    setMatchedOnce(_matched);
  }

  /**
   * @return AbstractCapellaSearchResultEntry children, potentially matches themselves or intermediary structural
   *         object(s) for ordering purposes only, void Collection otherwise
   */
  public Collection<Object> getResults() {
    return children;
  }

  /**
   * Adds AbstractCapellaSearchResultEntry child, potentially match itself or intermediary structural object(s) for
   * ordering purposes only.
   */
  public void addChildren(AbstractCapellaSearchResultEntry e) {
    children.add(e);
  }

  /**
   * Remove AbstractCapellaSearchResultEntry child, potentially match itself or intermediary structural object(s) for
   * ordering purposes only.
   */
  public void removeChildren(AbstractCapellaSearchResultEntry e) {
    children.remove(e);
  }

  /////////////////////////
  // Getters / Setters
  /////////////////////////

  /**
   * @return EObject source for current query search match occurence (eg. the match itself).
   */
  public Object getSource() {
    return source;
  }

  /**
   * @return AbstractCapellaSearchResultEntry parent, potentially match itself or intermediary structural object(s) for
   *         ordering purposes only.
   */
  public AbstractCapellaSearchResultEntry getParent() {
    return parent;
  }

  /**
   * @return EMF Resource Target of the current search query result.
   */
  public Object getTarget() {
    return target;
  }

  /**
   * Sets AbstractCapellaSearchResultEntry parent, potentially match itself or intermediary structural object(s) for
   * ordering purposes only.
   */
  public void setParent(AbstractCapellaSearchResultEntry p) {
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
  public Stack<AbstractCapellaSearchResultEntry> getHierarchyFromRootToLeaf() {
    Stack<AbstractCapellaSearchResultEntry> hierarchy = new Stack<AbstractCapellaSearchResultEntry>();
    getHierarchyFromRootToLeaf(this, hierarchy);
    return hierarchy;
  }

  /**
   * @return AbstractCapellaSearchResultEntry hierarchy in a root to leaf order (eg. tree like), void Stack otherwise.
   */
  private void getHierarchyFromRootToLeaf(AbstractCapellaSearchResultEntry e,
      Stack<AbstractCapellaSearchResultEntry> hierarchy) {
    if (e instanceof AbstractCapellaSearchResultEntry) {
      hierarchy.push(e);
      getHierarchyFromRootToLeaf(e.getParent(), hierarchy);
    }
  }

  /**
   * @return search query match fully qualified name, void String otherwise.
   */
  public abstract String getModelResultFullyQualifiedName();

  @Override
  public int hashCode() {
    final int prime = 31;
    int result = 1;
    result = prime * result + ((parent == null) ? 0 : parent.hashCode());
    result = prime * result + ((source == null) ? 0 : source.hashCode());
    result = prime * result + ((target == null) ? 0 : target.hashCode());
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
    final AbstractCapellaSearchResultEntry other = (AbstractCapellaSearchResultEntry) obj;
    if (source == null) {
      if (other.source != null)
        return false;
    } else if (!source.equals(other.source))
      return false;
    if (target == null) {
      if (other.target != null)
        return false;
    } else if (!target.equals(other.target))
      return false;
    if (parent == null) {
      if (other.parent != null)
        return false;
    } else if (!parent.equals(other.parent))
      return false;
    return true;
  }
}
