/*******************************************************************************
 * Copyright (c) 2006, 2017 THALES GLOBAL SERVICES.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *  
 * Contributors:
 *    Thales - initial API and implementation
 *******************************************************************************/
package org.polarsys.capella.core.commandline.core;

import java.util.Map;

import org.eclipse.equinox.app.IApplicationContext;

/**
 */
public class CommandLineArgumentHelper {

  /**
   * 
   */

  private static CommandLineArgumentHelper instance;
  private String filePath;
  private String outputFolder;
  private boolean helpNeeded;
  private String logFilePath;
  private String appid;
  private String importProjects;
  private boolean copyOnWorkspace = false;
  private boolean createFolder;

  private String exportProject;

  private String zipNameProject;

  protected static String[] args;

  public static CommandLineArgumentHelper getInstance() {
    if (null == instance) {
      instance = new CommandLineArgumentHelper();
    }
    return instance;
  }

  public static String[] parseContext(IApplicationContext context) {
    Map arguments = context.getArguments();
    args = (String[]) arguments.get(IApplicationContext.APPLICATION_ARGS);
    return args;
  }

  public void parseArgs(String[] args) {

    for (int i = 0; i < args.length; i++) {
      String arg = args[i].toLowerCase();

      if (CommandLineConstants.HELP.equalsIgnoreCase(arg)) {
        helpNeeded = true;

      } else if (CommandLineConstants.ID.equalsIgnoreCase(arg)) {
        appid = args[++i];

      } else if (CommandLineConstants.IMPORT.equalsIgnoreCase(arg)) {
        importProjects = args[++i];

      } else if (CommandLineConstants.EXPORT.equalsIgnoreCase(arg)) {
        exportProject = args[++i];

      } else if (CommandLineConstants.EXPORT_ZIP_NAME.equalsIgnoreCase(arg)) {
        zipNameProject = args[++i];

      } else if (CommandLineConstants.FILE_PATH.equalsIgnoreCase(arg)) {
        filePath = args[++i];

      } else if (CommandLineConstants.OUTPUTFOLDER.equalsIgnoreCase(arg)) {
        outputFolder = args[++i];

      } else if (CommandLineConstants.FORCEOUTPUTFOLDERCREATION.equalsIgnoreCase(arg)) {
        createFolder = true;

      } else if (CommandLineConstants.LOG_FILE_PATH.equalsIgnoreCase(arg)) {
        logFilePath = args[++i];

      } else if (CommandLineConstants.COPY_ON_WORKSPACE.equalsIgnoreCase(arg)) {
        copyOnWorkspace = true;

      }

    }
  }

  /**
   * @return the args
   */
  public static String[] getArgs() {
    return args;
  }

  /**
   * @return the filePath
   */
  public String getFilePath() {
    return filePath;
  }

  /**
   * @return the outputFolder
   */
  public String getOutputFolder() {
    return outputFolder;
  }

  /**
   * @return the helpNeeded
   */
  public boolean isHelpNeeded() {
    return helpNeeded;
  }

  /**
   * @return the appid
   */
  public String getAppid() {
    return appid;
  }

  /**
   * @return the importProjects
   */
  public String getImportProjects() {
    return importProjects;
  }

  /**
   * @return the createFolder
   */
  public boolean isCreateFolder() {
    return createFolder;
  }

  /**
   * @return the logFilePath
   */
  public String getLogFilePath() {
    return logFilePath;
  }

  /**
   * @return the createFolder
   */
  public boolean isCopyOnWorkspace() {
    return copyOnWorkspace;
  }

  /**
   * @return the exportProject
   */
  public String getExportProject() {
    return exportProject;
  }

  /**
   * @return the zipNameProject
   */
  public String getZipNameProject() {
    return zipNameProject;
  }
}
