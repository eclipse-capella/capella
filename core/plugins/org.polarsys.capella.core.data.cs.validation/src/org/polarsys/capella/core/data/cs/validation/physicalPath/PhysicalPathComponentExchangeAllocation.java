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
package org.polarsys.capella.core.data.cs.validation.physicalPath;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import org.eclipse.core.runtime.IStatus;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.validation.EMFEventType;
import org.eclipse.emf.validation.IValidationContext;
import org.eclipse.emf.validation.model.ConstraintStatus;

import org.polarsys.capella.core.business.queries.IBusinessQuery;
import org.polarsys.capella.core.business.queries.capellacore.BusinessQueriesProvider;
import org.polarsys.capella.core.data.cs.PhysicalPath;
import org.polarsys.capella.core.data.fa.FaPackage;
import org.polarsys.capella.core.data.capellacore.CapellaElement;
import org.polarsys.capella.core.model.helpers.CapellaElementExt;
import org.polarsys.capella.core.validation.rule.AbstractValidationRule;
import org.polarsys.capella.common.data.modellingcore.AbstractNamedElement;

public class PhysicalPathComponentExchangeAllocation extends AbstractValidationRule {
  /**
   * @see org.eclipse.emf.validation.AbstractModelConstraint#validate(org.eclipse.emf.validation.IValidationContext)
   */
  @Override
  public IStatus validate(IValidationContext ctx) {
    EObject eObj = ctx.getTarget();
    EMFEventType eType = ctx.getEventType();
    Collection<IStatus> statuses = new ArrayList<IStatus>();
    if (eType == EMFEventType.NULL) {

      if (eObj instanceof PhysicalPath) {
        PhysicalPath chain = (PhysicalPath) eObj;
        List<CapellaElement> availableList = new ArrayList<CapellaElement>();
        List<CapellaElement> currentList = new ArrayList<CapellaElement>();
        IBusinessQuery query =
            BusinessQueriesProvider.getInstance().getContribution(chain.eClass(),
                FaPackage.Literals.COMPONENT_EXCHANGE_ALLOCATOR__OWNED_COMPONENT_EXCHANGE_ALLOCATIONS);
        if (query != null) {
          availableList.addAll(query.getAvailableElements(chain));
          currentList.addAll(query.getCurrentElements(chain, false));
          for (CapellaElement capellaElement : currentList) {
            if (!availableList.contains(capellaElement)) {
              IStatus createFailureStatus =
                  ctx.createFailureStatus(CapellaElementExt.getValidationRuleMessagePrefix(chain)
                                          + " should not allocate " + (((AbstractNamedElement) capellaElement)).getName() + " (" + capellaElement.eClass().getName() + ")"); //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$
              statuses.add(createFailureStatus);
            }
          }
        }
      }
    }

    // return multi-status message
    if (!statuses.isEmpty()) {
      return ConstraintStatus.createMultiStatus(ctx, statuses);
    }

    return ctx.createSuccessStatus();
  }
}
