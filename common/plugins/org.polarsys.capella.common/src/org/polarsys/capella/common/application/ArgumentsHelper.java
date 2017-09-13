/*******************************************************************************
 * Copyright (c) 2017 THALES GLOBAL SERVICES.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *  
 * Contributors:
 *    Thales - initial API and implementation
 *******************************************************************************/
package org.polarsys.capella.common.application;

import java.util.HashMap;

import org.eclipse.core.runtime.Platform;

/**
 * This argument helper retrieve arguments given to the application. By default, it reads Platform.getApplicationArgs()
 * 
 * It be customized to load additional parameters from IApplicationContext if IApplicationContext is not the default one
 * provided by the Platform.
 */
public class ArgumentsHelper {

  private HashMap<String, String> values = null;

  private boolean defaultLoaded = false;

  private static ArgumentsHelper instance = new ArgumentsHelper();

  public static ArgumentsHelper getInstance() {
    return instance;
  }

  private void loadArguments() {
    if (!defaultLoaded) {
      defaultLoaded = true;
      loadArguments(Platform.getApplicationArgs());
    }
  }

  public void loadArguments(String[] args) {
    if (values == null) {
      values = new HashMap<String, String>();
    }

    for (int i = 0; i < args.length; i++) {
      String arg = args[i].toLowerCase();

      if (arg.startsWith("-")) { //$NON-NLS-1$
        String value = i < args.length - 1 ? args[i + 1] : null;
        if (value != null && !value.startsWith("-")) { //$NON-NLS-1$
          values.put(arg, value);
        }
      }
    }
  }

  public String getString(String parameter) {
    loadArguments();
    String arg = parameter.toLowerCase();
    if (values.containsKey(arg)) {
      return values.get(arg);
    }
    return null;
  }

  public boolean hasParameter(String parameter) {
    loadArguments();
    String arg = parameter.toLowerCase();
    return values.containsKey(arg);
  }

}
