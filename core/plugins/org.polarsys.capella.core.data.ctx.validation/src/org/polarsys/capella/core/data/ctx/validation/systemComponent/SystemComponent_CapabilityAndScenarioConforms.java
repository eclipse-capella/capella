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
package org.polarsys.capella.core.data.ctx.validation.systemComponent;

import java.util.ArrayList;
import java.util.List;

import org.eclipse.core.runtime.IStatus;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.validation.EMFEventType;
import org.eclipse.emf.validation.IValidationContext;
import org.eclipse.emf.validation.model.ConstraintStatus;
import org.polarsys.capella.core.data.capellacore.Type;
import org.polarsys.capella.core.data.ctx.Capability;
import org.polarsys.capella.core.data.ctx.SystemComponent;
import org.polarsys.capella.core.data.information.AbstractInstance;
import org.polarsys.capella.core.data.interaction.InstanceRole;
import org.polarsys.capella.core.data.interaction.Scenario;
import org.polarsys.capella.core.model.helpers.BlockArchitectureExt;
import org.polarsys.capella.core.model.helpers.CapellaElementExt;
import org.polarsys.capella.core.validation.rule.AbstractValidationRule;

/**
 * Check if current Actor is involved with the Capability, but it does not appear in any of its scenario.
 */
public class SystemComponent_CapabilityAndScenarioConforms extends AbstractValidationRule {
  /**
   * @see org.eclipse.emf.validation.AbstractModelConstraint#validate(org.eclipse.emf.validation.IValidationContext)
   */
  @Override
  public IStatus validate(IValidationContext ctx) {
    EObject eObj = ctx.getTarget();
    EMFEventType eType = ctx.getEventType();

    java.util.Collection<IStatus> statuses = new ArrayList<IStatus>();

    // In the case of batch mode.
    if (eType == EMFEventType.NULL) {
      if (eObj instanceof SystemComponent) {
        SystemComponent actor = (SystemComponent) eObj;

        if (!BlockArchitectureExt.isRootComponent(actor)) {
          for (Capability capabilityReal : actor.getInvolvingCapabilities()) {
            List<SystemComponent> lcomponentList = new ArrayList<SystemComponent>();
            for (Scenario scenario : capabilityReal.getOwnedScenarios()) {
              for (InstanceRole instRole : scenario.getOwnedInstanceRoles()) {
                AbstractInstance inst = instRole.getRepresentedInstance();
                if (inst != null) {
                  Type type = inst.getType();
                  if (type instanceof SystemComponent) {
                    lcomponentList.add((SystemComponent) type);
                  }
                }
              }
            }
            if (!lcomponentList.contains(actor)) {
              IStatus status = ctx.createFailureStatus(
                  new Object[] { CapellaElementExt.getValidationRuleMessagePrefix(actor), capabilityReal.getName() });
              statuses.add(status);
            }
          }
        }
      }
    }
    if (statuses.isEmpty()) {
      return ctx.createSuccessStatus();
    }
    return ConstraintStatus.createMultiStatus(ctx, statuses);
  }
}
