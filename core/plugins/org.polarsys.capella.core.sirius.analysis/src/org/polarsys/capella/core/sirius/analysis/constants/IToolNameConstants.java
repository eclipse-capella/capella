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
package org.polarsys.capella.core.sirius.analysis.constants;

import org.polarsys.capella.common.mdsofa.common.constant.ICommonConstants;

/**
 * Centralizes constants about tools.
 */

@SuppressWarnings("nls")
public interface IToolNameConstants {

  // New Diagram Tools convention (tool name should be the same across all diagrams where the tool is available)
  // =================================================================================================

  String TOOL_CREATE_FUNCTION = "function";
  String TOOL_CREATE_FUNCTION_DUPLICATE = "duplicate";
  String TOOL_CREATE_FUNCTION_GATHER = "gather";
  String TOOL_CREATE_FUNCTION_ROUTE = "route";
  String TOOL_CREATE_FUNCTION_SELECT = "select";
  String TOOL_CREATE_FUNCTION_SPLIT = "split";
  String TOOL_CREATE_INPUT_PORT = "input.port";
  String TOOL_CREATE_OUTPUT_PORT = "output.port";
  String TOOL_CREATE_FUNCTIONAL_EXCHANGE = "functional.exchange";
  String TOOL_CREATE_FUNCTIONAL_CHAIN = "functional.chain";
  String TOOL_CREATE_CONSTRAINT = "constraint";
  String TOOL_CREATE_CONSTRAINT_ELEMENT = "constraint.element";
  String TOOL_CREATE_OPERATIONAL_ROLE = "operational.role";
  String TOOL_CREATE_OPERATIONAL_ACTIVITY = "operational.activity";
  String TOOL_CREATE_INTERACTION = "interaction";
  String TOOL_INSERT_REMOVE_FUNCTION_PORT = "function.ports";
  String TOOL_INSERT_REMOVE_FUNCTIONS = "functions";
  String TOOL_INSERT_REMOVE_FUNCTIONAL_EXCHANGES = "functional.exchanges";
  String TOOL_INSERT_REMOVE_FUNCTIONAL_CHAINS = "functional.chains";
  String TOOL_INSERT_REMOVE_EXCHANGE_CATEGORIES = "exchange.categories";
  String TOOL_INSERT_REMOVE_OPERATIONAL_ROLES = "operational.roles";
  String TOOL_INSERT_REMOVE_INTERACTIONS = "interactions";
  String TOOL_INSERT_REMOVE_ALLOCATED_ACTIVITIES = "allocated.activities";
  String TOOL_SHOW_ALL_ALLOCATED_ACTIVITIES = "all.allocated.activities";
  String TOOL_MANAGE_ACTIVITY_ALLOCATION = "manage.activity.allocation";
  String TOOL_SWITCH_FUNCTIONAL_EXCHANGE_CATEGORIES = "switch.functional.exchanges.categories";
  String TOOL_INSERT_REMOVE_CONSTRAINTS = "constraints";
  String TOOL_SWITCH_FUNCTIONAL_EXCHANGE_CATEGORY = "switch.functional.exchanges.categories";
  String TOOL_INSERT_REMOVE_PV = "applied.property.values";
  String TOOL_INSERT_REMOVE_PVG = "applied.property.value.groups";

  // Accelerators
  String TOOL_INITIALIZATION_FROM_EXISTING_DIAGRAM = "initialization.from.existing.diagram";

  String TOOL_CREATE_ACTOR = "actor";
  String TOOL_CREATE_COMPONENT = "component";
  String TOOL_CREATE_BEHAVIOR_COMPONENT = "behavior.pc";
  String TOOL_CREATE_NODE_COMPONENT = "node.pc";
  String TOOL_INSERT_REMOVE_ALLOCATED_FUNCTION = "allocated.function";
  String TOOL_INSERT_REMOVE_STATE_MODE = "state.mode";
  String TOOL_INSERT_REMOVE_ACTORS = "actors";
  String TOOL_INSERT_REMOVE_COMPONENTS = "components";

  // Sequence Diagram Tools operands
  String TOOL_CREATE_REFERENCE = "reference";
  String TOOL_CREATE_LOOP = "loop";
  String TOOL_CREATE_ALT = "alt";
  String TOOL_CREATE_PAR = "par";
  String TOOL_CREATE_OTHER_COMBINED_FRAGMENT = "other.combined.fragment";
  String TOOL_CREATE_OPERAND = "operand";
  String TOOL_CREATE_COMPONENT_EXCHANGE_WITH_RETURN_BRANCH = "component.exchange.with.return.branch";
  String TOOL_CREATE_COMPONENT_EXCHANGE = "component.exchange";
  String TOOL_LOST_COMPONENT_EXCHANGE = "lost.component.exchange";
  String TOOL_FOUND_COMPONENT_EXCHANGE = "found.component.exchange";
  String TOOL_LOST_FUNCTIONAL_EXCHANGE = "lost.functional.exchange";
  String TOOL_DURATION = "duration";
  String TOOL_CREATE_FUNCTIONAL_EXCHANGE_WITH_RETURN_BRANCH = "functional.exchange.with.return.branch";
  String TOOL_CREATE_FOUND_FUNCTIONAL_EXCHANGE = "found.functional.exchange";
  String TOOL_CREATE_ARM_TIMER = "arm.timer";
  String TOOL_CREATE_CANCEL_TIMER = "cancel.timer";
  String TOOL_CREATE_MESSAGE = "create.message";
  String TOOL_DELETE_MESSAGE = "delete.message";
  String TOOL_EXCHANGE_CONTEXT = "exchange.context";

  // =================================================================================================
  // Old Diagram Tools convention
  //
  //
  // Tools that are available to multiple types of diagrams
  //
  String TOOL_COMMON_ELEMENTS_FROM_SCENARIO = "elements.from.scenario";
  String TOOL_COOMON_FUNCTIONS_FROM_MODE_STATE = "functions.from.mode.state";

  String TOOL_CREATE_PHYSICAL_PATH = "physical.path";

  // Accelerators
  String TOOL_DIAGRAMINITIALIZATION = "DiagramInitialization";
  String TOOL_STATEMODE_ELEMENTS = "State-Mode Elements";
  String TOOL_SCENARIO_ELEMENTS = "Scenario Elements";
  String TOOL_SHOW_FUNCTIONAL_CHAIN_ELEMENTS = "Show Functional Chain Elements";

  // CC tools - Contextual Capability
  String TOOL_CC_CREATE_ACTOR = "actor";
  String TOOL_CC_CREATE_MISSION = "mission";
  String TOOL_CC_CREATE_CAPABILITY = "capability";
  String TOOL_CC_CREATE_CAPABILITY_EXPLOITATION = "capability.exploitation";
  String TOOL_CC_CREATE_INVOLVED_ACTOR = "involved.actor";
  String TOOL_CC_CREATE_EXTENDS = "extends";
  String TOOL_CC_CREATE_INCLUDES = "includes";
  String TOOL_CC_CREATE_ACTOR_GENERALIZATION = "actor.generalization";
  String TOOL_CC_CREATE_CAPABILITY_GENERALIZATION = TOOL_CC_CREATE_ACTOR_GENERALIZATION;
  String TOOL_CC_INSERT_REMOVE_ACTORS = "actors";
  String TOOL_CC_INSERT_REMOVE_MISSIONS = "missions";
  String TOOL_CC_INSERT_REMOVE_CAPABILITIES = "capabilities";
  String TOOL_CC_INSERT_REMOVE_RELATIONSHIPS = "relationships";
  String TOOL_CC_CREATE_CONSTRAINT = "constraint";
  String TOOL_CC_CREATE_CONSTRAINTELEMENT = "constraint.element";
  String TOOL_CC_INSERT_REMOVE_CONSTRAINTS = "constraints";
  String TOOL_CC_RECONNECT_GENERALIZATION_SOURCE = "CB Reconnect Generalization Source";
  String TOOL_CC_RECONNECT_GENERALIZATION_TARGET = "CB Reconnect Generalization Target";

  // tool id
  String TOOL_CC_SHOW_ACTORS = "s/h Actors";
  String TOOL_CC_SHOW_MISSIONS = "missions";
  String TOOL_CC_SHOW_CAPABILITY = "s/h Capabilities";

  // CCRI Tools - Contextual Capability Realization Involvement
  String TOOL_CCRI_CREATE_CAPABILITY_REALIZATION_NODE = "capability.realization";
  String TOOL_CCRI_CREATE_ACTOR_NODE = "actor";
  String TOOL_CCRI_CREATE_COMPONENT_NODE = "component";
  String TOOL_CCRI_CREATE_CAPABILITY_REALIZATION_INVOLVEMENT_EDGE = "involvement";
  String TOOL_CCRI_INSERT_CAPABILITY_REALIZATION_NODE = "capability.realizations";
  String TOOL_CCRI_INSERT_ACTOR_NODE = "actors";
  String TOOL_CCRI_INSERT_COMPONENT_NODE = "components";
  String TOOL_CCRI_INSERT_RELATIONSHIP_EDGE = "relationships";

  // CDB Tools - Class Diagram Blank
  // Classes Tools
  String TOOL_CDB_DELETE_CONSTRAINT_ELEMENT = "Delete Constraint Elements";
  String TOOL_CDB_CREATE_DATA_PACKAGE = "data.package";
  String TOOL_CDB_CREATE_CLASS = "class";
  String TOOL_CDB_CREATE_UNION = "union";
  String TOOL_CDB_CREATE_COLLECTION = "collection";
  String TOOL_CDB_CREATE_BOOLEAN_TYPE = "boolean.type";
  String TOOL_CDB_CREATE_BOOLEAN_LITERAL = "boolean.literal";
  String TOOL_CDB_CREATE_ENUMERATION = "enumeration";
  String TOOL_CDB_CREATE_ENUMERATION_LITERAL = "enumeration.literal";
  String TOOL_CDB_CREATE_NUMERIC_TYPE = "numeric.type";
  String TOOL_CDB_CREATE_STRING_TYPE = "string.type";
  String TOOL_CDB_CREATE_PHYSICAL_QUANTITY = "physical.quantity";
  String TOOL_CDB_CREATE_UNIT = "unit";
  String TOOL_CDB_CREATE_NUMERIC_REFERENCE = "numeric.reference";
  String TOOL_CDB_CREATE_LITERAL_NUMERIC_VALUE = "literal.numeric.value";
  String TOOL_CDB_CREATE_UNARY_EXPRESSION = "unary.expression";
  String TOOL_CDB_CREATE_BINARY_EXPRESSION = "binary.expression";
  String TOOL_CDB_CREATE_LITERAL_STRING_VALUE = "literal.string.value";
  String TOOL_CDB_CREATE_STRING_REFERENCE = "string.reference";
  String TOOL_CDB_CREATE_BOOLEAN_REFERENCE = "boolean.reference";
  String TOOL_CDB_CREATE_COMPLEX_VALUE = "complex.value";
  String TOOL_CDB_CREATE_COMPLEX_VALUE_REFERENCE = "complex.value.reference";
  String TOOL_CDB_CREATE_ENUMERATION_REFERENCE = "enumeration.reference";
  String TOOL_CDB_CREATE_COLLECTION_VALUE = "collection.value";
  String TOOL_CDB_CREATE_COLLECTION_VALUE_REFERENCE = "collection.value.reference";
  String TOOL_CDB_CREATE_PROPERTY = "property";
  String TOOL_CDB_CREATE_SERVICE = "class.operation";
  String TOOL_CDB_CREATE_PARAMETER = "parameter";
  String TOOL_CDB_CREATE_ASSOCIATION = "association";
  String TOOL_CDB_CREATE_AGGREGATION = "aggregation";
  String TOOL_CDB_CREATE_COMPOSITION = "composition";
  String TOOL_CDB_CREATE_GENERALIZATION = "generalization";
  String TOOL_CDB_CREATE_COLLECTION_TYPE = "collection.type";
  String TOOL_CDB_INSERT_REMOVE_DATA_PACKAGES = "data.packages";
  String TOOL_CDB_INSERT_REMOVE_TYPES = "types";
  String TOOL_CDB_INSERT_REMOVE_DATA_VALUES = "data.values";
  String TOOL_CDB_INSERT_RELATIONSHIPS = "relationships";
  String TOOL_CDB_CREATE_CONSTRAINT = "constraint";
  String TOOL_CDB_CREATE_CONSTRAINT_ELEMENT = "constraint.element";
  String TOOL_CDB_INSERT_REMOVE_CONSTRAINTS = "constraints";

  // Unsynchronized Classes Tools
  String TOOL_CDB_INSERT_REMOVE_PROPERTIES = "properties";
  String TOOL_CDB_INSERT_REMOVE_OPERATIONS = "operations";

  // tool id
  String TOOL_CDB_RECONNECT_ASSOCIATION_SOURCE = "CB Reconnect Association Source";
  String TOOL_CDB_RECONNECT_ASSOCIATION_TARGET = "CB Reconnect Association Target";
  String TOOL_CDB_RECONNECT_COLLECTION_TYPE = "CB Reconnect Collection Type";
  String TOOL_CDB_RECONNECT_GENERALIZATION_SOURCE = "CB Reconnect Generalization Source";
  String TOOL_CDB_RECONNECT_GENERALIZATION_TARGET = "CB Reconnect Generalization Target";
  String TOOL_CDB_RECONNECT_EXCHANGEITEMELEMENT_SOURCE = "ExchangeItemElement source";
  String TOOL_CDB_RECONNECT_EXCHANGEITEMELEMENT_TARGET = "ExchangeItemElement target";

  // Communication Tools
  String TOOL_CDB_CREATE_INTERFACE_PACKAGE = "interface.package";
  String TOOL_CDB_CREATE_INTERFACE = "interface";
  // EXCHANGE ITEMS
  String TOOL_CDB_CREATE_EI_OPERATION = "operation";
  String TOOL_CDB_CREATE_EI_EVENT = "event";
  String TOOL_CDB_CREATE_EI_FLOW = "flow";
  String TOOL_CDB_CREATE_EI_DATA = "data";
  String TOOL_CDB_CREATE_EI_UNSET = "undefined.exchange.item";
  // EXCHANGE ITEMS
  String TOOL_CDB_CREATE_EXCHANGE_ITEM_ELEMENT = "exchange.item.element";
  String TOOL_CDB_ALLOCATE_EXCHANGE_ITEM = "manage.exchange.item.allocations";
  String TOOL_CDB_INSERT_REMOVE_INTERFACE_PACKAGES = "interface.packages";
  String TOOL_CDB_INSERT_REMOVE_INTERFACES = "interfaces";
  String TOOL_CDB_INSERT_REMOVE_EXCHANGE_ITEMS = "exchange.items";
  String TOOL_CDB_INSERT_REMOVE_EXCHANGE_ITEM_ELEMENT_LINKS = "exchange.item.element.links";
  // Accelerators
  String TOOL_CDB_INITIALIZATION_FROM_EXISTING_DIAGRAM = "initialization.from.existing.diagram";
  // TOOLS ID
  String TOOL_CDB_CREATE_SIGNAL_RECEPTION = "Signal Reception";
  String TOOL_CDB_SHOW_HIDE_DATAPKGS = "data.packages";
  String TOOL_CDB_SHOW_HIDE_INTERFACEPKGS = "interface.packages";
  String TOOL_CDB_INSERT_REMOVE_TYPE = "types";
  String TOOL_CDB_SHOW_HIDE_ELEMENTS = "Show/Hide Elements in Package";
  String TOOL_CDB_SHOW_HIDE_CONSTRAINTS = "Show/Hide Constraints";
  String TOOL_CDB_SHOW_HIDE_RELATIONSHIPS = "Show/Hide Relationships";
  String TOOL_CDB_CREATE_DATAPKG = "data.package";
  String TOOL_CDB_CREATE_INTERFACEPKG = "InterfacePkg";
  String TOOL_CDB_SHOW_HIDE_INTERFACES = "Insert/Remove Interfaces";
  String TOOL_CDB_SHOW_HIDE_EXCHANGE_ITEMS = "exchange.items";
  String TOOL_CDB_SHOW_HIDE_EXCHANGEITEMELEMENT_LINK = "exchange.item.element.links";
  String TOOL_CDB_SHOW_HIDE_DATAVALUES = "Show/Hide DataValues";

