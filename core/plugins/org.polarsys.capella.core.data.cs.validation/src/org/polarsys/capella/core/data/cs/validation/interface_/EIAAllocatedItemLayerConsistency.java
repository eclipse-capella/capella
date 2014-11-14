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
package org.polarsys.capella.core.data.cs.validation.interface_;

import org.eclipse.core.runtime.IStatus;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.validation.IValidationContext;

import org.polarsys.capella.core.data.cs.ExchangeItemAllocation;
import org.polarsys.capella.core.data.information.ExchangeItem;
import org.polarsys.capella.core.model.helpers.CapellaElementExt;
import org.polarsys.capella.core.model.utils.CapellaLayerCheckingExt;
import org.polarsys.capella.core.validation.rule.AbstractValidationRule;

/**
 * ExchagneItemAllocation can only allocate Elements form current or uppder layer
 */
public class EIAAllocatedItemLayerConsistency extends AbstractValidationRule {

  /**
   * @see org.eclipse.emf.validation.AbstractModelConstraint#validate(org.eclipse.emf.validation.IValidationContext)
   */
  @Override
  public IStatus validate(IValidationContext ctx_p) {
    EObject eObj = ctx_p.getTarget();

    if (eObj instanceof ExchangeItemAllocation) {
      ExchangeItemAllocation eia = (ExchangeItemAllocation) eObj;
      ExchangeItem allocatedItem = eia.getAllocatedItem();
      if ((null != allocatedItem) && !CapellaLayerCheckingExt.isElementFromCurrentOrUpperLayer(allocatedItem, eia)) {
        return ctx_p.createFailureStatus(CapellaElementExt.getCapellaExplorerLabel(eia), allocatedItem.getName(), allocatedItem.eClass().getName());
      }
    }

    return ctx_p.createSuccessStatus();
  }
}
