/*******************************************************************************
 * Copyright (c) 2016, 2024 Thales LAS France SAS.
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
package org.polarsys.capella.core.validation.rule;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.eclipse.core.runtime.IStatus;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.validation.IValidationContext;
import org.polarsys.capella.common.data.modellingcore.AbstractNamedElement;

/**
 * Functional Chains shall start from an End User and finish with an End User
 */
public abstract class AbstractNameUnicity extends AbstractValidationRule {

	@Override
	public IStatus validate(final IValidationContext ctx) {
		if (testMetaClass(ctx.getTarget())) {
			// Create the list of elements names
			final Map<String, List<AbstractNamedElement>> listOfNames = getOrCreateListOfNamesOfCurrentValidation(ctx);
			
			// Check this element
			final IStatus status = processValidation(ctx, listOfNames);

			// Report
			return status;
		}
		return ctx.createSuccessStatus();
	}

	abstract protected boolean testMetaClass(final EObject theTarget);

	private Map<String, List<AbstractNamedElement>> getOrCreateListOfNamesOfCurrentValidation(
			final IValidationContext ctx) {
		Map<String, List<AbstractNamedElement>> listOfNames = null;

		if (ctx.getCurrentConstraintData() == null) {
			listOfNames = new HashMap<String, List<AbstractNamedElement>>();
			ctx.putCurrentConstraintData(listOfNames);
		} else {
			if (ctx.getCurrentConstraintData() instanceof Map<?, ?>) {
				listOfNames = (Map<String, List<AbstractNamedElement>>) ctx.getCurrentConstraintData();
			}
		}

		return listOfNames;
	}

	private IStatus processValidation(final IValidationContext ctx,
			final Map<String, List<AbstractNamedElement>> listOfNames) {
		IStatus result = ctx.createSuccessStatus();

		if (ctx.getTarget() instanceof AbstractNamedElement) {
			final AbstractNamedElement targetAbstractNamedElement = (AbstractNamedElement) ctx.getTarget();
			final List<AbstractNamedElement> existingListOfAbstractNamedElement = listOfNames
					.get(targetAbstractNamedElement.getName());
			if (existingListOfAbstractNamedElement != null) {
				// the name of that targetNamedElement is already used

				// [optional] add it in the structure
				existingListOfAbstractNamedElement.add(targetAbstractNamedElement);

				result = ctx.createFailureStatus(new Object[] { targetAbstractNamedElement.getFullLabel(),
						existingListOfAbstractNamedElement.get(0).getFullLabel() });
			} else {
				// no name conflict

				// store the current targetNamedElement name
				final List<AbstractNamedElement> newListOfAbstractNamedElement = new ArrayList<AbstractNamedElement>();
				newListOfAbstractNamedElement.add(targetAbstractNamedElement);
				listOfNames.put(targetAbstractNamedElement.getName(), newListOfAbstractNamedElement);
			}
		}

		return result;
	}

}
