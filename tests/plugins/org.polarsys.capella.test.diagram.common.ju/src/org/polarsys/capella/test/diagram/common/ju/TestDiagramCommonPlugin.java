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
package org.polarsys.capella.test.diagram.common.ju;

import org.eclipse.ui.plugin.AbstractUIPlugin;
import org.osgi.framework.BundleContext;
import org.polarsys.capella.test.diagram.common.ju.headless.ExternalJavaActionForTestTools;

/**
 * The activator class controls the plug-in life cycle
 */
public class TestDiagramCommonPlugin extends AbstractUIPlugin {

	// The plug-in ID
	public static final String PLUGIN_ID = "org.polarsys.capella.test.diagram.common.ju"; //$NON-NLS-1$

	// The shared instance
	private static TestDiagramCommonPlugin plugin;
	
	/**
	 * The constructor
	 */
	public TestDiagramCommonPlugin() {
	}

	/**
	 * @see org.eclipse.ui.plugin.AbstractUIPlugin#start(org.osgi.framework.BundleContext)
	 */
	public void start(BundleContext context) throws Exception {
		super.start(context);
		plugin = this;

		//Modify the extension registry in order to perform test
    // without any UI "interactions" expected from users.
    ExternalJavaActionForTestTools.INSTANCE.init();
	}

	/**
	 * @see org.eclipse.ui.plugin.AbstractUIPlugin#stop(org.osgi.framework.BundleContext)
	 */
	public void stop(BundleContext context) throws Exception {
    
    //Restore the extension registry to its initial state
    ExternalJavaActionForTestTools.INSTANCE.restore();

    plugin = null;
		super.stop(context);
	}

	/**
	 * Returns the shared instance
	 *
	 * @return the shared instance
	 */
	public static TestDiagramCommonPlugin getDefault() {
		return plugin;
	}

}
