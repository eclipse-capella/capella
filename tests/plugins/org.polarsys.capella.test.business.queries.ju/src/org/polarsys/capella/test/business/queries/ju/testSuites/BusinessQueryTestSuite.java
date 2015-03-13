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
package org.polarsys.capella.test.business.queries.ju.testSuites;

import java.util.ArrayList;
import java.util.List;

import junit.framework.Test;

import org.polarsys.capella.test.business.queries.ju.testcases.sysmodel.cs.Component_ImplementedInterfaces;
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
import org.polarsys.capella.test.business.queries.ju.testcases.sysmodel.cs.PhysicalLink_RealizedPhysicalLinks;
import org.polarsys.capella.test.business.queries.ju.testcases.sysmodel.cs.PhysicalPath_AllocatedComponentExchanges;
import org.polarsys.capella.test.business.queries.ju.testcases.sysmodel.cs.PhysicalPath_RealizedPhysicalPaths;
import org.polarsys.capella.test.business.queries.ju.testcases.sysmodel.cs.PhysicalPort_AllocatedComponentPorts;
import org.polarsys.capella.test.business.queries.ju.testcases.sysmodel.cs.PhysicalPort_AllocatedFunctionPorts;
import org.polarsys.capella.test.business.queries.ju.testcases.sysmodel.cs.PhysicalPort_RealizedPhysicalPorts;
import org.polarsys.capella.test.framework.api.BasicTestSuite;

/**
 * @author Erwan Brottier
 */
public class BusinessQueryTestSuite extends BasicTestSuite {

  /**
   * Returns the suite. This is required to unary launch this test.
   */
  public static Test suite() {
    return new BusinessQueryTestSuite();
  }

  @Override
  protected List<? extends Test> getTests() {
    List<Test> tests = new ArrayList<Test>();
    tests.add(new Component_ImplementedInterfaces());
    tests.add(new ExchangeItemAllocation_AllocatedItem());
    tests.add(new InterfaceImpl_ImplementedInterface());
    tests.add(new InterfaceUse_UsedInterface());
    tests.add(new Interface_ExchangeItems());
    tests.add(new Interface_InheritedInterfaces());
    tests.add(new Part_DeployedElements());
    tests.add(new Part_MaxCardinality());
    tests.add(new Part_MinCardinality());
    tests.add(new Part_Type());
    tests.add(new PhysicalLinkCategory_Links());
    tests.add(new PhysicalLink_AllocatedComponentExchanges());
    tests.add(new PhysicalLink_Categories());
    tests.add(new PhysicalLink_RealizedPhysicalLinks());
    tests.add(new PhysicalPath_AllocatedComponentExchanges());
    tests.add(new PhysicalPath_RealizedPhysicalPaths());
    tests.add(new PhysicalPort_AllocatedComponentPorts());
    tests.add(new PhysicalPort_AllocatedFunctionPorts());
    tests.add(new PhysicalPort_RealizedPhysicalPorts());
    return tests;
  }

}
