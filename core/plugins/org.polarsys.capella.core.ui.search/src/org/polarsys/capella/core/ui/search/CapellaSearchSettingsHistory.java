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

import org.eclipse.jface.dialogs.IDialogSettings;

public class CapellaSearchSettingsHistory extends AbstractCapellaHistory {

  private static final String SECTION_SEARCH_PATTERN = "pattern";
  private static final String SECTION_SEARCH_REGEX = "regex";
  private static final String SECTION_SEARCH_CASE_SENSITIVE = "case.sensitive";
  
  private static CapellaSearchSettingsHistory instance;

  protected CapellaSearchSettingsHistory() {}
  
  public static CapellaSearchSettingsHistory getInstance() {
    if(instance == null) {
      instance = new CapellaSearchSettingsHistory();
    }
    return instance;
  }

  protected void setSearchSettings(CapellaSearchSettings searchSettings, IDialogSettings searchHistorySection) {
    searchSettings.setTextPattern(searchHistorySection.get(SECTION_SEARCH_PATTERN));
    searchSettings.setRegExSearch(searchHistorySection.getBoolean(SECTION_SEARCH_REGEX));
    searchSettings.setCaseSensitive(searchHistorySection.getBoolean(SECTION_SEARCH_CASE_SENSITIVE));
  }
  
  protected void setSearchHistorySettings(CapellaSearchSettings capellaSearchSettings, IDialogSettings searchHistorySection) {
    searchHistorySection.put(SECTION_SEARCH_PATTERN, capellaSearchSettings.getTextPattern());
    searchHistorySection.put(SECTION_SEARCH_REGEX, capellaSearchSettings.isRegExSearch());
    searchHistorySection.put(SECTION_SEARCH_CASE_SENSITIVE, capellaSearchSettings.isCaseSensitive());
  }
}
