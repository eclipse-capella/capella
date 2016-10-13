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
package org.polarsys.capella.test.refinement.ju.testsuites.partial;

import java.util.ArrayList;
import java.util.List;

import junit.framework.Test;

import org.polarsys.capella.test.framework.api.BasicTestArtefact;
import org.polarsys.capella.test.framework.api.BasicTestSuite;
import org.polarsys.capella.test.refinement.ju.testcases.simple.ComplexTC1;
import org.polarsys.capella.test.refinement.ju.testcases.simple.ComplexTC2;
import org.polarsys.capella.test.refinement.ju.testcases.simple.ComplexTC3;
import org.polarsys.capella.test.refinement.ju.testcases.simple.MultiPartTCWithBothAmbiguity;
import org.polarsys.capella.test.refinement.ju.testcases.simple.MultiPartTCWithBothAmbiguityThroughPorts;
import org.polarsys.capella.test.refinement.ju.testcases.simple.MultiPartTCWithImplementationAmbiguity;
import org.polarsys.capella.test.refinement.ju.testcases.simple.MultiPartTCWithImplementationAmbiguityThroughPorts;
import org.polarsys.capella.test.refinement.ju.testcases.simple.MultiPartTCWithInterfaceInheritance;
import org.polarsys.capella.test.refinement.ju.testcases.simple.MultiPartTCWithInterfaceInheritanceThroughPorts;
import org.polarsys.capella.test.refinement.ju.testcases.simple.MultiPartTCWithReflexiveMessage;
import org.polarsys.capella.test.refinement.ju.testcases.simple.MultiPartTCWithReflexiveMessageThroughPorts;
import org.polarsys.capella.test.refinement.ju.testcases.simple.MultiPartTCWithUsageAmbiguity;
import org.polarsys.capella.test.refinement.ju.testcases.simple.MultiPartTCWithUsageAmbiguityThroughPorts;
import org.polarsys.capella.test.refinement.ju.testcases.simple.MultiPartTCWithoutAmbiguity;
import org.polarsys.capella.test.refinement.ju.testcases.simple.MultiPartTCWithoutAmbiguityThroughPorts;
import org.polarsys.capella.test.refinement.ju.testcases.simple.SimpleTCWithBothAmbiguity;
import org.polarsys.capella.test.refinement.ju.testcases.simple.SimpleTCWithBothAmbiguityThroughPorts;
import org.polarsys.capella.test.refinement.ju.testcases.simple.SimpleTCWithComponentReuse1;
import org.polarsys.capella.test.refinement.ju.testcases.simple.SimpleTCWithComponentReuse2;
import org.polarsys.capella.test.refinement.ju.testcases.simple.SimpleTCWithComponentReuse3;
import org.polarsys.capella.test.refinement.ju.testcases.simple.SimpleTCWithCreateAndDeleteMessages;
import org.polarsys.capella.test.refinement.ju.testcases.simple.SimpleTCWithImplementationAmbiguity;
import org.polarsys.capella.test.refinement.ju.testcases.simple.SimpleTCWithImplementationAmbiguityThroughPorts;
import org.polarsys.capella.test.refinement.ju.testcases.simple.SimpleTCWithInterfaceInheritance;
import org.polarsys.capella.test.refinement.ju.testcases.simple.SimpleTCWithInterfaceInheritanceThroughPorts;
import org.polarsys.capella.test.refinement.ju.testcases.simple.SimpleTCWithLogicalDecomposition_LC1;
import org.polarsys.capella.test.refinement.ju.testcases.simple.SimpleTCWithLogicalDecomposition_LC2;
import org.polarsys.capella.test.refinement.ju.testcases.simple.SimpleTCWithReflexiveMessage;
import org.polarsys.capella.test.refinement.ju.testcases.simple.SimpleTCWithReflexiveMessageThroughPorts;
import org.polarsys.capella.test.refinement.ju.testcases.simple.SimpleTCWithUndefinedInvokedOperation;
import org.polarsys.capella.test.refinement.ju.testcases.simple.SimpleTCWithUndefinedPartType;
import org.polarsys.capella.test.refinement.ju.testcases.simple.SimpleTCWithUndefinedRepresentedInstance;
import org.polarsys.capella.test.refinement.ju.testcases.simple.SimpleTCWithUsageAmbiguity;
import org.polarsys.capella.test.refinement.ju.testcases.simple.SimpleTCWithUsageAmbiguityThroughPorts;
import org.polarsys.capella.test.refinement.ju.testcases.simple.SimpleTCWithoutAmbiguity;
import org.polarsys.capella.test.refinement.ju.testcases.simple.SimpleTCWithoutAmbiguityThroughPorts;
import org.polarsys.capella.test.refinement.ju.testcases.simple.Simple_Communication_Mechanisms_TC1a;
import org.polarsys.capella.test.refinement.ju.testcases.simple.Simple_Communication_Mechanisms_TC1b;
import org.polarsys.capella.test.refinement.ju.testcases.simple.Simple_Communication_Mechanisms_TC2a;
import org.polarsys.capella.test.refinement.ju.testcases.simple.Simple_Communication_Mechanisms_TC2b;
import org.polarsys.capella.test.refinement.ju.testcases.simple.RegressionTC1;
import org.polarsys.capella.test.refinement.ju.testcases.simple.RegressionTC2;

