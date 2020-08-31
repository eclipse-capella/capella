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

public class OutputConfiguration implements Cloneable {

  protected List<LogLevel> logLevel;
  protected String outputName;

  public OutputConfiguration() {
    // Do nothing
  }
  
  public OutputConfiguration(Element element) {
    setOutputName(element.getAttribute("outputName"));
    NodeList logLevelList = element.getElementsByTagName("LogLevel");
    logLevel = new ArrayList<>();
    for (int i = 0; i < logLevelList.getLength(); i++) {
      logLevel.add(new LogLevel((Element) logLevelList.item(i)));
    }
  }

  public List<LogLevel> getLogLevel() {
    if (logLevel == null) {
      logLevel = new ArrayList<LogLevel>(1);
    }
    return this.logLevel;
  }

  /**
   * Gets the value of the outputName property.
   * 
   * @return possible object is {@link String }
   * 
   */
  public String getOutputName() {
    return outputName;
  }

  /**
   * Sets the value of the outputName property.
   * 
   * @param value
   *          allowed object is {@link String }
   * 
   */
  public void setOutputName(String value) {
    this.outputName = value;
  }

  public Element convertToElement(Document document) {
    Element element = document.createElement("OutputConfiguration");
    
    Attr outputNameAttr = document.createAttribute("outputName");
    outputNameAttr.setValue(getOutputName());
    element.setAttributeNode(outputNameAttr);
    
    getLogLevel().forEach(level -> {
      Element logLevelElement = level.convertToElement(document);
      element.appendChild(logLevelElement);
    });
    
    return element;
  }
  
  @Override
  protected OutputConfiguration clone() {
    OutputConfiguration clone = new OutputConfiguration();
    clone.outputName = outputName;
    for (LogLevel level : getLogLevel()) {
      clone.getLogLevel().add(level.clone());
    }
    return clone;
  }

}
