/*******************************************************************************
 * Copyright (c) 2016 THALES GLOBAL SERVICES.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *  
 * Contributors:
 *    Thales - initial API and implementation
 *******************************************************************************/
package org.polarsys.capella.test.diagram.common.ju.context;

import org.eclipse.sirius.diagram.DDiagram;
import org.polarsys.capella.core.data.pa.PhysicalComponentNature;
import org.polarsys.capella.core.model.helpers.BlockArchitectureExt;
import org.polarsys.capella.core.sirius.analysis.constants.IToolNameConstants;
import org.polarsys.capella.test.diagram.common.ju.step.tools.CreateNodeTool;

public class PA_ESDiagram extends ESDiagram {
  public PA_ESDiagram(BlockArchitectureExt.Type type, SessionContext context, DDiagram diagram) {
    super(type, context, diagram);
  }
  
  public void createComponent(String id, PhysicalComponentNature compNature) {
    String name = null;
    if (compNature == PhysicalComponentNature.BEHAVIOR) {
      name = IToolNameConstants.TOOL_ES_CREATE_BEHAVIOR_PC;
    } else if (compNature == PhysicalComponentNature.NODE) {
      name = IToolNameConstants.TOOL_ES_CREATE_NODE_PC;
    }
    new CreateNodeTool(this, name, getDiagramId(), id).run();
  }
}
