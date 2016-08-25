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
public class SystemEngineering_InterModelInconsistencyDetection extends AbstractValidationRule {
	
	/**
	 * @see org.eclipse.emf.validation.AbstractModelConstraint#validate(org.eclipse.emf.validation.IValidationContext)
	 */	
	@Override
	public IStatus validate(IValidationContext ctx) {
		List<InterModelInconsistency> inconsistencies = new InterModelInconsistencyDetector().getInterModelInconsistencies((SystemEngineering) ctx.getTarget());		 
		if (inconsistencies.size() > 0) {			
			return ctx.createFailureStatus(new Object[] {inconsistencies.size()});
		} else
			return ctx.createSuccessStatus();
	}
}
