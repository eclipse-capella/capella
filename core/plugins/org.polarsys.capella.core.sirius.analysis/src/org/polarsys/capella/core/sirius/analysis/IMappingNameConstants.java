/*******************************************************************************
 * Copyright (c) 2006, 2016 THALES GLOBAL SERVICES.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *  
 * Contributors:
 *    Thales - initial API and implementation
 *******************************************************************************/
package org.polarsys.capella.core.sirius.analysis;

/**
 * 
 */
public interface IMappingNameConstants {

  /**
   * FunctionalChain / OperationalProcess
   */
  String OPERATIONAL_PROCESS_MAPPING_NAME = "OperationalProcessEnd"; //$NON-NLS-1$
  String FUNCTIONAL_CHAIN_MAPPING_NAME = "FunctionalChainEnd"; //$NON-NLS-1$

  /**
   * Contextual DataFlow
   */
  String SUB_FUNCTION_MAPPING_NAME = "FunctionUnder"; //$NON-NLS-1$
  String SUB_CONTROL_NODE_MAPPING_NAME = "NodeUnder"; //$NON-NLS-1$
  String INTERNAL_DATA_FLOW_LAYER_NAME = "Internal Data Flow"; //$NON-NLS-1$

  String MS_MODE_STATE_MAPPING_NAME = "SM_ModeState_Container"; //$NON-NLS-1$
  String MS_INNER_MODE_STATE_MAPPING_NAME = "SM_InnerModeState_Container"; //$NON-NLS-1$
  String MS_PSEUDOSTATE_MAPPING_NAME = "SM_ModeState"; //$NON-NLS-1$
  String MS_INNER_PSEUDOSTATE_MAPPING_NAME = "SM_InnerModeState"; //$NON-NLS-1$
  String MS_TRANSITION_MAPPING_NAME = "SM_Transition"; //$NON-NLS-1$

  /**
   * Entity Architecture
   */
  String OAB_ENTITY_MAPPING_NAME = "OAB_Entity1"; //$NON-NLS-1$
  String OAB_COMMUNICATION_MEAN_MAPPING_NAME = "OAB_CommunicationMean"; //$NON-NLS-1$
  String OAIB_FUNCTIONAL_EXCHANGE_MAPPING_NAME = "OAIB Interaction"; //$NON-NLS-1$
  String OAIB_FUNCTION_MAPPING_NAME = "OAIB Operational Activity"; //$NON-NLS-1$
  String OAB_OPERATIONAL_PROCESS_MAPPING_NAME = "OAB_OperationalProcess"; //$NON-NLS-1$
  String OAB_OPERATIONAL_PROCESS_END_MAPPING_NAME = "OAB_OperationalProcessEnd"; //$NON-NLS-1$
  String OAIB_OPERATIONAL_PROCESS_MAPPING_NAME = "OAIB_OperationalProcess"; //$NON-NLS-1$
  String OAIB_OPERATIONAL_PROCESS_END_MAPPING_NAME = "OAIB_OperationalProcessEnd"; //$NON-NLS-1$
  String OAB_FUNCTIONAL_EXCHANGE_MAPPING_NAME = "OAB Interaction"; //$NON-NLS-1$
  @Deprecated
  String OEB_FUNCTIONAL_EXCHANGE_MAPPING_NAME = OAB_FUNCTIONAL_EXCHANGE_MAPPING_NAME;
  String OAB_FUNCTION_MAPPING_NAME = "OAB_Activity"; //$NON-NLS-1$
  @Deprecated
  String OEB_FUNCTION_MAPPING_NAME = OAB_FUNCTION_MAPPING_NAME;

  String OCB_OPERATIONAL_CAPABILITY_MAPPING_NAME = "COC_OperationalCapabilities"; //$NON-NLS-1$
  String OCB_COMMUNICATION_MEAN_MAPPING_NAME = "COC_CommunicationMeans"; //$NON-NLS-1$
  String OCB_ENTITY_INVOLVEMENT_MAPPING_NAME = "COC_EntityOperationalCapabilityInvolvement"; //$NON-NLS-1$
  String OCB_OPERATIONAL_ENTITY_MAPPING_NAME = "COC_OperationalEntities"; //$NON-NLS-1$
  String OCB_Extends_MAPPING_NAME = "COC_OC_Extends"; //$NON-NLS-1$
  String OCB_Include_MAPPING_NAME = "COC_OC_Include"; //$NON-NLS-1$
  String OCB_OC_Generalization_MAPPING_NAME = "COC_OC_Generalization"; //$NON-NLS-1$

  @Deprecated
  String COC_OPERATIONAL_ENTITY_MAPPING_NAME = OCB_OPERATIONAL_ENTITY_MAPPING_NAME;

  String ORB_OPERATIONAL_ACTIVITY_MAPPING_NAME = "ORB_OAAllocation"; //$NON-NLS-1$
  String ORB_FUNCTIONAL_EXCHANGE_MAPPING_NAME = "ORB Interaction"; //$NON-NLS-1$

  String OAB_COMPONENT_CATEGORY_MAPPING_NAME = "OAB_ComponentCategory";//$NON-NLS-1$
  String OAB_COMPONENT_CATEGORY_PIN_MAPPING_NAME = "OAB_ComponentCategory_Pin";//$NON-NLS-1$

  String COAI_INTERACTION_MAPPING_NAME = "OAI Interaction"; //$NON-NLS-1$
  String COAI_OPERATIONAL_ACTIVITY_MAPPING_NAME = "OAI Operational Activity"; //$NON-NLS-1$
  String COAI_OPERATIONAL_ACTIVITY_IMPORT_MAPPING_NAME = "OAI Contextual OA"; //$NON-NLS-1$
  String COAI_OPERATIONAL_ACTIVITY_IMPORT_SUB_MAPPING_NAME = "OAI sub OA"; //$NON-NLS-1$

