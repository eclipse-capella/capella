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
package org.polarsys.capella.common.tools.report.appenders;

import java.util.List;

import org.apache.log4j.Appender;
import org.apache.log4j.spi.LoggingEvent;

import org.polarsys.capella.common.tools.report.config.ReportManagerConstants;
import org.polarsys.capella.common.tools.report.config.persistence.ConfigurationInstance;
import org.polarsys.capella.common.tools.report.config.persistence.LogLevel;
import org.polarsys.capella.common.tools.report.config.persistence.OutputConfiguration;
import org.polarsys.capella.common.tools.report.config.registry.ReportManagerRegistry;

/**
 *
 */
public class ReportManagerFilter extends org.apache.log4j.spi.Filter {

  ReportManagerRegistry reg = null;
  Appender theAppender = null;

  public ReportManagerFilter(Appender appender_p) {
    super();
    theAppender = appender_p;
  }

  /**
   * @see org.apache.log4j.spi.Filter#decide(org.apache.log4j.spi.LoggingEvent)
   */
  @Override
  public int decide(LoggingEvent event_p) {
    String componentName = null;
    String level = event_p.getLevel().toString();
  
    componentName = event_p.getLoggerName();
    
    
    reg = ReportManagerRegistry.getInstance();
    
    ConfigurationInstance config = reg.getComponentConfiguration(componentName);
    if (config == null) {
      config = reg.getComponentConfiguration(ReportManagerConstants.LOG_OUTPUT_DEFAULT);
    }
    
    List<OutputConfiguration> configsList = config.getOutputConfiguration();
    
    for (OutputConfiguration outputConfiguration : configsList) {
      {
        String appenderName = theAppender.getName();
        
        if (appenderName.equals(outputConfiguration.getOutputName())) {
          List<LogLevel> logslevels = outputConfiguration.getLogLevel();
          for (LogLevel logLevel : logslevels) {
            if (logLevel.isValue() == true && level.equals(logLevel.getName())) {
              return ACCEPT;
            }
          }
        }
      }
    }
    
    return DENY;
  }
}
