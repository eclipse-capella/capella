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
package org.polarsys.capella.core.data.interaction.validation.scenario;

import org.eclipse.core.runtime.IStatus;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.validation.EMFEventType;
import org.eclipse.emf.validation.IValidationContext;

import org.polarsys.capella.core.data.cs.CsPackage;
import org.polarsys.capella.core.data.ctx.CtxPackage;
import org.polarsys.capella.core.data.epbs.EpbsPackage;
import org.polarsys.capella.core.data.interaction.Scenario;
import org.polarsys.capella.core.data.interaction.ScenarioKind;
import org.polarsys.capella.core.data.la.LaPackage;
import org.polarsys.capella.core.data.oa.OaPackage;
import org.polarsys.capella.core.data.pa.PaPackage;
import org.polarsys.capella.core.validation.rule.AbstractValidationRule;

/**
 * check consistency of Scenario kind vs Scenario Level.
 */
public class MDCHK_Scenario_KindVSLevelConsistency extends AbstractValidationRule {
	
	@Override
	public IStatus validate(IValidationContext ctx) {
		EObject eObj = ctx.getTarget();
		EMFEventType eType = ctx.getEventType();

		if (eType == EMFEventType.NULL) {
			if (eObj instanceof Scenario) {
				Scenario sc = (Scenario) eObj;
				if (null != sc.eContainer()) {
				  if (!checkKindConsistency(sc)) {
  				    return ctx.createFailureStatus(new Object[] { sc.getName() });
				  }
				}
			}
		}
		return ctx.createSuccessStatus();
	}
	
	/** check rules description */
	private boolean checkKindConsistency(Scenario sc_p) {
	  boolean result = true;
	  
	  // Get the layer
	  EObject container = sc_p.eContainer();
	  boolean layerfound = false;
	  while (null != container) {
	    layerfound = CsPackage.Literals.BLOCK_ARCHITECTURE.isSuperTypeOf(container.eClass());
	    if (layerfound) break;
	    container = container.eContainer();
	  }
	  
	  // Check scenario kind
	  if (layerfound) {
	  
	    ScenarioKind kind = sc_p.getKind();
	  
	    // Unset Type
	    if (kind == ScenarioKind.UNSET ) {
	      return false;
	    }
	    
  	  if (
  	      container.eClass() == OaPackage.Literals.OPERATIONAL_ANALYSIS
  	  ) {
  	    
  	    result = kind == ScenarioKind.INTERACTION;
  	    
  	  } else if (
  	      container.eClass() == CtxPackage.Literals.SYSTEM_ANALYSIS ||
  	      container.eClass() == LaPackage.Literals.LOGICAL_ARCHITECTURE ||
  	      container.eClass() == PaPackage.Literals.PHYSICAL_ARCHITECTURE
  	  ) {
  	    result =
  	      kind == ScenarioKind.DATA_FLOW ||
  	      kind == ScenarioKind.INTERFACE ||
  	      kind == ScenarioKind.FUNCTIONAL
  	    ;
  	  } else if (
          container.eClass() == EpbsPackage.Literals.EPBS_ARCHITECTURE 
      ) {
  	    result = kind == ScenarioKind.INTERFACE;
      }
  	  
	  }
	  
	  return result;
	}
	
}


