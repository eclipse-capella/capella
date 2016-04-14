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
package org.polarsys.capella.test.semantic.queries.ju.testsuites;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.polarsys.capella.test.framework.api.BasicTestArtefact;
import org.polarsys.capella.test.framework.api.BasicTestSuite;
import org.polarsys.capella.test.semantic.queries.ju.model.SemanticQueries;
import org.polarsys.capella.test.semantic.queries.ju.testcases.AbstractFunction_mother_activity_allocation;
import org.polarsys.capella.test.semantic.queries.ju.testcases.AbstractFunction_mother_function_allocation;
import org.polarsys.capella.test.semantic.queries.ju.testcases.AvailableForTypeClassExistTest;
import org.polarsys.capella.test.semantic.queries.ju.testcases.CapellaElement_applied_property_value_groups;
import org.polarsys.capella.test.semantic.queries.ju.testcases.CapellaElement_applied_property_values;
import org.polarsys.capella.test.semantic.queries.ju.testcases.PropertyValueGroup_applying_valued_element;
import org.polarsys.capella.test.semantic.queries.ju.testcases.PropertyValue_applying_valued_element;

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
    return tests;
  }
  @Override
  public List<String> getRequiredTestModels() {
    return Collections.singletonList(SemanticQueries.class.getSimpleName());
  }
}
