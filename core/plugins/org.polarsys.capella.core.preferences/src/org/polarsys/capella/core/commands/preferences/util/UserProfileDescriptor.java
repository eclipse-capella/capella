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
package org.polarsys.capella.core.commands.preferences.util;

import org.eclipse.core.runtime.CoreException;
import org.osgi.framework.FrameworkUtil;
import org.polarsys.capella.core.commands.preferences.preferences.ConfigurabilityPreferences;
import org.polarsys.capella.core.commands.preferences.service.AbstractItemDescriptor;
import org.polarsys.capella.core.commands.preferences.service.PreferencesItemsRegistry;
import org.polarsys.capella.core.preferences.Activator;
import org.polarsys.capella.core.preferences.commands.exceptions.ItemExistsException;

/**
 *
 */
public class UserProfileDescriptor extends AbstractItemDescriptor implements IUserProfileDescriptor {

	/**
	 * @param userProfileModeId
	 * @param userProfileModeName
	 */
	public UserProfileDescriptor(String userProfileModeId,String userProfileModeName) {

		try {
			assertNotNull(userProfileModeId, FrameworkUtil.getBundle(Activator.class).getSymbolicName());
			assertNotNull(userProfileModeName, FrameworkUtil.getBundle(Activator.class).getSymbolicName());
			
			PreferencesItemsRegistry.getInstance().registerUserProfile(this); 
			
			// ensure that I get my saved preference state
			ConfigurabilityPreferences.setInstanceScopePreferenceItem(userProfileModeId,ConfigurabilityPreferences.isInstanceScopePreferenceItemEnabled(userProfileModeId));
			 
			ConfigurabilityPreferences.save();
		} catch (CoreException e) {
			e.printStackTrace();
			setError(e);
		} catch (ItemExistsException exception_p) {
			StringBuilder loggerMessage = new StringBuilder("UserProfileDescriptor.UserProfileDescriptor(..) _ "); //$NON-NLS-1$
		}}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public String getName() {
		return XmlPreferencesConfig.USER_PROFILE_MODE_NAME;  
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public boolean isEnabledByDefault() {
		return false;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public String getId() {
		return XmlPreferencesConfig.USER_PROFILE_MODE_ID;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public String getPluginId() {
		return FrameworkUtil.getBundle(Activator.class).getSymbolicName();
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public String getDescription() {
		return "Expert User";
	}

}
