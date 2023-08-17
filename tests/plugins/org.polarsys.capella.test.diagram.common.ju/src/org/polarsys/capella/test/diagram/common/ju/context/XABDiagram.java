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

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.eclipse.core.commands.ExecutionEvent;
import org.eclipse.core.commands.ExecutionException;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.gef.EditPart;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.jface.viewers.StructuredSelection;
import org.eclipse.sirius.diagram.DDiagram;
import org.eclipse.sirius.diagram.DDiagramElement;
import org.eclipse.sirius.diagram.DDiagramElementContainer;
import org.eclipse.sirius.diagram.DNode;
import org.eclipse.sirius.diagram.business.api.helper.delete.DeleteHookHelper;
import org.eclipse.sirius.viewpoint.DSemanticDecorator;
import org.junit.Assert;
import org.polarsys.capella.core.data.capellacore.CapellaElement;
import org.polarsys.capella.core.data.cs.BlockArchitecture;
import org.polarsys.capella.core.data.cs.Component;
import org.polarsys.capella.core.data.cs.Part;
import org.polarsys.capella.core.data.cs.PhysicalPath;
import org.polarsys.capella.core.data.fa.ComponentExchange;
import org.polarsys.capella.core.model.handler.helpers.CapellaProjectHelper;
import org.polarsys.capella.core.model.handler.helpers.CapellaProjectHelper.TriStateBoolean;
import org.polarsys.capella.core.model.helpers.BlockArchitectureExt;
import org.polarsys.capella.core.model.helpers.BlockArchitectureExt.ComponentPortType;
import org.polarsys.capella.core.model.helpers.BlockArchitectureExt.FunctionPortType;
import org.polarsys.capella.core.model.helpers.BlockArchitectureExt.FunctionType;
import org.polarsys.capella.core.model.helpers.BlockArchitectureExt.LinkDirection;
import org.polarsys.capella.core.model.helpers.BlockArchitectureExt.Type;
import org.polarsys.capella.core.model.helpers.ComponentExt;
import org.polarsys.capella.core.sirius.analysis.DiagramServices;
import org.polarsys.capella.core.sirius.analysis.IDiagramNameConstants;
import org.polarsys.capella.core.sirius.analysis.actions.extensions.AbstractExternalJavaAction;
import org.polarsys.capella.core.sirius.analysis.constants.IDNDToolNameConstants;
import org.polarsys.capella.core.sirius.analysis.constants.IToolNameConstants;
import org.polarsys.capella.core.sirius.ui.handlers.CreateFunctionalChainActionHandler;
import org.polarsys.capella.core.sirius.ui.handlers.CreatePhysicalPathActionHandler;
import org.polarsys.capella.test.diagram.common.ju.step.crud.CreateDiagramStep;
import org.polarsys.capella.test.diagram.common.ju.step.crud.OpenDiagramStep;
import org.polarsys.capella.test.diagram.common.ju.step.tools.AbstractToolStep;
import org.polarsys.capella.test.diagram.common.ju.step.tools.CreateAbstractDNodeTool;
import org.polarsys.capella.test.diagram.common.ju.step.tools.CreateContainerTool;
import org.polarsys.capella.test.diagram.common.ju.step.tools.CreateDEdgeTool;
import org.polarsys.capella.test.diagram.common.ju.step.tools.CreateNodeTool;
import org.polarsys.capella.test.diagram.common.ju.step.tools.InitializationFromExistingDiagramTool;
import org.polarsys.capella.test.diagram.common.ju.step.tools.InsertRemoveTool;
import org.polarsys.capella.test.diagram.common.ju.step.tools.ReconnectTool;
import org.polarsys.capella.test.diagram.common.ju.step.tools.SelectFromListTool;
import org.polarsys.capella.test.diagram.common.ju.step.tools.SwitchTool;
import org.polarsys.capella.test.diagram.common.ju.step.tools.xab.ReuseComponentTool;
import org.polarsys.capella.test.framework.context.SessionContext;

public class XABDiagram extends CommonDiagram {

  BlockArchitectureExt.Type type = null;

  public XABDiagram(BlockArchitectureExt.Type type, SessionContext context, DDiagram diagram) {
    super(context, diagram);
    this.type = type;
  }

  public static XABDiagram createDiagram(SessionContext executionContext, String targetIdentifier) {
    BlockArchitecture architecture = BlockArchitectureExt
        .getRootBlockArchitecture(executionContext.getSemanticElement(targetIdentifier));
    final BlockArchitectureExt.Type type = BlockArchitectureExt.getBlockArchitectureType(architecture);

    String name = null;
    switch (type) {
    case OA:
      return OABDiagram.createDiagram(executionContext, targetIdentifier);

    case EPBS:
      return EABDiagram.createDiagram(executionContext, targetIdentifier);

    case PA:
      return PABDiagram.createDiagram(executionContext, targetIdentifier);

    case SA:
      name = IDiagramNameConstants.SYSTEM_ARCHITECTURE_BLANK_DIAGRAM_NAME;
      break;

    case LA:
      name = IDiagramNameConstants.LOGICAL_ARCHITECTURE_BLANK_DIAGRAM_NAME;
      break;
    }

    return (XABDiagram) new CreateDiagramStep(executionContext, targetIdentifier, name) {
      @Override
      public DiagramContext getResult() {
        return new XABDiagram(type, getExecutionContext(), diagram);
      }
    }.run().open();
  }

