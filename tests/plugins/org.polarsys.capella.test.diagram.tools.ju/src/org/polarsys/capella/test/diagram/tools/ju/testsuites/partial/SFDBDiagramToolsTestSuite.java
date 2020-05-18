/*******************************************************************************
 * Copyright (c) 2006, 2019 THALES GLOBAL SERVICES.
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
import java.util.Arrays;
import java.util.List;

import org.polarsys.capella.test.diagram.tools.ju.sdfb.DnDWithInternalFE;
import org.polarsys.capella.test.diagram.tools.ju.sdfb.DnDWithInternalFEAndCommonPort;
import org.polarsys.capella.test.diagram.tools.ju.sdfb.InitializeFromExistingDiagramTestCase;
import org.polarsys.capella.test.diagram.tools.ju.sdfb.RemoveCategoryWithoutExchange;
import org.polarsys.capella.test.diagram.tools.ju.sdfb.ShowHideFunctionalExchangeWithCategoryTestCase;
import org.polarsys.capella.test.diagram.tools.ju.sdfb.SwitchCategoryTestCase;
import org.polarsys.capella.test.diagram.tools.ju.sdfb.SwitchCategoryWithHiddenExchanges;
import org.polarsys.capella.test.diagram.tools.ju.sdfb.UndoOnHideSystemFunction;
import org.polarsys.capella.test.framework.api.BasicTestArtefact;
import org.polarsys.capella.test.framework.api.BasicTestSuite;

import junit.framework.Test;

public class SFDBDiagramToolsTestSuite extends BasicTestSuite {

  /**
   * Returns the suite. This is required to unary launch this test.
   */
  public static Test suite() {
    return new SFDBDiagramToolsTestSuite();
  }

  public List<String> getRequiredTestModels() {
    return Arrays.asList("EmptyProject", "SwitchCategory");
  }
  
  /**
   * @see org.polarsys.capella.test.framework.api.BasicTestSuite#getTests()
   */
	@Override
	protected List<BasicTestArtefact> getTests() {
    List<BasicTestArtefact> tests = new ArrayList<BasicTestArtefact>();
    tests.add(new DnDWithInternalFE());
    tests.add(new DnDWithInternalFEAndCommonPort());
    tests.add(new SwitchCategoryTestCase());
    tests.add(new UndoOnHideSystemFunction());
    tests.add(new InitializeFromExistingDiagramTestCase());
    tests.add(new ShowHideFunctionalExchangeWithCategoryTestCase());
    tests.add(new SwitchCategoryWithHiddenExchanges());
    tests.add(new RemoveCategoryWithoutExchange());
    return tests;
	}

}
