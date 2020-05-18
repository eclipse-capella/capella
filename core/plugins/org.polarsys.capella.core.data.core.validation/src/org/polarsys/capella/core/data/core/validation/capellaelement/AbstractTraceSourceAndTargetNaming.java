/*******************************************************************************
 * Copyright (c) 2006, 2019 THALES GLOBAL SERVICES.
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
                  String sourceName = sourceNamedElement.getName() == null ? "" : sourceNamedElement.getName();
                  String targetName = targetNamedElement.getName() == null ? "" : targetNamedElement.getName();
                  if (!sourceName.equals(targetName)) {
                    return ctx.createFailureStatus(sourceName, cst.eClass().getName(), targetName, targetNamedElement
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
   * @param element
   * @return boolean
   */
  private boolean isNotRootElement(EObject element) {
    if ((element instanceof Component) && ComponentExt.isComponentRoot(element)) {
      return false;
    } else if (element instanceof AbstractFunction) {
      if (FunctionExt.isRootFunction(element)) {
        return false;
      }
    }

    return true;
  }
}
