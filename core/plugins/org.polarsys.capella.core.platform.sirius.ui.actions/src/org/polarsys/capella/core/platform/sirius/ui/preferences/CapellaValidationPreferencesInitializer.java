/*******************************************************************************
 * Copyright (c) 2006, 2014 THALES GLOBAL SERVICES.
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

import org.polarsys.capella.core.commands.preferences.service.AbstractPreferencesInitializer;
import org.polarsys.capella.core.platform.sirius.ui.actions.CapellaActionsActivator;

/**
 * Initialize preferences for capella model validation
 */
public class CapellaValidationPreferencesInitializer extends AbstractPreferencesInitializer {

  public CapellaValidationPreferencesInitializer() {
    super(CapellaActionsActivator.PLUGIN_ID);
  }

  @Override
  public void initializeDefaultPreferences() {
    putBoolean(ICapellaValidationPreferences.P_CLEAN_PREVIOUS_VALIDATION_RESULTS, true, ProjectScope.class);
  }

}
