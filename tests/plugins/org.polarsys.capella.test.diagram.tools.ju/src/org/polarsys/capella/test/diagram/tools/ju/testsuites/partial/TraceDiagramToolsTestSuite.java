/*******************************************************************************
 * Copyright (c) 2026 Obeo.
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

import org.polarsys.capella.test.diagram.tools.ju.td.EditHierarchicalTdDiagram;
import org.polarsys.capella.test.diagram.tools.ju.td.EditSimpleTdDiagram;
import org.polarsys.capella.test.diagram.tools.ju.td.TdDiagramDesactivation;
import org.polarsys.capella.test.framework.api.BasicTestArtefact;
import org.polarsys.capella.test.framework.api.BasicTestSuite;

import junit.framework.Test;

/**
 * Suite of tests for Traces diagrams.
 */
public class TraceDiagramToolsTestSuite extends BasicTestSuite {

  /**
   * Returns the suite. This is required to unary launch this test.
   */
  public static Test suite() {
    return new TraceDiagramToolsTestSuite();
  }

  @Override
  public List<String> getRequiredTestModels() {
    return List.of(EditSimpleTdDiagram.REQUIRED_TEST_MODEL);
  }

	@Override
	protected List<BasicTestArtefact> getTests() {
    List<BasicTestArtefact> tests = new ArrayList<BasicTestArtefact>();
    tests.add(new EditSimpleTdDiagram());
    tests.add(new EditHierarchicalTdDiagram());
    tests.add(new TdDiagramDesactivation());
    return tests;
	}

}
