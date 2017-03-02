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

import org.polarsys.capella.test.framework.api.BasicTestArtefact;
import org.polarsys.capella.test.framework.api.BasicTestSuite;
import org.polarsys.capella.test.libraries.ju.testsuites.main.LibrariesTestSuite;
import org.polarsys.capella.test.re.updateconnections.ju.UpdateConnectionsTest.UpdateConnectionsTestSuite;
import org.polarsys.capella.test.recrpl.ju.testsuites.main.RecRplTestSuite;

import junit.framework.Test;

/**
 *
 */
public class RunInUIStep3TestSuite extends BasicTestSuite {

  /**
   * Returns the suite. This is required to unary launch this test.
   */
  public static Test suite() {
    return new RunInUIStep3TestSuite();
  }

  @Override
  protected List<BasicTestArtefact> getTests() {
    List<BasicTestArtefact> tests = new ArrayList<BasicTestArtefact>();
    tests.add(new LibrariesTestSuite());
    tests.add(new RecRplTestSuite());
    tests.add(new UpdateConnectionsTestSuite());
    return tests;
  }

}
