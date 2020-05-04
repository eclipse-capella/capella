package org.polarsys.capella.test.diagram.tools.ju.tb;

import org.polarsys.capella.core.model.helpers.BlockArchitectureExt;
import org.polarsys.capella.test.diagram.common.ju.context.CommonDiagram;
import org.polarsys.capella.test.diagram.common.ju.context.XABDiagram;

public abstract class xABTitleBlockTestCase extends AbstractTitleBlockTestCase {
  protected String diagramName;
  protected BlockArchitectureExt.Type type;

  public xABTitleBlockTestCase() {
    initData();
  }

  @Override
  protected CommonDiagram initDiagram() {
    return XABDiagram.openDiagram(context, diagramName, type);
  }

  public abstract void initData();

}
