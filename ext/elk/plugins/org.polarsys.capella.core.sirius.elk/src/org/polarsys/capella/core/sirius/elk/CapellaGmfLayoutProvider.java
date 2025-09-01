/*******************************************************************************
 * Copyright (c) 2019, 2025 Obeo.
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

import static org.polarsys.capella.core.sirius.analysis.IDiagramNameConstants.CAPABILITY_REALIZATION_BLANK;
import static org.polarsys.capella.core.sirius.analysis.IDiagramNameConstants.CAPABILITY_REALIZATION_REFINEMENT;
import static org.polarsys.capella.core.sirius.analysis.IDiagramNameConstants.CLASS_BLANK_DIAGRAM_NAME;
import static org.polarsys.capella.core.sirius.analysis.IDiagramNameConstants.CONFIGURATION_ITEMS_BREAKDOWN_DIAGRAM_NAME;
import static org.polarsys.capella.core.sirius.analysis.IDiagramNameConstants.CONTEXTUAL_CAPABILITY_REALIZATION_INVOLVEMENT;
import static org.polarsys.capella.core.sirius.analysis.IDiagramNameConstants.CONTEXTUAL_COMPONENT_DETAILED_INTERFACES_DIAGRAM_NAME;
import static org.polarsys.capella.core.sirius.analysis.IDiagramNameConstants.CONTEXTUAL_COMPONENT_EXTERNAL_INTERFACES_DIAGRAM_NAME;
import static org.polarsys.capella.core.sirius.analysis.IDiagramNameConstants.CONTEXTUAL_COMPONENT_INTERNAL_INTERFACES_DIAGRAM_NAME;
import static org.polarsys.capella.core.sirius.analysis.IDiagramNameConstants.CONTEXTUAL_MISSION_DIAGRAM_NAME;
import static org.polarsys.capella.core.sirius.analysis.IDiagramNameConstants.CONTEXTUAL_OC_DIAGRAM_NAME;
import static org.polarsys.capella.core.sirius.analysis.IDiagramNameConstants.CONTEXTUAL_SYSTEM_ACTORS_DIAGRAM_NAME;
import static org.polarsys.capella.core.sirius.analysis.IDiagramNameConstants.EPBS_ARCHITECTURE_BLANK_DIAGRAM_NAME;
import static org.polarsys.capella.core.sirius.analysis.IDiagramNameConstants.FUNCTIONAL_CHAIN_DIAGRAM_NAME;
import static org.polarsys.capella.core.sirius.analysis.IDiagramNameConstants.INTERFACES_BLANK_DIAGRAM_NAME;
import static org.polarsys.capella.core.sirius.analysis.IDiagramNameConstants.INTERFACE_DIAGRAM_NAME;
import static org.polarsys.capella.core.sirius.analysis.IDiagramNameConstants.LOGICAL_ARCHITECTURE_BLANK_DIAGRAM_NAME;
import static org.polarsys.capella.core.sirius.analysis.IDiagramNameConstants.LOGICAL_COMPONENT_BREAKDOWN_DIAGRAM_NAME;
import static org.polarsys.capella.core.sirius.analysis.IDiagramNameConstants.LOGICAL_DATA_FLOW_BLANK_DIAGRAM_NAME;
import static org.polarsys.capella.core.sirius.analysis.IDiagramNameConstants.LOGICAL_FUNCTION_BREAKDOWN_DIAGRAM_NAME;
import static org.polarsys.capella.core.sirius.analysis.IDiagramNameConstants.MISSIONS_BLANK_DIAGRAM_NAME;
import static org.polarsys.capella.core.sirius.analysis.IDiagramNameConstants.MISSIONS_CAPABILITIES_BLANK_DIAGRAM_NAME;
import static org.polarsys.capella.core.sirius.analysis.IDiagramNameConstants.MODES_AND_STATES_DIAGRAM_NAME;
import static org.polarsys.capella.core.sirius.analysis.IDiagramNameConstants.MODE_STATE_DIAGRAM_NAME;
import static org.polarsys.capella.core.sirius.analysis.IDiagramNameConstants.OPERATIONAL_ACTIVITY_BREAKDOWN_DIAGRAM_NAME;
import static org.polarsys.capella.core.sirius.analysis.IDiagramNameConstants.OPERATIONAL_ACTIVITY_INTERACTION_BLANK_DIAGRAM_NAME;
import static org.polarsys.capella.core.sirius.analysis.IDiagramNameConstants.OPERATIONAL_CAPABILITIES_ENTITYIES_BLANK_DIAGRAM_NAME;
import static org.polarsys.capella.core.sirius.analysis.IDiagramNameConstants.OPERATIONAL_ENTITY_BLANK_DIAGRAM_NAME;
import static org.polarsys.capella.core.sirius.analysis.IDiagramNameConstants.OPERATIONAL_ENTITY_BREAKDOWN_DIAGRAM_NAME;
import static org.polarsys.capella.core.sirius.analysis.IDiagramNameConstants.OPERATIONAL_PROCESS_DESCRIPTION_DIAGRAM_NAME;
import static org.polarsys.capella.core.sirius.analysis.IDiagramNameConstants.OPERATIONAL_ROLE_BLANK_DIAGRAM_NAME;
import static org.polarsys.capella.core.sirius.analysis.IDiagramNameConstants.PACKAGE_DEPENDENCIES_DIAGRAM_NAME;
import static org.polarsys.capella.core.sirius.analysis.IDiagramNameConstants.PHYSICAL_ARCHITECTURE_BLANK_DIAGRAM_NAME;
import static org.polarsys.capella.core.sirius.analysis.IDiagramNameConstants.PHYSICAL_COMPONENT_BREAKDOWN_DIAGRAM_NAME;
import static org.polarsys.capella.core.sirius.analysis.IDiagramNameConstants.PHYSICAL_DATA_FLOW_BLANK_DIAGRAM_NAME;
import static org.polarsys.capella.core.sirius.analysis.IDiagramNameConstants.PHYSICAL_FUNCTION_BREAKDOWN_DIAGRAM_NAME;
import static org.polarsys.capella.core.sirius.analysis.IDiagramNameConstants.PHYSICAL_PATH_DESCRIPTION_DIAGRAM_NAME;
import static org.polarsys.capella.core.sirius.analysis.IDiagramNameConstants.SYSTEM_ARCHITECTURE_BLANK_DIAGRAM_NAME;
import static org.polarsys.capella.core.sirius.analysis.IDiagramNameConstants.SYSTEM_DATA_FLOW_BLANK_DIAGRAM_NAME;
import static org.polarsys.capella.core.sirius.analysis.IDiagramNameConstants.SYSTEM_FUNCTION_BREAKDOWN_DIAGRAM_NAME;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.eclipse.core.runtime.IAdaptable;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.gmf.runtime.common.core.service.IOperation;
import org.eclipse.gmf.runtime.diagram.ui.editparts.DiagramEditPart;
import org.eclipse.gmf.runtime.diagram.ui.services.layout.ILayoutNode;
import org.eclipse.gmf.runtime.diagram.ui.services.layout.ILayoutNodeOperation;
import org.eclipse.gmf.runtime.notation.Diagram;
import org.eclipse.sirius.diagram.DDiagram;
import org.eclipse.sirius.diagram.description.CustomLayoutConfiguration;
import org.eclipse.sirius.diagram.description.DiagramDescription;
import org.eclipse.sirius.diagram.ui.tools.api.layout.provider.DefaultLayoutProvider;
import org.eclipse.sirius.diagram.ui.tools.api.layout.provider.LayoutProvider;
import org.polarsys.capella.core.sirius.analysis.activator.SiriusViewActivator;

/**
 * A specific {@link DefaultLayoutProvider} to enable ELK layout on some Capella diagrams.
 *
 * @author lredor
 */
