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

import java.util.EnumSet;
import java.util.List;
import java.util.Optional;

import org.eclipse.elk.alg.graphviz.layouter.DotOptions;
import org.eclipse.elk.alg.layered.options.EdgeLabelSideSelection;
import org.eclipse.elk.alg.layered.options.FixedAlignment;
import org.eclipse.elk.alg.layered.options.LayeredOptions;
import org.eclipse.elk.alg.mrtree.options.MrTreeOptions;
import org.eclipse.elk.core.options.CoreOptions;
import org.eclipse.elk.core.options.Direction;
import org.eclipse.elk.core.options.EdgeRouting;
import org.eclipse.elk.core.options.HierarchyHandling;
import org.eclipse.elk.core.options.SizeConstraint;
import org.eclipse.elk.graph.properties.IProperty;
import org.eclipse.gmf.runtime.diagram.ui.editparts.IGraphicalEditPart;
import org.eclipse.sirius.diagram.DDiagram;
import org.eclipse.sirius.diagram.description.CustomLayoutConfiguration;
import org.eclipse.sirius.diagram.description.DescriptionFactory;
import org.eclipse.sirius.diagram.description.DoubleLayoutOption;
import org.eclipse.sirius.diagram.description.EnumLayoutOption;
import org.eclipse.sirius.diagram.description.EnumLayoutValue;
import org.eclipse.sirius.diagram.description.EnumSetLayoutOption;
import org.eclipse.sirius.diagram.description.IntegerLayoutOption;
import org.eclipse.sirius.diagram.description.LayoutOption;
import org.eclipse.sirius.diagram.description.LayoutOptionTarget;
import org.eclipse.sirius.diagram.ui.business.api.query.EditPartQuery;
import org.eclipse.sirius.diagram.ui.internal.layout.GenericLayoutProvider;

/**
 * A specific {@link GenericLayoutProvider} to enable ELK layout on some Capella diagrams.
 *
 * @author lredor
 */
@SuppressWarnings("restriction")
public class CapellaSiriusLayoutProvider extends GenericLayoutProvider {
    
    private static final DescriptionFactory LFCT = DescriptionFactory.eINSTANCE;

    private static final List<LayoutOptionTarget> TARGET_PARENT = List.of(LayoutOptionTarget.PARENT);

    private static final List<LayoutOptionTarget> TARGET_PARENT_NODE = List.of(
            LayoutOptionTarget.PARENT, LayoutOptionTarget.NODE);

    private static final List<LayoutOptionTarget> TARGET_NODE = List.of(LayoutOptionTarget.NODE);

    @Override
    public Optional<CustomLayoutConfiguration> getLayoutConfiguration(IGraphicalEditPart partToLayout) {
        DDiagram diagram = new EditPartQuery(partToLayout).getDDiagram().orElse(null);
        if (diagram == null) {
            return Optional.empty();
        }
        
        // Activate a specific ELK layout on some Capella diagrams
        CustomLayoutConfiguration config = DiagramLayoutMode.getModeConfiguration(diagram);
        
        return Optional.ofNullable(config);
    }

    private static CustomLayoutConfiguration createConfiguration(String id) {
        CustomLayoutConfiguration result = LFCT.createCustomLayoutConfiguration();
        result.setId(id);
        return result;
    }

    static CustomLayoutConfiguration createMrTreeConfiguration() {
        return createConfiguration(MrTreeOptions.ALGORITHM_ID);
    }

    static CustomLayoutConfiguration createDotConfiguration() {
        // Note: 'Dot' Requires Graphiz but looks a lot like GMF Draw2D Layout.
        // Available in ELK 0.9.1.
    	// But in conflict regarding org.eclipse.gmf.runtime.diagram.ui.layoutProviders extension
    	// with Sirius.
        CustomLayoutConfiguration result = createConfiguration(DotOptions.ALGORITHM_ID);
        addIntegerOption(result, CoreOptions.FONT_SIZE, List.of(LayoutOptionTarget.LABEL), 16);
        // Change the routing style
        addEnumOption(result, CoreOptions.EDGE_ROUTING, TARGET_PARENT, EdgeRouting.ORTHOGONAL);
        // Change the direction of the layout
        addEnumOption(result, CoreOptions.DIRECTION, TARGET_PARENT, Direction.RIGHT);
        // Reduce the space between 2 edges in the same parent (including edge label)
        addDoubleOption(result, CoreOptions.SPACING_EDGE_EDGE, TARGET_PARENT, 5);
        return result;
    }

