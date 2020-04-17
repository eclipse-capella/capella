package org.polarsys.capella.test.diagram.common.ju.step.tools.titleblocks;

import static org.junit.Assert.assertTrue;

import org.eclipse.sirius.viewpoint.description.DAnnotation;
import org.polarsys.capella.core.diagram.helpers.TitleBlockHelper;
import org.polarsys.capella.test.diagram.common.ju.context.DiagramContext;

public class RemoveLineFromTitleBlockTool extends InsertRemoveLineInTitleBlockTool {
  public RemoveLineFromTitleBlockTool(DiagramContext context, String toolName, String diagramID, DAnnotation titleBlock,
      int lineNo) {
    super(context, toolName, diagramID, titleBlock, lineNo);
  }

  @Override
  protected void postRunTest() {
    // a selected line is removed
    int newLinesNo = TitleBlockHelper.getTitleBlockLines(titleBlock).size();
    assertTrue(newLinesNo == currentLinesNo - 1);

    // DAnnotation currentLine = TitleBlockHelper.getTitleBlockLines(titleBlock).get(currentLineNo);
    // DAnnotation newLine = TitleBlockHelper.getTitleBlockLines(titleBlock).get(currentLineNo + 1);
    // assertTrue(
    // TitleBlockHelper.getTitleBlockCells(newLine).size() == TitleBlockHelper.getTitleBlockCells(currentLine).size());

  }
}
