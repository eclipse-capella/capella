/*******************************************************************************
 * Copyright (c) 2006, 2020 THALES GLOBAL SERVICES.
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
package org.polarsys.capella.core.sirius.ui.handlers;

import org.eclipse.osgi.util.NLS;

public class Messages extends NLS {
  private static final String BUNDLE_NAME = "org.polarsys.capella.core.sirius.ui.handlers.messages"; //$NON-NLS-1$

  public static String RefreshDiagramJob_Name;
  
  public static String RefreshDiagramsCommandHandler_RepresentationsRefreshed;
  public static String RefreshDiagramsCommandHandler_FailureAddition;
  public static String RefreshDiagramsCommandHandler_Failure_NotLoadable;
  public static String RefreshDiagramsCommandHandler_Failure_NotLoadable_Log;
  public static String RefreshDiagramsCommandHandler_Failure_NoTarget;
  public static String RefreshDiagramsCommandHandler_Failure_NoTarget_Log;
  public static String RefreshDiagramsCommandHandler_Failure_Refresh;
  public static String RefreshDiagramsCommandHandler_Failure_Refresh_Log;
  public static String RefreshDiagramsCommandHandler_DescInfo;
  public static String RefreshDiagramsCommandHandler_DescInfo_NoTarget;
  public static String RefreshDiagramsCommandHandler_OpenDiagram_RefreshFailed;
  
  public static String RemoveHiddenElementsHandler_JobName;

  public static String RefreshRepresentation_0;
  public static String RefreshRepresentation_1;
  public static String RefreshRepresentation_2;
  public static String RefreshRepresentation_6;
  
  public static String RefreshRepresentation_4;
  public static String RefreshRepresentation_5;
  
  public static String RefreshRepresentation_7;
  public static String RefreshRepresentation_8;
  
  
  public static String RefreshRepresentation_9;
  public static String RefreshRepresentation_10;
  
  public static String RemoveHiddenElementsCommand_Name;
  public static String RemoveHiddenElementsCommand_Report;
  public static String RemoveHiddenElementsCommand_NoOp;
  public static String RemoveHiddenElementsCommand_DiagramUpdated;

  public static String RemoveHiddenElementsHandler_NoDiagramDialog_Text;
  public static String RemoveHiddenElementsHandler_ConfirmRefreshDialog_Title;
  public static String RemoveHiddenElementsHandler_ConfirmRefreshDialog_Text;

  static {
    // initialize resource bundle
    NLS.initializeMessages(BUNDLE_NAME, Messages.class);
  }

  private Messages() {
    // Private constructor.
  }
}
