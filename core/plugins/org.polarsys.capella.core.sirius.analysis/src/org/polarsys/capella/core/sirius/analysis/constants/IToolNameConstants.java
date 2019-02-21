/*******************************************************************************
 * Copyright (c) 2006, 2019 THALES GLOBAL SERVICES.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *  
 * Contributors:
 *    Thales - initial API and implementation
 *******************************************************************************/
package org.polarsys.capella.core.sirius.analysis.constants;

import org.polarsys.capella.common.mdsofa.common.constant.ICommonConstants;

/**
 * Centralizes constants about tools.
 */
public interface IToolNameConstants {

  
  // New Diagram Tools convention (tool name should be the same across all diagrams where the tool is available)
  // =================================================================================================
  
  String TOOL_CREATE_FUNCTION = "Function";
  String TOOL_CREATE_FUNCTION_DUPLICATE = "Duplicate";
  String TOOL_CREATE_FUNCTION_GATHER = "Gather";
  String TOOL_CREATE_FUNCTION_ROUTE = "Route";
  String TOOL_CREATE_FUNCTION_SELECT = "Select";
  String TOOL_CREATE_FUNCTION_SPLIT = "Split";
  String TOOL_CREATE_ACTOR_FUNCTION = "Actor Function";
  String TOOL_CREATE_INPUT_PORT = "Input Port";
  String TOOL_CREATE_OUTPUT_PORT = "Output Port";
  String TOOL_CREATE_FUNCTIONAL_EXCHANGE = "Functional Exchange";
  String TOOL_CREATE_FUNCTIONAL_CHAIN = "Functional Chain";
  String TOOL_CREATE_CONSTRAINT = "Constraint";
  String TOOL_CREATE_CONSTRAINT_ELEMENT = "ConstraintElement";
  String TOOL_CREATE_OPERATIONAL_ROLE = "Operational Role";
  String TOOL_CREATE_OPERATIONAL_ACTIVITY = "Operational Activity";
  String TOOL_CREATE_INTERACTION = "Interaction";
  String TOOL_INSERT_REMOVE_FUNCTION_PORT = "Show/Hide Function Ports";
  String TOOL_INSERT_REMOVE_FUNCTIONS = "Show/Hide Functions";
  String TOOL_INSERT_REMOVE_FUNCTIONAL_EXCHANGES = "Functional Exchanges";
  String TOOL_INSERT_REMOVE_FUNCTIONAL_CHAINS = "Show/Hide Functional Chains"; 
  String TOOL_INSERT_REMOVE_EXCHANGE_CATEGORIES = "Exchange Categories";
  String TOOL_INSERT_REMOVE_OPERATIONAL_ROLES = "Operational Roles";
  String TOOL_INSERT_REMOVE_INTERACTIONS = "Interactions";
  String TOOL_INSERT_REMOVE_ALLOCATED_ACTIVITIES = "Allocated Activities";
  String TOOL_SHOW_ALL_ALLOCATED_ACTIVITIES = "All Allocated Activities";
  String TOOL_MANAGE_ACTIVITY_ALLOCATION = "Manage Activity Allocation";
  String TOOL_SWITCH_FUNCTIONAL_EXCHANGE_CATEGORIES = "Switch Functional Exchanges / Categories";
  String TOOL_INSERT_REMOVE_CONSTRAINTS = "Show/Hide Constraints";
  String TOOL_SWITCH_FUNCTIONAL_EXCHANGE_CATEGORY = "Switch Functional Exchanges / Categories";
  String TOOL_INSERT_REMOVE_PV = "Applied Property Values";
  String TOOL_INSERT_REMOVE_PVG = "Applied Property Value Groups";
    
  // =================================================================================================
  // Old Diagram Tools convention
  //

  //
  // Tools that are available to multiple types of diagrams
  //
  String TOOL_COMMON_SELECT_SAME_TYPE = "SameType";
  String TOOL_COMMON_SELECT_SAME_MAPPING = "SameMapping";
  String TOOL_COMMON_SELECT_OWNED_PORTS = "OwnedPorts";
  String TOOL_COMMON_SELECT_OWNED_ELEMENTS = "OwnedElements";
  String TOOL_COMMON_SELECT_RELATED_EDGES = "OwnedEdges";
  String TOOL_COMMON_ELEMENTS_FROM_SCENARIO = "Elements from Scenario";
  String TOOL_COOMON_FUNCTIONS_FROM_MODE_STATE = "Functions from Mode / State";
  
  String TOOL_CREATE_PHYSICAL_PATH = "Physical Path";//$NON-NLS-1$

  // Accelerators
  String TOOL_DIAGRAMINITIALIZATION = "DiagramInitialization";//$NON-NLS-1$
  String TOOL_STATEMODE_ELEMENTS = "State-Mode Elements";//$NON-NLS-1$
  String TOOL_SCENARIO_ELEMENTS = "Scenario Elements";//$NON-NLS-1$
  String TOOL_SHOW_FUNCTIONAL_CHAIN_ELEMENTS = "Show Functional Chain Elements";//$NON-NLS-1$

  // CC tools - Contextual Capability
  String TOOL_CC_CREATE_ACTOR = "Actor"; //$NON-NLS-1$
  String TOOL_CC_CREATE_MISSION = "Mission"; //$NON-NLS-1$
  String TOOL_CC_CREATE_CAPABILITY = "Capability"; //$NON-NLS-1$
  String TOOL_CC_CREATE_CAPABILITY_EXPLOITATION = "Capability Exploitation"; //$NON-NLS-1$
  String TOOL_CC_CREATE_INVOLVED_ACTOR = "Involved Actor"; //$NON-NLS-1$
  String TOOL_CC_CREATE_EXTENDS = "Extends"; //$NON-NLS-1$
  String TOOL_CC_CREATE_INCLUDES = "Includes"; //$NON-NLS-1$
  String TOOL_CC_CREATE_CAPABILITY_GENERALIZATION = "Capability Generalization"; //$NON-NLS-1$
  String TOOL_CC_CREATE_ACTOR_GENERALIZATION = "Actor Generalization"; //$NON-NLS-1$
  String TOOL_CC_INSERT_REMOVE_ACTORS = "Actors"; //$NON-NLS-1$
  String TOOL_CC_INSERT_REMOVE_MISSIONS = "Missions"; //$NON-NLS-1$
  String TOOL_CC_INSERT_REMOVE_CAPABILITIES = "Capabilities"; //$NON-NLS-1$
  String TOOL_CC_INSERT_REMOVE_RELATIONSHIPS = "Relationships"; //$NON-NLS-1$
  String TOOL_CC_CREATE_CONSTRAINT = "Constraint"; //$NON-NLS-1$
  String TOOL_CC_CREATE_CONSTRAINTELEMENT = "ConstraintElement"; //$NON-NLS-1$
  String TOOL_CC_INSERT_REMOVE_CONSTRAINTS = "Constraints"; //$NON-NLS-1$
  String TOOL_CC_RECONNECT_GENERALIZATION_SOURCE = "CB Reconnect Generalization Source"; //$NON-NLS-1$
  String TOOL_CC_RECONNECT_GENERALIZATION_TARGET = "CB Reconnect Generalization Target"; //$NON-NLS-1$
  // tool id
  String TOOL_CC_SHOW_ACTORS = "s/h Actors"; //$NON-NLS-1$
  String TOOL_CC_SHOW_MISSIONS = "s/h Missions"; //$NON-NLS-1$
  String TOOL_CC_SHOW_CAPABILITY = "s/h Capabilities"; //$NON-NLS-1$

  // CCRI Tools - Contextual Capability Realization Involvement
  String TOOL_CCRI_CREATE_CAPABILITY_REALIZATION_NODE = "Capability Realization"; //$NON-NLS-1$
  String TOOL_CCRI_CREATE_ACTOR_NODE = "Actor"; //$NON-NLS-1$
  String TOOL_CCRI_CREATE_COMPONENT_NODE = "Component"; //$NON-NLS-1$
  String TOOL_CCRI_CREATE_CAPABILITY_REALIZATION_INVOLVEMENT_EDGE = "Involvement"; //$NON-NLS-1$
  String TOOL_CCRI_INSERT_CAPABILITY_REALIZATION_NODE = "s/h Capability Realization"; //$NON-NLS-1$
  String TOOL_CCRI_INSERT_ACTOR_NODE = "s/h Actors"; //$NON-NLS-1$
  String TOOL_CCRI_INSERT_COMPONENT_NODE = "s/h Components"; //$NON-NLS-1$
  String TOOL_CCRI_INSERT_RELATIONSHIP_EDGE = "Show/Hide Relationships"; //$NON-NLS-1$

  // CDB Tools - Class Diagram Blank
  // Classes Tools
  String TOOL_CDB_DELETE_CONSTRAINT_ELEMENT = "Delete Constraint Elements"; //$NON-NLS-1$
  String TOOL_CDB_CREATE_DATA_PACKAGE = "Data Package"; //$NON-NLS-1$
  String TOOL_CDB_CREATE_CLASS = "Class"; //$NON-NLS-1$
  String TOOL_CDB_CREATE_UNION = "Union"; //$NON-NLS-1$
  String TOOL_CDB_CREATE_COLLECTION = "Collection"; //$NON-NLS-1$
  String TOOL_CDB_CREATE_BOOLEAN_TYPE = "BooleanType"; //$NON-NLS-1$
  String TOOL_CDB_CREATE_BOOLEAN_LITERAL = "BooleanLiteral"; //$NON-NLS-1$
  String TOOL_CDB_CREATE_ENUMERATION = "Enumeration"; //$NON-NLS-1$
  String TOOL_CDB_CREATE_ENUMERATION_LITERAL = "EnumerationLiteral"; //$NON-NLS-1$
  String TOOL_CDB_CREATE_NUMERIC_TYPE = "NumericType"; //$NON-NLS-1$
  String TOOL_CDB_CREATE_STRING_TYPE = "StringType"; //$NON-NLS-1$
  String TOOL_CDB_CREATE_PHYSICAL_QUANTITY = "PhysicalQuantity"; //$NON-NLS-1$
  String TOOL_CDB_CREATE_UNIT = "Unit"; //$NON-NLS-1$
  String TOOL_CDB_CREATE_NUMERIC_REFERENCE = "Numeric Reference"; //$NON-NLS-1$
  String TOOL_CDB_CREATE_LITERAL_NUMERIC_VALUE = "Literal Numeric Value"; //$NON-NLS-1$
  String TOOL_CDB_CREATE_UNARY_EXPRESSION = "Unary Expression"; //$NON-NLS-1$
  String TOOL_CDB_CREATE_BINARY_EXPRESSION = "Binary Expression"; //$NON-NLS-1$
  String TOOL_CDB_CREATE_LITERAL_STRING_VALUE = "Literal String Value"; //$NON-NLS-1$
  String TOOL_CDB_CREATE_STRING_REFERENCE = "String Reference"; //$NON-NLS-1$
  String TOOL_CDB_CREATE_BOOLEAN_REFERENCE = "Boolean Reference"; //$NON-NLS-1$
  String TOOL_CDB_CREATE_COMPLEX_VALUE = "Complex Value"; //$NON-NLS-1$
  String TOOL_CDB_CREATE_COMPLEX_VALUE_REFERENCE = "Complex Value Reference"; //$NON-NLS-1$
  String TOOL_CDB_CREATE_ENUMERATION_REFERENCE = "Enumeration Reference"; //$NON-NLS-1$
  String TOOL_CDB_CREATE_COLLECTION_VALUE = "Collection Value"; //$NON-NLS-1$
  String TOOL_CDB_CREATE_COLLECTION_VALUE_REFERENCE = "Collection Value Reference"; //$NON-NLS-1$
  String TOOL_CDB_CREATE_PROPERTY = "Property"; //$NON-NLS-1$
  String TOOL_CDB_CREATE_SERVICE = "Class Operation"; //$NON-NLS-1$
  String TOOL_CDB_CREATE_PARAMETER = "Parameter"; //$NON-NLS-1$
  String TOOL_CDB_CREATE_ASSOCIATION = "Association"; //$NON-NLS-1$
  String TOOL_CDB_CREATE_AGGREGATION = "Aggregation"; //$NON-NLS-1$
  String TOOL_CDB_CREATE_COMPOSITION = "Composition"; //$NON-NLS-1$
  String TOOL_CDB_CREATE_GENERALIZATION = "Generalization"; //$NON-NLS-1$
  String TOOL_CDB_CREATE_COLLECTION_TYPE = "Collection Type"; //$NON-NLS-1$
  String TOOL_CDB_INSERT_REMOVE_DATA_PACKAGES = "Data Packages"; //$NON-NLS-1$
  String TOOL_CDB_INSERT_REMOVE_TYPES = "Types"; //$NON-NLS-1$
  String TOOL_CDB_INSERT_REMOVE_DATA_VALUES = "Data Values"; //$NON-NLS-1$
  String TOOL_CDB_INSERT_RELATIONSHIPS = "Relationships"; //$NON-NLS-1$
  String TOOL_CDB_CREATE_CONSTRAINT = "Constraint"; //$NON-NLS-1$
  String TOOL_CDB_CREATE_CONSTRAINT_ELEMENT = "ConstraintElement"; //$NON-NLS-1$
  String TOOL_CDB_INSERT_REMOVE_CONSTRAINTS = "Constraints"; //$NON-NLS-1$
  // Unsynchronized Classes Tools
  String TOOL_CDB_INSERT_REMOVE_PROPERTIES = "Properties"; //$NON-NLS-1$
  String TOOL_CDB_INSERT_REMOVE_OPERATIONS = "Operations"; //$NON-NLS-1$
  // tool id
  String TOOL_CDB_RECONNECT_ASSOCIATION_SOURCE = "CB Reconnect Association Source"; //$NON-NLS-1$
  String TOOL_CDB_RECONNECT_ASSOCIATION_TARGET = "CB Reconnect Association Target"; //$NON-NLS-1$
  String TOOL_CDB_RECONNECT_COLLECTION_TYPE = "CB Reconnect Collection Type"; //$NON-NLS-1$
  String TOOL_CDB_RECONNECT_GENERALIZATION_SOURCE = "CB Reconnect Generalization Source"; //$NON-NLS-1$
  String TOOL_CDB_RECONNECT_GENERALIZATION_TARGET = "CB Reconnect Generalization Target"; //$NON-NLS-1$
  String TOOL_CDB_RECONNECT_EXCHANGEITEMELEMENT_SOURCE = "ExchangeItemElement source"; //$NON-NLS-1$
  String TOOL_CDB_RECONNECT_EXCHANGEITEMELEMENT_TARGET = "ExchangeItemElement target"; //$NON-NLS-1$

  // Communication Tools
  String TOOL_CDB_CREATE_INTERFACE_PACKAGE = "Interface Package"; //$NON-NLS-1$
  String TOOL_CDB_CREATE_INTERFACE = "Interface"; //$NON-NLS-1$
  // EXCHANGE ITEMS
  String TOOL_CDB_CREATE_EI_OPERATION = "Operation"; //$NON-NLS-1$
  String TOOL_CDB_CREATE_EI_EVENT = "Event"; //$NON-NLS-1$
  String TOOL_CDB_CREATE_EI_FLOW = "Flow"; //$NON-NLS-1$
  String TOOL_CDB_CREATE_EI_DATA = "Data"; //$NON-NLS-1$
  String TOOL_CDB_CREATE_EI_UNSET = "Undefined Exchange Item"; //$NON-NLS-1$
  // EXCHANGE ITEMS
  String TOOL_CDB_CREATE_EXCHANGE_ITEM_ELEMENT = "Exchange Item Element"; //$NON-NLS-1$
  String TOOL_CDB_ALLOCATE_EXCHANGE_ITEM = "Manage Exchange Item Allocations"; //$NON-NLS-1$
  String TOOL_CDB_INSERT_REMOVE_INTERFACE_PACKAGES = "Interface Packages"; //$NON-NLS-1$
  String TOOL_CDB_INSERT_REMOVE_INTERFACES = "Interfaces"; //$NON-NLS-1$
  String TOOL_CDB_INSERT_REMOVE_EXCHANGE_ITEMS = "Exchange Items"; //$NON-NLS-1$
  String TOOL_CDB_INSERT_REMOVE_EXCHANGE_ITEM_ELEMENT_LINKS = "Exchange Item Element Links"; //$NON-NLS-1$
  // Accelerators
  String TOOL_CDB_INITIALIZATION_FROM_EXISTING_DIAGRAM = "Initialization from existing diagram"; //$NON-NLS-1$
  // TOOLS ID
  String TOOL_CDB_CREATE_SIGNAL_RECEPTION = "Signal Reception"; //$NON-NLS-1$
  String TOOL_CDB_SHOW_HIDE_DATAPKGS = "Show/Hide DataPkgs"; //$NON-NLS-1$
  String TOOL_CDB_SHOW_HIDE_INTERFACEPKGS = "Show/Hide InterfacePkgs"; //$NON-NLS-1$
  String TOOL_CDB_INSERT_REMOVE_TYPE = "Insert/Remove Types"; //$NON-NLS-1$
  String TOOL_CDB_SHOW_HIDE_ELEMENTS = "Show/Hide Elements in Package"; //$NON-NLS-1$
  String TOOL_CDB_SHOW_HIDE_CONSTRAINTS = "Show/Hide Constraints"; //$NON-NLS-1$
  String TOOL_CDB_SHOW_HIDE_RELATIONSHIPS = "Show/Hide Relationships"; //$NON-NLS-1$
  String TOOL_CDB_CREATE_DATAPKG = "DataPkg"; //$NON-NLS-1$
  String TOOL_CDB_CREATE_INTERFACEPKG = "InterfacePkg"; //$NON-NLS-1$
  String TOOL_CDB_SHOW_HIDE_INTERFACES = "Insert/Remove Interfaces"; //$NON-NLS-1$
  String TOOL_CDB_SHOW_HIDE_EXCHANGE_ITEMS = "Insert/Remove ExchangeItems"; //$NON-NLS-1$
  String TOOL_CDB_SHOW_HIDE_EXCHANGEITEMELEMENT_LINK = "Show/Hide ExchangeItemElement Link"; //$NON-NLS-1$
  String TOOL_CDB_SHOW_HIDE_DATAVALUES = "Show/Hide DataValues"; //$NON-NLS-1$

  // CDI tools - Contextual Component Detailed Interfaces
  String TOOL_CDI_CREATE_INTERFACE = "Interface"; //$NON-NLS-1$
  String TOOL_CDI_CREATE_IN_FLOW_PORT = "In Flow Port"; //$NON-NLS-1$
  String TOOL_CDI_CREATE_OUT_FLOW_PORT = "Out Flow Port"; //$NON-NLS-1$
  String TOOL_CDI_CREATE_INOUT_FLOW_PORT = "InOut Flow Port"; //$NON-NLS-1$
  String TOOL_CDI_CREATE_STANDARD_PORT = "Standard Port"; //$NON-NLS-1$
  String TOOL_CDI_CREATE_IMPLEMENTS = "Implements"; //$NON-NLS-1$
  String TOOL_CDI_CREATE_USES = "Uses"; //$NON-NLS-1$
  String TOOL_CDI_CREATE_PROVIDES = "Provides"; //$NON-NLS-1$
  String TOOL_CDI_CREATE_REQUIRES = "Requires"; //$NON-NLS-1$
  String TOOL_CDI_CREATE_GENERALIZATION = "Generalization"; //$NON-NLS-1$
  String TOOL_CDI_CREATE_EVENT = "Event"; //$NON-NLS-1$
  String TOOL_CDI_CREATE_OPERATION = "Operation"; //$NON-NLS-1$
  String TOOL_CDI_CREATE_FLOW = "Flow"; //$NON-NLS-1$
  String TOOL_CDI_CREATE_DATA = "Data"; //$NON-NLS-1$
  String TOOL_CDI_CREATE_UNDEFINED_EXCHANGE_ITEM = "Undefined Exchange Item"; //$NON-NLS-1$
  String TOOL_CDI_CREATE_EXCHANGE_ITEM_ELEMENT = "Exchange Item Element"; //$NON-NLS-1$
  String TOOL_CDI_CREATE_TRANSMIT = "Transmit"; //$NON-NLS-1$
  String TOOL_CDI_CREATE_ACQUIRE = "Acquire"; //$NON-NLS-1$
  String TOOL_CDI_MANAGE_EXCHANGE_ITEM_ALLOCATIONS = "Manage Exchange Item Allocations"; //$NON-NLS-1$
  String TOOL_CDI_INSERT_REMOVE_INTERFACES = "Interfaces"; //$NON-NLS-1$
  String TOOL_CDI_INSERT_REMOVE_EXCHANGE_ITEMS = "Exchange Items"; //$NON-NLS-1$
  String TOOL_CDI_CREATE_CONSTRAINT = "Constraint"; //$NON-NLS-1$
  String TOOL_CDI_CREATE_CONSTRAINTELEMENT = "ConstraintElement"; //$NON-NLS-1$
  String TOOL_CDI_INSERT_REMOVE_CONSTRAINTS = "Constraints"; //$NON-NLS-1$
  // Unsynchronized tools
  String TOOL_CDI_INSERT_REMOVE_EXCHANGE_ITEM_ALLOCATIONS = "Exchange Item Allocations"; //$NON-NLS-1$
  String TOOL_CDI_INSERT_REMOVE_EXCHANGE_ITEM_ELEMENTS = "Exchange Item Elements"; //$NON-NLS-1$

