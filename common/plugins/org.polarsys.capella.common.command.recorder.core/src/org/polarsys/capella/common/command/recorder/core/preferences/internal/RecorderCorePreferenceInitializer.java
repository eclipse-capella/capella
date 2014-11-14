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
package org.polarsys.capella.common.command.recorder.core.preferences.internal;

import org.eclipse.core.resources.ProjectScope;

import org.polarsys.capella.common.command.recorder.core.RecorderCoreActivator;
import org.polarsys.capella.common.command.recorder.core.preferences.IRecorderCorePreferenceConstants;
import org.polarsys.capella.core.commands.preferences.service.AbstractPreferencesInitializer;

/**
 * Preferences initializer
 */
public class RecorderCorePreferenceInitializer extends AbstractPreferencesInitializer {

  /**
   * 
   */
  public RecorderCorePreferenceInitializer() {
    super(RecorderCoreActivator.PLUGIN_ID);
  }

  /**
   * @see org.eclipse.core.runtime.preferences.AbstractPreferenceInitializer#initializeDefaultPreferences()
   */
  @Override
  public void initializeDefaultPreferences() {

    putString(IRecorderCorePreferenceConstants.RECORDER_STATE_PREF_ID, String.valueOf(IRecorderCorePreferenceConstants.RECORDER_STATE_PREF_DEFAULT_VALUE),
        ProjectScope.class);

    putString(IRecorderCorePreferenceConstants.RECORDER_CREATE_NEW_FILE_ON_START_PREF_ID,
        String.valueOf(IRecorderCorePreferenceConstants.RECORDER_CREATE_NEW_FILE_ON_START_PREF_DEFAULT_VALUE), ProjectScope.class);

    putString(IRecorderCorePreferenceConstants.RECORDER_EXTRA_DATA_FOR_EOBJECT_PREF_ID,
        String.valueOf(IRecorderCorePreferenceConstants.RECORDER_EXTRA_DATA_FOR_EOBJECT_PREF_DEFAULT_VALUE), ProjectScope.class);

    putString(IRecorderCorePreferenceConstants.RECORDER_HISTORY_IN_DAY_PREF_ID,
        String.valueOf(IRecorderCorePreferenceConstants.RECORDER_HISTORY_IN_DAY_DEFAULT_VALUE), ProjectScope.class);

    putString(IRecorderCorePreferenceConstants.RECORDER_MAX_FILE_SIZE_PREF_ID,
        String.valueOf(IRecorderCorePreferenceConstants.RECORDER_MAX_FILE_SIZE_DEFAULT_VALUE), ProjectScope.class);

    putString(IRecorderCorePreferenceConstants.RECORDER_DELETE_RECORDS_WITH_PROJECTS_PREF_ID,
        String.valueOf(IRecorderCorePreferenceConstants.RECORDER_DELETE_RECORDS_WITH_PROJECTS_DEFAULT_VALUE), ProjectScope.class);

    putString(IRecorderCorePreferenceConstants.RECORDER_ROOT_PATH_PREF_ID, IRecorderCorePreferenceConstants.RECORDER_ROOT_PATH_DEFAULT_VALUE,
        ProjectScope.class);

    return;
  }

}
