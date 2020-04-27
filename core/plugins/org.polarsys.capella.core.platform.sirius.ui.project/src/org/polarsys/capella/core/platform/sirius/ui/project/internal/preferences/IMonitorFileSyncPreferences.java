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
package org.polarsys.capella.core.platform.sirius.ui.project.internal.preferences;

/**
 * Preference constants related to monitoring file synchronization with the local file system.
 */
public interface IMonitorFileSyncPreferences {
  /**
   * Preference related to enable or disable the file sync monitoring.
   */
  public static final String PREFERENCE_ENABLE_FILE_SYNC_MONITORING = "EnableFileSyncMonitoring"; //$NON-NLS-1$
  /**
   * Preference related to the file sync monitoring delay, default value is 15 second.
   */
  public static final String PREFERENCE_FILE_SYNC_MONITORING_DELAY = "FileSyncMonitoringDelay"; //$NON-NLS-1$
  /**
   * Default value of the file sync monitoring delay.
   */
  public static final int FILE_SYNC_MONITORING_DEFAULT_DELAY = 15;

}
