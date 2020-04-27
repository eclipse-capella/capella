/*******************************************************************************
 * Copyright (c) 2006, 2014 THALES GLOBAL SERVICES.
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
package org.polarsys.capella.core.commands.preferences.service;



/**
 * Interface implemented by clients who wish to define a constraint
 * filter on their validator.
 * 
 */
public interface IItemFilter {
    /**
     * A shared filter instance that doesn't filter out any constraints (all
     * pass through).
     */
    IItemFilter IDENTITY_INSTANCE = new IItemFilter() { 
        public boolean accept(IItemDescriptor constraint, Object target) {
            return true;
        }};
        
	/**
	 * Determines whether a given <code>constraint</code> and
	 * <code>target</code> pair are accepted by this filter.  This is
     * applicable to both batch and live validation modes.
	 * 
	 * @param constraint descriptor of a constraint to consider for filtering
	 * @param target the object on which the <code>constraint</code> would
     *     be validated
     * 
	 * @return true if the pair is accepted by the filter, false otherwise
	 */
	boolean accept(IItemDescriptor constraint, Object target);
}
