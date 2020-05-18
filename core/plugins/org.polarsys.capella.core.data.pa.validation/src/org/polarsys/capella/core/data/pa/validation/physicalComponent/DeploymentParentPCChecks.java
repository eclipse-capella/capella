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
package org.polarsys.capella.core.data.pa.validation.physicalComponent;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import org.eclipse.core.runtime.IStatus;
import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.validation.EMFEventType;
import org.eclipse.emf.validation.IValidationContext;
import org.eclipse.emf.validation.model.ConstraintStatus;
import org.polarsys.capella.core.data.pa.PhysicalComponent;
import org.polarsys.capella.core.data.pa.PhysicalComponentNature;
import org.polarsys.capella.core.model.helpers.CapellaElementExt;
import org.polarsys.capella.core.model.helpers.PhysicalComponentExt;
import org.polarsys.capella.core.validation.rule.AbstractValidationRule;

/**
 * Check PhysicalComponent correct deployment, following the rules below:
 *    o Node Components (actor or not) can contain Node and deploy Node or Behavior.
 *    o Behavior Components (actor or not) can contain Behavior and deploy behavior.
*/
public class DeploymentParentPCChecks extends AbstractValidationRule {
  /**
   * @see org.eclipse.emf.validation.AbstractModelConstraint#validate(org.eclipse.emf.validation.IValidationContext)
   */
  @Override
  public IStatus validate(IValidationContext ctx) {
    EObject eObj = ctx.getTarget();
    EMFEventType eType = ctx.getEventType();

    Collection<IStatus> statuses = new ArrayList<IStatus>();
    if (eType == EMFEventType.NULL) {
      if (eObj instanceof PhysicalComponent) {
        PhysicalComponent currentElement = (PhysicalComponent) eObj;
        if (PhysicalComponentExt.isNode(currentElement)) {
          // 1. check that NODE PC can't contain BEHAVIOR PC
          List<PhysicalComponent> containedComponents = getContainedComponents(currentElement,
              PhysicalComponentNature.BEHAVIOR);
          if (!containedComponents.isEmpty()) {
            IStatus createFailureStatus = ctx
                .createFailureStatus(CapellaElementExt.getValidationRuleMessagePrefix(currentElement)
                    + "can't contain BEHAVIOR components.");
            statuses.add(createFailureStatus);
          }

        } else if (PhysicalComponentExt.isBehaviour(currentElement)) {
          // 2. check that BEHAVIOR PC can't deploy/contain NODE PC
          List<PhysicalComponent> containedComponents = getContainedComponents(currentElement,
              PhysicalComponentNature.NODE);
          if (!containedComponents.isEmpty()) {
            IStatus createFailureStatus = ctx
                .createFailureStatus(CapellaElementExt.getValidationRuleMessagePrefix(currentElement)
                    + "can't contain NODE components.");
            statuses.add(createFailureStatus);
          }

          List<PhysicalComponent> deployedComponents = getDeployedComponents(currentElement,
              PhysicalComponentNature.NODE);
          if (!deployedComponents.isEmpty()) {
            IStatus createFailureStatus = ctx
                .createFailureStatus(CapellaElementExt.getValidationRuleMessagePrefix(currentElement)
                    + "can't deploy NODE components.");
            statuses.add(createFailureStatus);
          }
        }
      }
    }

    if (!statuses.isEmpty()) {
      return ConstraintStatus.createMultiStatus(ctx, statuses);
    }
    return ctx.createSuccessStatus();
  }
  
  private List<PhysicalComponent> getContainedComponents(PhysicalComponent physicalComponent,
      PhysicalComponentNature nature) {
    List<PhysicalComponent> result = new ArrayList<PhysicalComponent>();
    EList<PhysicalComponent> subComponents = physicalComponent.getSubPhysicalComponents();
    EList<PhysicalComponent> deployedcomponents = physicalComponent.getDeployedPhysicalComponents();
    for (PhysicalComponent component : subComponents) {
      if (component.getNature() == nature && !deployedcomponents.contains(component)) {
        result.add(component);
      }
    }
    return result;
  }

  private List<PhysicalComponent> getDeployedComponents(PhysicalComponent physicalComponent,
      PhysicalComponentNature nature) {
    List<PhysicalComponent> result = new ArrayList<PhysicalComponent>();
    EList<PhysicalComponent> deployedcomponents = physicalComponent.getDeployedPhysicalComponents();
    for (PhysicalComponent component : deployedcomponents) {
      if (component.getNature() == nature) {
        result.add(component);
      }
    }
    return result;
  }
}
