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
  public void start(BundleContext context_p) throws Exception {
    super.start(context_p);
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
   * @param key_p The image key.
   * @return The corresponding image else null if none.
   */
  public Image getImage(String key_p) {
    return getImageRegistry().get(key_p);
  }

  /**
   * @see org.eclipse.ui.plugin.AbstractUIPlugin#initializeImageRegistry(org.eclipse.jface.resource.ImageRegistry)
   */
  @Override
  protected void initializeImageRegistry(ImageRegistry registry_p) {
    ImageDescriptor imgDescriptor = ImageDescriptor.createFromURL(getIconURL("Scenario.png")); //$NON-NLS-1$
    registry_p.put(InteractionPackage.Literals.SCENARIO.getName(), imgDescriptor);
  }

  /**
   * Gets the URL from the specified filename into the bundle icons directory.
   */
  private URL getIconURL(String filename_p) {
    return RefinementUIPlugin.class.getResource("icons_75/" + filename_p); //$NON-NLS-1$
  }
}
