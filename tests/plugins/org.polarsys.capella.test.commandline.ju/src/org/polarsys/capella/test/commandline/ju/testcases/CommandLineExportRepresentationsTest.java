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

import org.eclipse.core.resources.ResourcesPlugin;
import org.eclipse.core.runtime.IPath;
import org.eclipse.equinox.app.IApplicationContext;
import org.polarsys.capella.core.commandline.core.CommandLineConstants;
import org.polarsys.capella.core.sirius.ui.commandline.ExportRepresentationsCommandLine;
import org.polarsys.capella.test.commandline.ju.utils.MockApplicationContext;
import org.polarsys.capella.test.framework.api.BasicTestCase;
import org.polarsys.capella.test.framework.api.ModelProviderHelper;
import org.polarsys.capella.test.framework.helpers.GuiActions;

/**
 * Test simulating a Validation launch from command line.
 */
public class CommandLineExportRepresentationsTest extends BasicTestCase {
  @Override
  public void test() {
    IPath workspaceLocation = ResourcesPlugin.getWorkspace().getRoot().getRawLocation();
    String projectName = "RefreshRemoveExport";
    File sourceFolder = getFolderInTestModelRepository(projectName);

    ModelProviderHelper.getInstance().importCapellaProject(projectName, sourceFolder);
    try {
      exportImages(projectName);
    } catch (Exception e) {
      e.printStackTrace();
      assertFalse(e.getMessage(), true);
    }
    
    // Check we have a result file with the expected validation results
    IPath validationResultFile = workspaceLocation.append(projectName).append("ImagesExported").append(projectName).append(projectName + ".aird");
    assertTrue(validationResultFile.toFile().listFiles().length == 3);
  }

  private void exportImages(String projectName) throws Exception {

    String[] arguments = { CommandLineConstants.ID,
        "org.polarsys.capella.exportRepresentations", CommandLineConstants.INPUT,
        projectName + "/" + projectName + ".aird", CommandLineConstants.OUTPUTFOLDER, projectName + "/ImagesExported",
        CommandLineConstants.FORCEOUTPUTFOLDERCREATION };
    IApplicationContext mockApplicationContext = new MockApplicationContext(arguments);

    ExportRepresentationsCommandLine commandLine = new ExportRepresentationsCommandLine();
    commandLine.parseContext(mockApplicationContext);

    commandLine.checkArgs(mockApplicationContext);
    commandLine.prepare(mockApplicationContext);
    commandLine.execute(mockApplicationContext);
    GuiActions.flushASyncGuiJobs();
  }
}
