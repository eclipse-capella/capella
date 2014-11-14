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
package org.polarsys.capella.core.business.queries.preferences;

import org.eclipse.core.runtime.preferences.AbstractPreferenceInitializer;
import org.eclipse.jface.preference.IPreferenceStore;
import org.polarsys.capella.core.business.queries.BusinessQueriesPlugin;

/**
 * This is the preferences initializer for the core business queries preferences
 */
public class CoreBusinessQueriesPreferencesInitializer extends AbstractPreferenceInitializer {

  /**
   * @see org.eclipse.core.runtime.preferences.AbstractPreferenceInitializer#initializeDefaultPreferences()
   */
  @Override
  public void initializeDefaultPreferences() {
    IPreferenceStore preferenceStore = BusinessQueriesPlugin.getDefault().getPreferenceStore();
    //Sets the default value of the "skip already owned values" to true 
    preferenceStore.setDefault(ICoreBusinessQueriesPreferences.PREF_SKIP_OWNED_MIN_MAX_NULL_DEFAULT_VALUES, true);
  }
}
