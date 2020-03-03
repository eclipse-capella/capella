/*******************************************************************************
 * Copyright (c) 2006, 2019 THALES GLOBAL SERVICES.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *  
 * Contributors:
 *    Thales - initial API and implementation
 *******************************************************************************/

package org.polarsys.capella.common.tools.report.config.registry;

import java.io.IOException;
import java.net.URL;
import java.util.Collection;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Map;

import org.apache.log4j.Appender;
import org.apache.log4j.ConsoleAppender;
import org.apache.log4j.Hierarchy;
import org.apache.log4j.Level;
import org.apache.log4j.Logger;
import org.eclipse.core.internal.runtime.RuntimeLog;
import org.eclipse.core.runtime.FileLocator;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.jobs.IJobChangeEvent;
import org.eclipse.core.runtime.jobs.Job;
import org.eclipse.core.runtime.jobs.JobChangeAdapter;
import org.osgi.framework.Bundle;
import org.polarsys.capella.common.tools.report.EmbeddedMessage;
import org.polarsys.capella.common.tools.report.EmbeddedMessageRenderer;
import org.polarsys.capella.common.tools.report.ReportManagerActivator;
import org.polarsys.capella.common.tools.report.StatusRenderer;
import org.polarsys.capella.common.tools.report.appenders.IFlushableAppenders;
import org.polarsys.capella.common.tools.report.appenders.ReportManagerFilter;
import org.polarsys.capella.common.tools.report.config.ReportManagerConstants;
import org.polarsys.capella.common.tools.report.config.persistence.ConfigurationInstance;
import org.polarsys.capella.common.tools.report.config.persistence.CreateXmlConfiguration;
import org.polarsys.capella.common.tools.report.util.IJobConstants;
import org.polarsys.capella.common.tools.report.util.IReportManagerDefaultComponents;
import org.polarsys.capella.common.tools.report.util.LogExt;

public class ReportManagerRegistry {
	
  private static final Logger logger = Logger.getLogger(ReportManagerRegistry.class.getName());

  private final Object lockObj = new Object();

  private Map<String, ConfigurationInstance> configurations = new HashMap<>(1);

  private static ReportManagerRegistry instance;

  /**
   * Initial loading of configuration details of each component
   */
  protected ReportManagerRegistry() {

    Collection<String> kinds = getAppenderKinds();

    CreateXmlConfiguration configuration = new CreateXmlConfiguration();

    // Load default configuration
    ConfigurationInstance defaultConfInstance = configuration
        .createDefaultConfiguration(IReportManagerDefaultComponents.DEFAULT, kinds);

    configurations.put(defaultConfInstance.getComponentName(), defaultConfInstance);

    // Load configuration from XML configuration file
    if (configuration.isConfigurationFileExists()) {
      Map<String, ConfigurationInstance> persisted = configuration.loadConfiguration();
      Map<String, ConfigurationInstance> result = new HashMap<>(1);

      for (Map.Entry<String, ConfigurationInstance> entry : persisted.entrySet()) {
        if (entry.getKey() != null) {
          ConfigurationInstance newConf = configuration.createDefaultConfiguration(entry.getKey(), kinds);
          newConf.merge(entry.getValue());
          result.put(entry.getKey(), newConf);
        }
      }

      configurations.putAll(result);
    }

    setConfigurations(configurations);

    // Register loggers into Log4J
    initRootLogger();
    
    Job.getJobManager().addJobChangeListener(new JobChangeAdapter() {
      
      @Override
      public void done(IJobChangeEvent event) {
        Object property = event.getJob().getProperty(IJobConstants.ALWAYS_LOG_STATUS);
        if (Boolean.TRUE.equals(property)) {
          IStatus status = event.getResult();
          if (!status.matches(IStatus.ERROR) && !status.matches(IStatus.WARNING)) {
            //JobManager.endJob logs only ERROR and WARNING
            RuntimeLog.log(status);
          }
          LogExt.log(IReportManagerDefaultComponents.DEFAULT, status);
        }
      }
      
    });
  }

  public Collection<String> getAppenderKinds() {
    Collection<String> ids = new LinkedHashSet<>();
    List<Appender> appenders = ReportManagerActivator.getDefault().getAppenders();
    for (Appender appender : appenders) {
      ids.add(appender.getName());
    }
    return ids;
  }

  public static String getConfigurationFile(Bundle bundle, String path) {
    try {
      URL confURL = bundle.getEntry(path);
      return FileLocator.toFileURL(confURL).getPath();

    } catch (IOException e1) {
      // Nothing here
    }
    return null;
  }

  public static synchronized ReportManagerRegistry getInstance() {
    if (instance == null) {
      instance = new ReportManagerRegistry();
      instance.subscribe(IReportManagerDefaultComponents.DEFAULT); // $NON-NLS-1$
    }
    return instance;
  }


