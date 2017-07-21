/*******************************************************************************
 * Copyright (c) 2016, 2017 THALES GLOBAL SERVICES.
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

import org.polarsys.capella.test.diagram.tools.ju.xab.CreatePhysicalPath;
import org.polarsys.capella.test.diagram.tools.ju.xab.ShowHideComponentExchanges;
import org.polarsys.capella.test.diagram.tools.ju.xab.ShowHideFunctions;
import org.polarsys.capella.test.diagram.tools.ju.xab.ShowHidePhysicalLinks;
import org.polarsys.capella.test.diagram.tools.ju.xab.CreateFunctionalChain;
import org.polarsys.capella.test.framework.api.BasicTestArtefact;
import org.polarsys.capella.test.framework.api.BasicTestSuite;

import junit.framework.Test;

public class XABDiagramToolsTestSuite extends BasicTestSuite {

  /**
   * Returns the suite. This is required to unary launch this test.
   */
  public static Test suite() {
    return new XABDiagramToolsTestSuite();
  }

  /**
   * @see org.polarsys.capella.test.framework.api.BasicTestSuite#getTests()
   */
  @Override
  protected List<BasicTestArtefact> getTests() {
    List<BasicTestArtefact> tests = new ArrayList<BasicTestArtefact>();
    tests.add(new CreatePhysicalPath());
    tests.add(new CreateFunctionalChain());
    
    tests.add(new ShowHideComponentExchanges());
    tests.add(new ShowHidePhysicalLinks());
    tests.add(new ShowHideFunctions());
    return tests;
  }

}
