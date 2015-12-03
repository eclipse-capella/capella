/*******************************************************************************
 * Copyright (c) 2006, 2015 THALES GLOBAL SERVICES.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *  
 * Contributors:
 *    Thales - initial API and implementation
 *******************************************************************************/
package org.polarsys.capella.test.business.queries.ju.testSuites.partial;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import junit.framework.Test;

import org.polarsys.capella.test.business.queries.ju.testcases.sysmodel.capellacommon.ChoicePseudoState_StateRealizations;
import org.polarsys.capella.test.business.queries.ju.testcases.sysmodel.capellacommon.FinalState_Activity;
import org.polarsys.capella.test.business.queries.ju.testcases.sysmodel.capellacommon.FinalState_AvailableInStates;
import org.polarsys.capella.test.business.queries.ju.testcases.sysmodel.capellacommon.FinalState_Entry;
import org.polarsys.capella.test.business.queries.ju.testcases.sysmodel.capellacommon.FinalState_Exit;
import org.polarsys.capella.test.business.queries.ju.testcases.sysmodel.capellacommon.FinalState_StateRealizations;
import org.polarsys.capella.test.business.queries.ju.testcases.sysmodel.capellacommon.InitialPseudoState_StateRealizations;
import org.polarsys.capella.test.business.queries.ju.testcases.sysmodel.capellacommon.Mode_Activity;
import org.polarsys.capella.test.business.queries.ju.testcases.sysmodel.capellacommon.Mode_AvailableInStates;
import org.polarsys.capella.test.business.queries.ju.testcases.sysmodel.capellacommon.Mode_Entry;
import org.polarsys.capella.test.business.queries.ju.testcases.sysmodel.capellacommon.Mode_Exit;
import org.polarsys.capella.test.business.queries.ju.testcases.sysmodel.capellacommon.Mode_ReferencedStates;
import org.polarsys.capella.test.business.queries.ju.testcases.sysmodel.capellacommon.Mode_StateRealizations;
import org.polarsys.capella.test.business.queries.ju.testcases.sysmodel.capellacommon.StateTransitionEffect;
import org.polarsys.capella.test.business.queries.ju.testcases.sysmodel.capellacommon.StateTransitionTrigger;
import org.polarsys.capella.test.business.queries.ju.testcases.sysmodel.capellacommon.StateTransition_StateTransitionRealizations;
import org.polarsys.capella.test.business.queries.ju.testcases.sysmodel.capellacommon.State_Activity;
import org.polarsys.capella.test.business.queries.ju.testcases.sysmodel.capellacommon.State_AvailableInStates;
import org.polarsys.capella.test.business.queries.ju.testcases.sysmodel.capellacommon.State_Entry;
import org.polarsys.capella.test.business.queries.ju.testcases.sysmodel.capellacommon.State_Exit;
import org.polarsys.capella.test.business.queries.ju.testcases.sysmodel.capellacommon.State_ReferencedStates;
import org.polarsys.capella.test.business.queries.ju.testcases.sysmodel.capellacommon.State_StateRealizations;
import org.polarsys.capella.test.business.queries.ju.testcases.sysmodel.capellacommon.TerminatePseudoState_StateRealizations;
import org.polarsys.capella.test.business.queries.ju.testcases.sysmodel.capellamodeller.SystemEngineering_ReusedSharedPkg;
import org.polarsys.capella.test.framework.api.BasicTestArtefact;
import org.polarsys.capella.test.framework.api.BasicTestSuite;

/**
 * @author Erwan Brottier
 */
public class CapellaModellerBusinessQueryTestSuite extends BasicTestSuite {

  /**
   * Returns the suite. This is required to unary launch this test.
   */
  public static Test suite() {
    return new CapellaModellerBusinessQueryTestSuite();
  }

  @Override
  protected List<BasicTestArtefact> getTests() {
    List<BasicTestArtefact> tests = new ArrayList<BasicTestArtefact>();
    tests.add(new SystemEngineering_ReusedSharedPkg());
    return tests;
  }

  @Override
  public List<String> getRequiredTestModels() {
    return Arrays.asList("sysmodel");
  }
}
