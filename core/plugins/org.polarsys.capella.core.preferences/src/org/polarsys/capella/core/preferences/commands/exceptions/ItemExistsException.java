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
package org.polarsys.capella.core.preferences.commands.exceptions;

import org.polarsys.capella.core.commands.preferences.service.IItemDescriptor;
import org.polarsys.capella.core.commands.preferences.service.PreferencesItemsRegistry;

/**
 * Command thrown to indicate that a constraint descriptor cannot be
 * {@link PreferencesItemsRegistry#register(IItemDescriptor) registered} because
 * a item is already registered under the same ID.
 * <p>
 * This class is not intended to be extended or instantiated by clients.
 * </p>
 *
 * 
 * @noextend This class is not intended to be subclassed by clients.
 */
public class ItemExistsException extends Exception {
	private static final long serialVersionUID = 5637732649693164987L;

	/**
	 * Initializes me with a message.
	 * 
	 * @param s my message
	 */
	public ItemExistsException(String s) { 
		super(s);
	}
}
