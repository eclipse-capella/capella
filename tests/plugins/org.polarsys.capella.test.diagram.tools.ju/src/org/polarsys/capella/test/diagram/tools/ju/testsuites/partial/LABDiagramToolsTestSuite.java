/*******************************************************************************
 * Copyright (c) 2006, 2017 THALES GLOBAL SERVICES.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *  
 * Contributors:
 *    Thales - initial API and implementation
 *******************************************************************************/
package org.polarsys.capella.test.diagram.tools.ju.testsuites.partial;

import java.util.ArrayList;
import java.util.List;

import org.polarsys.capella.test.diagram.tools.ju.lab.ConstraintRenameTestCase;
import org.polarsys.capella.test.diagram.tools.ju.lab.DragAndDropLF;
import org.polarsys.capella.test.diagram.tools.ju.lab.ShowHideComponentExchangeWithCategoryTestCase;
import org.polarsys.capella.test.diagram.tools.ju.lab.ShowHideComponentTestCase;
import org.polarsys.capella.test.diagram.tools.ju.lab.ShowHideFETestCase;
import org.polarsys.capella.test.framework.api.BasicTestArtefact;
import org.polarsys.capella.test.framework.api.BasicTestSuite;

import junit.framework.Test;

public class LABDiagramToolsTestSuite extends BasicTestSuite {

  /**
   * Returns the suite. This is required to unary launch this test.
   */
  public static Test suite() {
    return new LABDiagramToolsTestSuite();
  }

  /**
   * @see org.polarsys.capella.test.framework.api.BasicTestSuite#getTests()
   */
  @Override
  protected List<BasicTestArtefact> getTests() {
    List<BasicTestArtefact> tests = new ArrayList<BasicTestArtefact>();
    tests.add(new ShowHideComponentTestCase());
    tests.add(new DragAndDropLF());
    tests.add(new ConstraintRenameTestCase());
    tests.add(new ShowHideComponentExchangeWithCategoryTestCase());
    tests.add(new ShowHideFETestCase());
    return tests;
  }

}
