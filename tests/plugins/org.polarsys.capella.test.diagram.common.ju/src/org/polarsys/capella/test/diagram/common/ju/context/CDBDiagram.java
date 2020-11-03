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

import static org.junit.Assert.assertTrue;

import java.util.ArrayList;
import java.util.List;

import org.eclipse.sirius.diagram.DDiagram;
import org.eclipse.sirius.diagram.DEdge;
import org.eclipse.sirius.diagram.DNodeListElement;
import org.eclipse.sirius.viewpoint.DSemanticDecorator;
import org.polarsys.capella.core.data.cs.ExchangeItemAllocation;
import org.polarsys.capella.core.data.cs.Interface;
import org.polarsys.capella.core.data.information.Collection;
import org.polarsys.capella.core.data.information.ExchangeItem;
import org.polarsys.capella.core.data.information.ExchangeItemElement;
import org.polarsys.capella.core.data.information.Parameter;
import org.polarsys.capella.core.data.information.Property;
import org.polarsys.capella.core.data.information.Service;
import org.polarsys.capella.core.diagram.helpers.naming.DiagramDescriptionConstants;
import org.polarsys.capella.core.sirius.analysis.constants.IDNDToolNameConstants;
import org.polarsys.capella.core.sirius.analysis.constants.IToolNameConstants;
import org.polarsys.capella.test.diagram.common.ju.step.crud.CreateDiagramStep;
import org.polarsys.capella.test.diagram.common.ju.step.crud.OpenDiagramStep;
import org.polarsys.capella.test.diagram.common.ju.step.tools.CreateAbstractDNodeWithSelectionTool;
import org.polarsys.capella.test.diagram.common.ju.step.tools.CreateContainerTool;
import org.polarsys.capella.test.diagram.common.ju.step.tools.CreateDEdgeTool;
import org.polarsys.capella.test.diagram.common.ju.step.tools.CreateNodeElementTool;
import org.polarsys.capella.test.diagram.common.ju.step.tools.InsertRemoveTool;
import org.polarsys.capella.test.diagram.common.ju.step.tools.ReconnectTool;
import org.polarsys.capella.test.framework.context.SessionContext;

public class CDBDiagram extends CommonDiagram {
  public enum DataType {
    BOOLEAN_TYPE, BOOLEAN_LITERAL, ENUMERATION, ENUMERATION_LITERAL, NUMERIC_TYPE, STRING_TYPE, PHYSICAL_QUANTITY, UNIT
  };

  public enum DataLiteral {
    BOOLEAN_LITERAL, ENUMERATION_LITERAL
  };

  public enum ReferenceType {
    NUMERIC_REFERENCE, LITERAL_NUMERIC_VALUE, UNARY_EXPRESSION, BINARY_EXPRESSION, LITERAL_STRING_VALUE, STRING_REFERENCE, BOOLEAN_REFERENCE, COMPLEX_VALUE, COMPLEX_VALUE_REFERENCE, ENUMERATION_REFERENCE, COLLECTION_VALUE, COLLECTION_VALUE_REFERENCE
  };

  public enum RelationshipType {
    ASSOCIATION, AGGREGATION, COMPOSITION, GENERALIZATION, COLLECTION_TYPE
  };

  public enum ExchangeItemType {
    EVENT, OPERATION, FLOW, DATA, UNDEFINED
  };

  public CDBDiagram(SessionContext context, DDiagram diagram) {
    super(context, diagram);
  }

  public static CDBDiagram createDiagram(SessionContext executionContext, String targetIdentifier) {
    return (CDBDiagram) new CreateDiagramStep(executionContext, targetIdentifier,
        DiagramDescriptionConstants.CLASS_BLANK_DIAGRAM_NAME) {
      @Override
      public DiagramContext getResult() {
        return new CDBDiagram(getExecutionContext(), diagram);
      }
    }.run().open();
  }

