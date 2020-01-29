/*******************************************************************************
 * Copyright (c) 2006, 2018 THALES GLOBAL SERVICES.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *  
 * Contributors:
 *    Thales - initial API and implementation
 *******************************************************************************/
package org.polarsys.capella.test.diagram.misc.ju.testsuites;

import java.util.ArrayList;
import java.util.List;

import org.polarsys.capella.test.diagram.misc.ju.testcases.Bug1006TestCase;
import org.polarsys.capella.test.diagram.misc.ju.testcases.Bug1024TestCase;
import org.polarsys.capella.test.diagram.misc.ju.testcases.Bug1512TestCase;
import org.polarsys.capella.test.diagram.misc.ju.testcases.Bug1917TestCase;
import org.polarsys.capella.test.diagram.misc.ju.testcases.Bug2145TestCase;
import org.polarsys.capella.test.diagram.misc.ju.testcases.Bug2579TestCase;
import org.polarsys.capella.test.diagram.misc.ju.testcases.CheckDiagramDirtyStateOnOpeningTestCase;
import org.polarsys.capella.test.diagram.misc.ju.testcases.CloneDiagramTestCase;
import org.polarsys.capella.test.diagram.misc.ju.testcases.GraphTest;
import org.polarsys.capella.test.diagram.misc.ju.testcases.InterfacePortSizeTestCase;
import org.polarsys.capella.test.diagram.misc.ju.testcases.OpenAllAvailableDDiagramsTestCase;
import org.polarsys.capella.test.diagram.misc.ju.testcases.StatusLineTestCase;
import org.polarsys.capella.test.framework.api.BasicTestArtefact;
import org.polarsys.capella.test.framework.api.BasicTestSuite;

import junit.framework.Test;

public class DiagramMiscTestSuite extends BasicTestSuite {

  /**
   * Returns the suite. This is required to unary launch this test.
   */
  public static Test suite() {
    return new DiagramMiscTestSuite();
  }

  @Override
  protected List<BasicTestArtefact> getTests() {
    List<BasicTestArtefact> tests = new ArrayList<BasicTestArtefact>();
    tests.add(new GraphTest());
    tests.add(new Bug1006TestCase());
    tests.add(new Bug1024TestCase());
    tests.add(new Bug1512TestCase());
    tests.add(new InterfacePortSizeTestCase());
    tests.add(new OpenAllAvailableDDiagramsTestCase());
    tests.add(new CheckDiagramDirtyStateOnOpeningTestCase());
    tests.add(new StatusLineTestCase());
    tests.add(new Bug1917TestCase());
    tests.add(new Bug2145TestCase());
    tests.add(new CloneDiagramTestCase());
    tests.add(new Bug2579TestCase());
    return tests;
  }
}
