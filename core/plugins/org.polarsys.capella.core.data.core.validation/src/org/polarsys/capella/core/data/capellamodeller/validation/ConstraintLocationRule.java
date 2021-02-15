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
package org.polarsys.capella.core.data.capellamodeller.validation;

import java.util.stream.Collectors;

import org.eclipse.core.runtime.IStatus;
import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.validation.EMFEventType;
import org.eclipse.emf.validation.IValidationContext;
import org.polarsys.capella.common.data.modellingcore.ModelElement;
import org.polarsys.capella.common.helpers.EObjectLabelProviderHelper;
import org.polarsys.capella.core.data.capellacore.Constraint;
import org.polarsys.capella.core.data.cs.Part;
import org.polarsys.capella.core.data.pa.deployment.PartDeploymentLink;
import org.polarsys.capella.core.validation.rule.AbstractValidationRule;

/**
 */
public class ConstraintLocationRule extends AbstractValidationRule {
  /**
   * @see org.eclipse.emf.validation.AbstractModelConstraint#validate(org.eclipse.emf.validation.IValidationContext)
   */
  @Override
  public IStatus validate(IValidationContext ctx) {
    EObject eObj = ctx.getTarget();
    EMFEventType eType = ctx.getEventType();
    if (eType == EMFEventType.NULL && eObj instanceof Constraint) {
      Constraint constraint = (Constraint) eObj;
      EList<ModelElement> constrainedElements = constraint.getConstrainedElements();
      
      boolean isConstrainingParts = constrainedElements.stream().filter(element -> element instanceof Part)
          .collect(Collectors.toList()).size() > 0;
      boolean isConstrainingDeployments = constrainedElements.stream().filter(element -> element instanceof PartDeploymentLink)
          .collect(Collectors.toList()).size() > 0;
      if ((constraint.eContainer() instanceof Part && !isConstrainingParts && !isConstrainingDeployments) ||
          (constraint.eContainer() instanceof PartDeploymentLink && !isConstrainingDeployments)) {
        return ctx.createFailureStatus(EObjectLabelProviderHelper.getText(constraint), "Component");
      }
    }
    return ctx.createSuccessStatus();
  }
}
