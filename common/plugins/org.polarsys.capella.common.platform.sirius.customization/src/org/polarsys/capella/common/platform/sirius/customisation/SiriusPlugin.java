/*******************************************************************************
 * Copyright (c) 2006, 2017 THALES GLOBAL SERVICES.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *  
 * Contributors:
 *    Thales - initial API and implementation
 *******************************************************************************/
package org.polarsys.capella.common.platform.sirius.customisation;

import org.eclipse.sirius.business.api.preferences.SiriusPreferencesKeys;
import org.eclipse.sirius.ui.business.api.preferences.SiriusUIPreferencesKeys;
import org.eclipse.sirius.viewpoint.provider.SiriusEditPlugin;
import org.eclipse.ui.IWorkbenchPreferenceConstants;
import org.eclipse.ui.PlatformUI;
import org.eclipse.ui.plugin.AbstractUIPlugin;
import org.osgi.framework.BundleContext;
import org.polarsys.capella.common.platform.sirius.customisation.uicallback.SiriusUiCallBack;

/**
 * The activator class controls the plug-in life cycle.
 * 
 */
public class SiriusPlugin extends AbstractUIPlugin {

  /** The plug-in ID. */
  public static final String PLUGIN_ID = "org.polarsys.capella.common.platform.sirius.customisation"; //$NON-NLS-1$

  // The shared instance
  private static SiriusPlugin plugin;

  /**
   * The default constructor.
   */
  public SiriusPlugin() {
  }

  /**
   * {@inheritDoc}
   * 
   * @see org.eclipse.ui.plugin.AbstractUIPlugin#start(org.osgi.framework.BundleContext)
   */
  @Override
public void start(BundleContext context) throws Exception {
    super.start(context);
    plugin = this;
    // Initialize the preferences for Sirius
    PlatformUI.getPreferenceStore().setValue(IWorkbenchPreferenceConstants.PROMPT_WHEN_SAVEABLE_STILL_OPEN, false);

    // Change the UICallBack to have a specific Session displayed name
    SiriusEditPlugin.getPlugin().setUiCallback(new SiriusUiCallBack(SiriusEditPlugin.getPlugin().getUiCallback()));

    // Allow by default aird fragment with no representation creation
    SiriusEditPlugin.getPlugin().getPreferenceStore()
        .setValue(SiriusPreferencesKeys.PREF_EMPTY_AIRD_FRAGMENT_ON_CONTROL.name(), true);

    // Deactivate by default Sirius scale option (Use 0 to have similar
    // behavior as before).
    SiriusEditPlugin.getPlugin().getPreferenceStore().setDefault(SiriusUIPreferencesKeys.PREF_SCALE_LEVEL_DIAGRAMS_ON_EXPORT.name(), 0);
  }

  /**
   * {@inheritDoc}
   * 
   * @see org.eclipse.ui.plugin.AbstractUIPlugin#stop(org.osgi.framework.BundleContext)
   */
  @Override
public void stop(BundleContext context) throws Exception {
    plugin = null;
    super.stop(context);
  }

  /**
   * Returns the shared instance.
   * 
   * @return the shared instance
   */
  public static SiriusPlugin getDefault() {
    return plugin;
  }
}
