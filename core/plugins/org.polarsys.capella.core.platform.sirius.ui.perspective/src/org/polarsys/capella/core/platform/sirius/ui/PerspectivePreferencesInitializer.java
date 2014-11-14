/*******************************************************************************
 * Copyright (c) 2006, 2014 THALES GLOBAL SERVICES.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *  
 * Contributors:
 *    Thales - initial API and implementation
 *******************************************************************************/
package org.polarsys.capella.core.platform.sirius.ui;

import org.eclipse.core.resources.ProjectScope;

import org.polarsys.capella.common.mdsofa.common.constant.ICommonConstants;
import org.polarsys.capella.core.commands.preferences.service.AbstractPreferencesInitializer;

/**
 * Initialize preferences to be used by the Capella Perspective.
 */
public class PerspectivePreferencesInitializer extends AbstractPreferencesInitializer {
  /**
   * @param pluginID_p
   */
  public PerspectivePreferencesInitializer(String pluginID_p) {
    super(PerspectivePlugin.PLUGIN_ID);
  }

  /**
   * @see org.eclipse.core.runtime.preferences.AbstractPreferenceInitializer#initializeDefaultPreferences()
   */
  @Override
  public void initializeDefaultPreferences() {
    // No default value. At Capella perspective creation time, the version number will be filled in.
    putString(PerspectivePreferences.PREFS_PRODUCT_VERSION, ICommonConstants.EMPTY_STRING, ProjectScope.class);
    // Hide the Outline View by default to improve performances
    putBoolean(PerspectivePreferences.PREFS_DISPLAY_OUTLINE_VIEW_ON_STARTUP, false, ProjectScope.class);
  }
}
