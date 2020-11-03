/*******************************************************************************
 * Copyright (c) 2006, 2020 THALES GLOBAL SERVICES.
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

import java.util.Arrays;

import org.eclipse.sirius.diagram.DDiagram;
import org.eclipse.sirius.diagram.DDiagramElementContainer;
import org.eclipse.sirius.diagram.DNode;
import org.junit.Assert;
import org.polarsys.capella.core.data.capellacore.CapellaElement;
import org.polarsys.capella.core.data.information.ExchangeMechanism;
import org.polarsys.capella.core.sirius.analysis.IDiagramNameConstants;
import org.polarsys.capella.core.sirius.analysis.constants.IDNDToolNameConstants;
import org.polarsys.capella.core.sirius.analysis.constants.IToolNameConstants;
import org.polarsys.capella.test.diagram.common.ju.step.crud.CreateDiagramStep;
import org.polarsys.capella.test.diagram.common.ju.step.crud.OpenDiagramStep;
import org.polarsys.capella.test.diagram.common.ju.step.tools.CreateContainerTool;
import org.polarsys.capella.test.diagram.common.ju.step.tools.CreateDEdgeTool;
import org.polarsys.capella.test.diagram.common.ju.step.tools.CreateNodeTool;
import org.polarsys.capella.test.diagram.common.ju.step.tools.InsertRemoveTool;
import org.polarsys.capella.test.diagram.common.ju.step.tools.ReconnectTool;
import org.polarsys.capella.test.framework.context.SessionContext;

/**
 * 
 */
public class IDBDiagram extends CommonDiagram {

  String type = null;

  public IDBDiagram(SessionContext context, DDiagram diagram) {
    super(context, diagram);
  }

  public static IDBDiagram createDiagram(SessionContext executionContext, String diagramKind, String targetIdentifier) {
    if (!Arrays.asList(IDiagramNameConstants.INTERFACES_BLANK_DIAGRAM_NAME,
        IDiagramNameConstants.CONTEXTUAL_COMPONENT_DETAILED_INTERFACES_DIAGRAM_NAME,
        IDiagramNameConstants.CONTEXTUAL_COMPONENT_EXTERNAL_INTERFACES_DIAGRAM_NAME,
        IDiagramNameConstants.CONTEXTUAL_COMPONENT_INTERNAL_INTERFACES_DIAGRAM_NAME).contains(diagramKind)) {
      throw new RuntimeException("this diagram is not compatible with IDBDiagram API");
    }

    return (IDBDiagram) new CreateDiagramStep(executionContext, targetIdentifier, diagramKind) {
      @Override
      public DiagramContext getResult() {
        return new IDBDiagram(getExecutionContext(), diagram);
      }
    }.run().open();
  }

  public static IDBDiagram openDiagram(SessionContext executionContext, String name) {
    return (IDBDiagram) new OpenDiagramStep(executionContext, name) {
      @Override
      public DiagramContext getResult() {
        return new IDBDiagram(getExecutionContext(), diagram);
      }
    }.run().open();
  }

  public static IDBDiagram createDiagram(SessionContext executionContext, String targetIdentifier) {
    return createDiagram(executionContext, IDiagramNameConstants.INTERFACES_BLANK_DIAGRAM_NAME, targetIdentifier);
  }

  public String createActor() {
    return createContainerElement(getDiagramId(), getCreateActorToolName());
  }

  public String createSubActor(String containerId) {
    return createContainerElement(containerId, getCreateActorToolName());
  }

  @Deprecated
  public void createActor(String id) {
    new CreateContainerTool(this, getCreateActorToolName(), getDiagramId(), id).run();
  }

  public String createComponent() {
    return createContainerElement(getDiagramId(), getCreateComponentToolName());
  }

  public String createSubComponent(String containerId) {
    return createContainerElement(containerId, getCreateComponentToolName());
  }

  @Deprecated
  public String createComponent(String containerId, String id) {
    DDiagramElementContainer element = new CreateContainerTool(this, getCreateComponentToolName(), containerId, id)
        .run();

    return ((CapellaElement) element.getTarget()).getId();
  }

  @Deprecated
  public void createComponent(String id) {
    createComponent(getDiagramId(), id);
  }

  @Deprecated
  public void createEvent(String containerId, String id) {
    new CreateContainerTool(this, getCreateEventToolName(), containerId, id).run();
  }

  @Deprecated
  public void createEvent(String id) {
    createEvent(getDiagramId(), id);
  }

  public String createEvent() {
    if (IDiagramNameConstants.CONTEXTUAL_COMPONENT_EXTERNAL_INTERFACES_DIAGRAM_NAME.equals(getType())
        || IDiagramNameConstants.CONTEXTUAL_COMPONENT_INTERNAL_INTERFACES_DIAGRAM_NAME.equals(getType())) {
      return createNodeElement(getDiagramId(), getCreateEventToolName());
    }
    return createContainerElement(getDiagramId(), getCreateEventToolName());
  }

  public String createOperation() {
    return createExchangeItem(ExchangeMechanism.OPERATION);
  }

  public String createFlow() {
    return createExchangeItem(ExchangeMechanism.FLOW);
  }

  public String createData() {
    return createExchangeItem(ExchangeMechanism.SHARED_DATA);
  }

  public String createExchangeItem(ExchangeMechanism type) {
    if (IDiagramNameConstants.CONTEXTUAL_COMPONENT_EXTERNAL_INTERFACES_DIAGRAM_NAME.equals(getType())
        || IDiagramNameConstants.CONTEXTUAL_COMPONENT_INTERNAL_INTERFACES_DIAGRAM_NAME.equals(getType())) {
      return createNodeElement(getDiagramId(), getCreateExchangeItemName(type));
    }
    return createContainerElement(getDiagramId(), getCreateExchangeItemName(type));
  }

  public String createExchangeItemNode(ExchangeMechanism type) {
    return createNodeElement(getDiagramId(), getCreateExchangeItemName(type));
  }

  public String createExchangeItemAllocation(String containerId, ExchangeMechanism type) {
    return createNodeListElement(containerId, getCreateExchangeItemName(type));
  }

