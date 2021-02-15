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

import org.eclipse.core.runtime.IStatus;
import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.validation.EMFEventType;
import org.eclipse.emf.validation.IValidationContext;
import org.polarsys.capella.common.data.modellingcore.ModelElement;
import org.polarsys.capella.core.data.capellacore.Constraint;
import org.polarsys.capella.core.data.cs.Part;
import org.polarsys.capella.core.data.pa.deployment.PartDeploymentLink;
import org.polarsys.capella.core.model.helpers.CapellaElementExt;
import org.polarsys.capella.core.sirius.analysis.CsServices;
import org.polarsys.capella.core.validation.rule.AbstractValidationRule;

/**
 */
public class ConstraintAssignmentRule extends AbstractValidationRule {
  /**
   * @see org.eclipse.emf.validation.AbstractModelConstraint#validate(org.eclipse.emf.validation.IValidationContext)
   */
  @Override
  public IStatus validate(IValidationContext ctx) {
    EObject eObj = ctx.getTarget();
    EMFEventType eType = ctx.getEventType();
    if (eType == EMFEventType.NULL && eObj instanceof Constraint && !CsServices.getService().isMultipartMode((ModelElement) eObj)) {
      Constraint constraint = (Constraint) eObj;
      //if one of the constrained elements is a Part or PartDeploymentLink, WARNING
      EList<ModelElement> constrainedElements = constraint.getConstrainedElements();
      for (ModelElement element : constrainedElements) {
        if (element instanceof Part || element instanceof PartDeploymentLink) {
          return ctx.createFailureStatus(CapellaElementExt.getValidationRuleMessagePrefix(constraint) , element.eClass().getName());
        }
      }
    }
    return ctx.createSuccessStatus();
  }
}
