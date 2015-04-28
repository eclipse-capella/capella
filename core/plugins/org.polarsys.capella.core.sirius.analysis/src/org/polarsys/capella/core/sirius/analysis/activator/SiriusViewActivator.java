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
package org.polarsys.capella.core.sirius.analysis.activator;

import java.util.HashSet;
import java.util.Set;

import org.eclipse.core.runtime.preferences.DefaultScope;
import org.eclipse.core.runtime.preferences.IEclipsePreferences;
import org.eclipse.sirius.business.api.componentization.ViewpointRegistry;
import org.eclipse.sirius.query.legacy.AcceleoInterpreterPlugin;
import org.eclipse.sirius.query.legacy.preferences.AcceleoPreferenceConstants;
import org.eclipse.sirius.viewpoint.description.Viewpoint;
import org.eclipse.ui.plugin.AbstractUIPlugin;
import org.osgi.framework.BundleContext;

public class SiriusViewActivator extends AbstractUIPlugin {

    public static final String ID = "org.polarsys.capella.core.sirius.analysis"; //$NON-NLS-1$

    private static SiriusViewActivator instance;

    private Set<Viewpoint> viewpoints;

    public SiriusViewActivator() {
      //
    }

    /**
     * {@inheritDoc}
     * 
     * @see org.eclipse.ui.plugin.AbstractUIPlugin#start(org.osgi.framework.BundleContext)
     */
    @Override
    public void start(BundleContext context) throws Exception {
        super.start(context);
        instance = this;
        viewpoints = new HashSet<Viewpoint>();
        viewpoints.addAll(ViewpointRegistry.getInstance().registerFromPlugin("/" + ID + "/description/common.odesign")); //$NON-NLS-1$ //$NON-NLS-2$
        viewpoints.addAll(ViewpointRegistry.getInstance().registerFromPlugin("/" + ID + "/description/oa.odesign")); //$NON-NLS-1$ //$NON-NLS-2$
        viewpoints.addAll(ViewpointRegistry.getInstance().registerFromPlugin("/" + ID + "/description/context.odesign")); //$NON-NLS-1$ //$NON-NLS-2$
        viewpoints.addAll(ViewpointRegistry.getInstance().registerFromPlugin("/" + ID + "/description/logical.odesign")); //$NON-NLS-1$ //$NON-NLS-2$
        viewpoints.addAll(ViewpointRegistry.getInstance().registerFromPlugin("/" + ID + "/description/physical.odesign")); //$NON-NLS-1$ //$NON-NLS-2$
        viewpoints.addAll(ViewpointRegistry.getInstance().registerFromPlugin("/" + ID + "/description/EPBS.odesign")); //$NON-NLS-1$ //$NON-NLS-2$
        
        //use java services before specific acceleo files
        IEclipsePreferences preferences = DefaultScope.INSTANCE.getNode(AcceleoInterpreterPlugin.PLUGIN_ID);
        preferences.putBoolean(AcceleoPreferenceConstants.PREF_USE_SPECIFIC_SCRIPTS_FIRST, false);
    }

    /**
     * {@inheritDoc}
     * 
     * @see org.eclipse.ui.plugin.AbstractUIPlugin#stop(org.osgi.framework.BundleContext)
     */
    @Override
    public void stop(BundleContext context) throws Exception {
        if (viewpoints != null) {
            for (Viewpoint viewpoint : viewpoints) {
                ViewpointRegistry.getInstance().disposeFromPlugin(viewpoint);
            }
            viewpoints.clear();
        }
    }

    /**
     * @generated
     */
    public static SiriusViewActivator getInstance() {
        return instance;
    }
}
