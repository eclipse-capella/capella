/*******************************************************************************
 * Copyright (c) 2017, 2026 THALES GLOBAL SERVICES.
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

import java.nio.file.Files;

import org.eclipse.core.resources.ResourcesPlugin;
import org.eclipse.core.runtime.IPath;
import org.polarsys.capella.core.commandline.core.CommandLineConstants;
import org.polarsys.capella.core.validation.commandline.ValidationCommandLine;
import org.polarsys.capella.test.commandline.ju.utils.MockApplicationContext;
import org.polarsys.capella.test.framework.api.BasicTestCase;
import org.polarsys.capella.test.framework.api.ModelProviderHelper;

/**
 * Test simulating a Validation launch from command line.
 */
public class CommandLineValidationTest extends BasicTestCase {

  private static final String PROJECT_NAME = "Test Command Line Validation"; //$NON-NLS-1$
  private static final String COMMAND_ID = "org.polarsys.capella.core.validation.commandline"; //$NON-NLS-1$
  
  
  @Override
  public void test() throws Exception {
    IPath workspaceLocation = ResourcesPlugin.getWorkspace().getRoot().getRawLocation();

    // Copy test project from the JUnit plugin to the workspace directory
    ModelProviderHelper.getInstance().importCapellaProject(getFolderInTestModelRepository(PROJECT_NAME));

    // Simulated validation command line

    String airdInput = PROJECT_NAME + "/" + PROJECT_NAME + ".aird";
    String output = PROJECT_NAME + "/ValidationResult";
    MockApplicationContext.execute(new ValidationCommandLine(), COMMAND_ID,
        CommandLineConstants.INPUT, airdInput, // input
        CommandLineConstants.OUTPUTFOLDER, PROJECT_NAME + "/ValidationResult");

    // Check we have a result file with the expected validation results
    IPath validationResultFile = workspaceLocation
        .append(output + "/" + airdInput + "/validation-results.html");
    String validationResult = Files.readString(validationResultFile.toFile().toPath());

    assertTrue(validationResult.contains("DCOM_03") && validationResult.contains("TJ_SA_01"));
    // check the content on the resources column
    assertTrue(validationResult.contains(
        "/Test Command Line Validation/TestCommandLineValidation/Operational Analysis/Operational Activities/Root Operational Activity/OperationalActivity 1"));
  }
}
