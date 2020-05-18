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
package org.polarsys.capella.core.sirius.ui;

import org.eclipse.osgi.util.NLS;

/**
 */
public class Messages extends NLS {
  private static final String BUNDLE_NAME = "org.polarsys.capella.core.sirius.ui.messages"; //$NON-NLS-1$
  public static String CloseSessionAction_Title;
  public static String ControlAction__UI_Control_menu_item;
  public static String ControlAction__UI_Control_menu_item_description;
  public static String ControlAction__UI_ControlCommand_label;
  public static String ControlAction__UI_Uncontrol_menu_item;
  public static String ControlAction__UI_Uncontrol_menu_item_description;
  public static String ControlAction__UI_UncontrolCommand_label;
  public static String ControlAction_Fragmentation_Error_Message;
  public static String ControlAction_Fragmentation_Error2_Message;
  public static String ControlAction_Fragmentation_Error3_Message;
  public static String ControlAction_Fragmentation_Error4_Message;
  public static String ControlAction_Fragmentation_Error5_Message;
  public static String ControlAction_Label_Title;
  public static String ControlAction_Window_Title;
  public static String CreateAirdResourceWizard_Title;
  public static String DeleteRepresentationAction_Message;
  public static String DeleteRepresentationAction_Multiple_Diagram_Message;
  public static String DeleteRepresentationAction_One_Diagram_Message;
  public static String DeleteRepresentationAction_Title;
  public static String CapellaSessionHelper_CreateSession_Title;
  public static String CapellaSessionHelper_Repair_Migrate_Message;
  public static String CapellaSessionHelper_SemanticModel_Error_Message_Part1;
  public static String CapellaSessionHelper_SemanticModel_Error_Message_Part2;
  public static String CapellaSessionHelper_SemanticModel_Error_Message_WrongVersion_Part1;
  public static String CapellaSessionHelper_SemanticModel_Error_Message_WrongVersion_Part2;
  public static String CapellaSessionHelper_SemanticModel_ErrorDialog_Title;
  public static String CapellaSessionHelper_UnknownError_Message;
  public static String OpenSessionAction_Title;
  public static String RenameRepresentationAction_Title;
  public static String SelectRepresentationsWizard_Title;
  public static String SiriusCreationWizardPage_Description;
  public static String SiriusCreationWizardPage_Title;

  public static String unableToSaveDialog_Title;
  public static String unableToSaveDialog_TopMsg;
  public static String unableToSaveDuringCloseOpsDialog_BottomQuestion;

  static {
    // initialize resource bundle
    NLS.initializeMessages(BUNDLE_NAME, Messages.class);
  }

  /**
   * Constructor.
   */
  private Messages() {
    // Do nothing.
  }
}
