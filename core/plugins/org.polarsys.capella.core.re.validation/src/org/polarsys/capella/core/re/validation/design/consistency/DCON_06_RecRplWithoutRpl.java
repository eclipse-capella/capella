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
package org.polarsys.capella.core.re.validation.design.consistency;

import org.eclipse.core.runtime.IStatus;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.validation.AbstractModelConstraint;
import org.eclipse.emf.validation.IValidationContext;
import org.eclipse.emf.validation.model.ConstraintStatus;
import org.eclipse.emf.validation.service.ConstraintRegistry;
import org.eclipse.emf.validation.service.IConstraintDescriptor;
import org.polarsys.capella.common.re.CatalogElement;
import org.polarsys.capella.common.re.CatalogElementKind;
import org.polarsys.capella.common.re.helpers.ReplicableElementExt;

public class DCON_06_RecRplWithoutRpl extends AbstractModelConstraint {

  @Override
  public IStatus validate(IValidationContext ctx) {
    CatalogElement catalogElement = (CatalogElement) ctx.getTarget();
    if (catalogElement.getKind() == CatalogElementKind.REC_RPL) {
      if (ReplicableElementExt.getReplicas(catalogElement).isEmpty()) {
        return createFailureStatus(ctx, catalogElement,
            new Object[] { catalogElement, catalogElement.getKind().toString() });
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
