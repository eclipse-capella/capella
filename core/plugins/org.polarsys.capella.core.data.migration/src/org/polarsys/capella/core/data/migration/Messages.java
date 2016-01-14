/*******************************************************************************
 * Copyright (c) 2006, 2015 THALES GLOBAL SERVICES.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *  
 * Contributors:
 *    Thales - initial API and implementation
 *******************************************************************************/
package org.polarsys.capella.core.data.migration;

import org.eclipse.osgi.util.NLS;

/**
 * I18n support.
 */
public class Messages extends NLS {
  private static final String BUNDLE_NAME = "org.polarsys.capella.core.data.migration.messages"; //$NON-NLS-1$
  public static String ECore2ECoreMigrationAction_Migration_OutOfMemoryError_Title;
  public static String ECore2ECoreMigrationAction_Migration_OutOfMemoryError_Description;

  public static String MigrationAction_Title;

  public static String MigrationAction_Command_LoadResources;
  public static String MigrationAction_Command_SaveResources;
  public static String MigrationAction_Command_SaveResource;
  public static String MigrationAction_Command_ProcessingMigration;

  static {
    // initialize resource bundle
    NLS.initializeMessages(BUNDLE_NAME, Messages.class);
  }

  private Messages() {
    // Do nothing.
  }
}
