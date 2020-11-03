/*******************************************************************************
 * Copyright (c) 2019, 2020 THALES GLOBAL SERVICES.
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
package org.polarsys.capella.test.odesign;

import org.osgi.framework.BundleActivator;
import org.osgi.framework.BundleContext;

public class Activator implements BundleActivator {

  private static BundleContext context;

  static BundleContext getContext() {
    return context;
  }

  public void start(BundleContext bundleContext) throws Exception {
    Activator.context = bundleContext;
  }

  public void stop(BundleContext bundleContext) throws Exception {
    Activator.context = null;
  }

}
