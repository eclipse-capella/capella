/*******************************************************************************
 * Copyright (c) 2017 THALES GLOBAL SERVICES.
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
    List<BasicTestArtefact> tests = new ArrayList<>();
    tests.add(new CommandLineValidationTest());
    tests.add(new CommandLineZipMigrationTest());
    tests.add(new CommandLineFolderMigrationTest());
    tests.add(new CommandLineRemoveHiddenElementsTest());
    tests.add(new CommandLineExportRepresentationsTest());
    tests.add(new CommandLineRefreshAirdTest());
    return tests;
  }
  

  /**
   * Added in order to launch this test suite without the Capella test framework.
   * @return
   */
  public static Test suite() {
    return new CommandLineTestSuite();
  }

}