  String TOOL_CDI_SHOW_HIDE_EXCHANGE_ITEMS = "Show/Hide Exchange Items"; //$NON-NLS-1$
  String TOOL_CDI_SHOW_HIDE_INTERFACES = "Show/Hide Interfaces"; //$NON-NLS-1$
  String TOOL_CDI_SHOW_HIDE_EXCHANGE_ITEM_ALLOCATIONS = "Show/Hide Exchange Item Allocations"; //$NON-NLS-1$

  // CEI tools - Contextual Component External Interface
  String TOOL_CEI_CREATE_COMPONENT = "Component"; //$NON-NLS-1$
  String TOOL_CEI_REUSE_COMPONENT = "Reuse Component"; //$NON-NLS-1$
  String TOOL_CEI_CREATE_ACTOR = "Actor"; //$NON-NLS-1$
  String TOOL_CEI_CREATE_IN_FLOW_PORT = "In Flow Port"; //$NON-NLS-1$
  String TOOL_CEI_CREATE_OUT_FLOW_PORT = "Out Flow Port"; //$NON-NLS-1$
  String TOOL_CEI_CREATE_INOUT_FLOW_PORT = "InOut Flow Port"; //$NON-NLS-1$
  String TOOL_CEI_CREATE_STANDARD_PORT = "Standard Port"; //$NON-NLS-1$
  String TOOL_CEI_CREATE_INTERFACE = "Interface"; //$NON-NLS-1$
  String TOOL_CEI_CREATE_IMPLEMENTS = "Implements"; //$NON-NLS-1$
  String TOOL_CEI_CREATE_USES = "Uses"; //$NON-NLS-1$
  String TOOL_CEI_CREATE_PROVIDES = "Provides"; //$NON-NLS-1$
  String TOOL_CEI_CREATE_REQUIRES = "Requires"; //$NON-NLS-1$
  String TOOL_CEI_CREATE_GENERALIZATION = "Generalization"; //$NON-NLS-1$
  String TOOL_CEI_CREATE_EVENT = "Event"; //$NON-NLS-1$
  String TOOL_CEI_CREATE_OPERATION = "Operation"; //$NON-NLS-1$
  String TOOL_CEI_CREATE_FLOW = "Flow"; //$NON-NLS-1$
  String TOOL_CEI_CREATE_DATA = "Data"; //$NON-NLS-1$
  String TOOL_CEI_CREATE_UNDEFINED_EXCHANGE_ITEM = "Undefined Exchange Item"; //$NON-NLS-1$
  String TOOL_CEI_CREATE_EXCHNAGE_ITEM_ALLOCATION = "Exchange Item Allocation"; //$NON-NLS-1$
  String TOOL_CEI_CREATE_TRANSMIT = "Transmit"; //$NON-NLS-1$
  String TOOL_CEI_CREATE_ACQUIRE = "Acquire"; //$NON-NLS-1$
  String TOOL_CEI_INSERT_REMOVE_COMPONENTS = "Components"; //$NON-NLS-1$
  String TOOL_CEI_SHOW_HIDE_COMPONENTS = "Show/Hide Components"; //$NON-NLS-1$
  String TOOL_CEI_INSERT_REMOVE_ACTORS = "Actors"; //$NON-NLS-1$
  String TOOL_CEI_INSERT_REMOVE_INTERFACES = "Interfaces"; //$NON-NLS-1$
  String TOOL_CEI_INSERT_REMOVE_EXCHANGES_ITEMS = "Exchange Items"; //$NON-NLS-1$
  String TOOL_CEI_CREATE_CONSTRAINT = "Constraint"; //$NON-NLS-1$
  String TOOL_CEI_CREATE_CONSTRAINTELEMENT = "ConstraintElement"; //$NON-NLS-1$
  String TOOL_CEI_INSERT_REMOVE_CONSTRAINTS = "Constraints"; //$NON-NLS-1$

  // CIBD tools - Configuration Items Breakdown
  String TOOL_CIBD_CREATE_COTS = "COTS"; //$NON-NLS-1$
  String TOOL_CIBD_CREATE_CS = "CS"; //$NON-NLS-1$
  String TOOL_CIBD_CREATE_HW = "HW"; //$NON-NLS-1$
  String TOOL_CIBD_CREATE_INTERFACE = "Interface"; //$NON-NLS-1$
  String TOOL_CIBD_CREATE_NDI = "NDI"; //$NON-NLS-1$
  String TOOL_CIBD_CREATE_PRIME_ITEM = "Prime Item"; //$NON-NLS-1$
  String TOOL_CIBD_CREATE_SYSTEM = "System"; //$NON-NLS-1$
  String TOOL_CIBD_CONTAINED_IN = "Contained In"; //$NON-NLS-1$
  String TOOL_CIBD_CREATE_CONSTRAINT = "Constraint"; //$NON-NLS-1$
  String TOOL_CIBD_CREATE_CONSTRAINTELEMENT = "ConstraintElement"; //$NON-NLS-1$
  String TOOL_CIBD_INSERT_REMOVE_CONSTRAINTS = "Constraints"; //$NON-NLS-1$

  // CM tools - Contextual Mission
  String TOOL_CM_CREATE_ACTOR = "Actor"; //$NON-NLS-1$
  String TOOL_CM_CREATE_CAPABILITY = "Capability"; //$NON-NLS-1$
  String TOOL_CM_CREATE_CAPABILITY_EXPLOITATION = "Capability Exploitation"; //$NON-NLS-1$
  String TOOL_CM_CREATE_ACTOR_INVOLVEMENT = "Actor Involvement"; //$NON-NLS-1$
  String TOOL_CM_CREATE_ACTOR_GENERALIZATION = "Actor Generalization"; //$NON-NLS-1$
  String TOOL_CM_INSERT_REMOVE_ACTORS = "Actors"; //$NON-NLS-1$
  String TOOL_CM_INSERT_REMOVE_CAPABILITIES = "Capabilities"; //$NON-NLS-1$
  String TOOL_CM_INSERT_REMOVE_RELATIONSHIPS = "Relationships"; //$NON-NLS-1$
  String TOOL_CM_CREATE_CONSTRAINT = "Constraint"; //$NON-NLS-1$
  String TOOL_CM_CREATE_CONSTRAINTELEMENT = "ConstraintElement"; //$NON-NLS-1$
  String TOOL_CM_INSERT_REMOVE_CONSTRAINTS = "Constraints"; //$NON-NLS-1$
  String TOOL_CM_RECONNECT_GENERALIZATION_SOURCE = TOOL_CC_RECONNECT_GENERALIZATION_SOURCE;
  String TOOL_CM_RECONNECT_GENERALIZATION_TARGET = TOOL_CC_RECONNECT_GENERALIZATION_TARGET;
  // tool id
  String TOOL_CM_INSERT_ACTORS = "s/h Actors"; //$NON-NLS-1$
  String TOOL_CM_INSERT_CAPABILITIES = "s/h Capabilities"; //$NON-NLS-1$

  // COC - Contextual Operational Capability tools
  String TOOL_COC_CREATE_OPERATIONAL_ENTITY = "Operational Entity"; //$NON-NLS-1$
  String TOOL_COC_CREATE_OPERATIONAL_ACTOR = "Operational Actor"; //$NON-NLS-1$
  String TOOL_COC_CREATE_OPERATIONAL_CAPABILITY = "Operational Capability"; //$NON-NLS-1$
  String TOOL_COC_CREATE_INVOLMENT = "Involvement"; //$NON-NLS-1$
  String TOOL_COC_CREATE_EXTENDS = "Extends"; //$NON-NLS-1$
  String TOOL_COC_CREATE_INCLUDES = "Includes"; //$NON-NLS-1$
  String TOOL_COC_CREATE_OPERATIONAL_CAPABILITY_GENERALIZATION = "Operational Capability Generalization"; //$NON-NLS-1$
  String TOOL_COC_INSERT_REMOVE_OPERATIONAL_ENTITIES = "Operational Entities"; //$NON-NLS-1$
  String TOOL_COC_INSERT_REMOVE_OPERATIONAL_ACTORS = "Operational Actors"; //$NON-NLS-1$
  String TOOL_COC_INSERT_REMOVE_OPERATIONAL_CAPABILITIES = "Operational Capabilities"; //$NON-NLS-1$
  String TOOL_COC_INSERT_REMOVE_RELATIONSHIPS = "Relationships"; //$NON-NLS-1$
  String TOOL_COC_CREATE_CONSTRAINT = "Constraint"; //$NON-NLS-1$
  String TOOL_COC_CREATE_CONSTRAINT_ELEMENT = "ConstraintElement"; //$NON-NLS-1$
  String TOOL_COC_INSERT_REMOVE_CONSTRAINTS = "Constraints"; //$NON-NLS-1$

  String TOOL_COC_SHOW_HIDE_OPERATIONAL_ENTITY = "Show/Hide OE"; //$NON-NLS-1$
  String TOOL_COC_SHOW_HIDE_OPERATIONAL_ACTORS = "Show/Hide OA"; //$NON-NLS-1$
  String TOOL_COC_SHOW_HIDE_CAPABILITIES = "Show/Hide Operational Capabilities"; //$NON-NLS-1$
  // CRB tools - Capability Realization Blank
  // Labels
  String TOOL_CRB_CREATE_CAPABILITY_REALIZATION = "Capability Realization "; //$NON-NLS-1$
  String TOOL_CRB_CREATE_COMPONENT = "Component"; //$NON-NLS-1$
  String TOOL_CRB_CREATE_COTS = "COTS"; //$NON-NLS-1$
  String TOOL_CRB_CREATE_CS = "CS"; //$NON-NLS-1$
  String TOOL_CRB_CREATE_HW = "HW"; //$NON-NLS-1$
  String TOOL_CRB_CREATE_INTERFACE = "Interface"; //$NON-NLS-1$
  String TOOL_CRB_CREATE_NDI = "NDI"; //$NON-NLS-1$
  String TOOL_CRB_CREATE_PRIME_ITEM = "Prime Item"; //$NON-NLS-1$
  String TOOL_CRB_CREATE_SYSTEM = "System"; //$NON-NLS-1$
  String TOOL_CRB_CREATE_ACTOR_LABEL = "Actor"; //$NON-NLS-1$
  String TOOL_CRB_CREATE_INVOLVEMENT = "Involvement"; //$NON-NLS-1$
  String TOOL_CRB_CREATE_EXTENDS = "Extends"; //$NON-NLS-1$
  String TOOL_CRB_CREATE_INCLUDES = "Includes"; //$NON-NLS-1$
  String TOOL_CRB_CREATE_CAPABILITY_GENERALIZATION = "Capability Generalization"; //$NON-NLS-1$
  String TOOL_CRB_CREATE_ACTOR_GENERALIZATION = "Actor Generalization"; //$NON-NLS-1$
  String TOOL_CRB_INSERT_REMOVE_CAPABILITY_REALIZATIONS = "Capability Realizations"; //$NON-NLS-1$
  String TOOL_CRB_INSERT_REMOVE_COMPONENTS = "Components"; //$NON-NLS-1$
  String TOOL_CRB_INSERT_REMOVE_ACTORS = "Actors"; //$NON-NLS-1$
  String TOOL_CRB_INSERT_REMOVE_RELATIONSHIPS = "Relationships"; //$NON-NLS-1$
  String TOOL_CRB_CREATE_CONSTRAINT = "Constraint"; //$NON-NLS-1$
  String TOOL_CRB_CREATE_CONSTRAINTELEMENT = "ConstraintElement"; //$NON-NLS-1$
  String TOOL_CRB_INSERT_REMOVE_CONSTRAINTS = "Constraints"; //$NON-NLS-1$

  // Names (ID) - Tool name and label are often the same excepting in the following cases
  String TOOL_CRB_CREATE_ACTOR_NAME = "CRB Actor"; //$NON-NLS-1$
  String TOOL_CRB_SHOW_HIDE_CAPABILITY_REALIZATIONS = "Show/Hide Capability Realizations"; //$NON-NLS-1$
  String TOOL_CRB_SHOW_HIDE_COMPONENTS = "Show/Hide Components"; //$NON-NLS-1$
  String TOOL_CRB_SHOW_HIDE_ACTORS = "Show/Hide Actors"; //$NON-NLS-1$
  String TOOL_CRB_SHOW_HIDE_RELATIONSHIPS = "Show/Hide Relationships"; //$NON-NLS-1$
  String TOOL_CRB_SHOW_HIDE_CONSTRAINTS = "Show/Hide Constraints"; //$NON-NLS-1$
  String TOOL_CRB_RECONNECT_GENERALIZATION_SOURCE = TOOL_CC_RECONNECT_GENERALIZATION_SOURCE;
  String TOOL_CRB_RECONNECT_GENERALIZATION_TARGET = TOOL_CC_RECONNECT_GENERALIZATION_TARGET;
  // CRI tools - Contextual Capability Realization Involvement
  String TOOL_CRI_INVOLVE_COMPONENT = "Involve Component"; //$NON-NLS-1$
  String TOOL_CRI_CREATE_CONSTRAINT = "Constraint"; //$NON-NLS-1$
  String TOOL_CRI_CREATE_CONSTRAINTELEMENT = "ConstraintElement"; //$NON-NLS-1$
  String TOOL_CRI_INSERT_REMOVE_CONSTRAINTS = "Constraints"; //$NON-NLS-1$

  // CSA tools - Contextual System Actors
  String TOOL_CSA_CREATE_ACTOR = "Actor"; //$NON-NLS-1$
  String TOOL_CSA_CREATE_ACTOR_GENERALIZATION = "Actor Generalization"; //$NON-NLS-1$
  String TOOL_CSA_CREATE_CONSTRAINT = "Constraint"; //$NON-NLS-1$
  String TOOL_CSA_CREATE_CONSTRAINTELEMENT = "ConstraintElement"; //$NON-NLS-1$
  String TOOL_CSA_INSERT_REMOVE_CONSTRAINTS = "Constraints"; //$NON-NLS-1$
  String TOOL_CSA_RECONNECT_GENERALIZATION_SOURCE = TOOL_CC_RECONNECT_GENERALIZATION_SOURCE;
  String TOOL_CSA_RECONNECT_GENERALIZATION_TARGET = TOOL_CC_RECONNECT_GENERALIZATION_TARGET;

  // EAB - EPBS Architecture Blank tools
  String TOOL_EAB_CREATE_COTS = "COTS "; //$NON-NLS-1$
  String TOOL_EAB_CREATE_CS = "CS"; //$NON-NLS-1$
  String TOOL_EAB_CREATE_HW = "HW"; //$NON-NLS-1$
  String TOOL_EAB_CREATE_INTERFACE = "Interface"; //$NON-NLS-1$
  String TOOL_EAB_CREATE_NDI = "NDI"; //$NON-NLS-1$
  String TOOL_EAB_CREATE_PRIME_ITEM = "Prime Item"; //$NON-NLS-1$
  String TOOL_EAB_CREATE_SYSTEM = "System"; //$NON-NLS-1$
  String TOOL_EAB_MANAGE_REALIZED_PHYSICAL_ARTIFACTS = "Manage Realized Physical Artifacts"; //$NON-NLS-1$
  String TOOL_EAB_INSERT_REMOVE_CONFIGURATION_ITEMS = "Configuration Items"; //$NON-NLS-1$
  String TOOL_EAB_CREATE_CONSTRAINT = "Constraint"; //$NON-NLS-1$
  String TOOL_EAB_CREATE_CONSTRAINT_ELEMENT = "ConstraintElement"; //$NON-NLS-1$
  String TOOL_EAB_INSERT_REMOVE_CONSTRAINTS = "Constraints"; //$NON-NLS-1$
  // Unsynchronized tools
  String TOOL_EAB_INSERT_REMOVE_REALIZED_PHYSICAL_ARTIFACTS = "Realized Physical Artifacts"; //$NON-NLS-1$
  // multi parts tool
  String TOOL_EAB_REUSE_CONFIGURATION_ITEM = "Reuse Configuration Item"; //$NON-NLS-1$

  String TOOL_EAB_SHOW_HIDE_CONFIGURATION_ITEMS = "Show/Hide Configuration Items"; //$NON-NLS-1$
  String TOOL_EAB_SHOW_HIDE_REALIZED_PHYSICAL_ARTIFACTS = "Show/Hide Realized Physical Artifacts"; //$NON-NLS-1$
  String TOOL_EAB_SHOW_HIDE_CONSTRAINTS = "Show/Hide Constraints"; //$NON-NLS-1$

  // ES Tools - Exchange Scenario
  String TOOL_ES_CREATE_NODE_PC = "Node PC"; //$NON-NLS-1$
  String TOOL_ES_CREATE_BEHAVIOR_PC = "Behavior PC"; //$NON-NLS-1$
  String TOOL_ES_CREATE_COMPONENT = "Component"; //$NON-NLS-1$
  String TOOL_ES_CREATE_ACTOR = "Actor"; //$NON-NLS-1$ -
  String TOOL_ES_INSERT_REMOVE_COMPONENTS = "Components"; //$NON-NLS-1$
  String TOOL_ES_INSERT_ACTOR = "Actors"; //$NON-NLS-1$
  String TOOL_ES_CREATE_FUNCTIONAL_EXCHANGE_WITH_RETURN_BRANCH = "Functional Exchange with Return Branch"; //$NON-NLS-1$
  String TOOL_ES_CREATE_FUNCTIONAL_EXCHANGE = "Functional Exchange"; //$NON-NLS-1$
  String TOOL_ES_CREATE_FOUND_FUNCTIONAL_EXCHANGE = "Found Functional Exchange"; //$NON-NLS-1$
  String TOOL_ES_INSERT_REMOVE_FUNCTION = "Allocated Function"; //$NON-NLS-1$
  String TOOL_ES_INSERT_REMOVE_STATE_MODE = "Involved State / Mode"; //$NON-NLS-1$
  String TOOL_ES_CREATE_REFERENCE = "Reference"; //$NON-NLS-1$
  String TOOL_ES_CREATE_LOOP = "LOOP"; //$NON-NLS-1$
  String TOOL_ES_CREATE_ALT = "ALT"; //$NON-NLS-1$
  String TOOL_ES_CREATE_PAR = "PAR"; //$NON-NLS-1$
  String TOOL_ES_CREATE_OTHER_COMBINED_FRAGMENT = "Other Combined Fragment"; //$NON-NLS-1$
  String TOOL_ES_CREATE_OPERAND = "Operand"; //$NON-NLS-1$
  String TOOL_ES_CREATE_COMPONENT_EXCHANGE_WITH_RETURN_BRANCH = "Component Exchange with Return Branch"; //$NON-NLS-1$
  String TOOL_ES_CREATE_COMPONENT_EXCHANGE = "Component Exchange"; //$NON-NLS-1$
  String TOOL_ES_LOST_COMPONENT_EXCHANGE = "Lost Component Exchange"; //$NON-NLS-1$
  String TOOL_ES_FOUND_COMPONENT_EXCHANGE = "Found Component Exchange"; //$NON-NLS-1$
  String TOOL_ES_LOST_FUNCTIONAL_EXCHANGE = "Lost Functional Exchange"; //$NON-NLS-1$
  String TOOL_ES_CREATE_CONSTRAINT = "Constraint"; //$NON-NLS-1$
  String TOOL_ES_CREATE_CONSTRAINTELEMENT = "ConstraintElement"; //$NON-NLS-1$
  String TOOL_ES_INSERT_REMOVE_CONSTRAINTS = "Constraints"; //$NON-NLS-1$
  String TOOL_ES_CREATE_ARM_TIMER = "Arm timer"; //$NON-NLS-1$
  String TOOL_ES_CREATE_CANCEL_TIMER = "Cancel timer"; //$NON-NLS-1$
  String TOOL_ES_DURATION = "Duration"; //$NON-NLS-1$
  String TOOL_ES_EXCHANGE_CONTEXT = "Exchange Context"; //$NON-NLS-1$

  // id tools
  String TOOL_ES_CREATE_CONSTRAINTELEMENTSCENARIO = "ConstraintElementScenario"; //$NON-NLS-1$
  String TOOL_ES_CREATE_FUNCTION_STATE = "Function state"; //$NON-NLS-1$
  String TOOL_ES_CREATE_STATE_MODE = "State / Mode"; //$NON-NLS-1$

  // FCD tools - Functional Chain Description
  String TOOL_FCD_INSERT_REMOVE_INVOLVE_FUNCTION = "Involve Function"; //$NON-NLS-1$
  String TOOL_FCD_INSERT_REMOVE_INVOLVE_FUNCTIONAL_EXCHANGE = "Involve Functional Exchange"; //$NON-NLS-1$
  String TOOL_FCD_INSERT_REMOVE_INVOLVE_FUNCTIONAL_CHAIN = "Involve Functional Chain"; //$NON-NLS-1$
  String TOOL_FCD_INSERT_REMOVE_INVOLVE_FUNCTIONAL_EXCHANGE_AND_TARGET_FUNCTION = "Involve Functional Exchange & Target Function"; //$NON-NLS-1$
  String TOOL_FCD_INSERT_REMOVE_INVOLVE_FUNCTIONAL_EXCHANGE_AND_TARGET_FUNCTIONAL_CHAIN = "Involve Functional Exchange & Target Functional Chain"; //$NON-NLS-1$
  String TOOL_FCD_CREATE_CONSTRAINT = "Constraint"; //$NON-NLS-1$
  String TOOL_FCD_CREATE_CONSTRAINTELEMENT = "ConstraintElement"; //$NON-NLS-1$
  String TOOL_FCD_INSERT_REMOVE_CONSTRAINTS = "Constraints"; //$NON-NLS-1$
  // Accelerators
  String TOOL_FCD_INITIALIZATION_FROM_EXISTING_DIAGRAM = "Initialization from existing diagram"; //$NON-NLS-1$

