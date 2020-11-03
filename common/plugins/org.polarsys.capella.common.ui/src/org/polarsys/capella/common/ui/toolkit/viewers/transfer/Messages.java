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
package org.polarsys.capella.common.ui.toolkit.viewers.transfer;

import org.eclipse.osgi.util.NLS;

/**
 * I18n support.
 */
public class Messages extends NLS {
  private static final String BUNDLE_NAME = "org.polarsys.capella.common.ui.toolkit.viewers.transfer.messages"; //$NON-NLS-1$
  public static String AbstractTransferViewer2_AddAllElements_Tooltip;
  public static String AbstractTransferViewer2_AddSelectedElements_Tooltip;
  public static String AbstractTransferViewer2_LeftViewer_Title;
  public static String AbstractTransferViewer2_RemoveAllElements_Tooltip;
  public static String AbstractTransferViewer2_RemoveSelectedElements_Tooltip;
  public static String AbstractTransferViewer2_RightViewer_Title;
  public static String OrderedTransferTreeListViewer_Down_Title;
  public static String OrderedTransferTreeListViewer_Down_Tooltip;
  public static String OrderedTransferTreeListViewer_Up_Title;
  public static String OrderedTransferTreeListViewer_Up_Tooltip;
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
