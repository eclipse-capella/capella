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
package org.polarsys.capella.core.ui.search.match;

import java.util.Objects;
import java.util.regex.Pattern;

import org.eclipse.core.resources.IProject;

public abstract class SearchMatchChild extends SearchMatch {

  protected SearchMatch parent;

  public SearchMatchChild(Object source, String text, IProject project, SearchMatch parent) {
    super(source, text, project);
    this.parent = parent;
  }

  public SearchMatch getParent() {
    return parent;
  }

  /*
   * Force the children to override the replace method.
   */
  @Override
  public abstract boolean replace(Pattern searchPattern, String replacement);

  @Override
  public Object getAttribute() {
    return parent.getAttribute();
  }

  @Override
  public int hashCode() {
    final int prime = 31;
    int result = super.hashCode();
    result = prime * result + Objects.hash(parent.attribute, parent.displayText, parent.originalText, parent.project);
    return result;
  }

  @Override
  public boolean equals(Object obj) {
    if (this == obj) {
      return true;
    }
    if (!super.equals(obj)) {
      return false;
    }
    if (!(obj instanceof SearchMatchChild)) {
      return false;
    }

    SearchMatchChild other = (SearchMatchChild) obj;
    SearchMatch otherParent = other.parent;

    return Objects.equals(parent.attribute, otherParent.attribute)
        && Objects.equals(displayText, otherParent.displayText)
        && Objects.equals(parent.originalText, otherParent.originalText)
        && Objects.equals(parent.project, otherParent.project);
  }

}
