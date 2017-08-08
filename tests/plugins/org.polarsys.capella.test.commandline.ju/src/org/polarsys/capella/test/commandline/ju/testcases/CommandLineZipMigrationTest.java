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

import org.eclipse.equinox.app.IApplicationContext;
import org.polarsys.capella.core.commandline.core.CommandLineConstants;
import org.polarsys.capella.core.data.migration.cmdline.MigrationCommandLine;
import org.polarsys.capella.test.commandline.ju.utils.MockApplicationContext;

public class CommandLineZipMigrationTest extends CommandLineMigrationTest {
  @Override
  public void test() throws Exception { 
    File sourceFolder = getFolderInTestModelRepository("sysmodel_with_libraries.zip");
    
    // Simulated migration command line with a zip
    String[] validationCommandLineArguments = {
        CommandLineConstants.ID, "org.polarsys.capella.migration",
        CommandLineConstants.IMPORT, sourceFolder.getAbsolutePath()
    };
    IApplicationContext mockApplicationContext = new MockApplicationContext(validationCommandLineArguments);

    // Simulate launching from command line
    MigrationCommandLine migrationCmdLine = new MigrationCommandLine();
    launchApplication(mockApplicationContext, migrationCmdLine);
    
    openSession("sysmodel");
    openSession("sysmodelLibrary_Sub");
    openSession("sysmodelLibrary_Super");
  }


}
