/*******************************************************************************
 * Copyright (c) 2006, 2014 THALES GLOBAL SERVICES.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *  
 * Contributors:
 *    Thales - initial API and implementation
 *******************************************************************************/
package org.polarsys.capella.core.data.cs.validation.part;

import org.eclipse.core.runtime.IStatus;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.validation.EMFEventType;
import org.eclipse.emf.validation.IValidationContext;

import org.polarsys.capella.core.data.cs.Part;
import org.polarsys.capella.core.data.ctx.Actor;
import org.polarsys.capella.core.data.ctx.System;
import org.polarsys.capella.core.data.ctx.SystemContext;
import org.polarsys.capella.core.data.la.LogicalActor;
import org.polarsys.capella.core.data.la.LogicalContext;
import org.polarsys.capella.core.data.pa.PhysicalActor;
import org.polarsys.capella.core.data.pa.PhysicalContext;
import org.polarsys.capella.core.validation.rule.AbstractValidationRule;
import org.polarsys.capella.common.data.modellingcore.AbstractNamedElement;
import org.polarsys.capella.common.data.modellingcore.AbstractType;

/**
 * Checks the parts locations for actors.
 */
public class MDCHK_Part_PartLocation extends AbstractValidationRule {
  /**
   * @see org.eclipse.emf.validation.AbstractModelConstraint#validate(org.eclipse.emf.validation.IValidationContext)
   */
  @Override
  public IStatus validate(IValidationContext ctx_p) {
    EObject eObj = ctx_p.getTarget();
    EMFEventType eType = ctx_p.getEventType();

    if (eType == EMFEventType.NULL) {
      if (eObj instanceof Part) {
        Part part = (Part) eObj;
        AbstractType partAbstractType = part.getAbstractType();
        if (partAbstractType != null) {
          boolean failure = false;
          if ((partAbstractType instanceof Actor || (partAbstractType instanceof System)) && !(part.eContainer() instanceof SystemContext)) {
            // test at the context level
            failure = true;
          }
          if ((partAbstractType instanceof LogicalActor) && !(part.eContainer() instanceof LogicalContext)) {
            // test at the logical level
            failure = true;
          }
          if ((partAbstractType instanceof PhysicalActor) && !(part.eContainer() instanceof PhysicalContext)) {
            // test at the physical level
            failure = true;
          }
          if (failure) {
            return createFailureStatus(ctx_p, new Object[] { part.getName(), partAbstractType.getName(), partAbstractType.eClass().getName(),
                                                            ((AbstractNamedElement) part.eContainer()).getName(), part.eContainer().eClass().getName() });
          }
        }
      }
    }
    return ctx_p.createSuccessStatus();
  }
}
