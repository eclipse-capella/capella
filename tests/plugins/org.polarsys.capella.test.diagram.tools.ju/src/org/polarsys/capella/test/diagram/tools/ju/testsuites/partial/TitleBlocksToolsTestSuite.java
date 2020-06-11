/*******************************************************************************
 * Copyright (c) 2020 THALES GLOBAL SERVICES.
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
package org.polarsys.capella.test.diagram.tools.ju.testsuites.partial;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.polarsys.capella.test.diagram.tools.ju.tb.CDBTitleBlockTestCase;
import org.polarsys.capella.test.diagram.tools.ju.tb.TitleBlockCopyPasteTest;
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
    tests.add(new CDBTitleBlockTestCase());
    tests.add(new TitleBlockPreferencePageTest());
    tests.add(new TitleBlockCopyPasteTest());

    return tests;
  }
}
