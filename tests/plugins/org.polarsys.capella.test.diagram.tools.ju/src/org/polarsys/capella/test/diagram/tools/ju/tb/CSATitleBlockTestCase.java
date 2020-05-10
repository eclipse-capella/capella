package org.polarsys.capella.test.diagram.tools.ju.tb;

import org.polarsys.capella.test.diagram.common.ju.context.CSADiagram;
import org.polarsys.capella.test.diagram.common.ju.context.CommonDiagram;

public abstract class CSATitleBlockTestCase extends AbstractTitleBlockTestCase {
  protected String diagramName;

  public CSATitleBlockTestCase() {
    initData();
  }

  @Override
  protected CommonDiagram initDiagram() {
    return CSADiagram.openDiagram(context, diagramName);
  }

  protected abstract void initData();
}

