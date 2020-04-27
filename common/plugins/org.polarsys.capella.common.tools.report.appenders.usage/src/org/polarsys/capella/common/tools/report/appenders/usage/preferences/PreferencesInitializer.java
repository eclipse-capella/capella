/*******************************************************************************
 * Copyright (c) 2017 THALES GLOBAL SERVICES.
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
package org.polarsys.capella.common.tools.report.appenders.usage.preferences;

import org.eclipse.core.resources.ProjectScope;
import org.polarsys.capella.common.tools.report.appenders.usage.UsageAppenderPlugin;
import org.polarsys.capella.core.commands.preferences.service.AbstractPreferencesInitializer;

/**
 * Preferences initializer.
 */
public class PreferencesInitializer extends AbstractPreferencesInitializer {
  /**
   *
   */
  public PreferencesInitializer() {
    super(UsageAppenderPlugin.PLUGIN_ID);
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public void initializeDefaultPreferences() {
    // Set default usage monitoring activation.
    putBoolean(IUsagePreferences.PREFERENCES_ACTIVATE_USAGE_MONITORING,
        IUsagePreferences.PREFERENCES_ACTIVATE_USAGE_MONITORING_DEFAULT.booleanValue(), ProjectScope.class);

  }
}
