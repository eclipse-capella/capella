/*******************************************************************************
 * Copyright (c) 2017, 2020 THALES GLOBAL SERVICES.
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
package org.polarsys.capella.common.tools.report.appenders.usage.util;

import org.apache.log4j.Logger;
import org.polarsys.capella.common.tools.report.appenders.usage.UsageAppenderPlugin;
import org.polarsys.capella.common.tools.report.appenders.usage.util.UsageMonitoring.EventStatus;
import org.polarsys.capella.common.tools.report.config.registry.ReportManagerRegistry;

public class UsageLogger {
  private static final String USAGE_LOGGER = "Usage";

  private String applicationName;
  private String applicationVersion;
  private Logger logger;

  public final static String NONE = "";
  public final static String OK = "OK";
  public final static String ERROR = "ERROR";

  public Logger getLogger() {

    if (logger == null) {
      String defaultConfiguration = ReportManagerRegistry.getConfigurationFile(UsageAppenderPlugin.getDefault().getBundle(),
          "usageConfiguration.xml");
      logger = ReportManagerRegistry.getInstance().subscribe(UsageLogger.USAGE_LOGGER, defaultConfiguration);

      try {
        UsageAppender appender = new UsageAppender();
        logger.addAppender(appender);

      } catch (Exception e) {
        // Nothing, usage can't start
      }
    }
    return logger;
  }

  public UsageLogger(String applicationName, String applicationVersion) {
    this.applicationName = applicationName;
    this.applicationVersion = applicationVersion;
  }

  public void log(final String eventName, final EventStatus eventStatus) {
    log(eventName, NONE, eventStatus, NONE);
  }

  public void log(final String eventName, final String eventContext, final EventStatus eventStatus) {
    log(eventName, eventContext, eventStatus, NONE);
  }

  public void log(final String eventName, final String eventContext, final EventStatus eventStatus,
      final String addendum) {
    try {
      getLogger().info(
          new UsageMonitoring(applicationName, applicationVersion, eventName, eventContext, eventStatus, addendum));
    } catch (Exception e) {
      // Nothing here
    }

  }
}