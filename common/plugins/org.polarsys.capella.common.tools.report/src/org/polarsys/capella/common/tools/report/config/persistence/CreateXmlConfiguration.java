/*******************************************************************************
 * Copyright (c) 2006, 2019 THALES GLOBAL SERVICES.
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

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.PropertyException;
import javax.xml.bind.Unmarshaller;

import org.eclipse.emf.common.util.URI;
import org.polarsys.capella.common.tools.report.config.ReportManagerConstants;

/**
 * 
 */
public class CreateXmlConfiguration {
  /**
   * 
   */
  private static final String FILE_PATH = System.getProperty("osgi.configuration.area") + "ReportConfiguration.xml"; //$NON-NLS-1$//$NON-NLS-2$

  private final ObjectFactory objectFactory = new ObjectFactory();

  private String filePath = null;

  public CreateXmlConfiguration() {
    this.filePath = URI.createURI(FILE_PATH).toFileString();
  }

  public CreateXmlConfiguration(String filePath) {
    this.filePath = filePath;
  }

  /**
   * Create default XML configuration file.
   * 
   * @param componentName
   *          name of current business component
   * @param map
   *          map of available appenders
   * @return
   * 
   */
  public ConfigurationInstance createDefaultConfiguration(String componentName, Collection<String> appenders) {

    ConfigurationInstance confInstance = objectFactory.createConfigurationInstance();
    confInstance.setComponentName(componentName);

    // list of outputConfigs
    List<OutputConfiguration> opConfList = confInstance.getOutputConfiguration();

    for (String appenderName : appenders) {
      OutputConfiguration currentConfig = objectFactory.createOutputConfiguration();
      currentConfig.setOutputName(appenderName);

      opConfList.add(currentConfig);

      createLogConfig(currentConfig);

    }

    return confInstance;
  }

  private void createLogConfig(OutputConfiguration outputConfiguration) {
    List<LogLevel> logLevelListFile = outputConfiguration.getLogLevel();
    logLevelListFile.clear();

    boolean value = ReportManagerConstants.LOG_OUTPUT_PROBLEMS_VIEW.equals(outputConfiguration.getOutputName());
    logLevelListFile.add(buildLogLevel(value, ReportManagerConstants.LOG_LEVEL_INFO));
    logLevelListFile.add(buildLogLevel(value, ReportManagerConstants.LOG_LEVEL_WARN));
    logLevelListFile.add(buildLogLevel(value, ReportManagerConstants.LOG_LEVEL_ERROR));
    logLevelListFile.add(buildLogLevel(value, ReportManagerConstants.LOG_LEVEL_FATAL));
    logLevelListFile.add(buildLogLevel(false, ReportManagerConstants.LOG_LEVEL_DEBUG));
  }

  /**
   * @param logLevelValue
   * @return
   */
  private LogLevel buildLogLevel(boolean logLevelValue, String logLevelInfo) {
    LogLevel infoLevelFile = objectFactory.createLogLevel();
    infoLevelFile.setName(logLevelInfo);
    infoLevelFile.setValue(logLevelValue);
    return infoLevelFile;
  }

  /**
   * Check if the configuration file exists
   * 
   * @return
   */
  public boolean isConfigurationFileExists() {
    // To do check for correct path in addition to adding uml model project name to file name
    File file = new File(filePath);
    return file.exists();
  }

  /**
   * load persisted configuration
   * 
   * Returns configuration Instance Map   
   * 
   * @return configurationMap
   */
  public Map<String, ConfigurationInstance> loadConfiguration() {
    if (isConfigurationFileExists()) {
      Map<String, ConfigurationInstance> configurationMap = new HashMap<>(1);
      try {
        JAXBContext jc = getJAXBContext();

        // create a UnMarshaller and marshal to a file
        Unmarshaller marshaller = jc.createUnmarshaller();
        // To do Check for file correct path
        ReportConfigurationFile file = (ReportConfigurationFile) marshaller.unmarshal(new FileInputStream(filePath));

        configurationMap.putAll(getConfiguration(file));

      } catch (JAXBException | IOException exception) {
        exception.printStackTrace();
        return configurationMap;
      }
      return configurationMap;
    }
    return null;
  }

  /**
   * Load persisted configuration
   * 
   * Returns configuration Instance Map
   * 
   * @return configurationMap
   */
  public Map<String, ConfigurationInstance> getConfiguration(ReportConfigurationFile file) {
    Map<String, ConfigurationInstance> configurationMap = new HashMap<>(1);

    List<ConfigurationInstance> confInst = file.getConfigurationInstance();

    for (ConfigurationInstance configurationInstance : confInst) {
      configurationMap.put(configurationInstance.getComponentName(), configurationInstance);
    }

    return configurationMap;
  }

  /**
   * Save configuration map to configuration file
   * 
   * @param configurationMap
   */
  public void saveConfiguration(Map<String, ConfigurationInstance> configurationMap) {

    ReportConfigurationFile repConffile = objectFactory.createReportConfigurationFile();
    repConffile.setFileFormatVersion(ReportManagerConstants.FILEFORMAT_VERSION);
    repConffile.setReportManagerVersion(ReportManagerConstants.REPORTMANAGER_VERSION);
    Collection<ConfigurationInstance> confInst = configurationMap.values();
    List<ConfigurationInstance> confInstanceList = repConffile.getConfigurationInstance();
    confInstanceList.addAll(confInst);
    repConffile.setConfigurationInstance(confInstanceList);

    doSaveConfigurationFile(repConffile);

  }

  /**
   * Do the jaxb technical stuff to save the configuration into a file
   * 
   * @param repConffile
   *          the virtual configuration
   * @throws JAXBException
   * @throws PropertyException
   * @throws FileNotFoundException
   */
  private void doSaveConfigurationFile(ReportConfigurationFile repConffile) {
    try {

      JAXBContext jc = getJAXBContext();
      Marshaller marshaller = jc.createMarshaller();
      marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, Boolean.TRUE);
      marshaller.marshal(repConffile, new FileOutputStream(filePath));

    } catch (FileNotFoundException | JAXBException exception) {
      exception.printStackTrace();
    }
  }

  /**
   * @return
   * @throws JAXBException
   */
  private JAXBContext getJAXBContext() throws JAXBException {
    ClassLoader theClassLoader = this.getClass().getClassLoader();
    return JAXBContext.newInstance(ReportManagerConstants.JAXB_INSTANCE, theClassLoader);
  }
}