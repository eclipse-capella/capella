package org.polarsys.capella.test.diagram.tools.ju.tb;

import org.polarsys.capella.core.model.helpers.BlockArchitectureExt;

public class SA_xABTitleBlockTestCase extends xABTitleBlockTestCase {

  private static final String ACTOR_ID = "9dcae60f-09c0-4fe1-b9c9-990c36bedc14";
  private static final String DIAGRAM_NAME = "[SAB] Structure";

  @Override
  public void initData() {
    elementId = ACTOR_ID;
    diagramName = DIAGRAM_NAME;
    type = BlockArchitectureExt.Type.SA;
  }

}
