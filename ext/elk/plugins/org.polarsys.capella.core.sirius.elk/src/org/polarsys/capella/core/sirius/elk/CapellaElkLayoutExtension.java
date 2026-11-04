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
import static org.polarsys.capella.core.sirius.analysis.IMappingNameConstants.PAB_COMPONENT_PORT_MAPPING_NAME;
import static org.polarsys.capella.core.sirius.analysis.IMappingNameConstants.PAB_FUNCTION_PORT_ALLOCATION_MAPPING_NAME;
import static org.polarsys.capella.core.sirius.analysis.IMappingNameConstants.PAB_FUNCTION_PORT_MAPPING_NAME;

import java.util.ArrayList;
import java.util.EnumSet;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.function.BiConsumer;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import org.eclipse.draw2d.geometry.Point;
import org.eclipse.draw2d.geometry.PrecisionRectangle;
import org.eclipse.draw2d.geometry.Rectangle;
import org.eclipse.elk.alg.layered.options.LayerConstraint;
import org.eclipse.elk.alg.layered.options.LayeredOptions;
import org.eclipse.elk.core.math.ElkMargin;
import org.eclipse.elk.core.math.KVector;
import org.eclipse.elk.core.options.CoreOptions;
import org.eclipse.elk.core.options.NodeLabelPlacement;
import org.eclipse.elk.core.options.PortConstraints;
import org.eclipse.elk.core.options.PortSide;
import org.eclipse.elk.core.options.SizeConstraint;
import org.eclipse.elk.core.service.LayoutMapping;
import org.eclipse.elk.core.util.ElkUtil;
import org.eclipse.elk.graph.ElkConnectableShape;
import org.eclipse.elk.graph.ElkEdge;
import org.eclipse.elk.graph.ElkEdgeSection;
import org.eclipse.elk.graph.ElkGraphElement;
import org.eclipse.elk.graph.ElkNode;
import org.eclipse.elk.graph.ElkPort;
import org.eclipse.elk.graph.util.ElkGraphUtil;
import org.eclipse.emf.common.util.ECollections;
import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.util.EcoreUtil;
import org.eclipse.gmf.runtime.diagram.ui.editparts.DiagramEditPart;
import org.eclipse.gmf.runtime.diagram.ui.editparts.IGraphicalEditPart;
import org.eclipse.sirius.diagram.DDiagram;
import org.eclipse.sirius.diagram.DDiagramElement;
import org.eclipse.sirius.diagram.DNode;
import org.eclipse.sirius.diagram.description.DiagramDescription;
import org.eclipse.sirius.diagram.description.DiagramElementMapping;
import org.eclipse.sirius.diagram.elk.ElkDiagramLayoutConnector;
import org.eclipse.sirius.diagram.elk.GmfLayoutCommand;
import org.eclipse.sirius.diagram.elk.IELKLayoutExtension;
import org.eclipse.sirius.diagram.ui.edit.api.part.IAbstractDiagramNodeEditPart;
import org.eclipse.sirius.diagram.ui.edit.api.part.IDiagramEdgeEditPart;
import org.eclipse.sirius.ext.gmf.runtime.editparts.GraphicalHelper;
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
import org.polarsys.capella.core.data.interaction.AbstractCapability;

/**
 * A sample implementation of {@link IELKLayoutExtension} that removes from the layout graph edges between two ports on
 * the same container.
 * 
 * @author Laurent Redor
 */
public class CapellaElkLayoutExtension implements IELKLayoutExtension {
    
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
    
