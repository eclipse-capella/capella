/*******************************************************************************
 * Copyright (c) 2006, 2018 THALES GLOBAL SERVICES.
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
package org.polarsys.capella.core.ui.toolkit.decomposition;

import java.util.ArrayList;
import java.util.List;

/**
 * Class <code>DecompositionComponent</code> to use in the DecompositionModel.
 * @see Decomposition#addTargetComponent(DecompositionComponent)
 */
public class DecompositionComponent {
  private String name;
  private Object value;
  private List<DecompositionItem> items;
  private String decompositionName;
  private boolean sourceComponent;
  private Decomposition parentDecomposition;
  private boolean reusedComponent;
  private boolean alreadyDecomposed;
  private Object reusedTarget;
  private String path;
  private boolean isComposite;
  private boolean trigger = false;

  /**
   * Constructor
   */
  public DecompositionComponent() {
    items = new ArrayList<DecompositionItem>();
  }

  /**
   * @return the component
   */
  public Object getValue() {
    return value;
  }

  /**
   * @param component
   *          the component to set
   */
  public void setValue(Object value) {
    this.value = value;
  }

  /**
   * @return the items
   */
  public List<DecompositionItem> getItems() {
    return items;
  }
  
  /**
   * @param items
   *          the items to set
   */
  public void setItems(List<DecompositionItem> items) {
    for (DecompositionItem pair : items)
      pair.setParentComponent(this);
    this.items = items;
  }

  /**
   * Adds a DecompositionItem item (Wrapper for any object) with name, value and status
   * @param item
   *          the item to be added
   */
  public void addItem(DecompositionItem item) {
    items.add(item);
  }

  /**
   * Removes an item from the component
   * @param item
   *          the item to be removed
   */
  public void removeItem(DecompositionItem item) {
    items.remove(item);
  }

  /**
   * @return the name
   */
  public String getName() {
    return name;
  }

  /**
   * @param name
   *          the name to set
   */
  public void setName(String name) {
    this.name = name;
  }

  /**
   * @return the decompositionName
   */
  public String getDecompositionName() {
    return decompositionName;
  }

  /**
   * @param decompositionName
   *          the decompositionName to set
   */
  public void setDecompositionName(String decompositionName) {
    this.decompositionName = decompositionName;
  }

  /**
   * @return the sourceComponent
   */
  public boolean isSourceComponent() {
    return sourceComponent;
  }

  /**
   * @param sourceComponent
   *          the sourceComponent to set
   */
  public void setSourceComponent(boolean sourceComponent) {
    this.sourceComponent = sourceComponent;
  }

  /**
   * @return the parentDecomposition
   */
  public Decomposition getParentDecomposition() {
    return parentDecomposition;
  }

  /**
   * @param parentDecomposition
   *          the parentDecomposition to set
   */
  public void setParentDecomposition(Decomposition parentDecomposition) {
    this.parentDecomposition = parentDecomposition;
  }

  /**
   * @see java.lang.Object#toString()
   */
  @Override
  public String toString() {
    return getName();
  }

  /**
   * @return the reusedComponent
   */
  public boolean isReusedComponent() {
    return reusedComponent;
  }

  /**
   * @param reusedComponent the reusedComponent to set
   */
  public void setReusedComponent(boolean reusedComponent) {
    this.reusedComponent = reusedComponent;
  }

  /**
   * @see java.lang.Object#equals(java.lang.Object)
   */
  @Override
  public boolean equals(Object object) {
    if (!(object instanceof DecompositionComponent))
      return false;
    if(this == object) return true;
    if (getValue() == null) {
      return false;
    }
    return getValue().equals(((DecompositionComponent) object).getValue());
  }
  
  @Override
  public int hashCode() {
	// To satisfy Sonar
	return super.hashCode();
  }

  /**
   * @return the alreadyDecomposed
   */
  public boolean isAlreadyDecomposed() {
    return alreadyDecomposed;
  }

  /**
   * @param alreadyDecomposed the alreadyDecomposed to set
   */
  public void setAlreadyDecomposed(boolean alreadyDecomposed) {
    this.alreadyDecomposed = alreadyDecomposed;
  }

  /**
   * @return the reusedTarget
   */
  public Object getReusedTarget() {
    return reusedTarget;
  }

  /**
   * @param reusedTarget the reusedTarget to set
   */
  public void setReusedTarget(Object reusedTarget) {
    this.reusedTarget = reusedTarget;
  }

  /**
   * @return the path
   */
  public String getPath() {
    return path;
  }

  /**
   * @param path the path to set
   */
  public void setPath(String path) {
    this.path = path;
  }

  /**
   * @return the isComposite
   */
  public boolean isComposite() {
    return isComposite;
  }

  /**
   * @param isComposite the isComposite to set
   */
  public void setComposite(boolean isComposite) {
    this.isComposite = isComposite;
  }
  /** 
   * @return the isTrigger : specific for LC Decomposition wizard.
   * 						 return true if naming convention triggered through this wizard 
   */
  public boolean isTrigger(){
	  return trigger;
  }
  /** 
   * @param trigger vaule to set : specific for LC Decomposition wizard.
   * 				 
   */
  public void setTrigger(boolean trigger){
    this.trigger =  trigger; 
  }
}
