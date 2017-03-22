/*******************************************************************************
 * Copyright (c) 2006, 2017 THALES GLOBAL SERVICES.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *  
 * Contributors:
 *    Thales - initial API and implementation
 *******************************************************************************/

package org.polarsys.capella.common.tools.report.config.registry;

import java.util.Enumeration;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.log4j.Appender;
import org.apache.log4j.Hierarchy;
import org.apache.log4j.Logger;
import org.polarsys.capella.common.tools.report.EmbeddedMessage;
import org.polarsys.capella.common.tools.report.EmbeddedMessageRenderer;
import org.polarsys.capella.common.tools.report.ReportManagerActivator;
import org.polarsys.capella.common.tools.report.appenders.IFlushableAppenders;
import org.polarsys.capella.common.tools.report.appenders.ReportManagerFilter;
import org.polarsys.capella.common.tools.report.config.ReportManagerConstants;
import org.polarsys.capella.common.tools.report.config.persistence.ConfigurationInstance;
import org.polarsys.capella.common.tools.report.config.persistence.CreateXmlConfiguration;
import org.polarsys.capella.common.tools.report.config.persistence.LogLevel;
import org.polarsys.capella.common.tools.report.config.persistence.OutputConfiguration;

public class ReportManagerRegistry {

  private Map<String, ConfigurationInstance> _configurations = new HashMap<String, ConfigurationInstance>(1);
  private Map<String, Appender> _appenders = new HashMap<String, Appender>(1);

  private static ReportManagerRegistry instance;

  /**
   * Initial loading of configuration details of each component
   */
  protected ReportManagerRegistry() {
    initRootLogger();

    CreateXmlConfiguration configuration = new CreateXmlConfiguration();

    // Create configuration file and start loading process.
    ConfigurationInstance defaultConfInstance = configuration.createDefaultConfiguration(ReportManagerConstants.LOG_OUTPUT_DEFAULT, _appenders);
    _configurations.put(defaultConfInstance.getComponentName(), defaultConfInstance);

    if (configuration.isConfigurationFileExists()) {
      // Load configuration from XML config file
      HashMap<String, ConfigurationInstance> persistedConfs = configuration.loadConfiguration();
      HashMap<String, ConfigurationInstance> virtualConfs = new HashMap<String, ConfigurationInstance>(1);

      for (Map.Entry<String, ConfigurationInstance> confEntry : persistedConfs.entrySet()) {
        ConfigurationInstance newVirtualConf = configuration.createDefaultConfiguration(confEntry.getKey(), _appenders);

        copyValuesOfConfigurationInstance(confEntry.getValue(), newVirtualConf);

        virtualConfs.put(confEntry.getKey(), newVirtualConf);

      }

      _configurations.putAll(virtualConfs);
    }

    setConfigurations(_configurations);

  }

  public synchronized static ReportManagerRegistry getInstance() {
    if (instance == null) {
      instance = new ReportManagerRegistry();
      instance.subscribe("Default"); //$NON-NLS-1$
    }
    return instance;
  }

  /**
   * @param source
   * @return
   */
  protected ConfigurationInstance copyConfig(ConfigurationInstance source) {
    ConfigurationInstance target = new ConfigurationInstance();
    target.setComponentName(source.getComponentName());
    List<OutputConfiguration> tgtOutputConfs = target.getOutputConfiguration();

    for (OutputConfiguration srcOutputConf : source.getOutputConfiguration()) {
      OutputConfiguration tgtOutputConf = new OutputConfiguration();
      tgtOutputConf.setOutputName(srcOutputConf.getOutputName());

      List<LogLevel> tgtLogLevels = tgtOutputConf.getLogLevel();
      for (LogLevel srcLogLevel : srcOutputConf.getLogLevel()) {
        LogLevel tgtLogLevel = new LogLevel();

        tgtLogLevel.setName(srcLogLevel.getName());
        tgtLogLevel.setValue(srcLogLevel.isValue());

        tgtLogLevels.add(tgtLogLevel);
      }

      tgtOutputConfs.add(tgtOutputConf);

    }

    return target;
  }

  /**
   * @param source
   * @param target
   * @return
   */
  protected void copyValuesOfConfigurationInstance(ConfigurationInstance source, ConfigurationInstance target) {

    if ((source.getComponentName() != null) && source.getComponentName().equals(target.getComponentName())) {
      copyValuesOfOutputConfiguration(source, target);
    }
  }

  /**
   * @param source
   * @param target
   */
  protected void copyValuesOfOutputConfiguration(ConfigurationInstance source, ConfigurationInstance target) {
    for (OutputConfiguration srcOutputConf : source.getOutputConfiguration()) {
      for (OutputConfiguration tgtOutputConf : target.getOutputConfiguration()) {
        if ((srcOutputConf.getOutputName() != null) && srcOutputConf.getOutputName().equals(tgtOutputConf.getOutputName())) {
          copyValuesOfLogLevels(srcOutputConf, tgtOutputConf);
          break;
        }
      }
    }
  }

