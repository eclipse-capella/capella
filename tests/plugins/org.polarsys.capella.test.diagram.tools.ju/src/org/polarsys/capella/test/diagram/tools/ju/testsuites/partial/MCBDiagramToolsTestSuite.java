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
package org.polarsys.capella.test.diagram.tools.ju.testsuites.partial;

import java.util.ArrayList;
import java.util.List;

import junit.framework.Test;

import org.polarsys.capella.test.diagram.tools.ju.mcb.CCScenario;
import org.polarsys.capella.test.diagram.tools.ju.mcb.CMScenario;
import org.polarsys.capella.test.diagram.tools.ju.mcb.EPBSCRBScenario;
import org.polarsys.capella.test.diagram.tools.ju.mcb.InsertRemoveScenario;
import org.polarsys.capella.test.diagram.tools.ju.mcb.LACRBScenario;
import org.polarsys.capella.test.diagram.tools.ju.mcb.MBScenario;
import org.polarsys.capella.test.diagram.tools.ju.mcb.MCBScenario;
import org.polarsys.capella.test.diagram.tools.ju.mcb.OCBScenario;
import org.polarsys.capella.test.framework.api.BasicTestArtefact;
import org.polarsys.capella.test.framework.api.BasicTestSuite;

public class MCBDiagramToolsTestSuite extends BasicTestSuite {

  /**
   * Returns the suite. This is required to unary launch this test.
   */
  public static Test suite() {
    return new MCBDiagramToolsTestSuite();
  }

  /**
   * @see org.polarsys.capella.test.framework.api.BasicTestSuite#getTests()
   */
  @Override
  protected List<BasicTestArtefact> getTests() {
    List<BasicTestArtefact> tests = new ArrayList<BasicTestArtefact>();

    tests.add(new InsertRemoveScenario());
    tests.add(new MCBScenario());
    tests.add(new CCScenario());
    tests.add(new EPBSCRBScenario());
    tests.add(new LACRBScenario());
    tests.add(new CMScenario());
    tests.add(new MBScenario());
    tests.add(new OCBScenario());

    return tests;
  }

  @Override
  public List<String> getRequiredTestModels() {
    return null;
  }
}
