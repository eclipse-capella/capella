/*******************************************************************************
 * Copyright (c) 2006, 2015 THALES GLOBAL SERVICES.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *  
 * Contributors:
 *    Thales - initial API and implementation
 *******************************************************************************/
package org.polarsys.capella.core.sirius.analysis.constants;

/**
 * Centralizes constants about filters.
 */
public interface IFilterNameConstants {

  //
  // Filters
  //
  // CC filters - Contextual Capability
  String FILTER_CC_HIDE_CAPABILITIES = "Hide Capabilities"; //$NON-NLS-1$
  String FILTER_CC_HIDE_CAPABILITY_EXPLOITATION = "Hide Capability Exploitations"; //$NON-NLS-1$
  String FILTER_CC_HIDE_CAPABILITY_EXTENDS = "Hide Capability Extends"; //$NON-NLS-1$
  String FILTER_CC_HIDE_CAPABILITY_INCLUDES = "Hide Capability Includes"; //$NON-NLS-1$
  String FILTER_CC_HIDE_MISSIONS = "Hide Missions"; //$NON-NLS-1$
  String FILTER_CC_HIDE_ACTORS = "Hide Actors"; //$NON-NLS-1$
  String FILTER_CC_HIDE_ACTOR_INVOLVEMENTS = "Hide Actor Involvements"; //$NON-NLS-1$
  String FILTER_CC_HIDE_ACTOR_GENERALIZATIONS = "Hide Actor Generalizations"; //$NON-NLS-1$

  // CDB filters - Class Diagram Blank
  String FILTER_CDB_HIDE_PROPERTIES = "Hide Properties"; //$NON-NLS-1$
  String FILTER_CDB_HIDE_OPERATIONS = "Hide Operations"; //$NON-NLS-1$
  String FILTER_CDB_HIDE_ASSOCIATIONS = "Hide Associations"; //$NON-NLS-1$
  String FILTER_CDB_HIDE_GENERALIZATIONS = "Hide Generalizations"; //$NON-NLS-1$
  String FILTER_CDB_HIDE_EXCHANGE_ITEMS_DETAILS_IN_INTERFACES = "Hide Exchange Items details in Interfaces"; //$NON-NLS-1$
  String FILTER_CDB_HIDE_ASSOCIATION_LABELS = "Hide Association Labels"; //$NON-NLS-1$
  String FILTER_CDB_HIDE_ROLE_LABELS = "Hide Role Labels"; //$NON-NLS-1$
  String FILTER_CDB_SHOW_FULL_PATH = "Show Full Path"; //$NON-NLS-1$
  String FILTER_CDB_HIDE_DERIVED_PROPERTIES = "Hide Derived Properties"; //$NON-NLS-1$
  String FILTER_CDB_HIDE_TECHNICALS_INTERFACES = "Hide Technical Interfaces"; //$NON-NLS-1$
  String FILTER_CDB_HIDE_ROLE_NAMES = "Hide Role Names"; //$NON-NLS-1$
  String FILTER_CDB_SHOW_MODIFIERS = "Show Modifiers"; //$NON-NLS-1$

  // CDI filters - Contextual Component Detailed Interfaces
  String FILTER_CDI_HIDE_INTERFACE_CONTENTS = "Hide Interface contents"; //$NON-NLS-1$
  String FILTER_CDI_HIDE_INTERFACES = "Hide Interfaces"; //$NON-NLS-1$
  String FILTER_CDI_HIDE_EXCHANGE_ITEMS_DETAILS_IN_INTERFACES = "Hide Exchange Items details in Interfaces"; //$NON-NLS-1$
  String FILTER_CDI_HIDE_EXCHANGE_ITEM_ELEMENTS = "Hide Exchange Item Elements"; //$NON-NLS-1$
  String FILTER_CDI_HIDE_EXCHANGE_ITEMS = "Hide Exchange Items"; //$NON-NLS-1$
  String FILTER_CDI_HIDE_COMPONENT_PORTS = "Hide Component Ports"; //$NON-NLS-1$
  String FILTER_CDI_HIDE_USE_LINKS = "Hide Use links"; //$NON-NLS-1$
  String FILTER_CDI_HIDE_IMPLEMENTATION_LINKS = "Hide Implementation links"; //$NON-NLS-1$
  String FILTER_CDI_HIDE_PROVIDE_LINKS = "Hide Provide links"; //$NON-NLS-1$
  String FILTER_CDI_HIDE_REQUIRE_LINKS = "Hide Require links"; //$NON-NLS-1$
  String FILTER_CDI_HIDE_COMMUNICATION_LINKS = "Hide Communication Links"; //$NON-NLS-1$
  String FILTER_CDI_HIDE_GENERALIZATION_LINKS = "Hide Generalization Links"; //$NON-NLS-1$LS-1$
  String FILTER_CDI_HIDE_TECHNICALS_INTERFACES = "Hide Technical Interfaces"; //$NON-NLS-1$
  String FILTER_CDI_SHOW_MODIFIERS = "Show Modifiers"; //$NON-NLS-1$

  // filter id
  String FILTER_CDI_HIDE_IMPLEMENTATION_LINKS_ID = "Hide Implmentation links"; //$NON-NLS-1$

  // CEI filters - Contextual Component External Interface
  String FILTER_CEI_HIDE_INTERFACES = "Hide Interfaces"; //$NON-NLS-1$
  String FILTER_CEI_HIDE_EXCHANGEITEM_ALLOCATION = "Hide ExchangeItem Allocation"; //$NON-NLS-1$
  String FILTER_CEI_HIDE_EXCHANGE_ITEMS = "Hide Exchange Items"; //$NON-NLS-1$
  String FILTER_CEI_HIDE_COMPONENT_PORTS = "Hide Component Ports"; //$NON-NLS-1$
  String FILTER_CEI_HIDE_USE_LINKS = "Hide Use links"; //$NON-NLS-1$
  String FILTER_CEI_HIDE_IMPLEMENTATION_LINKS = "Hide Implementation links"; //$NON-NLS-1$
  String FILTER_CEI_HIDE_PROVIDE_LINKS = "Hide Provide links"; //$NON-NLS-1$
  String FILTER_CEI_HIDE_REQUIRE_LINKS = "Hide Require links"; //$NON-NLS-1$
  String FILTER_CEI_HIDE_COMMUNICATION_LINKS = "Hide Communication Links"; //$NON-NLS-1$
  String FILTER_CEI_HIDE_GENERALIZATION_LINKS = "Hide Generalization Links"; //$NON-NLS-1$
  String FILTER_CEI_HIDE_SIMPLIFIED_COMPONENT_INTERACTIONS = "Hide Simplified Component Interactions"; //$NON-NLS-1$
  String FILTER_CEI_HIDE_TECHNICALS_INTERFACES = "Hide Technical Interfaces"; //$NON-NLS-1$

  // filter id
  String FILTER_CEI_HIDE_IMPLEMENTATION_LINKS_ID = "Hide Implmentation links"; //$NON-NLS-1$

  // COAI filters - Contextual Operational Activity Interaction
  String FILTER_COAI_HIDE_INTERACTIONS = "Hide Interactions"; //$NON-NLS-1$
  String FILTER_COAI_BACKUP_SHOW_EXCHANGEITEMS = "Show ExchangeItems"; //$NON-NLS-1$
  String FILTER_COAI_SHOW_EXCHANGEITEMS = "Show [EI1, EI2]"; //$NON-NLS-1$
  String FILTER_COAI_SHOW_EXCHANGEITEMS_PARAMETERS = "Show EI1(param1:type1, param2:type2), EI2(param3:type3)"; //$NON-NLS-1$
  String FILTER_COAI_SHOW_FUNCTIONAL_EXCHANGES = "Show FE"; //$NON-NLS-1$
  String FILTER_COAI_SHOW_FUNCTIONAL_EXCHANGES_EXCHANGEITEMS = "Show FE [EI1, EI2]"; //$NON-NLS-1$
  String FILTER_COAI_SHOW_FUNCTIONAL_EXCHANGES_EXCHANGEITEMS_PARAMETERS = "Show FE [EI1(param1:type1, param2:type2), EI2(param3:type3)]"; //$NON-NLS-1$

