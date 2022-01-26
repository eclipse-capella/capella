/*******************************************************************************
 * Copyright (c) 2006, 2020 THALES GLOBAL SERVICES.
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
package org.polarsys.capella.core.platform.sirius.ui.navigator.preferences;

import org.eclipse.jface.preference.IPreferenceStore;
import org.osgi.framework.FrameworkUtil;
import org.polarsys.capella.core.commands.preferences.service.AbstractPreferencesInitializer;
import org.polarsys.capella.core.platform.sirius.ui.navigator.CapellaNavigatorPlugin;
import org.polarsys.capella.core.preferences.Activator;

/**
 * Initialize Capella navigator preferences.
 */
public class CapellaNavigatorPreferencesInitializer extends AbstractPreferencesInitializer {
  /**
   */
  public CapellaNavigatorPreferencesInitializer() {
    super(FrameworkUtil.getBundle(CapellaNavigatorPlugin.class).getSymbolicName());
  }

  /**
   * @see org.eclipse.core.runtime.preferences.AbstractPreferenceInitializer#initializeDefaultPreferences()
   */
  @Override
  public void initializeDefaultPreferences() {
    IPreferenceStore preferenceStore = Activator.getDefault().getPreferenceStore();
    // Set default delete confirmation value.
    preferenceStore.setDefault(ICapellaNavigatorPreferences.PREFERENCE_SHOW_CAPELLA_PROJECT_CONCEPT, false);
    preferenceStore.setDefault(ICapellaNavigatorPreferences.PREFERENCE_PART_EXPLICIT_VIEW, true);
  }
}
