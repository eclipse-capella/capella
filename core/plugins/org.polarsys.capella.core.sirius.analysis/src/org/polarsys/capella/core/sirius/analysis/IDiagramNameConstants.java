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
package org.polarsys.capella.core.sirius.analysis;

import org.polarsys.capella.core.diagram.helpers.naming.DiagramDescriptionConstants;

/**
 * 
 */
public interface IDiagramNameConstants extends DiagramDescriptionConstants {

  /**
   * Common diagrams
   */
  String CLASS_BLANK_DIAGRAM_NAME = "Class Diagram Blank";
  String CONTEXTUAL_COMPONENT_DETAILED_INTERFACES_DIAGRAM_NAME = "Contextual Component Detailed Interfaces"; //$NON-NLS-1$
  String CONTEXTUAL_COMPONENT_EXTERNAL_INTERFACES_DIAGRAM_NAME = "Contextual Component External Interfaces"; //$NON-NLS-1$
  String CONTEXTUAL_COMPONENT_INTERNAL_INTERFACES_DIAGRAM_NAME = "Contextual Component Internal Interfaces"; //$NON-NLS-1$
  String DATA_FLOW_SCENARIO_DIAGRAM_NAME = "Component Exchanges Scenario"; //$NON-NLS-1$
  String INTERFACES_BLANK_DIAGRAM_NAME = "Interfaces Diagram Blank"; //$NON-NLS-1$
  String INTERFACE_DIAGRAM_NAME = "Interface Diagram"; //$NON-NLS-1$
  String INTERFACES_CAPABILITIES_AND_SCENARIOS_DIAGRAM_NAME = "Interfaces - Capabilities and Scenarios"; //$NON-NLS-1$
  String INTERFACES_CAPABILITIES_DIAGRAM_NAME = "Interfaces - Capabilities"; //$NON-NLS-1$
  String MODE_STATE_DIAGRAM_NAME = "Mode State Machine"; //$NON-NLS-1$
  String MODES_AND_STATES_DIAGRAM_NAME = "Modes & States"; //$NON-NLS-1$
  String MODES_STATE_MACHINE_DIAGRAM_NAME = "Mode State Machine"; //$NON-NLS-1$
  String PACKAGE_DEPENDENCIES_DIAGRAM_NAME = "Package Dependencies"; //$NON-NLS-1$
  String INTERFACE_SCENARIO_DIAGRAM_NAME = "Component Interfaces Scenario"; //$NON-NLS-1$
  String FUNCTIONAL_CHAIN_DIAGRAM_NAME = "Functional Chain Description"; //$NON-NLS-1$
  String PHYSICAL_PATH_DIAGRAM_NAME = "Physical Path Description"; //$NON-NLS-1$
  String STATE_AND_MODE_MATRIX = "State And Mode - Matrix"; //$NON-NLS-1$
  String CONTEXTUAL_STATE_AND_MODE_MATRIX = "Contextual State And Mode - Matrix"; //$NON-NLS-1$
  String CAPABILITY_REALIZATION_BLANK = "Capability Realization Blank"; //$NON-NLS-1$
  String CONTEXTUAL_CAPABILITY_REALIZATION_INVOLVEMENT = "Contextual Capability Realization Involvement"; //$NON-NLS-1$

  /**
   * Operational Analysis diagrams
   */
  String CONTEXTUAL_OPERATIONAL_ACTIVITY_INTERACTION_DIAGRAM_NAME = "Contextual Operational Activity Interaction"; //$NON-NLS-1$
  String OPERATIONAL_ACTIVITY_BREAKDOWN_DIAGRAM_NAME = "Operational Activity Breakdown"; //$NON-NLS-1$
  String OPERATIONAL_ACTIVITY_INTERACTION_BLANK_DIAGRAM_NAME = "Operational Activity " //$NON-NLS-1$
      + DiagramDescriptionConstants.INTERACTION_BLANK_DIAGRAM_NAME; // $NON-NLS-1$
  String OPERATIONAL_ENTITY_BLANK_DIAGRAM_NAME = "Operational Entity Blank"; //$NON-NLS-1$
  String OPERATIONAL_ENTITY_BREAKDOWN_DIAGRAM_NAME = "Operational Entity Breakdown"; //$NON-NLS-1$
  String OPERATIONAL_INTERACTION_SCENARIO_DIAGRAM_NAME = "Operational Interaction Scenario"; //$NON-NLS-1$
  String OPERATIONAL_ROLE_BLANK_DIAGRAM_NAME = "Operational Role Blank"; //$NON-NLS-1$
  String OPERATIONAL_CAPABILITIES_ENTITYIES_BLANK_DIAGRAM_NAME = "Operational Capabilities Blank"; //$NON-NLS-1$
  String OPERATIONAL_ACTIVITY_INTERACTION_SCENARIO_DIAGRAM_NAME = "Activity Interaction Scenario"; //$NON-NLS-1$
  String OPERATIONAL_PROCESS_DESCRIPTION_DIAGRAM_NAME = "Operational Process Description"; //$NON-NLS-1$
  String CONTEXTUAL_OPERATIONAL_CAPABILITIES__DIAGRAM_NAME = "Contextual Operational Capability"; //$NON-NLS-1$

