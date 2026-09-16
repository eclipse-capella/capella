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
 * Test the migration command line with project that have dependencies.
 * 
 * @author nicolas.peransin@obeo.fr
 */
public class MigrationWithDependenciesTest extends CommandLineMigrationTestBase {

  private static final String TEST_PROJECT_PATH = "capella6/"; //$NON-NLS-1$
  private static final String MAIN_PROJECT = "Demo"; //$NON-NLS-1$
  private static final List<String> TESTS_PROJECT_NAMES = List.of(MAIN_PROJECT,
      "LibBiz", //$NON-NLS-1$
      "LibCor"); //$NON-NLS-1$
  
  @Override
  public void test() throws Exception { 

    var importList = toListArg(TESTS_PROJECT_NAMES.stream()
      .map(projectName -> copyToTestPool(getFolderInTestModelRepository(TEST_PROJECT_PATH + projectName))));
    
    launchMigration(CommandLineConstants.IMPORT, importList,
        // With only 1 input, migration must retrieve dependencies and order the migration.
        CommandLineConstants.INPUT, MAIN_PROJECT);
    
    TESTS_PROJECT_NAMES.forEach(this::openSession);
  }

}
