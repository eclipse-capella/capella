/*******************************************************************************
 * Copyright (c) 2020 THALES GLOBAL SERVICES.
 * 
 * This program and the accompanying materials are made available under the
 * terms of the Eclipse Public License 2.0 which is available at
 * http://www.eclipse.org/legal/epl-2.0
 * 
 * SPDX-License-Identifier: EPL-2.0
 * 
 * Contributors:
 *    Thales - initial API and implementation
 *******************************************************************************/
package org.polarsys.capella.core.ui.search;

import java.util.Collections;
import java.util.HashSet;
import java.util.Set;
import java.util.regex.Pattern;
import java.util.regex.PatternSyntaxException;

import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.Status;
import org.eclipse.search.internal.core.text.PatternConstructor;
import org.osgi.framework.FrameworkUtil;
import org.polarsys.capella.core.ui.search.searchfor.item.SearchForNoteItem;

/*
 * Class used to keep the settings (parameters) of a search action.
 * We can use this class in the current search to update the ui accordingly to what the user has selected.
 * Also we use this class to keep a history of the search and be able to apply the search settings in a next search or replace.
 */
@SuppressWarnings("restriction")
public class CapellaSearchSettings {
  private boolean isCaseSensitive = false;
  private boolean isRegExSearch = false;
  private boolean isWholeWord = false;
  private String textPattern = null;
  private String replaceTextPattern = null;
  private Set<Object> objectsToSearch = new HashSet<>();
  private Set<Object> searchAttributeItems = new HashSet<>();
  private Set<Object> searchMetaClassItems = new HashSet<>();
  private boolean abstractChecked = true;
  private boolean nonSemanticChecked = true;
  private int scope;

  public void addObjectToSearch(Object objectToSearch) {
    objectsToSearch.add(objectToSearch);
  }

  public Set<Object> getObjectsToSearch() {
    return Collections.unmodifiableSet(objectsToSearch);
  }

  public boolean containProject(String name) {
    return objectsToSearch.contains(name);
  }

  public void clearProjects() {
    objectsToSearch.clear();
  }

  public boolean isCaseSensitive() {
    return isCaseSensitive;
  }

  public void setCaseSensitive(boolean isCaseSensitive) {
    this.isCaseSensitive = isCaseSensitive;
  }

  public boolean isRegExSearch() {
    return isRegExSearch;
  }

  public void setRegExSearch(boolean isRegExSearch) {
    this.isRegExSearch = isRegExSearch;
  }

  public boolean isWholeWord() {
    return isWholeWord;
  }

  public void setWholeWord(boolean isWholeWord) {
    this.isWholeWord = isWholeWord;
  }

  public String getTextPattern() {
    return textPattern;
  }

  public void setTextPattern(String textPattern) {
    this.textPattern = textPattern;
  }

  public String getReplaceTextPattern() {
    return replaceTextPattern;
  }

  public void setReplaceTextPattern(String replaceTextPattern) {
    this.replaceTextPattern = replaceTextPattern;
  }

  public Set<Object> getSearchAttributeItems() {
    return searchAttributeItems;
  }

  public void setSearchAttributeItems(Set<Object> searchAttributeItems) {
    this.searchAttributeItems = searchAttributeItems;
  }

  public Set<Object> getSearchClassItems() {
    return searchMetaClassItems;
  }

  public void setSearchClassItems(Set<Object> searchClassItems) {
    this.searchMetaClassItems = searchClassItems;
  }

  public void setAbstractChecked(boolean abstractChecked) {
    this.abstractChecked = abstractChecked;
  }

  public void setNonSemanticChecked(boolean nonSemanticChecked) {
    this.nonSemanticChecked = nonSemanticChecked;
  }

  public boolean isAbstractChecked() {
    return abstractChecked;
  }

  public boolean isNonSemanticChecked() {
    return nonSemanticChecked;
  }

  public void setScope(int scope) {
    this.scope = scope;
  }

  public int getScope() {
    return this.scope;
  }

