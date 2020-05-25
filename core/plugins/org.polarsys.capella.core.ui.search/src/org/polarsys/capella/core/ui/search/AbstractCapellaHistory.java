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

import java.util.ArrayList;
import java.util.List;

import org.eclipse.core.resources.IProject;
import org.eclipse.jface.dialogs.IDialogSettings;
import org.polarsys.capella.core.ui.search.searchfor.SearchForItemCache;
import org.polarsys.capella.core.ui.search.searchfor.item.SearchForAttributeItem;
import org.polarsys.capella.core.ui.search.searchfor.item.SearchForClassItem;

public abstract class AbstractCapellaHistory {
  protected static final int HISTORY_SIZE = 5;

  protected static final String SECTION_SEARCH = "org.polarsys.capella.core.ui.search.page.replace";
  protected static final String SECTION_SEARCH_REPLACE_PATTERN = "replace.pattern";

  protected static final String SECTION_HISTORY_PREFIX = "history.replace";
  protected static final String SECTION_HISTORY_COUNT = "history.count.replace";

  protected static final String SECTION_SEARCH_ATTRIBUTE_PREFIX = "attribute.replace";
  protected static final String SECTION_SEARCH_ATTRIBUTE_COUNT = "attribute.count.replace";

  protected static final String SECTION_SEARCH_METACLASS_PREFIX = "metaclass.replace";
  protected static final String SECTION_SEARCH_METACLASS_COUNT = "metaclass.count.replace";

  protected static final String SECTION_SEARCH_PROJECT_PREFIX = "project.replace";
  protected static final String SECTION_SEARCH_PROJECT_COUNT = "project.count.replace";

  public List<CapellaSearchSettings> getAllSearchSettings() {
    List<CapellaSearchSettings> capellaSearchSettingsInHistory = new ArrayList<>();

    IDialogSettings capellaSearchSection = getDialogSettingsForCapellaSearch();

    try {
      int currentHistoryCount = capellaSearchSection.getInt(SECTION_HISTORY_COUNT);
      for (int i = currentHistoryCount - 1; i >= 0; i--) {
        capellaSearchSettingsInHistory.add(createSearchSettingsFromHistoryIndex(i));
      }
    } catch (NumberFormatException e) {
    }
    return capellaSearchSettingsInHistory;
  }

  private CapellaSearchSettings createSearchSettingsFromHistoryIndex(int historyIndex) {
    IDialogSettings searchHistorySection = getDialogSettingsForHistoryIndex(historyIndex);
    CapellaSearchSettings searchSettings = new CapellaSearchSettings();
    if (searchHistorySection != null) {
      setSearchSettings(searchSettings, searchHistorySection);

      try {
        int projectsCount = searchHistorySection.getInt(SECTION_SEARCH_PROJECT_COUNT);
        for (int i = 0; i < projectsCount; i++) {
          searchSettings.addObjectToSearch(searchHistorySection.get(SECTION_SEARCH_PROJECT_PREFIX + i));
        }
        int fieldsCount = searchHistorySection.getInt(SECTION_SEARCH_ATTRIBUTE_COUNT);
        for (int i = 0; i < fieldsCount; i++) {
          String searchFieldText = searchHistorySection.get(SECTION_SEARCH_ATTRIBUTE_PREFIX + i);
          Object attribute = SearchForItemCache.getInstance().getAttribute(searchFieldText);
          if (attribute != null) {
            searchSettings.getSearchAttributeItems().add(attribute);
          }
        }

        int metaFieldsCount = searchHistorySection.getInt(SECTION_SEARCH_METACLASS_COUNT);
        for (int i = 0; i < metaFieldsCount; i++) {
          String searchFieldText = searchHistorySection.get(SECTION_SEARCH_METACLASS_PREFIX + i);
          Object metaclass = SearchForItemCache.getInstance().getClassItem(searchFieldText);
          if (metaclass != null) {
            searchSettings.getSearchClassItems().add(metaclass);
          } else {
            searchSettings.getSearchClassItems().add(searchFieldText);
          }
        }
      } catch (NumberFormatException e) {
        e.printStackTrace();
      }
    }
    return searchSettings;
  }

