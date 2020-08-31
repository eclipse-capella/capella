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
import java.io.IOException;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.xml.XMLConstants;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.OutputKeys;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;

import org.eclipse.emf.common.util.URI;
import org.polarsys.capella.common.tools.report.config.ReportManagerConstants;
import org.w3c.dom.Document;
import org.xml.sax.SAXException;

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
      Map<String, ConfigurationInstance> configurationMap = new HashMap<>();
      try {
        DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
        factory.setAttribute(XMLConstants.ACCESS_EXTERNAL_DTD, "");
        factory.setAttribute(XMLConstants.ACCESS_EXTERNAL_SCHEMA, "");
        DocumentBuilder builder;
        builder = factory.newDocumentBuilder();
        Document document = builder.parse(new File(filePath));
        document.getDocumentElement().normalize();
        ReportConfigurationFile file = new ReportConfigurationFile(document.getDocumentElement());
        configurationMap.putAll(getConfiguration(file));
      } catch (ParserConfigurationException | SAXException | IOException e) {
        e.printStackTrace();
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

  private void doSaveConfigurationFile(ReportConfigurationFile repConffile) {
    try {
      DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
      factory.setAttribute(XMLConstants.ACCESS_EXTERNAL_DTD, "");
      factory.setAttribute(XMLConstants.ACCESS_EXTERNAL_SCHEMA, "");
      DocumentBuilder documentBuilder = factory.newDocumentBuilder();
      Document document = documentBuilder.newDocument();
      document.setXmlStandalone(true);
      document.appendChild(repConffile.convertToElement(document));
      TransformerFactory transformerFactory = TransformerFactory.newInstance();
      transformerFactory.setAttribute(XMLConstants.ACCESS_EXTERNAL_DTD, "");
      transformerFactory.setAttribute(XMLConstants.ACCESS_EXTERNAL_STYLESHEET, "");
      Transformer transformer = transformerFactory.newTransformer();
      transformer.setOutputProperty(OutputKeys.INDENT, "yes");
      transformer.setOutputProperty("{http://xml.apache.org/xslt}indent-amount", "4");
      DOMSource domSource = new DOMSource(document);
      StreamResult streamResult = new StreamResult(new File(filePath));
      transformer.transform(domSource, streamResult);
    } catch (ParserConfigurationException | TransformerException e) {
      e.printStackTrace();
    }
  }
}