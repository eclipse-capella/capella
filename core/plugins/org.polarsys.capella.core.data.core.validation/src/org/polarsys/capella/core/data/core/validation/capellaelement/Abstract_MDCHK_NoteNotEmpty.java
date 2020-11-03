/*******************************************************************************
 * Copyright (c) 2006, 2020 THALES GLOBAL SERVICES.
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
package org.polarsys.capella.core.data.core.validation.capellaelement;

import org.eclipse.core.runtime.IStatus;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.validation.IValidationContext;
import org.polarsys.capella.common.data.modellingcore.AbstractNamedElement;
import org.polarsys.capella.common.mdsofa.common.constant.ICommonConstants;
import org.polarsys.capella.core.data.capellacore.CapellaElement;
import org.polarsys.capella.core.data.cs.Component;
import org.polarsys.capella.core.data.fa.AbstractFunction;
import org.polarsys.capella.core.data.fa.ComponentExchange;
import org.polarsys.capella.core.data.fa.FunctionalExchange;
import org.polarsys.capella.core.validation.rule.AbstractValidationRule;

public abstract class Abstract_MDCHK_NoteNotEmpty extends AbstractValidationRule {

  @Override
  public IStatus validate(IValidationContext ctx) {
    EObject eObj = ctx.getTarget();
    if (eObj instanceof CapellaElement && isImpactedByCurrentRule(eObj)) {
      CapellaElement me = (CapellaElement) eObj;
      String desc = getNoteContent(me);
      if (desc == null || desc.equals(ICommonConstants.EMPTY_STRING)) {
        if (me instanceof AbstractNamedElement) {
          AbstractNamedElement ne = (AbstractNamedElement) me;
          return createFailureStatus(ctx, new Object[] { me.eClass().getName(), ne.getName() });
        }
        return createFailureStatus(ctx, new Object[] { me.eClass().getName(), "" }); //$NON-NLS-1$
      }
    }
    return ctx.createSuccessStatus();
  }

  private boolean isImpactedByCurrentRule(EObject eObj) {
    return (eObj instanceof FunctionalExchange || eObj instanceof ComponentExchange || eObj instanceof AbstractFunction
        || eObj instanceof Component);
  }

  protected abstract String getNoteContent(CapellaElement me);

}
