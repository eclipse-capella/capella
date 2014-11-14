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
package org.polarsys.capella.common.command.recorder.ui.messages;

import org.eclipse.osgi.util.NLS;

import org.polarsys.capella.common.command.recorder.ui.RecorderUIActivator;

/**
 * I18n support for this plugin
 */
public class RecorderUIMessages extends NLS {
  private static final String BUNDLE_NAME = RecorderUIActivator.getDefault().getPluginId() + ".messages.messages"; //$NON-NLS-1$

  // Preference page
  
  public static String recorderPreferencePage_Desc;
  public static String recorderPreferencePage_MainGroup_Lbl;
  public static String recorderPreferencePage_MainGroup_ToolTip;
  public static String recorderPreferencePage_ActivateRecording_Lbl;
  public static String recorderPreferencePage_HistoryGroup_Lbl;
  public static String recorderPreferencePage_HistoryGroup_ToolTip;
  public static String recorderPreferencePage_HistoryInDay_Lbl;
  public static String recorderPreferencePage_MaxFileSize_Lbl;
  public static String recorderPreferencePage_RecordsLocation_Lbl;
  public static String recorderPreferencePage_ContentGroup_Lbl;
  public static String recorderPreferencePage_ContentGroup_ToolTip;
  public static String recorderPreferencePage_ExtraData_Lbl;
  public static String recorderPreferencePage_CreateNewFileOnStart_Lbl;
  public static String recorderPreferencePage_DeleteRecordsOnProjectDeletion_Lbl;

  // View
  
  public static String recorderView_loadFileMainButton;
  public static String recorderView_deleteButton;
  
  public static String recorderView_deleteButton_toolTip;
  public static String recorderView_confirmDelete_title;
  public static String recorderView_confirmDelete_msg;
  
  public static String recorderView_inProgress;
  public static String recorderView_stopped;

  static {
    // initialize resource bundle
    NLS.initializeMessages(BUNDLE_NAME, RecorderUIMessages.class);
  }

  private RecorderUIMessages() {
    // Do nothing.
  }
}
