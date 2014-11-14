/*******************************************************************************
 * Copyright (c) 2006, 2014 THALES GLOBAL SERVICES.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *  
 * Contributors:
 *    Thales - initial API and implementation
 *******************************************************************************/
package org.polarsys.capella.common.tools.report.appenders.console;

import org.eclipse.core.runtime.IConfigurationElement;
import org.eclipse.core.runtime.Plugin;
import org.osgi.framework.BundleContext;

import org.polarsys.capella.common.mdsofa.common.helper.ExtensionPointHelper;

/**
 * The activator class controls the plug-in life cycle
 */
public class ConsoleAppenderActivator extends Plugin {

  private static final String CONSOLE_EXTENSION_ID = "ConsoleExtension"; //$NON-NLS-1$
  // The shared instance
  private static ConsoleAppenderActivator plugin;

  // The plug-in ID
  public static final String PLUGIN_ID = "org.polarsys.capella.common.tools.report.appenders.console"; //$NON-NLS-1$

  private IReportConsole _consoleAppender;

  /**
   * The constructor
   */
  public ConsoleAppenderActivator() {
    plugin = this;
  }

  /**
   * Get Console Appender that contributed to plugin 'ConsoleExtension' extension point
   * @return
   */
  public IReportConsole getReportConsole() {
    if (_consoleAppender == null) {
      try {
        IConfigurationElement[] consoleProvider = ExtensionPointHelper.getConfigurationElements(PLUGIN_ID, CONSOLE_EXTENSION_ID);
        for (IConfigurationElement configurationElement : consoleProvider) {
          _consoleAppender = (IReportConsole) ExtensionPointHelper.createInstance(configurationElement, ExtensionPointHelper.ATT_CLASS);
        }
      } catch (Throwable ex) {
        ex.printStackTrace();
      }
    }
    return _consoleAppender;
  }

  /*
   * (non-Javadoc)
   * @see org.eclipse.core.runtime.Plugins#start(org.osgi.framework.BundleContext)
   */
  @Override
  public void start(BundleContext context) throws Exception {
    super.start(context);
    plugin = this;
  }

  /*
   * (non-Javadoc)
   * @see org.eclipse.core.runtime.Plugin#stop(org.osgi.framework.BundleContext)
   */
  @Override
  public void stop(BundleContext context) throws Exception {
    plugin = null;
    super.stop(context);
  }

  /**
   * Returns the shared instance
   * @return the shared instance
   */
  public static ConsoleAppenderActivator getDefault() {
    return plugin;
  }

}
