/*******************************************************************************
 * Copyright (c) 2006, 2020 THALES GLOBAL SERVICES.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *  
 * Contributors:
 *    Thales - initial API and implementation
 *******************************************************************************/

package org.polarsys.capella.core.platform.sirius.ui;

import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.Status;
import org.eclipse.core.runtime.preferences.DefaultScope;
import org.eclipse.core.runtime.preferences.IEclipsePreferences;
import org.eclipse.gmf.runtime.notation.JumpLinkStatus;
import org.eclipse.gmf.runtime.notation.JumpLinkType;
import org.eclipse.sirius.diagram.DiagramPlugin;
import org.eclipse.sirius.diagram.tools.api.preferences.SiriusDiagramCorePreferences;
import org.eclipse.ui.plugin.AbstractUIPlugin;
import org.osgi.framework.BundleContext;

/**
 * The activator class controls the plug-in life cycle
 */
public class PerspectivePlugin extends AbstractUIPlugin {
  /**
   * The shared instance
   */
  private static PerspectivePlugin __plugin;

  /*
   * 
   */
  public static final String PLUGIN_ID = "org.polarsys.capella.core.platform.sirius.ui.perspective"; //$NON-NLS-1$

  /**
   * @see org.eclipse.ui.plugin.AbstractUIPlugin#start(org.osgi.framework.BundleContext)
   */
  @Override
  public void start(BundleContext context_p) throws Exception {
    super.start(context_p);
    __plugin = this;
    try {
      IEclipsePreferences diagramCoreDefaultPreferences = DefaultScope.INSTANCE.getNode(DiagramPlugin.ID);

      // By default override the Jump Link properties default values
      diagramCoreDefaultPreferences.putBoolean(SiriusDiagramCorePreferences.PREF_JUMP_LINK_ENABLE_OVERRIDE, true);
      // Set the jump link status as "Above"
      diagramCoreDefaultPreferences.putInt(SiriusDiagramCorePreferences.PREF_JUMP_LINK_STATUS, JumpLinkStatus.ABOVE);
      // Set the jump link type as "Tunnel"
      diagramCoreDefaultPreferences.putInt(SiriusDiagramCorePreferences.PREF_JUMP_LINK_TYPE, JumpLinkType.TUNNEL);
    } catch (Exception e) {
      getLog().log(new Status(IStatus.ERROR, PLUGIN_ID, IStatus.OK, e.getMessage(), e));
    }
  }

  /**
   * @see org.eclipse.ui.plugin.AbstractUIPlugin#stop(org.osgi.framework.BundleContext)
   */
  @Override
  public void stop(BundleContext context_p) throws Exception {
    super.stop(context_p);
    __plugin = null;
  }

  /**
   * Returns the shared instance
   * @return the shared instance
   */
  public static PerspectivePlugin getDefault() {
    return __plugin;
  }
}
