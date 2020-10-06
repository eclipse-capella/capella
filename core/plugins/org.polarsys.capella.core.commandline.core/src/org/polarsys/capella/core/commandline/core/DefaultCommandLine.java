/*******************************************************************************
 * Copyright (c) 2020 THALES GLOBAL SERVICES.
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
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;
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

import org.eclipse.core.resources.IFile;
import org.eclipse.core.resources.IFolder;
import org.eclipse.core.resources.IProject;
import org.eclipse.core.resources.IWorkspace;
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
  public static String ALL_ARGUMENT = "/all";

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
    String projectsToImport = argHelper.getImportProjects();
    if (projectsToImport != null) {
      try {
        importProjects(toList(projectsToImport));
      } catch (CoreException exception) {
        throw new CommandLineException(exception.getMessage());
      }
    }
  }

  @Override
  public void postExecute(IApplicationContext context) throws CommandLineException {
    exportZips();
  }

  protected void exportZips() throws CommandLineException {
    String projectsToExportZip = argHelper.getExportZips();
    if (projectsToExportZip != null) {
      if (projectsToExportZip.equals(ALL_ARGUMENT)) {
        exportProjectZips(Arrays.stream(ResourcesPlugin.getWorkspace().getRoot().getProjects())
            .filter(CapellaResourceHelper::isCapellaProject).map(iProject -> iProject.getName())
            .collect(Collectors.toList()));
      } else {
        exportProjectZips(toList(projectsToExportZip));
      }
    }
  }

  /**
   * @param projectsToExportZip
   * @throws CoreException
   */
  protected void exportProjectZips(List<String> projectsToExportZip) throws CommandLineException {
    IFolder outputFolder = getOrCreateOutputFolder();
    if (outputFolder == null) {
      logger.error(Messages.export_zip_no_ouputfolder);
    } else {
      IProject[] iProjects = ResourcesPlugin.getWorkspace().getRoot().getProjects();
      for (String project : projectsToExportZip) {
        Optional<IProject> anyIProject = Arrays.stream(iProjects).filter(iProject -> iProject.getName().equals(project))
            .findAny();
        if (!anyIProject.isPresent()) {
          logger.error(Messages.export_zip_not_found + project);
        } else {
          IWorkspace workspace = ResourcesPlugin.getWorkspace();
          IFile file = outputFolder.getFile(project + ".zip");
          IProject newProject = workspace.getRoot().getProject(project);
          WorkbenchHelper.exportZipFile(newProject, file);
        }
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
    printHelp(Collections.emptyList());
  }

  protected void printHelp(List<String> hiddenArguments) {
    System.out.println("*** Applicable arguments for command line***"); //$NON-NLS-1$
    printArgumentsFromTable("commonParameters", true, hiddenArguments);
  }

  protected void printArgumentsFromTable(String tableId, boolean printTitle, List<String> hiddenArguments) {
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
        if (!tds.isEmpty() && !hiddenArguments.contains(tds.get(0))) {
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
