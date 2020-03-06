/*******************************************************************************
 * Copyright (c) 2020 THALES GLOBAL SERVICES.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *    Thales - initial API and implementation
 *******************************************************************************/
package org.polarsys.capella.core.commandline.core;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.net.URISyntaxException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import javax.xml.XMLConstants;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.xpath.XPath;
import javax.xml.xpath.XPathConstants;
import javax.xml.xpath.XPathExpression;
import javax.xml.xpath.XPathExpressionException;
import javax.xml.xpath.XPathFactory;

import org.apache.log4j.Logger;
import org.eclipse.core.resources.IFile;
import org.eclipse.core.resources.IFolder;
import org.eclipse.core.resources.IProject;
import org.eclipse.core.resources.IProjectDescription;
import org.eclipse.core.resources.IResource;
import org.eclipse.core.resources.ResourcesPlugin;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.FileLocator;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.MultiStatus;
import org.eclipse.core.runtime.NullProgressMonitor;
import org.eclipse.core.runtime.Path;
import org.eclipse.core.runtime.Status;
import org.eclipse.emf.common.util.URI;
import org.eclipse.equinox.app.IApplicationContext;
import org.osgi.framework.Bundle;
import org.osgi.framework.FrameworkUtil;
import org.polarsys.capella.common.mdsofa.common.constant.ICommonConstants;
import org.polarsys.capella.common.tools.report.config.registry.ReportManagerRegistry;
import org.polarsys.capella.common.tools.report.util.IReportManagerDefaultComponents;
import org.polarsys.capella.common.tools.report.util.LogExt;
import org.polarsys.capella.core.af.integration.CapellaMetadataProvider;
import org.polarsys.capella.core.model.handler.command.CapellaResourceHelper;
import org.w3c.dom.Document;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

public abstract class DefaultCommandLine implements ICommandLine {
  protected Logger logger;
  protected CommandLineArgumentHelper argHelper;
  protected CommandLineMode mode;
  private List<String> importedProjects = new ArrayList<>();
  public static String ALL_ARGUMENT = "/all";

  public DefaultCommandLine() {
    argHelper = CommandLineArgumentHelper.getInstance();
    logger = ReportManagerRegistry.getInstance().subscribe(IReportManagerDefaultComponents.DEFAULT);
  }

  @Override
  public void parseContext(IApplicationContext context) throws CommandLineException {
    String[] args = CommandLineArgumentHelper.parseContext(context);
    argHelper.parseArgs(args);
  }

  /**
   * {@inheritDoc}
   *
   * @throws CommandLineException
   */
  @Override
  public void prepare(IApplicationContext context) throws CommandLineException {
    // Import projects
    importProjects();
    // Create output folder
    getOrCreateOutputFolder();
  }

  protected void importProjects() throws CommandLineException {
    String projectsToImport = argHelper.getImportProjects();
    if (projectsToImport != null) {
      try {
        importProjects(toList(projectsToImport));
      } catch (CoreException exception) {
        throw new CommandLineException(exception.getMessage());
      }
    }
  }

  protected void checkProject(IProject project) throws CommandLineException {
    if (!project.exists()) {
      logError(Messages.project + project.getName() + Messages.not_exist);
    } else {
      try {
        project.open(new NullProgressMonitor());
      } catch (CoreException exception) {
        logError(Messages.unable_open_project + project.getName());
      }
      List<IFile> melodymodellerFiles = getCapellamodellerFiles(project);
      // check that all melodymodeller files are compliant with current Capella version
      for (IFile modeller : melodymodellerFiles) {
        compliancyCheck(modeller);
      }
    }
  }

  /**
   * Check that inputs arguments are valid
   * @throws CommandLineException
   */
  protected void checkInputs() throws CommandLineException {
    if (argHelper.getInputs() == null) {
      logger.error(Messages.inputs_mandatory);
    } else if (argHelper.getInputs().equals(ALL_ARGUMENT)) {
      // /all is accepted as argument
    } else {
      List<String> inputs = toList(argHelper.getInputs());
      for (String input : inputs) {
        if (input.endsWith(CapellaResourceHelper.AIRD_FILE_EXTENSION)) {
          checkInputAirdFile(input);
        } else {
          checkInputProject(input);
        }
      }
    }
  }

  protected void checkInputProject(String projectPath) throws CommandLineException {
    String projectName = getProjectNameFromPath(projectPath);
    IProject project = ResourcesPlugin.getWorkspace().getRoot().getProject(projectName);
    checkProject(project);
  }

