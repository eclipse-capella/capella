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
package org.polarsys.capella.test.diagram.filters.ju.xab;

import java.util.ArrayList;
import java.util.List;

import org.polarsys.capella.test.framework.api.BasicTestArtefact;
import org.polarsys.capella.test.framework.api.BasicTestSuite;

public class XABDiagramFiltersTestSuite extends BasicTestSuite {

  @Override
  protected List<BasicTestArtefact> getTests() {
    List<BasicTestArtefact> tests = new ArrayList<>();
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
    tests.add(new HideByDefaultSequencingInformationOAB());
    tests.add(new HideByDefaultSequencingInformationSAB());
    tests.add(new HideByDefaultSequencingInformationLAB());
    tests.add(new HideByDefaultSequencingInformationPAB());
    return tests;
  }
}