public class CapellaGmfLayoutProvider extends DefaultLayoutProvider {

    /**
     * Property to disable ELK layout. By default, elk layout is enable.
     */
    public static final String DISABLE_PROPERTY = "Capella.noELK"; // //$NON-NLS-1$
    
	/**
     * True to handle all kinds of Capella diagrams (work in progress to check them), or false to focus on the initial
     * kinds of diagrams: LDFB, OAIB and PAB.
     */
    public static final boolean HANDLE_ALL_CAPELLA_DIAGRAM_TYPES = true;

    private static final Map<String, List<DiagramLayoutMode>> CONFIGURATIONS = new HashMap<>();
    
    private static final boolean DISABLED = Boolean.getBoolean(DISABLE_PROPERTY);

    private static final List<String> LIMITED_LAYERED_DIAGRAM_DESCRIPTION_NAMES = List.of(
            LOGICAL_DATA_FLOW_BLANK_DIAGRAM_NAME, OPERATIONAL_ACTIVITY_INTERACTION_BLANK_DIAGRAM_NAME, PHYSICAL_ARCHITECTURE_BLANK_DIAGRAM_NAME
    );
    
    /** List of diagrams using Layered algorithm. */
    private static final List<String> LAYERED_DIAGRAM_DESCRIPTION_NAMES = List.of(
            // Not classified diagrams
            PACKAGE_DEPENDENCIES_DIAGRAM_NAME, // Not tested (no such diagram in IFE sample)

            CONTEXTUAL_COMPONENT_DETAILED_INTERFACES_DIAGRAM_NAME, // OK tested on "[CDI] Streaming Server"
            CONTEXTUAL_COMPONENT_EXTERNAL_INTERFACES_DIAGRAM_NAME, // Not tested (no such diagram in IFE sample)
            CONTEXTUAL_COMPONENT_INTERNAL_INTERFACES_DIAGRAM_NAME, // Not tested (no such diagram in IFE sample)
            INTERFACE_DIAGRAM_NAME, // Not tested (no such diagram in IFE sample)

            // Mode and States diagrams
            MODES_AND_STATES_DIAGRAM_NAME, // KO tested on "[M&S] IFE Operating Modes"
            // Done 1- "Initial" must have a specific size idem for "Final"
            // Done 2- Improvement (initial in first)
            // 3- Improvement : Maybe a top down layout is better
            MODE_STATE_DIAGRAM_NAME, // KO tested on a new diagram in IFE (same root as above diagram)
            // 1- "Initial" must have a specific size idem for "Final", "Choice"
            // 2- Wrong location for region (y=21)
            // 3- Improvement (initial in first)

            // Capabilities diagrams
            CONTEXTUAL_CAPABILITY_REALIZATION_INVOLVEMENT, // Not tested (no such diagram in IFE sample)
            CAPABILITY_REALIZATION_BLANK, // Not tested (no such diagram in IFE sample)
            MISSIONS_BLANK_DIAGRAM_NAME, // Not tested (no such diagram in IFE sample)
            CONTEXTUAL_CAPABILITY_REALIZATION_INVOLVEMENT, // KO tested on "[CC] Provide Video Entertainment
                                                                    // Services"
            // 1- Specific size for "Mission", "Capability" and "Actor"
            // 2- Improvement : Top down layout is better
            MISSIONS_CAPABILITIES_BLANK_DIAGRAM_NAME, // KO tested on "[MCB] All Missions and Capabilities"
            // 1- Specific size for "Mission", "Capability" and "Actor"
            CAPABILITY_REALIZATION_REFINEMENT, // Not tested (no such diagram in IFE sample)
            OPERATIONAL_CAPABILITIES_ENTITYIES_BLANK_DIAGRAM_NAME, // KO tested on "[OCB] Operational Capabilities"
            // 1- Specific size for "Entity", "Capability" and "Actor"
            // 2- Improvement : Same size for all actors
            CONTEXTUAL_OC_DIAGRAM_NAME, // Not tested (no such diagram in IFE sample)

            // Data flow blank diagrams
            OPERATIONAL_ACTIVITY_INTERACTION_BLANK_DIAGRAM_NAME, // tested
            SYSTEM_DATA_FLOW_BLANK_DIAGRAM_NAME, // tested
            LOGICAL_DATA_FLOW_BLANK_DIAGRAM_NAME, // tested
            PHYSICAL_DATA_FLOW_BLANK_DIAGRAM_NAME, // tested
            INTERFACES_BLANK_DIAGRAM_NAME, // Not tested (no such diagram in IFE sample)
            OPERATIONAL_ENTITY_BLANK_DIAGRAM_NAME, // Not tested (no such diagram in IFE sample)

            // Architecture blank diagrams
            SYSTEM_ARCHITECTURE_BLANK_DIAGRAM_NAME, // tested
            EPBS_ARCHITECTURE_BLANK_DIAGRAM_NAME, // KO (tested on "[EAB] Configuration Items and Realized
                                                                    // Artefacts" of IFE sample)
            // 1- specific size for "PhysicalLinInCI mapping"
            // 2- Wrong container size according to its label (if title on 2 lines)
            LOGICAL_ARCHITECTURE_BLANK_DIAGRAM_NAME, // OK (tested on IFE sample 
                                // "[LAB] [BUILD] All Components, Functions, CEs, FEs")
            PHYSICAL_ARCHITECTURE_BLANK_DIAGRAM_NAME, // tested
            OPERATIONAL_ROLE_BLANK_DIAGRAM_NAME // Not tested (no such diagram in IFE sample)
    );

