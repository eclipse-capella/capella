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
package org.polarsys.capella.core.data.cs.validation.physicalPort;

import org.eclipse.core.runtime.IStatus;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.validation.EMFEventType;
import org.eclipse.emf.validation.IValidationContext;

import org.polarsys.capella.core.data.cs.PhysicalPort;
import org.polarsys.capella.core.validation.rule.AbstractValidationRule;

public class PhysicalPortPhysicalLink extends AbstractValidationRule {

  @Override
  public IStatus validate(IValidationContext ctx_p) {
    EObject eObj = ctx_p.getTarget();
    EMFEventType eType = ctx_p.getEventType();

    if (eType == EMFEventType.NULL) {
      // check PhysicalPort type
      if (eObj instanceof PhysicalPort) {
        PhysicalPort port = (PhysicalPort) eObj;
        // if PhysicalPort is not linked to any PhysicalLink
        // return failure status
        if ((null == port.getInvolvedLinks()) || (port.getInvolvedLinks().size() == 0)) {
          return ctx_p.createFailureStatus(new Object[] { port.getName() });
        }
      }
    }
    return ctx_p.createSuccessStatus();
  }
}
