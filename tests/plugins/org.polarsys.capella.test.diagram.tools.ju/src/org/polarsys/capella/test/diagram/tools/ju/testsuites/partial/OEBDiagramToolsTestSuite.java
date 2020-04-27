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
package org.polarsys.capella.test.diagram.tools.ju.testsuites.partial;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.polarsys.capella.test.diagram.tools.ju.oeb.OEBCreateConstraint;
import org.polarsys.capella.test.diagram.tools.ju.oeb.OEBCreateConstraintElement;
import org.polarsys.capella.test.diagram.tools.ju.oeb.OEBCreateConstraints;
import org.polarsys.capella.test.diagram.tools.ju.oeb.OEBCreateContainedIn;
import org.polarsys.capella.test.diagram.tools.ju.oeb.OEBCreateOperationalActor;
import org.polarsys.capella.test.diagram.tools.ju.oeb.OEBCreateOperationalEntity;
import org.polarsys.capella.test.framework.api.BasicTestArtefact;
import org.polarsys.capella.test.framework.api.BasicTestSuite;

import junit.framework.Test;

public class OEBDiagramToolsTestSuite extends BasicTestSuite {

  /**
   * Returns the suite. This is required to unary launch this test.
   */
  public static Test suite() {
    return new OEBDiagramToolsTestSuite();
  }
  
  public List<String> getRequiredTestModels() {
    return Arrays.asList("DiagramToolsModel");
  }

  /**
   * @see org.polarsys.capella.test.framework.api.BasicTestSuite#getTests()
   */
  @Override
  protected List<BasicTestArtefact> getTests() {
    List<BasicTestArtefact> tests = new ArrayList<BasicTestArtefact>();
    tests.add(new OEBCreateOperationalEntity());
    tests.add(new OEBCreateOperationalActor());
    tests.add(new OEBCreateContainedIn());
    tests.add(new OEBCreateConstraint());
    tests.add(new OEBCreateConstraintElement());
    tests.add(new OEBCreateConstraints());

    return tests;
  }

}
