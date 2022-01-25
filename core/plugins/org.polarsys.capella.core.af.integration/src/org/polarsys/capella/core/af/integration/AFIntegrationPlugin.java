/*******************************************************************************
 * Copyright (c) 2006, 2020 THALES GLOBAL SERVICES.
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
package org.polarsys.capella.core.af.integration;

import org.eclipse.core.runtime.Plugin;
import org.osgi.framework.BundleContext;

/**
 * Plugin activator
 */
@SuppressWarnings({"findbugs:ST_WRITE_TO_STATIC_FROM_INSTANCE_METHOD","squid:S2696"})
public class AFIntegrationPlugin extends Plugin {

	// The shared instance
	private static AFIntegrationPlugin plugin;

	// The identifier of the Capella "virtual" viewpoint
	public static final String CAPELLA_VIEWPOINT_ID = "org.polarsys.capella.core.viewpoint";
	
	/**
	 * The constructor
	 */
	public AFIntegrationPlugin() {
	}

	/**
	 * {@inheritDoc}
	 */
	public void start(BundleContext context) throws Exception {
		super.start(context);
		plugin = this;
	}

	/**
   * {@inheritDoc}
	 */
	public void stop(BundleContext context) throws Exception {
		plugin = null;
		super.stop(context);
	}

	/**
	 * Returns the shared instance
	 * 
	 * @return the shared instance
	 */
	public static AFIntegrationPlugin getDefault() {
		return plugin;
	}
}
