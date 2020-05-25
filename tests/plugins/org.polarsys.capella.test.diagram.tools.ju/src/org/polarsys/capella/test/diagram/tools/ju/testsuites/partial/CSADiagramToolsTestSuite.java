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

import org.polarsys.capella.test.diagram.tools.ju.csa.Constraints;
import org.polarsys.capella.test.diagram.tools.ju.csa.CreateActor;
import org.polarsys.capella.test.diagram.tools.ju.csa.CreateActorGeneralization;
import org.polarsys.capella.test.diagram.tools.ju.csa.DragAndDropTest;
import org.polarsys.capella.test.diagram.tools.ju.csa.ReconnectGeneralization;
import org.polarsys.capella.test.framework.api.BasicTestArtefact;
import org.polarsys.capella.test.framework.api.BasicTestSuite;

import junit.framework.Test;

public class CSADiagramToolsTestSuite extends BasicTestSuite {

  /**
   * Returns the suite. This is required to unary launch this test.
   */
  public static Test suite() {
    return new CSADiagramToolsTestSuite();
  }

  @Override
  public List<String> getRequiredTestModels() {
    return Arrays.asList("EmptyProject");
  }

  /**
   * @see org.polarsys.capella.test.framework.api.BasicTestSuite#getTests()
   */
  @Override
  protected List<BasicTestArtefact> getTests() {
    List<BasicTestArtefact> tests = new ArrayList<BasicTestArtefact>();
    tests.add(new CreateActor());
    tests.add(new CreateActorGeneralization());
    tests.add(new Constraints());
    tests.add(new ReconnectGeneralization());
    tests.add(new DragAndDropTest());
    return tests;
  }

}
