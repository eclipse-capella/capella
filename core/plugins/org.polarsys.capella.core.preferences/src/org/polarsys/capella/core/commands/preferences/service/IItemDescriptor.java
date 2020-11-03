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
package org.polarsys.capella.core.commands.preferences.service;

import java.util.Set;

import org.polarsys.capella.core.commands.preferences.model.CategoryPreferences;

/**
 * <p>
 * A constraint descriptor provides information about a constraint's
 * role and status in the system.  This includes such information as what
 * categories the constraint is a member of and whether it is enabled, disabled,
 * or even broken because of a run-time fault, in addition to a variety of
 * meta-data declared about severity, evaluation mode, and triggers.
 * </p>
 * <p>
 * This interface is intended to be implemented by clients that have constraintProviders
 * that are contributing {@link org.eclipse.emf.validation.model.IModelItem}
 * that are not described in standard XML.  Note that the specialization
 * {@link IParameterizedItemDescriptor} may be of particular value in
 * describing items that are configurable and indicate their language.
 * </p>
 *
 */
public interface IItemDescriptor {
	/**
	 * Gets my name. This needs not be unique in any sense, and should be
	 * localized.
	 * 
	 * @return my name
	 */
	String getName();
	
	
	boolean isEnabledByDefault() ;
	
	

	/**
	 * Gets my ID. This must be unique. It is recommended that the ID be
	 * prefixed by the contributing plugin ID, as is usual for IDs in Eclipse.
	 * 
	 * @return my unique identifier
	 */
	String getId();

	/**
	 * Queries the ID of the plugin which defines me.
	 * 
	 * @return my plugin's ID
	 */
	String getPluginId();

	/**
	 * Obtains a description of my purpose, if any.
	 * 
	 * @return my description, or <CODE>null</CODE> if I have none
	 */
	String getDescription();

	

	
	/**
	 * If I am an {@link #isError error} constraint, obtains the exception
	 * that caused me not to be initialized.
	 *  
	 * @return my exception
	 */
	Throwable getException();
	
	/**
	 * Queries whether the constraint is enabled.  {@link #isError Errored}
	 * items are never enabled; other items may be disabled
	 * by the user.
	 * 
	 * @return whether the constraint that I represent is enabled
	 */
	boolean isEnabled();
	
	/**
	 * Sets whether the constraint is enabled.  Note that this only has any
	 * effect on items that are not {@link #isError() errored} and are
	 * not in a {@link CategoryPreferences#isMandatory() mandatory} category.
	 * 
	 * @param enabled whether the constraint that I represent is enabled
	 * 
	 * @see #isEnabled()
	 */
	void setEnabled(boolean enabled);
	
	/**
	 * <p>
	 * Sets my error status.
	 * </p>
	 * <p>
	 * This method should not be called outside of the validation framework.
	 * </p>
	 * 
	 * @param exception the exception that causes me to be an error constraint
	 */
	void setError(Throwable exception);
	
	/**
	 * Queries the categories that I am a member of.
	 * 
	 * @return an unmodifiable set of {@link CategoryPreferences}s
	 */
	Set<CategoryPreferences> getCategories();
	
	/**
	 * Adds a category to me.  If, previously, I was in the default category,
	 * then I will no longer be in the default category when this method
	 * returns.
	 * 
	 * @param category my category
	 * @throws IllegalArgumentException if <code>category</code> is the default
	 *     category, as this is not allowed to be set explicitly
	 */
	void addCategory(CategoryPreferences category);
	
	/**
	 * Removes a category from me.
	 * 
	 * @param category a category
	 */
	void removeCategory(CategoryPreferences category);
	
	
	
}