  // COC filters - Contextual Operational Capability
  String FILTER_COC_HIDE_ENTITIES = "Hide Entities"; //$NON-NLS-1$
  String FILTER_COC_HIDE_INVOLVEMENT_LINKS = "Hide Involvement Links"; //$NON-NLS-1$
  String FILTER_COC_HIDE_OPERATIONAL_CAPABILITY_EXTENDS = "Hide Operational Capability Extends"; //$NON-NLS-1$
  String FILTER_COC_HIDE_OPERATIONAL_CAPABILITY_INCLUDES = "Hide Operational Capability Includes"; //$NON-NLS-1$
  String FILTER_COC_HIDE_OPERATIONAL_CAPABILITY_GENERALIZATIONS = "Hide Operational Capability Generalizations"; //$NON-NLS-1$

  // CM filters - Contextual Mission
  String FILTER_CM_HIDE_CAPABILITIES = "Hide Capabilities"; //$NON-NLS-1$
  String FILTER_CM_HIDE_CAPABILITY_EXPLOITATIONS = "Hide Capability Exploitations"; //$NON-NLS-1$
  String FILTER_CM_HIDE_ACTORS = "Hide Actors"; //$NON-NLS-1$
  String FILTER_CM_HIDE_ACTOR_INVOLVEMENTS = "Hide Actor Involvements"; //$NON-NLS-1$
  String FILTER_CM_HIDE_ACTOR_GENERALIZATIONS = "Hide Actor Generalizations"; //$NON-NLS-1$

  // CRB filters - Capability Realization Blank
  String FILTER_CRB_HIDE_CAPABILITY_REALIZATIONS = "Hide Capability Realizations"; //$NON-NLS-1$
  String FILTER_CRB_HIDE_INVOLVEMENTS = "Hide Involvements"; //$NON-NLS-1$
  String FILTER_CRB_HIDE_CAPABILITY_EXTENDS = "Hide Capability Extends"; //$NON-NLS-1$
  String FILTER_CRB_HIDE_CAPABILTY_INCLUDES = "Hide Capability Includes"; //$NON-NLS-1$
  String FILTER_CRB_HIDE_CAPABILITY_REALIZATION_GENERALIZATIONS = "Hide Capability Realization Generalizations"; //$NON-NLS-1$
  String FILTER_CRB_HIDE_COMPONENTS = "Hide Components"; //$NON-NLS-1$
  String FILTER_CRB_HIDE_ACTORS = "Hide Actors"; //$NON-NLS-1$
  String FILTER_CRB_HIDE_ACTOR_GENERALIZATIONS = "Hide Actor Generalizations"; //$NON-NLS-1$

  // EAB filters - EPBS Architecture Blank
  String FILTER_EAB_HIDE_PHYSICAL_ARTIFACTS = "Hide Physical Artifacts"; //$NON-NLS-1$

  // ES filters - Exchange Scenario
  String FILTER_ES_HIDE_EXECUTIONS = "Hide executions"; //$NON-NLS-1$
  String FILTER_ES_HIDE_PRE_AND_POST_CONDITIONS = "Hide pre and post conditions"; //$NON-NLS-1$
  String FILTER_ES_HIDE_PRE_AND_POST_CONDITIONS_ID = "Hide PrePost conditions"; //$NON-NLS-1$
  String FILTER_ES_SHOW_EXCHANGEITEMS = "Show [EI1, EI2]"; //$NON-NLS-1$
  String FILTER_ES_SHOW_EXCHANGEITEMS_PARAMETERS = "Show EI1(param1:type1, param2:type2), EI2(param3:type3)"; //$NON-NLS-1$
  String FILTER_ES_SHOW_FUNCTIONAL_EXCHANGES = "Show FE"; //$NON-NLS-1$
  String FILTER_ES_SHOW_FUNCTIONAL_EXCHANGES_EXCHANGEITEMS = "Show FE [EI1, EI2]"; //$NON-NLS-1$
  String FILTER_ES_SHOW_FUNCTIONAL_EXCHANGES_PARAMETERS = "Show FE (type1, type2, type3)"; //$NON-NLS-1$
  String FILTER_ES_SHOW_FUNCTIONAL_EXCHANGES_EXCHANGEITEMS_PARAMETERS = "Show FE [EI1(param1:type1, param2:type2), EI2(param3:type3)]"; //$NON-NLS-1$
  String FILTER_ES_SHOW_COMPONENT_EXCHANGES_EXCHANGEITEMS = "Show CE [EI1, EI2]"; //$NON-NLS-1$
  String FILTER_ES_SHOW_CEPARAM = "Show CE (type1, type2)"; //$NON-NLS-1$
  String FILTER_ES_SHOW_CEEIPARAM = "Show CE [EI (param1:type1, param2:type2)]"; //$NON-NLS-1$
  String FILTER_ES_SHOW_EXCHANGE_CONTEXT = "Show {Exchange Context}"; //$NON-NLS-1$
  String FILTER_ES_SHOW_FE_EXCHANGE_CONTEXT = "Show FE {Exchange Context}"; //$NON-NLS-1$
  String FILTER_ES_SHOW_CE_EXCHANGE_CONTEXT = "Show CE {Exchange Context}"; //$NON-NLS-1$

  String FILTER_ES_SHOW_EXCHANGEITEMS_ID = "Show ExchangeItems"; //$NON-NLS-1$
  String FILTER_ES_SHOW_EXCHANGEITEMS_PARAMETERS_ID = "Show ExchangeItems parameters"; //$NON-NLS-1$
  String FILTER_ES_SHOW_FUNCTIONAL_EXCHANGES_ID = "Show Functional Exchanges"; //$NON-NLS-1$
  String FILTER_ES_SHOW_FUNCTIONAL_EXCHANGES_EXCHANGEITEMS_ID = "Show Functional Exchanges [ExchangeItems]"; //$NON-NLS-1$
  String FILTER_ES_SHOW_FUNCTIONAL_EXCHANGES_PARAMETERS_ID = "Show Functional Exchanges (Parameters)"; //$NON-NLS-1$
  String FILTER_ES_SHOW_FUNCTIONAL_EXCHANGES_EXCHANGEITEMS_PARAMETERS_ID = "Show Functional Exchanges [ExchangeItems(Parameters)]"; //$NON-NLS-1$
  String FILTER_ES_SHOW_COMPONENT_EXCHANGES_EXCHANGEITEMS_ID = "Show Component Exchanges [ExchangeItems]"; //$NON-NLS-1$
  String FILTER_ES_SHOW_CEPARAM_ID = "CEParam"; //$NON-NLS-1$
  String FILTER_ES_SHOW_CEEIPARAM_ID = "CEEIParam"; //$NON-NLS-1$
  String FILTER_ES_SHOW_EXCHANGE_CONTEXT_ID = "ShowExchangeContext"; //$NON-NLS-1$
  String FILTER_ES_SHOW_CE_EXCHANGE_CONTEXT_ID = "ShowCEExchangeContext"; //$NON-NLS-1$
  String FILTER_ES_SHOW_FE_EXCHANGE_CONTEXT_ID = "ShowFEExchangeContext"; //$NON-NLS-1$

  // FCD filters - Functional Chain Description
  String FILTER_FCD_SHOW_EXCHANGEITEMS = "Show ExchangeItems"; //$NON-NLS-1$
  String FILTER_FCD_SHOW_EXCHANGEITEMS_PARAMETERS = "Show ExchangeItems parameters"; //$NON-NLS-1$
  String FILTER_FCD_SHOW_FUNCTIONAL_EXCHANGES_EXCHANGEITEMS = "Show Functional Exchanges [ExchangeItems]"; //$NON-NLS-1$

  // FS filters - Function Scenario
  String FILTER_FS_HIDE_EXECUTIONS = "Hide executions"; //$NON-NLS-1$
  String FILTER_FS_HIDE_PRE_AND_POST_CONDITIONS = "Hide pre and post conditions"; //$NON-NLS-1$
  String FILTER_FS_HIDE_PRE_AND_POST_CONDITIONS_ID = "Hide PrePost conditions"; //$NON-NLS-1$
  String FILTER_FS_SHOW_EXCHANGEITEMS = "Show [EI1, EI2]"; //$NON-NLS-1$
  String FILTER_FS_SHOW_EXCHANGEITEMS_PARAMETERS = "Show EI1(param1:type1, param2:type2), EI2(param3:type3)"; //$NON-NLS-1$
  String FILTER_FS_SHOW_FUNCTIONAL_EXCHANGES_EXCHANGEITEMS = "Show FE [EI1, EI2]"; //$NON-NLS-1$
  String FILTER_FS_SHOW_FUNCTIONAL_EXCHANGES_PARAMETERS = "Show FE (type1, type2, type3)"; //$NON-NLS-1$
  String FILTER_FS_SHOW_FUNCTIONAL_EXCHANGES_EXCHANGEITEMS_PARAMETERS = "Show FE [EI1(param1:type1, param2:type2), EI2(param3:type3)]"; //$NON-NLS-1$
  String FILTER_FS_SHOW_EXCHANGE_CONTEXT = "Show {Exchange Context}"; //$NON-NLS-1$
  String FILTER_FS_SHOW_FE_EXCHANGE_CONTEXT = "Show FE {Exchange Context}"; //$NON-NLS-1$

