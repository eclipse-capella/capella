/*******************************************************************************
 * Copyright (c) 2006, 2016 THALES GLOBAL SERVICES.
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
import java.util.List;

import org.eclipse.core.runtime.IStatus;
import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.validation.EMFEventType;
import org.eclipse.emf.validation.IValidationContext;
import org.eclipse.emf.validation.model.ConstraintStatus;

import org.polarsys.capella.core.data.cs.BlockArchitecture;
import org.polarsys.capella.core.data.cs.PhysicalLink;
import org.polarsys.capella.core.data.fa.ComponentExchange;
import org.polarsys.capella.core.data.fa.FunctionalExchange;
import org.polarsys.capella.core.data.capellacore.CapellaElement;
import org.polarsys.capella.core.validation.rule.AbstractValidationRule;

/**
 * Checks that the functional exchanges allocated to a component exchange come from the same level.
 */
public class MDCHK_Connection_FunctionalExchangeAllocation extends AbstractValidationRule {
  /**
   * @see org.eclipse.emf.validation.AbstractModelConstraint#validate(org.eclipse.emf.validation.IValidationContext)
   */
  @Override
  public IStatus validate(IValidationContext ctx) {
    EObject eObj = ctx.getTarget();
    EMFEventType eType = ctx.getEventType();
    if (eType == EMFEventType.NULL) {
      // This rule does not applies to physical links
      if (eObj instanceof ComponentExchange && !(eObj instanceof PhysicalLink)) {
        ComponentExchange ce = (ComponentExchange) eObj;
        EList<FunctionalExchange> functionalExchanges = ce.getAllocatedFunctionalExchanges();
        BlockArchitecture ceLevel = getArchitectureLevel(ce);
        List<IStatus> statuses = new ArrayList<IStatus>();
        for (FunctionalExchange fe : functionalExchanges) {
          BlockArchitecture feLevel = getArchitectureLevel(fe);
          if (null != ceLevel && null != feLevel && feLevel != ceLevel) {
            statuses.add(ctx.createFailureStatus(new Object[] { ce.getName(), ce.eClass().getName(), ceLevel.getName(), fe.getName(), fe.eClass().getName(), feLevel.getName() }));
          }
        }
        if (statuses.size() > 0) {
          return ConstraintStatus.createMultiStatus(ctx, statuses);
        }

      }
    }
    return ctx.createSuccessStatus();
  }

  /**
   * TODO This method should be somewhere in a generic Capella helper Return the component architecture item above the given capella element
   * @param element the capella element
   * @return a <code>ComponentArchitecture</code> instance
   */
  private static BlockArchitecture getArchitectureLevel(CapellaElement element) {
    BlockArchitecture returnvalue = null;
    EObject container = element.eContainer();
    if (null != container) {
      while (!(container instanceof BlockArchitecture) && null != container) {
        container = container.eContainer();
      }
      if (container instanceof BlockArchitecture) {
        returnvalue = (BlockArchitecture) container;
      }
    }
    return returnvalue;
  }
}
