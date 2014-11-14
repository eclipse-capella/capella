/*******************************************************************************
 * Copyright (c) 2006, 2014 THALES GLOBAL SERVICES.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *  
 * Contributors:
 *    Thales - initial API and implementation
 *******************************************************************************/
package org.polarsys.capella.common.platform.sirius.ted;

import org.osgi.framework.BundleContext;

import org.polarsys.capella.common.mdsofa.common.activator.AbstractActivator;

/**
 * Controls platform.sirius.ted plug-in life cycle.
 */
public class PlatformSiriusTedActivator extends AbstractActivator {
  /**
   * Singleton instance.
   */
  private static PlatformSiriusTedActivator __instance;

  /**
   * @see org.eclipse.core.runtime.Plugin#start(org.osgi.framework.BundleContext)
   */
  @Override
  public void start(BundleContext context_p) throws Exception {
    super.start(context_p);
    __instance = this;
  }

  /**
   * @see org.eclipse.core.runtime.Plugin#stop(org.osgi.framework.BundleContext)
   */
  @Override
  public void stop(BundleContext context_p) throws Exception {
    __instance = null;
    super.stop(context_p);
  }

  /**
   * Return the singleton instance.
   * @return <code>null</code> if the plug-in is not started.
   */
  public static PlatformSiriusTedActivator getDefault() {
    return __instance;
  }
}
