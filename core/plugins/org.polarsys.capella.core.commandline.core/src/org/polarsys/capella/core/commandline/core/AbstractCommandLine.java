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
package org.polarsys.capella.core.commandline.core;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
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
import org.eclipse.core.runtime.Status;
import org.eclipse.emf.common.util.URI;
import org.eclipse.equinox.app.IApplicationContext;
import org.polarsys.capella.common.application.CommonArgumentsConstants;
import org.polarsys.capella.common.mdsofa.common.constant.ICommonConstants;
import org.polarsys.capella.common.tools.report.config.registry.ReportManagerRegistry;
import org.polarsys.capella.common.tools.report.util.IReportManagerDefaultComponents;
import org.polarsys.capella.common.tools.report.util.LogExt;
import org.polarsys.capella.core.af.integration.CapellaMetadataProvider;
import org.polarsys.capella.core.model.handler.command.CapellaResourceHelper;

/**
 * @deprecated (use DefaultCommandLine instead)
 */
@Deprecated
public class AbstractCommandLine implements ICommandLine {

  public static int FATAL = IStatus.CANCEL;

  protected Logger logger;
  protected CommandLineArgumentHelper argHelper;
  protected CommandLineMode mode;

  private List<String> importedProjects = new ArrayList<String>();

  /**
   *
   */
  public AbstractCommandLine() {
    argHelper = CommandLineArgumentHelper.getInstance();
    logger = ReportManagerRegistry.getInstance().subscribe(IReportManagerDefaultComponents.DEFAULT);
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
   */
  @Override
  public void parseContext(IApplicationContext context) throws CommandLineException {
    String[] args = CommandLineArgumentHelper.parseContext(context);
    argHelper.parseArgs(args);
  }

  /**
   * {@inheritDoc}
   *
   * @throws IOException
   */
  @Override
  public void checkArgs(IApplicationContext context) throws CommandLineException {
    // refreshing the workspace needed in case of folders removed from outside the workbench
    try {
      ResourcesPlugin.getWorkspace().getRoot().refreshLocal(IResource.DEPTH_INFINITE, new NullProgressMonitor());
    } catch (CoreException exception) {
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
   *
   * @throws CommandLineException
   */
  @Override
  public void prepare(IApplicationContext context) throws CommandLineException {
    if (CommandLineMode.NO_IMPORT.equals(mode)) {
      // nothing to do
    }

    else if (CommandLineMode.IMPORT.equals(mode)) {
      try {
        importProjects(toList(argHelper.getImportProjects()));
      } catch (CoreException exception) {
        throw new CommandLineException(exception.getMessage());
      }
    }

    // create outputfolder if necessary
    if (argHelper.isCreateFolder() && argHelper.getOutputFolder() != null) {
      createOutputFolder(argHelper.getOutputFolder());
    }
    if (!projectVersionIsCompliant()) {
      logErrorAndThrowException(Messages.Error_versionCompliancy);
    }

  }

  /**
   * {@inheritDoc}
   *
   * @throws CommandLineException
   */
  @Override
  public void postExecute(IApplicationContext context) throws CommandLineException {
    // Do nothing
  }

  public boolean projectVersionIsCompliant() throws CommandLineException {
    String projectName = getProjectName(argHelper.getFilePath());
    IProject project = ResourcesPlugin.getWorkspace().getRoot().getProject(projectName);
    if (!project.exists()) {
      logError(Messages.project + projectName + Messages.not_exist);
    } else {
      try {
        project.open(new NullProgressMonitor());
      } catch (CoreException exception) {
        StringBuilder loggerMessage = new StringBuilder("HTMLCommandLine.checkArgs(..) _ "); //$NON-NLS-1$
        logger.warn(loggerMessage.toString(), exception);
      }

      List<IFile> capellaResources = getCapellaResourceFiles(project);

      // check that all capella resources are compliant with current Capella version
      for (IFile capellaResource : capellaResources) {
        compliancyCheck(capellaResource);
      }
    }
    return true;
  }

  /**
   * @param modeller
   * @return
   */
  public void compliancyCheck(IFile modeller) throws CommandLineException {
    IStatus status = new CapellaMetadataProvider().checkVersion(modeller);
    if (!status.isOK()) {
      throw new CommandLineException(status.getMessage());
    }
  }

  /**
   * Get the major figure from a version.
   *
   * @param version
   *          must conform to 'x.y' format.
   * @return y as an integer.
   */
  protected int getMajorVersion(String version) {
    return Integer.parseInt(ICommonConstants.EMPTY_STRING + version.charAt(2));
  }

  /**
   * Get the minor figure from a version.
   *
   * @param version
   *          must conform to 'x.y.z' format.
   * @return z as an integer.
   */
  protected int getMinorVersion(String version) {
    return Integer.parseInt(ICommonConstants.EMPTY_STRING + version.charAt(4));
  }

  /**
   * Get the release figure from a version.
   *
   * @param version
   *          must conform to 'x.y' format.
   * @return x as an integer.
   */
  protected int getReleaseVersion(String version) {
    return Integer.parseInt(ICommonConstants.EMPTY_STRING + version.charAt(0));
  }

  /**
   * @param project
   */
  protected List<IFile> getCapellaResourceFiles(IProject project) {
    List<IFile> files = new ArrayList<IFile>();
    try {

      for (IResource res : project.members()) {

        if (CapellaResourceHelper.isCapellaResource(res, false)) {
          files.add((IFile) res);
        }

      }

    } catch (CoreException exception) {
      StringBuilder loggerMessage = new StringBuilder(Messages.compliance_check_pb);
      logger.warn(loggerMessage.toString(), exception);
    }
    return files;

  }

  /**
   * {@inheritDoc}
   */
  @Override
  public boolean execute(IApplicationContext context) throws CommandLineException {
    // do nothing
    return false;
  }

  /**
   * @param message
   * @throws CommandLineException
   */
  protected void logErrorAndThrowException(String message) throws CommandLineException {
    logError(message);
    throw new CommandLineException(message);
  }

  /**
   * @param var
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
    } catch (CoreException exception) {
      StringBuilder loggerMessage = new StringBuilder(Messages.unable_open_project + project.getName());
      logger.warn(loggerMessage.toString(), exception);
    }
    IFile file = project.getFile(getRelativeFilePath(argHelper.getFilePath()));
    if (!file.exists()) {
      String message = Messages.aird + argHelper.getFilePath() + Messages.not_exist;
      logError(message);
      throw new CommandLineException(message);
    }
  }

  protected void logStatus(IStatus status) {
    if (status.isOK()) {
      logger.info(status.getMessage());

    } else if (FATAL == status.getSeverity()) {
      logger.fatal(status.getMessage());

    } else if (IStatus.ERROR == status.getSeverity()) {
      logger.error(status.getMessage());

    } else if (IStatus.WARNING == status.getSeverity()) {
      logger.warn(status.getMessage());

    } else {
      logger.info(status.getMessage());

    }
    if (status.isMultiStatus()) {
      MultiStatus mStatus = (MultiStatus) status;
      for (IStatus childStatus : mStatus.getChildren()) {
        logStatus(childStatus);
      }
    }
  }

  protected void logError(String message) {
    StringBuilder loggerMessage = new StringBuilder(message);
    logger.error(loggerMessage.toString());
  }

  /**
   * @param message
   */
  protected void logInfo(String message) {
    StringBuilder loggerMessage = new StringBuilder(message);
    logger.info(loggerMessage.toString());

  }

  /**
   * @param mode
   *          the mode to set
   */
  public void setMode(CommandLineMode mode) {
    this.mode = mode;
  }

  /**
   * Transform a space separated list of strings into a List
   *
   * @param string
   * @return
   */
  protected List<String> toList(String string) {
    List<String> list = new ArrayList<String>();
    for (String path : Arrays.asList(string.split("\\|"))) { //$NON-NLS-1$
      list.add(path.trim());
    }
    return list;
  }

  /**
   * @param projectsToImport
   * @throws CoreException
   */
  protected void importProjects(List<String> projectsToImport) throws CoreException {

    for (String projectPath : projectsToImport) {
      IProject project = null;

      if (projectPath.endsWith(".zip")) {
        List<IFileImporter> importers = ImporterRegistry.getInstance().getImporters("zip");
        if (!importers.isEmpty()) {
          IFile file = ResourcesPlugin.getWorkspace().getRoot().getFile(new Path(projectPath));
          Collection<IProject> projects = importers.get(0).importFile(file, argHelper.isForceImport());
          for (IProject prj : projects)
            importedProjects.add(prj.getName());
        }
      } else {
        IProjectDescription description = ResourcesPlugin.getWorkspace()
            .loadProjectDescription(new Path(projectPath).append(".project")); //$NON-NLS-1$
        project = ResourcesPlugin.getWorkspace().getRoot().getProject(description.getName());

        if (!argHelper.isForceImport()) {
          if (project.exists()) {
            // Log an error if a project exists already and -forceImport is not given
            IStatus status = Status.error("Problem while importing project into the workspace: A project with the same name is referenced from the workspace. This should be removed from the workspace.");
            LogExt.log(IReportManagerDefaultComponents.MODEL, status);
          } else {
            project.create(description, null);
            project.open(null);
            importedProjects.add(project.getName());
          }
        } else {
          // If -forceImport is given, unreference existing project from the workspace
          if (project.exists())
            project.delete(IResource.NEVER_DELETE_PROJECT_CONTENT, new NullProgressMonitor());
          project.create(description, null);
          project.open(null);
          importedProjects.add(project.getName());
        }
      }
    }

  }

  /**
   * @param outputFolder
   * @throws CommandLineException
   */
  private void createOutputFolder(String outputFolder) throws CommandLineException {
    IFolder folder = ResourcesPlugin.getWorkspace().getRoot().getFolder(new Path(outputFolder));

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
      } else {
        logInfo(outputFolder + Messages.already_exist);
      }

    } catch (CoreException exception) {
      StringBuilder message = new StringBuilder(Messages.cannot_create_folder);
      logger.error(message.toString(), exception);
      throw new CommandLineException(message.toString());
    }
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public void printHelp() {
    System.out.println("*** Capella Command Line Core Mechanism ***"); //$NON-NLS-1$

    System.out.println(CommandLineConstants.ID
        + " value : defines the id of the command line application to launch, see org.polarsys.capella.core.commandline.core.commandline extension point."); //$NON-NLS-1$
    System.out.println(CommandLineConstants.DATA + " value : defines the path to the workspace."); //$NON-NLS-1$
    System.out.println(CommandLineConstants.IMPORT
        + " value : defines a list of projects to import into the workspace before doing the actual job. List of projects is a '|' separated list."); //$NON-NLS-1$
    System.out.println(
        CommandLineConstants.FORCEIMPORT + " : delete/unreference the project if it exists already in the workspace."); //$NON-NLS-1$
    System.out.println(CommandLineConstants.FILE_PATH + " value : defines the path to your aird file."); //$NON-NLS-1$
    System.out.println(CommandLineConstants.OUTPUTFOLDER + " value : defines the path to the output folder."); //$NON-NLS-1$
    System.out.println(
        CommandLineConstants.FORCEOUTPUTFOLDERCREATION + " value : create the output folder if it does not exist."); //$NON-NLS-1$
    System.out.println(CommandLineConstants.HELP + " : prints the help message"); //$NON-NLS-1$

    System.out.println(CommonArgumentsConstants.LOG_FILE_PATH__DESCRIPTION);
  }

  /**
   * @param filePath
   * @return
   */
  public static String getProjectName(String filePath) {
    URI fileURI = URI.createFileURI(filePath);
    return URI.decode(fileURI.segment(0));
  }

  /**
   * @param filePath
   * @return
   */
  public static String getRelativeFilePath(String filePath) {
    URI fileURI = URI.createFileURI(filePath);
    List<String> segments = new ArrayList<String>(Arrays.asList(fileURI.segments()));
    segments.remove(0);
    if (segments.isEmpty())
      return "";
    // We should avoid creating a file URI from an empty string because it will make an absolute path
    URI uri = URI.createFileURI(segments.get(0));
    for (int i = 1; i < segments.size(); i++)
      uri = uri.appendSegment(segments.get(i));
    return URI.decode(uri.toFileString());
  }

  public List<String> getImportedProjects() {
    return importedProjects;
  }

}
