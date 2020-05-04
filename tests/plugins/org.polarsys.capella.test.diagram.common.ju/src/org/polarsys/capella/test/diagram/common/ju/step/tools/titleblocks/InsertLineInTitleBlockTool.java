/*******************************************************************************
 * Copyright (c) 2020 THALES GLOBAL SERVICES.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *  
 * Contributors:
 *    Thales - initial API and implementation
 *******************************************************************************/
package org.polarsys.capella.test.diagram.common.ju.step.tools.titleblocks;

import static org.junit.Assert.assertTrue;

import org.eclipse.sirius.viewpoint.description.DAnnotation;
import org.polarsys.capella.core.diagram.helpers.TitleBlockHelper;
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

    DAnnotation currentLine = TitleBlockHelper.getTitleBlockLines(titleBlock).get(currentLineNo);
    DAnnotation newLine = TitleBlockHelper.getTitleBlockLines(titleBlock).get(currentLineNo + 1);
    assertTrue(
        TitleBlockHelper.getTitleBlockCells(newLine).size() == TitleBlockHelper.getTitleBlockCells(currentLine).size());
  }
}