  // FS tools - Function Scenario
  String TOOL_FS_CREATE_SYSTEM_FUNCTION = "System Function"; //$NON-NLS-1$
  String TOOL_FS_CREATE_LOGICAL_FUNCTION = "Logical Function"; //$NON-NLS-1$
  String TOOL_FS_CREATE_PHYSICAL_FUNCTION = "Physical Function"; //$NON-NLS-1$
  String TOOL_FS_CREATE_DUPLICATE = "Duplicate"; //$NON-NLS-1$
  String TOOL_FS_CREATE_GATHER = "Gather"; //$NON-NLS-1$
  String TOOL_FS_CREATE_ROUTE = "Route"; //$NON-NLS-1$
  String TOOL_FS_CREATE_SELECT = "Select"; //$NON-NLS-1$
  String TOOL_FS_CREATE_SPLIT = "Split"; //$NON-NLS-1$
  String TOOL_FS_INSERT_REMOVE_FUNCTIONS = "Functions"; //$NON-NLS-1$
  String TOOL_FS_CREATE_FUNCTIONAL_EXCHANGE_WITH_RETURN_BRANCH = "Functional Exchange with return branch"; //$NON-NLS-1$
  String TOOL_FS_CREATE_FUNCTIONAL_EXCHANGE = "Functional Exchange"; //$NON-NLS-1$
  String TOOL_FS_CREATE_REFERENCE = "Reference"; //$NON-NLS-1$
  String TOOL_FS_CREATE_LOOP = "LOOP"; //$NON-NLS-1$
  String TOOL_FS_CREATE_ALT = "ALT"; //$NON-NLS-1$
  String TOOL_FS_CREATE_PAR = "PAR"; //$NON-NLS-1$
  String TOOL_FS_CREATE_OTHER_COMBINED_FRAGMENT = "Other Combined Fragment"; //$NON-NLS-1$
  String TOOL_FS_CREATE_OPERAND = "Operand"; //$NON-NLS-1$
  String TOOL_FS_INSERT_REMOVE_STATE_MODE = "Involved State / Mode"; //$NON-NLS-1$
  String TOOL_FS_CREATE_CONSTRAINT = "Constraint"; //$NON-NLS-1$
  String TOOL_FS_CREATE_CONSTRAINTELEMENT = "ConstraintElement"; //$NON-NLS-1$
  String TOOL_FS_INSERT_REMOVE_CONSTRAINTS = "Constraints"; //$NON-NLS-1$
  String TOOL_FS_DURATION = "Duration"; //$NON-NLS-1$
  String TOOL_FS_EXCHANGE_CONTEXT = "Exchange Context"; //$NON-NLS-1$

  // ID tool
  String TOOL_FS_CREATE_CONSTRAINTELEMENTSCENARIO = "ConstraintElementScenario"; //$NON-NLS-1$
  String TOOL_FS_CREATE_STATE_MODE = "State / Mode"; //$NON-NLS-1$

  // ID tools - Interface Diagram
  String TOOL_ID_CREATE_EVENT = "Event"; //$NON-NLS-1$
  String TOOL_ID_CREATE_OPERATION = "Operation"; //$NON-NLS-1$
  String TOOL_ID_CREATE_FLOW = "Flow"; //$NON-NLS-1$
  String TOOL_ID_CREATE_DATA = "Data"; //$NON-NLS-1$
  String TOOL_ID_CREATE_UNDEFINED_EXCHANGE_ITEM = "Undefined Exchange Item"; //$NON-NLS-1$
  String TOOL_ID_CREATE_EXCHANGEITEMELEMENT = "ExchangeItemElement"; //$NON-NLS-1$
  String TOOL_ID_INSERT_REMOVE_EXCHANGE_ITEM_ALLOCATIONS = "Exchange Item Allocations"; //$NON-NLS-1$
  String TOOL_ID_CREATE_CONSTRAINT = "Constraint"; //$NON-NLS-1$
  String TOOL_ID_CREATE_CONSTRAINTELEMENT = "ConstraintElement"; //$NON-NLS-1$
  String TOOL_ID_INSERT_REMOVE_CONSTRAINTS = "Constraints"; //$NON-NLS-1$

  // IDB tools - Interfaces Diagram Blank
  String TOOL_IDB_CREATE_COMPONENT = "Component"; //$NON-NLS-1$
  String TOOL_IDB_REUSE_COMPONENT = "Reuse Component"; //$NON-NLS-1$
  String TOOL_IDB_CREATE_ACTOR = "Actor"; //$NON-NLS-1$
  String TOOL_IDB_CREATE_IN_FLOW_PORT = "In Flow Port"; //$NON-NLS-1$
  String TOOL_IDB_CREATE_OUT_FLOW_PORT = "Out Flow Port"; //$NON-NLS-1$
  String TOOL_IDB_CREATE_INOUT_FLOW_PORT = "InOut Flow Port"; //$NON-NLS-1$
  String TOOL_IDB_CREATE_STANDARD_PORT = "Standard Port"; //$NON-NLS-1$
  String TOOL_IDB_CREATE_INTERFACE = "Interface"; //$NON-NLS-1$
  String TOOL_IDB_CREATE_IMPLEMENTS = "Implements"; //$NON-NLS-1$
  String TOOL_IDB_CREATE_USES = "Uses"; //$NON-NLS-1$
  String TOOL_IDB_CREATE_PROVIDES = "Provides"; //$NON-NLS-1$
  String TOOL_IDB_CREATE_REQUIRES = "Requires"; //$NON-NLS-1$
  String TOOL_IDB_CREATE_DELEGATION = "Delegation"; //$NON-NLS-1$
  String TOOL_IDB_CREATE_GENERALIZATION = "Generalization"; //$NON-NLS-1$
  String TOOL_IDB_CREATE_EVENT = "Event"; //$NON-NLS-1$
  String TOOL_IDB_CREATE_OPERATION = "Operation"; //$NON-NLS-1$
  String TOOL_IDB_CREATE_FLOW = "Flow"; //$NON-NLS-1$
  String TOOL_IDB_CREATE_DATA = "Data"; //$NON-NLS-1$
  String TOOL_IDB_CREATE_UNDEFINED_EXCHANGE_ITEM = "Undefined Exchange Item"; //$NON-NLS-1$
  String TOOL_IDB_CREATE_EXCHANGE_ITEM_ELEMENT = "Exchange Item Element"; //$NON-NLS-1$
  String TOOL_IDB_CREATE_TRANSMIT = "Transmit"; //$NON-NLS-1$
  String TOOL_IDB_CREATE_ACQUIRE = "Acquire"; //$NON-NLS-1$
  String TOOL_IDB_MANAGE_EXCHANGE_ITEM_ALLOCATIONS = "Manage Exchange Item Allocations"; //$NON-NLS-1$
  String TOOL_IDB_INSERT_REMOVE_COMPONENTS = "Show/Hide Components"; //$NON-NLS-1$
  String TOOL_IDB_INSERT_REMOVE_ACTORS = "Actors"; //$NON-NLS-1$
  String TOOL_IDB_INSERT_REMOVE_INTERFACES = "Interfaces"; //$NON-NLS-1$
  String TOOL_IDB_INSERT_REMOVE_RELATIONSHIPS = "Show/Hide Relationships"; //$NON-NLS-1$
  String TOOL_IDB_CREATE_CONSTRAINT = "Constraint"; //$NON-NLS-1$
  String TOOL_IDB_CREATE_CONSTRAINTELEMENT = "ConstraintElement"; //$NON-NLS-1$
  String TOOL_IDB_INSERT_REMOVE_CONSTRAINTS = "Constraints"; //$NON-NLS-1$

  String TOOL_IDB_INSERT_REMOVE_COMPONENTS__LABEL = "Components"; //$NON-NLS-1$
  String TOOL_IDB_INSERT_REMOVE_RELATIONSHIPS__LABEL = "Relationships"; //$NON-NLS-1$

  // Accelerators
  String TOOL_IDB_INITIALIZATION_FROM_EXISTING_DIAGRAM = "Initialization from existing diagram"; //$NON-NLS-1$
  // Unsynchronized tools
  String TOOL_IDB_INSERT_REMOVE_COMPONENT_PORTS = "Component Ports"; //$NON-NLS-1$
  String TOOL_IDB_INSERT_REMOVE_EXCHANGE_ITEM_ALLOCATIONS = "Exchange Item Allocations"; //$NON-NLS-1$
  String TOOL_IDB_INSERT_REMOVE_EXCHANGE_ITEM = "Exchange Item"; //$NON-NLS-1$
  String TOOL_IDB_INSERT_REMOVE_EXCHANGE_ITEM_ELEMENTS = "Exchange Item Elements"; //$NON-NLS-1$
  String TOOL_IDB_INSERT_REMOVE_COMMUNICATION_LINKS = "Communication Links"; //$NON-NLS-1$

  // tools id
  String TOOL_IDB_SHOW_HIDE_ACTORS = "Show/Hide Actors";//$NON-NLS-1$
  String TOOL_IDB_SHOW_HIDE_INTERFACES = "Show/Hide Interfaces";//$NON-NLS-1$
  String TOOL_IDB_SHOW_HIDE_EXCHANGE_ITEMS = "Show/Hide Exchange Items";//$NON-NLS-1$
  String TOOL_IDB_SHOW_HIDE_COMPONENT_PORTS = "Show/Hide Component Ports";//$NON-NLS-1$
  String TOOL_IDB_SHOW_HIDE_RELATIONSHIPS = "Show/Hide Relationships";//$NON-NLS-1$
  String TOOL_IDB_SHOW_HIDE_COMMUNICATIONSLINKS = "Show/Hide CommunicationLinks";//$NON-NLS-1$
  String TOOL_IDB_SHOW_HIDE_EXCHANGE_ITEM_ALLOCATIONS = "Show/Hide Exchange Item Allocations";//$NON-NLS-1$
  String TOOL_IDB_SHOW_HIDE_EXCHANGE_ITEM_ELEMENTS = "Show/Hide Exchange Item Elements";//$NON-NLS-1$
  String TOOL_IDB_CREATE_COMMUNICATIONLINK_TRANSMIT = "CommunicationLink Send / Produce / Call / Write / Transmit";//$NON-NLS-1$
  String TOOL_IDB_CREATE_COMMUNICATIONLINK_ACQUIRE = "CommunicationLink Receive / Consume / Execute / Access / Acquire";//$NON-NLS-1$
  String TOOL_IDB_RECONNECT_IMPLEMENTS_TARGET = "implements target";//$NON-NLS-1$
  String TOOL_IDB_RECONNECT_USES_TARGET = "uses target";//$NON-NLS-1$
  String TOOL_IDB_RECONNECT_PINPROVIDED_SOURCE = "implements-pinprovided source";//$NON-NLS-1$
  String TOOL_IDB_RECONNECT_PINPROVIDEDINTERFACE_TARGET = "PinProvidedInterface target";//$NON-NLS-1$
  String TOOL_IDB_RECONNECT_PINREQUIRED_SOURCE = "used-pinrequired source";//$NON-NLS-1$
  String TOOL_IDB_RECONNECT_PINREQUIREDINTERFACE_TARGET = "PinRequiredInterface target";//$NON-NLS-1$
  String TOOL_IDB_RECONNECT_GENERALIZATION_SOURCE = "CB Reconnect Generalization Source";//$NON-NLS-1$
  String TOOL_IDB_RECONNECT_GENERALIZATION_TARGET = "CB Reconnect Generalization Target";//$NON-NLS-1$
  String TOOL_IDB_RECONNECT_COMMUNICATIONLINK_SOURCE = "CommunicationLink source";//$NON-NLS-1$
  String TOOL_IDB_RECONNECT_COMMUNICATIONLINK_TARGET = "CommunicationLink target";//$NON-NLS-1$

  // Interface Scenario Diagram Tools
  String TOOL_IS_CREATE_ACTOR = "Actor"; //$NON-NLS-1$
  String TOOL_IS_CREATE_COMPONENT = "Component"; //$NON-NLS-1$
  String TOOL_IS_INSERT_REMOVE_ACTORS = "Actors"; //$NON-NLS-1$
  String TOOL_IS_CREATE_MESSAGE = "Create message"; //$NON-NLS-1$
  String TOOL_IS_DELETE_MESSAGE = "Delete message"; //$NON-NLS-1$
  String TOOL_IS_FOUND_MESSAGE = "Found Message"; //$NON-NLS-1$
  String TOOL_IS_LOST_MESSAGE = "Lost Message"; //$NON-NLS-1$
  String TOOL_IS_SYNCHRONOUS_MESSAGE = "Sequence Message with Return Branch"; //$NON-NLS-1$
  String TOOL_IS_ASYNCHRONOUS_MESSAGE = "Sequence Message"; //$NON-NLS-1$
  String TOOL_IS_INSERT_REMOVE_SHARED_DATA_EVENT = "Shared Data/Event "; //$NON-NLS-1$
  String TOOL_IS_INSERT_REMOVE_STATE_MODE = "Involved State / Mode"; //$NON-NLS-1$
  String TOOL_IS_CREATE_REFERENCE = "Reference"; //$NON-NLS-1$
  String TOOL_IS_CREATE_LOOP = "LOOP"; //$NON-NLS-1$
  String TOOL_IS_CREATE_ALT = "ALT"; //$NON-NLS-1$
  String TOOL_IS_CREATE_PAR = "PAR"; //$NON-NLS-1$
  String TOOL_IS_CREATE_OTHER_COMBINED_FRAGMENT = "Other Combined Fragment"; //$NON-NLS-1$
  String TOOL_IS_CREATE_OPERAND = "Operand"; //$NON-NLS-1$
  String TOOL_IS_CREATE_CONSTRAINT = "Constraint"; //$NON-NLS-1$
  String TOOL_IS_CREATE_CONSTRAINTELEMENT = "ConstraintElement"; //$NON-NLS-1$
  String TOOL_IS_INSERT_REMOVE_CONSTRAINTS = "Constraints"; //$NON-NLS-1$
  String TOOL_IS_CREATE_COTS = "COTS"; //$NON-NLS-1$
  String TOOL_IS_CREATE_CS = "CS"; //$NON-NLS-1$
  String TOOL_IS_CREATE_HW = "HW"; //$NON-NLS-1$
  String TOOL_IS_CREATE_INTERFACE = "Interface"; //$NON-NLS-1$
  String TOOL_IS_CREATE_NDI = "NDI"; //$NON-NLS-1$
  String TOOL_IS_CREATE_PRIME_ITEM = "Prime Item"; //$NON-NLS-1$
  String TOOL_IS_CREATE_SYSTEM = "System"; //$NON-NLS-1$
  String TOOL_IS_INSERT_REMOVE_COMPONENTS = "Components"; //$NON-NLS-1$
  String TOOL_IS_INSERT_REMOVE_FUNCTION = "Allocated Function"; //$NON-NLS-1$
  String TOOL_IS_CREATE_ARM_TIMER = "Arm timer"; //$NON-NLS-1$
  String TOOL_IS_CREATE_CANCEL_TIMER = "Cancel timer"; //$NON-NLS-1$
  String TOOL_IS_DURATION = "Duration"; //$NON-NLS-1$
  String TOOL_IS_EXCHANGE_CONTEXT = "Exchange Context"; //$NON-NLS-1$
  String TOOL_IS_EI_EXCHANGE_CONTEXT = "Exchange Context"; //$NON-NLS-1$

  // id tools
  String TOOL_IS_CREATE_CONSTRAINTELEMENTSCENARIO = "ConstraintElementScenario"; //$NON-NLS-1$
  String TOOL_IS_CREATE_STATE_MODE = "State / Mode"; //$NON-NLS-1$

  // LAB tools
  // Components
  String TOOL_LAB_CREATE_COMPONENT = "Logical Component"; //$NON-NLS-1$
  String TOOL_LAB_CREATE_LOGICAL_ACTOR = "Logical Actor"; //$NON-NLS-1$
  String TOOL_LAB_CREATE_IN_FLOW_PORT_PORT = "In Flow Port"; //$NON-NLS-1$
  String TOOL_LAB_CREATE_OUT_FLOW_PORT_PORT = "Out Flow Port"; //$NON-NLS-1$
  String TOOL_LAB_CREATE_INOUT_FLOW_PORT_PORT = "InOut Flow Port"; //$NON-NLS-1$
  String TOOL_LAB_CREATE_STANDARD_PORT_PORT = "Standard Port"; //$NON-NLS-1$
  String TOOL_LAB_CREATE_COMPONENT_EXCHANGE = "Component Exchange"; //$NON-NLS-1$
  String TOOL_LAB_CREATE_COMPONENT_EXCHANGE_WITH_DELEGATIONS = "Component Exchange with Delegations"; //$NON-NLS-1$
  String TOOL_LAB_CREATE_DELEGATION = "Delegation"; //$NON-NLS-1$
  String TOOL_LAB_CREATE_PHYSICAL_LINK = "Physical Link"; //$NON-NLS-1$
  String TOOL_LAB_CREATE_PHYSICAL_PORT = "Physical Port"; //$NON-NLS-1$
  String TOOL_LAB_INSERT_REMOVE_COMPONENTS = "Components"; //$NON-NLS-1$
  String TOOL_LAB_INSERT_REMOVE_PHYSICAL_LINKS = "Physical Links"; //$NON-NLS-1$
  String TOOL_LAB_INSERT_REMOVE_PHYSICAL_PATH = "Physical Path"; //$NON-NLS-1$
  String TOOL_LAB_INSERT_REMOVE_ACTORS = "Actors"; //$NON-NLS-1$
  String TOOL_LAB_INSERT_REMOVE_COMPONENT_EXCHANGES = "Component Exchanges"; //$NON-NLS-1$
  String TOOL_LAB_CREATE_CONSTRAINT = "Constraint"; //$NON-NLS-1$
  String TOOL_LAB_INSERT_REMOVE_PHYSICAL_LINKS_CATEGORIES = "Switch Physical Links / Categories"; //$NON-NLS-1$
  String TOOL_LAB_CREATE_CONSTRAINT_ELEMENT = "ConstraintElement"; //$NON-NLS-1$
  String TOOL_LAB_INSERT_REMOVE_CONSTRAINTS = "Constraints"; //$NON-NLS-1$
  String TOOL_LAB_INSERT_ACTOR = "Show/Hide Actors"; //$NON-NLS-1$
  // Functions
  String TOOL_LAB_CREATE_LOGICAL_FUNCTION = "Logical Function"; //$NON-NLS-1$
  String TOOL_LAB_CREATE_DUPLICATE = "Duplicate"; //$NON-NLS-1$
  String TOOL_LAB_CREATE_GATHER = "Gather"; //$NON-NLS-1$
  String TOOL_LAB_CREATE_ROUTE = "Route"; //$NON-NLS-1$
  String TOOL_LAB_CREATE_SELECT = "Select"; //$NON-NLS-1$
  String TOOL_LAB_CREATE_SPLIT = "Split"; //$NON-NLS-1$
  String TOOL_LAB_CREATE_FUNCTIONAL_EXCHANGE = "Functional Exchange"; //$NON-NLS-1$
  String TOOL_LAB_CREATE_INPUT_PORT = "Input Port"; //$NON-NLS-1$
  String TOOL_LAB_CREATE_OUTPUT_PORT = "Output Port"; //$NON-NLS-1$
  String TOOL_LAB_CREATE_PORT_ALLOCATION = "Port Allocation"; //$NON-NLS-1$
  String TOOL_LAB_MANAGE_FUNCTION_ALLOCATION = "Manage Function Allocation"; //$NON-NLS-1$
  String TOOL_LAB_INSERT_REMOVE_ALLOCATED_FUNCTIONS = "Allocated Functions"; //$NON-NLS-1$
  String TOOL_LAB_INSERT_REMOVE_ALL_ALLOCATED_FUNCTIONS = "All Allocated Functions"; //$NON-NLS-1$
  String TOOL_LAB_INSERT_REMOVE_FUNCTIONAL_CHAINS = "Functional Chains"; //$NON-NLS-1$
  String TOOL_LAB_INSERT_REMOVE_FUNCTIONAL_EXCHANGES = "Functional Exchanges"; //$NON-NLS-1$
  String TOOL_LAB_INSERT_REMOVE_EXCHANGE_CATEGORIES = "Switch Functional Exchanges / Categories"; //$NON-NLS-1$
  String TOOL_LAB_INSERT_SCENARIO_ELEMENTS = "Scenario Elements"; //$NON-NLS-1$
  String TOOL_LAB_INSERT_STATEMODE_ELEMENTS = "State-Mode Elements"; //$NON-NLS-1$

