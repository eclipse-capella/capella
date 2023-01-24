/*******************************************************************************
 * Copyright (c) 2006, 2022 THALES GLOBAL SERVICES and others.
 * 
 * This program and the accompanying materials are made available under the
 * terms of the Eclipse Public License 2.0 which is available at
 * http://www.eclipse.org/legal/epl-2.0
 * 
 * SPDX-License-Identifier: EPL-2.0
 * 
 * Contributors:
 *    Thales - initial API and implementation
 *    Obeo - 2303 Add computed transitions in M&S diagrams
 *******************************************************************************/
package org.polarsys.capella.core.sirius.analysis;

/**
 * 
 */
@SuppressWarnings("nls")
public interface IMappingNameConstants {

  /**
   * FunctionalChain / OperationalProcess
   */
  String OPERATIONAL_PROCESS_MAPPING_NAME = "OperationalProcessEnd";
  String FUNCTIONAL_CHAIN_MAPPING_NAME = "FunctionalChainEnd";

  /**
   * Contextual DataFlow
   */
  @Deprecated
  String SUB_FUNCTION_MAPPING_NAME = "FunctionUnder";
  @Deprecated
  String SUB_CONTROL_NODE_MAPPING_NAME = "NodeUnder";
  @Deprecated
  String INTERNAL_DATA_FLOW_LAYER_NAME = "Internal Data Flow";

  String MS_MODE_STATE_MAPPING_NAME = "SM_ModeState_Container";
  String MS_INNER_MODE_STATE_MAPPING_NAME = "SM_InnerModeState_Container";
  String MS_PSEUDOSTATE_MAPPING_NAME = "SM_ModeState";
  String MS_INNER_PSEUDOSTATE_MAPPING_NAME = "SM_InnerModeState";
  String MS_TRANSITION_MAPPING_NAME = "SM_Transition";

  String MSM_MODE_STATE_MAPPING_NAME = "MSM_ModeState_Container";
  String MSM_PSEUDOSTATE_MAPPING_NAME = "MSM_ModeState";
  String MSM_TRANSITION_MAPPING_NAME = "MSM_Transition";
  String MSM_COMPUTED_TRANSITION_MAPPING_NAME = "MSM_ComputedTransition";
  String MSM_REGION_MAPPING_NAME = "MSM_Region";
  String MSM_ACTIVITIES_MAPPING_NAME = "MSM_Activities";
  String MSM_DOACTIVITY_MAPPING_NAME = "MSM_DoActivity";
  String MSM_ENTRY_MAPPING_NAME = "MSM_Entry";
  String MSM_EXIT_MAPPING_NAME = "MSM_Exit";
  String HIDE_REGION_NAMES = "hide.region.names.filter";
  String DISPLAY_REGION_NAME_ON_ENTRY_EXIT_POINTS = "DisplayRegionNameOnEntryExitPoints";
  String SHOW_TRIGGER_SOURCE_FUNCTION = "show.triggerfe.source.filter";

  /**
   * Entity Architecture
   */
  String OAB_ENTITY_MAPPING_NAME = "OAB_Entity1";
  String OAB_COMMUNICATION_MEAN_MAPPING_NAME = "OAB_CommunicationMean";
  String OAIB_FUNCTIONAL_EXCHANGE_MAPPING_NAME = "OAIB Interaction";
  String OAIB_FUNCTION_MAPPING_NAME = "OAIB Operational Activity";
  String OAB_OPERATIONAL_PROCESS_MAPPING_NAME = "OAB_OperationalProcess";
  String OAB_OPERATIONAL_PROCESS_END_MAPPING_NAME = "OAB_OperationalProcessEnd";
  String OAIB_OPERATIONAL_PROCESS_MAPPING_NAME = "OAIB_OperationalProcess";
  String OAIB_OPERATIONAL_PROCESS_END_MAPPING_NAME = "OAIB_OperationalProcessEnd";
  String OAB_FUNCTIONAL_EXCHANGE_MAPPING_NAME = "OAB Interaction";
  @Deprecated
  String OEB_FUNCTIONAL_EXCHANGE_MAPPING_NAME = OAB_FUNCTIONAL_EXCHANGE_MAPPING_NAME;
  String OAB_FUNCTION_MAPPING_NAME = "OAB_Activity";
  @Deprecated
  String OEB_FUNCTION_MAPPING_NAME = OAB_FUNCTION_MAPPING_NAME;

  String OCB_OPERATIONAL_CAPABILITY_MAPPING_NAME = "COC_OperationalCapabilities";
  String OCB_COMMUNICATION_MEAN_MAPPING_NAME = "COC_CommunicationMeans";
  String OCB_ENTITY_INVOLVEMENT_MAPPING_NAME = "COC_EntityOperationalCapabilityInvolvement";
  String OCB_OPERATIONAL_ENTITY_MAPPING_NAME = "COC_OperationalEntities";
  String OCB_Extends_MAPPING_NAME = "COC_OC_Extends";
  String OCB_Include_MAPPING_NAME = "COC_OC_Include";
  String OCB_OC_Generalization_MAPPING_NAME = "COC_OC_Generalization";

  @Deprecated
  String COC_OPERATIONAL_ENTITY_MAPPING_NAME = OCB_OPERATIONAL_ENTITY_MAPPING_NAME;

  String ORB_OPERATIONAL_ACTIVITY_MAPPING_NAME = "ORB_OAAllocation";
  String ORB_FUNCTIONAL_EXCHANGE_MAPPING_NAME = "ORB Interaction";

  String OAB_COMPONENT_CATEGORY_MAPPING_NAME = "OAB_ComponentCategory";
  String OAB_COMPONENT_CATEGORY_PIN_MAPPING_NAME = "OAB_ComponentCategory_Pin";

