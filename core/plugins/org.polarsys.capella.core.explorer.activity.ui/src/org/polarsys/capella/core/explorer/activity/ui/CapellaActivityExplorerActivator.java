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
	private static CapellaActivityExplorerActivator plugin;
	/**
	 * Shared Form colors.
	 */
	private FormColors formColors;



	static BundleContext getContext() {
		return context;
	}

	/*
	 * (non-Javadoc)
	 * @see org.osgi.framework.BundleActivator#start(org.osgi.framework.BundleContext)
	 */
	public void start(BundleContext bundleContext) throws Exception {
		CapellaActivityExplorerActivator.context = bundleContext;
		CapellaActivityExplorerActivator.plugin = this;
	}

	/*
	 * (non-Javadoc)
	 * @see org.osgi.framework.BundleActivator#stop(org.osgi.framework.BundleContext)
	 */
	public void stop(BundleContext bundleContext) throws Exception {
		try {
			if (null != formColors) {
				formColors.dispose();
				formColors = null;
			}
		} finally {
			plugin = null;
			CapellaActivityExplorerActivator.context = null;
		}
	}

	/**
	 * Returns the shared instance
	 * @return the shared instance
	 */
	public static CapellaActivityExplorerActivator getDefault() {
		return plugin;
	}

	/**
	 * Get shared form colors.
	 * @param display
	 * @return
	 */
	public FormColors getFormColors(Display display) {
		if (null == formColors) {
			formColors = new FormColors(display);
			formColors.markShared();
		}
		return formColors;
	}
}
