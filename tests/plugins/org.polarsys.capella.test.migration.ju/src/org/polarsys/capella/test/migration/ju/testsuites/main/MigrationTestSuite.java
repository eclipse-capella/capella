/*******************************************************************************
 * Copyright (c) 2018 THALES GLOBAL SERVICES.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *  
 * Contributors:
 *    Thales - initial API and implementation
 *******************************************************************************/
package org.polarsys.capella.test.migration.ju.testsuites.main;

import java.util.ArrayList;
import java.util.List;

import org.polarsys.capella.test.framework.api.BasicTestArtefact;
import org.polarsys.capella.test.framework.api.BasicTestSuite;
import org.polarsys.capella.test.migration.ju.testcases.basic.FunctionalChainNonRegressionTest;
import org.polarsys.capella.test.migration.ju.testcases.basic.MigrateAirdFrom_120_To_130_PreservingRepresentationUid_TestCase;
import org.polarsys.capella.test.migration.ju.testcases.basic.MigrateAirdFrom_12X_To_130_PreservingRepresentationUid_TestCase;
import org.polarsys.capella.test.migration.ju.testsuites.partial.MigrationBasicTestSuite;

import junit.framework.Test;

public class MigrationTestSuite extends BasicTestSuite {

  /**
   * Returns the suite. This is required to unary launch this test.
   */
  public static Test suite() {
    return new MigrationTestSuite();
  }

  @Override
  protected List<BasicTestArtefact> getTests() {
    List<BasicTestArtefact> tests = new ArrayList<BasicTestArtefact>();
    tests.add(new MigrationBasicTestSuite());
    tests.add(new MigrateAirdFrom_12X_To_130_PreservingRepresentationUid_TestCase());
    tests.add(new MigrateAirdFrom_120_To_130_PreservingRepresentationUid_TestCase());
    tests.add(new FunctionalChainNonRegressionTest());
    return tests;
  }

}
