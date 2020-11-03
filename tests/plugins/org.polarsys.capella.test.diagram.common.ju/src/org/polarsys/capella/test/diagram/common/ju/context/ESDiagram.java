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

import org.eclipse.sirius.diagram.DDiagram;
import org.polarsys.capella.core.data.cs.BlockArchitecture;
import org.polarsys.capella.core.model.helpers.BlockArchitectureExt;
import org.polarsys.capella.core.model.helpers.BlockArchitectureExt.Type;
import org.polarsys.capella.core.sirius.analysis.IDiagramNameConstants;
import org.polarsys.capella.core.sirius.analysis.constants.IToolNameConstants;
import org.polarsys.capella.test.diagram.common.ju.step.crud.CreateDiagramStep;
import org.polarsys.capella.test.diagram.common.ju.step.crud.OpenDiagramStep;
import org.polarsys.capella.test.diagram.common.ju.step.tools.sequence.LostFoundMessageCreationTool;
import org.polarsys.capella.test.diagram.common.ju.step.tools.sequence.MessageCreationTool;
import org.polarsys.capella.test.framework.context.SessionContext;

public class ESDiagram extends SequenceDiagram {
  public ESDiagram(BlockArchitectureExt.Type type, SessionContext context, DDiagram diagram) {
    super(type, context, diagram);
  }

  public static ESDiagram createDiagram(SessionContext executionContext, String targetIdentifier) {
    BlockArchitecture architecture = BlockArchitectureExt
        .getRootBlockArchitecture(executionContext.getSemanticElement(targetIdentifier));
    final BlockArchitectureExt.Type type = BlockArchitectureExt.getBlockArchitectureType(architecture);

    String name = null;
    if (type == Type.OA) {
      name = IDiagramNameConstants.OPERATIONAL_INTERACTION_SCENARIO_DIAGRAM_NAME;
    } else {
      name = IDiagramNameConstants.DATA_FLOW_SCENARIO_DIAGRAM_NAME;
    }

    if (type == Type.OA)
      return (OA_ESDiagram) new CreateDiagramStep(executionContext, targetIdentifier, name) {
        @Override
        public DiagramContext getResult() {
          return new OA_ESDiagram(type, getExecutionContext(), diagram);
        }
      }.run().open();
    if (type == Type.SA)
      return (SA_ESDiagram) new CreateDiagramStep(executionContext, targetIdentifier, name) {
        @Override
        public DiagramContext getResult() {
          return new SA_ESDiagram(type, getExecutionContext(), diagram);
        }
      }.run().open();
    else if (type == Type.PA)
      return (PA_ESDiagram) new CreateDiagramStep(executionContext, targetIdentifier, name) {
        @Override
        public DiagramContext getResult() {
          return new PA_ESDiagram(type, getExecutionContext(), diagram);
        }
      }.run().open();

    return (ESDiagram) new CreateDiagramStep(executionContext, targetIdentifier, name) {
      @Override
      public DiagramContext getResult() {
        return new ESDiagram(type, getExecutionContext(), diagram);
      }
    }.run().open();
  }

  public static ESDiagram openDiagram(SessionContext executionContext, String name,
      final BlockArchitectureExt.Type type) {
    return (ESDiagram) new OpenDiagramStep(executionContext, name) {
      @Override
      public DiagramContext getResult() {
        return new ESDiagram(type, getExecutionContext(), diagram);
      }
    }.run().open();
  }

  public String createComponent() {
    return createComponent(getDiagramId());
  }

  public String insertComponent(String id) {
    InsertRemoveInstanceRoleTool tool = new InsertRemoveInstanceRoleTool(this,
        IToolNameConstants.TOOL_INSERT_REMOVE_COMPONENTS);
    tool.insert(id);
    return id;
  }

  public String removeComponent(String id) {
    InsertRemoveInstanceRoleTool tool = new InsertRemoveInstanceRoleTool(this,
        IToolNameConstants.TOOL_INSERT_REMOVE_COMPONENTS);
    tool.remove(id);
    return id;
  }

  public String createComponent(String containerId) {
    return createNodeElement(containerId, IToolNameConstants.TOOL_CREATE_COMPONENT);
  }

  public void createComponentExchange(String componentExchange, String source, String target) {
    new MessageCreationTool(this, IToolNameConstants.TOOL_CREATE_COMPONENT_EXCHANGE, componentExchange, source, target)
        .run();
  }

  public void createComponentExchangeWithReturnBranch(String componentExchange, String source, String target) {
    new MessageCreationTool(this, IToolNameConstants.TOOL_CREATE_COMPONENT_EXCHANGE_WITH_RETURN_BRANCH,
        componentExchange, source, target, true).run();
  }

  // creates a sequence message that has only receiving end
  public void createFoundFunctionalExchange(String functionalExchange, String target) {
    String name = IToolNameConstants.TOOL_CREATE_FOUND_FUNCTIONAL_EXCHANGE;
    new LostFoundMessageCreationTool(this, name, target, functionalExchange, null, target).run();
  }

  // creates a sequence message that has only sending end
  public void createLostFunctionalExchange(String functionalExchange, String source) {
    String name = IToolNameConstants.TOOL_LOST_FUNCTIONAL_EXCHANGE;
    new LostFoundMessageCreationTool(this, name, source, functionalExchange, source, null).run();
  }
}
