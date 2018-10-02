/*******************************************************************************
 * Copyright (c) 2006, 2018 THALES GLOBAL SERVICES.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *  
 * Contributors:
 *    Thales - initial API and implementation
 *******************************************************************************/
package org.polarsys.capella.common.platform.sirius.customisation;

import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.Status;
import org.eclipse.jface.preference.IPreferenceStore;
import org.eclipse.sirius.business.api.preferences.SiriusPreferencesKeys;
import org.eclipse.sirius.common.tools.api.constant.CommonPreferencesConstants;
import org.eclipse.sirius.common.ui.SiriusTransPlugin;
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
    // Do all initialization inside a try-catch block to ensure plug-in start normally
    try {
      // Initialize the preferences for Sirius
      PlatformUI.getPreferenceStore().setValue(IWorkbenchPreferenceConstants.PROMPT_WHEN_SAVEABLE_STILL_OPEN, false);

      // Disable Sirius Pre-commit listener behavior since Capella has the same one.
      SiriusTransPlugin.getPlugin().getPreferenceStore().setValue(CommonPreferencesConstants.PREF_DEFENSIVE_EDIT_VALIDATION, false);
      
      // Change the UICallBack to have a specific Session displayed name
      SiriusEditPlugin.getPlugin().setUiCallback(new SiriusUiCallBack(SiriusEditPlugin.getPlugin().getUiCallback()));
      
      IPreferenceStore store = SiriusEditPlugin.getPlugin().getPreferenceStore();
      
      // Allow by default aird fragment with no representation creation
      store.setValue(SiriusPreferencesKeys.PREF_EMPTY_AIRD_FRAGMENT_ON_CONTROL.name(), true);

      // Required since Sirius 5.1 since default behavior is not welcome
      store.setValue(SiriusUIPreferencesKeys.PREF_SAVE_WHEN_NO_EDITOR.name(), false);
      store.setValue(SiriusUIPreferencesKeys.PREF_RELOAD_ON_LAST_EDITOR_CLOSE.name(), false);
      
      // Set the default Sirius scale option (20%).
      store.setDefault(SiriusUIPreferencesKeys.PREF_SCALE_LEVEL_DIAGRAMS_ON_EXPORT.name(), 2);

      // Don't use colors from odesign in diagram palettes
      store.setDefault(SiriusUIPreferencesKeys.PREF_DISPLAY_VSM_USER_FIXED_COLOR_IN_PALETTE.name(), false);

    } catch (Exception e) {
      getLog().log(new Status(IStatus.ERROR, PLUGIN_ID, IStatus.OK, e.getMessage(), e));
    }
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
