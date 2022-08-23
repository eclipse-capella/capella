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
package org.polarsys.capella.core.sirius.analysis.constants;

/**
 * Centralizes constants about filters.
 */
@SuppressWarnings("nls")
public interface IFilterNameConstants {

  //
  // Filters
  //

  // Common filters
  String FILTER_COMMON_HIDE_PV = "hide.property.values.filter";

  // CC filters - Contextual Capability
  String FILTER_CC_HIDE_CAPABILITIES = "hide.capabilities.filter";
  String FILTER_CC_HIDE_CAPABILITY_EXPLOITATION = "hide.capability.exploitations.filter";
  String FILTER_CC_HIDE_CAPABILITY_EXTENDS = "hide.capability.extends.filter";
  String FILTER_CC_HIDE_CAPABILITY_INCLUDES = "hide.capability.includes.filter";
  String FILTER_CC_HIDE_CAPABILITY_GENERALIZATIONS = "hide.capability.generalizations.filter";
  String FILTER_CC_HIDE_MISSIONS = "hide.missions.filter";
  String FILTER_CC_HIDE_ACTORS = "hide.actors.filter";
  String FILTER_CC_HIDE_ACTOR_INVOLVEMENTS = "hide.actor.involvements.filter";
  String FILTER_CC_HIDE_ACTOR_GENERALIZATIONS = "hide.actor.generalizations.filter";
  String FILTER_CC_HIDE_PROPERTY_VALUES = "hide.property.values.filter";

  // CDB filters - Class Diagram Blank
  String FILTER_CDB_HIDE_PROPERTIES = "hide.properties.filter";
  String FILTER_CDB_HIDE_OPERATIONS = "hide.operations.filter";
  String FILTER_CDB_HIDE_ASSOCIATIONS = "hide.associations.filter";
  String FILTER_CDB_HIDE_GENERALIZATIONS = "hide.generalizations.filter";
  String FILTER_CDB_HIDE_EXCHANGE_ITEMS_DETAILS_IN_INTERFACES = "hide.exchange.items.details.in.interfaces.filter";
  String FILTER_CDB_HIDE_ASSOCIATION_LABELS = "hide.association.labels.filter";
  String FILTER_CDB_HIDE_ROLE_LABELS = "hide.role.labels.filter";
  String FILTER_CDB_SHOW_FULL_PATH = "show.full.path.filter";
  String FILTER_CDB_HIDE_DERIVED_PROPERTIES = "hide.derived.properties.filter";
  String FILTER_CDB_HIDE_TECHNICALS_INTERFACES = "hide.technical.interfaces.filter";
  String FILTER_CDB_HIDE_ROLE_NAMES = "hide.role.names.filter";
  String FILTER_CDB_SHOW_MODIFIERS = "show.modifiers.filter";
  String FILTER_CDB_HIDE_PROPERTY_VALUES = "hide.property.values.filter";
  String FILTER_COMMON_HIDE_DIAGRAM_TITLE_BLOCKS = "hide.diagram.title.blocks.filter";
  String FILTER_COMMON_HIDE_ELEMENT_TITLE_BLOCKS = "hide.element.title.blocks.filter";

  // CDI filters - Contextual Component Detailed Interfaces
  String FILTER_CDI_HIDE_INTERFACE_CONTENTS = "hide.interface.contents.filter";
  String FILTER_CDI_HIDE_INTERFACES = "hide.interfaces.filter";
  String FILTER_CDI_HIDE_EXCHANGE_ITEMS_DETAILS_IN_INTERFACES = "hide.exchange.items.details.in.interfaces.filter";
  String FILTER_CDI_HIDE_EXCHANGE_ITEM_ELEMENTS = "hide.exchange.item.elements.filter";
  String FILTER_CDI_HIDE_EXCHANGE_ITEMS = "hide.exchange.items.filter";
  String FILTER_CDI_HIDE_COMPONENT_PORTS = "hide.component.ports.filter";
  String FILTER_CDI_HIDE_USE_LINKS = "hide.use.links.filter";
  String FILTER_CDI_HIDE_IMPLEMENTATION_LINKS = "hide.implmentation.links.filter";
  String FILTER_CDI_HIDE_PROVIDE_LINKS = "hide.provide.links.filter";
  String FILTER_CDI_HIDE_REQUIRE_LINKS = "hide.require.links.filter";
  String FILTER_CDI_HIDE_COMMUNICATION_LINKS = "hide.communication.links.filter";
  String FILTER_CDI_HIDE_GENERALIZATION_LINKS = "hide.generalization.links.filter";
  String FILTER_CDI_HIDE_TECHNICALS_INTERFACES = "hide.technical.interfaces.filter";
  String FILTER_CDI_SHOW_MODIFIERS = "show.modifiers.filter";
  String FILTER_CDI_HIDE_PROPERTY_VALUES = "hide.property.values.filter";

  // filter id
  String FILTER_CDI_HIDE_IMPLEMENTATION_LINKS_ID = "hide.implmentation.links.filter";

  // CEI filters - Contextual Component External Interface
  String FILTER_CEI_HIDE_INTERFACES = "hide.interfaces.filter";
  String FILTER_CEI_HIDE_EXCHANGEITEM_ALLOCATION = "hide.exchange.item.allocation.filter";
  String FILTER_CEI_HIDE_EXCHANGE_ITEMS = "hide.exchange.items.filter";
  String FILTER_CEI_HIDE_COMPONENT_PORTS = "hide.component.ports.filter";
  String FILTER_CEI_HIDE_USE_LINKS = "hide.use.links.filter";
  String FILTER_CEI_HIDE_IMPLEMENTATION_LINKS = "hide.implmentation.links.filter";
  String FILTER_CEI_HIDE_PROVIDE_LINKS = "hide.provide.links.filter";
  String FILTER_CEI_HIDE_REQUIRE_LINKS = "hide.require.links.filter";
  String FILTER_CEI_HIDE_COMMUNICATION_LINKS = "hide.communication.links.filter";
  String FILTER_CEI_HIDE_GENERALIZATION_LINKS = "hide.generalization.links.filter";
  String FILTER_CEI_HIDE_SIMPLIFIED_COMPONENT_INTERACTIONS = "hide.simplified.component.interactions.filter";
  String FILTER_CEI_HIDE_TECHNICALS_INTERFACES = "hide.technical.interfaces.filter";

  // filter id
  String FILTER_CEI_HIDE_IMPLEMENTATION_LINKS_ID = "hide.implmentation.links.filter";

  // COAI filters - Contextual Operational Activity Interaction
  String FILTER_COAI_HIDE_INTERACTIONS = "hide.interactions.filter";
  String FILTER_COAI_BACKUP_SHOW_EXCHANGEITEMS = "show.exchange.items.filters";
  String FILTER_COAI_SHOW_EXCHANGEITEMS = "show.exchange.items.filter";
  String FILTER_COAI_SHOW_EXCHANGEITEMS_PARAMETERS = "show.exchange.items.parameters.filter";
  String FILTER_COAI_SHOW_FUNCTIONAL_EXCHANGES = "show.functional.exchanges.filter";

  // COC filters - Contextual Operational Capability
  String FILTER_COC_HIDE_ENTITIES = "hide.entities.filter";
  String FILTER_COC_HIDE_INVOLVEMENT_LINKS = "hide.involvement.links.filter";
  String FILTER_COC_HIDE_OPERATIONAL_CAPABILITY_EXTENDS = "hide.operational.capability.extends.filter";
  String FILTER_COC_HIDE_OPERATIONAL_CAPABILITY_INCLUDES = "hide.operational.capability.includes.filter";
  String FILTER_COC_HIDE_OPERATIONAL_CAPABILITY_GENERALIZATIONS = "hide.operational.capability.generalizations.filter";
  String FILTER_COC_HIDE_PROPERTY_VALUES = "hide.property.values.filter";

  // CM filters - Contextual Mission
  String FILTER_CM_HIDE_CAPABILITIES = "hide.capabilities.filter";
  String FILTER_CM_HIDE_CAPABILITY_EXPLOITATIONS = "hide.capability.exploitations.filter";
  String FILTER_CM_HIDE_CAPABILITY_GENERALIZATIONS = "hide.capabilities.generalizations.filter";
  String FILTER_CM_HIDE_ACTORS = "hide.actors.filter";
  String FILTER_CM_HIDE_ACTOR_INVOLVEMENTS = "hide.actor.involvements.filter";
  String FILTER_CM_HIDE_ACTOR_GENERALIZATIONS = "hide.actor.generalizations.filter";
  String FILTER_CM_HIDE_PROPERTY_VALUES = "hide.property.values.filter";

