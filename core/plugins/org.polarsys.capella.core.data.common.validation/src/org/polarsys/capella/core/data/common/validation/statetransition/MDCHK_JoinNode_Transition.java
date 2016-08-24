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

import org.polarsys.capella.core.data.capellacommon.JoinPseudoState;
import org.polarsys.capella.core.validation.rule.AbstractValidationRule;

public class MDCHK_JoinNode_Transition extends AbstractValidationRule {


	@Override
	public IStatus validate(IValidationContext ctx) {
		JoinPseudoState cn = (JoinPseudoState) ctx.getTarget();
			if (cn.getOutgoing().size() != 1) {
				return createFailureStatus(ctx, new Object[] { cn.getName(), cn.eClass().getName() });
			}
		return ctx.createSuccessStatus();
	}

}