  public static XABDiagram openDiagram(SessionContext executionContext, String name,
      final BlockArchitectureExt.Type type) {
    return (XABDiagram) new OpenDiagramStep(executionContext, name) {
      @Override
      public DiagramContext getResult() {
        return new XABDiagram(type, getExecutionContext(), diagram);
      }
    }.run().open();
  }

  public String createActor(String id) {
    DDiagramElementContainer element = new CreateContainerTool(this, getCreateActorToolName(), getDiagramId(), id)
        .run();

    assertTrue(getCreatedComponent(element).isActor());
    return ((CapellaElement) element.getTarget()).getId();
  }

  public String createActor(String id, String containerId) {

    DDiagramElementContainer element = new CreateContainerTool(this, getCreateActorToolName(), containerId, id).run();

    assertTrue(getCreatedComponent(element).isActor());
    return ((CapellaElement) element.getTarget()).getId();
  }

  public void cannotCreateActor(String id, String containerId) {
    new CreateContainerTool(this, getCreateActorToolName(), containerId, id).cannotRun();
  }

  public void failedPreconditionCreateActor(String id, String containerId) {
    new CreateContainerTool(this, getCreateActorToolName(), containerId, id).shouldFail();
  }

  protected Component getCreatedComponent(DDiagramElementContainer element) {
    Component component = null;
    CapellaElement target = (CapellaElement) element.getTarget();
    if (target instanceof Part) {
      Part actorPart = (Part) element.getTarget();
      component = (Component) (actorPart.getAbstractType());
    } else {
      component = (Component) target;
    }
    return component;
  }

  protected String getCreateActorToolName() {
    String name = null;
    if (type == Type.OA) {
      name = IToolNameConstants.TOOL_OAB_CREATE_OA;
    } else if (type == Type.SA) {
      name = IToolNameConstants.TOOL_SAB_CREATE_ACTOR;
    } else if (type == Type.LA) {
      name = IToolNameConstants.TOOL_LAB_CREATE_LOGICAL_ACTOR;
    } else if (type == Type.PA) {
      name = IToolNameConstants.TOOL_PAB_CREATE_PHYSICAL_ACTOR;
    }
    return name;
  }

  public String createFunction(String id, String containerId) {
    DNode element = new CreateNodeTool(this, IToolNameConstants.TOOL_CREATE_FUNCTION, containerId, id).run();
    return ((CapellaElement) element.getTarget()).getId();
  }

  public void createFunction(String id, String containerId, FunctionType functionType) {
    String name = null;
    switch (functionType) {
    case SYSTEM_FUNCTION:
      name = IToolNameConstants.TOOL_CREATE_FUNCTION;
      break;
    case DUPLICATE:
      name = IToolNameConstants.TOOL_XAB_CREATE_DUPLICATE;
      break;
    case GATHER:
      name = IToolNameConstants.TOOL_XAB_CREATE_GATHER;
      break;
    case ROUTE:
      name = IToolNameConstants.TOOL_XAB_CREATE_ROUTE;
      break;
    case SELECT:
      name = IToolNameConstants.TOOL_XAB_CREATE_SELECT;
      break;
    case SPLIT:
      name = IToolNameConstants.TOOL_XAB_CREATE_SPLIT;
      break;
    default:
      break;
    }

    new CreateNodeTool(this, name, containerId, id).run();
  }

  public String getToolNameShowHideActor() {
    String name = null;
    if (type == Type.OA) {
      name = IToolNameConstants.TOOL_OAB_INSERT_REMOVE_OPERATIONAL_ENTITIES;
    } else if (TriStateBoolean.True
        .equals(CapellaProjectHelper.isReusableComponentsDriven(getDiagramDescriptor().getTarget()))) {
      name = IToolNameConstants.TOOL_XAB_INSERT_ACTORS_MULTIPART;
    } else {
      name = IToolNameConstants.TOOL_XAB_INSERT_ACTORS_MONOPART;
    }
    return name;
  }

  public void initializationFromExistingDiagram(DiagramContext existingContext) {
    InitializationFromExistingDiagramTool tool = new InitializationFromExistingDiagramTool(this,
        IToolNameConstants.TOOL_INITIALIZATION_FROM_EXISTING_DIAGRAM, existingContext);
    tool.setTolerance(6);
    tool.insert(existingContext.getDiagramId());
  }

  public void insertActor(String id) {
    new InsertRemoveTool(this, getToolNameShowHideActor()).insert(id);
  }

  public void removeActor(String id) {
    new InsertRemoveTool(this, getToolNameShowHideActor()).remove(id);
  }

  public void insertActor(String containerId, String id) {
    new InsertRemoveTool(this, getToolNameShowHideActor(), containerId).insert(id);
  }

  public void removeActor(String containerId, String id) {
    new InsertRemoveTool(this, getToolNameShowHideActor(), containerId).remove(id);
  }

  public void insertComponent(String id) {
    insertComponent(id, getDiagramId());
  }

