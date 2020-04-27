/*******************************************************************************
 * Copyright (c) 2006, 2016 THALES GLOBAL SERVICES.
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
package org.polarsys.capella.core.data.cs.validation.interface_;

import java.util.List;

import org.eclipse.core.runtime.IStatus;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.validation.IValidationContext;
import org.polarsys.capella.core.data.cs.Component;
import org.polarsys.capella.core.data.cs.Interface;
import org.polarsys.capella.core.model.helpers.CapellaElementExt;
import org.polarsys.capella.core.validation.rule.AbstractValidationRule;

/**
 * This rule checks that technical interfaces are not provided or required
 */
public class TechnicalInterfacesShouldNotBeImplementedByMoreThanOneComponent extends AbstractValidationRule {
	
	@Override
	public IStatus validate(IValidationContext ctx) {	
    EObject eObj = ctx.getTarget();
    if (eObj instanceof Interface) {
  	  Interface interfaze = (Interface) eObj;    	  
  	  if (!interfaze.isStructural()) {
  		  List<Component> implementorComponents = interfaze.getImplementorComponents();
  		  // we restrict the check since other rules check that there are at least one implements link and one use link. 
  		  if (implementorComponents.size() > 1) {	    		  	
					StringBuilder b = new StringBuilder();
					for (int i = 0; i < implementorComponents.size(); i++) {
						b.append(CapellaElementExt.getCapellaExplorerLabel(implementorComponents.get(i)));
						if (i < implementorComponents.size() - 1) {
							b.append(", "); //$NON-NLS-1$
						}
					}
		  		return ctx.createFailureStatus(
		  				CapellaElementExt.getCapellaExplorerLabel(interfaze),  
		  				b.toString());    		  		
		  	}
  	  }
    }        
    return ctx.createSuccessStatus();
	}
}