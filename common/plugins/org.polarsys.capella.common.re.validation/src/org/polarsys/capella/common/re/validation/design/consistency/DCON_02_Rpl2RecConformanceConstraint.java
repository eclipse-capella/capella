/*******************************************************************************
 * Copyright (c) 2006, 2015 THALES GLOBAL SERVICES.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *  
 * Contributors:
 *    Thales - initial API and implementation
 *******************************************************************************/
package org.polarsys.capella.common.re.validation.design.consistency;

import java.lang.management.ManagementFactory;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.NullProgressMonitor;
import org.eclipse.emf.diffmerge.api.IComparison;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.validation.AbstractModelConstraint;
import org.eclipse.emf.validation.IValidationContext;
import org.eclipse.emf.validation.model.ConstraintStatus;
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
      return validateRPL(catalogElement, ctx);
    }
    else if(catalogElement.getKind() == CatalogElementKind.REC){
      long start = ManagementFactory.getThreadMXBean().getCurrentThreadCpuTime();
      Collection<IStatus> statuses = new ArrayList<IStatus>();
      statuses.add(ctx.createSuccessStatus());
      // Collect all RPL from this REC and validate them
      List<EObject> allRPLs = EObjectExt.getReferencers(catalogElement, RePackage.eINSTANCE.getCatalogElement(), RePackage.eINSTANCE.getCatalogElement_Origin());
      ctx.skipCurrentConstraintForAll(allRPLs);
      for(EObject rpl : allRPLs){
        statuses.add(validateRPL((CatalogElement)rpl, ctx));
      }
      long stop = ManagementFactory.getThreadMXBean().getCurrentThreadCpuTime();
      System.out.println("["+catalogElement.getName()+"] Spent time: "+(stop-start)/1000000 +" ms");
      return ConstraintStatus.createMultiStatus(ctx, statuses);
    }
    return ctx.createSuccessStatus();
  }

  private IStatus validateRPL(CatalogElement rpl, IValidationContext ctx) {
    Collection<Object> selection = new ArrayList<Object>();
    selection.add(rpl);
    Rpl2RecConformanceCheckLauncher launcher = new Rpl2RecConformanceCheckLauncher();
    launcher.run(selection, false, new NullProgressMonitor());
    IComparison comparison = launcher.getComparison();
    if (comparison != null) {
      if (comparison.getNbDifferences() > 0) {
        ctx.skipCurrentConstraintFor(rpl);
        return ctx.createFailureStatus(rpl, rpl.getOrigin());
      }
    }
    return ctx.createSuccessStatus();
  }
}
