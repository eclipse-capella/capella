package org.polarsys.capella.test.diagram.tools.ju.tb;

import org.polarsys.capella.test.diagram.common.ju.context.CSADiagram;
import org.polarsys.capella.test.diagram.common.ju.context.CommonDiagram;

public abstract class CSATitleBlockTestCase extends AbstractTitleBlockTestCase {
  protected String diagramName;
  protected String systemPKG;

  public CSATitleBlockTestCase() {
    initData();
  }

  @Override
  protected CommonDiagram initDiagram() {
    return CSADiagram.openDiagram(context, diagramName);
  }

  @Override
  protected CommonDiagram createDiagram() {
    return CSADiagram.createDiagram(context, systemPKG);
  }

  protected abstract void initData();
}