  /**
   * Class Diagram Blank
   */
  String CDB_DATA_PKG_MAPPING_NAME = "DT_DataPkg"; //$NON-NLS-1$
  String CDB_INTERFACE_PKG_MAPPING_NAME = "DT_InterfacePkg"; //$NON-NLS-1$
  String CDB_CLASS_MAPPING_NAME = "DT_Class"; //$NON-NLS-1$
  String CDB_ASSOCIATION_MAPPING_NAME = "DT_Association"; //$NON-NLS-1$
  String CDB_GENERALIZATION_MAPPING_NAME = "DT_Generalization"; //$NON-NLS-1$
  String CDB_ENUMERATION_MAPPING_NAME = "DT_Enumeration"; //$NON-NLS-1$
  String CDB_ENUMERATION_ENUMERATION_LITERAL_MAPPING_NAME = "DT_EnumerationLiteral"; //$NON-NLS-1$
  String CDB_ENUMERATION_ENUMERATION_CONTENTS_MAPPING_NAME = "EnumerationContents"; //$NON-NLS-1$
  String CDB_COLLECTION_MAPPING_NAME = "DT_Collection"; //$NON-NLS-1$
  String CDB_DATA_TYPE_MAPPING_NAME = "DT_DataType"; //$NON-NLS-1$
  String CDB_DATA_TYPE_CONTENTS_MAPPING_NAME = "Contents"; //$NON-NLS-1$
  String CDB_DATA_TYPE_UNITS_MAPPING_NAME = "Units"; //$NON-NLS-1$
  String CDB_EXCHANGE_ITEM_MAPPING_NAME = "DT_ExchangeItem"; //$NON-NLS-1$
  String CDB_BOOLEAN_TYPE_MAPPING_NAME = "DT_BooleanType"; //$NON-NLS-1$
  String CDB_BOOLEAN_TYPE_BOOLEAN_LITERAL_MAPPING_NAME = "DT_BooleanLiteral"; //$NON-NLS-1$
  String CDB_BOOLEAN_TYPE_BOOLEAN_CONTENTS_MAPPING_NAME = "BooleanTypeContents"; //$NON-NLS-1$
  String CDB_DATAVALUE_MAPPING_NAME = "DT_DataValue"; //$NON-NLS-1$
  String CDB_SUBDATAVALUE_MAPPING_NAME = "DT_SubDataValue"; //$NON-NLS-1$
  String CDB_PROPERTY_MAPPING_NAME = "DT_Property"; //$NON-NLS-1$
  String CDB_OPERATION_MAPPING_NAME = "DT_Operation"; //$NON-NLS-1$
  String CDB_UNIT_MAPPING_NAME = "DT_Unit"; //$NON-NLS-1$
  String CDB_EXCHANGE_ITEM_ELEMENT_MAPPING_NAME = "DT_ExchangeItemElement";//$NON-NLS-1$
  String CDB_CONSTRAINT_MAPPING_NAME = "DT_Contraint";//$NON-NLS-1$
  String CDB_CONSTRAINT_ELEMENT_MAPPING_NAME = "DT_ContrainedElements";//$NON-NLS-1$
  String CDB_INTERFACE_MAPPING_NAME = IMappingNameConstants.CCDI_INTERFACE;

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
  String SAB_SYSTEM_MAPPING_NAME = "System System"; //$NON-NLS-1$
  String SAB_ACTOR_MAPPING_NAME = "System Actors"; //$NON-NLS-1$
  String SAB_SYSTEM_FUNCTION_MAPPING_NAME = "CA System Function"; //$NON-NLS-1$
  String SAB_CONTROL_NODE_MAPPING_NAME = "CA System Node"; //$NON-NLS-1$
  String SAB_FUNCTION_PORT_MAPPING_NAME = "CA Flow Port on System Function"; //$NON-NLS-1$

  String SAB_FUNCTION_PORT_ALLOCATION_MAPPING_NAME = "CA PortRealization FunctionPort to ComponentPort"; //$NON-NLS-1$
  String LAB_FUNCTION_PORT_ALLOCATION_MAPPING_NAME = "LAB PortRealization FlowPort to ComponentPort"; //$NON-NLS-1$
  String PAB_FUNCTION_PORT_ALLOCATION_MAPPING_NAME = "PAB PortRealization"; //$NON-NLS-1$

  String SAB_COMPONENT_PORT_MAPPING_NAME = "CA Component Port"; //$NON-NLS-1$
  String SAB_CONNECTION_MAPPING_NAME = "CA Data Flow Between Actors and System"; //$NON-NLS-1$
  String SDFB_PIN_MAPPING_NAME = "SDFB_Pin"; //$NON-NLS-1$
  String CSDF_PIN_MAPPING_NAME = "SDF_Pin"; //$NON-NLS-1$
  String SDFB_FUNCTIONAL_EXCHANGE_MAPPING_NAME = "SDFB_Exchange"; //$NON-NLS-1$
  String CSDF_FUNCTIONAL_EXCHANGE_MAPPING_NAME = "SDF_Exchange"; //$NON-NLS-1$
  String SDFB_EXCHANGE_CATEGORY_INPUTPORT_MAPPING_NAME = "SDFB_InputPin by Categorie"; //$NON-NLS-1$
  String CSDF_EXCHANGE_CATEGORY_INPUTPORT_MAPPING_NAME = "SDF_InputPin by Categorie"; //$NON-NLS-1$
  String SDFB_EXCHANGE_CATEGORY_OUTPUTPORT_MAPPING_NAME = "SDFB_OutputPin by Categorie"; //$NON-NLS-1$
  String CSDF_EXCHANGE_CATEGORY_OUTPUTPORT_MAPPING_NAME = "SDF_OutputPin by Categorie"; //$NON-NLS-1$
  String SDFB_EXCHANGE_CATEGORY_MAPPING_NAME = "SDFB_Exchange by Categorie"; //$NON-NLS-1$
  String CSDF_EXCHANGE_CATEGORY_MAPPING_NAME = "SDF_Exchange by Categorie"; //$NON-NLS-1$
  String SDFB_FUNCTION_MAPPING_NAME = "SDFB_Function"; //$NON-NLS-1$
  String CSDF_FUNCTION_MAPPING_NAME = "SDF_Function"; //$NON-NLS-1$
  String CSDF_FUNCTION_IMPORT_MAPPING_NAME = "SDF_FunctionUnder"; //$NON-NLS-1$
  String SDFB_CONTROL_NODE_MAPPING_NAME = "SDFB_Node"; //$NON-NLS-1$
  String CSDF_CONTROL_NODE_MAPPING_NAME = "SDF_Node"; //$NON-NLS-1$
  String CSDF_CONTROL_NODE_IMPORT_MAPPING_NAME = "SDF_NodeUnder"; //$NON-NLS-1$

