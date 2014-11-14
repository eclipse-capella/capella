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
package org.polarsys.capella.core.ui.fastlinker;

import org.eclipse.jface.resource.ImageRegistry;
import org.eclipse.ui.plugin.AbstractUIPlugin;
import org.osgi.framework.BundleContext;

/**
 * The activator class controls the plug-in life cycle.
 */
public class FastLinkerActivator extends AbstractUIPlugin {
  // The plug-in ID
  public static final String PLUGIN_ID = "org.polarsys.capella.core.ui.fastlinker"; //$NON-NLS-1$
  // The shared instance
  private static FastLinkerActivator __plugin;
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
  private FastLinkerManager _linkCreationManager;

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
    return _linkCreationManager;
  }

  /**
   * {@inheritDoc}
   */
  @Override
  @SuppressWarnings("nls")
  protected void initializeImageRegistry(ImageRegistry imageRegistry_p) {
    // Enabled icons.
    imageRegistry_p.put(IMG_PIN, imageDescriptorFromPlugin(PLUGIN_ID, "icons/elcl16/pin.gif"));
    imageRegistry_p.put(IMG_CLEAR, imageDescriptorFromPlugin(PLUGIN_ID, "icons/elcl16/clear.gif"));
  }

  /*
   * (non-Javadoc)
   * @see org.eclipse.ui.plugin.AbstractUIPlugin#start(org.osgi.framework.BundleContext)
   */
  @Override
  public void start(BundleContext context) throws Exception {
    super.start(context);
    __plugin = this;
    _linkCreationManager = new FastLinkerManager();
  }

  /*
   * (non-Javadoc)
   * @see org.eclipse.ui.plugin.AbstractUIPlugin#stop(org.osgi.framework.BundleContext)
   */
  @Override
  public void stop(BundleContext context) throws Exception {
    __plugin = null;
    super.stop(context);
  }

  /**
   * Returns the shared instance
   * @return the shared instance
   */
  public static FastLinkerActivator getDefault() {
    return __plugin;
  }

}