  String FILTER_FS_SHOW_EXCHANGEITEMS_ID = "Show ExchangeItems"; //$NON-NLS-1$
  String FILTER_FS_SHOW_EXCHANGEITEMS_PARAMETERS_ID = "Show ExchangeItems parameters"; //$NON-NLS-1$
  String FILTER_FS_SHOW_FUNCTIONAL_EXCHANGES_EXCHANGEITEMS_ID = "Show Functional Exchanges [ExchangeItems]"; //$NON-NLS-1$
  String FILTER_FS_SHOW_FUNCTIONAL_EXCHANGES_PARAMETERS_ID = "Show Functional Exchanges (Parameters)"; //$NON-NLS-1$
  String FILTER_FS_SHOW_FUNCTIONAL_EXCHANGES_EXCHANGEITEMS_PARAMETERS_ID = "Show Functional Exchanges [ExchangeItems(Parameters)]"; //$NON-NLS-1$

  // IDB filters - Interfaces Diagram Blank
  String FILTER_IDB_HIDE_INTERFACE_CONTENTS = "Hide Interface contents"; //$NON-NLS-1$
  String FILTER_IDB_HIDE_INTERFACES = "Hide Interfaces"; //$NON-NLS-1$
  String FILTER_IDB_HIDE_EXCHANGE_ITEMS_DETAILS_IN_INTERFACES = "Hide Exchange Items details in Interfaces"; //$NON-NLS-1$
  String FILTER_IDB_HIDE_EXCHANGE_ITEM_ELEMENTS = "Hide Exchange Item Elements"; //$NON-NLS-1$
  String FILTER_IDB_HIDE_EXCHANGE_ITEMS = "Hide Exchange Items"; //$NON-NLS-1$
  String FILTER_IDB_HIDE_COMPONENT_PORTS = "Hide Component Ports"; //$NON-NLS-1$
  String FILTER_IDB_HIDE_USE_LINKS = "Hide Use links"; //$NON-NLS-1$
  String FILTER_IDB_HIDE_IMPLEMENTATION_LINKS = "Hide Implementation links"; //$NON-NLS-1$
  String FILTER_IDB_HIDE_PROVIDE_LINKS = "Hide Provide links"; //$NON-NLS-1$
  String FILTER_IDB_HIDE_REQUIRE_LINKS = "Hide Require links"; //$NON-NLS-1$
  String FILTER_IDB_HIDE_COMMUNICATION_LINKS = "Hide Communication Links"; //$NON-NLS-1$
  String FILTER_IDB_HIDE_GENERALIZATION_LINKS = "Hide Generalization Links"; //$NON-NLS-1$
  String FILTER_IDB_HIDE_PORT_DELEGATIONS = "Hide Port Delegations"; //$NON-NLS-1$
  String FILTER_IDB_HIDE_SIMPLIFIED_MODEL_BASED_INTERACTIONS = "Hide Simplified Model Based Interactions"; //$NON-NLS-1$
  String FILTER_IDB_HIDE_SIMPLIFIED_DIAGRAM_BASED_INTERACTIONS = "Hide Simplified Diagram Based Interactions"; //$NON-NLS-1$
  String FILTER_IDB_HIDE_TECHNICALS_INTERFACES = "Hide Technical Interfaces"; //$NON-NLS-1$
  String FILTER_IDB_HIDE_DELEGATED_COMMUNICATION_LINKS = "Hide Delegated Communication Links"; //$NON-NLS-1$
  String FILTER_IDB_HIDE_DELEGATED_USE_IMPLEMENTATION_LINKS = "Hide Delegated Use/Implementation/Require/Provide Links"; //$NON-NLS-1$
  String FILTER_IDB_SHOW_MODIFIERS = "Show Modifiers"; //$NON-NLS-1$

  // filter id
  String FILTER_IDB_HIDE_IMPLEMENTATION_LINKS_ID = "Hide Implmentation links"; //$NON-NLS-1$
  String FILTER_IDB_HIDE_SIMPLIFIED_MODEL_BASED_INTERACTIONS_ID = "Hide Simplified Component Interactions"; //$NON-NLS-1$

  // IS filters - Interface Scenario
  String FILTER_IS_HIDE_EXECUTIONS = "Hide executions"; //$NON-NLS-1$
  String FILTER_IS_HIDE_CALL_ARGUMENTS = "Hide Call Arguments"; //$NON-NLS-1$
  String FILTER_IS_HIDE_PRE_AND_POST_CONDITIONS = "Hide pre and post conditions"; //$NON-NLS-1$
  String FILTER_IS_HIDE_PRE_AND_POST_CONDITIONS_ID = "Hide PrePost conditions"; //$NON-NLS-1$
  String FILTER_IS_SHOW_EXCHANGE_CONTEXT = "Show EI {Exchange Context}"; //$NON-NLS-1$
  String FILTER_IS_SHOW_EXCHANGE_CONTEXT_ID = "ShowEIExchangeContext"; //$NON-NLS-1$

  // LAB filters - Logical Architecture Blank
  String FILTER_LAB_COLLAPSE_COMPONENT_PORTS = "Collapse Component Ports"; //$NON-NLS-1$
  String FILTER_LAB_COLLAPSE_FUNCTION_PORTS = "Collapse Function Ports"; //$NON-NLS-1$
  String FILTER_LAB_HIDE_FUNCTION_PORTS_WITHOUT_EXCHANGES = "Hide Function Ports without Exchanges"; //$NON-NLS-1$
  String FILTER_LAB_HIDE_COMPONENT_PORTS_WITHOUT_EXCHANGES = "Hide Component Ports without Exchanges"; //$NON-NLS-1$
  String FILTER_LAB_HIDE_ALLOCATED_FUNCTIONAL_EXCHANGES = "Hide Allocated Functional Exchanges"; //$NON-NLS-1$
  String FILTER_LAB_HIDE_FUNCTIONS = "Hide Functions"; //$NON-NLS-1$
  String FILTER_LAB_HIDE_FUNCTIONAL_EXCHANGES = "Hide Functional Exchanges"; //$NON-NLS-1$
  String FILTER_LAB_HIDE_FUNCTIONAL_EXCHANGES_NAMES = "Hide Functional Exchanges names"; //$NON-NLS-1$
  String FILTER_LAB_HIDE_COMPONENT_EXCHANGES = "Hide Component Exchanges"; //$NON-NLS-1$
  String FILTER_LAB_HIDE_COMPONENT_EXCHANGES_NAMES = "Hide Component Exchanges names"; //$NON-NLS-1$
  String FILTER_LAB_HIDE_PORT_ALLOCATIONS = "Hide Port Allocations"; //$NON-NLS-1$
  String FILTER_LAB_HIDE_PORT_DELEGATIONS = "Hide Port Delegations"; //$NON-NLS-1$
  String FILTER_LAB_HIDE_ALLOCATED_FUNCTION_PORTS = "Hide Allocated Function Ports"; //$NON-NLS-1$
  String FILTER_LAB_SHOW_EXCHANGE_ITEMS_ON_FUNCTIONAL_EXCHANGES = "Show Exchange Items on Functional Exchanges"; //$NON-NLS-1$
  String FILTER_LAB_SHOW_EXCHANGE_ITEMS_ON_COMPONENT_EXCHANGES = "Show Exchange Items on Component Exchanges"; //$NON-NLS-1$
  String FILTER_LAB_SHOW_EXCHANGE_ITEMS_ON_COMPONENT_EXCHANGE_WITHOUT_FUNCTIONAL_EXCHANGES =
      "Show Exchange Items on Component Exchange (without Functional Exchanges)"; //$NON-NLS-1$
  String FILTER_LAB_SHOW_ALLOCATED_FUNCTIONAL_EXCHANGES_ON_COMPONENT_EXCHANGES =
      "Show Allocated Functional Exchanges on Component Exchanges"; //$NON-NLS-1$
  String FILTER_LAB_HIDE_CROSS_FUNCTIONAL_EXCHANGES_OF_REUSABLE_COMPONENTS = "Hide cross Functional Exchanges of reusable Components"; //$NON-NLS-1$
  String FILTER_LAB_HIDE_SIMPLIFIED_DIAGRAM_BASED_COMPONENT_EXCHANGES = "Hide Simplified Diagram Based Component Exchanges"; //$NON-NLS-1$
  String FILTER_LAB_HIDE_SIMPLIFIED_GROUPED_COMPONENT_EXCHANGES = "Hide Simplified Grouped Component Exchanges"; //$NON-NLS-1$
  String FILTER_LAB_HIDE_SIMPLIFIED_GROUP_OF_COMPONENT_EXCHANGES_ID = "Hide Simplified Group of Component Exchanges"; //$NON-NLS-1$
  String FILTER_LAB_HIDE_SIMPLIFIED_ORIENTED_GROUPED_COMPONENT_EXCHANGES = "Hide Simplified Oriented Grouped Component Exchanges"; //$NON-NLS-1$
  String FILTER_LAB_HIDE_PHYSICAL_LINKS_NAME = "Hide Physical Links names"; //$NON-NLS-1$

  
  // filter id
  String FILTER_LAB_HIDE_COMPONENT_EXCHANGES_ID = "Hide Connections"; //$NON-NLS-1$
  String FILTER_LAB_HIDE_PORT_ALLOCATIONS_ID = "Hide Port Realizations"; //$NON-NLS-1$
  String FILTER_LAB_HIDE_ALLOCATED_FUNCTION_PORTS_ID = "Hide Realized  Ports"; //$NON-NLS-1$
  String FILTER_LAB_SHOW_EXCHANGE_ITEMS_ON_FUNCTIONAL_EXCHANGES_ID = "Show ExchangeItems"; //$NON-NLS-1$
  String FILTER_LAB_SHOW_EXCHANGE_ITEMS_ON_COMPONENT_EXCHANGE_WITHOUT_FUNCTIONAL_EXCHANGES_ID =
      "Show Exchange Items on Component Exchange without Functional Exchanges"; //$NON-NLS-1$