  // CRB filters - Capability Realization Blank
  String FILTER_CRB_HIDE_CAPABILITY_REALIZATIONS = "hide.capability.realizations.filter";
  String FILTER_CRB_HIDE_INVOLVEMENTS = "hide.involvements.filter";
  String FILTER_CRB_HIDE_CAPABILITY_EXTENDS = "hide.capability.extends.filter";
  String FILTER_CRB_HIDE_CAPABILTY_INCLUDES = "hide.capability.includes.filter";
  String FILTER_CRB_HIDE_CAPABILITY_REALIZATION_GENERALIZATIONS = "hide.capability.realization.generalizations.filter";
  String FILTER_CRB_HIDE_COMPONENTS = "hide.components.filter";
  String FILTER_CRB_HIDE_ACTORS = "hide.actors.filter";
  String FILTER_CRB_HIDE_ACTOR_GENERALIZATIONS = "hide.actor.generalizations.filter";

  // EAB filters - EPBS Architecture Blank
  String FILTER_EAB_HIDE_PHYSICAL_ARTIFACTS = "hide.physical.artifacts.filter";

  // ES filters - Exchange Scenario
  String FILTER_ES_HIDE_EXECUTIONS = "hide.executions.filter";
  String FILTER_ES_HIDE_PRE_AND_POST_CONDITIONS = "hide.pre.post.conditions.filter";
  String FILTER_ES_HIDE_PRE_AND_POST_CONDITIONS_ID = "hide.pre.post.conditions.filter";
  String FILTER_ES_SHOW_EXCHANGEITEMS = "show.exchange.items.filter";
  String FILTER_ES_SHOW_EXCHANGEITEMS_PARAMETERS = "show.exchange.items.parameters.filter";
  String FILTER_ES_SHOW_FUNCTIONAL_EXCHANGES = "show.functional.exchanges.filter";
  String FILTER_ES_SHOW_FUNCTIONAL_EXCHANGES_EXCHANGEITEMS = "show.functional.exchanges.exchange.items.filter";
  String FILTER_ES_SHOW_FUNCTIONAL_EXCHANGES_PARAMETERS = "show.functional.exchanges.parameters.filter";
  String FILTER_ES_SHOW_FUNCTIONAL_EXCHANGES_EXCHANGEITEMS_PARAMETERS = "show.functional.exchanges.exchange.items.parameters.filter";
  String FILTER_ES_SHOW_COMPONENT_EXCHANGES_EXCHANGEITEMS = "show.component.exchanges.exchange.items.filter";
  String FILTER_ES_SHOW_CEPARAM = "show.ce.param.filter";
  String FILTER_ES_SHOW_CEEIPARAM = "show.ce.ei.param.filter";
  String FILTER_ES_SHOW_EXCHANGE_CONTEXT = "show.exchange.context.filter";
  String FILTER_ES_SHOW_FE_EXCHANGE_CONTEXT = "show.fe.exchange.context.filter";
  String FILTER_ES_SHOW_CE_EXCHANGE_CONTEXT = "show.ce.exchange.context.filter";

  String FILTER_ES_SHOW_EXCHANGEITEMS_ID = "show.exchange.items.filter";
  String FILTER_ES_SHOW_EXCHANGEITEMS_PARAMETERS_ID = "show.exchange.items.parameters.filter";
  String FILTER_ES_SHOW_FUNCTIONAL_EXCHANGES_ID = "show.functional.exchanges.filter";
  String FILTER_ES_SHOW_FUNCTIONAL_EXCHANGES_EXCHANGEITEMS_ID = "show.functional.exchanges.exchange.items.filter";
  String FILTER_ES_SHOW_FUNCTIONAL_EXCHANGES_PARAMETERS_ID = "show.functional.exchanges.parameters.filter";
  String FILTER_ES_SHOW_FUNCTIONAL_EXCHANGES_EXCHANGEITEMS_PARAMETERS_ID = "show.functional.exchanges.exchange.items.parameters.filter";
  String FILTER_ES_SHOW_COMPONENT_EXCHANGES_EXCHANGEITEMS_ID = "show.component.exchanges.exchange.items.filter";
  String FILTER_ES_SHOW_CEPARAM_ID = "show.ce.param.filter";
  String FILTER_ES_SHOW_CEEIPARAM_ID = "show.ce.ei.param.filter";
  String FILTER_ES_SHOW_EXCHANGE_CONTEXT_ID = "show.exchange.context.filter";
  String FILTER_ES_SHOW_CE_EXCHANGE_CONTEXT_ID = "show.ce.exchange.context.filter";
  String FILTER_ES_SHOW_FE_EXCHANGE_CONTEXT_ID = "show.fe.exchange.context.filter";

  // FCD filters - Functional Chain Description
  String FILTER_FCD_SHOW_EXCHANGEITEMS = "show.exchange.items.filter";
  String FILTER_FCD_SHOW_EXCHANGEITEMS_PARAMETERS = "show.exchange.items.parameters.filter";
  String FILTER_FCD_SHOW_FUNCTIONAL_EXCHANGES_EXCHANGEITEMS = "show.functional.exchanges.exchange.items.filter";
  String FILTER_FCD_HIDE_FUNCTIONAL_CHAIN_INVOLVEMENT_LINKS = "hide.functional.chain.involvement.links.filter";
  String FILTER_FCD_HIDE_SEQUENCING_INFORMATION = "hide.sequencing.information.filter";
  String FILTER_FCD_HIDE_COMPUTED_SEQUENCING_INFORMATION = "hide.computed.sequencing.information.filter";
  String FILTER_FCD_HIDE_ASSOCIATION_LINKS = "hide.association.links.filter";
  String FILTER_FCD_MERGE_FE_SL = "merge.associated.functional.exchange.involvements.and.sequence.links.without.control.node.filter";

  // FS filters - Function Scenario
  String FILTER_FS_HIDE_EXECUTIONS = "hide.executions.filter";
  String FILTER_FS_HIDE_PRE_AND_POST_CONDITIONS = "hide.pre.post.conditions.filter";
  String FILTER_FS_HIDE_PRE_AND_POST_CONDITIONS_ID = "hide.pre.post.conditions.filter";
  String FILTER_FS_SHOW_EXCHANGEITEMS = "show.exchange.items.filter";
  String FILTER_FS_SHOW_EXCHANGEITEMS_PARAMETERS = "show.exchange.items.parameters.filter";
  String FILTER_FS_SHOW_FUNCTIONAL_EXCHANGES_EXCHANGEITEMS = "show.functional.exchanges.exchange.items.filter";
  String FILTER_FS_SHOW_FUNCTIONAL_EXCHANGES_PARAMETERS = "show.functional.exchanges.parameters.filter";
  String FILTER_FS_SHOW_FUNCTIONAL_EXCHANGES_EXCHANGEITEMS_PARAMETERS = "show.functional.exchanges.exchange.items.parameters.filter";
  String FILTER_FS_SHOW_EXCHANGE_CONTEXT = "show.exchange.context.filter";
  String FILTER_FS_SHOW_FE_EXCHANGE_CONTEXT = "show.fe.exchange.context.filter";

  String FILTER_FS_SHOW_EXCHANGEITEMS_ID = "show.exchange.items.filter";
  String FILTER_FS_SHOW_EXCHANGEITEMS_PARAMETERS_ID = "show.exchange.items.parameters.filter";
  String FILTER_FS_SHOW_FUNCTIONAL_EXCHANGES_EXCHANGEITEMS_ID = "show.functional.exchanges.exchange.items.filter";
  String FILTER_FS_SHOW_FUNCTIONAL_EXCHANGES_PARAMETERS_ID = "show.functional.exchanges.parameters.filter";
  String FILTER_FS_SHOW_FUNCTIONAL_EXCHANGES_EXCHANGEITEMS_PARAMETERS_ID = "show.functional.exchanges.exchange.items.parameters.filter";

