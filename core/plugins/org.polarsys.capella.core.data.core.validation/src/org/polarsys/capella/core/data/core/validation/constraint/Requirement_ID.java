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
package org.polarsys.capella.core.data.core.validation.constraint;

import java.util.Iterator;
import java.util.Set;

import org.eclipse.core.runtime.IStatus;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.validation.IValidationContext;

import org.polarsys.capella.common.helpers.EObjectExt;
import org.polarsys.capella.core.data.capellamodeller.SystemEngineering;
import org.polarsys.capella.core.data.requirement.Requirement;
import org.polarsys.capella.core.data.requirement.RequirementPackage;
import org.polarsys.capella.core.validation.rule.AbstractValidationRule;

public class Requirement_ID extends AbstractValidationRule {

	
	@Override
	public IStatus validate(IValidationContext ctx) 
	{
		// Get the target
		EObject eObj = ctx.getTarget();
		// If the Target is a SystemEngineering
		if(eObj instanceof SystemEngineering)
		{
			// Typing the target
			SystemEngineering system = (SystemEngineering)eObj;
			// Get all Requirements of the project
			Set<EObject> reqSet = EObjectExt.getAll(system, RequirementPackage.Literals.REQUIREMENT);
			// Initialize index
			int itIndex = 0;
			// Create the Requirement List Iterator
			Iterator<EObject> itReq = reqSet.iterator();
			// For each elements
			while (itReq.hasNext()) 
			{
				// Typing the element
				Requirement currentReq = (Requirement) itReq.next();
				// Get the Requirement Id of the current Requirement
				String currentId = currentReq.getRequirementId();
				// Increment the index
				itIndex++;
				// If the current id is not null
				if(null != currentId)
				{
					// Create an other iterator to compare
					Iterator<EObject> itConpareReq = reqSet.iterator();
					// Increment iterator with the index to avoid double comparisons (Perfo)
					for(int i=0;i<itIndex;i++)
					{
						itConpareReq.next();
					}
					// For each element of the rest of the list
					while (itConpareReq.hasNext()) 
					{
						// Typing the element
						Requirement req = (Requirement) itConpareReq.next();
						// Get the requirement Id of the requirement to compare
						String reqId = req.getRequirementId();
						// If the 2 id are equals
						if(null != reqId 
								&& reqId.contentEquals(currentId))
						{
							// Return error
							return ctx.createFailureStatus(new Object[] { currentId, currentReq, req });
						}
					}
				}
			}
		}
		// Return success
		return ctx.createSuccessStatus();
	}

}
