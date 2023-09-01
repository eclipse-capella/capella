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
package org.polarsys.capella.core.platform.sirius.ui.actions;

import org.eclipse.osgi.util.NLS;

/**
 * I18n support.
 */
public class Messages extends NLS {
  private static final String BUNDLE_NAME = "org.polarsys.capella.core.platform.sirius.ui.actions.messages"; //$NON-NLS-1$
  public static String UpdateCategoriesWizardAction_msg;
  public static String UpdateCategoriesWizardAction_Title;
  public static String UpdateCategoriesWizardAction_Warning_Message;
  public static String MDGenerateConnectionsAction_CreatingExchange;
  public static String MDGenerateConnectionsAction_CreatingPhysicalLinks;
  public static String MDGenerateConnectionsAction_DiagramRefresh;
  public static String MDGenerateConnectionsAction_refreshingDiagrams;
  public static String GenerateInterfacesAction_CreatingInterfaces;
  public static String AllocationManagementWizardAction_Title;
  public static String AllocationManagementWizardAction_Command_Label;
  public static String AllocationManagementWizardAction_Warning_Message;
  public static String AllocationManagementWizardAction_Information_Message;
  public static String Allocation_Functions_Selection_Message;
  public static String Allocation_ExchangeItems_Selection_Message;
  public static String Allocation_PhysicalComponents_Selection_Message;
  public static String Allocation_FunctionalExchagnes_Selection_Message;
  public static String Allocation_ComponentExchagnes_Selection_Message;

  public static String CreateCategoriesController_cat_name;
  public static String CreateCategoriesController_create_cat;
  public static String CreateCategoriesController_EmptyName_Error_Message;
  public static String CreateFECategoriesController_prefix;
  public static String CreateCECategoriesController_prefix;
  public static String CreatePLCategoriesController_prefix;
  public static String CreateCECategoriesController_creation_msg;
  public static String CreatePLCategoriesController_creation_msg;
  public static String CreateFECategoriesController_creation_msg;
  public static String UpdateCECategories_remove_msg;
  public static String UpdateCMCategories_remove_msg;
  public static String UpdateFECategories_remove_msg;
  public static String UpdatePLCategories_remove_msg;
  public static String UpdateCECategories_add_msg;
  public static String UpdateCMCategories_add_msg;
  public static String UpdateFECategories_add_msg;
  public static String UpdatePLCategories_add_msg;

  static {
    // initialize resource bundle
    NLS.initializeMessages(BUNDLE_NAME, Messages.class);
  }

  private Messages() {
    // Does nothing
  }
}
