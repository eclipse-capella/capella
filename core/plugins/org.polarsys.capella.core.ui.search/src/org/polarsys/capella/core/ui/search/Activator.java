/*******************************************************************************
 * Copyright (c) 2019 THALES GLOBAL SERVICES.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *  
 * Contributors:
 *    Thales - initial API and implementation
 *******************************************************************************/
package org.polarsys.capella.core.ui.search;

import org.eclipse.sirius.common.ui.tools.api.plugin.AbstractUIActivator;
import org.osgi.framework.BundleContext;

public class Activator extends AbstractUIActivator {

  // The plug-in ID
  public static final String PLUGIN_ID = "org.polarsys.capella.core.ui.search"; //$NON-NLS-1$

  // The shared instance
  private static Activator plugin = new Activator();

  @Override
  public void stop(BundleContext context) throws Exception {
    super.stop(context);
    plugin = null;
  }

  public static Activator getDefault() {
    return plugin;
  }

}
