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
package org.polarsys.capella.core.data.cs.validation.interface_;

import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

import org.eclipse.core.runtime.IStatus;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.validation.IValidationContext;
import org.eclipse.emf.validation.model.ConstraintStatus;

import org.polarsys.capella.core.data.cs.Component;
import org.polarsys.capella.core.data.cs.Interface;
import org.polarsys.capella.core.model.helpers.ComponentExt;
import org.polarsys.capella.core.model.helpers.InterfaceExt;
import org.polarsys.capella.core.validation.rule.AbstractValidationRule;
import org.polarsys.capella.common.data.modellingcore.AbstractExchangeItem;

/**
 * 
 */
public class MDCHKComponentInterfaceFunctionPortExchangeItemAllocationConsistency extends AbstractValidationRule {

  /**
   * @see org.eclipse.emf.validation.AbstractModelConstraint#validate(org.eclipse.emf.validation.IValidationContext)
   */
	
  private Set<IStatus> statuses ;
  @Override
  public IStatus validate(IValidationContext context) {
    EObject eObj = context.getTarget(); 
    	if (eObj instanceof Component) {
    	statuses = new HashSet<IStatus>();
    	Component component = (Component) eObj ;  
    	validateComponent(context, component);
    	if (!statuses.isEmpty()) {
            // There are conflicts returns them as a multi-statuses status
            return ConstraintStatus.createMultiStatus(context, statuses);
        }
    	}
    	
    	return context.createSuccessStatus();
  		}

  		
		  
		/**
		 *   
		 * @param context
		 * @param component
		 * @return
		 */
		private void validateComponent(IValidationContext context, Component component) {
			if (!component.getAllocatedFunctions().isEmpty()) { 
				Set<AbstractExchangeItem> componentFunctionExchangeItems = ComponentExt.getAllocatedFunctionExchangeItems(component);
				Set<AbstractExchangeItem> exchangeItemsForInterfaces = getComponentInterfaceExchangeItems(component );
				boolean isOkForComponent = exchangeItemsForInterfaces.containsAll(componentFunctionExchangeItems);
				String PREFIXE = "\"" + component.getName()  +"\" ( "+ component.eClass().getName()+ " ) ";

				if (!isOkForComponent) {
					//(Component) has exchange items allocated to functions ports of its allocated functions not allocated to one of its Interfaces 
					this.statuses.add(createFailureStatus(context, new Object[] { PREFIXE + "has exchange items allocated to functions ports of its allocated functions not allocated to one of its Interfaces" }));

				}
				
				boolean isOkForRelatedInterface = componentFunctionExchangeItems.containsAll(exchangeItemsForInterfaces);
				if (!isOkForRelatedInterface) {
					this.statuses.add(createFailureStatus(context, new Object[] {PREFIXE+"has exchange items allocated by its Interfaces not allocated to one of functions ports of its allocated functions" }));
				}

			}
		}

	 
	  
	  /**
	   * 
	   * @param component
	 * @param componentFunctionExchangeItems 
	   * @return all exchange items from the used/implemented/provided/required interface of the given component
	   */  
	  private Set<AbstractExchangeItem> getComponentInterfaceExchangeItems(Component component) {
		  
		  Set<AbstractExchangeItem> exchangeItemsForCurrentInterfaces = new HashSet<AbstractExchangeItem>(0);
		  
		List<Interface> relatedInterfaces = (List<Interface>) ComponentExt.getRelatedInterfaces(component);
		for (Iterator<Interface> iterator = relatedInterfaces.iterator(); iterator.hasNext();) {
			Interface interfasse = iterator.next();
			exchangeItemsForCurrentInterfaces.addAll((Set<AbstractExchangeItem>) InterfaceExt.getAllExchangeItems(interfasse));
		}
		return exchangeItemsForCurrentInterfaces;
	  }
}
