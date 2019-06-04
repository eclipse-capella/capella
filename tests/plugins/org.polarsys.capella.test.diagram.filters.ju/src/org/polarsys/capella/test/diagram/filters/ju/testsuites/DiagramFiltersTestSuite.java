/*******************************************************************************
 * Copyright (c) 2006, 2019 THALES GLOBAL SERVICES.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *  
 * Contributors:
 *    Thales - initial API and implementation
 *******************************************************************************/
package org.polarsys.capella.test.diagram.filters.ju.testsuites;

import java.util.ArrayList;
import java.util.List;

import org.polarsys.capella.test.diagram.filters.ju.cdb.CDBDiagramFiltersTestSuite;
import org.polarsys.capella.test.diagram.filters.ju.cdi.CDIDiagramFiltersTestSuite;
import org.polarsys.capella.test.diagram.filters.ju.cei.CEIDiagramFiltersTestSuite;
import org.polarsys.capella.test.diagram.filters.ju.cii.CIIDiagramFiltersTestSuite;
import org.polarsys.capella.test.diagram.filters.ju.crb.CRBDiagramFiltersTestSuite;
import org.polarsys.capella.test.diagram.filters.ju.idb.IDBDiagramFiltersTestSuite;
import org.polarsys.capella.test.diagram.filters.ju.xab.XABDiagramFiltersTestSuite;
import org.polarsys.capella.test.diagram.filters.ju.xdfb.XDFBDiagramFiltersTestSuite;
import org.polarsys.capella.test.diagram.filters.ju.xfcd.XFCDDiagramFiltersTestSuite;
import org.polarsys.capella.test.framework.api.BasicTestArtefact;
import org.polarsys.capella.test.framework.api.BasicTestSuite;

import junit.framework.Test;

public class DiagramFiltersTestSuite extends BasicTestSuite {

  /**
   * Returns the suite. This is required to unary launch this test.
   */
  public static Test suite() {
    return new DiagramFiltersTestSuite();
  }

  /**
   * @see org.polarsys.capella.test.framework.api.BasicTestSuite#getTests()
   */
  @Override
  protected List<BasicTestArtefact> getTests() {
    List<BasicTestArtefact> tests = new ArrayList<BasicTestArtefact>();
    tests.add(new CEIDiagramFiltersTestSuite());
    tests.add(new CDBDiagramFiltersTestSuite());
    tests.add(new CRBDiagramFiltersTestSuite());
    tests.add(new XFCDDiagramFiltersTestSuite());
    tests.add(new XABDiagramFiltersTestSuite());
    tests.add(new CDIDiagramFiltersTestSuite());
    tests.add(new CEIDiagramFiltersTestSuite());
    tests.add(new CIIDiagramFiltersTestSuite());
    tests.add(new IDBDiagramFiltersTestSuite());
    tests.add(new XDFBDiagramFiltersTestSuite());
    return tests;
  }
}
