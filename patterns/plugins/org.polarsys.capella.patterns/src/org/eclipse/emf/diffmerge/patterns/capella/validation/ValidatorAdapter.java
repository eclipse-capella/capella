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
package org.eclipse.emf.diffmerge.patterns.capella.validation;

import java.util.Map;

import org.eclipse.emf.common.util.Diagnostic;
import org.eclipse.emf.common.util.DiagnosticChain;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EDataType;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EValidator;

import org.polarsys.capella.common.data.modellingcore.ModelElement;


/**
 * A validator for pattern instances which is registered by the
 * plug-in activator.
 */
public class ValidatorAdapter implements EValidator {
  
  /**
   * @see EValidator#validate(EObject, DiagnosticChain, Map)
   */
	public boolean validate(EObject eObject_p, DiagnosticChain diagnostics_p,
	    Map<Object, Object> context_p) {
		boolean result = true;
		if (eObject_p instanceof ModelElement) {
			ModelElement target = (ModelElement)eObject_p;
			// Check
			Diagnostic diag = Checker.getInstance().check(target);
			result = diag.getSeverity() == Diagnostic.OK;
			if (!result) diagnostics_p.add(diag);
		}
		return result;
	}
  
	/**
	 * @see EValidator#validate(EClass, EObject, DiagnosticChain, Map)
	 */
	public boolean validate(EClass eClass_p, EObject eObject_p,
	    DiagnosticChain diagnostics_p, Map<Object, Object> context_p) {
		return validate(eObject_p, diagnostics_p, context_p);
	}
  
	/**
	 * @see EValidator#validate(EDataType, Object, DiagnosticChain, Map)
	 */
	public boolean validate(EDataType eDataType_p, Object value_p,
	    DiagnosticChain diagnostics_p, Map<Object, Object> context_p) {
		return true;
	}
	
}
