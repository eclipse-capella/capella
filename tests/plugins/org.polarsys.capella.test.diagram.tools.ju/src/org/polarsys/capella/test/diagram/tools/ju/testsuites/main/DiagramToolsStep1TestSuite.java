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
package org.polarsys.capella.test.diagram.tools.ju.testsuites.main;

import java.util.ArrayList;
import java.util.List;

import org.polarsys.capella.test.diagram.tools.ju.es.MultiInstanceRoleTest;
import org.polarsys.capella.test.diagram.tools.ju.testsuites.partial.CCRIDiagramToolsTestSuite;
import org.polarsys.capella.test.diagram.tools.ju.testsuites.partial.CDBDiagramToolsTestSuite;
import org.polarsys.capella.test.diagram.tools.ju.testsuites.partial.CommonToolsTestSuite;
import org.polarsys.capella.test.diagram.tools.ju.testsuites.partial.DiagramActionsTestSuite;
import org.polarsys.capella.test.diagram.tools.ju.testsuites.partial.IDBDiagramToolsTestSuite;
import org.polarsys.capella.test.diagram.tools.ju.testsuites.partial.IDDiagramToolsTestSuite;
import org.polarsys.capella.test.diagram.tools.ju.testsuites.partial.MCBDiagramToolsTestSuite;
import org.polarsys.capella.test.diagram.tools.ju.testsuites.partial.MSDiagramToolsTestSuite;
import org.polarsys.capella.test.diagram.tools.ju.testsuites.partial.MSMDiagramToolsTestSuite;
import org.polarsys.capella.test.diagram.tools.ju.testsuites.partial.OEBDiagramToolsTestSuite;
import org.polarsys.capella.test.diagram.tools.ju.testsuites.partial.SFDBDiagramToolsTestSuite;
import org.polarsys.capella.test.framework.api.BasicTestArtefact;
import org.polarsys.capella.test.framework.api.BasicTestSuite;

import junit.framework.Test;

public class DiagramToolsStep1TestSuite extends BasicTestSuite {

  /**
   * Returns the suite. This is required to unary launch this test.
   */
  public static Test suite() {
    return new DiagramToolsStep1TestSuite();
  }

  @Override
  protected List<BasicTestArtefact> getTests() {
    List<BasicTestArtefact> tests = new ArrayList<BasicTestArtefact>();
    tests.add(new MSDiagramToolsTestSuite());
    // FIXME SFDBDiagramToolsTestSuite should run before MSMDiagramToolsTestSuite, because
    // InitializeFromExistingDiagramTestCase fails
    // when it run after MSMShowHideTransition2ModesTestand MSMShowHideTransition2StatesTest
    tests.add(new SFDBDiagramToolsTestSuite());
    tests.add(new MSMDiagramToolsTestSuite());
    tests.add(new CDBDiagramToolsTestSuite());
    tests.add(new IDBDiagramToolsTestSuite());
    tests.add(new MCBDiagramToolsTestSuite());
    tests.add(new DiagramActionsTestSuite());
    tests.add(new CommonToolsTestSuite());
    tests.add(new MultiInstanceRoleTest());
    tests.add(new CCRIDiagramToolsTestSuite());
    tests.add(new OEBDiagramToolsTestSuite());
    tests.add(new IDDiagramToolsTestSuite());
    return tests;
  }

}
