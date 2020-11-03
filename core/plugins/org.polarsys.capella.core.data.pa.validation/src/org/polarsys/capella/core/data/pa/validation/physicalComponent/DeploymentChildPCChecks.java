/*******************************************************************************
 * Copyright (c) 2019, 2020 THALES GLOBAL SERVICES.
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

import java.util.List;

import org.eclipse.core.runtime.IStatus;
import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.validation.EMFEventType;
import org.eclipse.emf.validation.IValidationContext;
import org.polarsys.capella.core.data.cs.Component;
import org.polarsys.capella.core.data.pa.PhysicalComponent;
import org.polarsys.capella.core.data.pa.PhysicalComponentNature;
import org.polarsys.capella.core.model.helpers.CapellaElementExt;
import org.polarsys.capella.core.model.helpers.ComponentExt;
import org.polarsys.capella.core.model.helpers.PhysicalComponentExt;
import org.polarsys.capella.core.validation.rule.AbstractValidationRule;

/**
 * Check PhysicalComponent correct deployment, following the rules below:
 *    o Node Components/Actors can't be deployed/contained in BEHAVIOR PC.
 *    o Behavior Components/Actors can't be contained in NODE PC.
*/
public class DeploymentChildPCChecks extends AbstractValidationRule {
  /**
   * @see org.eclipse.emf.validation.AbstractModelConstraint#validate(org.eclipse.emf.validation.IValidationContext)
   */
  @Override
  public IStatus validate(IValidationContext ctx) {
    EObject eObj = ctx.getTarget();
    EMFEventType eType = ctx.getEventType();

    // check that the child PC/PA (given in ctx as parameter) is correctly deployed/contained by a parent container
    if (eType == EMFEventType.NULL) {
      // current component/actor : assuming it could be deployed
      if (eObj instanceof PhysicalComponent) {
        PhysicalComponent currentElement = (PhysicalComponent) eObj;
        if (PhysicalComponentExt.isNode(currentElement)) {
          // 1. check that NODE PC/PA can't be contained or deployed on BEHAVIOR PC/PA
          PhysicalComponent deployingBehaviorPC = getDeployingComponent(currentElement,
              PhysicalComponentNature.BEHAVIOR);
          if (deployingBehaviorPC != null) {
            return ctx.createFailureStatus(CapellaElementExt.getValidationRuleMessagePrefix(currentElement)
                + "can't be deployed on " + CapellaElementExt.getValidationRuleMessagePrefix(deployingBehaviorPC));
          }
          PhysicalComponent behaviorPC = getContainingComponent(currentElement, PhysicalComponentNature.BEHAVIOR);
          if (behaviorPC != null) {
            return ctx.createFailureStatus(CapellaElementExt.getValidationRuleMessagePrefix(currentElement)
                + "can't be contained in " + CapellaElementExt.getValidationRuleMessagePrefix(behaviorPC));
          }
        } else if (PhysicalComponentExt.isBehaviour(currentElement)) {
          // 2. check that BEHAVIOR PC/PA can't be contained on NODE PC/PA
          PhysicalComponent nodePC = getContainingComponent(currentElement, PhysicalComponentNature.NODE);
          if (nodePC != null) {
            return ctx.createFailureStatus(CapellaElementExt.getValidationRuleMessagePrefix(currentElement)
                + "can't be contained in " + CapellaElementExt.getValidationRuleMessagePrefix(nodePC));
          }
        }
      }
    }
    return ctx.createSuccessStatus();
  }

  protected PhysicalComponent getDeployingComponent(PhysicalComponent physicalComponent,
      PhysicalComponentNature nature) {
    EList<PhysicalComponent> deployingPhysicalComponents = physicalComponent.getDeployingPhysicalComponents();
    for (PhysicalComponent capellaElement : deployingPhysicalComponents) {
      if (capellaElement.getNature() == nature) {
        return capellaElement;
      }
    }
    return null;
  }

  protected PhysicalComponent getContainingComponent(PhysicalComponent physicalComponent,
      PhysicalComponentNature nature) {
    List<Component> allAncestors = ComponentExt.getAllPartitionableElementAncestors(physicalComponent);
    for (Component component : allAncestors) {
      if (component instanceof PhysicalComponent) {
        PhysicalComponent comp = (PhysicalComponent) component;
        if (comp.getNature() == nature && !PhysicalComponentExt.isPhysicalComponentRoot(comp)) {
          return comp;
        }
      }
    }
    return null;
  }
}
