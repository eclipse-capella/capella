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

public class CapellaReplaceHistory extends AbstractCapellaHistory {
  private static CapellaReplaceHistory instance;

  protected CapellaReplaceHistory() {
  }

  public static CapellaReplaceHistory getInstance() {
    if (instance == null) {
      instance = new CapellaReplaceHistory();
    }
    return instance;
  }

  @Override
  protected void setSearchSettings(CapellaSearchSettings searchSettings, IDialogSettings searchHistorySection) {
    searchSettings.setReplaceTextPattern(searchHistorySection.get(SECTION_SEARCH_REPLACE_PATTERN));
  }

  @Override
  protected void setSearchHistorySettings(CapellaSearchSettings capellaSearchSettings,
      IDialogSettings searchHistorySection) {
    searchHistorySection.put(SECTION_SEARCH_REPLACE_PATTERN, capellaSearchSettings.getReplaceTextPattern());
  }
}