  // LCBD Filters
  String FILTER_LCBD_HIDE_CONTAINMENT_LINKS = "Hide containment links";//$NON-NLS-1$
  String FILTER_LCBD_HIDE_ROOT_CONTAINER = "Hide root container";//$NON-NLS-1$

  // LCCII filters - Logical Component Contextual Component Internal
  // Interfaces
  String FILTER_LCCII_HIDE_INTERFACES = "Hide Interfaces"; //$NON-NLS-1$
  String FILTER_LCCII_HIDE_PORT_DELEGATIONS = "Hide Port Delegations"; //$NON-NLS-1$
  String FILTER_LCCII_HIDE_SUBLINKS_WITH_INTERFACES = "Hide sub-links with interfaces"; //$NON-NLS-1$
  String FILTER_LCCII_HIDE_SUPERLINKS_WITH_INTERFACES = "Hide super-links with interfaces"; //$NON-NLS-1$
  String FILTER_LCCII_HIDE_EXCHANGE_ITEMS = "Hide Exchange Items"; //$NON-NLS-1$
  String FILTER_LCCII_HIDE_EXCHANGEITEM_ALLOCATION = "Hide ExchangeItem Allocation"; //$NON-NLS-1$
  String FILTER_LCCII_HIDE_COMPONENT_PORTS = "Hide Component Ports"; //$NON-NLS-1$
  String FILTER_LCCII_HIDE_USE_LINKS = "Hide Use links"; //$NON-NLS-1$
  String FILTER_LCCII_HIDE_IMPLEMENTATION_LINKS = "Hide Implementation links"; //$NON-NLS-1$
  String FILTER_LCCII_HIDE_PROVIDE_LINKS = "Hide Provide links"; //$NON-NLS-1$
  String FILTER_LCCII_HIDE_REQUIRE_LINKS = "Hide Require links"; //$NON-NLS-1$
  String FILTER_LCCII_HIDE_COMMUNICATION_LINKS = "Hide Communication Links"; //$NON-NLS-1$
  String FILTER_LCCII_HIDE_GENERALIZATION_LINKS = "Hide Generalization Links"; //$NON-NLS-1$
  String FILTER_LCCII_HIDE_SIMPLIFIED_COMPONENT_INTERACTIONS = "Hide Simplified Component Interactions"; //$NON-NLS-1$
  String FILTER_LCCII_HIDE_TECHNICALS_INTERFACES = "Hide Technical Interfaces"; //$NON-NLS-1$
  String FILTER_LCCII_HIDE_DELEGATED_COMMUNICATION_LINKS = "Hide Delegated Communication Links"; //$NON-NLS-1$
  String FILTER_LCCII_HIDE_DELEGATED_USE_IMPLEMENTATION_LINKS = "Hide Delegated Use/Implementation/Require/Provide Links"; //$NON-NLS-1$

  // LDFB filters - Logical Data Flow Blank
  String FILTER_LDFB_COLLAPSE_PORTS = "Collapse Ports"; //$NON-NLS-1$
  String FILTER_LDFB_HIDE_FUNCTIONAL_EXCHANGES = "Hide Functional Exchanges"; //$NON-NLS-1$
  String FILTER_LDFB_HIDE_FUNCTIONAL_EXCHANGES_NAMES = "Hide Functional Exchanges names"; //$NON-NLS-1$
  String FILTER_LDFB_HIDE_FUNCTION_PORTS_WITHOUT_EXCHANGES = "Hide Function Ports without Exchanges"; //$NON-NLS-1$
  String FILTER_LDFB_HIDE_EXCHANGE_CATEGORIES = "Hide Exchange Categories"; //$NON-NLS-1$
  String FILTER_LDFB_SHOW_EXCHANGEITEMS = "Show [EI1, EI2]"; //$NON-NLS-1$
  String FILTER_LDFB_SHOW_EXCHANGEITEMS_PARAMETERS = "Show EI1(param1:type1, param2:type2), EI2(param3:type3)"; //$NON-NLS-1$
  String FILTER_LDFB_SHOW_FUNCTIONAL_EXCHANGES_EXCHANGEITEMS = "Show FE [EI1, EI2]"; //$NON-NLS-1$
  String FILTER_LDFB_SHOW_FUNCTIONAL_EXCHANGES_PARAMETERS = "Show FE (type1, type2, type3)";//$NON-NLS-1$
  String FILTER_LDFB_SHOW_FUNCTIONAL_EXCHANGES_EXCHANGEITEMS_PARAMETERS = "Show FE [EI1(param1:type1, param2:type2), EI2(param3:type3)]"; //$NON-NLS-1$

  String FILTER_LDFB_SHOW_EXCHANGEITEMS_ID = "Show ExchangeItems"; //$NON-NLS-1$
  String FILTER_LDFB_SHOW_EXCHANGEITEMS_PARAMETERS_ID = "Show ExchangeItems parameters"; //$NON-NLS-1$
  String FILTER_LDFB_SHOW_FUNCTIONAL_EXCHANGES_EXCHANGEITEMS_ID = "Show Functional Exchanges [ExchangeItems]"; //$NON-NLS-1$
  String FILTER_LDFB_SHOW_FUNCTIONAL_EXCHANGES_PARAMETERS_ID = "Show Functional Exchanges (Parameters)";//$NON-NLS-1$
  String FILTER_LDFB_SHOW_FUNCTIONAL_EXCHANGES_EXCHANGEITEMS_PARAMETERS_ID = "Show Functional Exchanges [ExchangeItems(Parameters)]"; //$NON-NLS-1$

  // LFBD Filters
  String FILTER_LFBD_HIDE_CONTROL_NODES = "Hide Control Nodes";//$NON-NLS-1$

  // MB filters - Missions Blank
  String FILTER_MB_HIDE_CAPABILITIES = "Hide Capabilities"; //$NON-NLS-1$
  String FILTER_MB_HIDE_CAPABILITY_EXPLOITATION = "Hide Capability Exploitations"; //$NON-NLS-1$
  String FILTER_MB_HIDE_MISSIONS = "Hide Missions"; //$NON-NLS-1$
  String FILTER_MB_HIDE_ACTORS = "Hide Actors"; //$NON-NLS-1$
  String FILTER_MB_HIDE_ACTOR_INVOLVEMENTS = "Hide Actor Involvements"; //$NON-NLS-1$
  String FILTER_MB_HIDE_ACTOR_GENERALIZATIONS = "Hide Actor Generalizations"; //$NON-NLS-1$

