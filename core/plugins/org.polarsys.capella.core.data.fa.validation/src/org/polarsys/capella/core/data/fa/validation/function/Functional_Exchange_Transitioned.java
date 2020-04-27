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
import org.eclipse.emf.validation.IValidationContext;
import org.polarsys.capella.common.data.activity.ActivityNode;
import org.polarsys.capella.common.data.modellingcore.AbstractTrace;
import org.polarsys.capella.common.data.modellingcore.TraceableElement;
import org.polarsys.capella.core.data.fa.AbstractFunction;
import org.polarsys.capella.core.data.fa.FunctionPort;
import org.polarsys.capella.core.data.fa.FunctionalExchange;
import org.polarsys.capella.core.data.oa.OperationalAnalysis;
import org.polarsys.capella.core.model.helpers.SystemEngineeringExt;
import org.polarsys.capella.core.validation.rule.AbstractValidationRule;

public class Functional_Exchange_Transitioned extends AbstractValidationRule {

  /*
   * (non-Javadoc)
   * @see org.eclipse.emf.validation.AbstractModelConstraint#validate(org.eclipse.emf.validation.IValidationContext)
   */
  @Override
  public IStatus validate(IValidationContext ctx) {
    // Test values initialization
    boolean targetTest = false;
    boolean sourceTest = false;
    EObject eObj = ctx.getTarget();

    // Don't check the rule in the Operational Analysis level because there is no transition available above this level.
    if (SystemEngineeringExt.findArchitecture(eObj) instanceof OperationalAnalysis) {
      return ctx.createSuccessStatus();
    }

    if (eObj instanceof FunctionalExchange) {
      // Typing the object
      FunctionalExchange functionalExchange = (FunctionalExchange) eObj;

      // Get target and source ports
      ActivityNode source = functionalExchange.getSource();
      ActivityNode target = functionalExchange.getTarget();

      // Initialization
      EObject objectSource = null;
      EObject objectTarget = null;

      // Check if target and source are not null
      if ((null != source) && (null != target)) {
        // Get Source
        if (source instanceof FunctionPort) {
          objectSource = ((FunctionPort) source).eContainer();
        } else if (source instanceof AbstractFunction) {
          objectSource = ((AbstractFunction) source).eContainer();
        }

        // Get Target
        if (target instanceof FunctionPort) {
          objectTarget = ((FunctionPort) target).eContainer();
        } else if (target instanceof AbstractFunction) {
          objectTarget = ((AbstractFunction) target).eContainer();
        }
      }

      // Test the type of the functions
      if ((objectSource instanceof AbstractFunction) && (objectTarget instanceof AbstractFunction)) {
        // Get the source and target Outgoing
        List<AbstractTrace> outgoingSource = ((AbstractFunction) objectSource).getOutgoingTraces();
        List<AbstractTrace> outgoingTarget = ((AbstractFunction) objectTarget).getOutgoingTraces();

        // If one of the list is empty => Validation rule is success
        if (outgoingSource.isEmpty() || outgoingTarget.isEmpty()) {
          // Success
          return ctx.createSuccessStatus();
        }
        // Get the outgoingTraces
        List<AbstractTrace> outgoingExchanges = functionalExchange.getOutgoingTraces();
        for (AbstractTrace abstractTrace : outgoingExchanges) {
          // Get the target element
          TraceableElement sourceElement = abstractTrace.getTargetElement();
          if (sourceElement instanceof FunctionalExchange) {
            // Typing the element source
            ActivityNode sourceNode = ((FunctionalExchange) sourceElement).getSource();
            // Create the sourceObject to compare
            EObject sourceObject = sourceNode;
            // If the source is a Port
            if (sourceNode instanceof FunctionPort) {
              // Get the container of the port
              sourceObject = sourceNode.eContainer();
            }
            // Typing the element target
            ActivityNode targetNode = ((FunctionalExchange) sourceElement).getTarget();
            // Create the sourceObject to compare
            EObject targetObject = targetNode;
            // If the target is a Port
            if (targetNode instanceof FunctionPort) {
              // Get the container of the port
              targetObject = targetNode.eContainer();
            }
            // For each element of the outgoing target
            for (AbstractTrace trace : outgoingTarget) {
              if (trace.getTargetElement().equals(targetObject)) {
                // If the element is contained the test is OK
                targetTest = true;
              }
            }
            // For each element of the outgoing source
            for (AbstractTrace abstractTrace2 : outgoingSource) {
              if (abstractTrace2.getTargetElement().equals(sourceObject)) {
                // If the element is contained the test is OK
                sourceTest = true;
              }
            }
          }
        }
      }
      if (sourceTest && targetTest) {
        // If the 2 test are ok => success
        return ctx.createSuccessStatus();
      }
      // Warning on the validation rule
      EObject eSource = functionalExchange.getSource();
      EObject eTarget = functionalExchange.getTarget();
      return ctx.createFailureStatus(new Object[] { functionalExchange.getName(), eSource == null ? null : eSource.eContainer(),
                                                     eTarget == null ? null : eTarget.eContainer() });
    }

    // Warning on the validation rule
    return ctx.createFailureStatus(new Object[] {});
  }
}
