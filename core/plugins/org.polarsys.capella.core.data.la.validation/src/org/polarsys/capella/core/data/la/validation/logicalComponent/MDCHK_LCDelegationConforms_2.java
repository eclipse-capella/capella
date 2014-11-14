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
import java.util.Collection;
import java.util.Iterator;
import java.util.List;

import org.eclipse.core.runtime.IStatus;
import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.validation.EMFEventType;
import org.eclipse.emf.validation.IValidationContext;
import org.eclipse.emf.validation.model.ConstraintStatus;

import org.polarsys.capella.core.data.cs.Interface;
import org.polarsys.capella.core.data.cs.Part;
import org.polarsys.capella.core.data.information.Partition;
import org.polarsys.capella.core.data.la.LogicalComponent;
import org.polarsys.capella.core.model.helpers.ComponentExt;
import org.polarsys.capella.core.validation.rule.AbstractValidationRule;
import org.polarsys.capella.common.data.modellingcore.AbstractType;

/**
 * Check if current LogicalComponent delegate its implemented Interface.
 */
public class MDCHK_LCDelegationConforms_2 extends AbstractValidationRule {
  /**
   * @see org.eclipse.emf.validation.AbstractModelConstraint#validate(org.eclipse.emf.validation.IValidationContext)
   */
  @Override
  public IStatus validate(IValidationContext ctx) {
    EObject eObj = ctx.getTarget();
    EMFEventType eType = ctx.getEventType();

    if (eType == EMFEventType.NULL) {
      if (eObj instanceof LogicalComponent) {
        if (!(eObj.eContainer() instanceof LogicalComponent)) {
          return ctx.createSuccessStatus();
        }
        LogicalComponent lcpnt = (LogicalComponent) eObj;
        boolean localFlag = false;
        // This collection will store the conflicts statuses
        Collection<IStatus> statuses = new ArrayList<IStatus>();
        
        if (ComponentExt.isComposite(lcpnt)) {
          List<LogicalComponent> subLCs = new ArrayList<LogicalComponent>();
          EList<Partition> ownedPartitions = lcpnt.getOwnedPartitions();
          Iterator<Partition> iterator = ownedPartitions.iterator();
          while (iterator.hasNext()) {
            Partition next = iterator.next();
            if (next instanceof Part) {
              Part part = (Part) next;
              AbstractType abstractType = part.getAbstractType();
              if (abstractType instanceof LogicalComponent) {
                subLCs.add((LogicalComponent) abstractType);
              }
            }
          }

          for (Interface itf : ComponentExt.getImplementedInterfaces(lcpnt)) {
            boolean isImplemented = false;
            for (LogicalComponent lc : subLCs) {
              if (ComponentExt.isImplementingInterface(lc, itf)) {
                isImplemented = true;
              }
            }
            if (!isImplemented) {
              localFlag = true;
              // Creates a conflict status
              IStatus failureStatus = createFailureStatus(ctx, new Object[] {lcpnt.getName(), itf.getName()});
              statuses.add(failureStatus);
            }
          }
          
          if (localFlag) {
            // There are conflicts
            // Returns them as a multiStatuses 
            return ConstraintStatus.createMultiStatus(ctx, statuses);
          }
        }
      }
    }
    return ctx.createSuccessStatus();
  }

}
