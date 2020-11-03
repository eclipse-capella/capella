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
package org.polarsys.capella.test.explorer.activity.ju;

import org.eclipse.core.runtime.Plugin;
import org.osgi.framework.BundleContext;

public class TestActivityExplorerPlugin extends Plugin {

  // The plug-in ID
  public static final String PLUGIN_ID = "org.polarsys.capella.test.explorer.activity.ju"; //$NON-NLS-1$

  // The shared instance
  private static TestActivityExplorerPlugin plugin;

  /**
   * {@inheritDoc}
   */
  @Override
  public void start(BundleContext context) throws Exception {
    super.start(context);
    plugin = this;
  }

  /**
   * {@inheritDoc}
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
  public static TestActivityExplorerPlugin getDefault() {
    return plugin;
  }
}
