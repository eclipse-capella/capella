/*******************************************************************************
 * Copyright (c) 2017, 2018 THALES GLOBAL SERVICES.
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

import org.eclipse.equinox.app.IApplicationContext;
import org.polarsys.capella.core.commandline.core.CommandLineConstants;
import org.polarsys.capella.core.data.migration.cmdline.MigrationCommandLine;
import org.polarsys.capella.test.commandline.ju.utils.MockApplicationContext;
import org.polarsys.capella.test.framework.helpers.IResourceHelpers;

public class CommandLineZipMigrationTest extends CommandLineMigrationTest {
  @Override
  public void test() throws Exception {

    File superLibFolder = getFolderInTestModelRepository("sysmodelLibrary_Super");
    File subLibFolder = getFolderInTestModelRepository("sysmodelLibrary_Sub");
    File sysmodelFolder = getFolderInTestModelRepository("sysmodelProject");

    File sourceFolder = getFolderInTestModelRepository("sysmodel_with_libraries.zip");
    IResourceHelpers.createZip(sourceFolder, superLibFolder, subLibFolder, sysmodelFolder);

    
    // Simulated migration command line with a zip
    String[] validationCommandLineArguments = { CommandLineConstants.ID, "org.polarsys.capella.migration",
        CommandLineConstants.IMPORT, sourceFolder.getAbsolutePath() };
    IApplicationContext mockApplicationContext = new MockApplicationContext(validationCommandLineArguments);

    // Simulate launching from command line
    MigrationCommandLine migrationCmdLine = new MigrationCommandLine();
    launchApplication(mockApplicationContext, migrationCmdLine);

    openSession("sysmodelProject");
    openSession("sysmodelLibrary_Sub");
    openSession("sysmodelLibrary_Super");
  }


}
