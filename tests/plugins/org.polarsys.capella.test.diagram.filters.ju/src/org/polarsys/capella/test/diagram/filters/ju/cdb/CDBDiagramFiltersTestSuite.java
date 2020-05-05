/*******************************************************************************
 * Copyright (c) 2019, 2020 THALES GLOBAL SERVICES.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *  
 * Contributors:
 *    Thales - initial API and implementation
 *******************************************************************************/
package org.polarsys.capella.test.diagram.filters.ju.cdb;

import java.util.ArrayList;
import java.util.List;

import org.polarsys.capella.test.framework.api.BasicTestArtefact;
import org.polarsys.capella.test.framework.api.BasicTestSuite;

public class CDBDiagramFiltersTestSuite extends BasicTestSuite {

  @Override
  protected List<BasicTestArtefact> getTests() {
    List<BasicTestArtefact> tests = new ArrayList<>();
    tests.add(new ShowFullPath());
    tests.add(new HideProperties());
    tests.add(new HideOperations());
    tests.add(new HideAssociations());
    tests.add(new HideGeneralizations());
    tests.add(new HideExchangeItemsDetailsInInterfaces());
    tests.add(new HideRoleLabels());
    tests.add(new HideRoleNames());
    tests.add(new HideDerivedProperties());
    tests.add(new HidePropertyValues());
    tests.add(new ShowModifiers());
    tests.add(new HideAssociationLabels());
    tests.add(new HideTechnicalInterfaceForCDB());
    tests.add(new HideElementTitleBocksForCDB());
    tests.add(new HideDiagramTitleBocksForCDB());
    return tests;
  }

}
