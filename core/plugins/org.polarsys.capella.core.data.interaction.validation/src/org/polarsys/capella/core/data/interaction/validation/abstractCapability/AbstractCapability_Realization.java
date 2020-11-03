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
package org.polarsys.capella.core.data.interaction.validation.abstractCapability;

import org.eclipse.core.runtime.IStatus;
import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.validation.EMFEventType;
import org.eclipse.emf.validation.IValidationContext;

import org.polarsys.capella.core.data.interaction.AbstractCapability;
import org.polarsys.capella.core.data.interaction.AbstractCapabilityRealization;
import org.polarsys.capella.core.model.utils.CapellaLayerCheckingExt;
import org.polarsys.capella.core.validation.rule.AbstractValidationRule;
import org.polarsys.capella.common.data.modellingcore.AbstractTrace;

/**
 * Check weather an AbstractCapability is realized by any other AbstractCapability from lower layer
 */
public class AbstractCapability_Realization extends AbstractValidationRule {
  /**
   * @see org.eclipse.emf.validation.AbstractModelConstraint#validate(org.eclipse.emf.validation.IValidationContext)
   */
  @Override
  public IStatus validate(IValidationContext ctx_p) {
    EObject eObj = ctx_p.getTarget();
    EMFEventType eType = ctx_p.getEventType();
    if (eType == EMFEventType.NULL) {
      if ((eObj instanceof AbstractCapability)) {
        AbstractCapability capa = (AbstractCapability) eObj;
        EList<AbstractTrace> incomingTraces = capa.getIncomingTraces();
        if (!incomingTraces.isEmpty()) {
          for (AbstractTrace abstractTrace : incomingTraces) {
            if (abstractTrace instanceof AbstractCapabilityRealization) {
              return ctx_p.createSuccessStatus();
            }
          }
        }
        // if abstract_capabilty from EPBS layer
        if (CapellaLayerCheckingExt.isAOrInEPBSLayer(capa)) {
          return ctx_p.createSuccessStatus();
        }
        String lowerLayerCapa = "CapabilityRealization"; //$NON-NLS-1$
        if (CapellaLayerCheckingExt.isAOrInOperationalAnalysisLayer(capa)) {
          lowerLayerCapa = "Capability"; //$NON-NLS-1$
        }

        return ctx_p.createFailureStatus(capa.getName() + " (" + capa.eClass().getName() + ")" + " is not realized by any " + lowerLayerCapa); //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$
      }
    }
    return ctx_p.createSuccessStatus();
  }
}
