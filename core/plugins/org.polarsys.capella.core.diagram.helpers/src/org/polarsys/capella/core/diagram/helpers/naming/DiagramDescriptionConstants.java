/*******************************************************************************
 * Copyright (c) 2006, 2015 THALES GLOBAL SERVICES.
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

/**
 * Diagrams constants (no I18n support, hard coded named used in diagram descriptions) Used most often with
 * XXX.equals(diagram.getDescription().getName())
 * 
 * THESES CONSTANTS SHOULD NOT BE USED FOR NAMING A USER DIAGRAM, JUST TO IDENTITY THEM ACCORDING TO DESCRIPTION.NAME
 * PLEASE USE DiagramNamingConstants FOR I18N NAMING.
 */
public interface DiagramDescriptionConstants {

  /**
   * Global names
   */
  public static final String CLASS_BLANK_DIAGRAM_NAME = "Class Diagram Blank"; //$NON-NLS-1$
  public static final String ARCHITECTURE_BLANK_DIAGRAM_NAME = "Architecture Blank"; //$NON-NLS-1$
  public static final String INTERACTION_BLANK_DIAGRAM_NAME = "Interaction Blank"; //$NON-NLS-1$
  public static final String DATA_FLOW_BLANK_DIAGRAM_NAME = "Data Flow Blank"; //$NON-NLS-1$
  public static final String ENTITY_BLANK_DIAGRAM_NAME = "Entity Blank"; //$NON-NLS-1$

  public static final String BREAKDOWN_DIAGRAM_NAME = "Breakdown"; //$NON-NLS-1$
  public static final String INTERFACE_SCENARIO = "Component Interfaces Scenario"; //$NON-NLS-1$
  public static final String ROLE_BLANK_DIAGRAM_NAME = "Role Blank"; //$NON-NLS-1$
}