  // MCB filters - Mission Capabilities Blank
  String FILTER_MCB_HIDE_CAPABILITIES = "Hide Capabilities"; //$NON-NLS-1$
  String FILTER_MCB_HIDE_CAPABILITY_EXPLOITATIONS = "Hide Capability Exploitations"; //$NON-NLS-1$
  String FILTER_MCB_HIDE_CAPABILITY_EXTENDS = "Hide Capability Extends"; //$NON-NLS-1$
  String FILTER_MCB_HIDE_CAPABILITY_INCLUDES = "Hide Capability Includes"; //$NON-NLS-1$
  String FILTER_MCB_HIDE_MISSIONS = "Hide Missions"; //$NON-NLS-1$
  String FILTER_MCB_HIDE_ACTORS = "Hide Actors"; //$NON-NLS-1$
  String FILTER_MCB_HIDE_ACTOR_MISSION_INVOLVEMENTS = "Hide Actor Mission Involvements"; //$NON-NLS-1$
  String FILTER_MCB_HIDE_ACTOR_CAPABILITY_INVOLVEMENTS = "Hide Actor Capability Involvements"; //$NON-NLS-1$
  String FILTER_MCB_HIDE_ACTOR_GENERALIZATIONS = "Hide Actor Generalizations"; //$NON-NLS-1$

  // M&S filters - Modes and States
  String FILTER_MS_HIDE_INTERNAL_STATES = "Hide Internal States"; //$NON-NLS-1$
  String FILTER_MS_HIDEINTERNALSTATES_ID = "HideInternalStates"; //$NON-NLS-1$

  // New Capability Realization Refinement
  String FILTER_NEWCAPABILITYREALIZATIONREFINEMENT_HIDE_ACTORS = "Hide Actors"; //$NON-NLS-1$

  // OAB filters - Operational Architecture Blank
  String FILTER_OAB_HIDE_ALLOCATED_INTERACTIONS = "Hide Allocated Interactions"; //$NON-NLS-1$
  String FILTER_OAB_HIDE_OPERATIONAL_ACTIVITIES = "Hide Operational Activities"; //$NON-NLS-1$
  String FILTER_OAB_HIDE_ROLES = "Hide Roles"; //$NON-NLS-1$
  String FILTER_OAB_HIDE_OPERATIONAL_ACTORS = "Hide Operational Actors"; //$NON-NLS-1$
  String FILTER_OAB_HIDE_INTERACTIONS = "Hide Interactions"; //$NON-NLS-1$
  String FILTER_OAB_HIDE_INTERACTIONS_NAMES = "Hide Interactions names"; //$NON-NLS-1$
  String FILTER_OAB_HIDE_COMMUNICATION_MEANS = "Hide Communication Means"; //$NON-NLS-1$
  String FILTER_OAB_HIDE_COMMUNICATION_MEANS_NAMES = "Hide Communication Means names"; //$NON-NLS-1$
  String FILTER_OAB_SHOW_EXCHANGE_ITEMS_ON_INTERACTIONS = "Show Exchange Items on Interactions"; //$NON-NLS-1$
  String FILTER_OAB_SHOW_EXCHANGE_ITEM_ON_COMMUNICATION_MEANS = "Show Exchange Items on Communication Means"; //$NON-NLS-1$
  String FILTER_OAB_SHOW_EXCHANGE_ITEM_ON_COMMUNICATION_MEANS_WITHOUT_INTERACTIONS =
      "Show Exchange Items on Communication Means (without Interactions)"; //$NON-NLS-1$
  String FILTER_OAB_SHOW_ALLOCATED_INTERACTIONS_ON_COMMUNICATION_MEANS = "Show Allocated Interactions on Communication Means"; //$NON-NLS-1$
  String FILTER_OAB_HIDE_CROSS_INTEREACTIONS_OF_ROLES = "Hide cross Interactions of Roles"; //$NON-NLS-1$

  // filter id
  String FILTER_OAB_SHOW_EXCHANGE_ITEMS_ON_INTERACTIONS_ID = "Show ExchangeItems"; //$NON-NLS-1$
  String FILTER_OAB_SHOW_EXCHANGE_ITEM_ON_COMMUNICATION_MEANS_ID = "Show Exchange Items on Component Exchanges"; //$NON-NLS-1$
  String FILTER_OAB_SHOW_EXCHANGE_ITEM_ON_COMMUNICATION_MEANS_WITHOUT_INTERACTIONS_ID =
      "Show Exchange Items on Component Exchange without Functional Exchanges"; //$NON-NLS-1$
  String FILTER_OAB_SHOW_ALLOCATED_INTERACTIONS_ON_COMMUNICATION_MEANS_ID = "Show Allocated Functional Exchanges on Component Exchanges"; //$NON-NLS-1$
  String FILTER_OAB_HIDE_CROSS_INTEREACTIONS_OF_ROLES_ID = "Hide cross Functional Exchanges of reusable Components"; //$NON-NLS-1$
  String FILTER_OAB_HIDE_INTERACTIONS_NAMES_ID = "Hide Interactions names"; //$NON-NLS-1$
  String FILTER_OAB_HIDE_COMMUNICATION_MEANS_NAMES_ID = "Hide Communication Means names"; //$NON-NLS-1$

  // OAIB filters - Operational Activity Interaction Blank
  String FILTER_OAIB_HIDE_INTERACTIONS = "Hide Interactions"; //$NON-NLS-1$
  String FILTER_OAIB_HIDE_INTERACTIONS_NAMES = "Hide Interactions names"; //$NON-NLS-1$
  String FILTER_OAIB_SHOW_EXCHANGEITEMS = "Show [EI1, EI2]"; //$NON-NLS-1$
  String FILTER_OAIB_SHOW_EXCHANGEITEMS_PARAMETERS = "Show EI1(param1:type1, param2:type2), EI2(param3:type3)"; //$NON-NLS-1$
  String FILTER_OAIB_SHOW_FUNCTIONAL_EXCHANGES_EXCHANGEITEMS = "Show I [EI1, EI2]"; //$NON-NLS-1$
  String FILTER_OAIB_SHOW_FUNCTIONAL_EXCHANGES_PARAMETERS = "Show I (type1, type2, type3)"; //$NON-NLS-1$
  String FILTER_OAIB_SHOW_FUNCTIONAL_EXCHANGES_EXCHANGEITEMS_PARAMETERS = "Show I [EI1(param1:type1, param2:type2), EI2(param3:type3)]"; //$NON-NLS-1$
  // filter id
  String FILTER_OAIB_SHOW_EXCHANGEITEMS_ID = "Show ExchangeItems"; //$NON-NLS-1$
  String FILTER_OAIB_SHOW_EXCHANGEITEMS_PARAMETERS_ID = "Show ExchangeItems parameters"; //$NON-NLS-1$
  String FILTER_OAIB_SHOW_FUNCTIONAL_EXCHANGES_EXCHANGEITEMS_ID = "Show Functional Exchanges [ExchangeItems]"; //$NON-NLS-1$
  String FILTER_OAIB_SHOW_FUNCTIONAL_EXCHANGES_PARAMETERS_ID = "Show Functional Exchanges (Parameters)"; //$NON-NLS-1$
  String FILTER_OAIB_SHOW_FUNCTIONAL_EXCHANGES_EXCHANGEITEMS_PARAMETERS_ID = "Show Functional Exchanges [ExchangeItems(Parameters)]"; //$NON-NLS-1$

  // OCB filters - Operational Capabilities Blank
  String FILTER_OCB_HIDE_COMMUNICATION_MEANS = "Hide Communication Means"; //$NON-NLS-1$
  String FILTER_OCB_HIDE_INVOLVEMENT_LINKS = "Hide Involvement Links"; //$NON-NLS-1$
  String FILTER_OCB_HIDE_OPERATIONAL_CAPABILITY_EXTENDS = "Hide Operational Capability Extends"; //$NON-NLS-1$
  String FILTER_OCB_HIDE_OPERATIONAL_CAPABILITY_INCLUDES = "Hide Operational Capability Includes"; //$NON-NLS-1$
  String FILTER_OCB_HIDE_OPERATIONAL_CAPABILITY_GENERALIZATIONS = "Hide Operational Capability Generalizations"; //$NON-NLS-1$

