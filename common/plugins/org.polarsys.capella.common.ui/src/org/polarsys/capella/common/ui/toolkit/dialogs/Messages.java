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
package org.polarsys.capella.common.ui.toolkit.dialogs;

import org.eclipse.osgi.util.NLS;

/**
 * I18n support.
 */
public class Messages extends NLS {
  private static final String BUNDLE_NAME = "org.polarsys.capella.common.ui.toolkit.dialogs.messages"; //$NON-NLS-1$

  public static String AbstractViewerDialog_CANCEL_Title;
  public static String AbstractViewerDialog_OK_Title;
  public static String CheckboxTreeDialog_Window_Title;
  public static String SelectElementsDialog_Shell_Title;
  public static String TransferTreeListDialog_Shell_Title;

  public static String exportButtonLabel;
  public static String fileBrowserDialogTitle;

  public static String exportMetrics;
  public static String exportOk;
  public static String exportKo;

  public static String exportRootFileNameLabel;
  public static String exportDateLabel;

  public static String selectAll;
  public static String deselectAll;
  
  public static String blankName;
  public static String newRepresentationFor;
  public static String openRepresentationMessage;

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
