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
package org.polarsys.capella.test.diagram.filters.ju.xfcd;

import java.util.ArrayList;
import java.util.List;

import org.polarsys.capella.test.framework.api.BasicTestArtefact;
import org.polarsys.capella.test.framework.api.BasicTestSuite;

public class XFCDDiagramFiltersTestSuite extends BasicTestSuite {

  @Override
  protected List<BasicTestArtefact> getTests() {
    List<BasicTestArtefact> tests = new ArrayList<>();
    tests.add(new HideControlNodesForSFBD());
    tests.add(new HideControlNodesForLFBD());
    tests.add(new HideControlNodesForPFBD());
    tests.add(new MergeFilterOPDTest());
    tests.add(new MergeFilterPFCDTest());
    tests.add(new MergeFilterSFCDTest());
    tests.add(new HideFunctionalChainInvolvementLinksSFCDTest());
    tests.add(new HideFunctionalChainInvolvementLinksLFCD());
    tests.add(new HideFunctionalChainInvolvementLinksPFCDTest());
    tests.add(new HideFunctionalChainInvolvementLinksOPDTest());
    tests.add(new HideSequencingInformationSFCDTest());
    tests.add(new HideSequencingInformationLFCDTest());
    tests.add(new HideSequencingInformationPFCDTest());
    tests.add(new HideSequencingInformationOPDTest());
    tests.add(new HideComputedSequencingInformationSFCDTest());
    tests.add(new HideComputedSequencingInformationLFCDTest());
    tests.add(new HideComputedSequencingInformationPFCDTest());
    tests.add(new HideComputedSequencingInformationOPDTest());
    tests.add(new HideByDefaultAssociationLinksSFCD());
    tests.add(new HideByDefaultAssociationLinksLFCD());
    tests.add(new HideByDefaultAssociationLinksPFCD());
    tests.add(new HideByDefaultAssociationLinksOPD());
    tests.add(new MergeSequenceLinksAssociatedLinksTestCase1());
    tests.add(new MergeSequenceLinksAssociatedLinksTestCase2());
    tests.add(new MergeSequenceLinksAssociatedLinksTestCase3());
    tests.add(new ShowExchangeItemsOnInteractions());
    tests.add(new ShowInteractionsWithExchangeItems());
    tests.add(new ShowExchangeItemsParametersOnInteractions());
    return tests;
  }

}
