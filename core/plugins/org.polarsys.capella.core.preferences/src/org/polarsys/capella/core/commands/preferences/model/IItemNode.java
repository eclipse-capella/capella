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
package org.polarsys.capella.core.commands.preferences.model;

import java.util.Collection;

import org.eclipse.jface.viewers.CheckStateChangedEvent;



/**
 * Interface provided by a node in the Items selection table of the
 * validation preferences UI  Command nodes do not have any gray selection
 * state:  they are either selected or not, and they may be non-changeable if
 * they are mandatory or errored.
 *
 * 
 * @see ICategoryTreeNode
 */
public interface IItemNode {
	/**
	 * Obtains the ID of the constraint, to show in the GUI.
	 * 
	 * @return the ID of the constraint that I represent
	 */
	String getId();
	
	/**
	 * Obtains the name of the constraint, to show in the GUI.
	 * 
	 * @return the name of the constraint that I represent
	 */
	String getName();
	
	/**
	 * Obtains the description of the constraint, to show in the GUI.
	 * 
	 * @return the description of the constraint that I represent
	 */
	String getDescription();
	
	/**
	 * Obtains the categories of which the constraint is a member, to show in
	 * the GUI.
	 * 
	 * @return the categories of the constraint that I represent
	 */
	Collection<CategoryPreferences> getCategories();
	
	
	/**
	 * Queries whether I am checked.  When I am checked, my constraint is
	 * enabled in the validation system.
	 * 
	 * @return whether I show a check mark in the GUI
	 */
	boolean isChecked();
	
	/**
	 * Sets whether I am checked.  Note this method will have no effect if I
	 * am either {@link #isMandatory() mandatory} or
	 * {@link #isErrored() errored}.
	 * 
	 * @param checked whether I show a check mark in the GUI
	 */
	void setChecked(boolean checked);
	
	/**
	 * Queries whether I am a mandatory constraint.  If I am mandatory, then
	 * I cannot be de-selected.
	 * 
	 * @return <code>true</code> if I am mandatory, <code>false</code>,
	 *     otherwise
	 */
	boolean isMandatory();
	
	
	
	/**
	 * Causes me to transition to a new check and/or gray state, according to
	 * the given <code>event</code>.
	 * 
	 * @param event a check-state event in the GUI
	 */
	void checkStateChanged(CheckStateChangedEvent event);
	
	/**
	 * Adds a category tree node as one of the categories affected by my
	 * checked state.
	 * 
	 * @param category a category that includes me
	 */
	void addCategory(ICategoryTreeNode category);
	
	/**
	 * Applies my state to the constraint enablement preferences.
	 */
	void applyToPreferences();
	
	/**
	 * Reverts my state from the current constraint enablement preferences.
	 */
	void revertFromPreferences();

	/**
	 * Restores my state to the default.
	 */
	void restoreDefaults();
}
