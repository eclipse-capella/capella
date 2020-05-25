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
package org.polarsys.capella.test.transition.ju.testsuites.main;

import java.util.ArrayList;
import java.util.List;

import org.polarsys.capella.test.framework.api.BasicTestArtefact;
import org.polarsys.capella.test.framework.api.BasicTestSuite;
import org.polarsys.capella.test.transition.ju.model.AlreadyTransitionedActorTestCase;
import org.polarsys.capella.test.transition.ju.model.functionalchain.FunctionalChainTestCase;
import org.polarsys.capella.test.transition.ju.model.modestate.StateMachineTransitionTestCase;
import org.polarsys.capella.test.transition.ju.testcases.CommunicationMeanTestCase;
import org.polarsys.capella.test.transition.ju.testcases.FC2FSInitializationTestCase;
import org.polarsys.capella.test.transition.ju.testcases.LcWithPropertyValueTestCase;
import org.polarsys.capella.test.transition.ju.testcases.LogicalActorTransition;
import org.polarsys.capella.test.transition.ju.testcases.PartOwnedByPackage;
import org.polarsys.capella.test.transition.ju.testcases.ReconciliationCommunicationLinks;
import org.polarsys.capella.test.transition.ju.testcases.ReconciliationInterfaceUsesImplements;
import org.polarsys.capella.test.transition.ju.testcases.RootPropertyValueTestCase;
import org.polarsys.capella.test.transition.ju.testcases.SkeletonElementsNames;
import org.polarsys.capella.test.transition.ju.testcases.options.IncrementalModeTest;
import org.polarsys.capella.test.transition.ju.testcases.options.MergeCategoryManagerTest;
import org.polarsys.capella.test.transition.ju.testcases.sa.FunctionalTransitionInLib;

import junit.framework.Test;

public class TransitionTestSuite extends BasicTestSuite {

  /**
   * Returns the suite. This is required to unary launch this test.
   */
  public static Test suite() {
    return new TransitionTestSuite();
  }

  @Override
  protected List<BasicTestArtefact> getTests() {
    List<BasicTestArtefact> tests = new ArrayList<BasicTestArtefact>();

    tests.add(new MergeCategoryManagerTest());
    tests.add(new IncrementalModeTest());
    tests.add(new ReconciliationCommunicationLinks());
    tests.add(new ReconciliationInterfaceUsesImplements());
    tests.add(new SkeletonElementsNames());
    tests.add(new StateMachineTransitionTestCase());
    tests.add(new AlreadyTransitionedActorTestCase());
    tests.add(new LcWithPropertyValueTestCase());
    tests.add(new FC2FSInitializationTestCase());
    tests.add(new FunctionalChainTestCase());
    tests.add(new RootPropertyValueTestCase());
    tests.add(new PartOwnedByPackage());
    tests.add(new LogicalActorTransition());
    tests.add(new CommunicationMeanTestCase());
    tests.add(new TransitionsTestSuite());

    tests.add(new OaTransitionTestSuite());
    tests.add(new SaTransitionTestSuite());
    tests.add(new LaTransitionTestSuite());
    
    tests.add(new FunctionalTransitionInLib());
    return tests;
  }

}
