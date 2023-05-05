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

package org.polarsys.capella.core.semantic.queries.basic.queries;

import java.util.ArrayList;
import java.util.List;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EObject;
import org.polarsys.capella.common.data.modellingcore.AbstractTypedElement;
import org.polarsys.capella.common.helpers.query.IQuery;
import org.polarsys.capella.core.data.capellacommon.State;
import org.polarsys.capella.core.data.cs.Component;
import org.polarsys.capella.core.data.fa.AbstractFunction;
import org.polarsys.capella.core.data.fa.FunctionalExchange;
import org.polarsys.capella.core.data.helpers.fa.services.FunctionExt;
import org.polarsys.capella.core.data.information.AbstractEventOperation;
import org.polarsys.capella.core.data.information.ExchangeItemInstance;
import org.polarsys.capella.core.data.interaction.InteractionPackage;
import org.polarsys.capella.core.data.interaction.Scenario;
import org.polarsys.capella.core.data.oa.Role;
import org.polarsys.capella.core.model.handler.helpers.CrossReferencerHelper;

/**
 * Return referencing Scenario of Component, Role, AbstractFunction, ExchangeItemInstance and 
 * AbstractEventOperation
 */
public class CapellaElementReferencingScenario implements IQuery {

  public CapellaElementReferencingScenario() {
    // does nothing
  }

  /**
   * @see org.polarsys.capella.common.helpers.query.IQuery#compute(java.lang.Object)
   */
  public List<Object> compute(Object object) {
    List<Object> result = new ArrayList<Object>();
    // Component, because of the part
    if (object instanceof Component) {
      Component component = (Component) object;
      List<AbstractTypedElement> abstractTypedElements = component.getAbstractTypedElements();
      for (AbstractTypedElement abstractTypedElement : abstractTypedElements) {
    	  getReferencingScenarios(result, abstractTypedElement,InteractionPackage.Literals.INSTANCE_ROLE);
	  }
    }
    // Role, ExchangeItemInstance & AbstractFunction
    if (object instanceof Role || object instanceof AbstractFunction
    		|| object instanceof ExchangeItemInstance) {
    	 getReferencingScenarios(result, (EObject) object, InteractionPackage.Literals.INSTANCE_ROLE);
    	 getReferencingScenarios(result, (EObject) object,InteractionPackage.Literals.STATE_FRAGMENT);
    	 if(object instanceof AbstractFunction) {
    		 AbstractFunction f = (AbstractFunction) object;
    		 List<FunctionalExchange> fes = FunctionExt.getExchanges(f);
    		 for(FunctionalExchange fe : fes) {
    			 getReferencingScenarios(result, fe, InteractionPackage.Literals.EVENT_RECEIPT_OPERATION);
    			 getReferencingScenarios(result, fe, InteractionPackage.Literals.EVENT_SENT_OPERATION);
    		 }
    	 }
    }
    // AbstractEventOperation
    if (object instanceof AbstractEventOperation) {
    	 getReferencingScenarios(result, (EObject) object,InteractionPackage.Literals.EVENT_RECEIPT_OPERATION);
    }
    // State
    if (object instanceof State) {
    	getReferencingScenarios(result, (EObject) object,InteractionPackage.Literals.STATE_FRAGMENT);
    }
    
    
    return result;
  }

/**
 * Return referencing Scenario
 * @param result
 * @param abstractTypedElement
 */
private void getReferencingScenarios(List<Object> result,
		EObject eObject_p, EClass instanceToCompare) {
	List<EObject> referencingElements = CrossReferencerHelper.getReferencingElements(eObject_p);	
	  for (EObject eObject : referencingElements) {
		if (eObject.eClass().equals(instanceToCompare)) {
			EObject eContainer = eObject.eContainer();
			if (null != eContainer && eContainer instanceof Scenario) {
				result.add(eContainer);
			}
		}
	  }
}

}
