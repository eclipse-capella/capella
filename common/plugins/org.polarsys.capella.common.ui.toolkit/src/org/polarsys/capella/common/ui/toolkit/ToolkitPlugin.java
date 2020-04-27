/*******************************************************************************
 * Copyright (c) 2006, 2019 THALES GLOBAL SERVICES.
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


package org.polarsys.capella.common.ui.toolkit;

import java.net.URL;

import org.eclipse.jface.resource.ImageDescriptor;
import org.eclipse.jface.resource.ImageRegistry;
import org.osgi.framework.BundleContext;

import org.polarsys.capella.common.ui.services.AbstractUIActivator;

/**
 * The toolkit plug-in instance.
 */
public class ToolkitPlugin extends AbstractUIActivator {

  /**
   */
  public static final String ADD_ITEM_IMAGE_ID = "toolitem.add"; //$NON-NLS-1$

  /**
   */
  public static final String REMOVE_IMAGE_ITEM_ID = "toolitem.remove";//$NON-NLS-1$


  /**
   */
  public static final String MOVE_DOWN_ITEM_IMAGE_ID = "toolitem.move_down"; //$NON-NLS-1$

  /**
   */
  public static final String MOVE_UP_ITEM_IMAGE_ID = "toolitem.move_up"; //$NON-NLS-1$

  // The toolkit plugin unique instance.
  private static ToolkitPlugin __instance;

  /**
   * Constructs the toolkit plugin instance.
   */
  public ToolkitPlugin() {
    __instance = this;
  }

  /**
   * Gets the unique toolkit activator instance.
   * @return The toolkit plugin.
   */
  public static ToolkitPlugin getDefault() {
    return __instance;
  }

  /**
   * @see org.eclipse.ui.plugin.AbstractUIPlugin#initializeImageRegistry(org.eclipse.jface.resource.ImageRegistry)
   */
  @Override
  protected void initializeImageRegistry(ImageRegistry registry) {
    ImageDescriptor imgDescriptor = ImageDescriptor.createFromURL(getIconURL("add_element.gif")); //$NON-NLS-1$
    registry.put(ToolkitPlugin.ADD_ITEM_IMAGE_ID, imgDescriptor.createImage());
    imgDescriptor = ImageDescriptor.createFromURL(getIconURL("delete_element.gif")); //$NON-NLS-1$
    registry.put(ToolkitPlugin.REMOVE_IMAGE_ITEM_ID, imgDescriptor.createImage());

    imgDescriptor = ImageDescriptor.createFromURL(getIconURL("arrow_down.gif")); //$NON-NLS-1$
    registry.put(ToolkitPlugin.MOVE_DOWN_ITEM_IMAGE_ID, imgDescriptor.createImage());
    imgDescriptor = ImageDescriptor.createFromURL(getIconURL("arrow_up.gif")); //$NON-NLS-1$
    registry.put(ToolkitPlugin.MOVE_UP_ITEM_IMAGE_ID, imgDescriptor.createImage());
  }

  /**
   * Gets the URL from the specified filename into the bundle icons directory.
   */
  private URL getIconURL(String filename) {
    return ToolkitPlugin.class.getResource("icons/" + filename); //$NON-NLS-1$
  }

  /**
   * @see org.eclipse.ui.plugin.AbstractUIPlugin#start(org.osgi.framework.BundleContext)
   */
  @Override
  public void start(BundleContext context) throws Exception {
    super.start(context);
  }

  /**
   * @see org.eclipse.ui.plugin.AbstractUIPlugin#stop(org.osgi.framework.BundleContext)
   */
  @Override
  public void stop(BundleContext context) throws Exception {
    super.stop(context);
  }

  /**
   * Checks if a transaction is running or not.
   * @return <code>True</code> if a transaction is running or <code>false</code> if none.
   */
  public boolean isTransactionRunning() {
    return true;
  }
}
