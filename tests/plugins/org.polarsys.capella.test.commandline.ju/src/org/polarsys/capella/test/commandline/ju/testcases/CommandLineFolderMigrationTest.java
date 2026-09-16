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

import java.util.List;

import org.polarsys.capella.core.commandline.core.CommandLineConstants;


/**
 * Test the migration command line with some folders as input.
 * 
 * @author nicolas.peransin@obeo.fr
 */
public class CommandLineFolderMigrationTest extends CommandLineMigrationTestBase {

  private static final List<String> TESTS_PROJECT_NAMES = List.of("sysmodelLibrary_Super", "sysmodelLibrary_Sub", "sysmodelProject");
  
  @Override
  public void test() throws Exception { 

    var importList = toListArg(TESTS_PROJECT_NAMES.stream()
      .map(projectName -> copyToTestPool(getFolderInTestModelRepository(projectName))));
    
    launchMigration(CommandLineConstants.IMPORT, importList,
        CommandLineConstants.INPUT, toListArg(TESTS_PROJECT_NAMES.stream()));
    
    TESTS_PROJECT_NAMES.forEach(this::openSession);
  }

}
