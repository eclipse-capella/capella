/*******************************************************************************
 * Copyright (c) 2018 THALES GLOBAL SERVICES.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 * Thales - initial API and implementation
 *******************************************************************************/
package org.polarsys.capella.common.ui.massactions.activator;

import org.eclipse.ui.plugin.AbstractUIPlugin;
import org.osgi.framework.BundleContext;

/**
 * The activator class controls the plug-in life cycle
 * 
 * @author Sandu Postaru
 * 
 */
public class MACapellaActivator extends AbstractUIPlugin {

  // The plug-in ID
  public static final String PLUGIN_ID = "org.polarsys.capella.common.ui.massactions"; //$NON-NLS-1$
  public static final String ME_VIEW_ID = "org.polarsys.capella.common.ui.massactions.editing.view";
  public static final String MV_VIEW_ID = "org.polarsys.capella.common.ui.massactions.visualizing.view";

  public static final String SEND_TO_ME_VIEW_COMMAND_ID = "org.polarsys.capella.common.ui.massactions.command.SendToMassEditingView";
  public static final String SEND_TO_ME_VIEW_COMMAND_PARAMETER_PRIMARY_ID = "org.polarsys.capella.common.ui.massactions.command.SendToMassEditingView.primaryViewId";
  public static final String SEND_TO_ME_VIEW_COMMAND_PARAMETER_SECONDARY_ID = "org.polarsys.capella.common.ui.massactions.command.SendToMassEditingView.secondaryViewId";

  public static final String SEND_TO_MV_VIEW_COMMAND_ID = "org.polarsys.capella.common.ui.massactions.command.SendToMassVisualizationView";
  public static final String SEND_TO_MV_VIEW_COMMAND_PARAMETER_PRIMARY_ID = "org.polarsys.capella.common.ui.massactions.command.SendToMassVisualizationView.primaryViewId";
  public static final String SEND_TO_MV_VIEW_COMMAND_PARAMETER_SECONDARY_ID = "org.polarsys.capella.common.ui.massactions.command.SendToMassVisualizationView.secondaryViewId";

  // The shared instance
  private static MACapellaActivator plugin;

  /**
   * The constructor
   */
  public MACapellaActivator() {
    //
  }

  /*
   * (non-Javadoc)
   * 
   * @see org.eclipse.ui.plugin.AbstractUIPlugin#start(org.osgi.framework.BundleContext)
   */
  @Override
  public void start(BundleContext context) throws Exception {
    super.start(context);
    plugin = this;
  }

  /*
   * (non-Javadoc)
   * 
   * @see org.eclipse.ui.plugin.AbstractUIPlugin#stop(org.osgi.framework.BundleContext)
   */
  @Override
  public void stop(BundleContext context) throws Exception {
    plugin = null;
    super.stop(context);
  }

  /**
   * Returns the shared instance
   *
   * @return the shared instance
   */
  public static MACapellaActivator getDefault() {
    return plugin;
  }

}
