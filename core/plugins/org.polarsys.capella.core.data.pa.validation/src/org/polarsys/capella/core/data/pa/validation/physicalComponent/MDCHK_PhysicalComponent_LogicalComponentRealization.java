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

import java.util.Iterator;

import org.eclipse.core.runtime.IStatus;
import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.validation.EMFEventType;
import org.eclipse.emf.validation.IValidationContext;

import org.polarsys.capella.core.data.cs.Component;
import org.polarsys.capella.core.data.la.LogicalComponent;
import org.polarsys.capella.core.data.pa.LogicalComponentRealization;
import org.polarsys.capella.core.data.pa.PhysicalComponent;
import org.polarsys.capella.core.validation.rule.AbstractValidationRule;

/**
 * Ensures that the Component Realization targeting a Physical Component always realizes an Logical Component.
 */
public class MDCHK_PhysicalComponent_LogicalComponentRealization extends AbstractValidationRule {
  /**
   * @see org.eclipse.emf.validation.AbstractModelConstraint#validate(org.eclipse.emf.validation.IValidationContext)
   */
  @Override
  public IStatus validate(IValidationContext ctx_p) {
    EObject eObj = ctx_p.getTarget();
    EMFEventType eType = ctx_p.getEventType();

    if (eType == EMFEventType.NULL) {
      if (eObj instanceof PhysicalComponent) {
        PhysicalComponent physicalComponent = (PhysicalComponent) eObj;
        EList<LogicalComponentRealization> componentRealisations = physicalComponent.getOwnedLogicalComponentRealizations();
        Iterator<LogicalComponentRealization> iterator = componentRealisations.iterator();
        while (iterator.hasNext()) {
          LogicalComponentRealization next = iterator.next();
          Component allocatedComponent = next.getAllocatedComponent();
          if ((null == allocatedComponent) || !(allocatedComponent instanceof LogicalComponent)) {
            return createFailureStatus(ctx_p, new Object[] { physicalComponent.getName() });
          }
        }
      }
    }
    return ctx_p.createSuccessStatus();
  }

}
