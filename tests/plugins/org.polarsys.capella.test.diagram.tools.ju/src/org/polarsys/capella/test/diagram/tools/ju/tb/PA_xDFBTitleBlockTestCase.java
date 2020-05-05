package org.polarsys.capella.test.diagram.tools.ju.tb;

import org.polarsys.capella.core.model.helpers.BlockArchitectureExt;

public class PA_xDFBTitleBlockTestCase extends xDFBTitleBlockTestCase {

  private static final String ACTOR_ID = "d7145845-c391-4990-9ffc-4e0dce6b5632";
  private static final String FUNCTION_ID = "67eb99e8-1433-4746-927a-0a6b71345d17";
  private static final String DIAGRAM_NAME = "[PDFB] Root Physical Function";

  @Override
  protected void initData() {
    elementsId.add(ACTOR_ID);
    elementsId.add(FUNCTION_ID);
    diagramName = DIAGRAM_NAME;
    type = BlockArchitectureExt.Type.PA;
  }

}
