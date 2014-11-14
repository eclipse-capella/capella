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
package org.polarsys.capella.core.data.pa.validation.physicalComponent;

import org.eclipse.core.runtime.IStatus;
import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.validation.EMFEventType;
import org.eclipse.emf.validation.IValidationContext;

import org.polarsys.capella.core.data.pa.PhysicalComponent;
import org.polarsys.capella.core.model.helpers.CapellaElementExt;
import org.polarsys.capella.core.model.helpers.PhysicalComponentExt;
import org.polarsys.capella.core.validation.rule.AbstractValidationRule;

/**
 * NodePC can't be deployed in BehaviourPC
 */
public class NodeDeploymentInBehaviourPC extends AbstractValidationRule {
  /**
   * @see org.eclipse.emf.validation.AbstractModelConstraint#validate(org.eclipse.emf.validation.IValidationContext)
   */
  @Override
  public IStatus validate(IValidationContext ctx) {
    EObject eObj = ctx.getTarget();
    EMFEventType eType = ctx.getEventType();

    if (eType == EMFEventType.NULL) {
      // current component : assuming it could be deployed
      if (eObj instanceof PhysicalComponent) {
        PhysicalComponent currentElement = (PhysicalComponent) eObj;
        if (PhysicalComponentExt.isNode(currentElement)) {
          EList<PhysicalComponent> deployingPhysicalComponents = currentElement.getDeployingPhysicalComponents();
          for (PhysicalComponent capellaElement : deployingPhysicalComponents) {
            if (PhysicalComponentExt.isBehaviour(capellaElement)) {
              return ctx.createFailureStatus(CapellaElementExt.getValidationRuleMessagePrefix(currentElement)
                                             + "of nature NODE can't be deployed on Physical Component of nature BEHAVIOUR"); //$NON-NLS-1$
            }
          }
        }
      }
    }
    return ctx.createSuccessStatus();
  }
}
