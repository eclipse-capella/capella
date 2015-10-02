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
import org.polarsys.capella.test.refinement.ju.testcases.physical.PhysicalTC_LA_2_PA;
import org.polarsys.capella.test.refinement.ju.testcases.physical.PhysicalTC_PA_2_PC1;
import org.polarsys.capella.test.refinement.ju.testcases.physical.PhysicalTC_PA_2_PC2;
import org.polarsys.capella.test.refinement.ju.testcases.physical.PhysicalTC_PC1_2_PC11;
import org.polarsys.capella.test.refinement.ju.testcases.physical.PhysicalTC_PC1_2_PC12;

/**
 * Test suite in order to test the refinement in "stand-alone" mode e.g.
 */
public class PhysicalRefinementTestSuite extends BasicTestSuite {

  /**
   * @see org.polarsys.capella.test.common.AbstractExtendedTestSuite#getTests()
   */
  @Override
  protected List<BasicTestArtefact> getTests() {
    List<BasicTestArtefact> tests = new ArrayList<BasicTestArtefact>();

    tests.add(new PhysicalTC_LA_2_PA());
    tests.add(new PhysicalTC_PA_2_PC1());
    tests.add(new PhysicalTC_PC1_2_PC11());
    tests.add(new PhysicalTC_PC1_2_PC12());
    tests.add(new PhysicalTC_PA_2_PC2());

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
    return new PhysicalRefinementTestSuite();
  }
}
