/*******************************************************************************
 * Copyright (c) 2006, 2015 THALES GLOBAL SERVICES.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *  
 * Contributors:
 *    Thales - initial API and implementation
 *******************************************************************************/
package org.polarsys.capella.test.refinement.ju;

import org.osgi.framework.BundleContext;

import org.polarsys.capella.common.mdsofa.common.activator.AbstractActivator;
import org.polarsys.capella.test.refinement.ju.headless.ResolvingExtensionProvider;

/**
 * The activator class controls the plug-in life cycle
 */
public class TestRefinementPlugin  extends AbstractActivator {

  private static TestRefinementPlugin __instance;

  /**
   * Get the singleton instance.
   * @return
   */
  public static TestRefinementPlugin getDefault() {
    return __instance;
  }

  /**
   * @see org.eclipse.core.runtime.Plugin#start(org.osgi.framework.BundleContext)
   */
  @Override
  public void start(BundleContext context) throws Exception {
    super.start(context);
    __instance = this;
    
    // Modify the extension registry in order to perform test
    // without any UI "interactions" expected from users.
    ResolvingExtensionProvider.INSTANCE.init();
  }

  /**
   * @see org.eclipse.core.runtime.Plugin#stop(org.osgi.framework.BundleContext)
   */
  @Override
  public void stop(BundleContext context) throws Exception {
    __instance = null;

    // Restore the extension registry to its initial state
    ResolvingExtensionProvider.INSTANCE.restore();

    super.stop(context);
  }

}
