/*******************************************************************************
 * Copyright (c) 2022 THALES GLOBAL SERVICES.
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
package org.polarsys.capella.core.commands.preferences.initializers;

import org.eclipse.jface.preference.IPreferenceStore;
import org.polarsys.capella.core.commands.preferences.preferences.CapellaDiagramPreferences;
import org.polarsys.capella.core.commands.preferences.service.AbstractPreferencesInitializer;
import org.polarsys.capella.core.preferences.Activator;

/**
 * Initializes default values for @CapellaDiagramPreferencePage preferences
 * 
 * @author etraisnel
 *
 */
public class CapellaDiagramPreferencesInitializer extends AbstractPreferencesInitializer {

  public CapellaDiagramPreferencesInitializer(String pluginID) {
    super(Activator.PLUGIN_ID);
  }

  @Override
  public void initializeDefaultPreferences() {
    IPreferenceStore store = Activator.getDefault().getPreferenceStore();
    store.setDefault(CapellaDiagramPreferences.PREF_DISPLAY_NAVIGATE_ON_DOUBLE_CLICK, true);
    store.setDefault(CapellaDiagramPreferences.PREF_DATE_FORMAT, "yyyy-MM-dd HH:mm:ss");
    store.setDefault(CapellaDiagramPreferences.PREF_DATE_TIMEZONE, CapellaDiagramPreferences.PREF_DATE_TIMEZONE_SYSTEM);
  }

  public static String getFormatDate() {
    return getString(CapellaDiagramPreferences.PREF_DATE_FORMAT, false);
  }

  public static String getTimeZoneForDateFormatting() {
    return getString(CapellaDiagramPreferences.PREF_DATE_TIMEZONE, false);
  }

}
