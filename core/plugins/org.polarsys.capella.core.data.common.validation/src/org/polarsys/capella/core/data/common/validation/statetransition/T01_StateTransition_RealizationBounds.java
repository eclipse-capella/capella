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
package org.polarsys.capella.core.data.common.validation.statetransition;

import java.util.List;

import org.eclipse.core.runtime.IStatus;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.validation.IValidationContext;

import org.polarsys.capella.core.data.capellacommon.CapellacommonPackage;
import org.polarsys.capella.core.data.capellacommon.StateTransition;
import org.polarsys.capella.core.data.capellacore.CapellaElement;
import org.polarsys.capella.core.model.helpers.CapellaElementExt;
import org.polarsys.capella.core.model.helpers.RefinementLinkExt;
import org.polarsys.capella.core.validation.rule.AbstractValidationRule;
import org.polarsys.capella.common.data.modellingcore.TraceableElement;

/**
 *
 */
public class T01_StateTransition_RealizationBounds extends AbstractValidationRule {

  /**
   * @see org.eclipse.emf.validation.AbstractModelConstraint#validate(org.eclipse.emf.validation.IValidationContext)
   */
  @Override
  public IStatus validate(IValidationContext ctx) {
    EObject eObj = ctx.getTarget();

    if (eObj instanceof StateTransition) {
      StateTransition fci = (StateTransition)eObj;
      List<CapellaElement> previousPhaseElements = RefinementLinkExt.getRelatedTargetElements(fci, CapellacommonPackage.Literals.STATE_TRANSITION);
      
      boolean sourceValid = true;
      boolean targetValid = true;

      for (CapellaElement element : previousPhaseElements) {
        StateTransition exc = (StateTransition)element;

        if (fci.getSource() instanceof TraceableElement && exc.getSource() instanceof TraceableElement) {
          sourceValid = RefinementLinkExt.isLinkedTo((TraceableElement)fci.getSource(), (TraceableElement)exc.getSource());
        }
        if (fci.getTarget() instanceof TraceableElement && exc.getTarget() instanceof TraceableElement) {
          targetValid = RefinementLinkExt.isLinkedTo((TraceableElement)fci.getTarget(), (TraceableElement)exc.getTarget());
        }
        
      }

      if (sourceValid && targetValid) {
        return ctx.createSuccessStatus();
      }

      if (previousPhaseElements.size()!=0) {
        return createFailureStatus(ctx, new Object[] { CapellaElementExt.getName(fci) });
      }

    }
    return ctx.createSuccessStatus();
  }

}
