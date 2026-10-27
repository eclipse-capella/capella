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
import static org.polarsys.capella.core.sirius.analysis.IDiagramNameConstants.CONFIGURATION_ITEMS_BREAKDOWN_DIAGRAM_NAME;
import static org.polarsys.capella.core.sirius.analysis.IDiagramNameConstants.CONTEXTUAL_CAPABILITY_DIAGRAM_NAME;
import static org.polarsys.capella.core.sirius.analysis.IDiagramNameConstants.CONTEXTUAL_MISSION_DIAGRAM_NAME;
import static org.polarsys.capella.core.sirius.analysis.IDiagramNameConstants.FUNCTIONAL_CHAIN_DIAGRAM_NAME;
import static org.polarsys.capella.core.sirius.analysis.IDiagramNameConstants.LOGICAL_ARCHITECTURE_BLANK_DIAGRAM_NAME;
import static org.polarsys.capella.core.sirius.analysis.IDiagramNameConstants.LOGICAL_COMPONENT_BREAKDOWN_DIAGRAM_NAME;
import static org.polarsys.capella.core.sirius.analysis.IDiagramNameConstants.LOGICAL_DATA_FLOW_BLANK_DIAGRAM_NAME;
import static org.polarsys.capella.core.sirius.analysis.IDiagramNameConstants.LOGICAL_FUNCTION_BREAKDOWN_DIAGRAM_NAME;
import static org.polarsys.capella.core.sirius.analysis.IDiagramNameConstants.MISSIONS_CAPABILITIES_BLANK_DIAGRAM_NAME;
import static org.polarsys.capella.core.sirius.analysis.IDiagramNameConstants.MODES_AND_STATES_DIAGRAM_NAME;
import static org.polarsys.capella.core.sirius.analysis.IDiagramNameConstants.MODE_STATE_DIAGRAM_NAME;
import static org.polarsys.capella.core.sirius.analysis.IDiagramNameConstants.OPERATIONAL_ACTIVITY_BREAKDOWN_DIAGRAM_NAME;
import static org.polarsys.capella.core.sirius.analysis.IDiagramNameConstants.OPERATIONAL_ACTIVITY_INTERACTION_BLANK_DIAGRAM_NAME;
import static org.polarsys.capella.core.sirius.analysis.IDiagramNameConstants.OPERATIONAL_CAPABILITIES_ENTITYIES_BLANK_DIAGRAM_NAME;
import static org.polarsys.capella.core.sirius.analysis.IDiagramNameConstants.OPERATIONAL_ENTITY_BLANK_DIAGRAM_NAME;
import static org.polarsys.capella.core.sirius.analysis.IDiagramNameConstants.OPERATIONAL_ENTITY_BREAKDOWN_DIAGRAM_NAME;
import static org.polarsys.capella.core.sirius.analysis.IDiagramNameConstants.PHYSICAL_ARCHITECTURE_BLANK_DIAGRAM_NAME;
import static org.polarsys.capella.core.sirius.analysis.IDiagramNameConstants.PHYSICAL_COMPONENT_BREAKDOWN_DIAGRAM_NAME;
import static org.polarsys.capella.core.sirius.analysis.IDiagramNameConstants.PHYSICAL_DATA_FLOW_BLANK_DIAGRAM_NAME;
import static org.polarsys.capella.core.sirius.analysis.IDiagramNameConstants.PHYSICAL_FUNCTION_BREAKDOWN_DIAGRAM_NAME;
import static org.polarsys.capella.core.sirius.analysis.IDiagramNameConstants.SYSTEM_ARCHITECTURE_BLANK_DIAGRAM_NAME;
import static org.polarsys.capella.core.sirius.analysis.IDiagramNameConstants.SYSTEM_DATA_FLOW_BLANK_DIAGRAM_NAME;
import static org.polarsys.capella.core.sirius.analysis.IDiagramNameConstants.SYSTEM_FUNCTION_BREAKDOWN_DIAGRAM_NAME;
import static org.polarsys.capella.core.sirius.analysis.IMappingNameConstants.MSM_PSEUDOSTATE_MAPPING_NAME;
import static org.polarsys.capella.core.sirius.analysis.IMappingNameConstants.MS_INNER_PSEUDOSTATE_MAPPING_NAME;
import static org.polarsys.capella.core.sirius.analysis.IMappingNameConstants.MS_PSEUDOSTATE_MAPPING_NAME;