  protected abstract void setSearchSettings(CapellaSearchSettings searchSettings, IDialogSettings searchHistorySection);

  public void appendSearchSettings(CapellaSearchSettings capellaSearchSettings) {
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

  private IDialogSettings getDialogSettingsForCapellaSearch() {
    IDialogSettings dialogSettings = Activator.getDefault().getDialogSettings();
    IDialogSettings section = dialogSettings.getSection(SECTION_SEARCH);
    if (section == null) {
      section = dialogSettings.addNewSection(SECTION_SEARCH);
    }
    return section;
  }

  private IDialogSettings getDialogSettingsForHistoryIndex(int historyIndex) {
    IDialogSettings capellaSearch = getDialogSettingsForCapellaSearch();

    IDialogSettings searchSectionAtHistoryPoint = capellaSearch.getSection(SECTION_HISTORY_PREFIX + historyIndex);
    if (searchSectionAtHistoryPoint == null) {
      searchSectionAtHistoryPoint = capellaSearch.addNewSection(SECTION_HISTORY_PREFIX + historyIndex);
    }

    return searchSectionAtHistoryPoint;
  }

  private void saveSearchSettingsToHistoryPoint(CapellaSearchSettings capellaSearchSettings, int historyIndex) {
    IDialogSettings searchHistorySection = getDialogSettingsForHistoryIndex(historyIndex);
    if (searchHistorySection != null) {
      setSearchHistorySettings(capellaSearchSettings, searchHistorySection);
      int projectsCount = 0;
      for (Object project : capellaSearchSettings.getObjectsToSearch()) {
        if (project instanceof IProject) {
          searchHistorySection.put(SECTION_SEARCH_PROJECT_PREFIX + projectsCount, ((IProject) project).getName());
          projectsCount++;
        }
      }
      searchHistorySection.put(SECTION_SEARCH_PROJECT_COUNT, projectsCount);

      int attrFieldsCount = 0;
      for (Object searchAttribute : capellaSearchSettings.getSearchAttributeItems()) {
        SearchForAttributeItem attributeItem = (SearchForAttributeItem) searchAttribute;
        searchHistorySection.put(SECTION_SEARCH_ATTRIBUTE_PREFIX + attrFieldsCount, attributeItem.getText());
        attrFieldsCount++;
      }
      searchHistorySection.put(SECTION_SEARCH_ATTRIBUTE_COUNT, attrFieldsCount);

      int clsFieldsCount = 0;
      for (Object searchMetaClasses : capellaSearchSettings.getSearchClassItems()) {
        if (searchMetaClasses instanceof SearchForClassItem) {
          SearchForClassItem classItem = (SearchForClassItem) searchMetaClasses;
          searchHistorySection.put(SECTION_SEARCH_METACLASS_PREFIX + clsFieldsCount, classItem.getUniqueID());
          clsFieldsCount++;
        } else {
          searchHistorySection.put(SECTION_SEARCH_METACLASS_PREFIX + clsFieldsCount, searchMetaClasses.toString());
          clsFieldsCount++;
        }
      }
      searchHistorySection.put(SECTION_SEARCH_METACLASS_COUNT, clsFieldsCount);
    }
  }

  protected abstract void setSearchHistorySettings(CapellaSearchSettings searchSettings,
      IDialogSettings searchHistorySection);

  private int getHistoryIndex(CapellaSearchSettings capellaSearchSettings) {
    IDialogSettings capellaSearchSection = getDialogSettingsForCapellaSearch();
    try {
      int currentHistoryCount = capellaSearchSection.getInt(SECTION_HISTORY_COUNT);
      for (int i = 0; i < currentHistoryCount; i++) {
        CapellaSearchSettings searchSettingsInHistory = createSearchSettingsFromHistoryIndex(i);
        if (searchSettingsInHistory.equals(capellaSearchSettings)) {
          return i;
        }
      }
    } catch (NumberFormatException e) {
    }
    return -1;
  }

}
