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
 * Class <code>Decomposition</code> will be having list of target components
 * @see DecompositionModel#addDecomposition(Decomposition)
 */
public class Decomposition {
  private String _name;
  private Object _value;
  private List<DecompositionComponent> _targetComponents;
  private DecompositionModel _decompositionModel;
  public static final Object DUMMY_VALUE = new Object();

  /**
   * Constructor
   */
  public Decomposition() {
    _targetComponents = new ArrayList<>(1);
  }

  /**
   * @return the name
   */
  public String getName() {
    return _name;
  }

  /**
   * @param name
   *          the name to set
   */
  public void setName(String name) {
    this._name = name;
  }

  /**
   * @return the target components
   */
  public List<DecompositionComponent> getTargetComponents() {
    return _targetComponents;
  }

  /**
   * Adds a decomposition component
   * @param targetComponent
   *          the component to be added
   */
  public void addTargetComponent(DecompositionComponent targetComponent) {
    targetComponent.setDecompositionName(getName());
    targetComponent.setParentDecomposition(this);
    _targetComponents.add(targetComponent);
  }

  /**
   * Removes a target component
   * @param targetComponent
   *          the component to be removed
   */
  public void removeTargetComponent(DecompositionComponent targetComponent) {
    _targetComponents.remove(targetComponent);
  }

  /**
   * Removes all target components
   */
  public void removeAllTargetComponents() {
    _targetComponents.clear();
  }

  /**
   * @return the value
   */
  public Object getValue() {
    return _value;
  }

  /**
   * @param value
   *          the value to set
   */
  public void setValue(Object value) {
    this._value = value;
  }

  /**
   * @return the decompositionModel
   */
  public DecompositionModel getDecompositionModel() {
    return _decompositionModel;
  }

  /**
   * @param decompositionModel
   *          the decompositionModel to set
   */
  public void setDecompositionModel(DecompositionModel decompositionModel) {
    this._decompositionModel = decompositionModel;
  }

  /**
   * @see java.lang.Object#toString()
   */
  @Override
  public String toString() {
    return getName();
  }

  /**
   * @see java.lang.Object#equals(java.lang.Object)
   */
  @Override
  public boolean equals(Object object) {
    if (!(object instanceof Decomposition))
      return false;
    if(object == this)return true;
    if (getValue() == null)
      return false;
    Decomposition that = (Decomposition) object;
    if(this.getValue().equals(DUMMY_VALUE) && that.getValue().equals(DUMMY_VALUE)) {
      return this.getName().equals(that.getName());
    }
    return this.getValue().equals(that.getValue());
  }
  
  @Override
  public int hashCode() {
	// To satisfy Sonar
	return super.hashCode();
  }
}
