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
