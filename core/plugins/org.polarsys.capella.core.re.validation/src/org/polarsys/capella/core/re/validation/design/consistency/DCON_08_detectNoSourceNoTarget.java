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
package org.polarsys.capella.core.re.validation.design.consistency;

import org.eclipse.core.runtime.IStatus;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.validation.AbstractModelConstraint;
import org.eclipse.emf.validation.IValidationContext;
import org.eclipse.emf.validation.model.ConstraintStatus;
import org.eclipse.emf.validation.service.ConstraintRegistry;
import org.eclipse.emf.validation.service.IConstraintDescriptor;
import org.polarsys.capella.common.re.CatalogElementLink;
import org.polarsys.capella.core.model.utils.NamingHelper;

/**
 * DCON_08 - CatalogElementLink detect no source no target
 *
 */
public class DCON_08_detectNoSourceNoTarget extends AbstractModelConstraint {

  @Override
  public IStatus validate(IValidationContext ctx) {
    if (ctx.getTarget() instanceof CatalogElementLink) {
      CatalogElementLink catalogElementLink = (CatalogElementLink) ctx.getTarget();
      boolean isFailure = false;
      String message = "";
      if (catalogElementLink != null) {
        if (catalogElementLink.getSource() == null) {
          message += "no source";
          isFailure = true;
        }
        if (catalogElementLink.getTarget() == null) {
          message = isFailure ? "neither source nor target" : "no target";
          isFailure = true;
        }

        if (isFailure) {
          return createFailureStatus(ctx, catalogElementLink, catalogElementLink,
              NamingHelper.getTitleLabel(catalogElementLink), message);
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
