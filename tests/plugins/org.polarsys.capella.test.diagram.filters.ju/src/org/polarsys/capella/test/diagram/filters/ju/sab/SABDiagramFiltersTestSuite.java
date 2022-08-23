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
package org.polarsys.capella.test.diagram.filters.ju.sab;

import java.util.ArrayList;
import java.util.List;

import org.polarsys.capella.test.framework.api.BasicTestArtefact;
import org.polarsys.capella.test.framework.api.BasicTestSuite;

public class SABDiagramFiltersTestSuite extends BasicTestSuite {

  @Override
  protected List<BasicTestArtefact> getTests() {
    List<BasicTestArtefact> tests = new ArrayList<>();
    tests.add(new CollapseComponentPortsForSAB());
    tests.add(new CollapseFunctionPortsForSAB());
    tests.add(new HideAllocatedFunctionalExchangesForSAB());
    tests.add(new HideAllocatedFunctionPortsForSAB());
    tests.add(new HideComponentExchangesForSAB());
    tests.add(new HideComponentExchangesNamesForSAB());
    tests.add(new HideComponentPortsWithoutExchangesForSAB());
    // tests.add(new HideCrossFunctionalExchangesOfReusableComponentsForSAB());
    tests.add(new HideFunctionalExchangesForSAB());
    tests.add(new HideFunctionalExchangesNamesForSAB());
    tests.add(new HideFunctionPortsWithoutExchangesForSAB());
    tests.add(new HideFunctionsForSAB());
    tests.add(new HidePhysicalLinksNamesForSAB());
    tests.add(new HidePortAllocationsForSAB());
    tests.add(new HidePropertyValuesForSAB());
    tests.add(new HideSequencingInformationForSAB());
    tests.add(new HideSimplifiedGroupedComponentExchangesForSAB());
    tests.add(new HideSimplifiedOrientedGroupedComponentExchangesForSAB());
    tests.add(new ShowAllocatedFunctionalExchangesOnComponentExchangesForSAB());
    tests.add(new ShowExchangeItemsOnComponentExchangesForSAB());
    tests.add(new ShowExchangeItemsOnComponentExchangeWithoutFunctionalExchangesForSAB());
    tests.add(new ShowEI1EI2ForSAB());
    tests.add(new ShowEI1EI2WithParametersForSAB());
    tests.add(new ShowFunctionalExchangeE1E2ForSAB());
    tests.add(new ShowFunctionalExchangeWithParametersForSAB());
    tests.add(new ShowFunctionalExchangeWithTypesForSAB());
    return tests;
  }

}