  String CSDF_FUNCTIONAL_CHAIN_MAPPING_NAME = "SDF_FunctionalChain"; //$NON-NLS-1$
  String SDFB_FUNCTIONAL_CHAIN_MAPPING_NAME = "SDFB_FunctionalChain"; //$NON-NLS-1$
  String SAB_FUNCTIONAL_CHAIN_MAPPING_NAME = "CA_FunctionalChain"; //$NON-NLS-1$
  String CSDF_FUNCTIONAL_CHAIN_END_MAPPING_NAME = "SDF_FunctionalChainEnd"; //$NON-NLS-1$
  String SDFB_FUNCTIONAL_CHAIN_END_MAPPING_NAME = "SDFB_FunctionalChainEnd"; //$NON-NLS-1$
  String SAB_FUNCTIONAL_CHAIN_END_MAPPING_NAME = "CA_FunctionalChainEnd"; //$NON-NLS-1$

  String SAB_FUNCTIONAL_EXCHANGE_MAPPING_NAME = "CA DataFlow between Function"; //$NON-NLS-1$
  String SAB_EXCHANGE_CATEGORY_INPUTPORT_MAPPING_NAME = "CA_InputPin by Categorie"; //$NON-NLS-1$
  String SAB_EXCHANGE_CATEGORY_OUTPUTPORT_MAPPING_NAME = "CA_OutputPin by Categorie"; //$NON-NLS-1$
  String SAB_EXCHANGE_CATEGORY_MAPPING_NAME = "CA DataFlow by Categorie"; //$NON-NLS-1$
  String SDFB_INTERNAL_LINK_MAPPING_NAME = "SDFB_InternLink"; //$NON-NLS-1$
  String CSDF_INTERNAL_LINK_MAPPING_NAME = "SDF_InternLink"; //$NON-NLS-1$
  String SAB_INTERNAL_LINK_MAPPING_NAME = "CA_InternLink"; //$NON-NLS-1$
  String SAB_COMPONENT_CATEGORY_MAPPING_NAME = "SAB_ComponentCategory";//$NON-NLS-1$
  String SAB_COMPONENT_CATEGORY_PIN_MAPPING_NAME = "SAB_ComponentCategory_Pin";//$NON-NLS-1$
  String SAB_PHYSICALLINK_MAPPING_NAME = "SAB_PhysicalLink"; //$NON-NLS-1$
  String SAB_PHYSICAL_PORT_MAPPING_NAME = "SAB_PhysicalPort"; //$NON-NLS-1$

  String SAB_PHYSICAL_PATH_INTERNAL_LINK_MAPPING_NAME = "SAB_InternPhysicalPathLink"; //$NON-NLS-1$
  String SAB_PHYSICAL_PATH_END = "SAB_PhysicalPathEnd"; //$NON-NLS-1$
  String SAB_PHYSICAL_CATEGORY_MAPPING_NAME = "SAB_PhysicalCategory"; //$NON-NLS-1$
  String COC_ENTITY_MAPPING_NAME = "COC2_Entities"; //$NON-NLS-1$  

  String SAB_PHYSICAL_CATEGORY_PIN_MAPPING_NAME = "SAB_PhysicalCategory_Pin"; //$NON-NLS-1$