  public static CDBDiagram openDiagram(SessionContext executionContext, String name) {
    return (CDBDiagram) new OpenDiagramStep(executionContext, name) {
      @Override
      public DiagramContext getResult() {
        return new CDBDiagram(getExecutionContext(), diagram);
      }
    }.run().open();
  }
  
  public String createDataPackage() {
    return createDataPackage(getDiagramId());
  }

  public String createDataPackage(String containerId) {
    return createContainerElement(containerId, IToolNameConstants.TOOL_CDB_CREATE_DATA_PACKAGE);
  }

  public String createClass() {
    return createClass(getDiagramId());
  }

  public String createClass(String containerId) {
    return createContainerElement(containerId, IToolNameConstants.TOOL_CDB_CREATE_CLASS);
  }

  public String createUnion() {
    return createUnion(getDiagramId());
  }

  public String createUnion(String containerId) {
    return createContainerElement(containerId, IToolNameConstants.TOOL_CDB_CREATE_UNION);
  }

  public String createCollection() {
    return createCollection(getDiagramId());
  }

  public String createCollection(String containerId) {
    return createContainerElement(containerId, IToolNameConstants.TOOL_CDB_CREATE_COLLECTION);
  }

  public String createNodeDataType(DataType dataType) {
    return createNodeElement(getDiagramId(), getDataTypeName(dataType));
  }

  public String createDataType(DataType dataType) {
    return createContainerElement(getDiagramId(), getDataTypeName(dataType));
  }

  public String createDataLiteral(String containerId, DataLiteral literal) {
    return createNodeListElement(containerId, getDataLiteralName(literal));
  }

  public String createReference(ReferenceType reference) {
    return createNodeElement(getDiagramId(), getReferenceName(reference));
  }

  public String createProperty(String containerId, String dataTypeId) {
    DNodeListElement graphicalElement = new CreateAbstractDNodeWithSelectionTool<DNodeListElement>(this,
        IToolNameConstants.TOOL_CDB_CREATE_PROPERTY, containerId, containerId, dataTypeId, DNodeListElement.class,
        Property.class).run();
    return getSemanticIdFromView(graphicalElement);
  }

  public String createOperation(String containerId) {
    return createNodeListElement(containerId, IToolNameConstants.TOOL_CDB_CREATE_SERVICE);
  }

  public void createParameter(String containerId, String dataTypeId) {
    new CreateParameterTool(this, IToolNameConstants.TOOL_CDB_CREATE_PARAMETER, containerId, containerId, dataTypeId)
        .run();
  }

  public String createRelationship(String sourceId, String targetId, RelationshipType relationship) {
    return createEdgeElement(sourceId, targetId, getRelationshipName(relationship));
  }

  public String createCollectionType(String sourceId, String targetId) {
    DEdge graphicalElement = new CreateCollectionTypeTool(this, IToolNameConstants.TOOL_CDB_CREATE_COLLECTION_TYPE,
        sourceId, targetId).run();
    return getSemanticIdFromView(graphicalElement);
  }

  public void reconnectRelationshipSource(String id, String oldSourceId, String newSourceId,
      RelationshipType relationship) {
    new ReconnectTool(this, getReconnectSourceRelationshipName(relationship), id, oldSourceId, newSourceId).run();
  }

  public void reconnectRelationshipTarget(String id, String oldTargetId, String newTargetId,
      RelationshipType relationship) {
    new ReconnectTool(this, getReconnectTargetRelationshipName(relationship), id, oldTargetId, newTargetId).run();
  }

  public void cannotReconnectRelationshipSource(String id, String oldSourceId, String newSourceId,
      RelationshipType relationship) {
    new ReconnectTool(this, getReconnectSourceRelationshipName(relationship), id, oldSourceId, newSourceId)
        .shouldFail();
  }

  public void cannotReconnectRelationshipTarget(String id, String oldTargetId, String newTargetId,
      RelationshipType relationship) {
    new ReconnectTool(this, getReconnectTargetRelationshipName(relationship), id, oldTargetId, newTargetId)
        .shouldFail();
  }