  /**
   * System Analysis diagrams
   */
  String FUNCTIONAL_SCENARIO = "Functional Scenario"; //$NON-NLS-1$
  String CONTEXTUAL_CAPABILITY_DIAGRAM_NAME = "Contextual Capability"; //$NON-NLS-1$
  String CONTEXTUAL_MISSION_DIAGRAM_NAME = "Contextual Mission"; //$NON-NLS-1$
  String CONTEXTUAL_SYSTEM_ACTORS_DIAGRAM_NAME = "Contextual System Actors"; //$NON-NLS-1$
  String CONTEXTUAL_SYSTEM_DATA_FLOW_DIAGRAM_NAME = "Contextual System Data Flow"; //$NON-NLS-1$
  String MISSIONS_BLANK_DIAGRAM_NAME = "Missions Blank"; //$NON-NLS-1$
  String MISSIONS_CAPABILITIES_BLANK_DIAGRAM_NAME = "Missions Capabilities Blank"; //$NON-NLS-1$
  String SYSTEM_ACTOR_OPERATIONAL_ACTOR_DIAGRAM_NAME = "System Actor - Operational Actor"; //$NON-NLS-1$
  String SYSTEM_ARCHITECTURE_BLANK_DIAGRAM_NAME = "System " + ARCHITECTURE_BLANK_DIAGRAM_NAME; //$NON-NLS-1$
  String SYSTEM_DATA_FLOW_BLANK_DIAGRAM_NAME = "System " + DATA_FLOW_BLANK_DIAGRAM_NAME; //$NON-NLS-1$
  String SYSTEM_FUNCTION_BREAKDOWN_DIAGRAM_NAME = "System Function Breakdown"; //$NON-NLS-1$
  String SYSTEM_FUNCTIONS_OPERATIONAL_ACTIVITIES_DIAGRAM_NAME = "System Functions - Operational Activities"; //$NON-NLS-1$
  String CONTEXTUAL_OC_DIAGRAM_NAME = "Contextual Operational Capability"; //$NON-NLS-1$
  /**
   * Logical Architecture diagrams
   */
  String CONTEXTUAL_LOGICAL_DATA_FLOW_DIAGRAM_NAME = "Contextual Logical Data Flow"; //$NON-NLS-1$
  String LOGICAL_ACTOR_CONTEXT_ACTOR_DIAGRAM_NAME = "Logical Actor - Context Actor"; //$NON-NLS-1$
  String LOGICAL_ARCHITECTURE_BLANK_DIAGRAM_NAME = "Logical " + ARCHITECTURE_BLANK_DIAGRAM_NAME; //$NON-NLS-1$
  String LOGICAL_COMPONENT_BREAKDOWN_DIAGRAM_NAME = "Logical Component Breakdown"; //$NON-NLS-1$
  String LOGICAL_COMPONENTS_LOGICAL_FUNCTIONS_DIAGRAM_NAME = "Logical Components - Logical Functions"; //$NON-NLS-1$
  String LOGICAL_DATA_FLOW_BLANK_DIAGRAM_NAME = "Logical " + DATA_FLOW_BLANK_DIAGRAM_NAME; //$NON-NLS-1$
  String LOGICAL_FUNCTION_BREAKDOWN_DIAGRAM_NAME = "Logical Function Breakdown"; //$NON-NLS-1$
  String LOGICAL_FUNCTIONS_SYSTEM_FUNCTIONS_DIAGRAM_NAME = "Logical Functions - System Functions"; //$NON-NLS-1$
  String LOGICAL_INTERFACE_CONTEXT_INTERFACE_DIAGRAM_NAME = "Logical Interface - Context Interface"; //$NON-NLS-1$

  /**
   * Physical Architecture diagrams
   */
  String CONTEXTUAL_PHYSICAL_DATA_FLOW_DIAGRAM_NAME = "Contextual Physical Data Flow"; //$NON-NLS-1$
  String PHYSICAL_ACTOR_LOGICAL_ACTOR_DIAGRAM_NAME = "Physical Actor - Logical Actor"; //$NON-NLS-1$
  String PHYSICAL_ARCHITECTURE_BLANK_DIAGRAM_NAME = "Physical " + ARCHITECTURE_BLANK_DIAGRAM_NAME; //$NON-NLS-1$
  String PHYSICAL_COMPONENT_BREAKDOWN_DIAGRAM_NAME = "Physical Component Breakdown"; //$NON-NLS-1$
  String PHYSICAL_COMPONENTS_LOGICAL_COMPONENTS_DIAGRAM_NAME = "Physical Components - Logical Components"; //$NON-NLS-1$
  String PHYSICAL_COMPONENTS_PHYSICAL_FUNCTIONS_DIAGRAM_NAME = "Physical Components - Physical Functions"; //$NON-NLS-1$
  String PHYSICAL_DATA_FLOW_BLANK_DIAGRAM_NAME = "Physical " + DATA_FLOW_BLANK_DIAGRAM_NAME; //$NON-NLS-1$
  String PHYSICAL_FUNCTION_BREAKDOWN_DIAGRAM_NAME = "Physical Function Breakdown"; //$NON-NLS-1$
  String PHYSICAL_FUNCTIONS_LOGICAL_FUNCTIONS_DIAGRAM_NAME = "Physical Functions - Logical Functions"; //$NON-NLS-1$
  String PHYSICAL_INTERFACE_LOGICAL_INTERFACE_DIAGRAM_NAME = "Physical Interface - Logical Interface"; //$NON-NLS-1$
  String PHYSICAL_PATH_DESCRIPTION_DIAGRAM_NAME = "Physical Path Description"; //$NON-NLS-1$

  /**
   * EPBS Architecture diagrams
   */
  String CONFIGURATION_ITEMS_PHYSICAL_COMPONENTS_DIAGRAM_NAME = "Configuration Items - Physical Artifacts"; //$NON-NLS-1$
  String CONFIGURATION_ITEMS_BREAKDOWN_DIAGRAM_NAME = "Configuration Items Breakdown"; //$NON-NLS-1$
  String EPBS_ARCHITECTURE_BLANK_DIAGRAM_NAME = "EPBS " + ARCHITECTURE_BLANK_DIAGRAM_NAME; //$NON-NLS-1$
}
