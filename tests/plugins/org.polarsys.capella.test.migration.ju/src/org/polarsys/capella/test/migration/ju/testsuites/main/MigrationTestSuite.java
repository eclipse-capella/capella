/*******************************************************************************
 * Copyright (c) 2018, 2019 THALES GLOBAL SERVICES.
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
package org.polarsys.capella.test.migration.ju.testsuites.main;

import java.util.ArrayList;
import java.util.List;

import org.polarsys.capella.test.framework.api.BasicTestArtefact;
import org.polarsys.capella.test.framework.api.BasicTestSuite;
import org.polarsys.capella.test.migration.ju.testcases.basic.FunctionalChainNonRegressionTest;
import org.polarsys.capella.test.migration.ju.testcases.basic.RepresentationDescriptionNonRegressionTest;
import org.polarsys.capella.test.migration.ju.testcases.basic.InvalidAirdReferenceMigrationTest;
import org.polarsys.capella.test.migration.ju.testcases.basic.MigrationEnabledTest;
import org.polarsys.capella.test.migration.ju.testcases.basic.SysmodelFilterMigration;
import org.polarsys.capella.test.migration.ju.testcases.basic.SysmodelMigrationLayout;

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
    tests.add(new RepresentationDescriptionNonRegressionTest());
    tests.add(new FunctionalChainNonRegressionTest());
    tests.add(new SysmodelFilterMigration());
    tests.add(new SysmodelMigrationLayout());
    tests.add(new InvalidAirdReferenceMigrationTest());
    tests.add(new MigrationEnabledTest());
    return tests;
  }
}
