/*******************************************************************************
 * Copyright (c) 2017, 2018 THALES GLOBAL SERVICES.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *  
 * Contributors:
 *    Thales - initial API and implementation
 *******************************************************************************/
package org.polarsys.capella.common.tools.report.appenders.usage.util;

import java.io.File;
import java.io.IOException;

import org.apache.log4j.RollingFileAppender;
import org.apache.log4j.helpers.CountingQuietWriter;
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
  
  @Override
  protected void subAppend(LoggingEvent event) {
    // Before appending, update the count if it's needed
    if(qw instanceof CountingQuietWriter){
      CountingQuietWriter cqw = (CountingQuietWriter)qw;
      File f = new File(fileName);
      long length = f.length();
      if(cqw.getCount() < length){
        cqw.setCount(length);
      }
    }
    super.subAppend(event);
  }

  private static String getFileName() {
    return System.getProperty(UsageMonitoringLogger.USAGE_PATH)+ "\\Usage.log";
  }
}
