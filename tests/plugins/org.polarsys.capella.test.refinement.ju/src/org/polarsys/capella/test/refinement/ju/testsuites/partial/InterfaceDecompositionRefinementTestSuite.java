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
import org.polarsys.capella.test.refinement.ju.testcases.itfdecomposition.MultiPartTCWithBothAmbiguity;
import org.polarsys.capella.test.refinement.ju.testcases.itfdecomposition.MultiPartTCWithBothAmbiguityThroughPorts;
import org.polarsys.capella.test.refinement.ju.testcases.itfdecomposition.MultiPartTCWithImplementationAmbiguity;
import org.polarsys.capella.test.refinement.ju.testcases.itfdecomposition.MultiPartTCWithImplementationAmbiguityThroughPorts;
import org.polarsys.capella.test.refinement.ju.testcases.itfdecomposition.MultiPartTCWithUsageAmbiguity;
import org.polarsys.capella.test.refinement.ju.testcases.itfdecomposition.MultiPartTCWithUsageAmbiguityThroughPorts;
import org.polarsys.capella.test.refinement.ju.testcases.itfdecomposition.MultiPartTCWithoutAmbiguity;
import org.polarsys.capella.test.refinement.ju.testcases.itfdecomposition.MultiPartTCWithoutAmbiguityThroughPorts;
import org.polarsys.capella.test.refinement.ju.testcases.itfdecomposition.SimpleTCWithBothAmbiguity;
import org.polarsys.capella.test.refinement.ju.testcases.itfdecomposition.SimpleTCWithBothAmbiguityThroughPorts;
import org.polarsys.capella.test.refinement.ju.testcases.itfdecomposition.SimpleTCWithImplementationAmbiguity;
import org.polarsys.capella.test.refinement.ju.testcases.itfdecomposition.SimpleTCWithImplementationAmbiguityThroughPorts;
import org.polarsys.capella.test.refinement.ju.testcases.itfdecomposition.SimpleTCWithUsageAmbiguity;
import org.polarsys.capella.test.refinement.ju.testcases.itfdecomposition.SimpleTCWithUsageAmbiguityThroughPorts;
import org.polarsys.capella.test.refinement.ju.testcases.itfdecomposition.SimpleTCWithoutAmbiguity;
import org.polarsys.capella.test.refinement.ju.testcases.itfdecomposition.SimpleTCWithoutAmbiguityThroughPorts;

/**
 * Test suite in order to test the refinement in "stand-alone" mode e.g.
 *
 */
public class InterfaceDecompositionRefinementTestSuite extends BasicTestSuite {

  /**
   * @see org.polarsys.capella.test.common.AbstractExtendedTestSuite#getTests()
   */
  @Override
  protected List<BasicTestArtefact> getTests() {
    List<BasicTestArtefact> tests = new ArrayList<BasicTestArtefact>();

    // Simple TCs
    tests.add(new SimpleTCWithoutAmbiguity());
    tests.add(new SimpleTCWithUsageAmbiguity());
    tests.add(new SimpleTCWithImplementationAmbiguity());
    tests.add(new SimpleTCWithBothAmbiguity());

    tests.add(new SimpleTCWithoutAmbiguityThroughPorts());
    tests.add(new SimpleTCWithUsageAmbiguityThroughPorts());
    tests.add(new SimpleTCWithImplementationAmbiguityThroughPorts());
    tests.add(new SimpleTCWithBothAmbiguityThroughPorts());

    // MultiPart TCs
    tests.add(new MultiPartTCWithoutAmbiguity());
    tests.add(new MultiPartTCWithUsageAmbiguity());
    tests.add(new MultiPartTCWithImplementationAmbiguity());
    tests.add(new MultiPartTCWithBothAmbiguity());

    tests.add(new MultiPartTCWithoutAmbiguityThroughPorts());
    tests.add(new MultiPartTCWithUsageAmbiguityThroughPorts());
    tests.add(new MultiPartTCWithImplementationAmbiguityThroughPorts());
    tests.add(new MultiPartTCWithBothAmbiguityThroughPorts());

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
    return new InterfaceDecompositionRefinementTestSuite();
  }
}
