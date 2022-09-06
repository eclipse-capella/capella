/*******************************************************************************
 * Copyright (c) 2020, 2022 THALES GLOBAL SERVICES.
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
package org.polarsys.capella.core.sirius.analysis.preferences;

import org.eclipse.jface.preference.IPreferenceStore;
import org.polarsys.capella.core.commands.preferences.service.AbstractPreferencesInitializer;
import org.polarsys.capella.core.commands.preferences.service.ScopedCapellaPreferencesStore;
import org.polarsys.capella.core.platform.sirius.ui.actions.CapellaActionsActivator;

public class TitleBlockPreferencesInitializer extends AbstractPreferencesInitializer {

  public TitleBlockPreferencesInitializer() {
    super(CapellaActionsActivator.PLUGIN_ID);
  }

  @Override
  public void initializeDefaultPreferences() {
    IPreferenceStore preferenceStore = ScopedCapellaPreferencesStore.getInstance(CapellaActionsActivator.PLUGIN_ID);
    preferenceStore.setDefault(TitleBlockPreferencePage.COLUMNS_NUMBER_PREFERENCE_STORE, 2);
    preferenceStore.setDefault(TitleBlockPreferencePage.LINES_NUMBER_PREFERENCE_STORE, 2);
    preferenceStore.setDefault(TitleBlockPreferencePage.TABLE_CONTENT_PREFERENCE_STORE,
        TitleBlockPreferencePage.DEFAULT_TABLE);
    preferenceStore.setDefault(TitleBlockPreferencePage.DEFAULT_TITLEBLOCK_PREFERENCE_STORE, false);
  }

  /**
   * @return int the number of lines a Diagram Title Block has
   */
  public static int getLinesNumber() {
    return getInt(TitleBlockPreferencePage.LINES_NUMBER_PREFERENCE_STORE, true);
  }

  /**
   * @return int the number of columns a Diagram Title Block has
   */
  public static int getColumnsNumber() {
    return getInt(TitleBlockPreferencePage.COLUMNS_NUMBER_PREFERENCE_STORE, true);
  }

  /**
   * @return String[] the content of a Diagram Title Block
   */
  public static String[] getContentAsArray() {
    return getString(TitleBlockPreferencePage.TABLE_CONTENT_PREFERENCE_STORE, true)
        .split(TitleBlockPreferencePage.SEPARATOR, -1);
  }

  /**
   * @return String[] the content of a Diagram Title Block
   */
  public static String getContent() {
    return getString(TitleBlockPreferencePage.TABLE_CONTENT_PREFERENCE_STORE, true);
  }

  public static boolean isCreateDiagramTitleBlockByDefault() {
    return getBoolean(TitleBlockPreferencePage.DEFAULT_TITLEBLOCK_PREFERENCE_STORE, true);
  }
}
