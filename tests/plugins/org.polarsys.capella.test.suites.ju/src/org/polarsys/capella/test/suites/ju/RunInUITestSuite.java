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
package org.polarsys.capella.test.suites.ju;

import java.util.ArrayList;
import java.util.List;

import org.polarsys.capella.test.business.queries.ju.testSuites.main.BusinessQueryTestSuite;
import org.polarsys.capella.test.diagram.filters.ju.testsuites.DiagramFiltersTestSuite;
import org.polarsys.capella.test.diagram.misc.ju.testsuites.DiagramMiscTestSuite;
import org.polarsys.capella.test.diagram.tools.ju.testsuites.main.DiagramToolsTestSuite;
import org.polarsys.capella.test.explorer.activity.ju.testsuites.ActivityExplorerTestsSuite;
import org.polarsys.capella.test.fastlinker.ju.testsuites.FastLinkerTestsSuite;
import org.polarsys.capella.test.framework.api.BasicTestArtefact;
import org.polarsys.capella.test.framework.api.BasicTestSuite;
import org.polarsys.capella.test.libraries.ju.testsuites.main.LibrariesTestSuite;
import org.polarsys.capella.test.meta.ju.testSuites.MetaTestSuite;
import org.polarsys.capella.test.model.ju.testsuites.main.ModelTestSuite;
import org.polarsys.capella.test.progressmonitoring.ju.testsuites.SetProgressTestSuite;
import org.polarsys.capella.test.recrpl.ju.testsuites.main.RecRplTestSuite;
import org.polarsys.capella.test.refinement.ju.testsuites.main.AllRefinementTestSuites;
import org.polarsys.capella.test.transition.ju.testsuites.main.TransitionTestSuite;
import org.polarsys.capella.test.validation.rules.ju.testsuites.main.ValidationRulesTestSuite;

import junit.framework.Test;

/**
 *
 */
public class RunInUITestSuite extends BasicTestSuite {

  /**
   * Returns the suite. This is required to unary launch this test.
   */
  public static Test suite() {
    return new RunInUITestSuite();
  }

  @Override
  protected List<BasicTestArtefact> getTests() {
    List<BasicTestArtefact> tests = new ArrayList<BasicTestArtefact>();
    tests.add(new BusinessQueryTestSuite());
    tests.add(new ValidationRulesTestSuite());
    tests.add(new ModelTestSuite());
    tests.add(new LibrariesTestSuite());
    tests.add(new AllRefinementTestSuites());
    tests.add(new TransitionTestSuite());
    tests.add(new RecRplTestSuite());
    tests.add(new FastLinkerTestsSuite());
    tests.add(new ActivityExplorerTestsSuite());
    tests.add(new DiagramToolsTestSuite());
    tests.add(new DiagramFiltersTestSuite());
    tests.add(new MetaTestSuite());
    tests.add(new SetProgressTestSuite());
    tests.add(new DiagramMiscTestSuite());
    return tests;
  }

  @Override
  public List<String> getRequiredTestModels() {
    return null;
  }
}
