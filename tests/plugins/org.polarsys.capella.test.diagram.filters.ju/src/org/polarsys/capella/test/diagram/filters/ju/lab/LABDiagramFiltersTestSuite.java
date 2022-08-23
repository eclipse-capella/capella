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
package org.polarsys.capella.test.diagram.filters.ju.lab;

import java.util.ArrayList;
import java.util.List;

import org.polarsys.capella.test.framework.api.BasicTestArtefact;
import org.polarsys.capella.test.framework.api.BasicTestSuite;

public class LABDiagramFiltersTestSuite extends BasicTestSuite {

  @Override
  protected List<BasicTestArtefact> getTests() {
    List<BasicTestArtefact> tests = new ArrayList<>();
    tests.add(new CollapseComponentPortsForLAB());
    tests.add(new CollapseFunctionPortsForLAB());
    tests.add(new HideAllocatedFunctionalExchangesForLAB());
    tests.add(new HideAllocatedFunctionPortsForLAB());
    tests.add(new HideComponentExchangesForLAB());
    tests.add(new HideComponentExchangesNamesForLAB());
    tests.add(new HideComponentPortsWithoutExchangesForLAB());
    tests.add(new HideFunctionalExchangesForLAB());
    tests.add(new HideFunctionalExchangesNamesForLAB());
    tests.add(new HideFunctionPortsWithoutExchangesForLAB());
    tests.add(new HideFunctionsForLAB());
    tests.add(new HidePhysicalLinksNamesForLAB());
    tests.add(new HideComputedComponentExchangesForLAB());
    tests.add(new HidePortAllocationsForLAB());
    tests.add(new HidePortDelegationsForLAB());
    tests.add(new HidePropertyValuesForLAB());
    tests.add(new HideSequencingInformationForLAB());
    tests.add(new HideSimplifiedDiagramBasedComponentExchangesForLAB());
    tests.add(new HideSimplifiedGroupedComponentExchangesForLAB());
    tests.add(new HideSimplifiedOrientedGroupedComponentExchangesForLAB());
    tests.add(new ShowAllocatedFunctionalExchangesOnComponentExchangesForLAB());
    tests.add(new ShowExchangeItemsOnComponentExchangesForLAB());
    tests.add(new ShowExchangeItemsOnComponentExchangeWithoutFunctionalExchangesForLAB());
    tests.add(new ShowEI1EI2ForLAB());
    tests.add(new ShowEI1EI2WithParametersForLAB());
    tests.add(new ShowFunctionalExchangeE1E2ForLAB());
    tests.add(new ShowFunctionalExchangeWithParametersForLAB());
    tests.add(new ShowFunctionalExchangeWithTypesForLAB());

    return tests;
  }

}
