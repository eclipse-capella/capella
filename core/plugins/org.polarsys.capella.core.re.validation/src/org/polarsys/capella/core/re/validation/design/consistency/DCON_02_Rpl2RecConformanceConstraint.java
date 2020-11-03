/*******************************************************************************
 * Copyright (c) 2006, 2020 THALES GLOBAL SERVICES.
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

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.NullProgressMonitor;
import org.eclipse.core.runtime.Status;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.validation.AbstractModelConstraint;
import org.eclipse.emf.validation.IValidationContext;
import org.eclipse.emf.validation.model.ConstraintStatus;
import org.eclipse.emf.validation.service.ConstraintRegistry;
import org.eclipse.emf.validation.service.IConstraintDescriptor;
import org.polarsys.capella.common.helpers.EObjectExt;
import org.polarsys.capella.common.re.Activator;
import org.polarsys.capella.common.re.CatalogElement;
import org.polarsys.capella.common.re.CatalogElementKind;
import org.polarsys.capella.common.re.RePackage;
import org.polarsys.capella.common.re.launcher.Rpl2RecConformanceCheckLauncher;
import org.polarsys.capella.core.transition.common.constants.IOptionsConstants;
import org.polarsys.kitalpha.cadence.core.api.parameter.GenericParameter;

public class DCON_02_Rpl2RecConformanceConstraint extends AbstractModelConstraint {

  @Override
  public IStatus validate(IValidationContext ctx) {
    CatalogElement catalogElement = (CatalogElement) ctx.getTarget();

    if (catalogElement.getKind() == CatalogElementKind.RPL) {
      CatalogElement rec = catalogElement.getOrigin();
      if (rec == null || rec.eIsProxy()) {
        return new Status(IStatus.ERROR, Activator.PLUGIN_ID, "Your RPL is invalid (no REC), please validate your model.");
      }
      // if rec is valid
      return validateRPL(ctx, catalogElement);
    } else if (catalogElement.getKind() == CatalogElementKind.REC) {
      Collection<IStatus> statuses = new ArrayList<>();
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
    Collection<Object> selection = new ArrayList<>();
    selection.add(rpl);
    Rpl2RecConformanceCheckLauncher launcher = new Rpl2RecConformanceCheckLauncher();
    launcher.addSharedParameter(new GenericParameter<Boolean>(IOptionsConstants.IS_DRY_RUN, true, "This is a flag to indicate a dry run"));
    launcher.run(selection, false, new NullProgressMonitor());
    if (!launcher.isConform()) {
      // When the target is a REC, we need to set the RPL as target of the created failure status to be able to use it
      // in the quick-fix, so create always a status with the RPL as target.
      return createFailureStatus(ctx, rpl, rpl, rpl.getOrigin());
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
