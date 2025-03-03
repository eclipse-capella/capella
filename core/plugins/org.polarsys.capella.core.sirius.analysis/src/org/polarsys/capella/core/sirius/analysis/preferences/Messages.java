/*******************************************************************************
 * Copyright (c) 2020, 2022 THALES GLOBAL SERVICES.
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
package org.polarsys.capella.core.sirius.analysis.preferences;

import org.eclipse.osgi.util.NLS;

public class Messages extends NLS {
  private static final String BUNDLE_NAME = "org.polarsys.capella.core.sirius.analysis.preferences.messages"; //$NON-NLS-1$

  public static String TitleBlockPreferencePage_TimeZone;

  public static String TitleBlockPreferencePage_AddDefaultTitleBlock;

  public static String TitleBlockPreferencePage_ConfirmDeletion;

  public static String TitleBlockPreferencePage_DeleteEntireColumn;

  public static String TitleBlockPreferencePage_DeleteEntireRow;

  public static String TitleBlockPreferencePage_Description;

  public static String TitleBlockPreferencePage_EmptyMessage;

  public static String TitleBlockPreferencePage_ErrorMessage;

  public static String TitleBlockPreferencePage_InsertColumn;

  public static String TitleBlockPreferencePage_InsertLine;

  public static String TitleBlockPreferencePage_Message;

  public static String TitleBlockPreferencePage_RemoveColumn;

  public static String TitleBlockPreferencePage_RemoveLastColumnError;

  public static String TitleBlockPreferencePage_RemoveLastLineError;

  public static String TitleBlockPreferencePage_RemoveLine;

  public static String TitleBlockPreferencePage_Title;
  static {
    // initialize resource bundle
    NLS.initializeMessages(BUNDLE_NAME, Messages.class);
  }

  private Messages() {
  }
}
