/*******************************************************************************
 * Copyright (c) 2019, 2020 THALES GLOBAL SERVICES.
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
package org.polarsys.capella.test.transition.ju.testsuites.main;

import java.util.ArrayList;
import java.util.List;

import org.polarsys.capella.test.framework.api.BasicTestArtefact;
import org.polarsys.capella.test.framework.api.BasicTestSuite;
import org.polarsys.capella.test.transition.ju.testcases.sa.ActorTransition;
import org.polarsys.capella.test.transition.ju.testcases.sa.CapabilityTransition;
import org.polarsys.capella.test.transition.ju.testcases.sa.DataTransition;
import org.polarsys.capella.test.transition.ju.testcases.sa.ExchangeItemTransition;
import org.polarsys.capella.test.transition.ju.testcases.sa.FunctionalTransition;
import org.polarsys.capella.test.transition.ju.testcases.sa.ScenarioTransition;
import org.polarsys.capella.test.transition.ju.testcases.sa.StateMachineTransition;

import junit.framework.Test;

public class SaTransitionTestSuite extends BasicTestSuite {

  /**
   * Returns the suite. This is required to unary launch this test.
   */
  public static Test suite() {
    return new SaTransitionTestSuite();
  }

  @Override
  protected List<BasicTestArtefact> getTests() {
    List<BasicTestArtefact> tests = new ArrayList<BasicTestArtefact>();
    tests.add(new ActorTransition());
    tests.add(new CapabilityTransition());
    tests.add(new DataTransition());
    tests.add(new ExchangeItemTransition());
    tests.add(new FunctionalTransition());
    tests.add(new ScenarioTransition());
    tests.add(new StateMachineTransition());

    return tests;
  }

}
