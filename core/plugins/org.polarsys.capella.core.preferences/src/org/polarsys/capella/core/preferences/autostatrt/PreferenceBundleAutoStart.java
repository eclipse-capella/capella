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
package org.polarsys.capella.core.preferences.autostatrt;

import org.eclipse.core.runtime.Platform;
import org.eclipse.ui.IStartup;
import org.osgi.framework.BundleException;

import org.polarsys.capella.core.commands.preferences.properties.PreferencesHandler;
import org.polarsys.capella.core.preferences.Activator;

/**
 */
public class PreferenceBundleAutoStart implements IStartup {

  @Override
  public void earlyStartup() {

    if (Platform.isRunning()) {

      try {
        Activator.getDefault().getBundle().start();
        PreferencesHandler.initializePreferenceCommands();
      } catch (BundleException exception) {
        exception.printStackTrace();
        StringBuilder loggerMessage = new StringBuilder("PreferenceBundleAutoStart.earlyStartup(..) _ "); //$NON-NLS-1$
      }

    }
  }

}