  /**
   * Logical Architecture
   */
  String LAB_LOGICAL_COMPONENT_MAPPING_NAME = "LAB Logical Component"; //$NON-NLS-1$
  String LAB_LOGICAL_ACTOR_MAPPING_NAME = "Logical Actors"; //$NON-NLS-1$
  String LAB_LOGICAL_FUNCTION_MAPPING_NAME = "LAB Logical Function"; //$NON-NLS-1$
  String LAB_CONTROL_NODE_MAPPING_NAME = "LAB Sub Node"; //$NON-NLS-1$
  String LAB_FUNCTION_PORT_MAPPING_NAME = "LAB Flow Port on Logical Function"; //$NON-NLS-1$
  String LAB_FUNCTIONAL_EXCHANGE_MAPPING_NAME = "LAB DataFlow between Function"; //$NON-NLS-1$
  String LAB_COMPONENT_PORT_MAPPING_NAME = "LAB ComponentPort"; //$NON-NLS-1$
  String LAB_CONNECTION_MAPPING_NAME = "LAB DataFlow between Logical Components"; //$NON-NLS-1$
  String LAB_EXCHANGE_CATEGORY_INPUTPORT_MAPPING_NAME = "LAB InputPin by Categorie"; //$NON-NLS-1$
  String LAB_EXCHANGE_CATEGORY_OUTPUTPORT_MAPPING_NAME = "LAB OutputPin by Categorie"; //$NON-NLS-1$
  String LAB_EXCHANGE_CATEGORY_MAPPING_NAME = "LAB DataFlow by Categorie"; //$NON-NLS-1$
  String LDFB_PIN_MAPPING_NAME = "LDFB_Pin"; //$NON-NLS-1$
  String CLDF_PIN_MAPPING_NAME = "LDF_Pin"; //$NON-NLS-1$
  String LDFB_FUNCTIONAL_EXCHANGE_MAPPING_NAME = "LDFB_Exchange"; //$NON-NLS-1$
  String CLDF_FUNCTIONAL_EXCHANGE_MAPPING_NAME = "LDF_Exchange"; //$NON-NLS-1$
  String LDFB_EXCHANGE_CATEGORY_INPUTPORT_MAPPING_NAME = "LDFB_InputPin by Categorie"; //$NON-NLS-1$
  String CLDF_EXCHANGE_CATEGORY_INPUTPORT_MAPPING_NAME = "LDF_InputPin by Categorie"; //$NON-NLS-1$
  String LDFB_EXCHANGE_CATEGORY_OUTPUTPORT_MAPPING_NAME = "LDFB_OutputPin by Categorie"; //$NON-NLS-1$
  String CLDF_EXCHANGE_CATEGORY_OUTPUTPORT_MAPPING_NAME = "LDF_OutputPin by Categorie"; //$NON-NLS-1$
  String LDFB_EXCHANGE_CATEGORY_MAPPING_NAME = "LDFB_Exchange by Categorie"; //$NON-NLS-1$
  String CLDF_EXCHANGE_CATEGORY_MAPPING_NAME = "LDF_Exchange by Categorie"; //$NON-NLS-1$
  String LDFB_FUNCTION_MAPPING_NAME = "LDFB_Function"; //$NON-NLS-1$
  String CLDF_FUNCTION_MAPPING_NAME = "LDF_Function"; //$NON-NLS-1$
  String CLDF_FUNCTION_IMPORT_MAPPING_NAME = "LDF_FunctionUnder"; //$NON-NLS-1$
  String LDFB_CONTROL_NODE_MAPPING_NAME = "LDFB_Node"; //$NON-NLS-1$
  String CLDF_CONTROL_NODE_MAPPING_NAME = "LDF_Node"; //$NON-NLS-1$
  String CLDF_CONTROL_NODE_IMPORT_MAPPING_NAME = "LDF_NodeUnder"; //$NON-NLS-1$
  String CLDF_FUNCTIONAL_CHAIN_MAPPING_NAME = "LDF_FunctionalChain"; //$NON-NLS-1$
  String LDFB_FUNCTIONAL_CHAIN_MAPPING_NAME = "LDFB_FunctionalChain"; //$NON-NLS-1$
  String LAB_FUNCTIONAL_CHAIN_MAPPING_NAME = "LAB_FunctionalChain"; //$NON-NLS-1$
  String CLDF_FUNCTIONAL_CHAIN_END_MAPPING_NAME = "LDF_FunctionalChainEnd"; //$NON-NLS-1$
  String LDFB_FUNCTIONAL_CHAIN_END_MAPPING_NAME = "LDFB_FunctionalChainEnd"; //$NON-NLS-1$
  String LAB_FUNCTIONAL_CHAIN_END_MAPPING_NAME = "LAB_FunctionalChainEnd"; //$NON-NLS-1$
  String LDFB_INTERNAL_LINK_MAPPING_NAME = "LDFB_InternLink"; //$NON-NLS-1$
  String CLDF_INTERNAL_LINK_MAPPING_NAME = "LDF_InternLink"; //$NON-NLS-1$
  String LAB_INTERNAL_LINK_MAPPING_NAME = "LAB_InternLink"; //$NON-NLS-1$
  String LAB_COMPONENT_CATEGORY_MAPPING_NAME = "LAB_ComponentCategory";//$NON-NLS-1$
  String LAB_COMPONENT_CATEGORY_PIN_MAPPING_NAME = "LAB_ComponentCategory_Pin";//$NON-NLS-1$
  String LAB_PHYSICALLINK_MAPPING_NAME = "LAB_PhysicalLink"; //$NON-NLS-1$

  String LAB_PHYSICAL_PORT_MAPPING_NAME = "LAB_PhysicalPort"; //$NON-NLS-1$

  String LAB_PHYSICAL_PATH_INTERNAL_LINK_MAPPING_NAME = "LAB_InternPhysicalPathLink"; //$NON-NLS-1$
  String LAB_PHYSICAL_PATH_END = "LAB_PhysicalPathEnd"; //$NON-NLS-1$
  String LAB_PHYSICAL_CATEGORY_MAPPING_NAME = "LAB_PhysicalCategory"; //$NON-NLS-1$

  String LAB_PHYSICAL_CATEGORY_PIN_NAME = "LAB_PhysicalCategory_Pin"; //$NON-NLS-1$

