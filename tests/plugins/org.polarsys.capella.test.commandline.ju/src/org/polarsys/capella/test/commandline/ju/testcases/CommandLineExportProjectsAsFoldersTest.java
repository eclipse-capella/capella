/*******************************************************************************
 * Copyright (c) 2026 THALES GLOBAL SERVICES.
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
import java.nio.file.Path;

import org.polarsys.capella.core.commandline.core.CommandLineConstants;
import org.polarsys.capella.core.commandline.core.DefaultCommandLine;
import org.polarsys.capella.test.commandline.ju.utils.MockApplicationContext;

/**
 * Test Export project option in commandline.
 * 
 * @author nicolas.peransin@obeo.fr
 */
public class CommandLineExportProjectsAsFoldersTest extends AbstractCommandLineExportTestCase {

  private static final Path EXPORT_PATH = Path.of("target/test-run/CommandLineExportProjectsAsFoldersTest");

  /**
   * Test constructor.
   */
  public CommandLineExportProjectsAsFoldersTest() {
    super(EXPORT_PATH);
  }
  
  
  @Override
  public void test() throws Exception {
    MockApplicationContext.execute(new DefaultCommandLine() , "stub", 
        CommandLineConstants.INPUT, CommandLineConstants.ALL_ARGUMENT,
        CommandLineConstants.IMPORT, listProject(PROJECT_NAMES),
        CommandLineConstants.EXPORT_LIST, CommandLineConstants.ALL_ARGUMENT,
        CommandLineConstants.EXPORT_COPY,
        CommandLineConstants.EXPORT_TARGET, EXPORT_PATH.toAbsolutePath().toString()
        );
    
    for (String projectName : PROJECT_NAMES) {
      var exportElement = EXPORT_PATH.resolve(projectName);
      assertTrue("Fail to export Project as folder: " + projectName, Files.isDirectory(exportElement));
    }
  }
}
