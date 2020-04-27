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

import org.polarsys.capella.test.diagram.tools.ju.msm.MSMCreateChoicePseudoState;
import org.polarsys.capella.test.diagram.tools.ju.msm.MSMCreateConstrainedElement;
import org.polarsys.capella.test.diagram.tools.ju.msm.MSMCreateConstraint;
import org.polarsys.capella.test.diagram.tools.ju.msm.MSMCreateDeepHistoryPseudoState;
import org.polarsys.capella.test.diagram.tools.ju.msm.MSMCreateFinalPseudoState;
import org.polarsys.capella.test.diagram.tools.ju.msm.MSMCreateForkPseudoState;
import org.polarsys.capella.test.diagram.tools.ju.msm.MSMCreateJoinPseudoState;
import org.polarsys.capella.test.diagram.tools.ju.msm.MSMCreateShallowHistoryPseudoState;
import org.polarsys.capella.test.diagram.tools.ju.msm.MSMCreateTerminatePseudoState;
import org.polarsys.capella.test.diagram.tools.ju.msm.MSMDragAndDropStates;
import org.polarsys.capella.test.diagram.tools.ju.msm.MSMFunctionalExchangeActionLabelTest;
import org.polarsys.capella.test.diagram.tools.ju.msm.MSMHideRegionNamesFilterTest;
import org.polarsys.capella.test.diagram.tools.ju.msm.MSMHierarchyModesTest;
import org.polarsys.capella.test.diagram.tools.ju.msm.MSMHierarchyStatesTest;
import org.polarsys.capella.test.diagram.tools.ju.msm.MSMInsertRemoveConstraints;
import org.polarsys.capella.test.diagram.tools.ju.msm.MSMMixedModesStatesTest;
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
  
  public List<String> getRequiredTestModels() {
    return Arrays.asList("DiagramToolsModel");
  }

  /**
   * @see org.polarsys.capella.test.framework.api.BasicTestSuite#getTests()
   */
  @Override
  protected List<BasicTestArtefact> getTests() {
    List<BasicTestArtefact> tests = new ArrayList<BasicTestArtefact>();
    tests.add(new MSMCreateJoinPseudoState());
    tests.add(new MSMCreateChoicePseudoState());
    tests.add(new MSMCreateForkPseudoState());
    tests.add(new MSMDragAndDropStates());
    tests.add(new MSMCreateTerminatePseudoState());
    tests.add(new MSMCreateFinalPseudoState());
    tests.add(new MSMCreateShallowHistoryPseudoState());
    tests.add(new MSMCreateDeepHistoryPseudoState());
    tests.add(new MSMCreateConstraint());
    tests.add(new MSMInsertRemoveConstraints());
    tests.add(new MSMCreateConstrainedElement());
    tests.add(new MSMMixedModesStatesTest());
    tests.add(new MSMHierarchyModesTest());
    tests.add(new MSMHierarchyStatesTest());
    tests.add(new MSMShowHideInitialDeepTest());
    tests.add(new MSMShowHideInitialTest());
    tests.add(new MSMShowHideTransition2ModesTest());
    tests.add(new MSMShowHideTransition2StatesTest());
    tests.add(new MSMFunctionalExchangeActionLabelTest());
    tests.add(new MSMHideRegionNamesFilterTest());
//    tests.add(new MSMDisplayRegionNameOnEntryExitPointsFilterTest());
    
    return tests;
  }

}