  public void insertComponent(String toInsertId, String containerId) {
    String name = null;
    if (type == Type.OA) {
      name = IToolNameConstants.TOOL_OAB_INSERT_REMOVE_OPERATIONAL_ENTITIES;
    } else if (type == Type.LA) {
      if (TriStateBoolean.True
          .equals(CapellaProjectHelper.isReusableComponentsDriven(getDiagramDescriptor().getTarget()))) {
        name = IToolNameConstants.TOOL_XAB_INSERT_REMOVE_COMPONENTS_MULTIPART;
      } else {
        name = IToolNameConstants.TOOL_XAB_INSERT_REMOVE_COMPONENTS_MONOPART;
      }
    } else if (type == Type.PA) {
    }

    new InsertRemoveTool(this, name, containerId).insert(toInsertId);
  }

  public void removeComponent(String id) {
    removeComponent(id, getDiagramId());
  }

  public void removeComponent(String id, String containerId) {
    String name = null;
    if (type == Type.OA) {
      name = IToolNameConstants.TOOL_OAB_INSERT_REMOVE_OPERATIONAL_ENTITIES;
      new InsertRemoveTool(this, name).remove(id);
    } else if (type == Type.LA) {
      if (TriStateBoolean.True
          .equals(CapellaProjectHelper.isReusableComponentsDriven(getDiagramDescriptor().getTarget()))) {
        new InsertRemoveTool(this, new String[] { IToolNameConstants.TOOL_XAB_INSERT_REMOVE_COMPONENTS_MULTIPART,
            IToolNameConstants.TOOL_LAB_INSERT_REMOVE_COMPONENTS }, containerId).remove(id);
      } else {
        new InsertRemoveTool(this, new String[] { IToolNameConstants.TOOL_XAB_INSERT_REMOVE_COMPONENTS_MONOPART,
            IToolNameConstants.TOOL_LAB_INSERT_REMOVE_COMPONENTS }, containerId).remove(id);
      }
    }
  }

  public String createComponent(String id, String containerId) {
    String name = null;
    if (type == Type.OA) {
      name = IToolNameConstants.TOOL_OAB_CREATE_OE;

    } else if (type == Type.LA) {
      name = IToolNameConstants.TOOL_LAB_CREATE_COMPONENT;
    }
    DDiagramElementContainer element = new CreateContainerTool(this, name, containerId, id).run();
    assertFalse(getCreatedComponent(element).isActor());
    return ((CapellaElement) element.getTarget()).getId();
  }

  public void cannotCreateComponent(String id, String containerId) {
    String name = null;
    if (type == Type.OA) {
      name = IToolNameConstants.TOOL_OAB_CREATE_OE;

    } else if (type == Type.LA) {
      name = IToolNameConstants.TOOL_LAB_CREATE_COMPONENT;
    }
    new CreateContainerTool(this, name, containerId, id).cannotRun();
  }

  public void createComponentExchange(String idSource, String idTarget, String id) {
    String name = null;
    if (type == Type.OA) {
      name = IToolNameConstants.TOOL_OAB_CREATE_COMMUNICATION_MEAN;
    } else if (type == Type.SA) {
      name = IToolNameConstants.TOOL_SAB_CREATE_COMPONENT_EXCHANGE;
    } else if (type == Type.LA) {
      name = IToolNameConstants.TOOL_LAB_CREATE_COMPONENT_EXCHANGE;
    } else if (type == Type.PA) {
      name = IToolNameConstants.TOOL_PAB_CREATE_COMPONENT_EXCHANGE;
    }
    new CreateDEdgeTool(this, name, idSource, idTarget, id).run();
  }

  public void createComponentExchangeDelegation(String idSource, String idTarget, String id) {
    new CreateDEdgeTool(this, IToolNameConstants.TOOL_XAB_CREATE_DELEGATION, idSource, idTarget, id).run();
  }

  public void createComponentExchangeWithDelegation(String idSource, String idTarget, String id) {
    new CreateDEdgeTool(this, IToolNameConstants.TOOL_XAB_CREATE_COMPONENT_EXCHANGE_WITH_DELEGATIONS, idSource,
        idTarget, id).run();
  }

  public void createComponentExchangeWithPorts(String idSource, String idTarget, String id) {
    new CreateDEdgeTool(this, IToolNameConstants.TOOL_XAB_CREATE_COMPONENT_EXCHANGE_WITH_PORTS, idSource, idTarget, id)
        .run();
  }

  public void createComponentExchangeWithoutPorts(String idSource, String idTarget, String id) {
    new CreateDEdgeTool(this, IToolNameConstants.TOOL_XAB_CREATE_COMPONENT_EXCHANGE_WITHOUT_PORTS, idSource, idTarget,
        id).run();
  }

  public void createComponentExchangeBetweenTypes(String idSource, String idTarget, String id) {
    new CreateDEdgeTool(this, IToolNameConstants.TOOL_XAB_CREATE_COMPONENT_EXCHANGE_BETWEEN_TYPES, idSource, idTarget,
        id).run();
  }

