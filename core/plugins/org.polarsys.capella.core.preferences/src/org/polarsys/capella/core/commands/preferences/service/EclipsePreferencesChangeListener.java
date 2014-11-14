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