  // OAS filters - Operational Activity Scenario
  String FILTER_OAS_HIDE_EXECUTIONS = "Hide executions"; //$NON-NLS-1$
  String FILTER_OAS_HIDE_PRE_AND_POST_CONDITIONS = "Hide pre and post conditions"; //$NON-NLS-1$
  String FILTER_OAS_HIDE_PRE_AND_POST_CONDITIONS_ID = "Hide PrePost conditions"; //$NON-NLS-1$
  String FILTER_OAS_SHOW_EXCHANGEITEMS = "Show [EI1, EI2]"; //$NON-NLS-1$
  String FILTER_OAS_SHOW_EXCHANGEITEMS_PARAMETERS = "Show EI1(param1:type1, param2:type2), EI2(param3:type3)"; //$NON-NLS-1$
  String FILTER_OAS_SHOW_FUNCTIONAL_EXCHANGES_EXCHANGEITEMS = "Show I [EI1, EI2]"; //$NON-NLS-1$
  String FILTER_OAS_SHOW_FUNCTIONAL_EXCHANGES_PARAMETERS = "Show I (type1, type2, type3)"; //$NON-NLS-1$
  String FILTER_OAS_SHOW_FUNCTIONAL_EXCHANGES_EXCHANGEITEMS_PARAMETERS = "Show I [EI1(param1:type1, param2:type2), EI2(param3:type3)]"; //$NON-NLS-1$
  String FILTER_OAS_SHOW_CEEIPARAM = "Show CM [EI (param1:type1, param2:type2)]"; //$NON-NLS-1$
  String FILTER_OAS_SHOW_I_EXCHANGE_CONTEXT = "Show I {Exchange Context}"; //$NON-NLS-1$
  String FILTER_OAS_SHOW_EXCHANGE_CONTEXT = "Show {Exchange Context}"; //$NON-NLS-1$
  
  String FILTER_OAS_SHOW_EXCHANGEITEMS_ID = "Show ExchangeItems"; //$NON-NLS-1$
  String FILTER_OAS_SHOW_EXCHANGEITEMS_PARAMETERS_ID = "Show ExchangeItems parameters"; //$NON-NLS-1$
  String FILTER_OAS_SHOW_FUNCTIONAL_EXCHANGES_EXCHANGEITEMS_ID = "Show Functional Exchanges [ExchangeItems]"; //$NON-NLS-1$
  String FILTER_OAS_SHOW_FUNCTIONAL_EXCHANGES_PARAMETERS_ID = "Show Functional Exchanges (Parameters)"; //$NON-NLS-1$
  String FILTER_OAS_SHOW_FUNCTIONAL_EXCHANGES_EXCHANGEITEMS_PARAMETERS_ID = "Show Functional Exchanges [ExchangeItems(Parameters)]"; //$NON-NLS-1$

  // OEB filters - Operational Entity Blank
  String FILTER_OEB_HIDE_ALLOCATED_INTERACTIONS = "Hide Allocated Interactions"; //$NON-NLS-1$
  String FILTER_OEB_HIDE_OPERATIONAL_ACTIVITIES = "Hide Operational Activities"; //$NON-NLS-1$
  String FILTER_OEB_HIDE_ROLES = "Hide Roles"; //$NON-NLS-1$
  String FILTER_OEB_HIDE_OPERATIONAL_ACTORS = "Hide Operational Actors"; //$NON-NLS-1$
  String FILTER_OEB_HIDE_INTERACTIONS = "Hide Interactions"; //$NON-NLS-1$
  String FILTER_OEB_HIDE_COMMUNICATION_MEANS = "Hide Communication Means"; //$NON-NLS-1$
  String FILTER_OEB_SHOW_EXCHANGE_ITEMS_ON_INTERACTIONS = "Show Exchange Items on Interactions"; //$NON-NLS-1$
  String FILTER_OEB_SHOW_EXCHANGE_ITEM_ON_COMMUNICATION_MEANS = "Show Exchange Items on Communication Means"; //$NON-NLS-1$
  String FILTER_OEB_SHOW_EXCHANGE_ITEM_ON_COMMUNICATION_MEANS_WITHOUT_INTERACTIONS =
      "Show Exchange Items on Communication Means (without Interactions)"; //$NON-NLS-1$
  String FILTER_OEB_SHOW_ALLOCATED_INTERACTIONS_ON_COMMUNICATION_MEANS = "Show Allocated Interactions on Communication Means"; //$NON-NLS-1$
  String FILTER_OEB_HIDE_CROSS_INTEREACTIONS_OF_ROLES = "Hide cross Interactions of Roles"; //$NON-NLS-1$

  // OES filters - Operational Entity Scenario
  String FILTER_OES_HIDE_EXECUTIONS = "Hide executions"; //$NON-NLS-1$
  String FILTER_OES_HIDE_PRE_AND_POST_CONDITIONS = "Hide pre and post conditions"; //$NON-NLS-1$
  String FILTER_OES_HIDE_PRE_AND_POST_CONDITIONS_ID = "Hide PrePost conditions"; //$NON-NLS-1$
  String FILTER_OES_SHOW_EXCHANGEITEMS = "Show [EI1, EI2]"; //$NON-NLS-1$
  String FILTER_OES_SHOW_EXCHANGEITEMS_PARAMETERS = "Show EI1(param1:type1, param2:type2), EI2(param3:type3)"; //$NON-NLS-1$
  String FILTER_OES_SHOW_FUNCTIONAL_EXCHANGES = "Show I"; //$NON-NLS-1$
  String FILTER_OES_SHOW_FUNCTIONAL_EXCHANGES_EXCHANGEITEMS = "Show I [EI1, EI2]"; //$NON-NLS-1$
  String FILTER_OES_SHOW_FUNCTIONAL_EXCHANGES_PARAMETERS = "Show I (type1, type2, type3)"; //$NON-NLS-1$
  String FILTER_OES_SHOW_FUNCTIONAL_EXCHANGES_EXCHANGEITEMS_PARAMETERS = "Show I [EI1(param1:type1, param2:type2), EI2(param3:type3)]"; //$NON-NLS-1$
  String FILTER_OES_SHOW_COMPONENT_EXCHANGES_EXCHANGEITEMS = "Show CM [EI1, EI2]";//$NON-NLS-1$
  String FILTER_OES_SHOW_CEPARAM = "Show CM (type1, type2)"; //$NON-NLS-1$
  String FILTER_OES_SHOW_CEEIPARAM = "Show CM [EI (param1:type1, param2:type2)]"; //$NON-NLS-1$
  String FILTER_OES_SHOW_I_EXCHANGE_CONTEXT = "Show I {Exchange Context}"; //$NON-NLS-1$
  String FILTER_OES_SHOW_EXCHANGE_CONTEXT = "Show {Exchange Context}"; //$NON-NLS-1$
  String FILTER_OES_SHOW_CM_EXCHANGE_CONTEXT = "Show CM {Exchange Context}"; //$NON-NLS-1$
	
  
  // filter id
  String FILTER_OES_SHOW_EXCHANGEITEMS_ID = "Show ExchangeItems"; //$NON-NLS-1$
  String FILTER_OES_SHOW_EXCHANGEITEMS_PARAMETERS_ID = "Show ExchangeItems parameters"; //$NON-NLS-1$
  String FILTER_OES_SHOW_FUNCTIONAL_EXCHANGES_ID = "Show Functional Exchanges"; //$NON-NLS-1$
  String FILTER_OES_SHOW_FUNCTIONAL_EXCHANGES_EXCHANGEITEMS_ID = "Show Functional Exchanges [ExchangeItems]"; //$NON-NLS-1$
  String FILTER_OES_SHOW_FUNCTIONAL_EXCHANGES_PARAMETERS_ID = "Show Functional Exchanges (Parameters)"; //$NON-NLS-1$
  String FILTER_OES_SHOW_FUNCTIONAL_EXCHANGES_EXCHANGEITEMS_PARAMETERS_ID = "Show Functional Exchanges [ExchangeItems(Parameters)]"; //$NON-NLS-1$
  String FILTER_OES_SHOW_COMPONENT_EXCHANGES_EXCHANGEITEMS_ID = "Show Component Exchanges [ExchangeItems]";//$NON-NLS-1$
  String FILTER_OES_SHOW_CEPARAM_ID = "CEParam"; //$NON-NLS-1$
  String FILTER_OES_SHOW_CEEIPARAM_ID = "CEEIParam"; //$NON-NLS-1$

  // ORB filters - Operational Role Blank
  String FILTER_ORB_HIDE_ALLOCATED_INTERACTIONS = "Hide Allocated Interactions"; //$NON-NLS-1$
  String FILTER_ORB_HIDE_INTERACTIONS = "Hide Interactions"; //$NON-NLS-1$