/**
 * Test suite in order to test the refinement in "stand-alone" mode e.g.
 */
public class SimpleRefinementTestSuite extends BasicTestSuite {

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
    tests.add(new SimpleTCWithInterfaceInheritance());
    tests.add(new SimpleTCWithReflexiveMessage());

    tests.add(new SimpleTCWithoutAmbiguityThroughPorts());
    tests.add(new SimpleTCWithUsageAmbiguityThroughPorts());
    tests.add(new SimpleTCWithImplementationAmbiguityThroughPorts());
    tests.add(new SimpleTCWithBothAmbiguityThroughPorts());
    tests.add(new SimpleTCWithInterfaceInheritanceThroughPorts());
    tests.add(new SimpleTCWithReflexiveMessageThroughPorts());

    tests.add(new SimpleTCWithCreateAndDeleteMessages());
    tests.add(new SimpleTCWithUndefinedInvokedOperation());
    tests.add(new SimpleTCWithUndefinedRepresentedInstance());
    tests.add(new SimpleTCWithUndefinedPartType());

    // MultiPart TCs
    tests.add(new MultiPartTCWithoutAmbiguity());
    tests.add(new MultiPartTCWithUsageAmbiguity());
    tests.add(new MultiPartTCWithImplementationAmbiguity());
    tests.add(new MultiPartTCWithBothAmbiguity());
    tests.add(new MultiPartTCWithInterfaceInheritance());
    tests.add(new MultiPartTCWithReflexiveMessage());

    tests.add(new MultiPartTCWithoutAmbiguityThroughPorts());
    tests.add(new MultiPartTCWithUsageAmbiguityThroughPorts());
    tests.add(new MultiPartTCWithImplementationAmbiguityThroughPorts());
    tests.add(new MultiPartTCWithBothAmbiguityThroughPorts());
    tests.add(new MultiPartTCWithInterfaceInheritanceThroughPorts());
    tests.add(new MultiPartTCWithReflexiveMessageThroughPorts());

    // Decomposition TC
    tests.add(new SimpleTCWithLogicalDecomposition_LC1());
    tests.add(new SimpleTCWithLogicalDecomposition_LC2());

    // ComponentReuse TC
    tests.add(new SimpleTCWithComponentReuse1());
    tests.add(new SimpleTCWithComponentReuse2());
    tests.add(new SimpleTCWithComponentReuse3());

    // Complex TC
    tests.add(new ComplexTC1());
    tests.add(new ComplexTC2());
    tests.add(new ComplexTC3());

    // regression TC1
    tests.add(new RegressionTC1());
    // regression TC2
    tests.add(new RegressionTC2());
    // Simple Communication Mechanisms TC1a
    tests.add(new Simple_Communication_Mechanisms_TC1a());
    // Simple Communication Mechanisms TC1b
    tests.add(new Simple_Communication_Mechanisms_TC1b());
    // Simple Communication Mechanisms TC2a
    tests.add(new Simple_Communication_Mechanisms_TC2a());
    // Simple Communication Mechanisms TC2b
    tests.add(new Simple_Communication_Mechanisms_TC2b());

    return tests;
  }

  /**
   * Added in order to launch this test suite without the Capella test framework.
   */
  public static Test suite() {
    return new SimpleRefinementTestSuite();
  }
}
