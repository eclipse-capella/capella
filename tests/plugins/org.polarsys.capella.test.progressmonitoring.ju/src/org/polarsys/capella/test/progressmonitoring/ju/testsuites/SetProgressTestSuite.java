/*******************************************************************************
 * Copyright (c) 2006, 2019 THALES GLOBAL SERVICES.
 * 
 * This program and the accompanying materials are made available under the
 * terms of the Eclipse Public License 2.0 which is available at
 * http://www.eclipse.org/legal/epl-2.0
 * 
 * SPDX-License-Identifier: EPL-2.0
 * 
 * Contributors:
 *    Thales - initial API and implementation
 *******************************************************************************/
package org.polarsys.capella.test.progressmonitoring.ju.testsuites;

import java.util.ArrayList;
import java.util.List;

import junit.framework.Test;

import org.polarsys.capella.test.framework.api.BasicTestArtefact;
import org.polarsys.capella.test.framework.api.BasicTestSuite;
import org.polarsys.capella.test.progressmonitoring.ju.testcases.MetricTest;
import org.polarsys.capella.test.progressmonitoring.ju.testcases.SetProgressClearStatusTest;
import org.polarsys.capella.test.progressmonitoring.ju.testcases.SetProgressManySelectionTest;
import org.polarsys.capella.test.progressmonitoring.ju.testcases.SetProgressPropagateToAllElementsTest;
import org.polarsys.capella.test.progressmonitoring.ju.testcases.SetProgressPropagateToRepresentationsTest;
import org.polarsys.capella.test.progressmonitoring.ju.testcases.SetProgressSingleSelectionTest;

public class SetProgressTestSuite extends BasicTestSuite {

  /**
   * Returns the suite. This is required to unary launch this test.
   */
  public static Test suite() {
    return new SetProgressTestSuite();
  }

  @Override
  protected List<BasicTestArtefact> getTests() {
    List<BasicTestArtefact> tests = new ArrayList<BasicTestArtefact>();
    tests.add(new SetProgressSingleSelectionTest());
    tests.add(new SetProgressManySelectionTest());
    tests.add(new SetProgressClearStatusTest());
    tests.add(new SetProgressPropagateToAllElementsTest());
    tests.add(new SetProgressPropagateToRepresentationsTest());
    tests.add(new MetricTest());
    return tests;
  }

}