  public String getToolInsertActors() {
    String toolName = null;
    if (IDiagramNameConstants.INTERFACES_BLANK_DIAGRAM_NAME.equals(getType())) {
      toolName = IToolNameConstants.TOOL_IDB_INSERT_REMOVE_ACTORS;
    } else if (IDiagramNameConstants.CONTEXTUAL_COMPONENT_DETAILED_INTERFACES_DIAGRAM_NAME.equals(getType())) {
      throw new UnsupportedOperationException();
    } else if (IDiagramNameConstants.CONTEXTUAL_COMPONENT_EXTERNAL_INTERFACES_DIAGRAM_NAME.equals(getType())) {
      toolName = IToolNameConstants.TOOL_CEI_INSERT_REMOVE_ACTORS;
    } else if (IDiagramNameConstants.CONTEXTUAL_COMPONENT_INTERNAL_INTERFACES_DIAGRAM_NAME.equals(getType())) {
      toolName = IToolNameConstants.TOOL_LCCII_INSERT_REMOVE_ACTORS;
    }
    return toolName;
  }

  public String getToolInsertComponents() {
    String toolName = null;
    if (IDiagramNameConstants.INTERFACES_BLANK_DIAGRAM_NAME.equals(getType())) {
      toolName = IToolNameConstants.TOOL_IDB_INSERT_REMOVE_COMPONENTS;
    } else if (IDiagramNameConstants.CONTEXTUAL_COMPONENT_DETAILED_INTERFACES_DIAGRAM_NAME.equals(getType())) {
      throw new UnsupportedOperationException();
    } else if (IDiagramNameConstants.CONTEXTUAL_COMPONENT_EXTERNAL_INTERFACES_DIAGRAM_NAME.equals(getType())) {
      toolName = IToolNameConstants.TOOL_CEI_INSERT_REMOVE_COMPONENTS;
    } else if (IDiagramNameConstants.CONTEXTUAL_COMPONENT_INTERNAL_INTERFACES_DIAGRAM_NAME.equals(getType())) {
      toolName = IToolNameConstants.TOOL_LCCII_INSERT_REMOVE_COMPONENTS;
    }
    return toolName;
  }

  public String getToolInsertRelationship() {
    String toolName = null;
    if (IDiagramNameConstants.INTERFACES_BLANK_DIAGRAM_NAME.equals(getType())) {
      toolName = IToolNameConstants.TOOL_IDB_INSERT_REMOVE_RELATIONSHIPS;
    } else {
      throw new UnsupportedOperationException();
    }
    return toolName;
  }

  public void insertActors(String... id) {
    new InsertRemoveTool(this, getToolInsertActors()).insert(id);
  }

  public void insertSubActors(String containerId, String... id) {
    new InsertRemoveTool(this, getToolInsertActors(), containerId).insert(id);
  }

  public void insertInterfaces(String... id) {
    new InsertRemoveTool(this, IToolNameConstants.TOOL_IDB_INSERT_REMOVE_INTERFACES).insert(id);
  }

  public void removeInterfaces(String... id) {
    new InsertRemoveTool(this, IToolNameConstants.TOOL_IDB_INSERT_REMOVE_INTERFACES).remove(id);
  }

  public void insertExchangeItems(String... id) {
    new InsertRemoveTool(this, IToolNameConstants.TOOL_IDB_INSERT_REMOVE_EXCHANGE_ITEM).insert(id);
  }

  public void removeExchangeItems(String... id) {
    new InsertRemoveTool(this, IToolNameConstants.TOOL_IDB_INSERT_REMOVE_EXCHANGE_ITEM).remove(id);
  }

  public void insertRelationships(String containerId, String... id) {
    new InsertRemoveTool(this, IToolNameConstants.TOOL_IDB_INSERT_REMOVE_RELATIONSHIPS__LABEL, containerId).insert(id);
  }

  public void removeRelationships(String containerId, String... id) {
    new InsertRemoveTool(this, IToolNameConstants.TOOL_IDB_INSERT_REMOVE_RELATIONSHIPS__LABEL, containerId).remove(id);
  }

  public void insertComponents(String... id) {
    new InsertRemoveTool(this, getToolInsertComponents()).insert(id);
  }

  public void insertSubComponents(String containerId, String... id) {
    new InsertRemoveTool(this, getToolInsertComponents(), containerId).insert(id);
  }

  public void removeActors(String... id) {
    new InsertRemoveTool(this, getToolInsertActors()).remove(id);
  }

  public void removeComponents(String... id) {
    new InsertRemoveTool(this, getToolInsertComponents()).remove(id);
  }

  public void insertRelationship(String containerId, String... id) {
    new InsertRemoveTool(this, getToolInsertRelationship(), containerId).insert(id);
  }

  public void removeRelationship(String containerId, String... id) {
    new InsertRemoveTool(this, getToolInsertRelationship(), containerId).remove(id);
  }

  @Deprecated
  public void createCommunicationLinkAcquire(String idSource, String idTarget, String id) {
    new CreateDEdgeTool(this, getCommunicationLinkAcquireToolName(), idSource, idTarget, id).run();
  }

  public String createCommunicationLinkAcquire(String idSource, String idTarget) {
    return createEdgeElement(idSource, idTarget, getCommunicationLinkAcquireToolName());
  }

  @Deprecated
  public void createCommunicationLinkTransmit(String idSource, String idTarget, String id) {
    new CreateDEdgeTool(this, getCommunicationLinkTransmitToolName(), idSource, idTarget, id).run();
  }

  public String createCommunicationLinkTransmit(String idSource, String idTarget) {
    return createEdgeElement(idSource, idTarget, getCommunicationLinkTransmitToolName());
  }

  public String createInFlowPort(String idContainer, String id) {
    String toolName = null;
    if (IDiagramNameConstants.INTERFACES_BLANK_DIAGRAM_NAME.equals(getType())) {
      toolName = IToolNameConstants.TOOL_IDB_CREATE_IN_FLOW_PORT;
    } else if (IDiagramNameConstants.CONTEXTUAL_COMPONENT_DETAILED_INTERFACES_DIAGRAM_NAME.equals(getType())) {
      toolName = IToolNameConstants.TOOL_CDI_CREATE_IN_FLOW_PORT;
    } else if (IDiagramNameConstants.CONTEXTUAL_COMPONENT_EXTERNAL_INTERFACES_DIAGRAM_NAME.equals(getType())) {
      toolName = IToolNameConstants.TOOL_CEI_CREATE_IN_FLOW_PORT;
    } else if (IDiagramNameConstants.CONTEXTUAL_COMPONENT_INTERNAL_INTERFACES_DIAGRAM_NAME.equals(getType())) {
      toolName = IToolNameConstants.TOOL_LCCII_CREATE_IN_FLOW_PORT;
    }
    DNode element = new CreateNodeTool(this, toolName, idContainer, id).run();
    return ((CapellaElement) element.getTarget()).getId();
  }

