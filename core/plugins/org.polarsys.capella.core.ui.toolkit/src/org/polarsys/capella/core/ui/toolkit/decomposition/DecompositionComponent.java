/*******************************************************************************
 * Copyright (c) 2006, 2014 THALES GLOBAL SERVICES.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
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
  private String _name;
  private Object _value;
  private List<DecompositionItem> _items;
  private String _decompositionName;
  private boolean _sourceComponent;
  private Decomposition parentDecomposition;
  private boolean _reusedComponent;
  private boolean _alreadyDecomposed;
  private Object _reusedTarget;
  private String _path;
  private boolean _isComposite;
  private boolean _triger = false;
  /**
   * Constructor
   */
  public DecompositionComponent() {
    _items = new ArrayList<DecompositionItem>();
  }

  /**
   * @return the component
   */
  public Object getValue() {
    return _value;
  }

  /**
   * @param component_p
   *          the component to set
   */
  public void setValue(Object value_p) {
    _value = value_p;
  }

  /**
   * @return the items
   */
  public List<DecompositionItem> getItems() {
    return _items;
  }
  
  /**
   * @param items_p
   *          the items to set
   */
  public void setItems(List<DecompositionItem> items_p) {
    for (DecompositionItem pair : items_p)
      pair.setParentComponent(this);
    _items = items_p;
  }

  /**
   * Adds a DecompositionItem item (Wrapper for any object) with name, value and status
   * @param item_p
   *          the item to be added
   */
  public void addItem(DecompositionItem item_p) {
    _items.add(item_p);
  }

  /**
   * Removes an item from the component
   * @param item_p
   *          the item to be removed
   */
  public void removeItem(DecompositionItem item_p) {
    _items.remove(item_p);
  }

  /**
   * @return the name
   */
  public String getName() {
    return _name;
  }

  /**
   * @param name_p
   *          the name to set
   */
  public void setName(String name_p) {
    _name = name_p;
  }

  /**
   * @return the decompositionName
   */
  public String getDecompositionName() {
    return _decompositionName;
  }

  /**
   * @param decompositionName_p
   *          the decompositionName to set
   */
  public void setDecompositionName(String decompositionName_p) {
    _decompositionName = decompositionName_p;
  }

  /**
   * @return the sourceComponent
   */
  public boolean isSourceComponent() {
    return _sourceComponent;
  }

  /**
   * @param sourceComponent_p
   *          the sourceComponent to set
   */
  public void setSourceComponent(boolean sourceComponent_p) {
    _sourceComponent = sourceComponent_p;
  }

  /**
   * @return the parentDecomposition
   */
  public Decomposition getParentDecomposition() {
    return parentDecomposition;
  }

  /**
   * @param parentDecomposition_p
   *          the parentDecomposition to set
   */
  public void setParentDecomposition(Decomposition parentDecomposition_p) {
    parentDecomposition = parentDecomposition_p;
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
    return _reusedComponent;
  }

  /**
   * @param reusedComponent_p the reusedComponent to set
   */
  public void setReusedComponent(boolean reusedComponent_p) {
    _reusedComponent = reusedComponent_p;
  }

  /**
   * @see java.lang.Object#equals(java.lang.Object)
   */
  @Override
  public boolean equals(Object object_p) {
    if (!(object_p instanceof DecompositionComponent))
      return false;
    if(this == object_p) return true;
    if (getValue() == null) {
      return false;
    }
    return getValue().equals(((DecompositionComponent) object_p).getValue());
  }

  /**
   * @return the alreadyDecomposed
   */
  public boolean isAlreadyDecomposed() {
    return _alreadyDecomposed;
  }

  /**
   * @param alreadyDecomposed_p the alreadyDecomposed to set
   */
  public void setAlreadyDecomposed(boolean alreadyDecomposed_p) {
    _alreadyDecomposed = alreadyDecomposed_p;
  }

  /**
   * @return the reusedTarget
   */
  public Object getReusedTarget() {
    return _reusedTarget;
  }

  /**
   * @param reusedTarget_p the reusedTarget to set
   */
  public void setReusedTarget(Object reusedTarget_p) {
    _reusedTarget = reusedTarget_p;
  }

  /**
   * @return the path
   */
  public String getPath() {
    return _path;
  }

  /**
   * @param path_p the path to set
   */
  public void setPath(String path_p) {
    _path = path_p;
  }

  /**
   * @return the isComposite
   */
  public boolean isComposite() {
    return _isComposite;
  }

  /**
   * @param isComposite_p the isComposite to set
   */
  public void setComposite(boolean isComposite_p) {
    _isComposite = isComposite_p;
  }
  /** 
   * @return the isTrigger : specific for LC Decomposition wizard.
   * 						 return true if naming convention triggered through this wizard 
   */
  public boolean isTrigger(){
	  return _triger;
  }
  /** 
   * @param trigger_p vaule to set : specific for LC Decomposition wizard.
   * 				 
   */
  public void setTrigger(boolean trigger_p){
	_triger =  trigger_p; 
  }
}
