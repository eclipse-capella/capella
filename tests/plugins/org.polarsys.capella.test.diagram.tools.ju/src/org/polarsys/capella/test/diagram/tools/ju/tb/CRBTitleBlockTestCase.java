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
package org.polarsys.capella.test.diagram.tools.ju.tb;

import org.polarsys.capella.core.model.helpers.BlockArchitectureExt;
import org.polarsys.capella.test.diagram.common.ju.context.CommonDiagram;
import org.polarsys.capella.test.diagram.common.ju.context.MissionDiagram;

public abstract class CRBTitleBlockTestCase extends AbstractTitleBlockTestCase {
  protected String diagramName;
  protected String capabilityPKG;
  protected BlockArchitectureExt.Type type;
  private static final String DEFAULT_NAME = "Capability Realization Blank";


  public CRBTitleBlockTestCase() {
    initData();
  }

  @Override
  protected CommonDiagram initDiagram() {
    return MissionDiagram.openDiagram(context, diagramName, type);
  }

  @Override
  protected CommonDiagram createDiagram() {
    return MissionDiagram.createDiagram(context, capabilityPKG, DEFAULT_NAME);
  }

  public abstract void initData();
}
