/*******************************************************************************
 * Copyright (c) 2017 THALES GLOBAL SERVICES.
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
package org.polarsys.capella.core.ui.properties.richtext;

import org.osgi.framework.BundleContext;
import org.polarsys.capella.common.ui.services.AbstractUIActivator;

/**
 * The activator class controls the plug-in life cycle
 * 
 * @author Joao Barata
 */
public class CapellaUIPropertiesRichtextPlugin extends AbstractUIActivator {

    // The plug-in ID
    public static final String PLUGIN_ID = "org.polarsys.capella.core.ui.properties.richtext"; //$NON-NLS-1$

    /**
     * The shared instance
     */
    private static CapellaUIPropertiesRichtextPlugin __plugin;

    /**
     * @see org.eclipse.ui.plugin.AbstractUIPlugin#start(org.osgi.framework.BundleContext)
     */
    @Override
    public void start(BundleContext context) throws Exception {
        super.start(context);
        __plugin = this;
    }

    /**
     * @see org.eclipse.ui.plugin.AbstractUIPlugin#stop(org.osgi.framework.BundleContext)
     */
    @Override
    public void stop(BundleContext context) throws Exception {
        __plugin = null;
        super.stop(context);
    }

    /**
     * @return the shared instance
     */
    public static CapellaUIPropertiesRichtextPlugin getDefault() {
        return __plugin;
    }
}
