/*******************************************************************************
 * Copyright (c) 2016, 2019 THALES GLOBAL SERVICES.
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

import org.polarsys.capella.test.diagram.tools.ju.xfcd.MaxHierarchyLevel0TestOA;
import org.polarsys.capella.test.diagram.tools.ju.xfcd.MaxHierarchyLevel0TestSA;
import org.polarsys.capella.test.diagram.tools.ju.xfcd.MaxHierarchyLevel1TestOA;
import org.polarsys.capella.test.diagram.tools.ju.xfcd.MaxHierarchyLevel1TestSA;
import org.polarsys.capella.test.diagram.tools.ju.xfcd.MaxHierarchyLevel2TestOA;
import org.polarsys.capella.test.diagram.tools.ju.xfcd.MaxHierarchyLevel2TestSA;
import org.polarsys.capella.test.framework.api.BasicTestArtefact;
import org.polarsys.capella.test.framework.api.BasicTestSuite;

import junit.framework.Test;

public class XFCDDiagramToolsTestSuite extends BasicTestSuite {

  /**
   * Returns the suite. This is required to unary launch this test.
   */
  public static Test suite() {
    return new XFCDDiagramToolsTestSuite();
  }

  /**
   * @see org.polarsys.capella.test.framework.api.BasicTestSuite#getTests()
   */
  @Override
  protected List<BasicTestArtefact> getTests() {
    List<BasicTestArtefact> tests = new ArrayList<>();

    tests.add(new MaxHierarchyLevel0TestOA());
    tests.add(new MaxHierarchyLevel1TestOA());
    tests.add(new MaxHierarchyLevel2TestOA());

    tests.add(new MaxHierarchyLevel0TestSA());
    tests.add(new MaxHierarchyLevel1TestSA());
    tests.add(new MaxHierarchyLevel2TestSA());

    return tests;
  }

}
