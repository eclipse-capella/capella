/*******************************************************************************
 * Copyright (c) 2019 THALES GLOBAL SERVICES.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *  
 * Contributors:
 *    Thales - initial API and implementation
 *******************************************************************************/
package org.polarsys.capella.core.re.validation.integrity;

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
import org.polarsys.kitalpha.emde.model.Element;

/**
 * 
 * I_43 - Element shall not reference to aird element
 *
 */
public class I_43_ReferencingToAnAirdElement extends AbstractModelConstraint {

  @Override
  public IStatus validate(IValidationContext ctx) {
    if (ctx.getTarget() instanceof Element) {
      EObject currentElement = ctx.getTarget();
      EList<EReference> allReferences = currentElement.eClass().getEAllReferences();
      if (allReferences != null) {
        int nbOfAirdElement = 0;
        for (EReference eReference : allReferences) {
          if (!eReference.isDerived()) {
            Object referencedObject = currentElement.eGet(eReference);
            // a reference can be EObject(one ref) or a List(multiple ref)
            nbOfAirdElement = 0;
            if (referencedObject instanceof EObject) {
              nbOfAirdElement += (CapellaResourceHelper.isAirdElement((EObject) referencedObject)) ? 1 : 0;
            } else if (referencedObject instanceof List<?>) {
              @SuppressWarnings("unchecked")
              List<Object> referencedObjectList = (List<Object>) referencedObject;
              for (Object element : referencedObjectList) {
                if (element instanceof EObject) {
                  nbOfAirdElement += (CapellaResourceHelper.isAirdElement((EObject) element)) ? 1 : 0;
                }
              }
            }
            if (nbOfAirdElement == 1) {
              return createFailureStatus(ctx, currentElement, "to aird element");
            } else if (nbOfAirdElement > 2) {
              return createFailureStatus(ctx, currentElement, "to " + nbOfAirdElement + " aird elements");
            }
          }
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
}