  String COAI_INTERACTION_MAPPING_NAME = "OAI Interaction";
  String COAI_OPERATIONAL_ACTIVITY_MAPPING_NAME = "OAI Operational Activity";
  String COAI_OPERATIONAL_ACTIVITY_IMPORT_MAPPING_NAME = "OAI Contextual OA";
  String COAI_OPERATIONAL_ACTIVITY_IMPORT_SUB_MAPPING_NAME = "OAI sub OA";

  /**
   * Class Diagram Blank
   */
  String CDB_DATA_PKG_MAPPING_NAME = "DT_DataPkg";
  String CDB_INTERFACE_PKG_MAPPING_NAME = "DT_InterfacePkg";
  String CDB_CLASS_MAPPING_NAME = "DT_Class";
  String CDB_ASSOCIATION_MAPPING_NAME = "DT_Association";
  String CDB_GENERALIZATION_MAPPING_NAME = "DT_Generalization";
  String CDB_ENUMERATION_MAPPING_NAME = "DT_Enumeration";
  String CDB_ENUMERATION_ENUMERATION_LITERAL_MAPPING_NAME = "DT_EnumerationLiteral";
  String CDB_ENUMERATION_ENUMERATION_CONTENTS_MAPPING_NAME = "EnumerationContents";
  String CDB_COLLECTION_MAPPING_NAME = "DT_Collection";
  String CDB_DATA_TYPE_MAPPING_NAME = "DT_DataType";
  String CDB_DATA_TYPE_CONTENTS_MAPPING_NAME = "Contents";
  String CDB_DATA_TYPE_UNITS_MAPPING_NAME = "Units";
  String CDB_EXCHANGE_ITEM_MAPPING_NAME = "DT_ExchangeItem";
  String CDB_BOOLEAN_TYPE_MAPPING_NAME = "DT_BooleanType";
  String CDB_BOOLEAN_TYPE_BOOLEAN_LITERAL_MAPPING_NAME = "DT_BooleanLiteral";
  String CDB_BOOLEAN_TYPE_BOOLEAN_CONTENTS_MAPPING_NAME = "BooleanTypeContents";
  String CDB_DATAVALUE_MAPPING_NAME = "DT_DataValue";
  String CDB_SUBDATAVALUE_MAPPING_NAME = "DT_SubDataValue";
  String CDB_PROPERTY_MAPPING_NAME = "DT_Property";
  String CDB_OPERATION_MAPPING_NAME = "DT_Operation";
  String CDB_UNIT_MAPPING_NAME = "DT_Unit";
  String CDB_EXCHANGE_ITEM_ELEMENT_MAPPING_NAME = "DT_ExchangeItemElement";
  String CDB_CONSTRAINT_MAPPING_NAME = "DT_Contraint";
  String CDB_CONSTRAINT_ELEMENT_MAPPING_NAME = "DT_ContrainedElements";
  String CDB_INTERFACE_MAPPING_NAME = IMappingNameConstants.CCDI_INTERFACE;
  String DT_TITLE_BLOCK_CONTAINER = "DT_TitleBlockContainer";
  String DT_TITLE_BLOCK_LINE_CONTAINER = "DT_TitleBlockLineContainer";
  String DT_TITLE_BLOCK_COLUMN_CONTAINER = "DT_TitleBlockColumnContainer";
  String DT_TITLE_BLOCK_CELL = "DT_TitleBlockCellNode";
  String DT_TITLE_BLOCK_EDGE = "DT_TitleBlockEdge";

  @Deprecated
  String CB_CONSTRAINT = CDB_CONSTRAINT_MAPPING_NAME;
  @Deprecated
  String CB_CONSTRAINT_ELEMENT = CDB_CONSTRAINT_ELEMENT_MAPPING_NAME;
  @Deprecated
  String CB_CLASS = CDB_CLASS_MAPPING_NAME;
  @Deprecated
  String CB_COLLECTION = CDB_COLLECTION_MAPPING_NAME;
  @Deprecated
  String CB_ASSOCIATION_CLASS = CDB_ASSOCIATION_MAPPING_NAME;
  @Deprecated
  String CB_ASSOCIATION_COLLECTION = CDB_ASSOCIATION_MAPPING_NAME;
  @Deprecated
  String CB_GENERALIZATION = CDB_GENERALIZATION_MAPPING_NAME;
  @Deprecated
  String CB_DATATYPE = CDB_DATA_TYPE_MAPPING_NAME;
  @Deprecated
  String CB_ENUMERATION = CDB_ENUMERATION_MAPPING_NAME;
  @Deprecated
  String CB_EXCHANGE_ITEM_ELEMENT = CDB_EXCHANGE_ITEM_ELEMENT_MAPPING_NAME;
  @Deprecated
  String CB_BOOLEAN_TYPE = CDB_BOOLEAN_TYPE_MAPPING_NAME;

