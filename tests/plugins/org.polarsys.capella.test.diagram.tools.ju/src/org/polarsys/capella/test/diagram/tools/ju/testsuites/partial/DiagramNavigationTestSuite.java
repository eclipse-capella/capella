/*******************************************************************************
 * Copyright (c) 2022, THALES GLOBAL SERVICES.
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

import org.polarsys.capella.test.diagram.tools.ju.navigation.FCDiagramNavigationTest;
import org.polarsys.capella.test.diagram.tools.ju.navigation.PPDiagramNavigationTest;
import org.polarsys.capella.test.diagram.tools.ju.navigation.SequenceDiagramNavigationTest;
import org.polarsys.capella.test.framework.api.BasicTestArtefact;
import org.polarsys.capella.test.framework.api.BasicTestSuite;

import junit.framework.Test;

public class DiagramNavigationTestSuite extends BasicTestSuite {

  public static Test suite() {
    return new DiagramNavigationTestSuite();
  }

  @Override 
  protected List<BasicTestArtefact> getTests() {
    List<BasicTestArtefact> tests = new ArrayList<>();
    tests.add(new SequenceDiagramNavigationTest());
    tests.add(new FCDiagramNavigationTest());
    tests.add(new PPDiagramNavigationTest());

    return tests;
  }

}
