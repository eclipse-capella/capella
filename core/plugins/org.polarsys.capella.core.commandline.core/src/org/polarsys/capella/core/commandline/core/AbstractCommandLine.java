/*******************************************************************************
 * Copyright (c) 2006, 2014 THALES GLOBAL SERVICES.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *  
 * Contributors:
 *    Thales - initial API and implementation
 *******************************************************************************/
package org.polarsys.capella.core.commandline.core;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.apache.log4j.Logger;
import org.eclipse.core.resources.IFile;
import org.eclipse.core.resources.IFolder;
import org.eclipse.core.resources.IProject;
import org.eclipse.core.resources.IProjectDescription;
import org.eclipse.core.resources.IResource;
import org.eclipse.core.resources.ResourcesPlugin;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.MultiStatus;
import org.eclipse.core.runtime.NullProgressMonitor;
import org.eclipse.core.runtime.Path;
import org.eclipse.core.runtime.Platform;
import org.eclipse.emf.common.util.URI;
import org.eclipse.equinox.app.IApplicationContext;
import org.eclipse.osgi.util.NLS;

import org.polarsys.capella.common.bundle.FeatureHelper;
import org.polarsys.capella.common.tools.report.config.registry.ReportManagerRegistry;
import org.polarsys.capella.common.tools.report.util.IReportManagerDefaultComponents;
import org.polarsys.capella.common.mdsofa.common.constant.ICommonConstants;
import org.polarsys.capella.core.model.handler.command.CapellaResourceHelper;
import org.polarsys.capella.core.model.handler.helpers.CapellaFeatureHelper;

/**
 */
public class AbstractCommandLine implements ICommandLine {

  public static int FATAL = IStatus.CANCEL;

  protected static Logger __logger;
  protected static CommandLineArgumentHelper argHelper;
  protected CommandLineMode mode;

  /**
  * 
  */
  public AbstractCommandLine() {
    argHelper = CommandLineArgumentHelper.getInstance();
    __logger =
        ReportManagerRegistry.getInstance().subscribe(IReportManagerDefaultComponents.DEFAULT);
  }

  /**
   * parses the following parameters: {@value org.polarsys.capella.core.commandline.core.CommandLineConstants#HELP},
   * {@value org.polarsys.capella.core.commandline.core.CommandLineConstants#ID},
   * {@value org.polarsys.capella.core.commandline.core.CommandLineConstants#DATA},
   * {@value org.polarsys.capella.core.commandline.core.CommandLineConstants#IMPORT},
   * {@value org.polarsys.capella.core.commandline.core.CommandLineConstants#EXPORT},
   * {@value org.polarsys.capella.core.commandline.core.CommandLineConstants#EXPORT_ZIP_NAME},
   * {@value org.polarsys.capella.core.commandline.core.CommandLineConstants#COPY_ON_WORKSPACE},
   * 
   * 
   * {@value org.polarsys.capella.core.commandline.core.CommandLineConstants#FILE_PATH},
   * {@value org.polarsys.capella.core.commandline.core.CommandLineConstants#FORCEOUTPUTFOLDERCREATION},
   * {@value org.polarsys.capella.core.commandline.core.CommandLineConstants#OUTPUTFOLDER}
   * {@value org.polarsys.capella.core.commandline.core.CommandLineConstants#LOG_FILE_PATH},
   */
  @Override
  public void parseContext(IApplicationContext context_p) throws CommandLineException {

    String[] args = CommandLineArgumentHelper.parseContext(context_p);
    argHelper.parseArgs(args);

  }

  /**
   * {@inheritDoc}
   * @throws IOException
   */
  @Override
  public void checkArgs(IApplicationContext context_p) throws CommandLineException {
    // parseContext(context_p);
    isWorkspaceInUse();

    // refreshing the workspace needed in case of folders removed from outside the workbench
    try {
      ResourcesPlugin.getWorkspace().getRoot()
          .refreshLocal(IResource.DEPTH_INFINITE, new NullProgressMonitor());
    } catch (CoreException exception_p) {
      logErrorAndThrowException(Messages.refresh_problem);
    }

    // is filepath empty ?
    if (isEmtyOrNull(argHelper.getFilePath())) {
      logErrorAndThrowException(Messages.representation_mandatory);
    }

    // is outputfolder empty ?
    if (isEmtyOrNull(argHelper.getOutputFolder())) {
      logErrorAndThrowException(Messages.outputfolder_mandatory);
    }
    // check -import argument
    if (isEmtyOrNull(argHelper.getImportProjects())) {
      setMode(CommandLineMode.NO_IMPORT);
      // project are not imported => check that -filepath and -outputfolder exists into the workspace
      checkFilePath();

      if (!argHelper.isCreateFolder()) {
        checkoutputFolder();
      }
    }
    // -import <list of projects> is specified => import the projects
    else if (!isEmtyOrNull(argHelper.getImportProjects())) {
      setMode(CommandLineMode.IMPORT);
    }

  }

