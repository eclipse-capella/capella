/*******************************************************************************
 * Copyright (c) 2006, 2018 THALES GLOBAL SERVICES.
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
package org.polarsys.capella.core.platform.sirius.ui.project.internal.preferences;

import org.eclipse.jface.preference.IPreferenceStore;
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
  }
}
