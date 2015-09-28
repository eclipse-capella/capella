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
package org.polarsys.capella.test.refinement.ju.testsuites.partial;

import java.util.ArrayList;
import java.util.List;

import junit.framework.Test;

import org.polarsys.capella.test.framework.api.BasicTestArtefact;
import org.polarsys.capella.test.framework.api.BasicTestSuite;
import org.polarsys.capella.test.refinement.ju.testcases.withfragments.SimpleTC_WithInteractionFragmentTC0;
import org.polarsys.capella.test.refinement.ju.testcases.withfragments.SimpleTC_WithInteractionFragmentTC1;
import org.polarsys.capella.test.refinement.ju.testcases.withfragments.SimpleTC_WithInteractionFragmentTC2;
import org.polarsys.capella.test.refinement.ju.testcases.withfragments.SimpleTC_WithInteractionState;
import org.polarsys.capella.test.refinement.ju.testcases.withfragments.SimpleTC_WithInteractionUseTC0;
import org.polarsys.capella.test.refinement.ju.testcases.withfragments.SimpleTC_WithInteractionUseTC1LC1;
import org.polarsys.capella.test.refinement.ju.testcases.withfragments.SimpleTC_WithInteractionUseTC1LC2;
import org.polarsys.capella.test.refinement.ju.testcases.withfragments.SimpleTC_WithInteractionUseTC1LC3;
import org.polarsys.capella.test.refinement.ju.testcases.withfragments.RegressionTC;

/**
 * Test suite in order to test the refinement in "stand-alone" mode e.g.
 *
 */
public class RefinementWithFragmentsTestSuite extends BasicTestSuite {

  /**
   * @see org.polarsys.capella.test.common.AbstractExtendedTestSuite#getTests()
   */
  @Override
  protected List<BasicTestArtefact> getTests() {
    List<BasicTestArtefact> tests = new ArrayList<BasicTestArtefact>();

    // Simple TCs
    tests.add(new SimpleTC_WithInteractionFragmentTC0());
    tests.add(new SimpleTC_WithInteractionFragmentTC1());
    tests.add(new SimpleTC_WithInteractionFragmentTC2());
    tests.add(new SimpleTC_WithInteractionState());
    tests.add(new SimpleTC_WithInteractionUseTC0());
    tests.add(new SimpleTC_WithInteractionUseTC1LC1());
    tests.add(new SimpleTC_WithInteractionUseTC1LC2());
    tests.add(new SimpleTC_WithInteractionUseTC1LC3());

    // Regression TCs
    tests.add(new RegressionTC());

    return tests;
  }

  @Override
  public List<String> getRequiredTestModels() {
    return null;
  }

  /**
   * Added in order to launch this test suite without the Capella test framework.
   */
  public static Test suite() {
    return new RefinementWithFragmentsTestSuite();
  }
}
