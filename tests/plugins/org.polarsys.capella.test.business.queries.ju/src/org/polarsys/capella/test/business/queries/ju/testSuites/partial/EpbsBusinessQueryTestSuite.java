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

import junit.framework.Test;

import org.polarsys.capella.test.business.queries.ju.testcases.sysmodel.epbs.ConfigurationItem_ImplementedInterfaces;
import org.polarsys.capella.test.business.queries.ju.testcases.sysmodel.epbs.ConfigurationItem_RealizedPhysicalArtifacts;
import org.polarsys.capella.test.business.queries.ju.testcases.sysmodel.epbs.ConfigurationItem_InvolvingCapabilityRealizations;
import org.polarsys.capella.test.business.queries.ju.testcases.sysmodel.epbs.ConfigurationItem_UsedInterfaces;
import org.polarsys.capella.test.business.queries.ju.testcases.sysmodel.epbs.EPBSArchitecture_AllocatedPhysicalArchitecture;
import org.polarsys.capella.test.framework.api.BasicTestArtefact;
import org.polarsys.capella.test.framework.api.BasicTestSuite;

/**
 * @author Erwan Brottier
 */
public class EpbsBusinessQueryTestSuite extends BasicTestSuite {

  /**
   * Returns the suite. This is required to unary launch this test.
   */
  public static Test suite() {
    return new EpbsBusinessQueryTestSuite();
  }

  @Override
  protected List<BasicTestArtefact> getTests() {
    List<BasicTestArtefact> tests = new ArrayList<BasicTestArtefact>();
    tests.add(new ConfigurationItem_ImplementedInterfaces());
    tests.add(new ConfigurationItem_RealizedPhysicalArtifacts());
    tests.add(new ConfigurationItem_InvolvingCapabilityRealizations());
    tests.add(new ConfigurationItem_UsedInterfaces());
    tests.add(new EPBSArchitecture_AllocatedPhysicalArchitecture());    
    return tests;
  }
}
