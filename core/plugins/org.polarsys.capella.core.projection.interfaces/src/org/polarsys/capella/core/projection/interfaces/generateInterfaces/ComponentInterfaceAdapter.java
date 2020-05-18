/*******************************************************************************
 * Copyright (c) 2017, 2018 THALES GLOBAL SERVICES.
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
package org.polarsys.capella.core.projection.interfaces.generateInterfaces;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

import org.eclipse.emf.ecore.EObject;
import org.polarsys.capella.common.data.activity.InputPin;
import org.polarsys.capella.common.data.activity.OutputPin;
import org.polarsys.capella.common.helpers.EObjectLabelProviderHelper;
import org.polarsys.capella.core.data.cs.Component;
import org.polarsys.capella.core.data.cs.Interface;
import org.polarsys.capella.core.data.fa.AbstractFunction;
import org.polarsys.capella.core.data.fa.ComponentExchange;
import org.polarsys.capella.core.data.fa.FunctionInputPort;
import org.polarsys.capella.core.data.fa.FunctionOutputPort;

class ComponentInterfaceAdapter implements InterfaceProvider, InterfaceRequirer {

  private final Component component;
  
  ComponentInterfaceAdapter(Component component){
    this.component = component;
  }
  
  @Override
  public List<FunctionOutputPort> getFunctionOutputPorts() {
    List<FunctionOutputPort> result = new ArrayList<>();
    for (AbstractFunction function : component.getAllocatedFunctions()){
      for (OutputPin pin : function.getOutputs()){
        if (pin instanceof FunctionOutputPort){
          result.add((FunctionOutputPort) pin);
        }
      }
    }
    return result;
  }

  @Override
  public Collection<FunctionInputPort> getFunctionInputPorts() {
    List<FunctionInputPort> result = new ArrayList<>();
    for (AbstractFunction function : component.getAllocatedFunctions()){
      for (InputPin pin : function.getInputs()){
        if (pin instanceof FunctionInputPort){
          result.add((FunctionInputPort) pin);
        }
      }
    }
    return result;
  }

  @Override
  public int hashCode() {
    final int prime = 31;
    int result = 1;
    result = prime * result + ((component == null) ? 0 : component.hashCode());
    return result;
  }

  @Override
  public boolean equals(Object obj) {
    if (this == obj)
      return true;
    if (obj == null)
      return false;
    if (getClass() != obj.getClass())
      return false;
    ComponentInterfaceAdapter other = (ComponentInterfaceAdapter) obj;
    if (component == null) {
      if (other.component != null)
        return false;
    } else if (!component.equals(other.component)) {
    	return false;
    }
    return true;
  }

  @Override
  public boolean addRequiredInterface(Interface iface) {
    return false;
  }

  @Override
  public EObject getEObject() {
    return component;
  }

  @Override
  public String getText() {
    return EObjectLabelProviderHelper.getText(component);
  }

  @Override
  public boolean addProvidedInterface(Interface iface) {
    return false;
  }

  @Override
  public Collection<ComponentExchange> getComponentExchanges() {
    return Collections.emptyList();
  }

}
