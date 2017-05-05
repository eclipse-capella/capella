/*******************************************************************************
 * Copyright (c) 2006, 2017 THALES GLOBAL SERVICES.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *  
 * Contributors:
 *    Thales - initial API and implementation
 *******************************************************************************/
package org.polarsys.capella.common.tools.report;

import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Appender;
import org.eclipse.core.runtime.IConfigurationElement;
import org.eclipse.core.runtime.Plugin;
import org.osgi.framework.BundleContext;

import org.polarsys.capella.common.tools.report.appenders.IFlushableAppenders;
import org.polarsys.capella.common.mdsofa.common.helper.ExtensionPointHelper;

/**
 * The activator class controls the plug-in life cycle
 */
public class ReportManagerActivator extends Plugin {

  private static final String APPENDERS_EXTENSION_ID = "Log4jAppendersExtension"; //$NON-NLS-1$
  private static final String FLUSHABLE_APPENDERS_EXTENSION_ID = "AppenderOutputFlushCapability"; //$NON-NLS-1$
  // The shared instance
  private static ReportManagerActivator plugin;

  private static final String REPORT_PLUGIN_ID = "org.polarsys.capella.common.tools.report"; //$NON-NLS-1$

  private List<Appender> _appenders;
  private List<IFlushableAppenders> _flushableappenders;

  public ReportManagerActivator() {
    plugin = this;
  }

  /**
   * Get Appender(s) that contributed to plugin 'Log4jAppendersExtension' extension point
   */
  public List<Appender> getAppenders() {
    if ((_appenders == null) || (_appenders.size() == 0)) {
      try {
        _appenders = new ArrayList<Appender>();
        IConfigurationElement[] appendersProvider = ExtensionPointHelper.getConfigurationElements(REPORT_PLUGIN_ID, APPENDERS_EXTENSION_ID);
        for (IConfigurationElement configurationElement : appendersProvider) {
          Appender newAppender = (Appender) ExtensionPointHelper.createInstance(configurationElement, ExtensionPointHelper.ATT_CLASS);
          if (null != newAppender) {
            _appenders.add(newAppender);
          }
        }
      } catch (Throwable ex) {
        ex.printStackTrace();
      }
    }
    return _appenders;
  }

  /**
   * Get Appender(s) that contributed to plugin 'AppenderOutputFlushCapability' extension point
   */
  public List<IFlushableAppenders> getFlushableAppenders() {
    if ((_flushableappenders == null) || (_flushableappenders.size() == 0)) {
      try {
        _flushableappenders = new ArrayList<IFlushableAppenders>();
        IConfigurationElement[] appendersProvider = ExtensionPointHelper.getConfigurationElements(REPORT_PLUGIN_ID, FLUSHABLE_APPENDERS_EXTENSION_ID);
        for (IConfigurationElement configurationElement : appendersProvider) {
          IFlushableAppenders newAppender = (IFlushableAppenders) ExtensionPointHelper.createInstance(configurationElement, ExtensionPointHelper.ATT_CLASS);
          _flushableappenders.add(newAppender);
        }

      } catch (Throwable ex) {
        ex.printStackTrace();
      }
    }
    return _flushableappenders;
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public void start(BundleContext context) throws Exception {
    super.start(context);
    getAppenders();
    getFlushableAppenders();
  }

  /**
   * {@inheritDoc}
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
  public static ReportManagerActivator getDefault() {
    return plugin;
  }
}
