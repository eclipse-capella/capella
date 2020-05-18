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
package org.polarsys.capella.test.diagram.filters.ju.idb;

import java.util.ArrayList;
import java.util.List;

import org.polarsys.capella.test.framework.api.BasicTestArtefact;
import org.polarsys.capella.test.framework.api.BasicTestSuite;

public class IDBDiagramFiltersTestSuite extends BasicTestSuite {

  @Override
  protected List<BasicTestArtefact> getTests() {
    List<BasicTestArtefact> tests = new ArrayList<>();
    tests.add(new HideInterfaceContentsForIDB());
    tests.add(new HideInterfacesForIDB());
    tests.add(new HideExchangeItemsDetailsInInterfacesForIDB());
    tests.add(new HideExchangeItemElementsForIDB());
    tests.add(new HideExchangeItemsForIDB());
    tests.add(new HideComponentPortsForIDB());
    tests.add(new HideUseLinksForIDB());
    tests.add(new HideImplementationLinksForIDB());
    tests.add(new HideProvideLinksForIDB());
    tests.add(new HideRequireLinksForIDB());
    tests.add(new HideCommunicationLinksForIDB());
    tests.add(new HideGeneralizationLinksForIDB());
    tests.add(new HidePortDelegationsForIDB());
    tests.add(new HideSimplifiedModelBasedInteractionsForIDB());
    tests.add(new HideSimplifiedDiagramBasedInteractionsForIDB());
    tests.add(new HideTechnicalInterfacesForIDB());
    // tests.add(new HideDelegatedCommunicationLinksForIDB());
    tests.add(new HideDelegatedUseImplementationRequireProvideLinksForIDB());
    tests.add(new ShowModifiersForIDB());
    tests.add(new HidePropertyValuesForIDB());
    tests.add(new HideDiagramTitleBocksForIDB());
    tests.add(new HideElementTitleBocksForIDB());

    return tests;
  }
}
