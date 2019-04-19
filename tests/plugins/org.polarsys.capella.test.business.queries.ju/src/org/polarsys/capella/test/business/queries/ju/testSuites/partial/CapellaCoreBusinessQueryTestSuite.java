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
import java.util.Arrays;
import java.util.List;

import junit.framework.Test;

import org.polarsys.capella.test.business.queries.ju.testcases.sysmodel.capellacore.CapellaElement_AppliedPropertyValueGroups;
import org.polarsys.capella.test.business.queries.ju.testcases.sysmodel.capellacore.CapellaElement_AppliedPropertyValues;
import org.polarsys.capella.test.business.queries.ju.testcases.sysmodel.capellacore.CapellaElement_Status;
import org.polarsys.capella.test.business.queries.ju.testcases.sysmodel.capellacore.EnumerationPropertyValue_Type;
import org.polarsys.capella.test.business.queries.ju.testcases.sysmodel.capellacore.EnumerationPropertyValue_Value;
import org.polarsys.capella.test.framework.api.BasicTestArtefact;
import org.polarsys.capella.test.framework.api.BasicTestSuite;

/**
 * @author Erwan Brottier
 */
public class CapellaCoreBusinessQueryTestSuite extends BasicTestSuite {

  /**
   * Returns the suite. This is required to unary launch this test.
   */
  public static Test suite() {
    return new CapellaCoreBusinessQueryTestSuite();
  }

  @Override
  protected List<BasicTestArtefact> getTests() {
    List<BasicTestArtefact> tests = new ArrayList<BasicTestArtefact>();
    tests.add(new EnumerationPropertyValue_Type());
    tests.add(new EnumerationPropertyValue_Value());
    tests.add(new CapellaElement_AppliedPropertyValueGroups());
    tests.add(new CapellaElement_AppliedPropertyValues());
    tests.add(new CapellaElement_Status());
    return tests;
  }

  @Override
  public List<String> getRequiredTestModels() {
    return Arrays.asList("sysmodel");
  }

}
