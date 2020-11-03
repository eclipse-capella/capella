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
package org.polarsys.capella.core.application.appstart;

import org.eclipse.core.runtime.IConfigurationElement;
import org.eclipse.equinox.app.IApplication;
import org.eclipse.equinox.app.IApplicationContext;
import org.polarsys.capella.common.mdsofa.common.helper.ExtensionPointHelper;

public class AbstractApplication implements IApplication {

  @Override
  public Object start(IApplicationContext context) throws Exception {
    for (IConfigurationElement configElement : ExtensionPointHelper.getConfigurationElements(
        "org.polarsys.capella.core.application", "AppStart")) {
      AbstractApplication appStart = (AbstractApplication) ExtensionPointHelper.createInstance(configElement,
          ExtensionPointHelper.ATT_CLASS);
      appStart.start(context);
    }
    return EXIT_OK;
  }

  @Override
  public void stop() {
  }

}