  // CDI tools - Contextual Component Detailed Interfaces
  String TOOL_CDI_CREATE_INTERFACE = "interface";
  String TOOL_CDI_CREATE_IN_FLOW_PORT = "in.flow.port";
  String TOOL_CDI_CREATE_OUT_FLOW_PORT = "out.flow.port";
  String TOOL_CDI_CREATE_INOUT_FLOW_PORT = "in.out.flow.port";
  String TOOL_CDI_CREATE_STANDARD_PORT = "standard.port";
  String TOOL_CDI_CREATE_IMPLEMENTS = "implements";
  String TOOL_CDI_CREATE_USES = "uses";
  String TOOL_CDI_CREATE_PROVIDES = "provides";
  String TOOL_CDI_CREATE_REQUIRES = "requires";
  String TOOL_CDI_CREATE_GENERALIZATION = "generalization";
  String TOOL_CDI_CREATE_EVENT = "event";
  String TOOL_CDI_CREATE_OPERATION = "operation";
  String TOOL_CDI_CREATE_FLOW = "flow";
  String TOOL_CDI_CREATE_DATA = "data";
  String TOOL_CDI_CREATE_UNDEFINED_EXCHANGE_ITEM = "undefined.exchange.item";
  String TOOL_CDI_CREATE_EXCHANGE_ITEM_ELEMENT = "exchange.item.element";
  String TOOL_CDI_CREATE_TRANSMIT = "transmit";
  String TOOL_CDI_CREATE_ACQUIRE = "acquire";
  String TOOL_CDI_MANAGE_EXCHANGE_ITEM_ALLOCATIONS = "manage.exchange.item.allocations";
  String TOOL_CDI_INSERT_REMOVE_INTERFACES = "interfaces";
  String TOOL_CDI_INSERT_REMOVE_EXCHANGE_ITEMS = "exchange.items";
  String TOOL_CDI_CREATE_CONSTRAINT = "constraint";
  String TOOL_CDI_CREATE_CONSTRAINTELEMENT = "constraint.element";
  String TOOL_CDI_INSERT_REMOVE_CONSTRAINTS = "constraints";
  // Unsynchronized tools
  String TOOL_CDI_INSERT_REMOVE_EXCHANGE_ITEM_ALLOCATIONS = "exchange.item.allocations";
  String TOOL_CDI_INSERT_REMOVE_EXCHANGE_ITEM_ELEMENTS = "exchange.item.elements";

  String TOOL_CDI_SHOW_HIDE_EXCHANGE_ITEMS = "Show/Hide Exchange Items";
  String TOOL_CDI_SHOW_HIDE_INTERFACES = "Show/Hide Interfaces";
  String TOOL_CDI_SHOW_HIDE_EXCHANGE_ITEM_ALLOCATIONS = "Show/Hide Exchange Item Allocations";

  // CEI tools - Contextual Component External Interface
  String TOOL_CEI_CREATE_COMPONENT = "component";
  String TOOL_CEI_REUSE_COMPONENT = "reuse.component";
  String TOOL_CEI_CREATE_ACTOR = "actor";
  String TOOL_CEI_CREATE_IN_FLOW_PORT = "in.flow.port";
  String TOOL_CEI_CREATE_OUT_FLOW_PORT = "out.flow.port";
  String TOOL_CEI_CREATE_INOUT_FLOW_PORT = "in.out.flow.port";
  String TOOL_CEI_CREATE_STANDARD_PORT = "standard.port";
  String TOOL_CEI_CREATE_INTERFACE = "interface";
  String TOOL_CEI_CREATE_IMPLEMENTS = "implements";
  String TOOL_CEI_CREATE_USES = "uses";
  String TOOL_CEI_CREATE_PROVIDES = "provides";
  String TOOL_CEI_CREATE_REQUIRES = "requires";
  String TOOL_CEI_CREATE_GENERALIZATION = "generalization";
  String TOOL_CEI_CREATE_EVENT = "event";
  String TOOL_CEI_CREATE_OPERATION = "operation";
  String TOOL_CEI_CREATE_FLOW = "flow";
  String TOOL_CEI_CREATE_DATA = "data";
  String TOOL_CEI_CREATE_UNDEFINED_EXCHANGE_ITEM = "undefined.exchange.item";
  String TOOL_CEI_CREATE_EXCHNAGE_ITEM_ALLOCATION = "exchange.item.allocation";
  String TOOL_CEI_CREATE_TRANSMIT = "transmit";
  String TOOL_CEI_CREATE_ACQUIRE = "acquire";
  String TOOL_CEI_INSERT_REMOVE_COMPONENTS = "components";
  String TOOL_CEI_SHOW_HIDE_COMPONENTS = "Show/Hide Components";
  String TOOL_CEI_INSERT_REMOVE_ACTORS = "actors";
  String TOOL_CEI_INSERT_REMOVE_INTERFACES = "interfaces";
  String TOOL_CEI_INSERT_REMOVE_EXCHANGES_ITEMS = "exchange.items";
  String TOOL_CEI_CREATE_CONSTRAINT = "constraint";
  String TOOL_CEI_CREATE_CONSTRAINTELEMENT = "constraint.element";
  String TOOL_CEI_INSERT_REMOVE_CONSTRAINTS = "constraints";

  // CIBD tools - Configuration Items Breakdown
  String TOOL_CIBD_CREATE_COTS = "cots";
  String TOOL_CIBD_CREATE_CS = "cs";
  String TOOL_CIBD_CREATE_HW = "hw";
  String TOOL_CIBD_CREATE_INTERFACE = "interface";
  String TOOL_CIBD_CREATE_NDI = "ndi";
  String TOOL_CIBD_CREATE_PRIME_ITEM = "prime.item";
  String TOOL_CIBD_CREATE_SYSTEM = "system";
  String TOOL_CIBD_CONTAINED_IN = "contained.in";
  String TOOL_CIBD_CREATE_CONSTRAINT = "constraint";
  String TOOL_CIBD_CREATE_CONSTRAINTELEMENT = "constraint.element";
  String TOOL_CIBD_INSERT_REMOVE_CONSTRAINTS = "constraints";

  // CM tools - Contextual Mission
  String TOOL_CM_CREATE_ACTOR = "actor";
  String TOOL_CM_CREATE_CAPABILITY = "capability";
  String TOOL_CM_CREATE_CAPABILITY_EXPLOITATION = "capability.exploitation";
  String TOOL_CM_CREATE_ACTOR_INVOLVEMENT = "actor.involvement";
  String TOOL_CM_CREATE_ACTOR_GENERALIZATION = "actor.generalization";
  String TOOL_CM_INSERT_REMOVE_ACTORS = "actors";
  String TOOL_CM_INSERT_REMOVE_CAPABILITIES = "capabilities";
  String TOOL_CM_INSERT_REMOVE_RELATIONSHIPS = "relationships";
  String TOOL_CM_CREATE_CONSTRAINT = "constraint";
  String TOOL_CM_CREATE_CONSTRAINTELEMENT = "constraint.element";
  String TOOL_CM_INSERT_REMOVE_CONSTRAINTS = "constraints";
  String TOOL_CM_RECONNECT_GENERALIZATION_SOURCE = TOOL_CC_RECONNECT_GENERALIZATION_SOURCE;
  String TOOL_CM_RECONNECT_GENERALIZATION_TARGET = TOOL_CC_RECONNECT_GENERALIZATION_TARGET;

  // tool id
  String TOOL_CM_INSERT_ACTORS = "actors";
  String TOOL_CM_INSERT_CAPABILITIES = "capabilities";

  // COC - Contextual Operational Capability tools
  String TOOL_COC_CREATE_OPERATIONAL_ENTITY = "operational.entity";
  String TOOL_COC_CREATE_OPERATIONAL_ACTOR = "operational.actor";
  String TOOL_COC_CREATE_OPERATIONAL_CAPABILITY = "operational.capability";
  String TOOL_COC_CREATE_INVOLMENT = "involvement";
  String TOOL_COC_CREATE_EXTENDS = "extends";
  String TOOL_COC_CREATE_INCLUDES = "includes";
  String TOOL_COC_CREATE_OPERATIONAL_CAPABILITY_GENERALIZATION = "operational.capability.generalization";
  String TOOL_COC_INSERT_REMOVE_OPERATIONAL_ENTITIES = "operational.entities";
  String TOOL_COC_INSERT_REMOVE_OPERATIONAL_ACTORS = "operational.actors";
  String TOOL_COC_INSERT_REMOVE_OPERATIONAL_CAPABILITIES = "operational.capabilities";
  String TOOL_COC_INSERT_REMOVE_RELATIONSHIPS = "relationships";
  String TOOL_COC_CREATE_CONSTRAINT = "constraint";
  String TOOL_COC_CREATE_CONSTRAINT_ELEMENT = "constraint.element";
  String TOOL_COC_INSERT_REMOVE_CONSTRAINTS = "constraints";

  String TOOL_COC_SHOW_HIDE_OPERATIONAL_ENTITY = "Show/Hide OE";
  String TOOL_COC_SHOW_HIDE_OPERATIONAL_ACTORS = "Show/Hide OA";
  String TOOL_COC_SHOW_HIDE_CAPABILITIES = "Show/Hide Operational Capabilities";
  // CRB tools - Capability Realization Blank
  // Labels
  String TOOL_CRB_CREATE_CAPABILITY_REALIZATION = "capability.realization";
  String TOOL_CRB_CREATE_COMPONENT = "component";
  String TOOL_CRB_CREATE_COTS = "cots";
  String TOOL_CRB_CREATE_CS = "cs";
  String TOOL_CRB_CREATE_HW = "hw";
  String TOOL_CRB_CREATE_INTERFACE = "interface";
  String TOOL_CRB_CREATE_NDI = "ndi";
  String TOOL_CRB_CREATE_PRIME_ITEM = "prime.item";
  String TOOL_CRB_CREATE_SYSTEM = "system";
  String TOOL_CRB_CREATE_ACTOR_LABEL = "actor";
  String TOOL_CRB_CREATE_INVOLVEMENT = "involvement";
  String TOOL_CRB_CREATE_EXTENDS = "extends";
  String TOOL_CRB_CREATE_INCLUDES = "includes";
  String TOOL_CRB_CREATE_ACTOR_GENERALIZATION = "actor.generalization";
  String TOOL_CRB_CREATE_CAPABILITY_GENERALIZATION = TOOL_CRB_CREATE_ACTOR_GENERALIZATION;
  String TOOL_CRB_INSERT_REMOVE_CAPABILITY_REALIZATIONS = "capability.realizations";
  String TOOL_CRB_INSERT_REMOVE_COMPONENTS = "components";
  String TOOL_CRB_INSERT_REMOVE_ACTORS = "actors";
  String TOOL_CRB_INSERT_REMOVE_RELATIONSHIPS = "relationships";
  String TOOL_CRB_CREATE_CONSTRAINT = "constraint";
  String TOOL_CRB_CREATE_CONSTRAINTELEMENT = "constraint.element";
  String TOOL_CRB_INSERT_REMOVE_CONSTRAINTS = "constraints";

  // Names (ID) - Tool name and label are often the same excepting in the following cases
  String TOOL_CRB_CREATE_ACTOR_NAME = "actor";
  String TOOL_CRB_SHOW_HIDE_CAPABILITY_REALIZATIONS = "Show/Hide Capability Realizations";
  String TOOL_CRB_SHOW_HIDE_COMPONENTS = "Show/Hide Components";
  String TOOL_CRB_SHOW_HIDE_ACTORS = "Show/Hide Actors";
  String TOOL_CRB_SHOW_HIDE_RELATIONSHIPS = "Show/Hide Relationships";
  String TOOL_CRB_SHOW_HIDE_CONSTRAINTS = "Show/Hide Constraints";
  String TOOL_CRB_RECONNECT_GENERALIZATION_SOURCE = TOOL_CC_RECONNECT_GENERALIZATION_SOURCE;
  String TOOL_CRB_RECONNECT_GENERALIZATION_TARGET = TOOL_CC_RECONNECT_GENERALIZATION_TARGET;
  // CRI tools - Contextual Capability Realization Involvement
  String TOOL_CRI_INVOLVE_COMPONENT = "involve.component";
  String TOOL_CRI_CREATE_CONSTRAINT = "constraint";
  String TOOL_CRI_CREATE_CONSTRAINTELEMENT = "constraint.element";
  String TOOL_CRI_INSERT_REMOVE_CONSTRAINTS = "constraints";

  // CSA tools - Contextual System Actors
  String TOOL_CSA_CREATE_ACTOR = "actor";
  String TOOL_CSA_CREATE_ACTOR_GENERALIZATION = "actor.generalization";
  String TOOL_CSA_CREATE_CONSTRAINT = "constraint";
  String TOOL_CSA_CREATE_CONSTRAINTELEMENT = "constraint.element";
  String TOOL_CSA_INSERT_REMOVE_CONSTRAINTS = "constraints";
  String TOOL_CSA_RECONNECT_GENERALIZATION_SOURCE = TOOL_CC_RECONNECT_GENERALIZATION_SOURCE;
  String TOOL_CSA_RECONNECT_GENERALIZATION_TARGET = TOOL_CC_RECONNECT_GENERALIZATION_TARGET;

  // EAB - EPBS Architecture Blank tools
  String TOOL_EAB_CREATE_COTS = "cots";
  String TOOL_EAB_CREATE_CS = "cs";
  String TOOL_EAB_CREATE_HW = "hw";
  String TOOL_EAB_CREATE_INTERFACE = "interface";
  String TOOL_EAB_CREATE_NDI = "ndi";
  String TOOL_EAB_CREATE_PRIME_ITEM = "prime.item";
  String TOOL_EAB_CREATE_SYSTEM = "system";
  String TOOL_EAB_MANAGE_REALIZED_PHYSICAL_ARTIFACTS = "manage.realized.physical.artifacts";
  String TOOL_EAB_INSERT_REMOVE_CONFIGURATION_ITEMS = "configuration.items";
  String TOOL_EAB_CREATE_CONSTRAINT = "constraint";
  String TOOL_EAB_CREATE_CONSTRAINT_ELEMENT = "constraint.element";
  String TOOL_EAB_INSERT_REMOVE_CONSTRAINTS = "constraints";
  // Unsynchronized tools
  String TOOL_EAB_INSERT_REMOVE_REALIZED_PHYSICAL_ARTIFACTS = "realized.physical.artifacts";
  // multi parts tool
  String TOOL_EAB_REUSE_CONFIGURATION_ITEM = "reuse.configuration.item";

  String TOOL_EAB_SHOW_HIDE_CONFIGURATION_ITEMS = "Show/Hide Configuration Items";
  String TOOL_EAB_SHOW_HIDE_REALIZED_PHYSICAL_ARTIFACTS = "Show/Hide Realized Physical Artifacts";
  String TOOL_EAB_SHOW_HIDE_CONSTRAINTS = "Show/Hide Constraints";

  // ES Tools - Exchange Scenario
  String TOOL_ES_CREATE_NODE_PC = TOOL_CREATE_NODE_COMPONENT;
  String TOOL_ES_CREATE_BEHAVIOR_PC = TOOL_CREATE_BEHAVIOR_COMPONENT;
  String TOOL_ES_CREATE_COMPONENT = TOOL_CREATE_COMPONENT;
  String TOOL_ES_CREATE_ACTOR = "actor";
  String TOOL_ES_INSERT_REMOVE_COMPONENTS = TOOL_INSERT_REMOVE_COMPONENTS;
  String TOOL_ES_INSERT_ACTOR = TOOL_INSERT_REMOVE_ACTORS;
  String TOOL_ES_CREATE_FUNCTIONAL_EXCHANGE_WITH_RETURN_BRANCH = TOOL_CREATE_FUNCTIONAL_EXCHANGE_WITH_RETURN_BRANCH;
  String TOOL_ES_CREATE_FUNCTIONAL_EXCHANGE = TOOL_CREATE_FUNCTIONAL_EXCHANGE;
  String TOOL_ES_CREATE_FOUND_FUNCTIONAL_EXCHANGE = TOOL_CREATE_FOUND_FUNCTIONAL_EXCHANGE;
  String TOOL_ES_INSERT_REMOVE_FUNCTION = "allocated.function";
  String TOOL_ES_INSERT_REMOVE_STATE_MODE = "state.mode";
  String TOOL_ES_CREATE_REFERENCE = TOOL_CREATE_REFERENCE;
  String TOOL_ES_CREATE_LOOP = TOOL_CREATE_LOOP;
  String TOOL_ES_CREATE_ALT = TOOL_CREATE_ALT;
  String TOOL_ES_CREATE_PAR = TOOL_CREATE_PAR;
  String TOOL_ES_CREATE_OTHER_COMBINED_FRAGMENT = TOOL_CREATE_OTHER_COMBINED_FRAGMENT;
  String TOOL_ES_CREATE_OPERAND = TOOL_CREATE_OPERAND;
  String TOOL_ES_CREATE_COMPONENT_EXCHANGE_WITH_RETURN_BRANCH = TOOL_CREATE_COMPONENT_EXCHANGE_WITH_RETURN_BRANCH;
  String TOOL_ES_CREATE_COMPONENT_EXCHANGE = TOOL_CREATE_COMPONENT_EXCHANGE;
  String TOOL_ES_LOST_COMPONENT_EXCHANGE = TOOL_LOST_COMPONENT_EXCHANGE;
  String TOOL_ES_FOUND_COMPONENT_EXCHANGE = TOOL_FOUND_COMPONENT_EXCHANGE;
  String TOOL_ES_LOST_FUNCTIONAL_EXCHANGE = TOOL_LOST_FUNCTIONAL_EXCHANGE;
  String TOOL_ES_CREATE_CONSTRAINT = "constraint";
  String TOOL_ES_CREATE_CONSTRAINTELEMENT = "constraint.element";
  String TOOL_ES_INSERT_REMOVE_CONSTRAINTS = "constraints";
  String TOOL_ES_CREATE_ARM_TIMER = TOOL_CREATE_ARM_TIMER;
  String TOOL_ES_CREATE_CANCEL_TIMER = TOOL_CREATE_CANCEL_TIMER;
  String TOOL_ES_DURATION = TOOL_DURATION;
  String TOOL_ES_EXCHANGE_CONTEXT = TOOL_EXCHANGE_CONTEXT;

