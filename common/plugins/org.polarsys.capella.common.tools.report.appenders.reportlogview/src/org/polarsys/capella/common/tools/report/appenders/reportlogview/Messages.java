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
package org.polarsys.capella.common.tools.report.appenders.reportlogview;

import org.eclipse.osgi.util.NLS;

/**
 */
public class Messages extends NLS {
  private static final String BUNDLE_NAME = "org.polarsys.capella.common.tools.report.appenders.reportlogview.messages"; //$NON-NLS-1$
  public static String MarkerLabelProvider_EcoreDiagnosticSourceLabel;
  public static String MarkerLabelProvider_CategoryLabel;
  public static String MarkerLabelProvider_Severity_Label_Debug;
  public static String MarkerLabelProvider_Severity_Label_Error;
  public static String MarkerLabelProvider_Severity_Label_Fatal;
  public static String MarkerLabelProvider_Severity_Label_Info;
  public static String MarkerLabelProvider_Severity_Label_Warn;
  public static String MarkerView_Column_Title_Origin;
  public static String MarkerView_Column_Title_Level;
  public static String MarkerView_Column_Title_Message;
  public static String MarkerView_Column_Title_Resource;
  public static String MarkerView_Column_Title_RuleId;
  public static String MarkerView_Column_Title_RuleSetId;
  public static String MarkerView_Column_Title_Time;
  public static String MarkerView_Goto_Command_Name;
  public static String MarkerView_Quickfix_Command_Name;
  public static String MarkerView_QuickfixAll_Command_Name;

  static {
    // initialize resource bundle
    NLS.initializeMessages(BUNDLE_NAME, Messages.class);
  }

  private Messages() {
    // Static initialization.
  }
}