    static CustomLayoutConfiguration createLayeredConfiguration(boolean horizontal) {
        CustomLayoutConfiguration result = createConfiguration(LayeredOptions.ALGORITHM_ID);
        addEnumOption(result, CoreOptions.HIERARCHY_HANDLING, TARGET_PARENT_NODE, HierarchyHandling.INCLUDE_CHILDREN);
        // The labelPlacement is now directly computed according to the Sirius Style (see commit "[548820] Set the
        // label alignment according to the Sirius style")
        // layoutConfiguration.getLayoutOptions().add(createEnumSetLayoutOption(CoreOptions.NODE_LABELS_PLACEMENT.getId(),
        // TARGET_NODE_LABEL, NodeLabelPlacement.H_CENTER,
        // NodeLabelPlacement.V_TOP, NodeLabelPlacement.INSIDE);
        addEnumsOption(result, CoreOptions.NODE_SIZE_CONSTRAINTS, TARGET_NODE, SizeConstraint.PORTS, SizeConstraint.NODE_LABELS,
                SizeConstraint.MINIMUM_SIZE);
        // Reduce the space between 2 edges in the same parent (including edge label)
        addDoubleOption(result, CoreOptions.SPACING_EDGE_EDGE, TARGET_PARENT, 5);
        // The space between port is set to 13 according to the following values to avoid too many small segment on
        // the edge
        // * the general port size = 10
        // * the general label size = 17
        // * and the above space between 2 edges
        addDoubleOption(result, CoreOptions.SPACING_PORT_PORT, TARGET_PARENT, 13);
        // Align firstly the upper element and then the lower (ditto for left and then right)
        addEnumOption(result, LayeredOptions.NODE_PLACEMENT_BK_FIXED_ALIGNMENT, TARGET_PARENT, FixedAlignment.RIGHTDOWN);
        // Change the direction of the layout
        addEnumOption(result, CoreOptions.DIRECTION, TARGET_PARENT, horizontal ? Direction.RIGHT : Direction.DOWN);
        // Reduce the space between a label and its edge to 0 pixel (default value is 2)
        addDoubleOption(result, CoreOptions.SPACING_EDGE_LABEL, TARGET_PARENT, 0);
        // Change the size of the edge segment before having a "direction change" (on the start and end of the edge)
        addDoubleOption(result, LayeredOptions.SPACING_EDGE_NODE_BETWEEN_LAYERS, TARGET_PARENT, 20);
        // Change the space between the nodes and the edges (in opposite side of the layout direction)
        // layoutConfiguration.getLayoutOptions().add(addDoubleOption(CoreOptions.SPACING_EDGE_NODE.getId(),
        // TARGET_PARENT, 0);
        // Change the routing style (not necessarily UNDEFINED is used by default and result corresponds to
        // ORTHOGONAL)
        addEnumOption(result, CoreOptions.EDGE_ROUTING, TARGET_PARENT, EdgeRouting.ORTHOGONAL);
        // Force the labels of the edge to be below their edge.
        addEnumOption(result, LayeredOptions.EDGE_LABELS_SIDE_SELECTION, TARGET_PARENT, EdgeLabelSideSelection.ALWAYS_DOWN);
        return result;
    }

    /**
     * Generic method to finalize a {@link LayoutOption} initialization and to add it to the current container.
     * 
     * @param container
     *            The container to set this option on.
     * @param id
     *            The id of the option
     * @param targets
     *            The targets of this option (as it is defined in ELK documentation)
     * @param option
     *            The concerned option
     * @return the {@code option) for convenience
     */
    protected static LayoutOption addLayoutOption(CustomLayoutConfiguration container, IProperty<?> id, List<LayoutOptionTarget> targets, LayoutOption option) {
        option.setId(id.getId());
        option.getTargets().addAll(targets);
        container.getLayoutOptions().add(option);
        return option;
    }

    /**
     * Create a new {@link DoubleLayoutOption}.
     * 
     * @param id
     *            The id of the option
     * @param targets
     *            The targets of this option (as it is defined in ELK documentation)
     * @param value
     *            The value of this option
     * @return the new option
     */
    protected static LayoutOption addDoubleOption(CustomLayoutConfiguration container, IProperty<?> id, List<LayoutOptionTarget> targets, double value) {
        DoubleLayoutOption result = LFCT.createDoubleLayoutOption();
        result.setValue(value);
        return addLayoutOption(container, id, targets, result);
    }

    /**
     * Create a new {@link IntegerLayoutOption}.
     * 
     * @param id
     *            The id of the option
     * @param targets
     *            The targets of this option (as it is defined in ELK documentation)
     * @param value
     *            The value of this option
     * @return the new option
     */
    protected static LayoutOption addIntegerOption(CustomLayoutConfiguration container, IProperty<?> id, List<LayoutOptionTarget> targets, int value) {
        IntegerLayoutOption result = LFCT.createIntegerLayoutOption();
        result.setValue(value);
        return addLayoutOption(container, id, targets, result);
    }

    /**
     * Create a new {@link EnumLayoutOption}.
     * 
     * @param id
     *            The id of the option
     * @param targets
     *            The targets of this option (as it is defined in ELK documentation)
     * @param name
     *            The name of the Enum value
     * @return the new option
     */
    protected static <T extends Enum<?>> LayoutOption addEnumOption(CustomLayoutConfiguration container, IProperty<T> id, List<LayoutOptionTarget> targets, T value) {
        EnumLayoutOption result = LFCT.createEnumLayoutOption();
        EnumLayoutValue enumLayoutValue = LFCT.createEnumLayoutValue();
        enumLayoutValue.setName(value.name());
        result.setValue(enumLayoutValue);
        return addLayoutOption(container, id, targets, result);
    }
   
    /**
     * Create a new {@link EnumSetLayoutOption}.
     * 
     * @param id
     *            The id of the option
     * @param valuesNames
     *            The names of the Enum values
     * @param targets
     *            The targets of this option (as it is defined in ELK documentation)
     * @return the new option
     */
    protected static <T extends Enum<?>> LayoutOption addEnumsOption(CustomLayoutConfiguration container, IProperty<? extends EnumSet<? extends T>> id, List<LayoutOptionTarget> targets, @SuppressWarnings("unchecked") T... values) {
        EnumSetLayoutOption result = LFCT.createEnumSetLayoutOption();
        for (T value : values) {
            EnumLayoutValue enumLayoutValue = LFCT.createEnumLayoutValue();
            enumLayoutValue.setName(value.name());
            result.getValues().add(enumLayoutValue);
        }
        return addLayoutOption(container, id, targets, result);
    }
}
