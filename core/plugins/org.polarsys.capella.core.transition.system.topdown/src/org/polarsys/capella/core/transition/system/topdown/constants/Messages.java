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
package org.polarsys.capella.core.transition.system.topdown.constants;

import org.eclipse.osgi.util.NLS;

/**
 */
public class Messages extends NLS {
  private static final String BUNDLE_NAME = "org.polarsys.capella.core.transition.system.topdown.constants.messages"; //$NON-NLS-1$
  public static String IntramodelLauncher_Title;

  public static String TargetBoundNotTransitioned;
  public static String SourceBoundNotTransitioned;

  public static String SourceNull;
  public static String TargetNull;

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

  static {
    // initialize resource bundle
    NLS.initializeMessages(BUNDLE_NAME, Messages.class);
  }

  private Messages() {
  }
}
