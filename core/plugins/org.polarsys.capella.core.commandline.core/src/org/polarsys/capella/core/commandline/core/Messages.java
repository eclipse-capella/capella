/*******************************************************************************
 * Copyright (c) 2006, 2017 THALES GLOBAL SERVICES.
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
package org.polarsys.capella.core.commandline.core;

import org.eclipse.osgi.util.NLS;

/**
 */
public class Messages extends NLS {
  private static final String BUNDLE_NAME = "org.polarsys.capella.core.commandline.core.messages"; //$NON-NLS-1$
  public static String cannot_create_folder;
  public static String Error_versionCompliancy;
  public static String Error_needMigrationMessage;
  public static String Error_CorruptedMessage;
  public static String workspace_in_use;
  public static String refresh_problem;
  public static String project;
  public static String no_app_found;
  public static String not_exist;
  public static String outputfolder_mandatory;
  public static String aird;
  public static String exec_pb;
  public static String filepath_point_to_aird;
  public static String generation_done;
  public static String representation_mandatory;
  public static String resource_prefix;
  public static String displayIsNull;
  public static String already_exist;
  public static String could_not_create_exec;
  public static String unable_load_extension;
  public static String unable_open_project;
  public static String compliance_check_pb;
  public static String import_mandatory;
  public static String inputs_mandatory;
  public static String aird_not_found;
  public static String export_zip_not_found;
  public static String export_zip_no_ouputfolder;
  static {
    // initialize resource bundle
    NLS.initializeMessages(BUNDLE_NAME, Messages.class);
  }

  private Messages() {
  }
}
