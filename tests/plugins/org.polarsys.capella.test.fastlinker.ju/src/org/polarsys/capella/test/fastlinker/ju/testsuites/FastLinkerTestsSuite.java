/*******************************************************************************
 * Copyright (c) 2006, 2020 THALES GLOBAL SERVICES.
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
package org.polarsys.capella.test.fastlinker.ju.testsuites;

import java.util.ArrayList;
import java.util.List;

import junit.framework.Test;

import org.polarsys.capella.test.fastlinker.ju.testcases.CreateFunctionRealizationTest;
import org.polarsys.capella.test.fastlinker.ju.testcases.ForbiddenModelElementTest;
import org.polarsys.capella.test.framework.api.BasicTestArtefact;
import org.polarsys.capella.test.framework.api.BasicTestSuite;

/**
 * FastLinker test suite.
 */
public class FastLinkerTestsSuite extends BasicTestSuite {

  /**
   * {@inheritDoc}
   */
  @Override
  protected List<BasicTestArtefact> getTests() {
    List<BasicTestArtefact> tests = new ArrayList<BasicTestArtefact>();
    tests.add(new CreateFunctionRealizationTest());
    tests.add(new ForbiddenModelElementTest());
    return tests;
  }

  /**
   * Added in order to launch this test suite without the Capella test framework.
   * @return
   */
  public static Test suite() {
    return new FastLinkerTestsSuite();
  }

}
