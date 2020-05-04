package org.polarsys.capella.test.diagram.common.ju.step.tools.titleblocks;

import static org.junit.Assert.assertTrue;

import org.eclipse.sirius.viewpoint.description.DAnnotation;
import org.polarsys.capella.core.diagram.helpers.TitleBlockHelper;
import org.polarsys.capella.test.diagram.common.ju.context.DiagramContext;

public class RemoveColumnFromTitleBlockTool extends InsertRemoveColumnInTitleBlockTool {
  public RemoveColumnFromTitleBlockTool(DiagramContext context, String toolName, String diagramID,
      DAnnotation titleBlock, int colNo) {
    super(context, toolName, diagramID, titleBlock, colNo);
  }

  @Override
  protected void postRunTest() {
    // a selected column is removed
    int newColsNo = TitleBlockHelper.getNumOfColumns(titleBlock);
    assertTrue(newColsNo == currentColsNo - 1);
  }
}
