/*******************************************************************************
 * Copyright (c) 2017 THALES GLOBAL SERVICES.
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
import java.nio.file.Files;
import java.nio.file.Paths;

import org.eclipse.core.resources.ResourcesPlugin;
import org.eclipse.core.runtime.IPath;
import org.eclipse.equinox.app.IApplicationContext;
import org.polarsys.capella.core.commandline.core.CommandLineConstants;
import org.polarsys.capella.core.commandline.core.CommandLineMode;
import org.polarsys.capella.core.validation.commandline.Messages;
import org.polarsys.capella.core.validation.commandline.ValidationCommandLine;
import org.polarsys.capella.test.commandline.ju.utils.MockApplicationContext;
import org.polarsys.capella.test.framework.api.BasicTestCase;
import org.polarsys.capella.test.framework.api.ModelProviderHelper;

/**
 * Test simulating a Validation launch from command line.
 */
public class CommandLineValidationTest extends BasicTestCase {
  @Override
  public void test() throws Exception { 
    IPath workspaceLocation = ResourcesPlugin.getWorkspace().getRoot().getRawLocation();
    String projectName = "Test Command Line Validation";
    File sourceFolder = getFolderInTestModelRepository(projectName);
    // Copy test project from the JUnit plugin to the workspace directory
    ModelProviderHelper.getInstance().importCapellaProject(projectName, sourceFolder);
    
    // Simulated validation command line
    String[] validationCommandLineArguments = {
        CommandLineConstants.ID, "org.polarsys.capella.core.validation.commandline",
        CommandLineConstants.INPUT, projectName + "/Test Command Line Validation.aird",
        CommandLineConstants.OUTPUTFOLDER, projectName + "/ValidationResult",
        CommandLineConstants.FORCEOUTPUTFOLDERCREATION
    };
    IApplicationContext mockApplicationContext = new MockApplicationContext(validationCommandLineArguments);

    // Simulate launching from command line
    ValidationCommandLine validationCommandLine = new ValidationCommandLine();
    validationCommandLine.parseContext(mockApplicationContext);

    validationCommandLine.checkArgs(mockApplicationContext);
    validationCommandLine.prepare(mockApplicationContext);
    validationCommandLine.execute(mockApplicationContext);
    
    // Check we have a result file with the expected validation results    
    IPath validationResultFile = workspaceLocation.append(projectName).append("ValidationResult").append(projectName).append(projectName + ".aird").append(Messages.resultsFileName);
    byte[] fileContentInBytes = Files.readAllBytes(Paths.get(validationResultFile.toOSString()));
    String fileContentInString = new String(fileContentInBytes);
    assertTrue(fileContentInString.contains("DCOM_03") && fileContentInString.contains("TJ_SA_01")); 
  }
}
