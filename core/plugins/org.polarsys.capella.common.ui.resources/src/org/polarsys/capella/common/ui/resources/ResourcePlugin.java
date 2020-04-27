/*******************************************************************************
 * Copyright (c) 2006, 2017 THALES GLOBAL SERVICES.
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
package org.polarsys.capella.common.ui.resources;

import org.eclipse.jface.resource.ImageRegistry;
import org.eclipse.ui.plugin.AbstractUIPlugin;
import org.osgi.framework.BundleContext;

/**
 * The activator class controls the plug-in life cycle. the Capella elements icons could be retrieved with calling the {@link ImageRegistry#get(String)} with the
 * required Capella element literal.
 * @see CapellaPackage
 */
public class ResourcePlugin extends AbstractUIPlugin {
  /** The plug-in identifier */
  public static final String PLUGIN_ID = "org.polarsys.capella.common.ui.resources"; //$NON-NLS-1$

  // The shared instance
  private static ResourcePlugin plugin;

  /**
   * @see org.eclipse.ui.plugin.AbstractUIPlugin#start(org.osgi.framework.BundleContext)
   */
  @Override
  public void start(BundleContext context) throws Exception {
    super.start(context);
    ResourcePlugin.plugin = this;
  }

  /**
   * @see org.eclipse.ui.plugin.AbstractUIPlugin#stop(org.osgi.framework.BundleContext)
   */
  @Override
  public void stop(BundleContext context) throws Exception {
	  ResourcePlugin.plugin = null;
    super.stop(context);
  }

  /**
   * Returns the shared instance
   * @return the shared instance
   */
  public static ResourcePlugin getDefault() {
    if (null == plugin) {
      plugin = new ResourcePlugin();
    }
    return plugin;
  }
}
