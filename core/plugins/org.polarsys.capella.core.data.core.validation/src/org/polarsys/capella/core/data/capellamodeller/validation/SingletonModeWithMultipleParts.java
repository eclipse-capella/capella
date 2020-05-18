/*******************************************************************************
 * Copyright (c) 2006, 2014 THALES GLOBAL SERVICES.
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

import org.eclipse.core.runtime.IStatus;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.validation.AbstractModelConstraint;
import org.eclipse.emf.validation.IValidationContext;
import org.polarsys.capella.core.data.cs.Component;
import org.polarsys.capella.core.model.handler.helpers.CapellaProjectHelper;
import org.polarsys.capella.core.model.handler.helpers.CapellaProjectHelper.TriStateBoolean;

public class SingletonModeWithMultipleParts extends AbstractModelConstraint {

	public SingletonModeWithMultipleParts() {
		// TODO Auto-generated constructor stub
	}

	@Override
	public IStatus validate(IValidationContext ctx) {
		EObject eObj = ctx.getTarget();

		if (eObj instanceof Component) {
			Component current= (Component)eObj;
			if(TriStateBoolean.True.equals(CapellaProjectHelper.isSingletonComponentsDriven(current)) && current.getAbstractTypedElements()!=null && current.getAbstractTypedElements().size()>1){
				return ctx.createFailureStatus(new Object[] { current.getName(), current.eClass().getName().replaceAll("([a-z])([A-Z])", "$1 $2")});
			}

		}
		return null;
	}

}