  // PAB filters - Physical Architecture Blank
  String FILTER_PAB_COLLAPSE_COMPONENT_PHYSICAL_PORTS = "Collapse Component Physical Ports"; //$NON-NLS-1$
  String FILTER_PAB_COLLAPSE_COMPONENT_PORTS = "Collapse Component Ports"; //$NON-NLS-1$
  String FILTER_PAB_COLLAPSE_FUNCTION_PORTS = "Collapse Function Ports"; //$NON-NLS-1$
  String FILTER_PAB_HIDE_FUNCTION_PORTS_WITHOUT_EXCHANGES = "Hide Function Ports without Exchanges"; //$NON-NLS-1$
  String FILTER_PAB_HIDE_COMPONENT_PORTS_WITHOUT_EXCHANGES = "Hide Component Ports without Exchanges"; //$NON-NLS-1$
  String FILTER_PAB_HIDE_PHYSICAL_PORTS_WITHOUT_LINKS = "Hide Physical Ports without Links"; //$NON-NLS-1$
  String FILTER_PAB_HIDE_ALLOCATED_FUNCTION_ON_PARENT_CONTAINERS = "Hide Allocated Function On Parent Containers"; //$NON-NLS-1$
  String FILTER_PAB_HIDE_ALLOCATED_FUNCTIONAL_EXCHANGES = "Hide Allocated Functional Exchanges"; //$NON-NLS-1$
  String FILTER_PAB_HIDE_FUNCTIONS = "Hide Functions"; //$NON-NLS-1$
  String FILTER_PAB_HIDE_FUNCTIONAL_EXCHANGES = "Hide Functional Exchanges"; //$NON-NLS-1$
  String FILTER_PAB_HIDE_FUNCTIONAL_EXCHANGES_NAMES = "Hide Functional Exchanges names"; //$NON-NLS-1$
  String FILTER_PAB_HIDE_COMPONENT_EXCHANGES = "Hide Component Exchanges"; //$NON-NLS-1$
  String FILTER_PAB_HIDE_COMPONENT_EXCHANGES_NAMES = "Hide Component Exchanges names"; //$NON-NLS-1$
  String FILTER_PAB_HIDE_PHYSICAL_LINKS = "Hide Physical Links"; //$NON-NLS-1$
  String FILTER_PAB_HIDE_PHYSICAL_LINKS_NAMES = "Hide Physical Links names"; //$NON-NLS-1$
  String FILTER_PAB_HIDE_PORT_ALLOCATIONS = "Hide Port Allocations"; //$NON-NLS-1$
  String FILTER_PAB_HIDE_PORT_DELEGATIONS = "Hide Port Delegations"; //$NON-NLS-1$
  String FILTER_PAB_HIDE_COMPONENT_PORT_ALLOCATIONS = "Hide Component Port Allocations"; //$NON-NLS-1$
  String FILTER_PAB_HIDE_ALLOCATED_FUNCTION_PORTS = "Hide Allocated Function Ports"; //$NON-NLS-1$
  String FILTER_PAB_HIDE_NODE_PCS = "Hide Node PCs"; //$NON-NLS-1$
  String FILTER_PAB_HIDE_BEHAVIOR_PCS = "Hide Behavior PCs"; //$NON-NLS-1$
  String FILTER_PAB_HIDE_PHYSICAL_ACTORS = "Hide Physical Actors"; //$NON-NLS-1$
  String FILTER_PAB_HIDE_DEPLOYED_PCS = "Hide Deployed PCs"; //$NON-NLS-1$
  String FILTER_PAB_SHOW_EXCHANGE_ITEMS_ON_FUNCTIONAL_EXCHANGES = "Show Exchange Items on Functional Exchanges"; //$NON-NLS-1$
  String FILTER_PAB_SHOW_EXCHANGE_ITEMS_ON_COMPONENT_EXCHANGES = "Show Exchange Items on Component Exchanges"; //$NON-NLS-1$
  String FILTER_PAB_SHOW_EXCHANGE_ITEMS_ON_COMPONENT_EXCHANGE_WITHOUT_FUNCTIONAL_EXCHANGES =
      "Show Exchange Items on Component Exchange (without Functional Exchanges)"; //$NON-NLS-1$
  String FILTER_PAB_SHOW_ALLOCATED_FUNCTIONAL_EXCHANGES_ON_COMPONENT_EXCHANGES =
      "Show Allocated Functional Exchanges on Component Exchanges"; //$NON-NLS-1$
  String FILTER_PAB_HIDE_CROSS_FUNCTIONAL_EXCHANGES_OF_REUSABLE_COMPONENTS = "Hide cross Functional Exchanges of reusable Components"; //$NON-NLS-1$
  String FILTER_PAB_HIDE_SIMPLIFIED_DIAGRAM_BASED_COMPONENT_EXCHANGES = "Hide Simplified Diagram Based Component Exchanges"; //$NON-NLS-1$
  String FILTER_PAB_HIDE_SIMPLIFIED_GROUPED_COMPONENT_EXCHANGES = "Hide Simplified Grouped Component Exchanges"; //$NON-NLS-1$
  String FILTER_PAB_HIDE_SIMPLIFIED_GROUP_OF_COMPONENT_EXCHANGES_ID = "Hide Simplified Group of Component Exchanges"; //$NON-NLS-1$
  String FILTER_PAB_HIDE_PORT_REALIZATIONS_ID = "Hide Port Realizations"; //$NON-NLS-1$
  String FILTER_PAB_HIDE_SIMPLIFIED_ORIENTED_GROUPED_COMPONENT_EXCHANGES = "Hide Simplified Oriented Grouped Component Exchanges"; //$NON-NLS-1$

  // filter id
  String FILTER_PAB_HIDE_COMPONENT_EXCHANGES_ID = "Hide Connections"; //$NON-NLS-1$
  String FILTER_PAB_HIDE_PORT_ALLOCATIONS_ID = "Hide Port Realizations"; //$NON-NLS-1$
  String FILTER_PAB_HIDE_ALLOCATED_FUNCTION_PORTS_ID = "Hide Realized  Ports"; //$NON-NLS-1$
  String FILTER_PAB_SHOW_EXCHANGE_ITEMS_ON_FUNCTIONAL_EXCHANGES_ID = "Show ExchangeItems"; //$NON-NLS-1$
  String FILTER_PAB_SHOW_EXCHANGE_ITEMS_ON_COMPONENT_EXCHANGE_WITHOUT_FUNCTIONAL_EXCHANGES_ID =
      "Show Exchange Items on Component Exchange without Functional Exchanges"; //$NON-NLS-1$

  // PDFB filters - Physical Data Flow Blank
  String FILTER_PDFB_COLLAPSE_PORTS = "Collapse Ports"; //$NON-NLS-1$
  String FILTER_PDFB_HIDE_FUNCTIONAL_EXCHANGES = "Hide Functional Exchanges"; //$NON-NLS-1$
  String FILTER_PDFB_HIDE_FUNCTIONAL_EXCHANGES_NAMES = "Hide Functional Exchanges names"; //$NON-NLS-1$
  String FILTER_PDFB_HIDE_FUNCTION_PORTS_WITHOUT_EXCHANGES = "Hide Function Ports without Exchanges"; //$NON-NLS-1$
  String FILTER_PDFB_HIDE_EXCHANGE_CATEGORIES = "Hide Exchange Categories"; //$NON-NLS-1$
  String FILTER_PDFB_SHOW_EXCHANGEITEMS = "Show [EI1, EI2]"; //$NON-NLS-1$
  String FILTER_PDFB_SHOW_EXCHANGEITEMS_PARAMETERS = "Show EI1(param1:type1, param2:type2), EI2(param3:type3)"; //$NON-NLS-1$
  String FILTER_PDFB_SHOW_FUNCTIONAL_EXCHANGES_EXCHANGEITEMS = "Show FE [EI1, EI2]"; //$NON-NLS-1$
  String FILTER_PDFB_SHOW_FUNCTIONAL_EXCHANGES_PARAMETERS = "Show FE (type1, type2, type3)";//$NON-NLS-1$
  String FILTER_PDFB_SHOW_FUNCTIONAL_EXCHANGES_EXCHANGEITEMS_PARAMETERS = "Show FE [EI1(param1:type1, param2:type2), EI2(param3:type3)]"; //$NON-NLS-1$

  String FILTER_PDFB_SHOW_EXCHANGEITEMS_ID = "Show ExchangeItems"; //$NON-NLS-1$
  String FILTER_PDFB_SHOW_EXCHANGEITEMS_PARAMETERS_ID = "Show ExchangeItems parameters"; //$NON-NLS-1$
  String FILTER_PDFB_SHOW_FUNCTIONAL_EXCHANGES_EXCHANGEITEMS_ID = "Show Functional Exchanges [ExchangeItems]"; //$NON-NLS-1$
  String FILTER_PDFB_SHOW_FUNCTIONAL_EXCHANGES_PARAMETERS_ID = "Show Functional Exchanges (Parameters)";//$NON-NLS-1$
  String FILTER_PDFB_SHOW_FUNCTIONAL_EXCHANGES_EXCHANGEITEMS_PARAMETERS_ID = "Show Functional Exchanges [ExchangeItems(Parameters)]"; //$NON-NLS-1$

