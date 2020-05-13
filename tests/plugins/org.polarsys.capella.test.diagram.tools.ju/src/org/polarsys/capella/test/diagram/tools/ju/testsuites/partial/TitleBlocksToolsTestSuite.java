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

import org.polarsys.capella.test.diagram.tools.ju.tb.LABTitleBlockTestCase;
import org.polarsys.capella.test.diagram.tools.ju.tb.LA_CDBTitleBlockTestCase;
import org.polarsys.capella.test.diagram.tools.ju.tb.LA_IDTitleBlockTestCase;
import org.polarsys.capella.test.diagram.tools.ju.tb.LA_PDTitleBlockTestCase;
import org.polarsys.capella.test.diagram.tools.ju.tb.LDFBTitleBlockTestCase;
import org.polarsys.capella.test.diagram.tools.ju.tb.OABTitleBlockTestCase;
import org.polarsys.capella.test.diagram.tools.ju.tb.OAIBTitleBlockTestCase;
import org.polarsys.capella.test.diagram.tools.ju.tb.OA_CDBTitleBlockTestCase;
import org.polarsys.capella.test.diagram.tools.ju.tb.OA_IDTitleBlockTestCase;
import org.polarsys.capella.test.diagram.tools.ju.tb.OA_PDTitleBlockTestCase;
import org.polarsys.capella.test.diagram.tools.ju.tb.PABTitleBlockTestCase;
import org.polarsys.capella.test.diagram.tools.ju.tb.PA_CDBTitleBlockTestCase;
import org.polarsys.capella.test.diagram.tools.ju.tb.PA_IDTitleBlockTestCase;
import org.polarsys.capella.test.diagram.tools.ju.tb.PA_PDTitleBlockTestCase;
import org.polarsys.capella.test.diagram.tools.ju.tb.PDFBTitleBlockTestCase;
import org.polarsys.capella.test.diagram.tools.ju.tb.SABTitleBlockTestCase;
import org.polarsys.capella.test.diagram.tools.ju.tb.SA_CDBTitleBlockTestCase;
import org.polarsys.capella.test.diagram.tools.ju.tb.SA_CSATitleBlockTestCase;
import org.polarsys.capella.test.diagram.tools.ju.tb.SA_IDTitleBlockTestCase;
import org.polarsys.capella.test.diagram.tools.ju.tb.SA_PDTitleBlockTestCase;
import org.polarsys.capella.test.diagram.tools.ju.tb.SDFBTitleBlockTestCase;
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
    // tests.add(new EPBS_CRBTitleBlockTestCase());
    tests.add(new LA_CDBTitleBlockTestCase());
    // tests.add(new LA_CRBTitleBlockTestCase());
    tests.add(new LA_IDTitleBlockTestCase());
    tests.add(new LA_PDTitleBlockTestCase());
    tests.add(new LABTitleBlockTestCase());
    tests.add(new LDFBTitleBlockTestCase());
    tests.add(new OA_CDBTitleBlockTestCase());
    tests.add(new OA_IDTitleBlockTestCase());
    tests.add(new OA_PDTitleBlockTestCase());
    tests.add(new OABTitleBlockTestCase());
    tests.add(new OAIBTitleBlockTestCase());
    tests.add(new PA_CDBTitleBlockTestCase());
    // tests.add(new PA_CRBTitleBlockTestCase());
    tests.add(new PA_IDTitleBlockTestCase());
    tests.add(new PA_PDTitleBlockTestCase());
    tests.add(new PABTitleBlockTestCase());
    tests.add(new PDFBTitleBlockTestCase());
    tests.add(new SA_PDTitleBlockTestCase());
    tests.add(new SA_CDBTitleBlockTestCase());
    tests.add(new SA_CSATitleBlockTestCase());
    tests.add(new SA_IDTitleBlockTestCase());
    tests.add(new SABTitleBlockTestCase());
    tests.add(new SDFBTitleBlockTestCase());
    tests.add(new TitleBlockPreferencePageTest());

    return tests;
  }
}
