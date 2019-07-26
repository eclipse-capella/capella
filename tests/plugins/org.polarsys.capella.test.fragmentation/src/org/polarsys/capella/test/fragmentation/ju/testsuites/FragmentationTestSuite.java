/*******************************************************************************
 * Copyright (c) 2019 THALES GLOBAL SERVICES.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *  
 * Contributors:
 *    Thales - initial API and implementation
 *******************************************************************************/
package org.polarsys.capella.test.fragmentation.ju.testsuites;

import java.util.ArrayList;
import java.util.List;

import org.polarsys.capella.test.fragmentation.ju.testcases.DummyNonAbusiveFragmentModificationTestCase;
import org.polarsys.capella.test.framework.api.BasicTestArtefact;
import org.polarsys.capella.test.framework.api.BasicTestSuite;

import junit.framework.Test;

public class FragmentationTestSuite extends BasicTestSuite {

  public static Test suite() {
    return new FragmentationTestSuite();
  }

  @Override
  protected List<BasicTestArtefact> getTests() {
    ArrayList<BasicTestArtefact> tests = new ArrayList<BasicTestArtefact>();
    tests.add(new DummyNonAbusiveFragmentModificationTestCase());
    return tests;
  }

}
