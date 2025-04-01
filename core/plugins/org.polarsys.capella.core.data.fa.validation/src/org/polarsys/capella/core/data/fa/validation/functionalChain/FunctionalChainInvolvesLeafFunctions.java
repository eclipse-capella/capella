/*******************************************************************************
 * Copyright (c) 2014, 2024 Thales LAS France SAS.
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
package org.polarsys.capella.core.data.fa.validation.functionalChain;

import org.eclipse.core.runtime.IStatus;
import org.eclipse.emf.validation.IValidationContext;

import org.polarsys.capella.core.data.fa.AbstractFunction;
import org.polarsys.capella.core.data.fa.FunctionalChain;
import org.polarsys.capella.core.validation.rule.AbstractValidationRule;

/**
 * FunctionalChain should involve only leaf functions.
 */
public class FunctionalChainInvolvesLeafFunctions extends AbstractValidationRule {

	@Override
	public IStatus validate(IValidationContext ctx) {
		if (ctx.getTarget() instanceof FunctionalChain) {
			FunctionalChain fc = (FunctionalChain) ctx.getTarget();

			for (AbstractFunction func : fc.getInvolvedFunctions()) {
				if (func.getOwnedFunctions().size() > 0)
					return ctx.createFailureStatus(new Object[] { fc.getName(), func.getName() });
			}
		}

		return ctx.createSuccessStatus();
	}