  public String getToolNameReconnectComponentExchange(BlockArchitectureExt.LinkDirection direction) {
    String name = null;
    if (direction == LinkDirection.SOURCE) {
      if (type == Type.OA) {
        name = IToolNameConstants.TOOL_OAB_RECONNECT_COMMUNICATION_MEAN_SOURCE;
      } else if (type == Type.SA) {
        name = IToolNameConstants.TOOL_SAB_RECONNECT_EXCHANGES_SOURCE;
      } else if (type == Type.LA) {
        name = IToolNameConstants.TOOL_LAB_RECONNECT_CONNECTION_SOURCE;
      } else if (type == Type.PA) {
        name = IToolNameConstants.TOOL_PAB_RECONNECT_COMPONENTEXCHANGE_SOURCE;
      }
    } else if (direction == LinkDirection.TARGET) {
      if (type == Type.OA) {
        name = IToolNameConstants.TOOL_OAB_RECONNECT_COMMUNICATION_MEAN_TARGET;
      } else if (type == Type.SA) {
        name = IToolNameConstants.TOOL_SAB_RECONNECT_EXCHANGES_TARGET;
      } else if (type == Type.LA) {
        name = IToolNameConstants.TOOL_LAB_RECONNECT_CONNECTION_TARGET;
      } else if (type == Type.PA) {
        name = IToolNameConstants.TOOL_PAB_RECONNECT_COMPONENTEXCHANGE_TARGET;
      }
    }
    return name;
  }

  public void reconnectComponentExchange(String id, String oldTargetId, String newTargetId,
      BlockArchitectureExt.LinkDirection direction) {
    new ReconnectTool(this, getToolNameReconnectComponentExchange(direction), id, oldTargetId, newTargetId).run();
  }

  public void cannotReconnectComponentExchange(String id, String oldTargetId, String newTargetId,
      BlockArchitectureExt.LinkDirection direction) {
    new ReconnectTool(this, getToolNameReconnectComponentExchange(direction), id, oldTargetId, newTargetId)
        .shouldFail();
  }

  public void createFunctionalExchange(String idSource, String idTarget, String id) {
    new CreateDEdgeTool(this, IToolNameConstants.TOOL_XAB_CREATE_FUNCTIONAL_EXCHANGE, idSource, idTarget, id).run();
  }

  public void createPhysicalLink(String idSource, String idTarget, String id) {
    new CreateDEdgeTool(this, IToolNameConstants.TOOL_XAB_CREATE_PHYSICAL_LINK, idSource, idTarget, id).run();
  }

  public void cannotCreatePhysicalLink(String idSource, String idTarget, String id) {
    new CreateDEdgeTool(this, IToolNameConstants.TOOL_XAB_CREATE_PHYSICAL_LINK, idSource, idTarget, id).cannotRun();
  }

  public void creationPhysicalLinkFail(String idSource, String idTarget) {
    new CreateDEdgeTool(this, IToolNameConstants.TOOL_XAB_CREATE_PHYSICAL_LINK, idSource, idTarget).shouldFail();
  }

  public String getToolNameReconnectPhysicalLink(BlockArchitectureExt.LinkDirection direction) {
    String name = null;
    if (direction == LinkDirection.SOURCE) {
      name = IToolNameConstants.TOOL_XAB_RECONNECT_PHYSICALLINK_SOURCE_ID;
    } else if (direction == LinkDirection.TARGET) {
      name = IToolNameConstants.TOOL_XAB_RECONNECT_PHYSICALLINK_TARGET_ID;
    }
    return name;
  }

  public void reconnectPhysicalLink(String id, String oldTargetId, String newTargetId,
      BlockArchitectureExt.LinkDirection direction) {
    new ReconnectTool(this, getToolNameReconnectPhysicalLink(direction), id, oldTargetId, newTargetId).run();
  }

  public void cannotReconnectPhysicalLink(String id, String oldTargetId, String newTargetId,
      BlockArchitectureExt.LinkDirection direction) {
    new ReconnectTool(this, getToolNameReconnectPhysicalLink(direction), id, oldTargetId, newTargetId).shouldFail();
  }

  public void insertComponentExchange(String id, String containerId) {
    String name = null;
    if (type == Type.OA) {
      name = IToolNameConstants.TOOL_OAB_SHOW_HIDE_COMMUNICATION_MEAN;
    } else if (type == Type.SA) {
      name = IToolNameConstants.TOOL_SAB_SHOW_HIDE_COMPONENT_EXCHANGE;
    } else if (type == Type.LA) {
      name = IToolNameConstants.TOOL_LAB_SHOW_HIDE_COMPONENT_EXCHANGE;
    } else if (type == Type.PA) {
      name = IToolNameConstants.TOOL_PAB_SHOW_HIDE_COMPONENT_EXCHANGE;
    }
    new InsertRemoveTool(this, name, containerId).insert(id);
  }

  public void removeComponentExchange(String id, String containerId) {
    String name = null;
    if (type == Type.OA) {
      name = IToolNameConstants.TOOL_OAB_SHOW_HIDE_COMMUNICATION_MEAN;
    } else if (type == Type.SA) {
      name = IToolNameConstants.TOOL_SAB_SHOW_HIDE_COMPONENT_EXCHANGE;
    } else if (type == Type.LA) {
      name = IToolNameConstants.TOOL_LAB_SHOW_HIDE_COMPONENT_EXCHANGE;
    } else if (type == Type.PA) {
      name = IToolNameConstants.TOOL_PAB_SHOW_HIDE_COMPONENT_EXCHANGE;
    }
    new InsertRemoveTool(this, name, containerId).remove(id);
  }

