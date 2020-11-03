/*******************************************************************************
 * Copyright (c) 2017, 2020 THALES GLOBAL SERVICES.
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

import java.util.Collection;
import java.util.LinkedHashSet;

import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EReference;
import org.polarsys.capella.common.helpers.EObjectLabelProviderHelper;
import org.polarsys.capella.core.data.cs.Interface;
import org.polarsys.capella.core.data.fa.AbstractFunction;
import org.polarsys.capella.core.data.fa.ComponentExchange;
import org.polarsys.capella.core.data.fa.ComponentPort;
import org.polarsys.capella.core.data.fa.FaPackage;
import org.polarsys.capella.core.data.fa.FunctionInputPort;
import org.polarsys.capella.core.data.fa.FunctionOutputPort;
import org.polarsys.capella.core.data.fa.FunctionPort;
import org.polarsys.capella.core.data.fa.FunctionalExchange;

class ComponentPortInterfaceAdapter implements InterfaceRequirer, InterfaceProvider {

  @Override
  public int hashCode() {
    final int prime = 31;
    int result = 1;
    result = prime * result + ((port == null) ? 0 : port.hashCode());
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
    ComponentPortInterfaceAdapter other = (ComponentPortInterfaceAdapter) obj;
    if (port == null) {
      if (other.port != null)
        return false;
    } else if (!port.equals(other.port))
      return false;
    return true;
  }

  private final ComponentPort port;
  
  ComponentPortInterfaceAdapter(ComponentPort port){
    this.port = port;
  }
  
  @Override
  public Collection<FunctionInputPort> getFunctionInputPorts() {
    // all FIP directly allocated on this CP.
    Collection<FunctionInputPort> result = new LinkedHashSet<FunctionInputPort>();
    for (FunctionPort p : port.getAllocatedFunctionPorts()){
      if (p instanceof FunctionInputPort){
        result.add((FunctionInputPort) p);
      }
    }
    addFxPfromAllocatedFE(FaPackage.Literals.FUNCTIONAL_EXCHANGE__TARGET_FUNCTION_INPUT_PORT, result);
    return result;
  }


  @Override
  public Collection<FunctionOutputPort> getFunctionOutputPorts() {
    Collection<FunctionOutputPort> result = new LinkedHashSet<FunctionOutputPort>();
    for (FunctionPort p : port.getAllocatedFunctionPorts()){
      if (p instanceof FunctionOutputPort){
        result.add((FunctionOutputPort) p);
      }
    }
    addFxPfromAllocatedFE(FaPackage.Literals.FUNCTIONAL_EXCHANGE__SOURCE_FUNCTION_OUTPUT_PORT, result);
    return result;
  }

  @SuppressWarnings("unchecked")
  private <T> void addFxPfromAllocatedFE(EReference ref, Collection<T> result){
    for (ComponentExchange ce : getComponentExchanges()){
      for (FunctionalExchange allocated : ce.getAllocatedFunctionalExchanges()){
        FunctionPort fxp = (FunctionPort) allocated.eGet(ref);
        if (fxp != null && fxp.eContainer() instanceof AbstractFunction){
          AbstractFunction fun = (AbstractFunction) fxp.eContainer();
          if (fun.getAllocationBlocks().contains(port.eContainer())){
            result.add((T)fxp);
          }
        }
      }
    }
  }

  @Override
  public boolean addProvidedInterface(Interface iface) {
    return port.getProvidedInterfaces().add(iface);
  }

  @Override
  public boolean addRequiredInterface(Interface iface) {
    return port.getRequiredInterfaces().add(iface);
  }

  @Override
  public EObject getEObject() {
    return port;
  }

  @Override
  public String getText() {
    return EObjectLabelProviderHelper.getText(port);
  }

  @Override
  public Collection<ComponentExchange> getComponentExchanges() {
    return port.getComponentExchanges();
  }

}
