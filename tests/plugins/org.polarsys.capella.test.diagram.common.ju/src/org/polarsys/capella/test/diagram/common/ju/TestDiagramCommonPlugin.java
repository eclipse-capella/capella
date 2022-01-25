/*******************************************************************************
 * Copyright (c) 2006, 2021 THALES GLOBAL SERVICES.
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
package org.polarsys.capella.test.diagram.common.ju;

import org.eclipse.sirius.tools.api.SiriusPlugin;
import org.eclipse.ui.plugin.AbstractUIPlugin;
import org.osgi.framework.Bundle;
import org.osgi.framework.BundleContext;
import org.osgi.framework.BundleEvent;
import org.osgi.framework.BundleListener;
import org.osgi.framework.FrameworkUtil;
import org.polarsys.capella.test.diagram.common.ju.headless.HeadlessJavaActionsProvider;

/**
 * The activator class controls the plug-in life cycle
 */
public class TestDiagramCommonPlugin extends AbstractUIPlugin {

  // The shared instance
  private static TestDiagramCommonPlugin plugin;

  private boolean externalJavaActionReseted = false;

  /**
   * The constructor
   */
  public TestDiagramCommonPlugin() {
  }

  /**
   * @see org.eclipse.ui.plugin.AbstractUIPlugin#start(org.osgi.framework.BundleContext)
   */
  @Override
  public void start(BundleContext context) throws Exception {
    super.start(context);
    plugin = this;

    Bundle siriusBundle = FrameworkUtil.getBundle(SiriusPlugin.class);
    addStartedListener(siriusBundle);

    if (siriusBundle.getState() == Bundle.ACTIVE) {
      resetExternalJavaAction();
    }
  }

  /**
   * @see org.eclipse.ui.plugin.AbstractUIPlugin#stop(org.osgi.framework.BundleContext)
   */
  @Override
  public void stop(BundleContext context) throws Exception {
    plugin = null;
    super.stop(context);
  }

  protected void addStartedListener(final Bundle bundle) {

    bundle.getBundleContext().addBundleListener(new BundleListener() {
      @Override
      public void bundleChanged(BundleEvent event) {
        if (event.getBundle().equals(bundle)) {
          switch (event.getType()) {
          case BundleEvent.STARTED:
          case BundleEvent.LAZY_ACTIVATION:
          case BundleEvent.UPDATED:
            resetExternalJavaAction();
            break;
          default:
            break;
          }
        }
      }
    });
  }

  /**
   * Modify the extension registry in order to perform tests without any UI "interactions" expected from users.
   */
  protected synchronized void resetExternalJavaAction() {
    if (!externalJavaActionReseted) {
      new HeadlessJavaActionsProvider().init();
      externalJavaActionReseted = true;
    }
  }

  /**
   * Returns the shared instance
   * 
   * @return the shared instance
   */
  public static TestDiagramCommonPlugin getDefault() {
    return plugin;
  }

}