  public void insertPhysicalLink(String id, String containerId) {
    new InsertRemoveTool(this, IToolNameConstants.TOOL_XAB_SHOW_HIDE_PHYSICAL_LINK, containerId).insert(id);
  }

  public void removePhysicalLink(String id, String containerId) {
    new InsertRemoveTool(this, IToolNameConstants.TOOL_XAB_SHOW_HIDE_PHYSICAL_LINK, containerId).remove(id);
  }

  public String getToolNameManageAllocatedFunction() {
    String name = null;
    if (type == Type.OA) {
      name = IToolNameConstants.TOOL_OAB_MANAGE_ACTIVITY_ALLOCATION;
    } else {
      name = IToolNameConstants.TOOL_XAB_MANAGE_FUNCTION_ALLOCATION;
    }
    return name;
  }

  public void manageAllocatedFunction(String id, String containerId) {
    new InsertRemoveTool(this, getToolNameManageAllocatedFunction(), containerId).insert(id);
  }

  public void manageAllocatedFunctionRemove(String id, String containerId) {
    new InsertRemoveTool(this, getToolNameManageAllocatedFunction(), containerId).remove(id);
  }

  public void dragAndDropAbstractFunctionallocation(String idDraggedElement, String containerId) {
    dragAndDrop(idDraggedElement, containerId, IDNDToolNameConstants.TOOL_XAB_DND_ABSTRACTFUNCTION_ALLOCATION);
  }

  public void dragAndDropComponentPort(String idDraggedElement, String containerId) {
    dragAndDrop(idDraggedElement, containerId, IDNDToolNameConstants.TOOL_XAB_DND_COMPONENTPORT);
  }

  public void dragAndDropFunctionPort(String idDraggedElement, String containerId) {
    dragAndDrop(idDraggedElement, containerId, IDNDToolNameConstants.TOOL_XAB_DND_FUNCTIONPORT);
  }

  public void dragAndDropFunctionAllocationFromExplorer(String idDraggedElement, String containerId) {
    dragAndDropFromExplorer(idDraggedElement, containerId,
        IDNDToolNameConstants.TOOL_XAB_DND_FUNCTION_ALLOCATION_FROM_EXPLORER);
  }

  public void dragAndDropPhysicalPort(String idDraggedElement, String containerId) {
    dragAndDrop(idDraggedElement, containerId, IDNDToolNameConstants.TOOL_XAB_DND_PHYSICALPORT);
  }

  public String dragAndDropComponentsFromExplorer(String idDraggedElement, String containerId) {
    return dragAndDropFromExplorer(idDraggedElement, containerId,
        IDNDToolNameConstants.TOOL_XAB_DND_COMPONENTS_FROM_EXPLORER);
  }

  public void dragAndDropComponent(String idDraggedElement, String containerId) {
    dragAndDrop(idDraggedElement, containerId, getDragAndDropComponentToolName());
  }

  public void dragAndDropShouldFail(String sourceId, String targetId) {
    try {
      dragAndDropComponent(sourceId, targetId);
      Assert.fail(
          "Drag and drop should have failed for diagram: " + this + " source " + sourceId + " target " + targetId);
    } catch (AssertionError error) {
      Assert.assertTrue(error.getMessage().startsWith("Precondition"));
    }
  }

  public void dragAndDropShouldSucceed(String sourceId, String targetId) {
    dragAndDropComponent(sourceId, targetId);
  }

  private String getDragAndDropComponentToolName() {
    if (type == Type.OA) {
      return IDNDToolNameConstants.TOOL_OAB_DND_ENTITY;
    }
    return IDNDToolNameConstants.TOOL_XAB_DND_COMPONENT;
  }

  private String getToolNameShowHideAllocatedFunction() {
    String name = null;
    if (type == Type.OA) {
      name = IToolNameConstants.TOOL_OAB_INSERT_REMOVE_ALLOCATED_ACTIVITIES;
    } else if (type == Type.SA) {
      name = IToolNameConstants.TOOL_SAB_INSERT_REMOVE_ALLOCATED_FUNCTIONS;
    } else if (type == Type.LA) {
      name = IToolNameConstants.TOOL_LAB_INSERT_REMOVE_ALLOCATED_FUNCTIONS;
    } else if (type == Type.PA) {
      name = IToolNameConstants.TOOL_PAB_INSERT_REMOVE_ALLOCATED_FUNCTIONS;
    }
    return name;
  }

  public void insertAllocatedFunction(String id, String containerId) {
    new InsertRemoveTool(this, getToolNameShowHideAllocatedFunction(), containerId).insert(id);
  }

  public void removeAllocatedFunction(String id, String containerId) {
    new InsertRemoveTool(this, getToolNameShowHideAllocatedFunction(), containerId).remove(id);
  }

