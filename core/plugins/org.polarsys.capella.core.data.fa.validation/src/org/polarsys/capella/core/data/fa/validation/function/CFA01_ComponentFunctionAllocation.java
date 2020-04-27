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
package org.polarsys.capella.core.data.fa.validation.function;

import java.util.List;

import org.eclipse.core.runtime.IStatus;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.validation.EMFEventType;
import org.eclipse.emf.validation.IValidationContext;

import org.polarsys.capella.common.helpers.EcoreUtil2;
import org.polarsys.capella.core.data.cs.Component;
import org.polarsys.capella.core.data.fa.AbstractFunction;
import org.polarsys.capella.core.data.fa.AbstractFunctionalBlock;
import org.polarsys.capella.core.data.fa.ComponentFunctionalAllocation;
import org.polarsys.capella.core.data.fa.FaPackage;
import org.polarsys.capella.core.data.capellacore.CapellaElement;
import org.polarsys.capella.core.model.helpers.ComponentExt;
import org.polarsys.capella.core.model.helpers.CapellaElementExt;
import org.polarsys.capella.core.model.helpers.RefinementLinkExt;
import org.polarsys.capella.core.validation.rule.AbstractValidationRule;
import org.polarsys.capella.common.data.modellingcore.AbstractTrace;

/**
 * Checks allocation consistency between functions and components.
 */
public class CFA01_ComponentFunctionAllocation extends AbstractValidationRule {
  /**
   * @see org.eclipse.emf.validation.AbstractModelConstraint#validate(org.eclipse.emf.validation.IValidationContext)
   */
  @Override
  public IStatus validate(IValidationContext ctx) {
    EObject eObj = ctx.getTarget();
    EMFEventType eType = ctx.getEventType();

    if (eType == EMFEventType.NULL) {
      if (eObj instanceof ComponentFunctionalAllocation) {
        ComponentFunctionalAllocation fci = (ComponentFunctionalAllocation)eObj;

        boolean cptValid = false;
        boolean functionValid = false;

        List<CapellaElement> previousPhaseElements = RefinementLinkExt.getRelatedTargetElements((CapellaElement)eObj, FaPackage.Literals.COMPONENT_FUNCTIONAL_ALLOCATION);

        for (CapellaElement element : previousPhaseElements) {
          ComponentFunctionalAllocation exc = (ComponentFunctionalAllocation)element;

          AbstractFunction fct = exc.getFunction();
          if (null != fct) {
            for (AbstractTrace traceFunction : fct.getIncomingTraces()) {
              if (traceFunction.getSourceElement() instanceof AbstractFunction) {
                AbstractFunction targetFunction = (AbstractFunction)traceFunction.getSourceElement();
                if (EcoreUtil2.isOrIsContainedBy(fci.getFunction(), targetFunction)) {
                  functionValid = true;
                }
              }
            }
          }
          AbstractFunctionalBlock block = exc.getBlock();
          if (null != fct) {
            for (AbstractTrace traceFunction : block.getIncomingTraces()) {
              if (traceFunction.getSourceElement() instanceof AbstractFunctionalBlock) {
                AbstractFunctionalBlock targetFunction = (AbstractFunctionalBlock)traceFunction.getSourceElement();
                if (targetFunction instanceof Component && fci.getBlock() instanceof Component && (targetFunction == fci.getBlock() || 
                    ComponentExt.isComponentAncestor((Component)targetFunction, (Component)fci.getBlock()))) {
                  cptValid = true;
                }
              }
            }
          }
          if (functionValid && cptValid) {
            return ctx.createSuccessStatus();
          }
        }

        if (previousPhaseElements.size()!=0) {
          return ctx.createFailureStatus(new Object[] { CapellaElementExt.getName(fci) });
        }
      }
    }
    return ctx.createSuccessStatus();
  }
}
