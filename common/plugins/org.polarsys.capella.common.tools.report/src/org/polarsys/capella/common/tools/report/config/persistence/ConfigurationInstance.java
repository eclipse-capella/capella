/*******************************************************************************
 * Copyright (c) 2006, 2017 THALES GLOBAL SERVICES.
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

public class ConfigurationInstance implements Cloneable {

  protected List<OutputConfiguration> outputConfiguration;
  protected String componentName;

  public ConfigurationInstance() {
    // Do nothing
  }
  
  public ConfigurationInstance(Element element) {
    setComponentName(element.getAttribute("ComponentName"));
    NodeList outputConfigList = element.getElementsByTagName("OutputConfiguration");
    outputConfiguration = new ArrayList<>();
    for (int i = 0; i < outputConfigList.getLength(); i++) {
      outputConfiguration.add(new OutputConfiguration((Element) outputConfigList.item(i)));
    }
  }

  public List<OutputConfiguration> getOutputConfiguration() {
    if (outputConfiguration == null) {
      outputConfiguration = new ArrayList<OutputConfiguration>(1);
    }
    return this.outputConfiguration;
  }

  /**
   * Gets the value of the componentName property.
   * 
   * @return possible object is {@link String }
   * 
   */
  public String getComponentName() {
    return componentName;
  }

  /**
   * Sets the value of the componentName property.
   * 
   * @param value
   *          allowed object is {@link String }
   * 
   */
  public void setComponentName(String value) {
    this.componentName = value;
  }
  
  public Element convertToElement(Document document) {
    Element element = document.createElement("ConfigurationInstance");
    
    Attr componentNameAttr = document.createAttribute("ComponentName");
    componentNameAttr.setValue(getComponentName());
    element.setAttributeNode(componentNameAttr);
    
    getOutputConfiguration().forEach(config -> {
      Element outputConfigElement = config.convertToElement(document);
      element.appendChild(outputConfigElement);
    });
    
    return element;
  }

  @Override
  public ConfigurationInstance clone() {
    ConfigurationInstance clone = new ConfigurationInstance();
    clone.componentName = componentName;
    
    for (OutputConfiguration config : getOutputConfiguration()) {
      clone.getOutputConfiguration().add((OutputConfiguration)config.clone());
    }
    return clone;
  }

  /**
   * Merge the source into the current instance
   */
  public void merge(ConfigurationInstance source) {
    for (OutputConfiguration srcOutputConf : source.getOutputConfiguration()) {
      for (OutputConfiguration tgtOutputConf : this.getOutputConfiguration()) {
        if ((srcOutputConf.getOutputName() != null)
            && srcOutputConf.getOutputName().equals(tgtOutputConf.getOutputName())) {
          merge(srcOutputConf, tgtOutputConf);
          break;
        }
      }
    }
  }
  
  protected void merge(OutputConfiguration srcOutputConf, OutputConfiguration tgtOutputConf) {
    for (LogLevel srcLogLevel : srcOutputConf.getLogLevel()) {
      for (LogLevel tgtLogLevel : tgtOutputConf.getLogLevel()) {
        if (srcLogLevel.getName().equals(tgtLogLevel.getName())) {
          tgtLogLevel.setValue(srcLogLevel.isValue());
          break;
        }
      }
    }
  }
  

}
