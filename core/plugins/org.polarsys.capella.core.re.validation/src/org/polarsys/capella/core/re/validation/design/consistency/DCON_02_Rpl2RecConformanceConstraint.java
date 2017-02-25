/*******************************************************************************
 * Copyright (c) 2006, 2017 THALES GLOBAL SERVICES.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *  
 * Contributors:
 *    Thales - initial API and implementation
 *******************************************************************************/
package org.polarsys.capella.core.re.validation.design.consistency;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.NullProgressMonitor;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.validation.AbstractModelConstraint;
import org.eclipse.emf.validation.IValidationContext;
import org.eclipse.emf.validation.model.ConstraintStatus;
import org.eclipse.emf.validation.service.ConstraintRegistry;
import org.eclipse.emf.validation.service.IConstraintDescriptor;
import org.polarsys.capella.common.helpers.EObjectExt;
import org.polarsys.capella.common.re.CatalogElement;
import org.polarsys.capella.common.re.CatalogElementKind;
import org.polarsys.capella.common.re.RePackage;
import org.polarsys.capella.common.re.launcher.Rpl2RecConformanceCheckLauncher;

public class DCON_02_Rpl2RecConformanceConstraint extends AbstractModelConstraint {

  @Override
  public IStatus validate(IValidationContext ctx) {
    CatalogElement catalogElement = (CatalogElement) ctx.getTarget();
    if (catalogElement.getKind() == CatalogElementKind.RPL) {
      return validateRPL(ctx, catalogElement);
    } else if (catalogElement.getKind() == CatalogElementKind.REC) {
      Collection<IStatus> statuses = new ArrayList<IStatus>();
      statuses.add(ctx.createSuccessStatus());
      // Collect all RPL from this REC and validate them
      List<EObject> allRPLs = EObjectExt.getReferencers(catalogElement, RePackage.eINSTANCE.getCatalogElement(),
          RePackage.eINSTANCE.getCatalogElement_Origin());
      ctx.skipCurrentConstraintForAll(allRPLs);
      for (EObject rpl : allRPLs) {
        statuses.add(validateRPL(ctx, (CatalogElement) rpl));
      }
      return ConstraintStatus.createMultiStatus(ctx, statuses);
    } else if(catalogElement.getKind() == CatalogElementKind.REC_RPL){
      // Not supported yet
    }
    return ctx.createSuccessStatus();
  }

  private IStatus validateRPL(IValidationContext ctx, CatalogElement rpl) {
    Collection<Object> selection = new ArrayList<Object>();
    selection.add(rpl);
    Rpl2RecConformanceCheckLauncher launcher = new Rpl2RecConformanceCheckLauncher();
    launcher.run(selection, false, new NullProgressMonitor());
    if (!launcher.isConform()) {
      // When the target is a REC, we need to set the RPL as target of the created failure status to be able to use it
      // in the quick-fix, so create always a status with the RPL as target.
      return createFailureStatus(ctx, rpl, new Object[] { rpl, rpl.getOrigin() });
    }
    return ctx.createSuccessStatus();
  }
  
  private IStatus createFailureStatus(IValidationContext ctx, EObject target, Object... messageArgs) {
    IConstraintDescriptor constraintDescriptor = ConstraintRegistry.getInstance().getDescriptor(
        ctx.getCurrentConstraintId());
    return ConstraintStatus.createStatus(ctx, target, ctx.getResultLocus(), constraintDescriptor.getMessagePattern(),
        messageArgs);
  }
}
