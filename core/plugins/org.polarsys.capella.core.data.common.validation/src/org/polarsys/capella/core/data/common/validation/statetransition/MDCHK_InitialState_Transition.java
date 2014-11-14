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
package org.polarsys.capella.core.data.common.validation.statetransition;

import org.eclipse.core.runtime.IStatus;
import org.eclipse.emf.validation.IValidationContext;

import org.polarsys.capella.core.data.capellacommon.InitialPseudoState;
import org.polarsys.capella.core.validation.rule.AbstractValidationRule;

public class MDCHK_InitialState_Transition extends AbstractValidationRule {

	@Override
	public IStatus validate(IValidationContext ctx_p) {
		InitialPseudoState sm = (InitialPseudoState) ctx_p.getTarget();

		if (sm.getOutgoing().size() > 1) {
			return createFailureStatus(ctx_p, new Object[] { sm.getName(), sm.eClass().getName()  });
		}
		return ctx_p.createSuccessStatus();
	}

}
