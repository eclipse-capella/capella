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
package org.polarsys.capella.core.sirius.analysis.preferences;

import org.eclipse.jface.preference.IPreferenceStore;
import org.polarsys.capella.core.commands.preferences.service.AbstractPreferencesInitializer;
import org.polarsys.capella.core.platform.sirius.ui.actions.CapellaActionsActivator;
import org.polarsys.capella.core.preferences.Activator;

public class TitleBlockPreferencesInitializer extends AbstractPreferencesInitializer {

  public static final String TABEL_CONTENT_PREFERENCE_STORE = "tableTitleBlock";
  public static final String COLUMNS_NUMBER_PREFERENCE_STORE = "columnsNumberTitleBlock";
  public static final String LINES_NUMBER_PREFERENCE_STORE = "linesNumberTitleBlock";
  public static final String DEFAULT_TITLEBLOCK_PREFERENCE_STORE = "defaultTitleBlock";
  public static final String DEFAULT_TABLE = "Name#feature:name#Synchronized#feature:synchronized#Summary#aql:self.target.summary#Description#feature:documentation";
  private static final String ESCAPED_SEPARATOR = "\\#";
  
  public TitleBlockPreferencesInitializer() {
    super(CapellaActionsActivator.PLUGIN_ID);
  }

  @Override
  public void initializeDefaultPreferences() {
    IPreferenceStore preferenceStore = doGetPreferenceStore();
    preferenceStore.setDefault(COLUMNS_NUMBER_PREFERENCE_STORE, 2);
    preferenceStore.setDefault(LINES_NUMBER_PREFERENCE_STORE, 2);
    preferenceStore.setDefault(TABEL_CONTENT_PREFERENCE_STORE, DEFAULT_TABLE);

    preferenceStore.setDefault(DEFAULT_TITLEBLOCK_PREFERENCE_STORE, true);
  }
  
  
  /**
   * @return IPreferenceStore the default preference store instance
   */
  public static IPreferenceStore doGetPreferenceStore() {
    return Activator.getDefault().getPreferenceStore();
  }

  /**
   * @return int the number of lines a Diagram Title Block has
   */
  public static int getLinesNumber() {
    return doGetPreferenceStore().getInt(LINES_NUMBER_PREFERENCE_STORE);
  }
  
  /**
   * @return int the number of columns a Diagram Title Block has
   */
  public static int getColumnsNumber() {
    return doGetPreferenceStore().getInt(COLUMNS_NUMBER_PREFERENCE_STORE);
  }
  
  /**
   * @return String[] the content of a Diagram Title Block
   */
  public static String[] getContentAsArray() {
    return doGetPreferenceStore().getString(TABEL_CONTENT_PREFERENCE_STORE)
        .split(ESCAPED_SEPARATOR);
  }
  
  /**
   * @return String[] the content of a Diagram Title Block
   */
  public static String getContent() {
    return doGetPreferenceStore().getString(TABEL_CONTENT_PREFERENCE_STORE);
  }
  
  public static boolean isCreateDiagramTitleBlockByDefault() {
    return doGetPreferenceStore().getBoolean(DEFAULT_TITLEBLOCK_PREFERENCE_STORE);
  }
}
