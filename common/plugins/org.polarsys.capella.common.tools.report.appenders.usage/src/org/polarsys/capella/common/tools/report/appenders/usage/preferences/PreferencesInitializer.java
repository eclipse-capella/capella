/*******************************************************************************
 * Copyright (c) 2017 THALES GLOBAL SERVICES.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *  
 * Contributors:
 *    Thales - initial API and implementation
 *******************************************************************************/
package org.polarsys.capella.common.tools.report.appenders.usage.preferences;

import org.eclipse.jface.preference.IPreferenceStore;
import org.polarsys.capella.common.tools.report.appenders.usage.Activator;
import org.polarsys.capella.core.commands.preferences.service.AbstractPreferencesInitializer;

/**
 * Preferences initializer.
 */
public class PreferencesInitializer extends AbstractPreferencesInitializer {
  /**
   * @param pluginID_p
   */
  public PreferencesInitializer() {
    super(Activator.PLUGIN_ID);
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public void initializeDefaultPreferences() {
    IPreferenceStore preferenceStore = org.polarsys.capella.core.preferences.Activator.getDefault()
        .getPreferenceStore();
    // Set default usage monitoring activation.
    preferenceStore.setDefault(IUsagePreferences.PREFERENCES_ACTIVATE_USAGE_MONITORING,
        IUsagePreferences.PREFERENCES_ACTIVATE_USAGE_MONITORING_DEFAULT.booleanValue());

  }
}
