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

import org.polarsys.capella.common.command.recorder.core.variables.IRecorderVariableConstants;

/**
 * Constants for preferences.
 */
public interface IRecorderCorePreferenceConstants {

  /** the recorder state preference */
  final static public String RECORDER_STATE_PREF_ID = "recorder_state_pref"; //$NON-NLS-1$
  /** the recorder state preference default value */
  final static public boolean RECORDER_STATE_PREF_DEFAULT_VALUE = false;

  /** Create a new file on application start? */
  final static public String RECORDER_CREATE_NEW_FILE_ON_START_PREF_ID = "recorder_create_new_file_on_start_day_pref"; //$NON-NLS-1$
  /** Default value for new file creation on application start */
  final static public boolean RECORDER_CREATE_NEW_FILE_ON_START_PREF_DEFAULT_VALUE = true;

  /** the recorder preference for history keeping (in day)*/
  final static public String RECORDER_HISTORY_IN_DAY_PREF_ID = "recorder_history_day_pref"; //$NON-NLS-1$
  /** the default value for recorder preference for history keeping (in day)*/
  final static public int RECORDER_HISTORY_IN_DAY_DEFAULT_VALUE = 7;

  /** the recorder preference for history file max size (in MB)*/
  final static public String RECORDER_MAX_FILE_SIZE_PREF_ID = "recorder_max_file_sz_pref"; //$NON-NLS-1$
  /** the default value for the recorder preference for history file max size (in MB)*/
  final static public int RECORDER_MAX_FILE_SIZE_DEFAULT_VALUE = 1;

  /** root path to records */
  final static public String RECORDER_ROOT_PATH_PREF_ID = "recorder_root_path_pref"; //$NON-NLS-1$
  /** the default value for root path to records*/
  final static public String RECORDER_ROOT_PATH_DEFAULT_VALUE = "${" + IRecorderVariableConstants.DEFAULT_PATH_VARIABLE_ID + "}"; //$NON-NLS-1$ //$NON-NLS-2$

  /** delete records on project deletion ? */
  final static public String RECORDER_DELETE_RECORDS_WITH_PROJECTS_PREF_ID = "recorder_delete_path_pref"; //$NON-NLS-1$
  /** the default value for root path to records*/
  final static public boolean RECORDER_DELETE_RECORDS_WITH_PROJECTS_DEFAULT_VALUE = false;

  /** extra data serialization for EObject */
  final static public String RECORDER_EXTRA_DATA_FOR_EOBJECT_PREF_ID = "recorder_extra_data_for_eobject_pref"; //$NON-NLS-1$
  /** the recorder state preference default value */
  final static public boolean RECORDER_EXTRA_DATA_FOR_EOBJECT_PREF_DEFAULT_VALUE = true;

}