  /**
   * Physical Architecture
   */
  String PAB_PHYSICAL_COMPONENT_MAPPING_NAME = "PAB_PC"; //$NON-NLS-1$
  String PAB_PHYSICAL_COMPONENT_DEPLOYMENT_MAPPING_NAME = "PAB_Deployment"; //$NON-NLS-1$
  String PAB_PHYSICAL_ACTOR_MAPPING_NAME = "PAB_Actor"; //$NON-NLS-1$
  String PAB_CONTROL_NODE_MAPPING_NAME = "PAB_ControlFunction"; //$NON-NLS-1$
  String PAB_PHYSICAL_FUNCTION_MAPPING_NAME = "PAB_PhysicalFunction"; //$NON-NLS-1$
  String PAB_FUNCTION_PORT_MAPPING_NAME = "PAB_Pin"; //$NON-NLS-1$
  String PAB_FUNCTIONAL_EXCHANGE_MAPPING_NAME = "PAB_FunctionExchange"; //$NON-NLS-1$
  String PAB_PHYSICAL_PATH_END = "PAB_PhysicalPathEnd"; //$NON-NLS-1$
  String PAB_COMPONENT_PORT_MAPPING_NAME = "PAB_Port"; //$NON-NLS-1$
  String PAB_PHYSICAL_PORT_MAPPING_NAME = PAB_COMPONENT_PORT_MAPPING_NAME;
  String PAB_CONNECTION_MAPPING_NAME = "PAB_Exchange"; //$NON-NLS-1$ 
  String PAB_EXCHANGE_CATEGORY_INPUTPORT_MAPPING_NAME = "PAB InputPin by Categorie"; //$NON-NLS-1$
  String PAB_EXCHANGE_CATEGORY_OUTPUTPORT_MAPPING_NAME = "PAB OutputPin by Categorie"; //$NON-NLS-1$
  String PAB_EXCHANGE_CATEGORY_MAPPING_NAME = "PAB_FunctionExchange by Categorie"; //$NON-NLS-1$
  String PDFB_PIN_MAPPING_NAME = "PDFB_Pin"; //$NON-NLS-1$
  String CPDF_PIN_MAPPING_NAME = "PDF_Pin"; //$NON-NLS-1$
  String PDFB_FUNCTIONAL_EXCHANGE_MAPPING_NAME = "PDFB_Exchange"; //$NON-NLS-1$
  String CPDF_FUNCTIONAL_EXCHANGE_MAPPING_NAME = "PDF_Exchange"; //$NON-NLS-1$
  String PDFB_EXCHANGE_CATEGORY_INPUTPORT_MAPPING_NAME = "PDFB_InputPin by Categorie"; //$NON-NLS-1$
  String CPDF_EXCHANGE_CATEGORY_INPUTPORT_MAPPING_NAME = "PDF_InputPin by Categorie"; //$NON-NLS-1$
  String PDFB_EXCHANGE_CATEGORY_OUTPUTPORT_MAPPING_NAME = "PDFB_OutputPin by Categorie"; //$NON-NLS-1$
  String CPDF_EXCHANGE_CATEGORY_OUTPUTPORT_MAPPING_NAME = "PDF_OutputPin by Categorie"; //$NON-NLS-1$
  String PDFB_EXCHANGE_CATEGORY_MAPPING_NAME = "PDFB_Exchange by Categorie"; //$NON-NLS-1$
  String CPDF_EXCHANGE_CATEGORY_MAPPING_NAME = "PDF_Exchange by Categorie"; //$NON-NLS-1$
  String PDFB_FUNCTION_MAPPING_NAME = "PDFB_Function"; //$NON-NLS-1$
  String CPDF_FUNCTION_MAPPING_NAME = "PDF_Function"; //$NON-NLS-1$
  String CPDF_FUNCTION_IMPORT_MAPPING_NAME = "PDF_FunctionUnder"; //$NON-NLS-1$
  String PDFB_CONTROL_NODE_MAPPING_NAME = "PDFB_Node"; //$NON-NLS-1$
  String CPDF_CONTROL_NODE_MAPPING_NAME = "PDF_Node"; //$NON-NLS-1$
  String CPDF_CONTROL_NODE_IMPORT_MAPPING_NAME = "PDF_NodeUnder"; //$NON-NLS-1$
  String CPDF_FUNCTIONAL_CHAIN_MAPPING_NAME = "PDF_FunctionalChain"; //$NON-NLS-1$
  String PDFB_FUNCTIONAL_CHAIN_MAPPING_NAME = "PDFB_FunctionalChain"; //$NON-NLS-1$
  String PAB_FUNCTIONAL_CHAIN_MAPPING_NAME = "PAB_FunctionalChain"; //$NON-NLS-1$
  String CPDF_FUNCTIONAL_CHAIN_END_MAPPING_NAME = "PDF_FunctionalChainEnd"; //$NON-NLS-1$
  String PDFB_FUNCTIONAL_CHAIN_END_MAPPING_NAME = "PDFB_FunctionalChainEnd"; //$NON-NLS-1$
  String PAB_FUNCTIONAL_CHAIN_END_MAPPING_NAME = "PAB_FunctionalChainEnd"; //$NON-NLS-1$
  String PAB_PHYSICALLINK_MAPPING_NAME = "PAB_PhysicalLink"; //$NON-NLS-1$
  String PDFB_INTERNAL_LINK_MAPPING_NAME = "PDFB_InternLink"; //$NON-NLS-1$
  String CPDF_INTERNAL_LINK_MAPPING_NAME = "PDF_InternLink"; //$NON-NLS-1$
  String PAB_INTERNAL_LINK_MAPPING_NAME = "PAB_InternLink"; //$NON-NLS-1$
  String PAB_PHYSICAL_PATH_INTERNAL_LINK_MAPPING_NAME = "PAB_InternPhysicalPathLink"; //$NON-NLS-1$

  String PAB_COMPONENT_CATEGORY_MAPPING_NAME = "PAB_ComponentCategory";//$NON-NLS-1$
  String PAB_COMPONENT_CATEGORY_PIN_MAPPING_NAME = "PAB_ComponentCategory_Pin";//$NON-NLS-1$
  String PAB_PHYSICAL_CATEGORY_MAPPING_NAME = "PAB_PhysicalCategory";//$NON-NLS-1$
  String PAB_PHYSICAL_CATEGORY_PIN_MAPPING_NAME = "PAB_PhysicalCategory_Pin";//$NON-NLS-1$
  String PAB_COMPONENT_PORT_ALLOCATION_MAPPING_NAME = "PAB_ComponentPortAllocation";//$NON-NLS-1$

