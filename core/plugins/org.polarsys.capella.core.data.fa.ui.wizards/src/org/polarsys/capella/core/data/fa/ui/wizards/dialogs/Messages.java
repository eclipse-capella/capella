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
package org.polarsys.capella.core.data.fa.ui.wizards.dialogs;

import org.eclipse.osgi.util.NLS;

/**
 * I18n support.
 */
public class Messages extends NLS {
  private static final String BUNDLE_NAME = "org.polarsys.capella.core.data.fa.ui.wizards.dialogs.messages"; //$NON-NLS-1$

  public static String EIAllocationTransfertDlg_Title;
  public static String EIAllocationTransfertDlg_ShellTitle;
  public static String EIAllocationTransfertDlg_Message;
  
  public static String EIAllocationManagementWizardAction_Warning_Title;
  public static String EIAllocationManagementWizardAction_Warning_Message;
  
  static {
    // initialize resource bundle
    NLS.initializeMessages(BUNDLE_NAME, Messages.class);
  }

  private Messages() {
    // Does nothing
  }
}
