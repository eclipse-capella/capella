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
package org.polarsys.capella.core.data.fa.validation.componentPort;

import org.eclipse.core.runtime.IStatus;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.validation.EMFEventType;
import org.eclipse.emf.validation.IValidationContext;

import org.polarsys.capella.core.data.cs.Component;
import org.polarsys.capella.core.data.fa.ComponentPort;
import org.polarsys.capella.core.data.pa.PhysicalComponent;
import org.polarsys.capella.core.model.helpers.CapellaElementExt;
import org.polarsys.capella.core.model.helpers.PhysicalComponentExt;
import org.polarsys.capella.core.model.helpers.PortExt;
import org.polarsys.capella.core.validation.rule.AbstractValidationRule;

/**
 * ComponentPort not allowed on NodePC
 */
public class ComponentPortOnNodePC extends AbstractValidationRule {
  /**
   * @see org.eclipse.emf.validation.AbstractModelConstraint#validate(org.eclipse.emf.validation.IValidationContext)
   */
  @Override
  public IStatus validate(IValidationContext ctx) {
    EObject eObj = ctx.getTarget();
    EMFEventType eType = ctx.getEventType();

    if (eType == EMFEventType.NULL) {
      // current component : assuming it could be deployed
      if (eObj instanceof ComponentPort) {
        ComponentPort currentElement = (ComponentPort) eObj;
        Component relatedComponent = PortExt.getRelatedComponent(currentElement);
        if ((null != relatedComponent) && (relatedComponent instanceof PhysicalComponent)) {
          if (PhysicalComponentExt.isNode((PhysicalComponent) relatedComponent)) {
            return ctx.createFailureStatus(CapellaElementExt.getValidationRuleMessagePrefix(currentElement)
                                           + "can't be contained in PhysicalComponent of nature NODE"); //$NON-NLS-1$
          }
        }
      }
    }
    return ctx.createSuccessStatus();
  }
}
