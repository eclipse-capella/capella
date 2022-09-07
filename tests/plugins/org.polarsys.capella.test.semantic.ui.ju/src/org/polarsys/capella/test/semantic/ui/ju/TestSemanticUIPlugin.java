/*******************************************************************************
 * Copyright (c) 2016, 2022 THALES GLOBAL SERVICES.
 * 
 * This program and the accompanying materials are made available under the
 * terms of the Eclipse Public License 2.0 which is available at
 * http://www.eclipse.org/legal/epl-2.0
 * 
 * SPDX-License-Identifier: EPL-2.0
 * 
 * Contributors:
 *    Thales Global Services - initial API and implementation
 *******************************************************************************/
package org.polarsys.capella.test.semantic.ui.ju;

import org.eclipse.core.runtime.Plugin;
import org.osgi.framework.BundleContext;

/**
 * 
 * Activator
 *
 */
public class TestSemanticUIPlugin extends Plugin {
  /**
   * The plug-in ID
   */
  public static final String PLUGIN_ID = "org.polarsys.capella.test.semantic.ui.ju"; //$NON-NLS-1$
  /**
   * The shared instance
   */
  private static TestSemanticUIPlugin plugin;

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
  public static TestSemanticUIPlugin getDefault() {
    return plugin;
  }
}
