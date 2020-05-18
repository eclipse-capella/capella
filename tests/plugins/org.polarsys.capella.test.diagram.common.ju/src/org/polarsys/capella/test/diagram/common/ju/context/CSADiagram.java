/*******************************************************************************
 * Copyright (c) 2019 THALES GLOBAL SERVICES.
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
import org.eclipse.sirius.diagram.DNode;
import org.polarsys.capella.core.sirius.analysis.IDiagramNameConstants;
import org.polarsys.capella.core.sirius.analysis.constants.IToolNameConstants;
import org.polarsys.capella.core.sirius.analysis.helpers.ToolProviderHelper;
import org.polarsys.capella.test.diagram.common.ju.step.crud.CreateDiagramStep;
import org.polarsys.capella.test.diagram.common.ju.step.tools.CreateAbstractDNodeTool;
import org.polarsys.capella.test.diagram.common.ju.step.tools.InsertRemoveTool;
import org.polarsys.capella.test.diagram.common.ju.step.tools.ReconnectTool;
import org.polarsys.capella.test.framework.context.SessionContext;

public class CSADiagram extends CommonDiagram {

  public CSADiagram(SessionContext context, DDiagram diagram) {
    super(context, diagram);
  }

  public static CSADiagram createDiagram(SessionContext executionContext, String diagramKind, String targetIdentifier) {
    return (CSADiagram) new CreateDiagramStep(executionContext, targetIdentifier, diagramKind) {
      @Override
      public DiagramContext getResult() {
        return new CSADiagram(getExecutionContext(), diagram);
      }
    }.run().open();
  }

  public static CSADiagram createDiagram(SessionContext executionContext, String targetIdentifier) {
    return createDiagram(executionContext, IDiagramNameConstants.CONTEXTUAL_SYSTEM_ACTORS_DIAGRAM_NAME,
        targetIdentifier);
  }

  public String createActor() {
    DNode graphicalElement = new CreateAbstractDNodeTool<DNode>(this, IToolNameConstants.TOOL_CSA_CREATE_ACTOR,
        getDiagramId()) {
      protected int expectedNewElements() {
        return 2;
      };
    }.run();
    customVerificationOnCreatedNodeElement(IToolNameConstants.TOOL_CSA_CREATE_ACTOR, graphicalElement, getDiagramId());
    return getSemanticIdFromView(graphicalElement);
  }

  public String createActorGeneralization(String idTarget, String idSource) {
    return createEdgeElement(idSource, idTarget,
        ToolProviderHelper.getToolCreateActorGeneralization(this.getDiagram()));
  }

  public void insertConstraints(String... id) {
    new InsertRemoveTool(this, IToolNameConstants.TOOL_CSA_INSERT_REMOVE_CONSTRAINTS).insert(id);
  }

  public void removeConstraints(String... id) {
    new InsertRemoveTool(this, IToolNameConstants.TOOL_CSA_INSERT_REMOVE_CONSTRAINTS).remove(id);
  }

  public void reconnectActorGeneralizationSource(String edgeId, String oldSourceId, String newSourceId) {
    new ReconnectTool(this, IToolNameConstants.TOOL_CSA_RECONNECT_GENERALIZATION_SOURCE, edgeId, oldSourceId, newSourceId).run();
  }

  public void reconnectActorGeneralizationTarget(String edgeId, String oldTargetId, String newTargetId) {
    new ReconnectTool(this, IToolNameConstants.TOOL_CSA_RECONNECT_GENERALIZATION_TARGET, edgeId, oldTargetId, newTargetId).run();
  }

}
