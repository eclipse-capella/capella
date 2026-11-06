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

import org.eclipse.elk.graph.properties.IProperty;
import org.eclipse.sirius.diagram.description.BooleanLayoutOption;
import org.eclipse.sirius.diagram.description.CustomLayoutConfiguration;
import org.eclipse.sirius.diagram.description.DescriptionFactory;
import org.eclipse.sirius.diagram.description.DoubleLayoutOption;
import org.eclipse.sirius.diagram.description.EnumLayoutOption;
import org.eclipse.sirius.diagram.description.EnumLayoutValue;
import org.eclipse.sirius.diagram.description.EnumSetLayoutOption;
import org.eclipse.sirius.diagram.description.IntegerLayoutOption;
import org.eclipse.sirius.diagram.description.LayoutOption;
import org.eclipse.sirius.diagram.description.LayoutOptionTarget;
import org.eclipse.sirius.diagram.ui.internal.layout.GenericLayoutProvider;

/**
 * A specific {@link GenericLayoutProvider} to enable ELK layout on some Capella diagrams.
 *
 * @author lredor
 */
@SuppressWarnings("restriction")
public class ElkLayoutConfigurator {

    private static final DescriptionFactory LFCT = DescriptionFactory.eINSTANCE;

    public static final List<LayoutOptionTarget> TARGET_PARENT = List.of(LayoutOptionTarget.PARENT);

    public static final List<LayoutOptionTarget> TARGET_PARENT_NODE = List.of(
            LayoutOptionTarget.PARENT, LayoutOptionTarget.NODE);

    public static final List<LayoutOptionTarget> TARGET_NODE = List.of(LayoutOptionTarget.NODE);


    private final CustomLayoutConfiguration config = LFCT.createCustomLayoutConfiguration();
    
    /**
     * Creates a Configurator for Layout of Sirius Diagrams.
     * 
     * @param id identification of layout implementation
     */
    public ElkLayoutConfigurator(String id) {
        config.setId(id);
    }
    
    /**
     * Returns configuration.
     * 
     * @return {@link CustomLayoutConfiguration} 
     */
    public CustomLayoutConfiguration getConfiguration() {
        return config;
    }

    /**
     * Generic method to finalize a {@link LayoutOption} initialization and to add it to the current container.
     * 
     * @param <O> type of option
     * @param id
     *            The id of the option
     * @param targets
     *            The targets of this option (as it is defined in ELK documentation)
     * @param option
     *            The concerned option
     * @return the {@code option) for convenience
     */
    public <O extends LayoutOption> ElkLayoutConfigurator addLayoutOption(IProperty<?> id, List<LayoutOptionTarget> targets, O option) {
        option.setId(id.getId());
        option.getTargets().addAll(targets);
        config.getLayoutOptions().add(option);
        return this;
    }

    /**
     * Creates a new {@link EnumLayoutOption}.
     * 
     * @param id
     *            The id of the option
     * @param targets
     *            The targets of this option (as it is defined in ELK documentation)
     * @param name
     *            The name of the Enum value
     * @return the new option
     */
    public ElkLayoutConfigurator addBooleanOption(IProperty<Boolean> id, List<LayoutOptionTarget> targets, boolean value) {
        BooleanLayoutOption result = LFCT.createBooleanLayoutOption();
        result.setValue(value);
        return addLayoutOption(id, targets, result);
    }

    /**
     * Creates a new {@link DoubleLayoutOption}.
     * 
     * @param id
     *            The id of the option
     * @param targets
     *            The targets of this option (as it is defined in ELK documentation)
     * @param value
     *            The value of this option
     * @return the new option
     */
    public ElkLayoutConfigurator addDoubleOption(IProperty<?> id, List<LayoutOptionTarget> targets, double value) {
        DoubleLayoutOption result = LFCT.createDoubleLayoutOption();
        result.setValue(value);
        return addLayoutOption(id, targets, result);
    }

    /**
     * Creates a new {@link IntegerLayoutOption}.
     * 
     * @param id
     *            The id of the option
     * @param targets
     *            The targets of this option (as it is defined in ELK documentation)
     * @param value
     *            The value of this option
     * @return the new option
     */
    public ElkLayoutConfigurator addIntegerOption(IProperty<?> id, List<LayoutOptionTarget> targets, int value) {
        IntegerLayoutOption result = LFCT.createIntegerLayoutOption();
        result.setValue(value);
        return addLayoutOption(id, targets, result);
    }

    /**
     * Creates a new {@link EnumLayoutOption}.
     * 
     * @param id
     *            The id of the option
     * @param targets
     *            The targets of this option (as it is defined in ELK documentation)
     * @param name
     *            The name of the Enum value
     * @return the new option
     */
    public <T extends Enum<?>> ElkLayoutConfigurator addEnumOption(IProperty<T> id, List<LayoutOptionTarget> targets, T value) {
        EnumLayoutOption result = LFCT.createEnumLayoutOption();
        EnumLayoutValue enumLayoutValue = LFCT.createEnumLayoutValue();
        enumLayoutValue.setName(value.name());
        result.setValue(enumLayoutValue);
        return addLayoutOption(id, targets, result);
    }

    /**
     * Creates a new {@link EnumSetLayoutOption}.
     * 
     * @param id
     *            The id of the option
     * @param valuesNames
     *            The names of the Enum values
     * @param targets
     *            The targets of this option (as it is defined in ELK documentation)
     * @return the new option
     */
    public <T extends Enum<?>> ElkLayoutConfigurator addEnumsOption(IProperty<? extends EnumSet<? extends T>> id, List<LayoutOptionTarget> targets, @SuppressWarnings("unchecked") T... values) {
        EnumSetLayoutOption result = LFCT.createEnumSetLayoutOption();
        for (T value : values) {
            EnumLayoutValue enumLayoutValue = LFCT.createEnumLayoutValue();
            enumLayoutValue.setName(value.name());
            result.getValues().add(enumLayoutValue);
        }
        return addLayoutOption(id, targets, result);
    }
}
