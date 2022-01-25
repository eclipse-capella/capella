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
package org.polarsys.capella.core.ui.fastlinker;

import org.eclipse.jface.resource.ImageRegistry;
import org.eclipse.ui.plugin.AbstractUIPlugin;
import org.osgi.framework.BundleContext;
import org.osgi.framework.FrameworkUtil;

/**
 * The activator class controls the plug-in life cycle.
 */
public class FastLinkerActivator extends AbstractUIPlugin {
  // The plug-in ID
  public static final String PLUGIN_ID = "org.polarsys.capella.core.ui.fastlinker"; //$NON-NLS-1$
  // The shared instance
  private static FastLinkerActivator plugin;
  /**
   * Pin image key.
   */
  public static final String IMG_PIN = "IMG_PIN"; //$NON-NLS-1$
  /**
   * Clear image key.
   */
  public static final String IMG_CLEAR = "IMG_CLEAR"; //$NON-NLS-1$
  /**
   * Link creation manager instance.
   */
  private FastLinkerManager linkCreationManager;

  /**
   * The constructor.
   */
  public FastLinkerActivator() {
    // Nothing to do.
  }

  /**
   * @return the linkCreationManager
   */
  public FastLinkerManager getFastLinkerManager() {
    return linkCreationManager;
  }

  /**
   * {@inheritDoc}
   */
  @Override
  @SuppressWarnings("nls")
  protected void initializeImageRegistry(ImageRegistry imageRegistry) {
    // Enabled icons.
    imageRegistry.put(IMG_PIN, imageDescriptorFromPlugin(FrameworkUtil.getBundle(FastLinkerActivator.class).getSymbolicName(), "icons/elcl16/pin.gif"));
    imageRegistry.put(IMG_CLEAR, imageDescriptorFromPlugin(FrameworkUtil.getBundle(FastLinkerActivator.class).getSymbolicName(), "icons/elcl16/clear.gif"));
  }

  /*
   * (non-Javadoc)
   * @see org.eclipse.ui.plugin.AbstractUIPlugin#start(org.osgi.framework.BundleContext)
   */
  @Override
  public void start(BundleContext context) throws Exception {
    super.start(context);
    plugin = this;
    linkCreationManager = new FastLinkerManager();
  }

  /*
   * (non-Javadoc)
   * @see org.eclipse.ui.plugin.AbstractUIPlugin#stop(org.osgi.framework.BundleContext)
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
  public static FastLinkerActivator getDefault() {
    return plugin;
  }

}
