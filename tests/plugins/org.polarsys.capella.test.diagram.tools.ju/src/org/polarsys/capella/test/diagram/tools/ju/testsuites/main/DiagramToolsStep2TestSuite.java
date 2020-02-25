/*******************************************************************************
 * Copyright (c) 2006, 2019 THALES GLOBAL SERVICES.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *  
 * Contributors:
 *    Thales - initial API and implementation
 *******************************************************************************/
package org.polarsys.capella.test.diagram.tools.ju.testsuites.main;

import java.util.ArrayList;
import java.util.List;

import org.polarsys.capella.test.diagram.tools.ju.fcd.FCDDiagramToolsTestSuite;
import org.polarsys.capella.test.diagram.tools.ju.testsuites.partial.ORBDiagramToolsTestSuite;
import org.polarsys.capella.test.diagram.tools.ju.testsuites.partial.PDDiagramToolsTestSuite;
import org.polarsys.capella.test.diagram.tools.ju.testsuites.partial.ReuseTestSuite;
import org.polarsys.capella.test.diagram.tools.ju.testsuites.partial.SequenceDiagramToolsTestSuite;
import org.polarsys.capella.test.diagram.tools.ju.testsuites.partial.XABDiagramToolsTestSuite;
import org.polarsys.capella.test.diagram.tools.ju.testsuites.partial.XBDiagramToolsTestSuite;
import org.polarsys.capella.test.diagram.tools.ju.testsuites.partial.XDFBDiagramToolsTestSuite;
import org.polarsys.capella.test.diagram.tools.ju.testsuites.partial.XFCDDiagramToolsTestSuite;
import org.polarsys.capella.test.framework.api.BasicTestArtefact;
import org.polarsys.capella.test.framework.api.BasicTestSuite;

import junit.framework.Test;

public class DiagramToolsStep2TestSuite extends BasicTestSuite {

  /**
   * Returns the suite. This is required to unary launch this test.
   */
  public static Test suite() {
    return new DiagramToolsStep2TestSuite();
  }

  @Override
  protected List<BasicTestArtefact> getTests() {
    List<BasicTestArtefact> tests = new ArrayList<>();
    tests.add(new PDDiagramToolsTestSuite());
    tests.add(new ORBDiagramToolsTestSuite());
    tests.add(new XABDiagramToolsTestSuite());
    tests.add(new SequenceDiagramToolsTestSuite());
    tests.add(new XBDiagramToolsTestSuite());
    tests.add(new XDFBDiagramToolsTestSuite());
    tests.add(new XDFBDiagramToolsTestSuite.WithReloadedTestModel());
    tests.add(new FCDDiagramToolsTestSuite());
    tests.add(new XFCDDiagramToolsTestSuite());
    tests.add(new ReuseTestSuite());
    return tests;
  }

}
