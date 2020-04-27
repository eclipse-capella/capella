/*******************************************************************************
 * Copyright (c) 2006, 2017 THALES GLOBAL SERVICES.
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
package org.polarsys.capella.core.platform.sirius.ui.actions;

import org.osgi.framework.BundleContext;
import org.polarsys.capella.common.ui.services.AbstractUIActivator;
import org.polarsys.capella.core.model.preferences.DeletePreferences;
import org.polarsys.capella.core.platform.sirius.ui.preferences.ActionsPreferenceInitializer;
import org.polarsys.capella.core.platform.sirius.ui.preferences.CapellaValidationPreferencesInitializer;

/**
 * Nothing specific to implement here. There is just to take advantage of AbstractUIActivator services.
 */
public class CapellaActionsActivator extends AbstractUIActivator {

  /*
   * 
   */
  public static final String PLUGIN_ID = "org.polarsys.capella.core.platform.sirius.ui.actions"; //$NON-NLS-1$

  /**
   * Shared instance.
   */
  private static CapellaActionsActivator plugin;

  /**
   * @see org.eclipse.ui.plugin.AbstractUIPlugin#start(org.osgi.framework.BundleContext)
   */
  @Override
  public void start(BundleContext context) throws Exception {
    super.start(context);
    plugin = this;
    new DeletePreferences();
    new CapellaValidationPreferencesInitializer();
    new ActionsPreferenceInitializer();

  }

  /**
   * @see org.eclipse.ui.plugin.AbstractUIPlugin#stop(org.osgi.framework.BundleContext)
   */
  @Override
  public void stop(BundleContext context) throws Exception {
    plugin = null;
    super.stop(context);
  }

  /**
   * Get shared instance.
   * @return
   */
  public static CapellaActionsActivator getDefault() {
    return plugin;
  }

}
