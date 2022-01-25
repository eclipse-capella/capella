/*******************************************************************************
 * Copyright (c) 2006, 2020 THALES GLOBAL SERVICES.
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

package org.polarsys.capella.common.ef;

import org.osgi.framework.BundleContext;

import org.polarsys.capella.common.mdsofa.common.activator.AbstractActivator;

/**
 * Controls org.polarsys.capella.common.ef plug-in life cycle.
 */
public class CapellaEFPlugin extends AbstractActivator {
  /**
   * Plug-in id.
   */
  public static final String PLUGIN_ID = "org.polarsys.capella.common.ef"; //$NON-NLS-1$

  /**
   * Singleton instance.
   */
  private static CapellaEFPlugin __instance;

  /**
   * @see org.eclipse.core.runtime.Plugin#start(org.osgi.framework.BundleContext)
   */
  @Override
  public void start(BundleContext context) throws Exception {
    super.start(context);
    __instance = this;
  }

  /**
   * @see org.eclipse.core.runtime.Plugin#stop(org.osgi.framework.BundleContext)
   */
  @Override
  public void stop(BundleContext context) throws Exception {
    __instance = null;
    super.stop(context);
  }

  /**
   * Return the singleton instance.
   * @return <code>null</code> if the plug-in is not started.
   */
  public static CapellaEFPlugin getDefault() {
    return __instance;
  }
}
