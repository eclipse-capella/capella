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
package org.polarsys.capella.common.tools.report.appenders.file;

import java.io.IOException;
import java.net.URL;

import org.apache.log4j.Appender;
import org.apache.log4j.Layout;
import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;
import org.apache.log4j.spi.ErrorHandler;
import org.apache.log4j.spi.Filter;
import org.apache.log4j.spi.LoggingEvent;
import org.eclipse.core.resources.ResourcesPlugin;
import org.eclipse.core.runtime.FileLocator;
import org.polarsys.capella.common.tools.report.config.ReportManagerConstants;

public class UsageMonitoringFileAppender implements Appender {
  private static final String APPENDER_NAME = "Usage"; //$NON-NLS-1$
  private Appender usage = null;

  /**
   * @throws IOException
   */
  public UsageMonitoringFileAppender() throws IOException {

    // By default the workspace is set as Usage Monitoring Path
    System.setProperty("usageMonitoringPath", ResourcesPlugin.getWorkspace().getRoot().getLocation().toOSString()); //$NON-NLS-1$

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
    usage = Logger.getRootLogger().getAppender(UsageMonitoringFileAppender.APPENDER_NAME);
  }

  @Override
  public void addFilter(Filter newFilter) {
    usage.addFilter(newFilter);
  }

  @Override
  public void clearFilters() {
    usage.clearFilters();
  }

  @Override
  public ErrorHandler getErrorHandler() {
    return usage.getErrorHandler();
  }

  @Override
  public Filter getFilter() {
    return usage.getFilter();
  }

  @Override
  public Layout getLayout() {
    return usage.getLayout();
  }

  @Override
  public synchronized void doAppend(LoggingEvent event) {
    usage.doAppend(event);
  }

  @Override
  public void setLayout(Layout layout) {
    usage.setLayout(layout);
  }

  @Override
  public void setName(String name) {
    usage.setName(name);
  }

  public String getName() {
    return ReportManagerConstants.LOG_OUTPUT_USAGE_FILE;
  }

  @Override
  public void close() {
    usage.close();
  }

  @Override
  public void setErrorHandler(ErrorHandler errorHandler) {
    usage.setErrorHandler(errorHandler);
  }

  @Override
  public boolean requiresLayout() {
    return usage.requiresLayout();
  }
}