/*******************************************************************************
 * Copyright (c) 2006, 2015 THALES GLOBAL SERVICES.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *  
 * Contributors:
 *    Thales - initial API and implementation
 *******************************************************************************/
package org.polarsys.capella.test.business.queries.ju;

import org.osgi.framework.BundleContext;
import org.polarsys.capella.common.ui.services.AbstractUIActivator;

/**
 * The activator class controls the plug-in life cycle
 */
public class TestBusinessQueriesPlugin extends AbstractUIActivator {

	// The plug-in ID
	public static final String PLUGIN_ID = "org.polarsys.capella.test.business.queries.ju"; //$NON-NLS-1$

	// The shared instance
	private static TestBusinessQueriesPlugin plugin;
		
	/**
	 * The constructor
	 */
	public TestBusinessQueriesPlugin() {
	  // Do Nothing
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
	 *
	 * @return the shared instance
	 */
	public static TestBusinessQueriesPlugin getDefault() {
		return plugin;
	}

}