  String TOOL_LAB_INSERT_FUNCTIONS_FROM_MODE_STATE = "Functions from Mode / State"; //$NON-NLS-1$
  String TOOL_LAB_INSERT_ELEMENTS_FROM_SCENARIO = "Elements from Scenario"; //$NON-NLS-1$
  String TOOL_LAB_STATE_MODE_ELEMENTS_ID = "State-Mode Elements"; //$NON-NLS-1$
  String TOOL_LAB_SCENARIO_ELEMENTS_ID = "Scenario Elements"; //$NON-NLS-1$
  String TOOL_LAB_INSERT_REMOVE_CATEGORIES = "Categories"; //$NON-NLS-1$
  String TOOL_LAB_INSERT_REMOVE_COMPONENT_EXCHANGES_CATEGORIES = "Switch Component Exchanges / Categories"; //$NON-NLS-1$
  // Accelerators
  String TOOL_LAB_INITIALIZATION_FROM_EXISTING_DIAGRAM = "Initialization from existing diagram"; //$NON-NLS-1$
  // Unsynchronized tools
  String TOOL_LAB_INSERT_REMOVE_FUNCTION_PORTS = "Function Ports"; //$NON-NLS-1$
  String TOOL_LAB_INSERT_REMOVE_PORTS = "Ports"; //$NON-NLS-1$
  // Multi parts tools
  String TOOL_LAB_REUSE_LOGICAL_COMPONENT = "Reuse Logical Component"; //$NON-NLS-1$
  String TOOL_LAB_REUSE_LOGICAL_ACTOR = "Reuse Logical Actor"; //$NON-NLS-1$
  String TOOL_LAB_CREATE_COMPONENT_EXCHANGE_WITH_PORTS = "Component Exchange with ports"; //$NON-NLS-1$
  String TOOL_LAB_CREATE_COMPONENT_EXCHANGE_WITHOU_PORTS = "Component Exchange without ports"; //$NON-NLS-1$
  String TOOL_LAB_CREATE_COMPONENT_EXCHANGE_BETWEEN_TYPES = "Component Exchange between types"; //$NON-NLS-1$

  // tools id
  String TOOL_LAB_DELETE_ELEMENT = "delete capella element"; //$NON-NLS-1$
  String TOOL_LAB_SHOW_HIDE_COMPONENT_EXCHANGES = "Show/Hide Connections"; //$NON-NLS-1$
  String TOOL_LAB_SHOW_HIDE_PORTS = "Show/Hide Ports"; //$NON-NLS-1$
  String TOOL_LAB_SHOW_HIDE_FUNCTION_PORTS = "Show/Hide Function Ports"; //$NON-NLS-1$
  String TOOL_LAB_SHOW_HIDE_FUNCTIONAL_EXCHANGES = "Show/Hide Functional Exchanges"; //$NON-NLS-1$
  String TOOL_LAB_SHOW_HIDE_FUNCTIONAL_CHAINS = "Show/Hide Functional Chains"; //$NON-NLS-1$
  String TOOL_LAB_SHOW_HIDE_CONSTRAINTS = "Show/Hide Constraints"; //$NON-NLS-1$
  String TOOL_LAB_CREATE_FUNCTIONAL_CHAIN = "Functional Chain"; //$NON-NLS-1$
  String TOOL_LAB_CREATE_CONNECTION_WITH_PORTS = "Connection with ports"; //$NON-NLS-1$
  String TOOL_LAB_RECONNECT_CONNECTION_SOURCE = "Reconnect Connection Source"; //$NON-NLS-1$
  String TOOL_LAB_RECONNECT_CONNECTION_TARGET = "Reconnect Connection Target"; //$NON-NLS-1$
  String TOOL_LAB_RECONNECT_FUNCTION_EXCHANGE = "LAB Reconnect Function Exchanges"; //$NON-NLS-1$
  String TOOL_LAB_CREATE_OUTFLOW_PORT = "Out Flow Port"; //$NON-NLS-1$
  String TOOL_LAB_CREATE_INFLOW_PORT = "In Flow Port"; //$NON-NLS-1$

  // LCBD tools - Logical Component Breakdown
  String TOOL_LCBD_CREATE_LOGICAL_COMPONENT = "Logical Component"; //$NON-NLS-1$
  String TOOL_LCBD_CONTAINED_IN = "Contained In"; //$NON-NLS-1$
  String TOOL_LCBD_CREATE_CONSTRAINT = "Constraint"; //$NON-NLS-1$
  String TOOL_LCBD_CREATE_CONSTRAINTELEMENT = "ConstraintElement"; //$NON-NLS-1$
  String TOOL_LCBD_INSERT_REMOVE_CONSTRAINTS = "Constraints"; //$NON-NLS-1$
  // multi part tool
  String TOOL_LCBD_CREATE_PART = "Create Part"; //$NON-NLS-1$

  // LCCDI - Logical Contextual Component Detailed Interfaces tools
  String TOOL_LCCDI_CREATE_ECHANGE_ITEM_EVENT = "Event"; //$NON-NLS-1$
  String TOOL_LCCDI_CREATE_ECHANGE_ITEM_OPERATION = "Operation"; //$NON-NLS-1$
  String TOOL_LCCDI_CREATE_ECHANGE_ITEM_FLOW = "Flow"; //$NON-NLS-1$
  String TOOL_LCCDI_CREATE_ECHANGE_ITEM_DATA = "Data"; //$NON-NLS-1$
  String TOOL_LCCDI_CREATE_INTERFACE = "Interface"; //$NON-NLS-1$
  String TOOL_LCCDI_CREATE_COMMUNICATION_LINK_TRANSMIT = "CommunicationLink Send / Produce / Call / Write / Transmit"; //$NON-NLS-1$
  String TOOL_LCCDI_CREATE_COMMUNICATION_LINK_ACQUIRE = "CommunicationLink Receive / Consume / Execute / Access / Acquire"; //$NON-NLS-1$
  String TOOL_LCCDI_CREATE_USES = "Uses"; //$NON-NLS-1$
  String TOOL_LCCDI_CREATE_PROVIDES = "Provides"; //$NON-NLS-1$
  String TOOL_LCCDI_CREATE_REQUIRES = "Requires"; //$NON-NLS-1$
  String TOOL_LCCDI_CREATE_GENERALIZATION = "Generalization"; //$NON-NLS-1$
  String TOOL_LCCDI_CREATE_STANDARD_PORT = "Standard Port"; //$NON-NLS-1$
  String TOOL_LCCDI_CREATE_IMPLEMENTS = "Implements"; //$NON-NLS-1$

  // Logical system - Contextual Component External Interfaces (LCCEI) tools
  String TOOL_LCCEI_CREATE_ACTOR = "Actor"; //$NON-NLS-1$
  String TOOL_LCCEI_CREATE_ECHANGE_ITEM_EVENT = "Event"; //$NON-NLS-1$
  String TOOL_LCCEI_CREATE_ECHANGE_ITEM_OPERATION = "Operation"; //$NON-NLS-1$
  String TOOL_LCCEI_CREATE_ECHANGE_ITEM_FLOW = "Flow"; //$NON-NLS-1$
  String TOOL_LCCEI_CREATE_ECHANGE_ITEM_DATA = "Data"; //$NON-NLS-1$
  String TOOL_LCCEI_CREATE_UNDEFINED_ECHANGE_ITEM = "Undefined Exchange Item"; //$NON-NLS-1$
  String TOOL_LCCEI_CREATE_INTERFACE = "Interface"; //$NON-NLS-1$
  String TOOL_LCCEI_CREATE_COMMUNICATION_LINK_TRANSMIT = "CommunicationLink Send / Produce / Call / Write / Transmit"; //$NON-NLS-1$
  String TOOL_LCCEI_CREATE_COMMUNICATION_LINK_ACQUIRE = "CommunicationLink Receive / Consume / Execute / Access / Acquire"; //$NON-NLS-1$
  String TOOL_LCCEI_SHOW_HIDE_ACTORS = "Show/Hide Actors"; //$NON-NLS-1$
  String TOOL_LCCEI_SHOW_HIDE_EXCHANGE_ITEMS = "Show/Hide Exchange Items"; //$NON-NLS-1$
  String TOOL_LCCEI_CREATE_LOGICAL_COMPONENT = "Component"; //$NON-NLS-1$
  String TOOL_LCCEI_CREATE_USES = "Uses"; //$NON-NLS-1$
  String TOOL_LCCEI_CREATE_PROVIDES = "Provides"; //$NON-NLS-1$
  String TOOL_LCCEI_CREATE_REQUIRES = "Requires"; //$NON-NLS-1$
  String TOOL_LCCEI_CREATE_GENERALIZATION = "Generalization"; //$NON-NLS-1$
  String TOOL_LCCEI_CREATE_INOUT_FLOW_PORT_PORT = "InOut Flow Port"; //$NON-NLS-1$
  String TOOL_LCCEI_CREATE_STANDARD_PORT = "Standard Port"; //$NON-NLS-1$
  String TOOL_LCCEI_CREATE_PORT_DELEGATION = "Port Delegation"; //$NON-NLS-1$
  String TOOL_LCCEI_CREATE_IMPLEMENTS = "Implements"; //$NON-NLS-1$
  String TOOL_LCCEI_CREATE_EXCHANGE_ITEM_ALLOCATION = "Exchange Item Allocation"; //$NON-NLS-1$
  String TOOL_LCCEI_SHOW_HIDE_INTERFACES = "Show/Hide Interfaces"; //$NON-NLS-1$

  // LCCII - Logical Contextual Component Internal Interfaces tools
  String TOOL_LCCII_CREATE_LOGICAL_COMPONENT = "Component"; //$NON-NLS-1$
  String TOOL_LCCII_REUSE_COMPONENT = "Reuse Component"; //$NON-NLS-1$
  String TOOL_LCCII_CREATE_IN_FLOW_PORT = "In Flow Port"; //$NON-NLS-1$
  String TOOL_LCCII_CREATE_OUT_FLOW_PORT = "Out Flow Port"; //$NON-NLS-1$
  String TOOL_LCCII_CREATE_INOUT_FLOW_PORT_PORT = "InOut Flow Port"; //$NON-NLS-1$
  String TOOL_LCCII_CREATE_STANDARD_PORT = "Standard Port"; //$NON-NLS-1$
  String TOOL_LCCII_CREATE_INTERFACE = "Interface"; //$NON-NLS-1$
  String TOOL_LCCII_CREATE_IMPLEMENTS = "Implements"; //$NON-NLS-1$
  String TOOL_LCCII_CREATE_USES = "Uses"; //$NON-NLS-1$
  String TOOL_LCCII_CREATE_PROVIDES = "Provides"; //$NON-NLS-1$
  String TOOL_LCCII_CREATE_REQUIRES = "Requires"; //$NON-NLS-1$
  // the following constant is the label of "Port Delegation" tool
  String TOOL_LCCII_CREATE_DELEGATION = "Delegation"; //$NON-NLS-1$
  String TOOL_LCCII_CREATE_GENERALIZATION = "Generalization"; //$NON-NLS-1$
  String TOOL_LCCII_CREATE_ECHANGE_ITEM_EVENT = "Event"; //$NON-NLS-1$
  String TOOL_LCCII_CREATE_ECHANGE_ITEM_OPERATION = "Operation"; //$NON-NLS-1$
  String TOOL_LCCII_CREATE_ECHANGE_ITEM_FLOW = "Flow"; //$NON-NLS-1$
  String TOOL_LCCII_CREATE_ECHANGE_ITEM_DATA = "Data"; //$NON-NLS-1$
  String TOOL_LCCII_CREATE_UNDEFINED_EXCHANGE_ITEM = "Undefined Exchange Item"; //$NON-NLS-1$
  String TOOL_LCCII_CREATE_EXCHANGE_ITEM_ALLOCATION = "Exchange Item Allocation"; //$NON-NLS-1$
  String TOOL_LCCII_CREATE_TRANSMIT = "Transmit"; //$NON-NLS-1$
  String TOOL_LCCII_CREATE_ACQUIRE = "Acquire"; //$NON-NLS-1$
  String TOOL_LCCII_INSERT_REMOVE_COMPONENTS = "Components"; //$NON-NLS-1$
  String TOOL_LCCII_INSERT_REMOVE_INTERFACES = "Interfaces"; //$NON-NLS-1$
  String TOOL_LCCII_INSERT_REMOVE_EXCHANGE_ITEMS = "Exchange Items"; //$NON-NLS-1$
  String TOOL_LCCII_CREATE_CONSTRAINT = "Constraint"; //$NON-NLS-1$
  String TOOL_LCCII_CREATE_CONSTRAINTELEMENT = "ConstraintElement"; //$NON-NLS-1$
  String TOOL_LCCII_INSERT_REMOVE_CONSTRAINTS = "Constraints"; //$NON-NLS-1$
  // the ID of "Port Delegation" tool
  String TOOL_LCCII_CREATE_PORT_DELEGATION = "Port Delegation"; //$NON-NLS-1$
  String TOOL_LCCII_CREATE_COMMUNICATION_LINK_TRANSMIT = "CommunicationLink Send / Produce / Call / Write / Transmit"; //$NON-NLS-1$
  String TOOL_LCCII_CREATE_COMMUNICATION_LINK_ACQUIRE = "CommunicationLink Receive / Consume / Execute / Access / Acquire"; //$NON-NLS-1$

  // LDFB tools - Logical Data Flow Blank tools
  String TOOL_LDFB_CREATE_LOGICAL_FUNCTION = "Logical Function"; //$NON-NLS-1$
  String TOOL_LDFB_CREATE_ACTOR_FUNCTION = "Actor Function"; //$NON-NLS-1$
  String TOOL_LDFB_CREATE_DUPLICATE = "Duplicate"; //$NON-NLS-1$
  String TOOL_LDFB_CREATE_GATHER = "Gather"; //$NON-NLS-1$
  String TOOL_LDFB_CREATE_ROUTE = "Route"; //$NON-NLS-1$
  String TOOL_LDFB_CREATE_SELECT = "Select"; //$NON-NLS-1$
  String TOOL_LDFB_CREATE_SPLIT = "Split"; //$NON-NLS-1$
  String TOOL_LDFB_CREATE_INPUT_PORT = "Input Port"; //$NON-NLS-1$
  String TOOL_LDFB_CREATE_OUTPUT_PORT = "Output Port"; //$NON-NLS-1$
  String TOOL_LDFB_CREATE_FUNCTIONAL_EXCHANGE = "Functional Exchange"; //$NON-NLS-1$
  String TOOL_LDFB_INSERT_REMOVE_FUNCTIONS = "Functions"; //$NON-NLS-1$
  String TOOL_LDFB_INSERT_REMOVE_ACTOR_FUNCTIONS = "Actor Functions"; //$NON-NLS-1$
  String TOOL_LDFB_INSERT_REMOVE_FUNCTIONAL_EXCHANGES = "Functional Exchanges"; //$NON-NLS-1$
  String TOOL_LDFB_INSERT_REMOVE_FUNCTIONAL_CHAINS = "Functional Chains"; //$NON-NLS-1$
  String TOOL_LDFB_INSERT_REMOVE_FUNCTIONAL_CHAIN_ELEMENTS = "Functional Chain Elements"; //$NON-NLS-1$
  String TOOL_LDFB_CREATE_CONSTRAINT = "Constraint"; //$NON-NLS-1$
  String TOOL_LDFB_CREATE_CONSTRAINT_ELEMENT = "ConstraintElement"; //$NON-NLS-1$
  String TOOL_LDFB_INSERT_REMOVE_CONSTRAINTS = "Constraints"; //$NON-NLS-1$
  // Accelerators
  String TOOL_LDFB_INITIALIZATION_FROM_EXISTING_DIAGRAM = "Initialization from existing diagram"; //$NON-NLS-1$
  // unsynchronized tools
  String TOOL_LDFB_INSERT_REMOVE_FUNCTION_PORTS = "Function Ports"; //$NON-NLS-1$
  String TOOL_LDFB_INSERT_REMOVE_EXCHANGE_CATEGORIES = "Exchange Categories"; //$NON-NLS-1$
  // other tools and id tool
  String TOOL_LDFB_CREATE_FUNCTIONAL_CHAIN = "Functional Chain"; //$NON-NLS-1$
  String TOOL_LDFB_SHOW_HIDE_FUNCTIONAL_CHAIN = "Show/Hide Functional Chains"; //$NON-NLS-1$
  String TOOL_LDFB_SHOW_HIDE_FUNCTIONAL_EXCH_CATEGORIES = "Switch Functional Exchanges / Categories"; //$NON-NLS-1$
  String TOOL_LDFB_SHOW_HIDE_EXCH_CATEGORIES = "Show/Hide Exchange Categories"; //$NON-NLS-1$
  String TOOL_LDFB_SHOW_HIDE_FUNCTIONS = "Show/Hide Functions"; //$NON-NLS-1$
  String TOOL_LDFB_SHOW_HIDE_ACTOR_FUNCTIONS = "Show/Hide Actor Functions"; //$NON-NLS-1$
  String TOOL_LDFB_SHOW_HIDE_FUNCTIONAL_EXCHANGES = "Show/Hide Functional Exchanges"; //$NON-NLS-1$
  String TOOL_LDFB_SHOW_HIDE_FUNCTION_PORTS = "Show/Hide Function Ports"; //$NON-NLS-1$
  String TOOL_LDFB_SHOW_HIDE_FUNCTIONAL_EXCHANGE = "Show/Hide Functional Exchanges"; //$NON-NLS-1$
  String TOOL_LDFB_SHOW_HIDE_FUNCTION_PORT = "Show/Hide Function Ports"; //$NON-NLS-1$
  String TOOL_LDFB_DELETE_ELEMENT = "delete capella element"; //$NON-NLS-1$
  String TOOL_LDFB_SHOW_HIDE_FUNCTION = "Show/Hide Functions"; //$NON-NLS-1$
  String TOOL_LDFB_SHOW_HIDE_ACTOR_FUNCTION = "Show/Hide Actor Functions"; //$NON-NLS-1$
  String TOOL_LDFB_INSERT_SCENARIO_ELEMENTS = TOOL_LAB_INSERT_SCENARIO_ELEMENTS;
  String TOOL_LDFB_INSERT_STATEMODE_ELEMENTS = TOOL_LAB_INSERT_STATEMODE_ELEMENTS;
  String TOOL_LDFB_RECONNECT_EXCHANGE = "LDFB Reconnect Exchanges"; //$NON-NLS-1$

  String TOOL_LDFB_INSERT_FUNCTIONS_FROM_MODE_STATE = "Functions from Mode / State"; //$NON-NLS-1$
  String TOOL_LDFB_INSERT_ELEMENTS_FROM_SCENARIO = "Elements from Scenario"; //$NON-NLS-1$
  String TOOL_LDFB_STATE_MODE_ELEMENTS_ID = "State-Mode Elements"; //$NON-NLS-1$
  String TOOL_LDFB_SCENARIO_ELEMENTS_ID = "Scenario Elements"; //$NON-NLS-1$

  // LFBD tools - Logical Function Breakdown
  String TOOL_LFBD_CREATE_LOGICAL_FUNCTION = "Logical Function"; //$NON-NLS-1$
  String TOOL_LFBD_CREATE_DUPLICATE = "Duplicate"; //$NON-NLS-1$
  String TOOL_LFBD_CREATE_GATHER = "Gather"; //$NON-NLS-1$
  String TOOL_LFBD_CREATE_ROUTE = "Route"; //$NON-NLS-1$
  String TOOL_LFBD_CREATE_SELECT = "Select"; //$NON-NLS-1$
  String TOOL_LFBD_CREATE_SPLIT = "Split"; //$NON-NLS-1$
  String TOOL_LFBD_CONTAINED_IN = "Contained In"; //$NON-NLS-1$
  String TOOL_LFBD_CREATE_CONSTRAINT = "Constraint"; //$NON-NLS-1$
  String TOOL_LFBD_CREATE_CONSTRAINTELEMENT = "ConstraintElement"; //$NON-NLS-1$
  String TOOL_LFBD_INSERT_REMOVE_CONSTRAINTS = "Constraints"; //$NON-NLS-1$

  // Logical (la layer) Interface Diagram Blank (LIDB) tools
  String TOOL_LIDB_SHOW_HIDE_COMPONENTS = "Show/Hide Components"; //$NON-NLS-1$
  String TOOL_LIDB_CREATE_LOGICAL_COMPONENT = "Component"; //$NON-NLS-1$
  String TOOL_LIDB_CREATE_ACTOR = "Actor"; //$NON-NLS-1$
  String TOOL_LIDB_CREATE_ECHANGE_ITEM_EVENT = "Event"; //$NON-NLS-1$
  String TOOL_LIDB_CREATE_ECHANGE_ITEM_OPERATION = "Operation"; //$NON-NLS-1$
  String TOOL_LIDB_CREATE_ECHANGE_ITEM_FLOW = "Flow"; //$NON-NLS-1$
  String TOOL_LIDB_CREATE_ECHANGE_ITEM_DATA = "Data"; //$NON-NLS-1$
  String TOOL_LIDB_CREATE_EXCHANGE_ITEM_ELEMENT = "Exchange Item Element"; //$NON-NLS-1$
  String TOOL_LIDB_CREATE_INTERFACE = "Interface"; //$NON-NLS-1$
  String TOOL_LIDB_MANAGE_EXCHANGE_ITEM_ALLOCATIONS = "Manage Exchange Item Allocations"; //$NON-NLS-1$
  String TOOL_LIDB_CREATE_COMMUNICATION_LINK_TRANSMIT = "CommunicationLink Send / Produce / Call / Write / Transmit"; //$NON-NLS-1$
  String TOOL_LIDB_CREATE_COMMUNICATION_LINK_ACQUIRE = "CommunicationLink Receive / Consume / Execute / Access / Acquire"; //$NON-NLS-1$
  String TOOL_LIDB_CREATE_USES = "Uses"; //$NON-NLS-1$
  String TOOL_LIDB_CREATE_PROVIDES = "Provides"; //$NON-NLS-1$
  String TOOL_LIDB_CREATE_REQUIRES = "Requires"; //$NON-NLS-1$
  String TOOL_LIDB_CREATE_GENERALIZATION = "Generalization"; //$NON-NLS-1$
  String TOOL_LIDB_CREATE_INOUT_FLOW_PORT_PORT = "InOut Flow Port"; //$NON-NLS-1$
  String TOOL_LIDB_CREATE_STANDARD_PORT = "Standard Port"; //$NON-NLS-1$
  String TOOL_LIDB_CREATE_PORT_DELEGATION = "Port Delegation"; //$NON-NLS-1$
  String TOOL_LIDB_CREATE_IMPLEMENTS = "Implements"; //$NON-NLS-1$
  String TOOL_LIDB_CREATE_EXCHANGE_ITEM_ALLOCATION = "Exchange Item Allocation"; //$NON-NLS-1$

