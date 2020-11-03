/*******************************************************************************
 * Copyright (c) 2017, 2020 THALES GLOBAL SERVICES.
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
package org.polarsys.capella.core.af.integration.ui;

import org.eclipse.ui.plugin.AbstractUIPlugin;
import org.osgi.framework.BundleContext;
import org.polarsys.capella.core.af.integration.ui.listener.CapellaExplorerUpdater;
import org.polarsys.capella.core.af.integration.ui.listener.SemanticBrowserUpdater;
import org.polarsys.kitalpha.emde.extension.ModelExtensionHelper;
import org.polarsys.kitalpha.emde.extension.ModelExtensionOverallListener;

/**
 * Plugin activator
 */
public class AFIntegrationUIPlugin extends AbstractUIPlugin {

	// The shared instance
	private static AFIntegrationUIPlugin plugin;

	//
	private final ModelExtensionOverallListener[] listeners = { new CapellaExplorerUpdater(), new SemanticBrowserUpdater() };

	/**
	 * The constructor
	 */
	public AFIntegrationUIPlugin() {
	}

	/**
	 * @see org.eclipse.ui.plugin.AbstractUIPlugin#start(org.osgi.framework.BundleContext)
	 */
	public void start(BundleContext context) throws Exception {
		super.start(context);
		plugin = this;
    for (ModelExtensionOverallListener l : listeners) {
      ModelExtensionHelper.addOverallListener(l);
    }
	}

	/**
	 * @see org.eclipse.ui.plugin.AbstractUIPlugin#stop(org.osgi.framework.BundleContext)
	 */
	public void stop(BundleContext context) throws Exception {
		plugin = null;
    for (ModelExtensionOverallListener l : listeners) {
      ModelExtensionHelper.removeOverallListener(l);
    }
		super.stop(context);
	}

	/**
	 * Returns the shared instance
	 * 
	 * @return the shared instance
	 */
	public static AFIntegrationUIPlugin getDefault() {
		return plugin;
	}

	public static String getSymbolicName() {
		return plugin.getBundle().getSymbolicName();
	}
}
