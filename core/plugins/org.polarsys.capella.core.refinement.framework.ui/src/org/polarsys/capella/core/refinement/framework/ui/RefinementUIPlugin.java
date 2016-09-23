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
package org.polarsys.capella.core.refinement.framework.ui;

import java.net.URL;

import org.eclipse.jface.resource.ImageDescriptor;
import org.eclipse.jface.resource.ImageRegistry;
import org.eclipse.swt.graphics.Image;
import org.eclipse.ui.plugin.AbstractUIPlugin;
import org.osgi.framework.BundleContext;

import org.polarsys.capella.core.data.interaction.InteractionPackage;

/**
 */
public class RefinementUIPlugin extends AbstractUIPlugin {

  // The shared instance
  private static RefinementUIPlugin plugin;

  /**
   * Constructor.
   */
  public RefinementUIPlugin() {
    // Do nothing.
  }

  /**
   * @see org.eclipse.ui.plugin.AbstractUIPlugin#start(org.osgi.framework.BundleContext)
   */
  @Override
  public void start(BundleContext context) throws Exception {
    super.start(context);
    plugin = this;
  }

  /**
   * @see org.eclipse.ui.plugin.AbstractUIPlugin#stop(org.osgi.framework.BundleContext)
   */
  @Override
  public void stop(BundleContext context) throws Exception {
    plugin = null;
    super.stop(context);
  }

  /**
   * @return the shared instance
   */
  public static RefinementUIPlugin getDefault() {
    if (null == plugin) {
      plugin = new RefinementUIPlugin();
    }
    return plugin;
  }

  /**
   * Gets the image.
   * @param key The image key.
   * @return The corresponding image else null if none.
   */
  public Image getImage(String key) {
    return getImageRegistry().get(key);
  }

  /**
   * @see org.eclipse.ui.plugin.AbstractUIPlugin#initializeImageRegistry(org.eclipse.jface.resource.ImageRegistry)
   */
  @Override
  protected void initializeImageRegistry(ImageRegistry registry) {
    ImageDescriptor imgDescriptor = ImageDescriptor.createFromURL(getIconURL("Scenario.png")); //$NON-NLS-1$
    registry.put(InteractionPackage.Literals.SCENARIO.getName(), imgDescriptor);
  }

  /**
   * Gets the URL from the specified filename into the bundle icons directory.
   */
  private URL getIconURL(String filename) {
    return RefinementUIPlugin.class.getResource("icons_75/" + filename); //$NON-NLS-1$
  }
}
