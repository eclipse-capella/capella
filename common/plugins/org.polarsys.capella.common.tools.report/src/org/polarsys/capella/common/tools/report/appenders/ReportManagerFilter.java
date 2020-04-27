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

package org.polarsys.capella.common.tools.report.appenders;

import java.util.List;

import org.apache.log4j.Appender;
import org.apache.log4j.spi.LoggingEvent;
import org.polarsys.capella.common.tools.report.config.persistence.ConfigurationInstance;
import org.polarsys.capella.common.tools.report.config.persistence.LogLevel;
import org.polarsys.capella.common.tools.report.config.persistence.OutputConfiguration;
import org.polarsys.capella.common.tools.report.config.registry.ReportManagerRegistry;

/**
 *
 */
public class ReportManagerFilter extends org.apache.log4j.spi.Filter {

  String appenderName = null;

  public ReportManagerFilter(String appenderName) {
    this.appenderName = appenderName;
  }

  public ReportManagerFilter(Appender appender) {
    this(appender.getName());
  }

  /**
   * @see org.apache.log4j.spi.Filter#decide(org.apache.log4j.spi.LoggingEvent)
   */
  @Override
  public int decide(LoggingEvent event) {
    String level = event.getLevel().toString();
    String componentName = event.getLoggerName();

    ReportManagerRegistry registry = ReportManagerRegistry.getInstance();
    ConfigurationInstance config = registry.getComponentConfiguration(componentName);
    
    List<OutputConfiguration> configsList = config.getOutputConfiguration();
    for (OutputConfiguration outputConfiguration : configsList) {
      if (appenderName.equals(outputConfiguration.getOutputName())) {
        List<LogLevel> logslevels = outputConfiguration.getLogLevel();
        for (LogLevel logLevel : logslevels) {
          if (logLevel.isValue() && level.equals(logLevel.getName())) {
            return ACCEPT;
          }
        }
      }
    }

    return DENY;
  }
}