import java.util.EnumSet;
import java.util.List;
import java.util.Map;
import java.util.function.Predicate;

import org.eclipse.elk.alg.layered.options.LayerConstraint;
import org.eclipse.elk.alg.layered.options.LayeredOptions;
import org.eclipse.elk.core.math.ElkMargin;
import org.eclipse.elk.core.options.CoreOptions;
import org.eclipse.elk.core.options.Direction;
import org.eclipse.elk.core.options.NodeLabelPlacement;
import org.eclipse.elk.core.options.PortSide;
import org.eclipse.elk.core.options.SizeConstraint;
import org.eclipse.elk.graph.ElkNode;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.sirius.diagram.DNode;
import org.polarsys.capella.core.data.capellacommon.FinalState;
import org.polarsys.capella.core.data.capellacommon.InitialPseudoState;
import org.polarsys.capella.core.data.ctx.Mission;
import org.polarsys.capella.core.data.ctx.SystemComponent;
import org.polarsys.capella.core.data.fa.AbstractFunction;
import org.polarsys.capella.core.data.fa.ComponentPort;
import org.polarsys.capella.core.data.fa.FunctionInputPort;
import org.polarsys.capella.core.data.fa.FunctionKind;
import org.polarsys.capella.core.data.fa.FunctionOutputPort;
import org.polarsys.capella.core.data.fa.FunctionalChainInvolvement;
import org.polarsys.capella.core.data.fa.OrientationPortKind;
import org.polarsys.capella.core.data.information.PortAllocation;
import org.polarsys.capella.core.data.interaction.AbstractCapability;

/**
 * Customization of ELK configuration for Capella diagrams.
 * 
 * @author Laurent Redor
 */
public class CapellaElkLayoutExtension extends AbstractElkLayoutExtension {
    
    private static final EnumSet<NodeLabelPlacement> OUTSIDE_EAST = EnumSet.of(NodeLabelPlacement.OUTSIDE,
            NodeLabelPlacement.V_CENTER, NodeLabelPlacement.H_RIGHT);

    
    /**
     * The first port on the western and eastern side is already 13 pixels away from the northern border (by the
     * {@link CoreOptions#SPACING_PORT_PORT} option set to 13 pixels (in
     * {@link CapellaSiriusLayoutProvider#createLayeredConfiguration()}). So to have 20 at the top only 7 is needed, 8
     * in reality (I don't understand precisely why).
     */
    private static final ElkMargin DEFAULT_PORTS_MARGIN = new ElkMargin(8, 0, 0, 0);

    // Fix the size for all intermediate states
    private static final List<String> MASM_MAPPINGS = List.of(
            MS_PSEUDOSTATE_MAPPING_NAME,
            MS_INNER_PSEUDOSTATE_MAPPING_NAME,
            MSM_PSEUDOSTATE_MAPPING_NAME
            );

    private static final List<String> STATES_DIAGRAMS = List.of(
            MODES_AND_STATES_DIAGRAM_NAME,
            MODE_STATE_DIAGRAM_NAME
            );
    
    private static final List<String> BLANK_DIAGRAMS_CONTAINING_FUNCTIONS = List.of(
            // Kind is ignored for OA.
            OPERATIONAL_ACTIVITY_INTERACTION_BLANK_DIAGRAM_NAME,
            OPERATIONAL_ENTITY_BLANK_DIAGRAM_NAME,
            SYSTEM_ARCHITECTURE_BLANK_DIAGRAM_NAME,
            SYSTEM_DATA_FLOW_BLANK_DIAGRAM_NAME,
            LOGICAL_ARCHITECTURE_BLANK_DIAGRAM_NAME,
            LOGICAL_DATA_FLOW_BLANK_DIAGRAM_NAME,
            PHYSICAL_ARCHITECTURE_BLANK_DIAGRAM_NAME,
            PHYSICAL_DATA_FLOW_BLANK_DIAGRAM_NAME,
            // 
            FUNCTIONAL_CHAIN_DIAGRAM_NAME
            );
    
