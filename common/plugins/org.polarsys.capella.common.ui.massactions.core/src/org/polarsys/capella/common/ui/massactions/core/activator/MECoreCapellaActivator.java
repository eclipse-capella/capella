/*******************************************************************************
 * Copyright (c) 2018, 2020 THALES GLOBAL SERVICES.
 * 
 * This program and the accompanying materials are made available under the
 * terms of the Eclipse Public License 2.0 which is available at
 * http://www.eclipse.org/legal/epl-2.0
 * 
 * SPDX-License-Identifier: EPL-2.0
 *
 * Contributors:
 * Thales - initial API and implementation
 *******************************************************************************/
package org.polarsys.capella.common.ui.massactions.core.activator;

import org.eclipse.jface.resource.ImageRegistry;
import org.eclipse.ui.plugin.AbstractUIPlugin;
import org.osgi.framework.BundleContext;
import org.osgi.framework.FrameworkUtil;

/**
 * The activator class controls the plug-in life cycle
 * 
 * @author Sandu Postaru
 * 
 */
public class MECoreCapellaActivator extends AbstractUIPlugin {

  public static final String IMAGE_BROWSE = "IMAGE_BROWSE";

  // The shared instance
  private static MECoreCapellaActivator plugin;

  /*
   * (non-Javadoc)
   * 
   * @see org.eclipse.ui.plugin.AbstractUIPlugin#start(org.osgi.framework.BundleContext)
   */
  @Override
  public void start(BundleContext context) throws Exception {
    super.start(context);
    plugin = this;
  }

  /*
   * (non-Javadoc)
   * 
   * @see org.eclipse.ui.plugin.AbstractUIPlugin#stop(org.osgi.framework.BundleContext)
   */
  @Override
  public void stop(BundleContext context) throws Exception {
    plugin = null;
    super.stop(context);
  }

  /**
   * Returns the shared instance
   *
   * @return the shared instance
   */
  public static MECoreCapellaActivator getDefault() {
    return plugin;
  }

  @Override
  protected void initializeImageRegistry(ImageRegistry reg) {
    super.initializeImageRegistry(reg);

    reg.put(IMAGE_BROWSE, imageDescriptorFromPlugin(FrameworkUtil.getBundle(this.getClass()).getSymbolicName(), "icons/browse.gif"));
  }

}
