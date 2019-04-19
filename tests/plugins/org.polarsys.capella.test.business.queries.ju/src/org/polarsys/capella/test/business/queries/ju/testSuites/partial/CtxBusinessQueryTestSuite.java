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
import java.util.List;

import junit.framework.Test;

import org.polarsys.capella.test.business.queries.ju.testcases.sysmodel.ctx.Actor_FunctionalAllocation;
import org.polarsys.capella.test.business.queries.ju.testcases.sysmodel.ctx.Actor_ImplementedInterfaces;
import org.polarsys.capella.test.business.queries.ju.testcases.sysmodel.ctx.Actor_InheritedActors;
import org.polarsys.capella.test.business.queries.ju.testcases.sysmodel.ctx.Actor_InteractingCapabilities;
import org.polarsys.capella.test.business.queries.ju.testcases.sysmodel.ctx.Actor_RealizedCapabilities;
import org.polarsys.capella.test.business.queries.ju.testcases.sysmodel.ctx.Actor_RealizedOperationalActors;
import org.polarsys.capella.test.business.queries.ju.testcases.sysmodel.ctx.Actor_RealizedOperationalEntities;
import org.polarsys.capella.test.business.queries.ju.testcases.sysmodel.ctx.Actor_UsedInterfaces;
import org.polarsys.capella.test.business.queries.ju.testcases.sysmodel.ctx.Capability_AvailableInStates;
import org.polarsys.capella.test.business.queries.ju.testcases.sysmodel.ctx.Capability_ExtendedCapabilities;
import org.polarsys.capella.test.business.queries.ju.testcases.sysmodel.ctx.Capability_IncludedCapabilities;
import org.polarsys.capella.test.business.queries.ju.testcases.sysmodel.ctx.Capability_InheritedCapabilities;
import org.polarsys.capella.test.business.queries.ju.testcases.sysmodel.ctx.Capability_InteractingActors;
import org.polarsys.capella.test.business.queries.ju.testcases.sysmodel.ctx.Capability_InvolvedAbstractFunctions;
import org.polarsys.capella.test.business.queries.ju.testcases.sysmodel.ctx.Capability_InvolvedFunctionalChains;
import org.polarsys.capella.test.business.queries.ju.testcases.sysmodel.ctx.Capability_RealizedCapabilities;
import org.polarsys.capella.test.business.queries.ju.testcases.sysmodel.ctx.Mission_IncludedCapabilities;
import org.polarsys.capella.test.business.queries.ju.testcases.sysmodel.ctx.Mission_InteractingActors;
import org.polarsys.capella.test.business.queries.ju.testcases.sysmodel.ctx.SystemFunction_AvailableInstates;
import org.polarsys.capella.test.business.queries.ju.testcases.sysmodel.ctx.SystemFunction_FunctionRealization;
import org.polarsys.capella.test.business.queries.ju.testcases.sysmodel.ctx.System_FunctionalAllocation;
import org.polarsys.capella.test.business.queries.ju.testcases.sysmodel.ctx.System_ImplementedInterfaces;
import org.polarsys.capella.test.business.queries.ju.testcases.sysmodel.ctx.System_RealizedOperationalEntities;
import org.polarsys.capella.test.business.queries.ju.testcases.sysmodel.ctx.System_UsedInterfaces;
import org.polarsys.capella.test.framework.api.BasicTestArtefact;
import org.polarsys.capella.test.framework.api.BasicTestSuite;

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
    tests.add(new Actor_FunctionalAllocation());
    tests.add(new Actor_ImplementedInterfaces());
    tests.add(new Actor_InheritedActors());
    tests.add(new Actor_InteractingCapabilities());
    tests.add(new Actor_RealizedCapabilities());
    tests.add(new Actor_RealizedOperationalActors());
    tests.add(new Actor_RealizedOperationalEntities());
    tests.add(new Actor_UsedInterfaces());
    tests.add(new Capability_AvailableInStates());
    tests.add(new Capability_ExtendedCapabilities());
    tests.add(new Capability_IncludedCapabilities());
    tests.add(new Capability_InheritedCapabilities());
    tests.add(new Capability_InteractingActors());
    tests.add(new Capability_InvolvedAbstractFunctions());
    tests.add(new Capability_InvolvedFunctionalChains());
    tests.add(new Capability_RealizedCapabilities());
    tests.add(new Mission_IncludedCapabilities());
    tests.add(new Mission_InteractingActors());
    tests.add(new System_FunctionalAllocation());
    tests.add(new System_ImplementedInterfaces());
    tests.add(new System_RealizedOperationalEntities());
    tests.add(new System_UsedInterfaces());
    tests.add(new SystemFunction_AvailableInstates());
    tests.add(new SystemFunction_FunctionRealization());    
    return tests;
  }
}
