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
package org.polarsys.capella.core.data.information.validation.exchangeitem;

import java.util.List;

import org.eclipse.core.runtime.IStatus;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.validation.IValidationContext;
import org.polarsys.capella.common.data.modellingcore.AbstractType;
import org.polarsys.capella.core.data.capellacore.GeneralizableElement;
import org.polarsys.capella.core.data.information.ElementKind;
import org.polarsys.capella.core.data.information.ExchangeItem;
import org.polarsys.capella.core.data.information.ExchangeItemElement;
import org.polarsys.capella.core.model.helpers.CapellaElementExt;
import org.polarsys.capella.core.validation.rule.AbstractValidationRule;

/**
 * Model Validation shall check that an Exchange Item (EI 2) with Exchange Item Element (EIE 2) of Type T2, which inherits from an Exchange Item (EI 1) with an Exchange Item Element (EIE 1) of Type T1,verifies that T2 inherits from T1.
 */
public class InheritanceConstraintForExchangeItemElement extends AbstractValidationRule {

	@Override
	public IStatus validate(IValidationContext ctx) {	
		// get information
		ExchangeItem ei2 = null;  
		ExchangeItemElement eie2 = null;
		AbstractType t2 = null;
		ExchangeItem ei1 = null;
		ExchangeItemElement eie1 = null;
		AbstractType t1 = null;
		EObject eObj = ctx.getTarget();
    if (eObj instanceof ExchangeItem) {
    	ei2 = (ExchangeItem) eObj;
    	List<ExchangeItemElement> elements = ei2.getOwnedElements();
    	if (elements.size() == 1) {// Exchange Item must contain at most one EIE of kind "TYPE", not checked by this rule
    		eie2 = elements.get(0);
    		if (eie2 != null) {
    			t2 = eie2.getAbstractType();
    		}
    	}
    	List<GeneralizableElement> superItems = ei2.getSuper();
			if (superItems.size() == 1) {// Exchange Item must has at least one super Exchange Item by construction 
				ei1 = (ExchangeItem) superItems.get(0);
				elements = ei1.getOwnedElements();
				if (elements.size() == 1) {// Exchange Item must contain at most one EIE of kind "TYPE", not checked by this rule
      		eie1 = elements.get(0);
      		t1 = eie1.getAbstractType();
				}
			}
    }
    // check information
    if (ei1 != null) {
    	if (t2 != null && !(t2 instanceof GeneralizableElement) &&  eie2.getKind() == ElementKind.TYPE) {
    		return ctx.createFailureStatus(
    				CapellaElementExt.getCapellaExplorerLabel(ei2), 
    				"Exchange Item Element of "+CapellaElementExt.getCapellaExplorerLabel(ei2)+" must be a Generalizable Element"); //$NON-NLS-1$ //$NON-NLS-2$    		
    	} else if (t2 != null &&  eie2.getKind() == ElementKind.TYPE && t1 != null &&  eie1.getKind() == ElementKind.TYPE) {
    		GeneralizableElement t2Gen = (GeneralizableElement) t2;
    		if (!t1.equals(t2Gen) && !isInSuperType(t1, t2Gen)) {
    			return ctx.createFailureStatus(
    					CapellaElementExt.getCapellaExplorerLabel(ei2),
    					"Type of Exchange Item Element of "+CapellaElementExt.getCapellaExplorerLabel(ei2)+" must be a subtype of "+CapellaElementExt.getCapellaExplorerLabel(t1)); //$NON-NLS-1$ //$NON-NLS-2$
    		}
    	} else if (t2 == null && t1 != null && eie2 != null) {
  			return ctx.createFailureStatus(
  					CapellaElementExt.getCapellaExplorerLabel(ei2),
  					"Type of Exchange Item Element of "+CapellaElementExt.getCapellaExplorerLabel(ei2)+" is not defined (must be a subtype of "+CapellaElementExt.getCapellaExplorerLabel(t1)+")"); //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$    		
    	} else if (t1 == null && t2 != null && eie1 != null) {
  			return ctx.createFailureStatus(
  					CapellaElementExt.getCapellaExplorerLabel(ei2),
  					"Type of Exchange Item Element of "+CapellaElementExt.getCapellaExplorerLabel(ei1)+" must be defined"); //$NON-NLS-1$ //$NON-NLS-2$    		
    	}
		}
    return ctx.createSuccessStatus();
	}
	
	private boolean isInSuperType(AbstractType element, GeneralizableElement typeLeaf) {
		List<GeneralizableElement> genElts = typeLeaf.getSuper();
		boolean res = genElts.contains(element);
		for (int i = 0; !res && i < genElts.size(); i++) {
			res =  isInSuperType(element, genElts.get(i));
		}
		return res;
	}
}
