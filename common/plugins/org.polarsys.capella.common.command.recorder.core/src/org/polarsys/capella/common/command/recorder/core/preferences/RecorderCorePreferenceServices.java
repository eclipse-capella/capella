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
package org.polarsys.capella.common.command.recorder.core.preferences;

import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.preferences.IEclipsePreferences;
import org.eclipse.core.runtime.preferences.InstanceScope;
import org.eclipse.core.variables.IStringVariableManager;
import org.eclipse.core.variables.VariablesPlugin;

import org.polarsys.capella.common.command.recorder.core.variables.IRecorderVariableConstants;
import org.polarsys.capella.core.commands.preferences.service.AbstractPreferencesInitializer;
import org.polarsys.capella.core.preferences.Activator;

/**
 * Utility class in order to access to the preferences defined in this plugin.
 */
public class RecorderCorePreferenceServices {

  /**
   * Check whether the Recorder should be running
   * @return <code>true</code> whether yes, <code>false</code> otherwise
   */
  public static boolean isRecorderShouldBeRunning() {
    return AbstractPreferencesInitializer.getBoolean(IRecorderCorePreferenceConstants.RECORDER_STATE_PREF_ID,
        IRecorderCorePreferenceConstants.RECORDER_STATE_PREF_DEFAULT_VALUE);
  }

  /**
   * Check whether a new file should be created on application (or recorder) start
   * @return <code>true</code> whether yes, <code>false</code> otherwise
   */
  public static boolean isNewLogFileShouldBeCreatedOnStartUp() {
    return AbstractPreferencesInitializer.getBoolean(IRecorderCorePreferenceConstants.RECORDER_CREATE_NEW_FILE_ON_START_PREF_ID,
        IRecorderCorePreferenceConstants.RECORDER_CREATE_NEW_FILE_ON_START_PREF_DEFAULT_VALUE);
  }

  /**
   * Check whether the Recorder should serialize extra data for EObject
   * @return <code>true</code> whether yes, <code>false</code> otherwise
   */
  public static boolean isExtraDataShouldBeSerialized() {
    return AbstractPreferencesInitializer.getBoolean(IRecorderCorePreferenceConstants.RECORDER_EXTRA_DATA_FOR_EOBJECT_PREF_ID,
        IRecorderCorePreferenceConstants.RECORDER_EXTRA_DATA_FOR_EOBJECT_PREF_DEFAULT_VALUE);
  }

  /**
   * Check whether the record files should be deleted in the same time as project
   * @return <code>true</code> whether yes, <code>false</code> otherwise
   */
  public static boolean isRecordsShouldBeDeletedWithProject() {
    return AbstractPreferencesInitializer.getBoolean(IRecorderCorePreferenceConstants.RECORDER_DELETE_RECORDS_WITH_PROJECTS_PREF_ID,
        IRecorderCorePreferenceConstants.RECORDER_DELETE_RECORDS_WITH_PROJECTS_DEFAULT_VALUE);
  }

  /**
   * Return the number of day which correspond to files to keep.
   * @return -1 whether no limit has been set.
   */
  public static int getHistoryDelay() {
    return AbstractPreferencesInitializer.getInt(IRecorderCorePreferenceConstants.RECORDER_HISTORY_IN_DAY_PREF_ID, false);
  }

  /**
   * Return the upper limit for history file size (in MB).
   * @return
   */
  public static int getMaxFileSize() {
    return AbstractPreferencesInitializer.getInt(IRecorderCorePreferenceConstants.RECORDER_MAX_FILE_SIZE_PREF_ID, false);
  }

  /**
   * Get the root location path used to store records
   * @return the path as a {@link String}. Can not be null
   * @throws CoreException
   */
  public static String getRootRecordPath() {

    IEclipsePreferences preferences = getPluginPreferences();

    IStringVariableManager svm = VariablesPlugin.getDefault().getStringVariableManager();

    String path = null;

    try {
      path =
          svm.performStringSubstitution(preferences.get(IRecorderCorePreferenceConstants.RECORDER_ROOT_PATH_PREF_ID,
              IRecorderCorePreferenceConstants.RECORDER_ROOT_PATH_DEFAULT_VALUE));
    } catch (CoreException exception_p) {
      // Path is initialized to the default value.
      path = svm.getValueVariable(IRecorderVariableConstants.DEFAULT_PATH_VARIABLE_ID).getValue();
    }

    return path;
  }

  /** for internal use */
  private static IEclipsePreferences getPluginPreferences() {
    return new InstanceScope().getNode(Activator.PLUGIN_ID);
  }

}