  // MB tools - Missions Blank
  String TOOL_MB_CREATE_ACTOR = "Actor"; //$NON-NLS-1$
  String TOOL_MB_CREATE_CAPABILITY = "Capability"; //$NON-NLS-1$
  String TOOL_MB_CREATE_MISSION = "Mission"; //$NON-NLS-1$
  String TOOL_MB_CREATE_CAPABILITY_EXPLOITATION = "Capability Exploitation"; //$NON-NLS-1$
  String TOOL_MB_CREATE_ACTOR_INVOLVEMENT = "Actor Involvement"; //$NON-NLS-1$
  String TOOL_MB_CREATE_ACTOR_GENERALIZATION = "Actor Generalization"; //$NON-NLS-1$
  String TOOL_MB_INSERT_REMOVE_ACTORS = "Actors"; //$NON-NLS-1$
  String TOOL_MB_INSERT_REMOVE_CAPABILITIES = "Capabilities"; //$NON-NLS-1$
  String TOOL_MB_INSERT_REMOVE_MISSIONS = "Missions"; //$NON-NLS-1$
  String TOOL_MB_INSERT_REMOVE_RELATIONSHIPS = "Relationships"; //$NON-NLS-1$
  String TOOL_MB_CREATE_CONSTRAINT = "Constraint"; //$NON-NLS-1$
  String TOOL_MB_CREATE_CONSTRAINTELEMENT = "ConstraintElement"; //$NON-NLS-1$
  String TOOL_MB_INSERT_REMOVE_CONSTRAINTS = "Constraints"; //$NON-NLS-1$
  String TOOL_MB_RECONNECT_GENERALIZATION_SOURCE = TOOL_CC_RECONNECT_GENERALIZATION_SOURCE;
  String TOOL_MB_RECONNECT_GENERALIZATION_TARGET = TOOL_CC_RECONNECT_GENERALIZATION_TARGET;
  // tool id
  String TOOL_MB_SHOW_ACTOR = "s/h Actors"; //$NON-NLS-1$
  String TOOL_MB_SHOW_MISSION = "s/h Missions"; //$NON-NLS-1$
  String TOOL_MB_SHOW_CAPABILITY = "s/h Capabilities"; //$NON-NLS-1$

  // MCB tools - Mission Capabilities Blank
  String TOOL_MCB_CREATE_ACTOR = "Actor"; //$NON-NLS-1$
  String TOOL_MCB_CREATE_MISSION = "Mission"; //$NON-NLS-1$
  String TOOL_MCB_CREATE_CAPABILITY = "Capability"; //$NON-NLS-1$
  String TOOL_MCB_CREATE_CAPABILITY_EXPLOITATION = "Capability Exploitation"; //$NON-NLS-1$
  String TOOL_MCB_CREATE_INVOLVED_ACTOR = "Involved Actor"; //$NON-NLS-1$
  String TOOL_MCB_CREATE_EXTENDS = "Extends"; //$NON-NLS-1$
  String TOOL_MCB_CREATE_INCLUDES = "Includes"; //$NON-NLS-1$
  String TOOL_MCB_CREATE_CAPABILITY_GENERALIZATION = "Capability Generalization"; //$NON-NLS-1$
  String TOOL_MCB_CREATE_ACTOR_GENERALIZATION = "Actor Generalization"; //$NON-NLS-1$
  String TOOL_MCB_INSERT_REMOVE_ACTORS = "s/h Actors"; //$NON-NLS-1$
  String TOOL_MCB_INSERT_REMOVE_ACTORS_LABEL = "Actors"; //$NON-NLS-1$
  String TOOL_MCB_INSERT_REMOVE_MISSIONS = "s/h Missions"; //$NON-NLS-1$
  String TOOL_MCB_INSERT_REMOVE_MISSIONS_LABEL = "Missions"; //$NON-NLS-1$
  String TOOL_MCB_INSERT_REMOVE_CAPABILITIES = "s/h Capabilities"; //$NON-NLS-1$
  String TOOL_MCB_INSERT_REMOVE_CAPABILITIES_LABEL = "Capabilities"; //$NON-NLS-1$
  String TOOL_MCB_INSERT_REMOVE_RELATIONSHIPS = "Show/Hide Relationships"; //$NON-NLS-1$
  String TOOL_MCB_INSERT_REMOVE_RELATIONSHIPS_LABEL = "Relationships"; //$NON-NLS-1$
  String TOOL_MCB_CREATE_CONSTRAINT = "Constraint"; //$NON-NLS-1$
  String TOOL_MCB_CREATE_CONSTRAINTELEMENT = "ConstraintElement"; //$NON-NLS-1$
  String TOOL_MCB_INSERT_REMOVE_CONSTRAINTS = "Constraints"; //$NON-NLS-1$
  String TOOL_MCB_RECONNECT_GENERALIZATION_SOURCE = TOOL_CC_RECONNECT_GENERALIZATION_SOURCE;
  String TOOL_MCB_RECONNECT_GENERALIZATION_TARGET = TOOL_CC_RECONNECT_GENERALIZATION_TARGET;

  // M&S fools - Modes and States
  String TOOL_MS_CREATE_MODE = "Mode"; //$NON-NLS-1$
  String TOOL_MS_CREATE_STATE = "State"; //$NON-NLS-1$
  String TOOL_MS_REUSE_MODE_STATE = "Reuse Mode/State"; //$NON-NLS-1$
  String TOOL_MS_CREATE_INITIAL = "Initial"; //$NON-NLS-1$
  String TOOL_MS_CREATE_JOIN = "Join"; //$NON-NLS-1$
  String TOOL_MS_CREATE_CHOICE = "Choice"; //$NON-NLS-1$
  String TOOL_MS_CREATE_FORK = "Fork"; //$NON-NLS-1$
  String TOOL_MS_CREATE_TERMINATE = "Terminate"; //$NON-NLS-1$
  String TOOL_MS_CREATE_FINAL = "Final"; //$NON-NLS-1$
  String TOOL_MS_CREATE_DEEP_HISTORY = "DeepHistory"; //$NON-NLS-1$
  String TOOL_MS_CREATE_SHALLOW_HISTORY = "ShallowHistory"; //$NON-NLS-1$
  String TOOL_MS_CREATE_ENTRY_POINT = "EntryPoint"; //$NON-NLS-1$
  String TOOL_MS_CREATE_EXIT_POINT = "ExitPoint"; //$NON-NLS-1$
  String TOOL_MS_CREATE_TRANSISTION = "Transition"; //$NON-NLS-1$
  String TOOL_MS_CREATE_CONSTRAINT = "Constraint"; //$NON-NLS-1$
  String TOOL_MS_CREATE_CONSTRAINTELEMENT = "ConstraintElement"; //$NON-NLS-1$
  String TOOL_MS_INSERT_REMOVE_CONSTRAINTS = "Constraints"; //$NON-NLS-1$
  // Unsynchronized
  String TOOL_MS_INSERT_REMOVE_MODE_STATE = "State/Mode"; //$NON-NLS-1$
  String TOOL_MS_INSERT_REMOVE_TRANSITION = "Transition"; //$NON-NLS-1$

  // Accelerators
  String TOOL_MS_INITIALIZATION_FROM_EXISTING_DIAGRAM = "Initialization from existing diagram"; //$NON-NLS-1$
  // tools id
  String TOOL_MS_INSERT_MODE_STATE = "Insert Mode/State"; //$NON-NLS-1$
  String TOOL_MS_SHOW_HIDE_MODE_STATE = "ShowHide ModeState"; //$NON-NLS-1$
  String TOOL_MS_SHOW_HIDE_TRANSITION = "ShowHide Transition"; //$NON-NLS-1$
  String TOOL_MS_RECONNECT_TARGET_TRANSISTION = "Reconnect target transition"; //$NON-NLS-1$
  String TOOL_MS_RECONNECT_SOURCE_TRANSISTION = "Reconnect source transition"; //$NON-NLS-1$
  String TOOL_MS_DND_STATES_DROM_DIAGRAM = "D&D ModeState from Diagram"; //$NON-NLS-1$
  String TOOL_MS_DND_PSEUDOSTATES_DROM_DIAGRAM = "D&D PseudoState from Diagram"; //$NON-NLS-1$

  // M&S fools - Modes and States
  String TOOL_MSM_CREATE_MODE = "Mode"; //$NON-NLS-1$
  String TOOL_MSM_CREATE_STATE = "State"; //$NON-NLS-1$
  String TOOL_MSM_CREATE_REGION = "Region"; //$NON-NLS-1$
  String TOOL_MSM_REUSE_MODE_STATE = "Reuse Mode/State"; //$NON-NLS-1$
  String TOOL_MSM_CREATE_INITIAL = "Initial"; //$NON-NLS-1$
  String TOOL_MSM_CREATE_JOIN = "Join"; //$NON-NLS-1$
  String TOOL_MSM_CREATE_CHOICE = "Choice"; //$NON-NLS-1$
  String TOOL_MSM_CREATE_FORK = "Fork"; //$NON-NLS-1$
  String TOOL_MSM_CREATE_TERMINATE = "Terminate"; //$NON-NLS-1$
  String TOOL_MSM_CREATE_FINAL = "Final"; //$NON-NLS-1$
  String TOOL_MSM_CREATE_DEEP_HISTORY = "DeepHistory"; //$NON-NLS-1$
  String TOOL_MSM_CREATE_SHALLOW_HISTORY = "ShallowHistory"; //$NON-NLS-1$
  String TOOL_MSM_CREATE_ENTRY_POINT = "EntryPoint"; //$NON-NLS-1$
  String TOOL_MSM_CREATE_EXIT_POINT = "ExitPoint"; //$NON-NLS-1$
  String TOOL_MSM_CREATE_TRANSISTION = "Transition"; //$NON-NLS-1$
  String TOOL_MSM_CREATE_CONSTRAINT = "Constraint"; //$NON-NLS-1$
  String TOOL_MSM_CREATE_CONSTRAINTELEMENT = "ConstraintElement"; //$NON-NLS-1$
  String TOOL_MSM_INSERT_REMOVE_CONSTRAINTS = "Constraints"; //$NON-NLS-1$
  // Unsynchronized
  String TOOL_MSM_INSERT_REMOVE_MODE_STATE = "ShowHide ModeState"; //$NON-NLS-1$
  String TOOL_MSM_INSERT_REMOVE_TRANSITION = "ShowHide Transition"; //$NON-NLS-1$

  // Accelerators
  String TOOL_MSM_INITIALIZATION_FROM_EXISTING_DIAGRAM = "Initialization from existing diagram"; //$NON-NLS-1$
  // tools id
  String TOOL_MSM_INSERT_MODE_STATE = "Insert Mode/State"; //$NON-NLS-1$
  String TOOL_MSM_SHOW_HIDE_MODE_STATE = "ShowHide ModeState"; //$NON-NLS-1$
  String TOOL_MSM_SHOW_HIDE_TRANSITION = "ShowHide Transition"; //$NON-NLS-1$
  String TOOL_MSM_RECONNECT_TARGET_TRANSISTION = "Reconnect target transition"; //$NON-NLS-1$
  String TOOL_MSM_RECONNECT_SOURCE_TRANSISTION = "Reconnect source transition"; //$NON-NLS-1$
  String TOOL_MSM_DND_STATES_DROM_DIAGRAM = "D&D ModeState from Diagram"; //$NON-NLS-1$
  String TOOL_MSM_DND_PSEUDOSTATES_DROM_DIAGRAM = "D&D PseudoState from Diagram"; //$NON-NLS-1$

  // OAB tools - Operational Architecture Blank
  // Entities
  String TOOL_OAB_CREATE_OE = "Operational Entity"; //$NON-NLS-1$
  String TOOL_OAB_CREATE_OA = "Operational Actor"; //$NON-NLS-1$
  String TOOL_OAB_CREATE_COMMUNICATION_MEAN = "Communication Mean"; //$NON-NLS-1$
  String TOOL_OAB_INSERT_REMOVE_OPERATIONAL_ENTITIES = "Operational Entities"; //$NON-NLS-1$
  String TOOL_OAB_INSERT_REMOVE_OPERATIONAL_ACTORS = "Operational Actors"; //$NON-NLS-1$
  @Deprecated
  String TOOL_OAB_INSERT_REMOVE_COMMUNICATION_MEANS = "Communication Means"; //$NON-NLS-1$
  String TOOL_OAB_CREATE_CONSTRAINT = "Constraint"; //$NON-NLS-1$
  String TOOL_OAB_CREATE_CONSTRAINTELEMENT = "ConstraintElement"; //$NON-NLS-1$
  String TOOL_OAB_INSERT_REMOVE_CONSTRAINTS = "Constraints"; //$NON-NLS-1$
  // Roles
  String TOOL_OAB_CREATE_ROLE = "Role"; //$NON-NLS-1$
  String TOOL_OAB_MANAGE_ROLE_ALLOCATION = "Manage Role Allocation"; //$NON-NLS-1$
  String TOOL_OAB_INSERT_REMOVE_ALL_ALLOCATED_ROLES = "All Allocated Roles"; //$NON-NLS-1$
  String TOOL_OAB_INSERT_REMOVE_ALLOCATED_ROLES = "Allocated Roles"; //$NON-NLS-1$
  // Activities
  String TOOL_OAB_CREATE_OPERATIONAL_ACTIVITY = "Operational Activity"; //$NON-NLS-1$
  String TOOL_OAB_CREATE_INTERACTION = "Interaction"; //$NON-NLS-1$
  String TOOL_OAB_MANAGE_ACTIVITY_ALLOCATION = "Manage Activity Allocation"; //$NON-NLS-1$
  String TOOL_OAB_INSERT_REMOVE_INTERACTIONS = "Interactions"; //$NON-NLS-1$
  String TOOL_OAB_CREATE_OPERATIONAL_PROCESS = "Operational Process"; //$NON-NLS-1$
  String TOOL_OAB_INSERT_REMOVE_OPERATIONAL_PROCESSES = "Operational Processes"; //$NON-NLS-1$
  String TOOL_OAB_INSERT_REMOVE_ALLOCATED_ACTIVITIES = "Allocated Activities"; //$NON-NLS-1$
  String TOOL_OAB_INSERT_REMOVE_ALL_ALLOCATED_ACTIVITIES_IN_ENTITIES = "All Allocated Activities in Entities"; //$NON-NLS-1$
  String TOOL_OAB_INSERT_REMOVE_ALL_ALLOCATED_ACTIVITIES_IN_ROLES = "All Allocated Activities in Roles"; //$NON-NLS-1$
  String TOOL_OAB_INSERT_SCENARIO_ELEMENTS = TOOL_LAB_INSERT_SCENARIO_ELEMENTS;
  String TOOL_OAB_INSERT_STATEMODE_ELEMENTS = TOOL_LAB_INSERT_STATEMODE_ELEMENTS;

  String TOOL_OAB_INSERT_ACTIVITIES_FROM_MODE_STATE = "Activities from Mode / State"; //$NON-NLS-1$
  String TOOL_OAB_INSERT_ELEMENTS_FROM_SCENARIO = "Elements from Scenario"; //$NON-NLS-1$
  String TOOL_OAB_STATE_MODE_ELEMENTS_ID = "State-Mode Elements"; //$NON-NLS-1$
  String TOOL_OAB_SCENARIO_ELEMENTS_ID = "Scenario Elements"; //$NON-NLS-1$
  // tools id
  String TOOL_OAB_RECONNECT_COMMUNICATION_MEAN_TARGET = "Target Communication Mean"; //$NON-NLS-1$
  String TOOL_OAB_RECONNECT_COMMUNICATION_MEAN_SOURCE = "Source Communication Mean"; //$NON-NLS-1$
  String TOOL_OAB_RECONNECT_INTERACTION = "OAIB Reconnect Exchanges"; //$NON-NLS-1$

  // OABD tools - Operational Activity Breakdown
  String TOOL_OABD_CREATE_OPERATIONAL_ACTIVITY = "Operational Activity"; //$NON-NLS-1$
  String TOOL_OABD_CONTAINED_IN = "Contained In"; //$NON-NLS-1$
  String TOOL_OABD_CREATE_CONSTRAINT = "Constraint"; //$NON-NLS-1$
  String TOOL_OABD_CREATE_CONSTRAINTELEMENT = "ConstraintElement"; //$NON-NLS-1$
  String TOOL_OABD_INSERT_REMOVE_CONSTRAINTS = "Constraints"; //$NON-NLS-1$

  // OAIB tools - Operational Activity Interaction Blank Diagram
  String TOOL_OAIB_CREATE_OPERATIONAL_ACTIVITY = "Operational Activity"; //$NON-NLS-1$
  String TOOL_OAIB_CREATE_INTERACTION = "Interaction"; //$NON-NLS-1$
  String TOOL_OAIB_INSERT_REMOVE_OPERATIONAL_ACTIVITIES = "Operational Activities"; //$NON-NLS-1$
  String TOOL_OAIB_INSERT_REMOVE_INTERACTIONS = "Interactions"; //$NON-NLS-1$
  String TOOL_OAIB_SHOW_HIDE_OP = "Operational Processes"; //$NON-NLS-1$
  String TOOL_OAIB_CREATE_OPERATIONAL_PROCESS_ELEMENTS = "Operational Process Elements"; //$NON-NLS-1$
  String TOOL_OAIB_CREATE_CONSTRAINT = "Constraint"; //$NON-NLS-1$
  String TOOL_OAIB_CREATE_CONSTRAINT_ELEMENT = "ConstraintElement"; //$NON-NLS-1$
  String TOOL_OAIB_INSERT_REMOVE_CONSTRAINTS = "Constraints"; //$NON-NLS-1$
  String TOOL_OAIB_INSERT_SCENARIO_ELEMENTS = TOOL_LDFB_INSERT_SCENARIO_ELEMENTS;
  String TOOL_OAIB_INSERT_STATEMODE_ELEMENTS = TOOL_LDFB_INSERT_STATEMODE_ELEMENTS;

  String TOOL_OAIB_CREATE_OPERATIONAL_PROCESS = "Operational Process"; //$NON-NLS-1$
  String TOOL_OAIB_SHOW_HIDE_OA = "Show/Hide Operational Activity"; //$NON-NLS-1$
  String TOOL_OAIB_SHOW_HIDE_OPERATIONAL_PROCESS = "Operational Processes"; //$NON-NLS-1$
  String TOOL_OAIB_SHOW_OPERATIONAL_PROCESS_ELT = "Show Operational Process Elements"; //$NON-NLS-1$
  String TOOL_OAIB_SHOW_HIDE_CONSTRAINTS = "Show/Hide Constraints"; //$NON-NLS-1$
  String TOOL_OAIB_SHOW_HIDE_INTERACTION = "Show/Hide Interaction"; //$NON-NLS-1$
  String TOOL_OAIB_RECONNECT_EXCHANGES = "OAIB Reconnect Exchanges"; //$NON-NLS-1$
  String TOOL_OAIB_INSERT_ACTIVITIES_FROM_MODE_STATE = "Activities from Mode / State"; //$NON-NLS-1$
  String TOOL_OAIB_INSERT_ELEMENTS_FROM_SCENARIO = "Elements from Scenario"; //$NON-NLS-1$
  String TOOL_OAIB_STATE_MODE_ELEMENTS_ID = "State-Mode Elements"; //$NON-NLS-1$
  String TOOL_OAIB_SCENARIO_ELEMENTS_ID = "Scenario Elements"; //$NON-NLS-1$

  // OAS tools - Operational Activity Scenario
  String TOOL_OAS_CREATE_ACTIVITY = "Activity"; //$NON-NLS-1$
  String TOOL_OAS_CREATE_INTERACTION_WITH_RETURN_BRANCH = "Interaction with return branch"; //$NON-NLS-1$
  String TOOL_OAS_CREATE_INTERACTION = "Interaction"; //$NON-NLS-1$
  String TOOL_OAS_INSERT_REMOVE_ACTIVITIES = "Activities"; //$NON-NLS-1$
  String TOOL_OAS_INSERT_REMOVE_STATE_MODE = "State / Mode"; //$NON-NLS-1$
  String TOOL_OAS_CREATE_REFERENCE = "Reference"; //$NON-NLS-1$
  String TOOL_OAS_CREATE_LOOP = "LOOP"; //$NON-NLS-1$
  String TOOL_OAS_CREATE_ALT = "ALT"; //$NON-NLS-1$
  String TOOL_OAS_CREATE_PAR = "PAR"; //$NON-NLS-1$
  String TOOL_OAS_CREATE_OTHER_COMBINED_FRAGMENT = "Other Combined Fragment"; //$NON-NLS-1$
  String TOOL_OAS_CREATE_OPERAND = "Operand"; //$NON-NLS-1$
  String TOOL_OAS_CREATE_CONSTRAINT = "Constraint"; //$NON-NLS-1$
  String TOOL_OAS_CREATE_CONSTRAINTELEMENTSCENARIO = "ConstraintElementScenario"; //$NON-NLS-1$
  String TOOL_OAS_INSERT_REMOVE_CONSTRAINTS = "Constraints"; //$NON-NLS-1$
  String TOOL_OAS_DURATION = "Duration"; //$NON-NLS-1$
  String TOOL_OAS_EXCHANGE_CONTEXT = "Exchange Context"; //$NON-NLS-1$