  public String createInterfacePackage() {
    return createInterfacePackage(getDiagramId());
  }

  public String createInterfacePackage(String containerId) {
    return createContainerElement(containerId, IToolNameConstants.TOOL_CDB_CREATE_INTERFACE_PACKAGE);
  }

  public String createInterface() {
    return createInterface(getDiagramId());
  }

  public String createInterface(String containerId) {
    return createContainerElement(containerId, IToolNameConstants.TOOL_CDB_CREATE_INTERFACE);
  }

  public String createNodeExchangeItem(ExchangeItemType eiType) {
    return createNodeExchangeItem(getDiagramId(), eiType);
  }

  public String createNodeExchangeItem(String containerId, ExchangeItemType eiType) {
    return createNodeElement(containerId, getExchangeItemName(eiType));
  }

  public String createExchangeItem(ExchangeItemType eiType) {
    return createExchangeItemNode(getDiagramId(), eiType);
  }

  public String createExchangeItemNode(String containerId, ExchangeItemType eiType) {
    return createNodeElement(containerId, getExchangeItemName(eiType));
  }

  public String createExchangeItem(String containerId, ExchangeItemType eiType) {
    return createNodeListElement(containerId, getExchangeItemName(eiType));
  }

  public String createExchangeItemElement(String sourceId, String targetId) {
    DEdge graphicalElement = new CreateExchangeItemElementTool(this,
        IToolNameConstants.TOOL_CDB_CREATE_EXCHANGE_ITEM_ELEMENT, sourceId, targetId).run();
    return getSemanticIdFromView(graphicalElement);
  }

  public void removeDataPkg(String... ids) {
    new InsertRemoveTool(this, IToolNameConstants.TOOL_CDB_SHOW_HIDE_DATAPKGS).remove(ids);
  }

  public void insertDataPkg(String... ids) {
    new InsertRemoveTool(this, IToolNameConstants.TOOL_CDB_SHOW_HIDE_DATAPKGS).insert(ids);
  }

  public void removeType(String... ids) {
    new InsertRemoveTool(this, IToolNameConstants.TOOL_CDB_INSERT_REMOVE_TYPE).remove(ids);
  }

  public void insertType(String... ids) {
    new InsertRemoveTool(this, IToolNameConstants.TOOL_CDB_INSERT_REMOVE_TYPE).insert(ids);
  }

  public void removeDataValue(String container, String... ids) {
    new InsertRemoveTool(this, IToolNameConstants.TOOL_CDB_SHOW_HIDE_DATAVALUES, container).remove(ids);
  }

  public void insertDataValue(String container, String... ids) {
    new InsertRemoveTool(this, IToolNameConstants.TOOL_CDB_SHOW_HIDE_DATAVALUES, container).insert(ids);
  }

  public void removeDataValues(String id, String containerId) {
    new InsertRemoveTool(this, IToolNameConstants.TOOL_CDB_INSERT_REMOVE_DATA_VALUES, containerId).remove(id);
  }

  public void insertDataValues(String id, String containerId) {
    new InsertRemoveTool(this, IToolNameConstants.TOOL_CDB_INSERT_REMOVE_DATA_VALUES, containerId).insert(id);
  }

  public void removeRelationship(String id, String containerId) {
    new InsertRemoveTool(this, IToolNameConstants.TOOL_CDB_INSERT_RELATIONSHIPS, containerId).remove(id);
  }

  public void insertRelationship(String id, String containerId) {
    new InsertRemoveTool(this, IToolNameConstants.TOOL_CDB_INSERT_RELATIONSHIPS, containerId).insert(id);
  }

  public void removeProperty(String id, String containerId) {
    new InsertRemoveTool(this, IToolNameConstants.TOOL_CDB_INSERT_REMOVE_PROPERTIES, containerId).remove(id);
  }

  public void insertProperty(String id, String containerId) {
    new InsertRemoveTool(this, IToolNameConstants.TOOL_CDB_INSERT_REMOVE_PROPERTIES, containerId).insert(id);
  }

