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
package org.polarsys.capella.core.data.cs.validation.interface_;

import org.eclipse.core.runtime.IStatus;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.validation.EMFEventType;
import org.eclipse.emf.validation.IValidationContext;

import org.polarsys.capella.common.helpers.EcoreUtil2;
import org.polarsys.capella.core.data.cs.Interface;
import org.polarsys.capella.core.data.ctx.CtxPackage;
import org.polarsys.capella.core.data.la.ContextInterfaceRealization;
import org.polarsys.capella.core.data.la.LaPackage;
import org.polarsys.capella.core.validation.rule.AbstractValidationRule;
import org.polarsys.capella.common.data.modellingcore.AbstractTrace;

/**
 * This check insures that an interface is implemented by at least one Component.
 */
public class MDCHK_Interface_Realization_1 extends AbstractValidationRule {
  /**
   * @see org.eclipse.emf.validation.AbstractModelConstraint#validate(org.eclipse.emf.validation.IValidationContext)
   */
  @Override
  public IStatus validate(IValidationContext ctx) {
    EObject eObj = ctx.getTarget();
    EMFEventType eType = ctx.getEventType();

    if (eType == EMFEventType.NULL) {
      if ((eObj instanceof Interface) && EcoreUtil2.isContainedBy(eObj, CtxPackage.Literals.SYSTEM_ANALYSIS)) {
        Interface itf = (Interface) eObj;

        for (AbstractTrace trace : itf.getIncomingTraces()) {
          if ((trace instanceof ContextInterfaceRealization) && (trace.getSourceElement() instanceof Interface)
              && EcoreUtil2.isContainedBy(trace.getSourceElement(), LaPackage.Literals.LOGICAL_ARCHITECTURE)) {
            return ctx.createSuccessStatus();
          }
        }

        return createFailureStatus(ctx, new Object[] { itf.getName() });
      }
    }
    return ctx.createSuccessStatus();
  }
}
