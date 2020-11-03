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
package org.polarsys.capella.core.data.cs.validation.interface_;

import java.util.ArrayList;
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
public class AtMostOneTechnicalInterfaceBetweenTwoComponents extends AbstractValidationRule {
	
	@Override
	public IStatus validate(IValidationContext ctx) {	
      EObject eObj = ctx.getTarget();
      if (eObj instanceof Interface) {
    	  Interface interfaze = (Interface) eObj;    	  
    	  if (!interfaze.isStructural()) {
    		  List<Component> userComponents = interfaze.getUserComponents();
    		  List<Component> implementorComponents = interfaze.getImplementorComponents();
    		  // we restrict the check since other rules check that there are at least one implements link and one use link. 
    		  if (userComponents.size() == 1 && implementorComponents.size() == 1) {	
    		  	Component c1 = userComponents.get(0);
    		  	Component c2 = implementorComponents.get(0);
    		  	List<Interface> interfaces = new ArrayList<Interface>();
    		  	interfaces.addAll(c1.getUsedInterfaces());
    		  	interfaces.retainAll(c2.getImplementedInterfaces());
    		  	interfaces.remove(interfaze);
    		  	List<Interface> technicalInterfaces = new ArrayList<Interface>();
    		  	for (Interface inter : interfaces) {
							if (!inter.isStructural()) {
								technicalInterfaces.add(inter);
							}
						}
    		  	if (technicalInterfaces.size() > 0) {
    		  		technicalInterfaces.add(interfaze);
							StringBuilder b = new StringBuilder();
							for (int i = 0; i < technicalInterfaces.size(); i++) {
								b.append(CapellaElementExt.getCapellaExplorerLabel(technicalInterfaces.get(i)));
								if (i < technicalInterfaces.size() - 1) {
									b.append(", "); //$NON-NLS-1$
								}
							}
    		  		return ctx.createFailureStatus(
    		  				CapellaElementExt.getCapellaExplorerLabel(c1), 
    		  				CapellaElementExt.getCapellaExplorerLabel(c2), 
    		  				b.toString());    		  		
    		  	}

    		  }
    	  }
      }        
      return ctx.createSuccessStatus();
	}
}