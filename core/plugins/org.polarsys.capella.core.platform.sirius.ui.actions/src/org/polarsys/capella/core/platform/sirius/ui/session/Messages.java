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
package org.polarsys.capella.core.platform.sirius.ui.session;

import org.eclipse.osgi.util.NLS;

/**
 */
public class Messages extends NLS {
  private static final String BUNDLE_NAME = "org.polarsys.capella.core.platform.sirius.ui.session.messages"; //$NON-NLS-1$

  public static String CapellaSessionHelper_CreateSession_Title;
  public static String CapellaSessionHelper_Repair_Migrate_Message;

  public static String CapellaSessionHelper_Repair_Migrate_Project_Message;
  public static String CapellaSessionHelper_SemanticModel_Error_Message_WrongVersion_Part1;
  public static String CapellaSessionHelper_SemanticModel_Error_Message_WrongVersion_Part2;
  public static String CapellaSessionHelper_SemanticModel_ErrorDialog_Title;
  public static String CapellaSessionHelper_UnknownError_Message;
  public static String CapellaSessionHelper_MissingExtensions_Message;
  public static String CapellaSessionHelper_MissingLibraries_Message;
  public static String GitConflictHelper_ResourcesInConflictState;
  public static String GitConflictSessionListener_ResourcesInConflictState;
  public static String GitConflictSessionListener_DialogTitle;

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
