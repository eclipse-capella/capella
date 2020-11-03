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
package org.polarsys.capella.core.data.ctx.validation.mission;

import java.util.ArrayList;
import java.util.Collection;

import org.eclipse.core.runtime.IStatus;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.validation.EMFEventType;
import org.eclipse.emf.validation.IValidationContext;
import org.eclipse.emf.validation.model.ConstraintStatus;
import org.polarsys.capella.core.data.ctx.Capability;
import org.polarsys.capella.core.data.ctx.Mission;
import org.polarsys.capella.core.data.ctx.SystemComponent;
import org.polarsys.capella.core.model.helpers.BlockArchitectureExt;
import org.polarsys.capella.core.model.helpers.CapellaElementExt;
import org.polarsys.capella.core.validation.rule.AbstractValidationRule;

public class MDCHK_Mission_Inclusion_1 extends AbstractValidationRule {
  /**
   * @see org.eclipse.emf.validation.AbstractModelConstraint#validate(org.eclipse.emf.validation.IValidationContext)
   */
  @Override
  public IStatus validate(IValidationContext ctx) {
    EObject eObj = ctx.getTarget();
    EMFEventType eType = ctx.getEventType();
    Collection<IStatus> statuses = new ArrayList<IStatus>();
    if (eType == EMFEventType.NULL) {
      if (eObj instanceof Mission) {
        Mission mission = (Mission) eObj;

        for (Capability usecase : mission.getExploitedCapabilities()) {
          for (SystemComponent component : usecase.getInvolvedSystemComponents()) {
            if (!BlockArchitectureExt.isRootComponent(component)
                && !mission.getInvolvedSystemComponents().contains(component)) {
              IStatus status = ctx
                  .createFailureStatus(new Object[] { CapellaElementExt.getValidationRuleMessagePrefix(component),
                      mission.getName(), usecase.getName() });
              statuses.add(status);
            }
          }
        }
      }
    }
    if(statuses.size()>0){
      return ConstraintStatus.createMultiStatus(ctx, statuses);
    }
    return ctx.createSuccessStatus();
  }

}
