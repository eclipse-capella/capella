/*******************************************************************************
 * Copyright (c) 2006, 2020 THALES GLOBAL SERVICES.
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
package org.polarsys.capella.test.business.queries.ju.testSuites.partial;

import java.util.ArrayList;
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
import org.polarsys.capella.test.framework.api.BasicTestArtefact;
import org.polarsys.capella.test.framework.api.BasicTestSuite;

/**
 * @author Erwan Brottier
 */
public class CapellaCommonBusinessQueryTestSuite extends BasicTestSuite {

  /**
   * Returns the suite. This is required to unary launch this test.
   */
  public static Test suite() {
    return new CapellaCommonBusinessQueryTestSuite();
  }

  @Override
  protected List<BasicTestArtefact> getTests() {
    List<BasicTestArtefact> tests = new ArrayList<BasicTestArtefact>();
    tests.add(new ChoicePseudoState_StateRealizations());
    tests.add(new FinalState_Activity());
    tests.add(new FinalState_AvailableInStates());
    tests.add(new FinalState_Entry());
    tests.add(new FinalState_Exit());
    tests.add(new FinalState_StateRealizations());
    tests.add(new InitialPseudoState_StateRealizations());
    tests.add(new Mode_Activity());
    tests.add(new Mode_AvailableInStates());
    tests.add(new Mode_Entry());
    tests.add(new Mode_Exit());
    tests.add(new Mode_ReferencedStates());
    tests.add(new Mode_StateRealizations());
    tests.add(new State_Activity());
    tests.add(new State_AvailableInStates());
    tests.add(new State_Entry());
    tests.add(new State_Exit());
    tests.add(new State_ReferencedStates());
    tests.add(new State_StateRealizations());
    tests.add(new StateTransition_StateTransitionRealizations());
    tests.add(new StateTransitionEffect());
    tests.add(new StateTransitionTrigger());
    tests.add(new TerminatePseudoState_StateRealizations());
    return tests;
  }
}
