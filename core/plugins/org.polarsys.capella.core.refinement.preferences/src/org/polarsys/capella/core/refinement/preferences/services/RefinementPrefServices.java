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
package org.polarsys.capella.core.refinement.preferences.services;

import org.polarsys.capella.core.commands.preferences.service.AbstractPreferencesInitializer;
import org.polarsys.capella.core.refinement.preferences.IRefinementPreferences;

/**
 * Utility class in order to access to the refinement preference state.
 */
public class RefinementPrefServices {

  /**
   * Get the Allow Refined Diagram Creation Strategy current preference value. <br>
   * <br>
   * @link IRefinementPreferences#PREFS_ALLOW_REFINED_SCENARIO_DIAGRAM_CREATION} value <code>true or false</code>
   * @return boolean value
   */
  static public boolean isRefinedDiagramCreationAllowed() {
    return AbstractPreferencesInitializer.getBoolean(IRefinementPreferences.PREFS_ALLOW_REFINED_SCENARIO_DIAGRAM_CREATION,
        IRefinementPreferences.PREFS_ALLOW_REFINED_SCENARIO_DIAGRAM_CREATION_DEFAULT);
  }

  /**
   * Get the Allow Refined Diagram Opening Strategy current preference value. <br>
   * <br>
   * @link IRefinementPreferences#PREFS_ALLOW_REFINED_SCENARIO_DIAGRAM_OPENING} value <code>true or false</code>
   * @return boolean value
   */
  static public boolean isRefinedDiagramOpeningAllowed() {
    return AbstractPreferencesInitializer.getBoolean(IRefinementPreferences.PREFS_ALLOW_REFINED_SCENARIO_DIAGRAM_OPENING,
        IRefinementPreferences.PREFS_ALLOW_REFINED_SCENARIO_DIAGRAM_OPENING_DEFAULT);
  }

  /**
   * Get the preference value that indicates whether a validation is performed before the merge step.
   * @link IRefinementPreferences#PREFS_MERGE_PRE_VALIDATION_ACTIVATION} value <code>true or false</code>
   * @return boolean value
   */
  static public boolean isPreValidationForMergeActivated() {
    return AbstractPreferencesInitializer.getBoolean(IRefinementPreferences.PREFS_MERGE_PRE_VALIDATION_ACTIVATION,
        IRefinementPreferences.PREFS_MERGE_PRE_VALIDATION_ACTIVATION_DEFAULT);
  }

  /**
   * Get the preference value that indicates whether an error during validation stop the refinement.
   * @link IRefinementPreferences#PREFS_MERGE_PRE_VALIDATION_ACTIVATION} value <code>true or false</code>
   * @return boolean value
   */
  static public boolean isErrorOnPreValidationStopMerge() {
    return AbstractPreferencesInitializer.getBoolean(IRefinementPreferences.PREFS_MERGE_STOP_ON_ERROR_DURING_PRE_VALIDATION,
        IRefinementPreferences.PREFS_MERGE_STOP_ON_ERROR_DURING_PRE_VALIDATION_DEFAULT);
  }

}
