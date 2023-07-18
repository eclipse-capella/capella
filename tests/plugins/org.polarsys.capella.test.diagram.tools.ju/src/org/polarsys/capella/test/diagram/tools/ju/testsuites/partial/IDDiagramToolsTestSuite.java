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
package org.polarsys.capella.test.diagram.tools.ju.testsuites.partial;

import java.util.ArrayList;
import java.util.List;

import org.polarsys.capella.test.diagram.tool.ju.library.LibraryDragAndDropTest;
import org.polarsys.capella.test.diagram.tools.ju.id.CreateConstraint;
import org.polarsys.capella.test.diagram.tools.ju.id.CreateExchangeItemElement;
import org.polarsys.capella.test.diagram.tools.ju.id.CreateExchangeItemGroup;
import org.polarsys.capella.test.diagram.tools.ju.id.DragAndDropExchangeItem;
import org.polarsys.capella.test.diagram.tools.ju.model.settings.OA_IDProjectSettings;
import org.polarsys.capella.test.diagram.tools.ju.model.settings.SA_IDProjectSettings;
import org.polarsys.capella.test.framework.api.BasicTestArtefact;
import org.polarsys.capella.test.framework.api.BasicTestSuite;

import junit.framework.Test;

public class IDDiagramToolsTestSuite extends BasicTestSuite {

  /**
   * Returns the suite. This is required to unary launch this test.
   */
  public static Test suite() {
    return new IDDiagramToolsTestSuite();
  }

  /**
   * @see org.polarsys.capella.test.framework.api.BasicTestSuite#getTests()
   */
  @Override
  protected List<BasicTestArtefact> getTests() {
    List<BasicTestArtefact> tests = new ArrayList<BasicTestArtefact>();

    tests.add(new CreateExchangeItemGroup(new OA_IDProjectSettings()));
    tests.add(new CreateExchangeItemGroup(new SA_IDProjectSettings()));

    tests.add(new CreateExchangeItemElement(new OA_IDProjectSettings()));
    tests.add(new CreateExchangeItemElement(new SA_IDProjectSettings()));

    tests.add(new DragAndDropExchangeItem(new OA_IDProjectSettings()));
    tests.add(new DragAndDropExchangeItem(new SA_IDProjectSettings()));

    tests.add(new CreateConstraint(new OA_IDProjectSettings()));
    tests.add(new CreateConstraint(new SA_IDProjectSettings()));

    tests.add(new LibraryDragAndDropTest());

    return tests;
  }

}
