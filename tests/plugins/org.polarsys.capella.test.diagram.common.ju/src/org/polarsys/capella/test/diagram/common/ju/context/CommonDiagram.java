/*******************************************************************************
 * Copyright (c) 2016, 2019 THALES GLOBAL SERVICES.
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
import org.eclipse.sirius.diagram.DNodeListElement;
import org.eclipse.sirius.viewpoint.description.DAnnotation;
import org.polarsys.capella.core.data.capellacore.CapellaElement;
import org.polarsys.capella.core.sirius.analysis.constants.IDNDToolNameConstants;
import org.polarsys.capella.core.sirius.analysis.constants.IToolNameConstants;
import org.polarsys.capella.test.diagram.common.ju.step.tools.CreateAbstractDNodeTool;
import org.polarsys.capella.test.diagram.common.ju.step.tools.CreateContainerTool;
import org.polarsys.capella.test.diagram.common.ju.step.tools.CreateDEdgeTool;
import org.polarsys.capella.test.diagram.common.ju.step.tools.DragAndDropFromProjectExplorerTool;
import org.polarsys.capella.test.diagram.common.ju.step.tools.DragAndDropTool;
import org.polarsys.capella.test.diagram.common.ju.step.tools.InsertRemoveTool;
import org.polarsys.capella.test.diagram.common.ju.step.tools.SelectTool;
import org.polarsys.capella.test.diagram.common.ju.step.tools.titleblocks.CreateDiagramTitleBlockTool;
import org.polarsys.capella.test.diagram.common.ju.step.tools.titleblocks.CreateElementTitleBlockTool;
import org.polarsys.capella.test.diagram.common.ju.step.tools.titleblocks.InsertColumnInTitleBlockTool;
import org.polarsys.capella.test.diagram.common.ju.step.tools.titleblocks.InsertLineInTitleBlockTool;
import org.polarsys.capella.test.diagram.common.ju.step.tools.titleblocks.InsertRemoveTitleBlockTool;
import org.polarsys.capella.test.diagram.common.ju.step.tools.titleblocks.RemoveColumnFromTitleBlockTool;
import org.polarsys.capella.test.diagram.common.ju.step.tools.titleblocks.RemoveLineFromTitleBlockTool;
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

  public DAnnotation createDiagramTitleBlock() {
    DDiagramElementContainer createdTB = new CreateDiagramTitleBlockTool(this,
        IToolNameConstants.TOOL_CREATE_DIAGRAM_TITLE_BLOCK, getDiagramId()).run();
    return (DAnnotation) createdTB.getTarget();
  }

  public DAnnotation createElementTitleBlock(String containerId) {
    DDiagramElementContainer createdTB = new CreateElementTitleBlockTool(this,
        IToolNameConstants.TOOL_CREATE_ELEMENT_TITLE_BLOCK, containerId, getDiagramId()).run();
    return (DAnnotation) createdTB.getTarget();
  }

  public void checkCreateElementTitleBlock(String containerId) {
    new CreateElementTitleBlockTool(this, IToolNameConstants.TOOL_CREATE_ELEMENT_TITLE_BLOCK, containerId,
        getDiagramId()).contextOk();
  }

  public void checkCreateDiagramTitleBlock() {
    new CreateDiagramTitleBlockTool(this, IToolNameConstants.TOOL_CREATE_DIAGRAM_TITLE_BLOCK, getDiagramId())
        .contextOk();
  }

  public void checkAutocreateDiagramTitleBlock() {
    new CreateDiagramTitleBlockTool(this, IToolNameConstants.TOOL_CREATE_DIAGRAM_TITLE_BLOCK, getDiagramId())
        .checkAutocreate();
  }

  public void removeElementTitleBlock(String id) {
    new InsertRemoveTitleBlockTool(this, IToolNameConstants.TOOL_INSERT_REMOVE_ELEMENT_TITLE_BLOCK).remove(id);
  }

  public void insertElementTitleBlock(String id) {
    new InsertRemoveTitleBlockTool(this, IToolNameConstants.TOOL_INSERT_REMOVE_ELEMENT_TITLE_BLOCK).insert(id);
  }

  public void removeDiagramTitleBlock(String id) {
    new InsertRemoveTitleBlockTool(this, IToolNameConstants.TOOL_INSERT_REMOVE_DIAGRAM_TITLE_BLOCK).remove(id);
  }

  public void insertDiagramTitleBlock(String id) {
    new InsertRemoveTitleBlockTool(this, IToolNameConstants.TOOL_INSERT_REMOVE_DIAGRAM_TITLE_BLOCK).insert(id);
  }

  public DAnnotation insertLineInTitleBlock(DAnnotation titleBlock, int lineNo) {
    DDiagramElementContainer createdTB = new InsertLineInTitleBlockTool(this,
        IToolNameConstants.TOOL_INSERT_LINE_TITLE_BLOCK, getDiagramId(), titleBlock, lineNo).run();
    return (DAnnotation) createdTB.getTarget();
  }

  public DAnnotation insertColumnInTitleBlock(DAnnotation titleBlock, int colNo) {
    DDiagramElementContainer createdTB = new InsertColumnInTitleBlockTool(this,
        IToolNameConstants.TOOL_INSERT_COLUMN_TITLE_BLOCK, getDiagramId(), titleBlock, colNo).run();
    return (DAnnotation) createdTB.getTarget();
  }

  public DAnnotation removeLineFromTitleBlock(DAnnotation titleBlock, int lineNo) {
    DDiagramElementContainer createdTB = new RemoveLineFromTitleBlockTool(this,
        IToolNameConstants.TOOL_REMOVE_LINE_TITLE_BLOCK, getDiagramId(), titleBlock, lineNo).run();
    return (DAnnotation) createdTB.getTarget();
  }

  public DAnnotation removeColumnFromTitleBlock(DAnnotation titleBlock, int colNo) {
    DDiagramElementContainer createdTB = new RemoveColumnFromTitleBlockTool(this,
        IToolNameConstants.TOOL_REMOVE_COLUMN_TITLE_BLOCK, getDiagramId(), titleBlock, colNo).run();
    return (DAnnotation) createdTB.getTarget();
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

  public void dragAndDropConstraintFromExplorer(String idDraggedElement, String containerId) {
    dragAndDropFromExplorer(idDraggedElement, containerId, IDNDToolNameConstants.TOOL_DND_CONSTRAINT_FROM_EXPLORER);
  }

  public void dragAndDropConstraintsFromExplorer(String idDraggedElement, String containerId) {
    dragAndDropFromExplorer(idDraggedElement, containerId, IDNDToolNameConstants.TOOL_DND_CONSTRAINTS_FROM_EXPLORER);
  }

  public void dragAndDropCComponentFromExplorer(String idDraggedElement, String containerId) {
    dragAndDropFromExplorer(idDraggedElement, containerId, IDNDToolNameConstants.TOOL_DND_CONSTRAINT_FROM_EXPLORER);
  }

  protected String createNodeElement(String containerId, String toolName) {

    DNode graphicalElement = new CreateAbstractDNodeTool<DNode>(this, toolName, containerId).run();

    customVerificationOnCreatedNodeElement(toolName, graphicalElement, containerId);

    return getSemanticIdFromView(graphicalElement);
  }

  protected String createNodeListElement(String containerId, String toolName) {

    DNodeListElement graphicalElement = new CreateAbstractDNodeTool<DNodeListElement>(this, toolName, containerId)
        .run();

    return getSemanticIdFromView(graphicalElement);
  }

  protected String createNodeElement(String targetId, String containerId, String toolName) {

    DNode graphicalElement = new CreateAbstractDNodeTool<DNode>(this, toolName, targetId, containerId, null,
        DNode.class).run();

    customVerificationOnCreatedNodeElement(toolName, graphicalElement, containerId);

    return getSemanticIdFromView(graphicalElement);
  }

  protected String createContainerElement(String containerId, String toolName) {

    DDiagramElementContainer graphicalElement = new CreateContainerTool(this, toolName, containerId).run();

    customVerificationOnCreatedContainerElement(toolName, graphicalElement, containerId);

    return getSemanticIdFromView(graphicalElement);
  }

  protected String createEdgeElement(String sourceViewId, String targetViewId, String toolName) {

    DEdge graphicalElement = new CreateDEdgeTool(this, toolName, sourceViewId, targetViewId).run();

    customVerificationOnCreatedEdgeElement(toolName, graphicalElement, sourceViewId, targetViewId);

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

  protected void dragAndDrop(String idDraggedElement, String containerId, String toolName) {
    new DragAndDropTool(this, toolName, idDraggedElement, containerId).run();
  }

  protected String dragAndDropFromExplorer(String idDraggedElement, String containerId, String toolName) {
    EObject droppedElementSemantic = getSessionContext().getSemanticElement(idDraggedElement);
    DDiagramElement elem = new DragAndDropFromProjectExplorerTool(this, toolName, droppedElementSemantic, containerId)
        .run();
    return getSemanticIdFromView(elem);
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

  @Override
  protected String getSemanticIdFromView(DDiagramElement view) {

    EObject semanticElement = view.getTarget();

    return ((CapellaElement) semanticElement).getId();
  }

}