  public String createOutFlowPort(String idContainer, String id) {
    String toolName = null;
    if (IDiagramNameConstants.INTERFACES_BLANK_DIAGRAM_NAME.equals(getType())) {
      toolName = IToolNameConstants.TOOL_IDB_CREATE_OUT_FLOW_PORT;
    } else if (IDiagramNameConstants.CONTEXTUAL_COMPONENT_DETAILED_INTERFACES_DIAGRAM_NAME.equals(getType())) {
      toolName = IToolNameConstants.TOOL_CDI_CREATE_OUT_FLOW_PORT;
    } else if (IDiagramNameConstants.CONTEXTUAL_COMPONENT_EXTERNAL_INTERFACES_DIAGRAM_NAME.equals(getType())) {
      toolName = IToolNameConstants.TOOL_CEI_CREATE_OUT_FLOW_PORT;
    } else if (IDiagramNameConstants.CONTEXTUAL_COMPONENT_INTERNAL_INTERFACES_DIAGRAM_NAME.equals(getType())) {
      toolName = IToolNameConstants.TOOL_LCCII_CREATE_OUT_FLOW_PORT;
    }
    DNode element = new CreateNodeTool(this, toolName, idContainer, id).run();
    return ((CapellaElement) element.getTarget()).getId();
  }

  public String createInOutFlowPort(String idContainer, String id) {
    String toolName = null;
    if (IDiagramNameConstants.INTERFACES_BLANK_DIAGRAM_NAME.equals(getType())) {
      toolName = IToolNameConstants.TOOL_IDB_CREATE_INOUT_FLOW_PORT;
    } else if (IDiagramNameConstants.CONTEXTUAL_COMPONENT_DETAILED_INTERFACES_DIAGRAM_NAME.equals(getType())) {
      toolName = IToolNameConstants.TOOL_CDI_CREATE_INOUT_FLOW_PORT;
    } else if (IDiagramNameConstants.CONTEXTUAL_COMPONENT_EXTERNAL_INTERFACES_DIAGRAM_NAME.equals(getType())) {
      toolName = IToolNameConstants.TOOL_CEI_CREATE_INOUT_FLOW_PORT;
    } else if (IDiagramNameConstants.CONTEXTUAL_COMPONENT_INTERNAL_INTERFACES_DIAGRAM_NAME.equals(getType())) {
      toolName = IToolNameConstants.TOOL_LCCII_CREATE_INOUT_FLOW_PORT_PORT;
    }
    DNode element = new CreateNodeTool(this, toolName, idContainer, id).run();
    return ((CapellaElement) element.getTarget()).getId();
  }

  public String createStandardPort(String containerId, String id) {
    String toolName = null;
    if (IDiagramNameConstants.INTERFACES_BLANK_DIAGRAM_NAME.equals(getType())) {
      toolName = IToolNameConstants.TOOL_IDB_CREATE_STANDARD_PORT;
    } else if (IDiagramNameConstants.CONTEXTUAL_COMPONENT_DETAILED_INTERFACES_DIAGRAM_NAME.equals(getType())) {
      toolName = IToolNameConstants.TOOL_CDI_CREATE_STANDARD_PORT;
    } else if (IDiagramNameConstants.CONTEXTUAL_COMPONENT_EXTERNAL_INTERFACES_DIAGRAM_NAME.equals(getType())) {
      toolName = IToolNameConstants.TOOL_CEI_CREATE_STANDARD_PORT;
    } else if (IDiagramNameConstants.CONTEXTUAL_COMPONENT_INTERNAL_INTERFACES_DIAGRAM_NAME.equals(getType())) {
      toolName = IToolNameConstants.TOOL_LCCII_CREATE_STANDARD_PORT;
    }
    DNode element = new CreateNodeTool(this, toolName, containerId, id).run();
    return ((CapellaElement) element.getTarget()).getId();
  }

  public String createInterface() {
    if (IDiagramNameConstants.CONTEXTUAL_COMPONENT_EXTERNAL_INTERFACES_DIAGRAM_NAME.equals(getType())
        || IDiagramNameConstants.CONTEXTUAL_COMPONENT_INTERNAL_INTERFACES_DIAGRAM_NAME.equals(getType())) {
      return createNodeElement(getDiagramId(), getCreateInterfaceToolName());
    }
    return createContainerElement(getDiagramId(), getCreateInterfaceToolName());
  }

  public String createInterfaceInContainer(String containerId) {
    return createContainerElement(containerId, getCreateInterfaceToolName());
  }

  @Deprecated
  public void createInterface(String id) {
    createInterface(getDiagramId(), id);
  }

  @Deprecated
  public void createInterface(String containerId, String id) {
    new CreateContainerTool(this, getCreateInterfaceToolName(), containerId, id).run();
  }

  public String createGeneralization(String idSource, String idTarget) {
    return createEdgeElement(idSource, idTarget, getCreateGeneralizationToolName());
  }

  public void createGeneralization(String idSource, String idTarget, String id) {
    new CreateDEdgeTool(this, getCreateGeneralizationToolName(), idSource, idTarget, id).run();
  }

  public void createGeneralizationNotEnabled(String idSource, String idTarget) {
    new CreateDEdgeTool(this, getCreateGeneralizationToolName(), idSource, idTarget).cannotRun();
  }

  @Deprecated
  public void createImplements(String idSource, String idTarget, String id) {
    new CreateDEdgeTool(this, getCreateImplementsToolName(), idSource, idTarget, id).run();
  }

  public String createImplements(String idSource, String idTarget) {
    return createEdgeElement(idSource, idTarget, getCreateImplementsToolName());
  }

  public void createImplementsNotEnabled(String idSource, String idTarget) {
    new CreateDEdgeTool(this, getCreateImplementsToolName(), idSource, idTarget).cannotRun();
  }

  @Deprecated
  public void createProvides(String idSource, String idTarget, String id, String idNewSource) {
    new CreateDEdgeTool(this, getCreateProvidesToolName(), idSource, idTarget, id, idNewSource, null).run();
  }

