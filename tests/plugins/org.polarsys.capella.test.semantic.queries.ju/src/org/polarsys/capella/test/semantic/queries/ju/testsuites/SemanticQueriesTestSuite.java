/*******************************************************************************
 * Copyright (c) 2006, 2017 THALES GLOBAL SERVICES.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *  
 * Contributors:
 *    Thales - initial API and implementation
 *******************************************************************************/
package org.polarsys.capella.test.semantic.queries.ju.testsuites;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.polarsys.capella.test.framework.api.BasicTestArtefact;
import org.polarsys.capella.test.framework.api.BasicTestSuite;
import org.polarsys.capella.test.semantic.queries.ju.testcases.AbstractFunction_mother_activity_allocation;
import org.polarsys.capella.test.semantic.queries.ju.testcases.AbstractFunction_mother_function_allocation;
import org.polarsys.capella.test.semantic.queries.ju.testcases.AvailableForTypeClassExistTest;
import org.polarsys.capella.test.semantic.queries.ju.testcases.CapabilityRealization_InvolvedActors;
import org.polarsys.capella.test.semantic.queries.ju.testcases.CapabilityRealization_InvolvedComponents;
import org.polarsys.capella.test.semantic.queries.ju.testcases.CapabilityRealization_InvolvedFunctionalChains;
import org.polarsys.capella.test.semantic.queries.ju.testcases.CapabilityRealization_InvolvedFunctions;
import org.polarsys.capella.test.semantic.queries.ju.testcases.CapabilityRealization_OwnedFunctionalChains;
import org.polarsys.capella.test.semantic.queries.ju.testcases.CapabilityRealization_RealizedCapability;
import org.polarsys.capella.test.semantic.queries.ju.testcases.CapabilityRealization_RealizingCapabilityRealization;
import org.polarsys.capella.test.semantic.queries.ju.testcases.CapellaElement_applied_property_value_groups;
import org.polarsys.capella.test.semantic.queries.ju.testcases.CapellaElement_applied_property_values;
import org.polarsys.capella.test.semantic.queries.ju.testcases.EntryExitPoint_ParentRegionTest;
import org.polarsys.capella.test.semantic.queries.ju.testcases.PropertyValueGroup_applying_valued_element;
import org.polarsys.capella.test.semantic.queries.ju.testcases.PropertyValue_applying_valued_element;
import org.polarsys.capella.test.semantic.queries.ju.testcases.State_OwnedEntryExitPointsTest;

import junit.framework.Test;

public class SemanticQueriesTestSuite extends BasicTestSuite {

  @Override
  protected List<BasicTestArtefact> getTests() {
    List<BasicTestArtefact> tests = new ArrayList<BasicTestArtefact>();
    tests.add(new AvailableForTypeClassExistTest());
    tests.add(new AbstractFunction_mother_activity_allocation());
    tests.add(new AbstractFunction_mother_function_allocation());
    tests.add(new CapellaElement_applied_property_value_groups());
    tests.add(new CapellaElement_applied_property_values());
    tests.add(new PropertyValue_applying_valued_element());
    tests.add(new PropertyValueGroup_applying_valued_element());
    tests.add(new EntryExitPoint_ParentRegionTest());
    tests.add(new State_OwnedEntryExitPointsTest());
    tests.add(new CapabilityRealization_InvolvedActors());
    tests.add(new CapabilityRealization_InvolvedComponents());
    tests.add(new CapabilityRealization_InvolvedFunctionalChains());
    tests.add(new CapabilityRealization_InvolvedFunctions());
    tests.add(new CapabilityRealization_OwnedFunctionalChains());
    tests.add(new CapabilityRealization_RealizedCapability());
    tests.add(new CapabilityRealization_RealizingCapabilityRealization());
    
    return tests;
  }
  
  @Override
  public List<String> getRequiredTestModels() {
    return Collections.singletonList("SemanticQueries");
  }
  
  /**
   * Added in order to launch this test suite without the Capella test framework.
   */
  public static Test suite() {
    return new SemanticQueriesTestSuite();
  }
}
