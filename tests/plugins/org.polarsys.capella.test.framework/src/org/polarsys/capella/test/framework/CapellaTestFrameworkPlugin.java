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
package org.polarsys.capella.test.framework;

import org.eclipse.core.runtime.preferences.IEclipsePreferences;
import org.eclipse.core.runtime.preferences.InstanceScope;
import org.eclipse.egit.core.GitCorePreferences;
import org.eclipse.egit.ui.UIPreferences;
import org.osgi.framework.BundleContext;
import org.polarsys.capella.common.mdsofa.common.activator.AbstractActivator;

/**
 * Activator
 */
public class CapellaTestFrameworkPlugin extends AbstractActivator {

  private static CapellaTestFrameworkPlugin __instance;

  // The plug-in ID
  public static final String PLUGIN_ID = "org.polarsys.capella.test.framework"; //$NON-NLS-1$

  
  /**
   * Get the singleton instance.
   * 
   * @return
   */
  public static CapellaTestFrameworkPlugin getDefault() {
    return __instance;
  }

  /**
   * @see org.eclipse.core.runtime.Plugin#start(org.osgi.framework.BundleContext)
   */
  @Override
  public void start(BundleContext context_p) throws Exception {
    super.start(context_p);
    __instance = this;
    
    IEclipsePreferences pref = InstanceScope.INSTANCE.getNode(org.eclipse.egit.core.Activator.PLUGIN_ID);
    pref.putBoolean(GitCorePreferences.core_autoShareProjects, false);
    pref.putBoolean(GitCorePreferences.core_autoIgnoreDerivedResources, false);
    pref.flush();
    pref = InstanceScope.INSTANCE.getNode(org.eclipse.egit.ui.Activator.PLUGIN_ID);
    pref.putInt(UIPreferences.REFRESH_INDEX_INTERVAL, 300000); //instead of each 5min
    pref.flush();
  }

  /**
   * @see org.eclipse.core.runtime.Plugin#stop(org.osgi.framework.BundleContext)
   */
  @Override
  public void stop(BundleContext context_p) throws Exception {
    __instance = null;
    super.stop(context_p);
  }
}