  public void removeOperation(String id, String containerId) {
    new InsertRemoveTool(this, IToolNameConstants.TOOL_CDB_INSERT_REMOVE_OPERATIONS, containerId).remove(id);
  }

  public void insertOperation(String id, String containerId) {
    new InsertRemoveTool(this, IToolNameConstants.TOOL_CDB_INSERT_REMOVE_OPERATIONS, containerId).insert(id);
  }

  public void removeInterfacePkg(String... ids) {
    new InsertRemoveTool(this, IToolNameConstants.TOOL_CDB_SHOW_HIDE_INTERFACEPKGS).remove(ids);
  }

  public void insertInterfacePkg(String... ids) {
    new InsertRemoveTool(this, IToolNameConstants.TOOL_CDB_SHOW_HIDE_INTERFACEPKGS).insert(ids);
  }

  public void removeInterface(String... ids) {
    new InsertRemoveTool(this, IToolNameConstants.TOOL_CDB_INSERT_REMOVE_INTERFACES).remove(ids);
  }

  public void insertInterface(String... ids) {
    new InsertRemoveTool(this, IToolNameConstants.TOOL_CDB_INSERT_REMOVE_INTERFACES).insert(ids);
  }

  public void removeExchangeItem(String... ids) {
    new InsertRemoveTool(this, IToolNameConstants.TOOL_CDB_SHOW_HIDE_EXCHANGE_ITEMS).remove(ids);
  }

  public void insertExchangeItem(String... ids) {
    new InsertRemoveTool(this, IToolNameConstants.TOOL_CDB_SHOW_HIDE_EXCHANGE_ITEMS).insert(ids);
  }

  public void removeExchangeItemAllocations(String containerId, String... ids) {
    new ManageExchangeItemAllocationTools(this, IToolNameConstants.TOOL_CDB_ALLOCATE_EXCHANGE_ITEM, containerId)
        .remove(ids);
  }

  public void insertExchangeItemAllocations(String containerId, String... ids) {
    new ManageExchangeItemAllocationTools(this, IToolNameConstants.TOOL_CDB_ALLOCATE_EXCHANGE_ITEM, containerId)
        .insert(ids);
  }

  public void insertExchangeItemElement(String id, String containerId) {
    new InsertRemoveTool(this, IToolNameConstants.TOOL_CDB_SHOW_HIDE_EXCHANGEITEMELEMENT_LINK, containerId).insert(id);
  }

  public void removeExchangeItemElement(String id, String containerId) {
    new InsertRemoveTool(this, IToolNameConstants.TOOL_CDB_SHOW_HIDE_EXCHANGEITEMELEMENT_LINK, containerId).remove(id);
  }

  public void dragAndDropDataTypeFromExplorer(String idDraggedElement, String containerId) {
    dragAndDropFromExplorer(idDraggedElement, containerId, IDNDToolNameConstants.TOOL_CDB_DND_DATATYPE_FROM_EXPLORER);
  }

  public void dragAndDropBooleanTypeFromExplorer(String idDraggedElement, String containerId) {
    dragAndDropFromExplorer(idDraggedElement, containerId,
        IDNDToolNameConstants.TOOL_CDB_DND_BOOLEANTYPE_FROM_EXPLORER);
  }

  public void dragAndDropClassFromExplorer(String idDraggedElement, String containerId) {
    dragAndDropFromExplorer(idDraggedElement, containerId, IDNDToolNameConstants.TOOL_CDB_DND_CLASS_FROM_EXPLORER);
  }

  public void dragAndDropCollectionFromExplorer(String idDraggedElement, String containerId) {
    dragAndDropFromExplorer(idDraggedElement, containerId, IDNDToolNameConstants.TOOL_CDB_DND_COLLECTION_FROM_EXPLORER);
  }

  public void dragAndDropEnumerationFromExplorer(String idDraggedElement, String containerId) {
    dragAndDropFromExplorer(idDraggedElement, containerId,
        IDNDToolNameConstants.TOOL_CDB_DND_ENUMERATION_FROM_EXPLORER);
  }

