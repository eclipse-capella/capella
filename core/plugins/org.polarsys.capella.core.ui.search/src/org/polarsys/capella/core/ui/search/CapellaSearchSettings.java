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

import java.util.Collections;
import java.util.HashSet;
import java.util.Set;
import java.util.regex.Pattern;
import java.util.regex.PatternSyntaxException;

import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.Status;
import org.eclipse.search.internal.core.text.PatternConstructor;

/*
 * Class used to keep the settings (parameters) of a search action.
 * We can use this class in the current search to update the ui accordingly to what the user has selected.
 * Also we use this class to keep a history of the search and be able to apply the search settings in a next search or replace.
 */
public class CapellaSearchSettings {
  private boolean isCaseSensitive = false;
  private boolean isRegExSearch = false;
  private boolean isWholeWord = false;
  private String textPattern = null;
  private String replaceTextPattern = null;
  private Set<String> projects = new HashSet<>();
  private Set<Object> searchAttributes = new HashSet<>();
  private Set<Object> searchMetaClasses = new HashSet<>();
  private boolean isAbstractChecked = false;
  private boolean isSemanticChecked = true;
  
  public void addProject(String name) {
    projects.add(name);
  }

  public Set<String> getProjects() {
    return Collections.unmodifiableSet(projects);
  }

  public boolean containProject(String name) {
    return projects.contains(name);
  }

  public void clearProjects() {
    projects.clear();
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

  public Set<Object> getSearchAttributes() {
    return searchAttributes;
  }

  public void setSearchAttributes(Set<Object> searchAttributes) {
    this.searchAttributes = searchAttributes;
  }

  public Set<Object> getSearchMetaClasses() {
    return searchMetaClasses;
  }

  public void setSearchMetaClasses(Set<Object> searchMetaClasses) {
    this.searchMetaClasses = searchMetaClasses;
  }
  
  public void addSearchMetaClass(Object searchMetaClasse) {
    this.searchMetaClasses.add(searchMetaClasses);
  }
  
  public void removeSearchMetaClass(Object searchMetaClasse) {
    this.searchMetaClasses.remove(searchMetaClasses);
  }

  public void setAbstractChecked(boolean isAbstractChecked) {
    this.isAbstractChecked = isAbstractChecked;
  }

  public void setSemanticChecked(boolean isSemanticChecked) {
    this.isSemanticChecked = isSemanticChecked;
  }

  // method used to check the search settings (that we entered text, selected at least one mettaclass or attribute etc) 
  public IStatus validate() {
    if (textPattern == null || textPattern.isEmpty()) {
      return new Status(IStatus.ERROR, Activator.PLUGIN_ID,
          CapellaSearchConstants.CapellaSearchPage_Validation_Message_Pattern_Empty);
    }
    
    if (isRegExSearch) {
      try {
        CapellaSearchSettings.createPattern(textPattern, isCaseSensitive, isRegExSearch, isWholeWord);
      } catch (PatternSyntaxException e) {
        return new Status(IStatus.ERROR, Activator.PLUGIN_ID, e.getDescription());
      }
    }

    if (searchMetaClasses.isEmpty()) {
      return new Status(IStatus.ERROR, Activator.PLUGIN_ID,
          CapellaSearchConstants.CapellaSearchPage_Validation_Message_SearchMetaClass_Selection);
    }
    
    if (searchAttributes.isEmpty()) {
      return new Status(IStatus.ERROR, Activator.PLUGIN_ID,
          CapellaSearchConstants.CapellaSearchPage_Validation_Message_SearchAttribute_Selection);
    }
    
    if (!isAbstractChecked && !isSemanticChecked) {
      return new Status(IStatus.ERROR, Activator.PLUGIN_ID,
          CapellaSearchConstants.CapellaSearchPage_Validation_Message_SearchFilter_Selection);
    }

    return Status.OK_STATUS;
  }

  public static Pattern createPattern(String textPattern, boolean isCaseSensitive, boolean isRegExSearch,
      boolean isWholeWord) {
    return PatternConstructor.createPattern(textPattern, isRegExSearch, false, isCaseSensitive, isWholeWord);
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

    if (this.searchAttributes == null ? that.searchAttributes != null
        : !this.searchAttributes.equals(that.searchAttributes)) {
      return false;
    }
    
    if (this.searchMetaClasses == null ? that.searchMetaClasses != null
        : !this.searchMetaClasses.equals(that.searchMetaClasses)) {
      return false;
    }

    if (this.projects == null ? that.projects != null : !this.projects.equals(that.projects)) {
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
    hashCode += 7 * hashCode + searchAttributes.hashCode();
    hashCode += 7 * hashCode + searchMetaClasses.hashCode();
    hashCode += 7 * hashCode + projects.hashCode();
    return hashCode;
  }
}