  // PFBD Filters
  String FILTER_PFBD_HIDE_CONTROL_NODES = "Hide Control Nodes";//$NON-NLS-1$

  // SAB filters - System Architecture Blank
  String FILTER_SAB_COLLAPSE_COMPONENT_PORTS = "Collapse Component Ports"; //$NON-NLS-1$
  String FILTER_SAB_COLLAPSE_FUNCTION_PORTS = "Collapse Function Ports"; //$NON-NLS-1$
  String FILTER_SAB_HIDE_FUNCTION_PORTS_WITHOUT_EXCHANGES = "Hide Function Ports without Exchanges"; //$NON-NLS-1$
  String FILTER_SAB_HIDE_COMPONENT_PORTS_WITHOUT_EXCHANGES = "Hide Component Ports without Exchanges"; //$NON-NLS-1$
  String FILTER_SAB_HIDE_ALLOCATED_FUNCTIONAL_EXCHANGES = "Hide Allocated Functional Exchanges"; //$NON-NLS-1$
  String FILTER_SAB_HIDE_FUNCTIONS = "Hide Functions"; //$NON-NLS-1$
  String FILTER_SAB_HIDE_FUNCTIONAL_EXCHANGES = "Hide Functional Exchanges"; //$NON-NLS-1$
  String FILTER_SAB_HIDE_FUNCTIONAL_EXCHANGES_NAMES = "Hide Functional Exchanges names"; //$NON-NLS-1$
  String FILTER_SAB_HIDE_COMPONENT_EXCHANGES = "Hide Component Exchanges"; //$NON-NLS-1$
  String FILTER_SAB_HIDE_COMPONENT_EXCHANGES_NAMES = "Hide Component Exchanges names"; //$NON-NLS-1$
  String FILTER_SAB_HIDE_PHYSICAL_LINKS_NAMES = "Hide Physical Links names"; //$NON-NLS-1$
  String FILTER_SAB_HIDE_PORT_ALLOCATIONS = "Hide Port Allocations"; //$NON-NLS-1$
  String FILTER_SAB_HIDE_ALLOCATED_FUNCTION_PORTS = "Hide Allocated Function Ports"; //$NON-NLS-1$
  String FILTER_SAB_SHOW_EXCHANGE_ITEMS_ON_FUNCTIONAL_EXCHANGES = "Show Exchange Items on Functional Exchanges"; //$NON-NLS-1$
  String FILTER_SAB_SHOW_EXCHANGE_ITEMS_ON_COMPONENT_EXCHANGES = "Show Exchange Items on Component Exchanges"; //$NON-NLS-1$
  String FILTER_SAB_SHOW_EXCHANGE_ITEMS_ON_COMPONENT_EXCHANGE_WITHOUT_FUNCTIONAL_EXCHANGES =
      "Show Exchange Items on Component Exchange (without Functional Exchanges)"; //$NON-NLS-1$
  String FILTER_SAB_SHOW_ALLOCATED_FUNCTIONAL_EXCHANGES_ON_COMPONENT_EXCHANGES =
      "Show Allocated Functional Exchanges on Component Exchanges"; //$NON-NLS-1$
  String FILTER_SAB_HIDE_CROSS_FUNCTIONAL_EXCHANGES_OF_REUSABLE_COMPONENTS = "Hide cross Functional Exchanges of reusable Components"; //$NON-NLS-1$
  String FILTER_SAB_HIDE_SIMPLIFIED_GROUPED_COMPONENT_EXCHANGES = "Hide Simplified Grouped Component Exchanges"; //$NON-NLS-1$
  String FILTER_SAB_HIDE_SIMPLIFIED_GROUPED_COMPONENT_EXCHANGES_ID = "Hide Simplified Group of Component Exchanges"; //$NON-NLS-1$
  String FILTER_SAB_HIDE_SIMPLIFIED_ORIENTED_GROUPED_COMPONENT_EXCHANGES = "Hide Simplified Oriented Grouped Component Exchanges"; //$NON-NLS-1$

  // filter id
  String FILTER_SAB_HIDE_COMPONENT_EXCHANGES_ID = "Hide Connections"; //$NON-NLS-1$
  String FILTER_SAB_HIDE_PORT_ALLOCATIONS_ID = "Hide Port Realizations"; //$NON-NLS-1$
  String FILTER_SAB_HIDE_ALLOCATED_FUNCTION_PORTS_ID = "Hide Realized  Ports"; //$NON-NLS-1$
  String FILTER_SAB_SHOW_EXCHANGE_ITEMS_ON_FUNCTIONAL_EXCHANGES_ID = "Show ExchangeItems"; //$NON-NLS-1$
  String FILTER_SAB_SHOW_EXCHANGE_ITEMS_ON_COMPONENT_EXCHANGE_WITHOUT_FUNCTIONAL_EXCHANGES_ID =
      "Show Exchange Items on Component Exchange without Functional Exchanges"; //$NON-NLS-1$

  // SDFB filters - System Data Flow Bank
  String FILTER_SDFB_COLLAPSE_PORTS = "Collapse Ports"; //$NON-NLS-1$
  String FILTER_SDFB_HIDE_FUNCTIONAL_EXCHANGES = "Hide Functional Exchanges"; //$NON-NLS-1$
  String FILTER_SDFB_HIDE_FUNCTIONAL_EXCHANGES_NAMES = "Hide Functional Exchanges names"; //$NON-NLS-1$
  String FILTER_SDFB_HIDE_FUNCTION_PORTS_WITHOUT_EXCHANGES = "Hide Function Ports without Exchanges"; //$NON-NLS-1$
  String FILTER_SDFB_HIDE_EXCHANGE_CATEGORIES = "Hide Exchange Categories"; //$NON-NLS-1$
  String FILTER_SDFB_SHOW_EXCHANGEITEMS = "Show [EI1, EI2]"; //$NON-NLS-1$
  String FILTER_SDFB_SHOW_EXCHANGEITEMS_PARAMETERS = "Show EI1(param1:type1, param2:type2), EI2(param3:type3)"; //$NON-NLS-1$
  String FILTER_SDFB_SHOW_FUNCTIONAL_EXCHANGES_EXCHANGEITEMS = "Show FE [EI1, EI2]"; //$NON-NLS-1$
  String FILTER_SDFB_SHOW_FUNCTIONAL_EXCHANGES_PARAMETERS = "Show FE (type1, type2, type3)";//$NON-NLS-1$
  String FILTER_SDFB_SHOW_FUNCTIONAL_EXCHANGES_EXCHANGEITEMS_PARAMETERS = "Show FE [EI1(param1:type1, param2:type2), EI2(param3:type3)]"; //$NON-NLS-1$

  // filter id
  String FILTER_SDFB_SHOW_EXCHANGEITEMS_ID = "Show ExchangeItems"; //$NON-NLS-1$
  String FILTER_SDFB_SHOW_EXCHANGEITEMS_PARAMETERS_ID = "Show ExchangeItems parameters"; //$NON-NLS-1$
  String FILTER_SDFB_SHOW_FUNCTIONAL_EXCHANGES_EXCHANGEITEMS_ID = "Show Functional Exchanges [ExchangeItems]"; //$NON-NLS-1$
  String FILTER_SDFB_SHOW_FUNCTIONAL_EXCHANGES_PARAMETERS_ID = "Show Functional Exchanges (Parameters)";//$NON-NLS-1$
  String FILTER_SDFB_SHOW_FUNCTIONAL_EXCHANGES_EXCHANGEITEMS_PARAMETERS_ID = "Show Functional Exchanges [ExchangeItems(Parameters)]"; //$NON-NLS-1$

  // SFBD filters - System Function Breakdown
  String FILTER_SFBD_HIDE_CONTROL_NODES = "Hide Control Nodes";//$NON-NLS-1$

  // Exchange Context filters
  String FILTER_SHOW_EXCHANGE_CONTEXT_ID = "ShowExchangeContext"; //$NON-NLS-1$
  String FILTER_SHOW_CE_EXCHANGE_CONTEXT_ID = "ShowCEExchangeContext"; //$NON-NLS-1$
  String FILTER_SHOW_FE_EXCHANGE_CONTEXT_ID = "ShowFEExchangeContext"; //$NON-NLS-1$
  String FILTER_SHOW_EI_EXCHANGE_CONTEXT_ID = "ShowEIExchangeContext"; //$NON-NLS-1$
}