    /** List of diagrams using Mr Tree algorithm. */
    private static final List<String> MR_TREE_DIAGRAM_DESCRIPTION_NAMES = List.of(
            // Breakdown diagrams
            SYSTEM_FUNCTION_BREAKDOWN_DIAGRAM_NAME, // KO wrong children order
            CONTEXTUAL_MISSION_DIAGRAM_NAME, // KO (tested on "[CM] Provide Entertainment Solutions" of IFE sample)
            // 1- The space between children does not consider label outside of the node
            FUNCTIONAL_CHAIN_DIAGRAM_NAME, // KO (tested on "[LFCD] Broadcast Audio Announcement" of IFE sample)
            // 1- wrong location of edge's labels
            // 2- space for label on edge is too small
            CONFIGURATION_ITEMS_BREAKDOWN_DIAGRAM_NAME, // Not tested (no such diagram in IFE sample)
            LOGICAL_COMPONENT_BREAKDOWN_DIAGRAM_NAME, // KO (tested on "[LCBD] Architecture Drivers" of IFE sample)
            // 1 - wrong children order
            LOGICAL_FUNCTION_BREAKDOWN_DIAGRAM_NAME, // KO (tested on "[LFBD] All Functions" of IFE sample)
                                                                     // wrong children order
            OPERATIONAL_ENTITY_BREAKDOWN_DIAGRAM_NAME, // KO wrong children order
            OPERATIONAL_ACTIVITY_BREAKDOWN_DIAGRAM_NAME, // KO wrong children order
            PHYSICAL_FUNCTION_BREAKDOWN_DIAGRAM_NAME, // KO wrong children order and strange result on
                                                                     // "[PFBD] All Physical Functions"
            PHYSICAL_COMPONENT_BREAKDOWN_DIAGRAM_NAME, // KO (tested on "[PCBD] Behavioural Components")
            OPERATIONAL_PROCESS_DESCRIPTION_DIAGRAM_NAME // KO
            // 1- wrong location of edge's labels
            // 2- space for label on edge is too small
            // 3- Maybe a layered diagram desc would be better
    );

