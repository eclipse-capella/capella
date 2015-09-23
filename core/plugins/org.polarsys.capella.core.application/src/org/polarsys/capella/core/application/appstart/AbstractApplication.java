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
