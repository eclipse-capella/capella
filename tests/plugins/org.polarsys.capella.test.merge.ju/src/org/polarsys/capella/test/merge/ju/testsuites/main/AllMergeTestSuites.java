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
package org.polarsys.capella.test.merge.ju.testsuites.main;

import java.util.ArrayList;
import java.util.List;

import junit.framework.Test;

import org.polarsys.capella.test.framework.api.BasicTestArtefact;
import org.polarsys.capella.test.framework.api.BasicTestSuite;
import org.polarsys.capella.test.merge.ju.testsuites.partial.DanglingMergeLinkTestSuite;
import org.polarsys.capella.test.merge.ju.testsuites.partial.MergerOtherTestSuite;
import org.polarsys.capella.test.merge.ju.testsuites.partial.MergerTestSuite;
import org.polarsys.capella.test.merge.ju.testsuites.partial.MergerUML2TestSuite;

/**
 */
public class AllMergeTestSuites extends BasicTestSuite {

  /**
   * @see org.polarsys.capella.test.common.AbstractExtendedTestSuite#getTests()
   */
  @Override
  protected List<BasicTestArtefact> getTests() {
    List<BasicTestArtefact> list = new ArrayList<BasicTestArtefact>();

    list.add(new MergerTestSuite());
    list.add(new DanglingMergeLinkTestSuite());
    list.add(new MergerUML2TestSuite());
    list.add(new MergerOtherTestSuite());
    
    return list;
  }

  /**
   * Added in order to launch this test suite without the Capella test framework.
   * @return
   */
  public static Test suite() {
    return new AllMergeTestSuites();
  }

}
