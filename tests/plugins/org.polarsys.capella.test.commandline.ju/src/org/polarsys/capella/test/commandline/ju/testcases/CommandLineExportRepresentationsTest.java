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
package org.polarsys.capella.test.commandline.ju.testcases;

import java.io.File;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

import org.eclipse.core.resources.IFile;
import org.eclipse.core.resources.IFolder;
import org.eclipse.core.resources.ResourcesPlugin;
import org.eclipse.core.runtime.IPath;
import org.eclipse.equinox.app.IApplicationContext;
import org.eclipse.sirius.common.tools.api.resource.ImageFileFormat;
import org.polarsys.capella.core.commandline.core.CommandLineConstants;
import org.polarsys.capella.core.sirius.ui.commandline.ExportRepresentationsCommandLine;
import org.polarsys.capella.core.sirius.ui.commandline.ExportRepresentationsCommandLineConstants;
import org.polarsys.capella.test.commandline.ju.utils.MockApplicationContext;
import org.polarsys.capella.test.framework.api.BasicTestCase;
import org.polarsys.capella.test.framework.api.ModelProviderHelper;
import org.polarsys.capella.test.framework.helpers.GuiActions;
import org.polarsys.capella.test.framework.helpers.IResourceHelpers;

/**
 * Test simulating a Validation launch from command line.
 */
public class CommandLineExportRepresentationsTest extends BasicTestCase {
  
  @Override
  public void test() {
    String projectName = "RefreshRemoveExport";
    File sourceFolder = getFolderInTestModelRepository(projectName);

    IFolder outputFolder = ResourcesPlugin.getWorkspace().getRoot().getProject(projectName).getFolder("ImagesExported")
        .getFolder(projectName).getFolder(projectName + ".aird");

    // Commandline default value is JPG (defined in ExportRepresentationsArgumentHelper and related documentation)
    ModelProviderHelper.getInstance().importCapellaProject(projectName, sourceFolder);
    try {
      exportImages(projectName);
      assertTrue(getFiles(outputFolder, ImageFileFormat.JPG).size() == 3);

    } catch (Exception e) {
      e.printStackTrace();
      assertFalse(e.getMessage(), true);
    }

    // Ensure that SVG command line is properly exporting semantic identifiers
    try {
      exportImages(projectName, ImageFileFormat.SVG);
      Collection<IFile> files = getFiles(outputFolder, ImageFileFormat.SVG);
      assertTrue(files.size() == 3);

      String svgContent = IResourceHelpers.readFileAsString(files.iterator().next());

      // Ensure there is at least one semantic id in the svg
      assertTrue(!notNoneMatches(matches(svgContent, Pattern.compile("diagram:semanticTargetId=\"([^\"]+)\""))).isEmpty());

      // Ensure there is at least the semantic id of target of the diagram in the svg
      assertTrue(!notNoneMatches(matches(svgContent, Pattern.compile("diagram:semanticRoot=\"([^\"]+)\""))).isEmpty());

    } catch (Exception e) {
      e.printStackTrace();
      assertFalse(e.getMessage(), true);
    }

  }

  /**
   * Returns the matching group on all matches of the given pattern.
   */
  private Collection<String> matches(String content, Pattern pattern) {
    Matcher m = pattern.matcher(content);
    List<String> matches = new ArrayList<>();
    while (m.find()) {
      matches.add(m.group(1));
    }

    return matches;
  }

  /**
   * none is a keyword for null target in
   * org.eclipse.sirius.diagram.ui.tools.internal.render.SiriusRenderedMapModeGraphics
   */
  // 
  private Collection<String> notNoneMatches(Collection<String> semanticElementIds) {
    return semanticElementIds.stream().filter(x -> !"none".equals(x)).collect(Collectors.toList());
  }

  private Collection<IFile> getFiles(IFolder outputFolder, ImageFileFormat defaultImageFormat) {
    return IResourceHelpers.getIFilesIn(outputFolder, defaultImageFormat.getName().toLowerCase());
  }

  private void exportImages(String projectName) throws Exception {
    exportImages(projectName, null);
  }

  private void exportImages(String projectName, ImageFileFormat format) throws Exception {

    List<String> arguments = new ArrayList<>(
        Arrays.asList(CommandLineConstants.ID, "org.polarsys.capella.exportRepresentations", CommandLineConstants.INPUT,
            projectName + "/" + projectName + ".aird", CommandLineConstants.OUTPUTFOLDER,
            projectName + "/ImagesExported", CommandLineConstants.FORCEOUTPUTFOLDERCREATION));

    if (format != null) {
      arguments.add(ExportRepresentationsCommandLineConstants.IMAGE_FORMAT);
      arguments.add(format.getName());
    }

    IApplicationContext mockApplicationContext = new MockApplicationContext(arguments.toArray(new String[0]));

    ExportRepresentationsCommandLine commandLine = new ExportRepresentationsCommandLine();
    commandLine.parseContext(mockApplicationContext);

    commandLine.checkArgs(mockApplicationContext);
    commandLine.prepare(mockApplicationContext);
    commandLine.execute(mockApplicationContext);
    GuiActions.flushASyncGuiJobs();
  }
}
