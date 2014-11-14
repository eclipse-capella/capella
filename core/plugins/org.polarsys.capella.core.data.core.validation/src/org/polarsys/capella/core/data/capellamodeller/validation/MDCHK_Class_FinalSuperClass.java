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
package org.polarsys.capella.core.data.capellamodeller.validation;

import java.util.ArrayList;

import org.eclipse.core.runtime.IStatus;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.validation.EMFEventType;
import org.eclipse.emf.validation.IValidationContext;
import org.eclipse.emf.validation.model.ConstraintStatus;
import org.polarsys.capella.common.data.modellingcore.FinalizableElement;
import org.polarsys.capella.core.data.capellacore.GeneralizableElement;
import org.polarsys.capella.core.validation.rule.AbstractValidationRule;

/**
 * This query validates that a super collection is not final */

public class MDCHK_Class_FinalSuperClass extends AbstractValidationRule {

  /**
   * @see org.eclipse.emf.validation.AbstractModelConstraint#validate(org.eclipse.emf.validation.IValidationContext)
   */
@Override
  public IStatus validate(IValidationContext ctx_p) {
    EObject eObj = ctx_p.getTarget();
    EMFEventType eType = ctx_p.getEventType();
    java.util.Collection<IStatus> statuses = new ArrayList<IStatus>();
		if (eType == EMFEventType.NULL) { 
			if (eObj instanceof GeneralizableElement && eObj instanceof FinalizableElement) {
				GeneralizableElement ge = (GeneralizableElement) eObj;
				for (GeneralizableElement ges : ge.getSuper()) {
					if ((ges instanceof FinalizableElement) && ((FinalizableElement) ges).isFinal()) {
						IStatus status = createFailureStatus(ctx_p, new Object[] { ge.getName(), ges.getName() });
						statuses.add(status);
					}
				}
			}
		}
		if (statuses.isEmpty()) {
			return ctx_p.createSuccessStatus();
		}
		return ConstraintStatus.createMultiStatus(ctx_p, statuses);
	}
}
