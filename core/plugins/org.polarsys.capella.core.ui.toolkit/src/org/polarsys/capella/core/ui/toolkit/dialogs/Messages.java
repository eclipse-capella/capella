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
package org.polarsys.capella.core.ui.toolkit.dialogs;

import org.eclipse.osgi.util.NLS;

/**
 * I18n support.
 */
public class Messages extends NLS {
  private static final String BUNDLE_NAME = "org.polarsys.capella.core.ui.toolkit.dialogs.messages"; //$NON-NLS-1$

  public static String Ok_Title;
  public static String Cancel_Title;
  public static String ImpactAnalysisDialog_ResourceButton_Title;

  public static String CapellaDeleteCommand_ImpactAnalysis_ReferencingElements_Group_Title;

  public static String CapellaDeleteCommand_ImpactAnalysis_ReferencingElements_Group_Tooltip;

  public static String CapellaDeleteCommand_ImpactAnalysis_DeletedElements_Group_Title;

  public static String CapellaDeleteCommand_ImpactAnalysis_DeletedElements_Group_Tooltip;

  public static String CapellaDeleteCommand_Label;

public static String CapellaDeleteCommand_ConfirmDeletionQuestion;

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
