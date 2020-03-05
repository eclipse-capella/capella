/*******************************************************************************
 * Copyright (c) 2017, 2018 THALES GLOBAL SERVICES.
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

import org.eclipse.equinox.app.IApplicationContext;
import org.polarsys.capella.core.commandline.core.CommandLineConstants;
import org.polarsys.capella.core.data.migration.cmdline.MigrationCommandLine;
import org.polarsys.capella.test.commandline.ju.utils.MockApplicationContext;

public class CommandLineFolderMigrationTest extends CommandLineMigrationTest {
  @Override
  public void test() throws Exception { 
    File superLibFolder = getFolderInTestModelRepository("sysmodelLibrary_Super");
    File subLibFolder = getFolderInTestModelRepository("sysmodelLibrary_Sub");
    File sysmodelFolder = getFolderInTestModelRepository("sysmodelProject");
    
    // Simulated migration command line with multiple projects
    String[] validationCommandLineArguments = {
        CommandLineConstants.ID, "org.polarsys.capella.migration",
        CommandLineConstants.IMPORT, superLibFolder.getAbsolutePath() + "|" + subLibFolder.getAbsolutePath() + "|" + sysmodelFolder.getAbsolutePath(),
        CommandLineConstants.INPUT, "/all"
    };
    IApplicationContext mockApplicationContext = new MockApplicationContext(validationCommandLineArguments);

    // Simulate launching from command line
    MigrationCommandLine migrationCmdLine = new MigrationCommandLine();
    launchApplication(mockApplicationContext, migrationCmdLine);
    
    openSession("sysmodelProject");
    openSession("sysmodelLibrary_Sub");
    openSession("sysmodelLibrary_Super");
  }
}
