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
package org.polarsys.capella.common.tools.report.appenders.usage;

import java.io.IOException;
import java.net.URL;

import org.apache.log4j.PropertyConfigurator;
import org.eclipse.core.resources.ResourcesPlugin;
import org.eclipse.core.runtime.FileLocator;
import org.eclipse.core.runtime.Platform;
import org.polarsys.capella.common.tools.report.appenders.usage.preferences.IUsagePreferences;
import org.polarsys.capella.common.tools.report.appenders.usage.util.UsageLogger;
import org.polarsys.capella.core.commands.preferences.service.AbstractPreferencesInitializer;

public class UsageMonitoringLogger {
  private static UsageMonitoringLogger __instance = null;
  private final UsageLogger logger;
  private static String USAGE_PATH = "UsagePath";

  /**
   * @return a MonitoringLogger instance
   */
  public static UsageMonitoringLogger getInstance() {
    if (null == UsageMonitoringLogger.__instance) {
      UsageMonitoringLogger.__instance = new UsageMonitoringLogger();
    }
    return UsageMonitoringLogger.__instance;
  }
  
  /**
   * Set by default the workspace as usage path if any vmarg -DUsagePath is provided
   */
  private void setUsagePath() {
    if(null == System.getProperty(UsageMonitoringLogger.USAGE_PATH) || "".equals(System.getProperty(UsageMonitoringLogger.USAGE_PATH))){
      System.setProperty(UsageMonitoringLogger.USAGE_PATH, ResourcesPlugin.getWorkspace().getRoot().getLocation().toOSString());
    }
  }

  private UsageMonitoringLogger() {
    // configure the logger
    final String productName = Platform.getProduct().getName().toString();
    final String productVersion = Platform.getProduct().getDefiningBundle().getVersion().toString();
    logger = new UsageLogger(productName, productVersion); // $NON-NLS-1$
    
    setUsagePath();
    
    // The appender is loaded through the property file
    final URL confURL = Activator.getDefault().getBundle().getEntry("log4j.properties"); //$NON-NLS-1$
    try {
      PropertyConfigurator.configure(FileLocator.toFileURL(confURL).getFile());
    } catch (final IOException e) {
      // Do nothing! If it fail, no usage log, that's all.
      e.printStackTrace();
    } catch (final NullPointerException npe) {
      // Do nothing! If it fail, no usage log, that's all.
      npe.printStackTrace();
    }
  }

  public void log(final String eventName, final String eventStatus) {
    if (isUsageMonitoringActivated()) {
      logger.log(eventName, eventStatus);
    }
  }

  public void log(final String eventName, final String eventContext, final String eventStatus) {
    if (isUsageMonitoringActivated()) {
      logger.log(eventName, eventContext, eventStatus);
    }
  }
  
  public void log(final String eventName, final String eventContext, final String eventStatus, final String addendum) {
    if (isUsageMonitoringActivated()) {
      logger.log(eventName, eventContext, eventStatus, addendum);
    }
  }

  public boolean isUsageMonitoringActivated() {
    return AbstractPreferencesInitializer.getBoolean(IUsagePreferences.PREFERENCES_ACTIVATE_USAGE_MONITORING,
        IUsagePreferences.PREFERENCES_ACTIVATE_USAGE_MONITORING_DEFAULT.booleanValue());
  }
}