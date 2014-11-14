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

import org.eclipse.jface.preference.FieldEditor;

/**
 * 
 */
public abstract class CapellaFieldEditor extends FieldEditor {

	private UserProfileModeEnum userProfileModeEnum;

	public UserProfileModeEnum getMode() {
		return userProfileModeEnum;
	}

	public void setMode(UserProfileModeEnum userProfileModeEnum_p) {
		userProfileModeEnum = userProfileModeEnum_p;
	}

}
