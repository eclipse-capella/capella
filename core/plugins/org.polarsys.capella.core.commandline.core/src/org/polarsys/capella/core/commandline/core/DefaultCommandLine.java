/*******************************************************************************
 * Copyright (c) 2020, 2026 THALES GLOBAL SERVICES.
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

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.net.URISyntaxException;
import java.net.URL;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Arrays;
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

import org.apache.commons.io.FileUtils;
import org.eclipse.core.resources.IContainer;
import org.eclipse.core.resources.IFile;
import org.eclipse.core.resources.IFolder;
import org.eclipse.core.resources.IProject;
import org.eclipse.core.resources.IWorkspaceRoot;
import org.eclipse.core.resources.ResourcesPlugin;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.FileLocator;
import org.eclipse.core.runtime.NullProgressMonitor;
import org.eclipse.core.runtime.Path;
import org.eclipse.emf.common.util.URI;
import org.eclipse.equinox.app.IApplicationContext;
import org.osgi.framework.Bundle;
import org.osgi.framework.FrameworkUtil;
import org.polarsys.capella.common.tools.report.config.registry.ReportManagerRegistry;
import org.polarsys.capella.common.tools.report.util.IReportManagerDefaultComponents;
import org.polarsys.capella.core.model.handler.command.CapellaResourceHelper;
import org.w3c.dom.Document;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

public class DefaultCommandLine extends AbstractCommandLine {
  public static final String ALL_ARGUMENT = CommandLineConstants.ALL_ARGUMENT;

  public DefaultCommandLine() {
    argHelper = CommandLineArgumentHelper.getInstance();
    logger = ReportManagerRegistry.getInstance().subscribe(IReportManagerDefaultComponents.DEFAULT);
  }

  @Override
  public void prepare(IApplicationContext context) throws CommandLineException {
    // Import projects
    importProjects();
    // Create output folder
    getOrCreateOutputFolder();
  }

  protected void importProjects() throws CommandLineException {
    List<String> projectList = new ArrayList<>();
    try {
      String projectsToImport = argHelper.getImportProjects();
      if (projectsToImport != null) {
        projectList.addAll(toList(projectsToImport));
      }
      String allProjectsToImport = argHelper.getImportAllProjects();
      if (allProjectsToImport != null) {
        projectList.addAll(getAllProjectsInFolder(allProjectsToImport));
      }
      importProjects(projectList);
    } catch (CoreException | IOException exception) {
      throw new CommandLineException(exception.getMessage());
    }
  }

  @Override
  public void postExecute(IApplicationContext context) throws CommandLineException {
    legacyExportZips();
    exportProjects();
  }

  /**
   * Retrieves projects from a single text using 
   * 
   * @param names
   * @return
   */
  private List<IProject> getCapellaProjects(String names) {
    if (names == null) {
      return List.of();
    }
    
    IWorkspaceRoot root = ResourcesPlugin.getWorkspace().getRoot();
        
    if (names.equals(ALL_ARGUMENT)) {
      return Arrays.stream(root.getProjects())
          .filter(CapellaResourceHelper::isCapellaProject)
          .collect(Collectors.toList());
    }
    return toList(names)
        .stream()
        .map(root::getProject)
        .filter(project -> {
          boolean result = project.exists() && project.isOpen();
          if (!result) {
            logger.error(Messages.export_zip_not_found + project);
          }
          return result;
        })
        .collect(Collectors.toList());
  }

  
  /**
   * Exports specified projects using legacy options.
   * 
   * @throws CommandLineException if export fails
   */
  protected void legacyExportZips() throws CommandLineException {
    List<IProject> projects = getCapellaProjects(argHelper.getExportZips());
    if (projects.isEmpty()) { // Nothing to export
      return;
    }

    IFolder outputFolder = getOrCreateOutputFolder();
    if (outputFolder == null) {
      logger.error(Messages.export_zip_no_ouputfolder);
    } else {
      for (IProject project : projects) {
          IFile file = outputFolder.getFile(project.getName() + ".zip"); //$NON-NLS-1$
          WorkbenchHelper.exportZipFile(project, file);
      }
    }
  }
  
  /**
   * Exports specified projects.
   * 
   * @throws CommandLineException if export fails
   */
  protected void exportProjects() throws CommandLineException {
    List<IProject> projectsToExport = getCapellaProjects(argHelper.getExportProjects());

    if (!projectsToExport.isEmpty()) {
      if (argHelper.isSingleZip()) {
        exportProjectsAsSingleZip(projectsToExport);
      } else if (argHelper.isExportCopy()) {
        exportProjectsAsCopy(projectsToExport);
      } else { // default export mode
        exportProjectsAsZip(projectsToExport);
      }
    }
  }
  
  /**
   * Exports all projects as a folder copy.
   * 
   * @param projects list of projects
   * @throws CommandLineException if export fails
   */
  protected void exportProjectsAsCopy(List<IProject> projects) throws CommandLineException {
    File targetFolder = getExportTarget(true);
    if (targetFolder != null) {
      for (IProject project : projects) {
        try {
          FileUtils.copyDirectory(project.getLocation().toFile(), new File(targetFolder, project.getName()));
        } catch (IOException e) {
          throw new CommandLineException(Messages.cannot_create_folder);
        }
      }
    }
  }
  
  /**
   * Exports all projects in a Zip file.
   * 
   * @param projects list of projects
   * @throws CommandLineException if export fails
   */
  protected void exportProjectsAsSingleZip(List<IProject> projects) throws CommandLineException {
    File targetZip = getExportTarget(false);
    if (targetZip != null) {
      WorkbenchHelper.exportZipFile(projects, targetZip.getAbsolutePath());
    }
  }
  
  /**
   * Exports each projects in Zip file.
   * 
   * @param projects list of projects
   * @throws CommandLineException if export fails
   */
  protected void exportProjectsAsZip(List<IProject> projects) throws CommandLineException {
      File targetFolder = getExportTarget(true);
      if (targetFolder != null) {
        for (IProject project : projects) {
          WorkbenchHelper.exportZipFile(List.of(project), new File(targetFolder, project.getName() + ".zip").getAbsolutePath());
        }
      }
  }

  
  /**
   * Gets the target of location for export.
   * <p>
   * This method ensures the container exists.
   * </p>
   * 
   * @param isDirectory true if target if a folder.
   * @return target of export
   * @throws CommandLineException
   */
  private File getExportTarget(boolean isDirectory) throws CommandLineException {
    String input = argHelper.getExportTarget();

    if (input == null) {
      logger.error(Messages.export_zip_no_ouputfolder);
      return null;
    }

    File result = null;
    java.nio.file.Path inputValue = Paths.get(input);
    if (inputValue.isAbsolute()) {
      result = new File(input);
      File targetFolder = isDirectory ? result : result.getParentFile();
      if (!targetFolder.isDirectory() && !targetFolder.mkdirs()) {
        logger.error(Messages.cannot_create_folder, null);
        throw new CommandLineException(Messages.cannot_create_folder);
      }
    } else {
      result = getOrCreateOutputContainer(inputValue, isDirectory);
    }
    return result;
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
      List<IFile> capellaResources = getCapellaResourceFiles(project);

      // check that all capella resources are compliant with current Capella version
      for (IFile capellaResource : capellaResources) {
        compliancyCheck(capellaResource);
      }
    }
  }

  /**
   * Check that inputs arguments are valid
   * 
   * @throws CommandLineException
   */
  protected void checkInputs() throws CommandLineException {
    if (argHelper.getInputs() == null) {
      logger.error(Messages.inputs_mandatory);
    } else if (argHelper.getInputs().equals(ALL_ARGUMENT)) {
      for (IProject project : ResourcesPlugin.getWorkspace().getRoot().getProjects()) {
        checkProject(project);
      }
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
    if (!isBlank(argHelper.getExportProjects()) && isBlank(argHelper.getExportTarget())) {
      throw new CommandLineException(CommandLineConstants.EXPORT_TARGET
          + " option is required when using "
          + CommandLineConstants.EXPORT_LIST);
    }
  }

  private static boolean isBlank(String value) {
    return value == null || value.isBlank();
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

  /**
   * Creates the container if not existing and open it from specified path.
   * 
   * @param inputValue path of element to get
   * @param isDirectory true if the element is folder or project
   * @return associated file
   * @throws CommandLineException
   */
  protected File getOrCreateOutputContainer(java.nio.file.Path inputValue, boolean isDirectory) throws CommandLineException {
    IWorkspaceRoot root = ResourcesPlugin.getWorkspace().getRoot();
    
    File result;
    if (inputValue.getNameCount() == 1) {
      String filename = inputValue.getFileName().toString();
      
      if (!isDirectory) {
        result = new File(root.getLocation().toFile(), filename);
      } else {
        IProject project = openOrCreateContainer(root.getProject(filename));
        result = project.getLocation().toFile();
      }
    } else if (isDirectory) {
      IFolder folder = openOrCreateContainer(root.getFolder(new Path(inputValue.toString())));
      result = folder.getLocation().toFile();
    } else {
      IFile file = root.getFile(new Path(inputValue.toString()));
      openOrCreateContainer(file.getParent());
      result = file.getLocation().toFile();
    }
    return result;
  }
  
  /**
   * Create the container if not existing and open it.
   * 
   * @param <C> type of container
   * @param container element to create
   * @return provided container
   * @throws CommandLineException if container cannot be created du to invalid value.
   */
  protected <C extends IContainer> C openOrCreateContainer(C container) throws CommandLineException {
    try {
      if (container instanceof IProject) {
        IProject project = (IProject) container;
        if (!project.exists()) {
          project.create(new NullProgressMonitor());
        }

        if (!project.isOpen()) {
          project.open(new NullProgressMonitor());
        }
      } else if (container instanceof IFolder) {
        IFolder folder = (IFolder) container;
        openOrCreateContainer(folder.getProject());
        if (!folder.exists()) {
          folder.create(true, true, new NullProgressMonitor());
        }
      }

    } catch (CoreException exception) {
      logger.error(Messages.cannot_create_folder, exception);
      throw new CommandLineException(Messages.cannot_create_folder);
    }
    return container;
  }

  
  /**
   * Gets the output from argument.
   * <p>
   * Folder is created if necessary.
   * </p>
   * 
   * @throws CommandLineException if folder cannot be created du to invalid value.
   */
  protected IFolder getOrCreateOutputFolder() throws CommandLineException {
    String outputFolder = argHelper.getOutputFolder();
    if (outputFolder != null) {
      IWorkspaceRoot root = ResourcesPlugin.getWorkspace().getRoot();
      return openOrCreateContainer(root.getFolder(new Path(outputFolder)));
    }

    return null;
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public void printHelp() {
    printHelp(Collections.emptyList());
  }

  protected void printHelp(List<String> hiddenArguments) {
    System.out.println("*** Applicable arguments for command line***"); //$NON-NLS-1$
    printArgumentsFromTable("commonParameters", true, hiddenArguments); //$NON-NLS-1$
  }

  protected void printArgumentsFromTable(String tableId, boolean printTitle, List<String> hiddenArguments) {
    Bundle bundle = FrameworkUtil.getBundle(org.polarsys.capella.commandline.doc.Activator.class);
    URL fileURL = bundle.getEntry("html/19. Command Line Support/19.1. Core Mechanism and Applications.html"); //$NON-NLS-1$
    try {
      URL resolvedUrl = FileLocator.toFileURL(fileURL);
      // We need to use the 3-arg constructor of URI in order to properly escape file system chars.
      java.net.URI resolvedUri = new java.net.URI(resolvedUrl.getProtocol(), resolvedUrl.getPath(), null);
      File docFile = new File(resolvedUri);
      DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
      // Do not resolve external data
      dbf.setFeature(XMLConstants.FEATURE_SECURE_PROCESSING, true);
      dbf.setFeature("http://apache.org/xml/features/nonvalidating/load-external-dtd", false); //$NON-NLS-1$

      DocumentBuilder docBuilder = dbf.newDocumentBuilder();
      FileInputStream fis = new FileInputStream(docFile);
      Document doc = docBuilder.parse(fis);

      XPathFactory xPathFactory = XPathFactory.newInstance();
      XPath xpath = xPathFactory.newXPath();

      XPathExpression commonParametersTableExpr = xpath.compile("/html/body/table[@id='" + tableId + "']"); //$NON-NLS-1$ //$NON-NLS-2$
      Node tableNode = (Node) commonParametersTableExpr.evaluate(doc, XPathConstants.NODE);

      XPathExpression rowExpr = xpath.compile("tr"); //$NON-NLS-1$
      NodeList rowNodeList = (NodeList) rowExpr.evaluate(tableNode, XPathConstants.NODESET);

      // Print titles
      if (printTitle) {
        Node titleNode = rowNodeList.item(0);
        XPathExpression thExpr = xpath.compile("th"); //$NON-NLS-1$
        NodeList thNodeList = (NodeList) thExpr.evaluate(titleNode, XPathConstants.NODESET);
        System.out.printf("%-30s%-15s%s", thNodeList.item(0).getTextContent(), thNodeList.item(1).getTextContent(), //$NON-NLS-1$
            thNodeList.item(2).getTextContent());
        System.out.println();
      }

      // Print parameters
      for (int i = 1; i < rowNodeList.getLength(); i++) {
        Node trNode = rowNodeList.item(i);
        XPathExpression tdExpr = xpath.compile("td"); //$NON-NLS-1$
        NodeList tdNodeList = (NodeList) tdExpr.evaluate(trNode, XPathConstants.NODESET);
        List<String> tds = new ArrayList<>();
        for (int j = 0; j < tdNodeList.getLength(); j++) {
          Node tdNode = tdNodeList.item(j);
          tds.add(tdNode.getTextContent());
        }
        if (!tds.isEmpty() && !hiddenArguments.contains(tds.get(0))) {
          System.out.printf("%-30s%-15s%s", tds.get(0), tds.get(1), tds.get(2)); //$NON-NLS-1$
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
