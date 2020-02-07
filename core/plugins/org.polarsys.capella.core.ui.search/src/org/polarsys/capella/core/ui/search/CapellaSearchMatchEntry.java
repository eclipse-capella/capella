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
public class CapellaSearchMatchEntry extends Match {

  private Collection<Object> children;
  
  private String text;
  
  private IProject project;
  
  private Object attribute;

  public CapellaSearchMatchEntry(Object source, String text,
      IProject project, Object attribute) {
    super(source, -1, -1);
    this.project = project;
    this.text = text;
    this.attribute = attribute;
    children = new HashSet<Object>();
  }

  public String getText() {
    return text;
  }
  
  public void setText(String _text) {
    text = _text;
  }
  
  public Collection<Object> getChildren() {
    return children;
  }

  public IProject getProject() {
    return project;
  }
  
  /////////////////////////
  // Getters / Setters
  /////////////////////////

  @Override
  public int hashCode() {
    final int prime = 31;
    int result = 1;
    result = prime * result + ((getElement() == null) ? 0 : getElement().hashCode());
    return result;
  }

  public Object getAttribute() {
    return attribute;
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
    final CapellaSearchMatchEntry other = (CapellaSearchMatchEntry) obj;
    if (getElement() == null) {
      if (other.getElement() != null)
        return false;
    } else if (!getElement().equals(other.getElement()))
      return false;
    return true;
  }
}
