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
  private static LCDWizardPlugin __plugin;

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
    __plugin = this;
  }

  /**
   * @see AbstractUIPlugin#stop(BundleContext)
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
  public static LCDWizardPlugin getDefault() {
    return __plugin;
  }

  @Override
  protected void initializeImageRegistry(ImageRegistry registry_p) {
    addLightbulbImages(registry_p);
  }

  /**
   * Add light bulb images to image registry.
   * @param registry_p The image registry.
   */
  private void addLightbulbImages(ImageRegistry registry_p) {
    ImageDescriptor imgDescriptor = getIcon("green.gif"); //$NON-NLS-1$
    registry_p.put(DecompositionUtil.INTERFACE_ONCE_ASSIGNED_ID, imgDescriptor);

    imgDescriptor = getIcon("red.gif"); //$NON-NLS-1$
    registry_p.put(DecompositionUtil.INTERFACE_UNASSIGNED_ID, imgDescriptor);

    imgDescriptor = getIcon("orange.gif"); //$NON-NLS-1$
    registry_p.put(DecompositionUtil.INTERFACE_TWICE_ASSIGNED_ID, imgDescriptor);
  }

  private ImageDescriptor getIcon(String filename_p) {
    return AbstractUIPlugin.imageDescriptorFromPlugin(getPluginId(), "icons/" + filename_p); //$NON-NLS-1$
  }
}
