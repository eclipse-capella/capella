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
public class TechnicalInterfacesAreNotProvidedOrRequired extends AbstractValidationRule {

	@Override
	public IStatus validate(IValidationContext ctx) {	
      EObject eObj = ctx.getTarget();
      if (eObj instanceof Interface) {
    	  Interface interfaze = (Interface) eObj;
    	  if (!interfaze.isStructural()) {
    		  boolean errorsFound = false;
    		  StringBuilder b = new StringBuilder();
    		  List<Component> components = interfaze.getProvidingComponents();
    		  if (components.size() > 0) {
    			  if (components.size() > 0) {
    				  b.append("providing components : ["); //$NON-NLS-1$
    				  errorsFound = true;
    				  b.append(getComponentNamesList(components)+"]"); //$NON-NLS-1$
    			  }
    		  }	      
    		  components = interfaze.getRequiringComponents();
    		  if (components.size() > 0) {
    			  if (components.size() > 0) {
    				  if (errorsFound) {
    					  b.append(", "); //$NON-NLS-1$
    				  }
    				  b.append("requiring components : ["); //$NON-NLS-1$
    				  errorsFound = true;
    				  b.append(getComponentNamesList(components)+"]"); //$NON-NLS-1$
    			  }
    		  }
    		  if (errorsFound) {
    			  return ctx.createFailureStatus(CapellaElementExt.getCapellaExplorerLabel(interfaze), b.toString());    		  
    		  }    		  
    	  }
      }        
      return ctx.createSuccessStatus();
	}

	private String getComponentNamesList(List<Component> components) {
		StringBuilder b = new StringBuilder();
		for (int i = 0; i < components.size(); i++) {
			Component component = components.get(i);
			b.append(CapellaElementExt.getCapellaExplorerLabel(component));
			if (i < components.size() - 1) {
				b.append(", "); //$NON-NLS-1$
			}
		}
		return b.toString();
	}
}