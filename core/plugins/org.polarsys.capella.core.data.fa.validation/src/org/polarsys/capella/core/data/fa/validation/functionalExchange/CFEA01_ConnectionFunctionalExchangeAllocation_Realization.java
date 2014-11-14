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
package org.polarsys.capella.core.data.fa.validation.functionalExchange;

import java.util.List;

import org.eclipse.core.runtime.IStatus;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.validation.EMFEventType;
import org.eclipse.emf.validation.IValidationContext;

import org.polarsys.capella.core.data.fa.ComponentExchangeFunctionalExchangeAllocation;
import org.polarsys.capella.core.data.fa.FaPackage;
import org.polarsys.capella.core.data.capellacore.CapellaElement;
import org.polarsys.capella.core.model.helpers.CapellaElementExt;
import org.polarsys.capella.core.model.helpers.RefinementLinkExt;
import org.polarsys.capella.core.validation.rule.AbstractValidationRule;

/**
 * Checks allocation consistency between functions and components.
 */
public class CFEA01_ConnectionFunctionalExchangeAllocation_Realization extends AbstractValidationRule {
  /**
   * @see org.eclipse.emf.validation.AbstractModelConstraint#validate(org.eclipse.emf.validation.IValidationContext)
   */
  @Override
  public IStatus validate(IValidationContext ctx_p) {
    EObject eObj = ctx_p.getTarget();
    EMFEventType eType = ctx_p.getEventType();

    if (eType == EMFEventType.NULL) {
      if (eObj instanceof ComponentExchangeFunctionalExchangeAllocation) {
        ComponentExchangeFunctionalExchangeAllocation fci = (ComponentExchangeFunctionalExchangeAllocation)eObj;

        List<CapellaElement> previousPhaseElements = RefinementLinkExt.getRelatedTargetElements((CapellaElement)eObj, FaPackage.Literals.COMPONENT_EXCHANGE_FUNCTIONAL_EXCHANGE_ALLOCATION);

        boolean functionValid = false;
        boolean cptValid = false;

        for (CapellaElement element : previousPhaseElements) {
          ComponentExchangeFunctionalExchangeAllocation exc = (ComponentExchangeFunctionalExchangeAllocation)element;
          cptValid = RefinementLinkExt.isLinkedTo(fci.getAllocatingComponentExchange(), exc.getAllocatingComponentExchange());
          functionValid = RefinementLinkExt.isLinkedTo(fci.getAllocatedFunctionalExchange(), exc.getAllocatedFunctionalExchange());

          if (functionValid && cptValid) {
            return ctx_p.createSuccessStatus();
          }
        }

        if (previousPhaseElements.size()!=0) {
          return createFailureStatus(ctx_p, new Object[] { CapellaElementExt.getName(fci) });
        }

      }


    }
    return ctx_p.createSuccessStatus();
  }
}
