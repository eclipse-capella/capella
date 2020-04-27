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
import org.polarsys.capella.core.data.cs.BlockArchitecture;
import org.polarsys.capella.core.model.helpers.BlockArchitectureExt;
import org.polarsys.capella.core.sirius.analysis.IDiagramNameConstants;
import org.polarsys.capella.core.sirius.analysis.constants.IToolNameConstants;
import org.polarsys.capella.test.diagram.common.ju.step.crud.CreateDiagramStep;
import org.polarsys.capella.test.diagram.common.ju.step.crud.OpenDiagramStep;
import org.polarsys.capella.test.diagram.common.ju.step.crud.SetUnsynchronizedStep;
import org.polarsys.capella.test.diagram.common.ju.step.tools.CreateContainerTool;
import org.polarsys.capella.test.diagram.common.ju.step.tools.CreateDEdgeTool;
import org.polarsys.capella.test.diagram.common.ju.step.tools.CreateNodeTool;
import org.polarsys.capella.test.diagram.common.ju.step.tools.InsertRemoveTool;
import org.polarsys.capella.test.framework.context.SessionContext;

public class MSMDiagram extends CommonDiagram {

  BlockArchitectureExt.Type type = null;

  public MSMDiagram(BlockArchitectureExt.Type type, SessionContext context, DDiagram diagram) {
    super(context, diagram);
    this.type = type;
  }

  public static void setSynchronized(MSMDiagram diagram) {
    new SetUnsynchronizedStep(diagram, true).run();
  }

  public static void setUnsynchronized(MSMDiagram diagram) {
    new SetUnsynchronizedStep(diagram).run();
  }

  public static MSMDiagram createDiagram(SessionContext executionContext, String targetIdentifier) {
    BlockArchitecture architecture = BlockArchitectureExt
        .getRootBlockArchitecture(executionContext.getSemanticElement(targetIdentifier));
    final BlockArchitectureExt.Type type = BlockArchitectureExt.getBlockArchitectureType(architecture);

    String name = IDiagramNameConstants.MODES_STATE_MACHINE_DIAGRAM_NAME;

    return (MSMDiagram) new CreateDiagramStep(executionContext, targetIdentifier, name) {
      @Override
      public DiagramContext getResult() {
        return new MSMDiagram(type, getExecutionContext(), diagram);
      }
    }.run().open();
  }

  public static MSMDiagram openDiagram(SessionContext executionContext, String name,
      final BlockArchitectureExt.Type type) {
    return (MSMDiagram) new OpenDiagramStep(executionContext, name) {
      @Override
      public DiagramContext getResult() {
        return new MSMDiagram(type, getExecutionContext(), diagram);
      }
    }.run().open();
  }

  public void createMode(String idContainer, String id) {
    new CreateContainerTool(this, IToolNameConstants.TOOL_MSM_CREATE_MODE, idContainer, id).run();
  }

  public void cannotCreateMode(String idContainer, String id) {
    new CreateContainerTool(this, IToolNameConstants.TOOL_MSM_CREATE_MODE, idContainer, id).cannotRun();
  }

  public void cannotCreateState(String idContainer, String id) {
    new CreateContainerTool(this, IToolNameConstants.TOOL_MSM_CREATE_STATE, idContainer, id).cannotRun();
  }

  public void createState(String idContainer, String id) {
    new CreateContainerTool(this, IToolNameConstants.TOOL_MSM_CREATE_STATE, idContainer, id).run();
  }

  public void createRegion(String idContainer, String id) {
    new CreateContainerTool(this, IToolNameConstants.TOOL_MSM_CREATE_REGION, idContainer, id).run();
  }

  public void createChoice(String containerId) {
    createNodeElement(containerId, IToolNameConstants.TOOL_MSM_CREATE_CHOICE);
  }

  public void createFinal(String containerId) {
    createNodeElement(containerId, IToolNameConstants.TOOL_MSM_CREATE_FINAL);
  }

  public void createFork(String containerId) {
    createNodeElement(containerId, IToolNameConstants.TOOL_MSM_CREATE_FORK);
  }

  public void createInitial(String idContainer, String id) {
    new CreateNodeTool(this, IToolNameConstants.TOOL_MSM_CREATE_INITIAL, idContainer, id).run();
  }

  public void createJoin(String containerId) {
    createNodeElement(containerId, IToolNameConstants.TOOL_MSM_CREATE_JOIN);
  }

  public void createTerminate(String containerId) {
    createNodeElement(containerId, IToolNameConstants.TOOL_MSM_CREATE_TERMINATE);
  }

  public void createDeepHistory(String containerId) {
    createNodeElement(containerId, IToolNameConstants.TOOL_MSM_CREATE_DEEP_HISTORY);
  }

  public void createShallowHistory(String containerId) {
    createNodeElement(containerId, IToolNameConstants.TOOL_MSM_CREATE_SHALLOW_HISTORY);
  }

  public void createEntryPoint(String idTargetContainer, String idContainer, String id) {
    new CreateNodeTool(this, IToolNameConstants.TOOL_MSM_CREATE_ENTRY_POINT, idTargetContainer, idContainer, id).run();
  }

  public void createExitPoint(String idTargetContainer, String idContainer, String id) {
    new CreateNodeTool(this, IToolNameConstants.TOOL_MSM_CREATE_EXIT_POINT, idTargetContainer, idContainer, id).run();
  }

  public void createTransition(String idSource, String idTarget, String id) {
    new CreateDEdgeTool(this, IToolNameConstants.TOOL_MSM_CREATE_TRANSISTION, idSource, idTarget, id).run();
  }

  public void insertReuseModeState(String containerId, String id) {
    new InsertRemoveTool(this, IToolNameConstants.TOOL_MSM_REUSE_MODE_STATE, containerId).insert(id);
  }

  public void removeReuseModeState(String containerId, String id) {
    new InsertRemoveTool(this, IToolNameConstants.TOOL_MSM_REUSE_MODE_STATE, containerId).remove(id);
  }

  public void showStateMode(String containerId, String id) {
    new InsertRemoveTool(this, IToolNameConstants.TOOL_MSM_INSERT_REMOVE_MODE_STATE, containerId).insert(id);
  }

  public void hideStateMode(String containerId, String id) {
    new InsertRemoveTool(this, IToolNameConstants.TOOL_MSM_INSERT_REMOVE_MODE_STATE, containerId).remove(id);
  }

  public void showTransition(String containerId, String id) {
    new InsertRemoveTool(this, IToolNameConstants.TOOL_MSM_INSERT_REMOVE_TRANSITION, containerId).insert(id);
  }

  public void hideTransition(String containerId, String id) {
    new InsertRemoveTool(this, IToolNameConstants.TOOL_MSM_INSERT_REMOVE_TRANSITION, containerId).remove(id);
  }
}