  @Override
  public void dragAndDropConstraintFromExplorer(String idDraggedElement, String containerId) {
    dragAndDropFromExplorer(idDraggedElement, containerId, IDNDToolNameConstants.TOOL_CDB_DND_CONSTRAINT_FROM_EXPLORER);
  }

  public void dragAndDropDataPkgFromExplorer(String idDraggedElement, String containerId) {
    dragAndDropFromExplorer(idDraggedElement, containerId, IDNDToolNameConstants.TOOL_CDB_DND_DATAPKG_FROM_EXPLORER);
  }

  public void dragAndDropUnitFromExplorer(String idDraggedElement, String containerId) {
    dragAndDropFromExplorer(idDraggedElement, containerId, IDNDToolNameConstants.TOOL_CDB_DND_UNIT_FROM_EXPLORER);
  }

  public void dragAndDropClassIntoDataPkg(String idDraggedElement, String containerId) {
    dragAndDrop(idDraggedElement, containerId, IDNDToolNameConstants.TOOL_CDB_DND_CLASS_INTO_DATAPKG);
  }

  public void dragAndDropCollectionIntoDataPkg(String idDraggedElement, String containerId) {
    dragAndDrop(idDraggedElement, containerId, IDNDToolNameConstants.TOOL_CDB_DND_COLLECTION_INTO_DATAPKG);
  }

  public void dragAndDropDataTypeIntoDataPkg(String idDraggedElement, String containerId) {
    dragAndDrop(idDraggedElement, containerId, IDNDToolNameConstants.TOOL_CDB_DND_DATATYPE_INTO_DATAPKG);
  }

  public void dragAndDropDataPkgIntoDataPkg(String idDraggedElement, String containerId) {
    dragAndDrop(idDraggedElement, containerId, IDNDToolNameConstants.TOOL_CDB_DND_DATAPKG_INTO_DATAPKG);
  }

  public void dragAndDropDataValueFromExplorer(String idDraggedElement, String containerId) {
    dragAndDropFromExplorer(idDraggedElement, containerId, IDNDToolNameConstants.TOOL_CDB_DND_DATAVALUE_FROM_EXPLORER);
  }

  public void dragAndDropDataValueIntoDataPkg(String idDraggedElement, String containerId) {
    dragAndDrop(idDraggedElement, containerId, IDNDToolNameConstants.TOOL_CDB_DND_DATAVALUE_INTO_DATAPKG);
  }

  public void dragAndDropDataValueIntoClass(String idDraggedElement, String containerId) {
    dragAndDrop(idDraggedElement, containerId, IDNDToolNameConstants.TOOL_CDB_DND_DATAVALUE_INTO_CLASS);
  }

  public void dragAndDropUnitIntoDataPkg(String idDraggedElement, String containerId) {
    dragAndDrop(idDraggedElement, containerId, IDNDToolNameConstants.TOOL_CDB_DND_UNIT_INTO_DATAPKG);
  }

  public void dragAndDropInterfaceFromExplorer(String idDraggedElement, String containerId) {
    dragAndDropFromExplorer(idDraggedElement, containerId, IDNDToolNameConstants.TOOL_CDB_DND_INTERFACE_FROM_EXPLORER);
  }

  public void dragAndDropExchangeItemFromExplorer(String idDraggedElement, String containerId) {
    dragAndDropFromExplorer(idDraggedElement, containerId,
        IDNDToolNameConstants.TOOL_CDB_DND_EXCHANGEITEM_FROM_EXPLORER);
  }

  public void dragAndDropInterfacePkgFromExplorer(String idDraggedElement, String containerId) {
    dragAndDropFromExplorer(idDraggedElement, containerId,
        IDNDToolNameConstants.TOOL_CDB_DND_INTERFACEPKG_FROM_EXPLORER);
  }

