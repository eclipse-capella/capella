/*******************************************************************************
 * Copyright (c) 2016, 2020 THALES GLOBAL SERVICES.
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

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;
import static org.junit.Assert.assertTrue;

import org.eclipse.emf.ecore.EObject;
import org.eclipse.sirius.diagram.DDiagram;
import org.eclipse.sirius.diagram.DEdge;
import org.polarsys.capella.core.data.information.AbstractEventOperation;
import org.polarsys.capella.core.data.interaction.MessageKind;
import org.polarsys.capella.core.data.interaction.SequenceMessage;
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

  public void createMessage(String id, String source, String target) {
    DEdge edge = new MessageCreationTool(this, IToolNameConstants.TOOL_OES_CREATE_MESSAGE, id, source, target,
        MessageKind.CREATE).run();

    assertMessageCreationTarget(id, edge);
  }

  public void deleteMessage(String id, String source, String target) {
    DEdge edge = new MessageCreationTool(this, IToolNameConstants.TOOL_OES_DELETE_MESSAGE, id, source, target,
        MessageKind.DELETE).run();

    assertMessageCreationTarget(id, edge);
  }

  private void assertMessageCreationTarget(String id, DEdge edge) {
    EObject edgeTarget = edge.getTarget();
    assertTrue("The target of the edge should be a SequenceMessage", edgeTarget instanceof SequenceMessage);
    SequenceMessage message = (SequenceMessage) edgeTarget;

    AbstractEventOperation invokedOperation = message.getInvokedOperation();
    assertNotEquals("The Sequence Message should have a Invoked Operation", invokedOperation, null);

    assertEquals("The Invoked operation should have the name " + id, id, invokedOperation.getName());
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