  // IDB filters - Interfaces Diagram Blank
  String FILTER_IDB_HIDE_INTERFACE_CONTENTS = "hide.interface.contents.filter";
  String FILTER_IDB_HIDE_INTERFACES = "hide.interfaces.filter";
  String FILTER_IDB_HIDE_EXCHANGE_ITEMS_DETAILS_IN_INTERFACES = "hide.exchange.items.details.in.interfaces.filter";
  String FILTER_IDB_HIDE_EXCHANGE_ITEM_ELEMENTS = "hide.exchange.item.elements.filter";
  String FILTER_IDB_HIDE_EXCHANGE_ITEMS = "hide.exchange.items.filter";
  String FILTER_IDB_HIDE_COMPONENT_PORTS = "hide.component.ports.filter";
  String FILTER_IDB_HIDE_USE_LINKS = "hide.use.links.filter";
  String FILTER_IDB_HIDE_IMPLEMENTATION_LINKS = "hide.implmentation.links.filter";
  String FILTER_IDB_HIDE_PROVIDE_LINKS = "hide.provide.links.filter";
  String FILTER_IDB_HIDE_REQUIRE_LINKS = "hide.require.links.filter";
  String FILTER_IDB_HIDE_COMMUNICATION_LINKS = "hide.communication.links.filter";
  String FILTER_IDB_HIDE_GENERALIZATION_LINKS = "hide.generalization.links.filter";
  String FILTER_IDB_HIDE_PORT_DELEGATIONS = "hide.port.delegations.filter";
  String FILTER_IDB_HIDE_SIMPLIFIED_MODEL_BASED_INTERACTIONS = "hide.simplified.component.interactions.filter";
  String FILTER_IDB_HIDE_SIMPLIFIED_DIAGRAM_BASED_INTERACTIONS = "hide.simplified.diagram.based.interactions.filter";
  String FILTER_IDB_HIDE_TECHNICALS_INTERFACES = "hide.technical.interfaces.filter";
  String FILTER_IDB_HIDE_DELEGATED_COMMUNICATION_LINKS = "hide.delegated.communication.links.filter";
  String FILTER_IDB_HIDE_DELEGATED_USE_IMPLEMENTATION_LINKS = "hide.delegated.use.implementation.require.provide.links.filter";
  String FILTER_IDB_SHOW_MODIFIERS = "show.modifiers.filter";
  String FILTER_IDB_HIDE_PROPERTY_VALUES = "hide.property.values.filter";

  // filter id
  String FILTER_IDB_HIDE_IMPLEMENTATION_LINKS_ID = "hide.implmentation.links.filter";
  String FILTER_IDB_HIDE_SIMPLIFIED_MODEL_BASED_INTERACTIONS_ID = "hide.simplified.component.interactions.filter";

  // IS filters - Interface Scenario
  String FILTER_IS_HIDE_EXECUTIONS = "hide.executions.filter";
  String FILTER_IS_HIDE_CALL_ARGUMENTS = "hide.call.arguments.filter";
  String FILTER_IS_HIDE_PRE_AND_POST_CONDITIONS = "hide.pre.post.conditions.filter";
  String FILTER_IS_HIDE_PRE_AND_POST_CONDITIONS_ID = "hide.pre.post.conditions.filter";
  String FILTER_IS_SHOW_EXCHANGE_CONTEXT = "show.ei.exchange.context.filter";
  String FILTER_IS_SHOW_EXCHANGE_CONTEXT_ID = "show.ei.exchange.context.filter";

  // XAB Filters
  String FILTER_XAB_HIDE_SIMPLIFIED_DIAGRAM_BASED_COMPONENT_EXCHANGES = "hide.simplified.diagram.based.component.exchanges.filter";
  String FILTER_XAB_HIDE_SIMPLIFIED_GROUP_OF_COMPONENT_EXCHANGES_ID = "hide.simplified.group.of.component.exchanges.filter";
  String FILTER_XAB_HIDE_SIMPLIFIED_ORIENTED_GROUPED_COMPONENT_EXCHANGES = "hide.simplified.oriented.grouped.component.exchanges.filter";
  String FILTER_XAB_HIDE_SEQUENCING_INFORMATION = "hide.sequencing.information.filter";

  // LAB filters - Logical Architecture Blank
  String FILTER_LAB_COLLAPSE_COMPONENT_PORTS = "collapse.component.ports.filter";
  String FILTER_LAB_COLLAPSE_FUNCTION_PORTS = "collapse.function.ports.filter";
  String FILTER_LAB_HIDE_FUNCTION_PORTS_WITHOUT_EXCHANGES = "hide.function.ports.without.exchanges.filter";
  String FILTER_LAB_HIDE_COMPONENT_PORTS_WITHOUT_EXCHANGES = "hide.component.ports.without.exchanges.filter";
  String FILTER_LAB_HIDE_ALLOCATED_FUNCTIONAL_EXCHANGES = "hide.allocated.functional.exchanges.filter";
  String FILTER_LAB_HIDE_FUNCTIONS = "hide.functions.filter";
  String FILTER_LAB_HIDE_FUNCTIONAL_EXCHANGES = "hide.functional.exchanges.filter";
  String FILTER_LAB_HIDE_FUNCTIONAL_EXCHANGES_NAMES = "hide.functional.exchanges.names.filter";
  String FILTER_LAB_HIDE_COMPONENT_EXCHANGES = "hide.connections.filter";
  String FILTER_LAB_HIDE_COMPONENT_EXCHANGES_NAMES = "hide.component.exchanges.names.filter";
  String FILTER_LAB_HIDE_PORT_ALLOCATIONS = "hide.port.realizations.filter";
  String FILTER_LAB_HIDE_PORT_DELEGATIONS = "hide.port.delegations.filter";
  String FILTER_LAB_HIDE_ALLOCATED_FUNCTION_PORTS = "hide.realized.ports.filter";
  String FILTER_LAB_SHOW_EXCHANGE_ITEMS_ON_FUNCTIONAL_EXCHANGES = "show.exchange.items.filter";
  String FILTER_LAB_SHOW_EXCHANGEITEMS_PARAMETERS_ON_FUNCTIONAL_EXCHANGES = "show.exchange.items.parameters.filter";
  String FILTER_LAB_SHOW_FUNCTIONAL_EXCHANGES_EXCHANGEITEMS_ON_FUNCTIONAL_EXCHANGES = "show.functional.exchanges.exchange.items.filter";
  String FILTER_LAB_SHOW_FUNCTIONAL_EXCHANGES_PARAMETERS_ON_FUNCTIONAL_EXCHANGES = "show.functional.exchanges.parameters.filter";
  String FILTER_LAB_SHOW_FUNCTIONAL_EXCHANGES_EXCHANGEITEMS_PARAMETERS_ON_FUNCTIONAL_EXCHANGES = "show.functional.exchanges.exchange.items.parameters.filter";
  String FILTER_LAB_SHOW_EXCHANGE_ITEMS_ON_COMPONENT_EXCHANGES = "show.exchange.items.on.component.exchanges.filter";
  String FILTER_LAB_SHOW_EXCHANGE_ITEMS_ON_COMPONENT_EXCHANGE_WITHOUT_FUNCTIONAL_EXCHANGES = "show.exchange.items.on.component.exchange.without.functional.exchanges.filter";
  String FILTER_LAB_SHOW_ALLOCATED_FUNCTIONAL_EXCHANGES_ON_COMPONENT_EXCHANGES = "show.allocated.functional.exchanges.on.component.exchanges.filter";
  String FILTER_LAB_HIDE_CROSS_FUNCTIONAL_EXCHANGES_OF_REUSABLE_COMPONENTS = "hide.cross.functional.exchanges.of.reusable.components.filter";
  String FILTER_LAB_HIDE_SIMPLIFIED_DIAGRAM_BASED_COMPONENT_EXCHANGES = FILTER_XAB_HIDE_SIMPLIFIED_DIAGRAM_BASED_COMPONENT_EXCHANGES; // $NON-NLS-1$
  String FILTER_LAB_HIDE_SIMPLIFIED_GROUPED_COMPONENT_EXCHANGES = "hide.simplified.group.of.component.exchanges.filter";
  String FILTER_LAB_HIDE_SIMPLIFIED_GROUP_OF_COMPONENT_EXCHANGES_ID = FILTER_XAB_HIDE_SIMPLIFIED_GROUP_OF_COMPONENT_EXCHANGES_ID; // $NON-NLS-1$
  String FILTER_LAB_HIDE_SIMPLIFIED_ORIENTED_GROUPED_COMPONENT_EXCHANGES = FILTER_XAB_HIDE_SIMPLIFIED_ORIENTED_GROUPED_COMPONENT_EXCHANGES; // $NON-NLS-1$
  String FILTER_LAB_HIDE_PHYSICAL_LINKS_NAME = "hide.physical.links.names.filter";
  String FILTER_LAB_HIDE_PROPERTY_VALUES = "hide.property.values.filter";
  String FILTER_LAB_HIDE_SEQUENCING_INFORMATION = "hide.sequencing.information.filter";

  String FILTER_XAB_HIDE_COMPUTED_CE = "hide.computed.component.exchanges.filter";
  String FILTER_XAB_HIDE_COMPUTED_PL = "hide.computed.physical.links.filter";

  String FILTER_LAB_HIDE_COMPUTED_CE = FILTER_XAB_HIDE_COMPUTED_CE;
  String FILTER_LAB_HIDE_COMPUTED_PL = FILTER_XAB_HIDE_COMPUTED_PL;