  // id tools
  String TOOL_ES_CREATE_CONSTRAINTELEMENTSCENARIO = "constraint.element.scenario";
  String TOOL_ES_CREATE_FUNCTION_STATE = "Function state";
  String TOOL_ES_CREATE_STATE_MODE = TOOL_INSERT_REMOVE_STATE_MODE;

  // FCD tools - Functional Chain Description
  String TOOL_FCD_INVOLVE_FUNCTION = "involve.function";
  String TOOL_FCD_INVOLVE_EXCHANGE = "involve.exchange";
  String TOOL_FCD_INVOLVE_EXCHANGE_AND_FUNCTION = "involve.exchange.and.function";
  String TOOL_FCD_INVOLVE_FUNCTIONAL_CHAIN = "involve.functional.chain";
  String TOOL_FCD_CONNECT_FUNCTIONS = "connect.functions";

  String TOOL_FCD_CREATE_CONSTRAINT = "constraint";
  String TOOL_FCD_CREATE_CONSTRAINTELEMENT = "constraint.element";
  String TOOL_FCD_INSERT_REMOVE_CONSTRAINTS = "constraints";
  // Accelerators
  String TOOL_FCD_INITIALIZATION_FROM_EXISTING_DIAGRAM = "Initialization from existing diagram";

  /*
   * Sequencing section
   */
  // Control Node Tools
  String TOOL_CREATE_CONTROL_NODE_AND = "and";
  String TOOL_CREATE_CONTROL_NODE_OR = "or";
  String TOOL_CREATE_CONTROL_NODE_IT = "it";

  String TOOL_CREATE_CONSTRUCT_CONTROL_NODE_AND = "and.construct";
  String TOOL_CREATE_CONSTRUCT_CONTROL_NODE_OR = "or.construct";
  String TOOL_CREATE_CONSTRUCT_CONTROL_NODE_IT = "it.construct";

  String TOOL_CREATE_SEQUENCE_LINK = "sequence.link";
  String TOOL_CREATE_FUNCTION_ON_SEQUENCE_LINK = "involve.function.on.sequence.link";
  String TOOL_CREATE_EXCHANGE_WITH_SEQUENCE_LINK = "involve.exchange.with.sequence.link";
  String TOOL_ASSOCIATE_SEQUENCE_LINK_WITH_EXCHANGE = "associate.sequence.link.with.exchange";
  String TOOL_EXCHANGE_FROM_SEQUENCE_LINK = "involve.exchange.from.sequence.link";
  String TOOL_SEQUENCE_LINK_FROM_EXCHANGE = "sequence.link.from.exchange";

  // FS tools - Function Scenario
  String TOOL_FS_CREATE_SYSTEM_FUNCTION = TOOL_CREATE_FUNCTION;
  String TOOL_FS_CREATE_LOGICAL_FUNCTION = TOOL_CREATE_FUNCTION;
  String TOOL_FS_CREATE_PHYSICAL_FUNCTION = TOOL_CREATE_FUNCTION;
  String TOOL_FS_CREATE_DUPLICATE = TOOL_CREATE_FUNCTION_DUPLICATE;
  String TOOL_FS_CREATE_GATHER = TOOL_CREATE_FUNCTION_GATHER;
  String TOOL_FS_CREATE_ROUTE = TOOL_CREATE_FUNCTION_ROUTE;
  String TOOL_FS_CREATE_SELECT = TOOL_CREATE_FUNCTION_SELECT;
  String TOOL_FS_CREATE_SPLIT = TOOL_CREATE_FUNCTION_SPLIT;
  String TOOL_FS_INSERT_REMOVE_FUNCTIONS = "functions";
  String TOOL_FS_CREATE_FUNCTIONAL_EXCHANGE_WITH_RETURN_BRANCH = TOOL_CREATE_FUNCTIONAL_EXCHANGE_WITH_RETURN_BRANCH;
  String TOOL_FS_CREATE_FUNCTIONAL_EXCHANGE = "functional.exchange";
  String TOOL_FS_CREATE_REFERENCE = TOOL_CREATE_REFERENCE;
  String TOOL_FS_CREATE_LOOP = TOOL_CREATE_LOOP;
  String TOOL_FS_CREATE_ALT = TOOL_CREATE_ALT;
  String TOOL_FS_CREATE_PAR = TOOL_CREATE_PAR;
  String TOOL_FS_CREATE_OTHER_COMBINED_FRAGMENT = TOOL_CREATE_OTHER_COMBINED_FRAGMENT;
  String TOOL_FS_CREATE_OPERAND = TOOL_CREATE_OPERAND;
  String TOOL_FS_INSERT_REMOVE_STATE_MODE = "state.mode";
  String TOOL_FS_CREATE_CONSTRAINT = "constraint";
  String TOOL_FS_CREATE_CONSTRAINTELEMENT = "constraint.element";
  String TOOL_FS_INSERT_REMOVE_CONSTRAINTS = "constraints";
  String TOOL_FS_DURATION = TOOL_DURATION;
  String TOOL_FS_EXCHANGE_CONTEXT = TOOL_EXCHANGE_CONTEXT;

  // ID tool
  String TOOL_FS_CREATE_CONSTRAINTELEMENTSCENARIO = "constraint.element.scenario";
  String TOOL_FS_CREATE_STATE_MODE = TOOL_INSERT_REMOVE_STATE_MODE;

  // ID tools - Interface Diagram
  String TOOL_ID_CREATE_EVENT = "event";
  String TOOL_ID_CREATE_OPERATION = "operation";
  String TOOL_ID_CREATE_FLOW = "flow";
  String TOOL_ID_CREATE_DATA = "data";
  String TOOL_ID_CREATE_UNDEFINED_EXCHANGE_ITEM = "undefined.exchange.item";
  String TOOL_ID_CREATE_EXCHANGEITEMELEMENT = "exchange.item.element";
  String TOOL_ID_INSERT_REMOVE_EXCHANGE_ITEM_ALLOCATIONS = "exchange.item.allocations";
  String TOOL_ID_CREATE_CONSTRAINT = "constraint";
  String TOOL_ID_CREATE_CONSTRAINTELEMENT = "constraint.element";
  String TOOL_ID_INSERT_REMOVE_CONSTRAINTS = "constraints";

  // IDB tools - Interfaces Diagram Blank
  String TOOL_IDB_CREATE_COMPONENT = "component";
  String TOOL_IDB_REUSE_COMPONENT = "reuse.component";
  String TOOL_IDB_CREATE_ACTOR = "actor";
  String TOOL_IDB_CREATE_IN_FLOW_PORT = "in.flow.port";
  String TOOL_IDB_CREATE_OUT_FLOW_PORT = "out.flow.port";
  String TOOL_IDB_CREATE_INOUT_FLOW_PORT = "in.out.flow.port";
  String TOOL_IDB_CREATE_STANDARD_PORT = "standard.port";
  String TOOL_IDB_CREATE_INTERFACE = "interface";
  String TOOL_IDB_CREATE_IMPLEMENTS = "implements";
  String TOOL_IDB_CREATE_USES = "uses";
  String TOOL_IDB_CREATE_PROVIDES = "provides";
  String TOOL_IDB_CREATE_REQUIRES = "requires";
  String TOOL_IDB_CREATE_DELEGATION = "delegation";
  String TOOL_IDB_CREATE_GENERALIZATION = "generalization";
  String TOOL_IDB_CREATE_EVENT = "event";
  String TOOL_IDB_CREATE_OPERATION = "operation";
  String TOOL_IDB_CREATE_FLOW = "flow";
  String TOOL_IDB_CREATE_DATA = "data";
  String TOOL_IDB_CREATE_UNDEFINED_EXCHANGE_ITEM = "undefined.exchange.item";
  String TOOL_IDB_CREATE_EXCHANGE_ITEM_ELEMENT = "exchange.item.element";
  String TOOL_IDB_CREATE_TRANSMIT = "transmit";
  String TOOL_IDB_CREATE_ACQUIRE = "acquire";
  String TOOL_IDB_MANAGE_EXCHANGE_ITEM_ALLOCATIONS = "manage.exchange.item.allocations";
  String TOOL_IDB_INSERT_REMOVE_COMPONENTS = "components";
  String TOOL_IDB_INSERT_REMOVE_ACTORS = "actors";
  String TOOL_IDB_INSERT_REMOVE_INTERFACES = "interfaces";
  String TOOL_IDB_INSERT_REMOVE_RELATIONSHIPS = "relationships";
  String TOOL_IDB_CREATE_CONSTRAINT = "constraint";
  String TOOL_IDB_CREATE_CONSTRAINTELEMENT = "constraint.element";
  String TOOL_IDB_INSERT_REMOVE_CONSTRAINTS = "constraints";

  String TOOL_IDB_INSERT_REMOVE_COMPONENTS__LABEL = "components";
  String TOOL_IDB_INSERT_REMOVE_RELATIONSHIPS__LABEL = "relationships";

  // Accelerators
  String TOOL_IDB_INITIALIZATION_FROM_EXISTING_DIAGRAM = "initialization.from.existing.diagram";
  // Unsynchronized tools
  String TOOL_IDB_INSERT_REMOVE_COMPONENT_PORTS = "component.ports";
  String TOOL_IDB_INSERT_REMOVE_EXCHANGE_ITEM_ALLOCATIONS = "exchange.item.allocations";
  String TOOL_IDB_INSERT_REMOVE_EXCHANGE_ITEM = "exchange.items";
  String TOOL_IDB_INSERT_REMOVE_EXCHANGE_ITEM_ELEMENTS = "exchange.item.elements";
  String TOOL_IDB_INSERT_REMOVE_COMMUNICATION_LINKS = "Communication Links";

  // tools id
  String TOOL_IDB_SHOW_HIDE_ACTORS = "Show/Hide Actors";
  String TOOL_IDB_SHOW_HIDE_INTERFACES = "Show/Hide Interfaces";
  String TOOL_IDB_SHOW_HIDE_EXCHANGE_ITEMS = "Show/Hide Exchange Items";
  String TOOL_IDB_SHOW_HIDE_COMPONENT_PORTS = "Show/Hide Component Ports";
  String TOOL_IDB_SHOW_HIDE_RELATIONSHIPS = "Show/Hide Relationships";
  String TOOL_IDB_SHOW_HIDE_COMMUNICATIONSLINKS = "Show/Hide CommunicationLinks";
  String TOOL_IDB_SHOW_HIDE_EXCHANGE_ITEM_ALLOCATIONS = "Show/Hide Exchange Item Allocations";
  String TOOL_IDB_SHOW_HIDE_EXCHANGE_ITEM_ELEMENTS = "Show/Hide Exchange Item Elements";
  String TOOL_IDB_CREATE_COMMUNICATIONLINK_TRANSMIT = "transmit";
  String TOOL_IDB_CREATE_COMMUNICATIONLINK_ACQUIRE = "acquire";
  String TOOL_IDB_RECONNECT_IMPLEMENTS_TARGET = "implements target";
  String TOOL_IDB_RECONNECT_USES_TARGET = "uses target";
  String TOOL_IDB_RECONNECT_PINPROVIDED_SOURCE = "implements-pinprovided source";
  String TOOL_IDB_RECONNECT_PINPROVIDEDINTERFACE_TARGET = "PinProvidedInterface target";
  String TOOL_IDB_RECONNECT_PINREQUIRED_SOURCE = "used-pinrequired source";
  String TOOL_IDB_RECONNECT_PINREQUIREDINTERFACE_TARGET = "PinRequiredInterface target";
  String TOOL_IDB_RECONNECT_GENERALIZATION_SOURCE = "CB Reconnect Generalization Source";
  String TOOL_IDB_RECONNECT_GENERALIZATION_TARGET = "CB Reconnect Generalization Target";
  String TOOL_IDB_RECONNECT_COMMUNICATIONLINK_SOURCE = "CommunicationLink source";
  String TOOL_IDB_RECONNECT_COMMUNICATIONLINK_TARGET = "CommunicationLink target";

  // Interface Scenario Diagram Tools
  String TOOL_IS_CREATE_ACTOR = "actor";
  String TOOL_IS_CREATE_COMPONENT = "component";
  String TOOL_IS_INSERT_REMOVE_ACTORS = "actors";
  String TOOL_IS_CREATE_MESSAGE = TOOL_CREATE_MESSAGE;
  String TOOL_IS_DELETE_MESSAGE = TOOL_DELETE_MESSAGE;
  String TOOL_IS_FOUND_MESSAGE = "found.message";
  String TOOL_IS_LOST_MESSAGE = "lost.message";
  String TOOL_IS_SYNCHRONOUS_MESSAGE = "sequence.message.with.return.branch";
  String TOOL_IS_ASYNCHRONOUS_MESSAGE = "sequence.message";
  String TOOL_IS_INSERT_REMOVE_SHARED_DATA_EVENT = "shared.data.event";
  String TOOL_IS_INSERT_REMOVE_STATE_MODE = "state.mode";
  String TOOL_IS_CREATE_REFERENCE = TOOL_CREATE_REFERENCE;
  String TOOL_IS_CREATE_LOOP = TOOL_CREATE_LOOP;
  String TOOL_IS_CREATE_ALT = TOOL_CREATE_ALT;
  String TOOL_IS_CREATE_PAR = TOOL_CREATE_PAR;
  String TOOL_IS_CREATE_OTHER_COMBINED_FRAGMENT = TOOL_CREATE_OTHER_COMBINED_FRAGMENT;
  String TOOL_IS_CREATE_OPERAND = TOOL_CREATE_OPERAND;
  String TOOL_IS_CREATE_CONSTRAINT = "constraint";
  String TOOL_IS_CREATE_CONSTRAINTELEMENT = "constraint.element";
  String TOOL_IS_INSERT_REMOVE_CONSTRAINTS = "constraints";
  String TOOL_IS_CREATE_COTS = "cots";
  String TOOL_IS_CREATE_CS = "cs";
  String TOOL_IS_CREATE_HW = "hw";
  String TOOL_IS_CREATE_INTERFACE = "interface";
  String TOOL_IS_CREATE_NDI = "ndi";
  String TOOL_IS_CREATE_PRIME_ITEM = "prime.item";
  String TOOL_IS_CREATE_SYSTEM = "system";
  String TOOL_IS_INSERT_REMOVE_COMPONENTS = TOOL_INSERT_REMOVE_COMPONENTS;
  String TOOL_IS_INSERT_REMOVE_FUNCTION = "allocated.function";
  String TOOL_IS_CREATE_ARM_TIMER = TOOL_CREATE_ARM_TIMER;
  String TOOL_IS_CREATE_CANCEL_TIMER = TOOL_CREATE_CANCEL_TIMER;
  String TOOL_IS_DURATION = TOOL_DURATION;
  String TOOL_IS_EXCHANGE_CONTEXT = TOOL_EXCHANGE_CONTEXT;
  String TOOL_IS_EI_EXCHANGE_CONTEXT = TOOL_EXCHANGE_CONTEXT;

  // id tools
  String TOOL_IS_CREATE_CONSTRAINTELEMENTSCENARIO = "constraint.element.scenario";
  String TOOL_IS_CREATE_STATE_MODE = TOOL_INSERT_REMOVE_STATE_MODE;

  // LAB tools
  // Components
  String TOOL_LAB_CREATE_COMPONENT = "logical.component";
  String TOOL_LAB_CREATE_LOGICAL_ACTOR = "logical.actor";
  String TOOL_LAB_CREATE_IN_FLOW_PORT_PORT = "in.flow.port";
  String TOOL_LAB_CREATE_OUT_FLOW_PORT_PORT = "out.flow.port";
  String TOOL_LAB_CREATE_INOUT_FLOW_PORT_PORT = "in.out.flow.port";
  String TOOL_LAB_CREATE_STANDARD_PORT_PORT = "standard.port";
  String TOOL_LAB_CREATE_COMPONENT_EXCHANGE = TOOL_CREATE_COMPONENT_EXCHANGE;
  String TOOL_LAB_CREATE_COMPONENT_EXCHANGE_WITH_DELEGATIONS = "component.exchange.with.delegations";
  String TOOL_LAB_CREATE_DELEGATION = "delegation";
  String TOOL_LAB_CREATE_PHYSICAL_LINK = "physical.link";
  String TOOL_LAB_CREATE_PHYSICAL_PORT = "physical.port";
  String TOOL_LAB_INSERT_REMOVE_COMPONENTS = "components";
  String TOOL_LAB_INSERT_REMOVE_PHYSICAL_LINKS = "physical.links";
  String TOOL_LAB_INSERT_REMOVE_PHYSICAL_PATH = "physical.path";
  String TOOL_LAB_INSERT_REMOVE_ACTORS = "actors";
  String TOOL_LAB_INSERT_REMOVE_COMPONENT_EXCHANGES = "component.exchanges";
  String TOOL_LAB_CREATE_CONSTRAINT = "constraint";
  String TOOL_LAB_INSERT_REMOVE_PHYSICAL_LINKS_CATEGORIES = "switch.physical.links.categories";
  String TOOL_LAB_CREATE_CONSTRAINT_ELEMENT = "constraint.element";
  String TOOL_LAB_INSERT_REMOVE_CONSTRAINTS = "constraints";
  String TOOL_LAB_INSERT_ACTOR = "Show/Hide Actors";
  // Functions
  String TOOL_LAB_CREATE_LOGICAL_FUNCTION = "logical.function";
  String TOOL_LAB_CREATE_DUPLICATE = "duplicate";
  String TOOL_LAB_CREATE_GATHER = "gather";
  String TOOL_LAB_CREATE_ROUTE = "route";
  String TOOL_LAB_CREATE_SELECT = "select";
  String TOOL_LAB_CREATE_SPLIT = "split";
  String TOOL_LAB_CREATE_INPUT_PORT = "input.port";
  String TOOL_LAB_CREATE_OUTPUT_PORT = "output.port";
  String TOOL_LAB_CREATE_PORT_ALLOCATION = "port.allocation";
  String TOOL_LAB_MANAGE_FUNCTION_ALLOCATION = "manage.function.allocation";
  String TOOL_LAB_INSERT_REMOVE_ALLOCATED_FUNCTIONS = "allocated.functions";
  String TOOL_LAB_INSERT_REMOVE_ALL_ALLOCATED_FUNCTIONS = "all.allocated.functions";
  String TOOL_LAB_INSERT_REMOVE_FUNCTIONAL_CHAINS = "functional.chains";
  String TOOL_LAB_INSERT_REMOVE_FUNCTIONAL_EXCHANGES = "functional.exchanges";
  String TOOL_LAB_INSERT_REMOVE_EXCHANGE_CATEGORIES = "switch.functional.exchanges.categories";
  String TOOL_LAB_INSERT_SCENARIO_ELEMENTS = "Scenario Elements";
  String TOOL_LAB_INSERT_STATEMODE_ELEMENTS = "State-Mode Elements";

