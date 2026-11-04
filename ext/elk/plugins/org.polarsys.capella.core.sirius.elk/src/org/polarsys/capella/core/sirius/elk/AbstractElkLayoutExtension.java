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

import java.util.ArrayList;
import java.util.Collection;
import java.util.EnumSet;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.function.BiConsumer;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.function.Supplier;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import org.eclipse.draw2d.geometry.Point;
import org.eclipse.draw2d.geometry.PrecisionRectangle;
import org.eclipse.draw2d.geometry.Rectangle;
import org.eclipse.elk.alg.layered.options.LayerConstraint;
import org.eclipse.elk.alg.layered.options.LayeredOptions;
import org.eclipse.elk.core.math.KVector;
import org.eclipse.elk.core.options.CoreOptions;
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
import org.eclipse.sirius.diagram.description.DiagramDescription;
import org.eclipse.sirius.diagram.description.DiagramElementMapping;
import org.eclipse.sirius.diagram.elk.ElkDiagramLayoutConnector;
import org.eclipse.sirius.diagram.elk.GmfLayoutCommand;
import org.eclipse.sirius.diagram.elk.IELKLayoutExtension;
import org.eclipse.sirius.diagram.ui.edit.api.part.IAbstractDiagramNodeEditPart;
import org.eclipse.sirius.diagram.ui.edit.api.part.IDiagramEdgeEditPart;
import org.eclipse.sirius.ext.gmf.runtime.editparts.GraphicalHelper;


/**
 * An abstract implementation of IELKLayoutExtension containing utilitary methods.
 * <p>
 * This class separates business configuration from ELK element manipulation. It contains no 
 * specific configuration.
 * </p>
 * @author Laurent Redor
 */
public class AbstractElkLayoutExtension implements IELKLayoutExtension {

    private record EdgeDescription(ElkConnectableShape source, ElkConnectableShape target, 
            ElkNode container, Object part) {

        EdgeDescription(ElkEdge element, Object mappedPart) {
            this(element.getSources().get(0), element.getTargets().get(0),
                    element.getContainingNode(), mappedPart);
        }
    }
    
    private record Rule<T extends ElkGraphElement>(Class<T> type, Predicate<AbstractElkLayoutExtension> applicable, 
            BiConsumer<T, AbstractElkLayoutExtension> update) {
        
        @SuppressWarnings("unchecked")
        public void applyRule(ElkGraphElement element, AbstractElkLayoutExtension extension) {
            update().accept((T) element, extension);
        }
        
    }
    
    /**
     * Set of layout rules to apply.
     */
    public static class RuleSet {
        
        private final List<Rule<?>> rules = new ArrayList<>();
        
        /**
         * Add a rule that applies to nodes.
         * 
         * @param applicable evaluate if the rule is applicable for laid diagram.
         * @param update way to update the element
         */
        public void addNodeRule(Predicate<AbstractElkLayoutExtension> applicable, 
                BiConsumer<ElkNode, AbstractElkLayoutExtension> update) {
            addRule(ElkNode.class, applicable, update);
        }
        
        /**
         * Add a rule that applies to port.
         * 
         * @param applicable evaluate if the rule is applicable for laid diagram.
         * @param update way to update the element
         */
        public void addPortRule(Predicate<AbstractElkLayoutExtension> applicable, 
                BiConsumer<ElkPort, AbstractElkLayoutExtension> update) {
            addRule(ElkPort.class, applicable, update);
        }
        
        /**
         * Add a rule that applies to edges.
         * 
         * @param applicable evaluate if the rule is applicable for laid diagram.
         * @param update way to update the element
         */
       public void addEdgeRule(Predicate<AbstractElkLayoutExtension> applicable, 
                BiConsumer<ElkEdge, AbstractElkLayoutExtension> update) {
            addRule(ElkEdge.class, applicable, update);
        }
        
       /**
        * Add a rule that applies to an graph element.
        * 
        * @param applicable evaluate if the rule is applicable for laid diagram.
        * @param update way to update the element
        */
        public <T extends ElkGraphElement> void addRule(Class<T> type, Predicate<AbstractElkLayoutExtension> applicable, 
                BiConsumer<T, AbstractElkLayoutExtension> update) {
            rules.add(new Rule<T>(type, applicable, update));
        }

    }

    protected static Predicate<AbstractElkLayoutExtension> applyOn(Collection<? extends String> diagramNames) {
        return layout -> diagramNames.contains(layout.diagramDescrName);
    }