    private static final List<String> CAPABILITIES_DIAGRAMS = List.of(
            // Kind is ignored for OA.
            OPERATIONAL_CAPABILITIES_ENTITYIES_BLANK_DIAGRAM_NAME,
            CONTEXTUAL_MISSION_DIAGRAM_NAME, // reverse Mission involvement
            CAPABILITY_REALIZATION_BLANK,
            MISSIONS_CAPABILITIES_BLANK_DIAGRAM_NAME,
            CONTEXTUAL_CAPABILITY_DIAGRAM_NAME
            );

    private static final Map<String, String> BREAKDOWN_DIAGRAM_FEATURES = Map.of(
            // All containment relationships.
            SYSTEM_FUNCTION_BREAKDOWN_DIAGRAM_NAME, "SFB_SystemFunction_subFunctions", //$NON-NLS-1$
            CONFIGURATION_ITEMS_BREAKDOWN_DIAGRAM_NAME, "CIBD_ConfigurationItem_subComponents", //$NON-NLS-1$
            LOGICAL_COMPONENT_BREAKDOWN_DIAGRAM_NAME, "LCB_LogicalComponent_subComponents", //$NON-NLS-1$
            LOGICAL_FUNCTION_BREAKDOWN_DIAGRAM_NAME, "LFB_LogicalFunction_subFunctions", //$NON-NLS-1$
            OPERATIONAL_ACTIVITY_BREAKDOWN_DIAGRAM_NAME, "OAB_OperationalActivity_subFunctions", //$NON-NLS-1$
            OPERATIONAL_ENTITY_BREAKDOWN_DIAGRAM_NAME, "containedIn Mapping", //$NON-NLS-1$
            PHYSICAL_COMPONENT_BREAKDOWN_DIAGRAM_NAME, "PCB_PhysicalComponent_subComponents", //$NON-NLS-1$
            PHYSICAL_FUNCTION_BREAKDOWN_DIAGRAM_NAME, "PFB_PhysicalFunction_subFunctions" //$NON-NLS-1$
            );

    
    private static final RuleSet CAPELLA_RULES = new RuleSet();
    static {
        // State diagrams
        CAPELLA_RULES.addNodeRule(applyOn(STATES_DIAGRAMS), (node, ext) -> {
            var target = ext.getSemanticElement(node);
            if (target instanceof InitialPseudoState) {
                node.setProperty(LayeredOptions.LAYERING_LAYER_CONSTRAINT, LayerConstraint.FIRST);
            } else if (target instanceof FinalState) {
                node.setProperty(LayeredOptions.LAYERING_LAYER_CONSTRAINT, LayerConstraint.LAST);
            } else if (ext.containsMappingName(node, MASM_MAPPINGS)) {
                node.setProperty(CoreOptions.NODE_SIZE_CONSTRAINTS, SizeConstraint.fixed());
            }
        });

        
        // Diagrams with functions
        CAPELLA_RULES.addNodeRule(applyOn(BLANK_DIAGRAMS_CONTAINING_FUNCTIONS), (node, ext) -> {
            
            // Hint for improvement: 
            // To align all FunctionalChainEnd, they could be grouped in virtual node.
            
            var mapping = ext.getElementMapping(node);
            if (mapping != null && mapping.getName().endsWith("_FunctionalChainEnd")) { //$NON-NLS-1$
                node.setProperty(CoreOptions.NODE_SIZE_CONSTRAINTS, SizeConstraint.fixed());
                node.setWidth(20); // As defined in *.odesign
                node.setHeight(20);
                if (!node.getLabels().isEmpty()) {
                    node.getLabels().get(0).setProperty(CoreOptions.NODE_LABELS_PLACEMENT, OUTSIDE_EAST);
                }
            }
            
            EObject target = ext.getSemanticElement(node);
            if (isSpecialKindFunction(target)) {
                // inspired from sizeComputationExpression
                setMinimumSize(node, 40, 40);
            }
        });
        
        CAPELLA_RULES.addNodeRule(applyOn(CAPABILITIES_DIAGRAMS), (node, ext) -> {
            if (ext.getDiagramElement(node) instanceof DNode) {
                var semantic = ext.getSemanticElement(node);
                if (semantic instanceof AbstractCapability
                        || semantic instanceof SystemComponent
                        || semantic instanceof Mission)
                    setMinimumSize(node, 70, 50);
            }
        });
        
        BREAKDOWN_DIAGRAM_FEATURES.forEach((diagram, treeMapping) -> {
            CAPELLA_RULES.addEdgeRule(ext -> diagram.equals(ext.getDiagramDescrName()), (edge, ext) -> {
                var edgeMapping = ext.getElementMapping(edge).getName();
                if (treeMapping.equals(edgeMapping)) {
                    ext.flipEdge(edge);
                }
            });
        });

        // Force Side of Ports in layered Diagram.
        Map.of( // Rules: Axis, incoming, outgoing
                (Predicate<Direction>) Direction::isHorizontal, List.of(PortSide.WEST, PortSide.EAST),
                (Predicate<Direction>) Direction::isVertical, List.of(PortSide.NORTH, PortSide.SOUTH))
        .forEach((axis, sides) -> {
            Predicate<AbstractElkLayoutExtension> diagramAxis = ext -> {
                var dir = ext.getLayoutMapping().getLayoutGraph().getProperty(CoreOptions.DIRECTION);
                return axis.test(dir);
            };
            
            CAPELLA_RULES.addPortRule(
                    applyOn(BLANK_DIAGRAMS_CONTAINING_FUNCTIONS).and(diagramAxis),
                    (port, ext) -> {
                        int side = getPortSide(ext.getSemanticElement(port));
                        if (side != 0) {
                            forceSide(port, sides.get(side < 0 ? 0 : 1));
                        }
                    });
        });
        CAPELLA_RULES.addEdgeRule(applyOn(BLANK_DIAGRAMS_CONTAINING_FUNCTIONS), (edge, ext) -> {
            // XXX: This rule may not matter as sides are forced for 
            if (ext.getSemanticElement(edge) instanceof PortAllocation allocation
                    && shouldReverseAllocationEdge(allocation)) {
                ext.flipEdge(edge);
            }
        });
    }
        