  String TOOL_LAB_INSERT_FUNCTIONS_FROM_MODE_STATE = "functions.from.mode.state";
  String TOOL_LAB_INSERT_ELEMENTS_FROM_SCENARIO = "elements.from.scenario";
  String TOOL_LAB_STATE_MODE_ELEMENTS_ID = "State-Mode Elements";
  String TOOL_LAB_SCENARIO_ELEMENTS_ID = "Scenario Elements";
  String TOOL_LAB_INSERT_REMOVE_CATEGORIES = "categories";
  String TOOL_LAB_INSERT_REMOVE_COMPONENT_EXCHANGES_CATEGORIES = "switch.component.exchanges.categories";
  // Accelerators
  String TOOL_LAB_INITIALIZATION_FROM_EXISTING_DIAGRAM = "initialization.from.existing.diagram";
  // Unsynchronized tools
  String TOOL_LAB_INSERT_REMOVE_FUNCTION_PORTS = "function.ports";
  String TOOL_LAB_INSERT_REMOVE_PORTS = "ports";
  // Multi parts tools
  String TOOL_LAB_REUSE_LOGICAL_COMPONENT = "reuse.logical.component";
  String TOOL_LAB_REUSE_LOGICAL_ACTOR = "reuse.logical.actor";
  String TOOL_LAB_CREATE_COMPONENT_EXCHANGE_WITH_PORTS = "component.exchange.with.ports";
  String TOOL_LAB_CREATE_COMPONENT_EXCHANGE_WITHOU_PORTS = "component.exchange.without.ports";
  String TOOL_LAB_CREATE_COMPONENT_EXCHANGE_BETWEEN_TYPES = "component.exchange.between.types";

  // tools id
  String TOOL_LAB_DELETE_ELEMENT = "delete capella element";
  String TOOL_LAB_SHOW_HIDE_COMPONENT_EXCHANGES = "Show/Hide Connections";
  String TOOL_LAB_SHOW_HIDE_PORTS = "Show/Hide Ports";
  String TOOL_LAB_SHOW_HIDE_FUNCTION_PORTS = "Show/Hide Function Ports";
  String TOOL_LAB_SHOW_HIDE_FUNCTIONAL_EXCHANGES = "functional.exchanges";
  String TOOL_LAB_SHOW_HIDE_FUNCTIONAL_CHAINS = "Show/Hide Functional Chains";
  String TOOL_LAB_SHOW_HIDE_CONSTRAINTS = "Show/Hide Constraints";
  String TOOL_LAB_CREATE_FUNCTIONAL_CHAIN = "functional.chain";
  String TOOL_LAB_CREATE_CONNECTION_WITH_PORTS = "Connection with ports";
  String TOOL_LAB_RECONNECT_CONNECTION_SOURCE = "Reconnect Connection Source";
  String TOOL_LAB_RECONNECT_CONNECTION_TARGET = "Reconnect Connection Target";
  String TOOL_LAB_RECONNECT_FUNCTION_EXCHANGE = "LAB Reconnect Function Exchanges";
  String TOOL_LAB_CREATE_OUTFLOW_PORT = "out.flow.port";
  String TOOL_LAB_CREATE_INFLOW_PORT = "in.flow.port";

  // LCBD tools - Logical Component Breakdown
  String TOOL_LCBD_CREATE_LOGICAL_COMPONENT = "logical.component";
  String TOOL_LCBD_CREATE_LOGICAL_ACTOR = "actor";
  String TOOL_LCBD_CONTAINED_IN = "contained.in";
  String TOOL_LCBD_CREATE_CONSTRAINT = "constraint";
  String TOOL_LCBD_CREATE_CONSTRAINTELEMENT = "constraint.element";
  String TOOL_LCBD_INSERT_REMOVE_CONSTRAINTS = "constraints";
  // multi part tool
  String TOOL_LCBD_CREATE_PART = "create.part";

  // LCCDI - Logical Contextual Component Detailed Interfaces tools
  String TOOL_LCCDI_CREATE_ECHANGE_ITEM_EVENT = "event";
  String TOOL_LCCDI_CREATE_ECHANGE_ITEM_OPERATION = "operation";
  String TOOL_LCCDI_CREATE_ECHANGE_ITEM_FLOW = "flow";
  String TOOL_LCCDI_CREATE_ECHANGE_ITEM_DATA = "data";
  String TOOL_LCCDI_CREATE_INTERFACE = "interface";
  String TOOL_LCCDI_CREATE_COMMUNICATION_LINK_TRANSMIT = "CommunicationLink Send / Produce / Call / Write / Transmit";
  String TOOL_LCCDI_CREATE_COMMUNICATION_LINK_ACQUIRE = "CommunicationLink Receive / Consume / Execute / Access / Acquire";
  String TOOL_LCCDI_CREATE_USES = "uses";
  String TOOL_LCCDI_CREATE_PROVIDES = "provides";
  String TOOL_LCCDI_CREATE_REQUIRES = "requires";
  String TOOL_LCCDI_CREATE_GENERALIZATION = "generalization";
  String TOOL_LCCDI_CREATE_STANDARD_PORT = "standard.port";
  String TOOL_LCCDI_CREATE_IMPLEMENTS = "implements";

  // Logical system - Contextual Component External Interfaces (LCCEI) tools
  String TOOL_LCCEI_CREATE_ACTOR = "actor";
  String TOOL_LCCEI_CREATE_ECHANGE_ITEM_EVENT = "event";
  String TOOL_LCCEI_CREATE_ECHANGE_ITEM_OPERATION = "operation";
  String TOOL_LCCEI_CREATE_ECHANGE_ITEM_FLOW = "flow";
  String TOOL_LCCEI_CREATE_ECHANGE_ITEM_DATA = "data";
  String TOOL_LCCEI_CREATE_UNDEFINED_ECHANGE_ITEM = "undefined.exchange.item";
  String TOOL_LCCEI_CREATE_INTERFACE = "interface";
  String TOOL_LCCEI_CREATE_COMMUNICATION_LINK_TRANSMIT = "CommunicationLink Send / Produce / Call / Write / Transmit";
  String TOOL_LCCEI_CREATE_COMMUNICATION_LINK_ACQUIRE = "CommunicationLink Receive / Consume / Execute / Access / Acquire";
  String TOOL_LCCEI_SHOW_HIDE_ACTORS = "Show/Hide Actors";
  String TOOL_LCCEI_SHOW_HIDE_EXCHANGE_ITEMS = "Show/Hide Exchange Items";
  String TOOL_LCCEI_CREATE_LOGICAL_COMPONENT = "component";
  String TOOL_LCCEI_CREATE_USES = "uses";
  String TOOL_LCCEI_CREATE_PROVIDES = "provides";
  String TOOL_LCCEI_CREATE_REQUIRES = "requires";
  String TOOL_LCCEI_CREATE_GENERALIZATION = "generalization";
  String TOOL_LCCEI_CREATE_INOUT_FLOW_PORT_PORT = "in.out.flow.port";
  String TOOL_LCCEI_CREATE_STANDARD_PORT = "standard.port";
  String TOOL_LCCEI_CREATE_PORT_DELEGATION = "Port Delegation";
  String TOOL_LCCEI_CREATE_IMPLEMENTS = "implements";
  String TOOL_LCCEI_CREATE_EXCHANGE_ITEM_ALLOCATION = "exchange.item.allocation";
  String TOOL_LCCEI_SHOW_HIDE_INTERFACES = "Show/Hide Interfaces";

  // LCCII - Logical Contextual Component Internal Interfaces tools
  String TOOL_LCCII_CREATE_LOGICAL_COMPONENT = "component";
  String TOOL_LCCII_REUSE_COMPONENT = "reuse.component";
  String TOOL_LCCII_CREATE_IN_FLOW_PORT = "in.flow.port";
  String TOOL_LCCII_CREATE_OUT_FLOW_PORT = "out.flow.port";
  String TOOL_LCCII_CREATE_INOUT_FLOW_PORT_PORT = "in.out.flow.port";
  String TOOL_LCCII_CREATE_STANDARD_PORT = "standard.port";
  String TOOL_LCCII_CREATE_INTERFACE = "interface";
  String TOOL_LCCII_CREATE_IMPLEMENTS = "implements";
  String TOOL_LCCII_CREATE_USES = "uses";
  String TOOL_LCCII_CREATE_PROVIDES = "provides";
  String TOOL_LCCII_CREATE_REQUIRES = "requires";
  // the following constant is the label of "Port Delegation" tool
  String TOOL_LCCII_CREATE_DELEGATION = "delegation";
  String TOOL_LCCII_CREATE_GENERALIZATION = "generalization";
  String TOOL_LCCII_CREATE_ECHANGE_ITEM_EVENT = "event";
  String TOOL_LCCII_CREATE_ECHANGE_ITEM_OPERATION = "operation";
  String TOOL_LCCII_CREATE_ECHANGE_ITEM_FLOW = "flow";
  String TOOL_LCCII_CREATE_ECHANGE_ITEM_DATA = "data";
  String TOOL_LCCII_CREATE_UNDEFINED_EXCHANGE_ITEM = "undefined.exchange.item";
  String TOOL_LCCII_CREATE_EXCHANGE_ITEM_ALLOCATION = "exchange.item.allocation";
  String TOOL_LCCII_CREATE_TRANSMIT = "transmit";
  String TOOL_LCCII_CREATE_ACQUIRE = "acquire";
  String TOOL_LCCII_INSERT_REMOVE_ACTORS = "actors";
  String TOOL_LCCII_INSERT_REMOVE_COMPONENTS = "components";
  String TOOL_LCCII_INSERT_REMOVE_INTERFACES = "interfaces";
  String TOOL_LCCII_INSERT_REMOVE_EXCHANGE_ITEMS = "exchange.items";
  String TOOL_LCCII_CREATE_CONSTRAINT = "constraint";
  String TOOL_LCCII_CREATE_CONSTRAINTELEMENT = "constraint.element";
  String TOOL_LCCII_INSERT_REMOVE_CONSTRAINTS = "constraints";
  // the ID of "Port Delegation" tool
  String TOOL_LCCII_CREATE_PORT_DELEGATION = "Port Delegation";
  String TOOL_LCCII_CREATE_COMMUNICATION_LINK_TRANSMIT = "CommunicationLink Send / Produce / Call / Write / Transmit";
  String TOOL_LCCII_CREATE_COMMUNICATION_LINK_ACQUIRE = "CommunicationLink Receive / Consume / Execute / Access / Acquire";

  // LDFB tools - Logical Data Flow Blank tools
  String TOOL_LDFB_CREATE_LOGICAL_FUNCTION = "logical.function";
  String TOOL_LDFB_CREATE_DUPLICATE = "duplicate";
  String TOOL_LDFB_CREATE_GATHER = "gather";
  String TOOL_LDFB_CREATE_ROUTE = "route";
  String TOOL_LDFB_CREATE_SELECT = "select";
  String TOOL_LDFB_CREATE_SPLIT = "split";
  String TOOL_LDFB_CREATE_INPUT_PORT = "input.port";
  String TOOL_LDFB_CREATE_OUTPUT_PORT = "output.port";
  String TOOL_LDFB_CREATE_FUNCTIONAL_EXCHANGE = "functional.exchange";
  String TOOL_LDFB_INSERT_REMOVE_FUNCTIONS = "functions";
  String TOOL_LDFB_INSERT_REMOVE_FUNCTIONAL_EXCHANGES = "functional.exchanges";
  String TOOL_LDFB_INSERT_REMOVE_FUNCTIONAL_CHAINS = "functional.chains";
  String TOOL_LDFB_INSERT_REMOVE_FUNCTIONAL_CHAIN_ELEMENTS = "functional.chain.elements";
  String TOOL_LDFB_CREATE_CONSTRAINT = "constraint";
  String TOOL_LDFB_CREATE_CONSTRAINT_ELEMENT = "constraint.element";
  String TOOL_LDFB_INSERT_REMOVE_CONSTRAINTS = "constraints";
  // Accelerators
  String TOOL_LDFB_INITIALIZATION_FROM_EXISTING_DIAGRAM = "initialization.from.existing.diagram";
  // unsynchronized tools
  String TOOL_LDFB_INSERT_REMOVE_FUNCTION_PORTS = "function.ports";
  String TOOL_LDFB_INSERT_REMOVE_EXCHANGE_CATEGORIES = "exchange.categories";
  // other tools and id tool
  String TOOL_LDFB_CREATE_FUNCTIONAL_CHAIN = "functional.chain";
  String TOOL_LDFB_SHOW_HIDE_FUNCTIONAL_CHAIN = "Show/Hide Functional Chains";
  String TOOL_LDFB_SHOW_HIDE_FUNCTIONAL_EXCH_CATEGORIES = "switch.functional.exchanges.categories";
  String TOOL_LDFB_SHOW_HIDE_EXCH_CATEGORIES = "Show/Hide Exchange Categories";
  String TOOL_LDFB_SHOW_HIDE_FUNCTIONS = "Show/Hide Functions";
  String TOOL_LDFB_SHOW_HIDE_FUNCTIONAL_EXCHANGES = "Show/Hide Functional Exchanges";
  String TOOL_LDFB_SHOW_HIDE_FUNCTION_PORTS = "Show/Hide Function Ports";
  String TOOL_LDFB_SHOW_HIDE_FUNCTIONAL_EXCHANGE = "Show/Hide Functional Exchanges";
  String TOOL_LDFB_SHOW_HIDE_FUNCTION_PORT = "Show/Hide Function Ports";
  String TOOL_LDFB_DELETE_ELEMENT = "delete capella element";
  String TOOL_LDFB_SHOW_HIDE_FUNCTION = "Show/Hide Functions";
  String TOOL_LDFB_INSERT_SCENARIO_ELEMENTS = TOOL_LAB_INSERT_SCENARIO_ELEMENTS;
  String TOOL_LDFB_INSERT_STATEMODE_ELEMENTS = TOOL_LAB_INSERT_STATEMODE_ELEMENTS;
  String TOOL_LDFB_RECONNECT_EXCHANGE = "LDFB Reconnect Exchanges";

  String TOOL_LDFB_INSERT_FUNCTIONS_FROM_MODE_STATE = "functions.from.mode.state";
  String TOOL_LDFB_INSERT_ELEMENTS_FROM_SCENARIO = "elements.from.scenario";
  String TOOL_LDFB_STATE_MODE_ELEMENTS_ID = "State-Mode Elements";
  String TOOL_LDFB_SCENARIO_ELEMENTS_ID = "Scenario Elements";

  // LFBD tools - Logical Function Breakdown
  String TOOL_LFBD_CREATE_LOGICAL_FUNCTION = "logical.function";
  String TOOL_LFBD_CREATE_DUPLICATE = "duplicate";
  String TOOL_LFBD_CREATE_GATHER = "gather";
  String TOOL_LFBD_CREATE_ROUTE = "route";
  String TOOL_LFBD_CREATE_SELECT = "select";
  String TOOL_LFBD_CREATE_SPLIT = "split";
  String TOOL_LFBD_CONTAINED_IN = "contained.in";
  String TOOL_LFBD_CREATE_CONSTRAINT = "constraint";
  String TOOL_LFBD_CREATE_CONSTRAINTELEMENT = "constraint.element";
  String TOOL_LFBD_INSERT_REMOVE_CONSTRAINTS = "constraints";

