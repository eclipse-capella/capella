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
package org.polarsys.capella.core.projection.scenario;

import org.eclipse.osgi.util.NLS;

/**
 */
public class Messages extends NLS {
  private static final String BUNDLE_NAME = "org.polarsys.capella.core.projection.scenario.messages"; //$NON-NLS-1$
  public static String Df2ISEngine_0;
  public static String Rule_Event_CannotRetrieveInstanceRoleFromBound;
  public static String Rule_Event_FunctionalExchangeMultiAllocated;
  public static String Rule_Event_FunctionalExchangeMultiAllocatedConveyed;
  public static String Rule_Event_SelectionComponentExchange;
  public static String Rule_InstanceRole_AllocatedMoreThanOnce;
  public static String Rule_InstanceRole_AllocatingMultipart;
  public static String Rule_InstanceRole_CannotProcess_FunctionUnallocated;
  public static String Rule_InstanceRole_Selection;
  public static String Rule_InstanceRole_TransitionTitle;
  public static String Rule_InstanceRole_TransitionTitleDetailled;
  public static String Rule_Event_FunctionalExchangeNotAllocated;
  public static String Scenario_First;
  public static String Scenario_MultipleTransitionedElements;
  public static String Scenario_Nth;
  public static String Scenario_Second;
  public static String Scenario_SourceMessage;
  public static String Scenario_TargetMessage;
  public static String Scenario_Third;
  public static String ScenarioSuffix_CES;
  public static String ScenarioSuffix_IS;
  public static String ScenarioSuffix_OES;
  public static String Rule_InteractionState_FailMsg;
  public static String Rule_StateFragment_FailMsg;
  public static String Rule_InteractionFunctionState_FailMsg;
  public static String MultiInstanceRoleExtension_logmsg;
  public static String MultiInstanceRoleExtension_logmsg_confirm;
  public static String MultiInstanceRoleExtension_message;
  public static String MultiInstanceRoleExtension_title;
  public static String transitionES2IS_label;
  public static String transitionESF2ESB_label;
  public static String transitionFS2ES_label;
  public static String transitionFS2ES_OASA_label;
  public static String transitionFS2ES_SALAPA_label;
  public static String transitionES2ES_label;
  public static String transitionES2ES_OAtoSA_label;
  public static String transitionES2ES_SAtoLA_label;
  public static String transitionES2ES_LAtoPA_label;
  public static String transitionFS2FS_label;
  public static String transitionFS2FS_OAtoSA_label;
  public static String transitionFS2FS_SAtoLA_label;
  public static String transitionFS2FS_LAtoPA_label;
  public static String transitionIS2IS_SAtoLA_label;
  public static String transitionIS2IS_LAtoPA_label;
  public static String transitionIS2IS_PAtoEPBS_label;
  public static String transitionIS2IS_label;
  public static String FC2FSInitialization_Info_Lookup;
  public static String FC2FSInitialization_Confirm_Title;
  public static String FC2FSInitialization_Confirm_Message;
  public static String FC2FSInitialization_Info_Creating;
  public static String FC2FSInitialization_Info_Done;
  public static String FC2FSInitialization_Info_Initializing;
  public static String FC2FSInitialization_Info_Reordering;
  public static String FC2FSInitialization_Info_OldOrder;
  public static String FC2FSInitialization_Info_NewOrder;
  public static String FC2FSInitialization_Info_FCWithReply;
  public static String FC2FSInitialization_Info_FCWithoutReply;
  public static String FC2FSInitialization_Info_CreatingScenarioKind;
  public static String FC2FSInitialization_Info_CreatingRole;
  public static String FC2FSInitialization_Info_CreatingSeqMsg;
  public static String FC2FSInitialization_MessageEnd_Name;
  public static String FC2FSInitialization_ReceiverEnd_Name;
  public static String FC2FSInitialization_ExecutionEnd_Name;
  public static String FC2FSInitialization_Execution_Name;
  public static String FC2FSInitialization_EventSentOp_Name;
  public static String FC2FSInitialization_EventReceiptOp_Name;
  public static String FC2FSInitialization_ExecutionEvent_Name;
  public static String Rule_StateFragment_Error;

  static {
    // initialize resource bundle
    NLS.initializeMessages(BUNDLE_NAME, Messages.class);
  }

  private Messages() {
    // nothing
  }
}
