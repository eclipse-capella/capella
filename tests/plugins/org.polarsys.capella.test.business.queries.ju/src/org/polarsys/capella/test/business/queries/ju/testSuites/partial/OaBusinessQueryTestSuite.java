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

import org.polarsys.capella.test.business.queries.ju.testcases.sysmodel.oa.CommunicationMean_ConvoyedInformation;
import org.polarsys.capella.test.business.queries.ju.testcases.sysmodel.oa.CommunicationMean_AllocatedFunctionalExchanges;
import org.polarsys.capella.test.business.queries.ju.testcases.sysmodel.oa.Entity_AllocatedActivities;
import org.polarsys.capella.test.business.queries.ju.testcases.sysmodel.oa.Entity_AllocatedRoles;
import org.polarsys.capella.test.business.queries.ju.testcases.sysmodel.oa.OperationalActivity_AvailableInStates;
import org.polarsys.capella.test.business.queries.ju.testcases.sysmodel.oa.OperationalCapability_AvailableInStates;
import org.polarsys.capella.test.business.queries.ju.testcases.sysmodel.oa.OperationalCapability_InheritedCapabilities;
import org.polarsys.capella.test.business.queries.ju.testcases.sysmodel.oa.OperationalCapability_InvolvedActivity;
import org.polarsys.capella.test.business.queries.ju.testcases.sysmodel.oa.OperationalCapability_InvolvedEntities;
import org.polarsys.capella.test.business.queries.ju.testcases.sysmodel.oa.OperationalCapability_InvolvedOperationalProcess;
import org.polarsys.capella.test.business.queries.ju.testcases.sysmodel.oa.OperationalProcess_AvailableInStates;
import org.polarsys.capella.test.business.queries.ju.testcases.sysmodel.oa.Role_AllocatedActivities;
import org.polarsys.capella.test.framework.api.BasicTestArtefact;
import org.polarsys.capella.test.framework.api.BasicTestSuite;

import junit.framework.Test;

/**
 * @author Erwan Brottier
 */
public class OaBusinessQueryTestSuite extends BasicTestSuite {

  /**
   * Returns the suite. This is required to unary launch this test.
   */
  public static Test suite() {
    return new OaBusinessQueryTestSuite();
  }

  @Override
  protected List<BasicTestArtefact> getTests() {
    List<BasicTestArtefact> tests = new ArrayList<BasicTestArtefact>();
    tests.add(new CommunicationMean_ConvoyedInformation());
    tests.add(new CommunicationMean_AllocatedFunctionalExchanges());
    tests.add(new Entity_AllocatedActivities());
    tests.add(new Entity_AllocatedRoles());
    tests.add(new OperationalActivity_AvailableInStates());
    tests.add(new OperationalCapability_AvailableInStates());
    tests.add(new OperationalCapability_InheritedCapabilities());
    tests.add(new OperationalCapability_InvolvedActivity());
    tests.add(new OperationalCapability_InvolvedEntities());
    tests.add(new OperationalCapability_InvolvedOperationalProcess());
    tests.add(new OperationalProcess_AvailableInStates());
    tests.add(new Role_AllocatedActivities());

    return tests;
  }

}
