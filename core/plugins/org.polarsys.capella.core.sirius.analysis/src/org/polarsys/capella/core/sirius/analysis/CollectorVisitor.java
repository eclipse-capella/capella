/*******************************************************************************
 * Copyright (c) 2006, 2020 THALES GLOBAL SERVICES.
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
package org.polarsys.capella.core.sirius.analysis;

import java.util.Collection;

import org.polarsys.capella.core.data.capellacore.CapellaElement;

/**
 * This type defines the contract of a visitor that collect data on a model.
 * 
 * @param I
 *            the type of data that are visit by this visitor.
 * @param O
 *            the type of data to collect (output type).
 */
public interface CollectorVisitor<I, O> {

    /**
     * Visit one data.
     * 
     * @param input
     *            the input data.
     * @param output
     *            the output data.
     */
    void visit(final I input, final Collection<O> output);

    /**
     * Default visitor, input == output.
     */
    CollectorVisitor<? extends CapellaElement, ? extends CapellaElement> DEFAULT_VISITOR = new CollectorVisitor<CapellaElement, CapellaElement>() {
        public void visit(CapellaElement input, Collection<CapellaElement> output) {
            output.add(input);
        }
    };

    /**
     * Basic Collector Visitor Input == Output.
     * 
     */
    public static class BasicCollectorVisitor implements CollectorVisitor<Object, Object> {

        /** the filter type. */
        private Class<? extends Object> filterType;

        /**
         * Creates a new Basic Visitor.
         * 
         * @param filterType
         *            the filter type.
         */
        private BasicCollectorVisitor(final Class<? extends Object> filterType) {
            this.filterType = filterType;
        }

        /**
         * Creates a new Basic Visitor.
         * 
         * @param <I>
         *            the input type.
         * @param <O>
         *            the output type.
         * @param filterType
         *            the filter type.
         * @return a new Basic Visitor.
         */
        @SuppressWarnings("unchecked")
		public static <I, O> CollectorVisitor<I, O> getVisitor(Class<?> filterType) {
            return (CollectorVisitor<I, O>) new BasicCollectorVisitor(filterType);
        }

        /**
         * {@inheritDoc}
         * 
         * @see org.polarsys.capella.core.sirius.analysis.CollectorVisitor#visit(java.lang.Object,
         *      java.util.Collection)
         */
        public void visit(Object input, Collection<Object> output) {
            if (filterType.isInstance(input)) {
                output.add(input);
            }
        }

    }
}
