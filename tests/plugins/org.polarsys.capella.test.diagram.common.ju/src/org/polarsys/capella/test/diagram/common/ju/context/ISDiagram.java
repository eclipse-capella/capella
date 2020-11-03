/*******************************************************************************
 * Copyright (c) 2018, 2020 THALES GLOBAL SERVICES.
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
import org.polarsys.capella.core.data.cs.BlockArchitecture;
import org.polarsys.capella.core.data.interaction.MessageKind;
import org.polarsys.capella.core.model.helpers.BlockArchitectureExt;
import org.polarsys.capella.core.sirius.analysis.IDiagramNameConstants;
import org.polarsys.capella.core.sirius.analysis.constants.IToolNameConstants;
import org.polarsys.capella.test.diagram.common.ju.step.crud.CreateDiagramStep;
import org.polarsys.capella.test.diagram.common.ju.step.crud.OpenDiagramStep;
import org.polarsys.capella.test.diagram.common.ju.step.tools.sequence.MessageCreationTool;
import org.polarsys.capella.test.framework.context.SessionContext;

public class ISDiagram extends SequenceDiagram {

  public ISDiagram(BlockArchitectureExt.Type type, SessionContext context, DDiagram diagram) {
    super(type, context, diagram);
  }

  public static ISDiagram createDiagram(SessionContext executionContext, String targetIdentifier) {
    BlockArchitecture architecture = BlockArchitectureExt
        .getRootBlockArchitecture(executionContext.getSemanticElement(targetIdentifier));
    final BlockArchitectureExt.Type type = BlockArchitectureExt.getBlockArchitectureType(architecture);

    if (type == BlockArchitectureExt.Type.OA) {
      throw new UnsupportedOperationException();
    }

    String name = IDiagramNameConstants.INTERFACE_SCENARIO_DIAGRAM_NAME;
    return (ISDiagram) new CreateDiagramStep(executionContext, targetIdentifier, name) {
      @Override
      public DiagramContext getResult() {
        return new ISDiagram(type, getExecutionContext(), diagram);
      }
    }.run().open();
  }

  public static ISDiagram openDiagram(SessionContext executionContext, String name,
      final BlockArchitectureExt.Type type) {
    return (ISDiagram) new OpenDiagramStep(executionContext, name) {
      @Override
      public DiagramContext getResult() {
        return new ISDiagram(type, getExecutionContext(), diagram);
      }
    }.run().open();
  }

  public void createMessage(String id, String source, String target) {
    new MessageCreationTool(this, IToolNameConstants.TOOL_IS_CREATE_MESSAGE, id, source, target, MessageKind.CREATE)
        .run();
  }

  public void deleteMessage(String id, String source, String target) {
    new MessageCreationTool(this, IToolNameConstants.TOOL_IS_DELETE_MESSAGE, id, source, target, MessageKind.DELETE)
        .run();
  }

  public void createSequenceMessage(String id, String source, String target) {
    new MessageCreationTool(this, IToolNameConstants.TOOL_IS_ASYNCHRONOUS_MESSAGE, id, source, target).run();
  }

  public void createSequenceMessageWithReturnBranch(String id, String source, String target) {
    new MessageCreationTool(this, IToolNameConstants.TOOL_IS_SYNCHRONOUS_MESSAGE, id, source, target, true).run();
  }

  // Unsupported opperations
  @Override
  public void createFunctionalExchange(String functionalExchange, String source, String target) {
    throw new UnsupportedOperationException();
  }

  @Override
  public void createFunctionalExchangeWithReturnBranch(String functionalExchange, String source, String target) {
    throw new UnsupportedOperationException();
  }
}
