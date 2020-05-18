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
package org.polarsys.capella.core.data.interaction.properties.dialogs;

import org.eclipse.osgi.util.NLS;

/**
 * I18n support.
 */
public class Messages extends NLS {
  private static final String BUNDLE_NAME = "org.polarsys.capella.core.data.interaction.properties.dialogs.messages"; //$NON-NLS-1$

	public static String SelectOperationDialog_SequenceMessageKind;

  public static String ExchangeDialog_ComponentExchangeLabel;
  public static String ExchangeDialog_CreateComponentExchangeGroup;
  public static String ExchangeDialog_CreateComponentExchangeCheckbox;

  public static String ExchangeDialog_FunctionalExchangeLabel;
  public static String ExchangeDialog_CreateFunctionalExchangeGroup;
  public static String ExchangeDialog_CreateFunctionalExchangeCheckbox;
  public static String ExchangeDialog_SourceFunctionLabel;
  public static String ExchangeDialog_TargetFunctionLabel;
  
  public static String ExchangeDialog_InteractionLabel;
  public static String ExchangeDialog_CreateInteractionGroup;
  public static String ExchangeDialog_CreateInteractionCheckbox;
  public static String ExchangeDialog_SourceActivityLabel;
  public static String ExchangeDialog_TargetActivityLabel;
  
  public static String ExchangeDialog_ExchangeItemLabel;
  public static String ExchangeDialog_SelectExchangeItems;
  public static String ExchangeDialog_ExchangeItemSelectionWizardMessage;
  public static String ExchangeDialog_ExchangeItemSelectionWizardTitle;


  public static String SelectOperationDialog_0;
  public static String SelectOperationDialog_1;
  public static String SelectOperationDialog_2;
  public static String SelectOperationDialog_3;
  public static String SelectOperationDialog_4;
  public static String SelectOperationDialog_5;
  public static String SelectOperationDialog_6;
  public static String SelectOperationDialog_7;
  public static String SelectOperationDialog_8;

  public static String SelectOperationDialog_CreateNewExchangeItem;
  public static String SelectOperationDialog_CreateOrSelectInterface;
  public static String SelectOperationDialog_EnableCreationButton_Title;
  public static String SelectOperationDialog_Interface_Title;

  public static String SelectOperationDialog_InterfacePkgName8;
  public static String SelectOperationDialog_Operation_Title;
	public static String SelectOperationDialog_HideTechnicalInterfaceNamesButton_Title;
  public static String SelectOperationDialog_RestrictedInterfacesButton_Title;
  public static String SelectOperationDialog_SelectExistingOperationGroup_Title;
  public static String SelectOperationDialog_SelectInterfaceDialog_Message;
  public static String SelectOperationDialog_SelectInterfaceDialog_Title;

	public static String SelectOperationDialog_SequenceMessageKind_synchronous;
	public static String SelectOperationDialog_SequenceMessageKind_asynchronous;


  static {
    // initialize resource bundle
    NLS.initializeMessages(BUNDLE_NAME, Messages.class);
  }

  private Messages() {
    // Private constructor.
  }
}