  /**
   * {@inheritDoc}
   * @throws CommandLineException
   */
  @Override
  public void prepare(IApplicationContext context_p) throws CommandLineException {
    if (!projectVersionIsCompliant()) {
      logErrorAndThrowException(Messages.Error_versionCompliancy);
    }
    if (CommandLineMode.NO_IMPORT.equals(mode)) {
      // nothing to do
    }

    else if (CommandLineMode.IMPORT.equals(mode)) {
      try {
        importProjects(toList(argHelper.getImportProjects()));
      } catch (CoreException exception_p) {
        throw new CommandLineException(exception_p.getMessage());
      }
    }

    // create outputfolder if necessary
    if (argHelper.isCreateFolder()) {
      createOutputFolder(argHelper.getOutputFolder());
    }

  }

  public boolean projectVersionIsCompliant() throws CommandLineException {
    // retrieve melodymodeller files
    // project exists in the workspace ?
    String projectName = getProjectName(argHelper.getFilePath());
    IProject project = ResourcesPlugin.getWorkspace().getRoot().getProject(projectName);
    if (!project.exists()) {
      logError(Messages.project + projectName + Messages.not_exist);
    }
    // file exists in the project ?
    try {
      project.open(new NullProgressMonitor());
    } catch (CoreException exception_p) {
      StringBuilder loggerMessage = new StringBuilder("HTMLCommandLine.checkArgs(..) _ "); //$NON-NLS-1$
      __logger.warn(loggerMessage.toString(), exception_p);
    }

    List<IFile> melodymodellerFiles = getCapellamodellerFiles(project);

    // check that all melodymodeller files are compliant with current Capella version
    for (IFile modeller : melodymodellerFiles) {
      compliancyCheck(modeller);
    }

    return true;
  }

  /**
   * @param modeller_p
   * @return
   */
  private void compliancyCheck(IFile modeller_p) throws CommandLineException {
    // Check the detected version.
    // Migration can only migrate from n-1 to n versions.
    // Get major & minor version in given capella resource.
    String fileToMigrateVersion =
        CapellaFeatureHelper.getDetectedVersion(modeller_p).substring(0, 5);
    int fileToMigrateRelease = getReleaseVersion(fileToMigrateVersion);
    int fileToMigrateMajorVersion = getMajorVersion(fileToMigrateVersion);
    // Current version
    String version = FeatureHelper.getCapellaVersion(false);
    if (null != version) {
      String capellaCurrentVersion = version.substring(0, 5);
      int currentRelease = getReleaseVersion(capellaCurrentVersion);
      int currentMajorVersion = getMajorVersion(capellaCurrentVersion);
      // Compare versions...
      if (fileToMigrateRelease != currentRelease) {
        String formattedMessage =
            NLS.bind(Messages.Error_needMigrationMessage, new String[] { modeller_p.getFullPath()
                .toString() });
        throw new CommandLineException(formattedMessage);
      } else if (currentMajorVersion != fileToMigrateMajorVersion) {
        String formattedMessage =
            NLS.bind(Messages.Error_needMigrationMessage, new String[] { modeller_p.getFullPath()
                .toString() });
        throw new CommandLineException(formattedMessage);

      }

    } else {
      String formattedMessage =
          NLS.bind(Messages.Error_CorruptedMessage, new String[] { modeller_p.getFullPath()
              .toString() });
      throw new CommandLineException(formattedMessage);
    }

  }

  /**
   * Get the major figure from a version.
   * @param version_p must conform to 'x.y' format.
   * @return y as an integer.
   */
  protected int getMajorVersion(String version_p) {
    return Integer.parseInt(ICommonConstants.EMPTY_STRING + version_p.charAt(2));
  }

  /**
   * Get the minor figure from a version.
   * @param version_p must conform to 'x.y.z' format.
   * @return z as an integer.
   */
  protected int getMinorVersion(String version_p) {
    return Integer.parseInt(ICommonConstants.EMPTY_STRING + version_p.charAt(4));
  }

