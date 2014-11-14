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
package org.polarsys.capella.core.dashboard;

import org.eclipse.swt.widgets.Display;
import org.eclipse.ui.forms.FormColors;
import org.osgi.framework.BundleContext;

import org.polarsys.capella.common.ui.services.AbstractUIActivator;
import org.polarsys.capella.core.dashboard.editor.pages.preferences.CapellaDashboardPagesPreferencesInitializer;

/**
 * The activator class controls the plug-in life cycle
 */
public class CapellaDashboardActivator extends AbstractUIActivator {

  // The plug-in ID
  public static final String PLUGIN_ID = "org.polarsys.capella.core.dashboard.CapellaDashboardActivator"; //$NON-NLS-1$

  /**
   * The shared instance
   */
  private static CapellaDashboardActivator __plugin;
  /**
   * Shared Form colors.
   */
  private FormColors _formColors;

  /**
   * @see org.eclipse.ui.plugin.AbstractUIPlugin#start(org.osgi.framework.BundleContext)
   */
  @Override
  public void start(BundleContext context) throws Exception {
    super.start(context);
    __plugin = this;
    new CapellaDashboardPagesPreferencesInitializer();

  }

  /**
   * @see org.eclipse.ui.plugin.AbstractUIPlugin#stop(org.osgi.framework.BundleContext)
   */
  @Override
  public void stop(BundleContext context) throws Exception {
    try {
      if (null != _formColors) {
        _formColors.dispose();
        _formColors = null;
      }
    } finally {
      __plugin = null;
      super.stop(context);
    }
  }

  /**
   * Returns the shared instance
   * @return the shared instance
   */
  public static CapellaDashboardActivator getDefault() {
    return __plugin;
  }

  /**
   * Get shared form colors.
   * @param display
   * @return
   */
  public FormColors getFormColors(Display display) {
    if (null == _formColors) {
      _formColors = new FormColors(display);
      _formColors.markShared();
    }
    return _formColors;
  }
}
