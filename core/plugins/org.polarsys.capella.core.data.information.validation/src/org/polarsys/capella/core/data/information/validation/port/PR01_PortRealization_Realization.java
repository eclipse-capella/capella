/*******************************************************************************
 * Copyright (c) 2006, 2017 THALES GLOBAL SERVICES.
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
package org.polarsys.capella.core.data.information.validation.port;

import java.util.List;

import org.eclipse.core.runtime.IStatus;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.validation.EMFEventType;
import org.eclipse.emf.validation.IValidationContext;

import org.polarsys.capella.core.data.fa.ComponentPort;
import org.polarsys.capella.core.data.fa.FunctionPort;
import org.polarsys.capella.core.data.information.InformationPackage;
import org.polarsys.capella.core.data.information.Port;
import org.polarsys.capella.core.data.information.PortAllocation;
import org.polarsys.capella.core.data.capellacore.CapellaElement;
import org.polarsys.capella.core.model.helpers.PortExt;
import org.polarsys.capella.core.model.helpers.RefinementLinkExt;
import org.polarsys.capella.core.validation.rule.AbstractValidationRule;

/**
 * Checks port realizations consistency.
 */
public class PR01_PortRealization_Realization extends AbstractValidationRule {
  /**
   * @see org.eclipse.emf.validation.AbstractModelConstraint#validate(org.eclipse.emf.validation.IValidationContext)
   */
  @Override
  public IStatus validate(IValidationContext ctx) {
    EObject eObj = ctx.getTarget();
    EMFEventType eType = ctx.getEventType();

    if (eType == EMFEventType.NULL) {

      boolean functionValid = true;
      boolean cptValid = true;
      if (eObj instanceof PortAllocation) {
        PortAllocation port = (PortAllocation) eObj;

        if ((port.getTargetElement() != null) && (port.getSourceElement() != null)) {
          List<CapellaElement> previousPhaseElements =
              RefinementLinkExt.getRelatedTargetElements((CapellaElement) eObj, InformationPackage.Literals.PORT_ALLOCATION);

          for (CapellaElement element : previousPhaseElements) {
            PortAllocation sourceElement = (PortAllocation) element;

            if (sourceElement.getTargetElement() instanceof FunctionPort) {
              functionValid = PortExt.transitionedPortIsValid((FunctionPort) port.getTargetElement());
            }
            if ((sourceElement.getSourceElement() instanceof ComponentPort) && (port.getSourceElement() instanceof ComponentPort)) {
              cptValid = PortExt.isTransitionedTo((ComponentPort) sourceElement.getSourceElement(), (ComponentPort) port.getSourceElement());
            }
            if (!functionValid || !cptValid) {
              return ctx.createFailureStatus(new Object[] { ((Port) port.getTargetElement()).getName() });
            }
          }
        }
      }
    }
    return ctx.createSuccessStatus();
  }
}
