/*******************************************************************************
 * Copyright (c) 2019, 2020 THALES GLOBAL SERVICES.
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
package org.polarsys.capella.test.diagram.tools.ju.xab;

import java.util.ArrayList;
import java.util.List;

import org.eclipse.emf.ecore.EObject;
import org.eclipse.sirius.business.api.session.Session;
import org.eclipse.sirius.diagram.DDiagramElementContainer;
import org.eclipse.sirius.viewpoint.DSemanticDecorator;
import org.eclipse.ui.IActionBars;
import org.eclipse.ui.IEditorPart;
import org.eclipse.ui.actions.ActionFactory;
import org.polarsys.capella.common.data.modellingcore.AbstractType;
import org.polarsys.capella.core.data.cs.Part;
import org.polarsys.capella.core.data.pa.PhysicalComponent;
import org.polarsys.capella.core.data.pa.PhysicalComponentNature;
import org.polarsys.capella.core.model.helpers.BlockArchitectureExt;
import org.polarsys.capella.core.model.helpers.ComponentExt;
import org.polarsys.capella.core.model.helpers.PartExt;
import org.polarsys.capella.core.model.helpers.refmap.Pair;
import org.polarsys.capella.test.diagram.common.ju.context.DiagramContext;
import org.polarsys.capella.test.diagram.common.ju.context.XABDiagram;
import org.polarsys.capella.test.diagram.common.ju.step.crud.OpenDiagramStep;
import org.polarsys.capella.test.diagram.common.ju.step.tools.DragAndDropFromProjectExplorerTool;
import org.polarsys.capella.test.diagram.common.ju.wrapper.utils.DiagramHelper;
import org.polarsys.capella.test.framework.context.SessionContext;

public class DragAndDropPhysicalComponent extends XABDiagramsProject {

  @Override
  public void test() throws Exception {
    Session session = getSession(getRequiredTestModel());
    SessionContext context = new SessionContext(session);

    testDnDComponentsFromProjectExplorer(context);
    testDnDComponentsBehaviorAndNodeDeployedOrNot(session, context);
  }

  public void testDnDComponentsFromProjectExplorer(SessionContext context) {
    testOnXAB(context, PA__PAB_DIAGRAM, PA__PAB_DIAGRAM, PA__PC_PART_PC14);
    // drag and drop sub physical component
    testOnXAB(context, PA__PAB_DIAGRAM, PA__PAB_COMPONENT_PC2, PA__PC_PART_PC2_1);
    // drag and drop to a diagram created from PC2
    testOnXAB(context, PA__PAB_DIAGRAM_PC2, PA__PAB_DIAGRAM_PC2, PA__PC_PART_PC2);
    // drag and drop sub physical component
    testOnXAB(context, PA__PAB_DIAGRAM_PC2, PA__PC_PART_PC2, PA__PC_PART_PC2_1);
  }

  public void testOnXAB(SessionContext context, String diagramName, String containerId, String id) {
    DiagramContext diagramContext = new OpenDiagramStep(context, diagramName).run();
    EObject pc = context.getSemanticElement(id);

    new DragAndDropFromProjectExplorerTool(diagramContext, "D&D Components From Project Explorer", pc, containerId)
        .run();
  }

  public void testDnDComponentsBehaviorAndNodeDeployedOrNot(Session session, SessionContext context) throws Exception {
    List<String> draggedElements = new ArrayList<>();
    List<String> containerElements = new ArrayList<>();

    // Not deployed Node
    String NOT_DEPLOYED_NODE_DRAGGED = PA__PAB_COMPONENT_PC2;
    String NOT_DEPLOYED_NODE_CONTAINER = PA__PAB_COMPONENT_PC1;
    assertTrue(isNotDeployedNode(context, NOT_DEPLOYED_NODE_DRAGGED));
    assertTrue(isNotDeployedNode(context, NOT_DEPLOYED_NODE_CONTAINER));
    draggedElements.add(NOT_DEPLOYED_NODE_DRAGGED);
    containerElements.add(NOT_DEPLOYED_NODE_CONTAINER);

    // Deployed Node
    String DEPLOYED_NODE_DRAGGED = PA__PAB_COMPONENT_PC8;
    // Same component because only one of this type is present in this diagram
    String DEPLOYED_NODE_CONTAINER = PA__PAB_COMPONENT_PC8;
    assertTrue(isDeployedNode(context, DEPLOYED_NODE_DRAGGED));
    assertTrue(isDeployedNode(context, DEPLOYED_NODE_CONTAINER));
    draggedElements.add(DEPLOYED_NODE_DRAGGED);
    containerElements.add(DEPLOYED_NODE_CONTAINER);

    // Not deployed Behavior
    String NOT_DEPLOYED_BEHAVIOR_DRAGGED = PA__PAB_COMPONENT_PC3_2;
    // Same component because only one of this type is present in this diagram
    String NOT_DEPLOYED_BEHAVIOR_CONTAINER = PA__PAB_COMPONENT_PC3_2;
    assertTrue(isNotDeployedBehavior(context, NOT_DEPLOYED_BEHAVIOR_DRAGGED));
    assertTrue(isNotDeployedBehavior(context, NOT_DEPLOYED_BEHAVIOR_CONTAINER));
    draggedElements.add(NOT_DEPLOYED_BEHAVIOR_DRAGGED);
    containerElements.add(NOT_DEPLOYED_BEHAVIOR_CONTAINER);

    // Deployed Behavior
    String DEPLOYED_BEHAVIOR_DRAGGED = PA__PAB_COMPONENT_PC4;
    String DEPLOYED_BEHAVIOR_CONTAINER = PA__PAB_COMPONENT_PC7_PART;
    assertTrue(isDeployedBehavior(context, DEPLOYED_BEHAVIOR_DRAGGED));
    assertTrue(isDeployedBehavior(context, DEPLOYED_BEHAVIOR_CONTAINER));
    draggedElements.add(DEPLOYED_BEHAVIOR_DRAGGED);
    containerElements.add(DEPLOYED_BEHAVIOR_CONTAINER);

    // Actor
    String ACTOR_DRAGGED = PA__PAB_PHYSICAL_ACTOR1;
    // Same component because only one of this type is present in this diagram
    String ACTOR_CONTAINER = PA__PAB_PHYSICAL_ACTOR1;
    assertTrue(isActor(context, ACTOR_DRAGGED));
    assertTrue(isActor(context, ACTOR_CONTAINER));
    draggedElements.add(ACTOR_DRAGGED);
    containerElements.add(ACTOR_CONTAINER);

    // List of DnD that mustn't be possible
    List<Pair<String, String>> expectedNotPossible = new ArrayList<>();
    // Node (deployed or not) into Behavior container (deployed or not)
    expectedNotPossible.add(new Pair<>(NOT_DEPLOYED_NODE_DRAGGED, NOT_DEPLOYED_BEHAVIOR_CONTAINER));
    expectedNotPossible.add(new Pair<>(NOT_DEPLOYED_NODE_DRAGGED, DEPLOYED_BEHAVIOR_CONTAINER));
    expectedNotPossible.add(new Pair<>(DEPLOYED_NODE_DRAGGED, NOT_DEPLOYED_BEHAVIOR_CONTAINER));
    expectedNotPossible.add(new Pair<>(DEPLOYED_NODE_DRAGGED, DEPLOYED_BEHAVIOR_CONTAINER));
    // Not deployed Behavior into Node (deployed or not)
    expectedNotPossible.add(new Pair<>(NOT_DEPLOYED_BEHAVIOR_DRAGGED, NOT_DEPLOYED_NODE_CONTAINER));
    expectedNotPossible.add(new Pair<>(NOT_DEPLOYED_BEHAVIOR_DRAGGED, DEPLOYED_NODE_CONTAINER));
    expectedNotPossible.add(new Pair<>(NOT_DEPLOYED_BEHAVIOR_DRAGGED, ACTOR_CONTAINER));

    // Now testing all combinations of dragged elements and containers
    for (String draggedElement : draggedElements) {
      for (String containerElement : containerElements) {
        // same component, so cannot test the DnD
        if (!draggedElement.equals(containerElement)) {
          // The DnD is expected to be possible by default
          boolean expected = true;
          // Check in the expectedNotPossible map is the DnD mustn't be possible
          for (Pair<String, String> couple : expectedNotPossible) {
            if (couple.getFirstValue() == draggedElement && couple.getSecondValue() == containerElement) {
              expected = false;
            }
          }
          assertEquals(expected, testDnDOnPAB(session, context, PA__PAB_DIAGRAM, draggedElement, containerElement));
        }
      }
    }
  }

  /**
   * Return true if the Drag&Drop of draggedElement into containerElement in PAB diagramName is valid
   * 
   * @param session
   * @param context
   * @param diagramName
   * @param draggedElement
   * @param containerElement
   * @return
   * @throws Exception
   */
  public boolean testDnDOnPAB(//
      Session session, SessionContext context, String diagramName, String draggedElement, String containerElement)
      throws Exception {
    DiagramContext diagramContext = new OpenDiagramStep(context, diagramName).run();
    XABDiagram diagram = XABDiagram.openDiagram(context, diagramName, BlockArchitectureExt.Type.PA);

    // execute the DnD
    try {
      diagram.dragAndDropComponent(draggedElement, containerElement);
    } catch (AssertionError e) {
      // If the DnD failed, return false
      return false;
    }

    // If the DnD not failed, check the result of the DnD and undo it
    DSemanticDecorator draggedElementView = diagram.getView(draggedElement);
    DSemanticDecorator containerView = diagram.getView(containerElement);
    if (containerView instanceof DDiagramElementContainer) {
      DDiagramElementContainer dContainer = (DDiagramElementContainer) containerView;
      boolean result = dContainer.getElements().contains(draggedElementView);
      undo(session, diagramContext);
      return result;
    }
    throw new Exception("DragAndDropPhysicalComponent: containerView is not a DDiagramElementContainer");
  }

  public void undo(Session session, DiagramContext diagramContext) {
    IEditorPart editorPart = DiagramHelper.getDiagramEditor(session, diagramContext.getDiagram());
    IActionBars actionBars = editorPart.getEditorSite().getActionBars();
    actionBars.getGlobalActionHandler(ActionFactory.UNDO.getId()).run();
  }

  private boolean isNotDeployedNode(SessionContext context, String id) throws Exception {
    return getNature(context, id) == PhysicalComponentNature.NODE && //
        !isDeployed(context, id) && //
        !isActor(context, id);
  }

  private boolean isDeployedNode(SessionContext context, String id) throws Exception {
    return getNature(context, id) == PhysicalComponentNature.NODE && //
        isDeployed(context, id) && //
        !isActor(context, id);
  }

  private boolean isNotDeployedBehavior(SessionContext context, String id) throws Exception {
    return getNature(context, id) == PhysicalComponentNature.BEHAVIOR && //
        !isDeployed(context, id) && //
        !isActor(context, id);
  }

  private boolean isDeployedBehavior(SessionContext context, String id) throws Exception {
    return getNature(context, id) == PhysicalComponentNature.BEHAVIOR && //
        isDeployed(context, id) && //
        !isActor(context, id);
  }

  private PhysicalComponentNature getNature(SessionContext context, String id) throws Exception {
    EObject element = context.getSemanticElement(id);
    if (element instanceof Part) {
      Part part = (Part) element;
      AbstractType type = part.getAbstractType();
      if (type instanceof PhysicalComponent) {
        PhysicalComponent component = (PhysicalComponent) type;
        return component.getNature();
      }
    }
    throw new Exception("DragAndDropPhysicalComponent: semanticElement's abstractType is not a PhysicalComponent");
  }

  private boolean isDeployed(SessionContext context, String id) throws Exception {
    EObject element = context.getSemanticElement(id);
    if (element instanceof Part) {
      Part part = (Part) element;
      return PartExt.isDeployed(part);
    }
    throw new Exception("DragAndDropPhysicalComponent: semanticElement is not a Part");
  }

  private boolean isActor(SessionContext context, String id) throws Exception {
    EObject element = context.getSemanticElement(id);
    if (element instanceof Part) {
      Part part = (Part) element;
      AbstractType type = part.getAbstractType();
      if (type instanceof PhysicalComponent) {
        PhysicalComponent component = (PhysicalComponent) type;
        return ComponentExt.isActor(component);
      }
    }
    throw new Exception("DragAndDropPhysicalComponent: semanticElement's abstractType is not a PhysicalComponent");
  }
}