  private String getToolNameALLAllocatedFunction(String... types) {
    String name = null;
    String[] aTypes = types;
    if (type == Type.OA) {
      if (aTypes.length > 0) {
        name = aTypes[0];
      } else {
        name = IToolNameConstants.TOOL_OAB_INSERT_REMOVE_ALL_ALLOCATED_ACTIVITIES_IN_ENTITIES;
      }
    } else if (type == Type.SA) {
      name = IToolNameConstants.TOOL_SAB_INSERT_REMOVE_ALL_ALLOCATED_FUNCTIONS;
    } else if (type == Type.LA) {
      name = IToolNameConstants.TOOL_LAB_INSERT_REMOVE_ALL_ALLOCATED_FUNCTIONS;
    } else if (type == Type.PA) {
      name = IToolNameConstants.TOOL_PAB_INSERT_REMOVE_ALL_ALLOCATED_FUNCTIONS;
    }

    return name;
  }

  public void insertAllAllocatedFunction(String id, String containerId, String... types) {
    new InsertRemoveTool(this, getToolNameALLAllocatedFunction(types), containerId).insert(id);
  }

  public void removeAllAllocatedFunction(String id, String containerId, String... types) {
    new InsertRemoveTool(this, getToolNameALLAllocatedFunction(types), containerId).remove(id);
  }

  @Override
  public Collection<EObject> adaptTool(AbstractToolStep<?> tool, Map<String, Object> parameters,
      Collection<EObject> semanticElements) {
    Collection<EObject> scope = AbstractExternalJavaAction.getScope(parameters);
    if (scope.isEmpty()) {
      return semanticElements;
    }

    // If tool show component in wizard but display parts in diagrams, or the opposite, we switch between them
    EObject scopeElement = scope.iterator().next();
    Collection<EObject> result = new ArrayList<>();
    for (EObject element : semanticElements) {
      if ((element instanceof Part) && (scopeElement instanceof Component)) {
        result.add(((Part) element).getAbstractType());
      } else if ((element instanceof Component) && (scopeElement instanceof Part)) {
        result.add(((Component) element).getRepresentingParts().get(0));
      } else {
        result.add(element);
      }
    }
    return result;
  }

  public String getNameFunctionalExchange() {
    String name = null;
    if (type == Type.SA) {
      name = IToolNameConstants.TOOL_SAB_SHOW_HIDE_FUNCTIONAL_EXCHANGES;
    } else if (type == Type.LA) {
      name = IToolNameConstants.TOOL_LAB_SHOW_HIDE_FUNCTIONAL_EXCHANGES;
    } else if (type == Type.PA) {
      name = IToolNameConstants.TOOL_PAB_SHOW_HIDE_FUNCTIONAL_EXCHANGES;
    }
    return name;
  }

  public void insertFunctionalExchange(String id, String containerId, boolean autoRefresh) {
    new InsertRemoveTool(this, getNameFunctionalExchange(), containerId, autoRefresh).insert(id);
  }

  public void removeFunctionalExchange(String id, String containerId, boolean autoRefresh) {
    new InsertRemoveTool(this, getNameFunctionalExchange(), containerId, autoRefresh).remove(id);
  }

  public String getToolNameReconnectFunctionalExchange() {
    String name = null;
    if (type == Type.OA) {
      name = IToolNameConstants.TOOL_OAB_RECONNECT_INTERACTION;
    } else if (type == Type.SA) {
      name = IToolNameConstants.TOOL_SAB_RECONNECT_FUNCTION_EXCHANGES;
    } else if (type == Type.LA) {
      name = IToolNameConstants.TOOL_LAB_RECONNECT_FUNCTION_EXCHANGE;
    } else if (type == Type.PA) {
      name = IToolNameConstants.TOOL_PAB_RECONNECT_FUNCTION_EXCHANGE;
    }
    return name;
  }

  public void reconnectFunctionalExchange(String id, String oldTargetId, String newTargetId) {
    new ReconnectTool(this, getToolNameReconnectFunctionalExchange(), id, oldTargetId, newTargetId).run();
  }

  public void cannotReconnectFunctionalExchange(String id, String oldTargetId, String newTargetId) {
    new ReconnectTool(this, getToolNameReconnectFunctionalExchange(), id, oldTargetId, newTargetId).shouldFail();
  }

  public PhysicalPath createPhysicalPath(final String path, final String... links) {
    List<DSemanticDecorator> decorators = Arrays.stream(links).map(this::getView).collect(Collectors.toList());
    List<EditPart> correspondingEditPart = decorators.stream()
        .map(decorator -> DiagramServices.getDiagramServices().getEditPart((DDiagramElement) decorator))
        .collect(Collectors.toList());

    ExecutionEvent event = createExecutionEvent(correspondingEditPart);
    CreatePhysicalPathActionHandler handler = new CreatePhysicalPathActionHandler() {
      @Override
      protected IStructuredSelection getSelection() {
        StructuredSelection selection = new StructuredSelection(correspondingEditPart);
        return selection;
      }
    };
    Object result = null;
    try {
      result = handler.execute(event);
    } catch (ExecutionException e) {
      // TODO Auto-generated catch block
      e.printStackTrace();
    }
    putView(path, (DDiagramElement) result);
    getSessionContext().putSemanticElement(path, ((DDiagramElement) result).getTarget());
    return (PhysicalPath) ((DDiagramElement) result).getTarget();
  }