  /**
   * Interface Diagram
   */
  String IDB_EXCHANGE_ITEM_MAPPING_NAME = "IDB_ExchangeItem"; //$NON-NLS-1$
  String IDB_INTERFACE_MAPPING_NAME = "IDB_Interface"; //$NON-NLS-1$
  String IDB_EXCHANGE_ITEM_ELEMENT_MAPPING_NAME = "IDB_ExchangeItemElement"; //$NON-NLS-1$
  String IDB_OPERATION_MAPPING_NAME = "IDB_Operation"; //$NON-NLS-1$
  String IDB_COMPONENT_MAPPING_NAME = "IDB_Component"; //$NON-NLS-1$
  String IDB_COMPONENT_PORT_MAPPING_NAME = "IDB_Port"; //$NON-NLS-1$
  String IDB_IMPLEMENTATION_INTERFACE_MAPPING_NAME = "IDB_ImplementInterface"; //$NON-NLS-1$
  String IDB_USE_INTERFACE_MAPPING_NAME = "IDB_UseInterface"; //$NON-NLS-1$
  String IDB_PROVIDED_INTERFACE_MAPPING_NAME = "IDB_PinProvidedInterface"; //$NON-NLS-1$
  String IDB_REQUIRED_INTERFACE_MAPPING_NAME = "IDB_PinRequiredInterface"; //$NON-NLS-1$
  String IDB_PORT_MAPPING_NAME = "IDB_Port"; //$NON-NLS-1$
  String IDB_COMPONENT_EXCHANGE_MAPPING_NAME = "IDB_Connection"; //$NON-NLS-1$
  String IDB_COMMUNICATION_LINK_MAPPING_NAME = "IDB_CommunicationLink"; //$NON-NLS-1$
  String IDB_GENERALIZATION_MAPPING_NAME = "IDB_Generalization"; //$NON-NLS-1$

  String CCDI_INTERFACE = "FullInterface1"; //$NON-NLS-1$
  String CCDI_EXCHANGE_ITEM_MAPPING_NAME = "CCDI_ExchangeItem"; //$NON-NLS-1$
  String CCDI_COMMUNICATION_LINK_MAPPING_NAME = "CCDI_CommunicationLink"; //$NON-NLS-1$
  String CCDI_GENERALIZATION_MAPPING_NAME = "CCDI_Generalization"; //$NON-NLS-1$
  String CCDI_EXCHANGE_ITEM_ELEMENT_MAPPING_NAME = "CCDI_ExchangeItemElement"; //$NON-NLS-1$
  String CCDI_OPERATION_MAPPING_NAME = "DT_Operation1"; //$NON-NLS-1$
  String CCDI_COMPONENT_MAPPING_NAME = "System 1"; //$NON-NLS-1$
  String CCDI_IMPLEMENTATION_INTERFACE_MAPPING_NAME = "imp1"; //$NON-NLS-1$
  String CCDI_USE_INTERFACE_MAPPING_NAME = "use1"; //$NON-NLS-1$
  String CCDI_PROVIDED_INTERFACE_MAPPING_NAME = "CCDI_PinProvidedInterface"; //$NON-NLS-1$
  String CCDI_REQUIRED_INTERFACE_MAPPING_NAME = "CCDI_PinRequiredInterface"; //$NON-NLS-1$
  String CCDI_COMPONENT_PORT_MAPPING_NAME = "CCDI_Port"; //$NON-NLS-1$

  String CCEI_INTERFACE = "CA_Interface"; //$NON-NLS-1$
  String CCEI_COMPONENT = "CA_Component"; //$NON-NLS-1$
  String CCEI_EXCHANGE_ITEM_MAPPING_NAME = "CCEI_ExchangeItem"; //$NON-NLS-1$
  String CCEI_COMMUNICATION_LINK_MAPPING_NAME = "CCEI_CommunicationLink"; //$NON-NLS-1$
  String CCEI_GENERALIZATION_MAPPING_NAME = "CCEI_Generalization"; //$NON-NLS-1$
  String CCEI_IMPLEMENTATION_INTERFACE_MAPPING_NAME = "CA_imp2"; //$NON-NLS-1$
  String CCEI_USE_INTERFACE_MAPPING_NAME = "CA_use2"; //$NON-NLS-1$
  String CCEI_PROVIDED_INTERFACE_MAPPING_NAME = "CCEI_PinProvidedInterface"; //$NON-NLS-1$
  String CCEI_REQUIRED_INTERFACE_MAPPING_NAME = "CCEI_PinRequiredInterface"; //$NON-NLS-1$
  String CCEI_COMPONENT_PORT_MAPPING_NAME = "CCEI_Port"; //$NON-NLS-1$

  String CCII_INTERFACE = "CCII_Interface"; //$NON-NLS-1$
  String CCII_COMPONENT = "CCII_Component"; //$NON-NLS-1$
  String CCII_EXCHANGE_ITEM_MAPPING_NAME = "CCII_ExchangeItem"; //$NON-NLS-1$
  String CCII_PORT_MAPPING_NAME = "CCII_Port"; //$NON-NLS-1$
  String CCII_COMPONENT_EXCHANGE_MAPPING_NAME = "CCII_Connection"; //$NON-NLS-1$
  String CCII_COMMUNICATION_LINK_MAPPING_NAME = "CCII_CommunicationLink"; //$NON-NLS-1$
  String CCII_GENERALIZATION_MAPPING_NAME = "CCII_Generalization"; //$NON-NLS-1$
  String CCII_IMPLEMENTATION_INTERFACE_MAPPING_NAME = "CCII_ImplementInterface"; //$NON-NLS-1$
  String CCII_USE_INTERFACE_MAPPING_NAME = "CCII_UseInterface"; //$NON-NLS-1$
  String CCII_PROVIDED_INTERFACE_MAPPING_NAME = "CCII_PinProvidedInterface"; //$NON-NLS-1$
  String CCII_REQUIRED_INTERFACE_MAPPING_NAME = "CCII_PinRequiredInterface"; //$NON-NLS-1$
  String CCII_COMPONENT_PORT_MAPPING_NAME = "CCII_Port"; //$NON-NLS-1$

  String ID_EXCHANGE_ITEM_MAPPING_NAME = "ID_Operation"; //$NON-NLS-1$

