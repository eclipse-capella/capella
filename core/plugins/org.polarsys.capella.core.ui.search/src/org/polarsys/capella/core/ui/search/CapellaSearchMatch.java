/*******************************************************************************
 * Copyright (c) 2019 THALES GLOBAL SERVICES.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *  
 * Contributors:
 *    Thales - initial API and implementation
 *******************************************************************************/
package org.polarsys.capella.core.ui.search;

import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

import org.eclipse.core.resources.IProject;
import org.eclipse.search.ui.text.AbstractTextSearchResult;
import org.eclipse.search.ui.text.Match;

/**
 * As we need to reuse {@link Match} to be compatible with {@link AbstractTextSearchResult}, but a match can have
 * several occurrences, so the CapelaSearchMatch is extended to support that.
 * 
 * To support that:
 * 
 * - The offset attribute will always be set to -1
 * 
 * - The length attribute will always be set to -1
 * 
 * New attributes are added:
 * 
 * - matchOccurrences: collection of matched occurrences
 * 
 * - lineNumber: which line number of the element's text (for instance, description can have several lines)
 * 
 * - lineContent: for instance a line of the description
 * 
 * -- fullContent: for instance the whole description
 * 
 * - searchField: which field of the element is used to compare (name, summary or description, etc.)
 * 
 * - project: which project that the element belongs to
 */
public class CapellaSearchMatch extends Match {

  private final CapellaSearchField searchField;
  private final int lineNumber;
  private final String lineContent;
  private final String fullContent;
  private final IProject project;
  private final Set<CapellaSearchMatchOccurrence> matchOccurrences = new HashSet<>();

  public CapellaSearchMatch(Object element, IProject project, int lineNumber, String lineContent, String fullContent,
      CapellaSearchField searchField) {
    super(element, -1, -1);
    this.searchField = searchField;
    this.lineNumber = lineNumber;
    this.lineContent = lineContent;
    this.fullContent = fullContent;
    this.project = project;
  }

  public int getLineNumber() {
    return lineNumber;
  }

  public CapellaSearchField getSearchField() {
    return searchField;
  }

  public IProject getProject() {
    return project;
  }

  public void addMatchOccurrence(CapellaSearchMatchOccurrence matchOccurrence) {
    matchOccurrences.add(matchOccurrence);
  }

  public void removeMatchOccurrence(CapellaSearchMatchOccurrence matchOccurrence) {
    matchOccurrences.remove(matchOccurrence);
  }

  public Set<CapellaSearchMatchOccurrence> getMatchOccurrences() {
    return Collections.unmodifiableSet(matchOccurrences);
  }

  public String getLineContent() {
    return lineContent;
  }

  public String getFullContent() {
    return fullContent;
  }

  @Override
  public int hashCode() {
    int hashCode = super.hashCode();
    hashCode += hashCode * 7 + searchField.hashCode();
    hashCode += hashCode * 7 + lineNumber;
    hashCode += hashCode * 7 + (lineContent == null ? 0 : lineContent.hashCode());
    hashCode += hashCode * 7 + (fullContent == null ? 0 : fullContent.hashCode());
    hashCode += hashCode * 7 + (project == null ? 0 : project.hashCode());
    hashCode += hashCode * 7 + matchOccurrences.hashCode();
    hashCode += hashCode * 7 + (getElement() == null ? 0 : getElement().hashCode());
    return hashCode;
  }

  @Override
  public boolean equals(Object arg0) {
    if (super.equals(arg0)) {
      return true;
    }

    if (!(arg0 instanceof CapellaSearchMatch)) {
      return false;
    }

    CapellaSearchMatch that = (CapellaSearchMatch) arg0;

    if (this.searchField != that.searchField) {
      return false;
    }

    if (this.lineNumber != that.lineNumber) {
      return false;
    }

    if (this.lineContent == null ? that.lineContent != null : !this.lineContent.equals(that.lineContent)) {
      return false;
    }

    if (this.fullContent == null ? that.fullContent != null : !this.fullContent.equals(that.fullContent)) {
      return false;
    }

    if (this.project == null ? that.project != null : !this.project.equals(that.project)) {
      return false;
    }

    if (!this.matchOccurrences.equals(that.matchOccurrences)) {
      return false;
    }

    if (this.getElement() == null ? that.getElement() != null : !this.getElement().equals(that.getElement())) {
      return false;
    }

    return true;
  }
}
