/*******************************************************************************
 * Copyright (c) 2016, 2023 THALES GLOBAL SERVICES.
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

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.fail;

import java.util.Arrays;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.eclipse.core.commands.ExecutionEvent;
import org.eclipse.core.commands.ExecutionException;
import org.eclipse.core.expressions.EvaluationContext;
import org.eclipse.core.expressions.IEvaluationContext;
import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.gef.EditPart;
import org.eclipse.gmf.runtime.notation.View;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.jface.viewers.StructuredSelection;
import org.eclipse.sirius.diagram.DDiagram;
import org.eclipse.sirius.diagram.DDiagramElement;
import org.eclipse.sirius.diagram.DDiagramElementContainer;
import org.eclipse.sirius.diagram.DEdge;
import org.eclipse.sirius.diagram.DNode;
import org.eclipse.sirius.diagram.DNodeListElement;
import org.eclipse.sirius.viewpoint.DSemanticDecorator;
import org.eclipse.sirius.viewpoint.description.DAnnotation;
import org.eclipse.ui.ISources;
import org.eclipse.ui.PlatformUI;
import org.polarsys.capella.common.re.CatalogElement;
import org.polarsys.capella.common.re.ui.menu.RecDynamicMenu;
import org.polarsys.capella.common.re.ui.menu.RplDynamicMenu;
import org.polarsys.capella.core.data.capellacore.CapellaElement;
import org.polarsys.capella.core.platform.sirius.ui.navigator.actions.ShowInDiagramAction;
import org.polarsys.capella.core.sirius.analysis.DiagramServices;
import org.polarsys.capella.core.sirius.analysis.constants.IDNDToolNameConstants;
import org.polarsys.capella.core.sirius.analysis.constants.IToolNameConstants;
import org.polarsys.capella.core.sirius.ui.handlers.AbstractSelectInEditorCommandHandler;
import org.polarsys.capella.core.sirius.ui.handlers.SelectElementsOfSameTypeCommandHandler;
import org.polarsys.capella.core.sirius.ui.handlers.SelectOwnedElementsCommandHandler;
import org.polarsys.capella.core.sirius.ui.handlers.SelectOwnedPortsCommandHandler;
import org.polarsys.capella.core.sirius.ui.handlers.SelectRelatedConnectionsCommandHandler;
import org.polarsys.capella.core.sirius.ui.handlers.SelectRelatedFCElementsCommandHandler;
import org.polarsys.capella.core.sirius.ui.handlers.SelectRelatedPPElementsCommandHandler;
import org.polarsys.capella.core.sirius.ui.handlers.SelectResemblingElementsCommandHandler;
import org.polarsys.capella.test.diagram.common.ju.step.tools.CreateAbstractDNodeTool;
import org.polarsys.capella.test.diagram.common.ju.step.tools.CreateContainerTool;
import org.polarsys.capella.test.diagram.common.ju.step.tools.CreateDEdgeTool;
import org.polarsys.capella.test.diagram.common.ju.step.tools.DeleteElementTool;
import org.polarsys.capella.test.diagram.common.ju.step.tools.DragAndDropFromProjectExplorerTool;
import org.polarsys.capella.test.diagram.common.ju.step.tools.DragAndDropTool;
import org.polarsys.capella.test.diagram.common.ju.step.tools.InsertRemoveTool;
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

  public DDiagramElement createDiagramTitleBlock() {
    DDiagramElementContainer createdTB = new CreateDiagramTitleBlockTool(this,
        IToolNameConstants.TOOL_CREATE_TITLE_BLOCK, getDiagramId()).run();
    return createdTB;
  }

  public DDiagramElement createElementTitleBlock(String containerId) {
    DDiagramElementContainer createdTB = new CreateElementTitleBlockTool(this,
        IToolNameConstants.TOOL_CREATE_TITLE_BLOCK, containerId, getDiagramId()).run();
    return createdTB;
  }

  public void checkCreateElementTitleBlock(String containerId) {
    new CreateElementTitleBlockTool(this, IToolNameConstants.TOOL_CREATE_TITLE_BLOCK, containerId, getDiagramId())
        .contextOk();
  }

  public void checkCreateDiagramTitleBlock() {
    new CreateDiagramTitleBlockTool(this, IToolNameConstants.TOOL_CREATE_TITLE_BLOCK, getDiagramId()).contextOk();
  }

  public void checkAutocreateDiagramTitleBlock() {
    new CreateDiagramTitleBlockTool(this, IToolNameConstants.TOOL_CREATE_TITLE_BLOCK, getDiagramId()).checkAutocreate();
  }

  public void removeElementTitleBlock(String id) {
    new InsertRemoveTitleBlockTool(this, IToolNameConstants.TOOL_INSERT_REMOVE_TITLE_BLOCK).remove(id);
  }

  public void insertElementTitleBlock(String id) {
    new InsertRemoveTitleBlockTool(this, IToolNameConstants.TOOL_INSERT_REMOVE_TITLE_BLOCK).insert(id);
  }

  public void removeDiagramTitleBlock(String id) {
    new InsertRemoveTitleBlockTool(this, IToolNameConstants.TOOL_INSERT_REMOVE_TITLE_BLOCK).remove(id);
  }

  public void insertDiagramTitleBlock(String id) {
    new InsertRemoveTitleBlockTool(this, IToolNameConstants.TOOL_INSERT_REMOVE_TITLE_BLOCK).insert(id);
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

  /**
   * Perform a delete of the view (same effect than delete double red cross)
   */
  public void deleteSemantic(DDiagramElement view) {
    new DeleteElementTool(this).delete(view);
  }

  /**
   * Perform a delete of all views (same effect than ctrl+A then delete double red cross)
   */
  public void deleteAllSemantic() {
    new DeleteElementTool(this).deleteAll();
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

  public void selectSameType(String initialElement) {
    DSemanticDecorator decorator = getView(initialElement);
    EditPart correspondingEditPart = DiagramServices.getDiagramServices().getEditPart((DDiagramElement) decorator);
    ExecutionEvent event = createExecutionEvent(correspondingEditPart);

    SelectElementsOfSameTypeCommandHandler handler = new SelectElementsOfSameTypeCommandHandler() {
      @Override
      protected IStructuredSelection getSelection() {
        StructuredSelection selection = new StructuredSelection(correspondingEditPart);
        return selection;
      }
    };
    assertSelectionWorked(event, handler);
  }

  public void selectSameMapping(String initialElement) {
    DSemanticDecorator decorator = getView(initialElement);
    EditPart correspondingEditPart = DiagramServices.getDiagramServices().getEditPart((DDiagramElement) decorator);

    ExecutionEvent event = createExecutionEvent(correspondingEditPart);

    SelectResemblingElementsCommandHandler handler = new SelectResemblingElementsCommandHandler() {
      @Override
      protected IStructuredSelection getSelection() {
        StructuredSelection selection = new StructuredSelection(correspondingEditPart);
        return selection;
      }
    };
    assertSelectionWorked(event, handler);
  }

  public void selectRelatedRecs(String initialElement, String... expectedSelection) {

    // Get edit part from initial selected Semantic element
    DSemanticDecorator decorator = getView(initialElement);
    EditPart editPart = DiagramServices.getDiagramServices().getEditPart((DDiagramElement) decorator);

    // Collect available RECs if any, on that element
    Collection<CatalogElement> commonRecs = RecDynamicMenu.getCommonRecs(new StructuredSelection(editPart));
    assertNotNull(commonRecs);
    if (expectedSelection != null && expectedSelection.length != 0) {
      // Get the referenced elements of the first REC available
      // In our test model there is only one
      CatalogElement commonRec = commonRecs.iterator().next();
      EList<EObject> refs = commonRec.getReferencedElements();

      // Run the ShowInDiagramAction
      ShowInDiagramAction action = new ShowInDiagramAction();
      action.selectionChanged(new StructuredSelection(refs));
      action.run();

      // Get the selection set after the the action is ran, this ensure that we've filtered out all items that are not
      // displayed
      IStructuredSelection selec = (IStructuredSelection) PlatformUI.getWorkbench().getActiveWorkbenchWindow()
          .getActivePage().getSelection();

      // Collect Semantic elements from selection
      List<Object> selectedElements = ((List<Object>) selec.toList()).stream().map(EditPart.class::cast)
          .map(elem -> elem.getModel()).map(View.class::cast).map(view -> view.getElement())
          .map(DDiagramElement.class::cast).map(diagramElement -> diagramElement.getTarget())
          .collect(Collectors.toList());

      List<EObject> expectedElements = Arrays.stream(expectedSelection).map(sel -> getView(sel))
          .map(view -> view.getTarget()).collect(Collectors.toList());
      if (!(selectedElements.size() == expectedElements.size() && selectedElements.containsAll(expectedElements))) {
        fail("Selection didn't correspond to expected selection");
      }

    }
  }

  public void selectRelatedRpls(String initialElement, String... expectedSelection) {
    // Get edit part from initial selected Semantic element
    DSemanticDecorator decorator = getView(initialElement);
    EditPart editPart = DiagramServices.getDiagramServices().getEditPart((DDiagramElement) decorator);

    // Collect available RECs if any, on that element
    Collection<CatalogElement> commonRpls = RplDynamicMenu.getCommonRpls(new StructuredSelection(editPart));
    assertNotNull(commonRpls);
    if (expectedSelection != null && expectedSelection.length != 0) {
      // Get the referenced elements of the first REC available
      // In our test model there is only one
      CatalogElement commonRpl = commonRpls.iterator().next();
      EList<EObject> refs = commonRpl.getReferencedElements();

      // Run the ShowInDiagramAction
      ShowInDiagramAction action = new ShowInDiagramAction();
      action.selectionChanged(new StructuredSelection(refs));
      action.run();

      // Get the selection set after the the action is ran, this ensure that we've filtered out all items that are not
      // displayed
      IStructuredSelection selec = (IStructuredSelection) PlatformUI.getWorkbench().getActiveWorkbenchWindow()
          .getActivePage().getSelection();

      // Collect Semantic elements from selection
      List<Object> selectedElements = ((List<Object>) selec.toList()).stream().map(EditPart.class::cast)
          .map(elem -> elem.getModel()).map(View.class::cast).map(view -> view.getElement())
          .map(DDiagramElement.class::cast).map(diagramElement -> diagramElement.getTarget())
          .collect(Collectors.toList());

      List<EObject> expectedElements = Arrays.stream(expectedSelection).map(sel -> getView(sel))
          .map(view -> view.getTarget()).collect(Collectors.toList());
      if (!(selectedElements.size() == expectedElements.size() && selectedElements.containsAll(expectedElements))) {
        fail("Selection didn't correspond to expected selection");
      }

    }
  }

  public void selectRelatedFCElements(String initialElement, String... expectedSelection) {
    DSemanticDecorator decorator = getView(initialElement);
    EditPart correspondingEditPart = DiagramServices.getDiagramServices().getEditPart((DDiagramElement) decorator);

    ExecutionEvent event = createExecutionEvent(correspondingEditPart);

    SelectRelatedFCElementsCommandHandler handler = new SelectRelatedFCElementsCommandHandler() {
      @Override
      protected IStructuredSelection getSelection() {
        StructuredSelection selection = new StructuredSelection(correspondingEditPart);
        return selection;
      }
    };
    assertSelectionWorked(event, handler);
    // Get the selection set after the the action is ran, this ensure that we've filtered out all items that are not
    // displayed
    IStructuredSelection selec = (IStructuredSelection) PlatformUI.getWorkbench().getActiveWorkbenchWindow()
        .getActivePage().getSelection();

    // Collect Semantic elements from selection
    List<DDiagramElement> selectedElements = ((List<Object>) selec.toList()).stream().map(EditPart.class::cast)
        .map(elem -> elem.getModel()).map(View.class::cast).map(view -> view.getElement())
        .map(DDiagramElement.class::cast)
        .collect(Collectors.toList());

    List<EObject> expectedElements = Arrays.stream(expectedSelection)
        .map(sel -> org.polarsys.capella.test.diagram.common.ju.wrapper.utils.DiagramHelper
            .getOnDiagramByUID(getDiagram(), sel))
        .collect(Collectors.toList());

    if (!(selectedElements.size() == expectedElements.size() && selectedElements.containsAll(expectedElements))) {
      fail("Selection didn't correspond to expected selection");
    }
  }

  public void selectRelatedPPElements(String initialElement, String... expectedSelection) {
    DSemanticDecorator decorator = getView(initialElement);
    EditPart correspondingEditPart = DiagramServices.getDiagramServices().getEditPart((DDiagramElement) decorator);

    ExecutionEvent event = createExecutionEvent(correspondingEditPart);

    SelectRelatedPPElementsCommandHandler handler = new SelectRelatedPPElementsCommandHandler() {
      @Override
      protected IStructuredSelection getSelection() {
        StructuredSelection selection = new StructuredSelection(correspondingEditPart);
        return selection;
      }
    };
    assertSelectionWorked(event, handler);
    // Get the selection set after the the action is ran, this ensure that we've filtered out all items that are not
    // displayed
    IStructuredSelection selec = (IStructuredSelection) PlatformUI.getWorkbench().getActiveWorkbenchWindow()
        .getActivePage().getSelection();

    // Collect Semantic elements from selection
    List<DDiagramElement> selectedElements = ((List<Object>) selec.toList()).stream().map(EditPart.class::cast)
        .map(elem -> elem.getModel()).map(View.class::cast).map(view -> view.getElement())
        .map(DDiagramElement.class::cast).collect(Collectors.toList());

    List<EObject> expectedElements = Arrays.stream(expectedSelection)
        .map(sel -> org.polarsys.capella.test.diagram.common.ju.wrapper.utils.DiagramHelper
            .getOnDiagramByUID(getDiagram(), sel))
        .collect(Collectors.toList());
    if (!(selectedElements.size() == expectedElements.size() && selectedElements.containsAll(expectedElements))) {
      fail("Selection didn't correspond to expected selection");
    }
  }

  public void selectOwnedPorts(String initialElement) {
    DSemanticDecorator decorator = getView(initialElement);
    EditPart correspondingEditPart = DiagramServices.getDiagramServices().getEditPart((DDiagramElement) decorator);

    ExecutionEvent event = createExecutionEvent(correspondingEditPart);

    SelectOwnedPortsCommandHandler handler = new SelectOwnedPortsCommandHandler() {
      @Override
      protected IStructuredSelection getSelection() {
        StructuredSelection selection = new StructuredSelection(correspondingEditPart);
        return selection;
      }
    };
    assertSelectionWorked(event, handler);
  }

  public void selectOwnedElements(String initialElement) {
    DSemanticDecorator decorator = getView(initialElement);
    EditPart correspondingEditPart = DiagramServices.getDiagramServices().getEditPart((DDiagramElement) decorator);

    ExecutionEvent event = createExecutionEvent(correspondingEditPart);

    SelectOwnedElementsCommandHandler handler = new SelectOwnedElementsCommandHandler() {
      @Override
      protected IStructuredSelection getSelection() {
        StructuredSelection selection = new StructuredSelection(correspondingEditPart);
        return selection;
      }
    };
    assertSelectionWorked(event, handler);
  }

  public void selectRelatedEdges(String initialElement) {
    DSemanticDecorator decorator = getView(initialElement);
    EditPart correspondingEditPart = DiagramServices.getDiagramServices().getEditPart((DDiagramElement) decorator);

    ExecutionEvent event = createExecutionEvent(correspondingEditPart);

    SelectRelatedConnectionsCommandHandler handler = new SelectRelatedConnectionsCommandHandler() {
      @Override
      protected IStructuredSelection getSelection() {
        StructuredSelection selection = new StructuredSelection(correspondingEditPart);
        return selection;
      }
    };
    assertSelectionWorked(event, handler);
  }

  private void assertSelectionWorked(ExecutionEvent event, AbstractSelectInEditorCommandHandler handler) {
    Object result = null;
    try {
      result = handler.execute(event);
    } catch (ExecutionException e) {
      fail("Could not execute handler " + handler.getClass().getSimpleName());
    }
    if (result == null) {
      fail("Result shouldn't be null");
    }
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

  protected ExecutionEvent createExecutionEvent(Object... elements) {
    IEvaluationContext context = new EvaluationContext(null, new Object());
    Map<String, String> parameters = new HashMap<>();
    ExecutionEvent event = new ExecutionEvent(null, parameters, null, context);

    context.addVariable(ISources.ACTIVE_CURRENT_SELECTION_NAME, new StructuredSelection(Arrays.asList(elements)));

    return event;
  }
}