  // tool id
  String TOOL_OAS_CREATE_FUNCTIONAL_EXCHANGE_WITH_RETURN_BRANCH = "Functional Exchange with return branch"; //$NON-NLS-1$
  String TOOL_OAS_CREATE_FUNCTIONAL_EXCHANGE = "Functional Exchange"; //$NON-NLS-1$

  // OCB - Operational Capabilities Blank (OCB) tools
  String TOOL_OCB_CREATE_OPERATIONAL_ENTITY = "Operational Entity"; //$NON-NLS-1$
  String TOOL_OCB_CREATE_OPERATIONAL_ACTOR = "Operational Actor"; //$NON-NLS-1$
  String TOOL_OCB_CREATE_OPERATIONAL_CAPABILITY = "Operational Capability"; //$NON-NLS-1$
  String TOOL_OCB_CREATE_COMMUNICATION_MEAN = "CommunicationMean"; //$NON-NLS-1$
  String TOOL_OCB_CREATE_INVOLMENT = "Involvement"; //$NON-NLS-1$
  String TOOL_OCB_CREATE_EXTENDS = "Extends"; //$NON-NLS-1$
  String TOOL_OCB_CREATE_INCLUDES = "Includes"; //$NON-NLS-1$
  String TOOL_OCB_CREATE_OPERATIONAL_CAPABILITY_GENERALIZATION = "Operational Capability Generalization"; //$NON-NLS-1$
  String TOOL_OCB_INSERT_REMOVE_OPERATIONAL_ENTITIES = "Operational Entities"; //$NON-NLS-1$
  String TOOL_OCB_INSERT_REMOVE_OPERATIONAL_ACTORS = "Operational Actors"; //$NON-NLS-1$
  String TOOL_OCB_INSERT_REMOVE_OPERATIONAL_CAPABILITIES = "Operational Capabilities"; //$NON-NLS-1$
  String TOOL_OCB_INSERT_REMOVE_RELATIONSHIPS = "Relationships"; //$NON-NLS-1$
  String TOOL_OCB_CREATE_CONSTRAINT = "Constraint"; //$NON-NLS-1$
  String TOOL_OCB_CREATE_CONSTRAINT_ELEMENT = "ConstraintElement"; //$NON-NLS-1$
  String TOOL_OCB_INSERT_REMOVE_CONSTRAINTS = "Constraints"; //$NON-NLS-1$

  String TOOL_OCB_SHOW_HIDE_OPERATIONAL_ENTITY = "Show/Hide OE"; //$NON-NLS-1$
  String TOOL_OCB_SHOW_HIDE_OPERATIONAL_ACTORS = "Show/Hide OA"; //$NON-NLS-1$
  String TOOL_OCB_SHOW_HIDE_CAPABILITIES = "Show/Hide Operational Capabilities"; //$NON-NLS-1$

  // Operational Entity Blank Diagram Tools
  // Entities
  String TOOL_OEB_CREATE_OE = "Operational Entity"; //$NON-NLS-1$
  String TOOL_OEB_CREATE_OA = "Operational Actor"; //$NON-NLS-1$
  String TOOL_OEB_CREATE_COMMUNICATION_MEAN = "Communication Mean"; //$NON-NLS-1$
  String TOOL_OEB_INSERT_REMOVE_OPERATIONAL_ENTITIES = "Operational Entities"; //$NON-NLS-1$
  String TOOL_OEB_INSERT_REMOVE_OPERATIONAL_ACTORS = "Operational Actors"; //$NON-NLS-1$
  String TOOL_OEB_INSERT_REMOVE_COMMUNICATION_MEANS = "Communication Means"; //$NON-NLS-1$
  String TOOL_OEB_CREATE_CONSTRAINT = "Constraint"; //$NON-NLS-1$
  String TOOL_OEB_CREATE_CONSTRAINTELEMENT = "ConstraintElement"; //$NON-NLS-1$
  String TOOL_OEB_INSERT_REMOVE_CONSTRAINTS = "Constraints"; //$NON-NLS-1$
  // Roles
  String TOOL_OEB_CREATE_ROLE = "Role"; //$NON-NLS-1$
  String TOOL_OEB_MANAGE_ROLE_ALLOCATION = "Manage Role Allocation"; //$NON-NLS-1$
  String TOOL_OEB_INSERT_REMOVE_ALL_ALLOCATED_ROLES = "All Allocated Roles"; //$NON-NLS-1$
  String TOOL_OEB_INSERT_REMOVE_ALLOCATED_ROLES = "Allocated Roles"; //$NON-NLS-1$
  // Activities
  String TOOL_OEB_CREATE_OPERATIONAL_ACTIVITY = "Operational Activity"; //$NON-NLS-1$
  String TOOL_OEB_CREATE_INTERACTION = "Interaction"; //$NON-NLS-1$
  String TOOL_OEB_MANAGE_ACTIVITY_ALLOCATION = "Manage Activity Allocation"; //$NON-NLS-1$
  String TOOL_OEB_INSERT_REMOVE_INTERACTIONS = "Interactions"; //$NON-NLS-1$
  String TOOL_OEB_INSERT_REMOVE_OPERATIONAL_PROCESSES = "Operational Processes"; //$NON-NLS-1$
  String TOOL_OEB_INSERT_REMOVE_ALLOCATED_ACTIVITIES = "Allocated Activities"; //$NON-NLS-1$
  String TOOL_OEB_INSERT_REMOVE_ALL_ALLOCATED_ACTIVITIES_IN_ENTITIES = "All Allocated Activities in Entities"; //$NON-NLS-1$
  String TOOL_OEB_INSERT_REMOVE_ALL_ALLOCATED_ACTIVITIES_IN_ROLES = "All Allocated Activities in Roles"; //$NON-NLS-1$
  String TOOL_OEB_INSERT_SCENARIO_ELEMENTS = TOOL_LAB_INSERT_SCENARIO_ELEMENTS;
  String TOOL_OEB_INSERT_STATEMODE_ELEMENTS = TOOL_LAB_INSERT_STATEMODE_ELEMENTS;

  String TOOL_OEB_SHOW_HIDE_OE = "Show/Hide OE"; //$NON-NLS-1$
  String TOOL_OEB_SHOW_HIDE_OA = "Show/Hide OA"; //$NON-NLS-1$
  String TOOL_OEB_SHOW_HIDE_COMMUNICATION_MEAN = "Show/Hide Communication Mean"; //$NON-NLS-1$
  String TOOL_OEB_SHOW_HIDE_ALLOCATED_ACTIVITIES = "Show/Hide Allocated Activities"; //$NON-NLS-1$
  String TOOL_OEB_CREATE_OPERATIONAL_PROCESS = "Operational Process"; //$NON-NLS-1$
  String TOOL_OEB_SHOW_HIDE_OP = "Show/Hide Operational Processes"; //$NON-NLS-1$
  String TOOL_OEB_SHOW_HIDE_ALLOCATED_ROLES = "Show/Hide Allocated Roles"; //$NON-NLS-1$
  String TOOL_OEB_SHOW_HIDE_ALL_ALLOCATED_ROLES = "Show/hide All Allocated Roles"; //$NON-NLS-1$
  String TOOL_OEB_ALL_ALLOCATED_ACTIVITIES_IN_ROLES = "Insert All Allocated Activities in Roles"; //$NON-NLS-1$
  String TOOL_OEB_ALL_ALLOCATED_ACTIVITIES_IN_ENTITIES = "Insert All Allocated Activities in Entities"; //$NON-NLS-1$
  String TOOL_OEB_SHOW_HIDE_INTERACTION = "Show/Hide Interaction"; //$NON-NLS-1$

  String TOOL_OEB_INSERT_ACTIVITIES_FROM_MODE_STATE = "Activities from Mode / State"; //$NON-NLS-1$
  String TOOL_OEB_INSERT_ELEMENTS_FROM_SCENARIO = "Elements from Scenario"; //$NON-NLS-1$
  String TOOL_OEB_STATE_MODE_ELEMENTS_ID = "State-Mode Elements"; //$NON-NLS-1$
  String TOOL_OEB_SCENARIO_ELEMENTS_ID = "Scenario Elements"; //$NON-NLS-1$
  String TOOL_OEB_INSERT_REMOVE_CATEGORIES = "Categories"; //$NON-NLS-1$
  String TOOL_OEB_INSERT_REMOVE_COMMUNICATION_MEANS_CATEGORIES = "Communication Means / Categories"; //$NON-NLS-1$

  // OEBD tools - Operational Entity Breakdown
  String TOOL_OEBD_CREATE_OE = "Operational Entity"; //$NON-NLS-1$
  String TOOL_OEBD_CREATE_OA = "Operational Actor"; //$NON-NLS-1$
  String TOOL_OEBD_CONTAINED_IN = "Contained In"; //$NON-NLS-1$
  String TOOL_OEBD_CREATE_CONSTRAINT = "Constraint"; //$NON-NLS-1$
  String TOOL_OEBD_CREATE_CONSTRAINTELEMENT = "ConstraintElement"; //$NON-NLS-1$
  String TOOL_OEBD_INSERT_REMOVE_CONSTRAINTS = "Constraints"; //$NON-NLS-1$

  // OES tools - Operational Entity Scenario
  String TOOL_OES_CREATE_OE = "Operational Entity"; //$NON-NLS-1$
  String TOOL_OES_CREATE_OA = "Operational Actor"; //$NON-NLS-1$
  String TOOL_OES_CREATE_ROLE = "Role"; //$NON-NLS-1$
  String TOOL_OES_INSERT_REMOVE_OPERATIONAL_ENTITIES_ROLES = "Operational Entities / roles";  //$NON-NLS-1$
  String TOOL_OES_CREATE_INTERACTION_WITH_RETURN_BRANCH = "Interaction with Return Branch"; //$NON-NLS-1$
  String TOOL_OES_CREATE_INTERACTION = "Interaction"; //$NON-NLS-1$
  String TOOL_OES_CREATE_MESSAGE = "Create message"; //$NON-NLS-1$
  String TOOL_OES_DELETE_MESSAGE = "Delete message"; //$NON-NLS-1$
  String TOOL_OES_CREATE_REFERENCE = "Reference"; //$NON-NLS-1$
  String TOOL_OES_CREATE_LOOP = "LOOP"; //$NON-NLS-1$
  String TOOL_OES_CREATE_ALT = "ALT"; //$NON-NLS-1$
  String TOOL_OES_CREATE_PAR = "PAR"; //$NON-NLS-1$
  String TOOL_OES_CREATE_OTHER_COMBINED_FRAGMENT = "Other Combined Fragment"; //$NON-NLS-1$
  String TOOL_OES_CREATE_OPERAND = "Operand"; //$NON-NLS-1$
  String TOOL_OES_INSERT_REMOVE_ACTIVITY = "Activity"; //$NON-NLS-1$
  String TOOL_OES_INSERT_REMOVE_STATE_MODE = "State / Mode"; //$NON-NLS-1$
  String TOOL_OES_CREATE_COMMUNICATIONMEAN_RETURN_BRANCH = "CommunicationMean Return Branch"; //$NON-NLS-1$
  String TOOL_OES_CREATE_COMMUNICATION_MEAN = "Communication Mean"; //$NON-NLS-1$
  String TOOL_OES_CREATE_CONSTRAINT = "Constraint"; //$NON-NLS-1$
  String TOOL_OES_CREATE_CONSTRAINTELEMENT = "ConstraintElement"; //$NON-NLS-1$
  String TOOL_OES_INSERT_REMOVE_CONSTRAINTS = "Constraints"; //$NON-NLS-1$
  String TOOL_OES_CREATE_ARM_TIMER = "Arm timer"; //$NON-NLS-1$
  String TOOL_OES_CREATE_CANCEL_TIMER = "Cancel timer"; //$NON-NLS-1$
  String TOOL_OES_DURATION = "Duration"; //$NON-NLS-1$
  String TOOL_OES_EXCHANGE_CONTEXT = "Exchange Context"; //$NON-NLS-1$

  // OES tool ids (different from the label)
  String TOOL_OES_CREATE_SEQUENCE_MESSAGE = "Sequence Message"; //$NON-NLS-1$
  String TOOL_OES_CREATE_SEQUENCE_MESSAGE_WITH_RETURN_BRANCH = "Sequence Message with Return Branch"; //$NON-NLS-1$
  String TOOL_OES_CREATE_CONSTRAINTELEMENTSCENARIO = "ConstraintElementScenario"; //$NON-NLS-1$
  String TOOL_OES_CREATE_ACTIVITY_STATE = "Activity state"; //$NON-NLS-1$

  // OPD tools - Operational Process Description
  String TOOL_OPD_INSERT_REMOVE_INVOLVE_OPERATIONAL_ACTIVITY = "Involve Operational Activity"; //$NON-NLS-1$
  String TOOL_OPD_INSERT_REMOVE_INVOLVE_INTERACTION = "Involve Interaction"; //$NON-NLS-1$
  String TOOL_OPD_INSERT_REMOVE_INVOLVE_OPERATIONAL_PROCESS = "Involve Operational Process"; //$NON-NLS-1$
  String TOOL_OPD_INSERT_REMOVE_INVOLVE_INTERACTION_AND_TARGET_ACTIVITY = "Involve Interaction & Target Activity"; //$NON-NLS-1$
  String TOOL_OPD_INSERT_REMOVE_INVOLVE_INTERACTION_AND_TARGET_OPERATIONAL_PROCESS = "Involve Interaction & Target Operational Process"; //$NON-NLS-1$
  String TOOL_OPD_CREATE_CONSTRAINT = "Constraint"; //$NON-NLS-1$
  String TOOL_OPD_CREATE_CONSTRAINTELEMENT = "ConstraintElement"; //$NON-NLS-1$
  String TOOL_OPD_INSERT_REMOVE_CONSTRAINTS = "Constraints"; //$NON-NLS-1$

  // ORB tools - Operational Role Blank diagram
  // Roles
  String TOOL_ORB_CREATE_OPERATIONAL_ROLE = "Operational Role"; //$NON-NLS-1$
  String TOOL_ORB_INSERT_REMOVE_OPERATIONAL_ROLES = "Operational Roles"; //$NON-NLS-1$
  String TOOL_ORB_CREATE_CONSTRAINT = "Constraint"; //$NON-NLS-1$
  String TOOL_ORB_CREATE_CONSTRAINTELEMENT = "ConstraintElement"; //$NON-NLS-1$
  String TOOL_ORB_INSERT_REMOVE_CONSTRAINTS = "Constraints"; //$NON-NLS-1$
  // Activities
  String TOOL_ORB_CREATE_OPERATIONAL_ACTIVITY = "Operational Activity"; //$NON-NLS-1$
  String TOOL_ORB_CREATE_INTERACTION = "Interaction"; //$NON-NLS-1$
  String TOOL_ORB_INSERT_REMOVE_INTERACTIONS = "Interactions"; //$NON-NLS-1$
  String TOOL_ORB_MANAGE_ACTIVITY_ALLOCATION = "Manage Activity Allocation"; //$NON-NLS-1$
  String TOOL_ORB_INSERT_REMOVE_ALLOCATED_ACTIVITIES = "Allocated Activities"; //$NON-NLS-1$
  String TOOL_ORB_INSERT_REMOVE_ALL_ALLOCATED_ACTIVITIES = "All Allocated Activities"; //$NON-NLS-1$

  // Tool ID
  String TOOL_ORB_INSERT_OPERATIONAL_ROLE = "Insert Operational Role"; //$NON-NLS-1$
  String TOOL_ORB_SHOW_HIDE_ALLOCATED_ACTIVITIES = "Show/Hide Allocated Activities"; //$NON-NLS-1$
  String TOOL_ORB_INSERT_ALL_ALLOCATED_ACTIVITIES = "Insert All Allocated Activities"; //$NON-NLS-1$
  String TOOL_ORB_SHOW_HIDE_INTERACTION = "Show/Hide Interaction"; //$NON-NLS-1$

  // PAB tools - Physical Architecture Blank
  // Node
  String TOOL_PAB_CREATE_NODE_PHYSICAL_COMPONENT = "Node PC"; //$NON-NLS-1$
  String TOOL_PAB_DEPLOY_NODE_PC = "Deploy Node PC"; //$NON-NLS-1$
  String TOOL_PAB_CREATE_PHYSICAL_ACTOR = "Physical Actor"; //$NON-NLS-1$
  String TOOL_PAB_CREATE_PHYSICAL_LINK = "Physical Link"; //$NON-NLS-1$
  String TOOL_PAB_CREATE_PHYSICAL_PORT = "Physical Port"; //$NON-NLS-1$
  String TOOL_PAB_MANAGE_NODE_COMP_DEPLOYMENT = "Manage Node PCs Deployment"; //$NON-NLS-1$
  @Deprecated
  String TOOL_PAB_INSERT_REMOVE_NODE_PCS = "Node PCs"; //$NON-NLS-1$
  String TOOL_PAB_INSERT_REMOVE_ACTORS = "Actors"; //$NON-NLS-1$
  String TOOL_PAB_INSERT_REMOVE_DEPLOYED_PCS = "Deployed PCs"; //$NON-NLS-1$
  String TOOL_PAB_INSERT_REMOVE_PHYSICAL_LINKS = "Physical Links"; //$NON-NLS-1$
  String TOOL_PAB_CREATE_COMPONENT_PORT_ALLOCATION = "Component Port Allocation"; //$NON-NLS-1$
  String TOOL_PAB_INSERT_REMOVE_PHYSICAL_PATH = "Physical Path"; //$NON-NLS-1$
  String TOOL_PAB_INSERT_REMOVE_PHYSICAL_LINKS_CATEGORIES = "Switch Physical Links / Categories"; //$NON-NLS-1$
  String TOOL_PAB_CREATE_CONSTRAINT = "Constraint"; //$NON-NLS-1$
  String TOOL_PAB_CREATE_CONSTRAINT_ELEMENT = "ConstraintElement"; //$NON-NLS-1$
  String TOOL_PAB_INSERT_REMOVE_CONSTRAINTS = "Constraints"; //$NON-NLS-1$
  // Behavior
  String TOOL_PAB_DEPLOY_BEHAVIOR_PC = "Deploy Behavior PC"; //$NON-NLS-1$
  String TOOL_PAB_CREATE_BEHAVIOR_PHYSICAL_COMPONENT = "Behavior PC"; //$NON-NLS-1$
  String TOOL_PAB_CREATE_COMPONENT_EXCHANGE = "Component Exchange"; //$NON-NLS-1$
  String TOOL_PAB_CREATE_COMPONENT_EXCHANGE_WITH_DELEGATIONS = "Component Exchange with Delegations"; //$NON-NLS-1$
  String TOOL_PAB_CREATE_DELEGATION = "Delegation"; //$NON-NLS-1$
  String TOOL_PAB_CREATE_INFLOW_PORT = "In Flow Port"; //$NON-NLS-1$
  String TOOL_PAB_CREATE_OUTFLOW_PORT = "Out Flow Port"; //$NON-NLS-1$
  String TOOL_PAB_CREATE_INOUTFLOW_PORT = "InOut Flow Port"; //$NON-NLS-1$
  String TOOL_PAB_CREATE_STANDARD_PORT = "Standard Port"; //$NON-NLS-1$
  String TOOL_PAB_MANAGE_BEHAVIOR_COMP_DEPLOYMENT = "Manage Behavior PCs Deployment"; //$NON-NLS-1$