    /** List of diagrams using Layered algorithm. */
    private static final List<String> DOT_DIAGRAM_DESCRIPTION_NAMES = List.of(
            // Classes
            CLASS_BLANK_DIAGRAM_NAME, // OK (tested on "[CDB] In-Flight Entertainment Dictionary")
            CONTEXTUAL_SYSTEM_ACTORS_DIAGRAM_NAME, // See with a "star diagram"
            PHYSICAL_PATH_DESCRIPTION_DIAGRAM_NAME // See with a "star diagram" and why there is not the same
                             // kind of result as above CSA_DIAGRAM_NAME
    );
    
    private static final DiagramLayoutMode BUILTIN_LAYOUT_CONF = new DiagramLayoutMode("Default", null); //$NON-NLS-1$

    static {
        List<DiagramLayoutMode> layeredConfigs = List.of(// Default mode is the first
                new DiagramLayoutMode("ELK Layer Horizontal", //$NON-NLS-1$
                        CapellaSiriusLayoutProvider.createLayeredConfiguration(true)),
                new DiagramLayoutMode("ELK Layer Vertical", //$NON-NLS-1$
                        CapellaSiriusLayoutProvider.createLayeredConfiguration(false)),
                BUILTIN_LAYOUT_CONF);

        if (HANDLE_ALL_CAPELLA_DIAGRAM_TYPES) {
            Map.of(// Groups of diagrams by layout kind
                    layeredConfigs, LAYERED_DIAGRAM_DESCRIPTION_NAMES, // layered
                    getOptionalConfigModes("ELK MrTree", //$NON-NLS-1$
                            CapellaSiriusLayoutProvider.createMrTreeConfiguration()),
                    MR_TREE_DIAGRAM_DESCRIPTION_NAMES, // mr tree
                    getOptionalConfigModes("ELK Dot", //$NON-NLS-1$
                            CapellaSiriusLayoutProvider.createDotConfiguration()),
                    DOT_DIAGRAM_DESCRIPTION_NAMES // dot requires graphviz
            ).forEach((init, names) -> names.forEach(name -> CONFIGURATIONS.put(name, init)));
        } else {
            LIMITED_LAYERED_DIAGRAM_DESCRIPTION_NAMES.forEach(name -> CONFIGURATIONS.put(name, layeredConfigs));
        }
    }

