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

import org.eclipse.core.resources.IProject;
import org.eclipse.search.ui.text.Match;

/**
 * Entity responsible of model result hierarchical organization.
 */
public class CapellaSearchMatchEntry extends Match {

  private String text;
  
  private IProject project;
  
  private Object attribute;

  public CapellaSearchMatchEntry(Object source, String text,
      IProject project, Object attribute) {
    super(source, -1, -1);
    this.project = project;
    this.text = text;
    this.attribute = attribute;
  }

  public String getText() {
    return text;
  }
  
  public void setText(String _text) {
    text = _text;
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
    if (getAttribute() == null) {
      if (other.getAttribute() != null)
        return false;
    } else if (!getAttribute().equals(other.getAttribute()))
      return false;
    if (getText() == null) {
      if (other.getText() != null)
        return false;
    } else if (!getText().equals(other.getText()))
      return false;
    if (getProject() == null) {
      if (other.getProject() != null)
        return false;
    } else if (!getProject().equals(other.getProject()))
      return false;
    return true;
  }
}
