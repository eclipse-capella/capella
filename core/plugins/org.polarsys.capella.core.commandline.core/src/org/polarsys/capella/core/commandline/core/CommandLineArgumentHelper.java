/*******************************************************************************
 * Copyright (c) 2006, 2026 THALES GLOBAL SERVICES.
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

import org.eclipse.equinox.app.IApplicationContext;
import org.polarsys.capella.common.application.ArgumentsHelper;
import org.polarsys.capella.common.application.CommonArgumentsConstants;

/**
 */
public class CommandLineArgumentHelper {

  private static CommandLineArgumentHelper instance;

  private String input;
  private String outputFolder;
  
  private boolean helpNeeded;
  private String logFilePath;
  private String appid;
  private String importProjects;
  private String importAllProjects;
  private String exportZips;
  private boolean forceImport;
  private boolean copyOnWorkspace = false;
  private boolean backup;

  private String exportProjects;
  private boolean singleZip;
  private boolean exportCopy;
  private String exportTarget;

  /**
   * @deprecated (use -input instead)
   */
  @Deprecated
  private String filePath;
  /**
   * @deprecated (output folder is always created if it does not exist)
   */
  @Deprecated
  private boolean createFolder;
  @Deprecated
  /**
   * @deprecated (use -exportZip instead)
   */
  private String exportProject;
  /**
   * @deprecated (exported zips have the same name as the exported projects)
   */
  @Deprecated
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
    // Avoid singleton to be able to chain commands.
    ArgumentsHelper helper = new ArgumentsHelper();
    helper.loadArguments(args);
    
    helpNeeded = helper.hasParameter(CommandLineConstants.HELP);
    createFolder = helper.hasParameter(CommandLineConstants.FORCEOUTPUTFOLDERCREATION);
    copyOnWorkspace = helper.hasParameter(CommandLineConstants.COPY_ON_WORKSPACE);
    appid = helper.getString(CommandLineConstants.ID);
    importProjects = helper.getString(CommandLineConstants.IMPORT);
    importAllProjects = helper.getString(CommandLineConstants.IMPORT_ALL);
    exportZips = helper.getString(CommandLineConstants.EXPORTZIP);
    forceImport = helper.hasParameter(CommandLineConstants.FORCEIMPORT);
    exportProject = helper.getString(CommandLineConstants.EXPORT);
    zipNameProject = helper.getString(CommandLineConstants.EXPORT_ZIP_NAME);
    filePath = helper.getString(CommandLineConstants.FILE_PATH);
    input = helper.getString(CommandLineConstants.INPUT);
    outputFolder = helper.getString(CommandLineConstants.OUTPUTFOLDER);
    logFilePath = helper.getString(CommonArgumentsConstants.LOG_FILE_PATH);
    backup = helper.hasParameter(CommandLineConstants.BACKUP);
    
    singleZip = helper.hasParameter(CommandLineConstants.SINGLE_ZIP);
    exportProjects = helper.getString(CommandLineConstants.EXPORT_LIST);
    exportCopy = helper.hasParameter(CommandLineConstants.EXPORT_COPY);
    exportTarget = helper.getString(CommandLineConstants.EXPORT_TARGET);
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
  @Deprecated
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
   * @return the importAllProjects
   */
  public String getImportAllProjects() {
    return importAllProjects;
  }

  /**
   * @return the exportZips
   */
  public String getExportZips() {
    return exportZips;
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
  @Deprecated
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
  @Deprecated
  public String getExportProject() {
    return exportProject;
  }

  /**
   * @return the zipNameProject
   */
  @Deprecated
  public String getZipNameProject() {
    return zipNameProject;
  }
  
  /**
   * @return the inputs
   */
  public String getInputs() {
    return input;
  }
  
  /**
   * 
   * @return whether a backup is needed
   */
  public boolean isBackupNeeded() {
    return backup;
  }
  
  /**
   * Returns true if the export must create a single archive.
   * 
   * @return path of a file
   */
  public boolean isSingleZip() {
    return singleZip;
  }
  
  /**
   * Returns the path of the folder to zip projects in.
   * 
   * @return path of a file
   */
  public String getExportTarget() {
    return exportTarget;
  }

  /**
   * Returns true if the export must only perform a copy.
   * 
   * @return true for copy
   */
  public boolean isExportCopy() {
    return exportCopy;
  }

  /**
   * Returns the list of project to export.
   * 
   * @return path of a file
   */
  public String getExportProjects() {
    return exportProjects;
  }


}
