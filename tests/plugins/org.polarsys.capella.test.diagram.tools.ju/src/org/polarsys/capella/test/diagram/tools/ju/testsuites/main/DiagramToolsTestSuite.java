/*******************************************************************************
 * Copyright (c) 2006, 2016 THALES GLOBAL SERVICES.
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

import junit.framework.Test;

import org.polarsys.capella.test.diagram.tools.ju.testsuites.partial.LABDiagramToolsTestSuite;
import org.polarsys.capella.test.diagram.tools.ju.testsuites.partial.PABDiagramToolsTestSuite;
import org.polarsys.capella.test.diagram.tools.ju.testsuites.partial.SFDBDiagramToolsTestSuite;
import org.polarsys.capella.test.framework.api.BasicTestArtefact;
import org.polarsys.capella.test.framework.api.BasicTestSuite;

public class DiagramToolsTestSuite extends BasicTestSuite {

  /**
   * Returns the suite. This is required to unary launch this test.
   */
  public static Test suite() {
    return new DiagramToolsTestSuite();
  }

  @Override
  protected List<BasicTestArtefact> getTests() {
    List<BasicTestArtefact> tests = new ArrayList<BasicTestArtefact>();
    tests.add(new SFDBDiagramToolsTestSuite());
    tests.add(new LABDiagramToolsTestSuite());
    tests.add(new PABDiagramToolsTestSuite());
    return tests;
  }

  @Override
  public List<String> getRequiredTestModels() {
    return null;
  }
}