  /**
   * Class Diagram
   */
  @Deprecated
  String CB_DATA_PKG_MAPPING_NAME = CDB_DATA_PKG_MAPPING_NAME;
  @Deprecated
  String CB_INTERFACE_PKG_MAPPING_NAME = CDB_INTERFACE_PKG_MAPPING_NAME;
  @Deprecated
  String CB_CLASS_MAPPING_NAME = CDB_CLASS_MAPPING_NAME;
  @Deprecated
  String CB_ENUMERATION_MAPPING_NAME = CDB_ENUMERATION_MAPPING_NAME;
  @Deprecated
  String CB_ENUMERATION_ENUMERATION_LITERAL_MAPPING_NAME = CDB_ENUMERATION_ENUMERATION_LITERAL_MAPPING_NAME;
  @Deprecated
  String CB_ENUMERATION_ENUMERATION_CONTENTS_MAPPING_NAME = CDB_ENUMERATION_ENUMERATION_CONTENTS_MAPPING_NAME;
  @Deprecated
  String CB_COLLECTION_MAPPING_NAME = CDB_COLLECTION_MAPPING_NAME;
  @Deprecated
  String CB_DATA_TYPE_MAPPING_NAME = CDB_DATA_TYPE_MAPPING_NAME;
  @Deprecated
  String CB_DATA_TYPE_CONTENTS_MAPPING_NAME = CDB_DATA_TYPE_CONTENTS_MAPPING_NAME;
  @Deprecated
  String CB_DATA_TYPE_UNITS_MAPPING_NAME = CDB_DATA_TYPE_UNITS_MAPPING_NAME;
  @Deprecated
  String CB_EXCHANGE_ITEM_MAPPING_NAME = CDB_EXCHANGE_ITEM_MAPPING_NAME;
  @Deprecated
  String CB_BOOLEAN_TYPE_MAPPING_NAME = CDB_BOOLEAN_TYPE_MAPPING_NAME;
  @Deprecated
  String CB_BOOLEAN_TYPE_BOOLEAN_LITERAL_MAPPING_NAME = CDB_BOOLEAN_TYPE_BOOLEAN_LITERAL_MAPPING_NAME;
  @Deprecated
  String CB_BOOLEAN_TYPE_BOOLEAN_CONTENTS_MAPPING_NAME = CDB_BOOLEAN_TYPE_BOOLEAN_CONTENTS_MAPPING_NAME;
  @Deprecated
  String CB_DATAVALUE_MAPPING_NAME = CDB_DATAVALUE_MAPPING_NAME;
  @Deprecated
  String CB_SUBDATAVALUE_MAPPING_NAME = CDB_SUBDATAVALUE_MAPPING_NAME;
  @Deprecated
  String CB_PROPERTY_MAPPING_NAME = CDB_PROPERTY_MAPPING_NAME;
  @Deprecated
  String CB_OPERATION_MAPPING_NAME = CDB_OPERATION_MAPPING_NAME;
  @Deprecated
  String CB_UNIT_MAPPING_NAME = CDB_UNIT_MAPPING_NAME;

  /**
   * System Analysis
   */
  String SAB_SYSTEM_MAPPING_NAME = "System System";
  String SAB_ACTOR_MAPPING_NAME = "System Actors";
  String SAB_SYSTEM_FUNCTION_MAPPING_NAME = "CA System Function";
  String SAB_CONTROL_NODE_MAPPING_NAME = "CA System Node";
  String SAB_FUNCTION_PORT_MAPPING_NAME = "CA Flow Port on System Function";

  String SAB_FUNCTION_PORT_ALLOCATION_MAPPING_NAME = "CA PortRealization FunctionPort to ComponentPort";
  String LAB_FUNCTION_PORT_ALLOCATION_MAPPING_NAME = "LAB PortRealization FlowPort to ComponentPort";
  String PAB_FUNCTION_PORT_ALLOCATION_MAPPING_NAME = "PAB PortRealization";

  String SAB_COMPONENT_PORT_MAPPING_NAME = "CA Component Port";
  String SAB_CONNECTION_MAPPING_NAME = "CA Data Flow Between Actors and System";
  String SDFB_PIN_MAPPING_NAME = "SDFB_Pin";
  String CSDF_PIN_MAPPING_NAME = "SDF_Pin";
  String SDFB_FUNCTIONAL_EXCHANGE_MAPPING_NAME = "SDFB_Exchange";
  String CSDF_FUNCTIONAL_EXCHANGE_MAPPING_NAME = "SDF_Exchange";
  String SDFB_EXCHANGE_CATEGORY_INPUTPORT_MAPPING_NAME = "SDFB_InputPin by Categorie";
  String CSDF_EXCHANGE_CATEGORY_INPUTPORT_MAPPING_NAME = "SDF_InputPin by Categorie";
  String SDFB_EXCHANGE_CATEGORY_OUTPUTPORT_MAPPING_NAME = "SDFB_OutputPin by Categorie";
  String CSDF_EXCHANGE_CATEGORY_OUTPUTPORT_MAPPING_NAME = "SDF_OutputPin by Categorie";
  String SDFB_EXCHANGE_CATEGORY_MAPPING_NAME = "SDFB_Exchange by Categorie";
  String CSDF_EXCHANGE_CATEGORY_MAPPING_NAME = "SDF_Exchange by Categorie";
  String SDFB_FUNCTION_MAPPING_NAME = "SDFB_Function";
  String CSDF_FUNCTION_MAPPING_NAME = "SDF_Function";
  String CSDF_FUNCTION_IMPORT_MAPPING_NAME = "SDF_FunctionUnder";
  String SDFB_CONTROL_NODE_MAPPING_NAME = "SDFB_Node";
  String CSDF_CONTROL_NODE_MAPPING_NAME = "SDF_Node";
  String CSDF_CONTROL_NODE_IMPORT_MAPPING_NAME = "SDF_NodeUnder";

  String CSDF_FUNCTIONAL_CHAIN_MAPPING_NAME = "SDF_FunctionalChain";
  String SDFB_FUNCTIONAL_CHAIN_MAPPING_NAME = "SDFB_FunctionalChain";
  String SAB_FUNCTIONAL_CHAIN_MAPPING_NAME = "CA_FunctionalChain";
  String CSDF_FUNCTIONAL_CHAIN_END_MAPPING_NAME = "SDF_FunctionalChainEnd";
  String SDFB_FUNCTIONAL_CHAIN_END_MAPPING_NAME = "SDFB_FunctionalChainEnd";
  String SAB_FUNCTIONAL_CHAIN_END_MAPPING_NAME = "CA_FunctionalChainEnd";

