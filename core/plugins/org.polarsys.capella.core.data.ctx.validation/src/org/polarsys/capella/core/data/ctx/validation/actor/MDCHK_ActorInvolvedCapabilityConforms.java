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
package org.polarsys.capella.core.data.ctx.validation.actor;

import java.util.ArrayList;
import java.util.List;

import org.eclipse.core.runtime.IStatus;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.validation.EMFEventType;
import org.eclipse.emf.validation.IValidationContext;

import org.polarsys.capella.core.data.ctx.Actor;
import org.polarsys.capella.core.data.ctx.Capability;
import org.polarsys.capella.core.data.information.AbstractInstance;
import org.polarsys.capella.core.data.interaction.InstanceRole;
import org.polarsys.capella.core.data.interaction.Scenario;
import org.polarsys.capella.core.data.capellacore.Type;
import org.polarsys.capella.core.validation.rule.AbstractValidationRule;

/**
 * Check if current Actor is involved with the Capability, but it does not appear in any of its scenario.
 */
public class MDCHK_ActorInvolvedCapabilityConforms extends AbstractValidationRule {
  /**
   * @see org.eclipse.emf.validation.AbstractModelConstraint#validate(org.eclipse.emf.validation.IValidationContext)
   */
  @Override
  public IStatus validate(IValidationContext ctx) {
    EObject eObj = ctx.getTarget();
    EMFEventType eType = ctx.getEventType();

    // In the case of batch mode.
    if (eType == EMFEventType.NULL) {
      if (eObj instanceof Actor) {
        Actor actor = (Actor) eObj;

        for (Capability capabilityReal : actor.getContributedCapabilities()) {
          List<Actor> lcomponentList = new ArrayList<Actor>();
          for (Scenario scenario : capabilityReal.getOwnedScenarios()) {
            for (InstanceRole instRole : scenario.getOwnedInstanceRoles()) {
              AbstractInstance inst = instRole.getRepresentedInstance();
              if (inst != null) {
                Type type = inst.getType();
                if (type instanceof Actor) {
                  lcomponentList.add((Actor) type);
                }
              }
            }
          }
          if (!lcomponentList.contains(actor)) {
            return ctx.createFailureStatus(new Object[] { actor.getName(), capabilityReal.getName() });
          }
        }
      }
    }
    return ctx.createSuccessStatus();
  }
}
