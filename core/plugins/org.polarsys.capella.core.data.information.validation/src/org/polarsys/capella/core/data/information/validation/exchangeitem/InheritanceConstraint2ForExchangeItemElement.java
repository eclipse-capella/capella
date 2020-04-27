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
import org.polarsys.capella.core.data.capellacore.GeneralizableElement;
import org.polarsys.capella.core.data.information.ElementKind;
import org.polarsys.capella.core.data.information.ExchangeItem;
import org.polarsys.capella.core.data.information.ExchangeItemElement;
import org.polarsys.capella.core.model.helpers.CapellaElementExt;
import org.polarsys.capella.core.validation.rule.AbstractValidationRule;

/**
 * Model Validation shall check that an Exchange Item (EI 2) with Exchange Item Element (EIE 2) of Type T2, which inherits from an Exchange Item (EI 1) with an Exchange Item Element (EIE 1) of Type T1,verifies that T2 inherits from T1.
 */
public class InheritanceConstraint2ForExchangeItemElement extends AbstractValidationRule {

	@Override
	public IStatus validate(IValidationContext ctx) {
		// get information
		ExchangeItem ei2 = null;
		ExchangeItemElement eie2 = null;
		ExchangeItem ei1 = null;
		ExchangeItemElement eie1 = null;
		EObject eObj = ctx.getTarget();
		if (eObj instanceof ExchangeItem) {
			ei2 = (ExchangeItem) eObj;
			List<ExchangeItemElement> elements = ei2.getOwnedElements();
			if (elements.size() == 1) {// Exchange Item must contain at most one EIE of kind "TYPE", not checked by
										// this rule
				eie2 = elements.get(0);
			}
			List<GeneralizableElement> superItems = ei2.getSuper();
			if (superItems.size() == 1) {// Exchange Item must has at least one super Exchange Item by construction
				ei1 = (ExchangeItem) superItems.get(0);
				elements = ei1.getOwnedElements();
				if (elements.size() == 1) {// Exchange Item must contain at most one EIE of kind "TYPE", not checked by
											// this rule
					eie1 = elements.get(0);
				}
			}
		}
		// check information
		if (ei1 != null) {
			if (eie1 != null && eie1.getKind() == ElementKind.TYPE && eie2 == null) {
				return ctx.createFailureStatus(CapellaElementExt.getCapellaExplorerLabel(ei2),
						"Exchange Item " + CapellaElementExt.getCapellaExplorerLabel(ei2) //$NON-NLS-1$
								+ " must contain an Exchange Item Element since it inherits from " //$NON-NLS-1$
								+ CapellaElementExt.getCapellaExplorerLabel(ei1)
								+ " which has an Exchange Item Element"); //$NON-NLS-1$
			} else if (eie2 != null && eie2.getKind() == ElementKind.TYPE && eie1 == null) {
				return ctx.createFailureStatus(CapellaElementExt.getCapellaExplorerLabel(ei2),
						"Exchange Item " + CapellaElementExt.getCapellaExplorerLabel(ei1) //$NON-NLS-1$
								+ " must contain an Exchange Item Element since " //$NON-NLS-1$
								+ CapellaElementExt.getCapellaExplorerLabel(ei2)
								+ " has an Exchange Item Element and inherits from it"); //$NON-NLS-1$
			}
		}
		return ctx.createSuccessStatus();
	}
}