    private static final List<String> FUNCTIONS_INCLUDED_BLANK_DIAGRAMS = List.of(
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
    

    private record EdgeDescription(
            ElkConnectableShape source, 
            ElkConnectableShape target, 
            ElkNode  container, 
            Object part) {

        EdgeDescription(ElkEdge element, Object mappedPart) {
            this(element.getSources().get(0),
                    element.getTargets().get(0),
                    element.getContainingNode(),
                    mappedPart);
        }
    }

    /** Edges reversed before layout. */
    protected List<ElkEdge> reversedEdges = new ArrayList<>();;

    private Map<ElkEdge, EdgeDescription> extractedEdges = new HashMap<>();

    private LayoutMapping layoutMapping;

    @Override
    public void beforeELKLayout(LayoutMapping aLayoutMapping) {
        DiagramDescription diagramDescription = getDiagramDescription(aLayoutMapping);
        if (CapellaGmfLayoutProvider.getDiagramConfigurations(diagramDescription).isEmpty()) {
            // Not part of Capella ELK
            return;
        }
        this.layoutMapping = aLayoutMapping;
        layoutMapping.getLayoutGraph().setProperty(CoreOptions.SPACING_PORTS_SURROUNDING, DEFAULT_PORTS_MARGIN.clone());
        // Documentation not used yet but that could be in the future
        // 1- Property "org.eclipse.elk.layered.priority.direction": Option to add on some edges if necessary: 
        // Defines how important it is to have a certain edge point into the direction of the overall layout. 
        // This option is evaluated during the cycle breaking phase.
        // elkEdge.getProperties().put(LayeredOptions.PRIORITY_DIRECTION, 1);

        extractCircularEdges();

        if (PHYSICAL_ARCHITECTURE_BLANK_DIAGRAM_NAME.equals(diagramDescription.getName())) {
            beforePabLayout();
        } else if (STATES_DIAGRAMS.contains(diagramDescription.getName())) {
            beforeStateLayout();
        }
        beforeFunctionLayout(diagramDescription);
        beforeCapabilitiesLayout(diagramDescription);
        beforeBreakdown(diagramDescription);
    }
    

    private void beforeFunctionLayout(DiagramDescription diagramDescription) {
        if (!FUNCTIONS_INCLUDED_BLANK_DIAGRAMS.contains(diagramDescription.getName())) {
            return;
        }
        
        // XXX: To align all FunctionalChainEnd, they could be grouped in virtual node.
        
        // Fix the size for FunctionalChainEnd and set the label location to the east side as creation.
        streamAllNodes(layoutMapping.getLayoutGraph(), node -> {
            var mapping = getElementMapping(node);
            return mapping != null && mapping.getName().endsWith("_FunctionalChainEnd"); //$NON-NLS-1$
        }).forEach(node -> {
            node.setProperty(CoreOptions.NODE_SIZE_CONSTRAINTS, SizeConstraint.fixed());
            node.setWidth(20); // As defined in *.odesign
            node.setHeight(20);
            if (!node.getLabels().isEmpty()) {
                node.getLabels().get(0).setProperty(CoreOptions.NODE_LABELS_PLACEMENT, OUTSIDE_EAST);
            }
        });
        
        // Set minimum size for special functions.
        streamAllNodes(layoutMapping.getLayoutGraph(), this::isSpecialKindFunction)
        .forEach(node-> {
            // inspired from sizeComputationExpression
            node.setProperty(CoreOptions.NODE_SIZE_MINIMUM, new KVector(40, 40));                
        });
    }
    
    private void beforeCapabilitiesLayout(DiagramDescription diagramDescription) {
        if (!CAPABILITIES_DIAGRAMS.contains(diagramDescription.getName())) {
            return;
        }

        streamAllNodes(layoutMapping.getLayoutGraph(), it -> getDiagramElement(it) instanceof DNode)
        .forEach(node-> {
            var semanctic = getSemanticElement(node);
            if (semanctic instanceof AbstractCapability
                    || semanctic instanceof SystemComponent
                    || semanctic instanceof Mission)
                node.setProperty(CoreOptions.NODE_SIZE_MINIMUM, new KVector(70, 50));
        });        
    }
    
    private void beforeBreakdown(DiagramDescription diagramDescription) {
        String treeMapping = BREAKDOWN_DIAGRAM_FEATURES.get(diagramDescription.getName());
        if (treeMapping != null) {
            streamAllEdges(layoutMapping.getLayoutGraph(), 
                    edge -> treeMapping.equals(getElementMapping(edge).getName()))
            .forEach(this::flipEdge);
        }
    }
    
    private void beforePabLayout() {

        
        /** Edges reversed before layout */        
        // Reverse direction of "Port Allocation" edges
        List<ElkEdge> edges = getEdgesWithMappingName(layoutMapping.getLayoutGraph(), List.of(PAB_FUNCTION_PORT_ALLOCATION_MAPPING_NAME));
        edges.stream()
                // Filter invalid edges
                .filter(edge -> !edge.getTargets().isEmpty() && !edge.isHyperedge())
                // TODO : Is a check of Port Allocation kind is necessary ?
                .forEach(this::flipEdge);

        // Set fixed side for Input Port, Output Port and Component Port
        List<ElkPort> fctPorts = getPortsWithMappingName(layoutMapping.getLayoutGraph(), List.of(PAB_FUNCTION_PORT_MAPPING_NAME));
        fctPorts.forEach(port -> {
            DDiagramElement dde = getDiagramElement(port);
            if (dde.getTarget() instanceof FunctionInputPort) {
                forceSide(port, PortSide.WEST);
            } else if (dde.getTarget() instanceof FunctionOutputPort) {
                forceSide(port, PortSide.EAST);
            } 
            // else { System.out.println("**** CASE NOT HANDLED ****"); //$NON-NLS-1$ }
        });
        List<ElkPort> ports = getPortsWithMappingName(layoutMapping.getLayoutGraph(), List.of(PAB_COMPONENT_PORT_MAPPING_NAME));
        ports.forEach(port -> {
            DDiagramElement dde = getDiagramElement(port);
            if (dde.getTarget() instanceof ComponentPort componentPort) {
                if (componentPort.getOrientation() == OrientationPortKind.IN) {
                    forceSide(port, PortSide.WEST);
                } else if (componentPort.getOrientation() == OrientationPortKind.OUT) {
                    forceSide(port, PortSide.EAST);
                }
            }
        });
    }
    
    private boolean isSpecialKindFunction(ElkGraphElement elkElement) {
        var target = getSemanticElement(elkElement);
        if (target instanceof FunctionalChainInvolvement involvement
                && involvement.getInvolvedElement() instanceof AbstractFunction involvedElement) {
            target = involvedElement;
        }
        return target instanceof AbstractFunction function && function.getKind() != FunctionKind.FUNCTION ;
    }

    
    private static void forceSide(ElkPort port, PortSide side) {
        port.setProperty(CoreOptions.PORT_SIDE, side);
        port.getParent().setProperty(CoreOptions.PORT_CONSTRAINTS, PortConstraints.FIXED_SIDE);
    }
    
    private void beforeStateLayout() {
        List<ElkNode> nodes = getNodesWithMappingName(layoutMapping.getLayoutGraph(), MASM_MAPPINGS);
        nodes.forEach(node -> node.setProperty(CoreOptions.NODE_SIZE_CONSTRAINTS, SizeConstraint.fixed()));
        
        // Force the initial state to be in first and the final state to be in last
        List<ElkNode> rootNodes = nodes.stream()
                .filter(node -> node.getParent() == layoutMapping.getLayoutGraph() && node.getLabels().size() == 1)
                .collect(Collectors.toList());
        
        applyLayerEndConstraint(rootNodes, InitialPseudoState.class, LayerConstraint.FIRST);
        applyLayerEndConstraint(rootNodes, FinalState.class, LayerConstraint.LAST);
    }

    /**
     * This method inverses the sources and the targets of the edge.
     * <p>
     * The sources and targets coordinates are also inverted (and the sections if any).
     * </p>
     *
     * @param elkEdge
     *            The edge to reverse.
     * @throws IllegalArgumentException
     *             In case of the <code>elkEdge</code> is an hyperedge.
     */
    protected void flipEdge(ElkEdge elkEdge) throws IllegalArgumentException {
        if (elkEdge.isHyperedge()) {
            throw new IllegalArgumentException("The method reverseEdge does not handle \"hyperedge\"."); //$NON-NLS-1$
        }
        if (!reversedEdges.contains(elkEdge)) {
            changeEdgeOrientation(elkEdge);
            reversedEdges.add(elkEdge);
        }
    }
    
    private void changeEdgeOrientation(ElkEdge elkEdge) {
        // Do not call directly before layout.
        // Use flipEdge instead.
        EList<ElkConnectableShape> oldSources = ECollections.newBasicEList(elkEdge.getSources());
        elkEdge.getSources().clear();
        elkEdge.getSources().addAll(elkEdge.getTargets());
        elkEdge.getTargets().clear();
        elkEdge.getTargets().addAll(oldSources);

        for (ElkEdgeSection section : elkEdge.getSections()) {
            double oldStartX = section.getStartX();
            double oldStartY = section.getStartY();
            section.setStartX(section.getEndX());
            section.setStartY(section.getEndY());
            section.setEndX(oldStartX);
            section.setEndY(oldStartY);

            ECollections.reverse(section.getBendPoints());
        }
    }

    private void applyLayerEndConstraint(List<ElkNode> nodes, Class<?> elementType, LayerConstraint value) {
        // End constraint should be unique.
        Optional<ElkNode> end = nodes.stream() // Matching type
                .filter(it -> isSemanticInstanceOf(it, elementType)) // Only 1rst is considered.
                .findFirst();
        if (end.isPresent()) {
            end.get().setProperty(LayeredOptions.LAYERING_LAYER_CONSTRAINT, value);
        }
    }
    
    private void extractCircularEdges() {
        // A-Remove edges between two border nodes on the same container.
        fetchCircularEdges(layoutMapping.getLayoutGraph())
            .collect(Collectors.toList()) // Cannot directly remove from a stream
            .forEach(this::removeEdge);
    }
    
    @Override
    public void afterELKLayout(LayoutMapping aLayoutMapping) {
        if (layoutMapping != aLayoutMapping) {
            return; // This extension is not handling the mapping.
        }
        
        // Restore reverse edge
        reversedEdges.forEach(it -> changeEdgeOrientation(it));
        reversedEdges.clear();

        extractedEdges.forEach((e, n) -> restoreEdge(e, n));
    }
    
    private void removeEdge(ElkEdge edge) {
        // We remove it from the map between ELK elements and EditParts (after conserving this data in a
        // local map).
        Object mappedPart = layoutMapping.getGraphMap().remove(edge);
        extractedEdges.put(edge, new EdgeDescription(edge, mappedPart));

        // And we also remove the edges from its parent and all references
        EcoreUtil.delete(edge);
    }
    
    private void restoreEdge(ElkEdge edge, EdgeDescription descr) {
        // Restore edge
        edge.getSources().add(descr.source);
        edge.getTargets().add(descr.target);
        descr.container.getContainedEdges().add(edge);
        
        // Compute ends
        KVector sourceKVector = getParentRelativePoint(descr.source, descr.container);
        KVector targetKVector = getParentRelativePoint(descr.target, descr.container);

        ElkEdgeSection section = edge.getSections().get(0);

        Rectangle sourceArea = toRectangle(sourceKVector, descr.source);
        Rectangle targetArea = toRectangle(targetKVector, descr.target);
        
        applyCoordinates(GraphicalHelper.getIntersection(sourceArea.getCenter(), targetArea.getCenter(), sourceArea, true), 
                sourceKVector, section::setStartLocation);
        
        applyCoordinates(GraphicalHelper.getIntersection(sourceArea.getCenter(), targetArea.getCenter(), targetArea, false), 
                targetKVector, section::setEndLocation);
        
        // Remove potential bendpoints to have a straight edge
        section.getBendPoints().clear();

        // Add the edge in the mapping to be considered in the next steps (apply ELK layout to Sirius)
        layoutMapping.getGraphMap().put(edge, descr.part);
    }

    private void applyCoordinates(Optional<Point> value, KVector byDefault, BiConsumer<Double, Double> coordinate) {
        if (value.isPresent()) {
            coordinate.accept(value.get().preciseX(), value.get().preciseY());
        } else {
            coordinate.accept(byDefault.x, byDefault.y);
        }
    }
    
    private static boolean isLinkBorderWithSameParent(ElkEdge edge) {
        ElkNode sourceParent = toPortParent(edge, false);
        ElkNode targetParent = toPortParent(edge, true);
        return sourceParent != null
                && sourceParent == targetParent;
    }
    
    private Stream<ElkEdge> fetchCircularEdges(ElkNode elkNode) {
        return streamAllEdges(elkNode, CapellaElkLayoutExtension::isLinkBorderWithSameParent);
    }

    @Override
    public void afterGMFCommandApplied(GmfLayoutCommand gmfLayoutCommand, LayoutMapping aLayoutMapping) {
        this.layoutMapping = null;
        // Nothing to do
    }
    
    // 
    // Generic methods
    // 
    
    private static KVector getParentRelativePoint(ElkConnectableShape it, ElkNode container) {
        KVector point = new KVector(it.getX(), it.getY());
        return ElkUtil.toRelative(
                ElkUtil.toAbsolute(point, container),
                ElkGraphUtil.connectableShapeToNode(it)
            );
    }

    private static PrecisionRectangle toRectangle(KVector point, ElkConnectableShape shape) {
        return new PrecisionRectangle(point.x, point.y, shape.getWidth(), shape.getHeight());
    }
    
    private static ElkNode toPortParent(ElkEdge it, boolean target) {
        if (it.getSources().size() != 1 || it.getTargets().size() != 1) {
            return null; // Not applicable
        }
        ElkPort port = target 
                ? ElkGraphUtil.getTargetPort(it) 
                : ElkGraphUtil.getSourcePort(it);
        return port != null ? port.getParent() : null;
    }
    
    /**
     * Return the {@link DiagramDescription} name of the {@link DDiagram} concerned by this {@link LayoutMapping}.
     * 
     * @param layoutMapping
     *            The ELK layout mapping
     * @return the {@link DiagramDescription} name of the {@link DDiagram} concerned by this {@link LayoutMapping}.
     */
    private DiagramDescription getDiagramDescription(LayoutMapping aLayoutMapping) {
        // Retrieve the root diagram editPart
        DiagramEditPart part = aLayoutMapping.getProperty(ElkDiagramLayoutConnector.DIAGRAM_EDIT_PART);
        // Retrieve the GMF diagram
        if (part.getDiagramView().getElement() instanceof DDiagram diagram) {
            return diagram.getDescription();
        }
        return null;
    }

    private boolean containsMappingName(ElkGraphElement element, List<String> mappingNames) {
        DiagramElementMapping mapping = getElementMapping(element);
        return mapping != null && mappingNames.contains(mapping.getName());
    }
    
    private List<ElkEdge> getEdgesWithMappingName(ElkNode elkNode, List<String> mappingNames) {
        return streamAllEdges(elkNode, edge -> containsMappingName(edge, mappingNames)).collect(Collectors.toList());
    }

    private List<ElkNode> getNodesWithMappingName(ElkNode elkNode, List<String> mappingNames) {
        return streamAllNodes(elkNode, port -> containsMappingName(port, mappingNames)).collect(Collectors.toList());
    }

    private List<ElkPort> getPortsWithMappingName(ElkNode elkPort, List<String> mappingNames) {
        return streamAllPorts(elkPort, port -> containsMappingName(port, mappingNames)).collect(Collectors.toList());
    }

    private DiagramElementMapping getElementMapping(ElkGraphElement elkElement) {
        DDiagramElement view = getDiagramElement(elkElement);
        return view != null ? view.getDiagramElementMapping() : null;
    }
    
    private DDiagramElement getDiagramElement(ElkGraphElement elkElement) {
        Object widget = layoutMapping.getGraphMap().get(elkElement);
        if (widget instanceof IGraphicalEditPart editPart 
                && isSiriusPart(editPart)
                && editPart.resolveSemanticElement() instanceof DDiagramElement element) {
            return element;
        }
        return null;
    }
    
    private EObject getSemanticElement(ElkGraphElement elkElement) {
        DDiagramElement diagramElement = getDiagramElement(elkElement);
        return diagramElement != null ? diagramElement.getTarget() : null ;
    }
    
    private static boolean isSiriusPart(IGraphicalEditPart editPart) {
        return editPart instanceof IAbstractDiagramNodeEditPart 
            || editPart instanceof IDiagramEdgeEditPart;
    }
        
    /**
     * Evaluates if an element target is an instance of semantic class.
     * <p>
     * I.e. corresponding to the EditPart corresponding to the ElkNode is of the
     * expected type.
     * </p>
     * 
     * @param node to evaluated
     * @param expectedType of element
     * @return true if match
     */
    private boolean isSemanticInstanceOf(ElkGraphElement elkElement, Class<?> expectedType) {
        return expectedType.isInstance(getSemanticElement(elkElement));
    }

    /**
     * Creates a stream of all ports in this node and inside it.
     * 
     * @param root the node to explore
     * @param filter the predicate to select port
     * @return a stream of port
     */
    public static Stream<ElkPort> streamAllPorts(ElkNode root, Predicate<? super ElkPort> filter) {
        return streamAllElements(root, ElkNode::getPorts, filter);
    }

    /**
     * Creates a stream of all edges in this node and inside it.
     * 
     * @param root the node to explore
     * @param filter the predicate to select edge
     * @return a stream of edge
     */
    public static Stream<ElkEdge> streamAllEdges(ElkNode root, Predicate<? super ElkEdge> filter) {
        return streamAllElements(root, ElkNode::getContainedEdges, filter);
    }
    
    /**
     * Creates a stream of all nodes in this node and inside it.
     * 
     * @param root the node to explore
     * @param filter the predicate to select node
     * @return a stream of node
     */
    public static Stream<ElkNode> streamAllNodes(ElkNode root, Predicate<? super ElkNode> filter) {
        return streamAllElements(root, ElkNode::getChildren, filter);
    }
    
    private static <T extends ElkGraphElement> Stream<T> streamAllElements(ElkNode root, Function<ElkNode, List<T>> property, Predicate<? super T> filter) {
        // We collect contained edges with source port and target port with the same parent.
        Stream<T> nodes = property.apply(root).stream().filter(filter);

        // We recursively collect them for each child
        Stream<T> childrenNodes = root.getChildren()
                .stream()
                .flatMap(child -> streamAllElements(child, property, filter));
        return Stream.concat(nodes, childrenNodes);
    }
}
