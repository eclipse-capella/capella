/*******************************************************************************
 * Copyright (c) 2019 THALES GLOBAL SERVICES.
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
package org.polarsys.capella.core.re.validation.integrity;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import org.eclipse.core.runtime.IStatus;
import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EReference;
import org.eclipse.emf.validation.AbstractModelConstraint;
import org.eclipse.emf.validation.IValidationContext;
import org.eclipse.emf.validation.model.ConstraintStatus;
import org.eclipse.emf.validation.service.ConstraintRegistry;
import org.eclipse.emf.validation.service.IConstraintDescriptor;
import org.polarsys.capella.core.model.handler.command.CapellaResourceHelper;
import org.polarsys.capella.core.model.utils.NamingHelper;
import org.polarsys.kitalpha.emde.model.Element;

/**
 * 
 * I_43 - Model Element shall not reference to aird element
 *
 */
public class I_43_ReferencingToAnAirdElement extends AbstractModelConstraint {

  @Override
  public IStatus validate(IValidationContext ctx) {
    if (ctx.getTarget() instanceof Element) {
      EObject currentElement = ctx.getTarget();
      EList<EReference> allReferences = currentElement.eClass().getEAllReferences();
      if (allReferences != null) {
        Collection<IStatus> statuses = new ArrayList<>();
        for (EReference eReference : allReferences) {
          if (!eReference.isDerived()) {
            Object referencedObject = currentElement.eGet(eReference);
            // a reference can be EObject(one ref) or a List(multiple ref)
            if (referencedObject instanceof EObject) {
              statuses.add(validateReference(ctx, currentElement, (EObject) referencedObject));
            } else if (referencedObject instanceof List<?>) {
              @SuppressWarnings("unchecked")
              List<Object> referencedObjectList = (List<Object>) referencedObject;
              for (Object element : referencedObjectList) {
                if (element instanceof EObject) {
                  statuses.add(validateReference(ctx, currentElement, (EObject) element));
                }
              }
            }
          }
        }
        if (!statuses.isEmpty()) {
          return ConstraintStatus.createMultiStatus(ctx, statuses);
        }
      }
    }
    return ctx.createSuccessStatus();
  }

  private IStatus createFailureStatus(IValidationContext ctx, EObject target, Object... messageArgs) {
    IConstraintDescriptor constraintDescriptor = ConstraintRegistry.getInstance()
        .getDescriptor(ctx.getCurrentConstraintId());
    return ConstraintStatus.createStatus(ctx, target, ctx.getResultLocus(), constraintDescriptor.getMessagePattern(),
        messageArgs);
  }

  private IStatus validateReference(IValidationContext ctx, EObject currentElement, EObject targetElement) {
    if (CapellaResourceHelper.isAirdElement(targetElement)) {
      return createFailureStatus(ctx, currentElement, targetElement, NamingHelper.getTitleLabel(currentElement),
          "to aird element");
    }
    return ctx.createSuccessStatus();
  }
}
