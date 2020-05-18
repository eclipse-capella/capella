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
package org.polarsys.capella.patterns.ui;

import org.eclipse.ui.plugin.AbstractUIPlugin;
import org.osgi.framework.BundleContext;


/**
 * The activator class for this plug-in
 */
public class PatternsUIPlugin extends AbstractUIPlugin {

  /** The shared instance */
  private static PatternsUIPlugin __plugin;

  /** The plug-in ID */
  public static final String PLUGIN_ID = "org.polarsys.capella.patterns.ui"; //$NON-NLS-1$

  /**
   * Constructor
   */
  public PatternsUIPlugin() {

  }



  /**
   * Return the shared instance of the activator
   */
  public static PatternsUIPlugin getDefault() {
    return __plugin;
  }

  

  /**
   * Return the ID of this plug-in according to MANIFEST.MF
   */
  public String getPluginId() {
    return getBundle().getSymbolicName();
  }

  

  /**
   * @see org.eclipse.core.runtime.Plugin#start(org.osgi.framework.BundleContext)
   */
  @Override
  public void start(BundleContext context) throws Exception {
    super.start(context);
    __plugin = this;
  }

  /**
   * @see org.eclipse.core.runtime.Plugin#stop(org.osgi.framework.BundleContext)
   */
  @Override
  public void stop(BundleContext context) throws Exception {
    __plugin = null;
    super.stop(context);
  }

}
