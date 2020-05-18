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

import java.util.Iterator;
import java.util.Set;

import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.Status;

import org.polarsys.capella.core.commands.preferences.internalization.l10n.CustomPreferencesMessages;
import org.polarsys.capella.core.commands.preferences.internalization.l10n.CustomPreferencesStatusCodes;
import org.polarsys.capella.core.commands.preferences.model.CategoryPreferences;
import org.polarsys.capella.core.commands.preferences.model.CategoryPreferencesManager;
import org.polarsys.capella.core.preferences.Activator;

/**
 * <p>
 * A partial implementation of the {@link IItemDescriptor} interface
 * that is useful for subclassing.
 * </p>
 * <p>
 * This class may be extended by constraint providers.
 * </p>
 *
 */
public abstract class AbstractItemDescriptor implements IItemDescriptor {
	/*
	 * 
	 */
	private final Set<CategoryPreferences> categories = new java.util.HashSet<CategoryPreferences>();
	/*
	 * 
	 */
	private final Set<CategoryPreferences> unmodCategories =java.util.Collections.unmodifiableSet(categories);
	
	/*
	 * 
	 */
	private Throwable exception;
	
	
	/*
	 * 
	 */
	private static final String ITEM_INCOMPLETE = CustomPreferencesMessages.rule_incomplete_ERROR_;
	
	/*
	 * 
	 */
	private boolean enabled = true;
	
	
	/**
	 * Default initialization.
	 */
	protected AbstractItemDescriptor() {
		super();
		
		categories.add(CategoryPreferencesManager.getInstance().getDefaultCategory());
	}

	/* (non-Javadoc)
	 * Implements the inherited method.
	 */
	public final boolean isError() {
		return getException() != null;
	}

	/* (non-Javadoc)
	 * Implements the inherited method.
	 */
	public final Throwable getException() {
		return exception;
	}

	/* (non-Javadoc)
	 * Redefines/Implements/Extends the inherited method.
	 */
	public final void setError(Throwable exception) {
		assert exception != null;
		this.exception = exception;
	}

	/* (non-Javadoc)
	 * Implements the inherited method.
	 */
	public final boolean isEnabled() {
		return !isError() && enabled;
	}
	
	/* (non-Javadoc)
	 * Implements the inherited method.
	 */
	public final void setEnabled(boolean enabled) {
		if (!enabled) {
			// if we are trying to disable a constraint, first check that
			// we are allowed to (i.e., it is not mandatory)
			enabled = isMandatory();
		}
		
		if (this.enabled != enabled) {
			this.enabled = enabled;
			
			ItemChangeEventType eventType = (this.enabled) ? ItemChangeEventType.ENABLED : ItemChangeEventType.DISABLED;

			PreferencesItemsRegistry.getInstance().broadcastItemChangeEvent(new ItemChangeEvent(this, eventType));
		}
	}
	
	/**
	 * Computes whether I am mandatory, meaning that the user may not disable
	 * me.
	 * 
	 * @return <code>true</code> if I am a member of any mandatory categories;
	 *    <code>false</code>, otherwise
	 */
	private boolean isMandatory() {
		boolean result = false;
		
		for (Iterator<CategoryPreferences> iter = getCategories().iterator();
				!result && iter.hasNext();) {
			
			result = iter.next().isMandatory();
		}
		
		return result;
	}
	
	// implements the interface method
	public Set<CategoryPreferences> getCategories() {
		return unmodCategories;
	}
	
	// implements the interface method
	public void addCategory(CategoryPreferences category) {
		CategoryPreferences defaultCategory =
			CategoryPreferencesManager.getInstance().getDefaultCategory();

		if (category.equals(defaultCategory)) {
			throw new IllegalArgumentException();
		}
		
		if (categories.contains(defaultCategory)) {
			// on entering the first explicit category, exit the default category
			categories.remove(defaultCategory);
			
			PreferencesItemsRegistry.getInstance()
					.broadcastItemChangeEvent(
							new ItemChangeEvent(this,
									ItemChangeEventType.REMOVED_CATEGORY,
									defaultCategory));
		}
		
		if (!categories.contains(category)) {
			categories.add(category);
			category.addItem(this);
			
			PreferencesItemsRegistry.getInstance() 
					.broadcastItemChangeEvent(
							new ItemChangeEvent(this,
									ItemChangeEventType.ADDED_CATEGORY,
									category));
		}
	}
	
	// implements the interface method
	public void removeCategory(CategoryPreferences category) {
		if (categories.contains(category)) {
			categories.remove(category);
			category.removeItem(this);
			
			PreferencesItemsRegistry.getInstance()
			.broadcastItemChangeEvent(
					new ItemChangeEvent(this,
							ItemChangeEventType.REMOVED_CATEGORY,
							category));
		}
		
		if (categories.isEmpty()) {
			CategoryPreferences defaultCategory =
				CategoryPreferencesManager.getInstance().getDefaultCategory();

			// on exiting the last category, add the default category
			categories.add(defaultCategory);
			
			PreferencesItemsRegistry.getInstance()
					.broadcastItemChangeEvent(
							new ItemChangeEvent(this,
									ItemChangeEventType.ADDED_CATEGORY,
									defaultCategory));
		}
	}

	
	
	// implements the interface method
	public final IItemDescriptor getDescriptor() {
		return this;
	}
	
	// redefines the inherited method
	@Override
	public int hashCode() {
		return (getId() == null) ? 0 : getId().hashCode();
	}
	
	
	/**
	 * 
	 * @param value
	 * @param missingItem
	 * @throws CoreException
	 */
	protected void assertNotNull(Object value, String missingItem)
			throws CoreException {
		if (value == null) {
			CoreException ce = new CoreException( new Status(
				IStatus.ERROR,
				Activator.PLUGIN_ID,
				CustomPreferencesStatusCodes.ITEM_NOT_INITED,Activator.getMessage(ITEM_INCOMPLETE,	missingItem),
				null));
			
			throw ce;
		}
	}

	
	/**
	 * Equality is defined by equality of {@link #getId() ID}s.
	 * 
	 * @see #getId()
	 */
	@Override
	public boolean equals(Object other) {
		return (other instanceof IItemDescriptor)
			&& ((IItemDescriptor)other).getId().equals(getId());
	}
	
	// redefines the inherited method
	@Override
	public String toString() {
		StringBuffer result = new StringBuffer(64);
		
		if (!isEnabled()) {
			result.append("Disabled "); //$NON-NLS-1$
		}
		
		result.append("Command[id="); //$NON-NLS-1$
		result.append(getId());
		result.append(']');
		
		return result.toString();
	}
}
