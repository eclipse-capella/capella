/*******************************************************************************
 * Copyright (c) 2006, 2018 THALES GLOBAL SERVICES.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *  
 * Contributors:
 *    Thales - initial API and implementation
 *******************************************************************************/
package org.polarsys.capella.test.diagram.filters.ju.testsuites;

import java.util.ArrayList;
import java.util.List;

import org.polarsys.capella.test.diagram.filters.ju.testcases.HideComputedCEForPABTestCase1;
import org.polarsys.capella.test.diagram.filters.ju.testcases.HideComputedComponentExchangesForLAB;
import org.polarsys.capella.test.diagram.filters.ju.testcases.HideComputedComponentExchangesForPAB;
import org.polarsys.capella.test.diagram.filters.ju.testcases.HideComputedLinksForPABTestCase1;
import org.polarsys.capella.test.diagram.filters.ju.testcases.HideComputedLinksForPABTestCase2;
import org.polarsys.capella.test.diagram.filters.ju.testcases.HideComputedLinksForPABTestCase3;
import org.polarsys.capella.test.diagram.filters.ju.testcases.HideComputedLinksForPABTestCase4;
import org.polarsys.capella.test.diagram.filters.ju.testcases.HideComputedLinksForPABTestCase5;
import org.polarsys.capella.test.diagram.filters.ju.testcases.HideComputedLinksForPABTestCase6;
import org.polarsys.capella.test.diagram.filters.ju.testcases.HideComputedLinksForPABTestCase7;
import org.polarsys.capella.test.diagram.filters.ju.testcases.HideComputedPLForPABTestCase1;
import org.polarsys.capella.test.diagram.filters.ju.testcases.HideComputedPhysicalLinksForPAB;
import org.polarsys.capella.test.diagram.filters.ju.testcases.HideControlNodesForLFBD;
import org.polarsys.capella.test.diagram.filters.ju.testcases.HideControlNodesForPFBD;
import org.polarsys.capella.test.diagram.filters.ju.testcases.HideControlNodesForSFBD;
import org.polarsys.capella.test.diagram.filters.ju.testcases.HideDelegatedCommunicationLinksForCII;
import org.polarsys.capella.test.diagram.filters.ju.testcases.HideDelegatedCommunicationLinksForIDB;
import org.polarsys.capella.test.diagram.filters.ju.testcases.HideDelegatedUseImplementationLinksForCII;
import org.polarsys.capella.test.diagram.filters.ju.testcases.HideDelegatedUseImplementationLinksForIDB;
import org.polarsys.capella.test.diagram.filters.ju.testcases.HideFunctionalExchangesTestCase;
import org.polarsys.capella.test.diagram.filters.ju.testcases.HideTechnicalInterfaceForCDB;
import org.polarsys.capella.test.diagram.filters.ju.testcases.HideTechnicalInterfaceForCDI;
import org.polarsys.capella.test.diagram.filters.ju.testcases.HideTechnicalInterfaceForCEI;
import org.polarsys.capella.test.diagram.filters.ju.testcases.HideTechnicalInterfaceForCII;
import org.polarsys.capella.test.diagram.filters.ju.testcases.HideTechnicalInterfaceForIDB;
import org.polarsys.capella.test.diagram.filters.ju.testcases.ShowExchangeItemsOnInteractions;
import org.polarsys.capella.test.diagram.filters.ju.testcases.ShowExchangeItemsParametersOnInteractions;
import org.polarsys.capella.test.diagram.filters.ju.testcases.ShowInteractionsWithExchangeItems;
import org.polarsys.capella.test.diagram.filters.ju.testcases.ShowTriggerSourceFunctionForMSM;
import org.polarsys.capella.test.framework.api.BasicTestArtefact;
import org.polarsys.capella.test.framework.api.BasicTestSuite;

import junit.framework.Test;

public class DiagramFiltersTestSuite extends BasicTestSuite {

  /**
   * Returns the suite. This is required to unary launch this test.
   */
  public static Test suite() {
    return new DiagramFiltersTestSuite();
  }

  /**
   * @see org.polarsys.capella.test.framework.api.BasicTestSuite#getTests()
   */
  @Override
  protected List<BasicTestArtefact> getTests() {
    List<BasicTestArtefact> tests = new ArrayList<BasicTestArtefact>();
    tests.add(new ShowExchangeItemsOnInteractions());
    tests.add(new ShowInteractionsWithExchangeItems());
    tests.add(new ShowExchangeItemsParametersOnInteractions());
    tests.add(new HideControlNodesForSFBD());
    tests.add(new HideControlNodesForLFBD());
    tests.add(new HideControlNodesForPFBD());
    tests.add(new HideDelegatedCommunicationLinksForCII());
    tests.add(new HideDelegatedCommunicationLinksForIDB());
    tests.add(new HideDelegatedUseImplementationLinksForCII());
    tests.add(new HideDelegatedUseImplementationLinksForIDB());
    tests.add(new HideTechnicalInterfaceForCDB());
    tests.add(new HideTechnicalInterfaceForCDI());
    tests.add(new HideTechnicalInterfaceForCEI());
    tests.add(new HideTechnicalInterfaceForCII());
    tests.add(new HideTechnicalInterfaceForIDB());
    tests.add(new ShowTriggerSourceFunctionForMSM());
    tests.add(new HideComputedComponentExchangesForPAB());
    tests.add(new HideComputedComponentExchangesForLAB());
    tests.add(new HideComputedPhysicalLinksForPAB());
    tests.add(new HideComputedCEForPABTestCase1());
    tests.add(new HideComputedPLForPABTestCase1());
    tests.add(new HideComputedLinksForPABTestCase1());
    tests.add(new HideComputedLinksForPABTestCase2());
    tests.add(new HideComputedLinksForPABTestCase3());
    tests.add(new HideComputedLinksForPABTestCase4());
    tests.add(new HideComputedLinksForPABTestCase5());
    tests.add(new HideComputedLinksForPABTestCase6());
    tests.add(new HideComputedLinksForPABTestCase7());
    tests.add(new HideFunctionalExchangesTestCase());
    return tests;
  }

}
