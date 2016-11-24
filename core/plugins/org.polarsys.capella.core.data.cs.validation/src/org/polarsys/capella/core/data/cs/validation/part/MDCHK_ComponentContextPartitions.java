/*******************************************************************************
 * Copyright (c) 2006, 2016 THALES GLOBAL SERVICES.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *  
 * Contributors:
 *    Thales - initial API and implementation
 *******************************************************************************/
package org.polarsys.capella.core.data.cs.validation.part;

import java.util.Iterator;

import org.eclipse.core.runtime.IStatus;
import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.validation.EMFEventType;
import org.eclipse.emf.validation.IValidationContext;

import org.polarsys.capella.core.data.cs.ComponentContext;
import org.polarsys.capella.core.data.ctx.System;
import org.polarsys.capella.core.data.ctx.SystemContext;
import org.polarsys.capella.core.data.epbs.ConfigurationItem;
import org.polarsys.capella.core.data.epbs.EPBSContext;
import org.polarsys.capella.core.data.information.Partition;
import org.polarsys.capella.core.data.la.LogicalComponent;
import org.polarsys.capella.core.data.la.LogicalContext;
import org.polarsys.capella.core.data.oa.OperationalContext;
import org.polarsys.capella.core.data.pa.PhysicalComponent;
import org.polarsys.capella.core.data.pa.PhysicalContext;
import org.polarsys.capella.core.validation.rule.AbstractValidationRule;

/**
 * Checks that context packages contain at least one component (the root component) of the correct level.
 */
public class MDCHK_ComponentContextPartitions extends AbstractValidationRule {
  /**
   * @see org.eclipse.emf.validation.AbstractModelConstraint#validate(org.eclipse.emf.validation.IValidationContext)
   */
  @Override
  public IStatus validate(IValidationContext ctx) {
    EObject eObj = ctx.getTarget();
    EMFEventType eType = ctx.getEventType();
    if (eType == EMFEventType.NULL) {
      if (eObj instanceof ComponentContext) {
        ComponentContext ccontext = (ComponentContext) eObj;
        EList<Partition> ownedPartitions = ccontext.getOwnedPartitions();
        Iterator<Partition> iterator = ownedPartitions.iterator();
        if (ccontext instanceof OperationalContext) {
          return ctx.createSuccessStatus();
        }else if (ccontext instanceof SystemContext) {
          while (iterator.hasNext()) {
            Partition part = iterator.next();
            if (part.getAbstractType() instanceof System) {
              return ctx.createSuccessStatus();
            }
          }
        } else if (ccontext instanceof LogicalContext) {
          while (iterator.hasNext()) {
            Partition part = iterator.next();
            if (part.getAbstractType() instanceof LogicalComponent) {
              return ctx.createSuccessStatus();
            }
          }
        } else if (ccontext instanceof PhysicalContext) {
          while (iterator.hasNext()) {
            Partition part = iterator.next();
            if (part.getAbstractType() instanceof PhysicalComponent) {
              return ctx.createSuccessStatus();
            }
          }
        } else if (ccontext instanceof EPBSContext) {
            while (iterator.hasNext()) {
                Partition part = iterator.next();
                if (part.getAbstractType() instanceof ConfigurationItem) {
                  return ctx.createSuccessStatus();
                }
              }
         }
            
        
        return createFailureStatus(ctx, new Object[] { ccontext.getName(), ccontext.eClass().getName() });
      }
    }
    return ctx.createSuccessStatus();
  }
}