  String SAB_FUNCTIONAL_EXCHANGE_MAPPING_NAME = "CA DataFlow between Function";
  String SAB_EXCHANGE_CATEGORY_INPUTPORT_MAPPING_NAME = "CA_InputPin by Categorie";
  String SAB_EXCHANGE_CATEGORY_OUTPUTPORT_MAPPING_NAME = "CA_OutputPin by Categorie";
  String SAB_EXCHANGE_CATEGORY_MAPPING_NAME = "CA DataFlow by Categorie";
  String SDFB_INTERNAL_LINK_MAPPING_NAME = "SDFB_InternLink";
  String CSDF_INTERNAL_LINK_MAPPING_NAME = "SDF_InternLink";
  String SAB_INTERNAL_LINK_MAPPING_NAME = "CA_InternLink";
  String SAB_COMPONENT_CATEGORY_MAPPING_NAME = "SAB_ComponentCategory";
  String SAB_COMPONENT_CATEGORY_PIN_MAPPING_NAME = "SAB_ComponentCategory_Pin";
  String SAB_PHYSICALLINK_MAPPING_NAME = "SAB_PhysicalLink";
  String SAB_PHYSICAL_PORT_MAPPING_NAME = "SAB_PhysicalPort";

  String SAB_PHYSICAL_PATH_INTERNAL_LINK_MAPPING_NAME = "SAB_InternPhysicalPathLink";
  String SAB_PHYSICAL_PATH_END = "SAB_PhysicalPathEnd";
  String SAB_PHYSICAL_CATEGORY_MAPPING_NAME = "SAB_PhysicalCategory";
  String COC_ENTITY_MAPPING_NAME = "COC2_Entities";

  String SAB_PHYSICAL_CATEGORY_PIN_MAPPING_NAME = "SAB_PhysicalCategory_Pin";

  /**
   * Logical Architecture
   */
  String LAB_LOGICAL_COMPONENT_MAPPING_NAME = "LAB Logical Component";
  String LAB_LOGICAL_FUNCTION_MAPPING_NAME = "LAB Logical Function";
  String LAB_CONTROL_NODE_MAPPING_NAME = "LAB Sub Node";
  String LAB_FUNCTION_PORT_MAPPING_NAME = "LAB Flow Port on Logical Function";
  String LAB_FUNCTIONAL_EXCHANGE_MAPPING_NAME = "LAB DataFlow between Function";
  String LAB_COMPONENT_PORT_MAPPING_NAME = "LAB ComponentPort";
  String LAB_CONNECTION_MAPPING_NAME = "LAB DataFlow between Logical Components";
  String LAB_EXCHANGE_CATEGORY_INPUTPORT_MAPPING_NAME = "LAB InputPin by Categorie";
  String LAB_EXCHANGE_CATEGORY_OUTPUTPORT_MAPPING_NAME = "LAB OutputPin by Categorie";
  String LAB_EXCHANGE_CATEGORY_MAPPING_NAME = "LAB DataFlow by Categorie";
  String LDFB_PIN_MAPPING_NAME = "LDFB_Pin";
  String CLDF_PIN_MAPPING_NAME = "LDF_Pin";
  String LDFB_FUNCTIONAL_EXCHANGE_MAPPING_NAME = "LDFB_Exchange";
  String CLDF_FUNCTIONAL_EXCHANGE_MAPPING_NAME = "LDF_Exchange";
  String LDFB_EXCHANGE_CATEGORY_INPUTPORT_MAPPING_NAME = "LDFB_InputPin by Categorie";
  String CLDF_EXCHANGE_CATEGORY_INPUTPORT_MAPPING_NAME = "LDF_InputPin by Categorie";
  String LDFB_EXCHANGE_CATEGORY_OUTPUTPORT_MAPPING_NAME = "LDFB_OutputPin by Categorie";
  String CLDF_EXCHANGE_CATEGORY_OUTPUTPORT_MAPPING_NAME = "LDF_OutputPin by Categorie";
  String LDFB_EXCHANGE_CATEGORY_MAPPING_NAME = "LDFB_Exchange by Categorie";
  String CLDF_EXCHANGE_CATEGORY_MAPPING_NAME = "LDF_Exchange by Categorie";
  String LDFB_FUNCTION_MAPPING_NAME = "LDFB_Function";
  String CLDF_FUNCTION_MAPPING_NAME = "LDF_Function";
  String CLDF_FUNCTION_IMPORT_MAPPING_NAME = "LDF_FunctionUnder";
  String LDFB_CONTROL_NODE_MAPPING_NAME = "LDFB_Node";
  String CLDF_CONTROL_NODE_MAPPING_NAME = "LDF_Node";
  String CLDF_CONTROL_NODE_IMPORT_MAPPING_NAME = "LDF_NodeUnder";
  String CLDF_FUNCTIONAL_CHAIN_MAPPING_NAME = "LDF_FunctionalChain";
  String LDFB_FUNCTIONAL_CHAIN_MAPPING_NAME = "LDFB_FunctionalChain";
  String LAB_FUNCTIONAL_CHAIN_MAPPING_NAME = "LAB_FunctionalChain";
  String CLDF_FUNCTIONAL_CHAIN_END_MAPPING_NAME = "LDF_FunctionalChainEnd";
  String LDFB_FUNCTIONAL_CHAIN_END_MAPPING_NAME = "LDFB_FunctionalChainEnd";
  String LAB_FUNCTIONAL_CHAIN_END_MAPPING_NAME = "LAB_FunctionalChainEnd";
  String LDFB_INTERNAL_LINK_MAPPING_NAME = "LDFB_InternLink";
  String CLDF_INTERNAL_LINK_MAPPING_NAME = "LDF_InternLink";
  String LAB_INTERNAL_LINK_MAPPING_NAME = "LAB_InternLink";
  String LAB_COMPONENT_CATEGORY_MAPPING_NAME = "LAB_ComponentCategory";
  String LAB_COMPONENT_CATEGORY_PIN_MAPPING_NAME = "LAB_ComponentCategory_Pin";
  String LAB_PHYSICALLINK_MAPPING_NAME = "LAB_PhysicalLink";
  String LAB_COMPUTED_COMPONENT_EXCHANGE = "LAB_ComputedComponentExchange";
  String LAB_COMPUTED_PHYSICAL_LINK = "LAB_ComputedPhysicalLink";