  public void insertPhysicalPath(String... path) {
    new InsertRemoveTool(this, IToolNameConstants.TOOL_XAB_INSERT_REMOVE_PHYSICAL_PATH).insert(path);
  }

  public void removePhysicalPath(String... path) {
    new InsertRemoveTool(this, IToolNameConstants.TOOL_XAB_INSERT_REMOVE_PHYSICAL_PATH).remove(path);
  }

  public void createFunctionalChain(String path, String... links) {
    List<DSemanticDecorator> decorators = Arrays.stream(links).map(this::getView).collect(Collectors.toList());
    List<EditPart> correspondingEditPart = decorators.stream()
        .map(decorator -> DiagramServices.getDiagramServices().getEditPart((DDiagramElement) decorator))
        .collect(Collectors.toList());

    ExecutionEvent event = createExecutionEvent(correspondingEditPart);
    CreateFunctionalChainActionHandler handler = new CreateFunctionalChainActionHandler() {
      @Override
      protected IStructuredSelection getSelection() {
        StructuredSelection selection = new StructuredSelection(correspondingEditPart);
        return selection;
      }
    };
    Object result = null;
    try {
      result = handler.execute(event);
    } catch (ExecutionException e) {
      // TODO Auto-generated catch block
      e.printStackTrace();
    }
    putView(path, (DDiagramElement) result);
    getSessionContext().putSemanticElement(path, ((DDiagramElement) result).getTarget());
  }

  public String getToolNameFunctionalChains() {
    String name = null;
    if (type == Type.OA) {
      name = IToolNameConstants.TOOL_OAB_INSERT_REMOVE_OPERATIONAL_PROCESSES;
    } else {
      name = IToolNameConstants.TOOL_XAB_INSERT_REMOVE_FUNCTIONAL_CHAINS;
    }

    return name;
  }

  public void insertFunctionalChain(String id) {
    new InsertRemoveTool(this, getToolNameFunctionalChains(), getDiagramId()).insert(id);
  }

  public void removeFunctionalChain(String id) {
    new InsertRemoveTool(this, getToolNameFunctionalChains(), getDiagramId()).remove(id);
  }

  public void createFunctionPort(String id, FunctionPortType fpType, String containerId) {
    String name = null;
    switch (fpType) {
    case IN_FUNCTION_PORT:
      name = IToolNameConstants.TOOL_XAB_CREATE_FUNCTION_INPUT_PORT;
      break;
    case OUT_FUNCTION_PORT:
      name = IToolNameConstants.TOOL_XAB_CREATE_FUNCTION_OUTPUT_PORT;
    default:
      break;
    }
    new CreateAbstractDNodeTool<DNode>(this, name, containerId, id).run();
  }

  public void createComponentPort(String id, ComponentPortType cpType, String containerId) {
    String name = null;
    switch (cpType) {
    case IN_FLOW_PORT:
      name = IToolNameConstants.TOOL_XAB_CREATE_INFLOW_PORT;
      break;
    case OUT_FLOW_PORT:
      name = IToolNameConstants.TOOL_XAB_CREATE_OUTFLOW_PORT;
      break;
    case IN_OUT_FLOW_PORT:
      name = IToolNameConstants.TOOL_XAB_CREATE_INOUT_FLOW_PORT;
      break;
    case STANDARD_PORT:
      name = IToolNameConstants.TOOL_XAB_CREATE_STANDARD_PORT;
      break;
    default:
      break;
    }

    new CreateAbstractDNodeTool<DNode>(this, name, containerId, id).run();
  }

  public void createPhysicalPort(String id, String containerId) {
    new CreateAbstractDNodeTool<DNode>(this, IToolNameConstants.TOOL_XAB_CREATE_PHYSICAL_PORT, containerId, id).run();
  }

  public void createPortAllocation(String sourceId, String targetId) {
    new CreateDEdgeTool(this, IToolNameConstants.TOOL_XAB_CREATE_PORT_ALLOCATION, sourceId, targetId).run();
  }

  public void removePortAllocation(String containerId, String id) {
    new InsertRemoveTool(this, IToolNameConstants.TOOL_XAB_INSERT_REMOVE_PORT_ALLOCATION, containerId).insert(id);
  }

  public void insertPortAllocation(String containerId, String id) {
    new InsertRemoveTool(this, IToolNameConstants.TOOL_XAB_INSERT_REMOVE_PORT_ALLOCATION, containerId).remove(id);
  }

  public void selectElementsFromModesAndStates(String[] selectedModeAndStates, String... inserted) {
    String name = null;
    if (type == Type.OA) {
      name = IToolNameConstants.TOOL_OAB_INSERT_ACTIVITIES_FROM_MODE_STATE;
    } else {
      name = IToolNameConstants.TOOL_XAB_INSERT_FUNCTIONS_FROM_MODE_STATE;
    }

    new SelectFromListTool(this, name, this.getDiagramId(), inserted).select(selectedModeAndStates);
  }

  public void selectElementsFromScenario(String[] selectedScenarioIds, String... inserted) {
    new SelectFromListTool(this, IToolNameConstants.TOOL_XAB_INSERT_ELEMENTS_FROM_SCENARIO, this.getDiagramId(),
        inserted).select(selectedScenarioIds);
  }

