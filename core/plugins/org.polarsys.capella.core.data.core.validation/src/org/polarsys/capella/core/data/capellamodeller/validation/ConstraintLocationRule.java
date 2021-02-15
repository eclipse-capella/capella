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
import org.polarsys.capella.core.data.cs.Component;
import org.polarsys.capella.core.data.cs.Part;
import org.polarsys.capella.core.data.pa.deployment.PartDeploymentLink;
import org.polarsys.capella.core.model.helpers.CapellaElementExt;
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
      
      //if constraint is applied to only one Component/PartDeploymentLink AND constraint not stored under Component, then WARNING
      if (constrainedElements != null && constrainedElements.size() == 1) {
        ModelElement element = constrainedElements.get(0);
        ModelElement comp = getComponentFromElement(element);
        if (comp == null || comp.getOwnedConstraints() == null || !comp.getOwnedConstraints().contains(constraint)) {
          return ctx.createFailureStatus(CapellaElementExt.getValidationRuleMessagePrefix(constraint) , "Component");
        }
      }
    }
    return ctx.createSuccessStatus();
  }

  private ModelElement getComponentFromElement(ModelElement element) {
    ModelElement comp = null;
    if (element instanceof Component) {
      comp = (Component) element;
    } else if (element instanceof Part) {
      comp = ((Part) element).getAbstractType();
    } else if (element instanceof PartDeploymentLink) {
      comp = ((PartDeploymentLink) element).getDeployedElement();
      if (comp instanceof Part) {
        comp = ((Part) comp).getAbstractType();
      }
    }
    return comp;
  }
}
