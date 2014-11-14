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
package org.polarsys.capella.core.commands.preferences.service;


/**
 * Interface implemented by clients who wish to receive notification
 * whenever item are changed.
 *
 *
 */
public interface IItemListener {
	/**
	 * Notifies me that a constraint change event has taken place.  The
	 * event provides information about the constraint that has changed
	 * and the operation that took place (registration, enablement etc.)
	 * 
	 * @param event provides information about the constraint change
	 */
	public void itemChanged(ItemChangeEvent event);
}
