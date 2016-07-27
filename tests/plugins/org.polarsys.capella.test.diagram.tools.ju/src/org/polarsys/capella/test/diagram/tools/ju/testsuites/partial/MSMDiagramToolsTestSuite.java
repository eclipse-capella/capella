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

import org.polarsys.capella.test.diagram.tools.ju.msm.MSMDisplayRegionNameOnEntryExitPointsFilterTest;
import org.polarsys.capella.test.diagram.tools.ju.msm.MSMDragAndDropStates;
import org.polarsys.capella.test.diagram.tools.ju.msm.MSMFunctionalExchangeActionLabelTest;
import org.polarsys.capella.test.diagram.tools.ju.msm.MSMHideRegionNamesFilterTest;
import org.polarsys.capella.test.diagram.tools.ju.msm.MSMShowHideHierarchy1ModesStatesTest;
import org.polarsys.capella.test.diagram.tools.ju.msm.MSMShowHideHierarchy2ModesTest;
import org.polarsys.capella.test.diagram.tools.ju.msm.MSMShowHideHierarchy2StatesTest;
import org.polarsys.capella.test.diagram.tools.ju.msm.MSMShowHideHierarchy3StatesTest;
import org.polarsys.capella.test.diagram.tools.ju.msm.MSMShowHideInitialDeepTest;
import org.polarsys.capella.test.diagram.tools.ju.msm.MSMShowHideInitialTest;
import org.polarsys.capella.test.diagram.tools.ju.msm.MSMShowHideTransition2ModesTest;
import org.polarsys.capella.test.diagram.tools.ju.msm.MSMShowHideTransition2StatesTest;
import org.polarsys.capella.test.framework.api.BasicTestArtefact;
import org.polarsys.capella.test.framework.api.BasicTestSuite;

import junit.framework.Test;

public class MSMDiagramToolsTestSuite extends BasicTestSuite {

  /**
   * Returns the suite. This is required to unary launch this test.
   */
  public static Test suite() {
    return new MSMDiagramToolsTestSuite();
  }

  /**
   * @see org.polarsys.capella.test.framework.api.BasicTestSuite#getTests()
   */
  @Override
  protected List<BasicTestArtefact> getTests() {
    List<BasicTestArtefact> tests = new ArrayList<BasicTestArtefact>();
    tests.add(new MSMDragAndDropStates());
    tests.add(new MSMShowHideHierarchy1ModesStatesTest());
    tests.add(new MSMShowHideHierarchy2ModesTest());
    tests.add(new MSMShowHideHierarchy2StatesTest());
    tests.add(new MSMShowHideHierarchy3StatesTest());
    tests.add(new MSMShowHideInitialDeepTest());
    tests.add(new MSMShowHideInitialTest());
    tests.add(new MSMShowHideTransition2ModesTest());
    tests.add(new MSMShowHideTransition2StatesTest());
    tests.add(new MSMFunctionalExchangeActionLabelTest());
    tests.add(new MSMHideRegionNamesFilterTest());
//    tests.add(new MSMDisplayRegionNameOnEntryExitPointsFilterTest());
    
    return tests;
  }

  @Override
  public List<String> getRequiredTestModels() {
    return null;
  }
}
