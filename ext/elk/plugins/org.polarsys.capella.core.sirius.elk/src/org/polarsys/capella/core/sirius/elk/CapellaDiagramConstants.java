/*******************************************************************************
 * Copyright (c) 2024, 2025 Obeo.
 * 
 * This program and the accompanying materials are made available under the
 * terms of the Eclipse Public License 2.0 which is available at
 * http://www.eclipse.org/legal/epl-2.0
 * 
 * SPDX-License-Identifier: EPL-2.0
 *
 * Contributors:
 *     Obeo - initial API and implementation
 *******************************************************************************/
package org.polarsys.capella.core.sirius.elk;

import org.polarsys.capella.core.sirius.analysis.IDiagramNameConstants;
import org.polarsys.capella.core.sirius.analysis.activator.SiriusViewActivator;

/**
 * Constants for Capella Diagram names.
 *
 * @see org.polarsys.capella.core.sirius.analysis.IDiagramNameConstants
 * @see org.polarsys.capella.core.sirius.analysis.IMappingNameConstants
 * 
 * @author nperansin
 */
public final class CapellaDiagramConstants implements IDiagramNameConstants {
    
    /** Plugin with Capella VSM. */
    public static final String DESIGN_PATH = '/' + SiriusViewActivator.ID + "/description/"; //$NON-NLS-1$
    
    // common.odesign

    /** Diagram description name of CA diagram. */
    public static final String CA_DIAGRAM_NAME = "Capella Architecture"; //$NON-NLS-1$

    /** Diagram description name of MASM diagram. */
    public static final String MASM_DIAGRAM_NAME = MODES_AND_STATES_DIAGRAM_NAME;

    /** Diagram description name of MSM diagram. */
    public static final String MSM_DIAGRAM_NAME = MODE_STATE_DIAGRAM_NAME;

    /** Diagram description name of CDB diagram. */
    public static final String CDB_DIAGRAM_NAME = CLASS_BLANK_DIAGRAM_NAME;

    /** Diagram description name of CDI diagram. */
    public static final String CDI_DIAGRAM_NAME = CONTEXTUAL_COMPONENT_DETAILED_INTERFACES_DIAGRAM_NAME;

    /** Diagram description name of CCEI diagram. */
    public static final String CCEI_DIAGRAM_NAME = CONTEXTUAL_COMPONENT_EXTERNAL_INTERFACES_DIAGRAM_NAME;

    /** Diagram description name of CCII diagram. */
    public static final String CCII_DIAGRAM_NAME = CONTEXTUAL_COMPONENT_INTERNAL_INTERFACES_DIAGRAM_NAME;

    /** Diagram description name of IDB diagram. */
    public static final String IDB_DIAGRAM_NAME = INTERFACES_BLANK_DIAGRAM_NAME;

    /** Diagram description name of CCRI diagram. */
    public static final String CCRI_DIAGRAM_NAME = CONTEXTUAL_CAPABILITY_REALIZATION_INVOLVEMENT;

    /** Diagram description name of CRB diagram. */
    public static final String CRB_DIAGRAM_NAME = CAPABILITY_REALIZATION_BLANK;

    /** Diagram description name of PL diagram. */
    public static final String PD_DIAGRAM_NAME = PACKAGE_DEPENDENCIES_DIAGRAM_NAME;

    /** Diagram description name of ID diagram. */
    public static final String ID_DIAGRAM_NAME = INTERFACE_DIAGRAM_NAME;

    /** Diagram description name of FCD diagram. */
    public static final String FCD_DIAGRAM_NAME = FUNCTIONAL_CHAIN_DIAGRAM_NAME;

    // context.odesign
    /** Diagram description name of CM diagram. */
    public static final String CM_DIAGRAM_NAME = CONTEXTUAL_MISSION_DIAGRAM_NAME;

    /** Diagram description name of MB diagram. */
    public static final String MB_DIAGRAM_NAME = MISSIONS_BLANK_DIAGRAM_NAME;

    /** Diagram description name of CC diagram. */
    public static final String CC_DIAGRAM_NAME = CONTEXTUAL_CAPABILITY_REALIZATION_INVOLVEMENT;

    /** Diagram description name of MCB diagram. */
    public static final String MCB_DIAGRAM_NAME = MISSIONS_CAPABILITIES_BLANK_DIAGRAM_NAME;

    /** Diagram description name of SFBD diagram. */
    public static final String SFBD_DIAGRAM_NAME = SYSTEM_FUNCTION_BREAKDOWN_DIAGRAM_NAME;

