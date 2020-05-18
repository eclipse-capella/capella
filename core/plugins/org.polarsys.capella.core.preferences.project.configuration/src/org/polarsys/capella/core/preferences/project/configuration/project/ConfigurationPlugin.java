/*******************************************************************************
 * Copyright (c) 2006, 2014 THALES GLOBAL SERVICES.
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
package org.polarsys.capella.core.preferences.project.configuration.project;

import java.net.URL;

import org.eclipse.core.runtime.FileLocator;
import org.eclipse.core.runtime.Path;
import org.eclipse.core.runtime.Plugin;
import org.eclipse.jface.resource.ImageDescriptor;
import org.eclipse.jface.viewers.DecorationOverlayIcon;
import org.eclipse.jface.viewers.IDecoration;
import org.eclipse.swt.graphics.Image;
import org.osgi.framework.BundleContext;

public class ConfigurationPlugin extends Plugin {

  private static ConfigurationPlugin plugin;

  // The plug-in ID
  public static final String PLUGIN_ID = "org.polarsys.capella.core.preferences.project.configuration";//$NON-NLS-1$

  private static final String ICONS = "icons/"; //$NON-NLS-1$

  public static final String PROJECT_WIZARD_CONFIGURATION_FOLDER_IMG = "config_project_wizard.gif"; //$NON-NLS-1$

  /**
   * The constructor
   */
  public ConfigurationPlugin() {
    super();
  }

  /**
   * @see org.eclipse.core.runtime.Plugins#start(org.osgi.framework.BundleContext)
   */
  @Override
  public void start(BundleContext context) throws Exception {
    super.start(context);
    plugin = this;
  }

  /**
   * @see org.eclipse.core.runtime.Plugin#stop(org.osgi.framework.BundleContext)
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
  public static ConfigurationPlugin getDefault() {
    return plugin;
  }

  /**
   * <p>
   * Returns an {@link ImageDescriptor}whose path, relative to the plugin directory's <tt>icons/</tt> directory, is <code>imageFile</code>. If the image
   * descriptor cannot be created, either because the file does not exist or because of an internal error, then the result is the Eclipse default
   * "missing image" descriptor.
   * </p>
   * <p>
   * <b>Note </b> that the file specified must not have any leading "." or path separators "/" or "\". It is strictly relative to the <tt>icons/</tt> directory.
   * @param imageFile the name of the image file to retrieve
   * @return the corresponding image descriptor
   */
  public static ImageDescriptor getImageDescriptor(String imageFile) {
    URL fullPath = FileLocator.find(getDefault().getBundle(), new Path(ICONS + imageFile), null);
    if (fullPath != null) {
      return ImageDescriptor.createFromURL(fullPath);
    }

    return ImageDescriptor.getMissingImageDescriptor();
  }

  public static ImageDescriptor getWizadrConfigurationProjectIcon() {

    return getImageDescriptor(ConfigurationPlugin.PROJECT_WIZARD_CONFIGURATION_FOLDER_IMG); 
  }

  /**
   * @param baseImage
   * @param decoratorPath
   * @return
   */
  public static ImageDescriptor getOverlayedDescriptor(final Image baseImage, final String decoratorPath) {
    final ImageDescriptor decoratorDescriptor = ConfigurationPlugin.getImageDescriptor(decoratorPath);
    return new DecorationOverlayIcon(baseImage, decoratorDescriptor, IDecoration.TOP_RIGHT);
  }
}
