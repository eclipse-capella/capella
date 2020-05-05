/*******************************************************************************
 * Copyright (c) 2019 THALES GLOBAL SERVICES.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *  
 * Contributors:
 *    Thales - initial API and implementation
 *******************************************************************************/
package org.polarsys.capella.test.diagram.filters.ju.oab;

import java.util.ArrayList;
import java.util.List;

import org.polarsys.capella.test.diagram.filters.ju.oab.HideDiagramTitleBlocksTest;
import org.polarsys.capella.test.diagram.filters.ju.oab.HideElementTitleBlocksTest;
import org.polarsys.capella.test.framework.api.BasicTestArtefact;
import org.polarsys.capella.test.framework.api.BasicTestSuite;

public class OABDiagramFiltersTestSuite extends BasicTestSuite {

  @Override
  protected List<BasicTestArtefact> getTests() {
    List<BasicTestArtefact> tests = new ArrayList<>();
    tests.add(new HideAllocatedInteractionsForOAB());
    tests.add(new HideDiagramTitleBlocksTest());
    tests.add(new HideElementTitleBlocksTest());    
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
