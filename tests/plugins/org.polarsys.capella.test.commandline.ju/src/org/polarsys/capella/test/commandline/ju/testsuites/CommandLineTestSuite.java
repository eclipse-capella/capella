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
    tests.add(new CommandLineRefreshAirdTest());
    tests.add(new CommandLineRemoveHiddenElementsTest());
    tests.add(new CommandLineExportRepresentationsTest());
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
