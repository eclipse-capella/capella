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
package org.polarsys.capella.core.data.la.validation.logicalComponent;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.eclipse.core.runtime.IStatus;
import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.validation.EMFEventType;
import org.eclipse.emf.validation.IValidationContext;

import org.polarsys.capella.common.mdsofa.common.constant.ICommonConstants;
import org.polarsys.capella.core.data.cs.Component;
import org.polarsys.capella.core.data.cs.ComponentAllocation;
import org.polarsys.capella.core.data.la.LogicalComponent;
import org.polarsys.capella.core.data.pa.LogicalComponentRealization;
import org.polarsys.capella.core.data.pa.PhysicalComponent;
import org.polarsys.capella.core.validation.rule.AbstractValidationRule;

/**
 * This check insures that a Logical Component is not realized by more than one Physical Component.
 */
public class MDCHK_LogicalComponent_Realization_2 extends AbstractValidationRule {
  /**
   * @see org.eclipse.emf.validation.AbstractModelConstraint#validate(org.eclipse.emf.validation.IValidationContext)
   */
  @Override
  public IStatus validate(IValidationContext ctx) {
    EObject eObj = ctx.getTarget();
    EMFEventType eType = ctx.getEventType();

    if (eType == EMFEventType.NULL) {
      if (eObj instanceof LogicalComponent) {
        // current element
        LogicalComponent lc = (LogicalComponent) eObj;
        // list of providing component allocation
        EList<ComponentAllocation> provisioningComponentAllocations = lc.getProvisioningComponentAllocations();
        // collect all the allocating 'PhysicalComponent'
        List<PhysicalComponent> allocatingComponents = new ArrayList<PhysicalComponent>();
        String pcs = ICommonConstants.EMPTY_STRING;
        Iterator<ComponentAllocation> iterator = provisioningComponentAllocations.iterator();

        while (iterator.hasNext()) {
          ComponentAllocation componentAllocation = iterator.next();
          // filter 'LogicalComponentRealization'
          if (componentAllocation instanceof LogicalComponentRealization) {
            Component allocatingComponent = componentAllocation.getAllocatingComponent();
            // add to list if allocatingComponent is instance of 'PhysicalComponent'
            if (allocatingComponent instanceof PhysicalComponent) {
              allocatingComponents.add((PhysicalComponent) allocatingComponent);
              pcs = pcs + allocatingComponent.getName();
              if (iterator.hasNext()) {
                pcs = pcs + ", "; //$NON-NLS-1$
              }
            }
          }
        }

        // return failure message if there is more then one 'PhysicalComponent' realizing current 'LogicalComponent'
        if (allocatingComponents.size() > 1) {
          // create and return failure message
          return createFailureStatus(ctx, new Object[] { lc.getName(), pcs });
        }

      }
    }
    return ctx.createSuccessStatus();
  }
}
