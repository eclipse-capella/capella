/*******************************************************************************
 * Copyright (c) 2018, 2019 THALES GLOBAL SERVICES.
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
package org.polarsys.capella.test.diagram.filters.ju.xdfb;

import java.util.Arrays;
import java.util.List;

import org.eclipse.sirius.diagram.DDiagram;
import org.eclipse.sirius.diagram.DEdge;
import org.polarsys.capella.core.sirius.analysis.constants.IFilterNameConstants;
import org.polarsys.capella.test.diagram.common.ju.wrapper.utils.DiagramHelper;
import org.polarsys.capella.test.diagram.filters.ju.DiagramObjectFilterTestCase;

public class HideFunctionalExchangesTestCase extends DiagramObjectFilterTestCase {

  @Override
  protected String getTestProjectName() {
    return "HideFunctionalExchange";
  }

  @Override
  protected String getDiagramName() {
    return "[SDFB] Root System Function";
  }

  @Override
  protected String getFilterName() {
    return IFilterNameConstants.FILTER_SDFB_HIDE_FUNCTIONAL_EXCHANGES;
  }

  @Override
  protected List<String> getFilteredObjetIDs() {
    return Arrays.asList("ad7c0244-0750-43a3-b419-b14a7a5f1185", "d31f4c61-c647-4768-9986-d6c8fde6b700");
  }

  @Override
  public void test() {
    super.test();

    DDiagram diagram = (DDiagram) DiagramHelper.getDRepresentation(session, diagramName);
    DiagramHelper.refreshDiagram(diagram);

    // Assert that internal link is removed
    // Our diagram should have 2 filtered edges which are the two functional exchanges.
    // If the test fails due to the existence of 3 edges, this means that the internal link is not removed when applying
    // the filter.
    assertEquals("Internal link is not removed when applying the filter", 2, diagram.getEdges().size());
    for (DEdge edge : diagram.getEdges()) {
      assertTrue(DiagramHelper.isDiagramElementFiltered(edge));
    }

    // Check that total elements are 10
    assertEquals(10, diagram.getDiagramElements().size());
  }
}
