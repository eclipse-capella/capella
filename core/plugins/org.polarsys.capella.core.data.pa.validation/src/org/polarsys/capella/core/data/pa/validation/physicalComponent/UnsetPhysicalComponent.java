/*******************************************************************************
 * Copyright (c) 2017 THALES GLOBAL SERVICES.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *  
 * Contributors:
 *    Thales - initial API and implementation
 *******************************************************************************/
package org.polarsys.capella.core.data.pa.validation.physicalComponent;

import org.eclipse.core.runtime.IStatus;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.validation.EMFEventType;
import org.eclipse.emf.validation.IValidationContext;
import org.polarsys.capella.core.data.pa.PhysicalArchitecture;
import org.polarsys.capella.core.data.pa.PhysicalComponent;
import org.polarsys.capella.core.data.pa.PhysicalComponentNature;
import org.polarsys.capella.core.model.helpers.CapellaElementExt;
import org.polarsys.capella.core.model.helpers.SystemEngineeringExt;
import org.polarsys.capella.core.validation.rule.AbstractValidationRule;

/**
 * A Physical Component can't be of nature UNSET.
 */
public class UnsetPhysicalComponent extends AbstractValidationRule {
  /**
   * @see org.eclipse.emf.validation.AbstractModelConstraint#validate(org.eclipse.emf.validation.IValidationContext)
   */
  @Override
  public IStatus validate(IValidationContext ctx) {
    EObject eObj = ctx.getTarget();
    EMFEventType eType = ctx.getEventType();

    if (eType == EMFEventType.NULL) {
      if (eObj instanceof PhysicalComponent) {
        PhysicalComponent currentElement = (PhysicalComponent) eObj;
        PhysicalArchitecture architecture = SystemEngineeringExt.getPhysicalArchitecture(currentElement);
        if (currentElement != architecture.getSystem()) {
        	if (currentElement.getNature() == PhysicalComponentNature.UNSET) {
            	return ctx.createFailureStatus(CapellaElementExt.getValidationRuleMessagePrefix(currentElement)
                        + "must not be of nature UNSET."); //$NON-NLS-1$
            }
        }
      }
    }
    return ctx.createSuccessStatus();
  }
}
