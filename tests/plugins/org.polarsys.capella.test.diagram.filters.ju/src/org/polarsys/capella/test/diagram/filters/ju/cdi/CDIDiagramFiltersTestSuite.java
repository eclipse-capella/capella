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
package org.polarsys.capella.test.diagram.filters.ju.cdi;

import java.util.ArrayList;
import java.util.List;

import org.polarsys.capella.test.framework.api.BasicTestArtefact;
import org.polarsys.capella.test.framework.api.BasicTestSuite;

public class CDIDiagramFiltersTestSuite extends BasicTestSuite {

  @Override
  protected List<BasicTestArtefact> getTests() {
    List<BasicTestArtefact> tests = new ArrayList<>();
    tests.add(new HideInterfaceContentsForCDI());
    tests.add(new HideInterfacesForCDI());
    tests.add(new HideExchangeItemsDetailsInInterfacesForCDI());
    tests.add(new HideExchangeItemElementsForCDI());
    tests.add(new HideExchangeItemsForCDI());
    tests.add(new HideComponentPortsForCDI());
    tests.add(new HideUseLinksForCDI());
    tests.add(new HideImplementationLinksForCDI());
    tests.add(new HideProvideLinksForCDI());
    tests.add(new HideRequireLinksForCDI());
    tests.add(new HideCommunicationLinksForCDI());
    tests.add(new HideGeneralizationLinksForCDI());
    tests.add(new HideTechnicalInterfacesForCDI());
    tests.add(new ShowModifiersForCDI());
    tests.add(new HidePropertyValuesForCDI());
    return tests;
  }
}
