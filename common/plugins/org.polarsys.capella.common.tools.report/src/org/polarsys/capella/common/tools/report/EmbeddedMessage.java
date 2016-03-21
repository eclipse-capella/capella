/*******************************************************************************
 * Copyright (c) 2006, 2016 THALES GLOBAL SERVICES.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *  
 * Contributors:
 *    Thales - initial API and implementation
 *******************************************************************************/

package org.polarsys.capella.common.tools.report;


import java.util.ArrayList;
import java.util.List;

/**
 * A log message that is associated to one or more model elements.
 * 
 */
public class EmbeddedMessage {
  private String _label;
  private String _componentName;
  private List<Object> _capellaElements = new ArrayList<Object>();
  private String _source;
  
  /**
   * Constructor without object list
   * @param label : Title message
   * @param componentName : Component name
   */
  public EmbeddedMessage(String label, String componentName) {
    setLabel(label);
    setComponentName(componentName);
  }

  public EmbeddedMessage(String label, String componentName, List<Object> capellaElements) {
    this(label, componentName);
    setCapellaElements(capellaElements);
  }

  @SuppressWarnings("unchecked")
  public EmbeddedMessage(String label, String componentName, Object capellaElements) {
    this(label, componentName);
    if (capellaElements!=null) {
      if (capellaElements instanceof List<?>) {
        setCapellaElements((List<Object>)capellaElements);
      } else {
        List<Object> objets = new ArrayList<Object>();
        if (capellaElements instanceof Object[]) {
          for (Object obj : (Object[])capellaElements) {
            if (obj instanceof List<?>) {
              objets.addAll((List<?>)obj);
            } else {
              objets.add(obj);
            }
          }
        } else {
          objets.add(capellaElements);
        }
        setCapellaElements(objets);
      }
    }
  }

  @SuppressWarnings("nls")
  @Override
  public String toString() {
    StringBuffer containsMessage = new StringBuffer();
    containsMessage.append("[" + getComponentName() + "]\t");
    // Why 9
    if (getComponentName().length() < 9) {
      containsMessage.append('\t');
    }
    containsMessage.append(getLabel());
    // Not required in case of console output
    if (_capellaElements != null && _capellaElements.size() > 0) {
      containsMessage.append(" - Object(s) list : \n");
      for (Object theElement : _capellaElements) {
        if (theElement != null) {

          containsMessage.append(getName(theElement));
          containsMessage.append("\n");
        }
      }
    }
    return containsMessage.toString();
  }

  /**
   * @param theElement
   * @param name
   * @return
   */
  private String getName(Object theElement) {
    String name = null;

    try {
      name = (String) theElement.getClass().getMethod("getFullLabel", new Class[] {}).invoke(theElement, (Object[]) new Class[] {}); //$NON-NLS-1$
      
    } catch (Throwable exception_p) {
    } finally {
      if (name == null) {
        name = theElement.toString();
      }
    }
    return name;
  }

  public String getComponentName() {
    return _componentName;
  }

  public void setComponentName(String componentName) {
    _componentName = componentName;
  }

  public String getLabel() {
    return _label;
  }

  public void setLabel(String label) {
    _label = label;
  }

  public List<Object> getCapellaElements() {
    return _capellaElements;
  }

  public void setCapellaElements(List<Object> capellaElements) {
    _capellaElements = capellaElements;
  }
  
  /**
   * @deprecated use {@link EmbeddedMessage#setSource(String)}
   */
  @Deprecated
  public void setInfo(String info){
    setSource(info);
  }

  public void setSource(String source){
    _source = source;
  }
  public String getSource(){
    return _source;
  }

  @Override
  public boolean equals(Object ob) {
    if(ob instanceof EmbeddedMessage)
    {
      EmbeddedMessage newOb = (EmbeddedMessage)ob;
      boolean notEquElts = true;
      
      for (Object currentElt : this.getCapellaElements()) {
        for (Object newObElt : newOb.getCapellaElements()) {
          notEquElts = !currentElt.equals(newObElt);
        }
      }
      
      return  (
                this.getComponentName().equals(newOb.getComponentName()) &&
                this.getLabel().equals(newOb.getLabel()) &&
                !notEquElts
               );
    } 
    return false;
  }
  
  /**
   * Adapt the message and its specific features into the target element
   * @deprecated this method will be removed in a future version
   */
  @Deprecated
  public void adapt(Object target) {
  }
}
