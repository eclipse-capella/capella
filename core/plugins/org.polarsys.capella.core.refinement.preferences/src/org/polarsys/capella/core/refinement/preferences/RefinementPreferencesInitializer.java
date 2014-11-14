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
package org.polarsys.capella.core.refinement.preferences;

import org.eclipse.core.resources.ProjectScope;

import org.polarsys.capella.core.commands.preferences.service.AbstractPreferencesInitializer;

/**
 */
public class RefinementPreferencesInitializer extends AbstractPreferencesInitializer {

  /**
   * Default constructor.
   */
  public RefinementPreferencesInitializer() {
    super(RefinementPreferencesPlugin.PLUGIN_ID);
  }

  /**
   * @see org.eclipse.core.runtime.preferences.AbstractPreferenceInitializer#initializeDefaultPreferences()
   */
  @Override
  public void initializeDefaultPreferences() {

    putBoolean(IRefinementPreferences.PREFS_ALLOW_REFINED_SCENARIO_DIAGRAM_CREATION,
        IRefinementPreferences.PREFS_ALLOW_REFINED_SCENARIO_DIAGRAM_CREATION_DEFAULT, ProjectScope.class);
    putBoolean(IRefinementPreferences.PREFS_ALLOW_REFINED_SCENARIO_DIAGRAM_OPENING,
        IRefinementPreferences.PREFS_ALLOW_REFINED_SCENARIO_DIAGRAM_OPENING_DEFAULT, ProjectScope.class);
    putBoolean(IRefinementPreferences.PREFS_MERGE_PRE_VALIDATION_ACTIVATION, IRefinementPreferences.PREFS_MERGE_PRE_VALIDATION_ACTIVATION_DEFAULT,
        ProjectScope.class);
    putBoolean(IRefinementPreferences.PREFS_MERGE_STOP_ON_ERROR_DURING_PRE_VALIDATION,
        IRefinementPreferences.PREFS_MERGE_STOP_ON_ERROR_DURING_PRE_VALIDATION_DEFAULT, ProjectScope.class);
  }
}