  protected void checkInputAirdFile(String airdPath) throws CommandLineException {
    String projectName = getProjectNameFromPath(airdPath);
    IProject project = ResourcesPlugin.getWorkspace().getRoot().getProject(projectName);
    checkProject(project);
    IFile file = project.getFile(getRelativeFilePath(airdPath));
    if (!file.exists()) {
      String message = Messages.aird + airdPath + Messages.not_exist;
      logError(message);
      throw new CommandLineException(message);
    }
  }

  protected void compliancyCheck(IFile modeller) throws CommandLineException {
    IStatus status = new CapellaMetadataProvider().checkVersion(modeller);
    if (!status.isOK()) {
      logErrorAndThrowException(Messages.Error_versionCompliancy);
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

  private List<IFile> getCapellamodellerFiles(IProject project) {
    List<IFile> files = new ArrayList<>();
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

  protected void logStatus(IStatus status) {
    if (status.isOK()) {
      logger.info(status.getMessage());

    } else if (IStatus.CANCEL == status.getSeverity()) {
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
   * Transform a space separated list of strings into a List
   *
   * @param string
   * @return
   */
  protected List<String> toList(String string) {
    List<String> list = new ArrayList<>();
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
            IStatus status = new Status(IStatus.ERROR, CommandLineApp.PLUGIN_ID,
                "Problem while importing project into the workspace: A project with the same name is referenced from the workspace. This should be removed from the workspace.");
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
  protected IFolder getOrCreateOutputFolder() throws CommandLineException {
    String outputFolder = argHelper.getOutputFolder();
    IFolder folder = null;
    if (outputFolder != null) {
      folder = ResourcesPlugin.getWorkspace().getRoot().getFolder(new Path(outputFolder));

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

    return folder;
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public void printHelp() {
    System.out.println("*** Capella Command Line Core Mechanism ***"); //$NON-NLS-1$
    printArgumentsFromTable("commonParameters", true);
  }

  protected void printArgumentsFromTable(String tableId, boolean printTitle) {
    Bundle bundle = FrameworkUtil.getBundle(org.polarsys.capella.commandline.doc.Activator.class);
    URL fileURL = bundle.getEntry("html/19. Command Line Support/19.1. Core Mechanism and Applications.html");
    try {
      URL resolvedUrl = FileLocator.toFileURL(fileURL);
      // We need to use the 3-arg constructor of URI in order to properly escape file system chars.
      java.net.URI resolvedUri = new java.net.URI(resolvedUrl.getProtocol(), resolvedUrl.getPath(), null);
      File docFile = new File(resolvedUri);
      DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
      // Do not resolve external data
      dbf.setFeature(XMLConstants.FEATURE_SECURE_PROCESSING, true);
      dbf.setFeature("http://apache.org/xml/features/nonvalidating/load-external-dtd", false);

      DocumentBuilder docBuilder = dbf.newDocumentBuilder();
      FileInputStream fis = new FileInputStream(docFile);
      Document doc = docBuilder.parse(fis);

      XPathFactory xPathFactory = XPathFactory.newInstance();
      XPath xpath = xPathFactory.newXPath();

      XPathExpression commonParametersTableExpr = xpath.compile("/html/body/table[@id='" + tableId + "']");
      Node tableNode = (Node) commonParametersTableExpr.evaluate(doc, XPathConstants.NODE);

      XPathExpression rowExpr = xpath.compile("tr");
      NodeList rowNodeList = (NodeList) rowExpr.evaluate(tableNode, XPathConstants.NODESET);

      // Print titles
      if (printTitle) {
        Node titleNode = rowNodeList.item(0);
        XPathExpression thExpr = xpath.compile("th");
        NodeList thNodeList = (NodeList) thExpr.evaluate(titleNode, XPathConstants.NODESET);
        System.out.printf("%-30s%-15s%s", thNodeList.item(0).getTextContent(), thNodeList.item(1).getTextContent(),
            thNodeList.item(2).getTextContent());
        System.out.println();
      }

      // Print parameters
      for (int i = 1; i < rowNodeList.getLength(); i++) {
        Node trNode = rowNodeList.item(i);
        XPathExpression tdExpr = xpath.compile("td");
        NodeList tdNodeList = (NodeList) tdExpr.evaluate(trNode, XPathConstants.NODESET);
        List<String> tds = new ArrayList<>();
        for (int j = 0; j < tdNodeList.getLength(); j++) {
          Node tdNode = tdNodeList.item(j);
          tds.add(tdNode.getTextContent());
        }
        if (!tds.isEmpty()) {
          System.out.printf("%-30s%-15s%s", tds.get(0), tds.get(1), tds.get(2));
          System.out.println();
        }
      }
    } catch (ParserConfigurationException | SAXException | IOException | XPathExpressionException
        | URISyntaxException e) {
      logger.error(e.toString());
    }
  }

  /**
   * Deduce the project name from relative input path
   * 
   * @param relative
   *          path of aird / project
   * @return project name
   */
  protected String getProjectNameFromPath(String path) {
    URI fileURI = URI.createFileURI(path);
    return URI.decode(fileURI.segment(0));
  }

  /**
   * @param relative
   *          path of aird / project
   * @return aird files
   */
  protected List<IFile> getAirdFiles(String path) {
    if (path.endsWith(CapellaResourceHelper.AIRD_FILE_EXTENSION)) {
      return Arrays.asList(ResourcesPlugin.getWorkspace().getRoot().getFile(new Path(path)));
    }
    IProject project = ResourcesPlugin.getWorkspace().getRoot().getProject(getProjectNameFromPath(path));
    if (project.exists()) {
      try {
        return Arrays.stream(project.members()).filter(IFile.class::isInstance).map(IFile.class::cast)
            .filter(file -> file.getFileExtension().equals(CapellaResourceHelper.AIRD_FILE_EXTENSION))
            .collect(Collectors.toList());
      } catch (CoreException e) {
        logError(Messages.aird_not_found + project.getName());
      }
    }
    return Collections.emptyList();
  }

  protected List<IFile> getAirdFilesFromInput() {
    String inputs = argHelper.getInputs();
    List<IFile> airdFiles = new ArrayList<>();
    if (inputs == null) {
      return Collections.emptyList();
    } else if (inputs.equals(ALL_ARGUMENT)) {
      for (IProject p : ResourcesPlugin.getWorkspace().getRoot().getProjects()) {
        airdFiles.addAll(getAirdFiles(p.getName()));
      }
    } else {
      for (String inputPath : toList(inputs)) {
        airdFiles.addAll(getAirdFiles(inputPath));
      }
    }
    return airdFiles;
  }

  protected Set<IProject> getProjectsFromInput() {
    String inputs = argHelper.getInputs();
    if (inputs == null) {
      return Collections.emptySet();
    } else if (inputs.equals(ALL_ARGUMENT)) {
      return new HashSet<>(Arrays.asList(ResourcesPlugin.getWorkspace().getRoot().getProjects()));
    }
    Set<IProject> projects = new HashSet<>();
    for (String inputPath : toList(inputs)) {
      projects.add(ResourcesPlugin.getWorkspace().getRoot().getProject(getProjectNameFromPath(inputPath)));
    }
    return projects;
  }
  /**
   * @param input
   *          -input argument
   * @return
   */
  protected List<String> getInputProjects(String input) {
    return toList(input).stream().map(this::getProjectNameFromPath).collect(Collectors.toList());
  }

  /**
   * @param filePath
   * @return
   */
  public static String getRelativeFilePath(String filePath) {
    URI fileURI = URI.createFileURI(filePath);
    List<String> segments = new ArrayList<>(Arrays.asList(fileURI.segments()));
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

  @Override
  public void checkArgs(IApplicationContext context) throws CommandLineException {
    checkInputs();
  }

  protected IFolder getOrCreateOutputFolderForAird(URI airdURI) {
    IFolder airdFolder = null;
    String airdName = URI.decode(airdURI.lastSegment());
    String projectName = URI.decode(airdURI.segment(airdURI.segmentCount() - 2));
    try {
      IFolder outputFolder = getOrCreateOutputFolder();
      if (outputFolder.exists()) {
        IFolder projectFolder = outputFolder.getFolder(projectName);
        if (!projectFolder.exists()) {
          projectFolder.create(false, true, new NullProgressMonitor());
        }
        airdFolder = projectFolder.getFolder(airdName);
        if (!airdFolder.exists()) {
          airdFolder.create(false, true, new NullProgressMonitor());
        }
      }
    } catch (CoreException | CommandLineException e) {
      logger.error(Messages.cannot_create_folder);
    }
    return airdFolder;
  }
}