  public void dragAndDropInterfaceIntoInterfacePkg(String idDraggedElement, String containerId) {
    dragAndDrop(idDraggedElement, containerId, IDNDToolNameConstants.TOOL_CDB_DND_INTERFACE_INTO_INTERFACEPKG);
  }

  public void dragAndDropExchangeItemIntoInterfacePkg(String idDraggedElement, String containerId) {
    dragAndDrop(idDraggedElement, containerId, IDNDToolNameConstants.TOOL_CDB_DND_EXCHANGEITEM_INTO_EXCHANGEITEMPKG);
  }

  public void dragAndDropInterfacePkgIntoInterfacePkg(String idDraggedElement, String containerId) {
    dragAndDrop(idDraggedElement, containerId, IDNDToolNameConstants.TOOL_CDB_DND_INTERFACEPKG_INTO_INTERFACEPKG);
  }

  public void dragAndDropComponentFromExplorer(String idDraggedElement, String containerId) {
    dragAndDropFromExplorer(idDraggedElement, containerId, IDNDToolNameConstants.TOOL_CDB_DND_DATATYPE_FROM_EXPLORER);
  }

  private String getDataTypeName(DataType dataType) {
    switch (dataType) {
    case BOOLEAN_TYPE:
      return IToolNameConstants.TOOL_CDB_CREATE_BOOLEAN_TYPE;
    case ENUMERATION:
      return IToolNameConstants.TOOL_CDB_CREATE_ENUMERATION;
    case NUMERIC_TYPE:
      return IToolNameConstants.TOOL_CDB_CREATE_NUMERIC_TYPE;
    case STRING_TYPE:
      return IToolNameConstants.TOOL_CDB_CREATE_STRING_TYPE;
    case PHYSICAL_QUANTITY:
      return IToolNameConstants.TOOL_CDB_CREATE_PHYSICAL_QUANTITY;
    case UNIT:
      return IToolNameConstants.TOOL_CDB_CREATE_UNIT;
    default:
      break;
    }
    return null;
  }

  private String getDataLiteralName(DataLiteral literal) {
    switch (literal) {
    case BOOLEAN_LITERAL:
      return IToolNameConstants.TOOL_CDB_CREATE_BOOLEAN_LITERAL;
    case ENUMERATION_LITERAL:
      return IToolNameConstants.TOOL_CDB_CREATE_ENUMERATION_LITERAL;
    default:
      break;
    }
    return null;
  }

  private String getReferenceName(ReferenceType reference) {
    switch (reference) {
    case NUMERIC_REFERENCE:
      return IToolNameConstants.TOOL_CDB_CREATE_NUMERIC_REFERENCE;
    case LITERAL_NUMERIC_VALUE:
      return IToolNameConstants.TOOL_CDB_CREATE_LITERAL_NUMERIC_VALUE;
    case UNARY_EXPRESSION:
      return IToolNameConstants.TOOL_CDB_CREATE_UNARY_EXPRESSION;
    case BINARY_EXPRESSION:
      return IToolNameConstants.TOOL_CDB_CREATE_BINARY_EXPRESSION;
    case LITERAL_STRING_VALUE:
      return IToolNameConstants.TOOL_CDB_CREATE_LITERAL_STRING_VALUE;
    case STRING_REFERENCE:
      return IToolNameConstants.TOOL_CDB_CREATE_STRING_REFERENCE;
    case BOOLEAN_REFERENCE:
      return IToolNameConstants.TOOL_CDB_CREATE_BOOLEAN_REFERENCE;
    case COMPLEX_VALUE:
      return IToolNameConstants.TOOL_CDB_CREATE_COMPLEX_VALUE;
    case COMPLEX_VALUE_REFERENCE:
      return IToolNameConstants.TOOL_CDB_CREATE_COMPLEX_VALUE_REFERENCE;
    case ENUMERATION_REFERENCE:
      return IToolNameConstants.TOOL_CDB_CREATE_ENUMERATION_REFERENCE;
    case COLLECTION_VALUE:
      return IToolNameConstants.TOOL_CDB_CREATE_COLLECTION_VALUE;
    case COLLECTION_VALUE_REFERENCE:
      return IToolNameConstants.TOOL_CDB_CREATE_COLLECTION_VALUE_REFERENCE;
    default:
      break;
    }
    return "";
  }