  private String getToolNameShowHidePorts() {
    String name = null;
    if (type == Type.SA) {
      name = IToolNameConstants.TOOL_SAB_INSERT_REMOVE_PORTS;
    } else if (type == Type.LA) {
      name = IToolNameConstants.TOOL_LAB_INSERT_REMOVE_PORTS;
    } else if (type == Type.PA) {
      name = IToolNameConstants.TOOL_PAB_INSERT_REMOVE_PORTS;
    }
    return name;
  }

  public void insertPort(String id, String containerId) {
    new InsertRemoveTool(this, getToolNameShowHidePorts(), containerId).insert(id);
  }

  public void removePort(String id, String containerId) {
    new InsertRemoveTool(this, getToolNameShowHidePorts(), containerId).remove(id);
  }

  public void insertFunctionPort(String id, String containerId) {
    new InsertRemoveTool(this, IToolNameConstants.TOOL_XAB_INSERT_REMOVE_FUNCTION_PORTS, containerId).insert(id);
  }

  public void removeFunctionPort(String id, String containerId) {
    new InsertRemoveTool(this, IToolNameConstants.TOOL_XAB_INSERT_REMOVE_FUNCTION_PORTS, containerId).remove(id);
  }

  public void deleteControlNodes(String... ids) {
    deleteSequenceLinks(ids);
  }

  public void deleteSequenceLinks(String... ids) {
    List<DSemanticDecorator> decorators = Arrays.stream(ids).map(this::getView).collect(Collectors.toList());
    DeleteHookHelper helper = new DeleteHookHelper(decorators);
    assertTrue(helper.checkDeleteHook());
  }

  public void insertCategory(String id, String containerId) {
    // new InsertRemoveTool(this, getToolNameShowCategories(), containerId).insert(id);
    new SelectFromListTool(this, IToolNameConstants.TOOL_XAB_INSERT_REMOVE_CATEGORIES, containerId, id).select(id);
  }

  public void removeCategory(String id, String containerId) {
    // new InsertRemoveTool(this, getToolNameShowCategories(), containerId).remove(id);
    new SelectFromListTool(this, IToolNameConstants.TOOL_XAB_INSERT_REMOVE_CATEGORIES, containerId).select(id);
  }

  public void switchComponentExchangesCategories(String id, boolean addCategory) {
    if (addCategory)
      new SwitchTool(this, IToolNameConstants.TOOL_SAB_INSERT_REMOVE_COMPONENT_EXCHANGES_CATEGORIES).insert(id);
    else
      new SwitchTool(this, IToolNameConstants.TOOL_SAB_INSERT_REMOVE_COMPONENT_EXCHANGES_CATEGORIES).remove(id);
  }

  public void switchPhysicalLinksCategories(String id, boolean addCategory) {
    if (addCategory)
      new SwitchTool(this, IToolNameConstants.TOOL_PAB_INSERT_REMOVE_PHYSICAL_LINKS_CATEGORIES).insert(id);
    else
      new SwitchTool(this, IToolNameConstants.TOOL_PAB_INSERT_REMOVE_PHYSICAL_LINKS_CATEGORIES).remove(id);
  }

  public void switchFunctionalExchangesCategories(String id, boolean addCategory) {
    if (addCategory)
      new SwitchTool(this, IToolNameConstants.TOOL_XAB_INSERT_REMOVE_EXCHANGE_CATEGORIES).insert(id);
    else
      new SwitchTool(this, IToolNameConstants.TOOL_XAB_INSERT_REMOVE_EXCHANGE_CATEGORIES).remove(id);
  }

  public void reuseComponent(String containerId, String... ids) {
    String name = null;
    if (type == Type.LA) {
      name = IToolNameConstants.TOOL_LAB_REUSE_LOGICAL_COMPONENT;
    } else if (type == Type.PA) {
      name = IToolNameConstants.TOOL_PAB_REUSE_PC;
    }
    new ReuseComponentTool(this, name, containerId).select(ids);
  }

  public void reuseActor(String containerId, String... ids) {
    String name = null;
    if (type == Type.SA) {
      name = IToolNameConstants.TOOL_SAB_REUSE_ACTOR;
    } else if (type == Type.LA) {
      name = IToolNameConstants.TOOL_LAB_REUSE_LOGICAL_ACTOR;
    } else if (type == Type.PA) {
      name = IToolNameConstants.TOOL_PAB_REUSE_PHYSICAL_ACTOR;
    }
    new ReuseComponentTool(this, name, containerId).select(ids);
  }

  @Override
  public void hasntView(String identifier) {
    super.hasntView(identifier);
    checkContainedElements(identifier);
  }

  public void checkContainedElements(String id) {
    EObject semantic = getSessionContext().getSemanticElement(id);

    if (semantic != null) {
      if (semantic instanceof Part) {
        Part partSem = (Part) semantic;
        if (partSem.getAbstractType() instanceof Component) {
          Component component = (Component) partSem.getAbstractType();
          Collection<ComponentExchange> ce = ComponentExt.getAllRelatedComponentExchange(component);
          for (ComponentExchange c : ce) {
            super.hasntView(c.getId());
          }
        }
      }
    }
  }

  public BlockArchitectureExt.Type getDiagramType() {
    return type;
  }
}
