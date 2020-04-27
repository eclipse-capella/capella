/*******************************************************************************
 * Copyright (c) 2006, 2015 THALES GLOBAL SERVICES.
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
package org.polarsys.capella.core.data.migration.capella;

import org.eclipse.osgi.util.NLS;

/**
 * I18n support.
 * 
 */
public class Messages extends NLS {
  private static final String BUNDLE_NAME = "org.polarsys.capella.core.data.migration.capella.messages"; //$NON-NLS-1$
  public static String ECore2ECoreMigrationAction_Migration_OutOfMemoryError_Title;
  public static String ECore2ECoreMigrationAction_Migration_OutOfMemoryError_Description;

  public static String MigrationAction_ErrorDialog_TooOldMessage;
  public static String MigrationAction_ErrorDialog_CorruptedMessage;
  public static String MigrationAction_ErrorDialog_LibrariesNotMigratedMessage;

  public static String MigrationAction_Command_LoadResources;
  public static String MigrationAction_Command_SaveResources;
  public static String MigrationAction_Command_SaveResource;
  public static String MigrationAction_Command_ProcessingMigration;

  public static String MigrationAction_CapellaMigration;

  static {
    // initialize resource bundle
    NLS.initializeMessages(BUNDLE_NAME, Messages.class);
  }

  private Messages() {
    // Do nothing.
  }
}