  /**
   * @param srcOutputConf
   * @param tgtOutputConf
   */
  protected void copyValuesOfLogLevels(OutputConfiguration srcOutputConf, OutputConfiguration tgtOutputConf) {
    for (LogLevel srcLogLevel : srcOutputConf.getLogLevel()) {
      for (LogLevel tgtLogLevel : tgtOutputConf.getLogLevel()) {
        if (srcLogLevel.getName().equals(tgtLogLevel.getName())) {
          tgtLogLevel.setValue(srcLogLevel.isValue());
          break;
        }
      }
    }
  }

  /**
   * 
   */
  protected void initRootLogger() {

    try {
      Logger root = Logger.getRootLogger();
      ReportManagerActivator act = ReportManagerActivator.getDefault();
      List<Appender> theAppenders = act.getAppenders();

      for (Appender appender : theAppenders) {
        root.addAppender(appender);
        if (!(_appenders.containsKey(appender.getName()))) {
          _appenders.put(appender.getName(), appender);
        }
      }

      Hierarchy h = (Hierarchy) Logger.getRootLogger().getLoggerRepository();
      EmbeddedMessageRenderer emRenderer = new EmbeddedMessageRenderer();
      h.addRenderer(EmbeddedMessage.class, emRenderer);

    } catch (Throwable exception) {
      exception.printStackTrace();
    }

    Enumeration<?> appenders = Logger.getRootLogger().getAllAppenders();
    while (appenders.hasMoreElements()) {
      Appender currentApp = (Appender) appenders.nextElement();
      currentApp.clearFilters();
      currentApp.addFilter(new ReportManagerFilter(currentApp));
    }

  }

  /**
   * @param componentName
   * @return
   */
  public Logger subscribe(String componentName) {
    if (!_configurations.containsKey(componentName)) {

      ConfigurationInstance oConfigurationInstance = copyConfig(_configurations.get(ReportManagerConstants.LOG_OUTPUT_DEFAULT));

      if (null != oConfigurationInstance) {
        oConfigurationInstance.setComponentName(componentName);
        synchronized (_configurations) {
          _configurations.put(componentName, oConfigurationInstance);
        }
      }
    }

    Logger theLog = Logger.getLogger(componentName);

    return theLog;
  }

  /**
   * @param componentName
   */
  public void unSubscribe(String componentName) {
    if (_configurations.containsKey(componentName)) {
      synchronized (_configurations) {
        _configurations.remove(componentName);
      }
    }
  }

  /**
   * retrieves the flushable appenders
   * @return
   */
  protected List<IFlushableAppenders> getFlushableAppenders() {
    ReportManagerActivator act = ReportManagerActivator.getDefault();
    List<IFlushableAppenders> theAppenders = act.getFlushableAppenders();

    return theAppenders;
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
    for (IFlushableAppenders _appender : getFlushableAppenders()) {
      _appender.flush(componentName, loggedElement);
    }
  }


  /**
   * @see org.polarsys.capella.common.tools.report.config.registry.IReportManagerRegistry#getComponentsList()
   */
  public Object[] getComponentsList() {
    return _configurations.keySet().toArray();

  }

  /**
   * @see org.polarsys.capella.common.tools.report.config.registry.IReportManagerRegistry#getComponentConfiguration(java.lang.String)
   */
  public ConfigurationInstance getComponentConfiguration(String componentName) {
    ConfigurationInstance oConfigurationInstance = _configurations.get(componentName);
    if (null != oConfigurationInstance) {
      return oConfigurationInstance;
    }
    return oConfigurationInstance;
  }

  /**
   * @see org.polarsys.capella.common.tools.report.config.registry.IReportManagerRegistry#saveConfiguration()
   */
  public void saveConfiguration() {
    CreateXmlConfiguration configuration = new CreateXmlConfiguration();
    synchronized (_configurations) {
    	configuration.saveConfiguration(_configurations);
    }
  }

  /**
   * @see org.polarsys.capella.common.tools.report.config.registry.IReportManagerRegistry#getConfigurations()
   */
  public Map<String, ConfigurationInstance> getConfigurations() {
    return _configurations;
  }

  /**
   * @param map the _configurationMap to set
   */
  public void setConfigurations(Map<String, ConfigurationInstance> map) {
    synchronized (_configurations) {
      _configurations = map;
    }
  }

  /**
   * @see org.polarsys.capella.common.tools.report.config.registry.IReportManagerRegistry#toString()
   */
  @Override
  public String toString() {
    return "ReportManager"; //$NON-NLS-1$
  }

  /**
   * @return
   */
  public Map<String, Appender> getAppenders() {
    return _appenders;
  }

}
