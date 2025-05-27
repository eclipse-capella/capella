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

import static org.polarsys.capella.core.sirius.elk.CapellaDiagramConstants.AB_DIAGRAM_NAME;
import static org.polarsys.capella.core.sirius.elk.CapellaDiagramConstants.CA_DIAGRAM_NAME;
import static org.polarsys.capella.core.sirius.elk.CapellaDiagramConstants.CCEI_DIAGRAM_NAME;
import static org.polarsys.capella.core.sirius.elk.CapellaDiagramConstants.CCII_DIAGRAM_NAME;
import static org.polarsys.capella.core.sirius.elk.CapellaDiagramConstants.CCRI_DIAGRAM_NAME;
import static org.polarsys.capella.core.sirius.elk.CapellaDiagramConstants.CC_DIAGRAM_NAME;
import static org.polarsys.capella.core.sirius.elk.CapellaDiagramConstants.CDB_DIAGRAM_NAME;
import static org.polarsys.capella.core.sirius.elk.CapellaDiagramConstants.CDI_DIAGRAM_NAME;
import static org.polarsys.capella.core.sirius.elk.CapellaDiagramConstants.CIB_DIAGRAM_NAME;
import static org.polarsys.capella.core.sirius.elk.CapellaDiagramConstants.CM_DIAGRAM_NAME;
import static org.polarsys.capella.core.sirius.elk.CapellaDiagramConstants.COC_DIAGRAM_NAME;
import static org.polarsys.capella.core.sirius.elk.CapellaDiagramConstants.CRB_DIAGRAM_NAME;
import static org.polarsys.capella.core.sirius.elk.CapellaDiagramConstants.CRR_DIAGRAM_NAME;
import static org.polarsys.capella.core.sirius.elk.CapellaDiagramConstants.CSA_DIAGRAM_NAME;
import static org.polarsys.capella.core.sirius.elk.CapellaDiagramConstants.FCD_DIAGRAM_NAME;
import static org.polarsys.capella.core.sirius.elk.CapellaDiagramConstants.IDB_DIAGRAM_NAME;
import static org.polarsys.capella.core.sirius.elk.CapellaDiagramConstants.ID_DIAGRAM_NAME;
import static org.polarsys.capella.core.sirius.elk.CapellaDiagramConstants.LAB_DIAGRAM_NAME;
import static org.polarsys.capella.core.sirius.elk.CapellaDiagramConstants.LCB_DIAGRAM_NAME;
import static org.polarsys.capella.core.sirius.elk.CapellaDiagramConstants.LDFB_DIAGRAM_NAME;
import static org.polarsys.capella.core.sirius.elk.CapellaDiagramConstants.LFB_DIAGRAM_NAME;
import static org.polarsys.capella.core.sirius.elk.CapellaDiagramConstants.MASM_DIAGRAM_NAME;
import static org.polarsys.capella.core.sirius.elk.CapellaDiagramConstants.MB_DIAGRAM_NAME;
import static org.polarsys.capella.core.sirius.elk.CapellaDiagramConstants.MCB_DIAGRAM_NAME;
import static org.polarsys.capella.core.sirius.elk.CapellaDiagramConstants.MSM_DIAGRAM_NAME;
import static org.polarsys.capella.core.sirius.elk.CapellaDiagramConstants.OAB_DIAGRAM_NAME;
import static org.polarsys.capella.core.sirius.elk.CapellaDiagramConstants.OAIB_DIAGRAM_NAME;
import static org.polarsys.capella.core.sirius.elk.CapellaDiagramConstants.OCB_DIAGRAM_NAME;
import static org.polarsys.capella.core.sirius.elk.CapellaDiagramConstants.OEBLANK_DIAGRAM_NAME;
import static org.polarsys.capella.core.sirius.elk.CapellaDiagramConstants.OEBREAKDOWN_DIAGRAM_NAME;
import static org.polarsys.capella.core.sirius.elk.CapellaDiagramConstants.OPD_DIAGRAM_NAME;
import static org.polarsys.capella.core.sirius.elk.CapellaDiagramConstants.ORB_DIAGRAM_NAME;
import static org.polarsys.capella.core.sirius.elk.CapellaDiagramConstants.PAB_DIAGRAM_NAME;
import static org.polarsys.capella.core.sirius.elk.CapellaDiagramConstants.PCB_DIAGRAM_NAME;
import static org.polarsys.capella.core.sirius.elk.CapellaDiagramConstants.PDFB_DIAGRAM_NAME;
import static org.polarsys.capella.core.sirius.elk.CapellaDiagramConstants.PD_DIAGRAM_NAME;
import static org.polarsys.capella.core.sirius.elk.CapellaDiagramConstants.PFB_DIAGRAM_NAME;
import static org.polarsys.capella.core.sirius.elk.CapellaDiagramConstants.PPD_DIAGRAM_NAME;
import static org.polarsys.capella.core.sirius.elk.CapellaDiagramConstants.SAB_DIAGRAM_NAME;
import static org.polarsys.capella.core.sirius.elk.CapellaDiagramConstants.SDFB_DIAGRAM_NAME;
import static org.polarsys.capella.core.sirius.elk.CapellaDiagramConstants.SFBD_DIAGRAM_NAME;

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
            LDFB_DIAGRAM_NAME, OAIB_DIAGRAM_NAME, PAB_DIAGRAM_NAME
    );
    
    /** List of diagrams using Layered algorithm. */
    private static final List<String> LAYERED_DIAGRAM_DESCRIPTION_NAMES = List.of(
            // Not classified diagrams
            PD_DIAGRAM_NAME, // Not tested (no such diagram in IFE sample)
            // The Capella Architecture diagram is not tested. 
            // It is not really used in Capella as the precondition is "false".
            CA_DIAGRAM_NAME,

            CDI_DIAGRAM_NAME, // OK tested on "[CDI] Streaming Server"
            CCEI_DIAGRAM_NAME, // Not tested (no such diagram in IFE sample)
            CCII_DIAGRAM_NAME, // Not tested (no such diagram in IFE sample)
            ID_DIAGRAM_NAME, // Not tested (no such diagram in IFE sample)

            // Mode and States diagrams
            MASM_DIAGRAM_NAME, // KO tested on "[M&S] IFE Operating Modes"
            // Done 1- "Initial" must have a specific size idem for "Final"
            // Done 2- Improvement (initial in first)
            // 3- Improvement : Maybe a top down layout is better
            MSM_DIAGRAM_NAME, // KO tested on a new diagram in IFE (same root as above diagram)
            // 1- "Initial" must have a specific size idem for "Final", "Choice"
            // 2- Wrong location for region (y=21)
            // 3- Improvement (initial in first)

            // Capabilities diagrams
            CCRI_DIAGRAM_NAME, // Not tested (no such diagram in IFE sample)
            CRB_DIAGRAM_NAME, // Not tested (no such diagram in IFE sample)
            MB_DIAGRAM_NAME, // Not tested (no such diagram in IFE sample)
            CC_DIAGRAM_NAME, // KO tested on "[CC] Provide Video Entertainment
                                                                    // Services"
            // 1- Specific size for "Mission", "Capability" and "Actor"
            // 2- Improvement : Top down layout is better
            MCB_DIAGRAM_NAME, // KO tested on "[MCB] All Missions and Capabilities"
            // 1- Specific size for "Mission", "Capability" and "Actor"
            CRR_DIAGRAM_NAME, // Not tested (no such diagram in IFE sample)
            OCB_DIAGRAM_NAME, // KO tested on "[OCB] Operational Capabilities"
            // 1- Specific size for "Entity", "Capability" and "Actor"
            // 2- Improvement : Same size for all actors
            COC_DIAGRAM_NAME, // Not tested (no such diagram in IFE sample)

            // Data flow blank diagrams
            OAIB_DIAGRAM_NAME, // tested
            SDFB_DIAGRAM_NAME, // tested
            LDFB_DIAGRAM_NAME, // tested
            PDFB_DIAGRAM_NAME, // tested
            IDB_DIAGRAM_NAME, // Not tested (no such diagram in IFE sample)
            OEBLANK_DIAGRAM_NAME, // Not tested (no such diagram in IFE sample)

            // Architecture blank diagrams
            SAB_DIAGRAM_NAME, // tested
            AB_DIAGRAM_NAME, // KO (tested on "[EAB] Configuration Items and Realized
                                                                    // Artefacts" of IFE sample)
            // 1- specific size for "PhysicalLinInCI mapping"
            // 2- Wrong container size according to its label (if title on 2 lines)
            LAB_DIAGRAM_NAME, // OK (tested on IFE sample 
                                // "[LAB] [BUILD] All Components, Functions, CEs, FEs")
            PAB_DIAGRAM_NAME, // tested
            ORB_DIAGRAM_NAME // Not tested (no such diagram in IFE sample)
    );

    /** List of diagrams using Mr Tree algorithm. */
    private static final List<String> MR_TREE_DIAGRAM_DESCRIPTION_NAMES = List.of(
            // Breakdown diagrams
            SFBD_DIAGRAM_NAME, // KO wrong children order
            CM_DIAGRAM_NAME, // KO (tested on "[CM] Provide Entertainment Solutions" of IFE sample)
            // 1- The space between children does not consider label outside of the node
            FCD_DIAGRAM_NAME, // KO (tested on "[LFCD] Broadcast Audio Announcement" of IFE sample)
            // 1- wrong location of edge's labels
            // 2- space for label on edge is too small
            CIB_DIAGRAM_NAME, // Not tested (no such diagram in IFE sample)
            LCB_DIAGRAM_NAME, // KO (tested on "[LCBD] Architecture Drivers" of IFE sample)
            // 1 - wrong children order
            LFB_DIAGRAM_NAME, // KO (tested on "[LFBD] All Functions" of IFE sample)
                                                                     // wrong children order
            OEBREAKDOWN_DIAGRAM_NAME, // KO wrong children order
            OAB_DIAGRAM_NAME, // KO wrong children order
            PFB_DIAGRAM_NAME, // KO wrong children order and strange result on
                                                                     // "[PFBD] All Physical Functions"
            PCB_DIAGRAM_NAME, // KO (tested on "[PCBD] Behavioural Components")
            OPD_DIAGRAM_NAME // KO
            // 1- wrong location of edge's labels
            // 2- space for label on edge is too small
            // 3- Maybe a layered diagram desc would be better
    );

    /** List of diagrams using Layered algorithm. */
    private static final List<String> DOT_DIAGRAM_DESCRIPTION_NAMES = List.of(
            // Classes
            CDB_DIAGRAM_NAME, // OK (tested on "[CDB] In-Flight Entertainment Dictionary")
            CSA_DIAGRAM_NAME, // See with a "star diagram"
            PPD_DIAGRAM_NAME // See with a "star diagram" and why there is not the same
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
        return res.getURI().toString().contains(CapellaDiagramConstants.DESIGN_PATH);
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