  public String createProvides(String idSource, String idTarget) {
    return createEdgeElement(idSource, idTarget, getCreateProvidesToolName());
  }

  public void createProvidesNotEnabled(String idSource, String idTarget) {
    new CreateDEdgeTool(this, getCreateProvidesToolName(), idSource, idTarget).cannotRun();
  }

  public void createRequiresNotEnabled(String idSource, String idTarget) {
    new CreateDEdgeTool(this, getCreateRequiresToolName(), idSource, idTarget).cannotRun();
  }

  @Deprecated
  public void createRequires(String idSource, String idTarget, String id, String idNewSource) {
    new CreateDEdgeTool(this, getCreateRequiresToolName(), idSource, idTarget, id, idNewSource, null).run();
  }

  public String createRequires(String idSource, String idTarget) {
    return createEdgeElement(idSource, idTarget, getCreateRequiresToolName());
  }

  public String createDelegation(String idSource, String idTarget) {
    return createEdgeElement(idSource, idTarget, getCreateDelegationToolName());
  }

  @Deprecated
  public void createUses(String idSource, String idTarget, String id) {
    new CreateDEdgeTool(this, getCreateUsesToolName(), idSource, idTarget, id).run();
  }

  public String createUses(String idSource, String idTarget) {
    return createEdgeElement(idSource, idTarget, getCreateUsesToolName());
  }

  public void createUsesNotEnabled(String idSource, String idTarget, String id) {
    new CreateDEdgeTool(this, getCreateUsesToolName(), idSource, idTarget, id).cannotRun();
  }

  public void dragAndDropComponentFromExplorer(String idDraggedElement, String containerId) {
    dragAndDropFromExplorer(idDraggedElement, containerId, IDNDToolNameConstants.TOOL_IDB_DND_COMPONENT_FROM_EXPLORER);
  }

  public void dragAndDropComponentFromDiagram(String idDraggedElement, String containerId) {
    dragAndDrop(idDraggedElement, containerId, IDNDToolNameConstants.TOOL_IDB_DND_COMPONENT);
  }

  public void dragAndDropShouldFail(String sourceId, String targetId) {
    try {
      dragAndDropComponentFromDiagram(sourceId, targetId);
      Assert.fail(
          "Drag and drop should have failed for diagram: " + this + " source " + sourceId + " target " + targetId);
    } catch (AssertionError error) {
      Assert.assertTrue(error.getMessage().startsWith("Precondition"));
    }
  }

  public void dragAndDropShouldSucceed(String sourceId, String targetId) {
    dragAndDropComponentFromDiagram(sourceId, targetId);
  }

  public void dragAndDropComponentPortFromExplorer(String idDraggedElement, String containerId) {
    dragAndDropFromExplorer(idDraggedElement, containerId,
        IDNDToolNameConstants.TOOL_IDB_DND_COMPONENTPORT_FROM_EXPLORER);
  }

  public void dragAndDropComponentPortFromDiagram(String idDraggedElement, String containerId) {
    dragAndDrop(idDraggedElement, containerId, IDNDToolNameConstants.TOOL_IDB_DND_COMPONENTPORT);
  }

  public void dragAndDropInterfaceFromExplorer(String idDraggedElement, String containerId) {
    dragAndDropFromExplorer(idDraggedElement, containerId,
        IDNDToolNameConstants.TOOL_IDB_DND_INTERFACEDROP_FROM_EXPLORER);
  }

  public void dragAndDropInterfaceFromDiagram(String idDraggedElement, String containerId) {
    dragAndDrop(idDraggedElement, containerId, IDNDToolNameConstants.TOOL_IDB_DND_INTERFACEDROP);
  }

  public void dragAndDropExchangeItemAllocationFromExplorer(String idDraggedElement, String containerId) {
    dragAndDropFromExplorer(idDraggedElement, containerId,
        IDNDToolNameConstants.TOOL_IDB_DND_EXCHANGEITEM_ALLOCATION_FROM_EXPLORER);
  }

  public void dragAndDropExchangeItemAllocationFromDiagram(String idDraggedElement, String containerId) {
    dragAndDrop(idDraggedElement, containerId, IDNDToolNameConstants.TOOL_IDB_DND_EXCHANGEITEM_ALLOCATION);
  }

  public void reconnectGeneralizationSource(String edgeId, String oldSourceId, String newSourceId) {
    new ReconnectTool(this, getReconnectGeneralizationSourceToolName(), edgeId, oldSourceId, newSourceId).run();
  }

  public void reconnectGeneralizationTarget(String edgeId, String oldTargetId, String newTargetId) {
    new ReconnectTool(this, getReconnectGeneralizationTargetToolName(), edgeId, oldTargetId, newTargetId).run();
  }

  public void cannotReconnectGeneralizationSource(String edgeId, String oldSourceId, String newSourceId) {
    new ReconnectTool(this, getReconnectGeneralizationSourceToolName(), edgeId, oldSourceId, newSourceId).shouldFail();
  }

  public void cannotReconnectGeneralizationTarget(String edgeId, String oldTargetId, String newTargetId) {
    new ReconnectTool(this, getReconnectGeneralizationTargetToolName(), edgeId, oldTargetId, newTargetId).shouldFail();
  }

  public void reconnectCommunicationLinkSource(String edgeId, String oldSourceId, String newSourceId) {
    new ReconnectTool(this, getReconnectCommunicationLinkSourceToolName(), edgeId, oldSourceId, newSourceId).run();
  }

  public void reconnectCommunicationLinkTarget(String edgeId, String oldTargetId, String newTargetId) {
    new ReconnectTool(this, getReconnectCommunicationLinkTargetToolName(), edgeId, oldTargetId, newTargetId).run();
  }

  public void cannotReconnectCommunicationLinkSource(String edgeId, String oldSourceId, String newSourceId) {
    new ReconnectTool(this, getReconnectCommunicationLinkSourceToolName(), edgeId, oldSourceId, newSourceId)
        .shouldFail();
  }

  public void cannotReconnectCommunicationLinkTarget(String edgeId, String oldTargetId, String newTargetId) {
    new ReconnectTool(this, getReconnectCommunicationLinkTargetToolName(), edgeId, oldTargetId, newTargetId)
        .shouldFail();
  }

  public void reconnectImplementsTarget(String edgeId, String oldTargetId, String newTargetId) {
    new ReconnectTool(this, getReconnectImplementsTargetToolName(), edgeId, oldTargetId, newTargetId).run();
  }

