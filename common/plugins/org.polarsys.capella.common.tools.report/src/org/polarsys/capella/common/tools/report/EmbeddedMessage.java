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

package org.polarsys.capella.common.tools.report;


import java.util.ArrayList;
import java.util.List;

/**
 * A log message that is associated to one or more model elements.
 * 
 */
public class EmbeddedMessage {
  private String label;
  private String componentName;
  private List<Object> capellaElements = new ArrayList<>();
  private String source;
  
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
    if (capellaElements != null) {
      if (capellaElements instanceof List<?>) {
        setCapellaElements((List<Object>)capellaElements);
      } else {
        List<Object> objets = new ArrayList<>();
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
    StringBuilder containsMessage = new StringBuilder();
    containsMessage.append("[" + getComponentName() + "]\t");
    // Why 9
    if (getComponentName().length() < 9) {
      containsMessage.append('\t');
    }
    containsMessage.append(getLabel());
    // Not required in case of console output
    if (capellaElements != null && !capellaElements.isEmpty()) {
      containsMessage.append(" - Object(s) list : \n");
      for (Object theElement : capellaElements) {
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
      
    } catch (Exception exception) {
    	// Fail silently
    } finally {
      if (name == null) {
        name = theElement.toString();
      }
    }
    return name;
  }

  public String getComponentName() {
    return componentName;
  }

  public void setComponentName(String componentName) {
    this.componentName = componentName;
  }

  public String getLabel() {
    return label;
  }

  public void setLabel(String label) {
    this.label = label;
  }

  public List<Object> getCapellaElements() {
    return capellaElements;
  }

  public void setCapellaElements(List<Object> capellaElements) {
    this.capellaElements = capellaElements;
  }
  
  /**
   * @deprecated use {@link EmbeddedMessage#setSource(String)}
   */
  @Deprecated
  public void setInfo(String info){
    setSource(info);
  }

  public void setSource(String source){
    this.source = source;
  }
  public String getSource(){
    return source;
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
  
  @Override
  public int hashCode() {
	// To satisfy Sonar
	return super.hashCode();
  }
}
