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
package org.polarsys.capella.core.transition.system.ui.preferences;

import org.eclipse.core.runtime.preferences.DefaultScope;

import org.polarsys.capella.core.commands.preferences.service.AbstractPreferencesInitializer;
import org.polarsys.capella.core.transition.system.preferences.PreferenceConstants;

/**
 * Class used to initialize default preference values.
 */
public class TransitionsPreferencesInitializer extends AbstractPreferencesInitializer {

  /**
   */
  public TransitionsPreferencesInitializer() {
    super("org.polarsys.capella.core.transition.common.ui");
  }

  /*
   * (non-Javadoc)
   * @see org.eclipse.core.runtime.preferences.AbstractPreferenceInitializer#initializeDefaultPreferences()
   */
  @Override
  public void initializeDefaultPreferences() {
    putBoolean(PreferenceConstants.P_F, true, DefaultScope.class);
    putBoolean(PreferenceConstants.P_BC, true, DefaultScope.class);
    putBoolean(PreferenceConstants.P_IC, true, DefaultScope.class);
    putBoolean(PreferenceConstants.P_Actor, true, DefaultScope.class);
    putBoolean(PreferenceConstants.P_CAPABILITY, true, DefaultScope.class);
    putBoolean(PreferenceConstants.P_Part, false, DefaultScope.class);
    putBoolean(PreferenceConstants.P_FE, true, DefaultScope.class);
    putBoolean(PreferenceConstants.P_CE, true, DefaultScope.class);
    putBoolean(PreferenceConstants.P_PL, true, DefaultScope.class);
    putBoolean(PreferenceConstants.P_DL, true, DefaultScope.class);
    putBoolean(PreferenceConstants.P_FA, true, DefaultScope.class);
    putBoolean(PreferenceConstants.P_CA, true, DefaultScope.class);
    putBoolean(PreferenceConstants.P_FEA, true, DefaultScope.class);
    putBoolean(PreferenceConstants.P_FPort, true, DefaultScope.class);
    putBoolean(PreferenceConstants.P_CPort, true, DefaultScope.class);
    putBoolean(PreferenceConstants.P_PPort, true, DefaultScope.class);
    putBoolean(PreferenceConstants.P_EI, true, DefaultScope.class);
    putBoolean(PreferenceConstants.P_Iface, true, DefaultScope.class);
    putBoolean(PreferenceConstants.P_Data, true, DefaultScope.class);
    putBoolean(PreferenceConstants.P_MS, true, DefaultScope.class);
    putBoolean(PreferenceConstants.P_PropertyValues, false, DefaultScope.class);
    putBoolean(PreferenceConstants.P_Other, false, DefaultScope.class);

  }

}
