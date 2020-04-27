/*******************************************************************************
 * Copyright (c) 2006, 2016 THALES GLOBAL SERVICES.
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

package org.polarsys.capella.core.data.helpers.fa.services;

import java.util.Set;

import org.eclipse.emf.ecore.EObject;

/**
 * 
 */
public abstract class PatternHelper {

	/**
	 * Returns whether this Helper is an helper for given Pattern.
	 * 
	 * @param the
	 *            pattern.
	 */
	public boolean isHelperFor(Pattern pattern) {
		return false;
	}

	/**
	 * Returns this Helper's Pattern.
	 * 
	 * @param the
	 *            pattern (defaults to null).
	 */
	public Pattern getPattern() {
		return null;
	}

	/**
	 * Returns the model objects currently related to given model object
	 * following this helper's associated pattern rules.
	 * 
	 * @param the
	 *            model object to start from.
	 * @return the list of model all objects associated to from through our
	 *         Pattern (from is excluded from this list).
	 */
	public abstract Set<EObject> getCurrent(EObject from);

	/**
	 * Returns the model objects currently available for relation to given model
	 * object following this helper's associated pattern rules. Currently
	 * related objects must not be included.
	 */
	public abstract Set<EObject> getAvailable(EObject from);

	/**
	 * Connects the given list of model objects to given model object following
	 * this helper's associated pattern rules.
	 * 
	 * @return true if connection was successful for all objects.
	 */
	public abstract boolean doConnect(Set<EObject> toConnect);

	/**
	 * Dis-connects the given list of model objects to given model object
	 * following this helper's associated pattern rules.
	 * 
	 * @return true if dis-connection was successful for all objects.
	 */
	public abstract boolean doDisconnect(Set<EObject> toDisconnect);

	/**
	 * Pre-Validates Connection of the model objects following this helper's
	 * associated pattern rules.
	 * 
	 * @return true if pre-validation is successful for all objects.
	 */
	public abstract boolean validateConnection(Set<EObject> toConnect);

	/**
	 * Pre-Validates Dis-connection of the given model objects following this
	 * helper's associated pattern rules.
	 * 
	 * @return true if pre-validation was successful.
	 */
	public abstract boolean validateDisconnection(Set<EObject> toDisconnect);

	/**
	 * Validates the relation of the given list of model objects following this
	 * helper's associated pattern rules.
	 * 
	 * @return true if validation is successful.
	 */
	public abstract boolean validatePattern(Set<EObject> objects);

}
