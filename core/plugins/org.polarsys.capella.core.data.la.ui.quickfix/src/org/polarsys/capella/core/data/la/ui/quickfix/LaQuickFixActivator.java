/*******************************************************************************
 * Copyright (c) 2019 THALES GLOBAL SERVICES.
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
package org.polarsys.capella.core.data.la.ui.quickfix;

import org.osgi.framework.BundleContext;
import org.polarsys.capella.common.ui.services.AbstractUIActivator;

/**
 * The activator class controls the plug-in life cycle
 */
public class LaQuickFixActivator extends AbstractUIActivator {
  // The shared instance
  private static LaQuickFixActivator plugin;

  /**
   * The constructor
   */
  public LaQuickFixActivator() {
    // Do nothing
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public void start(BundleContext context) throws Exception {
    super.start(context);
    plugin = this;
  }

  /**
   * {@inheritDoc}
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
  public static LaQuickFixActivator getDefault() {
    return plugin;
  }
}
