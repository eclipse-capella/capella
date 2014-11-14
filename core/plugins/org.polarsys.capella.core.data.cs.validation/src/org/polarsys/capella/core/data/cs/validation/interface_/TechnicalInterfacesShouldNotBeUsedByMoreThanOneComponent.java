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
public class TechnicalInterfacesShouldNotBeUsedByMoreThanOneComponent extends AbstractValidationRule {
	
	@Override
	public IStatus validate(IValidationContext ctx_p) {	
      EObject eObj = ctx_p.getTarget();
      if (eObj instanceof Interface) {
    	  Interface interfaze = (Interface) eObj;    	  
    	  if (!interfaze.isStructural()) {
    		  List<Component> userComponents = interfaze.getUserComponents();
    		  // we restrict the check since other rules check that there are at least one implements link and one use link. 
    		  if (userComponents.size() > 1) {	    		  	
						StringBuilder b = new StringBuilder();
						for (int i = 0; i < userComponents.size(); i++) {
							b.append(CapellaElementExt.getCapellaExplorerLabel(userComponents.get(i)));
							if (i < userComponents.size() - 1) {
								b.append(", "); //$NON-NLS-1$
							}
						}
  		  		return ctx_p.createFailureStatus(
  		  				CapellaElementExt.getCapellaExplorerLabel(interfaze),  
  		  				b.toString());    		  		
  		  	}
    	  }
      }        
      return ctx_p.createSuccessStatus();
	}
}