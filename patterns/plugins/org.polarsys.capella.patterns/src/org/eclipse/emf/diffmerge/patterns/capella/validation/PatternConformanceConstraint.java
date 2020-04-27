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

import static org.eclipse.emf.diffmerge.patterns.capella.validation.ValidationUtil.buildDiagnosticMessages;

import org.eclipse.core.runtime.IStatus;
import org.eclipse.emf.common.util.Diagnostic;
import org.eclipse.emf.diffmerge.patterns.ui.PatternsUIPlugin;
import org.eclipse.emf.diffmerge.patterns.ui.environment.IModelEnvironmentUI;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.validation.AbstractModelConstraint;
import org.eclipse.emf.validation.IValidationContext;
import org.polarsys.capella.common.data.modellingcore.ModelElement;


/**
 * The "pattern conformity" constraint which is registered by this plug-in.
 */
public class PatternConformanceConstraint extends AbstractModelConstraint {
  
  /**
   * @see AbstractModelConstraint#validate(IValidationContext)
   */
	@Override
	public IStatus validate(IValidationContext ctx_p) {
		IStatus result = ctx_p.createSuccessStatus();
		EObject eo = ctx_p.getTarget();
		if (eo instanceof ModelElement) {
			ModelElement target = (ModelElement)eo;
			// Check
			Diagnostic diag = Checker.getInstance().check(target);
			if (diag.getSeverity() != Diagnostic.OK) {
			  IModelEnvironmentUI me = PatternsUIPlugin.getDefault().getModelEnvironmentUI();
				String sTarget = me.getText(target);
				String suffix = buildSingleDiagnosticMessage(diag);
				result = ctx_p.createFailureStatus(new Object[] {sTarget, suffix});
			}
		}
		return result;
	}
  
  /**
   * Return a single String message describing the whole content of the
   * given Diagnostic
   */
  protected String buildSingleDiagnosticMessage(Diagnostic root_p) {
    String[] messages = buildDiagnosticMessages(root_p, false);
    StringBuilder builder = new StringBuilder();
    boolean first = true;
    for(String msg : messages) {
      if (first)
        first = false;
      else 
        builder.append(" - "); //$NON-NLS-1$
      builder.append(msg);
    }
    return builder.toString();
  }
  
}