  public void cannotReconnectImplementsTarget(String edgeId, String oldTargetId, String newTargetId) {
    new ReconnectTool(this, getReconnectImplementsTargetToolName(), edgeId, oldTargetId, newTargetId).shouldFail();
  }

  public void reconnectUsesTarget(String edgeId, String oldTargetId, String newTargetId) {
    new ReconnectTool(this, getReconnectUsesTargetToolName(), edgeId, oldTargetId, newTargetId).run();
  }

  public void cannotReconnectUsesTarget(String edgeId, String oldTargetId, String newTargetId) {
    new ReconnectTool(this, getReconnectUsesTargetToolName(), edgeId, oldTargetId, newTargetId).shouldFail();
  }

  public void reconnectProvidesSource(String edgeId, String oldSourceId, String newSourceId) {
    new ReconnectTool(this, getReconnectProvidesSourceToolName(), edgeId, oldSourceId, newSourceId).run();
  }

  public void reconnectProvidesTarget(String edgeId, String oldTargetId, String newTargetId) {
    new ReconnectTool(this, getReconnectProvidesTargetToolName(), edgeId, oldTargetId, newTargetId).run();
  }

  public void reconnectRequiresSource(String edgeId, String oldSourceId, String newSourceId) {
    new ReconnectTool(this, getReconnectRequiresSourceToolName(), edgeId, oldSourceId, newSourceId).run();
  }

  public void reconnectRequiresTarget(String edgeId, String oldTargetId, String newTargetId) {
    new ReconnectTool(this, getReconnectRequiresTargetToolName(), edgeId, oldTargetId, newTargetId).run();
  }

  protected String getCreateActorToolName() {
    String toolName = null;
    if (IDiagramNameConstants.INTERFACES_BLANK_DIAGRAM_NAME.equals(getType())) {
      toolName = IToolNameConstants.TOOL_IDB_CREATE_ACTOR;
    } else if (IDiagramNameConstants.CONTEXTUAL_COMPONENT_DETAILED_INTERFACES_DIAGRAM_NAME.equals(getType())) {
      throw new UnsupportedOperationException();
    } else if (IDiagramNameConstants.CONTEXTUAL_COMPONENT_EXTERNAL_INTERFACES_DIAGRAM_NAME.equals(getType())) {
      toolName = IToolNameConstants.TOOL_CEI_CREATE_ACTOR;
    } else if (IDiagramNameConstants.CONTEXTUAL_COMPONENT_INTERNAL_INTERFACES_DIAGRAM_NAME.equals(getType())) {
      throw new UnsupportedOperationException();
    }
    return toolName;
  }

  protected String getCreateComponentToolName() {
    String toolName = null;
    if (IDiagramNameConstants.INTERFACES_BLANK_DIAGRAM_NAME.equals(getType())) {
      toolName = IToolNameConstants.TOOL_IDB_CREATE_COMPONENT;
    } else if (IDiagramNameConstants.CONTEXTUAL_COMPONENT_DETAILED_INTERFACES_DIAGRAM_NAME.equals(getType())) {
      throw new UnsupportedOperationException();
    } else if (IDiagramNameConstants.CONTEXTUAL_COMPONENT_EXTERNAL_INTERFACES_DIAGRAM_NAME.equals(getType())) {
      toolName = IToolNameConstants.TOOL_CEI_CREATE_COMPONENT;
    } else if (IDiagramNameConstants.CONTEXTUAL_COMPONENT_INTERNAL_INTERFACES_DIAGRAM_NAME.equals(getType())) {
      toolName = IToolNameConstants.TOOL_LCCII_CREATE_LOGICAL_COMPONENT;
    }
    return toolName;
  }

  protected String getCreateInterfaceToolName() {
    String toolName = null;
    if (IDiagramNameConstants.INTERFACES_BLANK_DIAGRAM_NAME.equals(getType())) {
      toolName = IToolNameConstants.TOOL_IDB_CREATE_INTERFACE;
    } else if (IDiagramNameConstants.CONTEXTUAL_COMPONENT_DETAILED_INTERFACES_DIAGRAM_NAME.equals(getType())) {
      toolName = IToolNameConstants.TOOL_CDI_CREATE_INTERFACE;
    } else if (IDiagramNameConstants.CONTEXTUAL_COMPONENT_EXTERNAL_INTERFACES_DIAGRAM_NAME.equals(getType())) {
      toolName = IToolNameConstants.TOOL_CEI_CREATE_INTERFACE;
    } else if (IDiagramNameConstants.CONTEXTUAL_COMPONENT_INTERNAL_INTERFACES_DIAGRAM_NAME.equals(getType())) {
      toolName = IToolNameConstants.TOOL_LCCII_CREATE_INTERFACE;
    }
    return toolName;
  }

  protected String getCreateEventToolName() {
    String toolName = null;
    if (IDiagramNameConstants.INTERFACES_BLANK_DIAGRAM_NAME.equals(getType())) {
      toolName = IToolNameConstants.TOOL_IDB_CREATE_EVENT;
    } else if (IDiagramNameConstants.CONTEXTUAL_COMPONENT_DETAILED_INTERFACES_DIAGRAM_NAME.equals(getType())) {
      toolName = IToolNameConstants.TOOL_CDI_CREATE_EVENT;
    } else if (IDiagramNameConstants.CONTEXTUAL_COMPONENT_EXTERNAL_INTERFACES_DIAGRAM_NAME.equals(getType())) {
      toolName = IToolNameConstants.TOOL_CEI_CREATE_EVENT;
    } else if (IDiagramNameConstants.CONTEXTUAL_COMPONENT_INTERNAL_INTERFACES_DIAGRAM_NAME.equals(getType())) {
      toolName = IToolNameConstants.TOOL_LCCII_CREATE_ECHANGE_ITEM_EVENT;
    }
    return toolName;
  }

  protected String getCreateExchangeItemName(ExchangeMechanism eiType) {
    switch (eiType) {
    case EVENT:
      return IToolNameConstants.TOOL_IDB_CREATE_EVENT;
    case OPERATION:
      return IToolNameConstants.TOOL_IDB_CREATE_OPERATION;
    case FLOW:
      return IToolNameConstants.TOOL_IDB_CREATE_FLOW;
    case SHARED_DATA:
      return IToolNameConstants.TOOL_IDB_CREATE_DATA;
    case UNSET:
      return IToolNameConstants.TOOL_IDB_CREATE_UNDEFINED_EXCHANGE_ITEM;
    default:
      break;
    }
    return null;
  }

