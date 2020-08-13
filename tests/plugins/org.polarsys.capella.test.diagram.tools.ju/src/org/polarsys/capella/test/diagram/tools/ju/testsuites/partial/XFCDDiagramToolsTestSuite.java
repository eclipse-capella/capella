/*******************************************************************************
 * Copyright (c) 2016, 2019 THALES GLOBAL SERVICES.
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

import org.polarsys.capella.test.diagram.tools.ju.xfcd.AccelerateOnFunctionalChainInvolvementLinkTest;
import org.polarsys.capella.test.diagram.tools.ju.xfcd.AccelerateOnSequenceLink;
import org.polarsys.capella.test.diagram.tools.ju.xfcd.AssociateSequenceLinkWithExchangeTest;
import org.polarsys.capella.test.diagram.tools.ju.xfcd.CreateConstructControlNodeTest;
import org.polarsys.capella.test.diagram.tools.ju.xfcd.CreateControlNodeTest;
import org.polarsys.capella.test.diagram.tools.ju.xfcd.CreateExchangeWithSequenceLinkTest;
import org.polarsys.capella.test.diagram.tools.ju.xfcd.CreateFunctionOnSequenceLinkTest;
import org.polarsys.capella.test.diagram.tools.ju.xfcd.CreateSequenceLinkTest;
import org.polarsys.capella.test.diagram.tools.ju.xfcd.DragAndDropTest;
import org.polarsys.capella.test.diagram.tools.ju.xfcd.InsertExchangeCycleDetection;
import org.polarsys.capella.test.diagram.tools.ju.xfcd.MaxHierarchyLevel0TestOA;
import org.polarsys.capella.test.diagram.tools.ju.xfcd.MaxHierarchyLevel0TestSA;
import org.polarsys.capella.test.diagram.tools.ju.xfcd.MaxHierarchyLevel1TestOA;
import org.polarsys.capella.test.diagram.tools.ju.xfcd.MaxHierarchyLevel1TestSA;
import org.polarsys.capella.test.diagram.tools.ju.xfcd.MaxHierarchyLevel2TestOA;
import org.polarsys.capella.test.diagram.tools.ju.xfcd.MaxHierarchyLevel2TestSA;
import org.polarsys.capella.test.diagram.tools.ju.xfcd.utils.LA_Settings;
import org.polarsys.capella.test.diagram.tools.ju.xfcd.utils.OA_Settings;
import org.polarsys.capella.test.diagram.tools.ju.xfcd.utils.SA_Settings;
import org.polarsys.capella.test.framework.api.BasicTestArtefact;
import org.polarsys.capella.test.framework.api.BasicTestSuite;

import junit.framework.Test;

public class XFCDDiagramToolsTestSuite extends BasicTestSuite {

  /**
   * Returns the suite. This is required to unary launch this test.
   */
  public static Test suite() {
    return new XFCDDiagramToolsTestSuite();
  }

  @Override
  public List<String> getRequiredTestModels() {
    return Arrays.asList("FunctionalChains");
  }

  /**
   * @see org.polarsys.capella.test.framework.api.BasicTestSuite#getTests()
   */
  @Override
  protected List<BasicTestArtefact> getTests() {
    List<BasicTestArtefact> tests = new ArrayList<>();

    tests.add(new AccelerateOnFunctionalChainInvolvementLinkTest());
    tests.add(new AccelerateOnSequenceLink());

    tests.add(new MaxHierarchyLevel0TestOA());
    tests.add(new MaxHierarchyLevel1TestOA());
    tests.add(new MaxHierarchyLevel2TestOA());

    tests.add(new MaxHierarchyLevel0TestSA());
    tests.add(new MaxHierarchyLevel1TestSA());
    tests.add(new MaxHierarchyLevel2TestSA());

    tests.add(new InsertExchangeCycleDetection());

    tests.add(new CreateControlNodeTest(new OA_Settings()));
    tests.add(new CreateControlNodeTest(new SA_Settings()));
    tests.add(new CreateControlNodeTest(new LA_Settings()));
    tests.add(new CreateConstructControlNodeTest(new OA_Settings()));
    tests.add(new CreateConstructControlNodeTest(new LA_Settings()));
    tests.add(new CreateConstructControlNodeTest(new SA_Settings()));
    tests.add(new CreateSequenceLinkTest(new OA_Settings()));
    tests.add(new CreateSequenceLinkTest(new SA_Settings()));
    tests.add(new CreateSequenceLinkTest(new LA_Settings()));
    tests.add(new CreateFunctionOnSequenceLinkTest(new OA_Settings()));
    tests.add(new CreateFunctionOnSequenceLinkTest(new SA_Settings()));
    tests.add(new CreateFunctionOnSequenceLinkTest(new LA_Settings()));
    tests.add(new CreateExchangeWithSequenceLinkTest(new OA_Settings()));
    tests.add(new CreateExchangeWithSequenceLinkTest(new SA_Settings()));
    tests.add(new CreateExchangeWithSequenceLinkTest(new LA_Settings()));
    tests.add(new AssociateSequenceLinkWithExchangeTest(new OA_Settings()));
    tests.add(new AssociateSequenceLinkWithExchangeTest(new SA_Settings()));
    tests.add(new AssociateSequenceLinkWithExchangeTest(new LA_Settings()));

    tests.add(new DragAndDropTest(new OA_Settings()));
    tests.add(new DragAndDropTest(new SA_Settings()));

    return tests;
  }

}
