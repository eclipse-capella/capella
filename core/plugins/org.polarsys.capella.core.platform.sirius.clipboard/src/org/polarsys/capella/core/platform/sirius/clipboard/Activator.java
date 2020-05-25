/*******************************************************************************
 * Copyright (c) 2006, 2016 THALES GLOBAL SERVICES.
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
package org.polarsys.capella.core.platform.sirius.clipboard;

import org.eclipse.ui.plugin.AbstractUIPlugin;
import org.osgi.framework.BundleContext;

/**
 * The activator class controls the plug-in life cycle
 */
public class Activator extends AbstractUIPlugin {

  //The plug-in ID
  public static final String PLUGIN_ID = "org.polarsys.capella.core.platform.sirius.clipboard"; //$NON-NLS-1$

 // The shared instance
	private static Activator __plugin;

	// A label describing this plugin
	public static final String LABEL = Messages.Activator_Label;

	/**
	 * The constructor
	 */
	public Activator() {
		// Nothing
	}

	@Override
	public void start(BundleContext context) throws Exception {
		super.start(context);
		__plugin = this;
	}

	@Override
	public void stop(BundleContext context) throws Exception {
		__plugin = null;
		super.stop(context);
	}

	/**
	 * Returns the shared instance
	 * 
	 * @return the shared instance
	 */
	public static Activator getDefault() {
		return __plugin;
	}
}
