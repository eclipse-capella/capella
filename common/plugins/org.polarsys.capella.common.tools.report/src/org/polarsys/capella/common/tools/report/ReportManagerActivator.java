/*******************************************************************************
 * Copyright (c) 2006, 2018 THALES GLOBAL SERVICES.
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
package org.polarsys.capella.common.tools.report;

import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Appender;
import org.apache.log4j.Logger;
import org.eclipse.core.runtime.IConfigurationElement;
import org.eclipse.core.runtime.Plugin;
import org.osgi.framework.BundleContext;
import org.polarsys.capella.common.mdsofa.common.helper.ExtensionPointHelper;
import org.polarsys.capella.common.tools.report.appenders.IFlushableAppenders;

/**
 * The activator class controls the plug-in life cycle
 */
public class ReportManagerActivator extends Plugin {
  
  private static final Logger logger = Logger.getLogger(ReportManagerActivator.class.getName());

  private static final String APPENDERS_EXTENSION_ID = "Log4jAppendersExtension"; //$NON-NLS-1$
  private static final String FLUSHABLE_APPENDERS_EXTENSION_ID = "AppenderOutputFlushCapability"; //$NON-NLS-1$
  // The shared instance
  private static ReportManagerActivator plugin;

  private static final String REPORT_PLUGIN_ID = "org.polarsys.capella.common.tools.report"; //$NON-NLS-1$

  private List<Appender> appenders;
  private List<IFlushableAppenders> flushableAppenders;

  public ReportManagerActivator() {
    plugin = this;
  }

  /**
   * Get Appender(s) that contributed to plugin 'Log4jAppendersExtension' extension point
   */
  public List<Appender> getAppenders() {
    if ((appenders == null) || (appenders.isEmpty())) {
      try {
        appenders = new ArrayList<>();
        IConfigurationElement[] appendersProvider = ExtensionPointHelper.getConfigurationElements(REPORT_PLUGIN_ID, APPENDERS_EXTENSION_ID);
        for (IConfigurationElement configurationElement : appendersProvider) {
          Appender newAppender = (Appender) ExtensionPointHelper.createInstance(configurationElement, ExtensionPointHelper.ATT_CLASS);
          if (null != newAppender) {
            appenders.add(newAppender);
          }
        }
      } catch (Exception ex) {
    	  logger.error(ex.getMessage(), ex);
      }
    }
    return appenders;
  }

  /**
   * Get Appender(s) that contributed to plug-in 'AppenderOutputFlushCapability' extension point
   */
  public List<IFlushableAppenders> getFlushableAppenders() {
    if ((flushableAppenders == null) || (flushableAppenders.isEmpty())) {
      try {
    	  flushableAppenders = new ArrayList<>();
        IConfigurationElement[] appendersProvider = ExtensionPointHelper.getConfigurationElements(REPORT_PLUGIN_ID, FLUSHABLE_APPENDERS_EXTENSION_ID);
        for (IConfigurationElement configurationElement : appendersProvider) {
          IFlushableAppenders newAppender = (IFlushableAppenders) ExtensionPointHelper.createInstance(configurationElement, ExtensionPointHelper.ATT_CLASS);
          flushableAppenders.add(newAppender);
        }

      } catch (Exception ex) {
    	  logger.error(ex.getMessage(), ex);
      }
    }
    return flushableAppenders;
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
