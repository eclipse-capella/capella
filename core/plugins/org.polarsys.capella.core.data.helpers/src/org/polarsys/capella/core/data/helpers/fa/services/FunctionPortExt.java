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

package org.polarsys.capella.core.data.helpers.fa.services;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.eclipse.emf.common.util.EList;

import org.polarsys.capella.core.data.cs.Component;
import org.polarsys.capella.core.data.fa.AbstractFunction;
import org.polarsys.capella.core.data.fa.ComponentFunctionalAllocation;
import org.polarsys.capella.core.data.fa.FunctionInputPort;
import org.polarsys.capella.core.data.fa.FunctionOutputPort;
import org.polarsys.capella.core.data.fa.FunctionPort;
import org.polarsys.capella.core.data.information.ExchangeItem;
import org.polarsys.capella.core.data.information.Port;
import org.polarsys.capella.core.data.information.PortRealization;
import org.polarsys.capella.common.data.modellingcore.AbstractTrace;

/**
 * Helper for function ports.
 */
public class FunctionPortExt {

  /**
   * @param fp
   * @return
   */
  public static List<Port> getRealizedPorts(FunctionPort fp) {
    List<Port> result = new ArrayList<>();
    for (AbstractTrace trace : fp.getOutgoingTraces()) {
      if (trace instanceof PortRealization) {
        Port realizedPort = ((PortRealization) trace).getRealizedPort();
        if(realizedPort != null) {
          result.add(realizedPort);          
        }
      }
    }
    return result;
  }

  /**
   * @param fp
   * @return
   */
  public static List<Port> getRealizingPorts(FunctionPort fp) {
    List<Port> result = new ArrayList<>();
    for (AbstractTrace trace : fp.getIncomingTraces()) {
      if (trace instanceof PortRealization) {
        Port realizingPort = ((PortRealization) trace).getRealizingPort();
        if(realizingPort != null) {          
          result.add(realizingPort);
        }
      }
    }
    return result;
  }
  
  
  /**
   * @param fp
   * @return
   */
  public static Set<ExchangeItem> getAllIncomingExchangeItems(FunctionPort fp) {
	  
	  Set<ExchangeItem> exchangesItems = new HashSet<>();
	  
	  if (fp instanceof FunctionInputPort) {
		FunctionInputPort functionInputPort = (FunctionInputPort) fp;
		exchangesItems.addAll(functionInputPort.getIncomingExchangeItems()) ;
	  }else if (fp instanceof FunctionOutputPort) {
		FunctionOutputPort functionOutputPort = (FunctionOutputPort) fp;
		exchangesItems.addAll(functionOutputPort.getOutgoingExchangeItems()) ;
	  }
	  
	  return exchangesItems ;
   
  }
  
  
  /**
   * @param fp
   * @return
   */
  public static Set<Component> getAllProvidedRealizedRequiredInterfaces(FunctionPort fp) {
	  Set<Component> exchangesItems = new HashSet<>();
	  AbstractFunction abstractFunction = (AbstractFunction) fp.eContainer();
	  EList<ComponentFunctionalAllocation> componentFunctionalAllocation = abstractFunction.getComponentFunctionalAllocations() ;
	  for (ComponentFunctionalAllocation componentFunctionalAllocation2 : componentFunctionalAllocation) {
		  Component containerComponent = (Component) componentFunctionalAllocation2.getSourceElement();
		  if(containerComponent != null) {
		    exchangesItems.add(containerComponent) ;		    
		  }
	  }
	 
	  return exchangesItems ;
   
  }
}
