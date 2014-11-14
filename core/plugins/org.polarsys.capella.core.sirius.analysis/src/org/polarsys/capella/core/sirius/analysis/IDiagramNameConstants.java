/*******************************************************************************
 * Copyright (c) 2006, 2014 THALES GLOBAL SERVICES.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *  
 * Contributors:
 *    Thales - initial API and implementation
 *******************************************************************************/
package org.polarsys.capella.core.sirius.analysis;

import org.polarsys.capella.core.diagram.helpers.naming.DiagramDescriptionConstants;

/**
 */
@SuppressWarnings("nls")
public class IDiagramNameConstants extends DiagramDescriptionConstants {

  /**
   * Common diagrams
   */
  public static final String CONTEXTUAL_COMPONENT_DETAILED_INTERFACES_DIAGRAM_NAME = "Contextual Component Detailed Interfaces";
  public static final String CONTEXTUAL_COMPONENT_EXTERNAL_INTERFACES_DIAGRAM_NAME = "Contextual Component External Interfaces";
  public static final String CONTEXTUAL_COMPONENT_INTERNAL_INTERFACES_DIAGRAM_NAME = "Contextual Component Internal Interfaces";
  public static final String DATA_FLOW_SCENARIO_DIAGRAM_NAME = "Component Exchanges Scenario";
  public static final String INTERFACES_BLANK_DIAGRAM_NAME = "Interfaces Diagram Blank";
  public static final String INTERFACE_DIAGRAM_NAME = "Interface Diagram";
  public static final String INTERFACES_CAPABILITIES_AND_SCENARIOS_DIAGRAM_NAME = "Interfaces - Capabilities and Scenarios";
  public static final String INTERFACES_CAPABILITIES_DIAGRAM_NAME = "Interfaces - Capabilities";
  public static final String MODES_AND_STATES_DIAGRAM_NAME = "Modes & States";
  public static final String PACKAGE_DEPENDENCIES_DIAGRAM_NAME = "Package Dependencies";
  public static final String INTERFACE_SCENARIO_DIAGRAM_NAME = "Component Interfaces Scenario";
  public static final String FUNCTIONAL_CHAIN_DIAGRAM_NAME = "Functional Chain Description";
  public static final String PHYSICAL_PATH_DIAGRAM_NAME = "Physical Path Description";
  public static final String STATE_AND_MODE_MATRIX = "State And Mode - Matrix";
  public static final String CAPABILITY_REALIZATION_BLANK = "Capability Realization Blank";

  /**
   * Operational Analysis diagrams
   */
  public static final String CONTEXTUAL_OPERATIONAL_ACTIVITY_INTERACTION_DIAGRAM_NAME = "Contextual Operational Activity Interaction";
  public static final String OPERATIONAL_ACTIVITY_BREAKDOWN_DIAGRAM_NAME = "Operational Activity Breakdown";
  public static final String OPERATIONAL_ACTIVITY_INTERACTION_BLANK_DIAGRAM_NAME = "Operational Activity "
                                                                                   + DiagramDescriptionConstants.INTERACTION_BLANK_DIAGRAM_NAME;
  public static final String OPERATIONAL_ENTITY_BLANK_DIAGRAM_NAME = "Operational Entity Blank";
  public static final String OPERATIONAL_ENTITY_BREAKDOWN_DIAGRAM_NAME = "Operational Entity Breakdown";
  public static final String OPERATIONAL_INTERACTION_SCENARIO_DIAGRAM_NAME = "Operational Interaction Scenario";
  public static final String OPERATIONAL_ROLE_BLANK_DIAGRAM_NAME = "Operational Role Blank";
  public static final String OPERATIONAL_ACTIVITIES_REQUIREMENTS_DIAGRAM_NAME = "Operational Activities - Requirements";
  public static final String OPERATIONAL_CAPABILITIES_ENTITYIES_BLANK_DIAGRAM_NAME = "Operational Capabilities Blank";
  public static final String OPERATIONAL_ACTIVITY_INTERACTION_SCENARIO_DIAGRAM_NAME = "Activity Interaction Scenario";
  public static final String REQUIREMENTS_OPERATIONAL_ACTIVITIES__DIAGRAM_NAME = "Requirements - Operational Activities";
  public static final String OPERATIONAL_PROCESS_DESCRIPTION_DIAGRAM_NAME = "Operational Process Description";
  public static final String CONTEXTUAL_OPERATIONAL_CAPABILITIES__DIAGRAM_NAME = "Contextual Operational Capability";

