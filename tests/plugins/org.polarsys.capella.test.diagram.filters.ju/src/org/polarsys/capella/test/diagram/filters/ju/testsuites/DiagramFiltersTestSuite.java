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
package org.polarsys.capella.test.diagram.filters.ju.testsuites;

import java.util.ArrayList;
import java.util.List;

import org.polarsys.capella.test.diagram.filters.ju.testcases.HideControlNodesForLFBD;
import org.polarsys.capella.test.diagram.filters.ju.testcases.HideControlNodesForPFBD;
import org.polarsys.capella.test.diagram.filters.ju.testcases.HideControlNodesForSFBD;
import org.polarsys.capella.test.diagram.filters.ju.testcases.HideDelegatedCommunicationLinksForCII;
import org.polarsys.capella.test.diagram.filters.ju.testcases.HideDelegatedCommunicationLinksForIDB;
import org.polarsys.capella.test.diagram.filters.ju.testcases.HideDelegatedUseImplementationLinksForCII;
import org.polarsys.capella.test.diagram.filters.ju.testcases.HideDelegatedUseImplementationLinksForIDB;
import org.polarsys.capella.test.diagram.filters.ju.testcases.HideTechnicalInterfaceForCDB;
import org.polarsys.capella.test.diagram.filters.ju.testcases.HideTechnicalInterfaceForCDI;
import org.polarsys.capella.test.diagram.filters.ju.testcases.HideTechnicalInterfaceForCEI;
import org.polarsys.capella.test.diagram.filters.ju.testcases.HideTechnicalInterfaceForCII;
import org.polarsys.capella.test.diagram.filters.ju.testcases.HideTechnicalInterfaceForIDB;
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
    return tests;
  }

}