  protected String getCommunicationLinkAcquireToolName() {
    String toolName = null;
    if (IDiagramNameConstants.INTERFACES_BLANK_DIAGRAM_NAME.equals(getType())) {
      toolName = IToolNameConstants.TOOL_IDB_CREATE_COMMUNICATIONLINK_ACQUIRE;
    } else if (IDiagramNameConstants.CONTEXTUAL_COMPONENT_DETAILED_INTERFACES_DIAGRAM_NAME.equals(getType())) {
      toolName = IToolNameConstants.TOOL_CDI_CREATE_ACQUIRE;
    } else if (IDiagramNameConstants.CONTEXTUAL_COMPONENT_EXTERNAL_INTERFACES_DIAGRAM_NAME.equals(getType())) {
      toolName = IToolNameConstants.TOOL_CEI_CREATE_ACQUIRE;
    } else if (IDiagramNameConstants.CONTEXTUAL_COMPONENT_INTERNAL_INTERFACES_DIAGRAM_NAME.equals(getType())) {
      toolName = IToolNameConstants.TOOL_LCCII_CREATE_ACQUIRE;
    }
    return toolName;
  }

  protected String getCommunicationLinkTransmitToolName() {
    String toolName = null;
    if (IDiagramNameConstants.INTERFACES_BLANK_DIAGRAM_NAME.equals(getType())) {
      toolName = IToolNameConstants.TOOL_IDB_CREATE_TRANSMIT;
    } else if (IDiagramNameConstants.CONTEXTUAL_COMPONENT_DETAILED_INTERFACES_DIAGRAM_NAME.equals(getType())) {
      toolName = IToolNameConstants.TOOL_CDI_CREATE_TRANSMIT;
    } else if (IDiagramNameConstants.CONTEXTUAL_COMPONENT_EXTERNAL_INTERFACES_DIAGRAM_NAME.equals(getType())) {
      toolName = IToolNameConstants.TOOL_CEI_CREATE_TRANSMIT;
    } else if (IDiagramNameConstants.CONTEXTUAL_COMPONENT_INTERNAL_INTERFACES_DIAGRAM_NAME.equals(getType())) {
      toolName = IToolNameConstants.TOOL_LCCII_CREATE_TRANSMIT;
    }
    return toolName;
  }

  protected String getCreateImplementsToolName() {
    String toolName = null;
    if (IDiagramNameConstants.INTERFACES_BLANK_DIAGRAM_NAME.equals(getType())) {
      toolName = IToolNameConstants.TOOL_IDB_CREATE_IMPLEMENTS;
    } else if (IDiagramNameConstants.CONTEXTUAL_COMPONENT_DETAILED_INTERFACES_DIAGRAM_NAME.equals(getType())) {
      toolName = IToolNameConstants.TOOL_CDI_CREATE_IMPLEMENTS;
    } else if (IDiagramNameConstants.CONTEXTUAL_COMPONENT_EXTERNAL_INTERFACES_DIAGRAM_NAME.equals(getType())) {
      toolName = IToolNameConstants.TOOL_CEI_CREATE_IMPLEMENTS;
    } else if (IDiagramNameConstants.CONTEXTUAL_COMPONENT_INTERNAL_INTERFACES_DIAGRAM_NAME.equals(getType())) {
      toolName = IToolNameConstants.TOOL_LCCII_CREATE_IMPLEMENTS;
    }
    return toolName;
  }

  protected String getCreateUsesToolName() {
    String toolName = null;
    if (IDiagramNameConstants.INTERFACES_BLANK_DIAGRAM_NAME.equals(getType())) {
      toolName = IToolNameConstants.TOOL_IDB_CREATE_USES;
    } else if (IDiagramNameConstants.CONTEXTUAL_COMPONENT_DETAILED_INTERFACES_DIAGRAM_NAME.equals(getType())) {
      toolName = IToolNameConstants.TOOL_CDI_CREATE_USES;
    } else if (IDiagramNameConstants.CONTEXTUAL_COMPONENT_EXTERNAL_INTERFACES_DIAGRAM_NAME.equals(getType())) {
      toolName = IToolNameConstants.TOOL_CEI_CREATE_USES;
    } else if (IDiagramNameConstants.CONTEXTUAL_COMPONENT_INTERNAL_INTERFACES_DIAGRAM_NAME.equals(getType())) {
      toolName = IToolNameConstants.TOOL_LCCII_CREATE_USES;
    }
    return toolName;
  }

  protected String getCreateProvidesToolName() {
    String toolName = null;
    if (IDiagramNameConstants.INTERFACES_BLANK_DIAGRAM_NAME.equals(getType())) {
      toolName = IToolNameConstants.TOOL_IDB_CREATE_PROVIDES;
    } else if (IDiagramNameConstants.CONTEXTUAL_COMPONENT_DETAILED_INTERFACES_DIAGRAM_NAME.equals(getType())) {
      toolName = IToolNameConstants.TOOL_CDI_CREATE_PROVIDES;
    } else if (IDiagramNameConstants.CONTEXTUAL_COMPONENT_EXTERNAL_INTERFACES_DIAGRAM_NAME.equals(getType())) {
      toolName = IToolNameConstants.TOOL_CEI_CREATE_PROVIDES;
    } else if (IDiagramNameConstants.CONTEXTUAL_COMPONENT_INTERNAL_INTERFACES_DIAGRAM_NAME.equals(getType())) {
      toolName = IToolNameConstants.TOOL_LCCII_CREATE_PROVIDES;
    }
    return toolName;
  }

  protected String getCreateRequiresToolName() {
    String toolName = null;
    if (IDiagramNameConstants.INTERFACES_BLANK_DIAGRAM_NAME.equals(getType())) {
      toolName = IToolNameConstants.TOOL_IDB_CREATE_REQUIRES;
    } else if (IDiagramNameConstants.CONTEXTUAL_COMPONENT_DETAILED_INTERFACES_DIAGRAM_NAME.equals(getType())) {
      toolName = IToolNameConstants.TOOL_CDI_CREATE_REQUIRES;
    } else if (IDiagramNameConstants.CONTEXTUAL_COMPONENT_EXTERNAL_INTERFACES_DIAGRAM_NAME.equals(getType())) {
      toolName = IToolNameConstants.TOOL_CEI_CREATE_REQUIRES;
    } else if (IDiagramNameConstants.CONTEXTUAL_COMPONENT_INTERNAL_INTERFACES_DIAGRAM_NAME.equals(getType())) {
      toolName = IToolNameConstants.TOOL_LCCII_CREATE_REQUIRES;
    }
    return toolName;
  }

