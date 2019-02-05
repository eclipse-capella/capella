/*******************************************************************************
 * Copyright (c) 2006, 2017 THALES GLOBAL SERVICES.
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
import org.polarsys.capella.test.benchmarks.ju.suites.AllBenchmarksTestSuite;
import org.polarsys.capella.test.framework.api.BasicTestArtefact;
import org.polarsys.capella.test.framework.api.BasicTestSuite;

import junit.framework.Test;

/**
 *
 */
public class RunInUITestSuite extends BasicTestSuite {

  /**
   * Returns the suite. This is required to unary launch this test.
   */
  public static Test suite() {
    return new RunInUITestSuite();
  }

  @Override
  protected List<BasicTestArtefact> getTests() {
    List<BasicTestArtefact> tests = new ArrayList<BasicTestArtefact>();
    tests.add(new RunInUIStep1TestSuite());
    tests.add(new RunInUIStep2TestSuite());
    tests.add(new RunInUIStep3TestSuite());
    tests.add(new RunInUIStep4TestSuite());
    tests.add(new RunInUIStep5TestSuite());
    tests.add(new AllBenchmarksTestSuite());
    return tests;
  }

}