  String LAB_PHYSICAL_PORT_MAPPING_NAME = "LAB_PhysicalPort";

  String LAB_PHYSICAL_PATH_INTERNAL_LINK_MAPPING_NAME = "LAB_InternPhysicalPathLink";
  String LAB_PHYSICAL_PATH_END = "LAB_PhysicalPathEnd";
  String LAB_PHYSICAL_CATEGORY_MAPPING_NAME = "LAB_PhysicalCategory";

  String LAB_PHYSICAL_CATEGORY_PIN_NAME = "LAB_PhysicalCategory_Pin";
  String LAB_DIAGRAM = "Logical Architecture Blank";

  /**
   * Physical Architecture
   */
  String PAB_PHYSICAL_COMPONENT_MAPPING_NAME = "PAB_PC";
  String PAB_PHYSICAL_COMPONENT_DEPLOYMENT_MAPPING_NAME = "PAB_Deployment";
  String PAB_CONTROL_NODE_MAPPING_NAME = "PAB_ControlFunction";
  String PAB_PHYSICAL_FUNCTION_MAPPING_NAME = "PAB_PhysicalFunction";
  String PAB_FUNCTION_PORT_MAPPING_NAME = "PAB_Pin";
  String PAB_FUNCTIONAL_EXCHANGE_MAPPING_NAME = "PAB_FunctionExchange";
  String PAB_PHYSICAL_PATH_END = "PAB_PhysicalPathEnd";
  String PAB_COMPONENT_PORT_MAPPING_NAME = "PAB_Port";
  String PAB_PHYSICAL_PORT_MAPPING_NAME = PAB_COMPONENT_PORT_MAPPING_NAME;
  String PAB_CONNECTION_MAPPING_NAME = "PAB_Exchange";
  String PAB_EXCHANGE_CATEGORY_INPUTPORT_MAPPING_NAME = "PAB InputPin by Categorie";
  String PAB_EXCHANGE_CATEGORY_OUTPUTPORT_MAPPING_NAME = "PAB OutputPin by Categorie";
  String PAB_EXCHANGE_CATEGORY_MAPPING_NAME = "PAB_FunctionExchange by Categorie";
  String PDFB_PIN_MAPPING_NAME = "PDFB_Pin";
  String CPDF_PIN_MAPPING_NAME = "PDF_Pin";
  String PDFB_FUNCTIONAL_EXCHANGE_MAPPING_NAME = "PDFB_Exchange";
  String CPDF_FUNCTIONAL_EXCHANGE_MAPPING_NAME = "PDF_Exchange";
  String PDFB_EXCHANGE_CATEGORY_INPUTPORT_MAPPING_NAME = "PDFB_InputPin by Categorie";
  String CPDF_EXCHANGE_CATEGORY_INPUTPORT_MAPPING_NAME = "PDF_InputPin by Categorie";
  String PDFB_EXCHANGE_CATEGORY_OUTPUTPORT_MAPPING_NAME = "PDFB_OutputPin by Categorie";
  String CPDF_EXCHANGE_CATEGORY_OUTPUTPORT_MAPPING_NAME = "PDF_OutputPin by Categorie";
  String PDFB_EXCHANGE_CATEGORY_MAPPING_NAME = "PDFB_Exchange by Categorie";
  String CPDF_EXCHANGE_CATEGORY_MAPPING_NAME = "PDF_Exchange by Categorie";
  String PDFB_FUNCTION_MAPPING_NAME = "PDFB_Function";
  String CPDF_FUNCTION_MAPPING_NAME = "PDF_Function";
  String CPDF_FUNCTION_IMPORT_MAPPING_NAME = "PDF_FunctionUnder";
  String PDFB_CONTROL_NODE_MAPPING_NAME = "PDFB_Node";
  String CPDF_CONTROL_NODE_MAPPING_NAME = "PDF_Node";
  String CPDF_CONTROL_NODE_IMPORT_MAPPING_NAME = "PDF_NodeUnder";
  String CPDF_FUNCTIONAL_CHAIN_MAPPING_NAME = "PDF_FunctionalChain";
  String PDFB_FUNCTIONAL_CHAIN_MAPPING_NAME = "PDFB_FunctionalChain";
  String PAB_FUNCTIONAL_CHAIN_MAPPING_NAME = "PAB_FunctionalChain";
  String CPDF_FUNCTIONAL_CHAIN_END_MAPPING_NAME = "PDF_FunctionalChainEnd";
  String PDFB_FUNCTIONAL_CHAIN_END_MAPPING_NAME = "PDFB_FunctionalChainEnd";
  String PAB_FUNCTIONAL_CHAIN_END_MAPPING_NAME = "PAB_FunctionalChainEnd";
  String PAB_PHYSICALLINK_MAPPING_NAME = "PAB_PhysicalLink";
  String PDFB_INTERNAL_LINK_MAPPING_NAME = "PDFB_InternLink";
  String CPDF_INTERNAL_LINK_MAPPING_NAME = "PDF_InternLink";
  String PAB_INTERNAL_LINK_MAPPING_NAME = "PAB_InternLink";
  String PAB_PHYSICAL_PATH_INTERNAL_LINK_MAPPING_NAME = "PAB_InternPhysicalPathLink";
  String PAB_COMPUTED_COMPONENT_EXCHANGE = "PAB_ComputedComponentExchange";
  String PAB_COMPUTED_PHYSICAL_LINK = "PAB_ComputedPhysicalLink";

