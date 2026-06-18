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
public class CommandLineExportProjectsAsSingleZipTest extends AbstractCommandLineExportTestCase {

  private static final Path EXTERNAl_EXPORT_TARGET = Path.of("target/test-run/CommandLineExportProjectsAsSingleZipTest/projects.zip");
  
  private static final String RELATIVE_EXPORT_SEGMENT = "CommandLineExportProjectsAsSingleZipTest.zip";
  // Path at the root of Workspace
  private static final Path RELATIVE_EXPORT_TARGET = WS_DATA_PATH.resolve(RELATIVE_EXPORT_SEGMENT);


  /**
   * Test constructor.
   */
  public CommandLineExportProjectsAsSingleZipTest() {
    super(EXTERNAl_EXPORT_TARGET.getParent(), RELATIVE_EXPORT_TARGET);
  }
  
  @Override
  public void test() throws Exception {
    // External location
    MockApplicationContext.execute(new DefaultCommandLine() , "stub", 
        CommandLineConstants.INPUT, CommandLineConstants.ALL_ARGUMENT,
        CommandLineConstants.IMPORT, listProject(PROJECT_NAMES),
        CommandLineConstants.EXPORT_LIST, CommandLineConstants.ALL_ARGUMENT,
        CommandLineConstants.SINGLE_ZIP,
        CommandLineConstants.EXPORT_TARGET, EXTERNAl_EXPORT_TARGET.toAbsolutePath().toString()
        );
    
    assertTrue(Files.exists(EXTERNAl_EXPORT_TARGET) && Files.size(EXTERNAl_EXPORT_TARGET) > 0);
    
    // Relative location
    MockApplicationContext.execute(new DefaultCommandLine() , "stub", 
        CommandLineConstants.INPUT, CommandLineConstants.ALL_ARGUMENT,
        CommandLineConstants.IMPORT, listProject(PROJECT_NAMES),
        CommandLineConstants.EXPORT_LIST, CommandLineConstants.ALL_ARGUMENT,
        CommandLineConstants.SINGLE_ZIP,
        CommandLineConstants.EXPORT_TARGET, RELATIVE_EXPORT_SEGMENT
        );
    
    assertTrue(Files.exists(RELATIVE_EXPORT_TARGET) && Files.size(RELATIVE_EXPORT_TARGET) > 0);
  }
}
