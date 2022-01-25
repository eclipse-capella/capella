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
package org.polarsys.capella.core.business.queries;

import org.eclipse.core.runtime.Plugin;
import org.osgi.framework.BundleContext;

/**
 * This is the activator of the business queries plug-in
 */
public class BusinessQueriesPlugin extends Plugin {

  /**
   * Single instance of the class
   */
  private static BusinessQueriesPlugin __instance;

  /**
   * Returns the single instance of the class
   * @return a <code>BusinessQueriesPlugin</code> instance
   */
  public static BusinessQueriesPlugin getDefault() {
    return __instance;
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public void start(BundleContext context) throws Exception {
    super.start(context);
    __instance = this;
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public void stop(BundleContext context) throws Exception {
    super.stop(context);
    __instance = null;
  }
}
