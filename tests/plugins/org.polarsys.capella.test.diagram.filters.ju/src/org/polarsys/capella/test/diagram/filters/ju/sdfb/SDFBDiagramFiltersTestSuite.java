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
package org.polarsys.capella.test.diagram.filters.ju.sdfb;

import java.util.ArrayList;
import java.util.List;

import org.polarsys.capella.test.diagram.filters.ju.pdfb.HideDiagramTitleBlocksForPDFB;
import org.polarsys.capella.test.diagram.filters.ju.pdfb.HideElementTitleBlocksForPDFB;
import org.polarsys.capella.test.framework.api.BasicTestArtefact;
import org.polarsys.capella.test.framework.api.BasicTestSuite;

public class SDFBDiagramFiltersTestSuite extends BasicTestSuite {

  @Override
  protected List<BasicTestArtefact> getTests() {
    List<BasicTestArtefact> tests = new ArrayList<>();
    tests.add(new CollapsePortsForSDFB());
    tests.add(new HideDiagramTitleBlocksForPDFB());
    tests.add(new HideElementTitleBlocksForPDFB());
    tests.add(new HideExchangeCategoriesForSDFB());
    tests.add(new HideFunctionalExchangesForSDFB());
    tests.add(new HideFunctionalExchangesNamesForSDFB());
    tests.add(new HideFunctionPortsWithoutExchangesForSDFB());
    tests.add(new HidePropertyValuesForSDFB());
    tests.add(new ShowEI1EI2ForSDFB());
    tests.add(new ShowEI1EI2WithParametersForSDFB());
    tests.add(new ShowFunctionalExchangeE1E2ForSDFB());
    tests.add(new ShowFunctionalExchangeWithParametersForSDFB());
    tests.add(new ShowFunctionalExchangeWithTypesForSDFB());
    return tests;
  }

}
