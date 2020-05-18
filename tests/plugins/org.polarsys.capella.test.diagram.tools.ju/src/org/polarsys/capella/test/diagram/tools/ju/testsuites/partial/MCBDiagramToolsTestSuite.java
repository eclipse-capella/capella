/*******************************************************************************
 * Copyright (c) 2006, 2019 THALES GLOBAL SERVICES.
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
package org.polarsys.capella.test.diagram.tools.ju.testsuites.partial;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.polarsys.capella.test.diagram.tools.ju.mcb.CCScenario;
import org.polarsys.capella.test.diagram.tools.ju.mcb.CMScenario;
import org.polarsys.capella.test.diagram.tools.ju.mcb.COCScenario;
import org.polarsys.capella.test.diagram.tools.ju.mcb.CRBScenario;
import org.polarsys.capella.test.diagram.tools.ju.mcb.EPBSCRBScenario;
import org.polarsys.capella.test.diagram.tools.ju.mcb.InsertRemoveScenario;
import org.polarsys.capella.test.diagram.tools.ju.mcb.MBScenario;
import org.polarsys.capella.test.diagram.tools.ju.mcb.MCBScenario;
import org.polarsys.capella.test.diagram.tools.ju.mcb.OCBHideEntityTestCase;
import org.polarsys.capella.test.diagram.tools.ju.mcb.OCBScenario;
import org.polarsys.capella.test.framework.api.BasicTestArtefact;
import org.polarsys.capella.test.framework.api.BasicTestSuite;

import junit.framework.Test;

public class MCBDiagramToolsTestSuite extends BasicTestSuite {

  /**
   * Returns the suite. This is required to unary launch this test.
   */
  public static Test suite() {
    return new MCBDiagramToolsTestSuite();
  }
  
  public List<String> getRequiredTestModels() {
    return Arrays.asList("EmptyProject");
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
    tests.add(new CRBScenario());
    tests.add(new CMScenario());
    tests.add(new MBScenario());
    tests.add(new OCBScenario());
    tests.add(new OCBHideEntityTestCase());
    tests.add(new COCScenario());

    return tests;
  }

}
