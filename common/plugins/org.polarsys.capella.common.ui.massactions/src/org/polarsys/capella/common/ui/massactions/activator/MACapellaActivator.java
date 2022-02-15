/*******************************************************************************
 * Copyright (c) 2018, 2020 THALES GLOBAL SERVICES.
 * 
 * This program and the accompanying materials are made available under the
 * terms of the Eclipse Public License 2.0 which is available at
 * http://www.eclipse.org/legal/epl-2.0
 * 
 * SPDX-License-Identifier: EPL-2.0
 *
 * Contributors:
 * Thales - initial API and implementation
 *******************************************************************************/
package org.polarsys.capella.common.ui.massactions.activator;

import java.net.URL;

import org.eclipse.core.runtime.FileLocator;
import org.eclipse.core.runtime.IPath;
import org.eclipse.core.runtime.Path;
import org.eclipse.jface.resource.ImageDescriptor;
import org.eclipse.jface.resource.ImageRegistry;
import org.eclipse.ui.plugin.AbstractUIPlugin;
import org.osgi.framework.Bundle;
import org.osgi.framework.BundleContext;

/**
 * The activator class controls the plug-in life cycle
 * 
 * @author Sandu Postaru
 * 
 */
public class MACapellaActivator extends AbstractUIPlugin {

  public static final String ME_VIEW_ID = "org.polarsys.capella.common.ui.massactions.editing.view";
  public static final String MV_VIEW_ID = "org.polarsys.capella.common.ui.massactions.visualizing.view";

  public static final String SEND_TO_ME_VIEW_COMMAND_ID = "org.polarsys.capella.common.ui.massactions.command.SendToMassEditingView";
  public static final String SEND_TO_ME_VIEW_COMMAND_PARAMETER_PRIMARY_ID = "org.polarsys.capella.common.ui.massactions.command.SendToMassEditingView.primaryViewId";
  public static final String SEND_TO_ME_VIEW_COMMAND_PARAMETER_SECONDARY_ID = "org.polarsys.capella.common.ui.massactions.command.SendToMassEditingView.secondaryViewId";
  public static final String SEND_TO_ME_VIEW_COMMAND_PARAMTER_SHOULD_CREATE_VIEW_ID = "org.polarsys.capella.common.ui.massactions.command.SendToMassEditingView.shouldCreateViewId";

  public static final String SEND_TO_MV_VIEW_COMMAND_ID = "org.polarsys.capella.common.ui.massactions.command.SendToMassVisualizationView";
  public static final String SEND_TO_MV_VIEW_COMMAND_PARAMETER_PRIMARY_ID = "org.polarsys.capella.common.ui.massactions.command.SendToMassVisualizationView.primaryViewId";
  public static final String SEND_TO_MV_VIEW_COMMAND_PARAMETER_SECONDARY_ID = "org.polarsys.capella.common.ui.massactions.command.SendToMassVisualizationView.secondaryViewId";
  public static final String SEND_TO_MV_VIEW_COMMAND_PARAMTER_SHOULD_CREATE_VIEW_ID = "org.polarsys.capella.common.ui.massactions.command.SendToMassVisualizationView.shouldCreateViewId";

  public static final IPath ICONS_PATH = new Path("icons/full");
  public static final String OBJ = "obj16/";

  public static final String ME_VIEW_OBJ = "ME_VIEW_OBJ";
  public static final String ME_NEW_VIEW_OBJ = "ME_NEW_VIEW_OBJ";
  public static final String MV_VIEW_OBJ = "MV_VIEW_OBJ";
  public static final String MV_NEW_VIEW_OBJ = "MV_NEW_VIEW_OBJ";

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

  @Override
  protected void initializeImageRegistry(ImageRegistry reg) {
    IPath path = ICONS_PATH.append(OBJ).append("me_new_view.gif");//$NON-NLS-1$
    ImageDescriptor imageDescriptor = createImageDescriptor(getDefault().getBundle(), path);
    reg.put(ME_NEW_VIEW_OBJ, imageDescriptor);

    path = ICONS_PATH.append(OBJ).append("me_view.gif");//$NON-NLS-1$
    imageDescriptor = createImageDescriptor(getDefault().getBundle(), path);
    reg.put(ME_VIEW_OBJ, imageDescriptor);

    path = ICONS_PATH.append(OBJ).append("mv_new_view.gif");//$NON-NLS-1$
    imageDescriptor = createImageDescriptor(getDefault().getBundle(), path);
    reg.put(MV_NEW_VIEW_OBJ, imageDescriptor);

    path = ICONS_PATH.append(OBJ).append("mv_view.gif");//$NON-NLS-1$
    imageDescriptor = createImageDescriptor(getDefault().getBundle(), path);
    reg.put(MV_VIEW_OBJ, imageDescriptor);
  }

  private ImageDescriptor createImageDescriptor(Bundle bundle, IPath path) {
    URL url = FileLocator.find(bundle, path, null);
    if (url != null) {
      return ImageDescriptor.createFromURL(url);
    }
    return null;
  }
}