  // Logical (la layer) Interface Diagram Blank (LIDB) tools
  String TOOL_LIDB_SHOW_HIDE_COMPONENTS = "Show/Hide Components";
  String TOOL_LIDB_CREATE_LOGICAL_COMPONENT = "Component";
  String TOOL_LIDB_CREATE_ACTOR = "Actor";
  String TOOL_LIDB_CREATE_ECHANGE_ITEM_EVENT = "Event";
  String TOOL_LIDB_CREATE_ECHANGE_ITEM_OPERATION = "Operation";
  String TOOL_LIDB_CREATE_ECHANGE_ITEM_FLOW = "Flow";
  String TOOL_LIDB_CREATE_ECHANGE_ITEM_DATA = "Data";
  String TOOL_LIDB_CREATE_EXCHANGE_ITEM_ELEMENT = "Exchange Item Element";
  String TOOL_LIDB_CREATE_INTERFACE = "Interface";
  String TOOL_LIDB_MANAGE_EXCHANGE_ITEM_ALLOCATIONS = "Manage Exchange Item Allocations";
  String TOOL_LIDB_CREATE_COMMUNICATION_LINK_TRANSMIT = "CommunicationLink Send / Produce / Call / Write / Transmit";
  String TOOL_LIDB_CREATE_COMMUNICATION_LINK_ACQUIRE = "CommunicationLink Receive / Consume / Execute / Access / Acquire";
  String TOOL_LIDB_CREATE_USES = "Uses";
  String TOOL_LIDB_CREATE_PROVIDES = "Provides";
  String TOOL_LIDB_CREATE_REQUIRES = "Requires";
  String TOOL_LIDB_CREATE_GENERALIZATION = "Generalization";
  String TOOL_LIDB_CREATE_INOUT_FLOW_PORT_PORT = "InOut Flow Port";
  String TOOL_LIDB_CREATE_STANDARD_PORT = "Standard Port";
  String TOOL_LIDB_CREATE_PORT_DELEGATION = "Port Delegation";
  String TOOL_LIDB_CREATE_IMPLEMENTS = "Implements";
  String TOOL_LIDB_CREATE_EXCHANGE_ITEM_ALLOCATION = "Exchange Item Allocation";

  // MB tools - Missions Blank
  String TOOL_MB_CREATE_ACTOR = "actor";
  String TOOL_MB_CREATE_CAPABILITY = "capability";
  String TOOL_MB_CREATE_MISSION = "mission";
  String TOOL_MB_CREATE_CAPABILITY_EXPLOITATION = "capability.exploitation";
  String TOOL_MB_CREATE_ACTOR_INVOLVEMENT = "actor.involvement";
  String TOOL_MB_CREATE_ACTOR_GENERALIZATION = "actor.generalization";
  String TOOL_MB_INSERT_REMOVE_ACTORS = "actors";
  String TOOL_MB_INSERT_REMOVE_CAPABILITIES = "capabilities";
  String TOOL_MB_INSERT_REMOVE_MISSIONS = "missions";
  String TOOL_MB_INSERT_REMOVE_RELATIONSHIPS = "relationships";
  String TOOL_MB_CREATE_CONSTRAINT = "constraint";
  String TOOL_MB_CREATE_CONSTRAINTELEMENT = "constraint.element";
  String TOOL_MB_INSERT_REMOVE_CONSTRAINTS = "constraints";
  String TOOL_MB_RECONNECT_GENERALIZATION_SOURCE = TOOL_CC_RECONNECT_GENERALIZATION_SOURCE;
  String TOOL_MB_RECONNECT_GENERALIZATION_TARGET = TOOL_CC_RECONNECT_GENERALIZATION_TARGET;
  // tool id
  String TOOL_MB_SHOW_ACTOR = "s/h Actors";
  String TOOL_MB_SHOW_MISSION = "s/h Missions";
  String TOOL_MB_SHOW_CAPABILITY = "s/h Capabilities";

  // MCB tools - Mission Capabilities Blank
  String TOOL_MCB_CREATE_ACTOR = "actor";
  String TOOL_MCB_CREATE_MISSION = "mission";
  String TOOL_MCB_CREATE_CAPABILITY = "capability";
  String TOOL_MCB_CREATE_CAPABILITY_EXPLOITATION = "capability.exploitation";
  String TOOL_MCB_CREATE_INVOLVED_ACTOR = "involved.actor";
  String TOOL_MCB_CREATE_EXTENDS = "extends";
  String TOOL_MCB_CREATE_INCLUDES = "includes";
  String TOOL_MCB_CREATE_ACTOR_GENERALIZATION = "actor.generalization";
  String TOOL_MCB_CREATE_CAPABILITY_GENERALIZATION = TOOL_MCB_CREATE_ACTOR_GENERALIZATION;
  String TOOL_MCB_INSERT_REMOVE_ACTORS = "actors";
  String TOOL_MCB_INSERT_REMOVE_ACTORS_LABEL = "actors";
  String TOOL_MCB_INSERT_REMOVE_MISSIONS = "missions";
  String TOOL_MCB_INSERT_REMOVE_MISSIONS_LABEL = "missions";
  String TOOL_MCB_INSERT_REMOVE_CAPABILITIES = "capabilities";
  String TOOL_MCB_INSERT_REMOVE_CAPABILITIES_LABEL = "capabilities";
  String TOOL_MCB_INSERT_REMOVE_RELATIONSHIPS = "relationships";
  String TOOL_MCB_INSERT_REMOVE_RELATIONSHIPS_LABEL = "relationships";
  String TOOL_MCB_CREATE_CONSTRAINT = "constraint";
  String TOOL_MCB_CREATE_CONSTRAINTELEMENT = "constraint.element";
  String TOOL_MCB_INSERT_REMOVE_CONSTRAINTS = "constraints";
  String TOOL_MCB_RECONNECT_GENERALIZATION_SOURCE = TOOL_CC_RECONNECT_GENERALIZATION_SOURCE;
  String TOOL_MCB_RECONNECT_GENERALIZATION_TARGET = TOOL_CC_RECONNECT_GENERALIZATION_TARGET;

  // M&S fools - Modes and States
  String TOOL_MS_CREATE_MODE = "Mode";
  String TOOL_MS_CREATE_STATE = "state";
  String TOOL_MS_REUSE_MODE_STATE = "reuse.mode.state";
  String TOOL_MS_CREATE_INITIAL = "Initial";
  String TOOL_MS_CREATE_JOIN = "Join";
  String TOOL_MS_CREATE_CHOICE = "Choice";
  String TOOL_MS_CREATE_FORK = "Fork";
  String TOOL_MS_CREATE_TERMINATE = "Terminate";
  String TOOL_MS_CREATE_FINAL = "Final";
  String TOOL_MS_CREATE_DEEP_HISTORY = "DeepHistory";
  String TOOL_MS_CREATE_SHALLOW_HISTORY = "ShallowHistory";
  String TOOL_MS_CREATE_ENTRY_POINT = "EntryPoint";
  String TOOL_MS_CREATE_EXIT_POINT = "ExitPoint";
  String TOOL_MS_CREATE_TRANSISTION = "Transition";
  String TOOL_MS_CREATE_CONSTRAINT = "Constraint";
  String TOOL_MS_CREATE_CONSTRAINTELEMENT = "ConstraintElement";
  String TOOL_MS_INSERT_REMOVE_CONSTRAINTS = "Constraints";
  // Unsynchronized
  String TOOL_MS_INSERT_REMOVE_MODE_STATE = "State/Mode";
  String TOOL_MS_INSERT_REMOVE_TRANSITION = "Transition";

  // Accelerators
  String TOOL_MS_INITIALIZATION_FROM_EXISTING_DIAGRAM = "Initialization from existing diagram";
  // tools id
  String TOOL_MS_INSERT_MODE_STATE = "Insert Mode/State";
  String TOOL_MS_SHOW_HIDE_MODE_STATE = "ShowHide ModeState";
  String TOOL_MS_SHOW_HIDE_TRANSITION = "ShowHide Transition";
  String TOOL_MS_RECONNECT_TARGET_TRANSISTION = "Reconnect target transition";
  String TOOL_MS_RECONNECT_SOURCE_TRANSISTION = "Reconnect source transition";
  String TOOL_MS_DND_STATES_DROM_DIAGRAM = "D&D ModeState from Diagram";
  String TOOL_MS_DND_PSEUDOSTATES_DROM_DIAGRAM = "D&D PseudoState from Diagram";

  // M&S fools - Modes and States
  String TOOL_MSM_CREATE_MODE = "mode";
  String TOOL_MSM_CREATE_STATE = "state";
  String TOOL_MSM_CREATE_REGION = "region";
  String TOOL_MSM_REUSE_MODE_STATE = "reuse.mode.state";
  String TOOL_MSM_CREATE_INITIAL = "initial";
  String TOOL_MSM_CREATE_JOIN = "join";
  String TOOL_MSM_CREATE_CHOICE = "choice";
  String TOOL_MSM_CREATE_FORK = "fork";
  String TOOL_MSM_CREATE_TERMINATE = "terminate";
  String TOOL_MSM_CREATE_FINAL = "final";
  String TOOL_MSM_CREATE_DEEP_HISTORY = "deep.history";
  String TOOL_MSM_CREATE_SHALLOW_HISTORY = "shallow.history";
  String TOOL_MSM_CREATE_ENTRY_POINT = "entry.point";
  String TOOL_MSM_CREATE_EXIT_POINT = "exit.point";
  String TOOL_MSM_CREATE_TRANSISTION = "transition";
  String TOOL_MSM_CREATE_CONSTRAINT = "constraint";
  String TOOL_MSM_CREATE_CONSTRAINTELEMENT = "constraint.element";
  String TOOL_MSM_INSERT_REMOVE_CONSTRAINTS = "constraints";
  // Unsynchronized
  String TOOL_MSM_INSERT_REMOVE_MODE_STATE = "state.mode";
  String TOOL_MSM_INSERT_REMOVE_TRANSITION = "transitions";

  // Accelerators
  String TOOL_MSM_INITIALIZATION_FROM_EXISTING_DIAGRAM = "initialization.from.existing.diagram";
  // tools id
  String TOOL_MSM_INSERT_MODE_STATE = "Insert Mode/State";
  String TOOL_MSM_SHOW_HIDE_MODE_STATE = "ShowHide ModeState";
  String TOOL_MSM_SHOW_HIDE_TRANSITION = "ShowHide Transition";
  String TOOL_MSM_RECONNECT_TARGET_TRANSISTION = "Reconnect target transition";
  String TOOL_MSM_RECONNECT_SOURCE_TRANSISTION = "Reconnect source transition";
  String TOOL_MSM_DND_STATES_DROM_DIAGRAM = "D&D ModeState from Diagram";
  String TOOL_MSM_DND_PSEUDOSTATES_DROM_DIAGRAM = "D&D PseudoState from Diagram";

  // OAB tools - Operational Architecture Blank
  // Entities
  String TOOL_OAB_CREATE_OE = "operational.entity";
  String TOOL_OAB_CREATE_OA = "operational.actor";
  String TOOL_OAB_CREATE_COMMUNICATION_MEAN = "communication.mean";
  String TOOL_OAB_INSERT_REMOVE_OPERATIONAL_ENTITIES = "operational.entities";
  String TOOL_OAB_INSERT_REMOVE_OPERATIONAL_ACTORS = "operational.actors";
  @Deprecated
  String TOOL_OAB_INSERT_REMOVE_COMMUNICATION_MEANS = "communication.means";
  String TOOL_OAB_CREATE_CONSTRAINT = "constraint";
  String TOOL_OAB_CREATE_CONSTRAINTELEMENT = "constraint.element";
  String TOOL_OAB_INSERT_REMOVE_CONSTRAINTS = "constraints";
  // Roles
  String TOOL_OAB_CREATE_ROLE = "role";
  String TOOL_OAB_MANAGE_ROLE_ALLOCATION = "manage.role.allocation";
  String TOOL_OAB_INSERT_REMOVE_ALL_ALLOCATED_ROLES = "all.allocated.roles";
  String TOOL_OAB_INSERT_REMOVE_ALLOCATED_ROLES = "allocated.roles";
  // Activities
  String TOOL_OAB_CREATE_OPERATIONAL_ACTIVITY = "operational.activity";
  String TOOL_OAB_MANAGE_ACTIVITY_ALLOCATION = "manage.activity.allocation";
  String TOOL_OAB_INSERT_REMOVE_INTERACTIONS = "interactions";
  String TOOL_OAB_CREATE_OPERATIONAL_PROCESS = "Operational Process";
  String TOOL_OAB_INSERT_REMOVE_OPERATIONAL_PROCESSES = "operational.processes";
  String TOOL_OAB_INSERT_REMOVE_ALLOCATED_ACTIVITIES = "allocated.activities";
  String TOOL_OAB_INSERT_REMOVE_ALL_ALLOCATED_ACTIVITIES_IN_ENTITIES = "all.allocated.activities.in.entities";
  String TOOL_OAB_INSERT_REMOVE_ALL_ALLOCATED_ACTIVITIES_IN_ROLES = "all.allocated.activities.in.roles";
  String TOOL_OAB_INSERT_SCENARIO_ELEMENTS = TOOL_LAB_INSERT_SCENARIO_ELEMENTS;
  String TOOL_OAB_INSERT_STATEMODE_ELEMENTS = TOOL_LAB_INSERT_STATEMODE_ELEMENTS;

  String TOOL_OAB_INSERT_ACTIVITIES_FROM_MODE_STATE = "activities.from.mode.state";
  String TOOL_OAB_INSERT_ELEMENTS_FROM_SCENARIO = "elements.from.scenario";
  String TOOL_OAB_STATE_MODE_ELEMENTS_ID = "State-Mode Elements";
  String TOOL_OAB_SCENARIO_ELEMENTS_ID = "Scenario Elements";
  // tools id
  String TOOL_OAB_RECONNECT_COMMUNICATION_MEAN_TARGET = "Target Communication Mean";
  String TOOL_OAB_RECONNECT_COMMUNICATION_MEAN_SOURCE = "Source Communication Mean";
  String TOOL_OAB_RECONNECT_INTERACTION = "OAIB Reconnect Exchanges";

  // OABD tools - Operational Activity Breakdown
  String TOOL_OABD_CREATE_OPERATIONAL_ACTIVITY = "operational.activity";
  String TOOL_OABD_CONTAINED_IN = "contained.in";
  String TOOL_OABD_CREATE_CONSTRAINT = "constraint";
  String TOOL_OABD_CREATE_CONSTRAINTELEMENT = "constraint.element";
  String TOOL_OABD_INSERT_REMOVE_CONSTRAINTS = "constraints";

  // OAIB tools - Operational Activity Interaction Blank Diagram
  String TOOL_OAIB_INSERT_REMOVE_OPERATIONAL_ACTIVITIES = "operational.activities";
  String TOOL_OAIB_INSERT_REMOVE_INTERACTIONS = "interactions";
  String TOOL_OAIB_SHOW_HIDE_OP = "operational.processes";
  String TOOL_OAIB_CREATE_OPERATIONAL_PROCESS_ELEMENTS = "operational.process.elements";
  String TOOL_OAIB_CREATE_CONSTRAINT = "constraint";
  String TOOL_OAIB_CREATE_CONSTRAINT_ELEMENT = "constraint.element";
  String TOOL_OAIB_INSERT_REMOVE_CONSTRAINTS = "constraints";
  String TOOL_OAIB_INSERT_SCENARIO_ELEMENTS = TOOL_LDFB_INSERT_SCENARIO_ELEMENTS;
  String TOOL_OAIB_INSERT_STATEMODE_ELEMENTS = TOOL_LDFB_INSERT_STATEMODE_ELEMENTS;

  String TOOL_OAIB_CREATE_OPERATIONAL_PROCESS = "operational.process";
  String TOOL_OAIB_SHOW_HIDE_OA = "Show/Hide Operational Activity";
  String TOOL_OAIB_SHOW_HIDE_OPERATIONAL_PROCESS = "operational.processes";
  String TOOL_OAIB_SHOW_OPERATIONAL_PROCESS_ELT = "Show Operational Process Elements";
  String TOOL_OAIB_SHOW_HIDE_CONSTRAINTS = "Show/Hide Constraints";
  String TOOL_OAIB_SHOW_HIDE_INTERACTION = "Show/Hide Interaction";
  String TOOL_OAIB_RECONNECT_EXCHANGES = "OAIB Reconnect Exchanges";
  String TOOL_OAIB_INSERT_ACTIVITIES_FROM_MODE_STATE = "activities.from.mode.state";
  String TOOL_OAIB_INSERT_ELEMENTS_FROM_SCENARIO = "elements.from.scenario";
  String TOOL_OAIB_STATE_MODE_ELEMENTS_ID = "State-Mode Elements";
  String TOOL_OAIB_SCENARIO_ELEMENTS_ID = "Scenario Elements";

