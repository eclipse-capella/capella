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
package org.polarsys.capella.common.tools.report.appenders.file.util;

import org.apache.log4j.Logger;
import org.eclipse.core.runtime.Platform;
import org.polarsys.capella.common.tools.report.config.ReportManagerConstants;

public class UsageLogger {

  public final static String START = "START";
  public final static String STOP = "STOP";
  public final static String OK = "OK";
  public final static String ERROR = "ERROR";
  public final static String WARNING = "WARNING";

  private final static String NONE = "";

  private final static String productName = Platform.getProduct().getName().toString();
  private final static String toolVersion = Platform.getProduct().getDefiningBundle().getVersion().toString();

  private final Logger logger;

  public UsageLogger(Logger logger) {
    this.logger = logger;
  }

  public void logTrack(final String event, final String context, final String status) {
    try {
      final String formattedUsageMonitoring = UsageFormatter
          .format(new UsageMonitoring(productName, toolVersion, event, context, status)); // $NON-NLS-1$
      logger.info(formattedUsageMonitoring);
    } catch (Exception e) {
      e.printStackTrace();
    }
  }

  public void logTrack(final String event, final String context) {
    try {
      final String formattedUsageMonitoring = UsageFormatter
          .format(new UsageMonitoring(productName, toolVersion, event, context, UsageLogger.NONE)); // $NON-NLS-1$
      logger.info(formattedUsageMonitoring);
    } catch (Exception e) {
      e.printStackTrace();
    }
  }

  public void logInfo(final String context) {
    try {
      final String formattedUsageMonitoring = UsageFormatter
          .format(new UsageMonitoring(productName, toolVersion, context, ReportManagerConstants.LOG_LEVEL_INFO)); // $NON-NLS-1$
      logger.info(formattedUsageMonitoring);
    } catch (Exception e) {
      e.printStackTrace();
    }
  }

  public void logError(final String context) {
    try {
      final String formattedUsageMonitoring = UsageFormatter
          .format(new UsageMonitoring(productName, toolVersion, context, ReportManagerConstants.LOG_LEVEL_ERROR)); // $NON-NLS-1$
      logger.error(formattedUsageMonitoring);
    } catch (Exception e) {
      e.printStackTrace();
    }
  }

  public void logWarn(final String context) {
    try {
      final String formattedUsageMonitoring = UsageFormatter
          .format(new UsageMonitoring(productName, toolVersion, context, ReportManagerConstants.LOG_LEVEL_WARN)); // $NON-NLS-1$
      logger.warn(formattedUsageMonitoring);
    } catch (Exception e) {
      e.printStackTrace();
    }
  }

  public void logFatal(final String context) {
    try {
      final String formattedUsageMonitoring = UsageFormatter
          .format(new UsageMonitoring(productName, toolVersion, context, ReportManagerConstants.LOG_LEVEL_FATAL)); // $NON-NLS-1$
      logger.fatal(formattedUsageMonitoring);
    } catch (Exception e) {
      e.printStackTrace();
    }
  }

  public void logDebug(final String event) {
    try {
      final String formattedUsageMonitoring = UsageFormatter
          .format(new UsageMonitoring(productName, toolVersion, event, ReportManagerConstants.LOG_LEVEL_DEBUG)); // $NON-NLS-1$
      logger.debug(formattedUsageMonitoring);
    } catch (Exception e) {
      e.printStackTrace();
    }
  }
}
