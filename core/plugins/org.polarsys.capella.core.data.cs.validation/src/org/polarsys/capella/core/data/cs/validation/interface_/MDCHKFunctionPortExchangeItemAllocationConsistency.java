/*******************************************************************************
 * Copyright (c) 2006, 2016 THALES GLOBAL SERVICES.
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
package org.polarsys.capella.core.data.cs.validation.interface_;

import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

import org.eclipse.core.runtime.IStatus;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.validation.IValidationContext;

import org.polarsys.capella.core.data.cs.Component;
import org.polarsys.capella.core.data.cs.Interface;
import org.polarsys.capella.core.data.fa.FunctionPort;
import org.polarsys.capella.core.data.helpers.fa.services.FunctionPortExt;
import org.polarsys.capella.core.data.information.ExchangeItem;
import org.polarsys.capella.core.model.helpers.ComponentExt;
import org.polarsys.capella.core.model.helpers.InterfaceExt;
import org.polarsys.capella.core.validation.rule.AbstractValidationRule;
import org.polarsys.capella.common.data.modellingcore.AbstractExchangeItem;

/**
 *
 */
public class MDCHKFunctionPortExchangeItemAllocationConsistency extends AbstractValidationRule {

	  /**
	   * @see org.eclipse.emf.validation.AbstractModelConstraint#validate(org.eclipse.emf.validation.IValidationContext)
	   */
	  @Override
	  public IStatus validate(IValidationContext context) {
	    EObject eObj = context.getTarget(); 
	    	 if (eObj instanceof FunctionPort) {
	        	FunctionPort functionPort = (FunctionPort)eObj ;
	        	return validateFunctionPort(context, functionPort);
	        }
	    	
	    	return context.createSuccessStatus();
	  		}
	  
		  
		  
		  /**
			 * 	
			 * @param context
			 * @param functionPort
			 * @return
			 */
			  private IStatus validateFunctionPort(IValidationContext context, FunctionPort functionPort) {
				  Set<ExchangeItem> functionPortExchangeItems = FunctionPortExt.getAllIncomingExchangeItems(functionPort);
				  Set<Component> components = FunctionPortExt.getAllProvidedRealizedRequiredInterfaces(functionPort);
				  Set<AbstractExchangeItem> exchangeItems = getAllExchangesInterfaceItems(components);
				
				  boolean isOk = !functionPortExchangeItems.isEmpty() && exchangeItems.isEmpty() ? false : exchangeItems.containsAll(functionPortExchangeItems);
				if (!isOk && !components.isEmpty()) {
					String FUNCTION_PREFIXE = "\"" + functionPort.getName()  +"\" ( "+ functionPort.eClass().getName()+ " ) ";
 				    Component component = (Component)components.toArray()[0];
					String COMPONENT_PREFIXE = "\"" + component.getName()  +"\" ( "+ component.eClass().getName()+ " ) ";
					
					return createFailureStatus(context, new Object[] { FUNCTION_PREFIXE, COMPONENT_PREFIXE});
					 
				  }
				 
				return context.createSuccessStatus();
			}


		/**
		 * @param components
		 */
		private Set<AbstractExchangeItem> getAllExchangesInterfaceItems(Set<Component> components) {
			Set<AbstractExchangeItem> interfacesExchangeItems = new HashSet<AbstractExchangeItem>();
			for (Iterator iterator1 = components.iterator(); iterator1.hasNext();) {
				Component containerComponent = (Component) iterator1.next();
				
				List<Interface> interfaces = (List<Interface>) ComponentExt.getRelatedInterfaces(containerComponent);
				for (Iterator iterator = interfaces.iterator(); iterator.hasNext();) {
					Interface interfazz = (Interface) iterator.next();
					Set<AbstractExchangeItem> interfaceExchangeItems = (Set<AbstractExchangeItem>) InterfaceExt.getAllExchangeItems(interfazz) ;
					interfacesExchangeItems.addAll(interfaceExchangeItems);
				}
				
			}
			
			return interfacesExchangeItems ;
			
		}

	
}