  // OAS tools - Operational Activity Scenario
  String TOOL_OAS_CREATE_ACTIVITY = TOOL_CREATE_FUNCTION;
  String TOOL_OAS_CREATE_INTERACTION_WITH_RETURN_BRANCH = TOOL_CREATE_FUNCTIONAL_EXCHANGE_WITH_RETURN_BRANCH;
  String TOOL_OAS_CREATE_INTERACTION = TOOL_CREATE_FUNCTIONAL_EXCHANGE;
  String TOOL_OAS_INSERT_REMOVE_ACTIVITIES = TOOL_INSERT_REMOVE_FUNCTIONS;
  String TOOL_OAS_INSERT_REMOVE_STATE_MODE = TOOL_INSERT_REMOVE_STATE_MODE;
  String TOOL_OAS_CREATE_REFERENCE = TOOL_CREATE_REFERENCE;
  String TOOL_OAS_CREATE_LOOP = TOOL_CREATE_LOOP;
  String TOOL_OAS_CREATE_ALT = TOOL_CREATE_ALT;
  String TOOL_OAS_CREATE_PAR = TOOL_CREATE_PAR;
  String TOOL_OAS_CREATE_OTHER_COMBINED_FRAGMENT = TOOL_CREATE_OTHER_COMBINED_FRAGMENT;
  String TOOL_OAS_CREATE_OPERAND = TOOL_CREATE_OPERAND;
  String TOOL_OAS_CREATE_CONSTRAINT = "constraint";
  String TOOL_OAS_CREATE_CONSTRAINTELEMENTSCENARIO = "constraint.element.scenario";
  String TOOL_OAS_INSERT_REMOVE_CONSTRAINTS = "constraints";
  String TOOL_OAS_DURATION = TOOL_DURATION;
  String TOOL_OAS_EXCHANGE_CONTEXT = TOOL_EXCHANGE_CONTEXT;

  // tool id
  String TOOL_OAS_CREATE_FUNCTIONAL_EXCHANGE_WITH_RETURN_BRANCH = TOOL_CREATE_FUNCTIONAL_EXCHANGE_WITH_RETURN_BRANCH;
  String TOOL_OAS_CREATE_FUNCTIONAL_EXCHANGE = "Functional Exchange";

  // OCB - Operational Capabilities Blank (OCB) tools
  String TOOL_OCB_CREATE_OPERATIONAL_ENTITY = "operational.entity";
  String TOOL_OCB_CREATE_OPERATIONAL_ACTOR = "operational.actor";
  String TOOL_OCB_CREATE_OPERATIONAL_CAPABILITY = "operational.capability";
  String TOOL_OCB_CREATE_COMMUNICATION_MEAN = "communication.mean";
  String TOOL_OCB_CREATE_INVOLMENT = "involvement";
  String TOOL_OCB_CREATE_EXTENDS = "extends";
  String TOOL_OCB_CREATE_INCLUDES = "includes";
  String TOOL_OCB_CREATE_OPERATIONAL_CAPABILITY_GENERALIZATION = "operational.capability.generalization";
  String TOOL_OCB_INSERT_REMOVE_OPERATIONAL_ENTITIES = "operational.entities";
  String TOOL_OCB_INSERT_REMOVE_OPERATIONAL_ACTORS = "operational.actors";
  String TOOL_OCB_INSERT_REMOVE_OPERATIONAL_CAPABILITIES = "operational.capabilities";
  String TOOL_OCB_INSERT_REMOVE_RELATIONSHIPS = "relationships";
  String TOOL_OCB_CREATE_CONSTRAINT = "constraint";
  String TOOL_OCB_CREATE_CONSTRAINT_ELEMENT = "constraint.element";
  String TOOL_OCB_INSERT_REMOVE_CONSTRAINTS = "constraints";

  String TOOL_OCB_SHOW_HIDE_OPERATIONAL_ENTITY = "Show/Hide OE";
  String TOOL_OCB_SHOW_HIDE_OPERATIONAL_ACTORS = "Show/Hide OA";
  String TOOL_OCB_SHOW_HIDE_CAPABILITIES = "Show/Hide Operational Capabilities";

  // Operational Entity Blank Diagram Tools
  // Entities
  String TOOL_OEB_CREATE_OE = "operational.entity";
  String TOOL_OEB_CREATE_OA = "operational.actor";
  String TOOL_OEB_CREATE_COMMUNICATION_MEAN = "Communication Mean";
  String TOOL_OEB_INSERT_REMOVE_OPERATIONAL_ENTITIES = "Operational Entities";
  String TOOL_OEB_INSERT_REMOVE_OPERATIONAL_ACTORS = "Operational Actors";
  String TOOL_OEB_INSERT_REMOVE_COMMUNICATION_MEANS = "communication.means";
  String TOOL_OEB_CREATE_CONSTRAINT = "Constraint";
  String TOOL_OEB_CREATE_CONSTRAINTELEMENT = "ConstraintElement";
  String TOOL_OEB_INSERT_REMOVE_CONSTRAINTS = "Constraints";
  // Roles
  String TOOL_OEB_CREATE_ROLE = "Role";
  String TOOL_OEB_MANAGE_ROLE_ALLOCATION = "manage.role.allocation";
  String TOOL_OEB_INSERT_REMOVE_ALL_ALLOCATED_ROLES = "all.allocated.roles";
  String TOOL_OEB_INSERT_REMOVE_ALLOCATED_ROLES = "allocated.roles";
  // Activities
  String TOOL_OEB_CREATE_OPERATIONAL_ACTIVITY = "operational.activity";
  String TOOL_OEB_CREATE_INTERACTION = "Interaction";
  String TOOL_OEB_MANAGE_ACTIVITY_ALLOCATION = "Manage Activity Allocation";
  String TOOL_OEB_INSERT_REMOVE_INTERACTIONS = "Interactions";
  String TOOL_OEB_INSERT_REMOVE_OPERATIONAL_PROCESSES = "operational.processes";
  String TOOL_OEB_INSERT_REMOVE_ALLOCATED_ACTIVITIES = "Allocated Activities";
  String TOOL_OEB_INSERT_REMOVE_ALL_ALLOCATED_ACTIVITIES_IN_ENTITIES = "all.allocated.activities.in.entities";
  String TOOL_OEB_INSERT_REMOVE_ALL_ALLOCATED_ACTIVITIES_IN_ROLES = "all.allocated.activities.in.roles";
  String TOOL_OEB_INSERT_SCENARIO_ELEMENTS = TOOL_LAB_INSERT_SCENARIO_ELEMENTS;
  String TOOL_OEB_INSERT_STATEMODE_ELEMENTS = TOOL_LAB_INSERT_STATEMODE_ELEMENTS;

  String TOOL_OEB_SHOW_HIDE_OE = "Show/Hide OE";
  String TOOL_OEB_SHOW_HIDE_OA = "Show/Hide OA";
  String TOOL_OEB_SHOW_HIDE_COMMUNICATION_MEAN = "Show/Hide Communication Mean";
  String TOOL_OEB_SHOW_HIDE_ALLOCATED_ACTIVITIES = "Show/Hide Allocated Activities";
  String TOOL_OEB_CREATE_OPERATIONAL_PROCESS = "Operational Process";
  String TOOL_OEB_SHOW_HIDE_OP = "Show/Hide Operational Processes";
  String TOOL_OEB_SHOW_HIDE_ALLOCATED_ROLES = "Show/Hide Allocated Roles";
  String TOOL_OEB_SHOW_HIDE_ALL_ALLOCATED_ROLES = "Show/hide All Allocated Roles";
  String TOOL_OEB_ALL_ALLOCATED_ACTIVITIES_IN_ROLES = "Insert All Allocated Activities in Roles";
  String TOOL_OEB_ALL_ALLOCATED_ACTIVITIES_IN_ENTITIES = "all.allocated.activities.in.entities";
  String TOOL_OEB_SHOW_HIDE_INTERACTION = "Show/Hide Interaction";

  String TOOL_OEB_INSERT_ACTIVITIES_FROM_MODE_STATE = "Activities from Mode / State";
  String TOOL_OEB_INSERT_ELEMENTS_FROM_SCENARIO = "Elements from Scenario";
  String TOOL_OEB_STATE_MODE_ELEMENTS_ID = "State-Mode Elements";
  String TOOL_OEB_SCENARIO_ELEMENTS_ID = "Scenario Elements";
  String TOOL_OEB_INSERT_REMOVE_CATEGORIES = "Categories";
  String TOOL_OEB_INSERT_REMOVE_COMMUNICATION_MEANS_CATEGORIES = "Communication Means / Categories";

  // OEBD tools - Operational Entity Breakdown
  String TOOL_OEBD_CREATE_OE = "operational.entity";
  String TOOL_OEBD_CREATE_OA = "operational.actor";
  String TOOL_OEBD_CONTAINED_IN = "contained.in";
  String TOOL_OEBD_CREATE_CONSTRAINT = "constraint";
  String TOOL_OEBD_CREATE_CONSTRAINTELEMENT = "constraint.element";
  String TOOL_OEBD_INSERT_REMOVE_CONSTRAINTS = "constraints";

  // OES tools - Operational Entity Scenario
  String TOOL_OES_CREATE_OE = TOOL_CREATE_COMPONENT;
  String TOOL_OES_CREATE_OA = TOOL_CREATE_ACTOR;
  String TOOL_OES_CREATE_ROLE = "role";
  String TOOL_OES_INSERT_REMOVE_OPERATIONAL_ENTITIES_ROLES = TOOL_INSERT_REMOVE_ACTORS;
  String TOOL_OES_CREATE_INTERACTION_WITH_RETURN_BRANCH = "interaction.with.return.branch";
  String TOOL_OES_CREATE_INTERACTION = "interaction";
  String TOOL_OES_CREATE_MESSAGE = TOOL_CREATE_MESSAGE;
  String TOOL_OES_DELETE_MESSAGE = TOOL_DELETE_MESSAGE;
  String TOOL_OES_CREATE_REFERENCE = TOOL_CREATE_REFERENCE;
  String TOOL_OES_CREATE_LOOP = TOOL_CREATE_LOOP;
  String TOOL_OES_CREATE_ALT = TOOL_CREATE_ALT;
  String TOOL_OES_CREATE_PAR = TOOL_CREATE_PAR;
  String TOOL_OES_CREATE_OTHER_COMBINED_FRAGMENT = TOOL_CREATE_OTHER_COMBINED_FRAGMENT;
  String TOOL_OES_CREATE_OPERAND = TOOL_CREATE_OPERAND;
  String TOOL_OES_INSERT_REMOVE_ACTIVITY = "activity";
  String TOOL_OES_INSERT_REMOVE_STATE_MODE = TOOL_INSERT_REMOVE_STATE_MODE;
  String TOOL_OES_CREATE_COMMUNICATIONMEAN_RETURN_BRANCH = "communication.mean.return.branch";
  String TOOL_OES_CREATE_COMMUNICATION_MEAN = "communication.mean";
  String TOOL_OES_CREATE_CONSTRAINT = "constraint";
  String TOOL_OES_CREATE_CONSTRAINTELEMENT = "constraint.element";
  String TOOL_OES_INSERT_REMOVE_CONSTRAINTS = "constraints";
  String TOOL_OES_CREATE_ARM_TIMER = TOOL_CREATE_ARM_TIMER;
  String TOOL_OES_CREATE_CANCEL_TIMER = TOOL_CREATE_CANCEL_TIMER;
  String TOOL_OES_DURATION = TOOL_DURATION;
  String TOOL_OES_EXCHANGE_CONTEXT = TOOL_EXCHANGE_CONTEXT;

  // OES tool ids (different from the label)
  String TOOL_OES_CREATE_SEQUENCE_MESSAGE = "sequence.message";
  String TOOL_OES_CREATE_SEQUENCE_MESSAGE_WITH_RETURN_BRANCH = "sequence.message.with.return.branch";
  String TOOL_OES_CREATE_CONSTRAINTELEMENTSCENARIO = "constraint.element.scenario";
  String TOOL_OES_CREATE_ACTIVITY_STATE = "Activity state";

  // OPD tools - Operational Process Description
  String TOOL_OPD_INSERT_REMOVE_INVOLVE_OPERATIONAL_ACTIVITY = "Involve Operational Activity";
  String TOOL_OPD_INSERT_REMOVE_INVOLVE_INTERACTION = "Involve Interaction";
  String TOOL_OPD_INSERT_REMOVE_INVOLVE_OPERATIONAL_PROCESS = "Involve Operational Process";
  String TOOL_OPD_INSERT_REMOVE_INVOLVE_INTERACTION_AND_TARGET_ACTIVITY = "Involve Interaction & Target Activity";
  String TOOL_OPD_INSERT_REMOVE_INVOLVE_INTERACTION_AND_TARGET_OPERATIONAL_PROCESS = "Involve Interaction & Target Operational Process";
  String TOOL_OPD_CREATE_CONSTRAINT = "constraint";
  String TOOL_OPD_CREATE_CONSTRAINTELEMENT = "constraint.element";
  String TOOL_OPD_INSERT_REMOVE_CONSTRAINTS = "constraints";

  // ORB tools - Operational Role Blank diagram
  // Roles
  String TOOL_ORB_CREATE_OPERATIONAL_ROLE = "operational.role";
  String TOOL_ORB_INSERT_REMOVE_OPERATIONAL_ROLES = "operational.roles";
  String TOOL_ORB_CREATE_CONSTRAINT = "constraint";
  String TOOL_ORB_CREATE_CONSTRAINTELEMENT = "constraint.element";
  String TOOL_ORB_INSERT_REMOVE_CONSTRAINTS = "constraints";
  // Activities
  String TOOL_ORB_CREATE_OPERATIONAL_ACTIVITY = "operational.activity";
  String TOOL_ORB_CREATE_INTERACTION = "interaction";
  String TOOL_ORB_INSERT_REMOVE_INTERACTIONS = "interactions";
  String TOOL_ORB_MANAGE_ACTIVITY_ALLOCATION = "manage.activity.allocation";
  String TOOL_ORB_INSERT_REMOVE_ALLOCATED_ACTIVITIES = "allocated.activities";
  String TOOL_ORB_INSERT_REMOVE_ALL_ALLOCATED_ACTIVITIES = "all.allocated.activities";

  // Tool ID
  String TOOL_ORB_INSERT_OPERATIONAL_ROLE = "Insert Operational Role";
  String TOOL_ORB_SHOW_HIDE_ALLOCATED_ACTIVITIES = "Show/Hide Allocated Activities";
  String TOOL_ORB_INSERT_ALL_ALLOCATED_ACTIVITIES = "all.allocated.activities";
  String TOOL_ORB_SHOW_HIDE_INTERACTION = "Show/Hide Interaction";

  // PAB tools - Physical Architecture Blank
  // Node
  String TOOL_PAB_CREATE_NODE_PHYSICAL_COMPONENT = "node.pc";
  String TOOL_PAB_DEPLOY_NODE_PC = "deploy.node.pc";
  String TOOL_PAB_CREATE_PHYSICAL_ACTOR = "physical.actor";
  String TOOL_PAB_CREATE_PHYSICAL_LINK = "physical.link";
  String TOOL_PAB_CREATE_PHYSICAL_PORT = "physical.port";
  String TOOL_PAB_MANAGE_NODE_COMP_DEPLOYMENT = "manage.node.pcs.deployment";
  @Deprecated
  String TOOL_PAB_INSERT_REMOVE_NODE_PCS = "node.pcs.monopart";
  String TOOL_PAB_INSERT_REMOVE_ACTORS = "actors";
  String TOOL_PAB_INSERT_REMOVE_DEPLOYED_NODE_PCS = "deployed.node.pcs";
  String TOOL_PAB_INSERT_REMOVE_DEPLOYED_BEHAVIOUR_PCS = "deployed.behaviour.pcs";

  String TOOL_PAB_INSERT_REMOVE_PHYSICAL_LINKS = "physical.links";
  String TOOL_PAB_CREATE_COMPONENT_PORT_ALLOCATION = "component.port.allocation";
  String TOOL_PAB_INSERT_REMOVE_PHYSICAL_PATH = "physical.path";
  String TOOL_PAB_INSERT_REMOVE_PHYSICAL_LINKS_CATEGORIES = "switch.physical.links.categories";
  String TOOL_PAB_CREATE_CONSTRAINT = "constraint";
  String TOOL_PAB_CREATE_CONSTRAINT_ELEMENT = "constraint.element";
  String TOOL_PAB_INSERT_REMOVE_CONSTRAINTS = "constraints";
  // Behavior
  String TOOL_PAB_DEPLOY_BEHAVIOR_PC = "deploy.behavior.pc";
  String TOOL_PAB_CREATE_BEHAVIOR_PHYSICAL_COMPONENT = "behavior.pc";
  String TOOL_PAB_CREATE_COMPONENT_EXCHANGE = TOOL_CREATE_COMPONENT_EXCHANGE;
  String TOOL_PAB_CREATE_COMPONENT_EXCHANGE_WITH_DELEGATIONS = "component.exchange.with.delegations";
  String TOOL_PAB_CREATE_DELEGATION = "delegation";
  String TOOL_PAB_CREATE_INFLOW_PORT = "in.flow.port";
  String TOOL_PAB_CREATE_OUTFLOW_PORT = "out.flow.port";
  String TOOL_PAB_CREATE_INOUTFLOW_PORT = "in.out.flow.port";
  String TOOL_PAB_CREATE_STANDARD_PORT = "standard.port";
  String TOOL_PAB_MANAGE_BEHAVIOR_COMP_DEPLOYMENT = "manage.behavior.pcs.deployment";
  @Deprecated
  String TOOL_PAB_INSERT_REMOVE_BEHAVIOR_PCS = "behavior.pcs";
  String TOOL_PAB_INSERT_ALL_DEPLOYED_NODE_PCS = "all.deployed.node.pcs";
  String TOOL_PAB_INSERT_ALL_DEPLOYED_BEHAVIOUR_PCS = "all.deployed.behaviour.pcs";

