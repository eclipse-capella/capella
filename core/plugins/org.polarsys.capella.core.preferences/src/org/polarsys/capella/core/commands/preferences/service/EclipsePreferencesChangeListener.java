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
package org.polarsys.capella.core.commands.preferences.service;

import org.eclipse.core.runtime.preferences.IEclipsePreferences.IPreferenceChangeListener;
import org.eclipse.core.runtime.preferences.IEclipsePreferences.PreferenceChangeEvent;

/**
 * This class is used to refresh all affected Preference FieldEditors Page when the user modify the profile mode
 */
public class EclipsePreferencesChangeListener implements IPreferenceChangeListener {

  /**
   * {@inheritDoc}
   */
  @Override
  public void preferenceChange(PreferenceChangeEvent event_p) {

  }

}