  // filter id
  String FILTER_LAB_HIDE_COMPONENT_EXCHANGES_ID = "hide.connections.filter";
  String FILTER_LAB_HIDE_PORT_ALLOCATIONS_ID = "hide.port.realizations.filter";
  String FILTER_LAB_HIDE_ALLOCATED_FUNCTION_PORTS_ID = "hide.realized.ports.filter";
  String FILTER_LAB_SHOW_EXCHANGE_ITEMS_ON_FUNCTIONAL_EXCHANGES_ID = "show.exchange.items.filter";
  String FILTER_LAB_SHOW_EXCHANGEITEMS_PARAMETERS_ON_FUNCTIONAL_EXCHANGES_ID = "show.exchange.items.parameters.filter";
  String FILTER_LAB_SHOW_FUNCTIONAL_EXCHANGES_EXCHANGEITEMS_ON_FUNCTIONAL_EXCHANGES_ID = "show.functional.exchanges.exchange.items.filter";
  String FILTER_LAB_SHOW_FUNCTIONAL_EXCHANGES_PARAMETERS_ON_FUNCTIONAL_EXCHANGES_ID = "show.functional.exchanges.parameters.filter";
  String FILTER_LAB_SHOW_FUNCTIONAL_EXCHANGES_EXCHANGEITEMS_PARAMETERS_ON_FUNCTIONAL_EXCHANGES_ID = "show.functional.exchanges.exchange.items.parameters.filter";
  String FILTER_LAB_SHOW_EXCHANGE_ITEMS_ON_COMPONENT_EXCHANGE_WITHOUT_FUNCTIONAL_EXCHANGES_ID = "show.exchange.items.on.component.exchange.without.functional.exchanges.filter";

  // LCBD Filters
  String FILTER_LCBD_HIDE_ROOT_CONTAINER = "hide.root.container.filter";
  String FILTER_LCBD_HIDE_PROPERTY_VALUES = "hide.property.values.filter";

  // LCCII filters - Logical Component Contextual Component Internal
  // Interfaces
  String FILTER_LCCII_HIDE_INTERFACES = "hide.interfaces.filter";
  String FILTER_LCCII_HIDE_PORT_DELEGATIONS = "hide.port.delegations.filter";
  String FILTER_LCCII_HIDE_SUBLINKS_WITH_INTERFACES = "hide.sub-links.with.interfaces.filter";
  String FILTER_LCCII_HIDE_SUPERLINKS_WITH_INTERFACES = "hide.super-links.with.interfaces.filter";
  String FILTER_LCCII_HIDE_EXCHANGE_ITEMS = "hide.exchange.items.filter";
  String FILTER_LCCII_HIDE_EXCHANGEITEM_ALLOCATION = "hide.exchange.item.allocation.filter";
  String FILTER_LCCII_HIDE_COMPONENT_PORTS = "hide.component.ports.filter";
  String FILTER_LCCII_HIDE_USE_LINKS = "hide.use.links.filter";
  String FILTER_LCCII_HIDE_IMPLEMENTATION_LINKS = "hide.implmentation.links.filter";
  String FILTER_LCCII_HIDE_IMPLEMENTATION_LINKS_ID = "hide.implmentation.links.filter";
  String FILTER_LCCII_HIDE_PROVIDE_LINKS = "hide.provide.links.filter";
  String FILTER_LCCII_HIDE_REQUIRE_LINKS = "hide.require.links.filter";
  String FILTER_LCCII_HIDE_COMMUNICATION_LINKS = "hide.communication.links.filter";
  String FILTER_LCCII_HIDE_GENERALIZATION_LINKS = "hide.generalization.links.filter";
  String FILTER_LCCII_HIDE_SIMPLIFIED_COMPONENT_INTERACTIONS = "hide.simplified.component.interactions.filter";
  String FILTER_LCCII_HIDE_TECHNICALS_INTERFACES = "hide.technical.interfaces.filter";
  String FILTER_LCCII_HIDE_DELEGATED_COMMUNICATION_LINKS = "hide.delegated.communication.links.filter";
  String FILTER_LCCII_HIDE_DELEGATED_USE_IMPLEMENTATION_LINKS = "hide.delegated.use.implementation.require.provide.links.filter";
  String FILTER_LCCII_HIDE_PROPERTY_VALUES = "hide.property.values.filter";

  // LDFB filters - Logical Data Flow Blank
  String FILTER_LDFB_COLLAPSE_PORTS = "collapse.ports.filter";
  String FILTER_LDFB_HIDE_FUNCTIONAL_EXCHANGES = "hide.functional.exchanges.filter";
  String FILTER_LDFB_HIDE_FUNCTIONAL_EXCHANGES_NAMES = "hide.functional.exchanges.names.filter";
  String FILTER_LDFB_HIDE_FUNCTION_PORTS_WITHOUT_EXCHANGES = "hide.function.ports.without.exchanges.filter";
  String FILTER_LDFB_HIDE_EXCHANGE_CATEGORIES = "hide.exchange.categories.filter";
  String FILTER_LDFB_SHOW_EXCHANGEITEMS = "show.exchange.items.filter";
  String FILTER_LDFB_SHOW_EXCHANGEITEMS_PARAMETERS = "show.exchange.items.parameters.filter";
  String FILTER_LDFB_SHOW_FUNCTIONAL_EXCHANGES_EXCHANGEITEMS = "show.functional.exchanges.exchange.items.filter";
  String FILTER_LDFB_SHOW_FUNCTIONAL_EXCHANGES_PARAMETERS = "show.functional.exchanges.parameters.filter";
  String FILTER_LDFB_SHOW_FUNCTIONAL_EXCHANGES_EXCHANGEITEMS_PARAMETERS = "show.functional.exchanges.exchange.items.parameters.filter";
  String FILTER_LDFB_HIDE_PROPERTY_VALUES = "hide.property.values.filter";

  String FILTER_LDFB_SHOW_EXCHANGEITEMS_ID = "show.exchange.items.filter";
  String FILTER_LDFB_SHOW_EXCHANGEITEMS_PARAMETERS_ID = "show.exchange.items.parameters.filter";
  String FILTER_LDFB_SHOW_FUNCTIONAL_EXCHANGES_EXCHANGEITEMS_ID = "show.functional.exchanges.exchange.items.filter";
  String FILTER_LDFB_SHOW_FUNCTIONAL_EXCHANGES_PARAMETERS_ID = "show.functional.exchanges.parameters.filter";
  String FILTER_LDFB_SHOW_FUNCTIONAL_EXCHANGES_EXCHANGEITEMS_PARAMETERS_ID = "show.functional.exchanges.exchange.items.parameters.filter";

  // LFBD Filters
  String FILTER_LFBD_HIDE_CONTROL_NODES = "hide.control.nodes.filter";
  String FILTER_LFBD_HIDE_PROPERTY_VALUES = "hide.property.values.filter";

  // MB filters - Missions Blank
  String FILTER_MB_HIDE_CAPABILITIES = "hide.capabilities.filter";
  String FILTER_MB_HIDE_CAPABILITY_EXPLOITATION = "hide.capability.exploitations.filter";
  String FILTER_MB_HIDE_CAPABILITY_GENERALIZATIONS = "hide.capability.generalizations.filter";
  String FILTER_MB_HIDE_MISSIONS = "hide.missions.filter";
  String FILTER_MB_HIDE_ACTORS = "hide.actors.filter";
  String FILTER_MB_HIDE_ACTOR_INVOLVEMENTS = "hide.actor.involvements.filter";
  String FILTER_MB_HIDE_ACTOR_GENERALIZATIONS = "hide.actor.generalizations.filter";
  String FILTER_MB_HIDE_PROPERTY_VALUES = "hide.property.values.filter";

  // MCB filters - Mission Capabilities Blank
  String FILTER_MCB_HIDE_CAPABILITIES = "hide.capabilities.filter";
  String FILTER_MCB_HIDE_CAPABILITY_EXPLOITATIONS = "hide.capability.exploitations.filter";
  String FILTER_MCB_HIDE_CAPABILITY_EXTENDS = "hide.capability.extends.filter";
  String FILTER_MCB_HIDE_CAPABILITY_INCLUDES = "hide.capability.includes.filter";
  String FILTER_MCB_HIDE_CAPABILITY_GENERALIZATIONS = "hide.capability.generalizations.filter";
  String FILTER_MCB_HIDE_MISSIONS = "hide.missions.filter";
  String FILTER_MCB_HIDE_ACTORS = "hide.actors.filter";
  String FILTER_MCB_HIDE_ACTOR_MISSION_INVOLVEMENTS = "hide.actor.mission.involvements.filter";
  String FILTER_MCB_HIDE_ACTOR_CAPABILITY_INVOLVEMENTS = "hide.actor.capability.involvements.filter";
  String FILTER_MCB_HIDE_ACTOR_GENERALIZATIONS = "hide.actor.generalizations.filter";
  String FILTER_MCB_HIDE_PROPERTY_VALUES = "hide.property.values.filter";

  // M&S filters - Modes and States
  String FILTER_MS_HIDE_INTERNAL_STATES = "hide.internal.states.filter";
  String FILTER_MS_HIDEINTERNALSTATES_ID = "HideInternalStates";
  String FILTER_MSM_HIDECOMPUTEDTRANSITIONS_ID = "hide.computed.transitions.filter";

