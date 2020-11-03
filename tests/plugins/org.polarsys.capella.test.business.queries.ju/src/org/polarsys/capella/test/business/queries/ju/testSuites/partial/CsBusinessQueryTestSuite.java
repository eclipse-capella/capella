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

import org.polarsys.capella.test.business.queries.ju.testcases.sysmodel.cs.ExchangeItemAllocation_AllocatedItem;
import org.polarsys.capella.test.business.queries.ju.testcases.sysmodel.cs.InterfaceImpl_ImplementedInterface;
import org.polarsys.capella.test.business.queries.ju.testcases.sysmodel.cs.InterfaceUse_UsedInterface;
import org.polarsys.capella.test.business.queries.ju.testcases.sysmodel.cs.Interface_ExchangeItems;
import org.polarsys.capella.test.business.queries.ju.testcases.sysmodel.cs.Interface_InheritedInterfaces;
import org.polarsys.capella.test.business.queries.ju.testcases.sysmodel.cs.Part_DeployedElements;
import org.polarsys.capella.test.business.queries.ju.testcases.sysmodel.cs.Part_MaxCardinality;
import org.polarsys.capella.test.business.queries.ju.testcases.sysmodel.cs.Part_MinCardinality;
import org.polarsys.capella.test.business.queries.ju.testcases.sysmodel.cs.Part_Type;
import org.polarsys.capella.test.business.queries.ju.testcases.sysmodel.cs.PhysicalLinkCategory_Links;
import org.polarsys.capella.test.business.queries.ju.testcases.sysmodel.cs.PhysicalLink_AllocatedComponentExchanges;
import org.polarsys.capella.test.business.queries.ju.testcases.sysmodel.cs.PhysicalLink_Categories;
import org.polarsys.capella.test.business.queries.ju.testcases.sysmodel.cs.PhysicalLink_LinkEnds;
import org.polarsys.capella.test.business.queries.ju.testcases.sysmodel.cs.PhysicalLink_RealizedPhysicalLinks;
import org.polarsys.capella.test.business.queries.ju.testcases.sysmodel.cs.PhysicalPath_AllocatedComponentExchanges;
import org.polarsys.capella.test.business.queries.ju.testcases.sysmodel.cs.PhysicalPath_RealizedPhysicalPaths;
import org.polarsys.capella.test.business.queries.ju.testcases.sysmodel.cs.PhysicalPort_AllocatedComponentPorts;
import org.polarsys.capella.test.business.queries.ju.testcases.sysmodel.cs.PhysicalPort_AllocatedFunctionPorts;
import org.polarsys.capella.test.business.queries.ju.testcases.sysmodel.cs.PhysicalPort_RealizedPhysicalPorts;
import org.polarsys.capella.test.framework.api.BasicTestArtefact;
import org.polarsys.capella.test.framework.api.BasicTestSuite;

import junit.framework.Test;

/**
 * @author Erwan Brottier
 */
public class CsBusinessQueryTestSuite extends BasicTestSuite {

  /**
   * Returns the suite. This is required to unary launch this test.
   */
  public static Test suite() {
    return new CsBusinessQueryTestSuite();
  }

  @Override
  protected List<BasicTestArtefact> getTests() {
    List<BasicTestArtefact> tests = new ArrayList<BasicTestArtefact>();
    tests.add(new ExchangeItemAllocation_AllocatedItem());
    tests.add(new Interface_ExchangeItems());
    tests.add(new Interface_InheritedInterfaces());
    tests.add(new InterfaceImpl_ImplementedInterface());
    tests.add(new InterfaceUse_UsedInterface());
    tests.add(new Part_DeployedElements());
    tests.add(new Part_MaxCardinality());
    tests.add(new Part_MinCardinality());
    tests.add(new Part_Type());
    tests.add(new PhysicalLink_AllocatedComponentExchanges());
    tests.add(new PhysicalLink_Categories());
    tests.add(new PhysicalLink_LinkEnds());
    tests.add(new PhysicalLink_RealizedPhysicalLinks());
    tests.add(new PhysicalLinkCategory_Links());
    tests.add(new PhysicalPath_AllocatedComponentExchanges());
    tests.add(new PhysicalPath_RealizedPhysicalPaths());
    tests.add(new PhysicalPort_AllocatedComponentPorts());
    tests.add(new PhysicalPort_AllocatedFunctionPorts());
    tests.add(new PhysicalPort_RealizedPhysicalPorts());
    return tests;
  }
}
