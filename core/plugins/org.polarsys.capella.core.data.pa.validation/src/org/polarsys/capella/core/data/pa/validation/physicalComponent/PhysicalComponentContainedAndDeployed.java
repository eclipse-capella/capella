/*******************************************************************************
 * Copyright (c) 2021 THALES GLOBAL SERVICES.
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

import org.eclipse.core.runtime.IStatus;
import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.validation.EMFEventType;
import org.eclipse.emf.validation.IValidationContext;
import org.eclipse.emf.validation.model.ConstraintStatus;
import org.polarsys.capella.common.helpers.EObjectLabelProviderHelper;
import org.polarsys.capella.core.data.pa.PhysicalComponent;
import org.polarsys.capella.core.model.helpers.ComponentExt;
import org.polarsys.capella.core.model.helpers.PhysicalComponentExt;
import org.polarsys.capella.core.validation.rule.AbstractValidationRule;

/**
 * DWF_DC_44 - Physical Component NODE/BEHAVIOUR is contained and deployed inside the same parent Component
 */
public class PhysicalComponentContainedAndDeployed extends AbstractValidationRule {
  private String validatorMessage = "{0} (PhysicalComponent) of Nature {1} should not be contained and deployed in the same parent {2} (PhysicalComponent)";
  
  /**
   * @see org.eclipse.emf.validation.AbstractModelConstraint#validate(org.eclipse.emf.validation.IValidationContext)
   */
  @Override
  public IStatus validate(IValidationContext ctx) {
    EObject eObj = ctx.getTarget();
    EMFEventType eType = ctx.getEventType();

    if (eType == EMFEventType.NULL && eObj instanceof PhysicalComponent) {
      PhysicalComponent physicalComponent = (PhysicalComponent) eObj;
      if (!ComponentExt.isActor(physicalComponent) && 
          (PhysicalComponentExt.isNode(physicalComponent) || PhysicalComponentExt.isBehaviour(physicalComponent))) {
        EList<PhysicalComponent> deployingPhysicalComponents = physicalComponent.getDeployingPhysicalComponents();
        EObject parent = physicalComponent.eContainer();
        if(parent instanceof PhysicalComponent && deployingPhysicalComponents.contains(parent)) {
          
          Collection<EObject> resultLocus = new ArrayList<>();
          resultLocus.add(physicalComponent);
          resultLocus.add(parent);
          return ConstraintStatus.createStatus(ctx, eObj, resultLocus,
              validatorMessage,
              physicalComponent.getName(), physicalComponent.getNature(), EObjectLabelProviderHelper.getText(parent));
        }
      }
    }
    return ctx.createSuccessStatus();
  }
}