  String PAB_COMPONENT_CATEGORY_MAPPING_NAME = "PAB_ComponentCategory";
  String PAB_COMPONENT_CATEGORY_PIN_MAPPING_NAME = "PAB_ComponentCategory_Pin";
  String PAB_PHYSICAL_CATEGORY_MAPPING_NAME = "PAB_PhysicalCategory";
  String PAB_PHYSICAL_CATEGORY_PIN_MAPPING_NAME = "PAB_PhysicalCategory_Pin";
  String PAB_COMPONENT_PORT_ALLOCATION_MAPPING_NAME = "PAB_ComponentPortAllocation";
  String PAB_DIAGRAM = "Physical Architecture Blank";

  /**
   * Interface Diagram
   */
  String IDB_EXCHANGE_ITEM_MAPPING_NAME = "IDB_ExchangeItem";
  String IDB_INTERFACE_MAPPING_NAME = "IDB_Interface";
  String IDB_EXCHANGE_ITEM_ELEMENT_MAPPING_NAME = "IDB_ExchangeItemElement";
  String IDB_OPERATION_MAPPING_NAME = "IDB_Operation";
  String IDB_COMPONENT_MAPPING_NAME = "IDB_Component";
  String IDB_COMPONENT_PORT_MAPPING_NAME = "IDB_Port";
  String IDB_IMPLEMENTATION_INTERFACE_MAPPING_NAME = "IDB_ImplementInterface";
  String IDB_USE_INTERFACE_MAPPING_NAME = "IDB_UseInterface";
  String IDB_PROVIDED_INTERFACE_MAPPING_NAME = "IDB_PinProvidedInterface";
  String IDB_REQUIRED_INTERFACE_MAPPING_NAME = "IDB_PinRequiredInterface";
  String IDB_PORT_MAPPING_NAME = "IDB_Port";
  String IDB_COMPONENT_EXCHANGE_MAPPING_NAME = "IDB_Connection";
  String IDB_COMMUNICATION_LINK_MAPPING_NAME = "IDB_CommunicationLink";
  String IDB_GENERALIZATION_MAPPING_NAME = "IDB_Generalization";

  String CCDI_INTERFACE = "FullInterface1";
  String CCDI_EXCHANGE_ITEM_MAPPING_NAME = "CCDI_ExchangeItem";
  String CCDI_COMMUNICATION_LINK_MAPPING_NAME = "CCDI_CommunicationLink";
  String CCDI_GENERALIZATION_MAPPING_NAME = "CCDI_Generalization";
  String CCDI_EXCHANGE_ITEM_ELEMENT_MAPPING_NAME = "CCDI_ExchangeItemElement";
  String CCDI_OPERATION_MAPPING_NAME = "DT_Operation1";
  String CCDI_COMPONENT_MAPPING_NAME = "System 1";
  String CCDI_IMPLEMENTATION_INTERFACE_MAPPING_NAME = "imp1";
  String CCDI_USE_INTERFACE_MAPPING_NAME = "use1";
  String CCDI_PROVIDED_INTERFACE_MAPPING_NAME = "CCDI_PinProvidedInterface";
  String CCDI_REQUIRED_INTERFACE_MAPPING_NAME = "CCDI_PinRequiredInterface";
  String CCDI_COMPONENT_PORT_MAPPING_NAME = "CCDI_Port";

  String CCEI_INTERFACE = "CA_Interface";
  String CCEI_COMPONENT = "CA_Component";
  String CCEI_EXCHANGE_ITEM_MAPPING_NAME = "CCEI_ExchangeItem";
  String CCEI_COMMUNICATION_LINK_MAPPING_NAME = "CCEI_CommunicationLink";
  String CCEI_GENERALIZATION_MAPPING_NAME = "CCEI_Generalization";
  String CCEI_IMPLEMENTATION_INTERFACE_MAPPING_NAME = "CA_imp2";
  String CCEI_USE_INTERFACE_MAPPING_NAME = "CA_use2";
  String CCEI_PROVIDED_INTERFACE_MAPPING_NAME = "CCEI_PinProvidedInterface";
  String CCEI_REQUIRED_INTERFACE_MAPPING_NAME = "CCEI_PinRequiredInterface";
  String CCEI_COMPONENT_PORT_MAPPING_NAME = "CCEI_Port";

  String CCII_INTERFACE = "CCII_Interface";
  String CCII_COMPONENT = "CCII_Component";
  String CCII_EXCHANGE_ITEM_MAPPING_NAME = "CCII_ExchangeItem";
  String CCII_PORT_MAPPING_NAME = "CCII_Port";
  String CCII_COMPONENT_EXCHANGE_MAPPING_NAME = "CCII_Connection";
  String CCII_COMMUNICATION_LINK_MAPPING_NAME = "CCII_CommunicationLink";
  String CCII_GENERALIZATION_MAPPING_NAME = "CCII_Generalization";
  String CCII_IMPLEMENTATION_INTERFACE_MAPPING_NAME = "CCII_ImplementInterface";
  String CCII_USE_INTERFACE_MAPPING_NAME = "CCII_UseInterface";
  String CCII_PROVIDED_INTERFACE_MAPPING_NAME = "CCII_PinProvidedInterface";
  String CCII_REQUIRED_INTERFACE_MAPPING_NAME = "CCII_PinRequiredInterface";
  String CCII_COMPONENT_PORT_MAPPING_NAME = "CCII_Port";

