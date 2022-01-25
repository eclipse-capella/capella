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
package org.polarsys.capella.common.tools.report.appenders.reportlogview;

/*
 * "The Java Developer's Guide to Eclipse" by D'Anjou, Fairbrother, Kehn, Kellerman, McCarthy (C) Copyright International Business Machines Corporation, 2003,
 * 2004. All Rights Reserved. Code or samples provided herein are provided without warranty of any kind.
 */

import java.util.MissingResourceException;
import java.util.ResourceBundle;

import org.osgi.framework.FrameworkUtil;
import org.polarsys.capella.common.ui.services.AbstractUIActivator;

/**
 * The main plugin class to be used in the desktop.
 */
public class MarkerViewPlugin extends AbstractUIActivator {
  // The shared instance.
  private static MarkerViewPlugin plugin;
  // Resource bundle.
  private ResourceBundle resourceBundle;

  /**
   * The constructor.
   */
  public MarkerViewPlugin() {
    super();
    plugin = this;
    try {
      resourceBundle = ResourceBundle.getBundle(FrameworkUtil.getBundle(MarkerViewPlugin.class).getSymbolicName());
    } catch (MissingResourceException x) {
      resourceBundle = null;
    }
  }

  /**
   * @return Returns the shared instance.
   */
  public static MarkerViewPlugin getDefault() {
    return plugin;
  }

  /**
   * @param key
   * @return the string from the plugin's resource bundle, or 'key' if not found.
   */
  public String getResourceString(String key) {
    ResourceBundle bundle = getResourceBundle();
    try {
      return (bundle != null ? bundle.getString(key) : key);
    } catch (MissingResourceException e) {
      return key;
    }
  }

  /**
   * @return the plugin's resource bundle.
   */
  public ResourceBundle getResourceBundle() {
    return resourceBundle;
  }

 
}
