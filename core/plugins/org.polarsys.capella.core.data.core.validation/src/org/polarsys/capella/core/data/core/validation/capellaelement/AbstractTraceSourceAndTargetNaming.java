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
package org.polarsys.capella.core.data.core.validation.capellaelement;

import org.eclipse.core.runtime.IStatus;
import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.validation.EMFEventType;
import org.eclipse.emf.validation.IValidationContext;

import org.polarsys.capella.core.data.cs.Component;
import org.polarsys.capella.core.data.fa.AbstractFunction;
import org.polarsys.capella.core.data.helpers.fa.services.FunctionExt;
import org.polarsys.capella.core.data.capellacore.CapellaElement;
import org.polarsys.capella.core.model.helpers.ComponentExt;
import org.polarsys.capella.core.model.helpers.CapellaElementExt;
import org.polarsys.capella.core.model.utils.CapellaLayerCheckingExt;
import org.polarsys.capella.core.validation.rule.AbstractValidationRule;
import org.polarsys.capella.common.data.modellingcore.AbstractNamedElement;
import org.polarsys.capella.common.data.modellingcore.AbstractTrace;
import org.polarsys.capella.common.data.modellingcore.TraceableElement;

/**
 * Trace source and target naming check
 */
public class AbstractTraceSourceAndTargetNaming extends AbstractValidationRule {
  /**
   * @see org.eclipse.emf.validation.AbstractModelConstraint#validate(org.eclipse.emf.validation.IValidationContext)
   */
  @Override
  public IStatus validate(IValidationContext ctx) {
    EObject eObj = ctx.getTarget();
    EMFEventType eType = ctx.getEventType();
    if (eType == EMFEventType.NULL) {
      if (eObj instanceof TraceableElement) {
        TraceableElement cst = (TraceableElement) eObj;
        EList<AbstractTrace> outgoingTraces = cst.getOutgoingTraces();
        for (AbstractTrace abstractTrace : outgoingTraces) {
          // OK for validation
          if (CapellaElementExt.isValidTransitionTrace(abstractTrace) && isNotRootElement(cst)) {
            TraceableElement sourceElement = abstractTrace.getSourceElement();
            TraceableElement targetElement = abstractTrace.getTargetElement();
            // not null & are nameable
            if ((null != sourceElement) && (null != targetElement) && (sourceElement instanceof AbstractNamedElement)
                && (targetElement instanceof AbstractNamedElement)) {
              // are of valid layering
              if ((sourceElement instanceof CapellaElement) && (targetElement instanceof CapellaElement)) {
                if (CapellaLayerCheckingExt.isElementFromUpperLayer((CapellaElement) targetElement, (CapellaElement) sourceElement)) {
                  AbstractNamedElement sourceNamedElement = (AbstractNamedElement) sourceElement;
                  AbstractNamedElement targetNamedElement = (AbstractNamedElement) targetElement;
                  // if names not equal raise warning message
                  if (!sourceNamedElement.getName().equals(targetNamedElement.getName())) {
                    return ctx.createFailureStatus(sourceNamedElement.getName(), cst.eClass().getName(), targetNamedElement.getName(), targetNamedElement
                        .eClass().getName());
                  }
                }
              }
            }
          }
        }
      }
    }
    return ctx.createSuccessStatus();
  }

  /**
   * Return true if element is not Root function or Root Component
   * @param element_p
   * @return boolean
   */
  private boolean isNotRootElement(EObject element_p) {
    if ((element_p instanceof Component) && ComponentExt.isComponentRoot(element_p)) {
      return false;
    } else if (element_p instanceof AbstractFunction) {
      if (FunctionExt.isRootFunction(element_p)) {
        return false;
      }
    }

    return true;
  }
}
