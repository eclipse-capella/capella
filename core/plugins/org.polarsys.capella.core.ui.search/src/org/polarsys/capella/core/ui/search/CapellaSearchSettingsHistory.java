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

import org.eclipse.jface.dialogs.IDialogSettings;

public class CapellaSearchSettingsHistory extends AbstractCapellaHistory {

  private static final String SECTION_SEARCH_PATTERN = "pattern";
  private static final String SECTION_SEARCH_REGEX = "regex";
  private static final String SECTION_SEARCH_CASE_SENSITIVE = "case.sensitive";
  private static final String SECTION_SEARCH_WHOLE_WORD = "whole.word";
  private static final String SECTION_SEARCH_SCOPE = "scope";
  private static final String SECTION_LEFT_ABSTRACT_FILTER_CHECKED = "abstract.filter";
  private static final String SECTION_LEFT_NON_SEMANTIC_FILTER_CHECKED = "non.semantic.filter";

  private static CapellaSearchSettingsHistory instance;

  protected CapellaSearchSettingsHistory() {
  }

  public static CapellaSearchSettingsHistory getInstance() {
    if (instance == null) {
      instance = new CapellaSearchSettingsHistory();
    }
    return instance;
  }

  @Override
  protected void setSearchSettings(CapellaSearchSettings searchSettings, IDialogSettings searchHistorySection) {
    searchSettings.setTextPattern(searchHistorySection.get(SECTION_SEARCH_PATTERN));
    searchSettings.setRegExSearch(searchHistorySection.getBoolean(SECTION_SEARCH_REGEX));
    searchSettings.setCaseSensitive(searchHistorySection.getBoolean(SECTION_SEARCH_CASE_SENSITIVE));
    searchSettings.setWholeWord(searchHistorySection.getBoolean(SECTION_SEARCH_WHOLE_WORD));
    searchSettings.setScope(searchHistorySection.getInt(SECTION_SEARCH_SCOPE));

    searchSettings.setAbstractChecked(searchHistorySection.getBoolean(SECTION_LEFT_ABSTRACT_FILTER_CHECKED));
    searchSettings.setNonSemanticChecked(searchHistorySection.getBoolean(SECTION_LEFT_NON_SEMANTIC_FILTER_CHECKED));
  }

  @Override
  protected void setSearchHistorySettings(CapellaSearchSettings capellaSearchSettings,
      IDialogSettings searchHistorySection) {
    searchHistorySection.put(SECTION_SEARCH_PATTERN, capellaSearchSettings.getTextPattern());
    searchHistorySection.put(SECTION_SEARCH_REGEX, capellaSearchSettings.isRegExSearch());
    searchHistorySection.put(SECTION_SEARCH_CASE_SENSITIVE, capellaSearchSettings.isCaseSensitive());
    searchHistorySection.put(SECTION_SEARCH_WHOLE_WORD, capellaSearchSettings.isWholeWord());
    searchHistorySection.put(SECTION_SEARCH_SCOPE, capellaSearchSettings.getScope());

    searchHistorySection.put(SECTION_LEFT_ABSTRACT_FILTER_CHECKED, capellaSearchSettings.isAbstractChecked());
    searchHistorySection.put(SECTION_LEFT_NON_SEMANTIC_FILTER_CHECKED, capellaSearchSettings.isNonSemanticChecked());
  }
}