    /** Diagram description name of SDFB diagram. */
    public static final String SDFB_DIAGRAM_NAME = SYSTEM_DATA_FLOW_BLANK_DIAGRAM_NAME;

    /** Diagram description name of SAB diagram. */
    public static final String SAB_DIAGRAM_NAME = SYSTEM_ARCHITECTURE_BLANK_DIAGRAM_NAME;

    /** Diagram description name of CSA diagram. */
    public static final String CSA_DIAGRAM_NAME = CONTEXTUAL_SYSTEM_ACTORS_DIAGRAM_NAME;

    // EPBS.odesign
    /** Diagram description name of AB diagram. */
    public static final String AB_DIAGRAM_NAME = EPBS_ARCHITECTURE_BLANK_DIAGRAM_NAME;

    /** Diagram description name of CIB diagram. */
    public static final String CIB_DIAGRAM_NAME = CONFIGURATION_ITEMS_BREAKDOWN_DIAGRAM_NAME;

    // logical.odesign
    /** Diagram description name of LCB diagram. */
    public static final String LCB_DIAGRAM_NAME = LOGICAL_COMPONENT_BREAKDOWN_DIAGRAM_NAME;

    /** Diagram description name of LFB diagram. */
    public static final String LFB_DIAGRAM_NAME = LOGICAL_FUNCTION_BREAKDOWN_DIAGRAM_NAME;

    /** Diagram description name of LDFB diagram. */
    public static final String LDFB_DIAGRAM_NAME = LOGICAL_DATA_FLOW_BLANK_DIAGRAM_NAME;

    /** Diagram description name of LAB diagram. */
    public static final String LAB_DIAGRAM_NAME = LOGICAL_ARCHITECTURE_BLANK_DIAGRAM_NAME;

    /** Diagram description name of CRR diagram. */ // Missing from constants
    public static final String CRR_DIAGRAM_NAME = "Capability Realization Refinement"; //$NON-NLS-1$

    // oa.odesign
    /** Diagram description name of OEB diagram. */
    public static final String OEBREAKDOWN_DIAGRAM_NAME = OPERATIONAL_ENTITY_BREAKDOWN_DIAGRAM_NAME;

    /** Diagram description name of OAIB diagram. */
    public static final String OAIB_DIAGRAM_NAME = OPERATIONAL_ACTIVITY_INTERACTION_BLANK_DIAGRAM_NAME;

    /** Diagram description name of OEB diagram. */
    public static final String OEBLANK_DIAGRAM_NAME = OPERATIONAL_ENTITY_BLANK_DIAGRAM_NAME;

    /** Diagram description name of OAB diagram. */
    public static final String OAB_DIAGRAM_NAME = OPERATIONAL_ACTIVITY_BREAKDOWN_DIAGRAM_NAME;

    /** Diagram description name of ORB diagram. */
    public static final String ORB_DIAGRAM_NAME = OPERATIONAL_ROLE_BLANK_DIAGRAM_NAME;

    /** Diagram description name of OPD diagram. */
    public static final String OPD_DIAGRAM_NAME = OPERATIONAL_PROCESS_DESCRIPTION_DIAGRAM_NAME;

    /** Diagram description name of OCB diagram. */
    public static final String OCB_DIAGRAM_NAME = OPERATIONAL_CAPABILITIES_ENTITYIES_BLANK_DIAGRAM_NAME;

    /** Diagram description name of COC diagram. */
    public static final String COC_DIAGRAM_NAME = CONTEXTUAL_OC_DIAGRAM_NAME;

    // physical.odesign
    /** Diagram description name of PFB diagram. */
    public static final String PFB_DIAGRAM_NAME = PHYSICAL_FUNCTION_BREAKDOWN_DIAGRAM_NAME;

    /** Diagram description name of PDFB diagram. */
    public static final String PDFB_DIAGRAM_NAME = PHYSICAL_DATA_FLOW_BLANK_DIAGRAM_NAME;

    /** Diagram description name of PCB diagram. */
    public static final String PCB_DIAGRAM_NAME = PHYSICAL_COMPONENT_BREAKDOWN_DIAGRAM_NAME;

    /** Diagram description name of PAB diagram. */
    public static final String PAB_DIAGRAM_NAME = PHYSICAL_ARCHITECTURE_BLANK_DIAGRAM_NAME;

    /** Diagram description name of PDD diagram. */
    public static final String PPD_DIAGRAM_NAME = PHYSICAL_PATH_DESCRIPTION_DIAGRAM_NAME;

    /**
     * Private constructor to avoid instantiation.
     */
    private CapellaDiagramConstants() {
    }

}
