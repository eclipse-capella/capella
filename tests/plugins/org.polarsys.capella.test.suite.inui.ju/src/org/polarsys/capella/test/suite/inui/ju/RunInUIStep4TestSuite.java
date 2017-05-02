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
package org.polarsys.capella.test.suite.inui.ju;

import java.util.ArrayList;
import java.util.List;

import org.polarsys.capella.test.commandline.ju.testsuites.CommandLineTestSuite;
import org.polarsys.capella.test.framework.api.BasicTestArtefact;
import org.polarsys.capella.test.framework.api.BasicTestSuite;
import org.polarsys.capella.test.migration.ju.testsuites.main.MigrationTestSuite;
import org.polarsys.capella.test.refinement.ju.testsuites.main.AllRefinementTestSuites;
import org.polarsys.capella.test.transition.ju.testsuites.main.TransitionTestSuite;

import junit.framework.Test;

/**
 *
 */
public class RunInUIStep4TestSuite extends BasicTestSuite {

  /**
   * Returns the suite. This is required to unary launch this test.
   */
  public static Test suite() {
    return new RunInUIStep4TestSuite();
  }

  @Override
  protected List<BasicTestArtefact> getTests() {
    List<BasicTestArtefact> tests = new ArrayList<BasicTestArtefact>();
    tests.add(new AllRefinementTestSuites());
    tests.add(new TransitionTestSuite());
    tests.add(new MigrationTestSuite());
    tests.add(new CommandLineTestSuite());
    return tests;
  }

}
