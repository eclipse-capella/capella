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
package org.polarsys.capella.core.commands.preferences.util;

import org.eclipse.core.runtime.IConfigurationElement;
import org.eclipse.core.runtime.IExtension;
import org.eclipse.core.runtime.dynamichelpers.IExtensionChangeHandler;
import org.eclipse.core.runtime.dynamichelpers.IExtensionTracker;

import org.polarsys.capella.core.commands.preferences.model.CategoryPreferences;
import org.polarsys.capella.core.commands.preferences.model.CategoryPreferencesManager;

/**
 *
 */
public class PreferencesExtensionHandler implements IExtensionChangeHandler {
	
	private final CategoryPreferences globalCategory = CategoryPreferences.GLOBAL_NAMESPACE;
	
	/**
	 * 
	 * {@inheritDoc}
	 */

	public void addExtension(IExtensionTracker tracker, IExtension extension) {
		for (IConfigurationElement next : extension.getConfigurationElements()) {
			
			if (next.getName().equals(XmlPreferencesConfig.ELEMENT_CATEGORY)) {
				CategoryPreferencesManager.getInstance().loadCategories(globalCategory, next);
				
			}else if (next.getName().equals(XmlPreferencesConfig.ELEMENT_PROJECT_NATURE)) {
				CategoryPreferencesManager.getInstance().loadProjectsNature(globalCategory, next);
				
			}

		}
	}
	
	/**
	 * 
	 * {@inheritDoc}
	 */
	public void removeExtension(IExtension extension, Object[] objects) {
		
	}
}
