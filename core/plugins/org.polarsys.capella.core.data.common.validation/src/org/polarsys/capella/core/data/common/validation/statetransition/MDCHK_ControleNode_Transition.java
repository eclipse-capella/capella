/*******************************************************************************
 * Copyright (c) 2006, 2016 THALES GLOBAL SERVICES.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *  
 * Contributors:
 *    Thales - initial API and implementation
 *******************************************************************************/
package org.polarsys.capella.core.data.common.validation.statetransition;

import org.eclipse.core.runtime.IStatus;
import org.eclipse.emf.validation.IValidationContext;

import org.polarsys.capella.core.data.capellacommon.ChoicePseudoState;
import org.polarsys.capella.core.data.capellacommon.ForkPseudoState;
import org.polarsys.capella.core.data.capellacommon.Pseudostate;
import org.polarsys.capella.core.validation.rule.AbstractValidationRule;

public class MDCHK_ControleNode_Transition extends AbstractValidationRule {


	@Override
	public IStatus validate(IValidationContext ctx) {
		Pseudostate cn = (Pseudostate) ctx.getTarget();
		if (cn instanceof ChoicePseudoState || cn instanceof ForkPseudoState) {
			if (cn.getIncoming().size() != 1) {
				return createFailureStatus(ctx, new Object[] { cn.getName(), cn.eClass().getName() });
			}
		}
		return ctx.createSuccessStatus();
	}

}
