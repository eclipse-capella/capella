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
package org.polarsys.capella.test.diagram.tools.ju.tb;

import org.eclipse.ui.preferences.ScopedPreferenceStore;
import org.polarsys.capella.common.ui.MdeCommonUiActivator;
import org.polarsys.capella.test.framework.api.BasicTestCase;

public class TitleBlockPreferencePageTest extends BasicTestCase {
  private ScopedPreferenceStore preferenceStore;
  private static final String COLUMNS_NUMBER_TITLE_BLOCK = "columnsNumberTitleBlock";
  private static final String LINES_NUMBER_TITLE_BLOCK = "linesNumberTitleBlock";
  private static final String DEFAULT_TITLE_BLOCK = "defaultTitleBlock";

  public TitleBlockPreferencePageTest() {
    preferenceStore = (ScopedPreferenceStore) MdeCommonUiActivator.getDefault().getPreferenceStore();
  }

  @Override
  public void test() throws Exception {
    updateTitleBlockPreferencePageColumnsNumber(4);
    updateTitleBlockPreferencePageLinesNumber(3);
    updateTitleBlockPreferencePageDefault();

  }

  private void updateTitleBlockPreferencePageColumnsNumber(int value) {
    preferenceStore.setValue(COLUMNS_NUMBER_TITLE_BLOCK, value);
    int newValue = preferenceStore.getInt(COLUMNS_NUMBER_TITLE_BLOCK);
    assertTrue(value == newValue);
  }

  private void updateTitleBlockPreferencePageLinesNumber(int value) {
    preferenceStore.setValue(LINES_NUMBER_TITLE_BLOCK, value);
    int newValue = preferenceStore.getInt(LINES_NUMBER_TITLE_BLOCK);
    assertTrue(value == newValue);
  }

  private void updateTitleBlockPreferencePageDefault() {
    boolean value = preferenceStore.getBoolean(DEFAULT_TITLE_BLOCK);
    preferenceStore.setValue(DEFAULT_TITLE_BLOCK, !value);
    boolean newValue = preferenceStore.getBoolean(DEFAULT_TITLE_BLOCK);
    assertTrue(value != newValue);
  }

}