  /**
   * Capability diagrams
   */
  String CC_COMPONENT_MAPPING_NAME = "ActorNode3"; //$NON-NLS-1$
  String CC_CAPABILITY_MAPPING_NAME = "Capability3"; //$NON-NLS-1$
  String CC_MISSION_MAPPING_NAME = "MissionNode3"; //$NON-NLS-1$
  String CC_ACTOR_INVOLVEMENT = "ActCapInv3"; //$NON-NLS-1$
  String CC_CAPABILITY_EXPLOITATION = "CapExploitation3"; //$NON-NLS-1$
  String CC_ABSTRACT_CAPABILITY_EXTEND = "AbsCapExtends3"; //$NON-NLS-1$
  String CC_ABSTRACT_CAPABILITY_INCLUDE = "AbsCapInclude3"; //$NON-NLS-1$
  String CC_ABSTRACT_CAPABILITY_GENERALIZATION = "Generalization3"; //$NON-NLS-1$
  String CC_ACTOR_GENERALIZATION = "actorGeneralization1"; //$NON-NLS-1$

  String CM_CAPABILITY_MAPPING_NAME = "CapNode"; //$NON-NLS-1$
  String CM_COMPONENT_MAPPING_NAME = "ActorNode"; //$NON-NLS-1$
  String CM_MISSION_MAPPING_NAME = "MissionNode"; //$NON-NLS-1$
  String CM_ACTOR_MISSION_INVOLVEMENT = "ActMissInv"; //$NON-NLS-1$
  String CM_CAPABILITY_EXPLOITATION = "CapExploitation"; //$NON-NLS-1$
  String CM_ACTOR_GENERALIZATION = "actorGeneralization4"; //$NON-NLS-1$

  String MB_COMPONENT_MAPPING_NAME = "ActorNode2"; //$NON-NLS-1$
  String MB_CAPABILITY_MAPPING_NAME = "CapNode2"; //$NON-NLS-1$
  String MB_MISSION_MAPPING_NAME = "MissionNode2"; //$NON-NLS-1$
  String MB_ACTOR_MISSION_INVOLVEMENT = "ActMissInv2"; //$NON-NLS-1$
  String MB_CAPABILITY_EXPLOITATION = "CapExploitation2"; //$NON-NLS-1$
  String MB_ACTOR_GENERALIZATION = "actorGeneralization3"; //$NON-NLS-1$

  String MCB_COMPONENT_MAPPING_NAME = "ActorNode4"; //$NON-NLS-1$
  String MCB_CAPABILITY_MAPPING_NAME = "CapabilityNode4"; //$NON-NLS-1$
  String MCB_MISSION_MAPPING_NAME = "MissionNode4"; //$NON-NLS-1$
  String MCB_ACTOR_INVOLVEMENT = "ActCapInv4"; //$NON-NLS-1$
  String MCB_CAPABILITY_EXPLOITATION = "CapExploitation4"; //$NON-NLS-1$
  String MCB_ABSTRACT_CAPABILITY_EXTEND = "AbsCapExtends4int"; //$NON-NLS-1$
  String MCB_ABSTRACT_CAPABILITY_INCLUDE = "AbsCapInclude4int"; //$NON-NLS-1$
  String MCB_ABSTRACT_CAPABILITY_GENERALIZATION = "Generalization4int"; //$NON-NLS-1$
  String MCB_ACTOR_GENERALIZATION = "actorGeneralization2"; //$NON-NLS-1$
  String MCB_ACTOR_MISSION_INVOLVEMENT = "ActMissInv3"; //$NON-NLS-1$

  @Deprecated
  String OEB_CONNECTION_MAPPING_NAME = OAB_COMMUNICATION_MEAN_MAPPING_NAME;

  // filters
  String HIDE_ALL_PARAMETER = "Hide Exchange Items details in Interfaces"; //$NON-NLS-1$
  String HIDE_OPERATION_PARAMETER = "Hide Operation parameters in Interfaces"; //$NON-NLS-1$
  String SHOW_EXCHANGE_ITEMS = "Show ExchangeItems"; //$NON-NLS-1$
  String SHOW_FUNCTIONAL_EXCHANGES = "Show Functional Exchanges"; //$NON-NLS-1$
  String SHOW_EXCHANGE_ITEMS_PARAMETERS = "Show ExchangeItems parameters"; //$NON-NLS-1$
  String SHOW_EXCHANGE_ITEMS_ON_CE = "Show Exchange Items on Component Exchanges"; //$NON-NLS-1$
  String SHOW_EXCHANGE_ITEMS_ON_CE_WITH_OUT_FE = "Show Exchange Items on Component Exchange without Functional Exchanges"; //$NON-NLS-1$
  String SHOW_FE_ON_CE = "Show Allocated Functional Exchanges on Component Exchanges"; //$NON-NLS-1$
  String HIDE_CROSS_FE_IN_MULTIPART = "Hide cross Functional Exchanges of reusable Components"; //$NON-NLS-1$
  String HIDE_ASSOCIATION_LABELS = "Hide Association Labels";//$NON-NLS-1$
  String HIDE_ROLE_LABELS = "Hide Role Labels";//$NON-NLS-1$
  String HIDE_ROLE_NAMES = "Hide Role Names";//$NON-NLS-1$
  String HIDE_FUNCTIONAL_EXCHANGES_NAMES = "Hide Functional Exchanges names";//$NON-NLS-1$
  String HIDE_COMPONENT_EXCHANGES_NAMES = "Hide Component Exchanges names";//$NON-NLS-1$
  String HIDE_PHYSICAL_LINKS_NAMES = "Hide Physical Links names";//$NON-NLS-1$
  String HIDE_INTERACTIONS_NAMES = "Hide Interactions names";//$NON-NLS-1$
  String HIDE_COMMUNICATION_MEANS_NAMES = "Hide Communication Means names";//$NON-NLS-1$
  Object SHOW_FUNCTIONAL_EXCHANGES_ECHANGE_ITEMS = "Show Functional Exchanges [ExchangeItems]"; //$NON-NLS-1$
  Object SHOW_COMPONENT_EXCHANGES_ECHANGE_ITEMS = "Show Component Exchanges [ExchangeItems]"; //$NON-NLS-1$
  Object HIDE_CE_BY_DELEGATION = "Hide Simplified Diagram Based Component Exchanges"; //$NON-NLS-1$
  Object HIDE_CE_BY_GROUP = "Hide Simplified Group of Component Exchanges"; //$NON-NLS-1$
  Object HIDE_CE_BY_GROUP_ORIENTED = "Hide Simplified Oriented Grouped Component Exchanges"; //$NON-NLS-1$

