/*******************************************************************************
 * Copyright (c) 2020, 2022 THALES GLOBAL SERVICES.
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
package org.polarsys.capella.test.diagram.common.ju.step.tools.titleblocks;

import static org.junit.Assert.assertTrue;

import org.eclipse.emf.common.util.EList;
import org.eclipse.sirius.diagram.DDiagramElement;
import org.eclipse.sirius.diagram.DNodeContainer;
import org.eclipse.sirius.viewpoint.description.DAnnotation;
import org.polarsys.capella.core.diagram.helpers.TitleBlockHelper;
import org.polarsys.capella.core.sirius.analysis.DiagramServices;
import org.polarsys.capella.test.diagram.common.ju.context.DiagramContext;

public class InsertLineInTitleBlockTool extends InsertRemoveLineInTitleBlockTool {
  public InsertLineInTitleBlockTool(DiagramContext context, String toolName, String diagramID, DAnnotation titleBlock,
      int lineNo) {
    super(context, toolName, diagramID, titleBlock, lineNo);
  }

  @Override
  protected void postRunTest() {
    // a new line is added under the current selected line
    int newLinesNo = TitleBlockHelper.getTitleBlockLines(titleBlock).size();
    assertTrue(newLinesNo == currentLinesNo + 1);

    // Check that newly created line is at the same index in both titleblock.references, and in the diagram elements

    DAnnotation currentLine = TitleBlockHelper.getTitleBlockLines(titleBlock).get(currentLineNo);
    DAnnotation newLine = TitleBlockHelper.getTitleBlockLines(titleBlock).get(currentLineNo + 1);
    assertTrue(
        TitleBlockHelper.getTitleBlockCells(newLine).size() == TitleBlockHelper.getTitleBlockCells(currentLine).size());

    DNodeContainer titleBlockSpec = (DNodeContainer) DiagramServices.getDiagramServices()
        .getDiagramElement(getDiagramContext().getDiagram(), titleBlock);

    EList<DDiagramElement> diagramElements = titleBlockSpec.getOwnedDiagramElements();
    DDiagramElement newLineDiagramElement = diagramElements.stream().filter(element -> element.getTarget() == newLine)
        .findFirst().get();
    assertTrue(newLineDiagramElement != null);
    int newLineDiagramElementLineNo = diagramElements.indexOf(newLineDiagramElement);
    int newLineLineNo = titleBlock.getReferences().indexOf(newLine);

    assertTrue(newLineDiagramElementLineNo == newLineLineNo);
  }
}
