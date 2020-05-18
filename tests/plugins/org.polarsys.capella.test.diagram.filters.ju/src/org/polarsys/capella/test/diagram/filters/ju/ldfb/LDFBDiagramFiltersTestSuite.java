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
package org.polarsys.capella.test.diagram.filters.ju.ldfb;

import java.util.ArrayList;
import java.util.List;

import org.polarsys.capella.test.diagram.filters.ju.pdfb.HideDiagramTitleBlocksForPDFB;
import org.polarsys.capella.test.diagram.filters.ju.pdfb.HideElementTitleBlocksForPDFB;
import org.polarsys.capella.test.framework.api.BasicTestArtefact;
import org.polarsys.capella.test.framework.api.BasicTestSuite;

public class LDFBDiagramFiltersTestSuite extends BasicTestSuite {

  @Override
  protected List<BasicTestArtefact> getTests() {
    List<BasicTestArtefact> tests = new ArrayList<>();
    tests.add(new CollapsePortsForLDFB());
    tests.add(new HideDiagramTitleBlocksForPDFB());
    tests.add(new HideElementTitleBlocksForPDFB());
    tests.add(new HideExchangeCategoriesForLDFB());
    tests.add(new HideFunctionalExchangesForLDFB());
    tests.add(new HideFunctionalExchangesNamesForLDFB());
    tests.add(new HideFunctionPortsWithoutExchangesForLDFB());
    tests.add(new HidePropertyValuesForLDFB());
    tests.add(new ShowEI1EI2ForLDFB());
    tests.add(new ShowEI1EI2WithParametersForLDFB());
    tests.add(new ShowFunctionalExchangeE1E2ForLDFB());
    tests.add(new ShowFunctionalExchangeWithParametersForLDFB());
    tests.add(new ShowFunctionalExchangeWithTypesForLDFB());
    return tests;
  }

}
