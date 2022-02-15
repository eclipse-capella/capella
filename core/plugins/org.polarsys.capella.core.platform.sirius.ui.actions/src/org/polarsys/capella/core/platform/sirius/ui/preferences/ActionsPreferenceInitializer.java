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

package org.polarsys.capella.core.platform.sirius.ui.preferences;

import org.eclipse.core.resources.ProjectScope;
import org.eclipse.core.runtime.preferences.DefaultScope;
import org.osgi.framework.FrameworkUtil;
import org.polarsys.capella.core.commands.preferences.service.AbstractPreferencesInitializer;
import org.polarsys.capella.core.model.handler.command.CapellaResourceHelper;

/**
 */
public class ActionsPreferenceInitializer extends AbstractPreferencesInitializer {
  /**
   * @param pluginID
   */
  public ActionsPreferenceInitializer() {
    super(FrameworkUtil.getBundle(ActionsPreferenceInitializer.class).getSymbolicName());

  }

  /**
   * @see org.eclipse.core.runtime.preferences.AbstractPreferenceInitializer#initializeDefaultPreferences()
   */
  @Override
  public void initializeDefaultPreferences() {
    // Set default detection version preference.
    putBoolean(ICapellaPreferences.PREFERENCE_DETECTION_VERSION, true, DefaultScope.class);
    // Set default Capella AIRD fragment file extension.
    putString(ICapellaPreferences.PREFERENCE_CAPELLA_AIRD_FRAGMENT_FILE_EXTENSION, CapellaResourceHelper.AIRD_FRAGMENT_FILE_EXTENSION, ProjectScope.class);

  }
}
