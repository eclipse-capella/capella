/*******************************************************************************
 * Copyright (c) 2006, 2022 THALES GLOBAL SERVICES.
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
package org.polarsys.capella.core.platform.sirius.ui.commands;

import org.eclipse.osgi.util.NLS;

/**
 */
public class Messages extends NLS {
  private static final String BUNDLE_NAME = "org.polarsys.capella.core.platform.sirius.ui.commands.messages"; //$NON-NLS-1$
  public static String CapellaCloneDiagramCommand_CloneName_Prefix;
  public static String CapellaCloneDiagramCommand_CommandLabel;
  public static String CapellaDeleteCommand_CancelDeletionMessage;
  public static String CapellaDeleteCommand_ConfirmDeletionQuestion;
  public static String CapellaDeleteCommand_ConfirmDeletionWithOneSemanticElement;
  public static String CapellaDeleteCommand_ConfirmDeletionWithManySemanticElement;
  public static String CapellaDeleteCommand_ConfirmDeletionWithOneRepresentation;
  public static String CapellaDeleteCommand_ConfirmDeletionWithManyRepresentations;
  public static String CapellaDeleteCommand_ProtectedElementsError;
  public static String CapellaDeleteCommand_ConfirmDeletionWithOneElementInfo;
  public static String CapellaDeleteCommand_ConfirmDeletionWithMultipleDifferentElementsInfo;
  public static String CapellaDeleteCommand_ControlledElementsError_Message;
  public static String CapellaDeleteCommand_DeleteCommandError_Message;
  public static String CapellaDeleteCommand_ImpactAnalysis_DeletedElements_Group_Title;
  public static String CapellaDeleteCommand_ImpactAnalysis_DeletedElements_Group_Tooltip;
  public static String CapellaDeleteCommand_ImpactAnalysis_ReferencingElements_Group_Title;
  public static String CapellaDeleteCommand_ImpactAnalysis_ReferencingElements_Group_Tooltip;
  public static String CapellaDeleteCommand_Label;
  public static String CapellaDeleteCommand_ConfirmLabel;
  public static String CapellaDeleteCommand_RemoveElement_Msg;
  public static String CapellaDeleteCommand_UnsetElement_Msg;
  public static String CapellaPasteCommand_COPY_2_OF;
  public static String CapellaPasteCommand_COPY_MULTIPLE;
  public static String CapellaPasteCommand_COPY_OF;
  public static String CapellaPasteCommand_error_session;
  public static String CapellaPasteCommand_error_command;
  public static String PropagateEIOnPorts;
  public static String PropagatePortRealizations;
  public static String ComponentExchangeThroughDelegations;

  static {
    // initialize resource bundle
    NLS.initializeMessages(BUNDLE_NAME, Messages.class);
  }

  private Messages() {
    // Static initialization
  }
}