  /**
   * System Analysis diagrams
   */
  public static final String FUNCTIONAL_SCENARIO = "Functional Scenario";
  public static final String CONTEXTUAL_CAPABILITY_DIAGRAM_NAME = "Contextual Capability";
  public static final String CONTEXTUAL_MISSION_DIAGRAM_NAME = "Contextual Mission";
  public static final String CONTEXTUAL_SYSTEM_ACTORS_DIAGRAM_NAME = "Contextual System Actors";
  public static final String CONTEXTUAL_SYSTEM_DATA_FLOW_DIAGRAM_NAME = "Contextual System Data Flow";
  public static final String MISSIONS_BLANK_DIAGRAM_NAME = "Missions Blank";
  public static final String MISSIONS_CAPABILITIES_BLANK_DIAGRAM_NAME = "Missions Capabilities Blank";
  public static final String SYSTEM_ACTOR_OPERATIONAL_ACTOR_DIAGRAM_NAME = "System Actor - Operational Actor";
  public static final String SYSTEM_ARCHITECTURE_BLANK_DIAGRAM_NAME = "System " + ARCHITECTURE_BLANK_DIAGRAM_NAME;
  public static final String SYSTEM_DATA_FLOW_BLANK_DIAGRAM_NAME = "System " + DATA_FLOW_BLANK_DIAGRAM_NAME;
  public static final String SYSTEM_FUNCTION_BREAKDOWN_DIAGRAM_NAME = "System Function Breakdown";
  public static final String SYSTEM_FUNCTIONS_OPERATIONAL_ACTIVITIES_DIAGRAM_NAME = "System Functions - Operational Activities";
  public static final String SYSTEM_FUNCTIONS_REQUIREMENTS_DIAGRAM_NAME = "System Functions - Requirements";
  public static final String REQUIREMENTS_SYSTEM_FUNCTIONS_DIAGRAM_NAME = "Requirements - System Functions";
  public static final String CONTEXTUAL_OC_DIAGRAM_NAME = "Contextual Operational Capability";
  /**
   * Logical Architecture diagrams
   */
  public static final String CONTEXTUAL_LOGICAL_DATA_FLOW_DIAGRAM_NAME = "Contextual Logical Data Flow";
  public static final String LOGICAL_ACTOR_CONTEXT_ACTOR_DIAGRAM_NAME = "Logical Actor - Context Actor";
  public static final String LOGICAL_ARCHITECTURE_BLANK_DIAGRAM_NAME = "Logical " + ARCHITECTURE_BLANK_DIAGRAM_NAME;
  public static final String LOGICAL_ARCHITECTURE_REQUIREMENT_REFINEMENTS_DIAGRAM_NAME = "Logical Architecture Requirement Refinements";
  public static final String LOGICAL_COMPONENT_BREAKDOWN_DIAGRAM_NAME = "Logical Component Breakdown";
  public static final String LOGICAL_COMPONENTS_LOGICAL_FUNCTIONS_DIAGRAM_NAME = "Logical Components - Logical Functions";
  public static final String LOGICAL_COMPONENTS_REQUIREMENTS_DIAGRAM_NAME = "Logical Components - Requirements";
  public static final String LOGICAL_DATA_FLOW_BLANK_DIAGRAM_NAME = "Logical " + DATA_FLOW_BLANK_DIAGRAM_NAME;
  public static final String LOGICAL_FUNCTION_BREAKDOWN_DIAGRAM_NAME = "Logical Function Breakdown";
  public static final String LOGICAL_FUNCTIONS_REQUIREMENTS_DIAGRAM_NAME = "Logical Functions - Requirements";
  public static final String LOGICAL_FUNCTIONS_SYSTEM_FUNCTIONS_DIAGRAM_NAME = "Logical Functions - System Functions";
  public static final String LOGICAL_INTERFACE_CONTEXT_INTERFACE_DIAGRAM_NAME = "Logical Interface - Context Interface";
  public static final String REQUIREMENTS_LOGICAL_FUNCTIONS_DIAGRAM_NAME = "Requirements - Logical Functions";

  /**
   * Physical Architecture diagrams
   */
  public static final String CONTEXTUAL_PHYSICAL_DATA_FLOW_DIAGRAM_NAME = "Contextual Physical Data Flow";
  public static final String PHYSICAL_ACTOR_LOGICAL_ACTOR_DIAGRAM_NAME = "Physical Actor - Logical Actor";
  public static final String PHYSICAL_ARCHITECTURE_BLANK_DIAGRAM_NAME = "Physical " + ARCHITECTURE_BLANK_DIAGRAM_NAME;
  public static final String PHYSICAL_COMPONENT_BREAKDOWN_DIAGRAM_NAME = "Physical Component Breakdown";
  public static final String PHYSICAL_COMPONENTS_LOGICAL_COMPONENTS_DIAGRAM_NAME = "Physical Components - Logical Components";
  public static final String PHYSICAL_COMPONENTS_PHYSICAL_FUNCTIONS_DIAGRAM_NAME = "Physical Components - Physical Functions";
  public static final String PHYSICAL_COMPONENTS_REQUIREMENTS_DIAGRAM_NAME = "Physical Components - Requirements";
  public static final String PHYSICAL_DATA_FLOW_BLANK_DIAGRAM_NAME = "Physical " + DATA_FLOW_BLANK_DIAGRAM_NAME;
  public static final String PHYSICAL_FUNCTION_BREAKDOWN_DIAGRAM_NAME = "Physical Function Breakdown";
  public static final String PHYSICAL_FUNCTIONS_LOGICAL_FUNCTIONS_DIAGRAM_NAME = "Physical Functions - Logical Functions";
  public static final String PHYSICAL_FUNCTIONS_REQUIREMENTS_DIAGRAM_NAME = "Physical Functions - Requirements";
  public static final String PHYSICAL_INTERFACE_LOGICAL_INTERFACE_DIAGRAM_NAME = "Physical Interface - Logical Interface";
  public static final String REQUIREMENTS_PHYSICAL_FUNCTIONS_DIAGRAM_NAME = "Requirements - Physical Functions";
  public static final String PHYSICAL_PATH_DESCRIPTION_DIAGRAM_NAME = "Physical Path Description";

  /**
   * EPBS Architecture diagrams
   */
  public static final String CONFIGURATION_ITEMS_REQUIREMENTS_DIAGRAM_NAME = "Configuration Items - Requirements";
  public static final String CONFIGURATION_ITEMS_PHYSICAL_COMPONENTS_DIAGRAM_NAME = "Configuration Items - Physical Artifacts";
  public static final String CONFIGURATION_ITEMS_BREAKDOWN_DIAGRAM_NAME = "Configuration Items Breakdown";
  public static final String EPBS_ARCHITECTURE_BLANK_DIAGRAM_NAME = "EPBS " + ARCHITECTURE_BLANK_DIAGRAM_NAME;
  public static final String EPBS_REQUIREMENT_REFINEMENTS_DIAGRAM_NAME = "EPBS Requirement Refinements";

}
