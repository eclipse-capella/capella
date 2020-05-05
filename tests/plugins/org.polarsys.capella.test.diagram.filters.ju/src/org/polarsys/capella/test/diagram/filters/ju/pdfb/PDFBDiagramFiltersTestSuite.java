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
package org.polarsys.capella.test.diagram.filters.ju.pdfb;

import java.util.ArrayList;
import java.util.List;

import org.polarsys.capella.test.framework.api.BasicTestArtefact;
import org.polarsys.capella.test.framework.api.BasicTestSuite;

public class PDFBDiagramFiltersTestSuite extends BasicTestSuite {

  @Override
  protected List<BasicTestArtefact> getTests() {
    List<BasicTestArtefact> tests = new ArrayList<>();
    tests.add(new CollapsePortsForPDFB());
    tests.add(new HideDiagramTitleBlocksTest());
    tests.add(new HideElementTitleBlocksTest());
    tests.add(new HideExchangeCategoriesForPDFB());
    tests.add(new HideFunctionalExchangesForPDFB());
    tests.add(new HideFunctionalExchangesNamesForPDFB());
    tests.add(new HideFunctionPortsWithoutExchangesForPDFB());
    tests.add(new HidePropertyValuesForPDFB());
    tests.add(new ShowEI1EI2ForPDFB());
    tests.add(new ShowEI1EI2WithParametersForPDFB());
    tests.add(new ShowFunctionalExchangeE1E2ForPDFB());
    tests.add(new ShowFunctionalExchangeWithParametersForPDFB());
    tests.add(new ShowFunctionalExchangeWithTypesForPDFB());
    return tests;
  }

}
