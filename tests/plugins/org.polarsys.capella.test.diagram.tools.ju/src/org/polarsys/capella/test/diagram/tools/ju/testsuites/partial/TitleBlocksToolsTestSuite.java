/*******************************************************************************
 * Copyright (c) 2020 THALES GLOBAL SERVICES.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *  
 * Contributors:
 *    Thales - initial API and implementation
 *******************************************************************************/
package org.polarsys.capella.test.diagram.tools.ju.testsuites.partial;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.polarsys.capella.test.diagram.tools.ju.tb.LA_xABTitleBlockTestCase;
import org.polarsys.capella.test.diagram.tools.ju.tb.LA_xDFBTitleBlockTestCase;
import org.polarsys.capella.test.diagram.tools.ju.tb.OA_xABTitleBlockTestCase;
import org.polarsys.capella.test.diagram.tools.ju.tb.OA_xDFBTitleBlockTestCase;
import org.polarsys.capella.test.diagram.tools.ju.tb.PA_xABTitleBlockTestCase;
import org.polarsys.capella.test.diagram.tools.ju.tb.PA_xDFBTitleBlockTestCase;
import org.polarsys.capella.test.diagram.tools.ju.tb.SA_CDBTitleBlockTestCase;
import org.polarsys.capella.test.diagram.tools.ju.tb.SA_xABTitleBlockTestCase;
import org.polarsys.capella.test.diagram.tools.ju.tb.SA_xDFBTitleBlockTestCase;
import org.polarsys.capella.test.diagram.tools.ju.tb.TitleBlockPreferencePageTest;
import org.polarsys.capella.test.framework.api.BasicTestArtefact;
import org.polarsys.capella.test.framework.api.BasicTestSuite;

import junit.framework.Test;

public class TitleBlocksToolsTestSuite extends BasicTestSuite {

  private static final String TITLE_BLOCK_MODEL = "TitleBlocksModel";

  /**
   * Returns the suite. This is required to unary launch this test.
   */
  public static Test suite() {
    return new TitleBlocksToolsTestSuite();
  }

  @Override
  public List<String> getRequiredTestModels() {
    return Arrays.asList(TITLE_BLOCK_MODEL);
  }

  /**
   * @see org.polarsys.capella.test.framework.api.BasicTestSuite#getTests()
   */
  @Override
  protected List<BasicTestArtefact> getTests() {
    List<BasicTestArtefact> tests = new ArrayList<BasicTestArtefact>();
    tests.add(new LA_xABTitleBlockTestCase());
    tests.add(new LA_xDFBTitleBlockTestCase());
    tests.add(new OA_xABTitleBlockTestCase());
    tests.add(new OA_xDFBTitleBlockTestCase());
    tests.add(new PA_xABTitleBlockTestCase());
    tests.add(new PA_xDFBTitleBlockTestCase());
    tests.add(new SA_CDBTitleBlockTestCase());
    tests.add(new SA_xABTitleBlockTestCase());
    tests.add(new SA_xDFBTitleBlockTestCase());
    tests.add(new TitleBlockPreferencePageTest());

    return tests;
  }
}
