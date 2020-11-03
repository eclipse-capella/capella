/*******************************************************************************
 * Copyright (c) 2019, 2020 THALES GLOBAL SERVICES.
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
package org.polarsys.capella.test.diagram.filters.ju.cii;

import java.util.ArrayList;
import java.util.List;

import org.polarsys.capella.test.framework.api.BasicTestArtefact;
import org.polarsys.capella.test.framework.api.BasicTestSuite;

public class CIIDiagramFiltersTestSuite extends BasicTestSuite {

  @Override
  protected List<BasicTestArtefact> getTests() {
    List<BasicTestArtefact> tests = new ArrayList<>();
    tests.add(new HideInterfacesForCII());
    // tests.add(new HideSubLinksWithInterfacesForCII());
    // tests.add(new HideSuperLinksWithInterfacesForCII());
    tests.add(new HideExchangeItemsForCII());
    tests.add(new HideExchangeItemAllocationForCII());
    tests.add(new HideComponentPortsForCII());
    tests.add(new HideUseLinksForCII());
    tests.add(new HideImplementationLinksForCII());
    tests.add(new HideProvideLinksForCII());
    tests.add(new HideRequireLinksForCII());
    tests.add(new HideCommunicationLinksForCII());
    tests.add(new HideGeneralizationLinksForCII());
    tests.add(new HidePortDelegationsForCII());
    tests.add(new HideSimplifiedComponentInteractionsForCII());
    tests.add(new HideTechnicalInterfacesForCII());
    // tests.add(new HideDelegatedCommunicationLinksForCII());
    tests.add(new HideDelegatedUseImplementationRequireProvideLinksForCII());
    tests.add(new HidePropertyValuesForCII());

    return tests;
  }
}
