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
package org.polarsys.capella.test.refinement.ju.testsuites.main;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import junit.framework.Test;

import org.polarsys.capella.test.framework.api.BasicTestArtefact;
import org.polarsys.capella.test.framework.api.BasicTestSuite;
import org.polarsys.capella.test.merge.ju.testsuites.main.AllMergeTestSuites;
import org.polarsys.capella.test.refinement.ju.testsuites.partial.BugFixesTestSuite;
import org.polarsys.capella.test.refinement.ju.testsuites.partial.PhysicalRefinementTestSuite;
import org.polarsys.capella.test.refinement.ju.testsuites.partial.RefinementWithFragmentsTestSuite;
import org.polarsys.capella.test.refinement.ju.testsuites.partial.SimpleRefinementTestSuite;

/**
 */
public class AllRefinementTestSuites extends BasicTestSuite {

  @Override
  public List<String> getRequiredTestModels() {
    return Arrays.asList("BugFixes", "InterfaceDecompositionRefinement_TCs", "PhysicalRefinement_TCs",
        "RefinementWithFragments_TCs", "SimpleRefinement_TCs");
  }
  
  /**
   * @see org.polarsys.capella.test.common.AbstractExtendedTestSuite#getTests()
   */
  @Override
  protected List<BasicTestArtefact> getTests() {
    List<BasicTestArtefact> list = new ArrayList<BasicTestArtefact>();

    list.add(new SimpleRefinementTestSuite());
    list.add(new RefinementWithFragmentsTestSuite());
    list.add(new PhysicalRefinementTestSuite());
    //list.add(new InterfaceDecompositionRefinementTestSuite());
    list.add(new BugFixesTestSuite());

    list.add(new AllMergeTestSuites());

    return list;
  }

  /**
   * Added in order to launch this test suite without the Capella test framework.
   */
  public static Test suite() {
    return new AllRefinementTestSuites();
  }
}
