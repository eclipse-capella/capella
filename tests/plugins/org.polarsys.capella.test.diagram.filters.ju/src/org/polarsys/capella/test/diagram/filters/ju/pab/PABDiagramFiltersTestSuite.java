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
package org.polarsys.capella.test.diagram.filters.ju.pab;

import java.util.ArrayList;
import java.util.List;

import org.polarsys.capella.test.framework.api.BasicTestArtefact;
import org.polarsys.capella.test.framework.api.BasicTestSuite;

public class PABDiagramFiltersTestSuite extends BasicTestSuite {

  @Override
  protected List<BasicTestArtefact> getTests() {
    List<BasicTestArtefact> tests = new ArrayList<>();
    tests.add(new CollapseComponentPhysicalPortsForPAB());
    tests.add(new CollapseComponentPortsForPAB());
    tests.add(new CollapseFunctionPortsForPAB());
    tests.add(new HideAllocatedFunctionalExchangesForPAB());
    tests.add(new HideAllocatedFunctionPortsForPAB());
    tests.add(new HideBehaviourPcsForPAB());
    tests.add(new HideComponentExchangesForPAB());
    tests.add(new HideComponentExchangesNamesForPAB());
    tests.add(new HideComponentPortAllocationsForPAB());
    tests.add(new HideComponentPortsWithoutExchangesForPAB());
    tests.add(new HideComputedComponentExchangesForPAB());
    tests.add(new HideDeployedPCsForPAB());
    tests.add(new HideComputedPhysicaLinksForPAB());
    tests.add(new HideFunctionalExchangesForPAB());
    tests.add(new HideFunctionalExchangesNamesForPAB());
    tests.add(new HideFunctionPortsWithoutExchangesForPAB());
    tests.add(new HideFunctionsForPAB());
    tests.add(new HideNodePCs());
    tests.add(new HidePhysicalActors());
    tests.add(new HidePhysicalLinksForPAB());
    tests.add(new HidePhysicalLinksNamesForPAB());
    tests.add(new HidePhysicalPortsWithoutLinksForPAB());
    tests.add(new HidePortAllocationsForPAB());
    tests.add(new HidePortDelegationsForPAB());
    tests.add(new HidePropertyValuesForPAB());
    tests.add(new HideSequencingInformationForPAB());
    tests.add(new HideSimplifiedDiagramBasedComponentExchangesForPAB());
    tests.add(new ShowAllocatedFunctionalExchangesOnComponentExchangesForPAB());
    tests.add(new ShowExchangeItemsOnComponentExchangesForPAB());
    tests.add(new ShowExchangeItemsOnComponentExchangeWithoutFunctionalExchangesForPAB());
    tests.add(new ShowEI1EI2ForPAB());
    tests.add(new ShowEI1EI2WithParametersForPAB());
    tests.add(new ShowFunctionalExchangeE1E2ForPAB());
    tests.add(new ShowFunctionalExchangeWithParametersForPAB());
    tests.add(new ShowFunctionalExchangeWithTypesForPAB());

    return tests;
  }

}