  String TOOL_PAB_INSERT_REMOVE_COMPONENT_EXCHANGES = "component.exchanges";
  String TOOL_PAB_INSERT_REMOVE_COMPONENT_EXCHANGES_CATEGORIES = "switch.component.exchanges.categories";
  // Functions
  String TOOL_PAB_CREATE_DUPLICATE = "duplicate";
  String TOOL_PAB_CREATE_GATHER = "gather";
  String TOOL_PAB_CREATE_ROUTE = "route";
  String TOOL_PAB_CREATE_SELECT = "select";
  String TOOL_PAB_CREATE_SPLIT = "split";
  String TOOL_PAB_CREATE_FUNCTION_INPUT_PORT = "input.port";
  String TOOL_PAB_CREATE_FUNCTION_OUTPUT_PORT = "output.port";
  String TOOL_PAB_CREATE_PORT_ALLOCATION = "port.allocation";
  String TOOL_PAB_MANAGE_FUNCTION_ALLOCATION = "manage.function.allocation";
  String TOOL_PAB_INSERT_REMOVE_ALLOCATED_FUNCTIONS = "allocated.functions";
  String TOOL_PAB_INSERT_REMOVE_ALL_ALLOCATED_FUNCTIONS = "all.allocated.functions";
  String TOOL_PAB_INSERT_REMOVE_FUNCTIONAL_CHAINS = "functional.chains";
  String TOOL_PAB_INSERT_REMOVE_FUNCTIONAL_EXCHANGES = "functional.exchanges";
  String TOOL_PAB_INSERT_REMOVE_EXCHANGE_CATEGORIES = "switch.functional.exchanges.categories";
  String TOOL_PAB_INSERT_SCENARIO_ELEMENTS = TOOL_LAB_INSERT_SCENARIO_ELEMENTS;
  String TOOL_PAB_INSERT_STATEMODE_ELEMENTS = TOOL_LAB_INSERT_STATEMODE_ELEMENTS;
  String TOOL_PAB_INSERT_REMOVE_COMPONENT_PORT_ALLOCATION = "component.port.allocations";

  // Unsynchronized tools
  String TOOL_PAB_INSERT_REMOVE_PORTS = "behaviour.ports";
  String TOOL_PAB_INSERT_REMOVE_FUNCTION_PORTS = "function.ports";
  String TOOL_PAB_INSERT_REMOVE_CATEGORIES = "node.categories";
  // multi parts tools
  String TOOL_PAB_REUSE_PC = "reuse.behaviour.pc";
  String TOOL_PAB_REUSE_NODE_PC = "reuse.node.pc";
  String TOOL_PAB_REUSE_BEHAVIOR_PC = "reuse.behaviour.pc";
  String TOOL_PAB_REUSE_PHYSICAL_ACTOR = "reuse.physical.actor";
  String TOOL_PAB_CREATE_COMPONENT_EXCHANGE_WITH_PORTS = "component.exchange.with.ports";
  String TOOL_PAB_CREATE_COMPONENT_EXCHANGE_WITHOUT_PORTS = "component.exchange.without.ports";
  String TOOL_PAB_CREATE_COMPONENT_EXCHANGE_BETWEEN_TYPES = "component.exchange.between.types";

  // Others - tools id
  String TOOL_PAB_SHOW_HIDE_DEPLOYED_COMPONENT = "Show/Hide Deployed Components";
  String TOOL_PAB_SHOW_HIDE_COMPONENT_EXCHANGES = "Show/Hide ComponentExchanges";
  String TOOL_PAB_SHOW_HIDE_PHYSICAL_LINKS = "Show/Hide Physical Links";
  String TOOL_PAB_SHOW_HIDE_PORTS = "Show/Hide Ports";
  String TOOL_PAB_SHOW_HIDE_FUNCTION_PORTS = "Show/Hide Function Ports";
  String TOOL_PAB_CREATE_FUNCTIONAL_CHAIN = "functional.chain";
  String TOOL_PAB_CREATE_PHYSICAL_PATH = "physical.path";
  String TOOL_PAB_INSERT_PHYSICAL_ACTOR = "Show/Hide Actors";
  String TOOL_PAB_SHOW_HIDE_CONSTRAINT = "Show/Hide Constraints";
  String TOOL_PAB_SHOW_HIDE_FUNCTIONAL_CHAINS = "Show/Hide Functional Chains";
  String TOOL_PAB_SHOW_HIDE_FUNCTIONAL_EXCHANGES = "functional.exchanges";
  String TOOL_PAB_RECONNECT_PHYSICALLINK_TARGET = "Reconnect PhysicalLink Target";
  String TOOL_PAB_RECONNECT_PHYSICALLINK_SOURCE = "Reconnect PhysicalLink Source";
  String TOOL_PAB_RECONNECT_COMPONENTEXCHANGE_TARGET = "Reconnect ComponentExchange Target";
  String TOOL_PAB_RECONNECT_COMPONENTEXCHANGE_SOURCE = "Reconnect ComponentExchange Source";
  String TOOL_PAB_RECONNECT_FUNCTION_EXCHANGE = "PAB Reconnect Function Exchanges";
  String TOOL_PAB_CREATE_INPUT_PORT = "input.port";
  String TOOL_PAB_CREATE_OUTPUT_PORT = "output.port";
  String TOOL_PAB_CREATE_INOUT_FLOW_PORT_PORT = "in.out.flow.port";
  String TOOL_PAB_SHOW_HIDE_EXCHANGE_CATEGORIES = "Show/Hide Exchange Categories";
  String TOOL_PAB_SWITCH_COMPONENT_CATEGORIES = "Switch Component Categories";
  String TOOL_PAB_SWITCH_PHYSICAL_CATEGORIES = "Switch Physical Categories";

  String TOOL_PAB_INSERT_FUNCTIONS_FROM_MODE_STATE = "functions.from.mode.state";
  String TOOL_PAB_INSERT_ELEMENTS_FROM_SCENARIO = "elements.from.scenario";
  String TOOL_PAB_STATE_MODE_ELEMENTS_ID = "State-Mode Elements";
  String TOOL_PAB_SCENARIO_ELEMENTS_ID = "Scenario Elements";
  // Accelerators
  String TOOL_PAB_INITIALIZATION_FROM_EXISTING_DIAGRAM = "initialization.from.existing.diagram";

  // PCBD tools - Physical Component Breakdown
  String TOOL_PCBD_CREATE_NODE_PC = "node.pc";
  String TOOL_PCBD_CREATE_BEHAVIOR_PC = "behavior.pc";
  String TOOL_PCBD_CREATE_PHYSICAL_ACTOR = "actor";
  String TOOL_PCBD_CONTAINED_IN = "contained.in";
  String TOOL_PCBD_CREATE_CONSTRAINT = "constraint";
  String TOOL_PCBD_CREATE_CONSTRAINTELEMENT = "constraint.element";
  String TOOL_PCBD_INSERT_REMOVE_CONSTRAINTS = "constraints";
  // Multi parts tools
  String TOOL_PCBD_CREATE_PART = "create.part";

  // PD tools - Package Dependencies
  String TOOL_PD_INSERT_REMOVE_DATA_PACKAGES = "data.packages";
  String TOOL_PD_INSERT_REMOVE_INTERFACE_PACKAGES = "interface.packages";
  String TOOL_PD_INSERT_REMOVE_DEPENDENCIES = "dependencies";
  String TOOL_PD_CREATE_CONSTRAINT = "constraint";
  String TOOL_PD_CREATE_CONSTRAINTELEMENT = "constraint.element";
  String TOOL_PD_INSERT_REMOVE_CONSTRAINTS = "constraints";

  // tools ids
  String TOOL_PD_SHOW_HIDE_DATAPKGS = "Show/Hide DataPkgs";
  String TOOL_PD_SHOW_HIDE_INTERFACEPKGS = "Show/Hide InterfacePkgs";
  String TOOL_PD_SHOW_HIDE_DEPENDENT_PACKAGES = "Show/Hide Dependent Packages";

  // PDFB tools - Physical Data Flow Blank tools
  String TOOL_PDFB_CREATE_DUPLICATE = "duplicate";
  String TOOL_PDFB_CREATE_GATHER = "gather";
  String TOOL_PDFB_CREATE_ROUTE = "route";
  String TOOL_PDFB_CREATE_SELECT = "select";
  String TOOL_PDFB_CREATE_SPLIT = "split";
  String TOOL_PDFB_CREATE_INPUT_PORT = "input.port";
  String TOOL_PDFB_CREATE_OUTPUT_PORT = "output.port";
  String TOOL_PDFB_INSERT_FUNCTIONS = "functions";
  String TOOL_PDFB_INSERT_REMOVE_FUNCTIONAL_EXCHANGES = "functional.exchanges";
  String TOOL_PDFB_INSERT_REMOVE_FUNCTION_PORTS = "function.ports";
  String TOOL_PDFB_INSERT_REMOVE_EXCHANGE_CATEGORIES = "exchange.categories";
  String TOOL_PDFB_INSERT_REMOVE_FUNCTIONAL_CHAINS = "functional.chains";
  String TOOL_PDFB_CREATE_FUNCTIONAL_CHAIN_ELEMENTS = "functional.chain.elements";
  String TOOL_PDFB_CREATE_CONSTRAINT = "constraint";
  String TOOL_PDFB_CREATE_CONSTRAINT_ELEMENT = "constraint.element";
  String TOOL_PDFB_INSERT_REMOVE_CONSTRAINTS = "constraints";
  String TOOL_PDFB_INSERT_SCENARIO_ELEMENTS = TOOL_LDFB_INSERT_SCENARIO_ELEMENTS;
  String TOOL_PDFB_INSERT_STATEMODE_ELEMENTS = TOOL_LDFB_INSERT_STATEMODE_ELEMENTS;

  String TOOL_PDFB_CREATE_FUNCTIONAL_CHAIN = "functional.chain";
  String TOOL_PDFB_SHOW_HIDE_FUNCTIONAL_CHAIN = "Show/Hide Functional Chains";
  String TOOL_PDFB_SHOW_HIDE_FUNCTIONAL_EXCH_CATEGORIES = "switch.functional.exchanges.categories";
  String TOOL_PDFB_SHOW_HIDE_EXCH_CATEGORIES = "Show/Hide Exchange Categories";
  String TOOL_PDFB_SHOW_HIDE_FUNCTIONS = "Show/Hide Functions";
  String TOOL_PDFB_SHOW_HIDE_FUNCTIONAL_EXCHANGES = "Show/Hide Functional Exchanges";
  String TOOL_PDFB_SHOW_HIDE_FUNCTION_PORTS = "Show/Hide Function Ports";

  String TOOL_PDFB_INSERT_FUNCTIONS_FROM_MODE_STATE = "functions.from.mode.state";
  String TOOL_PDFB_INSERT_ELEMENTS_FROM_SCENARIO = "elements.from.scenario";
  String TOOL_PDFB_STATE_MODE_ELEMENTS_ID = "State-Mode Elements";
  String TOOL_PDFB_SCENARIO_ELEMENTS_ID = "Scenario Elements";
  // Accelerators
  String TOOL_PDFB_INITIALIZATION_FROM_EXISTING_DIAGRAM = "initialization.from.existing.diagram";

  // PFBD tools - Physical Function Breakdown
  String TOOL_PFBD_CREATE_PHYSICAL_FUNCTION = "physical.function";
  String TOOL_PFBD_CREATE_DUPLICATE = "duplicate";
  String TOOL_PFBD_CREATE_GATHER = "gather";
  String TOOL_PFBD_CREATE_ROUTE = "route";
  String TOOL_PFBD_CREATE_SELECT = "select";
  String TOOL_PFBD_CREATE_SPLIT = "split";
  String TOOL_PFBD_CONTAINED_IN = "contained.in";
  String TOOL_PFBD_CREATE_CONSTRAINT = "constraint";
  String TOOL_PFBD_CREATE_CONSTRAINTELEMENT = "constraint.element";
  String TOOL_PFBD_INSERT_REMOVE_CONSTRAINTS = "constraints";

  // tool id
  String TOOL_PDFB_RECONNECT_EXCHANGE = "PDFB Reconnect Function Exchanges";

  // PPD tools - Physical Path Description
  String TOOL_PPD_INSERT_REMOVE_INVOLVE_COMPONENT = "involve.component";
  String TOOL_PPD_INSERT_REMOVE_INVOLVE_PHYSICAL_PATH = "involve.physical.path";
  String TOOL_PPD_INSERT_REMOVE_INVOLVE_PHYSICAL_LINK = "involve.physical.link";
  String TOOL_PPD_INSERT_REMOVE_INVOLVE_PHYSICAL_LINK_AND_TARGET_COMPONENT = "involve.physical.link.and.target.component";
  String TOOL_PPD_INSERT_REMOVE_INVOLVE_PHYSICAL_LINK_AND_TARGET_PHYSICAL_PATH = "involve.physical.link.and.target.physical.path";
  String TOOL_PPD_CREATE_CONSTRAINT = "constraint";
  String TOOL_PPD_CREATE_CONSTRAINTELEMENT = "constraint.element";
  String TOOL_PPD_INSERT_REMOVE_CONSTRAINTS = "constraints";
  // multi parts tools
  String TOOL_PPD_INSERT_REMOVE_INVOLVE_PART = "involve.part";
  String TOOL_PPD_INSERT_REMOVE_INVOLVE_PHYSICAL_LINK_AND_TARGET_PART = "involve.physical.link.and.target.part";

  // SAB Tools - System Architecture Blank
  // Components
  String TOOL_SAB_CREATE_ACTOR = "actor";
  String TOOL_SAB_CREATE_IN_FLOW_PORT = "in.flow.port";
  String TOOL_SAB_CREATE_OUT_FLOW_PORT = "out.flow.port";
  String TOOL_SAB_CREATE_INOUT_FLOW_PORT = "in.out.flow.port";
  String TOOL_SAB_CREATE_STANDARD_PORT = "standard.port";

  // WARNING: label and id tool are different and are used in different tests
  String TOOL_SAB_CREATE_COMPONENT_EXCHANGE_LABEL = TOOL_CREATE_COMPONENT_EXCHANGE;
  String TOOL_SAB_CREATE_PHYSICAL_LINK = "physical.link";
  String TOOL_SAB_CREATE_PHYSICAL_PORT = "physical.port";
  String TOOL_SAB_INSERT_REMOVE_ACTORS = "actors";
  @Deprecated
  String TOOL_SAB_INSERT_REMOVE_COMPONENT_EXCHANGES = "component.exchanges";
  @Deprecated
  String TOOL_SAB_INSERT_REMOVE_PHYSICAL_LINKS = "physical.links";
  String TOOL_SAB_INSERT_REMOVE_PHYSICAL_PATH = "physical.path";
  String TOOL_SAB_INSERT_REMOVE_PHYSICAL_LINKS_CATEGORIES = "switch.physical.links.categories";
  String TOOL_SAB_CREATE_CONSTRAINT = "constraint";
  String TOOL_SAB_CREATE_CONSTRAINT_ELEMENT = "constraint.element";
  String TOOL_SAB_INSERT_REMOVE_CONSTRAINTS = "constraints";
  // Functions
  String TOOL_SAB_CREATE_DUPLICATE = "duplicate";
  String TOOL_SAB_CREATE_GATHER = "gather";
  String TOOL_SAB_CREATE_ROUTE = "route";
  String TOOL_SAB_CREATE_SELECT = "select";
  String TOOL_SAB_CREATE_SPLIT = "split";
  String TOOL_SAB_CREATE_FUNCTION_INPUT_PORT = "input.port";
  String TOOL_SAB_CREATE_FUNCTION_OUTPUT_PORT = "output.port";
  String TOOL_SAB_CREATE_PORT_ALLOCATION = "port.allocation";
  String TOOL_SAB_MANAGE_FUNCTION_ALLOCATION = "manage.function.allocation";
  String TOOL_SAB_INSERT_REMOVE_ALLOCATED_FUNCTIONS = "allocated.functions";
  String TOOL_SAB_INSERT_REMOVE_ALL_ALLOCATED_FUNCTIONS = "all.allocated.functions";
  String TOOL_SAB_INSERT_REMOVE_FUNCTIONAL_CHAINS = "functional.chains";
  String TOOL_SAB_INSERT_REMOVE_FUNCTIONAL_EXCHANGES = "functional.exchanges";
  String TOOL_SAB_INSERT_REMOVE_EXCHANGE_CATEGORIES = "switch.functional.exchanges.categories";
  String TOOL_SAB_INSERT_SCENARIO_ELEMENTS = TOOL_LAB_INSERT_SCENARIO_ELEMENTS;
  String TOOL_SAB_INSERT_STATEMODE_ELEMENTS = TOOL_LAB_INSERT_STATEMODE_ELEMENTS;