    public CapellaElkLayoutExtension() {
        super(CAPELLA_RULES);
    }

    @Override
    public boolean isDiagramApplicable() {
        return !CapellaGmfLayoutProvider.getDiagramConfigurations(getDiagramDescription()).isEmpty();
    }
    
    @Override
    protected void addGlobalConfiguration(ElkNode root) {
        // Complex properties is not supported by configuration.
        root.setProperty(CoreOptions.SPACING_PORTS_SURROUNDING, DEFAULT_PORTS_MARGIN.clone());
        // Documentation not used yet but that could be in the future
        // 1- Property "org.eclipse.elk.layered.priority.direction": Option to add on some edges if necessary: 
        // Defines how important it is to have a certain edge point into the direction of the overall layout. 
        // This option is evaluated during the cycle breaking phase.
        // elkEdge.getProperties().put(LayeredOptions.PRIORITY_DIRECTION, 1);
    }

    static boolean isSpecialKindFunction(EObject target) {
        if (target instanceof FunctionalChainInvolvement involvement
                && involvement.getInvolvedElement() instanceof AbstractFunction involvedElement) {
            target = involvedElement;
        }
        return target instanceof AbstractFunction function && function.getKind() != FunctionKind.FUNCTION;
    }

    static boolean shouldReverseAllocationEdge(PortAllocation allocation) {
        int sourceSide = getPortSide(allocation.getSourceElement()); // Component Port
        int targetSide = getPortSide(allocation.getTargetElement()); // Function Port
        // CP FP Should Reverse
        // -------------
        // -1 -1 no
        // +1 -1 (unaligned) no
        // -1 +1 (unaligned) no
        // +1 +1 yes
        return  0 < targetSide // inner port => should be before
                && 0 < sourceSide; // outer port => should be after
    }

    static int getPortSide(EObject target) {
        int side = 0; // no special side
        if (target instanceof FunctionInputPort) {
            side = -1;
        } else if (target instanceof FunctionOutputPort) {
            side = 1;
        } else if (target instanceof ComponentPort componentPort) {
            if (componentPort.getOrientation() == OrientationPortKind.IN) {
                side = -1;
            } else if (componentPort.getOrientation() == OrientationPortKind.OUT) {
                side = 1;
            }
        }
        return side;
    }

}
