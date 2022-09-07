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
package org.polarsys.capella.test.suite.inui.ju;

import java.util.ArrayList;
import java.util.List;

import org.polarsys.capella.test.business.queries.ju.testSuites.main.BusinessQueryTestSuite;
import org.polarsys.capella.test.framework.api.BasicTestArtefact;
import org.polarsys.capella.test.framework.api.BasicTestSuite;
import org.polarsys.capella.test.navigator.ju.testsuites.main.NavigatorUITestSuite;
import org.polarsys.capella.test.semantic.queries.ju.testsuites.SemanticQueriesTestSuite;
import org.polarsys.capella.test.semantic.ui.ju.testsuites.SemanticUITestSuite;
import org.polarsys.capella.test.validation.rules.ju.testsuites.main.ValidationRulesTestSuite;

import junit.framework.Test;

/**
 *
 */
public class RunInUIStep1TestSuite extends BasicTestSuite {

  /**
   * Returns the suite. This is required to unary launch this test.
   */
  public static Test suite() {
    return new RunInUIStep1TestSuite();
  }

  @Override
  protected List<BasicTestArtefact> getTests() {
    List<BasicTestArtefact> tests = new ArrayList<BasicTestArtefact>();
    tests.add(new BusinessQueryTestSuite());
    tests.add(new SemanticUITestSuite());
    tests.add(new SemanticQueriesTestSuite());
    tests.add(new ValidationRulesTestSuite());
    tests.add(new NavigatorUITestSuite());
    return tests;
  }

}