  String ID_EXCHANGE_ITEM_MAPPING_NAME = "ID_Operation";

  /**
   * Capability diagrams
   */
  String CC_COMPONENT_MAPPING_NAME = "ActorNode3";
  String CC_CAPABILITY_MAPPING_NAME = "Capability3";
  String CC_MISSION_MAPPING_NAME = "MissionNode3";
  String CC_ACTOR_INVOLVEMENT = "ActCapInv3";
  String CC_CAPABILITY_EXPLOITATION = "CapExploitation3";
  String CC_ABSTRACT_CAPABILITY_EXTEND = "AbsCapExtends3";
  String CC_ABSTRACT_CAPABILITY_INCLUDE = "AbsCapInclude3";
  String CC_ABSTRACT_CAPABILITY_GENERALIZATION = "Generalization3";
  String CC_ACTOR_GENERALIZATION = "actorGeneralization1";

  String CM_CAPABILITY_MAPPING_NAME = "CapNode";
  String CM_COMPONENT_MAPPING_NAME = "ActorNode";
  String CM_MISSION_MAPPING_NAME = "MissionNode";
  String CM_ACTOR_MISSION_INVOLVEMENT = "ActMissInv";
  String CM_CAPABILITY_EXPLOITATION = "CapExploitation";
  String CM_ACTOR_GENERALIZATION = "actorGeneralization4";

  String MB_COMPONENT_MAPPING_NAME = "ActorNode2";
  String MB_CAPABILITY_MAPPING_NAME = "CapNode2";
  String MB_MISSION_MAPPING_NAME = "MissionNode2";
  String MB_ACTOR_MISSION_INVOLVEMENT = "ActMissInv2";
  String MB_CAPABILITY_EXPLOITATION = "CapExploitation2";
  String MB_ACTOR_GENERALIZATION = "actorGeneralization3";

  String MCB_COMPONENT_MAPPING_NAME = "ActorNode4";
  String MCB_CAPABILITY_MAPPING_NAME = "CapabilityNode4";
  String MCB_MISSION_MAPPING_NAME = "MissionNode4";
  String MCB_ACTOR_INVOLVEMENT = "ActCapInv4";
  String MCB_CAPABILITY_EXPLOITATION = "CapExploitation4";
  String MCB_ABSTRACT_CAPABILITY_EXTEND = "AbsCapExtends4int";
  String MCB_ABSTRACT_CAPABILITY_INCLUDE = "AbsCapInclude4int";
  String MCB_ABSTRACT_CAPABILITY_GENERALIZATION = "Generalization4int";
  String MCB_ACTOR_GENERALIZATION = "actorGeneralization2";
  String MCB_ACTOR_MISSION_INVOLVEMENT = "ActMissInv3";

  @Deprecated
  String OEB_CONNECTION_MAPPING_NAME = OAB_COMMUNICATION_MEAN_MAPPING_NAME;

  // filters
  String HIDE_ALL_PARAMETER = "hide.exchange.items.details.in.interfaces.filter";
  String HIDE_OPERATION_PARAMETER = "Hide Operation parameters in Interfaces";
  String SHOW_EXCHANGE_ITEMS = "show.exchange.items.filter";
  String SHOW_FUNCTIONAL_EXCHANGES = "show.functional.exchanges.filter";
  String SHOW_EXCHANGE_ITEMS_PARAMETERS = "show.exchange.items.parameters.filter";
  String SHOW_EXCHANGE_ITEMS_ON_CE = "show.exchange.items.on.component.exchanges.filter";
  String SHOW_EXCHANGE_ITEMS_ON_CE_WITH_OUT_FE = "show.exchange.items.on.component.exchange.without.functional.exchanges.filter";
  String SHOW_FE_ON_CE = "show.allocated.functional.exchanges.on.component.exchanges.filter";
  String HIDE_CROSS_FE_IN_MULTIPART = "hide.cross.functional.exchanges.of.reusable.components.filter";
  String HIDE_ASSOCIATION_LABELS = "hide.association.labels.filter";
  String HIDE_ROLE_LABELS = "hide.role.labels.filter";
  String SHOW_MODIFIERS = "show.modifiers.filter";
  String HIDE_ROLE_NAMES = "hide.role.names.filter";
  String HIDE_FUNCTIONAL_EXCHANGES_NAMES = "hide.functional.exchanges.names.filter";
  String HIDE_COMPONENT_EXCHANGES_NAMES = "hide.component.exchanges.names.filter";
  String HIDE_PHYSICAL_LINKS_NAMES = "hide.physical.links.names.filter";
  String HIDE_INTERACTIONS_NAMES = "hide.interactions.names.filter";
  String HIDE_COMMUNICATION_MEANS_NAMES = "hide.communication.means.names.filter";
  String SHOW_FUNCTIONAL_EXCHANGES_ECHANGE_ITEMS = "show.functional.exchanges.exchange.items.filter";
  String SHOW_COMPONENT_EXCHANGES_ECHANGE_ITEMS = "show.component.exchanges.exchange.items.filter";
  String HIDE_CE_BY_DELEGATION = "hide.simplified.diagram.based.component.exchanges.filter";
  String HIDE_CE_BY_GROUP = "hide.simplified.group.of.component.exchanges.filter";
  String HIDE_CE_BY_GROUP_ORIENTED = "hide.simplified.oriented.grouped.component.exchanges.filter";
  String HIDE_OVERLAPPED_PHYSICAL_PATHS_ICON = "hide.overlappedphysical.paths.icon.filter";
  String HIDE_OVERLAPPED_PHYSICAL_PATHS_LABEL = "hide.overlappedphysical.paths.label.filter";
  String HIDE_OVERLAPPED_FUNCTIONAL_CHAINS_ICON = "hide.overlappedfunctional.chains.icon.filter";
  String HIDE_OVERLAPPED_FUNCTIONAL_CHAINS_LABEL = "hide.overlappedfunctional.chains.label.filter";
  
