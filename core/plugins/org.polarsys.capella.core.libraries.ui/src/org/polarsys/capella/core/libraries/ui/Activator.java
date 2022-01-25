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
package org.polarsys.capella.core.libraries.ui;

import org.eclipse.jface.resource.ImageDescriptor;
import org.eclipse.jface.resource.ImageRegistry;
import org.eclipse.ui.plugin.AbstractUIPlugin;
import org.osgi.framework.BundleContext;

/**
 * The activator class controls the plug-in life cycle
 */
public class Activator extends AbstractUIPlugin {

  private static final String ICONS_PATH = "icons/";

  // The shared instance
  private static Activator plugin;

  /**
   * The constructor
   */
  public Activator() {
  }

  /*
   * (non-Javadoc)
   * @see org.eclipse.ui.plugin.AbstractUIPlugin#start(org.osgi.framework.BundleContext)
   */
  @Override
  public void start(BundleContext context) throws Exception {
    super.start(context);
    plugin = this;
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

  public ImageDescriptor getImageDescriptor(String key_p) {
    ImageRegistry imageRegistry = getImageRegistry();
    ImageDescriptor imageDescriptor = imageRegistry.getDescriptor(key_p);

    if (null == imageDescriptor) {
      imageDescriptor = createImageDescriptor(key_p);
      imageRegistry.put(key_p, imageDescriptor);
    }
    return imageDescriptor;
  }

  protected ImageDescriptor createImageDescriptor(String key_p) {
    ImageDescriptor imageDescriptor = AbstractUIPlugin.imageDescriptorFromPlugin(getPluginId(), ICONS_PATH + key_p);
    return imageDescriptor;
  }

  public String getPluginId() {
    return getBundle().getSymbolicName();
  }

  /**
   * Returns the shared instance
   * @return the shared instance
   */
  public static Activator getDefault() {
    return plugin;
  }

}