  // New Capability Realization Refinement
  String FILTER_NEWCAPABILITYREALIZATIONREFINEMENT_HIDE_ACTORS = "hide.actors.filter";

  // OAB filters - Operational Architecture Blank
  String FILTER_OAB_HIDE_ALLOCATED_INTERACTIONS = "hide.allocated.interactions.filter";
  String FILTER_OAB_HIDE_OPERATIONAL_ACTIVITIES = "hide.operational.activities.filter";
  String FILTER_OAB_HIDE_ROLES = "hide.roles.filter";
  String FILTER_OAB_HIDE_OPERATIONAL_ACTORS = "hide.operational.actors.filter";
  String FILTER_OAB_HIDE_INTERACTIONS = "hide.interactions.filter";
  String FILTER_OAB_HIDE_INTERACTIONS_NAMES = "hide.interactions.names.filter";
  String FILTER_OAB_HIDE_COMMUNICATION_MEANS = "hide.communication.means.filter";
  String FILTER_OAB_HIDE_COMMUNICATION_MEANS_NAMES = "hide.communication.means.names.filter";
  String FILTER_OAB_SHOW_EXCHANGE_ITEMS_ON_INTERACTIONS = "show.exchange.items.filter";
  String FILTER_OAB_SHOW_EXCHANGEITEMS_PARAMETERS_ON_INTERACTIONS = "show.exchange.items.parameters.filter";
  String FILTER_OAB_SHOW_FUNCTIONAL_EXCHANGES_EXCHANGEITEMS_ON_INTERACTIONS = "show.functional.exchanges.exchange.items.filter";
  String FILTER_OAB_SHOW_FUNCTIONAL_EXCHANGES_PARAMETERS_ON_INTERACTIONS = "show.functional.exchanges.parameters.filter";
  String FILTER_OAB_SHOW_FUNCTIONAL_EXCHANGES_EXCHANGEITEMS_PARAMETERS_ON_INTERACTIONS = "show.functional.exchanges.exchange.items.parameters.filter";
  String FILTER_OAB_SHOW_EXCHANGE_ITEM_ON_COMMUNICATION_MEANS = "show.exchange.items.on.component.exchanges.filter";
  String FILTER_OAB_SHOW_EXCHANGE_ITEM_ON_COMMUNICATION_MEANS_WITHOUT_INTERACTIONS = "show.exchange.items.on.component.exchange.without.functional.exchanges.filter";
  String FILTER_OAB_SHOW_ALLOCATED_INTERACTIONS_ON_COMMUNICATION_MEANS = "show.allocated.functional.exchanges.on.component.exchanges.filter";
  String FILTER_OAB_HIDE_CROSS_INTEREACTIONS_OF_ROLES = "hide.cross.functional.exchanges.of.reusable.components.filter";
  String FILTER_OAB_HIDE_PROPERTY_VALUES = "hide.property.values.filter";
  String FILTER_OAB_HIDE_SEQUENCING_INFORMATION = "hide.sequencing.information.filter";

  // filter id
  String FILTER_OAB_SHOW_EXCHANGE_ITEMS_ON_INTERACTIONS_ID = "show.exchange.items.filter";
  String FILTER_OAB_SHOW_EXCHANGEITEMS_PARAMETERS_ON_INTERACTIONS_ID = "show.exchange.items.parameters.filter";
  String FILTER_OAB_SHOW_FUNCTIONAL_EXCHANGES_EXCHANGEITEMS_ON_INTERACTIONS_ID = "show.functional.exchanges.exchange.items.filter";
  String FILTER_OAB_SHOW_FUNCTIONAL_EXCHANGES_PARAMETERS_ON_INTERACTIONS_ID = "show.functional.exchanges.parameters.filter";
  String FILTER_OAB_SHOW_FUNCTIONAL_EXCHANGES_EXCHANGEITEMS_PARAMETERS_ON_INTERACTIONS_ID = "show.functional.exchanges.exchange.items.parameters.filter";
  String FILTER_OAB_SHOW_EXCHANGE_ITEM_ON_COMMUNICATION_MEANS_ID = "show.exchange.items.on.component.exchanges.filter";
  String FILTER_OAB_SHOW_EXCHANGE_ITEM_ON_COMMUNICATION_MEANS_WITHOUT_INTERACTIONS_ID = "show.exchange.items.on.component.exchange.without.functional.exchanges.filter";
  String FILTER_OAB_SHOW_ALLOCATED_INTERACTIONS_ON_COMMUNICATION_MEANS_ID = "show.allocated.functional.exchanges.on.component.exchanges.filter";
  String FILTER_OAB_HIDE_CROSS_INTEREACTIONS_OF_ROLES_ID = "hide.cross.functional.exchanges.of.reusable.components.filter";
  String FILTER_OAB_HIDE_INTERACTIONS_NAMES_ID = "hide.interactions.names.filter";
  String FILTER_OAB_HIDE_COMMUNICATION_MEANS_NAMES_ID = "hide.communication.means.names.filter";

  // OAIB filters - Operational Activity Interaction Blank
  String FILTER_OAIB_HIDE_INTERACTIONS = "hide.interactions.filter";
  String FILTER_OAIB_HIDE_INTERACTIONS_NAMES = "hide.interactions.names.filter";
  String FILTER_OAIB_SHOW_EXCHANGEITEMS = "show.exchange.items.filter";
  String FILTER_OAIB_SHOW_EXCHANGEITEMS_PARAMETERS = "show.exchange.items.parameters.filter";
  String FILTER_OAIB_SHOW_FUNCTIONAL_EXCHANGES_EXCHANGEITEMS = "show.functional.exchanges.exchange.items.filter";
  String FILTER_OAIB_SHOW_FUNCTIONAL_EXCHANGES_PARAMETERS = "show.functional.exchanges.parameters.filter";
  String FILTER_OAIB_SHOW_FUNCTIONAL_EXCHANGES_EXCHANGEITEMS_PARAMETERS = "show.functional.exchanges.exchange.items.parameters.filter";
  // filter id
  String FILTER_OAIB_SHOW_EXCHANGEITEMS_ID = "show.exchange.items.filter";
  String FILTER_OAIB_SHOW_EXCHANGEITEMS_PARAMETERS_ID = "show.exchange.items.parameters.filter";
  String FILTER_OAIB_SHOW_FUNCTIONAL_EXCHANGES_EXCHANGEITEMS_ID = "show.functional.exchanges.exchange.items.filter";
  String FILTER_OAIB_SHOW_FUNCTIONAL_EXCHANGES_PARAMETERS_ID = "show.functional.exchanges.parameters.filter";
  String FILTER_OAIB_SHOW_FUNCTIONAL_EXCHANGES_EXCHANGEITEMS_PARAMETERS_ID = "show.functional.exchanges.exchange.items.parameters.filter";

  // OCB filters - Operational Capabilities Blank
  String FILTER_OCB_HIDE_COMMUNICATION_MEANS = "hide.communication.means.filter";
  String FILTER_OCB_HIDE_INVOLVEMENT_LINKS = "hide.involvement.links.filter";
  String FILTER_OCB_HIDE_OPERATIONAL_CAPABILITY_EXTENDS = "hide.operational.capability.extends.filter";
  String FILTER_OCB_HIDE_OPERATIONAL_CAPABILITY_INCLUDES = "hide.operational.capability.includes.filter";
  String FILTER_OCB_HIDE_OPERATIONAL_CAPABILITY_GENERALIZATIONS = "hide.operational.capability.generalizations.filter";
  String FILTER_OCB_HIDE_PROPERTY_VALUES = "hide.property.values.filter";

  // OAS filters - Operational Activity Scenario
  String FILTER_OAS_HIDE_EXECUTIONS = "hide.executions.filter";
  String FILTER_OAS_HIDE_PRE_AND_POST_CONDITIONS = "hide.pre.post.conditions.filter";
  String FILTER_OAS_HIDE_PRE_AND_POST_CONDITIONS_ID = "hide.pre.post.conditions.filter";
  String FILTER_OAS_SHOW_EXCHANGEITEMS = "show.exchange.items.filter";
  String FILTER_OAS_SHOW_EXCHANGEITEMS_PARAMETERS = "show.exchange.items.parameters.filter";
  String FILTER_OAS_SHOW_FUNCTIONAL_EXCHANGES_EXCHANGEITEMS = "show.functional.exchanges.exchange.items.filter";
  String FILTER_OAS_SHOW_FUNCTIONAL_EXCHANGES_PARAMETERS = "show.functional.exchanges.parameters.filter";
  String FILTER_OAS_SHOW_FUNCTIONAL_EXCHANGES_EXCHANGEITEMS_PARAMETERS = "show.functional.exchanges.exchange.items.parameters.filter";
  String FILTER_OAS_SHOW_CEEIPARAM = "show.ce.ei.param.filter";
  String FILTER_OAS_SHOW_I_EXCHANGE_CONTEXT = "show.fe.exchange.context.filter";
  String FILTER_OAS_SHOW_EXCHANGE_CONTEXT = "show.exchange.context.filter";