  /**
   * Get the release figure from a version.
   * @param version_p must conform to 'x.y' format.
   * @return x as an integer.
   */
  protected int getReleaseVersion(String version_p) {
    return Integer.parseInt(ICommonConstants.EMPTY_STRING + version_p.charAt(0));
  }

  /**
   * @param project_p
   */
  private List<IFile> getCapellamodellerFiles(IProject project_p) {
    List<IFile> files = new ArrayList<IFile>();
    try {

      for (IResource res : project_p.members()) {

        if (CapellaResourceHelper.isCapellaResource(res, false)) {
          files.add((IFile) res);
        }

      }

    } catch (CoreException exception_p) {
      StringBuilder loggerMessage = new StringBuilder(Messages.compliance_check_pb);
      __logger.warn(loggerMessage.toString(), exception_p);
    }
    return files;

  }

  /**
   * {@inheritDoc}
   */
  @Override
  public boolean execute(IApplicationContext context_p) throws CommandLineException {
    // do nothing
    return false;
  }

  /**
   * @param message_p
   * @throws CommandLineException
   */
  protected void logErrorAndThrowException(String message_p) throws CommandLineException {
    logError(message_p);
    throw new CommandLineException(message_p);
  }

  /**
   * @throws CommandLineException
   */
  private void isWorkspaceInUse() throws CommandLineException {
    try {
      if (!Platform.getInstanceLocation().lock()) {
        throw new CommandLineException(Messages.workspace_in_use);
      }
    } catch (IOException exception_p) {
      StringBuilder loggerMessage = new StringBuilder(exception_p.getMessage());
      __logger.error(loggerMessage.toString(), exception_p);
    }

  }

  /**
   * @param importProjects_p
   * @return
   */
  protected boolean isEmtyOrNull(String var) {
    return ((null == var) || var.isEmpty());
  }

  /**
   * @throws CommandLineException
   */
  private void checkoutputFolder() throws CommandLineException {
    Path path = new Path(argHelper.getOutputFolder());
    IFolder folder = ResourcesPlugin.getWorkspace().getRoot().getFolder(path);
    if (!folder.exists()) {
      String message = "folder " + argHelper.getOutputFolder() + Messages.not_exist; //$NON-NLS-1$
      logError(message);
      throw new CommandLineException(message);
    }

  }

  /**
   * @throws CommandLineException
   */
  private void checkFilePath() throws CommandLineException {
    // project exists in the workspace ?
    String projectName = getProjectName(argHelper.getFilePath());
    IProject project = ResourcesPlugin.getWorkspace().getRoot().getProject(projectName);
    if (!project.exists()) {
      logError(Messages.project + projectName + Messages.not_exist);
    }
    // file exists in the project ?
    try {
      project.open(new NullProgressMonitor());
    } catch (CoreException exception_p) {
      StringBuilder loggerMessage =
          new StringBuilder(Messages.unable_open_project + project.getName());
      __logger.warn(loggerMessage.toString(), exception_p);
    }
    IFile file = project.getFile(getRelativeFilePath(argHelper.getFilePath()));
    if (!file.exists()) {
      String message = Messages.aird + argHelper.getFilePath() + Messages.not_exist;
      logError(message);
      throw new CommandLineException(message);
    }
  }

  protected void logStatus(IStatus status_p) {
    if (status_p.isOK()) {
      __logger.info(status_p.getMessage());

    } else if (FATAL == status_p.getSeverity()) {
      __logger.fatal(status_p.getMessage());

    } else if (IStatus.ERROR == status_p.getSeverity()) {
      __logger.error(status_p.getMessage());

    } else if (IStatus.WARNING == status_p.getSeverity()) {
      __logger.warn(status_p.getMessage());

    } else {
      __logger.info(status_p.getMessage());

    }
    if (status_p.isMultiStatus()) {
      MultiStatus mStatus = (MultiStatus) status_p;
      for (IStatus childStatus : mStatus.getChildren()) {
        logStatus(childStatus);
      }
    }
  }

  protected void logError(String message) {
    StringBuilder loggerMessage = new StringBuilder(message);
    __logger.error(loggerMessage.toString());
  }

  /**
   * @param string_p
   */
  protected void logInfo(String message) {
    StringBuilder loggerMessage = new StringBuilder(message);
    __logger.info(loggerMessage.toString());

  }