    /** Edges reversed before layout. */
    protected List<ElkEdge> reversedEdges = new ArrayList<>();

    private Map<ElkEdge, EdgeDescription> extractedEdges = new HashMap<>();

    private LayoutMapping layoutMapping;

    protected DiagramDescription diagramDescription;

    protected String diagramDescrName;

    private final RuleSet ruleSet;
    
    protected AbstractElkLayoutExtension(RuleSet elkRules) {
        ruleSet = elkRules;
    }
    
    public String getDiagramDescrName() {
        return diagramDescrName;
    }
    
    public DiagramDescription getDiagramDescription() {
        return diagramDescription;
    }
    
    public LayoutMapping getLayoutMapping() {
        return layoutMapping;
    }
    
    @Override
    public void beforeELKLayout(LayoutMapping aLayoutMapping) {
        diagramDescription = getDiagramDescription(aLayoutMapping);
        diagramDescrName = diagramDescription.getName();

        this.layoutMapping = aLayoutMapping;
        ElkNode root = layoutMapping.getLayoutGraph();
        
        if (!isDiagramApplicable()) {
            return;
        }
        
        addGlobalConfiguration(root);
        
        extractCircularEdges();

        Map<Class<? extends ElkGraphElement>, List<Rule<?>>> ruleSets = new HashMap<>();
        // List.of(ElkNode.class, ElkPort.class, ElkEdge.class).forEach(type -> ruleSets.put(type, new ArrayList<>()));
        
        Map<Class<? extends ElkGraphElement>, Supplier<? extends Stream<?>>> tasks = Map.of(
                ElkNode.class, () -> streamAllNodes(root, it-> true),
                ElkPort.class, () -> streamAllPorts(root, it-> true),
                ElkEdge.class, () -> streamAllEdges(root, it-> true));
        
        tasks.keySet().forEach(type -> ruleSets.put(type, new ArrayList<>()));
        
        ruleSet.rules.stream()
            .filter(rule -> rule.applicable().test(this))
            .forEach(rule -> ruleSets.keySet()
                    .stream()
                    .filter(type -> rule.type().isAssignableFrom(type))
                    .forEach(type -> 
                ruleSets.get(type).add(rule)
            ));

        tasks.forEach((type, task) -> {
            List<Rule<?>> rules = ruleSets.get(type);
            task.get().forEach(element ->  
                rules.forEach(rule -> rule.applyRule((ElkGraphElement) element, this))
            );
        });
    }
    
    public boolean isDiagramApplicable() {
        return true;
    }
    
    protected void addGlobalConfiguration(ElkNode root) {
    }
    
    @Override
    public void afterELKLayout(LayoutMapping aLayoutMapping) {
        if (layoutMapping != aLayoutMapping) {
            return; // This extension is not handling the mapping.
        }
        
        // Restore reverse edge
        reversedEdges.forEach(this::changeEdgeOrientation);
        reversedEdges.clear();

        // Restore edges
        extractedEdges.forEach((e, n) -> restoreEdge(e, n));
    }

    @Override
    public void afterGMFCommandApplied(GmfLayoutCommand gmfLayoutCommand, LayoutMapping aLayoutMapping) {
        this.layoutMapping = null;
        // Nothing to do
    }

    static void setMinimumSize(ElkNode node, double width, double heigth) {
        KVector oldMin = node.getProperty(CoreOptions.NODE_SIZE_MINIMUM);
        KVector min = new KVector(Math.max(width, oldMin.x), Math.max(heigth, oldMin.y));
        node.setProperty(CoreOptions.NODE_SIZE_MINIMUM, min);
        
        EnumSet<SizeConstraint> contraints = node.getProperty(CoreOptions.NODE_SIZE_CONSTRAINTS);
        if (!contraints.contains(SizeConstraint.MINIMUM_SIZE)) {
            contraints = contraints.clone();
            contraints.clone().add(SizeConstraint.MINIMUM_SIZE);
            node.setProperty(CoreOptions.NODE_SIZE_CONSTRAINTS,  contraints);
        }
    }