  private String getRelationshipName(RelationshipType relationship) {
    switch (relationship) {
    case ASSOCIATION:
      return IToolNameConstants.TOOL_CDB_CREATE_ASSOCIATION;
    case AGGREGATION:
      return IToolNameConstants.TOOL_CDB_CREATE_AGGREGATION;
    case COMPOSITION:
      return IToolNameConstants.TOOL_CDB_CREATE_COMPOSITION;
    case GENERALIZATION:
      return IToolNameConstants.TOOL_CDB_CREATE_GENERALIZATION;
    default:
      break;
    }
    return null;
  }

  private String getReconnectSourceRelationshipName(RelationshipType relationship) {
    switch (relationship) {
    case ASSOCIATION:
      return IToolNameConstants.TOOL_CDB_RECONNECT_ASSOCIATION_SOURCE;
    case GENERALIZATION:
      return IToolNameConstants.TOOL_CDB_RECONNECT_GENERALIZATION_SOURCE;
    case COLLECTION_TYPE:
      return IToolNameConstants.TOOL_CDB_RECONNECT_COLLECTION_TYPE;
    default:
      break;
    }
    return null;
  }

  private String getReconnectTargetRelationshipName(RelationshipType relationship) {
    switch (relationship) {
    case ASSOCIATION:
      return IToolNameConstants.TOOL_CDB_RECONNECT_ASSOCIATION_TARGET;
    case GENERALIZATION:
      return IToolNameConstants.TOOL_CDB_RECONNECT_GENERALIZATION_TARGET;
    case COLLECTION_TYPE:
      return IToolNameConstants.TOOL_CDB_RECONNECT_COLLECTION_TYPE;
    default:
      break;
    }
    return null;
  }

  private String getExchangeItemName(ExchangeItemType eiType) {
    switch (eiType) {
    case EVENT:
      return IToolNameConstants.TOOL_CDB_CREATE_EI_EVENT;
    case OPERATION:
      return IToolNameConstants.TOOL_CDB_CREATE_EI_OPERATION;
    case FLOW:
      return IToolNameConstants.TOOL_CDB_CREATE_EI_FLOW;
    case DATA:
      return IToolNameConstants.TOOL_CDB_CREATE_EI_DATA;
    case UNDEFINED:
      return IToolNameConstants.TOOL_CDB_CREATE_EI_UNSET;
    default:
      break;
    }
    return null;
  }

  @Deprecated
  public void createAssociation(String classSourceId, String classTargetId, String id) {
    new CreateDEdgeTool(this, IToolNameConstants.TOOL_CDB_CREATE_ASSOCIATION, classSourceId, classTargetId, id).run();
  }

  @Deprecated
  public void createEnumeration(String id) {
    new CreateContainerTool(this, IToolNameConstants.TOOL_CDB_CREATE_ENUMERATION, getDiagramId(), id).run();
  }

  @Deprecated
  public void createEnumerationLiteral(String id, String containerId) {
    new CreateNodeElementTool(this, IToolNameConstants.TOOL_CDB_CREATE_ENUMERATION_LITERAL, containerId, id).run();
  }

  @Deprecated
  public void createBooleanType(String id) {
    new CreateContainerTool(this, IToolNameConstants.TOOL_CDB_CREATE_BOOLEAN_TYPE, getDiagramId(), id).run();
  }

  @Deprecated
  public void createLiteralBooleanValue(String id, String containerId) {
    new CreateNodeElementTool(this, IToolNameConstants.TOOL_CDB_CREATE_BOOLEAN_LITERAL, containerId, id).run();
  }

  protected class CreateParameterTool extends CreateAbstractDNodeWithSelectionTool<DNodeListElement> {
    protected List<Parameter> ownedParameters;
    protected Service service;

