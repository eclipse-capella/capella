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
	public boolean isHelperFor(Pattern pattern_p) {
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
	 * @return the list of model all objects associated to from_p through our
	 *         Pattern (from_p is excluded from this list).
	 */
	public abstract Set<EObject> getCurrent(EObject from_p);

	/**
	 * Returns the model objects currently available for relation to given model
	 * object following this helper's associated pattern rules. Currently
	 * related objects must not be included.
	 */
	public abstract Set<EObject> getAvailable(EObject from_p);

	/**
	 * Connects the given list of model objects to given model object following
	 * this helper's associated pattern rules.
	 * 
	 * @return true if connection was successful for all objects.
	 */
	public abstract boolean doConnect(Set<EObject> toConnect_p);

	/**
	 * Dis-connects the given list of model objects to given model object
	 * following this helper's associated pattern rules.
	 * 
	 * @return true if dis-connection was successful for all objects.
	 */
	public abstract boolean doDisconnect(Set<EObject> toDisconnect_p);

	/**
	 * Pre-Validates Connection of the model objects following this helper's
	 * associated pattern rules.
	 * 
	 * @return true if pre-validation is successful for all objects.
	 */
	public abstract boolean validateConnection(Set<EObject> toConnect_p);

	/**
	 * Pre-Validates Dis-connection of the given model objects following this
	 * helper's associated pattern rules.
	 * 
	 * @return true if pre-validation was successful.
	 */
	public abstract boolean validateDisconnection(Set<EObject> toDisconnect_p);

	/**
	 * Validates the relation of the given list of model objects following this
	 * helper's associated pattern rules.
	 * 
	 * @return true if validation is successful.
	 */
	public abstract boolean validatePattern(Set<EObject> objects_p);

}
