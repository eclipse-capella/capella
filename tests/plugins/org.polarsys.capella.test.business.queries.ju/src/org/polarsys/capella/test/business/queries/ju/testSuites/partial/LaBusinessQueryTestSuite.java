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
package org.polarsys.capella.test.business.queries.ju.testSuites.partial;

import java.util.ArrayList;
import java.util.List;

import org.polarsys.capella.test.business.queries.ju.testcases.sysmodel.la.CapabilityRealization_AvailableInStates;
import org.polarsys.capella.test.business.queries.ju.testcases.sysmodel.la.CapabilityRealization_InheritedCapabilities;
import org.polarsys.capella.test.business.queries.ju.testcases.sysmodel.la.CapabilityRealization_InvolvedAbstractFunctions;
import org.polarsys.capella.test.business.queries.ju.testcases.sysmodel.la.CapabilityRealization_InvolvedComponents;
import org.polarsys.capella.test.business.queries.ju.testcases.sysmodel.la.CapabilityRealization_InvolvedFunctionalChains;
import org.polarsys.capella.test.business.queries.ju.testcases.sysmodel.la.CapabilityRealization_RealizedCapabilities;
import org.polarsys.capella.test.business.queries.ju.testcases.sysmodel.la.LogicalComponent_AllocatedFunctions;
import org.polarsys.capella.test.business.queries.ju.testcases.sysmodel.la.LogicalComponent_ImplementedInterfaces;
import org.polarsys.capella.test.business.queries.ju.testcases.sysmodel.la.LogicalComponent_InvolvingCapabilityRealizations;
import org.polarsys.capella.test.business.queries.ju.testcases.sysmodel.la.LogicalComponent_RealizedComponents;
import org.polarsys.capella.test.business.queries.ju.testcases.sysmodel.la.LogicalComponent_Super;
import org.polarsys.capella.test.business.queries.ju.testcases.sysmodel.la.LogicalComponent_UsedInterfaces;
import org.polarsys.capella.test.business.queries.ju.testcases.sysmodel.la.LogicalFunction_AvailableInstates;
import org.polarsys.capella.test.business.queries.ju.testcases.sysmodel.la.LogicalFunction_RealizedFunctions;
import org.polarsys.capella.test.framework.api.BasicTestArtefact;
import org.polarsys.capella.test.framework.api.BasicTestSuite;

import junit.framework.Test;

/**
 * @author Erwan Brottier
 */
public class LaBusinessQueryTestSuite extends BasicTestSuite {

  /**
   * Returns the suite. This is required to unary launch this test.
   */
  public static Test suite() {
    return new LaBusinessQueryTestSuite();
  }

  @Override
  protected List<BasicTestArtefact> getTests() {
    List<BasicTestArtefact> tests = new ArrayList<BasicTestArtefact>();
    tests.add(new CapabilityRealization_InvolvedComponents());
    tests.add(new CapabilityRealization_AvailableInStates());
    tests.add(new CapabilityRealization_InheritedCapabilities());
    tests.add(new CapabilityRealization_InvolvedComponents());
    tests.add(new CapabilityRealization_InvolvedAbstractFunctions());
    tests.add(new CapabilityRealization_InvolvedFunctionalChains());
    tests.add(new CapabilityRealization_RealizedCapabilities());
    tests.add(new LogicalComponent_RealizedComponents());
    tests.add(new LogicalComponent_Super());
    tests.add(new LogicalComponent_AllocatedFunctions());
    tests.add(new LogicalComponent_ImplementedInterfaces());
    tests.add(new LogicalComponent_InvolvingCapabilityRealizations());
    tests.add(new LogicalComponent_UsedInterfaces());
    tests.add(new LogicalFunction_AvailableInstates());
    tests.add(new LogicalFunction_RealizedFunctions());    
    return tests;
  }
}
