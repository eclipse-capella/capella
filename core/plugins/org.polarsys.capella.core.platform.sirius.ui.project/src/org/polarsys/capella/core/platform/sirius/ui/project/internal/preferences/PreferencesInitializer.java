/*******************************************************************************
 * Copyright (c) 2006, 2018 THALES GLOBAL SERVICES.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *  
 * Contributors:
 *    Thales - initial API and implementation
 *******************************************************************************/
package org.polarsys.capella.core.platform.sirius.ui.project.internal.preferences;

import org.eclipse.jface.preference.IPreferenceStore;
import org.eclipse.sirius.ui.business.api.preferences.SiriusUIPreferencesKeys;
import org.eclipse.sirius.viewpoint.provider.SiriusEditPlugin;
import org.polarsys.capella.core.commands.preferences.service.AbstractPreferencesInitializer;
import org.polarsys.capella.core.platform.sirius.ui.project.CapellaProjectActivator;
import org.polarsys.capella.core.preferences.Activator;

/**
 * Preferences initializer.
 */
public class PreferencesInitializer extends AbstractPreferencesInitializer {
  /**
   * @param pluginID_p
   */
  public PreferencesInitializer() {
    super(CapellaProjectActivator.PLUGIN_ID);
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public void initializeDefaultPreferences() {
    IPreferenceStore preferenceStore = Activator.getDefault().getPreferenceStore();
    // Set default delay value.
    preferenceStore.setDefault(IMonitorFileSyncPreferences.PREFERENCE_FILE_SYNC_MONITORING_DELAY,
        IMonitorFileSyncPreferences.FILE_SYNC_MONITORING_DEFAULT_DELAY);
    preferenceStore.setDefault(IMonitorFileSyncPreferences.PREFERENCE_ENABLE_FILE_SYNC_MONITORING, false);

    // Set Sirius UI preferences
    // required since Sirius 5.1 since default behavior is not welcome
    IPreferenceStore siriusUiPreferenceStore = SiriusEditPlugin.getPlugin().getPreferenceStore();
    siriusUiPreferenceStore.setValue(SiriusUIPreferencesKeys.PREF_SAVE_WHEN_NO_EDITOR.name(), false);
    siriusUiPreferenceStore.setValue(SiriusUIPreferencesKeys.PREF_RELOAD_ON_LAST_EDITOR_CLOSE.name(), false);
    // Don't use colors from odesign in diagram palettes
    siriusUiPreferenceStore.setDefault(SiriusUIPreferencesKeys.PREF_DISPLAY_VSM_USER_FIXED_COLOR_IN_PALETTE.name(), false);
  }
}
