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
package org.polarsys.capella.core.data.capellamodeller.validation;

import java.util.List;

import org.eclipse.core.runtime.IStatus;
import org.eclipse.emf.validation.IValidationContext;
import org.polarsys.capella.core.data.capellamodeller.SystemEngineering;
import org.polarsys.capella.core.model.helpers.intermodelInconsistencyDetection.InterModelInconsistency;
import org.polarsys.capella.core.model.helpers.intermodelInconsistencyDetection.InterModelInconsistencyDetector;
import org.polarsys.capella.core.validation.rule.AbstractValidationRule;

/** 
 * @author Erwan Brottier
 */
// I_34 (Batch)
public class InterModelInconsistencyValidationRule extends AbstractValidationRule {
	
	/**
	 * @see org.eclipse.emf.validation.AbstractModelConstraint#validate(org.eclipse.emf.validation.IValidationContext)
	 */	
	@Override
	public IStatus validate(IValidationContext ctx) {
		List<InterModelInconsistency> inconsistencies = new InterModelInconsistencyDetector().getInterModelInconsistencies((SystemEngineering) ctx.getTarget());		 
		if (inconsistencies.isEmpty()) {			
		    return ctx.createSuccessStatus();
		}
		return ctx.createFailureStatus(inconsistencies.toArray());
	}
}