    protected static void forceSide(ElkPort port, PortSide side) {
        port.setProperty(CoreOptions.PORT_SIDE, side);
        ElkNode parent = port.getParent();
        PortConstraints portConstraint = parent.getProperty(CoreOptions.PORT_CONSTRAINTS);
        if (portConstraint.compareTo(PortConstraints.FIXED_SIDE) < 0) {
            // PortConstraints is cumulative.
            parent.setProperty(CoreOptions.PORT_CONSTRAINTS, PortConstraints.FIXED_SIDE);
        }
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

    protected void applyLayerEndConstraint(List<ElkNode> nodes, Class<?> elementType, LayerConstraint value) {
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

    protected void removeEdge(ElkEdge edge) {
        if (!extractedEdges.containsKey(edge)) {
            // We remove it from the map between ELK elements and EditParts (after conserving this data in a
            // local map).
            Object mappedPart = layoutMapping.getGraphMap().remove(edge);
            extractedEdges.put(edge, new EdgeDescription(edge, mappedPart));
    
            // And we also remove the edges from its parent and all references
            EcoreUtil.delete(edge);
        }
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
        double x = byDefault.x;
        double y = byDefault.y;
        
        if (value.isPresent()) {
            x = value.get().preciseX();
            y = value.get().preciseY();
        }
        coordinate.accept(x, y);
    }
    
    private static boolean isLinkBorderWithSameParent(ElkEdge edge) {
        ElkNode sourceParent = toPortParent(edge, false);
        ElkNode targetParent = toPortParent(edge, true);
        return sourceParent != null
                && sourceParent == targetParent;
    }
    
    private Stream<ElkEdge> fetchCircularEdges(ElkNode elkNode) {
        return streamAllEdges(elkNode, AbstractElkLayoutExtension::isLinkBorderWithSameParent);
    }

    protected static KVector getParentRelativePoint(ElkConnectableShape it, ElkNode container) {
        KVector point = new KVector(it.getX(), it.getY());
        return ElkUtil.toRelative(
                ElkUtil.toAbsolute(point, container),
                ElkGraphUtil.connectableShapeToNode(it)
            );
    }

    protected static PrecisionRectangle toRectangle(KVector point, ElkConnectableShape shape) {
        return new PrecisionRectangle(point.x, point.y, shape.getWidth(), shape.getHeight());
    }
    
    protected static ElkNode toPortParent(ElkEdge it, boolean target) {
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
    protected DiagramDescription getDiagramDescription(LayoutMapping aLayoutMapping) {
        // Retrieve the root diagram editPart
        DiagramEditPart part = aLayoutMapping.getProperty(ElkDiagramLayoutConnector.DIAGRAM_EDIT_PART);
        // Retrieve the GMF diagram
        if (part.getDiagramView().getElement() instanceof DDiagram diagram) {
            return diagram.getDescription();
        }
        return null;
    }

    protected boolean containsMappingName(ElkGraphElement element, List<String> mappingNames) {
        DiagramElementMapping mapping = getElementMapping(element);
        return mapping != null && mappingNames.contains(mapping.getName());
    }
    
    protected List<ElkEdge> getEdgesWithMappingName(ElkNode elkNode, List<String> mappingNames) {
        return streamAllEdges(elkNode, edge -> containsMappingName(edge, mappingNames)).collect(Collectors.toList());
    }

    protected List<ElkNode> getNodesWithMappingName(ElkNode elkNode, List<String> mappingNames) {
        return streamAllNodes(elkNode, node -> containsMappingName(node, mappingNames)).collect(Collectors.toList());
    }

    protected List<ElkPort> getPortsWithMappingName(ElkNode elkNode, List<String> mappingNames) {
        return streamAllPorts(elkNode, port -> containsMappingName(port, mappingNames)).collect(Collectors.toList());
    }

    protected DiagramElementMapping getElementMapping(ElkGraphElement elkElement) {
        DDiagramElement view = getDiagramElement(elkElement);
        return view != null ? view.getDiagramElementMapping() : null;
    }
    
    protected DDiagramElement getDiagramElement(ElkGraphElement elkElement) {
        Object widget = layoutMapping.getGraphMap().get(elkElement);
        if (widget instanceof IGraphicalEditPart editPart 
                && isSiriusPart(editPart)
                && editPart.resolveSemanticElement() instanceof DDiagramElement element) {
            return element;
        }
        return null;
    }
    
    protected EObject getSemanticElement(ElkGraphElement elkElement) {
        DDiagramElement diagramElement = getDiagramElement(elkElement);
        return diagramElement != null ? diagramElement.getTarget() : null ;
    }
    
    protected static boolean isSiriusPart(IGraphicalEditPart editPart) {
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
    protected boolean isSemanticInstanceOf(ElkGraphElement elkElement, Class<?> expectedType) {
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