  String FILTER_OAS_SHOW_EXCHANGEITEMS_ID = "show.exchange.items.filter";
  String FILTER_OAS_SHOW_EXCHANGEITEMS_PARAMETERS_ID = "show.exchange.items.parameters.filter";
  String FILTER_OAS_SHOW_FUNCTIONAL_EXCHANGES_EXCHANGEITEMS_ID = "show.functional.exchanges.exchange.items.filter";
  String FILTER_OAS_SHOW_FUNCTIONAL_EXCHANGES_PARAMETERS_ID = "show.functional.exchanges.parameters.filter";
  String FILTER_OAS_SHOW_FUNCTIONAL_EXCHANGES_EXCHANGEITEMS_PARAMETERS_ID = "show.functional.exchanges.exchange.items.parameters.filter";

  // OEB filters - Operational Entity Blank
  String FILTER_OEB_HIDE_OPERATIONAL_ACTIVITIES = "hide.operational.activities.filter";
  String FILTER_OEB_HIDE_ROLES = "hide.roles.filter";
  String FILTER_OEB_HIDE_OPERATIONAL_ACTORS = "hide.operational.actors.filter";
  String FILTER_OEB_HIDE_INTERACTIONS = "Hide Interactions";
  String FILTER_OEB_HIDE_COMMUNICATION_MEANS = "Hide Communication Means";
  String FILTER_OEB_SHOW_EXCHANGE_ITEMS_ON_INTERACTIONS = "show.exchange.items.filter";
  String FILTER_OEB_SHOW_EXCHANGE_ITEM_ON_COMMUNICATION_MEANS = "show.exchange.items.on.component.exchanges.filter";
  String FILTER_OEB_SHOW_EXCHANGE_ITEM_ON_COMMUNICATION_MEANS_WITHOUT_INTERACTIONS = "show.exchange.items.on.component.exchange.without.functional.exchanges.filter";
  String FILTER_OEB_SHOW_ALLOCATED_INTERACTIONS_ON_COMMUNICATION_MEANS = "show.allocated.functional.exchanges.on.component.exchanges.filter";
  String FILTER_OEB_HIDE_CROSS_INTEREACTIONS_OF_ROLES = "hide.cross.functional.exchanges.of.reusable.components.filter";

  // OES filters - Operational Entity Scenario
  String FILTER_OES_HIDE_EXECUTIONS = "hide.executions.filter";
  String FILTER_OES_HIDE_PRE_AND_POST_CONDITIONS = "hide.pre.post.conditions.filter";
  String FILTER_OES_HIDE_PRE_AND_POST_CONDITIONS_ID = "hide.pre.post.conditions.filter";
  String FILTER_OES_SHOW_EXCHANGEITEMS = "show.exchange.items.filter";
  String FILTER_OES_SHOW_EXCHANGEITEMS_PARAMETERS = "show.exchange.items.parameters.filter";
  String FILTER_OES_SHOW_FUNCTIONAL_EXCHANGES = "show.functional.exchanges.filter";
  String FILTER_OES_SHOW_FUNCTIONAL_EXCHANGES_EXCHANGEITEMS = "show.functional.exchanges.exchange.items.filter";
  String FILTER_OES_SHOW_FUNCTIONAL_EXCHANGES_PARAMETERS = "show.functional.exchanges.parameters.filter";
  String FILTER_OES_SHOW_FUNCTIONAL_EXCHANGES_EXCHANGEITEMS_PARAMETERS = "show.functional.exchanges.exchange.items.parameters.filter";
  String FILTER_OES_SHOW_COMPONENT_EXCHANGES_EXCHANGEITEMS = "show.component.exchanges.exchange.items.filter";
  String FILTER_OES_SHOW_CEPARAM = "show.ce.param.filter";
  String FILTER_OES_SHOW_CEEIPARAM = "show.ce.ei.param.filter";
  String FILTER_OES_SHOW_I_EXCHANGE_CONTEXT = "show.fe.exchange.context.filter";
  String FILTER_OES_SHOW_EXCHANGE_CONTEXT = "show.exchange.context.filter";
  String FILTER_OES_SHOW_CM_EXCHANGE_CONTEXT = "show.ce.exchange.context.filter";

  // filter id
  String FILTER_OES_SHOW_EXCHANGEITEMS_ID = "show.exchange.items.filter";
  String FILTER_OES_SHOW_EXCHANGEITEMS_PARAMETERS_ID = "show.exchange.items.parameters.filter";
  String FILTER_OES_SHOW_FUNCTIONAL_EXCHANGES_ID = "show.functional.exchanges.filter";
  String FILTER_OES_SHOW_FUNCTIONAL_EXCHANGES_EXCHANGEITEMS_ID = "show.functional.exchanges.exchange.items.filter";
  String FILTER_OES_SHOW_FUNCTIONAL_EXCHANGES_PARAMETERS_ID = "show.functional.exchanges.parameters.filter";
  String FILTER_OES_SHOW_FUNCTIONAL_EXCHANGES_EXCHANGEITEMS_PARAMETERS_ID = "show.functional.exchanges.exchange.items.parameters.filter";
  String FILTER_OES_SHOW_COMPONENT_EXCHANGES_EXCHANGEITEMS_ID = "show.component.exchanges.exchange.items.filter";
  String FILTER_OES_SHOW_CEPARAM_ID = "show.ce.param.filter";
  String FILTER_OES_SHOW_CEEIPARAM_ID = "show.ce.ei.param.filter";

  // ORB filters - Operational Role Blank
  String FILTER_ORB_HIDE_ALLOCATED_INTERACTIONS = "hide.allocated.interactions.filter";
  String FILTER_ORB_HIDE_INTERACTIONS = "hide.interactions.filter";
  String FILTER_ORB_HIDE_PROPERTY_VALUES = "hide.property.values.filter";

