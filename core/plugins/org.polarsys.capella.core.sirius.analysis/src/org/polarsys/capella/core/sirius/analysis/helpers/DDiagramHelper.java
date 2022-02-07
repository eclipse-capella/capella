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
package org.polarsys.capella.core.sirius.analysis.helpers;

import org.eclipse.sirius.diagram.DDiagram;
import org.polarsys.capella.core.diagram.helpers.naming.DiagramDescriptionConstants;
import org.polarsys.capella.core.sirius.analysis.IDiagramNameConstants;

/**
 *
 */
public class DDiagramHelper {

  public static boolean isOAB(DDiagram diagram_p) {
    return IDiagramNameConstants.OPERATIONAL_ENTITY_BLANK_DIAGRAM_NAME.equals(diagram_p.getDescription().getName());
  }

  public static boolean isLAB(DDiagram diagram_p) {
    return IDiagramNameConstants.LOGICAL_ARCHITECTURE_BLANK_DIAGRAM_NAME.equals(diagram_p.getDescription().getName());
  }

  public static boolean isSAB(DDiagram diagram_p) {
    return IDiagramNameConstants.SYSTEM_ARCHITECTURE_BLANK_DIAGRAM_NAME.equals(diagram_p.getDescription().getName());
  }

  public static boolean isPAB(DDiagram diagram_p) {
    return IDiagramNameConstants.PHYSICAL_ARCHITECTURE_BLANK_DIAGRAM_NAME.equals(diagram_p.getDescription().getName());
  }

  public static boolean isOAIB(DDiagram diagram_p) {
    return IDiagramNameConstants.OPERATIONAL_ACTIVITY_INTERACTION_BLANK_DIAGRAM_NAME.equals(diagram_p.getDescription().getName());
  }

  public static boolean isLDFB(DDiagram diagram_p) {
    return IDiagramNameConstants.LOGICAL_DATA_FLOW_BLANK_DIAGRAM_NAME.equals(diagram_p.getDescription().getName());
  }

  public static boolean isSDFB(DDiagram diagram_p) {
    return IDiagramNameConstants.SYSTEM_DATA_FLOW_BLANK_DIAGRAM_NAME.equals(diagram_p.getDescription().getName());
  }

  public static boolean isPDFB(DDiagram diagram_p) {
    return IDiagramNameConstants.PHYSICAL_DATA_FLOW_BLANK_DIAGRAM_NAME.equals(diagram_p.getDescription().getName());
  }

  public static boolean isXAB(DDiagram diagram_p) {
    return isOAB(diagram_p) || isSAB(diagram_p) || isLAB(diagram_p) || isPAB(diagram_p);
  }

  public static boolean isOPD(DDiagram diagram_p) {
    return IDiagramNameConstants.OPERATIONAL_PROCESS_DESCRIPTION_DIAGRAM_NAME.equals(diagram_p.getDescription().getName());
  }

  public static boolean isFCD(DDiagram diagram_p) {
    return IDiagramNameConstants.FUNCTIONAL_CHAIN_DIAGRAM_NAME.equals(diagram_p.getDescription().getName());
  }

  public static boolean isPPD(DDiagram diagram_p) {
    return IDiagramNameConstants.PHYSICAL_PATH_DESCRIPTION_DIAGRAM_NAME.equals(diagram_p.getDescription().getName());
  }

  public static boolean isXFCD(DDiagram diagram_p) {
    return isOPD(diagram_p) || isFCD(diagram_p) || isPPD(diagram_p);
  }

  public static boolean isOCB(DDiagram diagram_p) {
    return IDiagramNameConstants.OPERATIONAL_CAPABILITIES_ENTITYIES_BLANK_DIAGRAM_NAME.equals(diagram_p.getDescription().getName());
  }

  public static boolean isMCB(DDiagram diagram_p) {
    return IDiagramNameConstants.MISSIONS_CAPABILITIES_BLANK_DIAGRAM_NAME.equals(diagram_p.getDescription().getName());
  }

  public static boolean isMB(DDiagram diagram_p) {
    return IDiagramNameConstants.MISSIONS_BLANK_DIAGRAM_NAME.equals(diagram_p.getDescription().getName());
  }

  public static boolean isCC(DDiagram diagram_p) {
    return IDiagramNameConstants.CONTEXTUAL_CAPABILITY_DIAGRAM_NAME.equals(diagram_p.getDescription().getName());
  }

  public static boolean isCM(DDiagram diagram_p) {
    return IDiagramNameConstants.CONTEXTUAL_MISSION_DIAGRAM_NAME.equals(diagram_p.getDescription().getName());
  }

  public static boolean isCRB(DDiagram diagram_p) {
    return IDiagramNameConstants.CAPABILITY_REALIZATION_BLANK.equals(diagram_p.getDescription().getName());
  }

  public static boolean isCSA(DDiagram diagram_p) {
    return IDiagramNameConstants.CONTEXTUAL_SYSTEM_ACTORS_DIAGRAM_NAME.equals(diagram_p.getDescription().getName());
  }

  public static boolean isIDB(DDiagram diagram_p) {
    return IDiagramNameConstants.INTERFACES_BLANK_DIAGRAM_NAME.equals(diagram_p.getDescription().getName());
  }

  public static boolean isCDB(DDiagram diagram_p) {
    return DiagramDescriptionConstants.CLASS_BLANK_DIAGRAM_NAME.equals(diagram_p.getDescription().getName());
  }

  public static boolean isMSM(DDiagram diagram) {
    return IDiagramNameConstants.MODE_STATE_DIAGRAM_NAME.equals(diagram.getDescription().getName());
  }
}
