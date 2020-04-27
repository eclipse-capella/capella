/*******************************************************************************
 * Copyright (c) 2016, 2019 THALES GLOBAL SERVICES.
 * 
 * This program and the accompanying materials are made available under the
 * terms of the Eclipse Public License 2.0 which is available at
 * http://www.eclipse.org/legal/epl-2.0
 * 
 * SPDX-License-Identifier: EPL-2.0
 *  
 * Contributors:
 *    Thales - initial API and implementation
 *******************************************************************************/
package org.polarsys.capella.test.diagram.common.ju.context;

import org.eclipse.sirius.diagram.DDiagram;
import org.polarsys.capella.core.data.pa.PhysicalComponentNature;
import org.polarsys.capella.core.model.helpers.BlockArchitectureExt;
import org.polarsys.capella.core.sirius.analysis.constants.IToolNameConstants;
import org.polarsys.capella.test.diagram.common.ju.step.crud.OpenDiagramStep;
import org.polarsys.capella.test.framework.context.SessionContext;

public class PA_ESDiagram extends ESDiagram {
  public PA_ESDiagram(BlockArchitectureExt.Type type, SessionContext context, DDiagram diagram) {
    super(type, context, diagram);
  }
  
  public String createComponent(PhysicalComponentNature compNature) {
    String tool = null;
    if (compNature == PhysicalComponentNature.BEHAVIOR) {
      tool = IToolNameConstants.TOOL_CREATE_BEHAVIOR_COMPONENT;
    } else if (compNature == PhysicalComponentNature.NODE) {
      tool = IToolNameConstants.TOOL_CREATE_NODE_COMPONENT;
    }
    return createNodeElement(getDiagramId(), tool);
  }
  
  public static PA_ESDiagram openDiagram(SessionContext executionContext, String name,
      final BlockArchitectureExt.Type type) {
    return (PA_ESDiagram) new OpenDiagramStep(executionContext, name) {
      @Override
      public DiagramContext getResult() {
        return new PA_ESDiagram(type, getExecutionContext(), diagram);
      }
    }.run().open();
  }
}
