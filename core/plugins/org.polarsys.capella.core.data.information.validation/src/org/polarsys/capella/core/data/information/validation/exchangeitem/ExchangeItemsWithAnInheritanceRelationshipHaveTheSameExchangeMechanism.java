/*******************************************************************************
 * Copyright (c) 2006, 2017 THALES GLOBAL SERVICES.
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
package org.polarsys.capella.core.data.information.validation.exchangeitem;

import java.util.List;

import org.eclipse.core.runtime.IStatus;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.validation.IValidationContext;
import org.polarsys.capella.core.data.capellacore.GeneralizableElement;
import org.polarsys.capella.core.data.information.ExchangeItem;
import org.polarsys.capella.core.model.helpers.CapellaElementExt;
import org.polarsys.capella.core.validation.rule.AbstractValidationRule;

/**
 * Model Validation shall check that Exchange Items with an inheritance relationship have the same Kind.
 */
public class ExchangeItemsWithAnInheritanceRelationshipHaveTheSameExchangeMechanism extends AbstractValidationRule {

	@Override
	public IStatus validate(IValidationContext ctx) {	
      EObject eObj = ctx.getTarget();
      if (eObj instanceof ExchangeItem) {
        ExchangeItem exchangeItem = (ExchangeItem) eObj;
        List<GeneralizableElement> superExchangeItems = exchangeItem.getSuper();
        for (GeneralizableElement elt : superExchangeItems) {
        	ExchangeItem superExchangeItem = (ExchangeItem) elt;
        	if (exchangeItem.getExchangeMechanism() != superExchangeItem.getExchangeMechanism()) {
        		return ctx.createFailureStatus(CapellaElementExt.getCapellaExplorerLabel(exchangeItem), CapellaElementExt.getCapellaExplorerLabel(superExchangeItem));
        	}
        }
	  }        
      return ctx.createSuccessStatus();
	}

}
