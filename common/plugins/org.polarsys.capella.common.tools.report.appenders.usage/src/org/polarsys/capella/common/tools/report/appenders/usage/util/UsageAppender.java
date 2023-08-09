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

import java.io.File;
import java.io.IOException;

import org.apache.log4j.RollingFileAppender;
import org.apache.log4j.spi.Filter;
import org.apache.log4j.spi.LoggingEvent;
import org.polarsys.capella.common.tools.report.appenders.usage.UsageMonitoringLogger;

public class UsageAppender extends RollingFileAppender {

  public UsageAppender() throws IOException {
    super(new UsageLayout(), getFileName());
    setMaxFileSize("20MB");
    setMaxBackupIndex(30);

    addFilter(new Filter() {
      @Override
      public int decide(LoggingEvent arg0) {
        return arg0.getMessage() instanceof UsageMonitoring ? ACCEPT : DENY;
      }
    });
  }

  private static String getFileName() {
    return System.getProperty(UsageMonitoringLogger.USAGE_PATH) + File.separator + "Usage.log";
  }
}
