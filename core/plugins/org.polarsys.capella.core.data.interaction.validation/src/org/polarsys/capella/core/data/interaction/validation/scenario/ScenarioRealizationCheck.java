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
package org.polarsys.capella.core.data.interaction.validation.scenario;

import org.eclipse.core.runtime.IStatus;
import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.validation.EMFEventType;
import org.eclipse.emf.validation.IValidationContext;

import org.polarsys.capella.core.data.interaction.Scenario;
import org.polarsys.capella.core.data.interaction.ScenarioRealization;
import org.polarsys.capella.core.model.utils.CapellaLayerCheckingExt;
import org.polarsys.capella.core.validation.rule.AbstractValidationRule;
import org.polarsys.capella.common.data.modellingcore.AbstractTrace;

/**
 * Check weather Scenario is realized by any other Scenario from lower layer
 */
public class ScenarioRealizationCheck extends AbstractValidationRule {
  /**
   * @see org.eclipse.emf.validation.AbstractModelConstraint#validate(org.eclipse.emf.validation.IValidationContext)
   */
  @Override
  public IStatus validate(IValidationContext ctx_p) {
    EObject eObj = ctx_p.getTarget();
    EMFEventType eType = ctx_p.getEventType();
    if (eType == EMFEventType.NULL) {
      if ((eObj instanceof Scenario)) {
        Scenario scenario = (Scenario) eObj;
        EList<AbstractTrace> outgoingTraces = scenario.getIncomingTraces();
        if (!outgoingTraces.isEmpty()) {
          for (AbstractTrace abstractTrace : outgoingTraces) {
            if (abstractTrace instanceof ScenarioRealization) {
              return ctx_p.createSuccessStatus();
            }
          }
        }
        // if scenario from EPBS layer
        if (CapellaLayerCheckingExt.isAOrInEPBSLayer(scenario)) {
          return ctx_p.createSuccessStatus();
        }
        return ctx_p.createFailureStatus(scenario.getName() + " (" + scenario.eClass().getName() + ")" + " is not realized by any Scenario"); //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$
      }
    }
    return ctx_p.createSuccessStatus();
  }
}
