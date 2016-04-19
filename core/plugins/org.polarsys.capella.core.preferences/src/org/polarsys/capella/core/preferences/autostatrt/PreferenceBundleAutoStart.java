/*******************************************************************************
 * Copyright (c) 2006, 2016 THALES GLOBAL SERVICES.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *  
 * Contributors:
 *    Thales - initial API and implementation
 *******************************************************************************/
package org.polarsys.capella.core.preferences.autostatrt;

import org.eclipse.core.commands.common.NotDefinedException;
import org.eclipse.core.runtime.Platform;
import org.eclipse.ui.IStartup;
import org.polarsys.capella.core.commands.preferences.model.CategoryPreferencesManager;
import org.polarsys.capella.core.preferences.Activator;

/**
 */
public class PreferenceBundleAutoStart implements IStartup {

  @Override
  public void earlyStartup() {
    if (Platform.isRunning()) {
    	try {
    		initializeUserProfilePreferences();
			Activator.getDefault().initializePreferenceCommands();
		} catch (NotDefinedException e) {
			e.printStackTrace();
		}
    }
  }
  
  /**
   * 
   */
  private void initializeUserProfilePreferences() {
    CategoryPreferencesManager.getInstance().loadUserProfile();
  }
}
