/*******************************************************************************
 *  Copyright (c) 2007, 2009 LCELB
 *  All rights reserved. This program and the accompanying materials
 *  are made available under the terms of the Eclipse Public License v1.0
 *  which accompanies this distribution, and is available at
 *  http://www.eclipse.org/legal/epl-v10.html
 * 
 *  Contributors:
 *      LCELB - initial API and implementation
 *******************************************************************************/
package org.polarsys.capella.common.ui.services;

import java.net.URL;

import org.eclipse.core.runtime.Platform;
import org.eclipse.core.runtime.Status;
import org.eclipse.jface.resource.ImageDescriptor;
import org.eclipse.jface.resource.ImageRegistry;
import org.eclipse.swt.graphics.Image;
import org.eclipse.ui.internal.util.BundleUtility;
import org.eclipse.ui.plugin.AbstractUIPlugin;
import org.osgi.framework.Bundle;

/**
 * Base class for plug-ins that integrate with the Eclipse platform UI.<br>
 */
public abstract class AbstractUIActivator extends AbstractUIPlugin {
  private static final String ICONS_PATH = "icons/"; //$NON-NLS-1$

  /**
   * Get an image descriptor for given key.<br>
   * Images must be located in 'plug-in folder'/icons
   * @param key_p the key must be the file name of the related image.
   * @return an {@link ImageDescriptor} or null if not found
   */
  public ImageDescriptor getImageDescriptor(String key_p) {
    ImageRegistry imageRegistry = getImageRegistry();
    ImageDescriptor imageDescriptor = imageRegistry.getDescriptor(key_p);

    if (null == imageDescriptor) {
      imageDescriptor = createImageDescriptor(key_p);
      imageRegistry.put(key_p, imageDescriptor);
    }
    return imageDescriptor;
  }

  /**
   * Get an image for given key.<br>
   * Images must be located in 'plug-in folder'/icons
   * @param key_p the key must be the file name of the related image.
   * @return an {@link Image} or null if not found
   */
  public Image getImage(String key_p) {
    ImageRegistry imageRegistry = getImageRegistry();

    Image image = imageRegistry.get(key_p);
    if (null == image) {
      // Create an image descriptor for given id.
      ImageDescriptor imageDescriptor = createImageDescriptor(key_p);
      // Store the (id, imageDescriptor) rather than (id,image)
      // because with storing (id,image) the getDescriptor method will return null in later usage
      // this way, everything is correctly initialized.
      imageRegistry.put(key_p, imageDescriptor);

      // Everything is all right at this step, let's get the real image
      image = imageRegistry.get(key_p);
    }
    return image;
  }

  /**
   * Create an image descriptor for given key.<br>
   * Images must be located in 'plug-in folder'/icons
   * @param key_p the key must be the file name of the related image.
   * @return an {@link ImageDescriptor} or null if error occurred
   */
  protected ImageDescriptor createImageDescriptor(String key_p) {
    ImageDescriptor imageDescriptor = AbstractUIPlugin.imageDescriptorFromPlugin(getPluginId(), ICONS_PATH + key_p);
    return imageDescriptor;
  }

  /**
   * Get the plug-in ID according to MANISFEST.MF definition.
   * @return a String containing the plug-in ID.
   */
  public String getPluginId() {
    return getBundle().getSymbolicName();
  }

  /**
   * Log a message in the Eclipse log file.
   * @param severity_p one of <code>IStatus.OK</code>, <code>IStatus.ERROR</code>, <code>IStatus.INFO</code>, <code>IStatus.WARNING</code>, or
   *          <code>IStatus.CANCEL</code>
   * @param message a human-readable message, localized to the current locale
   * @param exception a low-level exception, or <code>null</code> if not applicable
   */
  public void log(int severity_p, String message_p, Throwable exception_p) {
    getLog().log(new Status(severity_p, getPluginId(), message_p, exception_p));
  }

  /**
   * Get icon url for specified image key.<br>
   * Images must be located in 'plug-in folder'/icons
   * @param key_p the key must be the file name of the related image.
   * @return null if image file not found.
   */
  public URL getImageURL(String key_p) {
    Bundle bundle = Platform.getBundle(getPluginId());
    if (!BundleUtility.isReady(bundle)) {
      return null;
    }
    return BundleUtility.find(bundle, ICONS_PATH + key_p);
  }
}
