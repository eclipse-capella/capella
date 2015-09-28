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
package org.polarsys.capella.test.merge.ju.testsuites.partial;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import junit.framework.Test;

import org.polarsys.capella.test.framework.api.BasicTestArtefact;
import org.polarsys.capella.test.framework.api.BasicTestSuite;
import org.polarsys.capella.test.merge.ju.testcases.mergertest.CommunicationMechanismes;
import org.polarsys.capella.test.merge.ju.testcases.mergertest.Dummy_UC;
import org.polarsys.capella.test.merge.ju.testcases.mergertest.Merge1;
import org.polarsys.capella.test.merge.ju.testcases.mergertest.MergePower;
import org.polarsys.capella.test.merge.ju.testcases.mergertest.MultiPartSimple;
import org.polarsys.capella.test.merge.ju.testcases.mergertest.PhysicalLayer;
import org.polarsys.capella.test.merge.ju.testcases.mergertest.ReUseComplex;
import org.polarsys.capella.test.merge.ju.testcases.mergertest.ReUseSimple1;
import org.polarsys.capella.test.merge.ju.testcases.mergertest.ReUseSimple2;
import org.polarsys.capella.test.merge.ju.testcases.mergertest.ReflexiveDecomposedComplex;
import org.polarsys.capella.test.merge.ju.testcases.mergertest.ReflexiveDecomposedDummy;
import org.polarsys.capella.test.merge.ju.testcases.mergertest.ReflexiveDecomposedWithAnchor;
import org.polarsys.capella.test.merge.ju.testcases.mergertest.ReflexiveDummy;
import org.polarsys.capella.test.merge.ju.testcases.mergertest.ReflexiveWithPartialPart;
import org.polarsys.capella.test.merge.ju.testcases.mergertest.WithDummyDec;
import org.polarsys.capella.test.merge.ju.testcases.mergertest.WithEE;
import org.polarsys.capella.test.merge.ju.testcases.mergertest.WithEE2;

/**
 * Test suite in order to test the merger in "stand-alone" mode
 */
public class MergerTestSuite extends BasicTestSuite {
  
  /**
   * Returns the suite. This is required to unary launch this test.
   */
  public static Test suite() {
    return new MergerTestSuite();
  }

  /**
   * @see org.polarsys.capella.test.common.AbstractExtendedTestSuite#getTests()
   */
  @Override
  protected List<BasicTestArtefact> getTests() {
    List<BasicTestArtefact> tests = new ArrayList<BasicTestArtefact>();
    tests.add(new Merge1());
    tests.add(new Dummy_UC());
    tests.add(new WithEE());
    tests.add(new WithEE2());
    tests.add(new WithDummyDec());
    tests.add(new MergePower());
    tests.add(new ReflexiveDummy());
    tests.add(new ReflexiveDecomposedDummy());
    tests.add(new ReflexiveDecomposedComplex());
    tests.add(new ReUseSimple1());
    tests.add(new ReUseSimple2());
    tests.add(new ReUseComplex());
    tests.add(new ReflexiveDecomposedWithAnchor());
    tests.add(new ReflexiveWithPartialPart());
    tests.add(new CommunicationMechanismes());
    tests.add(new MultiPartSimple());
    tests.add(new PhysicalLayer());
    return tests;
  }

  @Override
  public List<String> getRequiredTestModels() {
    return Arrays.asList("mergerTest");
  }
}
