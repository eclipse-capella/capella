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
package org.polarsys.capella.core.data.la.validation.logicalComponent;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.eclipse.core.runtime.IStatus;
import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.validation.EMFEventType;
import org.eclipse.emf.validation.IValidationContext;

import org.polarsys.capella.core.data.cs.Part;
import org.polarsys.capella.core.data.information.Partition;
import org.polarsys.capella.core.data.la.LogicalComponent;
import org.polarsys.capella.core.data.pa.LogicalComponentRealization;
import org.polarsys.capella.core.data.pa.PhysicalComponent;
import org.polarsys.capella.core.validation.rule.AbstractValidationRule;
import org.polarsys.capella.common.data.modellingcore.AbstractTrace;

/**
 * This check insures that a leaf Logical Component is realized by at least one Physical Component.
 */
public class MDCHK_LogicalComponent_Realization_1 extends AbstractValidationRule {
  /**
   * @see org.eclipse.emf.validation.AbstractModelConstraint#validate(org.eclipse.emf.validation.IValidationContext)
   */
  @Override
  public IStatus validate(IValidationContext ctx) {
    EObject eObj = ctx.getTarget();
    EMFEventType eType = ctx.getEventType();
    if (eType == EMFEventType.NULL) {
      if (eObj instanceof LogicalComponent) {
        LogicalComponent logCpnt = (LogicalComponent) eObj;
        // The verification is done only if the Logical Component is a leaf
        // Gets the sub owned parts:
        EList<Partition> ownedPartitions = logCpnt.getOwnedPartitions();
        // will store the owned parts only because the ownedPartitions feature for example store function ports
        List<Part> ownedParts = new ArrayList<Part>();
        Iterator<Partition> iterator = ownedPartitions.iterator();
        while (iterator.hasNext()) {
          Partition next = iterator.next();
          if (next instanceof Part) {
            ownedParts.add((Part) next);
          }
        }
        if (ownedParts.size() == 0) {
          for (AbstractTrace trace : logCpnt.getIncomingTraces()) {
            if ((trace instanceof LogicalComponentRealization) && (trace.getSourceElement() instanceof PhysicalComponent)) {
              return ctx.createSuccessStatus();
            }
          }
          return createFailureStatus(ctx, new Object[] { logCpnt.getName() });
        }
      }
    }
    return ctx.createSuccessStatus();
  }
}
