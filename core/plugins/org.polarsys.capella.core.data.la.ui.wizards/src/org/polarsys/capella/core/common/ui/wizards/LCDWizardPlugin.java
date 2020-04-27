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
package org.polarsys.capella.core.common.ui.wizards;

import org.eclipse.jface.resource.ImageDescriptor;
import org.eclipse.jface.resource.ImageRegistry;
import org.eclipse.ui.plugin.AbstractUIPlugin;
import org.osgi.framework.BundleContext;

import org.polarsys.capella.common.ui.services.AbstractUIActivator;
import org.polarsys.capella.core.ui.toolkit.decomposition.DecompositionUtil;

/**
 * 
 */
public class LCDWizardPlugin extends AbstractUIActivator {
  // The shared instance
  private static LCDWizardPlugin plugin;

  /**
   * The constructor.
   */
  public LCDWizardPlugin() {
    // Do nothing.
  }

  /**
   * @see AbstractUIPlugin#start(BundleContext)
   */
  @Override
  public void start(BundleContext context) throws Exception {
    super.start(context);
    plugin = this;
  }

  /**
   * @see AbstractUIPlugin#stop(BundleContext)
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
  public static LCDWizardPlugin getDefault() {
    return plugin;
  }

  @Override
  protected void initializeImageRegistry(ImageRegistry registry) {
    addLightbulbImages(registry);
  }

  /**
   * Add light bulb images to image registry.
   * @param registry The image registry.
   */
  private void addLightbulbImages(ImageRegistry registry) {
    ImageDescriptor imgDescriptor = getIcon("green.gif"); //$NON-NLS-1$
    registry.put(DecompositionUtil.INTERFACE_ONCE_ASSIGNED_ID, imgDescriptor);

    imgDescriptor = getIcon("red.gif"); //$NON-NLS-1$
    registry.put(DecompositionUtil.INTERFACE_UNASSIGNED_ID, imgDescriptor);

    imgDescriptor = getIcon("orange.gif"); //$NON-NLS-1$
    registry.put(DecompositionUtil.INTERFACE_TWICE_ASSIGNED_ID, imgDescriptor);
  }

  private ImageDescriptor getIcon(String filename) {
    return AbstractUIPlugin.imageDescriptorFromPlugin(getPluginId(), "icons/" + filename); //$NON-NLS-1$
  }
}
