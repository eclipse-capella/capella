/*******************************************************************************
 * Copyright (c) 2017, 2020 THALES GLOBAL SERVICES.
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
package org.polarsys.capella.common.tools.report.appenders.usage;

import org.eclipse.core.runtime.Plugin;
import org.osgi.framework.BundleContext;
import org.polarsys.capella.common.tools.report.appenders.usage.preferences.PreferencesInitializer;

/**
 * The activator class controls the plug-in life cycle
 */
public class UsageAppenderPlugin extends Plugin {
  
  // The shared instance
  private static UsageAppenderPlugin plugin;

  /**
   * The constructor
   */
  public UsageAppenderPlugin() {
    // nothing
  }

  /**
   * @see org.eclipse.core.runtime.Plugin#start(org.osgi.framework.BundleContext)
   */
  @Override
  public void start(BundleContext context) throws Exception {
    super.start(context);
    plugin = this;
    new PreferencesInitializer();
  }

  /**
   * @see org.eclipse.core.runtime.Plugin#stop(org.osgi.framework.BundleContext)
   */
  @Override
  public void stop(BundleContext context) throws Exception {
    plugin = null;
    super.stop(context);
  }

  /**
   * Returns the shared instance
   * @return the shared instance
   */
  public static UsageAppenderPlugin getDefault() {
    return plugin;
  }

}
