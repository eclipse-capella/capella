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

import org.eclipse.core.resources.ResourcesPlugin;
import org.eclipse.core.runtime.Platform;
import org.polarsys.capella.common.mdsofa.common.constant.ICommonConstants;
import org.polarsys.capella.common.tools.report.appenders.usage.preferences.IUsagePreferences;
import org.polarsys.capella.common.tools.report.appenders.usage.util.UsageLogger;
import org.polarsys.capella.common.tools.report.appenders.usage.util.UsageMonitoring.EventStatus;
import org.polarsys.capella.core.commands.preferences.service.AbstractPreferencesInitializer;

public class UsageMonitoringLogger {
  private static UsageMonitoringLogger instance;
  private final UsageLogger logger;
  public static String USAGE_PATH = "UsagePath";
  
  /**
   * @return a MonitoringLogger instance
   */
  public static UsageMonitoringLogger getInstance() {
    if (UsageMonitoringLogger.instance == null) {
    	UsageMonitoringLogger.instance = new UsageMonitoringLogger();
    }
    return UsageMonitoringLogger.instance;
  }
  
  /**
   * Set by default the workspace as usage path if any vmarg -DUsagePath is provided
   */
  private void setUsagePath() {
    if (null == System.getProperty(UsageMonitoringLogger.USAGE_PATH) || "".equals(System.getProperty(UsageMonitoringLogger.USAGE_PATH))){
      System.setProperty(UsageMonitoringLogger.USAGE_PATH, ResourcesPlugin.getWorkspace().getRoot().getLocation().toOSString());
    }
  }

  private UsageMonitoringLogger() {
    setUsagePath();
    
    // configure the logger
    if (Platform.getProduct() != null) {
    	final String productName = Platform.getProduct().getName().toString();
        final String productVersion = Platform.getProduct().getDefiningBundle().getVersion().toString();
        logger = new UsageLogger(productName, productVersion);
    } else {
    	logger = new UsageLogger(ICommonConstants.EMPTY_STRING, ICommonConstants.EMPTY_STRING);
    }
  }

  public void log(final String eventName, final EventStatus eventStatus) {
    if (isUsageMonitoringActivated()) {
      logger.log(eventName, eventStatus);
    }
  }

  public void log(final String eventName, final String eventContext, final EventStatus eventStatus) {
    if (isUsageMonitoringActivated()) {
      logger.log(eventName, eventContext, eventStatus);
    }
  }
  
  public void log(final String eventName, final String eventContext, final EventStatus eventStatus, final String addendum) {
    if (isUsageMonitoringActivated()) {
      logger.log(eventName, eventContext, eventStatus, addendum);
    }
  }

  public boolean isUsageMonitoringActivated() {
    return AbstractPreferencesInitializer.getBoolean(IUsagePreferences.PREFERENCES_ACTIVATE_USAGE_MONITORING,
        IUsagePreferences.PREFERENCES_ACTIVATE_USAGE_MONITORING_DEFAULT.booleanValue());
  }
}