    private static List<DiagramLayoutMode> getOptionalConfigModes(String name, CustomLayoutConfiguration cfg) {
        return List.of(new DiagramLayoutMode(name, cfg), BUILTIN_LAYOUT_CONF);
    }
    
    /**
     * Indicates if a Diagram Description is managed by this Layout Provider.
     * 
     * @param diagramDescription
     *            the description to evaluate
     * @return true if diagram description applies to this provider
     */
    public static List<DiagramLayoutMode> getDiagramConfigurations(DiagramDescription diagramDescription) {
        if (!DISABLED && isCapellaDiagram(diagramDescription)) {
            return CONFIGURATIONS.getOrDefault(diagramDescription.getName(), List.of());
        }
        return List.of();
    }

    private static boolean isCapellaDiagram(DiagramDescription diagramDescription) {
        if (diagramDescription == null || diagramDescription.eResource() == null) {
            return false;
        }
        Resource res = diagramDescription.eResource();
        return res.getURI().toString().contains(SiriusViewActivator.VSM_PATH);
    }

    /**
     * Evaluates if a GMF operation is related to Capella diagrams layout.
     * 
     * Only some Capella diagrams are handled in this example, so this method returns true only for these kinds of
     * diagram.
     * 
     * @param operation
     *            the operation to evaluate
     * @return true if related to Capella diagrams layout.
     */
    @Override
    public boolean provides(IOperation operation) {
        return super.provides(operation) && isAnyNodeHandled(((ILayoutNodeOperation) operation).getLayoutNodes());
    }

    @Override
    public LayoutProvider getDiagramLayoutProvider(DiagramEditPart diagramEditPart, IAdaptable layoutHint) {
        return new CapellaSiriusLayoutProvider();
    }

    // 
    // Generic methods
    // 
    private static boolean isAnyNodeHandled(List<?> values) {
        return values.stream().anyMatch(it -> it instanceof ILayoutNode && isNodeHandled((ILayoutNode) it));
    }

    private static boolean isNodeHandled(ILayoutNode node) {
        Diagram gmfDiagram = node.getNode() != null ? node.getNode().getDiagram() : null;
        return gmfDiagram != null // unlikely
                && gmfDiagram.getElement() instanceof DDiagram ddiagram
                && DiagramLayoutMode.getModeConfiguration(ddiagram) != null;
    }

}
