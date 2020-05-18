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

import org.polarsys.capella.core.commands.preferences.model.CategoryPreferences;

/**
 * Event notifying {@link IItemListener}s that a constraint has been
 * changed.
 * <p>
 * This class is not intended to be extended or instantiated by clients.
 * </p>
 * 
 *
 * 
 */
public class ItemChangeEvent {

	private IItemDescriptor itemDescriptor;
	
	private ItemChangeEventType eventType;
	
	private CategoryPreferences category;
	
	
	/**
	 * Initializes me with the constraint that has changed, an event that
	 * details the change and the category associated with the event.
	 * 
	 * @param itemDescriptor the constraint that has changed
	 * @param eventType the event that details the constraint change
	 * @param category the category associated with the event (if eventType is
	 *        {@link ItemChangeEventType#ADDED_CATEGORY} or 
	 *        {@link ItemChangeEventType#REMOVED_CATEGORY})      
	 */
	public ItemChangeEvent(IItemDescriptor itemDescriptor, ItemChangeEventType eventType, CategoryPreferences category) {
		this.itemDescriptor = itemDescriptor;
		this.eventType = eventType;
		this.category = category;
	}
	
	/**
	 * Initializes me with the constraint that has changed and the event that
	 * details the change.
	 * 
	 * @param itemDescriptor the constraint that has changed
	 * @param eventType the event that details the constraint change     
	 */
	public ItemChangeEvent(IItemDescriptor itemDescriptor, ItemChangeEventType eventType) {
		this(itemDescriptor, eventType, null);
	}
	
	/**
	 * Obtains {@link IItemDescriptor} of the constraint associated with
     * the event
	 * 
	 * @return the constraint associated with the event
	 */
	public IItemDescriptor getItemDescriptor() {
		return this.itemDescriptor;
	}
	
	/**
	 * Obtains {@link ItemChangeEventType} that details the event
	 * 
	 * @return the event type for this event
	 */
	public ItemChangeEventType getEventType() {
		return this.eventType;
	}
	
	/**
	 * Obtains {@link CategoryPreferences} associated with this event
	 * 
	 * @return the category associated with the event
	 */
	public CategoryPreferences getCategory() {
		return this.category;
	}
	
	/**
	 * Sets the constraint descriptor for re-use of an event instance in
	 * bulk notifications.
	 * 
	 * @param itemDescriptor the descriptor to set
	 */
	public void setItemDescriptor(IItemDescriptor itemDescriptor) {
	    this.itemDescriptor = itemDescriptor;
	}
}
