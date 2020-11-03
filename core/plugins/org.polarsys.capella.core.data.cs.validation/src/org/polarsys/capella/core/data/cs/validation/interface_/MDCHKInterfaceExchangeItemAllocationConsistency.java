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
package org.polarsys.capella.core.data.cs.validation.interface_;
/**
 * 
 */
import java.util.Collection;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;

import org.eclipse.core.runtime.IStatus;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.validation.IValidationContext;
import org.eclipse.emf.validation.model.ConstraintStatus;

import org.polarsys.capella.core.data.cs.Component;
import org.polarsys.capella.core.data.cs.Interface;
import org.polarsys.capella.core.data.fa.AbstractFunction;
import org.polarsys.capella.core.model.helpers.AbstractFunctionExt;
import org.polarsys.capella.core.model.helpers.ComponentExt;
import org.polarsys.capella.core.model.helpers.InterfaceExt;
import org.polarsys.capella.core.validation.rule.AbstractValidationRule;
import org.polarsys.capella.common.data.modellingcore.AbstractExchangeItem;

/**
 *
 */
public class MDCHKInterfaceExchangeItemAllocationConsistency extends AbstractValidationRule {

	private Set<IStatus> statuses ;
	  /**
	   * @see org.eclipse.emf.validation.AbstractModelConstraint#validate(org.eclipse.emf.validation.IValidationContext)
	   */
	  @Override
	  public IStatus validate(IValidationContext context) {
		statuses = new HashSet<IStatus>();
	    EObject eObj = context.getTarget(); 
	    	 if (eObj instanceof Interface) {
	          Interface interfaze = (Interface) eObj;
	           validateInterface(context, interfaze);
	           if (!statuses.isEmpty()) {
		             // There are conflicts Returns them as a multi-statuses status
		             return ConstraintStatus.createMultiStatus(context, statuses);
		         }
	        }
	    	return context.createSuccessStatus();
	  		}
	  
	  	/**
		 * 
		 * @param context
		 * @param interfaze
		 * @return
		 */
		private void validateInterface(IValidationContext context, Interface interfaze) {
			Set<Component> relatedComponent = (Set<Component>) InterfaceExt.getRelatedComponents(interfaze);
			Set<AbstractExchangeItem> relatedComponentsExchangeItems = new HashSet<AbstractExchangeItem>(0);
			  for (Iterator<Component> iterator = relatedComponent.iterator(); iterator.hasNext();) {
				Component currentComponent = (Component) iterator.next();			
				Set<AbstractFunction> allocatedFunctions = (Set<AbstractFunction>) ComponentExt.getAllocatedFunctions(currentComponent);
				  for (Iterator<AbstractFunction> iterator2 = allocatedFunctions.iterator(); iterator2.hasNext();) {
					AbstractFunction allocatedFunction = (AbstractFunction) iterator2.next();
					Set<AbstractExchangeItem> relatedComponentExchangeItems = (Set<AbstractExchangeItem>) AbstractFunctionExt.getAllExchangeItems(allocatedFunction);
					relatedComponentsExchangeItems.addAll(relatedComponentExchangeItems);
					
					Set<AbstractExchangeItem> interfaceExchangeItems = (Set<AbstractExchangeItem>) InterfaceExt.getAllExchangeItems(interfaze);
					Collection<Interface> interfaces = ComponentExt.getRelatedInterfaces(currentComponent);
					boolean isOK =  interfaces.contains(interfaze) && relatedComponentExchangeItems.containsAll(interfaceExchangeItems);
					if (!isOK) {
						String COMPONENT_PREFIX = "\"" + currentComponent.getName()  +"\" ( "+ currentComponent.eClass().getName()+ " ) ";
						String INTARFACE_PREFIX = "\"" + interfaze.getName()  +"\" ( "+ interfaze.eClass().getName()+ " ) ";
						statuses.add(createFailureStatus(context, new Object[] { INTARFACE_PREFIX, COMPONENT_PREFIX }));
						break;
						
					}
				}
			  }
		}
		
		
}
