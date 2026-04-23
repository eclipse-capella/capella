/*******************************************************************************
 * Copyright (c) 2017, 2020 THALES GLOBAL SERVICES.
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
package org.polarsys.capella.test.commandline.ju.testsuites;

import java.util.ArrayList;
import java.util.List;

import org.polarsys.capella.test.commandline.ju.testcases.CommandLineExportProjectsAsFoldersTest;
import org.polarsys.capella.test.commandline.ju.testcases.CommandLineExportProjectsAsSingleZipTest;
import org.polarsys.capella.test.commandline.ju.testcases.CommandLineExportProjectsAsZipsTest;
import org.polarsys.capella.test.commandline.ju.testcases.CommandLineExportRepresentationsTest;
import org.polarsys.capella.test.commandline.ju.testcases.CommandLineFolderMigrationTest;
import org.polarsys.capella.test.commandline.ju.testcases.CommandLineRefreshAirdTest;
import org.polarsys.capella.test.commandline.ju.testcases.CommandLineRemoveHiddenElementsTest;
import org.polarsys.capella.test.commandline.ju.testcases.CommandLineValidationTest;
import org.polarsys.capella.test.commandline.ju.testcases.CommandLineZipMigrationTest;
import org.polarsys.capella.test.framework.api.BasicTestArtefact;
import org.polarsys.capella.test.framework.api.BasicTestSuite;

import junit.framework.Test;

public class CommandLineTestSuite extends BasicTestSuite {
  
  @Override
  protected List<BasicTestArtefact> getTests() {
    return new ArrayList<>(List.of( // result must be mutable for BasicTestSuite.
      // Common arguments tests
      new CommandLineExportProjectsAsFoldersTest(),
      new CommandLineExportProjectsAsSingleZipTest(),
      new CommandLineExportProjectsAsZipsTest(),
      
      // Built-in command line tests
      new CommandLineValidationTest(), 
      new CommandLineZipMigrationTest(),
      new CommandLineFolderMigrationTest(), 
      new CommandLineRemoveHiddenElementsTest(),
      new CommandLineExportRepresentationsTest(), 
      new CommandLineRefreshAirdTest()
     ));
  }
  

  /**
   * Added in order to launch this test suite without the Capella test framework.
   * @return
   */
  public static Test suite() {
    return new CommandLineTestSuite();
  }

}
