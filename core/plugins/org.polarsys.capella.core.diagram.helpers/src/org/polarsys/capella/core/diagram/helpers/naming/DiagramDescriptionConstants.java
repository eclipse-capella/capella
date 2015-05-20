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

package org.polarsys.capella.core.diagram.helpers.naming;

/**
 * Diagrams constants (no I18n support, hard coded named used in diagram descriptions) Used most often with
 * XXX.equals(diagram.getDescription().getName())
 * 
 * THESES CONSTANTS SHOULD NOT BE USED FOR NAMING A USER DIAGRAM, JUST TO IDENTITY THEM ACCORDING TO DESCRIPTION.NAME
 * PLEASE USE DiagramNamingConstants FOR I18N NAMING.
 */
@SuppressWarnings("nls")
public class DiagramDescriptionConstants {

  /**
   * Global names
   */
  public static final String CLASS_BLANK_DIAGRAM_NAME = "Class Diagram Blank";
  public static final String ARCHITECTURE_BLANK_DIAGRAM_NAME = "Architecture Blank";
  public static final String INTERACTION_BLANK_DIAGRAM_NAME = "Interaction Blank";
  public static final String DATA_FLOW_BLANK_DIAGRAM_NAME = "Data Flow Blank";
  public static final String ENTITY_BLANK_DIAGRAM_NAME = "Entity Blank";

  public static final String BREAKDOWN_DIAGRAM_NAME = "Breakdown";
  public static final String INTERFACE_SCENARIO = "Component Interfaces Scenario";
  public static final String ROLE_BLANK_DIAGRAM_NAME = "Role Blank";
}
