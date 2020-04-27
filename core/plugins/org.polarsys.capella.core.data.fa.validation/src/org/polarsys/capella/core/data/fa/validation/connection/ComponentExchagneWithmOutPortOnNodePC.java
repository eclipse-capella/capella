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
package org.polarsys.capella.core.data.fa.validation.connection;

import java.util.ArrayList;
import java.util.Collection;

import org.eclipse.core.runtime.IStatus;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.validation.EMFEventType;
import org.eclipse.emf.validation.IValidationContext;
import org.eclipse.emf.validation.model.ConstraintStatus;

import org.polarsys.capella.core.data.cs.Component;
import org.polarsys.capella.core.data.fa.ComponentExchange;
import org.polarsys.capella.core.data.pa.PhysicalComponent;
import org.polarsys.capella.core.model.helpers.ComponentExchangeExt;
import org.polarsys.capella.core.model.helpers.ComponentExt;
import org.polarsys.capella.core.model.helpers.CapellaElementExt;
import org.polarsys.capella.core.model.helpers.PhysicalComponentExt;
import org.polarsys.capella.core.validation.rule.AbstractValidationRule;

/**
 * ComponentExchange not allowed on NodePC
 */
public class ComponentExchagneWithmOutPortOnNodePC extends AbstractValidationRule {
  /**
   * @see org.eclipse.emf.validation.AbstractModelConstraint#validate(org.eclipse.emf.validation.IValidationContext)
   */
  @Override
  public IStatus validate(IValidationContext ctx) {
    EObject eObj = ctx.getTarget();
    EMFEventType eType = ctx.getEventType();

    if (eType == EMFEventType.NULL && eObj instanceof ComponentExchange) {
      Collection<IStatus> statuses = new ArrayList<>();

      ComponentExchange currentElement = (ComponentExchange) eObj;
      // get source component
      Component sourceComponent = ComponentExchangeExt.getSourceComponent(currentElement);
      if (sourceComponent instanceof PhysicalComponent && !ComponentExt.isActor(sourceComponent)
          && PhysicalComponentExt.isNode((PhysicalComponent) sourceComponent)) {
        // if its nodePC return failure message
        IStatus createFailureStatus =
            ctx.createFailureStatus(CapellaElementExt.getValidationRuleMessagePrefix(currentElement)
                                    + "source end should not be PhysicalComponent of nature NODE"); //$NON-NLS-1$
        statuses.add(createFailureStatus);
      }
      // get target component
      Component targetComponent = ComponentExchangeExt.getTargetComponent(currentElement);
      if (targetComponent instanceof PhysicalComponent && !ComponentExt.isActor(targetComponent)
          && PhysicalComponentExt.isNode((PhysicalComponent) targetComponent)) {
        // if its nodePC return failure message
        IStatus createFailureStatus =
            ctx.createFailureStatus(CapellaElementExt.getValidationRuleMessagePrefix(currentElement)
                                    + "target end should not be PhysicalComponent of nature NODE"); //$NON-NLS-1$
        statuses.add(createFailureStatus);
      }

      // return multistatus message
      if (!statuses.isEmpty()) {
        return ConstraintStatus.createMultiStatus(ctx, statuses);
      }
    }
    return ctx.createSuccessStatus();
  }

}