  String SHOW_FUNCTIONAL_EXCHANGES_PARAMS = "show.functional.exchanges.parameters.filter";
  String SHOW_FUNCTIONAL_EXCHANGES_ECHANGE_ITEMS_PARAMS = "show.functional.exchanges.exchange.items.parameters.filter";
  String CDB_SHOW_FULL_PATH = "show.full.path.filter";
  String SHOW_CONTEXTUAL_ELEMENTS = "Show Contextual Elements";

  String MERGE_ASSOCIATED_FE_AND_SL = "merge.associated.functional.exchange.involvements.and.sequence.links.without.control.node.filter";

  // EPBS Architecture
  String EAB_CI = "CI container mapping";
  String EAB_PHYSICAL_COMPONENT_IN_CI = "PhysicalComponentInCI mapping";
  String EAB_PHYSICAL_LINK_IN_CI = "PhysicalLinkInCI mapping";
  String EAB_PHYSICAL_PORT_IN_CI = "PhysicalPortInCI mapping";

  // Capability Realization Blank diagram
  String CRB_CAPABILITY_REALIZATION_MAPPING = "CRB CapabilityRealization";
  String CRB_COMPONENT_MAPPING = "CRB Component";
  String CRB_INVOLVEMENT_MAPPING = "CRB Involvement";
  String CRB_EXTENDS_MAPPING = "CRB Cap Realization Extends";
  String CRB_INCLIDE_MAPPING = "CRB Cap Realization Include";
  String CRB_CAP_GENERALIZATION_MAPPING = "CRB Cap Generalization";
  String CRB_ACTOR_GENERALIZATION_MAPPING = "CRB Actor Generalization";

  // Interface Scenario
  String IS_CONSTRAINT_MAPPING = "Scenario_Constraint";
  String IS_CONSTRAINT_ELEMENT_MAPPING = "Scenario_ContrainedElements";
  String IS_HIDE_CALL_ARGUMENTS = "hide.call.arguments.filter";
  String OAB_ROLE_MAPPING_NAME = "OAB_subRoles";
  String ORB_ROLE_MAPPING_NAME = "ORB_Role1";

  // additions 15/02/2012
  String SHOW_COMPONENT_EXCHANGES_PARAMS = "show.ce.param.filter";
  String SHOW_COMPONENT_EXCHANGES_EXCHANGE_ITEMS_PARAMS = "show.ce.ei.param.filter";

  // FunctionalChainDescription
  String FCD_DIAGRAM = "Functional Chain Description";
  String FCD_FUNCTION__MAPPING_NAME = "FC_AbstractFunction";
  String FCD_FUNCTIONAL_CHAIN__MAPPING_NAME = "FC_FunctionalChain";
  String FCD_FUNCTIONAL_EXCHANGE__MAPPING_NAME = "FC_Exchange";
  String FCD_CONTROL_NODE__MAPPING_NAME = "FC_ControlNode";
  String FCD_SEQUENCE_LINK__MAPPING_NAME = "FC_SequenceLink";
  String FCD_ASSOCIATION_LINK__MAPPING_NAME = "FC_SequenceLink_InvolvementLink";
  String FCD_CHAIN_REFERENCE_CHAIN__MAPPING_NAME = "FC_FunctionalChainStacked";
  
  // PhysicalPathDescription
  String PPD_PHYSICAL_LINK__MAPPING_NAME = "PPD_PhysicalLink";
  String PPD_PHYSICAL_PATH__MAPPING_NAME = "PPD_PhysicalPath";
  String PPD_PART__MAPPING_NAME = "PPD_Part";

  // Contextual OC Diagram
  String COC_OC_MAPPING_NAME = "COC2_OperationalCapabilities";
  String COC_COMMUNICATION_MEAN_MAPPING_NAME = "COC_CommunicationMeans";
  String COC_EntityCapabilityInvolvement_MAPPING_NAME = "COC2_EntityCapabilityInvolvement";
  String COC_Extends_MAPPING_NAME = "COC2_OC_Extends";
  String COC_Include_MAPPING_NAME = "COC2_OC_Include";
  String COC_Generalization_MAPPING_NAME = "COC2_OC_Generalization";

  // Exchange context
  String SHOW_EXCHANGE_CONTEXT = "show.exchange.context.filter";
  String SHOW_CE_EXCHANGE_CONTEXT = "show.ce.exchange.context.filter";
  String SHOW_FE_EXCHANGE_CONTEXT = "show.fe.exchange.context.filter";
  String SHOW_EI_EXCHANGE_CONTEXT = "show.ei.exchange.context.filter";

  // Contextual Capability Realization Involvement Diagram
  String CCRI_COMPONENT = "CCRI Component";
  String CCRI_CAPABILITY_REALIZATION = "CCRI CapabilityRealization";
  String CCRI_CAPABILITY_REALIZATION_INVOLVEMENT = "CCRI involvement";

}
