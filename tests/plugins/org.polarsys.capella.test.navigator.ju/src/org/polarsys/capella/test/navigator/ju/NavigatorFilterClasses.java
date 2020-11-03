/*******************************************************************************
 * Copyright (c) 2018, 2020 THALES GLOBAL SERVICES.
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
package org.polarsys.capella.test.navigator.ju;

import java.util.ArrayList;
import java.util.Collection;
import java.util.stream.Collectors;

import org.eclipse.core.runtime.IConfigurationElement;
import org.eclipse.core.runtime.Platform;
import org.eclipse.osgi.util.NLS;
import org.polarsys.capella.core.platform.sirius.ui.navigator.view.CapellaCommonNavigator;
import org.polarsys.capella.test.framework.api.BasicTestCase;

/**
 * This class instanciates all filters on Project Explorer to detect if some filters classes have been deleted
 * without removing the filter
 */
public class NavigatorFilterClasses extends BasicTestCase {

  @Override
  public void test() throws Exception {

    //Retrieve all filter regexp patterns applying to the Capella Navigator
    //Only filters with id matching these patterns are displayed in the navigator
    Collection<String> patterns = new ArrayList<String>();
    for (IConfigurationElement element : Platform.getExtensionRegistry()
        .getConfigurationElementsFor("org.eclipse.ui.navigator.viewer")) {
      if ("viewerContentBinding".equals(element.getName())
          && element.getAttribute("viewerId").equals(CapellaCommonNavigator.ID)) {
        for (IConfigurationElement include : element.getChildren("includes")) {
          for (IConfigurationElement pattern : include.getChildren("contentExtension")) {
            patterns.add(pattern.getAttribute("pattern"));
          }
        }
      }
    }

    //Try to instantiate filters using a class
    Collection<String> invalidClasses = new ArrayList<String>();
    for (IConfigurationElement element : Platform.getExtensionRegistry()
        .getConfigurationElementsFor("org.eclipse.ui.navigator.navigatorContent")) {
      if ("commonFilter".equals(element.getName())) {
        if (match(element.getAttribute("id"), patterns)) {
          String clazz = element.getAttribute("class");
          if (clazz != null) {
            try {
              element.createExecutableExtension("class");
            } catch (Exception e) {
              invalidClasses.add(clazz);
            }
          }
        }
      }
    }

    assertTrue(
        NLS.bind("Some filters can't be instantiated: {0}", invalidClasses.stream().collect(Collectors.joining("\n"))),
        invalidClasses.isEmpty());

  }

  private boolean match(String attribute, Collection<String> patterns) {
    for (String pattern : patterns) {
      if (attribute.matches(pattern)) {
        return true;
      }
    }
    return false;
  }

}
