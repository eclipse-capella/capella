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
package org.polarsys.capella.test.diagram.filters.ju.oab;

import java.util.ArrayList;
import java.util.List;

import org.polarsys.capella.test.diagram.filters.ju.oab.HideDiagramTitleBlocksForOAB;
import org.polarsys.capella.test.diagram.filters.ju.oab.HideElementTitleBlocksForOAB;
import org.polarsys.capella.test.framework.api.BasicTestArtefact;
import org.polarsys.capella.test.framework.api.BasicTestSuite;

public class OABDiagramFiltersTestSuite extends BasicTestSuite {

  @Override
  protected List<BasicTestArtefact> getTests() {
    List<BasicTestArtefact> tests = new ArrayList<>();
    tests.add(new HideAllocatedInteractionsForOAB());
    tests.add(new HideDiagramTitleBlocksForOAB());
    tests.add(new HideElementTitleBlocksForOAB());    
    tests.add(new HideOperationalActivitiesForOAB());
    tests.add(new HideRolesForOAB());
    tests.add(new HideOperationalActorsForOAB());
    tests.add(new HideInteractionsForOAB());
    tests.add(new HideCommunicationMeansForOAB());
    tests.add(new ShowExchangeItemsOnInteractionsForOAB());
    tests.add(new ShowExchangeItemsOnCommunicationMeansForOAB());
    tests.add(new ShowExchangeItemsOnCommunicationMeansWithoutInteractionsForOAB());
    tests.add(new ShowAllocatedInteractionsOnCommunicationMeansForOAB());
    tests.add(new HideCrossInteractionsOfRolesForOAB());
    tests.add(new HideInteractionsNamesForOAB());
    tests.add(new HideCommunicationMeansNamesForOAB());
    tests.add(new HidePropertyValuesForOAB());
    tests.add(new HideSequencingInformationForOAB());
    return tests;
  }

}
