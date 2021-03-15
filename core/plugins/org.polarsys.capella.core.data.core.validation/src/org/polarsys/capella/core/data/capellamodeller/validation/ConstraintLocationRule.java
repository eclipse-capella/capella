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
import org.polarsys.capella.core.data.capellacore.Constraint;
import org.polarsys.capella.core.data.cs.Part;
import org.polarsys.capella.core.data.epbs.EPBSArchitecture;
import org.polarsys.capella.core.data.pa.deployment.PartDeploymentLink;
import org.polarsys.capella.core.model.helpers.BlockArchitectureExt;
import org.polarsys.capella.core.model.helpers.CapellaElementExt;
import org.polarsys.capella.core.sirius.analysis.CsServices;
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
      
      if (CsServices.getService().isMultipartMode((ModelElement) eObj)) {
        //multipart mode
        // A constraint should not be stored under PartDeploymentlink
        // Exception : If first ConstrainedElements value is container PartDeploymentlink or empty ConstraintElements value
        if (constraint.eContainer() instanceof PartDeploymentLink && 
            (constrainedElements != null && !constrainedElements.isEmpty()) &&
            !constrainedElements.get(0).equals(constraint.eContainer())) {
          return ctx.createFailureStatus(CapellaElementExt.getValidationRuleMessagePrefix(constraint), "first value of ConstrainedElements");         
        }
      } else {
        //monopart mode
        // Constraint should not be stored under Part/PartDeploymentLink and have ConstrainedElements values as Part/PartDeploymentLinks
        // Exception : In EPBS Layer constraint's can be stored under Part
        if (BlockArchitectureExt.getRootBlockArchitecture(constraint) instanceof EPBSArchitecture) {
          return ctx.createSuccessStatus();
        }
        boolean isConstrainingPartOrPDL = !constrainedElements.stream()
            .filter(element -> (element instanceof Part) || (element instanceof PartDeploymentLink))
            .collect(Collectors.toList()).isEmpty();
        if ((constraint.eContainer() instanceof Part || constraint.eContainer() instanceof PartDeploymentLink) &&
            !isConstrainingPartOrPDL) {
          return ctx.createFailureStatus(CapellaElementExt.getValidationRuleMessagePrefix(constraint), "Component");
        }
      }
    }
    return ctx.createSuccessStatus();
  }
}