  /**
   * use logInfo(String message) instead.
   * @param message
   */
  @Deprecated
  protected void LogInfo(String message) {
    logInfo(message);
  }

  /**
   * @param mode_p the mode to set
   */
  public void setMode(CommandLineMode mode_p) {
    mode = mode_p;
  }

  /**
   * Transform a space separated list of strings into a List
   * @param importProjects_p
   * @return
   */
  private List<String> toList(String string) {
    List<String> list = new ArrayList<String>();
    for (String path : Arrays.asList(string.split("\\|"))) { //$NON-NLS-1$
      list.add(path.trim());
    }
    return list;
  }

  /**
   * @param projectsToImport
   * @param workspacePath_p
   * @throws CoreException
   */
  private void importProjects(List<String> projectsToImport) throws CoreException {

    for (String projectPath : projectsToImport) {
      IProject project = null;

      if (projectPath.endsWith(".zip")) {
        IFile file = ResourcesPlugin.getWorkspace().getRoot().getFile(new Path(projectPath));
        WorkbenchHelper.importZipFile(file);

      } else {
        IProjectDescription description = ResourcesPlugin.getWorkspace().loadProjectDescription(new Path(projectPath).append(".project")); //$NON-NLS-1$
        project = ResourcesPlugin.getWorkspace().getRoot().getProject(description.getName());
        if (!project.exists()) {
          project.create(description, null);
        }
        if (!project.isOpen()) {
          project.open(null);
        }
      }
    }

  }

  /**
   * @param outputFolder_p
   * @throws CommandLineException
   */
  private void createOutputFolder(String outputFolder_p) throws CommandLineException {
    IFolder folder = ResourcesPlugin.getWorkspace().getRoot().getFolder(new Path(outputFolder_p));

    try {
      IProject project = folder.getProject();
      if (!project.exists()) {
        project.create(new NullProgressMonitor());
      }

      if (!project.isOpen()) {
        project.open(new NullProgressMonitor());
      }
      if (!folder.exists()) {
        folder.create(true, true, new NullProgressMonitor());
      }
      else{
        logInfo(outputFolder_p + Messages.already_exist);
     }

    } catch (CoreException exception_p) {
      StringBuilder message = new StringBuilder(Messages.cannot_create_folder);
      __logger.error(message.toString(), exception_p);
      throw new CommandLineException(message.toString());
    }
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public void printHelp() {
    System.out.println("*** Capella Command Line Core Mechanism ***"); //$NON-NLS-1$
    System.out
        .println(CommandLineConstants.ID
                 + " value : defines the id of the command line application to launch, see org.polarsys.capella.core.commandline.core.commandline extension point."); //$NON-NLS-1$
    System.out.println(CommandLineConstants.DATA + " value : defines the path to the workspace."); //$NON-NLS-1$
    System.out
        .println(CommandLineConstants.IMPORT
                 + " value : defines a list of projects to import into the workspace before doing the actual job. List of projects is a '|' separated list."); //$NON-NLS-1$
    System.out.println(CommandLineConstants.FILE_PATH
                       + " value : defines the path to your aird file."); //$NON-NLS-1$
    System.out.println(CommandLineConstants.OUTPUTFOLDER
                       + " value : defines the path to the output folder."); //$NON-NLS-1$
    System.out.println(CommandLineConstants.FORCEOUTPUTFOLDERCREATION
                       + " value : create the output folder if it does not exist."); //$NON-NLS-1$
    System.out.println(CommandLineConstants.LOG_FILE_PATH
                       + " value : defines the path to the log file."); //$NON-NLS-1$
    System.out.println(CommandLineConstants.HELP + " : prints the help message"); //$NON-NLS-1$
  }

  /**
   * @param filePath_p
   * @return
   */
  public static String getProjectName(String filePath_p) {
    URI fileURI = URI.createFileURI(filePath_p);
    return URI.decode(fileURI.segment(0));
  }

  /**
   * @param filePath_p
   * @return
   */
  public static String getRelativeFilePath(String filePath_p) {
    URI fileURI = URI.createFileURI(filePath_p);
    List<String> segments = new ArrayList<String>(Arrays.asList(fileURI.segments()));
    segments.remove(0);
    URI uri = URI.createFileURI("").appendSegments(segments.toArray(new String[segments.size()])); //$NON-NLS-1$
    return uri.toFileString();
  }

}
