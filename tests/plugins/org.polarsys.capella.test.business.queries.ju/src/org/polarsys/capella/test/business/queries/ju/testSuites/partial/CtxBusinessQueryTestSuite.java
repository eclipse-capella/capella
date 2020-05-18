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

import org.polarsys.capella.test.business.queries.ju.testcases.sysmodel.ctx.Capability_AvailableInStates;
import org.polarsys.capella.test.business.queries.ju.testcases.sysmodel.ctx.Capability_ExtendedCapabilities;
import org.polarsys.capella.test.business.queries.ju.testcases.sysmodel.ctx.Capability_IncludedCapabilities;
import org.polarsys.capella.test.business.queries.ju.testcases.sysmodel.ctx.Capability_InheritedCapabilities;
import org.polarsys.capella.test.business.queries.ju.testcases.sysmodel.ctx.Capability_InvolvedAbstractFunctions;
import org.polarsys.capella.test.business.queries.ju.testcases.sysmodel.ctx.Capability_InvolvedFunctionalChains;
import org.polarsys.capella.test.business.queries.ju.testcases.sysmodel.ctx.Capability_InvolvedSystemComponents;
import org.polarsys.capella.test.business.queries.ju.testcases.sysmodel.ctx.Capability_RealizedCapabilities;
import org.polarsys.capella.test.business.queries.ju.testcases.sysmodel.ctx.Mission_ExploitedCapabilities;
import org.polarsys.capella.test.business.queries.ju.testcases.sysmodel.ctx.Mission_InvolvedSystemComponents;
import org.polarsys.capella.test.business.queries.ju.testcases.sysmodel.ctx.SystemComponent_AllocatedFunctions;
import org.polarsys.capella.test.business.queries.ju.testcases.sysmodel.ctx.SystemComponent_ImplementedInterfaces;
import org.polarsys.capella.test.business.queries.ju.testcases.sysmodel.ctx.SystemComponent_InvolvingCapabilities;
import org.polarsys.capella.test.business.queries.ju.testcases.sysmodel.ctx.SystemComponent_RealizedEntities;
import org.polarsys.capella.test.business.queries.ju.testcases.sysmodel.ctx.SystemComponent_Super;
import org.polarsys.capella.test.business.queries.ju.testcases.sysmodel.ctx.SystemComponent_UsedInterfaces;
import org.polarsys.capella.test.business.queries.ju.testcases.sysmodel.ctx.SystemFunction_AvailableInstates;
import org.polarsys.capella.test.business.queries.ju.testcases.sysmodel.ctx.SystemFunction_RealizedFunctions;
import org.polarsys.capella.test.framework.api.BasicTestArtefact;
import org.polarsys.capella.test.framework.api.BasicTestSuite;

import junit.framework.Test;

/**
 * @author Erwan Brottier
 */
public class CtxBusinessQueryTestSuite extends BasicTestSuite {

  /**
   * Returns the suite. This is required to unary launch this test.
   */
  public static Test suite() {
    return new CtxBusinessQueryTestSuite();
  }

  @Override
  protected List<BasicTestArtefact> getTests() {
    List<BasicTestArtefact> tests = new ArrayList<BasicTestArtefact>();
    tests.add(new SystemComponent_Super());
    tests.add(new SystemComponent_InvolvingCapabilities());
    tests.add(new Capability_AvailableInStates());
    tests.add(new Capability_ExtendedCapabilities());
    tests.add(new Capability_IncludedCapabilities());
    tests.add(new Capability_InheritedCapabilities());
    tests.add(new Capability_InvolvedSystemComponents());
    tests.add(new Capability_InvolvedAbstractFunctions());
    tests.add(new Capability_InvolvedFunctionalChains());
    tests.add(new Capability_RealizedCapabilities());
    tests.add(new Mission_ExploitedCapabilities());
    tests.add(new Mission_InvolvedSystemComponents());
    tests.add(new SystemComponent_AllocatedFunctions());
    tests.add(new SystemComponent_ImplementedInterfaces());
    tests.add(new SystemComponent_RealizedEntities());
    tests.add(new SystemComponent_UsedInterfaces());
    tests.add(new SystemFunction_AvailableInstates());
    tests.add(new SystemFunction_RealizedFunctions());    
    return tests;
  }
}
