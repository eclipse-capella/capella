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
package org.polarsys.capella.core.explorer.activity.ui;

import org.eclipse.swt.widgets.Display;
import org.eclipse.ui.forms.FormColors;
import org.osgi.framework.BundleContext;
import org.polarsys.capella.common.ui.services.AbstractUIActivator;

public class CapellaActivityExplorerActivator extends AbstractUIActivator {

	private static BundleContext context;

	// The plug-in ID
	public static final String PLUGIN_ID = "org.polarsys.capella.core.explorer.activity.ui.CapellaActivityExplorerActivator"; //$NON-NLS-1$

	/**
	 * The shared instance
	 */
	private static CapellaActivityExplorerActivator __plugin;
	/**
	 * Shared Form colors.
	 */
	private FormColors _formColors;



	static BundleContext getContext() {
		return context;
	}

	/*
	 * (non-Javadoc)
	 * @see org.osgi.framework.BundleActivator#start(org.osgi.framework.BundleContext)
	 */
	public void start(BundleContext bundleContext) throws Exception {
		CapellaActivityExplorerActivator.context = bundleContext;
		CapellaActivityExplorerActivator.__plugin = this;
	}

	/*
	 * (non-Javadoc)
	 * @see org.osgi.framework.BundleActivator#stop(org.osgi.framework.BundleContext)
	 */
	public void stop(BundleContext bundleContext) throws Exception {
		try {
			if (null != _formColors) {
				_formColors.dispose();
				_formColors = null;
			}
		} finally {
			__plugin = null;
			CapellaActivityExplorerActivator.context = null;
		}
	}

	/**
	 * Returns the shared instance
	 * @return the shared instance
	 */
	public static CapellaActivityExplorerActivator getDefault() {
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
