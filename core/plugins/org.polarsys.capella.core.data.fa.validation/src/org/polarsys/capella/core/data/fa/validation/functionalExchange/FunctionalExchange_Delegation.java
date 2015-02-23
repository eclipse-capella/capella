/*******************************************************************************
 * Copyright (c) 2006, 2015 THALES GLOBAL SERVICES.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *  
 * Contributors:
 *    Thales - initial API and implementation
 *******************************************************************************/

package org.polarsys.capella.core.data.fa.validation.functionalExchange;

import java.util.List;

import org.eclipse.core.runtime.IStatus;
import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.validation.EMFEventType;
import org.eclipse.emf.validation.IValidationContext;
import org.polarsys.capella.common.data.activity.ActivityNode;
import org.polarsys.capella.common.data.modellingcore.AbstractExchangeItem;
import org.polarsys.capella.common.data.modellingcore.AbstractNamedElement;
import org.polarsys.capella.common.mdsofa.common.constant.ICommonConstants;
import org.polarsys.capella.core.data.fa.AbstractFunction;
import org.polarsys.capella.core.data.fa.FunctionInputPort;
import org.polarsys.capella.core.data.fa.FunctionOutputPort;
import org.polarsys.capella.core.data.fa.FunctionalExchange;
import org.polarsys.capella.core.data.information.ExchangeItem;
import org.polarsys.capella.core.model.helpers.FunctionalExchangeExt;
import org.polarsys.capella.core.validation.rule.AbstractValidationRule;

public class FunctionalExchange_Delegation extends AbstractValidationRule {
	/**
	 * @see org.eclipse.emf.validation.AbstractModelConstraint#validate(org.eclipse.emf.validation.IValidationContext)
	 */
	@Override
	public IStatus validate(IValidationContext ctx_p) {
		EObject eObj = ctx_p.getTarget();
		EMFEventType eType = ctx_p.getEventType();

		if (eType == EMFEventType.NULL) {
			if (eObj instanceof FunctionalExchange) {
				FunctionalExchange fe = (FunctionalExchange) eObj;
				AbstractFunction parentSource = FunctionalExchangeExt
						.getSourceFunction(fe);
				AbstractFunction parentTarget = FunctionalExchangeExt
						.getTargetFunction(fe);

				AbstractFunction commonAncestorFunction = FunctionalExchangeExt
						.getDefaultContainer(fe);
				EList<FunctionalExchange> lstFE = commonAncestorFunction
						.getOwnedFunctionalExchanges();
				boolean bDelegatedFE = false;
				if (parentSource.getOwnedFunctions().size() == 0 && parentTarget
						.getOwnedFunctions().size() == 0)
				{
					bDelegatedFE = true;
				}
				for (FunctionalExchange aFE : lstFE) {
					AbstractFunction aSource = FunctionalExchangeExt
							.getSourceFunction(aFE);
					AbstractFunction aTarget = FunctionalExchangeExt
							.getTargetFunction(aFE);
					if ((parentSource.getOwnedFunctions().contains(aSource) && parentTarget == aTarget)
							|| (parentSource == aSource && parentTarget
									.getOwnedFunctions().contains(aTarget))
							|| (parentSource.getOwnedFunctions().contains(
									aSource) && parentTarget
									.getOwnedFunctions().contains(aTarget)))
						bDelegatedFE = true;
				}
				if (!bDelegatedFE)
					return createFailureStatus(ctx_p, new Object[] { fe });
			}
		}
		return ctx_p.createSuccessStatus();
	}

}
