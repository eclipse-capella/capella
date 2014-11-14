/*******************************************************************************
 * Copyright (c) 2006, 2014 THALES GLOBAL SERVICES.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
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
 * @param IT
 *            the type of data that are visit by this visitor.
 * @param OT
 *            the type of data to collect (output type).
 */
public interface CollectorVisitor<IT, OT> {

    /**
     * Visit one data.
     * 
     * @param input
     *            the input data.
     * @param output
     *            the output data.
     */
    void visit(final IT input, final Collection<OT> output);

    /**
     * Default visitor, input == output.
     */
    public static final CollectorVisitor<? extends CapellaElement, ? extends CapellaElement> DEFAULT_VISITOR = new CollectorVisitor<CapellaElement, CapellaElement>() {
        public void visit(CapellaElement input, Collection<CapellaElement> output) {
            output.add(input);
        }
    };

    /**
     * Basic Collector Visitor Input == Output.
     * 
     */
    public static class BasicCollectorVisitor implements CollectorVisitor<Object, Object> {

        /** teh filter type. */
        private Class<? extends Object> filterType;

        /**
         * Creates a new Basic Visitor.
         * 
         * @param filterType
         *            the filter type.
         */
        private BasicCollectorVisitor(final Class<? extends Object> filterType_p) {
            this.filterType = filterType_p;
        }

        /**
         * Creates a new Basic Visitor.
         * 
         * @param <IT>
         *            the input type.
         * @param <OT>
         *            the output type.
         * @param filterType
         *            the filter type.
         * @return a new Basic Visitor.
         */
        @SuppressWarnings("unchecked")
		public static <IT, OT> CollectorVisitor<IT, OT> getVisitor(Class<?> filterType) {
            return (CollectorVisitor<IT, OT>) new BasicCollectorVisitor(filterType);
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
