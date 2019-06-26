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
import org.polarsys.capella.core.data.interaction.MessageKind;
import org.polarsys.capella.core.model.helpers.BlockArchitectureExt;
import org.polarsys.capella.core.sirius.analysis.constants.IToolNameConstants;
import org.polarsys.capella.test.diagram.common.ju.step.crud.OpenDiagramStep;
import org.polarsys.capella.test.diagram.common.ju.step.tools.sequence.MessageCreationTool;
import org.polarsys.capella.test.framework.context.SessionContext;

public class OA_ESDiagram extends ESDiagram {
  public OA_ESDiagram(BlockArchitectureExt.Type type, SessionContext context, DDiagram diagram) {
    super(type, context, diagram);
  }
  
  public String createRole() {
    return createNodeElement(getDiagramId(), IToolNameConstants.TOOL_OES_CREATE_ROLE);
  }
  
  public void createMessage(String source, String target) {
    new MessageCreationTool(this, IToolNameConstants.TOOL_OES_CREATE_MESSAGE, source,
        target, MessageKind.CREATE).run();
  }
  
  public void deleteMessage(String source, String target) {
    new MessageCreationTool(this, IToolNameConstants.TOOL_OES_DELETE_MESSAGE, source,
        target, MessageKind.DELETE).run();
  }
  
  public static OA_ESDiagram openDiagram(SessionContext executionContext, String name,
      final BlockArchitectureExt.Type type) {
    return (OA_ESDiagram) new OpenDiagramStep(executionContext, name) {
      @Override
      public DiagramContext getResult() {
        return new OA_ESDiagram(type, getExecutionContext(), diagram);
      }
    }.run().open();
  }
}
