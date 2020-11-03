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

import org.polarsys.capella.test.diagram.layout.ju.testsuites.LayoutTestSuite;
import org.polarsys.capella.test.diagram.tools.ju.testsuites.main.DiagramToolsStep1TestSuite;
import org.polarsys.capella.test.framework.api.BasicTestArtefact;
import org.polarsys.capella.test.framework.api.BasicTestSuite;
import org.polarsys.capella.test.platform.ju.testsuites.PlatformTestSuite;
import org.polarsys.capella.test.richtext.ju.testsuites.RichtextTestSuite;

import junit.framework.Test;

/**
 *
 */
public class RunInUIStep5TestSuite extends BasicTestSuite {

  /**
   * Returns the suite. This is required to unary launch this test.
   */
  public static Test suite() {
    return new RunInUIStep5TestSuite();
  }

  @Override
  protected List<BasicTestArtefact> getTests() {
    List<BasicTestArtefact> tests = new ArrayList<BasicTestArtefact>();
    tests.add(new DiagramToolsStep1TestSuite());
    tests.add(new LayoutTestSuite());
    tests.add(new RichtextTestSuite());
    tests.add(new PlatformTestSuite());

    // tests.add(new DocTestSuite());
    return tests;
  }

}