  // method used to check the search settings (that we entered text, selected at least one mettaclass or attribute etc)
  public IStatus validate() {
    if (textPattern == null || textPattern.isEmpty()) {
      return new Status(IStatus.ERROR, FrameworkUtil.getBundle(Activator.class).getSymbolicName(),
          CapellaSearchConstants.CapellaSearchPage_Validation_Message_Pattern_Empty);
    }

    if (isRegExSearch) {
      if (isWholeWord) {
        return new Status(IStatus.ERROR, FrameworkUtil.getBundle(Activator.class).getSymbolicName(),
            CapellaSearchConstants.CapellaSearchPage_Validation_Message_Whole_Word_Same_Time_Regex);
      }

      try {
        CapellaSearchSettings.createPattern(textPattern, isCaseSensitive, isRegExSearch, isWholeWord);
      } catch (PatternSyntaxException e) {
        return new Status(IStatus.ERROR, FrameworkUtil.getBundle(Activator.class).getSymbolicName(),
            e.getDescription());
      }
    }

    if (searchMetaClassItems.isEmpty()) {
      return new Status(IStatus.ERROR, FrameworkUtil.getBundle(Activator.class).getSymbolicName(),
          CapellaSearchConstants.CapellaSearchPage_Validation_Message_SearchMetaClass_Selection);
    }

    if (searchAttributeItems.isEmpty()
        && !searchMetaClassItems.stream().anyMatch(SearchForNoteItem.class::isInstance)) {
      return new Status(IStatus.ERROR, FrameworkUtil.getBundle(Activator.class).getSymbolicName(),
          CapellaSearchConstants.CapellaSearchPage_Validation_Message_SearchAttribute_Selection);
    }

    return Status.OK_STATUS;
  }

  public static Pattern createPattern(String textPattern, boolean isCaseSensitive, boolean isRegExSearch,
      boolean isWholeWord) {
    return PatternConstructor.createPattern(textPattern, isRegExSearch, true, isCaseSensitive, isWholeWord);
  }

  public Pattern createPattern() {
    return createPattern(textPattern, isCaseSensitive, isRegExSearch, isWholeWord);
  }

  @Override
  public boolean equals(Object arg0) {
    if (arg0 == this) {
      return true;
    }

    if (!(arg0 instanceof CapellaSearchSettings)) {
      return false;
    }

    CapellaSearchSettings that = (CapellaSearchSettings) arg0;

    if (this.isCaseSensitive != that.isCaseSensitive) {
      return false;
    }

    if (this.isRegExSearch != that.isRegExSearch) {
      return false;
    }

    if (this.textPattern == null ? that.textPattern != null : !this.textPattern.equals(that.textPattern)) {
      return false;
    }

    if (this.searchAttributeItems == null ? that.searchAttributeItems != null
        : !this.searchAttributeItems.equals(that.searchAttributeItems)) {
      return false;
    }

    if (this.searchMetaClassItems == null ? that.searchMetaClassItems != null
        : !this.searchMetaClassItems.equals(that.searchMetaClassItems)) {
      return false;
    }

    if (this.objectsToSearch == null ? that.objectsToSearch != null
        : !this.objectsToSearch.equals(that.objectsToSearch)) {
      return false;
    }

    if (this.scope != that.scope) {
      return false;
    }

    return true;
  }

  @Override
  public int hashCode() {
    int hashCode = 3;
    hashCode += 7 * hashCode + (isCaseSensitive ? 1 : 0);
    hashCode += 7 * hashCode + (isRegExSearch ? 1 : 0);
    hashCode += 7 * hashCode + (textPattern == null ? 0 : textPattern.hashCode());
    hashCode += 7 * hashCode + (replaceTextPattern == null ? 0 : replaceTextPattern.hashCode());
    hashCode += 7 * hashCode + searchAttributeItems.hashCode();
    hashCode += 7 * hashCode + searchMetaClassItems.hashCode();
    hashCode += 7 * hashCode + objectsToSearch.hashCode();
    hashCode += 7 * hashCode + scope;
    return hashCode;
  }
}
