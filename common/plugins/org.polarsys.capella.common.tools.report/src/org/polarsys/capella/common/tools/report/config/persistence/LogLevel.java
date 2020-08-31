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

import org.w3c.dom.Attr;
import org.w3c.dom.Document;
import org.w3c.dom.Element;

public class LogLevel implements Cloneable {

    protected String _name;
    protected boolean _value;

    public LogLevel() {
      // Do nothing
    }

    public LogLevel(Element element) {
      setName(element.getAttribute("name"));
      setValue(Boolean.parseBoolean(element.getAttribute("value")));
    }

    /**
     * Gets the value of the name property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getName() {
        return _name;
    }

    /**
     * Sets the value of the name property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setName(String value) {
        this._name = value;
    }

    /**
     * Gets the value of the value property.
     * 
     */
    public boolean isValue() {
        return _value;
    }

    /**
     * Sets the value of the value property.
     * 
     */
    public void setValue(boolean value) {
        this._value = value;
    }
    
    public Element convertToElement(Document document) {
      Element element = document.createElement("LogLevel");
      
      Attr nameAttr = document.createAttribute("name");
      nameAttr.setValue(getName());
      element.setAttributeNode(nameAttr);
      
      Attr valueAttr = document.createAttribute("value");
      valueAttr.setValue(Boolean.toString(isValue()));
      element.setAttributeNode(valueAttr);
      
      return element;
    }
    
    @Override
    protected LogLevel clone() {
      LogLevel clone = new LogLevel();
      clone._name = _name;
      clone._value = _value;
      return clone;
    }

}
