/*******************************************************************************
 * Copyright (c) 2006, 2020 THALES GLOBAL SERVICES.
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

import java.util.ArrayList;
import java.util.List;

import org.w3c.dom.Attr;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;


public class ReportConfigurationFile {

    protected List<ConfigurationInstance> configurationInstance;
    protected String fileFormatVersion;
    protected String reportManagerVersion;
    
    public ReportConfigurationFile() {
      // Do nothing
    }

    public ReportConfigurationFile(Element element) {
      setFileFormatVersion(element.getAttribute("fileFormatVersion"));
      setReportManagerVersion(element.getAttribute("reportManagerVersion"));
      NodeList configInstanceList = element.getElementsByTagName("ConfigurationInstance");
      configurationInstance = new ArrayList<>();
      for (int i = 0; i < configInstanceList.getLength(); i++) {
        configurationInstance.add(new ConfigurationInstance((Element) configInstanceList.item(i)));
      }
    }

    public List<ConfigurationInstance> getConfigurationInstance() {
        if (configurationInstance == null) {
            configurationInstance = new ArrayList<ConfigurationInstance>(1);
        }
        return this.configurationInstance;
    }

   public void setConfigurationInstance( List<ConfigurationInstance> configurationInstance){
     this.configurationInstance = configurationInstance;
   }
    
    /**
     * Gets the value of the fileFormatVersion property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getFileFormatVersion() {
        return fileFormatVersion;
    }

    /**
     * Sets the value of the fileFormatVersion property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setFileFormatVersion(String value) {
        this.fileFormatVersion = value;
    }

    /**
     * Gets the value of the reportManagerVersion property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getReportManagerVersion() {
        return reportManagerVersion;
    }

    /**
     * Sets the value of the reportManagerVersion property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setReportManagerVersion(String value) {
        this.reportManagerVersion = value;
    }
    
    public Element convertToElement(Document document) {
      Element element = document.createElement("ReportConfigurationFile");
      
      Attr fileFormatVersionAttr = document.createAttribute("fileFormatVersion");
      fileFormatVersionAttr.setValue(getFileFormatVersion());
      element.setAttributeNode(fileFormatVersionAttr);
      
      Attr reportManagerVersionAttr = document.createAttribute("reportManagerVersion");
      reportManagerVersionAttr.setValue(getFileFormatVersion());
      element.setAttributeNode(reportManagerVersionAttr);
      
      getConfigurationInstance().forEach(configInstance -> {
        Element outputConfigElement = configInstance.convertToElement(document);
        element.appendChild(outputConfigElement);
      });
      
      return element;
    }

}
