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

import java.util.ArrayList;
import java.util.List;

import org.eclipse.jface.dialogs.IDialogSettings;

public abstract class AbstractCapellaHistory {
  protected static final int HISTORY_SIZE = 5;

  protected static final String SECTION_SEARCH = "org.polarsys.capella.core.ui.search.page.replace";
  protected static final String SECTION_SEARCH_REPLACE_PATTERN = "replace.pattern";

  protected static final String SECTION_HISTORY_PREFIX = "history.replace";
  protected static final String SECTION_HISTORY_COUNT = "history.count.replace";

  protected static final String SECTION_SEARCH_FIELD_PREFIX = "field.replace";
  protected static final String SECTION_SEARCH_FIELD_COUNT = "field.count.replace";

  protected static final String SECTION_SEARCH_PROJECT_PREFIX = "project.replace";
  protected static final String SECTION_SEARCH_PROJECT_COUNT = "project.count.replace";

  protected AbstractCapellaHistory() {}

  public static List<CapellaSearchSettings> getAllSearchSettings() {
    List<CapellaSearchSettings> capellaSearchSettingsInHistory = new ArrayList<>();

    IDialogSettings capellaSearchSection = getDialogSettingsForCapellaSearch();

    try {
      int currentHistoryCount = capellaSearchSection.getInt(SECTION_HISTORY_COUNT);
      for (int i = currentHistoryCount - 1; i >= 0; i--) {
        capellaSearchSettingsInHistory.add(createSearchSettingsFromHistoryIndex(i));
      }
    } catch (NumberFormatException e) {
      // nothing
    }
    return capellaSearchSettingsInHistory;
  }

  private static CapellaSearchSettings createSearchSettingsFromHistoryIndex(int historyIndex) {
    IDialogSettings searchHistorySection = getDialogSettingsForHistoryIndex(historyIndex);
    CapellaSearchSettings searchSettings = new CapellaSearchSettings();
    if (searchHistorySection != null) {
      setSearchSettings(searchSettings, searchHistorySection);
      searchSettings.setReplaceTextPattern(searchHistorySection.get(SECTION_SEARCH_REPLACE_PATTERN));

      try {
        int projectsCount = searchHistorySection.getInt(SECTION_SEARCH_PROJECT_COUNT);
        for (int i = 0; i < projectsCount; i++) {
          searchSettings.addProject(searchHistorySection.get(SECTION_SEARCH_PROJECT_PREFIX + i));
        }
        int fieldsCount = searchHistorySection.getInt(SECTION_SEARCH_FIELD_COUNT);
        for (int i = 0; i < fieldsCount; i++) {
          String searchFieldText = searchHistorySection.get(SECTION_SEARCH_FIELD_PREFIX + i);
          searchSettings.addSearchField(CapellaSearchField.valueOf(searchFieldText));
        }
      } catch (NumberFormatException e) {
        // Nothing
      }
    }
    return searchSettings;
  }
  
  protected static void setSearchSettings(CapellaSearchSettings searchSettings, IDialogSettings searchHistorySection) {}

  public static void appendSearchSettings(CapellaSearchSettings capellaSearchSettings) {
    IDialogSettings capellaSearchSection = getDialogSettingsForCapellaSearch();
    int historyIndex = getHistoryIndex(capellaSearchSettings);
    if (historyIndex == -1) { // not yet in history
      int historyPointToAppend = 0;
      try {
        int currentHistoryCount = capellaSearchSection.getInt(SECTION_HISTORY_COUNT);
        if (currentHistoryCount == HISTORY_SIZE) {
          for (int i = 0; i < currentHistoryCount - 1; i++) {
            // If reach the history size limit, we remove the oldest history point (the one at index 0)
            saveSearchSettingsToHistoryPoint(createSearchSettingsFromHistoryIndex(i + 1), i);
          }
          historyPointToAppend = HISTORY_SIZE - 1;
        } else {
          // Otherwise save to the last index
          historyPointToAppend = currentHistoryCount;
        }
      } catch (NumberFormatException e) {
        // If the exception, that means the key history.count is not yet set and the history is empty
        // So the history point to append is 0
      }

      saveSearchSettingsToHistoryPoint(capellaSearchSettings, historyPointToAppend);
      capellaSearchSection.put(SECTION_HISTORY_COUNT, historyPointToAppend + 1);
    }
  }

  private static IDialogSettings getDialogSettingsForCapellaSearch() {
    IDialogSettings dialogSettings = Activator.getDefault().getDialogSettings();
    IDialogSettings section = dialogSettings.getSection(SECTION_SEARCH);
    if (section == null) {
      section = dialogSettings.addNewSection(SECTION_SEARCH);
    }
    return section;
  }

  private static IDialogSettings getDialogSettingsForHistoryIndex(int historyIndex) {
    IDialogSettings capellaSearch = getDialogSettingsForCapellaSearch();

    IDialogSettings searchSectionAtHistoryPoint = capellaSearch.getSection(SECTION_HISTORY_PREFIX + historyIndex);
    if (searchSectionAtHistoryPoint == null) {
      searchSectionAtHistoryPoint = capellaSearch.addNewSection(SECTION_HISTORY_PREFIX + historyIndex);
    }

    return searchSectionAtHistoryPoint;
  }

  private static void saveSearchSettingsToHistoryPoint(CapellaSearchSettings capellaSearchSettings, int historyIndex) {
    IDialogSettings searchHistorySection = getDialogSettingsForHistoryIndex(historyIndex);
    if (searchHistorySection != null) {
      searchHistorySection.put(SECTION_SEARCH_REPLACE_PATTERN, capellaSearchSettings.getReplaceTextPattern());
      setSearchHistorySettings(capellaSearchSettings, searchHistorySection);
      int projectsCount = 0;
      for (String project : capellaSearchSettings.getProjects()) {
        searchHistorySection.put(SECTION_SEARCH_PROJECT_PREFIX + projectsCount, project);
        projectsCount++;
      }
      searchHistorySection.put(SECTION_SEARCH_PROJECT_COUNT, projectsCount);

      int fieldsCount = 0;
      for (CapellaSearchField searchField : capellaSearchSettings.getSearchFields()) {
        searchHistorySection.put(SECTION_SEARCH_FIELD_PREFIX + fieldsCount, searchField.toString());
        fieldsCount++;
      }
      searchHistorySection.put(SECTION_SEARCH_FIELD_COUNT, fieldsCount);
    }
  }
  
  protected static void setSearchHistorySettings(CapellaSearchSettings searchSettings, IDialogSettings searchHistorySection) {
  }
  
  private static int getHistoryIndex(CapellaSearchSettings capellaSearchSettings) {
    IDialogSettings capellaSearchSection = getDialogSettingsForCapellaSearch();
    try {
      int currentHistoryCount = capellaSearchSection.getInt(SECTION_HISTORY_COUNT);
      for (int i = 0; i < currentHistoryCount; i++) {
        CapellaSearchSettings searchSettingsInHistory = createSearchSettingsFromHistoryIndex(i);
        if (searchSettingsInHistory.equalsForReplace(capellaSearchSettings)) {
          return i;
        }
      }
    } catch (NumberFormatException e) {
      // Nothing
    }
    return -1;
  }

}
