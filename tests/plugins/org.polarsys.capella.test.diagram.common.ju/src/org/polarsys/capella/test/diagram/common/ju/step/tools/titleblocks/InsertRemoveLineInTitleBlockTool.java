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
package org.polarsys.capella.test.diagram.common.ju.step.tools.titleblocks;

import static org.junit.Assert.assertFalse;

import java.util.List;

import org.eclipse.sirius.diagram.DDiagram;
import org.eclipse.sirius.diagram.DDiagramElement;
import org.eclipse.sirius.diagram.DDiagramElementContainer;
import org.eclipse.sirius.viewpoint.description.DAnnotation;
import org.polarsys.capella.core.diagram.helpers.TitleBlockHelper;
import org.polarsys.capella.core.sirius.analysis.DiagramServices;
import org.polarsys.capella.test.diagram.common.ju.context.DiagramContext;
import org.polarsys.capella.test.diagram.common.ju.step.tools.CreateAbstractDNodeTool;
import org.polarsys.capella.test.diagram.common.ju.wrapper.utils.ArgumentType;

public class InsertRemoveLineInTitleBlockTool extends CreateAbstractDNodeTool<DDiagramElementContainer> {

  protected DAnnotation titleBlock;
  protected DAnnotation titleBlockSelectedCell;
  protected int currentLineNo;
  protected int currentLinesNo;

  public InsertRemoveLineInTitleBlockTool(DiagramContext context, String toolName, String diagramID,
      DAnnotation titleBlock, int lineNo) {
    super(context, toolName, diagramID);
    this.titleBlock = titleBlock;
    this.currentLineNo = lineNo;
    this.titleBlockSelectedCell = getSelectedCell();
    this.currentLinesNo = TitleBlockHelper.getTitleBlockLines(titleBlock).size();
  }

  private DAnnotation getSelectedCell() {
    List<DAnnotation> cells = TitleBlockHelper
        .getTitleBlockCells(TitleBlockHelper.getTitleBlockLines(titleBlock).get(currentLineNo));
    assertFalse(cells.isEmpty());
    return cells.get(0);
  }

  @Override
  protected void initToolArguments() {
    DDiagram diagram = getDiagramContext().getDiagram();
    DDiagramElement selectedCellView = DiagramServices.getDiagramServices().getDiagramElement(diagram,
        getSelectedCell());

    _toolWrapper.setArgumentValue(ArgumentType.CONTAINER, selectedCellView.getTarget());
    _toolWrapper.setArgumentValue(ArgumentType.CONTAINER_VIEW, selectedCellView);
  }

  @Override
  public DDiagramElementContainer getResult() {
    DDiagram diagram = getDiagramContext().getDiagram();
    DDiagramElement titleBlockView = DiagramServices.getDiagramServices().getDiagramElement(diagram, titleBlock);
    return (DDiagramElementContainer) titleBlockView;
  }
}