    public CreateParameterTool(DiagramContext context, String toolName, String targetContainerId, String containerId,
        String selectedId) {
      super(context, toolName, targetContainerId, containerId, selectedId, null, null);
    }

    @Override
    protected void preRunTest() {
      super.preRunTest();
      DSemanticDecorator element = getContainerView();
      assertTrue(element.getTarget() instanceof Service);
      service = (Service) element.getTarget();
      ownedParameters = new ArrayList<Parameter>();
      ownedParameters.addAll(service.getOwnedParameters());
    }

    @Override
    protected void postRunTest() {
      List<Parameter> newOwnedParameters = new ArrayList<Parameter>();
      newOwnedParameters.addAll(service.getOwnedParameters());
      newOwnedParameters.removeAll(ownedParameters);

      assertTrue(newOwnedParameters.size() == 1);
    }

    @Override
    public DNodeListElement getResult() {
      return null;
    }
  }

  protected class CreateCollectionTypeTool extends CreateDEdgeTool {
    public CreateCollectionTypeTool(DiagramContext context, String toolName, String sourceViewId, String targetViewId) {
      super(context, toolName, sourceViewId, targetViewId);
    }

    @Override
    protected void postRunTest() {
      super.postRunTest();
      assertTrue(_newEdgesElements.size() == 1);
      Collection collection = (Collection) _newEdgesElements.iterator().next().getTarget();
      assertTrue(collection.getId().equals(_sourceView));
      assertTrue(collection.getType().getId().equals(_targetView));
    }
  }

  protected class CreateExchangeItemElementTool extends CreateDEdgeTool {
    public CreateExchangeItemElementTool(DiagramContext context, String toolName, String sourceViewId,
        String targetViewId) {
      super(context, toolName, sourceViewId, targetViewId);
    }

    @Override
    protected void postRunTest() {
      super.postRunTest();
      assertTrue(_newEdgesElements.size() == 1);
      ExchangeItemElement eiElement = (ExchangeItemElement) _newEdgesElements.iterator().next().getTarget();
      ExchangeItem ei = getExecutionContext().getSemanticElement(_sourceView);
      assertTrue(ei.getOwnedElements().contains(eiElement));
      assertTrue(eiElement.getType().getId().equals(_targetView));
    }
  }

  protected class ManageExchangeItemAllocationTools extends InsertRemoveTool {
    Interface interf;
    ArrayList<ExchangeItemAllocation> ownedEIAllocations;

    public ManageExchangeItemAllocationTools(DiagramContext context, String toolName, String containerId) {
      super(context, toolName, containerId);
    }

    @Override
    protected void preRunTest() {
      super.preRunTest();
      DSemanticDecorator element = getDiagramContext().getView(containerId);
      assertTrue(element.getTarget() instanceof Interface);
      interf = (Interface) element.getTarget();
      ownedEIAllocations = new ArrayList<ExchangeItemAllocation>();
      ownedEIAllocations.addAll(interf.getOwnedExchangeItemAllocations());
    }

    @Override
    protected void checkPreconditions() {

    }

    @Override
    protected void postRunTest() {
      List<ExchangeItemAllocation> insertedEIAllocations = new ArrayList<ExchangeItemAllocation>();
      insertedEIAllocations.addAll(interf.getOwnedExchangeItemAllocations());
      insertedEIAllocations.removeAll(ownedEIAllocations);

      List<ExchangeItemAllocation> removedEIAllocations = new ArrayList<ExchangeItemAllocation>();
      removedEIAllocations.addAll(ownedEIAllocations);
      removedEIAllocations.removeAll(interf.getOwnedExchangeItemAllocations());

      for (String id : insertedElements) {
        assertTrue(insertedEIAllocations.stream().anyMatch(x -> x.getAllocatedItem().getId().equals(id)));
      }
      for (String id : removedElements) {
        assertTrue(removedEIAllocations.stream().anyMatch(x -> x.getAllocatedItem().getId().equals(id)));
      }
    }
  }

}
