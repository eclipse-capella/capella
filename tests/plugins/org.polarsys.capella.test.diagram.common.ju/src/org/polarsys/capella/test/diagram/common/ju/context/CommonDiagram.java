/*******************************************************************************
 * Copyright (c) 2016, 2018 THALES GLOBAL SERVICES.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *  
 * Contributors:
 *    Thales - initial API and implementation
 *******************************************************************************/
package org.polarsys.capella.test.diagram.common.ju.context;

import org.eclipse.emf.ecore.EObject;
import org.eclipse.sirius.diagram.DDiagram;
import org.eclipse.sirius.diagram.DDiagramElement;
import org.eclipse.sirius.diagram.DDiagramElementContainer;
import org.eclipse.sirius.diagram.DEdge;
import org.eclipse.sirius.diagram.DNode;
import org.polarsys.capella.core.data.capellacore.CapellaElement;
import org.polarsys.capella.core.sirius.analysis.constants.IToolNameConstants;
import org.polarsys.capella.test.diagram.common.ju.step.tools.CreateAbstractDNodeTool;
import org.polarsys.capella.test.diagram.common.ju.step.tools.CreateContainerTool;
import org.polarsys.capella.test.diagram.common.ju.step.tools.CreateDEdgeTool;
import org.polarsys.capella.test.diagram.common.ju.step.tools.InsertRemoveTool;
import org.polarsys.capella.test.diagram.common.ju.step.tools.SelectTool;
import org.polarsys.capella.test.framework.context.SessionContext;

public class CommonDiagram extends DiagramContext {
  public CommonDiagram(SessionContext context, DDiagram diagram) {
    super(context, diagram);
  }

  public String createConstraint(String id) {
    return createConstraint(id, getDiagramId());
  }

  public String createConstraint(String id, String containerId) {
    // All diagrams shared the same tool
    String name = IToolNameConstants.TOOL_CC_CREATE_CONSTRAINT;
    DNode createdConstraint = new CreateAbstractDNodeTool<DNode>(this, name, containerId, id).run();

    return getSemanticIdFromView(createdConstraint);
  }

  public void createConstrainedElement(String sourceId, String targetId) {
    // All diagrams shared the same tool
    String name = IToolNameConstants.TOOL_CC_CREATE_CONSTRAINTELEMENT;
    new CreateDEdgeTool(this, name, sourceId, targetId).run();
  }

  public void removeConstraint(String id, String containerId) {
    new InsertRemoveTool(this, IToolNameConstants.TOOL_CC_INSERT_REMOVE_CONSTRAINTS, containerId).remove(id);
  }

  public void insertConstraint(String id, String containerId) {
    new InsertRemoveTool(this, IToolNameConstants.TOOL_CC_INSERT_REMOVE_CONSTRAINTS, containerId).insert(id);
  }

  public void insertPV(String id, String containerId) {
    new InsertRemoveTool(this, IToolNameConstants.TOOL_INSERT_REMOVE_PV, containerId).insert(id);
  }

  public void removePV(String id, String containerId) {
    new InsertRemoveTool(this, IToolNameConstants.TOOL_INSERT_REMOVE_PV, containerId).remove(id);
  }

  public void insertPVG(String id, String containerId) {
    new InsertRemoveTool(this, IToolNameConstants.TOOL_INSERT_REMOVE_PVG, containerId).insert(id);
  }

  public void removePVG(String id, String containerId) {
    new InsertRemoveTool(this, IToolNameConstants.TOOL_INSERT_REMOVE_PVG, containerId).remove(id);
  }

  public void selectSameType(String id) {
    new SelectTool(this, IToolNameConstants.TOOL_COMMON_SELECT_SAME_TYPE).ensurePrecondition(true).select(id);
  }

  public void selectSameMapping(String id) {
    new SelectTool(this, IToolNameConstants.TOOL_COMMON_SELECT_SAME_MAPPING).ensurePrecondition(true).select(id);
  }

  public void selectOwnedPorts(String id) {
    new SelectTool(this, IToolNameConstants.TOOL_COMMON_SELECT_OWNED_PORTS).ensurePrecondition(true).select(id);
  }

  public void selectOwnedElements(String id) {
    new SelectTool(this, IToolNameConstants.TOOL_COMMON_SELECT_OWNED_ELEMENTS).ensurePrecondition(true).select(id);
  }

  public void selectRelatedEdges(String id) {
    new SelectTool(this, IToolNameConstants.TOOL_COMMON_SELECT_RELATED_EDGES).ensurePrecondition(true).select(id);
  }

  protected String createNodeElement(String containerId, String toolName) {

    DNode graphicalElement = new CreateAbstractDNodeTool<DNode>(this, toolName, containerId).run();

    customVerificationOnCreatedNodeElement(toolName, graphicalElement, containerId);

    return getSemanticIdFromView(graphicalElement);
  }

  protected String createContainerElement(String containerId, String toolName) {

    DDiagramElementContainer graphicalElement = new CreateContainerTool(this, toolName, containerId).run();

    customVerificationOnCreatedContainerElement(toolName, graphicalElement, containerId);

    return getSemanticIdFromView(graphicalElement);
  }

  protected String createEdgeElement(String sourceViewId, String targetViewId, String toolName, int newEdgesNumber) {

    DEdge graphicalElement = new CreateDEdgeTool(this, toolName, sourceViewId, targetViewId, newEdgesNumber, 0, 0)
        .run();

    customVerificationOnCreatedEdgeElement(toolName, graphicalElement, sourceViewId, targetViewId);

    return getSemanticIdFromView(graphicalElement);
  }

  protected void insertElements(String containerId, String toolName, String... elementsToBeInsertedIds) {
    new InsertRemoveTool(this, toolName, containerId).insert(elementsToBeInsertedIds);
  }

  protected void removeElements(String containerId, String toolName, String... elementsToBeRemovedIds) {
    new InsertRemoveTool(this, toolName, containerId).remove(elementsToBeRemovedIds);
  }

  /*
   * CHECK FUNCTIONS
   */
  protected void customVerificationOnCreatedNodeElement(String toolName, DNode graphicalElement, String containerId) {
  }

  protected void customVerificationOnCreatedEdgeElement(String toolName, DDiagramElement view, String sourceId,
      String targetId) {
  }

  protected void customVerificationOnCreatedContainerElement(String toolName, DDiagramElementContainer graphicalElement,
      String containerId) {
  }

  protected String getSemanticIdFromView(DDiagramElement view) {

    EObject semanticElement = view.getTarget();

    return ((CapellaElement) semanticElement).getId();
  }
}
