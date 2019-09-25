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
package org.polarsys.capella.test.platform.ju.testcases;

import java.util.ArrayList;
import java.util.Collection;
import java.util.stream.Collectors;

import org.eclipse.core.runtime.IConfigurationElement;
import org.eclipse.core.runtime.Platform;
import org.eclipse.core.runtime.preferences.AbstractPreferenceInitializer;
import org.eclipse.osgi.util.NLS;
import org.polarsys.capella.test.framework.api.BasicTestCase;

/**
 * This test ensures that defined PreferenceInitializer are valid
 */
public class InvalidPreferencesInitializer extends BasicTestCase {

  @Override
  public void test() throws Exception {
    Collection<String> errors = new ArrayList<>();
    IConfigurationElement[] configurationElements = Platform.getExtensionRegistry()
        .getConfigurationElementsFor("org.eclipse.core.runtime.preferences");
    
    for (IConfigurationElement element : configurationElements) {
      if ("initializer".equals(element.getName())) {
        try {
          Object object = element.createExecutableExtension("class");
          if (!AbstractPreferenceInitializer.class.isInstance(object)) {
            String pluginId = element.getContributor().getName();
            errors.add(NLS.bind("{0} is not a valid initializer (plugin:{1})", element.getAttribute("class"), pluginId));
          }
        } catch (Exception e) {
          errors.add(NLS.bind("{0}", e.getMessage()));
        }
      }
    }
    assertTrue(errors.stream().collect(Collectors.joining("\n")), errors.isEmpty());
  }

}
