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
package org.polarsys.capella.core.dashboard;

import org.eclipse.jface.resource.ImageDescriptor;
import org.eclipse.sirius.table.metamodel.table.provider.TableUIPlugin;
import org.eclipse.ui.plugin.AbstractUIPlugin;
import org.polarsys.capella.core.sirius.ui.SiriusUIPlugin;

/**
 * Image constants definition.
 */
public interface IImageKeys {
  /**
   * Synchronize the viewer according to a section image id.
   */
  public final static String IMG_SYNCRONIZE_VIEWER = "full/obj16/synced.gif"; //$NON-NLS-1$
  /**
   * Capella Overview image id.
   */
  public final static String IMG_CAPELLA_OVERVIEW = "full/obj16/menu-overview.gif"; //$NON-NLS-1$s

  /**
   * Perform an automated transition image id.
   */
  public final static String IMG_PERFORM_AUTOMATED_TRANSITION = "full/ctool/transition.png"; //$NON-NLS-1$
  /**
   * Create Traceability Matrix image id.
   */
  public final static String IMG_TRACEABILITY_MATRIX = "full/ctool/matrix.png"; //$NON-NLS-1$
  /**
   * Create a new Contextual System Actors diagram image id.
   */
  public final static String IMG_NEW_CONTEXTUAL_SYSTEM_ACTORS_DIAGRAM = "full/ctool/contextual-system-actors-diagram.png"; //$NON-NLS-1$
  /**
   * Create image id.<br>
   */
  public final static String IMG_NEW_MISSION_AND_OR_CAPABILITY_DIAGRAM = "full/ctool/missions-capabilities-diagram.png"; //$NON-NLS-1$
  /**
   * Create a new Functional Breakdown diagram image id.
   */
  public final static String IMG_NEW_FUNCTIONAL_BREAKDOWN_DIAGRAM = "full/ctool/functional-breakdown-diagram.png"; //$NON-NLS-1$
  /**
   * Create a new Logical Breakdown diagram image id.
   */
  public final static String IMG_NEW_LOGICAL_BREAKDOWN_DIAGRAM = "full/ctool/logical-component-breakdown-diagram.png"; //$NON-NLS-1$
  /**
   * Create a new Physical Breakdown diagram image id.
   */
  public final static String IMG_NEW_PHYSICAL_BREAKDOWN_DIAGRAM = "full/ctool/physical-component-breakdown-diagram.png"; //$NON-NLS-1$
  /**
   * Create a new Blank Functional Dataflow diagram image id.
   */
  public final static String IMG_NEW_BLANK_FUNCTIONAL_DATAFLOW_DIAGRAM = "full/ctool/dataflows-diagram.png"; //$NON-NLS-1$
  /**
   * Create a new Functional Scenario diagram image id.
   */
  public final static String IMG_NEW_FUNCTIONAL_SCENARIO_DIAGRAM = "full/ctool/function-scenario.png"; //$NON-NLS-1$
  /**
   * Create new AF System Architecture diagram image id.
   */
  public final static String IMG_NEW_AF_SYSTEM_ARCHITECTURE_DIAGRAM = "full/ctool/AF-system-architecture-diagram.png"; //$NON-NLS-1$
  /**
   * Create a new allocation matrix image id.
   */
  public final static String IMG_NEW_ALLOCATIONS_MATRIX = "full/ctool/matrix.png"; //$NON-NLS-1$
  /**
   * Create a new Exchange scenario image id.
   */
  public final static String IMG_NEW_EXCHANGE_SCENARIO = "full/ctool/exchange-scenario.png"; //$NON-NLS-1$
  /**
   * Create a new detailed interface diagram on the System image id.
   */
  public final static String IMG_NEW_DETAILED_INTERFACE_DIAGRAM_ON_SYSTEM = "full/ctool/detailed-interface-diagram.png"; //$NON-NLS-1$
  /**
   * Create a new external interface diagram on the System image id.
   */
  public final static String IMG_NEW_EXTERNAL_INTERFACE_DIAGRAM_ON_SYSTEM = "full/ctool/external-interface-diagram.png"; //$NON-NLS-1$
  /**
   * Create a New Scenario image id.
   */
  public final static String IMG_NEW_INTERFACE_SCENARIO = "full/ctool/interface-scenario.png"; //$NON-NLS-1$
  /**
   * New Class diagram image id.
   */
  public final static String IMG_NEW_CLASS_DIAGRAM = "full/ctool/class-diagram.png"; //$NON-NLS-1$
  /**
   * New Mode State Machine diagram image id.
   */
  public final static String IMG_NEW_MODE_STATE_DIAGRAM = "full/ctool/mode-state-diagram.png"; //$NON-NLS-1$
  /**
   * Create Allocate logical function - Logical Architecture diagram image id.
   */
  public final static String IMG_NEW_AF_LOGICAL_ARCHITECTURE_DIAGRAM = "full/ctool/AF-logical-architecture-diagram.png"; //$NON-NLS-1$
  /**
   * Create Logical Architecture diagram image id.
   */
  public final static String IMG_NEW_LOGICAL_ARCHITECTURE_DIAGRAM = "full/ctool/logical-architecture-diagram.png"; //$NON-NLS-1$
  /**
   * Create a new internal interface diagram image id.
   */
  public final static String IMG_NEW_INTERNAL_INTERFACE_DIAGRAM = "full/ctool/internal-interface-diagram.png"; //$NON-NLS-1$
  /**
   * Create Physical Architecture diagram image id.
   */
  public final static String IMG_NEW_PHYSICAL_ARCHITECTURE_DIAGRAM = "full/ctool/physical-architecture-diagram.png"; //$NON-NLS-1$
  /**
   * Create Physical Architecture diagram image id.
   */
  public final static String IMG_NEW_PHYSICAL_ARCHITECTURE_FUNCTIONS_DIAGRAM = "full/ctool/physical-architecture-functions-diagram.png"; //$NON-NLS-1$
  /**
   * Create Operational Entity Breakdown diagram image id.
   */
  public final static String IMG_NEW_OPERATIONAL_ENTITY_BREAKDOWN_DIAGRAM = "full/ctool/operational-entity-breakdown-diagram.png"; //$NON-NLS-1$
  /**
   * Create a new Operational Capabilities diagram image id.
   */
  public final static String IMG_NEW_OPERATIONAL_CAPABILITY_BLANK_DIAGRAM = "full/ctool/operational-capabilities.png"; //$NON-NLS-1$
  /**
   * Create a new Operational Entity Blank diagram image id.
   */
  public final static String IMG_NEW_OPERATIONAL_ENTITY_BLANK_DIAGRAM = "full/ctool/operational-entity-blank-diagram.png"; //$NON-NLS-1$
  /**
   * Create a new Role Blank diagram image id.
   */
  public final static String IMG_NEW_ROLE_BLANK_DIAGRAM = "full/ctool/operational-role-blank-diagram.png"; //$NON-NLS-1$
  /**
   * Create a new Operational Activity Breakdown diagram image id.
   */
  public final static String IMG_NEW_OPERATIONAL_ACTIVITY_BREAKDOWN_DIAGRAM = "full/ctool/operational-activity-breakdown-diagram.png"; //$NON-NLS-1$
  /**
   * Create a new Operational Interaction Blank diagram image id.
   */
  public final static String IMG_NEW_OPERATIONAL_INTERACTION_BLANK_DIAGRAM = "full/ctool/operational-interaction-blank-diagram.png"; //$NON-NLS-1$
  /**
   * Create a new Operational Activity Blank diagram image id.
   */
  public final static String IMG_NEW_OPERATIONAL_ACTIVITY_BLANK_DIAGRAM = "full/ctool/operational-entity-blank-diagram.png"; //$NON-NLS-1$
  /**
   * Create a new OA dataflow scenario image id.
   */
  public final static String IMG_NEW_OA_DATAFLOW_SCENARIO = "full/ctool/interaction-scenario.png"; //$NON-NLS-1$
  /**
   * Create a new Activity Scenario image id. TODO IMAGE TO BE UPDATED ? 
   */
  public final static String IMG_NEW_OA_ACTIVITY_SCENARIO = "full/ctool/activity-scenario.png"; //$NON-NLS-1$
  /**
   * Create a new EPBS Breakdown diagram image id.
   */
  public final static String IMG_NEW_EPBS_BREAKDOWN_DIAGRAM = "full/ctool/logical-component-breakdown-diagram.png"; //$NON-NLS-1$
  /**
   * Create a new EPBS Architecture Blank diagram image id.
   */
  public final static String IMG_NEW_EPBS_ARCHITECTURE_BLANK_DIAGRAM = "full/ctool/logical-architecture-diagram.png"; //$NON-NLS-1$
  /**
   * Operational Analysis Overview Page image id.
   */
  public final static String IMG_OPERATIONAL_ANALYSIS_OVERVIEW_PAGE = "full/navigable/off/welcome-operational-analysis-off.png"; //$NON-NLS-1$
  /**
   * System Analysis Overview Page image id.
   */
  public final static String IMG_SYSTEM_ANALYSIS_OVERVIEW_PAGE = "full/navigable/off/welcome-system-analysis-off.png"; //$NON-NLS-1$
  /**
   * Logical Architecture Overview Page image id.
   */
  public final static String IMG_LOGICAL_ARCHITECTURE_OVERVIEW_PAGE = "full/navigable/off/welcome-logical-architecture-off.png"; //$NON-NLS-1$
  /**
   * Physical Architecture Overview Page image id.
   */
  public final static String IMG_PHYSICAL_ARCHITECTURE_OVERVIEW_PAGE = "full/navigable/off/welcome-physical-architecture-off.png"; //$NON-NLS-1$
  /**
   * EPBS Architecture Overview Page image id.
   */
  public final static String IMG_EPBS_ARCHITECTURE_OVERVIEW_PAGE = "full/navigable/off/welcome-epbs-off.png"; //$NON-NLS-1$
  /**
   * Operational Analysis Overview Page hover image id.
   */
  public final static String IMG_OPERATIONAL_ANALYSIS_OVERVIEW_HOVER_PAGE = "full/navigable/on/welcome-operational-analysis-on.png"; //$NON-NLS-1$
  /**
   * System Analysis Overview Page hover image id.
   */
  public final static String IMG_SYSTEM_ANALYSIS_OVERVIEW_HOVER_PAGE = "full/navigable/on/welcome-system-analysis-on.png"; //$NON-NLS-1$
  /**
   * Logical Architecture Overview Page hover image id.
   */
  public final static String IMG_LOGICAL_ARCHITECTURE_OVERVIEW_HOVER_PAGE = "full/navigable/on/welcome-logical-architecture-on.png"; //$NON-NLS-1$
  /**
   * Physical Architecture Overview Page hover image id.
   */
  public final static String IMG_PHYSICAL_ARCHITECTURE_OVERVIEW_HOVER_PAGE = "full/navigable/on/welcome-physical-architecture-on.png"; //$NON-NLS-1$
  /**
   * EPBS Architecture Overview Page hover image id.
   */
  public final static String IMG_EPBS_ARCHITECTURE_OVERVIEW_HOVER_PAGE = "full/navigable/on/welcome-epbs-on.png"; //$NON-NLS-1$
  /**
   * Operational Analysis Overview Page text image id.
   */
  public final static String IMG_OPERATIONAL_ANALYSIS_OVERVIEW_TEXT_PAGE = "full/navigable/text/welcome-operational-analysis-texte.png"; //$NON-NLS-1$
  /**
   * System Analysis Overview Page text image id.
   */
  public final static String IMG_SYSTEM_ANALYSIS_OVERVIEW_TEXT_PAGE = "full/navigable/text/welcome-system-analysis-texte.png"; //$NON-NLS-1$
  /**
   * Logical Architecture Overview Page text image id.
   */
  public final static String IMG_LOGICAL_ARCHITECTURE_OVERVIEW_TEXT_PAGE = "full/navigable/text/welcome-logical-architecture-texte.png"; //$NON-NLS-1$
  /**
   * Physical Architecture Overview Page text image id.
   */
  public final static String IMG_PHYSICAL_ARCHITECTURE_OVERVIEW_TEXT_PAGE = "full/navigable/text/welcome-physical-architecture-texte.png"; //$NON-NLS-1$
  /**
   * EPBS Architecture Overview Page text image id.
   */
  public final static String IMG_EPBS_ARCHITECTURE_OVERVIEW_TEXT_PAGE = "full/navigable/text/welcome-epbs-texte.png"; //$NON-NLS-1$
  /**
   * Image Descriptor for new diagram creation.
   */
  public final static ImageDescriptor IMAGE_DESCRIPTOR_NEW_DIAGRAM = SiriusUIPlugin.getDefault().getImageDescriptor("newDiagram.png"); //$NON-NLS-1$
  /**
   * Image Descriptor for new diagram creation.
   */
  public final static ImageDescriptor IMAGE_DESCRIPTOR_NEW_TABLE =
      AbstractUIPlugin.imageDescriptorFromPlugin(TableUIPlugin.ID, "/icons/full/obj16/CrossTableDescription.gif"); //$NON-NLS-1$
  /**
   * Image Descriptor for description actions.
   */
  public final static String IMAGE_DESCRIPTOR_DESCRIPTION = "full/obj16/help.gif"; //$NON-NLS-1$
}
