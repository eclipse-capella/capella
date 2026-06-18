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
public class CommandLineExportProjectsAsZipsTest extends AbstractCommandLineExportTestCase {

  private static final Path EXTERNAl_EXPORT_PATH = Path.of("target/test-run/CommandLineExportProjectsAsZipsTest");
  private static final String RELATIVE_EXPORT_SEGMENT = "CommandLineExportProjectsAsZipsTest/output";
  private static final Path RELATIVE_EXPORT_PATH = WS_DATA_PATH.resolve(RELATIVE_EXPORT_SEGMENT);
  

  /**
   * Test constructor.
   */
  public CommandLineExportProjectsAsZipsTest() {
    super(EXTERNAl_EXPORT_PATH, RELATIVE_EXPORT_PATH);
  }
  
  
  @Override
  public void test() throws Exception {
    // External export
    MockApplicationContext.execute(new DefaultCommandLine() , "stub", 
        CommandLineConstants.INPUT, CommandLineConstants.ALL_ARGUMENT,
        CommandLineConstants.IMPORT, listProject(PROJECT_NAMES),
        CommandLineConstants.EXPORT_LIST, CommandLineConstants.ALL_ARGUMENT,
        CommandLineConstants.EXPORT_TARGET, EXTERNAl_EXPORT_PATH.toAbsolutePath().toString()
        );
    
    for (String projectName : PROJECT_NAMES) {
      var exportFile = EXTERNAl_EXPORT_PATH.resolve(projectName + ".zip"); //$NON-NLS-1$
      assertTrue("Fail to export Project as zip: " + projectName, Files.exists(exportFile) && Files.size(exportFile) > 0);
    }
    
    // External export
    MockApplicationContext.execute(new DefaultCommandLine() , "stub", 
        CommandLineConstants.INPUT, CommandLineConstants.ALL_ARGUMENT,
        CommandLineConstants.IMPORT, listProject(PROJECT_NAMES),
        CommandLineConstants.EXPORT_LIST, CommandLineConstants.ALL_ARGUMENT,
        CommandLineConstants.EXPORT_TARGET, RELATIVE_EXPORT_SEGMENT
        );
    
    for (String projectName : PROJECT_NAMES) {
      var exportFile = RELATIVE_EXPORT_PATH.resolve(projectName + ".zip"); //$NON-NLS-1$
      assertTrue(Files.exists(exportFile) && Files.size(exportFile) > 0);
    }
  }
}