  /**
   * Register appenders into Log4J
   */
  protected void initRootLogger() {

    try {
      Logger root = Logger.getRootLogger();

      // We don't want default level being overridden by a plugin
      root.setLevel(Level.DEBUG);

      // If any, console appenders must comply with levels defined in Windows/Preferences/MDE-Reporting preferences, so we add our filter mechanism on them
      for (Enumeration<Appender> e = root.getAllAppenders(); e.hasMoreElements();) {
        Appender appender = e.nextElement();
        if (appender instanceof ConsoleAppender) {
           appender.addFilter(new ReportManagerFilter(ReportManagerConstants.LOG_OUTPUT_CONSOLE));
        }
      }
      
      for (Appender appender : ReportManagerActivator.getDefault().getAppenders()) {
        root.addAppender(appender);
        appender.addFilter(new ReportManagerFilter(appender));
      }
      
      Hierarchy h = (Hierarchy) root.getLoggerRepository();
      EmbeddedMessageRenderer emRenderer = new EmbeddedMessageRenderer();
      h.addRenderer(EmbeddedMessage.class, emRenderer);

      StatusRenderer stRenderer = new StatusRenderer();
      h.addRenderer(IStatus.class, stRenderer);
      
    } catch (Exception exception) {
      logger.error(exception.getMessage(), exception);
    }
  }

  public Logger subscribe(String componentName) {
    return subscribe(componentName, null);
  }

  public Logger subscribe(String componentName, String defaultConfigurationPath) {
    getComponentConfiguration(componentName, defaultConfigurationPath);
    return Logger.getLogger(componentName);
  }

  /**
   * @param componentName
   */
  public void unSubscribe(String componentName) {
    if (configurations.containsKey(componentName)) {
      synchronized (lockObj) {
        configurations.remove(componentName);
      }
    }
  }

  /**
   * retrieves the flushable appenders
   * 
   * @return
   */
  protected List<IFlushableAppenders> getFlushableAppenders() {
    ReportManagerActivator act = ReportManagerActivator.getDefault();
    return act.getFlushableAppenders();
  }

  /**
   * 
   */
  public void beginLoggingSession() {
    beginLoggingSession(IFlushableAppenders.ALL);
  }

  /**
   * @param componentName
   */
  public void beginLoggingSession(String componentName) {
    beginLoggingSession(componentName, null);
  }

  /**
   * @param componentName
   * @param loggedElement
   */
  public void beginLoggingSession(String componentName, Object loggedElement) {
    for (IFlushableAppenders appender : getFlushableAppenders()) {
    	appender.flush(componentName, loggedElement);
    }
  }

  /**
   * @see org.polarsys.capella.common.tools.report.config.registry.IReportManagerRegistry#getComponentsList()
   */
  public Object[] getComponentsList() {
    return configurations.keySet().toArray();
  }

  /**
   * @see org.polarsys.capella.common.tools.report.config.registry.IReportManagerRegistry#getComponentConfiguration(java.lang.String)
   */
  public ConfigurationInstance getComponentConfiguration(String componentName) {
    return getComponentConfiguration(componentName, null);
  }

  protected ConfigurationInstance getComponentConfiguration(String componentName, String defaultConfigurationPath) {
    synchronized (configurations) {
      ConfigurationInstance cfgInstance = configurations.get(componentName);

      if (null == cfgInstance && defaultConfigurationPath != null) {
        cfgInstance = loadFromFile(defaultConfigurationPath, componentName);
      }
      if (null == cfgInstance) {
        cfgInstance = configurations.get(IReportManagerDefaultComponents.DEFAULT).clone();
        cfgInstance.setComponentName(componentName);
      }

      configurations.put(componentName, cfgInstance);
      return cfgInstance;
    }

  }

  private ConfigurationInstance loadFromFile(String defaultConfigurationPath, String componentName) {
    try {
      return new CreateXmlConfiguration(defaultConfigurationPath).loadConfiguration().get(componentName);
    } catch (Exception e) {
      // Invalid configuration file
    }
    return null;
  }

  /**
   * @see org.polarsys.capella.common.tools.report.config.registry.IReportManagerRegistry#saveConfiguration()
   */
  public void saveConfiguration() {
    CreateXmlConfiguration configuration = new CreateXmlConfiguration();

    synchronized (configurations) {
      configuration.saveConfiguration(configurations);
    }
  }

  /**
   * @see org.polarsys.capella.common.tools.report.config.registry.IReportManagerRegistry#getConfigurations()
   */
  public Map<String, ConfigurationInstance> getConfigurations() {
    return configurations;
  }

  /**
   * @param map
   *          the _configurationMap to set
   */
  public void setConfigurations(Map<String, ConfigurationInstance> map) {
    synchronized (lockObj) {
      configurations = map;
    }
  }

  /**
   * @see org.polarsys.capella.common.tools.report.config.registry.IReportManagerRegistry#toString()
   */
  @Override
  public String toString() {
    return "ReportManager"; //$NON-NLS-1$
  }
}