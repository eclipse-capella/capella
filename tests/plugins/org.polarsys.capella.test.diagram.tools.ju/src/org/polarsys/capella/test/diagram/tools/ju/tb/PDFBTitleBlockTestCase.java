package org.polarsys.capella.test.diagram.tools.ju.tb;

import org.polarsys.capella.core.model.helpers.BlockArchitectureExt;

public class PDFBTitleBlockTestCase extends XDFBTitleBlockTestCase {

  private static final String ACTOR_ID = "d7145845-c391-4990-9ffc-4e0dce6b5632";
  private static final String FUNCTION_ID = "67eb99e8-1433-4746-927a-0a6b71345d17";
  private static final String DIAGRAM_NAME = "[PDFB] Root Physical Function";
  private static final String ROOT_FUNCTION = "80e122fd-26b8-42ec-9f90-429ebb1b8e19";

  @Override
  protected void initData() {
    elementsId.add(ACTOR_ID);
    elementsId.add(FUNCTION_ID);
    diagramName = DIAGRAM_NAME;
    type = BlockArchitectureExt.Type.PA;
    rootFunction = ROOT_FUNCTION;
  }

}
