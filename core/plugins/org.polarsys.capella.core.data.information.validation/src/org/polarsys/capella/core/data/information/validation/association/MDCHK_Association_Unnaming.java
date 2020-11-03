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
package org.polarsys.capella.core.data.information.validation.association;

import org.eclipse.core.runtime.IStatus;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.validation.IValidationContext;

import org.polarsys.capella.common.mdsofa.common.constant.ICommonConstants;
import org.polarsys.capella.core.data.capellacore.NamedElement;
import org.polarsys.capella.core.validation.rule.AbstractValidationRule;

public class MDCHK_Association_Unnaming extends AbstractValidationRule {

	@Override
	public IStatus validate(IValidationContext ctx) {
	    // Get the Target
	    EObject eObj = ctx.getTarget();
        // Get the name of the element
        String currentElementName = ((NamedElement)eObj).getName();
        // If the name is empty or null
        if(currentElementName.equalsIgnoreCase(ICommonConstants.EMPTY_STRING) || currentElementName.equalsIgnoreCase("null")) //$NON-NLS-1$
        {
          // Failure
          return ctx.createFailureStatus(new Object[] { eObj.eClass().getName() });
        }
        return ctx.createSuccessStatus();
	}

}
