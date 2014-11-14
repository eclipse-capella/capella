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
package org.polarsys.capella.common.consonance.ui.sirius;

import org.eclipse.ui.plugin.AbstractUIPlugin;
import org.osgi.framework.BundleContext;


/**
 * The activator for this plug-in.
 */
public class SiriusComparePlugin extends AbstractUIPlugin {
  
	/** The plug-in ID */
	public static final String PLUGIN_ID = "org.polarsys.capella.common.consonance.ui.sirius"; //$NON-NLS-1$
  
	/** The shared instance */
	private static SiriusComparePlugin plugin;
  
  
	/**
	 * Constructor
	 */
	public SiriusComparePlugin() {
	  // Nothing needed
	}
  
  /**
   * Return the shared instance
   * @return a non-null instance of this class
   */
  public static SiriusComparePlugin getDefault() {
    return plugin;
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
  
}
