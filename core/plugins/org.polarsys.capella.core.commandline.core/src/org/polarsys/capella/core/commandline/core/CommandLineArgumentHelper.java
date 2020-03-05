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

import org.eclipse.equinox.app.IApplicationContext;
import org.polarsys.capella.common.application.ArgumentsHelper;
import org.polarsys.capella.common.application.CommonArgumentsConstants;

/**
 */
public class CommandLineArgumentHelper {

  private static CommandLineArgumentHelper instance;
  
  @Deprecated
  private String filePath;
  private String input;
  private String outputFolder;
  private boolean helpNeeded;
  private String logFilePath;
  private String appid;
  private String importProjects;
  private boolean forceImport;
  private boolean copyOnWorkspace = false;
  @Deprecated
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
    args = (String[]) context.getArguments().get(IApplicationContext.APPLICATION_ARGS);
    return args;
  }

  public void parseArgs(String[] args) {
    ArgumentsHelper helper = ArgumentsHelper.getInstance();
    helper.loadArguments(args);
    
    helpNeeded = helper.hasParameter(CommandLineConstants.HELP);
    createFolder = helper.hasParameter(CommandLineConstants.FORCEOUTPUTFOLDERCREATION);
    copyOnWorkspace = helper.hasParameter(CommandLineConstants.COPY_ON_WORKSPACE);
    appid = helper.getString(CommandLineConstants.ID);
    importProjects = helper.getString(CommandLineConstants.IMPORT);
    forceImport = helper.hasParameter(CommandLineConstants.FORCEIMPORT);
    exportProject = helper.getString(CommandLineConstants.EXPORT);
    zipNameProject = helper.getString(CommandLineConstants.EXPORT_ZIP_NAME);
    filePath = helper.getString(CommandLineConstants.FILE_PATH);
    input = helper.getString(CommandLineConstants.INPUT);
    outputFolder = helper.getString(CommandLineConstants.OUTPUTFOLDER);
    logFilePath = helper.getString(CommonArgumentsConstants.LOG_FILE_PATH);
    
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
   * @return the forceImport
   */
  public boolean isForceImport() {
    return forceImport;
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
  
  /**
   * @return the inputs
   */
  public String getInputs() {
    return input;
  }
}
