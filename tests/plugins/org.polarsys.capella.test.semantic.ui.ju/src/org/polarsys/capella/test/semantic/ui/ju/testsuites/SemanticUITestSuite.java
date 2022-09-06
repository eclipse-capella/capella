/*******************************************************************************
 * Copyright (c) 2018, 2022 THALES GLOBAL SERVICES and others.
 * 
 * This program and the accompanying materials are made available under the
 * terms of the Eclipse Public License 2.0 which is available at
 * http://www.eclipse.org/legal/epl-2.0
 * 
 * SPDX-License-Identifier: EPL-2.0
 * 
 * Contributors:
 *    Thales - initial API and implementation
 *    Obeo - 2304 In Semantic Browser, add Involved Activities category for Operational Capability
 *******************************************************************************/
package org.polarsys.capella.test.semantic.ui.ju.testsuites;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.polarsys.capella.test.framework.api.BasicTestArtefact;
import org.polarsys.capella.test.framework.api.BasicTestSuite;
import org.polarsys.capella.test.semantic.ui.ju.testcases.SemanticBrowserReferencingElementNavigationTest;

import junit.framework.Test;

public class SemanticUITestSuite extends BasicTestSuite {

  @Override
  protected List<BasicTestArtefact> getTests() {
    List<BasicTestArtefact> tests = new ArrayList<BasicTestArtefact>();
    
    tests.add(new SemanticBrowserReferencingElementNavigationTest());
    
    return tests;
  }

  @Override
  public List<String> getRequiredTestModels() {
    return Collections.singletonList("SemanticBrowserNavigation");
  }

  /**
   * Added in order to launch this test suite without the Capella test framework.
   */
  public static Test suite() {
    return new SemanticUITestSuite();
  }
}
