/*******************************************************************************
 * Copyright (c) 2019 THALES GLOBAL SERVICES.
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
package org.polarsys.capella.test.benchmarks.ju.suites;

import java.util.ArrayList;
import java.util.List;

import org.polarsys.capella.test.benchmarks.ju.testcases.TearDownTestCase;
import org.polarsys.capella.test.diagram.common.ju.context.DiagramContext;
import org.polarsys.capella.test.framework.api.BasicTestArtefact;
import org.polarsys.capella.test.framework.api.BasicTestSuite;

public class BenchmarkTestSuite extends BasicTestSuite {
  List<DiagramContext> contexts;
  
  @Override
  protected List<BasicTestArtefact> getTests() {
    List<BasicTestArtefact> tests = new ArrayList<BasicTestArtefact>();
    contexts = new ArrayList<>();
    BasicTestArtefact mainTestCase = initMainTestCase(contexts);
    BasicTestArtefact setUpTestCase = initSetUpTestCase(contexts, mainTestCase);
    BasicTestArtefact tearDownTestCase = initTearDownTestCase(mainTestCase);
    tests.add(setUpTestCase);
    tests.add(mainTestCase);
    tests.add(tearDownTestCase);
    return tests;
  }

  protected BasicTestArtefact initTearDownTestCase(BasicTestArtefact mainTestCase) {
    return new TearDownTestCase(mainTestCase);
  }

  protected BasicTestArtefact initSetUpTestCase(List<DiagramContext> contexts, BasicTestArtefact mainTestCase) {
    return null;
  }

  protected BasicTestArtefact initMainTestCase(List<DiagramContext> contexts) {
    return null;
  }
}