  protected String getCreateDelegationToolName() {
    String toolName = null;
    if (IDiagramNameConstants.INTERFACES_BLANK_DIAGRAM_NAME.equals(getType())) {
      toolName = IToolNameConstants.TOOL_IDB_CREATE_DELEGATION;
    } else if (IDiagramNameConstants.CONTEXTUAL_COMPONENT_DETAILED_INTERFACES_DIAGRAM_NAME.equals(getType())) {
      throw new UnsupportedOperationException();
    } else if (IDiagramNameConstants.CONTEXTUAL_COMPONENT_EXTERNAL_INTERFACES_DIAGRAM_NAME.equals(getType())) {
      throw new UnsupportedOperationException();
    } else if (IDiagramNameConstants.CONTEXTUAL_COMPONENT_INTERNAL_INTERFACES_DIAGRAM_NAME.equals(getType())) {
      toolName = IToolNameConstants.TOOL_LCCII_CREATE_DELEGATION;
    }
    return toolName;
  }

  protected String getCreateGeneralizationToolName() {
    String toolName = null;
    if (IDiagramNameConstants.INTERFACES_BLANK_DIAGRAM_NAME.equals(getType())) {
      toolName = IToolNameConstants.TOOL_IDB_CREATE_GENERALIZATION;
    } else if (IDiagramNameConstants.CONTEXTUAL_COMPONENT_DETAILED_INTERFACES_DIAGRAM_NAME.equals(getType())) {
      toolName = IToolNameConstants.TOOL_CDI_CREATE_GENERALIZATION;
    } else if (IDiagramNameConstants.CONTEXTUAL_COMPONENT_EXTERNAL_INTERFACES_DIAGRAM_NAME.equals(getType())) {
      toolName = IToolNameConstants.TOOL_CEI_CREATE_GENERALIZATION;
    } else if (IDiagramNameConstants.CONTEXTUAL_COMPONENT_INTERNAL_INTERFACES_DIAGRAM_NAME.equals(getType())) {
      toolName = IToolNameConstants.TOOL_LCCII_CREATE_GENERALIZATION;
    }
    return toolName;
  }

  protected String getReconnectGeneralizationSourceToolName() {
    String toolName = null;
    if (IDiagramNameConstants.INTERFACES_BLANK_DIAGRAM_NAME.equals(getType())) {
      toolName = IToolNameConstants.TOOL_IDB_RECONNECT_GENERALIZATION_SOURCE;
    } else if (IDiagramNameConstants.CONTEXTUAL_COMPONENT_DETAILED_INTERFACES_DIAGRAM_NAME.equals(getType())) {
      throw new UnsupportedOperationException();
    } else if (IDiagramNameConstants.CONTEXTUAL_COMPONENT_EXTERNAL_INTERFACES_DIAGRAM_NAME.equals(getType())) {
      toolName = IToolNameConstants.TOOL_CEI_CREATE_ACTOR;
    } else if (IDiagramNameConstants.CONTEXTUAL_COMPONENT_INTERNAL_INTERFACES_DIAGRAM_NAME.equals(getType())) {
      throw new UnsupportedOperationException();
    }
    return toolName;
  }

  protected String getReconnectGeneralizationTargetToolName() {
    String toolName = null;
    if (IDiagramNameConstants.INTERFACES_BLANK_DIAGRAM_NAME.equals(getType())) {
      toolName = IToolNameConstants.TOOL_IDB_RECONNECT_GENERALIZATION_TARGET;
    } else if (IDiagramNameConstants.CONTEXTUAL_COMPONENT_DETAILED_INTERFACES_DIAGRAM_NAME.equals(getType())) {
      throw new UnsupportedOperationException();
    } else if (IDiagramNameConstants.CONTEXTUAL_COMPONENT_EXTERNAL_INTERFACES_DIAGRAM_NAME.equals(getType())) {
      toolName = IToolNameConstants.TOOL_CEI_CREATE_ACTOR;
    } else if (IDiagramNameConstants.CONTEXTUAL_COMPONENT_INTERNAL_INTERFACES_DIAGRAM_NAME.equals(getType())) {
      throw new UnsupportedOperationException();
    }
    return toolName;
  }

  protected String getReconnectCommunicationLinkSourceToolName() {
    String toolName = null;
    if (IDiagramNameConstants.INTERFACES_BLANK_DIAGRAM_NAME.equals(getType())) {
      toolName = IToolNameConstants.TOOL_IDB_RECONNECT_COMMUNICATIONLINK_SOURCE;
    } else if (IDiagramNameConstants.CONTEXTUAL_COMPONENT_DETAILED_INTERFACES_DIAGRAM_NAME.equals(getType())) {
      throw new UnsupportedOperationException();
    } else if (IDiagramNameConstants.CONTEXTUAL_COMPONENT_EXTERNAL_INTERFACES_DIAGRAM_NAME.equals(getType())) {
      toolName = IToolNameConstants.TOOL_CEI_CREATE_ACTOR;
    } else if (IDiagramNameConstants.CONTEXTUAL_COMPONENT_INTERNAL_INTERFACES_DIAGRAM_NAME.equals(getType())) {
      throw new UnsupportedOperationException();
    }
    return toolName;
  }

  protected String getReconnectCommunicationLinkTargetToolName() {
    String toolName = null;
    if (IDiagramNameConstants.INTERFACES_BLANK_DIAGRAM_NAME.equals(getType())) {
      toolName = IToolNameConstants.TOOL_IDB_RECONNECT_COMMUNICATIONLINK_TARGET;
    } else if (IDiagramNameConstants.CONTEXTUAL_COMPONENT_DETAILED_INTERFACES_DIAGRAM_NAME.equals(getType())) {
      throw new UnsupportedOperationException();
    } else if (IDiagramNameConstants.CONTEXTUAL_COMPONENT_EXTERNAL_INTERFACES_DIAGRAM_NAME.equals(getType())) {
      toolName = IToolNameConstants.TOOL_CEI_CREATE_ACTOR;
    } else if (IDiagramNameConstants.CONTEXTUAL_COMPONENT_INTERNAL_INTERFACES_DIAGRAM_NAME.equals(getType())) {
      throw new UnsupportedOperationException();
    }
    return toolName;
  }

