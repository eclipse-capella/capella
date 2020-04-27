/*******************************************************************************
 * Copyright (c) 2006, 2019 THALES GLOBAL SERVICES.
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
package org.polarsys.capella.core.ui.semantic.browser.model;

import java.util.ArrayList;
import java.util.Hashtable;
import java.util.List;

import org.polarsys.capella.common.ui.toolkit.browser.category.CategoryImpl;
import org.polarsys.capella.common.ui.toolkit.browser.category.ICategory;
import org.polarsys.capella.common.ui.toolkit.browser.model.ISemanticBrowserModel;

public class SemanticBrowserModel implements ISemanticBrowserModel {

  private static final String PATTERN_CATEGORY_NAME = "All Related Pattern Instances"; //$NON-NLS-1$
  private static final String DIAGRAM_CATEGORY_NAME = "All Related Diagrams"; //$NON-NLS-1$
  protected boolean showPatterns = false; // default value
  protected boolean showDiagrams = true; // default value
  protected boolean limitateTreeExpansion = false; // default value
  protected Hashtable<String, List<String>> browserID2ExpandedObjectHashcodes = new Hashtable<>();

  /**
   * Whether or not the view is listening to page selection events.<br>
   */
  private boolean isListeningToPageSelectionEvents = false;

  @Override
  public boolean doesShowPatterns() {
    return showPatterns;
  }

  @Override
  public void setShowPatterns(boolean showPatterns) {
    this.showPatterns = showPatterns;
  }

  @Override
  public boolean doesShowDiagrams() {
    return showDiagrams;
  }

  @Override
  public void setShowDiagrams(boolean showDiagrams) {
    this.showDiagrams = showDiagrams;
  }

  @Override
  public boolean doesLimitateTreeExpansion() {
    return limitateTreeExpansion;
  }

  @Override
  public void setLimitateTreeExpansion(boolean limitateTreeExpansion) {
    this.limitateTreeExpansion = limitateTreeExpansion;
  }

  @Override
  public boolean doesShowCategory(ICategory category) {
    return !((!showDiagrams && category.getName().equals(DIAGRAM_CATEGORY_NAME))
        || (!showPatterns && category.getName().equals(PATTERN_CATEGORY_NAME)));
  }

  @Override
  public void saveExpandedState(CategoryImpl category, String browserID, boolean expanded) {
    List<String> expandedObjects = getOrCreateHistory(browserID);
    String hashcode = getHashcode(category);
    if (expandedObjects.contains(hashcode) && !expanded) {
      expandedObjects.remove(hashcode);
    } else if (!expandedObjects.contains(hashcode) && expanded) {
      expandedObjects.add(hashcode);
    }
  }

  @Override
  public boolean getExpandedState(ICategory category, String browserID) {
    if (doesLimitateTreeExpansion()) {
      List<String> expandedObjects = browserID2ExpandedObjectHashcodes.get(browserID);
      if (expandedObjects != null && !expandedObjects.contains(getHashcode(category))) {
        return false;
      }
    }
    return true;
  }

  @Override
  public boolean containsExpandedHistory(String browserID) {
    return browserID2ExpandedObjectHashcodes.containsKey(browserID);
  }

  @Override
  public List<String> getOrCreateHistory(String browserID) {
    return browserID2ExpandedObjectHashcodes.computeIfAbsent(browserID, f -> new ArrayList<>());
  }

  public void setListeningToPageSelectionEvents(boolean value) {
    isListeningToPageSelectionEvents = value;
  }

  public boolean isListeningToPageSelectionEvents() {
    return isListeningToPageSelectionEvents;
  }

  private String getHashcode(ICategory category) {
    return category.getName();
  }
}
