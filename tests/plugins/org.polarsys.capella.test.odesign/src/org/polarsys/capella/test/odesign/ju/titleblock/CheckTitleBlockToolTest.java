/*******************************************************************************
 * Copyright (c) 2020 THALES GLOBAL SERVICES.
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
package org.polarsys.capella.test.odesign.ju.titleblock;

import java.util.List;
import java.util.stream.Collectors;

import org.eclipse.emf.ecore.util.EcoreUtil;
import org.eclipse.sirius.business.api.componentization.ViewpointRegistry;
import org.eclipse.sirius.diagram.description.DiagramDescription;
import org.polarsys.capella.core.sirius.analysis.activator.SiriusViewActivator;
import org.polarsys.capella.core.sirius.analysis.constants.IToolNameConstants;
import org.polarsys.capella.test.odesign.helper.OdesignTestHelper;

public class CheckTitleBlockToolTest extends CheckTitleBlockTest {
  @Override
  public void test() throws Exception {
    assertAllDiagramContainTool(IToolNameConstants.TOOL_CREATE_TITLE_BLOCK);
    assertAllDiagramContainTool(IToolNameConstants.TOOL_INSERT_COLUMN_TITLE_BLOCK);
    assertAllDiagramContainTool(IToolNameConstants.TOOL_INSERT_LINE_TITLE_BLOCK);
    assertAllDiagramContainTool(IToolNameConstants.TOOL_INSERT_REMOVE_TITLE_BLOCK);
    assertAllDiagramContainTool(IToolNameConstants.TOOL_REMOVE_COLUMN_TITLE_BLOCK);
    assertAllDiagramContainTool(IToolNameConstants.TOOL_REMOVE_LINE_TITLE_BLOCK);
  }

  /**
   * Assert that all diagram descriptions must contain the given tool
   */
  private void assertAllDiagramContainTool(String toolName) {
    List<DiagramDescription> diagramDescriptionsWithoutTool = getDiagramDescriptionsWithoutTool(toolName);
    assertTrue(
        "The following diagrams do not have Title block " + toolName + " tool: "
            + diagramDescriptionsWithoutTool.stream().map(m -> m.getName()).collect(Collectors.joining(", ")),
        diagramDescriptionsWithoutTool.isEmpty());
  }

  private List<DiagramDescription> getDiagramDescriptionsWithoutTool(String toolName) {
    return ViewpointRegistry.getInstance().getViewpoints().stream()
        .filter(vp -> EcoreUtil.getURI(vp).segment(1) == SiriusViewActivator.ID)
        .flatMap(vp -> vp.getOwnedRepresentations().stream())
        .filter(DiagramDescription.class::isInstance).map(DiagramDescription.class::cast)
        .filter(des -> !isDiagramDescriptionIgnored(des)).filter(des -> !OdesignTestHelper.hasTool(des, toolName))
        .collect(Collectors.toList());
  }
}
