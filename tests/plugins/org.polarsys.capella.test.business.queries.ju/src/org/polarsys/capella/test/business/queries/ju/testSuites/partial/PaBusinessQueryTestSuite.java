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
package org.polarsys.capella.test.business.queries.ju.testSuites.partial;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.polarsys.capella.test.business.queries.ju.testcases.sysmodel.pa.PhysicalActor_FunctionalAllocation;
import org.polarsys.capella.test.business.queries.ju.testcases.sysmodel.pa.PhysicalActor_ImplementedInterfaces;
import org.polarsys.capella.test.business.queries.ju.testcases.sysmodel.pa.PhysicalActor_InheritedActors;
import org.polarsys.capella.test.business.queries.ju.testcases.sysmodel.pa.PhysicalActor_InteractingCapabilities;
import org.polarsys.capella.test.business.queries.ju.testcases.sysmodel.pa.PhysicalActor_LogicalActorRealization;
import org.polarsys.capella.test.business.queries.ju.testcases.sysmodel.pa.PhysicalActor_UsedInterfaces;
import org.polarsys.capella.test.business.queries.ju.testcases.sysmodel.pa.PhysicalArchitecture_AllocatedLogicalArchitecture;
import org.polarsys.capella.test.business.queries.ju.testcases.sysmodel.pa.PhysicalComp_DefineRealizations;
import org.polarsys.capella.test.business.queries.ju.testcases.sysmodel.pa.PhysicalComp_Deployers;
import org.polarsys.capella.test.business.queries.ju.testcases.sysmodel.pa.PhysicalComp_Deployments;
import org.polarsys.capella.test.business.queries.ju.testcases.sysmodel.pa.PhysicalComp_ImplementedInterface;
import org.polarsys.capella.test.business.queries.ju.testcases.sysmodel.pa.PhysicalComp_UsedInterface;
import org.polarsys.capella.test.business.queries.ju.testcases.sysmodel.pa.PhysicalComponent_DeployedComponents;
import org.polarsys.capella.test.business.queries.ju.testcases.sysmodel.pa.PhysicalComponent_FunctionalAllocation;
import org.polarsys.capella.test.business.queries.ju.testcases.sysmodel.pa.PhysicalComponent_LogicalComponentRealization;
import org.polarsys.capella.test.business.queries.ju.testcases.sysmodel.pa.PhysicalFunction_AvailableInstates;
import org.polarsys.capella.test.business.queries.ju.testcases.sysmodel.pa.PhysicalFunction_FunctionalRealization;
import org.polarsys.capella.test.framework.api.BasicTestArtefact;
import org.polarsys.capella.test.framework.api.BasicTestSuite;

import junit.framework.Test;

/**
 * @author Erwan Brottier
 */
public class PaBusinessQueryTestSuite extends BasicTestSuite {

  /**
   * Returns the suite. This is required to unary launch this test.
   */
  public static Test suite() {
    return new PaBusinessQueryTestSuite();
  }

  @Override
  protected List<BasicTestArtefact> getTests() {
    List<BasicTestArtefact> tests = new ArrayList<BasicTestArtefact>();
    tests.add(new PhysicalActor_FunctionalAllocation());
    tests.add(new PhysicalActor_ImplementedInterfaces());
    tests.add(new PhysicalActor_InheritedActors());
    tests.add(new PhysicalActor_InteractingCapabilities());
    tests.add(new PhysicalActor_LogicalActorRealization());
    tests.add(new PhysicalActor_UsedInterfaces());
    tests.add(new PhysicalArchitecture_AllocatedLogicalArchitecture());
    tests.add(new PhysicalComp_DefineRealizations());
    tests.add(new PhysicalComp_Deployers());
    tests.add(new PhysicalComp_Deployments());
    tests.add(new PhysicalComp_ImplementedInterface());
    //tests.add(new PhysicalComp_ImplementedLCS());
    tests.add(new PhysicalComp_UsedInterface());
    tests.add(new PhysicalComponent_DeployedComponents());
    tests.add(new PhysicalComponent_FunctionalAllocation());
    tests.add(new PhysicalComponent_LogicalComponentRealization());
    tests.add(new PhysicalFunction_AvailableInstates());
    tests.add(new PhysicalFunction_FunctionalRealization());    
    return tests;
  }

  @Override
  public List<String> getRequiredTestModels() {
    return Arrays.asList("sysmodel");
  }

}
