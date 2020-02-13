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

public class CapellaReplaceHistory extends AbstractCapellaHistory {
  protected CapellaReplaceHistory() {}

  protected static void setSearchSettings(CapellaSearchSettings searchSettings, IDialogSettings searchHistorySection) {
    searchSettings.setReplaceTextPattern(searchHistorySection.get(SECTION_SEARCH_REPLACE_PATTERN));
  }
  
  protected static void setSearchHistorySettings(CapellaSearchSettings capellaSearchSettings, IDialogSettings searchHistorySection) {
    searchHistorySection.put(SECTION_SEARCH_REPLACE_PATTERN, capellaSearchSettings.getReplaceTextPattern());
  }
}