  String TOOL_SAB_INSERT_FUNCTIONS_FROM_MODE_STATE = "functions.from.mode.state";
  String TOOL_SAB_INSERT_ELEMENTS_FROM_SCENARIO = "elements.from.scenario";
  String TOOL_SAB_STATE_MODE_ELEMENTS_ID = "State-Mode Elements";
  String TOOL_SAB_SCENARIO_ELEMENTS_ID = "Scenario Elements";
  String TOOL_SAB_INSERT_REMOVE_CATEGORIES = "categories";
  String TOOL_SAB_INSERT_REMOVE_COMPONENT_EXCHANGES_CATEGORIES = "switch.component.exchanges.categories";
  // Accelerators
  String TOOL_SAB_INITIALIZATION_FROM_EXISTING_DIAGRAM = "initialization.from.existing.diagram";
  // Unsynchronized tools
  String TOOL_SAB_INSERT_REMOVE_FUNCTION_PORTS = "function.ports";
  String TOOL_SAB_INSERT_REMOVE_PORTS = "component.ports";
  // Multi parts tools
  String TOOL_SAB_REUSE_ACTOR = "reuse.actor";
  String TOOL_SAB_CREATE_COMPONENT_EXCHANGE_WITH_PORTS = "component.exchange.with.ports";
  String TOOL_SAB_CREATE_COMPONENT_EXCHANGE_WITHOU_PORTS = "component.exchange.without.ports";
  String TOOL_SAB_CREATE_COMPONENT_EXCHANGE_BETWEEN_TYPES = "component.exchange.between.types";

  // tools id
  String TOOL_SAB_CREATE_INFLOW_PORT = "in.flow.port";
  String TOOL_SAB_CREATE_OUTFLOW_PORT = "out.flow.port";
  String TOOL_SAB_INSERT_ACTOR = "Show/Hide Actors";
  String TOOL_SAB_CREATE_COMPONENT_EXCHANGE = "component.exchange.with.ports";

  String TOOL_SAB_SHOW_HIDE_COMPONENT_EXCHANGE = "component.exchanges";
  String TOOL_LAB_SHOW_HIDE_COMPONENT_EXCHANGE = "component.exchanges.delegations";
  String TOOL_PAB_SHOW_HIDE_COMPONENT_EXCHANGE = "component.exchanges.delegations";
  String TOOL_OAB_SHOW_HIDE_COMMUNICATION_MEAN = "communication.means";

  String TOOL_SAB_SHOW_HIDE_PHYSICAL_LINK = "Show/Hide Physical Links";
  String TOOL_LAB_SHOW_HIDE_PHYSICAL_LINK = "Show/Hide Physical Links";
  String TOOL_PAB_SHOW_HIDE_PHYSICAL_LINK = "Show/Hide Physical Links";

  String TOOL_SAB_SHOW_HIDE_PORTS = "Show/Hide Ports";
  String TOOL_SAB_SHOW_HIDE_CONSTRAINTS = "Show/Hide Constraints";
  String TOOL_SAB_CREATE_FUNCTIONAL_CHAIN = "functional.chain";
  String TOOL_SAB_SHOW_HIDE_FUNCTIONAL_CHAINS = "Show/Hide Functional Chains";
  String TOOL_SAB_SHOW_HIDE_FUNCTIONAL_EXCHANGES = "functional.exchanges";
  String TOOL_SAB_SHOW_HIDE_FUNCTION_PORTS = "Show/Hide Function Ports";
  String TOOL_SAB_RECONNECT_EXCHANGES_TARGET = "CA Reconnect Exchanges Target";
  String TOOL_SAB_RECONNECT_EXCHANGES_SOURCE = "CA Reconnect Exchanges Source";
  String TOOL_SAB_RECONNECT_FUNCTION_EXCHANGES = "SAB Reconnect Function Exchanges";

  String TOOL_SAB_RECONNECT_PHYSICALLINK_SOURCE_ID = "Reconnect PhysicalLink Source";
  String TOOL_SAB_RECONNECT_PHYSICALLINK_TARGET_ID = "Reconnect PhysicalLink Target";
  String TOOL_LAB_RECONNECT_PHYSICALLINK_TARGET_ID = "Reconnect PhysicalLink Target";
  String TOOL_LAB_RECONNECT_PHYSICALLINK_SOURCE_ID = "Reconnect PhysicalLink Source";

  // SDFB tools - System Data Flow Blank tools
  String TOOL_SDFB_CREATE_OUTPUT_PORT = "output.port";
  String TOOL_SDFB_CREATE_INPUT_PORT = "input.port";
  String TOOL_SDFB_CREATE_DUPLICATE = "duplicate";
  String TOOL_SDFB_CREATE_GATHER = "gather";
  String TOOL_SDFB_CREATE_ROUTE = "route";
  String TOOL_SDFB_CREATE_SELECT = "select";
  String TOOL_SDFB_CREATE_SPLIT = "split";
  String TOOL_SDFB_CREATE_FUNCTIONAL_CHAIN = "functional.chain";
  String TOOL_SDFB_RECONNECT_EXCHANGE = "SDFB Reconnect Exchanges";
  String TOOL_SDFB_SHOW_HIDE_EXCH_CATEGORIES = "Show/Hide Exchange Categories";
  String TOOL_SDFB_SHOW_HIDE_FUNCTIONS = "functions";
  String TOOL_SDFB_SHOW_HIDE_FUNCTIONAL_CHAIN = "Show/Hide Functional Chains";
  String TOOL_SDFB_SHOW_HIDE_FUNCTIONAL_EXCH_CATEGORIES = "switch.functional.exchanges.categories";
  String TOOL_SDFB_SHOW_HIDE_FUNCTIONAL_EXCHANGES = "Show/Hide Functional Exchanges";
  String TOOL_SDFB_SHOW_HIDE_FUNCTION_PORTS = "Show/Hide Function Ports";
  String TOOL_SDFB_INSERT_SCENARIO_ELEMENTS = TOOL_LDFB_INSERT_SCENARIO_ELEMENTS;
  String TOOL_SDFB_INSERT_STATEMODE_ELEMENTS = TOOL_LDFB_INSERT_STATEMODE_ELEMENTS;
  String TOOL_SDFB_DND_SYSTEM_FUNCTION = "DnD DF AbstractFunction";
  String TOOL_SDFB_RECONNECT_EXCHANGES = "SDFB Reconnect Exchanges";

  // SDFB tool constants added
  String TOOL_SDFB_INSERT_REMOVE_FUNCTIONS = "functions";
  String TOOL_SDFB_INSERT_REMOVE_FUNCTIONAL_EXCHANGES = "functional.exchanges";
  String TOOL_SDFB_INSERT_REMOVE_FUNCTIONAL_CHAINS = "functional.chains";
  String TOOL_SDFB_CREATE_FUNCTIONAL_CHAIN_ELEMENTS = "functional.chain.elements";
  String TOOL_SDFB_CREATE_CONSTRAINT = "constraint";
  String TOOL_SDFB_CREATE_CONSTRAINT_ELEMENT = "constraint.element";
  String TOOL_SDFB_INSERT_REMOVE_CONSTRAINTS = "constraints";

  String TOOL_SDFB_INSERT_FUNCTIONS_FROM_MODE_STATE = "functions.from.mode.state";
  String TOOL_SDFB_INSERT_ELEMENTS_FROM_SCENARIO = "elements.from.scenario";
  String TOOL_SDFB_STATE_MODE_ELEMENTS_ID = "State-Mode Elements";
  String TOOL_SDFB_SCENARIO_ELEMENTS_ID = "Scenario Elements";
  // Accelerators
  String TOOL_SDFB_INITIALIZATION_FROM_EXISTING_DIAGRAM = "initialization.from.existing.diagram";
  // Unsynchronized tools
  String TOOL_SDFB_INSERT_REMOVE_FUNCTION_PORTS = "function.ports";
  String TOOL_SDFB_INSERT_REMOVE_EXCHANGE_CATEGORIES = "exchange.categories";
  String TOOL_SDFB_EXCHANGE_CONTEXT = "Exchange Context";

  // SFBD tools - System Function Breakdown
  String TOOL_SFBD_CREATE_SYSTEM_FUNCTION = "system.function";
  String TOOL_SFBD_CREATE_DUPLICATE = "duplicate";
  String TOOL_SFBD_CREATE_GATHER = "gather";
  String TOOL_SFBD_CREATE_ROUTE = "route";
  String TOOL_SFBD_CREATE_SELECT = "select";
  String TOOL_SFBD_CREATE_SPLIT = "split";
  String TOOL_SFBD_CONTAINED_IN = "contained.in";
  String TOOL_SFBD_CREATE_CONSTRAINT = "constraint";
  String TOOL_SFBD_CREATE_CONSTRAINTELEMENT = "constraint.element";
  String TOOL_SFBD_INSERT_REMOVE_CONSTRAINTS = "constraints";

  // System (ex-CTX layer) Interface Diagram Blank (SIDB) tools
  String TOOL_SIDB_SHOW_HIDE_COMPONENTS = "Show/Hide Components";
  String TOOL_SIDB_CREATE_ACTOR = "Actor";
  String TOOL_SIDB_CREATE_ECHANGE_ITEM_EVENT = "Event";
  String TOOL_SIDB_CREATE_ECHANGE_ITEM_OPERATION = "Operation";
  String TOOL_SIDB_CREATE_ECHANGE_ITEM_FLOW = "Flow";
  String TOOL_SIDB_CREATE_ECHANGE_ITEM_DATA = "Data";
  String TOOL_SIDB_CREATE_UNDEFINED_ECHANGE_ITEM = "Undefined Exchange Item";
  String TOOL_SIDB_CREATE_INTERFACE = "Interface";
  String TOOL_SIDB_CREATE_COMMUNICATION_LINK_TRANSMIT = "CommunicationLink Send / Produce / Call / Write / Transmit";
  String TOOL_SIDB_CREATE_COMMUNICATION_LINK_ACQUIRE = "CommunicationLink Receive / Consume / Execute / Access / Acquire";
  String TOOL_SIDB_MANAGE_EXCHANGE_ITEM_ALLOCATIONS = "Manage Exchange Item Allocations";
  String TOOL_SIDB_SHOW_HIDE_ACTORS = "Show/Hide Actors";
  String TOOL_SIDB_SHOW_HIDE_EXCHANGE_ITEMS = "Show/Hide Exchange Items";
  String TOOL_SIDB_SHOW_HIDE_ITEM_ALLOCATIONS = "Show/Hide Exchange Item Allocations";
  String TOOL_SIDB_SHOW_HIDE_EXCHANGE_ITEM_ELEMENTS = "Show/Hide Exchange Item Elements";
  String TOOL_SIDB_CREATE_EXCHANGE_ITEM_ELEMENT = "Exchange Item Element";

  // Common diagram tools id of LAB, PAB and SAB
  String TOOL_XAB_SHOW_HIDE_ALLOCATED_FUNCTIONS = "show.hide.allocated.functions";
  String TOOL_XAB_SHOW_ALL_ALLOCATED_FUNCTIONS = "show.all.allocated.functions";
  String TOOL_XAB_SHOW_HIDE_COMPONENTS = "show.hide.components";

  String TOOL_XAB_INSERT_REMOVE_COMPONENTS_MONOPART = "components.monopart";
  String TOOL_XAB_INSERT_REMOVE_COMPONENTS_MULTIPART = "components.multipart";
  String TOOL_PAB_INSERT_REMOVE_NODE_COMPONENTS_MONOPART = "node.pcs.monopart";
  String TOOL_PAB_INSERT_REMOVE_NODE_COMPONENTS_MULTIPART = "node.pcs.multipart";
  String TOOL_PAB_INSERT_REMOVE_BEHAVIOUR_COMPONENTS_MONOPART = "behavior.pcs.monopart";
  String TOOL_PAB_INSERT_REMOVE_BEHAVIOUR_COMPONENTS_MULTIPART = "behavior.pcs.multipart";
  //
  // Representation Descriptions moved to IDiagramNameConstants
  //

  // TABLE TOOLS - Currently there are only two operations available
  // 1. Create a cell value with "X" and delete the cell value -- which is an
  // empty string ""

  String INTERFACES_SCENARIOS_DIAGRAM_NAME = "Interfaces - Scenarios";

  String TABLE_TOOL_CREATE_CELL_VALUE = "X";
  String TABLE_TOOL_DELETE_CELL_VALUE = ICommonConstants.EMPTY_STRING;

  /**
   * Tool on IS, ES diagrams to create multiple instance roles for identical represented
   */
  String TOOL_SCENARIO_MULTI_INSTANCEROLE_COMPONENT = "add.multiple.lifelines.for.an.existing.component";
  String TOOL_SCENARIO_MULTI_INSTANCEROLE_ACTOR = "add.multiple.lifelines.for.an.existing.actor";
  String TOOL_SCENARIO_SYSTEM_FUNCTION = "system.function";
  String TOOL_SCENARIO_LOGICAL_FUNCTION = "logical.function";
  String TOOL_SCENARIO_PHYSICAL_FUNCTION = "physical.function";
  String TOOL_OES_MULTI_INSTANCEROLE_ENTITYACTOR = "add.multiple.lifelines.for.an.existing.entity.actor";

  /*
   * XAB
   */
  String TOOL_XAB_CREATE_PHYSICAL_LINK = "physical.link";
  String TOOL_XAB_CREATE_PHYSICAL_PORT = "physical.port";
  String TOOL_XAB_RECONNECT_PHYSICALLINK_SOURCE_ID = "Reconnect PhysicalLink Source";
  String TOOL_XAB_RECONNECT_PHYSICALLINK_TARGET_ID = "Reconnect PhysicalLink Target";
  String TOOL_XAB_SHOW_HIDE_PHYSICAL_LINK = "physical.links";
  String TOOL_XAB_CREATE_DELEGATION = "delegation";
  String TOOL_XAB_CREATE_COMPONENT_EXCHANGE_WITH_DELEGATIONS = "component.exchange.with.delegations";
  String TOOL_XAB_CREATE_COMPONENT_EXCHANGE_WITH_PORTS = "component.exchange.with.ports";
  String TOOL_XAB_CREATE_COMPONENT_EXCHANGE_WITHOUT_PORTS = "component.exchange.without.ports";
  String TOOL_XAB_CREATE_COMPONENT_EXCHANGE_BETWEEN_TYPES = "component.exchange.between.types";
  String TOOL_XAB_CREATE_FUNCTIONAL_EXCHANGE = "functional.exchange";
  String TOOL_XAB_CREATE_INFLOW_PORT = "in.flow.port";
  String TOOL_XAB_CREATE_OUTFLOW_PORT = "out.flow.port";
  String TOOL_XAB_CREATE_INOUT_FLOW_PORT = "in.out.flow.port";
  String TOOL_XAB_CREATE_STANDARD_PORT = "standard.port";
  String TOOL_XAB_INSERT_ACTORS_MONOPART = "actors.monopart";
  String TOOL_XAB_INSERT_ACTORS_MULTIPART = "actors.multipart";
  String TOOL_XAB_CREATE_FUNCTION_INPUT_PORT = "input.port";
  String TOOL_XAB_CREATE_FUNCTION_OUTPUT_PORT = "output.port";
  String TOOL_XAB_CREATE_PORT_ALLOCATION = "port.allocation";
  String TOOL_XAB_INSERT_REMOVE_PORT_ALLOCATION = "port.allocations";
  String TOOL_XAB_CREATE_DUPLICATE = "duplicate";
  String TOOL_XAB_CREATE_GATHER = "gather";
  String TOOL_XAB_CREATE_ROUTE = "route";
  String TOOL_XAB_CREATE_SELECT = "select";
  String TOOL_XAB_CREATE_SPLIT = "split";
  String TOOL_XAB_MANAGE_FUNCTION_ALLOCATION = "manage.function.allocation";
  String TOOL_XAB_INSERT_REMOVE_EXCHANGE_CATEGORIES = "switch.functional.exchanges.categories";
  String TOOL_XAB_INSERT_REMOVE_FUNCTIONAL_CHAINS = "functional.chains";
  String TOOL_XAB_INSERT_REMOVE_FUNCTION_PORTS = "function.ports";
  String TOOL_XAB_INSERT_REMOVE_CATEGORIES = "categories";
  String TOOL_XAB_INSERT_ELEMENTS_FROM_SCENARIO = "elements.from.scenario";
  String TOOL_XAB_INSERT_FUNCTIONS_FROM_MODE_STATE = "functions.from.mode.state";
  String TOOL_XAB_INSERT_REMOVE_PHYSICAL_PATH = "physical.paths";

  // Title Blocks tools
  String TOOL_CREATE_TITLE_BLOCK = "title.block";

  // insert/remove line/col TB
  String TOOL_INSERT_LINE_TITLE_BLOCK = "title.block.line";
  String TOOL_INSERT_COLUMN_TITLE_BLOCK = "title.block.column";
  String TOOL_REMOVE_LINE_TITLE_BLOCK = "remove.title.block.line";
  String TOOL_REMOVE_COLUMN_TITLE_BLOCK = "remove.title.block.column";

  // s/h TB
  String TOOL_INSERT_REMOVE_TITLE_BLOCK = "title.blocks";

  //delete TB tools
  String TOOL_DELETE_DIAGRAM_TITLE_BLOCK = "Delete Title Block";
  
}