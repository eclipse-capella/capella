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
package org.polarsys.capella.core.data.cs.validation.physicalPort;

import org.eclipse.core.runtime.IStatus;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.validation.EMFEventType;
import org.eclipse.emf.validation.IValidationContext;
import org.polarsys.capella.core.data.cs.Component;
import org.polarsys.capella.core.data.cs.PhysicalPort;
import org.polarsys.capella.core.data.pa.PhysicalComponent;
import org.polarsys.capella.core.model.helpers.CapellaElementExt;
import org.polarsys.capella.core.model.helpers.PhysicalComponentExt;
import org.polarsys.capella.core.model.helpers.PortExt;
import org.polarsys.capella.core.validation.rule.AbstractValidationRule;

/**
 * PhysicalPort can't be contained in BehaviourPC
 */
public class PhysicalPortOnBehaviourPC extends AbstractValidationRule {

  @Override
  public IStatus validate(IValidationContext ctx) {
    EObject eObj = ctx.getTarget();
    EMFEventType eType = ctx.getEventType();

    if (eType == EMFEventType.NULL && eObj instanceof PhysicalPort) {
      PhysicalPort currentElement = (PhysicalPort) eObj;
      Component relatedComponent = PortExt.getRelatedComponent(currentElement);
      if (relatedComponent instanceof PhysicalComponent) {
        PhysicalComponent physicalComponent = (PhysicalComponent) relatedComponent;

        if (PhysicalComponentExt.isBehaviour(physicalComponent)) {

          return ctx.createFailureStatus(
              CapellaElementExt.getValidationRuleMessagePrefix(currentElement) + "can't be contained in " //$NON-NLS-1$
                  + CapellaElementExt.getValidationRuleMessagePrefix(physicalComponent) + " due to its nature");
        }
      }
    }
    return ctx.createSuccessStatus();
  }

}