  // PAB filters - Physical Architecture Blank
  String FILTER_PAB_COLLAPSE_COMPONENT_PHYSICAL_PORTS = "collapse.component.physical.ports.filter";
  String FILTER_PAB_COLLAPSE_COMPONENT_PORTS = "collapse.component.ports.filter";
  String FILTER_PAB_COLLAPSE_FUNCTION_PORTS = "collapse.function.ports.filter";
  String FILTER_PAB_HIDE_FUNCTION_PORTS_WITHOUT_EXCHANGES = "hide.function.ports.without.exchanges.filter";
  String FILTER_PAB_HIDE_COMPONENT_PORTS_WITHOUT_EXCHANGES = "hide.component.ports.without.exchanges.filter";
  String FILTER_PAB_HIDE_PHYSICAL_PORTS_WITHOUT_LINKS = "hide.physical.ports.without.links.filter";
  String FILTER_PAB_HIDE_ALLOCATED_FUNCTION_ON_PARENT_CONTAINERS = "hide.allocated.function.on.parent.containers.filter";
  String FILTER_PAB_HIDE_ALLOCATED_FUNCTIONAL_EXCHANGES = "hide.allocated.functional.exchanges.filter";
  String FILTER_PAB_HIDE_FUNCTIONS = "hide.functions.filter";
  String FILTER_PAB_HIDE_FUNCTIONAL_EXCHANGES = "hide.functional.exchanges.filter";
  String FILTER_PAB_HIDE_FUNCTIONAL_EXCHANGES_NAMES = "hide.functional.exchanges.names.filter";
  String FILTER_PAB_HIDE_COMPONENT_EXCHANGES = "hide.connections.filter";
  String FILTER_PAB_HIDE_COMPONENT_EXCHANGES_NAMES = "hide.component.exchanges.names.filter";
  String FILTER_PAB_HIDE_PHYSICAL_LINKS = "hide.physical.links.filter";
  String FILTER_PAB_HIDE_PHYSICAL_LINKS_NAMES = "hide.physical.links.names.filter";
  String FILTER_PAB_HIDE_PORT_ALLOCATIONS = "hide.port.realizations.filter";
  String FILTER_PAB_HIDE_PORT_DELEGATIONS = "hide.port.delegations.filter";
  String FILTER_PAB_HIDE_COMPONENT_PORT_ALLOCATIONS = "hide.component.port.allocations.filter";
  String FILTER_PAB_HIDE_ALLOCATED_FUNCTION_PORTS = "hide.realized.ports.filter";
  String FILTER_PAB_HIDE_NODE_PCS = "hide.node.pcs.filter";
  String FILTER_PAB_HIDE_BEHAVIOR_PCS = "hide.behavior.pcs.filter";
  String FILTER_PAB_HIDE_PHYSICAL_ACTORS = "hide.physical.actors.filter";
  String FILTER_PAB_HIDE_DEPLOYED_PCS = "hide.deployed.pcs.filter";
  String FILTER_PAB_SHOW_EXCHANGE_ITEMS_ON_FUNCTIONAL_EXCHANGES = "show.exchange.items.filter";
  String FILTER_PAB_SHOW_EXCHANGEITEMS_PARAMETERS_ON_FUNCTIONAL_EXCHANGES = "show.exchange.items.parameters.filter";
  String FILTER_PAB_SHOW_FUNCTIONAL_EXCHANGES_EXCHANGEITEMS_ON_FUNCTIONAL_EXCHANGES = "show.functional.exchanges.exchange.items.filter";
  String FILTER_PAB_SHOW_FUNCTIONAL_EXCHANGES_PARAMETERS_ON_FUNCTIONAL_EXCHANGES = "show.functional.exchanges.parameters.filter";
  String FILTER_PAB_SHOW_FUNCTIONAL_EXCHANGES_EXCHANGEITEMS_PARAMETERS_ON_FUNCTIONAL_EXCHANGES = "show.functional.exchanges.exchange.items.parameters.filter";
  String FILTER_PAB_SHOW_EXCHANGE_ITEMS_ON_COMPONENT_EXCHANGES = "show.exchange.items.on.component.exchanges.filter";
  String FILTER_PAB_SHOW_EXCHANGE_ITEMS_ON_COMPONENT_EXCHANGE_WITHOUT_FUNCTIONAL_EXCHANGES = "show.exchange.items.on.component.exchange.without.functional.exchanges.filter";
  String FILTER_PAB_SHOW_ALLOCATED_FUNCTIONAL_EXCHANGES_ON_COMPONENT_EXCHANGES = "show.allocated.functional.exchanges.on.component.exchanges.filter";
  String FILTER_PAB_HIDE_CROSS_FUNCTIONAL_EXCHANGES_OF_REUSABLE_COMPONENTS = "hide.cross.functional.exchanges.of.reusable.components.filter";
  String FILTER_PAB_HIDE_SIMPLIFIED_DIAGRAM_BASED_COMPONENT_EXCHANGES = FILTER_XAB_HIDE_SIMPLIFIED_DIAGRAM_BASED_COMPONENT_EXCHANGES; // $NON-NLS-1$
  String FILTER_PAB_HIDE_SIMPLIFIED_GROUPED_COMPONENT_EXCHANGES = "hide.simplified.group.of.component.exchanges.filter";
  String FILTER_PAB_HIDE_SIMPLIFIED_GROUP_OF_COMPONENT_EXCHANGES_ID = FILTER_XAB_HIDE_SIMPLIFIED_GROUP_OF_COMPONENT_EXCHANGES_ID; // $NON-NLS-1$
  String FILTER_PAB_HIDE_PORT_REALIZATIONS_ID = "hide.port.realizations.filter";
  String FILTER_PAB_HIDE_SIMPLIFIED_ORIENTED_GROUPED_COMPONENT_EXCHANGES = FILTER_XAB_HIDE_SIMPLIFIED_ORIENTED_GROUPED_COMPONENT_EXCHANGES; // $NON-NLS-1$
  String FILTER_PAB_HIDE_COMPUTED_CE = FILTER_XAB_HIDE_COMPUTED_CE;
  String FILTER_PAB_HIDE_COMPUTED_PL = FILTER_XAB_HIDE_COMPUTED_PL;

  String FILTER_PAB_HIDE_SEQUENCING_INFORMATION = "hide.sequencing.information.filter";
  String FILTER_PAB_HIDE_PROPERTY_VALUES = "hide.property.values.filter";

  // filter id
  String FILTER_PAB_HIDE_COMPONENT_EXCHANGES_ID = "hide.connections.filter";
  String FILTER_PAB_HIDE_PORT_ALLOCATIONS_ID = "hide.port.realizations.filter";
  String FILTER_PAB_HIDE_ALLOCATED_FUNCTION_PORTS_ID = "hide.realized.ports.filter";
  String FILTER_PAB_SHOW_EXCHANGE_ITEMS_ON_FUNCTIONAL_EXCHANGES_ID = "show.exchange.items.filter";
  String FILTER_PAB_SHOW_EXCHANGEITEMS_PARAMETERS_ON_FUNCTIONAL_EXCHANGES_ID = "show.exchange.items.parameters.filter";
  String FILTER_PAB_SHOW_FUNCTIONAL_EXCHANGES_EXCHANGEITEMS_ON_FUNCTIONAL_EXCHANGES_ID = "show.functional.exchanges.exchange.items.filter";
  String FILTER_PAB_SHOW_FUNCTIONAL_EXCHANGES_PARAMETERS_ON_FUNCTIONAL_EXCHANGES_ID = "show.functional.exchanges.parameters.filter";
  String FILTER_PAB_SHOW_FUNCTIONAL_EXCHANGES_EXCHANGEITEMS_PARAMETERS_ON_FUNCTIONAL_EXCHANGES_ID = "show.functional.exchanges.exchange.items.parameters.filter";
  String FILTER_PAB_SHOW_EXCHANGE_ITEMS_ON_COMPONENT_EXCHANGE_WITHOUT_FUNCTIONAL_EXCHANGES_ID = "show.exchange.items.on.component.exchange.without.functional.exchanges.filter";

  // PDFB filters - Physical Data Flow Blank
  String FILTER_PDFB_COLLAPSE_PORTS = "collapse.ports.filter";
  String FILTER_PDFB_HIDE_FUNCTIONAL_EXCHANGES = "hide.functional.exchanges.filter";
  String FILTER_PDFB_HIDE_FUNCTIONAL_EXCHANGES_NAMES = "hide.functional.exchanges.names.filter";
  String FILTER_PDFB_HIDE_FUNCTION_PORTS_WITHOUT_EXCHANGES = "hide.function.ports.without.exchanges.filter";
  String FILTER_PDFB_HIDE_EXCHANGE_CATEGORIES = "hide.exchange.categories.filter";
  String FILTER_PDFB_SHOW_EXCHANGEITEMS = "show.exchange.items.filter";
  String FILTER_PDFB_SHOW_EXCHANGEITEMS_PARAMETERS = "show.exchange.items.parameters.filter";
  String FILTER_PDFB_SHOW_FUNCTIONAL_EXCHANGES_EXCHANGEITEMS = "show.functional.exchanges.exchange.items.filter";
  String FILTER_PDFB_SHOW_FUNCTIONAL_EXCHANGES_PARAMETERS = "show.functional.exchanges.parameters.filter";
  String FILTER_PDFB_SHOW_FUNCTIONAL_EXCHANGES_EXCHANGEITEMS_PARAMETERS = "show.functional.exchanges.exchange.items.parameters.filter";

  String FILTER_PDFB_SHOW_EXCHANGEITEMS_ID = "show.exchange.items.filter";
  String FILTER_PDFB_SHOW_EXCHANGEITEMS_PARAMETERS_ID = "show.exchange.items.parameters.filter";
  String FILTER_PDFB_SHOW_FUNCTIONAL_EXCHANGES_EXCHANGEITEMS_ID = "show.functional.exchanges.exchange.items.filter";
  String FILTER_PDFB_SHOW_FUNCTIONAL_EXCHANGES_PARAMETERS_ID = "show.functional.exchanges.parameters.filter";
  String FILTER_PDFB_SHOW_FUNCTIONAL_EXCHANGES_EXCHANGEITEMS_PARAMETERS_ID = "show.functional.exchanges.exchange.items.parameters.filter";
  String FILTER_PDFB_HIDE_PROPERTY_VALUES = "hide.property.values.filter";

  // PFBD Filters
  String FILTER_PFBD_HIDE_CONTROL_NODES = "hide.control.nodes.filter";
  String FILTER_PFBD_HIDE_PROPERTY_VALUES = "hide.property.values.filter";

