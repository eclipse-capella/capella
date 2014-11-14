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
package org.polarsys.capella.core.data.pa.validation.physicalComponent;

import java.util.Iterator;

import org.eclipse.core.runtime.IStatus;
import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.validation.EMFEventType;
import org.eclipse.emf.validation.IValidationContext;

import org.polarsys.capella.core.data.cs.AbstractDeploymentLink;
import org.polarsys.capella.core.data.cs.DeployableElement;
import org.polarsys.capella.core.data.pa.PhysicalComponent;
import org.polarsys.capella.core.data.pa.PhysicalComponentNature;
import org.polarsys.capella.core.validation.rule.AbstractValidationRule;

/**
 * This rule ensures that a behavior PC cannot deploy a node PC.
 */
public class MDCHK_PhysicalComponent_Deployment extends AbstractValidationRule {
  /**
   * @see org.eclipse.emf.validation.AbstractModelConstraint#validate(org.eclipse.emf.validation.IValidationContext)
   */
  @Override
  public IStatus validate(IValidationContext ctx) {
    EObject eObj = ctx.getTarget();
    EMFEventType eType = ctx.getEventType();

    if (eType == EMFEventType.NULL) {
      if (eObj instanceof PhysicalComponent) {
        PhysicalComponent physCpnt = (PhysicalComponent) eObj;
        if (physCpnt.getNature() == PhysicalComponentNature.BEHAVIOR) {
          EList<AbstractDeploymentLink> ownedDeployments = physCpnt.getOwnedDeploymentLinks();
          Iterator<AbstractDeploymentLink> iterator = ownedDeployments.iterator();
          while (iterator.hasNext()) {
            AbstractDeploymentLink next = iterator.next();
            DeployableElement deployedElement = next.getDeployedElement();
            if (deployedElement instanceof PhysicalComponent) {
              PhysicalComponent deployedPc = (PhysicalComponent) deployedElement;
              if (deployedPc.getNature() == PhysicalComponentNature.NODE) {
                return createFailureStatus(ctx, new Object[] { physCpnt.getName(), deployedPc.getName() });
              }
            }
          }
        }
      }
    }
    return ctx.createSuccessStatus();
  }
}
