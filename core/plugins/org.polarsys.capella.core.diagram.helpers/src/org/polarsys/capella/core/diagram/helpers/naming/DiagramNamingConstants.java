/*******************************************************************************
 * Copyright (c) 2006, 2016 THALES GLOBAL SERVICES.
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

package org.polarsys.capella.core.diagram.helpers.naming;

import org.eclipse.osgi.util.NLS;

/**
 * Diagrams naming constants (I18n support)
 */
public class DiagramNamingConstants extends NLS {

  private static final String BUNDLE_NAME = "org.polarsys.capella.core.diagram.helpers.naming.messages"; //$NON-NLS-1$

  public static String OPERATIONAL_PROCESS_DESCRIPTION_DIAGRAM_PREFIX;
  public static String OPERATIONAL_PROCESS_DESCRIPTION_DIAGRAM_NAME;

  public static String FUNCTIONAL_CHAIN_DIAGRAM_PREFIX;
  public static String FUNCTIONAL_CHAIN_DIAGRAM_SYSTEM_PREFIX;
  public static String FUNCTIONAL_CHAIN_DIAGRAM_LOGICAL_PREFIX;
  public static String FUNCTIONAL_CHAIN_DIAGRAM_PHYSICAL_PREFIX;
  
  public static String PHYSICAL_PATH_DIAGRAM_PREFIX;
  public static String PHYSICAL_PATH_DIAGRAM_SYSTEM_PREFIX;
  public static String PHYSICAL_PATH_DIAGRAM_LOGICAL_PREFIX;
  public static String PHYSICAL_PATH_DIAGRAM_PHYSICAL_PREFIX;

  public static String FUNCTIONAL_CHAIN_DIAGRAM_NAME;
  public static String FUNCTIONAL_CHAIN_DIAGRAM_SYSTEM_NAME;
  public static String FUNCTIONAL_CHAIN_DIAGRAM_LOGICAL_NAME;
  public static String FUNCTIONAL_CHAIN_DIAGRAM_PHYSICAL_NAME;

  public static String PHYSICAL_PATH_DIAGRAM_NAME;
  public static String PHYSICAL_PATH_DIAGRAM_SYSTEM_NAME;
  public static String PHYSICAL_PATH_DIAGRAM_LOGICAL_NAME;
  public static String PHYSICAL_PATH_DIAGRAM_PHYSICAL_NAME;
  
  public static String OPERATIONAL_ACTIVITY_INTERACTION_BLANK_DIAGRAM_PREFIX;
  public static String SYSTEM_DATA_FLOW_BLANK_DIAGRAM_PREFIX;
  public static String LOGICAL_DATA_FLOW_BLANK_DIAGRAM_PREFIX;
  public static String PHYSICAL_DATA_FLOW_BLANK_DIAGRAM_PREFIX;

  public static String OPERATIONAL_ENTITY_BLANK_DIAGRAM_PREFIX;
  public static String SYSTEM_ARCHITECTURE_BLANK_DIAGRAM_PREFIX;
  public static String LOGICAL_ARCHITECTURE_BLANK_DIAGRAM_PREFIX;
  public static String PHYSICAL_ARCHITECTURE_BLANK_DIAGRAM_PREFIX;

  public static String OPERATIONAL_CAPABILITIES_ENTITYIES_BLANK_DIAGRAM_PREFIX;
  public static String MISSIONS_CAPABILITIES_BLANK_DIAGRAM_PREFIX;
  public static String CAPABILITY_REALIZATION_BLANK_DIAGRAM_PREFIX;
  
  public static String INTERFACE_SCENARIO_PREFIX;
  public static String FUNCTION_SCENARIO_PREFIX;
  public static String EXCHANGE_SCENARIO_PREFIX;
  public static String ENTITY_SCENARIO_PREFIX;
  public static String ACTIVITY_SCENARIO_PREFIX;

  static {
    // initialize resource bundle
    NLS.initializeMessages(BUNDLE_NAME, DiagramNamingConstants.class);
  }

  private DiagramNamingConstants() {
    // Private constructor.
  }
}