  // SAB filters - System Architecture Blank
  String FILTER_SAB_COLLAPSE_COMPONENT_PORTS = "collapse.component.ports.filter";
  String FILTER_SAB_COLLAPSE_FUNCTION_PORTS = "collapse.function.ports.filter";
  String FILTER_SAB_HIDE_FUNCTION_PORTS_WITHOUT_EXCHANGES = "hide.function.ports.without.exchanges.filter";
  String FILTER_SAB_HIDE_COMPONENT_PORTS_WITHOUT_EXCHANGES = "hide.component.ports.without.exchanges.filter";
  String FILTER_SAB_HIDE_ALLOCATED_FUNCTIONAL_EXCHANGES = "hide.allocated.functional.exchanges.filter";
  String FILTER_SAB_HIDE_FUNCTIONS = "hide.functions.filter";
  String FILTER_SAB_HIDE_FUNCTIONAL_EXCHANGES = "hide.functional.exchanges.filter";
  String FILTER_SAB_HIDE_FUNCTIONAL_EXCHANGES_NAMES = "hide.functional.exchanges.names.filter";
  String FILTER_SAB_HIDE_COMPONENT_EXCHANGES = "hide.connections.filter";
  String FILTER_SAB_HIDE_COMPONENT_EXCHANGES_NAMES = "hide.component.exchanges.names.filter";
  String FILTER_SAB_HIDE_PHYSICAL_LINKS_NAMES = "hide.physical.links.names.filter";
  String FILTER_SAB_HIDE_PORT_ALLOCATIONS = "hide.port.realizations.filter";
  String FILTER_SAB_HIDE_ALLOCATED_FUNCTION_PORTS = "hide.realized.ports.filter";
  String FILTER_SAB_SHOW_EXCHANGE_ITEMS_ON_FUNCTIONAL_EXCHANGES = "show.exchange.items.filter";
  String FILTER_SAB_SHOW_EXCHANGEITEMS_PARAMETERS_ON_FUNCTIONAL_EXCHANGES = "show.exchange.items.parameters.filter";
  String FILTER_SAB_SHOW_FUNCTIONAL_EXCHANGES_EXCHANGEITEMS_ON_FUNCTIONAL_EXCHANGES = "show.functional.exchanges.exchange.items.filter";
  String FILTER_SAB_SHOW_FUNCTIONAL_EXCHANGES_PARAMETERS_ON_FUNCTIONAL_EXCHANGES = "show.functional.exchanges.parameters.filter";
  String FILTER_SAB_SHOW_FUNCTIONAL_EXCHANGES_EXCHANGEITEMS_PARAMETERS_ON_FUNCTIONAL_EXCHANGES = "show.functional.exchanges.exchange.items.parameters.filter";
  String FILTER_SAB_SHOW_EXCHANGE_ITEMS_ON_COMPONENT_EXCHANGES = "show.exchange.items.on.component.exchanges.filter";
  String FILTER_SAB_SHOW_EXCHANGE_ITEMS_ON_COMPONENT_EXCHANGE_WITHOUT_FUNCTIONAL_EXCHANGES = "show.exchange.items.on.component.exchange.without.functional.exchanges.filter";
  String FILTER_SAB_SHOW_ALLOCATED_FUNCTIONAL_EXCHANGES_ON_COMPONENT_EXCHANGES = "show.allocated.functional.exchanges.on.component.exchanges.filter";
  String FILTER_SAB_HIDE_CROSS_FUNCTIONAL_EXCHANGES_OF_REUSABLE_COMPONENTS = "hide.cross.functional.exchanges.of.reusable.components.filter";
  String FILTER_SAB_HIDE_SIMPLIFIED_GROUPED_COMPONENT_EXCHANGES = "hide.simplified.group.of.component.exchanges.filter";
  String FILTER_SAB_HIDE_SIMPLIFIED_GROUPED_COMPONENT_EXCHANGES_ID = FILTER_XAB_HIDE_SIMPLIFIED_GROUP_OF_COMPONENT_EXCHANGES_ID; // $NON-NLS-1$
  String FILTER_SAB_HIDE_SIMPLIFIED_ORIENTED_GROUPED_COMPONENT_EXCHANGES = FILTER_XAB_HIDE_SIMPLIFIED_ORIENTED_GROUPED_COMPONENT_EXCHANGES; // $NON-NLS-1$
  String FILTER_SAB_HIDE_PROPERTY_VALUES = "hide.property.values.filter";
  String FILTER_SAB_HIDE_SEQUENCING_INFORMATION = "hide.sequencing.information.filter";

  // filter id
  String FILTER_SAB_HIDE_COMPONENT_EXCHANGES_ID = "hide.connections.filter";
  String FILTER_SAB_HIDE_PORT_ALLOCATIONS_ID = "hide.port.realizations.filter";
  String FILTER_SAB_HIDE_ALLOCATED_FUNCTION_PORTS_ID = "hide.realized.ports.filter";
  String FILTER_SAB_SHOW_EXCHANGE_ITEMS_ON_FUNCTIONAL_EXCHANGES_ID = "show.exchange.items.filter";
  String FILTER_SAB_SHOW_EXCHANGEITEMS_PARAMETERS_ON_FUNCTIONAL_EXCHANGES_ID = "show.exchange.items.parameters.filter";
  String FILTER_SAB_SHOW_FUNCTIONAL_EXCHANGES_EXCHANGEITEMS_ON_FUNCTIONAL_EXCHANGES_ID = "show.functional.exchanges.exchange.items.filter";
  String FILTER_SAB_SHOW_FUNCTIONAL_EXCHANGES_PARAMETERS_ON_FUNCTIONAL_EXCHANGES_ID = "show.functional.exchanges.parameters.filter";
  String FILTER_SAB_SHOW_FUNCTIONAL_EXCHANGES_EXCHANGEITEMS_PARAMETERS_ON_FUNCTIONAL_EXCHANGES_ID = "show.functional.exchanges.exchange.items.parameters.filter";
  String FILTER_SAB_SHOW_EXCHANGE_ITEMS_ON_COMPONENT_EXCHANGE_WITHOUT_FUNCTIONAL_EXCHANGES_ID = "show.exchange.items.on.component.exchange.without.functional.exchanges.filter";

  // SDFB filters - System Data Flow Bank
  String FILTER_SDFB_COLLAPSE_PORTS = "collapse.ports.filter";
  String FILTER_SDFB_HIDE_FUNCTIONAL_EXCHANGES = "hide.functional.exchanges.filter";
  String FILTER_SDFB_HIDE_FUNCTIONAL_EXCHANGES_NAMES = "hide.functional.exchanges.names.filter";
  String FILTER_SDFB_HIDE_FUNCTION_PORTS_WITHOUT_EXCHANGES = "hide.function.ports.without.exchanges.filter";
  String FILTER_SDFB_HIDE_EXCHANGE_CATEGORIES = "hide.exchange.categories.filter";
  String FILTER_SDFB_SHOW_EXCHANGEITEMS = "show.exchange.items.filter";
  String FILTER_SDFB_SHOW_EXCHANGEITEMS_PARAMETERS = "show.exchange.items.parameters.filter";
  String FILTER_SDFB_SHOW_FUNCTIONAL_EXCHANGES_EXCHANGEITEMS = "show.functional.exchanges.exchange.items.filter";
  String FILTER_SDFB_SHOW_FUNCTIONAL_EXCHANGES_PARAMETERS = "show.functional.exchanges.parameters.filter";
  String FILTER_SDFB_SHOW_FUNCTIONAL_EXCHANGES_EXCHANGEITEMS_PARAMETERS = "show.functional.exchanges.exchange.items.parameters.filter";
  String FILTER_SDFB_HIDE_PROPERTY_VALUES = "hide.property.values.filter";

  // filter id
  String FILTER_SDFB_SHOW_EXCHANGEITEMS_ID = "show.exchange.items.filter";
  String FILTER_SDFB_SHOW_EXCHANGEITEMS_PARAMETERS_ID = "show.exchange.items.parameters.filter";
  String FILTER_SDFB_SHOW_FUNCTIONAL_EXCHANGES_EXCHANGEITEMS_ID = "show.functional.exchanges.exchange.items.filter";
  String FILTER_SDFB_SHOW_FUNCTIONAL_EXCHANGES_PARAMETERS_ID = "show.functional.exchanges.parameters.filter";
  String FILTER_SDFB_SHOW_FUNCTIONAL_EXCHANGES_EXCHANGEITEMS_PARAMETERS_ID = "show.functional.exchanges.exchange.items.parameters.filter";

  // SFBD filters - System Function Breakdown
  String FILTER_SFBD_HIDE_CONTROL_NODES = "hide.control.nodes.filter";
  String FILTER_SFBD_HIDE_PROPERTY_VALUES = "hide.property.values.filter";

  // Exchange Context filters
  String FILTER_SHOW_EXCHANGE_CONTEXT_ID = "show.exchange.context.filter";
  String FILTER_SHOW_CE_EXCHANGE_CONTEXT_ID = "show.ce.exchange.context.filter";
  String FILTER_SHOW_FE_EXCHANGE_CONTEXT_ID = "show.fe.exchange.context.filter";
  String FILTER_SHOW_EI_EXCHANGE_CONTEXT_ID = "show.ei.exchange.context.filter";

  String FILTER_FUNCTIONAL_CHAINS_INTERNAL_LINKS_ID = "hide.functional.chains.internal.links.filter";
  String FILTER_PHYSICAL_PATHS_INTERNAL_LINKS_ID = "hide.physical.paths.internal.links.filter";

  String FILTER_MERGE_ASSOCIATED_FE_AND_SL = "merge.associated.functional.exchange.involvements.and.sequence.links.without.control.node.filter";
}
