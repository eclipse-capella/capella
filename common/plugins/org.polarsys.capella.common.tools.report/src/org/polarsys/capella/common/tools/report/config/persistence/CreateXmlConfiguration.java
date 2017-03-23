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

package org.polarsys.capella.common.tools.report.config.persistence;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.PropertyException;
import javax.xml.bind.Unmarshaller;

import org.apache.log4j.Appender;

import org.polarsys.capella.common.tools.report.config.ReportManagerConstants;

/**
 * 
 */
public class CreateXmlConfiguration {
  /**
   * 
   */
  private static final String FILE_PATH = System.getProperty("user.home") + File.separator + "ReportConfiguration.xml"; //$NON-NLS-1$//$NON-NLS-2$

  private final ObjectFactory _factory = new ObjectFactory();

  public CreateXmlConfiguration() {
    super();
  }

  /**
   * Create default XML configuration file.
   * @param componentName name of current business component
   * @param map map of available appenders
   * @return
   * 
   */
  public ConfigurationInstance createDefaultConfiguration(String componentName, Map<String, Appender> map) {

    Set<String> appenders = map.keySet();

    ConfigurationInstance confInstance = _factory.createConfigurationInstance();
    confInstance.setComponentName(componentName);

    // list of outputConfigs
    List<OutputConfiguration> opConfList = confInstance.getOutputConfiguration();

    for (String appenderName : appenders) {
      OutputConfiguration currentConfig = _factory.createOutputConfiguration();
      currentConfig.setOutputName(appenderName);

      opConfList.add(currentConfig);

      createLogConfig(currentConfig, true);

    }

    return confInstance;
  }

  /**
   * 
   * 
   */
  private void createLogConfig(OutputConfiguration outputConfiguration, boolean logLevelValue) {
    List<LogLevel> logLevelListFile = outputConfiguration.getLogLevel();

    logLevelListFile.clear();

    if (ReportManagerConstants.LOG_OUTPUT_FILE.equals(outputConfiguration.getOutputName())) {
      logLevelListFile.add(buildLogLevel(false, ReportManagerConstants.LOG_LEVEL_INFO));
      logLevelListFile.add(buildLogLevel(false, ReportManagerConstants.LOG_LEVEL_DEBUG));
      logLevelListFile.add(buildLogLevel(false, ReportManagerConstants.LOG_LEVEL_WARN));
      logLevelListFile.add(buildLogLevel(false, ReportManagerConstants.LOG_LEVEL_ERROR));
      logLevelListFile.add(buildLogLevel(false, ReportManagerConstants.LOG_LEVEL_FATAL));
    } else {
      logLevelListFile.add(buildLogLevel(logLevelValue, ReportManagerConstants.LOG_LEVEL_INFO));
      logLevelListFile.add(buildLogLevel(false, ReportManagerConstants.LOG_LEVEL_DEBUG));
      logLevelListFile.add(buildLogLevel(logLevelValue, ReportManagerConstants.LOG_LEVEL_WARN));
      logLevelListFile.add(buildLogLevel(logLevelValue, ReportManagerConstants.LOG_LEVEL_ERROR));
      logLevelListFile.add(buildLogLevel(logLevelValue, ReportManagerConstants.LOG_LEVEL_FATAL));
    }
  }
  /**
   * @param logLevelValue
   * @return
   */
  private LogLevel buildLogLevel(boolean logLevelValue, String logLevelInfo) {
    LogLevel infoLevelFile = _factory.createLogLevel();
    infoLevelFile.setName(logLevelInfo);
    infoLevelFile.setValue(logLevelValue);
    return infoLevelFile;
  }

  /**
   * Check if the configuration file exists
   * @return
   */
  public boolean isConfigurationFileExists() {
    // To do check for correct path in addition to adding uml model project name to file name
    File file = new File(FILE_PATH);
    return file.exists();
  }

  /**
   * load persisted configuration
   * 
   * Returns configuration Instance Map
   * @return configurationMap
   */
  public HashMap<String, ConfigurationInstance> loadConfiguration() {
    if (isConfigurationFileExists()) {
      HashMap<String, ConfigurationInstance> configurationMap = new HashMap<String, ConfigurationInstance>(1);
      try {
        JAXBContext jc = getJAXBContext();

        // create a UnMarshaller and marshal to a file
        Unmarshaller marshaller = jc.createUnmarshaller();
        // To do Check for file correct path
        ReportConfigurationFile file = (ReportConfigurationFile) marshaller.unmarshal(new FileInputStream(FILE_PATH));

        configurationMap.putAll(getConfiguration(file));

      } catch (JAXBException je) {
        je.printStackTrace();
        return configurationMap;

      } catch (IOException ioe) {
        ioe.printStackTrace();
        return configurationMap;
      }
      return configurationMap;
    }
    return null;
  }

  /**
   * load persisted configuration
   * 
   * Returns configuration Instance Map
   * @return configurationMap
   */
  public HashMap<String, ConfigurationInstance> getConfiguration(ReportConfigurationFile file) {
    HashMap<String, ConfigurationInstance> configurationMap = new HashMap<String, ConfigurationInstance>(1);

    List<ConfigurationInstance> confInst = file.getConfigurationInstance();

    for (ConfigurationInstance configurationInstance : confInst) {
      configurationMap.put(configurationInstance.getComponentName(), configurationInstance);
    }

    return configurationMap;
  }

  /**
   * save configuraion hashmap to configuration file
   * @param configurationMap
   */
  public void saveConfiguration(Map<String, ConfigurationInstance> configurationMap) {

    ReportConfigurationFile repConffile = _factory.createReportConfigurationFile();
    repConffile.setFileFormatVersion(ReportManagerConstants.FILEFORMAT_VERSION);
    repConffile.setReportManagerVersion(ReportManagerConstants.REPORTMANAGER_VERSION);
    Collection<ConfigurationInstance> confInst = configurationMap.values();
    List<ConfigurationInstance> confInstanceList = repConffile.getConfigurationInstance();
    confInstanceList.addAll(confInst);
    repConffile.setConfigurationInstance(confInstanceList);

    doSaveConfigurationFile(repConffile);

  }

  /**
   * do the jaxb technical stuff to save the configuration into a file
   * 
   * @param repConffile the virtual configuration
   * @throws JAXBException
   * @throws PropertyException
   * @throws FileNotFoundException
   */
  private void doSaveConfigurationFile(ReportConfigurationFile repConffile) {
    try {

      JAXBContext jc = getJAXBContext();
      Marshaller marshaller = jc.createMarshaller();
      marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, Boolean.TRUE);
      marshaller.marshal(repConffile, new FileOutputStream(FILE_PATH));

    } catch (PropertyException exception) {
      exception.printStackTrace();

    } catch (FileNotFoundException exception) {
      exception.printStackTrace();

    } catch (JAXBException exception) {
      exception.printStackTrace();
    }
  }

  /**
   * @return
   * @throws JAXBException
   */
  private JAXBContext getJAXBContext() throws JAXBException {
    ClassLoader theClassLoader = this.getClass().getClassLoader();
    JAXBContext jc = JAXBContext.newInstance(ReportManagerConstants.JAXB_INSTANCE, theClassLoader);
    return jc;
  }

}