  Object SHOW_FUNCTIONAL_EXCHANGES_PARAMS = "Show Functional Exchanges (Parameters)"; //$NON-NLS-1$
  Object SHOW_FUNCTIONAL_EXCHANGES_ECHANGE_ITEMS_PARAMS = "Show Functional Exchanges [ExchangeItems(Parameters)]"; //$NON-NLS-1$
  String CDB_SHOW_FULL_PATH = "Show Full Path";//$NON-NLS-1$
  String SHOW_CONTEXTUAL_ELEMENTS = "Show Contextual Elements";//$NON-NLS-1$

  // EPBS Architecture
  String EAB_CI = "CI container mapping"; //$NON-NLS-1$
  String EAB_PHYSICAL_COMPONENT_IN_CI = "PhysicalComponentInCI mapping"; //$NON-NLS-1$
  String EAB_PHYSICAL_LINK_IN_CI = "PhysicalLinkInCI mapping"; //$NON-NLS-1$
  String EAB_PHYSICAL_PORT_IN_CI = "PhysicalPortInCI mapping"; //$NON-NLS-1$

  // Capability Realization Blank diagram
  String CRB_CAPABILITY_REALIZATION_MAPPING = "CRB CapabilityRealization"; //$NON-NLS-1$
  String CRB_COMPONENT_MAPPING = "CRB Component"; //$NON-NLS-1$
  String CRB_INVOLVEMENT_MAPPING = "CRB Involvement"; //$NON-NLS-1$
  String CRB_EXTENDS_MAPPING = "CRB Cap Realization Extends"; //$NON-NLS-1$
  String CRB_INCLIDE_MAPPING = "CRB Cap Realization Include"; //$NON-NLS-1$
  String CRB_CAP_GENERALIZATION_MAPPING = "CRB Cap Generalization"; //$NON-NLS-1$
  String CRB_ACTOR_GENERALIZATION_MAPPING = "CRB Actor Generalization"; //$NON-NLS-1$

  // Interface Scenario
  String IS_CONSTRAINT_MAPPING = "Scenario_Constraint"; //$NON-NLS-1$
  String IS_CONSTRAINT_ELEMENT_MAPPING = "Scenario_ContrainedElements"; //$NON-NLS-1$
  String IS_HIDE_CALL_ARGUMENTS = "Hide Call Arguments"; //$NON-NLS-1$
  String OAB_ROLE_MAPPING_NAME = "OAB_subRoles"; //$NON-NLS-1$
  String ORB_ROLE_MAPPING_NAME = "ORB_Role1"; //$NON-NLS-1$

  // additions 15/02/2012
  String SHOW_COMPONENT_EXCHANGES_PARAMS = "CEParam"; //$NON-NLS-1$
  String SHOW_COMPONENT_EXCHANGES_EXCHANGE_ITEMS_PARAMS = "CEEIParam"; //$NON-NLS-1$

  // FunctionalChainDescription
  String FCD_FUNCTION__MAPPING_NAME = "FC_AbstractFunction"; //$NON-NLS-1$
  String FCD_FUNCTIONAL_CHAIN__MAPPING_NAME = "FC_FunctionalChain"; //$NON-NLS-1$
  String FCD_FUNCTIONAL_EXCHANGE__MAPPING_NAME = "FC_Exchange"; //$NON-NLS-1$

  // PhysicalPathDescription
  String PPD_PHYSICAL_LINK__MAPPING_NAME = "PPD_PhysicalLink"; //$NON-NLS-1$
  String PPD_PHYSICAL_PATH__MAPPING_NAME = "PPD_PhysicalPath"; //$NON-NLS-1$
  String PPD_PART__MAPPING_NAME = "PPD_Part"; //$NON-NLS-1$

  // Contextual OC Diagram
  String COC_OC_MAPPING_NAME = "COC2_OperationalCapabilities"; //$NON-NLS-1$
  String COC_COMMUNICATION_MEAN_MAPPING_NAME = "COC_CommunicationMeans"; //$NON-NLS-1$
  String COC_EntityCapabilityInvolvement_MAPPING_NAME = "COC2_EntityCapabilityInvolvement";//$NON-NLS-1$
  String COC_Extends_MAPPING_NAME = "COC2_OC_Extends";//$NON-NLS-1$
  String COC_Include_MAPPING_NAME = "COC2_OC_Include";//$NON-NLS-1$
  String COC_Generalization_MAPPING_NAME = "COC2_OC_Generalization";//$NON-NLS-1$

  // Exchange context
  String SHOW_EXCHANGE_CONTEXT = "ShowExchangeContext"; //$NON-NLS-1$
  String SHOW_CE_EXCHANGE_CONTEXT = "ShowCEExchangeContext"; //$NON-NLS-1$
  String SHOW_FE_EXCHANGE_CONTEXT = "ShowFEExchangeContext"; //$NON-NLS-1$
  String SHOW_EI_EXCHANGE_CONTEXT = "ShowEIExchangeContext"; //$NON-NLS-1$
}
