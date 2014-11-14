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
    _targetComponents = new ArrayList<DecompositionComponent>(1);
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
   * @return the target components
   */
  public List<DecompositionComponent> getTargetComponents() {
    return _targetComponents;
  }

  /**
   * Adds a decomposition component
   * @param targetComponent_p
   *          the component to be added
   */
  public void addTargetComponent(DecompositionComponent targetComponent_p) {
    targetComponent_p.setDecompositionName(getName());
    targetComponent_p.setParentDecomposition(this);
    _targetComponents.add(targetComponent_p);
  }

  /**
   * Removes a target component
   * @param targetComponent_p
   *          the component to be removed
   */
  public void removeTargetComponent(DecompositionComponent targetComponent_p) {
    _targetComponents.remove(targetComponent_p);
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
   * @param value_p
   *          the value to set
   */
  public void setValue(Object value_p) {
    _value = value_p;
  }

  /**
   * @return the decompositionModel
   */
  public DecompositionModel getDecompositionModel() {
    return _decompositionModel;
  }

  /**
   * @param decompositionModel_p
   *          the decompositionModel to set
   */
  public void setDecompositionModel(DecompositionModel decompositionModel_p) {
    _decompositionModel = decompositionModel_p;
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
  public boolean equals(Object object_p) {
    if (!(object_p instanceof Decomposition))
      return false;
    if(object_p == this)return true;
    if (getValue() == null)
      return false;
    Decomposition that = (Decomposition) object_p;
    if(this.getValue().equals(DUMMY_VALUE) && that.getValue().equals(DUMMY_VALUE)) {
      return this.getName().equals(that.getName());
    }
    return this.getValue().equals(that.getValue());
  }
}
