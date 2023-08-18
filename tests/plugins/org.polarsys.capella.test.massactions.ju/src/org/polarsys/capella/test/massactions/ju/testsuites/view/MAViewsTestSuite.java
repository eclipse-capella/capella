/*******************************************************************************
 * Copyright (c) 2018, 2022 THALES GLOBAL SERVICES.
 * 
 * This program and the accompanying materials are made available under the
 * terms of the Eclipse Public License 2.0 which is available at
 * http://www.eclipse.org/legal/epl-2.0
 * 
 * SPDX-License-Identifier: EPL-2.0
 *
 * Contributors:
 * Thales - initial API and implementation
 *******************************************************************************/
package org.polarsys.capella.test.massactions.ju.testsuites.view;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.polarsys.capella.test.framework.api.BasicTestArtefact;
import org.polarsys.capella.test.framework.api.BasicTestSuite;
import org.polarsys.capella.test.massactions.ju.model.AbstractCapellaMATestCase;
import org.polarsys.capella.test.massactions.ju.testcases.view.shared.AddingElementsTest;
import org.polarsys.capella.test.massactions.ju.testcases.view.shared.CollectionsInitializationTest;
import org.polarsys.capella.test.massactions.ju.testcases.view.shared.ColumnFilterTest;
import org.polarsys.capella.test.massactions.ju.testcases.view.shared.ColumnHideShowTest;
import org.polarsys.capella.test.massactions.ju.testcases.view.shared.ColumnReorderTest;
import org.polarsys.capella.test.massactions.ju.testcases.view.shared.ColumnSortTest;
import org.polarsys.capella.test.massactions.ju.testcases.view.visualize.ColumnGroupByTest;

import junit.framework.Test;

/**
 * A test suite for all of the underlying view mechanics.
 * 
 * @author Sandu Postaru
 *
 */
public class MAViewsTestSuite extends BasicTestSuite {

  public static Test suite() {
    return new MAViewsTestSuite();
  }

  @Override
  public List<String> getRequiredTestModels() {
    return Arrays.asList(AbstractCapellaMATestCase.MODEL_NAME);
  }

  @Override
  protected List<BasicTestArtefact> getTests() {
    List<BasicTestArtefact> testSuite = new ArrayList<>();

    // shared
    testSuite.add(new CollectionsInitializationTest());
    testSuite.add(new AddingElementsTest());
    testSuite.add(new ColumnFilterTest());
    testSuite.add(new ColumnHideShowTest());
    testSuite.add(new ColumnSortTest());
    testSuite.add(new ColumnReorderTest());

    // visualize
    testSuite.add(new ColumnGroupByTest());

    return testSuite;
  }
}
