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
package org.polarsys.capella.test.commandline.ju.testcases;

import java.io.File;

import org.eclipse.core.resources.ResourcesPlugin;
import org.eclipse.core.runtime.IPath;
import org.eclipse.equinox.app.IApplicationContext;
import org.polarsys.capella.core.commandline.core.CommandLineConstants;
import org.polarsys.capella.core.commandline.core.CommandLineMode;
import org.polarsys.capella.core.platform.sirius.ui.navigator.commandline.ExportRepresentationsCommandLine;
import org.polarsys.capella.test.commandline.ju.utils.MockApplicationContext;
import org.polarsys.capella.test.framework.api.BasicTestCase;
import org.polarsys.capella.test.framework.api.ModelProviderHelper;

/**
 * Test simulating a Validation launch from command line.
 */
public class CommandLineExportRepresentationsTest extends BasicTestCase {
  @Override
  public void test() throws Exception { 
    IPath workspaceLocation = ResourcesPlugin.getWorkspace().getRoot().getRawLocation();
    String projectName = "sysmodelProject";
    File sourceFolder = getFolderInTestModelRepository(projectName);
    
    // Copy test project from the JUnit plugin to the workspace directory
    ModelProviderHelper.getInstance().importCapellaProject(projectName, sourceFolder);
    
    // Simulated validation command line
    String[] validationCommandLineArguments = {
        CommandLineConstants.ID, "org.polarsys.capella.exportRepresentations",
        CommandLineConstants.FILE_PATH, projectName + "/sysmodelProject.aird",
        CommandLineConstants.OUTPUTFOLDER, projectName + "/ImagesExported",
        CommandLineConstants.FORCEOUTPUTFOLDERCREATION
    };
    IApplicationContext mockApplicationContext = new MockApplicationContext(validationCommandLineArguments);

    // Simulate launching from command line
    ExportRepresentationsCommandLine exportRepresenationsCommandLine = new ExportRepresentationsCommandLine();
    exportRepresenationsCommandLine.parseContext(mockApplicationContext);
    exportRepresenationsCommandLine.setMode(CommandLineMode.NO_IMPORT);

    // precondition: check parameters validity
    exportRepresenationsCommandLine.checkArgs(mockApplicationContext);

    // prepare execution (e.g. import project into a specified workspace)
    exportRepresenationsCommandLine.prepare(mockApplicationContext);

    // call execute
    exportRepresenationsCommandLine.execute(mockApplicationContext);
    
    // Check we have a result file with the expected validation results    
    IPath validationResultFile = workspaceLocation.append(projectName).append("ImagesExported");
    assertTrue(validationResultFile.toFile().listFiles().length == 123);
  }
}
