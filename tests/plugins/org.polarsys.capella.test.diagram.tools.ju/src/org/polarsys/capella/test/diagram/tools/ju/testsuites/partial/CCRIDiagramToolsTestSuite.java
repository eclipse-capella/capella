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

import org.polarsys.capella.core.sirius.analysis.constants.IToolNameConstants;
import org.polarsys.capella.test.diagram.tools.ju.ccri.CreateDiagramElement;
import org.polarsys.capella.test.diagram.tools.ju.ccri.CreateEdges;
import org.polarsys.capella.test.diagram.tools.ju.ccri.DragAndDropTest;
import org.polarsys.capella.test.diagram.tools.ju.ccri.TestInsertTool;
import org.polarsys.capella.test.diagram.tools.ju.ccri.TestRelationshipsTool;
import org.polarsys.capella.test.framework.api.BasicTestArtefact;
import org.polarsys.capella.test.framework.api.BasicTestSuite;

import junit.framework.Test;

public class CCRIDiagramToolsTestSuite extends BasicTestSuite {

  /**
   * Returns the suite. This is required to unary launch this test.
   */
  public static Test suite() {
    return new CCRIDiagramToolsTestSuite();
  }

  @Override
  protected List<BasicTestArtefact> getTests() {

    String[] diagramElementTypes = { IToolNameConstants.TOOL_CCRI_CREATE_ACTOR_NODE,
        IToolNameConstants.TOOL_CCRI_CREATE_COMPONENT_NODE,
        IToolNameConstants.TOOL_CCRI_CREATE_CAPABILITY_REALIZATION_NODE };

    List<BasicTestArtefact> tests = new ArrayList<BasicTestArtefact>();

    for (String elementType : diagramElementTypes) {
      tests.add(new CreateDiagramElement(elementType));
    }

    tests.add(new CreateEdges());
    tests.add(new TestInsertTool());
    tests.add(new TestRelationshipsTool());
    tests.add(new DragAndDropTest());

    return tests;
  }

}