  @Deprecated
  String TOOL_PAB_INSERT_REMOVE_BEHAVIOR_PCS = "Behavior PCs"; //$NON-NLS-1$
  String TOOL_PAB_INSERT_ALL_DEPLOYED_PCS = "All Deployed PCs"; //$NON-NLS-1$
  String TOOL_PAB_INSERT_REMOVE_COMPONENT_EXCHANGES = "Component Exchanges"; //$NON-NLS-1$
  String TOOL_PAB_INSERT_REMOVE_COMPONENT_EXCHANGES_CATEGORIES = "Switch Component Exchanges / Categories"; //$NON-NLS-1$
  // Functions
  String TOOL_PAB_CREATE_PHYSICAL_FUNCTION = "Physical Function"; //$NON-NLS-1$
  String TOOL_PAB_CREATE_DUPLICATE = "Duplicate"; //$NON-NLS-1$
  String TOOL_PAB_CREATE_GATHER = "Gather"; //$NON-NLS-1$
  String TOOL_PAB_CREATE_ROUTE = "Route"; //$NON-NLS-1$
  String TOOL_PAB_CREATE_SELECT = "Select"; //$NON-NLS-1$
  String TOOL_PAB_CREATE_SPLIT = "Split"; //$NON-NLS-1$
  String TOOL_PAB_CREATE_FUNCTIONAL_EXCHANGE = "Functional Exchange"; //$NON-NLS-1$
  String TOOL_PAB_CREATE_FUNCTION_INPUT_PORT = "Input Port"; //$NON-NLS-1$
  String TOOL_PAB_CREATE_FUNCTION_OUTPUT_PORT = "Output Port"; //$NON-NLS-1$
  String TOOL_PAB_CREATE_PORT_ALLOCATION = "Port Allocation"; //$NON-NLS-1$
  String TOOL_PAB_MANAGE_FUNCTION_ALLOCATION = "Manage Function Allocation"; //$NON-NLS-1$
  String TOOL_PAB_INSERT_REMOVE_ALLOCATED_FUNCTIONS = "Allocated Functions"; //$NON-NLS-1$
  String TOOL_PAB_INSERT_REMOVE_ALL_ALLOCATED_FUNCTIONS = "All Allocated Functions"; //$NON-NLS-1$
  String TOOL_PAB_INSERT_REMOVE_FUNCTIONAL_CHAINS = "Functional Chains"; //$NON-NLS-1$
  String TOOL_PAB_INSERT_REMOVE_FUNCTIONAL_EXCHANGES = "Functional Exchanges"; //$NON-NLS-1$
  String TOOL_PAB_INSERT_REMOVE_EXCHANGE_CATEGORIES = "Switch Functional Exchanges / Categories"; //$NON-NLS-1$
  String TOOL_PAB_INSERT_SCENARIO_ELEMENTS = TOOL_LAB_INSERT_SCENARIO_ELEMENTS;
  String TOOL_PAB_INSERT_STATEMODE_ELEMENTS = TOOL_LAB_INSERT_STATEMODE_ELEMENTS;
  String TOOL_PAB_INSERT_REMOVE_COMPONENT_PORT_ALLOCATION = "Component Port Allocations";

  // Unsynchronized tools
  String TOOL_PAB_INSERT_REMOVE_PORTS = "Ports"; //$NON-NLS-1$
  String TOOL_PAB_INSERT_REMOVE_FUNCTION_PORTS = "Function Ports"; //$NON-NLS-1$
  String TOOL_PAB_INSERT_REMOVE_CATEGORIES = "Categories"; //$NON-NLS-1$
  // multi parts tools
  String TOOL_PAB_REUSE_PC = "Reuse PC"; //$NON-NLS-1$
  String TOOL_PAB_REUSE_NODE_PC = "Reuse Node PC"; //$NON-NLS-1$
  String TOOL_PAB_REUSE_BEHAVIOR_PC = "Reuse Behavior PC"; //$NON-NLS-1$
  String TOOL_PAB_REUSE_PHYSICAL_ACTOR = "Reuse Physical Actor"; //$NON-NLS-1$
  String TOOL_PAB_CREATE_COMPONENT_EXCHANGE_WITH_PORTS = "Component Exchange with ports"; //$NON-NLS-1$
  String TOOL_PAB_CREATE_COMPONENT_EXCHANGE_WITHOUT_PORTS = "Component Exchange without ports"; //$NON-NLS-1$
  String TOOL_PAB_CREATE_COMPONENT_EXCHANGE_BETWEEN_TYPES = "Component Exchange between types"; //$NON-NLS-1$

  // Others - tools id
  String TOOL_PAB_SHOW_HIDE_DEPLOYED_COMPONENT = "Show/Hide Deployed Components"; //$NON-NLS-1$
  String TOOL_PAB_SHOW_HIDE_COMPONENT_EXCHANGES = "Show/Hide ComponentExchanges"; //$NON-NLS-1$
  String TOOL_PAB_SHOW_HIDE_PHYSICAL_LINKS = "Show/Hide Physical Links"; //$NON-NLS-1$
  String TOOL_PAB_SHOW_HIDE_PORTS = "Show/Hide Ports"; //$NON-NLS-1$
  String TOOL_PAB_SHOW_HIDE_FUNCTION_PORTS = "Show/Hide Function Ports"; //$NON-NLS-1$
  String TOOL_PAB_CREATE_FUNCTIONAL_CHAIN = "Functional Chain"; //$NON-NLS-1$
  String TOOL_PAB_CREATE_PHYSICAL_PATH = "Physical Path"; //$NON-NLS-1$
  String TOOL_PAB_INSERT_PHYSICAL_ACTOR = "Show/Hide Actors"; //$NON-NLS-1$
  String TOOL_PAB_SHOW_HIDE_CONSTRAINT = "Show/Hide Constraints"; //$NON-NLS-1$
  String TOOL_PAB_SHOW_HIDE_FUNCTIONAL_CHAINS = "Show/Hide Functional Chains"; //$NON-NLS-1$
  String TOOL_PAB_SHOW_HIDE_FUNCTIONAL_EXCHANGES = "Show/Hide Functional Exchanges"; //$NON-NLS-1$
  String TOOL_PAB_RECONNECT_PHYSICALLINK_TARGET = "Reconnect PhysicalLink Target"; //$NON-NLS-1$
  String TOOL_PAB_RECONNECT_PHYSICALLINK_SOURCE = "Reconnect PhysicalLink Source"; //$NON-NLS-1$
  String TOOL_PAB_RECONNECT_COMPONENTEXCHANGE_TARGET = "Reconnect ComponentExchange Target"; //$NON-NLS-1$
  String TOOL_PAB_RECONNECT_COMPONENTEXCHANGE_SOURCE = "Reconnect ComponentExchange Source"; //$NON-NLS-1$
  String TOOL_PAB_RECONNECT_FUNCTION_EXCHANGE = "PAB Reconnect Function Exchanges"; //$NON-NLS-1$
  String TOOL_PAB_CREATE_INPUT_PORT = "Input Port"; //$NON-NLS-1$
  String TOOL_PAB_CREATE_OUTPUT_PORT = "Output Port"; //$NON-NLS-1$
  String TOOL_PAB_CREATE_INOUT_FLOW_PORT_PORT = "InOut Flow Port"; //$NON-NLS-1$
  String TOOL_PAB_SHOW_HIDE_EXCHANGE_CATEGORIES = "Show/Hide Exchange Categories"; //$NON-NLS-1$
  String TOOL_PAB_SWITCH_COMPONENT_CATEGORIES = "Switch Component Categories"; //$NON-NLS-1$
  String TOOL_PAB_SWITCH_PHYSICAL_CATEGORIES = "Switch Physical Categories"; //$NON-NLS-1$

  String TOOL_PAB_INSERT_FUNCTIONS_FROM_MODE_STATE = "Functions from Mode / State"; //$NON-NLS-1$
  String TOOL_PAB_INSERT_ELEMENTS_FROM_SCENARIO = "Elements from Scenario"; //$NON-NLS-1$
  String TOOL_PAB_STATE_MODE_ELEMENTS_ID = "State-Mode Elements"; //$NON-NLS-1$
  String TOOL_PAB_SCENARIO_ELEMENTS_ID = "Scenario Elements"; //$NON-NLS-1$
  // Accelerators
  String TOOL_PAB_INITIALIZATION_FROM_EXISTING_DIAGRAM = "Initialization from existing diagram"; //$NON-NLS-1$

  // PCBD tools - Physical Component Breakdown
  String TOOL_PCBD_CREATE_NODE_PC = "Node PC"; //$NON-NLS-1$
  String TOOL_PCBD_CREATE_BEHAVIOR_PC = "Behavior PC"; //$NON-NLS-1$
  String TOOL_PCBD_CONTAINED_IN = "Contained In"; //$NON-NLS-1$
  String TOOL_PCBD_CREATE_CONSTRAINT = "Constraint"; //$NON-NLS-1$
  String TOOL_PCBD_CREATE_CONSTRAINTELEMENT = "ConstraintElement"; //$NON-NLS-1$
  String TOOL_PCBD_INSERT_REMOVE_CONSTRAINTS = "Constraints"; //$NON-NLS-1$
  // Multi parts tools
  String TOOL_PCBD_CREATE_PART = "Create Part"; //$NON-NLS-1$

  // PD tools - Package Dependencies
  String TOOL_PD_INSERT_REMOVE_DATA_PACKAGES = "Data Packages"; //$NON-NLS-1$
  String TOOL_PD_INSERT_REMOVE_INTERFACE_PACKAGES = "Interface Packages"; //$NON-NLS-1$
  String TOOL_PD_INSERT_REMOVE_DEPENDENCIES = "Dependencies"; //$NON-NLS-1$
  String TOOL_PD_CREATE_CONSTRAINT = "Constraint"; //$NON-NLS-1$
  String TOOL_PD_CREATE_CONSTRAINTELEMENT = "ConstraintElement"; //$NON-NLS-1$
  String TOOL_PD_INSERT_REMOVE_CONSTRAINTS = "Constraints"; //$NON-NLS-1$

  // tools ids
  String TOOL_PD_SHOW_HIDE_DATAPKGS = "Show/Hide DataPkgs"; //$NON-NLS-1$
  String TOOL_PD_SHOW_HIDE_INTERFACEPKGS = "Show/Hide InterfacePkgs"; //$NON-NLS-1$
  String TOOL_PD_SHOW_HIDE_DEPENDENT_PACKAGES = "Show/Hide Dependent Packages"; //$NON-NLS-1$

  // PDFB tools - Physical Data Flow Blank tools
  String TOOL_PDFB_CREATE_PHYSICAL_FUNCTION = "Physical Function"; //$NON-NLS-1$
  String TOOL_PDFB_CREATE_ACTOR_FUNCTION = "Actor Function"; //$NON-NLS-1$
  String TOOL_PDFB_CREATE_DUPLICATE = "Duplicate"; //$NON-NLS-1$
  String TOOL_PDFB_CREATE_GATHER = "Gather"; //$NON-NLS-1$
  String TOOL_PDFB_CREATE_ROUTE = "Route"; //$NON-NLS-1$
  String TOOL_PDFB_CREATE_SELECT = "Select"; //$NON-NLS-1$
  String TOOL_PDFB_CREATE_SPLIT = "Split"; //$NON-NLS-1$
  String TOOL_PDFB_CREATE_INPUT_PORT = "Input Port"; //$NON-NLS-1$
  String TOOL_PDFB_CREATE_OUTPUT_PORT = "Output Port"; //$NON-NLS-1$
  String TOOL_PDFB_CREATE_FUNCTIONAL_EXCHANGE = "Functional Exchange"; //$NON-NLS-1$
  String TOOL_PDFB_INSERT_FUNCTIONS = "Functions"; //$NON-NLS-1$
  String TOOL_PDFB_INSERT_REMOVE_ACTOR_FUNCTIONS = "Actor Functions"; //$NON-NLS-1$
  String TOOL_PDFB_INSERT_REMOVE_FUNCTIONAL_EXCHANGES = "Functional Exchanges"; //$NON-NLS-1$
  String TOOL_PDFB_INSERT_REMOVE_FUNCTION_PORTS = "Function Ports"; //$NON-NLS-1$
  String TOOL_PDFB_INSERT_REMOVE_EXCHANGE_CATEGORIES = "Exchange Categories"; //$NON-NLS-1$
  String TOOL_PDFB_INSERT_REMOVE_FUNCTIONAL_CHAINS = "Functional Chains"; //$NON-NLS-1$
  String TOOL_PDFB_CREATE_FUNCTIONAL_CHAIN_ELEMENTS = "Functional Chain Elements"; //$NON-NLS-1$
  String TOOL_PDFB_CREATE_CONSTRAINT = "Constraint"; //$NON-NLS-1$
  String TOOL_PDFB_CREATE_CONSTRAINT_ELEMENT = "ConstraintElement"; //$NON-NLS-1$
  String TOOL_PDFB_INSERT_REMOVE_CONSTRAINTS = "Constraints"; //$NON-NLS-1$
  String TOOL_PDFB_INSERT_SCENARIO_ELEMENTS = TOOL_LDFB_INSERT_SCENARIO_ELEMENTS;
  String TOOL_PDFB_INSERT_STATEMODE_ELEMENTS = TOOL_LDFB_INSERT_STATEMODE_ELEMENTS;

  String TOOL_PDFB_CREATE_FUNCTIONAL_CHAIN = "Functional Chain"; //$NON-NLS-1$
  String TOOL_PDFB_SHOW_HIDE_FUNCTIONAL_CHAIN = "Show/Hide Functional Chains"; //$NON-NLS-1$
  String TOOL_PDFB_SHOW_HIDE_FUNCTIONAL_EXCH_CATEGORIES = "Switch Functional Exchanges / Categories"; //$NON-NLS-1$
  String TOOL_PDFB_SHOW_HIDE_EXCH_CATEGORIES = "Show/Hide Exchange Categories"; //$NON-NLS-1$
  String TOOL_PDFB_SHOW_HIDE_FUNCTIONS = "Show/Hide Functions"; //$NON-NLS-1$
  String TOOL_PDFB_SHOW_HIDE_ACTOR_FUNCTIONS = "Show/Hide Actor Functions"; //$NON-NLS-1$
  String TOOL_PDFB_SHOW_HIDE_FUNCTIONAL_EXCHANGES = "Show/Hide Functional Exchanges"; //$NON-NLS-1$
  String TOOL_PDFB_SHOW_HIDE_FUNCTION_PORTS = "Show/Hide Function Ports"; //$NON-NLS-1$

  String TOOL_PDFB_INSERT_FUNCTIONS_FROM_MODE_STATE = "Functions from Mode / State"; //$NON-NLS-1$
  String TOOL_PDFB_INSERT_ELEMENTS_FROM_SCENARIO = "Elements from Scenario"; //$NON-NLS-1$
  String TOOL_PDFB_STATE_MODE_ELEMENTS_ID = "State-Mode Elements"; //$NON-NLS-1$
  String TOOL_PDFB_SCENARIO_ELEMENTS_ID = "Scenario Elements"; //$NON-NLS-1$
  // Accelerators
  String TOOL_PDFB_INITIALIZATION_FROM_EXISTING_DIAGRAM = "Initialization from existing diagram"; //$NON-NLS-1$

  // PFBD tools - Physical Function Breakdown
  String TOOL_PFBD_CREATE_PHYSICAL_FUNCTION = "Physical Function"; //$NON-NLS-1$
  String TOOL_PFBD_CREATE_DUPLICATE = "Duplicate"; //$NON-NLS-1$
  String TOOL_PFBD_CREATE_GATHER = "Gather"; //$NON-NLS-1$
  String TOOL_PFBD_CREATE_ROUTE = "Route"; //$NON-NLS-1$
  String TOOL_PFBD_CREATE_SELECT = "Select"; //$NON-NLS-1$
  String TOOL_PFBD_CREATE_SPLIT = "Split"; //$NON-NLS-1$
  String TOOL_PFBD_CONTAINED_IN = "Contained In"; //$NON-NLS-1$
  String TOOL_PFBD_CREATE_CONSTRAINT = "Constraint"; //$NON-NLS-1$
  String TOOL_PFBD_CREATE_CONSTRAINTELEMENT = "ConstraintElement"; //$NON-NLS-1$
  String TOOL_PFBD_INSERT_REMOVE_CONSTRAINTS = "Constraints"; //$NON-NLS-1$

  // tool id
  String TOOL_PDFB_RECONNECT_EXCHANGE = "PDFB Reconnect Function Exchanges"; //$NON-NLS-1$

  // PPD tools - Physical Path Description
  String TOOL_PPD_INSERT_REMOVE_INVOLVE_COMPONENT = "Involve Component"; //$NON-NLS-1$
  String TOOL_PPD_INSERT_REMOVE_INVOLVE_PHYSICAL_PATH = "Involve Physical Path"; //$NON-NLS-1$
  String TOOL_PPD_INSERT_REMOVE_INVOLVE_PHYSICAL_LINK = "Involve Physical Link"; //$NON-NLS-1$
  String TOOL_PPD_INSERT_REMOVE_INVOLVE_PHYSICAL_LINK_AND_TARGET_COMPONENT = "Involve Physical Link & Target Component"; //$NON-NLS-1$
  String TOOL_PPD_INSERT_REMOVE_INVOLVE_PHYSICAL_LINK_AND_TARGET_PHYSICAL_PATH = "Involve Physical Link & Target Physical Path"; //$NON-NLS-1$
  String TOOL_PPD_CREATE_CONSTRAINT = "Constraint"; //$NON-NLS-1$
  String TOOL_PPD_CREATE_CONSTRAINTELEMENT = "ConstraintElement"; //$NON-NLS-1$
  String TOOL_PPD_INSERT_REMOVE_CONSTRAINTS = "Constraints"; //$NON-NLS-1$
  // multi parts tools
  String TOOL_PPD_INSERT_REMOVE_INVOLVE_PART = "Involve Part"; //$NON-NLS-1$
  String TOOL_PPD_INSERT_REMOVE_INVOLVE_PHYSICAL_LINK_AND_TARGET_PART = "Involve Physical Link & Target Part"; //$NON-NLS-1$

  // SAB Tools - System Architecture Blank
  // Components
  String TOOL_SAB_CREATE_ACTOR = "Actor"; //$NON-NLS-1$
  String TOOL_SAB_CREATE_IN_FLOW_PORT = "In Flow Port"; //$NON-NLS-1$
  String TOOL_SAB_CREATE_OUT_FLOW_PORT = "Out Flow Port"; //$NON-NLS-1$
  String TOOL_SAB_CREATE_INOUT_FLOW_PORT = "InOut Flow Port"; //$NON-NLS-1$
  String TOOL_SAB_CREATE_STANDARD_PORT = "Standard Port"; //$NON-NLS-1$

  // WARNING: label and id tool are different and are used in different tests
  String TOOL_SAB_CREATE_COMPONENT_EXCHANGE_LABEL = "Component Exchange"; //$NON-NLS-1$
  String TOOL_SAB_CREATE_PHYSICAL_LINK = "Physical Link"; //$NON-NLS-1$
  String TOOL_SAB_CREATE_PHYSICAL_PORT = "Physical Port"; //$NON-NLS-1$
  String TOOL_SAB_INSERT_REMOVE_ACTORS = "Actors"; //$NON-NLS-1$
  @Deprecated
  String TOOL_SAB_INSERT_REMOVE_COMPONENT_EXCHANGES = "Component Exchanges"; //$NON-NLS-1$
  @Deprecated
  String TOOL_SAB_INSERT_REMOVE_PHYSICAL_LINKS = "Physical Links"; //$NON-NLS-1$
  String TOOL_SAB_INSERT_REMOVE_PHYSICAL_PATH = "Physical Path"; //$NON-NLS-1$
  String TOOL_SAB_INSERT_REMOVE_PHYSICAL_LINKS_CATEGORIES = "Switch Physical Links / Categories"; //$NON-NLS-1$
  String TOOL_SAB_CREATE_CONSTRAINT = "Constraint"; //$NON-NLS-1$
  String TOOL_SAB_CREATE_CONSTRAINT_ELEMENT = "ConstraintElement"; //$NON-NLS-1$
  String TOOL_SAB_INSERT_REMOVE_CONSTRAINTS = "Constraints"; //$NON-NLS-1$
  // Functions
  String TOOL_SAB_CREATE_SYSTEM_FUNCTION = "System Function"; //$NON-NLS-1$
  String TOOL_SAB_CREATE_DUPLICATE = "Duplicate"; //$NON-NLS-1$
  String TOOL_SAB_CREATE_GATHER = "Gather"; //$NON-NLS-1$
  String TOOL_SAB_CREATE_ROUTE = "Route"; //$NON-NLS-1$
  String TOOL_SAB_CREATE_SELECT = "Select"; //$NON-NLS-1$
  String TOOL_SAB_CREATE_SPLIT = "Split"; //$NON-NLS-1$
  String TOOL_SAB_CREATE_FUNCTIONAL_EXCHANGE = "Functional Exchange"; //$NON-NLS-1$
  String TOOL_SAB_CREATE_FUNCTION_INPUT_PORT = "Input Port"; //$NON-NLS-1$
  String TOOL_SAB_CREATE_FUNCTION_OUTPUT_PORT = "Output Port"; //$NON-NLS-1$
  String TOOL_SAB_CREATE_PORT_ALLOCATION = "Port Allocation"; //$NON-NLS-1$
  String TOOL_SAB_MANAGE_FUNCTION_ALLOCATION = "Manage Function Allocation"; //$NON-NLS-1$
  String TOOL_SAB_INSERT_REMOVE_ALLOCATED_FUNCTIONS = "Allocated Functions"; //$NON-NLS-1$
  String TOOL_SAB_INSERT_REMOVE_ALL_ALLOCATED_FUNCTIONS = "All Allocated Functions"; //$NON-NLS-1$
  String TOOL_SAB_INSERT_REMOVE_FUNCTIONAL_CHAINS = "Functional Chains"; //$NON-NLS-1$
  String TOOL_SAB_INSERT_REMOVE_FUNCTIONAL_EXCHANGES = "Functional Exchanges"; //$NON-NLS-1$
  String TOOL_SAB_INSERT_REMOVE_EXCHANGE_CATEGORIES = "Switch Functional Exchanges / Categories"; //$NON-NLS-1$
  String TOOL_SAB_INSERT_SCENARIO_ELEMENTS = TOOL_LAB_INSERT_SCENARIO_ELEMENTS;
  String TOOL_SAB_INSERT_STATEMODE_ELEMENTS = TOOL_LAB_INSERT_STATEMODE_ELEMENTS;