  protected String getReconnectImplementsTargetToolName() {
    String toolName = null;
    if (IDiagramNameConstants.INTERFACES_BLANK_DIAGRAM_NAME.equals(getType())) {
      toolName = IToolNameConstants.TOOL_IDB_RECONNECT_IMPLEMENTS_TARGET;
    } else if (IDiagramNameConstants.CONTEXTUAL_COMPONENT_DETAILED_INTERFACES_DIAGRAM_NAME.equals(getType())) {
      throw new UnsupportedOperationException();
    } else if (IDiagramNameConstants.CONTEXTUAL_COMPONENT_EXTERNAL_INTERFACES_DIAGRAM_NAME.equals(getType())) {
      toolName = IToolNameConstants.TOOL_CEI_CREATE_ACTOR;
    } else if (IDiagramNameConstants.CONTEXTUAL_COMPONENT_INTERNAL_INTERFACES_DIAGRAM_NAME.equals(getType())) {
      throw new UnsupportedOperationException();
    }
    return toolName;
  }

  protected String getReconnectUsesTargetToolName() {
    String toolName = null;
    if (IDiagramNameConstants.INTERFACES_BLANK_DIAGRAM_NAME.equals(getType())) {
      toolName = IToolNameConstants.TOOL_IDB_RECONNECT_USES_TARGET;
    } else if (IDiagramNameConstants.CONTEXTUAL_COMPONENT_DETAILED_INTERFACES_DIAGRAM_NAME.equals(getType())) {
      throw new UnsupportedOperationException();
    } else if (IDiagramNameConstants.CONTEXTUAL_COMPONENT_EXTERNAL_INTERFACES_DIAGRAM_NAME.equals(getType())) {
      toolName = IToolNameConstants.TOOL_CEI_CREATE_ACTOR;
    } else if (IDiagramNameConstants.CONTEXTUAL_COMPONENT_INTERNAL_INTERFACES_DIAGRAM_NAME.equals(getType())) {
      throw new UnsupportedOperationException();
    }
    return toolName;
  }

  protected String getReconnectProvidesSourceToolName() {
    String toolName = null;
    if (IDiagramNameConstants.INTERFACES_BLANK_DIAGRAM_NAME.equals(getType())) {
      toolName = IToolNameConstants.TOOL_IDB_RECONNECT_PINPROVIDED_SOURCE;
    } else if (IDiagramNameConstants.CONTEXTUAL_COMPONENT_DETAILED_INTERFACES_DIAGRAM_NAME.equals(getType())) {
      throw new UnsupportedOperationException();
    } else if (IDiagramNameConstants.CONTEXTUAL_COMPONENT_EXTERNAL_INTERFACES_DIAGRAM_NAME.equals(getType())) {
      toolName = IToolNameConstants.TOOL_CEI_CREATE_ACTOR;
    } else if (IDiagramNameConstants.CONTEXTUAL_COMPONENT_INTERNAL_INTERFACES_DIAGRAM_NAME.equals(getType())) {
      throw new UnsupportedOperationException();
    }
    return toolName;
  }

  protected String getReconnectProvidesTargetToolName() {
    String toolName = null;
    if (IDiagramNameConstants.INTERFACES_BLANK_DIAGRAM_NAME.equals(getType())) {
      toolName = IToolNameConstants.TOOL_IDB_RECONNECT_PINPROVIDEDINTERFACE_TARGET;
    } else if (IDiagramNameConstants.CONTEXTUAL_COMPONENT_DETAILED_INTERFACES_DIAGRAM_NAME.equals(getType())) {
      throw new UnsupportedOperationException();
    } else if (IDiagramNameConstants.CONTEXTUAL_COMPONENT_EXTERNAL_INTERFACES_DIAGRAM_NAME.equals(getType())) {
      toolName = IToolNameConstants.TOOL_CEI_CREATE_ACTOR;
    } else if (IDiagramNameConstants.CONTEXTUAL_COMPONENT_INTERNAL_INTERFACES_DIAGRAM_NAME.equals(getType())) {
      throw new UnsupportedOperationException();
    }
    return toolName;
  }

  protected String getReconnectRequiresSourceToolName() {
    String toolName = null;
    if (IDiagramNameConstants.INTERFACES_BLANK_DIAGRAM_NAME.equals(getType())) {
      toolName = IToolNameConstants.TOOL_IDB_RECONNECT_PINREQUIRED_SOURCE;
    } else if (IDiagramNameConstants.CONTEXTUAL_COMPONENT_DETAILED_INTERFACES_DIAGRAM_NAME.equals(getType())) {
      throw new UnsupportedOperationException();
    } else if (IDiagramNameConstants.CONTEXTUAL_COMPONENT_EXTERNAL_INTERFACES_DIAGRAM_NAME.equals(getType())) {
      toolName = IToolNameConstants.TOOL_CEI_CREATE_ACTOR;
    } else if (IDiagramNameConstants.CONTEXTUAL_COMPONENT_INTERNAL_INTERFACES_DIAGRAM_NAME.equals(getType())) {
      throw new UnsupportedOperationException();
    }
    return toolName;
  }

  protected String getReconnectRequiresTargetToolName() {
    String toolName = null;
    if (IDiagramNameConstants.INTERFACES_BLANK_DIAGRAM_NAME.equals(getType())) {
      toolName = IToolNameConstants.TOOL_IDB_RECONNECT_PINREQUIREDINTERFACE_TARGET;
    } else if (IDiagramNameConstants.CONTEXTUAL_COMPONENT_DETAILED_INTERFACES_DIAGRAM_NAME.equals(getType())) {
      throw new UnsupportedOperationException();
    } else if (IDiagramNameConstants.CONTEXTUAL_COMPONENT_EXTERNAL_INTERFACES_DIAGRAM_NAME.equals(getType())) {
      toolName = IToolNameConstants.TOOL_CEI_CREATE_ACTOR;
    } else if (IDiagramNameConstants.CONTEXTUAL_COMPONENT_INTERNAL_INTERFACES_DIAGRAM_NAME.equals(getType())) {
      throw new UnsupportedOperationException();
    }
    return toolName;
  }
}
