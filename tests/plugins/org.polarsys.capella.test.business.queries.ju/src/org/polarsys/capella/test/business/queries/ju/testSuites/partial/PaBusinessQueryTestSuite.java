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

import org.polarsys.capella.test.business.queries.ju.testcases.sysmodel.pa.PhysicalArchitecture_AllocatedLogicalArchitecture;
import org.polarsys.capella.test.business.queries.ju.testcases.sysmodel.pa.PhysicalComp_Deployers;
import org.polarsys.capella.test.business.queries.ju.testcases.sysmodel.pa.PhysicalComp_Deployments;
import org.polarsys.capella.test.business.queries.ju.testcases.sysmodel.pa.PhysicalComponent_AllocatedFunctions;
import org.polarsys.capella.test.business.queries.ju.testcases.sysmodel.pa.PhysicalComponent_DeployedComponents;
import org.polarsys.capella.test.business.queries.ju.testcases.sysmodel.pa.PhysicalComponent_ImplementedInterfaces;
import org.polarsys.capella.test.business.queries.ju.testcases.sysmodel.pa.PhysicalComponent_InvolvingCapabilityRealizations;
import org.polarsys.capella.test.business.queries.ju.testcases.sysmodel.pa.PhysicalComponent_RealizedComponents;
import org.polarsys.capella.test.business.queries.ju.testcases.sysmodel.pa.PhysicalComponent_Super;
import org.polarsys.capella.test.business.queries.ju.testcases.sysmodel.pa.PhysicalComponent_UsedInterfaces;
import org.polarsys.capella.test.business.queries.ju.testcases.sysmodel.pa.PhysicalFunction_AvailableInstates;
import org.polarsys.capella.test.business.queries.ju.testcases.sysmodel.pa.PhysicalFunction_RealizedFunctions;
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
    tests.add(new PhysicalArchitecture_AllocatedLogicalArchitecture());
    tests.add(new PhysicalComponent_Super());
    tests.add(new PhysicalComponent_InvolvingCapabilityRealizations());
    tests.add(new PhysicalComp_Deployers());
    tests.add(new PhysicalComp_Deployments());
    tests.add(new PhysicalComponent_ImplementedInterfaces());
    tests.add(new PhysicalComponent_UsedInterfaces());
    tests.add(new PhysicalComponent_DeployedComponents());
    tests.add(new PhysicalComponent_AllocatedFunctions());
    tests.add(new PhysicalComponent_RealizedComponents());
    tests.add(new PhysicalFunction_AvailableInstates());
    tests.add(new PhysicalFunction_RealizedFunctions());
    return tests;
  }
}