  String TOOL_SAB_INSERT_FUNCTIONS_FROM_MODE_STATE = "Functions from Mode / State"; //$NON-NLS-1$
  String TOOL_SAB_INSERT_ELEMENTS_FROM_SCENARIO = "Elements from Scenario"; //$NON-NLS-1$
  String TOOL_SAB_STATE_MODE_ELEMENTS_ID = "State-Mode Elements"; //$NON-NLS-1$
  String TOOL_SAB_SCENARIO_ELEMENTS_ID = "Scenario Elements"; //$NON-NLS-1$
  String TOOL_SAB_INSERT_REMOVE_CATEGORIES = "Categories"; //$NON-NLS-1$
  String TOOL_SAB_INSERT_REMOVE_COMPONENT_EXCHANGES_CATEGORIES = "Switch Component Exchanges / Categories"; //$NON-NLS-1$
  // Accelerators
  String TOOL_SAB_INITIALIZATION_FROM_EXISTING_DIAGRAM = "Initialization from existing diagram"; //$NON-NLS-1$
  // Unsynchronized tools
  String TOOL_SAB_INSERT_REMOVE_FUNCTION_PORTS = "Function Ports"; //$NON-NLS-1$
  String TOOL_SAB_INSERT_REMOVE_PORTS = "Component Ports"; //$NON-NLS-1$
  // Multi parts tools
  String TOOL_SAB_REUSE_ACTOR = "Reuse Actor"; //$NON-NLS-1$
  String TOOL_SAB_CREATE_COMPONENT_EXCHANGE_WITH_PORTS = "Component Exchange with ports"; //$NON-NLS-1$
  String TOOL_SAB_CREATE_COMPONENT_EXCHANGE_WITHOU_PORTS = "Component Exchange without ports"; //$NON-NLS-1$
  String TOOL_SAB_CREATE_COMPONENT_EXCHANGE_BETWEEN_TYPES = "Component Exchange between types"; //$NON-NLS-1$

  // tools id
  String TOOL_SAB_CREATE_INFLOW_PORT = "In Flow Port"; //$NON-NLS-1$
  String TOOL_SAB_CREATE_OUTFLOW_PORT = "Out Flow Port"; //$NON-NLS-1$
  String TOOL_SAB_INSERT_ACTOR = "Show/Hide Actors"; //$NON-NLS-1$
  String TOOL_SAB_CREATE_COMPONENT_EXCHANGE = "Connection with ports"; //$NON-NLS-1$

  String TOOL_SAB_SHOW_HIDE_COMPONENT_EXCHANGE = "Show/Hide Connections"; //$NON-NLS-1$
  String TOOL_LAB_SHOW_HIDE_COMPONENT_EXCHANGE = "Show/Hide Connections"; //$NON-NLS-1$
  String TOOL_PAB_SHOW_HIDE_COMPONENT_EXCHANGE = "Show/Hide ComponentExchanges"; //$NON-NLS-1$
  String TOOL_OAB_SHOW_HIDE_COMMUNICATION_MEAN = "Show/Hide Communication Mean"; //$NON-NLS-1$

  String TOOL_SAB_SHOW_HIDE_PHYSICAL_LINK = "Show/Hide Physical Links"; //$NON-NLS-1$
  String TOOL_LAB_SHOW_HIDE_PHYSICAL_LINK = "Show/Hide Physical Links"; //$NON-NLS-1$
  String TOOL_PAB_SHOW_HIDE_PHYSICAL_LINK = "Show/Hide Physical Links"; //$NON-NLS-1$

  String TOOL_SAB_SHOW_HIDE_PORTS = "Show/Hide Ports"; //$NON-NLS-1$
  String TOOL_SAB_SHOW_HIDE_CONSTRAINTS = "Show/Hide Constraints"; //$NON-NLS-1$
  String TOOL_SAB_CREATE_FUNCTIONAL_CHAIN = "Functional Chain"; //$NON-NLS-1$
  String TOOL_SAB_SHOW_HIDE_FUNCTIONAL_CHAINS = "Show/Hide Functional Chains"; //$NON-NLS-1$
  String TOOL_SAB_SHOW_HIDE_FUNCTIONAL_EXCHANGES = "Show/Hide Functional Exchanges"; //$NON-NLS-1$
  String TOOL_SAB_SHOW_HIDE_FUNCTION_PORTS = "Show/Hide Function Ports"; //$NON-NLS-1$
  String TOOL_SAB_RECONNECT_EXCHANGES_TARGET = "CA Reconnect Exchanges Target"; //$NON-NLS-1$
  String TOOL_SAB_RECONNECT_EXCHANGES_SOURCE = "CA Reconnect Exchanges Source"; //$NON-NLS-1$
  String TOOL_SAB_RECONNECT_FUNCTION_EXCHANGES = "SAB Reconnect Function Exchanges"; //$NON-NLS-1$

  String TOOL_SAB_RECONNECT_PHYSICALLINK_SOURCE_ID = "Reconnect PhysicalLink Source"; //$NON-NLS-1$
  String TOOL_SAB_RECONNECT_PHYSICALLINK_TARGET_ID = "Reconnect PhysicalLink Target"; //$NON-NLS-1$
  String TOOL_LAB_RECONNECT_PHYSICALLINK_TARGET_ID = "Reconnect PhysicalLink Target"; //$NON-NLS-1$
  String TOOL_LAB_RECONNECT_PHYSICALLINK_SOURCE_ID = "Reconnect PhysicalLink Source"; //$NON-NLS-1$

  // SDFB tools - System Data Flow Blank tools
  String TOOL_SDFB_CREATE_SYSTEM_FUNCTION = "System Function"; //$NON-NLS-1$
  String TOOL_SDFB_CREATE_ACTOR_FUNCTION = "Actor Function"; //$NON-NLS-1$
  String TOOL_SDFB_CREATE_OUTPUT_PORT = "Output Port"; //$NON-NLS-1$
  String TOOL_SDFB_CREATE_INPUT_PORT = "Input Port"; //$NON-NLS-1$
  String TOOL_SDFB_CREATE_DUPLICATE = "Duplicate"; //$NON-NLS-1$
  String TOOL_SDFB_CREATE_GATHER = "Gather"; //$NON-NLS-1$
  String TOOL_SDFB_CREATE_ROUTE = "Route"; //$NON-NLS-1$
  String TOOL_SDFB_CREATE_SELECT = "Select"; //$NON-NLS-1$
  String TOOL_SDFB_CREATE_SPLIT = "Split"; //$NON-NLS-1$
  String TOOL_SDFB_CREATE_FUNCTIONAL_EXCHANGE = "Functional Exchange"; //$NON-NLS-1$
  String TOOL_SDFB_CREATE_FUNCTIONAL_CHAIN = "Functional Chain"; //$NON-NLS-1$
  String TOOL_SDFB_RECONNECT_EXCHANGE = "SDFB Reconnect Exchanges"; //$NON-NLS-1$
  String TOOL_SDFB_SHOW_HIDE_EXCH_CATEGORIES = "Show/Hide Exchange Categories"; //$NON-NLS-1$
  String TOOL_SDFB_SHOW_HIDE_FUNCTIONS = "Show/Hide Functions"; //$NON-NLS-1$
  String TOOL_SDFB_SHOW_HIDE_ACTOR_FUNCTIONS = "Show/Hide Actor Functions"; //$NON-NLS-1$
  String TOOL_SDFB_SHOW_HIDE_FUNCTIONAL_CHAIN = "Show/Hide Functional Chains"; //$NON-NLS-1$
  String TOOL_SDFB_SHOW_HIDE_FUNCTIONAL_EXCH_CATEGORIES = "Switch Functional Exchanges / Categories"; //$NON-NLS-1$
  String TOOL_SDFB_SHOW_HIDE_FUNCTIONAL_EXCHANGES = "Show/Hide Functional Exchanges"; //$NON-NLS-1$
  String TOOL_SDFB_SHOW_HIDE_FUNCTION_PORTS = "Show/Hide Function Ports"; //$NON-NLS-1$
  String TOOL_SDFB_INSERT_SCENARIO_ELEMENTS = TOOL_LDFB_INSERT_SCENARIO_ELEMENTS;
  String TOOL_SDFB_INSERT_STATEMODE_ELEMENTS = TOOL_LDFB_INSERT_STATEMODE_ELEMENTS;
  String TOOL_SDFB_DND_SYSTEM_FUNCTION = "DnD DF AbstractFunction";
  String TOOL_SDFB_RECONNECT_EXCHANGES = "SDFB Reconnect Exchanges";

  // SDFB tool constants added
  String TOOL_SDFB_INSERT_REMOVE_FUNCTIONS = "Functions"; //$NON-NLS-1$
  String TOOL_SDFB_INSERT_REMOVE_ACTOR_FUNCTIONS = "Actor Functions"; //$NON-NLS-1$
  String TOOL_SDFB_INSERT_REMOVE_FUNCTIONAL_EXCHANGES = "Functional Exchanges"; //$NON-NLS-1$
  String TOOL_SDFB_INSERT_REMOVE_FUNCTIONAL_CHAINS = "Functional Chains"; //$NON-NLS-1$
  String TOOL_SDFB_CREATE_FUNCTIONAL_CHAIN_ELEMENTS = "Functional Chain Elements"; //$NON-NLS-1$
  String TOOL_SDFB_CREATE_CONSTRAINT = "Constraint"; //$NON-NLS-1$
  String TOOL_SDFB_CREATE_CONSTRAINT_ELEMENT = "ConstraintElement"; //$NON-NLS-1$
  String TOOL_SDFB_INSERT_REMOVE_CONSTRAINTS = "Constraints"; //$NON-NLS-1$

  String TOOL_SDFB_INSERT_FUNCTIONS_FROM_MODE_STATE = "Functions from Mode / State"; //$NON-NLS-1$
  String TOOL_SDFB_INSERT_ELEMENTS_FROM_SCENARIO = "Elements from Scenario"; //$NON-NLS-1$
  String TOOL_SDFB_STATE_MODE_ELEMENTS_ID = "State-Mode Elements"; //$NON-NLS-1$
  String TOOL_SDFB_SCENARIO_ELEMENTS_ID = "Scenario Elements"; //$NON-NLS-1$
  // Accelerators
  String TOOL_SDFB_INITIALIZATION_FROM_EXISTING_DIAGRAM = "Initialization from existing diagram"; //$NON-NLS-1$
  // Unsynchronized tools
  String TOOL_SDFB_INSERT_REMOVE_FUNCTION_PORTS = "Function Ports"; //$NON-NLS-1$
  String TOOL_SDFB_INSERT_REMOVE_EXCHANGE_CATEGORIES = "Exchange Categories"; //$NON-NLS-1$
  String TOOL_SDFB_EXCHANGE_CONTEXT = "Exchange Context"; //$NON-NLS-1$

  // SFBD tools - System Function Breakdown
  String TOOL_SFBD_CREATE_SYSTEM_FUNCTION = "System Function";//$NON-NLS-1$
  String TOOL_SFBD_CREATE_DUPLICATE = "Duplicate"; //$NON-NLS-1$
  String TOOL_SFBD_CREATE_GATHER = "Gather"; //$NON-NLS-1$
  String TOOL_SFBD_CREATE_ROUTE = "Route"; //$NON-NLS-1$
  String TOOL_SFBD_CREATE_SELECT = "Select"; //$NON-NLS-1$
  String TOOL_SFBD_CREATE_SPLIT = "Split"; //$NON-NLS-1$
  String TOOL_SFBD_CONTAINED_IN = "Contained In"; //$NON-NLS-1$
  String TOOL_SFBD_CREATE_CONSTRAINT = "Constraint"; //$NON-NLS-1$
  String TOOL_SFBD_CREATE_CONSTRAINTELEMENT = "ConstraintElement"; //$NON-NLS-1$
  String TOOL_SFBD_INSERT_REMOVE_CONSTRAINTS = "Constraints"; //$NON-NLS-1$

  // System (ex-CTX layer) Interface Diagram Blank (SIDB) tools
  String TOOL_SIDB_SHOW_HIDE_COMPONENTS = "Show/Hide Components"; //$NON-NLS-1$
  String TOOL_SIDB_CREATE_ACTOR = "Actor"; //$NON-NLS-1$
  String TOOL_SIDB_CREATE_ECHANGE_ITEM_EVENT = "Event"; //$NON-NLS-1$
  String TOOL_SIDB_CREATE_ECHANGE_ITEM_OPERATION = "Operation"; //$NON-NLS-1$
  String TOOL_SIDB_CREATE_ECHANGE_ITEM_FLOW = "Flow"; //$NON-NLS-1$
  String TOOL_SIDB_CREATE_ECHANGE_ITEM_DATA = "Data"; //$NON-NLS-1$
  String TOOL_SIDB_CREATE_UNDEFINED_ECHANGE_ITEM = "Undefined Exchange Item"; //$NON-NLS-1$
  String TOOL_SIDB_CREATE_INTERFACE = "Interface"; //$NON-NLS-1$
  String TOOL_SIDB_CREATE_COMMUNICATION_LINK_TRANSMIT = "CommunicationLink Send / Produce / Call / Write / Transmit"; //$NON-NLS-1$
  String TOOL_SIDB_CREATE_COMMUNICATION_LINK_ACQUIRE = "CommunicationLink Receive / Consume / Execute / Access / Acquire"; //$NON-NLS-1$
  String TOOL_SIDB_MANAGE_EXCHANGE_ITEM_ALLOCATIONS = "Manage Exchange Item Allocations"; //$NON-NLS-1$
  String TOOL_SIDB_SHOW_HIDE_ACTORS = "Show/Hide Actors"; //$NON-NLS-1$
  String TOOL_SIDB_SHOW_HIDE_EXCHANGE_ITEMS = "Show/Hide Exchange Items"; //$NON-NLS-1$
  String TOOL_SIDB_SHOW_HIDE_ITEM_ALLOCATIONS = "Show/Hide Exchange Item Allocations"; //$NON-NLS-1$
  String TOOL_SIDB_SHOW_HIDE_EXCHANGE_ITEM_ELEMENTS = "Show/Hide Exchange Item Elements"; //$NON-NLS-1$
  String TOOL_SIDB_CREATE_EXCHANGE_ITEM_ELEMENT = "Exchange Item Element"; //$NON-NLS-1$

  // Common diagram tools id of LAB, PAB and SAB
  String TOOL_XAB_SHOW_HIDE_ALLOCATED_FUNCTIONS = "Show/Hide Allocated Functions"; //$NON-NLS-1$
  String TOOL_XAB_SHOW_ALL_ALLOCATED_FUNCTIONS = "Show All Allocated Functions"; //$NON-NLS-1$
  String TOOL_XAB_SHOW_HIDE_COMPONENTS = "Show/Hide Components"; //$NON-NLS-1$
  String TOOL_XAB_INSERT_REMOVE_COMPONENTS_MONOPART = "Insert/Remove Components mono part"; //$NON-NLS-1$
  String TOOL_PAB_INSERT_REMOVE_COMPONENTS_MONOPART = "Insert/remove Components mono part"; //$NON-NLS-1$

  //
  // Representation Descriptions moved to IDiagramNameConstants
  //

  // TABLE TOOLS - Currently there are only two operations available
  // 1. Create a cell value with "X" and delete the cell value -- which is an
  // empty string ""

  // FIXME -- IDiagramNameConstants.REQUIREMENTS_DIAGRAM_NAME required
  String REQUIREMENTS_DIAGRAM_NAME = "Requirements"; //$NON-NLS-1$
  String INTERFACES_SCENARIOS_DIAGRAM_NAME = "Interfaces - Scenarios"; //$NON-NLS-1$

  String TABLE_TOOL_CREATE_CELL_VALUE = "X"; //$NON-NLS-1$
  String TABLE_TOOL_DELETE_CELL_VALUE = ICommonConstants.EMPTY_STRING;

  String TABLE_TOOL_REQ_REQ_PKG = "Requirement Pkg"; //$NON-NLS-1$
  String TABLE_TOOL_REQ_SUR = "System User Requirement"; //$NON-NLS-1$
  String TABLE_TOOL_REQ_SFR = "System Functional Requirement"; //$NON-NLS-1$
  String TABLE_TOOL_REQ_SFIR = "System Functional Interface Requirement"; //$NON-NLS-1$
  String TABLE_TOOL_REQ_SNFR = "System Non Functional Requirement"; //$NON-NLS-1$
  String TABLE_TOOL_REQ_SNFIR = "System Non Functional Interface Requirement"; //$NON-NLS-1$

  /**
   * Tool on IS, ES diagrams to create multiple instance roles for identical represented
   */
  String TOOL_SCENARIO_MULTI_INSTANCEROLE_COMPONENT = "Add multiple lifelines for an existing Component"; //$NON-NLS-1$
  String TOOL_SCENARIO_MULTI_INSTANCEROLE_ACTOR = "Add multiple lifelines for an existing Actor"; //$NON-NLS-1$
  String TOOL_OES_MULTI_INSTANCEROLE_ENTITYACTOR = "Add multiple lifelines for an existing Entity / Actor"; //$NON-NLS-1$
  
  /*
   * XAB
   */
  String TOOL_XAB_CREATE_PHYSICAL_LINK = "Physical Link"; //$NON-NLS-1$
  String TOOL_XAB_CREATE_PHYSICAL_PORT = "Physical Port"; //$NON-NLS-1$
  String TOOL_XAB_RECONNECT_PHYSICALLINK_SOURCE_ID = "Reconnect PhysicalLink Source"; //$NON-NLS-1$
  String TOOL_XAB_RECONNECT_PHYSICALLINK_TARGET_ID = "Reconnect PhysicalLink Target"; //$NON-NLS-1$
  String TOOL_XAB_SHOW_HIDE_PHYSICAL_LINK = "Show/Hide Physical Links"; //$NON-NLS-1$
  String TOOL_XAB_CREATE_DELEGATION = "Delegation"; //$NON-NLS-1$
  String TOOL_XAB_CREATE_COMPONENT_EXCHANGE_WITH_DELEGATIONS = "Component Exchange with Delegations"; //$NON-NLS-1$
  String TOOL_XAB_CREATE_COMPONENT_EXCHANGE_WITH_PORTS = "Component Exchange with ports"; //$NON-NLS-1$
  String TOOL_XAB_CREATE_COMPONENT_EXCHANGE_WITHOUT_PORTS = "Component Exchange without ports"; //$NON-NLS-1$
  String TOOL_XAB_CREATE_COMPONENT_EXCHANGE_BETWEEN_TYPES = "Component Exchange between types"; //$NON-NLS-1$
  String TOOL_XAB_CREATE_FUNCTIONAL_EXCHANGE = "Functional Exchange"; //$NON-NLS-1$
  String TOOL_XAB_CREATE_INFLOW_PORT = "In Flow Port"; //$NON-NLS-1$
  String TOOL_XAB_CREATE_OUTFLOW_PORT = "Out Flow Port"; //$NON-NLS-1$
  String TOOL_XAB_CREATE_INOUT_FLOW_PORT = "InOut Flow Port"; //$NON-NLS-1$
  String TOOL_XAB_CREATE_STANDARD_PORT = "Standard Port"; //$NON-NLS-1$
  String TOOL_XAB_INSERT_ACTOR = "Show/Hide Actors"; //$NON-NLS-1$
  String TOOL_XAB_CREATE_FUNCTION_INPUT_PORT = "Input Port"; //$NON-NLS-1$
  String TOOL_XAB_CREATE_FUNCTION_OUTPUT_PORT = "Output Port"; //$NON-NLS-1$
  String TOOL_XAB_CREATE_PORT_ALLOCATION = "Port Allocation"; //$NON-NLS-1$
  String TOOL_XAB_INSERT_REMOVE_PORT_ALLOCATION = "Port Allocations"; //$NON-NLS-1$
  String TOOL_XAB_CREATE_DUPLICATE = "Duplicate"; //$NON-NLS-1$
  String TOOL_XAB_CREATE_GATHER = "Gather"; //$NON-NLS-1$
  String TOOL_XAB_CREATE_ROUTE = "Route"; //$NON-NLS-1$
  String TOOL_XAB_CREATE_SELECT = "Select"; //$NON-NLS-1$
  String TOOL_XAB_CREATE_SPLIT = "Split"; //$NON-NLS-1$
  String TOOL_XAB_MANAGE_FUNCTION_ALLOCATION = "Manage Function Allocation"; //$NON-NLS-1$
  String TOOL_XAB_INSERT_REMOVE_EXCHANGE_CATEGORIES = "Switch Functional Exchanges / Categories"; //$NON-NLS-1$
  String TOOL_XAB_INSERT_REMOVE_FUNCTIONAL_CHAINS = "Functional Chains"; //$NON-NLS-1$
  String TOOL_XAB_INSERT_REMOVE_FUNCTION_PORTS = "Function Ports"; //$NON-NLS-1$
  String TOOL_XAB_INSERT_REMOVE_CATEGORIES = "Categories"; //$NON-NLS-1$
  String TOOL_XAB_INSERT_ELEMENTS_FROM_SCENARIO = "Elements from Scenario"; //$NON-NLS-1$
  String TOOL_XAB_INSERT_FUNCTIONS_FROM_MODE_STATE = "Functions from Mode / State"; //$NON-NLS-1$
  String TOOL_XAB_INSERT_REMOVE_PHYSICAL_PATH = "Show/Hide Physical Path"; //$NON-NLS-1$
}
